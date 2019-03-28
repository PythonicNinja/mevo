package com.google.zxing.oned;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

public abstract class OneDReader implements Reader {
    public abstract Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException;

    public void reset() {
    }

    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, FormatException {
        return decode(binaryBitmap, null);
    }

    public Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        try {
            return doDecode(binaryBitmap, map);
        } catch (NotFoundException e) {
            Object obj = (map == null || !map.containsKey(DecodeHintType.TRY_HARDER)) ? null : 1;
            if (obj == null || !binaryBitmap.isRotateSupported()) {
                throw e;
            }
            binaryBitmap = binaryBitmap.rotateCounterClockwise();
            map = doDecode(binaryBitmap, map);
            Map resultMetadata = map.getResultMetadata();
            int i = 270;
            if (resultMetadata != null && resultMetadata.containsKey(ResultMetadataType.ORIENTATION)) {
                i = (((Integer) resultMetadata.get(ResultMetadataType.ORIENTATION)).intValue() + 270) % 360;
            }
            map.putMetadata(ResultMetadataType.ORIENTATION, Integer.valueOf(i));
            ResultPoint[] resultPoints = map.getResultPoints();
            if (resultPoints != null) {
                binaryBitmap = binaryBitmap.getHeight();
                for (int i2 = 0; i2 < resultPoints.length; i2++) {
                    resultPoints[i2] = new ResultPoint((((float) binaryBitmap) - resultPoints[i2].getY()) - 1.0f, resultPoints[i2].getX());
                }
            }
            return map;
        }
    }

    private com.google.zxing.Result doDecode(com.google.zxing.BinaryBitmap r20, java.util.Map<com.google.zxing.DecodeHintType, ?> r21) throws com.google.zxing.NotFoundException {
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
        r19 = this;
        r0 = r21;
        r1 = r20.getWidth();
        r2 = r20.getHeight();
        r3 = new com.google.zxing.common.BitArray;
        r3.<init>(r1);
        r4 = r2 >> 1;
        r5 = 0;
        r6 = 1;
        if (r0 == 0) goto L_0x001f;
    L_0x0015:
        r7 = com.google.zxing.DecodeHintType.TRY_HARDER;
        r7 = r0.containsKey(r7);
        if (r7 == 0) goto L_0x001f;
    L_0x001d:
        r7 = 1;
        goto L_0x0020;
    L_0x001f:
        r7 = 0;
    L_0x0020:
        if (r7 == 0) goto L_0x0025;
    L_0x0022:
        r8 = 8;
        goto L_0x0026;
    L_0x0025:
        r8 = 5;
    L_0x0026:
        r8 = r2 >> r8;
        r8 = java.lang.Math.max(r6, r8);
        if (r7 == 0) goto L_0x0030;
    L_0x002e:
        r7 = r2;
        goto L_0x0032;
    L_0x0030:
        r7 = 15;
    L_0x0032:
        r9 = r0;
        r0 = 0;
    L_0x0034:
        if (r0 >= r7) goto L_0x00ed;
    L_0x0036:
        r10 = r0 + 1;
        r11 = r10 / 2;
        r0 = r0 & 1;
        if (r0 != 0) goto L_0x0040;
    L_0x003e:
        r0 = 1;
        goto L_0x0041;
    L_0x0040:
        r0 = 0;
    L_0x0041:
        if (r0 == 0) goto L_0x0044;
    L_0x0043:
        goto L_0x0045;
    L_0x0044:
        r11 = -r11;
    L_0x0045:
        r11 = r11 * r8;
        r11 = r11 + r4;
        if (r11 < 0) goto L_0x00ed;
    L_0x004a:
        if (r11 < r2) goto L_0x004e;
    L_0x004c:
        goto L_0x00ed;
    L_0x004e:
        r0 = r20;
        r12 = r0.getBlackRow(r11, r3);	 Catch:{ NotFoundException -> 0x00de }
        r3 = 0;
    L_0x0055:
        r13 = 2;
        if (r3 >= r13) goto L_0x00d5;
    L_0x0058:
        if (r3 != r6) goto L_0x0077;
    L_0x005a:
        r12.reverse();
        if (r9 == 0) goto L_0x0077;
    L_0x005f:
        r13 = com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK;
        r13 = r9.containsKey(r13);
        if (r13 == 0) goto L_0x0077;
    L_0x0067:
        r13 = new java.util.EnumMap;
        r14 = com.google.zxing.DecodeHintType.class;
        r13.<init>(r14);
        r13.putAll(r9);
        r9 = com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK;
        r13.remove(r9);
        r9 = r13;
    L_0x0077:
        r13 = r19;
        r14 = r13.decodeRow(r11, r12, r9);	 Catch:{ ReaderException -> 0x00c6 }
        if (r3 != r6) goto L_0x00c5;	 Catch:{ ReaderException -> 0x00c6 }
    L_0x007f:
        r15 = com.google.zxing.ResultMetadataType.ORIENTATION;	 Catch:{ ReaderException -> 0x00c6 }
        r6 = 180; // 0xb4 float:2.52E-43 double:8.9E-322;	 Catch:{ ReaderException -> 0x00c6 }
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ ReaderException -> 0x00c6 }
        r14.putMetadata(r15, r6);	 Catch:{ ReaderException -> 0x00c6 }
        r6 = r14.getResultPoints();	 Catch:{ ReaderException -> 0x00c6 }
        if (r6 == 0) goto L_0x00c5;	 Catch:{ ReaderException -> 0x00c6 }
    L_0x0090:
        r15 = new com.google.zxing.ResultPoint;	 Catch:{ ReaderException -> 0x00c6 }
        r0 = (float) r1;
        r16 = r1;
        r1 = r6[r5];	 Catch:{ ReaderException -> 0x00c8 }
        r1 = r1.getX();	 Catch:{ ReaderException -> 0x00c8 }
        r1 = r0 - r1;
        r17 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r1 = r1 - r17;
        r18 = r2;
        r2 = r6[r5];	 Catch:{ ReaderException -> 0x00ca }
        r2 = r2.getY();	 Catch:{ ReaderException -> 0x00ca }
        r15.<init>(r1, r2);	 Catch:{ ReaderException -> 0x00ca }
        r6[r5] = r15;	 Catch:{ ReaderException -> 0x00ca }
        r1 = new com.google.zxing.ResultPoint;	 Catch:{ ReaderException -> 0x00ca }
        r2 = 1;
        r15 = r6[r2];	 Catch:{ ReaderException -> 0x00cb }
        r15 = r15.getX();	 Catch:{ ReaderException -> 0x00cb }
        r0 = r0 - r15;	 Catch:{ ReaderException -> 0x00cb }
        r0 = r0 - r17;	 Catch:{ ReaderException -> 0x00cb }
        r15 = r6[r2];	 Catch:{ ReaderException -> 0x00cb }
        r15 = r15.getY();	 Catch:{ ReaderException -> 0x00cb }
        r1.<init>(r0, r15);	 Catch:{ ReaderException -> 0x00cb }
        r6[r2] = r1;	 Catch:{ ReaderException -> 0x00cb }
    L_0x00c5:
        return r14;
    L_0x00c6:
        r16 = r1;
    L_0x00c8:
        r18 = r2;
    L_0x00ca:
        r2 = 1;
    L_0x00cb:
        r3 = r3 + 1;
        r1 = r16;
        r2 = r18;
        r0 = r20;
        r6 = 1;
        goto L_0x0055;
    L_0x00d5:
        r13 = r19;
        r16 = r1;
        r18 = r2;
        r2 = 1;
        r3 = r12;
        goto L_0x00e5;
    L_0x00de:
        r13 = r19;
        r16 = r1;
        r18 = r2;
        r2 = 1;
    L_0x00e5:
        r0 = r10;
        r1 = r16;
        r2 = r18;
        r6 = 1;
        goto L_0x0034;
    L_0x00ed:
        r13 = r19;
        r0 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.OneDReader.doDecode(com.google.zxing.BinaryBitmap, java.util.Map):com.google.zxing.Result");
    }

    protected static void recordPattern(BitArray bitArray, int i, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        Arrays.fill(iArr, 0, length, 0);
        int size = bitArray.getSize();
        if (i >= size) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i2 = bitArray.get(i) ^ 1;
        int i3 = 0;
        while (i < size) {
            if ((bitArray.get(i) ^ i2) != 0) {
                iArr[i3] = iArr[i3] + 1;
            } else {
                i3++;
                if (i3 == length) {
                    break;
                }
                iArr[i3] = 1;
                i2 = i2 == 0 ? 1 : 0;
            }
            i++;
        }
        if (i3 == length) {
            return;
        }
        if (i3 != length - 1 || i != size) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    protected static void recordPatternInReverse(BitArray bitArray, int i, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        boolean z = bitArray.get(i);
        while (i > 0 && length >= 0) {
            i--;
            if (bitArray.get(i) != z) {
                length--;
                z = !z;
            }
        }
        if (length >= 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        recordPattern(bitArray, i + 1, iArr);
    }

    protected static float patternMatchVariance(int[] iArr, int[] iArr2, float f) {
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
