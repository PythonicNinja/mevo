package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

public final class Code39Reader extends OneDReader {
    private static final char[] ALPHABET = ALPHABET_STRING.toCharArray();
    static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%";
    private static final int ASTERISK_ENCODING = CHARACTER_ENCODINGS[39];
    static final int[] CHARACTER_ENCODINGS = new int[]{52, 289, 97, 352, 49, 304, 112, 37, 292, 100, 265, 73, 328, 25, 280, 88, 13, 268, 76, 28, 259, 67, 322, 19, 274, 82, 7, 262, 70, 22, 385, 193, 448, 145, 400, 208, 133, 388, 196, 148, 168, 162, 138, 42};
    private final int[] counters;
    private final StringBuilder decodeRowResult;
    private final boolean extendedMode;
    private final boolean usingCheckDigit;

    public Code39Reader() {
        this(false);
    }

    public Code39Reader(boolean z) {
        this(z, false);
    }

    public Code39Reader(boolean z, boolean z2) {
        this.usingCheckDigit = z;
        this.extendedMode = z2;
        this.decodeRowResult = new StringBuilder(true);
        this.counters = new int[true];
    }

    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        int nextSet;
        map = this.counters;
        Arrays.fill(map, 0);
        Object obj = this.decodeRowResult;
        obj.setLength(0);
        int[] findAsteriskPattern = findAsteriskPattern(bitArray, map);
        int nextSet2 = bitArray.getNextSet(findAsteriskPattern[1]);
        int size = bitArray.getSize();
        while (true) {
            OneDReader.recordPattern(bitArray, nextSet2, map);
            int toNarrowWidePattern = toNarrowWidePattern(map);
            if (toNarrowWidePattern < 0) {
                throw NotFoundException.getNotFoundInstance();
            }
            char patternToChar = patternToChar(toNarrowWidePattern);
            obj.append(patternToChar);
            int i2 = nextSet2;
            for (int i3 : map) {
                i2 += i3;
            }
            nextSet = bitArray.getNextSet(i2);
            if (patternToChar == '*') {
                break;
            }
            nextSet2 = nextSet;
        }
        obj.setLength(obj.length() - 1);
        int i4 = 0;
        for (int i22 : map) {
            i4 += i22;
        }
        bitArray = (nextSet - nextSet2) - i4;
        if (nextSet == size || bitArray * 2 >= i4) {
            if (this.usingCheckDigit != null) {
                bitArray = obj.length() - 1;
                toNarrowWidePattern = 0;
                for (size = 0; size < bitArray; size++) {
                    toNarrowWidePattern += ALPHABET_STRING.indexOf(this.decodeRowResult.charAt(size));
                }
                if (obj.charAt(bitArray) != ALPHABET[toNarrowWidePattern % 43]) {
                    throw ChecksumException.getChecksumInstance();
                }
                obj.setLength(bitArray);
            }
            if (obj.length() == null) {
                throw NotFoundException.getNotFoundInstance();
            }
            if (this.extendedMode != null) {
                bitArray = decodeExtended(obj);
            } else {
                bitArray = obj.toString();
            }
            float f = ((float) nextSet2) + (((float) i4) / 2.0f);
            map = new ResultPoint[2];
            i = (float) i;
            map[0] = new ResultPoint(((float) (findAsteriskPattern[1] + findAsteriskPattern[0])) / 2.0f, i);
            map[1] = new ResultPoint(f, i);
            return new Result(bitArray, null, map, BarcodeFormat.CODE_39);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int[] findAsteriskPattern(BitArray bitArray, int[] iArr) throws NotFoundException {
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        int length = iArr.length;
        int i = nextSet;
        int i2 = 0;
        int i3 = 0;
        while (nextSet < size) {
            if ((bitArray.get(nextSet) ^ i2) != 0) {
                iArr[i3] = iArr[i3] + 1;
            } else {
                int i4 = length - 1;
                if (i3 != i4) {
                    i3++;
                } else if (toNarrowWidePattern(iArr) == ASTERISK_ENCODING && bitArray.isRange(Math.max(0, i - ((nextSet - i) / 2)), i, false)) {
                    return new int[]{i, nextSet};
                } else {
                    i += iArr[0] + iArr[1];
                    int i5 = length - 2;
                    System.arraycopy(iArr, 2, iArr, 0, i5);
                    iArr[i5] = 0;
                    iArr[i4] = 0;
                    i3--;
                }
                iArr[i3] = 1;
                i2 ^= 1;
            }
            nextSet++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int toNarrowWidePattern(int[] iArr) {
        int i;
        int length = iArr.length;
        int i2 = 0;
        while (true) {
            int i3 = Integer.MAX_VALUE;
            for (int i4 : iArr) {
                if (i4 < i3 && i4 > r2) {
                    i3 = i4;
                }
            }
            int i5 = 0;
            i = 0;
            int i42 = 0;
            for (i2 = 0; i2 < length; i2++) {
                int i6 = iArr[i2];
                if (i6 > i3) {
                    i |= 1 << ((length - 1) - i2);
                    i5++;
                    i42 += i6;
                }
            }
            if (i5 == 3) {
                break;
            } else if (i5 <= 3) {
                return -1;
            } else {
                i2 = i3;
            }
        }
        for (int i7 = 0; i7 < length && i5 > 0; i7++) {
            i2 = iArr[i7];
            if (i2 > i3) {
                i5--;
                if (i2 * 2 >= i42) {
                    return -1;
                }
            }
        }
        return i;
    }

    private static char patternToChar(int i) throws NotFoundException {
        for (int i2 = 0; i2 < CHARACTER_ENCODINGS.length; i2++) {
            if (CHARACTER_ENCODINGS[i2] == i) {
                return ALPHABET[i2];
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static String decodeExtended(CharSequence charSequence) throws FormatException {
        int length = charSequence.length();
        StringBuilder stringBuilder = new StringBuilder(length);
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (!(charAt == '+' || charAt == '$' || charAt == '%')) {
                if (charAt != '/') {
                    stringBuilder.append(charAt);
                    i++;
                }
            }
            i++;
            char charAt2 = charSequence.charAt(i);
            if (charAt != '+') {
                if (charAt != '/') {
                    switch (charAt) {
                        case '$':
                            if (charAt2 >= 'A' && charAt2 <= 'Z') {
                                charAt = (char) (charAt2 - 64);
                                break;
                            }
                            throw FormatException.getFormatInstance();
                            break;
                        case '%':
                            if (charAt2 < 'A' || charAt2 > 'E') {
                                if (charAt2 >= 'F' && charAt2 <= 'W') {
                                    charAt = (char) (charAt2 - 11);
                                    break;
                                }
                                throw FormatException.getFormatInstance();
                            }
                            charAt = (char) (charAt2 - 38);
                            break;
                            break;
                        default:
                            charAt = '\u0000';
                            break;
                    }
                } else if (charAt2 >= 'A' && charAt2 <= 'O') {
                    charAt = (char) (charAt2 - 32);
                } else if (charAt2 == 'Z') {
                    charAt = ':';
                } else {
                    throw FormatException.getFormatInstance();
                }
            } else if (charAt2 < 'A' || charAt2 > 'Z') {
                throw FormatException.getFormatInstance();
            } else {
                charAt = (char) (charAt2 + 32);
            }
            stringBuilder.append(charAt);
            i++;
        }
        return stringBuilder.toString();
    }
}
