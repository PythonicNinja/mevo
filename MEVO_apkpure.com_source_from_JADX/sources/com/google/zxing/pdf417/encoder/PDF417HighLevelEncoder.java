package com.google.zxing.pdf417.encoder;

import com.google.zxing.WriterException;
import com.google.zxing.common.CharacterSetECI;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;

final class PDF417HighLevelEncoder {
    private static final int BYTE_COMPACTION = 1;
    private static final Charset DEFAULT_ENCODING = Charset.forName("ISO-8859-1");
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final int LATCH_TO_BYTE = 924;
    private static final int LATCH_TO_BYTE_PADDED = 901;
    private static final int LATCH_TO_NUMERIC = 902;
    private static final int LATCH_TO_TEXT = 900;
    private static final byte[] MIXED = new byte[128];
    private static final int NUMERIC_COMPACTION = 2;
    private static final byte[] PUNCTUATION = new byte[128];
    private static final int SHIFT_TO_BYTE = 913;
    private static final int SUBMODE_ALPHA = 0;
    private static final int SUBMODE_LOWER = 1;
    private static final int SUBMODE_MIXED = 2;
    private static final int SUBMODE_PUNCTUATION = 3;
    private static final int TEXT_COMPACTION = 0;
    private static final byte[] TEXT_MIXED_RAW = new byte[]{(byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 38, (byte) 13, (byte) 9, (byte) 44, (byte) 58, (byte) 35, (byte) 45, (byte) 46, (byte) 36, (byte) 47, (byte) 43, (byte) 37, (byte) 42, (byte) 61, (byte) 94, (byte) 0, (byte) 32, (byte) 0, (byte) 0, (byte) 0};
    private static final byte[] TEXT_PUNCTUATION_RAW = new byte[]{(byte) 59, (byte) 60, (byte) 62, (byte) 64, (byte) 91, (byte) 92, (byte) 93, (byte) 95, (byte) 96, (byte) 126, (byte) 33, (byte) 13, (byte) 9, (byte) 44, (byte) 58, (byte) 10, (byte) 45, (byte) 46, (byte) 36, (byte) 47, (byte) 34, (byte) 124, (byte) 42, (byte) 40, (byte) 41, (byte) 63, (byte) 123, (byte) 125, (byte) 39, (byte) 0};

    private static boolean isAlphaLower(char c) {
        if (c != ' ') {
            if (c < 'a' || c > 'z') {
                return false;
            }
        }
        return true;
    }

    private static boolean isAlphaUpper(char c) {
        if (c != ' ') {
            if (c < 'A' || c > 'Z') {
                return false;
            }
        }
        return true;
    }

    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean isText(char c) {
        if (!(c == '\t' || c == '\n' || c == '\r')) {
            if (c < ' ' || c > '~') {
                return false;
            }
        }
        return true;
    }

    static {
        Arrays.fill(MIXED, (byte) -1);
        for (byte b = (byte) 0; b < TEXT_MIXED_RAW.length; b = (byte) (b + 1)) {
            byte b2 = TEXT_MIXED_RAW[b];
            if (b2 > (byte) 0) {
                MIXED[b2] = b;
            }
        }
        Arrays.fill(PUNCTUATION, (byte) -1);
        for (byte b3 = (byte) 0; b3 < TEXT_PUNCTUATION_RAW.length; b3 = (byte) (b3 + 1)) {
            byte b4 = TEXT_PUNCTUATION_RAW[b3];
            if (b4 > (byte) 0) {
                PUNCTUATION[b4] = b3;
            }
        }
    }

    private PDF417HighLevelEncoder() {
    }

    static String encodeHighLevel(String str, Compaction compaction, Charset charset) throws WriterException {
        StringBuilder stringBuilder = new StringBuilder(str.length());
        if (charset == null) {
            charset = DEFAULT_ENCODING;
        } else if (!DEFAULT_ENCODING.equals(charset)) {
            CharacterSetECI characterSetECIByName = CharacterSetECI.getCharacterSetECIByName(charset.name());
            if (characterSetECIByName != null) {
                encodingECI(characterSetECIByName.getValue(), stringBuilder);
            }
        }
        int length = str.length();
        if (compaction != Compaction.TEXT) {
            if (compaction != Compaction.BYTE) {
                if (compaction != Compaction.NUMERIC) {
                    compaction = null;
                    int i = 0;
                    loop0:
                    while (true) {
                        int i2 = 0;
                        while (compaction < length) {
                            int determineConsecutiveDigitCount = determineConsecutiveDigitCount(str, compaction);
                            if (determineConsecutiveDigitCount >= 13) {
                                stringBuilder.append('Ά');
                                i = 2;
                                encodeNumeric(str, compaction, determineConsecutiveDigitCount, stringBuilder);
                                compaction += determineConsecutiveDigitCount;
                            } else {
                                int determineConsecutiveTextCount = determineConsecutiveTextCount(str, compaction);
                                if (determineConsecutiveTextCount < 5) {
                                    if (determineConsecutiveDigitCount != length) {
                                        determineConsecutiveDigitCount = determineConsecutiveBinaryCount(str, compaction, charset);
                                        if (determineConsecutiveDigitCount == 0) {
                                            determineConsecutiveDigitCount = 1;
                                        }
                                        determineConsecutiveDigitCount += compaction;
                                        compaction = str.substring(compaction, determineConsecutiveDigitCount).getBytes(charset);
                                        if (compaction.length == 1 && i == 0) {
                                            encodeBinary(compaction, 0, 1, 0, stringBuilder);
                                        } else {
                                            encodeBinary(compaction, 0, compaction.length, i, stringBuilder);
                                            i = 1;
                                            i2 = 0;
                                        }
                                        compaction = determineConsecutiveDigitCount;
                                    }
                                }
                                if (i != 0) {
                                    stringBuilder.append('΄');
                                    i = 0;
                                    i2 = 0;
                                }
                                i2 = encodeText(str, compaction, determineConsecutiveTextCount, stringBuilder, i2);
                                compaction += determineConsecutiveTextCount;
                            }
                        }
                        break loop0;
                    }
                }
                stringBuilder.append('Ά');
                encodeNumeric(str, 0, length, stringBuilder);
            } else {
                str = str.getBytes(charset);
                encodeBinary(str, 0, str.length, 1, stringBuilder);
            }
        } else {
            encodeText(str, 0, length, stringBuilder, 0);
        }
        return stringBuilder.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int encodeText(java.lang.CharSequence r17, int r18, int r19, java.lang.StringBuilder r20, int r21) {
        /*
        r0 = r17;
        r1 = r19;
        r2 = r20;
        r3 = new java.lang.StringBuilder;
        r3.<init>(r1);
        r4 = 2;
        r6 = 0;
        r9 = r21;
        r8 = 0;
    L_0x0010:
        r10 = r18 + r8;
        r11 = r0.charAt(r10);
        r12 = 26;
        r13 = 32;
        r14 = 27;
        r15 = 28;
        r5 = 29;
        switch(r9) {
            case 0: goto L_0x00b4;
            case 1: goto L_0x007b;
            case 2: goto L_0x0033;
            default: goto L_0x0023;
        };
    L_0x0023:
        r10 = isPunctuation(r11);
        if (r10 == 0) goto L_0x011d;
    L_0x0029:
        r10 = PUNCTUATION;
        r10 = r10[r11];
        r10 = (char) r10;
        r3.append(r10);
        goto L_0x00ea;
    L_0x0033:
        r12 = isMixed(r11);
        if (r12 == 0) goto L_0x0043;
    L_0x0039:
        r10 = MIXED;
        r10 = r10[r11];
        r10 = (char) r10;
        r3.append(r10);
        goto L_0x00ea;
    L_0x0043:
        r12 = isAlphaUpper(r11);
        if (r12 == 0) goto L_0x004e;
    L_0x0049:
        r3.append(r15);
        goto L_0x0120;
    L_0x004e:
        r12 = isAlphaLower(r11);
        if (r12 == 0) goto L_0x0059;
    L_0x0054:
        r3.append(r14);
        goto L_0x00d0;
    L_0x0059:
        r10 = r10 + 1;
        if (r10 >= r1) goto L_0x006e;
    L_0x005d:
        r10 = r0.charAt(r10);
        r10 = isPunctuation(r10);
        if (r10 == 0) goto L_0x006e;
    L_0x0067:
        r9 = 3;
        r5 = 25;
        r3.append(r5);
        goto L_0x0010;
    L_0x006e:
        r3.append(r5);
        r10 = PUNCTUATION;
        r10 = r10[r11];
        r10 = (char) r10;
        r3.append(r10);
        goto L_0x00ea;
    L_0x007b:
        r10 = isAlphaLower(r11);
        if (r10 == 0) goto L_0x008e;
    L_0x0081:
        if (r11 != r13) goto L_0x0087;
    L_0x0083:
        r3.append(r12);
        goto L_0x00ea;
    L_0x0087:
        r11 = r11 + -97;
        r10 = (char) r11;
        r3.append(r10);
        goto L_0x00ea;
    L_0x008e:
        r10 = isAlphaUpper(r11);
        if (r10 == 0) goto L_0x009e;
    L_0x0094:
        r3.append(r14);
        r11 = r11 + -65;
        r10 = (char) r11;
        r3.append(r10);
        goto L_0x00ea;
    L_0x009e:
        r10 = isMixed(r11);
        if (r10 == 0) goto L_0x00a8;
    L_0x00a4:
        r3.append(r15);
        goto L_0x00dc;
    L_0x00a8:
        r3.append(r5);
        r10 = PUNCTUATION;
        r10 = r10[r11];
        r10 = (char) r10;
        r3.append(r10);
        goto L_0x00ea;
    L_0x00b4:
        r10 = isAlphaUpper(r11);
        if (r10 == 0) goto L_0x00c7;
    L_0x00ba:
        if (r11 != r13) goto L_0x00c0;
    L_0x00bc:
        r3.append(r12);
        goto L_0x00ea;
    L_0x00c0:
        r11 = r11 + -65;
        r10 = (char) r11;
        r3.append(r10);
        goto L_0x00ea;
    L_0x00c7:
        r10 = isAlphaLower(r11);
        if (r10 == 0) goto L_0x00d3;
    L_0x00cd:
        r3.append(r14);
    L_0x00d0:
        r9 = 1;
        goto L_0x0010;
    L_0x00d3:
        r10 = isMixed(r11);
        if (r10 == 0) goto L_0x00df;
    L_0x00d9:
        r3.append(r15);
    L_0x00dc:
        r9 = 2;
        goto L_0x0010;
    L_0x00df:
        r3.append(r5);
        r10 = PUNCTUATION;
        r10 = r10[r11];
        r10 = (char) r10;
        r3.append(r10);
    L_0x00ea:
        r8 = r8 + 1;
        if (r8 < r1) goto L_0x0010;
    L_0x00ee:
        r0 = r3.length();
        r1 = 0;
        r7 = 0;
    L_0x00f4:
        if (r1 >= r0) goto L_0x0112;
    L_0x00f6:
        r8 = r1 % 2;
        if (r8 == 0) goto L_0x00fc;
    L_0x00fa:
        r8 = 1;
        goto L_0x00fd;
    L_0x00fc:
        r8 = 0;
    L_0x00fd:
        if (r8 == 0) goto L_0x010b;
    L_0x00ff:
        r7 = r7 * 30;
        r8 = r3.charAt(r1);
        r7 = r7 + r8;
        r7 = (char) r7;
        r2.append(r7);
        goto L_0x010f;
    L_0x010b:
        r7 = r3.charAt(r1);
    L_0x010f:
        r1 = r1 + 1;
        goto L_0x00f4;
    L_0x0112:
        r0 = r0 % r4;
        if (r0 == 0) goto L_0x011c;
    L_0x0115:
        r7 = r7 * 30;
        r7 = r7 + r5;
        r0 = (char) r7;
        r2.append(r0);
    L_0x011c:
        return r9;
    L_0x011d:
        r3.append(r5);
    L_0x0120:
        r9 = 0;
        goto L_0x0010;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.encoder.PDF417HighLevelEncoder.encodeText(java.lang.CharSequence, int, int, java.lang.StringBuilder, int):int");
    }

    private static void encodeBinary(byte[] bArr, int i, int i2, int i3, StringBuilder stringBuilder) {
        int i4;
        int i5 = i2;
        StringBuilder stringBuilder2 = stringBuilder;
        if (i5 == 1 && i3 == 0) {
            stringBuilder2.append('Α');
        } else {
            if ((i5 % 6 == 0 ? 1 : null) != null) {
                stringBuilder2.append('Μ');
            } else {
                stringBuilder2.append('΅');
            }
        }
        int i6 = 6;
        if (i5 >= 6) {
            int i7 = 5;
            char[] cArr = new char[5];
            i4 = i;
            while ((i + i5) - i4 >= i6) {
                long j = 0;
                int i8 = 0;
                while (i8 < i6) {
                    i8++;
                    j = (j << 8) + ((long) (bArr[i4 + i8] & 255));
                }
                i8 = 0;
                for (i7 = 
/*
Method generation error in method: com.google.zxing.pdf417.encoder.PDF417HighLevelEncoder.encodeBinary(byte[], int, int, int, java.lang.StringBuilder):void, dex: classes.dex
jadx.core.utils.exceptions.CodegenException: Error generate insn: PHI: (r5_1 'i7' int) = (r5_0 'i7' int), (r5_5 'i7' int) binds: {(r5_5 'i7' int)=B:25:0x0064, (r5_0 'i7' int)=B:13:0x0027} in method: com.google.zxing.pdf417.encoder.PDF417HighLevelEncoder.encodeBinary(byte[], int, int, int, java.lang.StringBuilder):void, dex: classes.dex
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:226)
	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:184)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:61)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:219)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:61)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:118)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:57)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:187)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:320)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:257)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:220)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:75)
	at jadx.core.codegen.CodeGen.visit(CodeGen.java:12)
	at jadx.core.ProcessClass.process(ProcessClass.java:40)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
Caused by: jadx.core.utils.exceptions.CodegenException: PHI can be used only in fallback mode
	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:537)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:509)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:220)
	... 26 more

*/

                private static void encodeNumeric(String str, int i, int i2, StringBuilder stringBuilder) {
                    StringBuilder stringBuilder2 = new StringBuilder((i2 / 3) + 1);
                    BigInteger valueOf = BigInteger.valueOf(900);
                    BigInteger valueOf2 = BigInteger.valueOf(0);
                    int i3 = 0;
                    while (i3 < i2) {
                        stringBuilder2.setLength(0);
                        int min = Math.min(44, i2 - i3);
                        StringBuilder stringBuilder3 = new StringBuilder();
                        stringBuilder3.append('1');
                        int i4 = i + i3;
                        stringBuilder3.append(str.substring(i4, i4 + min));
                        BigInteger bigInteger = new BigInteger(stringBuilder3.toString());
                        do {
                            stringBuilder2.append((char) bigInteger.mod(valueOf).intValue());
                            bigInteger = bigInteger.divide(valueOf);
                        } while (!bigInteger.equals(valueOf2));
                        for (int length = stringBuilder2.length() - 1; length >= 0; length--) {
                            stringBuilder.append(stringBuilder2.charAt(length));
                        }
                        i3 += min;
                    }
                }

                private static boolean isMixed(char c) {
                    return MIXED[c] != '￿';
                }

                private static boolean isPunctuation(char c) {
                    return PUNCTUATION[c] != '￿';
                }

                private static int determineConsecutiveDigitCount(CharSequence charSequence, int i) {
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

                private static int determineConsecutiveTextCount(CharSequence charSequence, int i) {
                    int length = charSequence.length();
                    int i2 = i;
                    while (i2 < length) {
                        char charAt = charSequence.charAt(i2);
                        int i3 = 0;
                        while (i3 < 13 && isDigit(r2) && i2 < length) {
                            i3++;
                            i2++;
                            if (i2 < length) {
                                charAt = charSequence.charAt(i2);
                            }
                        }
                        if (i3 >= 13) {
                            return (i2 - i) - i3;
                        }
                        if (i3 <= 0) {
                            if (!isText(charSequence.charAt(i2))) {
                                break;
                            }
                            i2++;
                        }
                    }
                    return i2 - i;
                }

                private static int determineConsecutiveBinaryCount(String str, int i, Charset charset) throws WriterException {
                    charset = charset.newEncoder();
                    int length = str.length();
                    int i2 = i;
                    while (i2 < length) {
                        char charAt = str.charAt(i2);
                        int i3 = 0;
                        while (i3 < 13 && isDigit(r2)) {
                            i3++;
                            int i4 = i2 + i3;
                            if (i4 >= length) {
                                break;
                            }
                            charAt = str.charAt(i4);
                        }
                        if (i3 >= 13) {
                            return i2 - i;
                        }
                        charAt = str.charAt(i2);
                        if (charset.canEncode(charAt)) {
                            i2++;
                        } else {
                            i = new StringBuilder();
                            i.append("Non-encodable character detected: ");
                            i.append(charAt);
                            i.append(" (Unicode: ");
                            i.append(charAt);
                            i.append(')');
                            throw new WriterException(i.toString());
                        }
                    }
                    return i2 - i;
                }

                private static void encodingECI(int i, StringBuilder stringBuilder) throws WriterException {
                    if (i >= 0 && i < LATCH_TO_TEXT) {
                        stringBuilder.append('Ο');
                        stringBuilder.append((char) i);
                    } else if (i < 810900) {
                        stringBuilder.append('Ξ');
                        stringBuilder.append((char) ((i / LATCH_TO_TEXT) - 1));
                        stringBuilder.append((char) (i % LATCH_TO_TEXT));
                    } else if (i < 811800) {
                        stringBuilder.append('Ν');
                        stringBuilder.append((char) (810900 - i));
                    } else {
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("ECI number not in valid range from 0..811799, but was ");
                        stringBuilder2.append(i);
                        throw new WriterException(stringBuilder2.toString());
                    }
                }
            }
