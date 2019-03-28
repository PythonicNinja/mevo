package com.google.maps.android.heatmaps;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.support.v4.util.LongSparseArray;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Tile;
import com.google.android.gms.maps.model.TileProvider;
import com.google.maps.android.geometry.Bounds;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.quadtree.PointQuadTree;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class HeatmapTileProvider implements TileProvider {
    public static final Gradient DEFAULT_GRADIENT = new Gradient(DEFAULT_GRADIENT_COLORS, DEFAULT_GRADIENT_START_POINTS);
    private static final int[] DEFAULT_GRADIENT_COLORS = new int[]{Color.rgb(102, 225, 0), Color.rgb(255, 0, 0)};
    private static final float[] DEFAULT_GRADIENT_START_POINTS = new float[]{0.2f, 1.0f};
    private static final int DEFAULT_MAX_ZOOM = 11;
    private static final int DEFAULT_MIN_ZOOM = 5;
    public static final double DEFAULT_OPACITY = 0.7d;
    public static final int DEFAULT_RADIUS = 20;
    private static final int MAX_RADIUS = 50;
    private static final int MAX_ZOOM_LEVEL = 22;
    private static final int MIN_RADIUS = 10;
    private static final int SCREEN_SIZE = 1280;
    private static final int TILE_DIM = 512;
    static final double WORLD_WIDTH = 1.0d;
    private Bounds mBounds;
    private int[] mColorMap;
    private Collection<WeightedLatLng> mData;
    private Gradient mGradient;
    private double[] mKernel;
    private double[] mMaxIntensity;
    private double mOpacity;
    private int mRadius;
    private PointQuadTree<WeightedLatLng> mTree;

    public static class Builder {
        private Collection<WeightedLatLng> data;
        private Gradient gradient = HeatmapTileProvider.DEFAULT_GRADIENT;
        private double opacity = 0.7d;
        private int radius = 20;

        public Builder data(Collection<LatLng> collection) {
            return weightedData(HeatmapTileProvider.wrapData(collection));
        }

        public Builder weightedData(Collection<WeightedLatLng> collection) {
            this.data = collection;
            if (this.data.isEmpty() == null) {
                return this;
            }
            throw new IllegalArgumentException("No input points.");
        }

        public Builder radius(int i) {
            this.radius = i;
            if (this.radius >= 10) {
                if (this.radius <= 50) {
                    return this;
                }
            }
            throw new IllegalArgumentException("Radius not within bounds.");
        }

        public Builder gradient(Gradient gradient) {
            this.gradient = gradient;
            return this;
        }

        public Builder opacity(double d) {
            this.opacity = d;
            if (this.opacity >= 0.0d) {
                if (this.opacity <= 1.0d) {
                    return this;
                }
            }
            throw new IllegalArgumentException("Opacity must be in range [0, 1]");
        }

        public HeatmapTileProvider build() {
            if (this.data != null) {
                return new HeatmapTileProvider();
            }
            throw new IllegalStateException("No input data: you must use either .data or .weightedData before building");
        }
    }

    private HeatmapTileProvider(Builder builder) {
        this.mData = builder.data;
        this.mRadius = builder.radius;
        this.mGradient = builder.gradient;
        this.mOpacity = builder.opacity;
        this.mKernel = generateKernel(this.mRadius, ((double) this.mRadius) / 3.0d);
        setGradient(this.mGradient);
        setWeightedData(this.mData);
    }

    public void setWeightedData(Collection<WeightedLatLng> collection) {
        this.mData = collection;
        if (this.mData.isEmpty() != null) {
            throw new IllegalArgumentException("No input points.");
        }
        this.mBounds = getBounds(this.mData);
        this.mTree = new PointQuadTree(this.mBounds);
        for (WeightedLatLng add : this.mData) {
            this.mTree.add(add);
        }
        this.mMaxIntensity = getMaxIntensities(this.mRadius);
    }

    public void setData(Collection<LatLng> collection) {
        setWeightedData(wrapData(collection));
    }

    private static Collection<WeightedLatLng> wrapData(Collection<LatLng> collection) {
        Collection arrayList = new ArrayList();
        for (LatLng weightedLatLng : collection) {
            arrayList.add(new WeightedLatLng(weightedLatLng));
        }
        return arrayList;
    }

    public Tile getTile(int i, int i2, int i3) {
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        double d = 1.0d;
        double pow = 1.0d / Math.pow(2.0d, (double) i6);
        double d2 = (((double) this.mRadius) * pow) / 512.0d;
        double d3 = ((2.0d * d2) + pow) / ((double) ((this.mRadius * 2) + 512));
        double d4 = (((double) i4) * pow) - d2;
        double d5 = (((double) (i4 + 1)) * pow) + d2;
        double d6 = (((double) i5) * pow) - d2;
        double d7 = (((double) (i5 + 1)) * pow) + d2;
        Collection arrayList = new ArrayList();
        if (d4 < 0.0d) {
            d = -1.0d;
            arrayList = r0.mTree.search(new Bounds(d4 + 1.0d, 1.0d, d6, d7));
        } else if (d5 > 1.0d) {
            arrayList = r0.mTree.search(new Bounds(0.0d, d5 - 1.0d, d6, d7));
        } else {
            d = 0.0d;
        }
        Bounds bounds = new Bounds(d4, d5, d6, d7);
        if (!bounds.intersects(new Bounds(r0.mBounds.minX - d2, r0.mBounds.maxX + d2, r0.mBounds.minY - d2, r0.mBounds.maxY + d2))) {
            return TileProvider.NO_TILE;
        }
        Collection<WeightedLatLng> search = r0.mTree.search(bounds);
        if (search.isEmpty()) {
            return TileProvider.NO_TILE;
        }
        double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{(r0.mRadius * 2) + 512, (r0.mRadius * 2) + 512});
        for (WeightedLatLng weightedLatLng : search) {
            Point point = weightedLatLng.getPoint();
            int i7 = (int) ((point.f14x - d4) / d3);
            int i8 = (int) ((point.f15y - d6) / d3);
            double[] dArr2 = dArr[i7];
            dArr2[i8] = dArr2[i8] + weightedLatLng.getIntensity();
        }
        for (WeightedLatLng weightedLatLng2 : r4) {
            Point point2 = weightedLatLng2.getPoint();
            i8 = (int) (((point2.f14x + d) - d4) / d3);
            int i9 = (int) ((point2.f15y - d6) / d3);
            double[] dArr3 = dArr[i8];
            dArr3[i9] = dArr3[i9] + weightedLatLng2.getIntensity();
        }
        return convertBitmap(colorize(convolve(dArr, r0.mKernel), r0.mColorMap, r0.mMaxIntensity[i6]));
    }

    public void setGradient(Gradient gradient) {
        this.mGradient = gradient;
        this.mColorMap = gradient.generateColorMap(this.mOpacity);
    }

    public void setRadius(int i) {
        this.mRadius = i;
        this.mKernel = generateKernel(this.mRadius, ((double) this.mRadius) / 3.0d);
        this.mMaxIntensity = getMaxIntensities(this.mRadius);
    }

    public void setOpacity(double d) {
        this.mOpacity = d;
        setGradient(this.mGradient);
    }

    private double[] getMaxIntensities(int i) {
        double[] dArr = new double[22];
        int i2 = 5;
        while (true) {
            int i3 = 11;
            if (i2 >= 11) {
                break;
            }
            dArr[i2] = getMaxValue(this.mData, this.mBounds, i, (int) (Math.pow(2.0d, (double) (i2 - 3)) * 1280.0d));
            if (i2 == 5) {
                for (i3 = 0; i3 < i2; i3++) {
                    dArr[i3] = dArr[i2];
                }
            }
            i2++;
        }
        while (i3 < 22) {
            dArr[i3] = dArr[10];
            i3++;
        }
        return dArr;
    }

    private static Tile convertBitmap(Bitmap bitmap) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        return new Tile(512, 512, byteArrayOutputStream.toByteArray());
    }

    static Bounds getBounds(Collection<WeightedLatLng> collection) {
        Iterator it = collection.iterator();
        WeightedLatLng weightedLatLng = (WeightedLatLng) it.next();
        double d = weightedLatLng.getPoint().f14x;
        double d2 = weightedLatLng.getPoint().f14x;
        double d3 = d;
        double d4 = d2;
        double d5 = weightedLatLng.getPoint().f15y;
        double d6 = weightedLatLng.getPoint().f15y;
        while (it.hasNext()) {
            weightedLatLng = (WeightedLatLng) it.next();
            d = weightedLatLng.getPoint().f14x;
            d2 = weightedLatLng.getPoint().f15y;
            if (d < d3) {
                d3 = d;
            }
            if (d > d4) {
                d4 = d;
            }
            if (d2 < d5) {
                d5 = d2;
            }
            if (d2 > d6) {
                d6 = d2;
            }
        }
        return new Bounds(d3, d4, d5, d6);
    }

    static double[] generateKernel(int i, double d) {
        double[] dArr = new double[((i * 2) + 1)];
        for (int i2 = -i; i2 <= i; i2++) {
            dArr[i2 + i] = Math.exp(((double) ((-i2) * i2)) / ((2.0d * d) * d));
        }
        return dArr;
    }

    static double[][] convolve(double[][] dArr, double[] dArr2) {
        int i;
        int i2;
        double[][] dArr3 = dArr;
        double[] dArr4 = dArr2;
        int floor = (int) Math.floor(((double) dArr4.length) / 2.0d);
        int length = dArr3.length;
        int i3 = length - (floor * 2);
        int i4 = (floor + i3) - 1;
        double[][] dArr5 = (double[][]) Array.newInstance(double.class, new int[]{length, length});
        for (i = 0; i < length; i++) {
            for (int i5 = 0; i5 < length; i5++) {
                double d = dArr3[i][i5];
                if (d != 0.0d) {
                    int i6 = i + floor;
                    if (i4 < i6) {
                        i6 = i4;
                    }
                    i6++;
                    int i7 = i - floor;
                    for (i2 = floor > i7 ? floor : i7; i2 < i6; i2++) {
                        double[] dArr6 = dArr5[i2];
                        dArr6[i5] = dArr6[i5] + (dArr4[i2 - i7] * d);
                    }
                }
            }
        }
        dArr3 = (double[][]) Array.newInstance(double.class, new int[]{i3, i3});
        for (i3 = floor; i3 < i4 + 1; i3++) {
            for (i2 = 0; i2 < length; i2++) {
                double d2 = dArr5[i3][i2];
                if (d2 != 0.0d) {
                    i = i2 + floor;
                    if (i4 < i) {
                        i = i4;
                    }
                    i++;
                    int i8 = i2 - floor;
                    int i9 = floor > i8 ? floor : i8;
                    while (i9 < i) {
                        double[] dArr7 = dArr3[i3 - floor];
                        int i10 = i9 - floor;
                        dArr7[i10] = dArr7[i10] + (dArr4[i9 - i8] * d2);
                        i9++;
                    }
                }
            }
        }
        return dArr3;
    }

    static Bitmap colorize(double[][] dArr, int[] iArr, double d) {
        double[][] dArr2 = dArr;
        int[] iArr2 = iArr;
        int i = iArr2[iArr2.length - 1];
        double length = ((double) (iArr2.length - 1)) / d;
        int length2 = dArr2.length;
        int[] iArr3 = new int[(length2 * length2)];
        for (int i2 = 0; i2 < length2; i2++) {
            for (int i3 = 0; i3 < length2; i3++) {
                double d2 = dArr2[i3][i2];
                int i4 = (i2 * length2) + i3;
                int i5 = (int) (d2 * length);
                if (d2 == 0.0d) {
                    iArr3[i4] = 0;
                } else if (i5 < iArr2.length) {
                    iArr3[i4] = iArr2[i5];
                } else {
                    iArr3[i4] = i;
                }
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(length2, length2, Config.ARGB_8888);
        createBitmap.setPixels(iArr3, 0, length2, 0, 0, length2, length2);
        return createBitmap;
    }

    static double getMaxValue(Collection<WeightedLatLng> collection, Bounds bounds, int i, int i2) {
        Bounds bounds2 = bounds;
        double d = bounds2.minX;
        double d2 = bounds2.maxX;
        double d3 = bounds2.minY;
        d2 -= d;
        double d4 = bounds2.maxY - d3;
        if (d2 <= d4) {
            d2 = d4;
        }
        d4 = ((double) ((int) (((double) (i2 / (i * 2))) + 0.5d))) / d2;
        LongSparseArray longSparseArray = new LongSparseArray();
        double d5 = 0.0d;
        for (WeightedLatLng weightedLatLng : collection) {
            LongSparseArray longSparseArray2;
            double d6;
            int i3 = (int) ((weightedLatLng.getPoint().f15y - d3) * d4);
            long j = (long) ((int) ((weightedLatLng.getPoint().f14x - d) * d4));
            LongSparseArray longSparseArray3 = (LongSparseArray) longSparseArray.get(j);
            if (longSparseArray3 == null) {
                longSparseArray3 = new LongSparseArray();
                longSparseArray.put(j, longSparseArray3);
            }
            j = (long) i3;
            Double d7 = (Double) longSparseArray3.get(j);
            if (d7 == null) {
                longSparseArray2 = longSparseArray;
                d6 = d;
                d7 = Double.valueOf(0.0d);
            } else {
                longSparseArray2 = longSparseArray;
                d6 = d;
            }
            Double valueOf = Double.valueOf(d7.doubleValue() + weightedLatLng.getIntensity());
            longSparseArray3.put(j, valueOf);
            if (valueOf.doubleValue() > d5) {
                d5 = valueOf.doubleValue();
            }
            d = d6;
            longSparseArray = longSparseArray2;
        }
        return d5;
    }
}
