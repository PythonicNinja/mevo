package com.google.zxing.pdf417.detector;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class Detector {
    private static final int BARCODE_MIN_HEIGHT = 10;
    private static final int[] INDEXES_START_PATTERN = new int[]{0, 4, 1, 5};
    private static final int[] INDEXES_STOP_PATTERN = new int[]{6, 2, 7, 3};
    private static final float MAX_AVG_VARIANCE = 0.42f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.8f;
    private static final int MAX_PATTERN_DRIFT = 5;
    private static final int MAX_PIXEL_DRIFT = 3;
    private static final int ROW_STEP = 5;
    private static final int SKIPPED_ROW_COUNT_MAX = 25;
    private static final int[] START_PATTERN = new int[]{8, 1, 1, 1, 1, 1, 1, 3};
    private static final int[] STOP_PATTERN = new int[]{7, 1, 1, 3, 1, 1, 1, 2, 1};

    private Detector() {
    }

    public static PDF417DetectorResult detect(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map, boolean z) throws NotFoundException {
        binaryBitmap = binaryBitmap.getBlackMatrix();
        map = detect(z, binaryBitmap);
        if (map.isEmpty()) {
            binaryBitmap = binaryBitmap.clone();
            binaryBitmap.rotate180();
            map = detect(z, binaryBitmap);
        }
        return new PDF417DetectorResult(binaryBitmap, map);
    }

    private static List<ResultPoint[]> detect(boolean z, BitMatrix bitMatrix) {
        List<ResultPoint[]> arrayList = new ArrayList();
        int i = 0;
        loop0:
        while (true) {
            int i2 = 0;
            Object obj = null;
            while (i < bitMatrix.getHeight()) {
                Object findVertices = findVertices(bitMatrix, i, i2);
                if (findVertices[0] != null || findVertices[3] != null) {
                    arrayList.add(findVertices);
                    if (!z) {
                        break loop0;
                    }
                    int x;
                    if (findVertices[2] != null) {
                        x = (int) findVertices[2].getX();
                        i = (int) findVertices[2].getY();
                    } else {
                        x = (int) findVertices[4].getX();
                        i = (int) findVertices[4].getY();
                    }
                    i2 = x;
                    obj = 1;
                } else if (obj == null) {
                    break;
                } else {
                    for (ResultPoint[] resultPointArr : arrayList) {
                        if (resultPointArr[1] != null) {
                            i = (int) Math.max((float) i, resultPointArr[1].getY());
                        }
                        if (resultPointArr[3] != null) {
                            i = Math.max(i, (int) resultPointArr[3].getY());
                        }
                    }
                    i += 5;
                }
            }
            break loop0;
        }
        return arrayList;
    }

    private static ResultPoint[] findVertices(BitMatrix bitMatrix, int i, int i2) {
        int height = bitMatrix.getHeight();
        int width = bitMatrix.getWidth();
        ResultPoint[] resultPointArr = new ResultPoint[8];
        copyToResult(resultPointArr, findRowsWithPattern(bitMatrix, height, width, i, i2, START_PATTERN), INDEXES_START_PATTERN);
        if (resultPointArr[4] != null) {
            i2 = (int) resultPointArr[4].getX();
            i = (int) resultPointArr[4].getY();
        }
        copyToResult(resultPointArr, findRowsWithPattern(bitMatrix, height, width, i, i2, STOP_PATTERN), INDEXES_STOP_PATTERN);
        return resultPointArr;
    }

    private static void copyToResult(ResultPoint[] resultPointArr, ResultPoint[] resultPointArr2, int[] iArr) {
        for (int i = 0; i < iArr.length; i++) {
            resultPointArr[iArr[i]] = resultPointArr2[i];
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.zxing.ResultPoint[] findRowsWithPattern(com.google.zxing.common.BitMatrix r18, int r19, int r20, int r21, int r22, int[] r23) {
        /*
        r0 = r19;
        r1 = 4;
        r1 = new com.google.zxing.ResultPoint[r1];
        r9 = r23;
        r2 = r9.length;
        r10 = new int[r2];
        r11 = r21;
    L_0x000c:
        r12 = 0;
        r13 = 1;
        if (r11 >= r0) goto L_0x0051;
    L_0x0010:
        r6 = 0;
        r2 = r18;
        r3 = r22;
        r4 = r11;
        r5 = r20;
        r7 = r9;
        r8 = r10;
        r2 = findGuardPattern(r2, r3, r4, r5, r6, r7, r8);
        if (r2 == 0) goto L_0x004e;
    L_0x0020:
        r14 = r2;
        if (r11 <= 0) goto L_0x0037;
    L_0x0023:
        r11 = r11 + -1;
        r6 = 0;
        r2 = r18;
        r3 = r22;
        r4 = r11;
        r5 = r20;
        r7 = r9;
        r8 = r10;
        r2 = findGuardPattern(r2, r3, r4, r5, r6, r7, r8);
        if (r2 == 0) goto L_0x0036;
    L_0x0035:
        goto L_0x0020;
    L_0x0036:
        r11 = r11 + r13;
    L_0x0037:
        r2 = new com.google.zxing.ResultPoint;
        r3 = r14[r12];
        r3 = (float) r3;
        r4 = (float) r11;
        r2.<init>(r3, r4);
        r1[r12] = r2;
        r2 = new com.google.zxing.ResultPoint;
        r3 = r14[r13];
        r3 = (float) r3;
        r2.<init>(r3, r4);
        r1[r13] = r2;
        r2 = 1;
        goto L_0x0052;
    L_0x004e:
        r11 = r11 + 5;
        goto L_0x000c;
    L_0x0051:
        r2 = 0;
    L_0x0052:
        r3 = r11 + 1;
        if (r2 == 0) goto L_0x00c4;
    L_0x0056:
        r14 = 2;
        r2 = new int[r14];
        r4 = r1[r12];
        r4 = r4.getX();
        r4 = (int) r4;
        r2[r12] = r4;
        r4 = r1[r13];
        r4 = r4.getX();
        r4 = (int) r4;
        r2[r13] = r4;
        r16 = r2;
        r15 = r3;
        r8 = 0;
    L_0x006f:
        if (r15 >= r0) goto L_0x00a8;
    L_0x0071:
        r3 = r16[r12];
        r6 = 0;
        r2 = r18;
        r4 = r15;
        r5 = r20;
        r7 = r9;
        r14 = r8;
        r8 = r10;
        r2 = findGuardPattern(r2, r3, r4, r5, r6, r7, r8);
        if (r2 == 0) goto L_0x009d;
    L_0x0082:
        r3 = r16[r12];
        r4 = r2[r12];
        r3 = r3 - r4;
        r3 = java.lang.Math.abs(r3);
        r4 = 5;
        if (r3 >= r4) goto L_0x009d;
    L_0x008e:
        r3 = r16[r13];
        r5 = r2[r13];
        r3 = r3 - r5;
        r3 = java.lang.Math.abs(r3);
        if (r3 >= r4) goto L_0x009d;
    L_0x0099:
        r16 = r2;
        r8 = 0;
        goto L_0x00a4;
    L_0x009d:
        r2 = 25;
        if (r14 <= r2) goto L_0x00a2;
    L_0x00a1:
        goto L_0x00a9;
    L_0x00a2:
        r8 = r14 + 1;
    L_0x00a4:
        r15 = r15 + 1;
        r14 = 2;
        goto L_0x006f;
    L_0x00a8:
        r14 = r8;
    L_0x00a9:
        r8 = r14 + 1;
        r3 = r15 - r8;
        r0 = new com.google.zxing.ResultPoint;
        r2 = r16[r12];
        r2 = (float) r2;
        r4 = (float) r3;
        r0.<init>(r2, r4);
        r2 = 2;
        r1[r2] = r0;
        r0 = 3;
        r2 = new com.google.zxing.ResultPoint;
        r5 = r16[r13];
        r5 = (float) r5;
        r2.<init>(r5, r4);
        r1[r0] = r2;
    L_0x00c4:
        r3 = r3 - r11;
        r0 = 10;
        if (r3 >= r0) goto L_0x00d2;
    L_0x00c9:
        r0 = r1.length;
        if (r12 >= r0) goto L_0x00d2;
    L_0x00cc:
        r0 = 0;
        r1[r12] = r0;
        r12 = r12 + 1;
        goto L_0x00c9;
    L_0x00d2:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.detector.Detector.findRowsWithPattern(com.google.zxing.common.BitMatrix, int, int, int, int, int[]):com.google.zxing.ResultPoint[]");
    }

    private static int[] findGuardPattern(BitMatrix bitMatrix, int i, int i2, int i3, boolean z, int[] iArr, int[] iArr2) {
        int i4;
        Arrays.fill(iArr2, 0, iArr2.length, 0);
        int length = iArr.length;
        int i5 = 0;
        while (bitMatrix.get(i, i2) && i > 0) {
            i4 = i5 + 1;
            if (i5 >= 3) {
                break;
            }
            i--;
            i5 = i4;
        }
        i4 = i;
        i5 = 0;
        while (true) {
            boolean z2 = true;
            if (i >= i3) {
                break;
            }
            if ((bitMatrix.get(i, i2) ^ z) != 0) {
                iArr2[i5] = iArr2[i5] + 1;
            } else {
                int i6 = length - 1;
                if (i5 != i6) {
                    i5++;
                } else if (patternMatchVariance(iArr2, iArr, MAX_INDIVIDUAL_VARIANCE) < MAX_AVG_VARIANCE) {
                    return new int[]{i4, i};
                } else {
                    i4 += iArr2[0] + iArr2[1];
                    int i7 = length - 2;
                    System.arraycopy(iArr2, 2, iArr2, 0, i7);
                    iArr2[i7] = 0;
                    iArr2[i6] = 0;
                    i5--;
                }
                iArr2[i5] = 1;
                if (z) {
                    z2 = false;
                }
                z = z2;
            }
            i++;
        }
        if (i5 != length - 1 || patternMatchVariance(iArr2, iArr, MAX_INDIVIDUAL_VARIANCE) >= 1054280253) {
            return null;
        }
        return new int[]{i4, i - 1};
    }

    private static float patternMatchVariance(int[] iArr, int[] iArr2, float f) {
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            i += iArr[i3];
            i2 += iArr2[i3];
        }
        if (i < i2) {
            return Float.POSITIVE_INFINITY;
        }
        float f2 = (float) i;
        float f3 = f2 / ((float) i2);
        f *= f3;
        float f4 = 0.0f;
        for (int i4 = 0; i4 < length; i4++) {
            float f5 = ((float) iArr2[i4]) * f3;
            float f6 = (float) iArr[i4];
            f6 = f6 > f5 ? f6 - f5 : f5 - f6;
            if (f6 > f) {
                return Float.POSITIVE_INFINITY;
            }
            f4 += f6;
        }
        return f4 / f2;
    }
}
