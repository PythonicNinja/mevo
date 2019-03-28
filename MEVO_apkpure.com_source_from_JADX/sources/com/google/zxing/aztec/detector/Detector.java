package com.google.zxing.aztec.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.detector.MathUtils;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;

public final class Detector {
    private static final int[] EXPECTED_CORNER_BITS = new int[]{3808, 476, 2107, 1799};
    private boolean compact;
    private final BitMatrix image;
    private int nbCenterLayers;
    private int nbDataBlocks;
    private int nbLayers;
    private int shift;

    static final class Point {
        /* renamed from: x */
        private final int f18x;
        /* renamed from: y */
        private final int f19y;

        ResultPoint toResultPoint() {
            return new ResultPoint((float) getX(), (float) getY());
        }

        Point(int i, int i2) {
            this.f18x = i;
            this.f19y = i2;
        }

        int getX() {
            return this.f18x;
        }

        int getY() {
            return this.f19y;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Operation.LESS_THAN);
            stringBuilder.append(this.f18x);
            stringBuilder.append(' ');
            stringBuilder.append(this.f19y);
            stringBuilder.append('>');
            return stringBuilder.toString();
        }
    }

    public Detector(BitMatrix bitMatrix) {
        this.image = bitMatrix;
    }

    public AztecDetectorResult detect() throws NotFoundException {
        return detect(false);
    }

    public AztecDetectorResult detect(boolean z) throws NotFoundException {
        ResultPoint[] bullsEyeCorners = getBullsEyeCorners(getMatrixCenter());
        if (z) {
            ResultPoint resultPoint = bullsEyeCorners[0];
            bullsEyeCorners[0] = bullsEyeCorners[2];
            bullsEyeCorners[2] = resultPoint;
        }
        extractParameters(bullsEyeCorners);
        return new AztecDetectorResult(sampleGrid(this.image, bullsEyeCorners[this.shift % 4], bullsEyeCorners[(this.shift + 1) % 4], bullsEyeCorners[(this.shift + 2) % 4], bullsEyeCorners[(this.shift + 3) % 4]), getMatrixCornerPoints(bullsEyeCorners), this.compact, this.nbDataBlocks, this.nbLayers);
    }

    private void extractParameters(ResultPoint[] resultPointArr) throws NotFoundException {
        int i = 0;
        if (isValid(resultPointArr[0]) && isValid(resultPointArr[1]) && isValid(resultPointArr[2])) {
            if (isValid(resultPointArr[3])) {
                int[] iArr = new int[]{sampleLine(resultPointArr[0], resultPointArr[1], this.nbCenterLayers * 2), sampleLine(resultPointArr[1], resultPointArr[2], this.nbCenterLayers * 2), sampleLine(resultPointArr[2], resultPointArr[3], this.nbCenterLayers * 2), sampleLine(resultPointArr[3], resultPointArr[0], this.nbCenterLayers * 2)};
                this.shift = getRotation(iArr, this.nbCenterLayers * 2);
                long j = 0;
                while (i < 4) {
                    resultPointArr = iArr[(this.shift + i) % 4];
                    j = this.compact ? (j << 7) + ((long) ((resultPointArr >> 1) & 127)) : (j << 10) + ((long) (((resultPointArr >> 2) & 992) + ((resultPointArr >> 1) & 31)));
                    i++;
                }
                resultPointArr = getCorrectedParameterData(j, this.compact);
                if (this.compact) {
                    this.nbLayers = (resultPointArr >> 6) + 1;
                    this.nbDataBlocks = (resultPointArr & 63) + 1;
                    return;
                }
                this.nbLayers = (resultPointArr >> 11) + 1;
                this.nbDataBlocks = (resultPointArr & 2047) + 1;
                return;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int getRotation(int[] iArr, int i) throws NotFoundException {
        int i2 = 0;
        for (int i3 : iArr) {
            i2 = (i2 << 3) + (((i3 >> (i - 2)) << 1) + (i3 & 1));
        }
        iArr = ((i2 & 1) << 11) + (i2 >> 1);
        for (int i4 = 0; i4 < 4; i4++) {
            if (Integer.bitCount(EXPECTED_CORNER_BITS[i4] ^ iArr) <= 2) {
                return i4;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int getCorrectedParameterData(long r5, boolean r7) throws com.google.zxing.NotFoundException {
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
        r0 = 4;
        if (r7 == 0) goto L_0x0006;
    L_0x0003:
        r7 = 7;
        r1 = 2;
        goto L_0x0009;
    L_0x0006:
        r7 = 10;
        r1 = 4;
    L_0x0009:
        r2 = r7 - r1;
        r3 = new int[r7];
        r7 = r7 + -1;
    L_0x000f:
        if (r7 < 0) goto L_0x001a;
    L_0x0011:
        r4 = (int) r5;
        r4 = r4 & 15;
        r3[r7] = r4;
        r5 = r5 >> r0;
        r7 = r7 + -1;
        goto L_0x000f;
    L_0x001a:
        r5 = new com.google.zxing.common.reedsolomon.ReedSolomonDecoder;	 Catch:{ ReedSolomonException -> 0x0031 }
        r6 = com.google.zxing.common.reedsolomon.GenericGF.AZTEC_PARAM;	 Catch:{ ReedSolomonException -> 0x0031 }
        r5.<init>(r6);	 Catch:{ ReedSolomonException -> 0x0031 }
        r5.decode(r3, r2);	 Catch:{ ReedSolomonException -> 0x0031 }
        r5 = 0;
        r6 = 0;
    L_0x0026:
        if (r5 >= r1) goto L_0x0030;
    L_0x0028:
        r6 = r6 << 4;
        r7 = r3[r5];
        r6 = r6 + r7;
        r5 = r5 + 1;
        goto L_0x0026;
    L_0x0030:
        return r6;
    L_0x0031:
        r5 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.detector.Detector.getCorrectedParameterData(long, boolean):int");
    }

    private ResultPoint[] getBullsEyeCorners(Point point) throws NotFoundException {
        this.nbCenterLayers = 1;
        Point point2 = point;
        Point point3 = point2;
        Point point4 = point3;
        Point point5 = point4;
        boolean z = true;
        while (r0.nbCenterLayers < 9) {
            Point firstDifferent = getFirstDifferent(point2, z, 1, -1);
            Point firstDifferent2 = getFirstDifferent(point3, z, 1, 1);
            Point firstDifferent3 = getFirstDifferent(point4, z, -1, 1);
            Point firstDifferent4 = getFirstDifferent(point5, z, -1, -1);
            if (r0.nbCenterLayers > 2) {
                double distance = (double) ((distance(firstDifferent4, firstDifferent) * ((float) r0.nbCenterLayers)) / (distance(point5, point2) * ((float) (r0.nbCenterLayers + 2))));
                if (distance < 0.75d || distance > 1.25d) {
                    break;
                } else if (!isWhiteOrBlackRectangle(firstDifferent, firstDifferent2, firstDifferent3, firstDifferent4)) {
                    break;
                }
            }
            z ^= 1;
            r0.nbCenterLayers++;
            point5 = firstDifferent4;
            point2 = firstDifferent;
            point3 = firstDifferent2;
            point4 = firstDifferent3;
        }
        if (r0.nbCenterLayers == 5 || r0.nbCenterLayers == 7) {
            r0.compact = r0.nbCenterLayers == 5;
            ResultPoint resultPoint = new ResultPoint(((float) point2.getX()) + 0.5f, ((float) point2.getY()) - 0.5f);
            ResultPoint resultPoint2 = new ResultPoint(((float) point3.getX()) + 0.5f, ((float) point3.getY()) + 0.5f);
            ResultPoint resultPoint3 = new ResultPoint(((float) point4.getX()) - 0.5f, ((float) point4.getY()) + 0.5f);
            ResultPoint resultPoint4 = new ResultPoint(((float) point5.getX()) - 0.5f, ((float) point5.getY()) - 0.5f);
            return expandSquare(new ResultPoint[]{resultPoint, resultPoint2, resultPoint3, resultPoint4}, (float) ((r0.nbCenterLayers * 2) - 3), (float) (r0.nbCenterLayers * 2));
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private com.google.zxing.aztec.detector.Detector.Point getMatrixCenter() {
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
        r12 = this;
        r0 = 3;
        r1 = 2;
        r2 = -1;
        r3 = 1;
        r4 = 0;
        r5 = new com.google.zxing.common.detector.WhiteRectangleDetector;	 Catch:{ NotFoundException -> 0x0019 }
        r6 = r12.image;	 Catch:{ NotFoundException -> 0x0019 }
        r5.<init>(r6);	 Catch:{ NotFoundException -> 0x0019 }
        r5 = r5.detect();	 Catch:{ NotFoundException -> 0x0019 }
        r6 = r5[r4];	 Catch:{ NotFoundException -> 0x0019 }
        r7 = r5[r3];	 Catch:{ NotFoundException -> 0x0019 }
        r8 = r5[r1];	 Catch:{ NotFoundException -> 0x0019 }
        r5 = r5[r0];	 Catch:{ NotFoundException -> 0x0019 }
        goto L_0x0067;
    L_0x0019:
        r5 = r12.image;
        r5 = r5.getWidth();
        r5 = r5 / r1;
        r6 = r12.image;
        r6 = r6.getHeight();
        r6 = r6 / r1;
        r7 = new com.google.zxing.aztec.detector.Detector$Point;
        r8 = r5 + 7;
        r9 = r6 + -7;
        r7.<init>(r8, r9);
        r7 = r12.getFirstDifferent(r7, r4, r3, r2);
        r7 = r7.toResultPoint();
        r10 = new com.google.zxing.aztec.detector.Detector$Point;
        r6 = r6 + 7;
        r10.<init>(r8, r6);
        r8 = r12.getFirstDifferent(r10, r4, r3, r3);
        r8 = r8.toResultPoint();
        r10 = new com.google.zxing.aztec.detector.Detector$Point;
        r5 = r5 + -7;
        r10.<init>(r5, r6);
        r6 = r12.getFirstDifferent(r10, r4, r2, r3);
        r6 = r6.toResultPoint();
        r10 = new com.google.zxing.aztec.detector.Detector$Point;
        r10.<init>(r5, r9);
        r5 = r12.getFirstDifferent(r10, r4, r2, r2);
        r5 = r5.toResultPoint();
        r11 = r8;
        r8 = r6;
        r6 = r7;
        r7 = r11;
    L_0x0067:
        r9 = r6.getX();
        r10 = r5.getX();
        r9 = r9 + r10;
        r10 = r7.getX();
        r9 = r9 + r10;
        r10 = r8.getX();
        r9 = r9 + r10;
        r10 = 1082130432; // 0x40800000 float:4.0 double:5.34643471E-315;
        r9 = r9 / r10;
        r9 = com.google.zxing.common.detector.MathUtils.round(r9);
        r6 = r6.getY();
        r5 = r5.getY();
        r6 = r6 + r5;
        r5 = r7.getY();
        r6 = r6 + r5;
        r5 = r8.getY();
        r6 = r6 + r5;
        r6 = r6 / r10;
        r5 = com.google.zxing.common.detector.MathUtils.round(r6);
        r6 = new com.google.zxing.common.detector.WhiteRectangleDetector;	 Catch:{ NotFoundException -> 0x00af }
        r7 = r12.image;	 Catch:{ NotFoundException -> 0x00af }
        r8 = 15;	 Catch:{ NotFoundException -> 0x00af }
        r6.<init>(r7, r8, r9, r5);	 Catch:{ NotFoundException -> 0x00af }
        r6 = r6.detect();	 Catch:{ NotFoundException -> 0x00af }
        r7 = r6[r4];	 Catch:{ NotFoundException -> 0x00af }
        r8 = r6[r3];	 Catch:{ NotFoundException -> 0x00af }
        r1 = r6[r1];	 Catch:{ NotFoundException -> 0x00af }
        r0 = r6[r0];	 Catch:{ NotFoundException -> 0x00af }
        goto L_0x00eb;
    L_0x00af:
        r0 = new com.google.zxing.aztec.detector.Detector$Point;
        r1 = r9 + 7;
        r6 = r5 + -7;
        r0.<init>(r1, r6);
        r0 = r12.getFirstDifferent(r0, r4, r3, r2);
        r7 = r0.toResultPoint();
        r0 = new com.google.zxing.aztec.detector.Detector$Point;
        r5 = r5 + 7;
        r0.<init>(r1, r5);
        r0 = r12.getFirstDifferent(r0, r4, r3, r3);
        r8 = r0.toResultPoint();
        r0 = new com.google.zxing.aztec.detector.Detector$Point;
        r9 = r9 + -7;
        r0.<init>(r9, r5);
        r0 = r12.getFirstDifferent(r0, r4, r2, r3);
        r1 = r0.toResultPoint();
        r0 = new com.google.zxing.aztec.detector.Detector$Point;
        r0.<init>(r9, r6);
        r0 = r12.getFirstDifferent(r0, r4, r2, r2);
        r0 = r0.toResultPoint();
    L_0x00eb:
        r2 = r7.getX();
        r3 = r0.getX();
        r2 = r2 + r3;
        r3 = r8.getX();
        r2 = r2 + r3;
        r3 = r1.getX();
        r2 = r2 + r3;
        r2 = r2 / r10;
        r2 = com.google.zxing.common.detector.MathUtils.round(r2);
        r3 = r7.getY();
        r0 = r0.getY();
        r3 = r3 + r0;
        r0 = r8.getY();
        r3 = r3 + r0;
        r0 = r1.getY();
        r3 = r3 + r0;
        r3 = r3 / r10;
        r0 = com.google.zxing.common.detector.MathUtils.round(r3);
        r1 = new com.google.zxing.aztec.detector.Detector$Point;
        r1.<init>(r2, r0);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.detector.Detector.getMatrixCenter():com.google.zxing.aztec.detector.Detector$Point");
    }

    private ResultPoint[] getMatrixCornerPoints(ResultPoint[] resultPointArr) {
        return expandSquare(resultPointArr, (float) (this.nbCenterLayers * 2), (float) getDimension());
    }

    private BitMatrix sampleGrid(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4) throws NotFoundException {
        GridSampler instance = GridSampler.getInstance();
        int dimension = getDimension();
        float f = ((float) dimension) / 2.0f;
        float f2 = f - ((float) this.nbCenterLayers);
        float f3 = f + ((float) this.nbCenterLayers);
        return instance.sampleGrid(bitMatrix, dimension, dimension, f2, f2, f3, f2, f3, f3, f2, f3, resultPoint.getX(), resultPoint.getY(), resultPoint2.getX(), resultPoint2.getY(), resultPoint3.getX(), resultPoint3.getY(), resultPoint4.getX(), resultPoint4.getY());
    }

    private int sampleLine(ResultPoint resultPoint, ResultPoint resultPoint2, int i) {
        float distance = distance(resultPoint, resultPoint2);
        float f = distance / ((float) i);
        float x = resultPoint.getX();
        float y = resultPoint.getY();
        float x2 = ((resultPoint2.getX() - resultPoint.getX()) * f) / distance;
        f = (f * (resultPoint2.getY() - resultPoint.getY())) / distance;
        resultPoint2 = null;
        for (resultPoint = null; resultPoint < i; resultPoint++) {
            float f2 = (float) resultPoint;
            if (this.image.get(MathUtils.round((f2 * x2) + x), MathUtils.round((f2 * f) + y))) {
                resultPoint2 |= 1 << ((i - resultPoint) - 1);
            }
        }
        return resultPoint2;
    }

    private boolean isWhiteOrBlackRectangle(Point point, Point point2, Point point3, Point point4) {
        Point point5 = new Point(point.getX() - 3, point.getY() + 3);
        point = new Point(point2.getX() - 3, point2.getY() - 3);
        point2 = new Point(point3.getX() + 3, point3.getY() - 3);
        point3 = new Point(point4.getX() + 3, point4.getY() + 3);
        point4 = getColor(point3, point5);
        boolean z = false;
        if (point4 == null || getColor(point5, point) != point4 || getColor(point, point2) != point4) {
            return false;
        }
        if (getColor(point2, point3) == point4) {
            z = true;
        }
        return z;
    }

    private int getColor(Point point, Point point2) {
        float distance = distance(point, point2);
        float x = ((float) (point2.getX() - point.getX())) / distance;
        point2 = ((float) (point2.getY() - point.getY())) / distance;
        float x2 = (float) point.getX();
        float y = (float) point.getY();
        point = this.image.get(point.getX(), point.getY());
        int i = 0;
        float f = y;
        int i2 = 0;
        y = x2;
        for (int i3 = 0; ((float) i3) < distance; i3++) {
            y += x;
            f += point2;
            if (this.image.get(MathUtils.round(y), MathUtils.round(f)) != point) {
                i2++;
            }
        }
        point2 = ((float) i2) / distance;
        if (point2 > 1036831949 && point2 < 0.9f) {
            return 0;
        }
        int i4 = 1;
        if (point2 <= 1036831949) {
            i = 1;
        }
        if (i != point) {
            i4 = -1;
        }
        return i4;
    }

    private Point getFirstDifferent(Point point, boolean z, int i, int i2) {
        int x = point.getX() + i;
        point = point.getY() + i2;
        while (isValid(x, point) && this.image.get(x, point) == z) {
            x += i;
            point += i2;
        }
        x -= i;
        point -= i2;
        while (isValid(x, point) && this.image.get(x, point) == z) {
            x += i;
        }
        x -= i;
        while (isValid(x, point) != 0 && this.image.get(x, point) == z) {
            point += i2;
        }
        return new Point(x, point - i2);
    }

    private static ResultPoint[] expandSquare(ResultPoint[] resultPointArr, float f, float f2) {
        f2 /= f * 2.0f;
        float x = (resultPointArr[0].getX() + resultPointArr[2].getX()) / 2.0f;
        float y = (resultPointArr[0].getY() + resultPointArr[2].getY()) / 2.0f;
        float x2 = (resultPointArr[0].getX() - resultPointArr[2].getX()) * f2;
        float y2 = (resultPointArr[0].getY() - resultPointArr[2].getY()) * f2;
        ResultPoint resultPoint = new ResultPoint(x + x2, y + y2);
        ResultPoint resultPoint2 = new ResultPoint(x - x2, y - y2);
        float x3 = (resultPointArr[1].getX() + resultPointArr[3].getX()) / 2.0f;
        float y3 = (resultPointArr[1].getY() + resultPointArr[3].getY()) / 2.0f;
        y2 = (resultPointArr[1].getX() - resultPointArr[3].getX()) * f2;
        f2 *= resultPointArr[1].getY() - resultPointArr[3].getY();
        resultPointArr = new ResultPoint(x3 + y2, y3 + f2);
        ResultPoint resultPoint3 = new ResultPoint(x3 - y2, y3 - f2);
        return new ResultPoint[]{resultPoint, resultPointArr, resultPoint2, resultPoint3};
    }

    private boolean isValid(int i, int i2) {
        return i >= 0 && i < this.image.getWidth() && i2 > 0 && i2 < this.image.getHeight();
    }

    private boolean isValid(ResultPoint resultPoint) {
        return isValid(MathUtils.round(resultPoint.getX()), MathUtils.round(resultPoint.getY()));
    }

    private static float distance(Point point, Point point2) {
        return MathUtils.distance(point.getX(), point.getY(), point2.getX(), point2.getY());
    }

    private static float distance(ResultPoint resultPoint, ResultPoint resultPoint2) {
        return MathUtils.distance(resultPoint.getX(), resultPoint.getY(), resultPoint2.getX(), resultPoint2.getY());
    }

    private int getDimension() {
        if (this.compact) {
            return (this.nbLayers * 4) + 11;
        }
        if (this.nbLayers <= 4) {
            return (this.nbLayers * 4) + 15;
        }
        return ((this.nbLayers * 4) + ((((this.nbLayers - 4) / 8) + 1) * 2)) + 15;
    }
}
