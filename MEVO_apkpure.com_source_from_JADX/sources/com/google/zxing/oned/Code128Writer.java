package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class Code128Writer extends OneDimensionalCodeWriter {
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_B = 100;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final char ESCAPE_FNC_1 = 'ñ';
    private static final char ESCAPE_FNC_2 = 'ò';
    private static final char ESCAPE_FNC_3 = 'ó';
    private static final char ESCAPE_FNC_4 = 'ô';

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.CODE_128) {
            return super.encode(str, barcodeFormat, i, i2, map);
        }
        i = new StringBuilder();
        i.append("Can only encode CODE_128, but got ");
        i.append(barcodeFormat);
        throw new IllegalArgumentException(i.toString());
    }

    public boolean[] encode(String str) {
        int length = str.length();
        if (length >= 1) {
            if (length <= 80) {
                int i = 0;
                for (int i2 = 0; i2 < length; i2++) {
                    char charAt = str.charAt(i2);
                    if (charAt < ' ' || charAt > '~') {
                        switch (charAt) {
                            case 'ñ':
                            case 'ò':
                            case 'ó':
                            case 'ô':
                                break;
                            default:
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("Bad character in input: ");
                                stringBuilder.append(charAt);
                                throw new IllegalArgumentException(stringBuilder.toString());
                        }
                    }
                }
                Collection<int[]> arrayList = new ArrayList();
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                int i6 = 1;
                while (i3 < length) {
                    int i7 = 99;
                    int i8 = 100;
                    if (!isDigits(str, i3, i4 == 99 ? 2 : 4)) {
                        i7 = 100;
                    }
                    if (i7 == i4) {
                        switch (str.charAt(i3)) {
                            case 'ñ':
                                i8 = 102;
                                break;
                            case 'ò':
                                i8 = 97;
                                break;
                            case 'ó':
                                i8 = 96;
                                break;
                            case 'ô':
                                break;
                            default:
                                if (i4 != 100) {
                                    i8 = Integer.parseInt(str.substring(i3, i3 + 2));
                                    i3++;
                                    break;
                                }
                                i8 = str.charAt(i3) - 32;
                                break;
                        }
                        i3++;
                    } else {
                        i8 = i4 == 0 ? i7 == 100 ? 104 : 105 : i7;
                        i4 = i7;
                    }
                    arrayList.add(Code128Reader.CODE_PATTERNS[i8]);
                    i5 += i8 * i6;
                    if (i3 != 0) {
                        i6++;
                    }
                }
                arrayList.add(Code128Reader.CODE_PATTERNS[i5 % 103]);
                arrayList.add(Code128Reader.CODE_PATTERNS[106]);
                length = 0;
                for (int[] iArr : arrayList) {
                    i4 = length;
                    for (int i52 : (int[]) str.next()) {
                        i4 += i52;
                    }
                    length = i4;
                }
                str = new boolean[length];
                for (int[] appendPattern : arrayList) {
                    i += OneDimensionalCodeWriter.appendPattern(str, i, appendPattern, true);
                }
                return str;
            }
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Contents length should be between 1 and 80 characters, but got ");
        stringBuilder2.append(length);
        throw new IllegalArgumentException(stringBuilder2.toString());
    }

    private static boolean isDigits(CharSequence charSequence, int i, int i2) {
        boolean z;
        i2 += i;
        int length = charSequence.length();
        while (true) {
            z = false;
            if (i < i2 && i < length) {
                char charAt = charSequence.charAt(i);
                if (charAt < '0' || charAt > '9') {
                    if (charAt != ESCAPE_FNC_1) {
                        return false;
                    }
                    i2++;
                }
                i++;
            } else if (i2 <= length) {
                z = true;
            }
        }
        if (i2 <= length) {
            z = true;
        }
        return z;
    }
}
