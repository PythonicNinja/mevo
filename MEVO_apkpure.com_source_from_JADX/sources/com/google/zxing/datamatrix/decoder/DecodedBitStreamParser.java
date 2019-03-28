package com.google.zxing.datamatrix.decoder;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.DecoderResult;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

final class DecodedBitStreamParser {
    private static final char[] C40_BASIC_SET_CHARS = new char[]{'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final char[] C40_SHIFT2_SET_CHARS = new char[]{'!', '\"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_'};
    private static final char[] TEXT_BASIC_SET_CHARS = new char[]{'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final char[] TEXT_SHIFT2_SET_CHARS = C40_SHIFT2_SET_CHARS;
    private static final char[] TEXT_SHIFT3_SET_CHARS = new char[]{'`', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '{', '|', '}', '~', ''};

    private enum Mode {
        PAD_ENCODE,
        ASCII_ENCODE,
        C40_ENCODE,
        TEXT_ENCODE,
        ANSIX12_ENCODE,
        EDIFACT_ENCODE,
        BASE256_ENCODE
    }

    private DecodedBitStreamParser() {
    }

    static DecoderResult decode(byte[] bArr) throws FormatException {
        String stringBuilder;
        BitSource bitSource = new BitSource(bArr);
        StringBuilder stringBuilder2 = new StringBuilder(100);
        CharSequence stringBuilder3 = new StringBuilder(0);
        List arrayList = new ArrayList(1);
        Mode mode = Mode.ASCII_ENCODE;
        do {
            if (mode == Mode.ASCII_ENCODE) {
                mode = decodeAsciiSegment(bitSource, stringBuilder2, stringBuilder3);
            } else {
                switch (mode) {
                    case C40_ENCODE:
                        decodeC40Segment(bitSource, stringBuilder2);
                        break;
                    case TEXT_ENCODE:
                        decodeTextSegment(bitSource, stringBuilder2);
                        break;
                    case ANSIX12_ENCODE:
                        decodeAnsiX12Segment(bitSource, stringBuilder2);
                        break;
                    case EDIFACT_ENCODE:
                        decodeEdifactSegment(bitSource, stringBuilder2);
                        break;
                    case BASE256_ENCODE:
                        decodeBase256Segment(bitSource, stringBuilder2, arrayList);
                        break;
                    default:
                        throw FormatException.getFormatInstance();
                }
                mode = Mode.ASCII_ENCODE;
            }
            if (mode != Mode.PAD_ENCODE) {
            }
            if (stringBuilder3.length() > 0) {
                stringBuilder2.append(stringBuilder3);
            }
            stringBuilder = stringBuilder2.toString();
            if (arrayList.isEmpty()) {
                arrayList = null;
            }
            return new DecoderResult(bArr, stringBuilder, arrayList, null);
        } while (bitSource.available() > 0);
        if (stringBuilder3.length() > 0) {
            stringBuilder2.append(stringBuilder3);
        }
        stringBuilder = stringBuilder2.toString();
        if (arrayList.isEmpty()) {
            arrayList = null;
        }
        return new DecoderResult(bArr, stringBuilder, arrayList, null);
    }

    private static Mode decodeAsciiSegment(BitSource bitSource, StringBuilder stringBuilder, StringBuilder stringBuilder2) throws FormatException {
        Object obj = null;
        do {
            int readBits = bitSource.readBits(8);
            if (readBits == 0) {
                throw FormatException.getFormatInstance();
            } else if (readBits <= 128) {
                if (obj != null) {
                    readBits += 128;
                }
                stringBuilder.append((char) (readBits - 1));
                return Mode.ASCII_ENCODE;
            } else if (readBits == GmsClientSupervisor.DEFAULT_BIND_FLAGS) {
                return Mode.PAD_ENCODE;
            } else {
                if (readBits <= 229) {
                    readBits -= 130;
                    if (readBits < 10) {
                        stringBuilder.append('0');
                    }
                    stringBuilder.append(readBits);
                } else if (readBits == 230) {
                    return Mode.C40_ENCODE;
                } else {
                    if (readBits == 231) {
                        return Mode.BASE256_ENCODE;
                    }
                    if (readBits == 232) {
                        stringBuilder.append('\u001d');
                    } else if (readBits != 233) {
                        if (readBits != 234) {
                            if (readBits == 235) {
                                obj = 1;
                            } else if (readBits == 236) {
                                stringBuilder.append("[)>\u001e05\u001d");
                                stringBuilder2.insert(0, "\u001e\u0004");
                            } else if (readBits == 237) {
                                stringBuilder.append("[)>\u001e06\u001d");
                                stringBuilder2.insert(0, "\u001e\u0004");
                            } else if (readBits == 238) {
                                return Mode.ANSIX12_ENCODE;
                            } else {
                                if (readBits == 239) {
                                    return Mode.TEXT_ENCODE;
                                }
                                if (readBits == 240) {
                                    return Mode.EDIFACT_ENCODE;
                                }
                                if (readBits != 241) {
                                    if (readBits >= 242 && !(readBits == 254 && bitSource.available() == 0)) {
                                        throw FormatException.getFormatInstance();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } while (bitSource.available() > 0);
        return Mode.ASCII_ENCODE;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void decodeC40Segment(com.google.zxing.common.BitSource r8, java.lang.StringBuilder r9) throws com.google.zxing.FormatException {
        /*
        r0 = 3;
        r1 = new int[r0];
        r2 = 0;
        r3 = 0;
        r4 = 0;
    L_0x0006:
        r5 = r8.available();
        r6 = 8;
        if (r5 != r6) goto L_0x000f;
    L_0x000e:
        return;
    L_0x000f:
        r5 = r8.readBits(r6);
        r7 = 254; // 0xfe float:3.56E-43 double:1.255E-321;
        if (r5 != r7) goto L_0x0018;
    L_0x0017:
        return;
    L_0x0018:
        r6 = r8.readBits(r6);
        parseTwoBytes(r5, r6, r1);
        r5 = r3;
        r3 = 0;
    L_0x0021:
        if (r3 >= r0) goto L_0x009c;
    L_0x0023:
        r6 = r1[r3];
        switch(r4) {
            case 0: goto L_0x0079;
            case 1: goto L_0x0069;
            case 2: goto L_0x003d;
            case 3: goto L_0x002d;
            default: goto L_0x0028;
        };
    L_0x0028:
        r8 = com.google.zxing.FormatException.getFormatInstance();
        throw r8;
    L_0x002d:
        if (r5 == 0) goto L_0x0036;
    L_0x002f:
        r6 = r6 + 224;
        r4 = (char) r6;
        r9.append(r4);
        goto L_0x0071;
    L_0x0036:
        r6 = r6 + 96;
        r4 = (char) r6;
        r9.append(r4);
        goto L_0x0077;
    L_0x003d:
        r4 = C40_SHIFT2_SET_CHARS;
        r4 = r4.length;
        if (r6 >= r4) goto L_0x0053;
    L_0x0042:
        r4 = C40_SHIFT2_SET_CHARS;
        r4 = r4[r6];
        if (r5 == 0) goto L_0x004f;
    L_0x0048:
        r4 = r4 + 128;
        r4 = (char) r4;
        r9.append(r4);
        goto L_0x0071;
    L_0x004f:
        r9.append(r4);
        goto L_0x0077;
    L_0x0053:
        r4 = 27;
        if (r6 != r4) goto L_0x005d;
    L_0x0057:
        r4 = 29;
        r9.append(r4);
        goto L_0x0077;
    L_0x005d:
        r4 = 30;
        if (r6 != r4) goto L_0x0064;
    L_0x0061:
        r4 = 1;
        r5 = 1;
        goto L_0x0077;
    L_0x0064:
        r8 = com.google.zxing.FormatException.getFormatInstance();
        throw r8;
    L_0x0069:
        if (r5 == 0) goto L_0x0073;
    L_0x006b:
        r6 = r6 + 128;
        r4 = (char) r6;
        r9.append(r4);
    L_0x0071:
        r5 = 0;
        goto L_0x0077;
    L_0x0073:
        r4 = (char) r6;
        r9.append(r4);
    L_0x0077:
        r4 = 0;
        goto L_0x0094;
    L_0x0079:
        if (r6 >= r0) goto L_0x007e;
    L_0x007b:
        r4 = r6 + 1;
        goto L_0x0094;
    L_0x007e:
        r7 = C40_BASIC_SET_CHARS;
        r7 = r7.length;
        if (r6 >= r7) goto L_0x0097;
    L_0x0083:
        r7 = C40_BASIC_SET_CHARS;
        r6 = r7[r6];
        if (r5 == 0) goto L_0x0091;
    L_0x0089:
        r6 = r6 + 128;
        r5 = (char) r6;
        r9.append(r5);
        r5 = 0;
        goto L_0x0094;
    L_0x0091:
        r9.append(r6);
    L_0x0094:
        r3 = r3 + 1;
        goto L_0x0021;
    L_0x0097:
        r8 = com.google.zxing.FormatException.getFormatInstance();
        throw r8;
    L_0x009c:
        r3 = r8.available();
        if (r3 > 0) goto L_0x00a3;
    L_0x00a2:
        return;
    L_0x00a3:
        r3 = r5;
        goto L_0x0006;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.decodeC40Segment(com.google.zxing.common.BitSource, java.lang.StringBuilder):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void decodeTextSegment(com.google.zxing.common.BitSource r8, java.lang.StringBuilder r9) throws com.google.zxing.FormatException {
        /*
        r0 = 3;
        r1 = new int[r0];
        r2 = 0;
        r3 = 0;
        r4 = 0;
    L_0x0006:
        r5 = r8.available();
        r6 = 8;
        if (r5 != r6) goto L_0x000f;
    L_0x000e:
        return;
    L_0x000f:
        r5 = r8.readBits(r6);
        r7 = 254; // 0xfe float:3.56E-43 double:1.255E-321;
        if (r5 != r7) goto L_0x0018;
    L_0x0017:
        return;
    L_0x0018:
        r6 = r8.readBits(r6);
        parseTwoBytes(r5, r6, r1);
        r5 = r3;
        r3 = 0;
    L_0x0021:
        if (r3 >= r0) goto L_0x00a8;
    L_0x0023:
        r6 = r1[r3];
        switch(r4) {
            case 0: goto L_0x0084;
            case 1: goto L_0x0074;
            case 2: goto L_0x0048;
            case 3: goto L_0x002d;
            default: goto L_0x0028;
        };
    L_0x0028:
        r8 = com.google.zxing.FormatException.getFormatInstance();
        throw r8;
    L_0x002d:
        r4 = TEXT_SHIFT3_SET_CHARS;
        r4 = r4.length;
        if (r6 >= r4) goto L_0x0043;
    L_0x0032:
        r4 = TEXT_SHIFT3_SET_CHARS;
        r4 = r4[r6];
        if (r5 == 0) goto L_0x003f;
    L_0x0038:
        r4 = r4 + 128;
        r4 = (char) r4;
        r9.append(r4);
        goto L_0x007c;
    L_0x003f:
        r9.append(r4);
        goto L_0x0082;
    L_0x0043:
        r8 = com.google.zxing.FormatException.getFormatInstance();
        throw r8;
    L_0x0048:
        r4 = TEXT_SHIFT2_SET_CHARS;
        r4 = r4.length;
        if (r6 >= r4) goto L_0x005e;
    L_0x004d:
        r4 = TEXT_SHIFT2_SET_CHARS;
        r4 = r4[r6];
        if (r5 == 0) goto L_0x005a;
    L_0x0053:
        r4 = r4 + 128;
        r4 = (char) r4;
        r9.append(r4);
        goto L_0x007c;
    L_0x005a:
        r9.append(r4);
        goto L_0x0082;
    L_0x005e:
        r4 = 27;
        if (r6 != r4) goto L_0x0068;
    L_0x0062:
        r4 = 29;
        r9.append(r4);
        goto L_0x0082;
    L_0x0068:
        r4 = 30;
        if (r6 != r4) goto L_0x006f;
    L_0x006c:
        r4 = 1;
        r5 = 1;
        goto L_0x0082;
    L_0x006f:
        r8 = com.google.zxing.FormatException.getFormatInstance();
        throw r8;
    L_0x0074:
        if (r5 == 0) goto L_0x007e;
    L_0x0076:
        r6 = r6 + 128;
        r4 = (char) r6;
        r9.append(r4);
    L_0x007c:
        r5 = 0;
        goto L_0x0082;
    L_0x007e:
        r4 = (char) r6;
        r9.append(r4);
    L_0x0082:
        r4 = 0;
        goto L_0x009f;
    L_0x0084:
        if (r6 >= r0) goto L_0x0089;
    L_0x0086:
        r4 = r6 + 1;
        goto L_0x009f;
    L_0x0089:
        r7 = TEXT_BASIC_SET_CHARS;
        r7 = r7.length;
        if (r6 >= r7) goto L_0x00a3;
    L_0x008e:
        r7 = TEXT_BASIC_SET_CHARS;
        r6 = r7[r6];
        if (r5 == 0) goto L_0x009c;
    L_0x0094:
        r6 = r6 + 128;
        r5 = (char) r6;
        r9.append(r5);
        r5 = 0;
        goto L_0x009f;
    L_0x009c:
        r9.append(r6);
    L_0x009f:
        r3 = r3 + 1;
        goto L_0x0021;
    L_0x00a3:
        r8 = com.google.zxing.FormatException.getFormatInstance();
        throw r8;
    L_0x00a8:
        r3 = r8.available();
        if (r3 > 0) goto L_0x00af;
    L_0x00ae:
        return;
    L_0x00af:
        r3 = r5;
        goto L_0x0006;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.decodeTextSegment(com.google.zxing.common.BitSource, java.lang.StringBuilder):void");
    }

    private static void decodeAnsiX12Segment(BitSource bitSource, StringBuilder stringBuilder) throws FormatException {
        int[] iArr = new int[3];
        while (bitSource.available() != 8) {
            int readBits = bitSource.readBits(8);
            if (readBits != 254) {
                parseTwoBytes(readBits, bitSource.readBits(8), iArr);
                for (readBits = 0; readBits < 3; readBits++) {
                    int i = iArr[readBits];
                    if (i == 0) {
                        stringBuilder.append('\r');
                    } else if (i == 1) {
                        stringBuilder.append('*');
                    } else if (i == 2) {
                        stringBuilder.append('>');
                    } else if (i == 3) {
                        stringBuilder.append(' ');
                    } else if (i < 14) {
                        stringBuilder.append((char) (i + 44));
                    } else if (i < 40) {
                        stringBuilder.append((char) (i + 51));
                    } else {
                        throw FormatException.getFormatInstance();
                    }
                }
                if (bitSource.available() <= 0) {
                    return;
                }
            }
            return;
        }
    }

    private static void parseTwoBytes(int i, int i2, int[] iArr) {
        i = ((i << 8) + i2) - 1;
        int i3 = i / 1600;
        iArr[0] = i3;
        i -= i3 * 1600;
        i3 = i / 40;
        iArr[1] = i3;
        iArr[2] = i - (i3 * 40);
    }

    private static void decodeEdifactSegment(BitSource bitSource, StringBuilder stringBuilder) {
        while (bitSource.available() > 16) {
            for (int i = 0; i < 4; i++) {
                int readBits = bitSource.readBits(6);
                if (readBits == 31) {
                    stringBuilder = 8 - bitSource.getBitOffset();
                    if (stringBuilder != 8) {
                        bitSource.readBits(stringBuilder);
                    }
                    return;
                }
                if ((readBits & 32) == 0) {
                    readBits |= 64;
                }
                stringBuilder.append((char) readBits);
            }
            if (bitSource.available() <= 0) {
                return;
            }
        }
    }

    private static void decodeBase256Segment(BitSource bitSource, StringBuilder stringBuilder, Collection<byte[]> collection) throws FormatException {
        int byteOffset = bitSource.getByteOffset() + 1;
        int i = byteOffset + 1;
        byteOffset = unrandomize255State(bitSource.readBits(8), byteOffset);
        if (byteOffset == 0) {
            byteOffset = bitSource.available() / 8;
        } else if (byteOffset >= Callback.DEFAULT_SWIPE_ANIMATION_DURATION) {
            byteOffset = ((byteOffset - 249) * Callback.DEFAULT_SWIPE_ANIMATION_DURATION) + unrandomize255State(bitSource.readBits(8), i);
            i++;
        }
        if (byteOffset < 0) {
            throw FormatException.getFormatInstance();
        }
        Object obj = new byte[byteOffset];
        int i2 = 0;
        while (i2 < byteOffset) {
            if (bitSource.available() < 8) {
                throw FormatException.getFormatInstance();
            }
            int i3 = i + 1;
            obj[i2] = (byte) unrandomize255State(bitSource.readBits(8), i);
            i2++;
            i = i3;
        }
        collection.add(obj);
        try {
            stringBuilder.append(new String(obj, "ISO8859_1"));
        } catch (BitSource bitSource2) {
            collection = new StringBuilder();
            collection.append("Platform does not support required encoding: ");
            collection.append(bitSource2);
            throw new IllegalStateException(collection.toString());
        }
    }

    private static int unrandomize255State(int i, int i2) {
        i -= ((i2 * 149) % 255) + 1;
        return i >= 0 ? i : i + 256;
    }
}
