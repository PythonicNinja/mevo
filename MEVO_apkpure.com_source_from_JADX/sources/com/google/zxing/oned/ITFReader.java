package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Map;

public final class ITFReader extends OneDReader {
    private static final int[] DEFAULT_ALLOWED_LENGTHS = new int[]{6, 8, 10, 12, 14};
    private static final int[] END_PATTERN_REVERSED = new int[]{1, 1, 3};
    private static final float MAX_AVG_VARIANCE = 0.38f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.78f;
    /* renamed from: N */
    private static final int f37N = 1;
    static final int[][] PATTERNS = new int[][]{new int[]{1, 1, 3, 3, 1}, new int[]{3, 1, 1, 1, 3}, new int[]{1, 3, 1, 1, 3}, new int[]{3, 3, 1, 1, 1}, new int[]{1, 1, 3, 1, 3}, new int[]{3, 1, 3, 1, 1}, new int[]{1, 3, 3, 1, 1}, new int[]{1, 1, 1, 3, 3}, new int[]{3, 1, 1, 3, 1}, new int[]{1, 3, 1, 3, 1}};
    private static final int[] START_PATTERN = new int[]{1, 1, 1, 1};
    /* renamed from: W */
    private static final int f38W = 3;
    private int narrowLineWidth = -1;

    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws FormatException, NotFoundException {
        int[] decodeStart = decodeStart(bitArray);
        int[] decodeEnd = decodeEnd(bitArray);
        StringBuilder stringBuilder = new StringBuilder(20);
        decodeMiddle(bitArray, decodeStart[1], decodeEnd[0], stringBuilder);
        bitArray = stringBuilder.toString();
        map = map != null ? (int[]) map.get(DecodeHintType.ALLOWED_LENGTHS) : null;
        if (map == null) {
            map = DEFAULT_ALLOWED_LENGTHS;
        }
        int length = bitArray.length();
        int i2 = 0;
        for (int i3 : r13) {
            if (length == i3) {
                map = true;
                break;
            }
            if (i3 > i2) {
                i2 = i3;
            }
        }
        map = null;
        if (map == null && length > i2) {
            map = true;
        }
        if (map == null) {
            throw FormatException.getFormatInstance();
        }
        r4 = new ResultPoint[2];
        i = (float) i;
        r4[0] = new ResultPoint((float) decodeStart[1], i);
        r4[1] = new ResultPoint((float) decodeEnd[0], i);
        return new Result(bitArray, null, r4, BarcodeFormat.ITF);
    }

    private static void decodeMiddle(BitArray bitArray, int i, int i2, StringBuilder stringBuilder) throws NotFoundException {
        int[] iArr = new int[10];
        int[] iArr2 = new int[5];
        int[] iArr3 = new int[5];
        while (i < i2) {
            OneDReader.recordPattern(bitArray, i, iArr);
            for (int i3 = 0; i3 < 5; i3++) {
                int i4 = i3 * 2;
                iArr2[i3] = iArr[i4];
                iArr3[i3] = iArr[i4 + 1];
            }
            stringBuilder.append((char) (decodeDigit(iArr2) + 48));
            stringBuilder.append((char) (decodeDigit(iArr3) + 48));
            for (int i42 : iArr) {
                i += i42;
            }
        }
    }

    int[] decodeStart(BitArray bitArray) throws NotFoundException {
        int[] findGuardPattern = findGuardPattern(bitArray, skipWhiteSpace(bitArray), START_PATTERN);
        this.narrowLineWidth = (findGuardPattern[1] - findGuardPattern[0]) / 4;
        validateQuietZone(bitArray, findGuardPattern[0]);
        return findGuardPattern;
    }

    private void validateQuietZone(BitArray bitArray, int i) throws NotFoundException {
        int i2 = this.narrowLineWidth * 10;
        if (i2 >= i) {
            i2 = i;
        }
        i--;
        while (i2 > 0 && i >= 0) {
            if (bitArray.get(i)) {
                break;
            }
            i2--;
            i--;
        }
        if (i2 != 0) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private static int skipWhiteSpace(BitArray bitArray) throws NotFoundException {
        int size = bitArray.getSize();
        bitArray = bitArray.getNextSet(0);
        if (bitArray != size) {
            return bitArray;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    int[] decodeEnd(BitArray bitArray) throws NotFoundException {
        bitArray.reverse();
        try {
            int[] findGuardPattern = findGuardPattern(bitArray, skipWhiteSpace(bitArray), END_PATTERN_REVERSED);
            validateQuietZone(bitArray, findGuardPattern[0]);
            int i = findGuardPattern[0];
            findGuardPattern[0] = bitArray.getSize() - findGuardPattern[1];
            findGuardPattern[1] = bitArray.getSize() - i;
            return findGuardPattern;
        } finally {
            bitArray.reverse();
        }
    }

    private static int[] findGuardPattern(BitArray bitArray, int i, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        Object obj = new int[length];
        int size = bitArray.getSize();
        int i2 = i;
        int i3 = 0;
        int i4 = 0;
        while (i < size) {
            if ((bitArray.get(i) ^ i3) != 0) {
                obj[i4] = obj[i4] + 1;
            } else {
                int i5 = length - 1;
                if (i4 != i5) {
                    i4++;
                } else if (OneDReader.patternMatchVariance(obj, iArr, MAX_INDIVIDUAL_VARIANCE) < MAX_AVG_VARIANCE) {
                    return new int[]{i2, i};
                } else {
                    i2 += obj[0] + obj[1];
                    int i6 = length - 2;
                    System.arraycopy(obj, 2, obj, 0, i6);
                    obj[i6] = null;
                    obj[i5] = null;
                    i4--;
                }
                obj[i4] = 1;
                i3 ^= 1;
            }
            i++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int decodeDigit(int[] iArr) throws NotFoundException {
        int length = PATTERNS.length;
        float f = MAX_AVG_VARIANCE;
        int i = -1;
        for (int i2 = 0; i2 < length; i2++) {
            float patternMatchVariance = OneDReader.patternMatchVariance(iArr, PATTERNS[i2], MAX_INDIVIDUAL_VARIANCE);
            if (patternMatchVariance < f) {
                i = i2;
                f = patternMatchVariance;
            }
        }
        if (i >= 0) {
            return i;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
