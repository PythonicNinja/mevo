package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

public abstract class UPCEANReader extends OneDReader {
    static final int[][] L_AND_G_PATTERNS = new int[20][];
    static final int[][] L_PATTERNS = new int[][]{new int[]{3, 2, 1, 1}, new int[]{2, 2, 2, 1}, new int[]{2, 1, 2, 2}, new int[]{1, 4, 1, 1}, new int[]{1, 1, 3, 2}, new int[]{1, 2, 3, 1}, new int[]{1, 1, 1, 4}, new int[]{1, 3, 1, 2}, new int[]{1, 2, 1, 3}, new int[]{3, 1, 1, 2}};
    private static final float MAX_AVG_VARIANCE = 0.48f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.7f;
    static final int[] MIDDLE_PATTERN = new int[]{1, 1, 1, 1, 1};
    static final int[] START_END_PATTERN = new int[]{1, 1, 1};
    private final StringBuilder decodeRowStringBuffer = new StringBuilder(20);
    private final EANManufacturerOrgSupport eanManSupport = new EANManufacturerOrgSupport();
    private final UPCEANExtensionSupport extensionReader = new UPCEANExtensionSupport();

    protected abstract int decodeMiddle(BitArray bitArray, int[] iArr, StringBuilder stringBuilder) throws NotFoundException;

    abstract BarcodeFormat getBarcodeFormat();

    static {
        int i = 10;
        System.arraycopy(L_PATTERNS, 0, L_AND_G_PATTERNS, 0, 10);
        while (i < 20) {
            int[] iArr = L_PATTERNS[i - 10];
            int[] iArr2 = new int[iArr.length];
            for (int i2 = 0; i2 < iArr.length; i2++) {
                iArr2[i2] = iArr[(iArr.length - i2) - 1];
            }
            L_AND_G_PATTERNS[i] = iArr2;
            i++;
        }
    }

    protected UPCEANReader() {
    }

    static int[] findStartGuardPattern(BitArray bitArray) throws NotFoundException {
        int[] iArr = new int[START_END_PATTERN.length];
        int[] iArr2 = null;
        boolean z = false;
        int i = 0;
        while (!z) {
            Arrays.fill(iArr, 0, START_END_PATTERN.length, 0);
            iArr2 = findGuardPattern(bitArray, i, false, START_END_PATTERN, iArr);
            i = iArr2[0];
            int i2 = iArr2[1];
            int i3 = i - (i2 - i);
            if (i3 >= 0) {
                z = bitArray.isRange(i3, i, false);
            }
            i = i2;
        }
        return iArr2;
    }

    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        return decodeRow(i, bitArray, findStartGuardPattern(bitArray), map);
    }

    public com.google.zxing.Result decodeRow(int r12, com.google.zxing.common.BitArray r13, int[] r14, java.util.Map<com.google.zxing.DecodeHintType, ?> r15) throws com.google.zxing.NotFoundException, com.google.zxing.ChecksumException, com.google.zxing.FormatException {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r11 = this;
        r0 = 0;
        if (r15 != 0) goto L_0x0005;
    L_0x0003:
        r1 = r0;
        goto L_0x000d;
    L_0x0005:
        r1 = com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK;
        r1 = r15.get(r1);
        r1 = (com.google.zxing.ResultPointCallback) r1;
    L_0x000d:
        r2 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r3 = 1;
        r4 = 0;
        if (r1 == 0) goto L_0x0023;
    L_0x0013:
        r5 = new com.google.zxing.ResultPoint;
        r6 = r14[r4];
        r7 = r14[r3];
        r6 = r6 + r7;
        r6 = (float) r6;
        r6 = r6 / r2;
        r7 = (float) r12;
        r5.<init>(r6, r7);
        r1.foundPossibleResultPoint(r5);
    L_0x0023:
        r5 = r11.decodeRowStringBuffer;
        r5.setLength(r4);
        r6 = r11.decodeMiddle(r13, r14, r5);
        if (r1 == 0) goto L_0x0038;
    L_0x002e:
        r7 = new com.google.zxing.ResultPoint;
        r8 = (float) r6;
        r9 = (float) r12;
        r7.<init>(r8, r9);
        r1.foundPossibleResultPoint(r7);
    L_0x0038:
        r6 = r11.decodeEnd(r13, r6);
        if (r1 == 0) goto L_0x004e;
    L_0x003e:
        r7 = new com.google.zxing.ResultPoint;
        r8 = r6[r4];
        r9 = r6[r3];
        r8 = r8 + r9;
        r8 = (float) r8;
        r8 = r8 / r2;
        r9 = (float) r12;
        r7.<init>(r8, r9);
        r1.foundPossibleResultPoint(r7);
    L_0x004e:
        r1 = r6[r3];
        r7 = r6[r4];
        r7 = r1 - r7;
        r7 = r7 + r1;
        r8 = r13.getSize();
        if (r7 >= r8) goto L_0x0109;
    L_0x005b:
        r1 = r13.isRange(r1, r7, r4);
        if (r1 != 0) goto L_0x0063;
    L_0x0061:
        goto L_0x0109;
    L_0x0063:
        r1 = r5.toString();
        r5 = r1.length();
        r7 = 8;
        if (r5 >= r7) goto L_0x0074;
    L_0x006f:
        r12 = com.google.zxing.FormatException.getFormatInstance();
        throw r12;
    L_0x0074:
        r5 = r11.checkChecksum(r1);
        if (r5 != 0) goto L_0x007f;
    L_0x007a:
        r12 = com.google.zxing.ChecksumException.getChecksumInstance();
        throw r12;
    L_0x007f:
        r5 = r14[r3];
        r14 = r14[r4];
        r5 = r5 + r14;
        r14 = (float) r5;
        r14 = r14 / r2;
        r5 = r6[r3];
        r7 = r6[r4];
        r5 = r5 + r7;
        r5 = (float) r5;
        r5 = r5 / r2;
        r2 = r11.getBarcodeFormat();
        r7 = new com.google.zxing.Result;
        r8 = 2;
        r8 = new com.google.zxing.ResultPoint[r8];
        r9 = new com.google.zxing.ResultPoint;
        r10 = (float) r12;
        r9.<init>(r14, r10);
        r8[r4] = r9;
        r14 = new com.google.zxing.ResultPoint;
        r14.<init>(r5, r10);
        r8[r3] = r14;
        r7.<init>(r1, r0, r8, r2);
        r14 = r11.extensionReader;	 Catch:{ ReaderException -> 0x00d0 }
        r5 = r6[r3];	 Catch:{ ReaderException -> 0x00d0 }
        r12 = r14.decodeRow(r12, r13, r5);	 Catch:{ ReaderException -> 0x00d0 }
        r13 = com.google.zxing.ResultMetadataType.UPC_EAN_EXTENSION;	 Catch:{ ReaderException -> 0x00d0 }
        r14 = r12.getText();	 Catch:{ ReaderException -> 0x00d0 }
        r7.putMetadata(r13, r14);	 Catch:{ ReaderException -> 0x00d0 }
        r13 = r12.getResultMetadata();	 Catch:{ ReaderException -> 0x00d0 }
        r7.putAllMetadata(r13);	 Catch:{ ReaderException -> 0x00d0 }
        r13 = r12.getResultPoints();	 Catch:{ ReaderException -> 0x00d0 }
        r7.addResultPoints(r13);	 Catch:{ ReaderException -> 0x00d0 }
        r12 = r12.getText();	 Catch:{ ReaderException -> 0x00d0 }
        r12 = r12.length();	 Catch:{ ReaderException -> 0x00d0 }
        goto L_0x00d1;
    L_0x00d0:
        r12 = 0;
    L_0x00d1:
        if (r15 != 0) goto L_0x00d4;
    L_0x00d3:
        goto L_0x00dd;
    L_0x00d4:
        r13 = com.google.zxing.DecodeHintType.ALLOWED_EAN_EXTENSIONS;
        r13 = r15.get(r13);
        r0 = r13;
        r0 = (int[]) r0;
    L_0x00dd:
        if (r0 == 0) goto L_0x00f3;
    L_0x00df:
        r13 = r0.length;
        r14 = 0;
    L_0x00e1:
        if (r14 >= r13) goto L_0x00eb;
    L_0x00e3:
        r15 = r0[r14];
        if (r12 != r15) goto L_0x00e8;
    L_0x00e7:
        goto L_0x00ec;
    L_0x00e8:
        r14 = r14 + 1;
        goto L_0x00e1;
    L_0x00eb:
        r3 = 0;
    L_0x00ec:
        if (r3 != 0) goto L_0x00f3;
    L_0x00ee:
        r12 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r12;
    L_0x00f3:
        r12 = com.google.zxing.BarcodeFormat.EAN_13;
        if (r2 == r12) goto L_0x00fb;
    L_0x00f7:
        r12 = com.google.zxing.BarcodeFormat.UPC_A;
        if (r2 != r12) goto L_0x0108;
    L_0x00fb:
        r12 = r11.eanManSupport;
        r12 = r12.lookupCountryIdentifier(r1);
        if (r12 == 0) goto L_0x0108;
    L_0x0103:
        r13 = com.google.zxing.ResultMetadataType.POSSIBLE_COUNTRY;
        r7.putMetadata(r13, r12);
    L_0x0108:
        return r7;
    L_0x0109:
        r12 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.UPCEANReader.decodeRow(int, com.google.zxing.common.BitArray, int[], java.util.Map):com.google.zxing.Result");
    }

    boolean checkChecksum(String str) throws FormatException {
        return checkStandardUPCEANChecksum(str);
    }

    static boolean checkStandardUPCEANChecksum(CharSequence charSequence) throws FormatException {
        int length = charSequence.length();
        boolean z = false;
        if (length == 0) {
            return false;
        }
        int i = length - 2;
        int i2 = 0;
        while (i >= 0) {
            int charAt = charSequence.charAt(i) - 48;
            if (charAt >= 0) {
                if (charAt <= 9) {
                    i2 += charAt;
                    i -= 2;
                }
            }
            throw FormatException.getFormatInstance();
        }
        i2 *= 3;
        length--;
        while (length >= 0) {
            charAt = charSequence.charAt(length) - 48;
            if (charAt >= 0) {
                if (charAt <= 9) {
                    i2 += charAt;
                    length -= 2;
                }
            }
            throw FormatException.getFormatInstance();
        }
        if (i2 % 10 == 0) {
            z = true;
        }
        return z;
    }

    int[] decodeEnd(BitArray bitArray, int i) throws NotFoundException {
        return findGuardPattern(bitArray, i, false, START_END_PATTERN);
    }

    static int[] findGuardPattern(BitArray bitArray, int i, boolean z, int[] iArr) throws NotFoundException {
        return findGuardPattern(bitArray, i, z, iArr, new int[iArr.length]);
    }

    private static int[] findGuardPattern(BitArray bitArray, int i, boolean z, int[] iArr, int[] iArr2) throws NotFoundException {
        int length = iArr.length;
        int size = bitArray.getSize();
        i = z ? bitArray.getNextUnset(i) : bitArray.getNextSet(i);
        int i2 = i;
        int i3 = 0;
        while (i < size) {
            boolean z2 = true;
            if ((bitArray.get(i) ^ z) != 0) {
                iArr2[i3] = iArr2[i3] + 1;
            } else {
                int i4 = length - 1;
                if (i3 != i4) {
                    i3++;
                } else if (OneDReader.patternMatchVariance(iArr2, iArr, MAX_INDIVIDUAL_VARIANCE) < MAX_AVG_VARIANCE) {
                    return new int[]{i2, i};
                } else {
                    i2 += iArr2[0] + iArr2[1];
                    int i5 = length - 2;
                    System.arraycopy(iArr2, 2, iArr2, 0, i5);
                    iArr2[i5] = 0;
                    iArr2[i4] = 0;
                    i3--;
                }
                iArr2[i3] = 1;
                if (z) {
                    z2 = false;
                }
                z = z2;
            }
            i++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    static int decodeDigit(BitArray bitArray, int[] iArr, int i, int[][] iArr2) throws NotFoundException {
        OneDReader.recordPattern(bitArray, i, iArr);
        bitArray = iArr2.length;
        i = 1056293519;
        int i2 = -1;
        for (int i3 = 0; i3 < bitArray; i3++) {
            int patternMatchVariance = OneDReader.patternMatchVariance(iArr, iArr2[i3], MAX_INDIVIDUAL_VARIANCE);
            if (patternMatchVariance < i) {
                i2 = i3;
                i = patternMatchVariance;
            }
        }
        if (i2 >= 0) {
            return i2;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
