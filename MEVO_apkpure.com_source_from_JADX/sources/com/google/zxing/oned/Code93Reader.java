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
import okhttp3.internal.http.StatusLine;

public final class Code93Reader extends OneDReader {
    private static final char[] ALPHABET = ALPHABET_STRING.toCharArray();
    private static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*";
    private static final int ASTERISK_ENCODING = CHARACTER_ENCODINGS[47];
    private static final int[] CHARACTER_ENCODINGS = new int[]{276, 328, 324, 322, 296, 292, 290, 336, 274, 266, 424, 420, 418, 404, 402, 394, 360, 356, 354, StatusLine.HTTP_PERM_REDIRECT, 282, 344, 332, 326, 300, 278, 436, 434, 428, 422, 406, 410, 364, 358, 310, 314, 302, 468, 466, 458, 366, 374, 430, 294, 474, 470, 306, 350};
    private final int[] counters = new int[6];
    private final StringBuilder decodeRowResult = new StringBuilder(20);

    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        int i2;
        map = findAsteriskPattern(bitArray);
        int nextSet = bitArray.getNextSet(map[1]);
        int size = bitArray.getSize();
        int[] iArr = this.counters;
        Arrays.fill(iArr, 0);
        CharSequence charSequence = this.decodeRowResult;
        charSequence.setLength(0);
        while (true) {
            OneDReader.recordPattern(bitArray, nextSet, iArr);
            int toPattern = toPattern(iArr);
            if (toPattern < 0) {
                throw NotFoundException.getNotFoundInstance();
            }
            char patternToChar = patternToChar(toPattern);
            charSequence.append(patternToChar);
            i2 = nextSet;
            for (int i3 : iArr) {
                i2 += i3;
            }
            int nextSet2 = bitArray.getNextSet(i2);
            if (patternToChar == '*') {
                break;
            }
            nextSet = nextSet2;
        }
        charSequence.deleteCharAt(charSequence.length() - 1);
        i2 = 0;
        for (int i32 : iArr) {
            i2 += i32;
        }
        if (nextSet2 != size) {
            if (bitArray.get(nextSet2) != null) {
                if (charSequence.length() < 2) {
                    throw NotFoundException.getNotFoundInstance();
                }
                checkChecksums(charSequence);
                charSequence.setLength(charSequence.length() - 2);
                bitArray = decodeExtended(charSequence);
                float f = ((float) nextSet) + (((float) i2) / 2.0f);
                r2 = new ResultPoint[2];
                i = (float) i;
                r2[0] = new ResultPoint(((float) (map[1] + map[0])) / 1073741824, i);
                r2[1] = new ResultPoint(f, i);
                return new Result(bitArray, null, r2, BarcodeFormat.CODE_93);
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private int[] findAsteriskPattern(BitArray bitArray) throws NotFoundException {
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        Arrays.fill(this.counters, 0);
        Object obj = this.counters;
        int length = obj.length;
        int i = nextSet;
        int i2 = 0;
        int i3 = 0;
        while (nextSet < size) {
            if ((bitArray.get(nextSet) ^ i2) != 0) {
                obj[i3] = obj[i3] + 1;
            } else {
                int i4 = length - 1;
                if (i3 != i4) {
                    i3++;
                } else if (toPattern(obj) == ASTERISK_ENCODING) {
                    return new int[]{i, nextSet};
                } else {
                    i += obj[0] + obj[1];
                    int i5 = length - 2;
                    System.arraycopy(obj, 2, obj, 0, i5);
                    obj[i5] = null;
                    obj[i4] = null;
                    i3--;
                }
                obj[i3] = 1;
                i2 ^= 1;
            }
            nextSet++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int toPattern(int[] iArr) {
        int round;
        int length = iArr.length;
        int i = 0;
        for (int round2 : iArr) {
            i += round2;
        }
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            round2 = Math.round((((float) iArr[i2]) * 9.0f) / ((float) i));
            if (round2 >= 1) {
                if (round2 <= 4) {
                    if ((i2 & 1) == 0) {
                        int i4 = i3;
                        for (i3 = 0; i3 < round2; i3++) {
                            i4 = (i4 << 1) | 1;
                        }
                        i3 = i4;
                    } else {
                        i3 <<= round2;
                    }
                    i2++;
                }
            }
            return -1;
        }
        return i3;
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
            if (charAt < 'a' || charAt > 'd') {
                stringBuilder.append(charAt);
            } else if (i >= length - 1) {
                throw FormatException.getFormatInstance();
            } else {
                i++;
                char charAt2 = charSequence.charAt(i);
                switch (charAt) {
                    case 'a':
                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                            charAt = (char) (charAt2 - 64);
                            break;
                        }
                        throw FormatException.getFormatInstance();
                    case 'b':
                        if (charAt2 < 'A' || charAt2 > 'E') {
                            if (charAt2 < 'F' || charAt2 > 'J') {
                                if (charAt2 < 'K' || charAt2 > 'O') {
                                    if (charAt2 < 'P' || charAt2 > 'S') {
                                        if (charAt2 >= 'T' && charAt2 <= 'Z') {
                                            charAt = '';
                                            break;
                                        }
                                        throw FormatException.getFormatInstance();
                                    }
                                    charAt = (char) (charAt2 + 43);
                                    break;
                                }
                                charAt = (char) (charAt2 + 16);
                                break;
                            }
                            charAt = (char) (charAt2 - 11);
                            break;
                        }
                        charAt = (char) (charAt2 - 38);
                        break;
                        break;
                    case 'c':
                        if (charAt2 >= 'A' && charAt2 <= 'O') {
                            charAt = (char) (charAt2 - 32);
                            break;
                        } else if (charAt2 == 'Z') {
                            charAt = ':';
                            break;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                        break;
                    case 'd':
                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                            charAt = (char) (charAt2 + 32);
                            break;
                        }
                        throw FormatException.getFormatInstance();
                        break;
                    default:
                        charAt = '\u0000';
                        break;
                }
                stringBuilder.append(charAt);
            }
            i++;
        }
        return stringBuilder.toString();
    }

    private static void checkChecksums(CharSequence charSequence) throws ChecksumException {
        int length = charSequence.length();
        checkOneChecksum(charSequence, length - 2, 20);
        checkOneChecksum(charSequence, length - 1, 15);
    }

    private static void checkOneChecksum(CharSequence charSequence, int i, int i2) throws ChecksumException {
        int i3 = 0;
        int i4 = 1;
        for (int i5 = i - 1; i5 >= 0; i5--) {
            i3 += ALPHABET_STRING.indexOf(charSequence.charAt(i5)) * i4;
            i4++;
            if (i4 > i2) {
                i4 = 1;
            }
        }
        if (charSequence.charAt(i) != ALPHABET[i3 % 47]) {
            throw ChecksumException.getChecksumInstance();
        }
    }
}
