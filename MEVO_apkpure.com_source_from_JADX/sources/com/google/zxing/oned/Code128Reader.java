package com.google.zxing.oned;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class Code128Reader extends OneDReader {
    private static final int CODE_CODE_A = 101;
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_A = 101;
    private static final int CODE_FNC_4_B = 100;
    static final int[][] CODE_PATTERNS = new int[][]{new int[]{2, 1, 2, 2, 2, 2}, new int[]{2, 2, 2, 1, 2, 2}, new int[]{2, 2, 2, 2, 2, 1}, new int[]{1, 2, 1, 2, 2, 3}, new int[]{1, 2, 1, 3, 2, 2}, new int[]{1, 3, 1, 2, 2, 2}, new int[]{1, 2, 2, 2, 1, 3}, new int[]{1, 2, 2, 3, 1, 2}, new int[]{1, 3, 2, 2, 1, 2}, new int[]{2, 2, 1, 2, 1, 3}, new int[]{2, 2, 1, 3, 1, 2}, new int[]{2, 3, 1, 2, 1, 2}, new int[]{1, 1, 2, 2, 3, 2}, new int[]{1, 2, 2, 1, 3, 2}, new int[]{1, 2, 2, 2, 3, 1}, new int[]{1, 1, 3, 2, 2, 2}, new int[]{1, 2, 3, 1, 2, 2}, new int[]{1, 2, 3, 2, 2, 1}, new int[]{2, 2, 3, 2, 1, 1}, new int[]{2, 2, 1, 1, 3, 2}, new int[]{2, 2, 1, 2, 3, 1}, new int[]{2, 1, 3, 2, 1, 2}, new int[]{2, 2, 3, 1, 1, 2}, new int[]{3, 1, 2, 1, 3, 1}, new int[]{3, 1, 1, 2, 2, 2}, new int[]{3, 2, 1, 1, 2, 2}, new int[]{3, 2, 1, 2, 2, 1}, new int[]{3, 1, 2, 2, 1, 2}, new int[]{3, 2, 2, 1, 1, 2}, new int[]{3, 2, 2, 2, 1, 1}, new int[]{2, 1, 2, 1, 2, 3}, new int[]{2, 1, 2, 3, 2, 1}, new int[]{2, 3, 2, 1, 2, 1}, new int[]{1, 1, 1, 3, 2, 3}, new int[]{1, 3, 1, 1, 2, 3}, new int[]{1, 3, 1, 3, 2, 1}, new int[]{1, 1, 2, 3, 1, 3}, new int[]{1, 3, 2, 1, 1, 3}, new int[]{1, 3, 2, 3, 1, 1}, new int[]{2, 1, 1, 3, 1, 3}, new int[]{2, 3, 1, 1, 1, 3}, new int[]{2, 3, 1, 3, 1, 1}, new int[]{1, 1, 2, 1, 3, 3}, new int[]{1, 1, 2, 3, 3, 1}, new int[]{1, 3, 2, 1, 3, 1}, new int[]{1, 1, 3, 1, 2, 3}, new int[]{1, 1, 3, 3, 2, 1}, new int[]{1, 3, 3, 1, 2, 1}, new int[]{3, 1, 3, 1, 2, 1}, new int[]{2, 1, 1, 3, 3, 1}, new int[]{2, 3, 1, 1, 3, 1}, new int[]{2, 1, 3, 1, 1, 3}, new int[]{2, 1, 3, 3, 1, 1}, new int[]{2, 1, 3, 1, 3, 1}, new int[]{3, 1, 1, 1, 2, 3}, new int[]{3, 1, 1, 3, 2, 1}, new int[]{3, 3, 1, 1, 2, 1}, new int[]{3, 1, 2, 1, 1, 3}, new int[]{3, 1, 2, 3, 1, 1}, new int[]{3, 3, 2, 1, 1, 1}, new int[]{3, 1, 4, 1, 1, 1}, new int[]{2, 2, 1, 4, 1, 1}, new int[]{4, 3, 1, 1, 1, 1}, new int[]{1, 1, 1, 2, 2, 4}, new int[]{1, 1, 1, 4, 2, 2}, new int[]{1, 2, 1, 1, 2, 4}, new int[]{1, 2, 1, 4, 2, 1}, new int[]{1, 4, 1, 1, 2, 2}, new int[]{1, 4, 1, 2, 2, 1}, new int[]{1, 1, 2, 2, 1, 4}, new int[]{1, 1, 2, 4, 1, 2}, new int[]{1, 2, 2, 1, 1, 4}, new int[]{1, 2, 2, 4, 1, 1}, new int[]{1, 4, 2, 1, 1, 2}, new int[]{1, 4, 2, 2, 1, 1}, new int[]{2, 4, 1, 2, 1, 1}, new int[]{2, 2, 1, 1, 1, 4}, new int[]{4, 1, 3, 1, 1, 1}, new int[]{2, 4, 1, 1, 1, 2}, new int[]{1, 3, 4, 1, 1, 1}, new int[]{1, 1, 1, 2, 4, 2}, new int[]{1, 2, 1, 1, 4, 2}, new int[]{1, 2, 1, 2, 4, 1}, new int[]{1, 1, 4, 2, 1, 2}, new int[]{1, 2, 4, 1, 1, 2}, new int[]{1, 2, 4, 2, 1, 1}, new int[]{4, 1, 1, 2, 1, 2}, new int[]{4, 2, 1, 1, 1, 2}, new int[]{4, 2, 1, 2, 1, 1}, new int[]{2, 1, 2, 1, 4, 1}, new int[]{2, 1, 4, 1, 2, 1}, new int[]{4, 1, 2, 1, 2, 1}, new int[]{1, 1, 1, 1, 4, 3}, new int[]{1, 1, 1, 3, 4, 1}, new int[]{1, 3, 1, 1, 4, 1}, new int[]{1, 1, 4, 1, 1, 3}, new int[]{1, 1, 4, 3, 1, 1}, new int[]{4, 1, 1, 1, 1, 3}, new int[]{4, 1, 1, 3, 1, 1}, new int[]{1, 1, 3, 1, 4, 1}, new int[]{1, 1, 4, 1, 3, 1}, new int[]{3, 1, 1, 1, 4, 1}, new int[]{4, 1, 1, 1, 3, 1}, new int[]{2, 1, 1, 4, 1, 2}, new int[]{2, 1, 1, 2, 1, 4}, new int[]{2, 1, 1, 2, 3, 2}, new int[]{2, 3, 3, 1, 1, 1, 2}};
    private static final int CODE_SHIFT = 98;
    private static final int CODE_START_A = 103;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final float MAX_AVG_VARIANCE = 0.25f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.7f;

    private static int[] findStartPattern(BitArray bitArray) throws NotFoundException {
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        Object obj = new int[6];
        int length = obj.length;
        int i = nextSet;
        int i2 = 0;
        int i3 = 0;
        while (nextSet < size) {
            if ((bitArray.get(nextSet) ^ i2) != 0) {
                obj[i3] = obj[i3] + 1;
            } else {
                int i4 = length - 1;
                if (i3 == i4) {
                    int i5;
                    float f = MAX_AVG_VARIANCE;
                    int i6 = -1;
                    for (i5 = 103; i5 <= 105; i5++) {
                        float patternMatchVariance = OneDReader.patternMatchVariance(obj, CODE_PATTERNS[i5], MAX_INDIVIDUAL_VARIANCE);
                        if (patternMatchVariance < f) {
                            i6 = i5;
                            f = patternMatchVariance;
                        }
                    }
                    if (i6 < 0 || !bitArray.isRange(Math.max(0, i - ((nextSet - i) / 2)), i, false)) {
                        i += obj[0] + obj[1];
                        i5 = length - 2;
                        System.arraycopy(obj, 2, obj, 0, i5);
                        obj[i5] = null;
                        obj[i4] = null;
                        i3--;
                    } else {
                        return new int[]{i, nextSet, i6};
                    }
                }
                i3++;
                obj[i3] = 1;
                i2 ^= 1;
            }
            nextSet++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int decodeCode(BitArray bitArray, int[] iArr, int i) throws NotFoundException {
        OneDReader.recordPattern(bitArray, i, iArr);
        bitArray = 1048576000;
        i = -1;
        for (int i2 = 0; i2 < CODE_PATTERNS.length; i2++) {
            float patternMatchVariance = OneDReader.patternMatchVariance(iArr, CODE_PATTERNS[i2], MAX_INDIVIDUAL_VARIANCE);
            if (patternMatchVariance < bitArray) {
                i = i2;
                bitArray = patternMatchVariance;
            }
        }
        if (i >= 0) {
            return i;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.Result decodeRow(int r23, com.google.zxing.common.BitArray r24, java.util.Map<com.google.zxing.DecodeHintType, ?> r25) throws com.google.zxing.NotFoundException, com.google.zxing.FormatException, com.google.zxing.ChecksumException {
        /*
        r22 = this;
        r0 = r24;
        r1 = r25;
        r2 = 1;
        r3 = 0;
        if (r1 == 0) goto L_0x0012;
    L_0x0008:
        r4 = com.google.zxing.DecodeHintType.ASSUME_GS1;
        r1 = r1.containsKey(r4);
        if (r1 == 0) goto L_0x0012;
    L_0x0010:
        r1 = 1;
        goto L_0x0013;
    L_0x0012:
        r1 = 0;
    L_0x0013:
        r4 = findStartPattern(r24);
        r5 = 2;
        r6 = r4[r5];
        r7 = new java.util.ArrayList;
        r8 = 20;
        r7.<init>(r8);
        r9 = (byte) r6;
        r9 = java.lang.Byte.valueOf(r9);
        r7.add(r9);
        switch(r6) {
            case 103: goto L_0x0037;
            case 104: goto L_0x0034;
            case 105: goto L_0x0031;
            default: goto L_0x002c;
        };
    L_0x002c:
        r0 = com.google.zxing.FormatException.getFormatInstance();
        throw r0;
    L_0x0031:
        r12 = 99;
        goto L_0x0039;
    L_0x0034:
        r12 = 100;
        goto L_0x0039;
    L_0x0037:
        r12 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
    L_0x0039:
        r13 = new java.lang.StringBuilder;
        r13.<init>(r8);
        r8 = r4[r3];
        r14 = r4[r2];
        r15 = 6;
        r15 = new int[r15];
        r16 = r6;
        r2 = r8;
        r3 = r12;
        r5 = 0;
        r6 = 0;
        r8 = 0;
        r9 = 0;
        r10 = 0;
        r12 = 0;
        r17 = 0;
        r18 = 1;
    L_0x0053:
        if (r6 != 0) goto L_0x01c8;
    L_0x0055:
        r2 = decodeCode(r0, r15, r14);
        r9 = (byte) r2;
        r9 = java.lang.Byte.valueOf(r9);
        r7.add(r9);
        r9 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        if (r2 == r9) goto L_0x0067;
    L_0x0065:
        r18 = 1;
    L_0x0067:
        if (r2 == r9) goto L_0x006f;
    L_0x0069:
        r17 = r17 + 1;
        r19 = r17 * r2;
        r16 = r16 + r19;
    L_0x006f:
        r11 = r15.length;
        r19 = r14;
        r9 = 0;
    L_0x0073:
        if (r9 >= r11) goto L_0x007c;
    L_0x0075:
        r20 = r15[r9];
        r19 = r19 + r20;
        r9 = r9 + 1;
        goto L_0x0073;
    L_0x007c:
        switch(r2) {
            case 103: goto L_0x0088;
            case 104: goto L_0x0088;
            case 105: goto L_0x0088;
            default: goto L_0x007f;
        };
    L_0x007f:
        r9 = 96;
        switch(r3) {
            case 99: goto L_0x0172;
            case 100: goto L_0x0103;
            case 101: goto L_0x008d;
            default: goto L_0x0084;
        };
    L_0x0084:
        r11 = 100;
        goto L_0x01af;
    L_0x0088:
        r0 = com.google.zxing.FormatException.getFormatInstance();
        throw r0;
    L_0x008d:
        r11 = 64;
        if (r2 >= r11) goto L_0x00a5;
    L_0x0091:
        if (r5 != r10) goto L_0x009b;
    L_0x0093:
        r5 = r2 + 32;
        r5 = (char) r5;
        r13.append(r5);
        goto L_0x0116;
    L_0x009b:
        r5 = r2 + 32;
        r5 = r5 + 128;
        r5 = (char) r5;
        r13.append(r5);
        goto L_0x0116;
    L_0x00a5:
        if (r2 >= r9) goto L_0x00b9;
    L_0x00a7:
        if (r5 != r10) goto L_0x00b1;
    L_0x00a9:
        r5 = r2 + -64;
        r5 = (char) r5;
        r13.append(r5);
        goto L_0x0116;
    L_0x00b1:
        r5 = r2 + 64;
        r5 = (char) r5;
        r13.append(r5);
        goto L_0x0116;
    L_0x00b9:
        r9 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        if (r2 == r9) goto L_0x00bf;
    L_0x00bd:
        r18 = 0;
    L_0x00bf:
        if (r2 == r9) goto L_0x00fe;
    L_0x00c1:
        switch(r2) {
            case 96: goto L_0x00fa;
            case 97: goto L_0x00fa;
            case 98: goto L_0x00f3;
            case 99: goto L_0x0154;
            case 100: goto L_0x00ef;
            case 101: goto L_0x00d9;
            case 102: goto L_0x00c5;
            default: goto L_0x00c4;
        };
    L_0x00c4:
        goto L_0x00fa;
    L_0x00c5:
        if (r1 == 0) goto L_0x00fa;
    L_0x00c7:
        r9 = r13.length();
        if (r9 != 0) goto L_0x00d3;
    L_0x00cd:
        r9 = "]C1";
        r13.append(r9);
        goto L_0x00fa;
    L_0x00d3:
        r9 = 29;
        r13.append(r9);
        goto L_0x00fa;
    L_0x00d9:
        if (r10 != 0) goto L_0x00e2;
    L_0x00db:
        if (r5 == 0) goto L_0x00e2;
    L_0x00dd:
        r10 = r3;
        r3 = 0;
        r5 = 1;
        goto L_0x0147;
    L_0x00e2:
        if (r10 == 0) goto L_0x00eb;
    L_0x00e4:
        if (r5 == 0) goto L_0x00eb;
    L_0x00e6:
        r10 = r3;
        r3 = 0;
        r5 = 0;
        goto L_0x0147;
    L_0x00eb:
        r5 = r10;
        r9 = 1;
        goto L_0x0162;
    L_0x00ef:
        r9 = r5;
        r5 = r10;
        r3 = 0;
        goto L_0x00f6;
    L_0x00f3:
        r9 = r5;
        r5 = r10;
        r3 = 1;
    L_0x00f6:
        r10 = 100;
        goto L_0x0169;
    L_0x00fa:
        r9 = r5;
        r5 = r10;
        goto L_0x0162;
    L_0x00fe:
        r9 = r5;
        r5 = r10;
        r6 = 1;
        goto L_0x0162;
    L_0x0103:
        if (r2 >= r9) goto L_0x011c;
    L_0x0105:
        if (r5 != r10) goto L_0x010e;
    L_0x0107:
        r5 = r2 + 32;
        r5 = (char) r5;
        r13.append(r5);
        goto L_0x0116;
    L_0x010e:
        r5 = r2 + 32;
        r5 = r5 + 128;
        r5 = (char) r5;
        r13.append(r5);
    L_0x0116:
        r5 = 0;
        r9 = 0;
        r11 = 100;
        goto L_0x01b1;
    L_0x011c:
        r9 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        if (r2 == r9) goto L_0x0122;
    L_0x0120:
        r18 = 0;
    L_0x0122:
        if (r2 == r9) goto L_0x0165;
    L_0x0124:
        switch(r2) {
            case 96: goto L_0x0160;
            case 97: goto L_0x0160;
            case 98: goto L_0x015a;
            case 99: goto L_0x0154;
            case 100: goto L_0x0140;
            case 101: goto L_0x013c;
            case 102: goto L_0x0128;
            default: goto L_0x0127;
        };
    L_0x0127:
        goto L_0x0160;
    L_0x0128:
        if (r1 == 0) goto L_0x0160;
    L_0x012a:
        r9 = r13.length();
        if (r9 != 0) goto L_0x0136;
    L_0x0130:
        r9 = "]C1";
        r13.append(r9);
        goto L_0x0160;
    L_0x0136:
        r9 = 29;
        r13.append(r9);
        goto L_0x0160;
    L_0x013c:
        r9 = r5;
        r5 = r10;
        r3 = 0;
        goto L_0x015d;
    L_0x0140:
        if (r10 != 0) goto L_0x0149;
    L_0x0142:
        if (r5 == 0) goto L_0x0149;
    L_0x0144:
        r10 = r3;
        r3 = 0;
        r5 = 1;
    L_0x0147:
        r9 = 0;
        goto L_0x0169;
    L_0x0149:
        if (r10 == 0) goto L_0x0151;
    L_0x014b:
        if (r5 == 0) goto L_0x0151;
    L_0x014d:
        r10 = r3;
        r3 = 0;
        r5 = 0;
        goto L_0x0147;
    L_0x0151:
        r5 = r10;
        r9 = 1;
        goto L_0x0162;
    L_0x0154:
        r9 = r5;
        r5 = r10;
        r3 = 0;
        r10 = 99;
        goto L_0x0169;
    L_0x015a:
        r9 = r5;
        r5 = r10;
        r3 = 1;
    L_0x015d:
        r10 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        goto L_0x0169;
    L_0x0160:
        r9 = r5;
        r5 = r10;
    L_0x0162:
        r10 = r3;
        r3 = 0;
        goto L_0x0169;
    L_0x0165:
        r9 = r5;
        r5 = r10;
        r6 = 1;
        goto L_0x0162;
    L_0x0169:
        r11 = 100;
        r21 = r5;
        r5 = r3;
        r3 = r10;
        r10 = r21;
        goto L_0x01b1;
    L_0x0172:
        r11 = 100;
        if (r2 >= r11) goto L_0x0183;
    L_0x0176:
        r9 = 10;
        if (r2 >= r9) goto L_0x017f;
    L_0x017a:
        r9 = 48;
        r13.append(r9);
    L_0x017f:
        r13.append(r2);
        goto L_0x01af;
    L_0x0183:
        r9 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        if (r2 == r9) goto L_0x0189;
    L_0x0187:
        r18 = 0;
    L_0x0189:
        if (r2 == r9) goto L_0x01ab;
    L_0x018b:
        switch(r2) {
            case 100: goto L_0x01a7;
            case 101: goto L_0x01a3;
            case 102: goto L_0x018f;
            default: goto L_0x018e;
        };
    L_0x018e:
        goto L_0x01af;
    L_0x018f:
        if (r1 == 0) goto L_0x01af;
    L_0x0191:
        r9 = r13.length();
        if (r9 != 0) goto L_0x019d;
    L_0x0197:
        r9 = "]C1";
        r13.append(r9);
        goto L_0x01af;
    L_0x019d:
        r9 = 29;
        r13.append(r9);
        goto L_0x01af;
    L_0x01a3:
        r9 = r5;
        r3 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        goto L_0x01b0;
    L_0x01a7:
        r9 = r5;
        r3 = 100;
        goto L_0x01b0;
    L_0x01ab:
        r9 = r5;
        r5 = 0;
        r6 = 1;
        goto L_0x01b1;
    L_0x01af:
        r9 = r5;
    L_0x01b0:
        r5 = 0;
    L_0x01b1:
        if (r8 == 0) goto L_0x01bd;
    L_0x01b3:
        r8 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r3 != r8) goto L_0x01ba;
    L_0x01b7:
        r3 = 100;
        goto L_0x01bf;
    L_0x01ba:
        r3 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        goto L_0x01bf;
    L_0x01bd:
        r8 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
    L_0x01bf:
        r8 = r5;
        r5 = r9;
        r9 = r12;
        r12 = r2;
        r2 = r14;
        r14 = r19;
        goto L_0x0053;
    L_0x01c8:
        r1 = r14 - r2;
        r5 = r0.getNextUnset(r14);
        r6 = r24.getSize();
        r8 = r5 - r2;
        r10 = 2;
        r8 = r8 / r10;
        r8 = r8 + r5;
        r6 = java.lang.Math.min(r6, r8);
        r8 = 0;
        r0 = r0.isRange(r5, r6, r8);
        if (r0 != 0) goto L_0x01e7;
    L_0x01e2:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
    L_0x01e7:
        r17 = r17 * r9;
        r16 = r16 - r17;
        r0 = r16 % 103;
        if (r0 == r9) goto L_0x01f4;
    L_0x01ef:
        r0 = com.google.zxing.ChecksumException.getChecksumInstance();
        throw r0;
    L_0x01f4:
        r0 = r13.length();
        if (r0 != 0) goto L_0x01ff;
    L_0x01fa:
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
    L_0x01ff:
        if (r0 <= 0) goto L_0x0212;
    L_0x0201:
        if (r18 == 0) goto L_0x0212;
    L_0x0203:
        r5 = 99;
        if (r3 != r5) goto L_0x020d;
    L_0x0207:
        r3 = r0 + -2;
        r13.delete(r3, r0);
        goto L_0x0212;
    L_0x020d:
        r3 = r0 + -1;
        r13.delete(r3, r0);
    L_0x0212:
        r0 = 1;
        r3 = r4[r0];
        r0 = 0;
        r4 = r4[r0];
        r3 = r3 + r4;
        r0 = (float) r3;
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r0 = r0 / r3;
        r2 = (float) r2;
        r1 = (float) r1;
        r1 = r1 / r3;
        r2 = r2 + r1;
        r1 = r7.size();
        r3 = new byte[r1];
        r4 = 0;
    L_0x0228:
        if (r4 >= r1) goto L_0x0239;
    L_0x022a:
        r5 = r7.get(r4);
        r5 = (java.lang.Byte) r5;
        r5 = r5.byteValue();
        r3[r4] = r5;
        r4 = r4 + 1;
        goto L_0x0228;
    L_0x0239:
        r1 = new com.google.zxing.Result;
        r4 = r13.toString();
        r5 = 2;
        r5 = new com.google.zxing.ResultPoint[r5];
        r6 = new com.google.zxing.ResultPoint;
        r7 = r23;
        r7 = (float) r7;
        r6.<init>(r0, r7);
        r0 = 0;
        r5[r0] = r6;
        r0 = new com.google.zxing.ResultPoint;
        r0.<init>(r2, r7);
        r2 = 1;
        r5[r2] = r0;
        r0 = com.google.zxing.BarcodeFormat.CODE_128;
        r1.<init>(r4, r3, r5, r0);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code128Reader.decodeRow(int, com.google.zxing.common.BitArray, java.util.Map):com.google.zxing.Result");
    }
}
