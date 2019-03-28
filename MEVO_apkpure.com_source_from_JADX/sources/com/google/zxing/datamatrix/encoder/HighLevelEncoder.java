package com.google.zxing.datamatrix.encoder;

import com.google.zxing.Dimension;
import java.util.Arrays;

public final class HighLevelEncoder {
    static final int ASCII_ENCODATION = 0;
    static final int BASE256_ENCODATION = 5;
    static final int C40_ENCODATION = 1;
    static final char C40_UNLATCH = 'þ';
    static final int EDIFACT_ENCODATION = 4;
    static final char LATCH_TO_ANSIX12 = 'î';
    static final char LATCH_TO_BASE256 = 'ç';
    static final char LATCH_TO_C40 = 'æ';
    static final char LATCH_TO_EDIFACT = 'ð';
    static final char LATCH_TO_TEXT = 'ï';
    private static final char MACRO_05 = 'ì';
    private static final String MACRO_05_HEADER = "[)>\u001e05\u001d";
    private static final char MACRO_06 = 'í';
    private static final String MACRO_06_HEADER = "[)>\u001e06\u001d";
    private static final String MACRO_TRAILER = "\u001e\u0004";
    private static final char PAD = '';
    static final int TEXT_ENCODATION = 2;
    static final char UPPER_SHIFT = 'ë';
    static final int X12_ENCODATION = 3;
    static final char X12_UNLATCH = 'þ';

    static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    static boolean isExtendedASCII(char c) {
        return c >= '' && c <= 'ÿ';
    }

    private static boolean isNativeC40(char c) {
        if (c != ' ' && (c < '0' || c > '9')) {
            if (c < 'A' || c > 'Z') {
                return false;
            }
        }
        return true;
    }

    private static boolean isNativeEDIFACT(char c) {
        return c >= ' ' && c <= '^';
    }

    private static boolean isNativeText(char c) {
        if (c != ' ' && (c < '0' || c > '9')) {
            if (c < 'a' || c > 'z') {
                return false;
            }
        }
        return true;
    }

    private static boolean isSpecialB256(char c) {
        return false;
    }

    private static boolean isX12TermSep(char c) {
        if (!(c == '\r' || c == '*')) {
            if (c != '>') {
                return false;
            }
        }
        return true;
    }

    private HighLevelEncoder() {
    }

    private static char randomize253State(char c, int i) {
        c += ((i * 149) % 253) + 1;
        if (c > 'þ') {
            c -= 'þ';
        }
        return (char) c;
    }

    public static String encodeHighLevel(String str) {
        return encodeHighLevel(str, SymbolShapeHint.FORCE_NONE, null, null);
    }

    public static String encodeHighLevel(String str, SymbolShapeHint symbolShapeHint, Dimension dimension, Dimension dimension2) {
        r0 = new Encoder[6];
        int i = 0;
        r0[0] = new ASCIIEncoder();
        r0[1] = new C40Encoder();
        r0[2] = new TextEncoder();
        r0[3] = new X12Encoder();
        r0[4] = new EdifactEncoder();
        r0[5] = new Base256Encoder();
        EncoderContext encoderContext = new EncoderContext(str);
        encoderContext.setSymbolShape(symbolShapeHint);
        encoderContext.setSizeConstraints(dimension, dimension2);
        if (str.startsWith(MACRO_05_HEADER) != null && str.endsWith(MACRO_TRAILER) != null) {
            encoderContext.writeCodeword(MACRO_05);
            encoderContext.setSkipAtEnd(2);
            encoderContext.pos += MACRO_05_HEADER.length();
        } else if (!(str.startsWith(MACRO_06_HEADER) == null || str.endsWith(MACRO_TRAILER) == null)) {
            encoderContext.writeCodeword(MACRO_06);
            encoderContext.setSkipAtEnd(2);
            encoderContext.pos += MACRO_06_HEADER.length();
        }
        while (encoderContext.hasMoreCharacters() != null) {
            r0[i].encode(encoderContext);
            if (encoderContext.getNewEncoding() >= null) {
                i = encoderContext.getNewEncoding();
                encoderContext.resetEncoderSignal();
            }
        }
        str = encoderContext.getCodewordCount();
        encoderContext.updateSymbolInfo();
        Dimension dataCapacity = encoderContext.getSymbolInfo().getDataCapacity();
        if (!(str >= dataCapacity || i == 0 || i == 5)) {
            encoderContext.writeCodeword('þ');
        }
        str = encoderContext.getCodewords();
        if (str.length() < dataCapacity) {
            str.append(PAD);
        }
        while (str.length() < dataCapacity) {
            str.append(randomize253State(PAD, str.length() + 1));
        }
        return encoderContext.getCodewords().toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int lookAheadTest(java.lang.CharSequence r18, int r19, int r20) {
        /*
        r0 = r18;
        r1 = r19;
        r3 = r18.length();
        if (r1 < r3) goto L_0x000b;
    L_0x000a:
        return r20;
    L_0x000b:
        r3 = 6;
        if (r20 != 0) goto L_0x0014;
    L_0x000e:
        r2 = new float[r3];
        r2 = {0, 1065353216, 1065353216, 1065353216, 1065353216, 1067450368};
        goto L_0x001d;
    L_0x0014:
        r4 = new float[r3];
        r4 = {1065353216, 1073741824, 1073741824, 1073741824, 1073741824, 1074790400};
        r5 = 0;
        r4[r20] = r5;
        r2 = r4;
    L_0x001d:
        r4 = 0;
        r5 = 0;
    L_0x001f:
        r6 = r1 + r5;
        r7 = r18.length();
        r8 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r9 = 5;
        r10 = 2;
        r11 = 4;
        r12 = 3;
        r13 = 1;
        if (r6 != r7) goto L_0x005d;
    L_0x002f:
        r0 = new byte[r3];
        r1 = new int[r3];
        r2 = findMinimums(r2, r1, r8, r0);
        r3 = getMinimumCount(r0);
        r1 = r1[r4];
        if (r1 != r2) goto L_0x0040;
    L_0x003f:
        return r4;
    L_0x0040:
        if (r3 != r13) goto L_0x0047;
    L_0x0042:
        r1 = r0[r9];
        if (r1 <= 0) goto L_0x0047;
    L_0x0046:
        return r9;
    L_0x0047:
        if (r3 != r13) goto L_0x004e;
    L_0x0049:
        r1 = r0[r11];
        if (r1 <= 0) goto L_0x004e;
    L_0x004d:
        return r11;
    L_0x004e:
        if (r3 != r13) goto L_0x0055;
    L_0x0050:
        r1 = r0[r10];
        if (r1 <= 0) goto L_0x0055;
    L_0x0054:
        return r10;
    L_0x0055:
        if (r3 != r13) goto L_0x005c;
    L_0x0057:
        r0 = r0[r12];
        if (r0 <= 0) goto L_0x005c;
    L_0x005b:
        return r12;
    L_0x005c:
        return r13;
    L_0x005d:
        r6 = r0.charAt(r6);
        r5 = r5 + 1;
        r7 = isDigit(r6);
        r14 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        if (r7 == 0) goto L_0x0076;
    L_0x006b:
        r7 = r2[r4];
        r8 = (double) r7;
        r16 = 4602678819172646912; // 0x3fe0000000000000 float:0.0 double:0.5;
        r8 = r8 + r16;
        r7 = (float) r8;
        r2[r4] = r7;
        goto L_0x009f;
    L_0x0076:
        r7 = isExtendedASCII(r6);
        if (r7 == 0) goto L_0x008f;
    L_0x007c:
        r7 = r2[r4];
        r7 = (double) r7;
        r7 = java.lang.Math.ceil(r7);
        r7 = (int) r7;
        r7 = (float) r7;
        r2[r4] = r7;
        r7 = r2[r4];
        r8 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r7 = r7 + r8;
        r2[r4] = r7;
        goto L_0x009f;
    L_0x008f:
        r7 = r2[r4];
        r7 = (double) r7;
        r7 = java.lang.Math.ceil(r7);
        r7 = (int) r7;
        r7 = (float) r7;
        r2[r4] = r7;
        r7 = r2[r4];
        r7 = r7 + r14;
        r2[r4] = r7;
    L_0x009f:
        r7 = isNativeC40(r6);
        r8 = 1068149419; // 0x3faaaaab float:1.3333334 double:5.277359326E-315;
        r9 = 1076538027; // 0x402aaaab float:2.6666667 double:5.318804556E-315;
        r16 = 1059760811; // 0x3f2aaaab float:0.6666667 double:5.235914095E-315;
        if (r7 == 0) goto L_0x00b5;
    L_0x00ae:
        r7 = r2[r13];
        r7 = r7 + r16;
        r2[r13] = r7;
        goto L_0x00c6;
    L_0x00b5:
        r7 = isExtendedASCII(r6);
        if (r7 == 0) goto L_0x00c1;
    L_0x00bb:
        r7 = r2[r13];
        r7 = r7 + r9;
        r2[r13] = r7;
        goto L_0x00c6;
    L_0x00c1:
        r7 = r2[r13];
        r7 = r7 + r8;
        r2[r13] = r7;
    L_0x00c6:
        r7 = isNativeText(r6);
        if (r7 == 0) goto L_0x00d3;
    L_0x00cc:
        r7 = r2[r10];
        r7 = r7 + r16;
        r2[r10] = r7;
        goto L_0x00e4;
    L_0x00d3:
        r7 = isExtendedASCII(r6);
        if (r7 == 0) goto L_0x00df;
    L_0x00d9:
        r7 = r2[r10];
        r7 = r7 + r9;
        r2[r10] = r7;
        goto L_0x00e4;
    L_0x00df:
        r7 = r2[r10];
        r7 = r7 + r8;
        r2[r10] = r7;
    L_0x00e4:
        r7 = isNativeX12(r6);
        if (r7 == 0) goto L_0x00f1;
    L_0x00ea:
        r7 = r2[r12];
        r7 = r7 + r16;
        r2[r12] = r7;
        goto L_0x0108;
    L_0x00f1:
        r7 = isExtendedASCII(r6);
        if (r7 == 0) goto L_0x0100;
    L_0x00f7:
        r7 = r2[r12];
        r8 = 1082829483; // 0x408aaaab float:4.3333335 double:5.34988848E-315;
        r7 = r7 + r8;
        r2[r12] = r7;
        goto L_0x0108;
    L_0x0100:
        r7 = r2[r12];
        r8 = 1079334229; // 0x40555555 float:3.3333333 double:5.33261963E-315;
        r7 = r7 + r8;
        r2[r12] = r7;
    L_0x0108:
        r7 = isNativeEDIFACT(r6);
        if (r7 == 0) goto L_0x0116;
    L_0x010e:
        r7 = r2[r11];
        r8 = 1061158912; // 0x3f400000 float:0.75 double:5.24282163E-315;
        r7 = r7 + r8;
        r2[r11] = r7;
        goto L_0x012b;
    L_0x0116:
        r7 = isExtendedASCII(r6);
        if (r7 == 0) goto L_0x0124;
    L_0x011c:
        r7 = r2[r11];
        r8 = 1082654720; // 0x40880000 float:4.25 double:5.349025035E-315;
        r7 = r7 + r8;
        r2[r11] = r7;
        goto L_0x012b;
    L_0x0124:
        r7 = r2[r11];
        r8 = 1078984704; // 0x40500000 float:3.25 double:5.330892746E-315;
        r7 = r7 + r8;
        r2[r11] = r7;
    L_0x012b:
        r6 = isSpecialB256(r6);
        if (r6 == 0) goto L_0x013a;
    L_0x0131:
        r6 = 5;
        r7 = r2[r6];
        r8 = 1082130432; // 0x40800000 float:4.0 double:5.34643471E-315;
        r7 = r7 + r8;
        r2[r6] = r7;
        goto L_0x0140;
    L_0x013a:
        r6 = 5;
        r7 = r2[r6];
        r7 = r7 + r14;
        r2[r6] = r7;
    L_0x0140:
        if (r5 < r11) goto L_0x001f;
    L_0x0142:
        r7 = new int[r3];
        r8 = new byte[r3];
        r9 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        findMinimums(r2, r7, r9, r8);
        r9 = getMinimumCount(r8);
        r14 = r7[r4];
        r15 = r7[r6];
        if (r14 >= r15) goto L_0x016f;
    L_0x0156:
        r6 = r7[r4];
        r14 = r7[r13];
        if (r6 >= r14) goto L_0x016f;
    L_0x015c:
        r6 = r7[r4];
        r14 = r7[r10];
        if (r6 >= r14) goto L_0x016f;
    L_0x0162:
        r6 = r7[r4];
        r14 = r7[r12];
        if (r6 >= r14) goto L_0x016f;
    L_0x0168:
        r6 = r7[r4];
        r14 = r7[r11];
        if (r6 >= r14) goto L_0x016f;
    L_0x016e:
        return r4;
    L_0x016f:
        r6 = 5;
        r14 = r7[r6];
        r6 = r7[r4];
        if (r14 < r6) goto L_0x01e1;
    L_0x0176:
        r6 = r8[r13];
        r14 = r8[r10];
        r6 = r6 + r14;
        r14 = r8[r12];
        r6 = r6 + r14;
        r14 = r8[r11];
        r6 = r6 + r14;
        if (r6 != 0) goto L_0x0184;
    L_0x0183:
        goto L_0x01e1;
    L_0x0184:
        if (r9 != r13) goto L_0x018b;
    L_0x0186:
        r6 = r8[r11];
        if (r6 <= 0) goto L_0x018b;
    L_0x018a:
        return r11;
    L_0x018b:
        if (r9 != r13) goto L_0x0192;
    L_0x018d:
        r6 = r8[r10];
        if (r6 <= 0) goto L_0x0192;
    L_0x0191:
        return r10;
    L_0x0192:
        if (r9 != r13) goto L_0x0199;
    L_0x0194:
        r6 = r8[r12];
        if (r6 <= 0) goto L_0x0199;
    L_0x0198:
        return r12;
    L_0x0199:
        r6 = r7[r13];
        r6 = r6 + r13;
        r8 = r7[r4];
        if (r6 >= r8) goto L_0x001f;
    L_0x01a0:
        r6 = r7[r13];
        r6 = r6 + r13;
        r8 = 5;
        r8 = r7[r8];
        if (r6 >= r8) goto L_0x001f;
    L_0x01a8:
        r6 = r7[r13];
        r6 = r6 + r13;
        r8 = r7[r11];
        if (r6 >= r8) goto L_0x001f;
    L_0x01af:
        r6 = r7[r13];
        r6 = r6 + r13;
        r8 = r7[r10];
        if (r6 >= r8) goto L_0x001f;
    L_0x01b6:
        r6 = r7[r13];
        r8 = r7[r12];
        if (r6 >= r8) goto L_0x01bd;
    L_0x01bc:
        return r13;
    L_0x01bd:
        r6 = r7[r13];
        r7 = r7[r12];
        if (r6 != r7) goto L_0x001f;
    L_0x01c3:
        r1 = r1 + r5;
        r1 = r1 + r13;
    L_0x01c5:
        r2 = r18.length();
        if (r1 >= r2) goto L_0x01e0;
    L_0x01cb:
        r2 = r0.charAt(r1);
        r3 = isX12TermSep(r2);
        if (r3 == 0) goto L_0x01d6;
    L_0x01d5:
        return r12;
    L_0x01d6:
        r2 = isNativeX12(r2);
        if (r2 != 0) goto L_0x01dd;
    L_0x01dc:
        goto L_0x01e0;
    L_0x01dd:
        r1 = r1 + 1;
        goto L_0x01c5;
    L_0x01e0:
        return r13;
    L_0x01e1:
        r0 = 5;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.encoder.HighLevelEncoder.lookAheadTest(java.lang.CharSequence, int, int):int");
    }

    private static int findMinimums(float[] fArr, int[] iArr, int i, byte[] bArr) {
        Arrays.fill(bArr, (byte) 0);
        int i2 = i;
        for (i = 0; i < 6; i++) {
            iArr[i] = (int) Math.ceil((double) fArr[i]);
            int i3 = iArr[i];
            if (i2 > i3) {
                Arrays.fill(bArr, (byte) 0);
                i2 = i3;
            }
            if (i2 == i3) {
                bArr[i] = (byte) (bArr[i] + 1);
            }
        }
        return i2;
    }

    private static int getMinimumCount(byte[] bArr) {
        int i = 0;
        for (int i2 = 0; i2 < 6; i2++) {
            i += bArr[i2];
        }
        return i;
    }

    private static boolean isNativeX12(char c) {
        if (!(isX12TermSep(c) || c == ' ' || (c >= '0' && c <= '9'))) {
            if (c < 'A' || c > 'Z') {
                return false;
            }
        }
        return true;
    }

    public static int determineConsecutiveDigitCount(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        if (i < length) {
            char charAt = charSequence.charAt(i);
            while (isDigit(charAt) && i < length) {
                i2++;
                i++;
                if (i < length) {
                    charAt = charSequence.charAt(i);
                }
            }
        }
        return i2;
    }

    static void illegalCharacter(char c) {
        String toHexString = Integer.toHexString(c);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0000".substring(0, 4 - toHexString.length()));
        stringBuilder.append(toHexString);
        toHexString = stringBuilder.toString();
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Illegal character: ");
        stringBuilder2.append(c);
        stringBuilder2.append(" (0x");
        stringBuilder2.append(toHexString);
        stringBuilder2.append(')');
        throw new IllegalArgumentException(stringBuilder2.toString());
    }
}
