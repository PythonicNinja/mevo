package com.google.zxing.qrcode.detector;

import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.PerspectiveTransform;
import com.google.zxing.common.detector.MathUtils;
import java.util.Map;

public class Detector {
    private final BitMatrix image;
    private ResultPointCallback resultPointCallback;

    public Detector(BitMatrix bitMatrix) {
        this.image = bitMatrix;
    }

    protected final BitMatrix getImage() {
        return this.image;
    }

    protected final ResultPointCallback getResultPointCallback() {
        return this.resultPointCallback;
    }

    public DetectorResult detect() throws NotFoundException, FormatException {
        return detect(null);
    }

    public final DetectorResult detect(Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        ResultPointCallback resultPointCallback;
        if (map == null) {
            resultPointCallback = null;
        } else {
            resultPointCallback = (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        }
        this.resultPointCallback = resultPointCallback;
        return processFinderPatternInfo(new FinderPatternFinder(this.image, this.resultPointCallback).find(map));
    }

    protected final com.google.zxing.common.DetectorResult processFinderPatternInfo(com.google.zxing.qrcode.detector.FinderPatternInfo r12) throws com.google.zxing.NotFoundException, com.google.zxing.FormatException {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r11 = this;
        r0 = r12.getTopLeft();
        r1 = r12.getTopRight();
        r12 = r12.getBottomLeft();
        r2 = r11.calculateModuleSize(r0, r1, r12);
        r3 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r4 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1));
        if (r4 >= 0) goto L_0x001b;
    L_0x0016:
        r12 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r12;
    L_0x001b:
        r4 = computeDimension(r0, r1, r12, r2);
        r5 = com.google.zxing.qrcode.decoder.Version.getProvisionalVersionForDimension(r4);
        r6 = r5.getDimensionForVersion();
        r6 = r6 + -7;
        r7 = 0;
        r5 = r5.getAlignmentPatternCenters();
        r5 = r5.length;
        r8 = 4;
        if (r5 <= 0) goto L_0x007c;
    L_0x0032:
        r5 = r1.getX();
        r9 = r0.getX();
        r5 = r5 - r9;
        r9 = r12.getX();
        r5 = r5 + r9;
        r9 = r1.getY();
        r10 = r0.getY();
        r9 = r9 - r10;
        r10 = r12.getY();
        r9 = r9 + r10;
        r10 = 1077936128; // 0x40400000 float:3.0 double:5.325712093E-315;
        r6 = (float) r6;
        r10 = r10 / r6;
        r3 = r3 - r10;
        r6 = r0.getX();
        r10 = r0.getX();
        r5 = r5 - r10;
        r5 = r5 * r3;
        r6 = r6 + r5;
        r5 = (int) r6;
        r6 = r0.getY();
        r10 = r0.getY();
        r9 = r9 - r10;
        r3 = r3 * r9;
        r6 = r6 + r3;
        r3 = (int) r6;
        r6 = 4;
    L_0x006e:
        r9 = 16;
        if (r6 > r9) goto L_0x007c;
    L_0x0072:
        r9 = (float) r6;
        r9 = r11.findAlignmentInRegion(r2, r5, r3, r9);	 Catch:{ NotFoundException -> 0x0079 }
        r7 = r9;
        goto L_0x007c;
    L_0x0079:
        r6 = r6 << 1;
        goto L_0x006e;
    L_0x007c:
        r2 = createTransform(r0, r1, r12, r7, r4);
        r3 = r11.image;
        r2 = sampleGrid(r3, r2, r4);
        r3 = 2;
        r4 = 0;
        r5 = 3;
        r6 = 1;
        if (r7 != 0) goto L_0x0095;
    L_0x008c:
        r5 = new com.google.zxing.ResultPoint[r5];
        r5[r4] = r12;
        r5[r6] = r0;
        r5[r3] = r1;
        goto L_0x00a0;
    L_0x0095:
        r8 = new com.google.zxing.ResultPoint[r8];
        r8[r4] = r12;
        r8[r6] = r0;
        r8[r3] = r1;
        r8[r5] = r7;
        r5 = r8;
    L_0x00a0:
        r12 = new com.google.zxing.common.DetectorResult;
        r12.<init>(r2, r5);
        return r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.detector.Detector.processFinderPatternInfo(com.google.zxing.qrcode.detector.FinderPatternInfo):com.google.zxing.common.DetectorResult");
    }

    private static PerspectiveTransform createTransform(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i) {
        float y;
        float f;
        float f2;
        float f3 = ((float) i) - 3.5f;
        if (resultPoint4 != null) {
            float x = resultPoint4.getX();
            y = resultPoint4.getY();
            f = x;
            f2 = f3 - 3.0f;
        } else {
            f = (resultPoint2.getX() - resultPoint.getX()) + resultPoint3.getX();
            y = (resultPoint2.getY() - resultPoint.getY()) + resultPoint3.getY();
            f2 = f3;
        }
        return PerspectiveTransform.quadrilateralToQuadrilateral(3.5f, 3.5f, f3, 3.5f, f2, f2, 3.5f, f3, resultPoint.getX(), resultPoint.getY(), resultPoint2.getX(), resultPoint2.getY(), f, y, resultPoint3.getX(), resultPoint3.getY());
    }

    private static BitMatrix sampleGrid(BitMatrix bitMatrix, PerspectiveTransform perspectiveTransform, int i) throws NotFoundException {
        return GridSampler.getInstance().sampleGrid(bitMatrix, i, i, perspectiveTransform);
    }

    private static int computeDimension(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, float f) throws NotFoundException {
        resultPoint2 = ((MathUtils.round(ResultPoint.distance(resultPoint, resultPoint2) / f) + MathUtils.round(ResultPoint.distance(resultPoint, resultPoint3) / f)) / 2) + 7;
        resultPoint = resultPoint2 & 3;
        if (resultPoint == null) {
            return resultPoint2 + 1;
        }
        switch (resultPoint) {
            case 2:
                return resultPoint2 - 1;
            case 3:
                throw NotFoundException.getNotFoundInstance();
            default:
                return resultPoint2;
        }
    }

    protected final float calculateModuleSize(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3) {
        return (calculateModuleSizeOneWay(resultPoint, resultPoint2) + calculateModuleSizeOneWay(resultPoint, resultPoint3)) / ErrorDialogData.SUPPRESSED;
    }

    private float calculateModuleSizeOneWay(ResultPoint resultPoint, ResultPoint resultPoint2) {
        float sizeOfBlackWhiteBlackRunBothWays = sizeOfBlackWhiteBlackRunBothWays((int) resultPoint.getX(), (int) resultPoint.getY(), (int) resultPoint2.getX(), (int) resultPoint2.getY());
        resultPoint = sizeOfBlackWhiteBlackRunBothWays((int) resultPoint2.getX(), (int) resultPoint2.getY(), (int) resultPoint.getX(), (int) resultPoint.getY());
        if (Float.isNaN(sizeOfBlackWhiteBlackRunBothWays) != null) {
            return resultPoint / 1088421888;
        }
        return Float.isNaN(resultPoint) != null ? sizeOfBlackWhiteBlackRunBothWays / 7.0f : (sizeOfBlackWhiteBlackRunBothWays + resultPoint) / 14.0f;
    }

    private float sizeOfBlackWhiteBlackRunBothWays(int i, int i2, int i3, int i4) {
        int i5;
        float sizeOfBlackWhiteBlackRun = sizeOfBlackWhiteBlackRun(i, i2, i3, i4);
        i3 = i - (i3 - i);
        int i6 = 0;
        if (i3 < 0) {
            i3 = ((float) i) / ((float) (i - i3));
            i5 = 0;
        } else if (i3 >= this.image.getWidth()) {
            i3 = ((float) ((this.image.getWidth() - 1) - i)) / ((float) (i3 - i));
            i5 = this.image.getWidth() - 1;
        } else {
            i5 = i3;
            i3 = 1065353216;
        }
        float f = (float) i2;
        i3 = (int) (f - (((float) (i4 - i2)) * i3));
        if (i3 < 0) {
            i3 = f / ((float) (i2 - i3));
        } else if (i3 >= this.image.getHeight()) {
            i3 = ((float) ((this.image.getHeight() - 1) - i2)) / ((float) (i3 - i2));
            i6 = this.image.getHeight() - 1;
        } else {
            i6 = i3;
            i3 = 1065353216;
        }
        return (sizeOfBlackWhiteBlackRun + sizeOfBlackWhiteBlackRun(i, i2, (int) (((float) i) + (((float) (i5 - i)) * i3)), i6)) - 1.0f;
    }

    private float sizeOfBlackWhiteBlackRun(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        Detector detector;
        int i9;
        int i10 = 1;
        Object obj = Math.abs(i4 - i2) > Math.abs(i3 - i) ? 1 : null;
        if (obj != null) {
            i5 = i;
            i6 = i2;
            i7 = i3;
            i8 = i4;
        } else {
            i6 = i;
            i5 = i2;
            i8 = i3;
            i7 = i4;
        }
        int abs = Math.abs(i8 - i6);
        int abs2 = Math.abs(i7 - i5);
        int i11 = (-abs) / 2;
        int i12 = -1;
        int i13 = i6 < i8 ? 1 : -1;
        if (i5 < i7) {
            i12 = 1;
        }
        i8 += i13;
        int i14 = i5;
        int i15 = i11;
        int i16 = 0;
        i11 = i6;
        while (i11 != i8) {
            Object obj2;
            boolean z;
            int i17 = obj != null ? i14 : i11;
            int i18 = obj != null ? i11 : i14;
            if (i16 == i10) {
                detector = this;
                i9 = i8;
                obj2 = obj;
                z = true;
            } else {
                detector = this;
                i9 = i8;
                obj2 = obj;
                z = false;
            }
            if (z == detector.image.get(i17, i18)) {
                if (i16 == 2) {
                    return MathUtils.distance(i11, i14, i6, i5);
                }
                i16++;
            }
            i15 += abs2;
            if (i15 > 0) {
                if (i14 == i7) {
                    break;
                }
                i14 += i12;
                i15 -= abs;
            }
            i11 += i13;
            obj = obj2;
            i8 = i9;
            i10 = 1;
        }
        detector = this;
        i9 = i8;
        return i16 == 2 ? MathUtils.distance(i9, i7, i6, i5) : Float.NaN;
    }

    protected final AlignmentPattern findAlignmentInRegion(float f, int i, int i2, float f2) throws NotFoundException {
        f2 = (int) (f2 * f);
        int max = Math.max(0, i - f2);
        int min = Math.min(this.image.getWidth() - 1, i + f2) - max;
        float f3 = 3.0f * f;
        if (((float) min) < f3) {
            throw NotFoundException.getNotFoundInstance();
        }
        int max2 = Math.max(0, i2 - f2);
        int min2 = Math.min(this.image.getHeight() - 1, i2 + f2) - max2;
        if (((float) min2) < f3) {
            throw NotFoundException.getNotFoundInstance();
        }
        return new AlignmentPatternFinder(this.image, max, max2, min, min2, f, this.resultPointCallback).find();
    }
}
