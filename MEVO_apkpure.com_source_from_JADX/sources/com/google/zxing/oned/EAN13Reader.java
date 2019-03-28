package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class EAN13Reader extends UPCEANReader {
    static final int[] FIRST_DIGIT_ENCODINGS = new int[]{0, 11, 13, 14, 19, 25, 28, 21, 22, 26};
    private final int[] decodeMiddleCounters = new int[4];

    protected int decodeMiddle(BitArray bitArray, int[] iArr, StringBuilder stringBuilder) throws NotFoundException {
        int[] iArr2 = this.decodeMiddleCounters;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int size = bitArray.getSize();
        int i = iArr[1];
        iArr = null;
        int i2 = 0;
        while (iArr < 6 && i < size) {
            int decodeDigit = UPCEANReader.decodeDigit(bitArray, iArr2, i, L_AND_G_PATTERNS);
            stringBuilder.append((char) ((decodeDigit % 10) + 48));
            int i3 = i;
            for (int i4 : iArr2) {
                i3 += i4;
            }
            if (decodeDigit >= 10) {
                i2 = (1 << (5 - iArr)) | i2;
            }
            iArr++;
            i = i3;
        }
        determineFirstDigit(stringBuilder, i2);
        int i5 = UPCEANReader.findGuardPattern(bitArray, i, true, MIDDLE_PATTERN)[1];
        iArr = null;
        while (iArr < 6 && i5 < size) {
            stringBuilder.append((char) (UPCEANReader.decodeDigit(bitArray, iArr2, i5, L_PATTERNS) + 48));
            i2 = i5;
            for (int i6 : iArr2) {
                i2 += i6;
            }
            iArr++;
            i5 = i2;
        }
        return i5;
    }

    BarcodeFormat getBarcodeFormat() {
        return BarcodeFormat.EAN_13;
    }

    private static void determineFirstDigit(StringBuilder stringBuilder, int i) throws NotFoundException {
        for (int i2 = 0; i2 < 10; i2++) {
            if (i == FIRST_DIGIT_ENCODINGS[i2]) {
                stringBuilder.insert(0, (char) (i2 + 48));
                return;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
