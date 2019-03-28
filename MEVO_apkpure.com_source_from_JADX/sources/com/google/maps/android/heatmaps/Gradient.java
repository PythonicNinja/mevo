package com.google.maps.android.heatmaps;

import android.graphics.Color;
import java.util.HashMap;

public class Gradient {
    private static final int DEFAULT_COLOR_MAP_SIZE = 1000;
    public final int mColorMapSize;
    public int[] mColors;
    public float[] mStartPoints;

    private class ColorInterval {
        private final int color1;
        private final int color2;
        private final float duration;

        private ColorInterval(int i, int i2, float f) {
            this.color1 = i;
            this.color2 = i2;
            this.duration = f;
        }
    }

    public Gradient(int[] iArr, float[] fArr) {
        this(iArr, fArr, 1000);
    }

    public Gradient(int[] iArr, float[] fArr, int i) {
        if (iArr.length != fArr.length) {
            throw new IllegalArgumentException("colors and startPoints should be same length");
        } else if (iArr.length == 0) {
            throw new IllegalArgumentException("No colors have been defined");
        } else {
            for (int i2 = 1; i2 < fArr.length; i2++) {
                if (fArr[i2] <= fArr[i2 - 1]) {
                    throw new IllegalArgumentException("startPoints should be in increasing order");
                }
            }
            this.mColorMapSize = i;
            this.mColors = new int[iArr.length];
            this.mStartPoints = new float[fArr.length];
            System.arraycopy(iArr, 0, this.mColors, 0, iArr.length);
            System.arraycopy(fArr, 0, this.mStartPoints, 0, fArr.length);
        }
    }

    private HashMap<Integer, ColorInterval> generateColorIntervals() {
        int i;
        HashMap<Integer, ColorInterval> hashMap = new HashMap();
        if (this.mStartPoints[0] != 0.0f) {
            hashMap.put(Integer.valueOf(0), new ColorInterval(Color.argb(0, Color.red(this.mColors[0]), Color.green(this.mColors[0]), Color.blue(this.mColors[0])), this.mColors[0], ((float) this.mColorMapSize) * this.mStartPoints[0]));
        }
        for (i = 1; i < this.mColors.length; i++) {
            int i2 = i - 1;
            hashMap.put(Integer.valueOf((int) (((float) this.mColorMapSize) * this.mStartPoints[i2])), new ColorInterval(this.mColors[i2], this.mColors[i], ((float) this.mColorMapSize) * (this.mStartPoints[i] - this.mStartPoints[i2])));
        }
        if (this.mStartPoints[this.mStartPoints.length - 1] != 1.0f) {
            i = this.mStartPoints.length - 1;
            hashMap.put(Integer.valueOf((int) (((float) this.mColorMapSize) * this.mStartPoints[i])), new ColorInterval(this.mColors[i], this.mColors[i], ((float) this.mColorMapSize) * (1.0f - this.mStartPoints[i])));
        }
        return hashMap;
    }

    int[] generateColorMap(double d) {
        HashMap generateColorIntervals = generateColorIntervals();
        int[] iArr = new int[this.mColorMapSize];
        int i = 0;
        ColorInterval colorInterval = (ColorInterval) generateColorIntervals.get(Integer.valueOf(0));
        int i2 = 0;
        for (int i3 = 0; i3 < this.mColorMapSize; i3++) {
            if (generateColorIntervals.containsKey(Integer.valueOf(i3))) {
                colorInterval = (ColorInterval) generateColorIntervals.get(Integer.valueOf(i3));
                i2 = i3;
            }
            iArr[i3] = interpolateColor(colorInterval.color1, colorInterval.color2, ((float) (i3 - i2)) / colorInterval.duration);
        }
        if (d != 1.0d) {
            while (i < this.mColorMapSize) {
                int i4 = iArr[i];
                iArr[i] = Color.argb((int) (((double) Color.alpha(i4)) * d), Color.red(i4), Color.green(i4), Color.blue(i4));
                i++;
            }
        }
        return iArr;
    }

    static int interpolateColor(int i, int i2, float f) {
        int alpha = (int) ((((float) (Color.alpha(i2) - Color.alpha(i))) * f) + ((float) Color.alpha(i)));
        float[] fArr = new float[3];
        Color.RGBToHSV(Color.red(i), Color.green(i), Color.blue(i), fArr);
        i = new float[3];
        Color.RGBToHSV(Color.red(i2), Color.green(i2), Color.blue(i2), i);
        i2 = 0;
        if (fArr[0] - i[0] > 180.0f) {
            i[0] = i[0] + 360.0f;
        } else if (i[0] - fArr[0] > 180.0f) {
            fArr[0] = fArr[0] + 360.0f;
        }
        float[] fArr2 = new float[3];
        while (i2 < 3) {
            fArr2[i2] = ((i[i2] - fArr[i2]) * f) + fArr[i2];
            i2++;
        }
        return Color.HSVToColor(alpha, fArr2);
    }
}
