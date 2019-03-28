package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;

public final class Encoder {
    public static final int DEFAULT_AZTEC_LAYERS = 0;
    public static final int DEFAULT_EC_PERCENT = 33;
    private static final int MAX_NB_BITS = 32;
    private static final int MAX_NB_BITS_COMPACT = 4;
    private static final int[] WORD_SIZE = new int[]{4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};

    private static int totalBitsInLayer(int i, boolean z) {
        return ((z ? 88 : 112) + (i * 16)) * i;
    }

    private Encoder() {
    }

    public static AztecCode encode(byte[] bArr) {
        return encode(bArr, 33, 0);
    }

    public static AztecCode encode(byte[] bArr, int i, int i2) {
        boolean z;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        BitArray encode = new HighLevelEncoder(bArr).encode();
        int size = ((encode.getSize() * i) / 100) + 11;
        int size2 = encode.getSize() + size;
        int i9 = 32;
        int i10 = 0;
        int abs;
        int totalBitsInLayer;
        if (i2 != 0) {
            z = i2 < 0;
            abs = Math.abs(i2);
            if (z) {
                i9 = 4;
            }
            if (abs > i9) {
                throw new IllegalArgumentException(String.format("Illegal value %s for layers", new Object[]{Integer.valueOf(i2)}));
            }
            totalBitsInLayer = totalBitsInLayer(abs, z);
            i9 = WORD_SIZE[abs];
            i3 = totalBitsInLayer - (totalBitsInLayer % i9);
            encode = stuffBits(encode, i9);
            if (encode.getSize() + size > i3) {
                throw new IllegalArgumentException("Data to large for user specified layer");
            } else if (!z || encode.getSize() <= i9 * 64) {
                i4 = totalBitsInLayer;
                i5 = abs;
            } else {
                throw new IllegalArgumentException("Data to large for user specified layer");
            }
        }
        BitArray bitArray = null;
        totalBitsInLayer = 0;
        abs = 0;
        while (totalBitsInLayer <= 32) {
            boolean z2 = totalBitsInLayer <= 3;
            i5 = z2 ? totalBitsInLayer + 1 : totalBitsInLayer;
            i4 = totalBitsInLayer(i5, z2);
            if (size2 <= i4) {
                if (abs != WORD_SIZE[i5]) {
                    abs = WORD_SIZE[i5];
                    bitArray = stuffBits(encode, abs);
                }
                i6 = i4 - (i4 % abs);
                if (!z2 || bitArray.getSize() <= abs * 64) {
                    if (bitArray.getSize() + size <= i6) {
                        i9 = abs;
                        encode = bitArray;
                        z = z2;
                    }
                }
            }
            totalBitsInLayer++;
            i10 = 0;
        }
        throw new IllegalArgumentException("Data too large for an Aztec code");
        BitArray generateCheckWords = generateCheckWords(encode, i4, i9);
        int size3 = encode.getSize() / i9;
        BitArray generateModeMessage = generateModeMessage(z, i5, size3);
        i9 = z ? (i5 * 4) + 11 : (i5 * 4) + 14;
        int[] iArr = new int[i9];
        i3 = 2;
        if (z) {
            for (i7 = 0; i7 < iArr.length; i7++) {
                iArr[i7] = i7;
            }
            i7 = i9;
        } else {
            i4 = i9 / 2;
            i7 = (i9 + 1) + (((i4 - 1) / 15) * 2);
            i6 = i7 / 2;
            for (i8 = 0; i8 < i4; i8++) {
                int i11 = (i8 / 15) + i8;
                iArr[(i4 - i8) - 1] = (i6 - i11) - 1;
                iArr[i4 + i8] = (i11 + i6) + 1;
            }
        }
        BitMatrix bitMatrix = new BitMatrix(i7);
        i6 = 0;
        i8 = 0;
        while (i6 < i5) {
            i11 = z ? ((i5 - i6) * 4) + 9 : ((i5 - i6) * 4) + 12;
            int i12 = 0;
            while (i12 < i11) {
                int i13 = i12 * 2;
                for (i3 = 
/*
Method generation error in method: com.google.zxing.aztec.encoder.Encoder.encode(byte[], int, int):com.google.zxing.aztec.encoder.AztecCode, dex: classes.dex
jadx.core.utils.exceptions.CodegenException: Error generate insn: PHI: (r9_10 'i3' int) = (r9_9 'i3' int), (r9_22 'i3' int) binds: {(r9_22 'i3' int)=B:82:0x019b, (r9_9 'i3' int)=B:65:0x0127} in method: com.google.zxing.aztec.encoder.Encoder.encode(byte[], int, int):com.google.zxing.aztec.encoder.AztecCode, dex: classes.dex
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
	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:219)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:61)
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

                private static void drawBullsEye(BitMatrix bitMatrix, int i, int i2) {
                    int i3;
                    int i4;
                    for (i3 = 0; i3 < i2; i3 += 2) {
                        i4 = i - i3;
                        int i5 = i4;
                        while (true) {
                            int i6 = i + i3;
                            if (i5 > i6) {
                                break;
                            }
                            bitMatrix.set(i5, i4);
                            bitMatrix.set(i5, i6);
                            bitMatrix.set(i4, i5);
                            bitMatrix.set(i6, i5);
                            i5++;
                        }
                    }
                    i3 = i - i2;
                    bitMatrix.set(i3, i3);
                    i4 = i3 + 1;
                    bitMatrix.set(i4, i3);
                    bitMatrix.set(i3, i4);
                    i += i2;
                    bitMatrix.set(i, i3);
                    bitMatrix.set(i, i4);
                    bitMatrix.set(i, i - 1);
                }

                static BitArray generateModeMessage(boolean z, int i, int i2) {
                    BitArray bitArray = new BitArray();
                    if (z) {
                        bitArray.appendBits(i - 1, true);
                        bitArray.appendBits(i2 - 1, true);
                        return generateCheckWords(bitArray, true, 4);
                    }
                    bitArray.appendBits(i - 1, true);
                    bitArray.appendBits(i2 - 1, true);
                    return generateCheckWords(bitArray, true, 4);
                }

                private static void drawModeMessage(BitMatrix bitMatrix, boolean z, int i, BitArray bitArray) {
                    i /= 2;
                    boolean z2 = false;
                    if (z) {
                        while (z2 < true) {
                            z = (i - 3) + z2;
                            if (bitArray.get(z2)) {
                                bitMatrix.set(z, i - 5);
                            }
                            if (bitArray.get(z2 + 7)) {
                                bitMatrix.set(i + 5, z);
                            }
                            if (bitArray.get(20 - z2)) {
                                bitMatrix.set(z, i + 5);
                            }
                            if (bitArray.get(27 - z2)) {
                                bitMatrix.set(i - 5, z);
                            }
                            z2++;
                        }
                        return;
                    }
                    while (z2 < true) {
                        z = ((i - 5) + z2) + (z2 / 5);
                        if (bitArray.get(z2)) {
                            bitMatrix.set(z, i - 7);
                        }
                        if (bitArray.get(z2 + 10)) {
                            bitMatrix.set(i + 7, z);
                        }
                        if (bitArray.get(29 - z2)) {
                            bitMatrix.set(z, i + 7);
                        }
                        if (bitArray.get(39 - z2)) {
                            bitMatrix.set(i - 7, z);
                        }
                        z2++;
                    }
                }

                private static BitArray generateCheckWords(BitArray bitArray, int i, int i2) {
                    int size = bitArray.getSize() / i2;
                    ReedSolomonEncoder reedSolomonEncoder = new ReedSolomonEncoder(getGF(i2));
                    int i3 = i / i2;
                    bitArray = bitsToWords(bitArray, i2, i3);
                    reedSolomonEncoder.encode(bitArray, i3 - size);
                    i %= i2;
                    BitArray bitArray2 = new BitArray();
                    int i4 = 0;
                    bitArray2.appendBits(0, i);
                    i = bitArray.length;
                    while (i4 < i) {
                        bitArray2.appendBits(bitArray[i4], i2);
                        i4++;
                    }
                    return bitArray2;
                }

                private static int[] bitsToWords(BitArray bitArray, int i, int i2) {
                    i2 = new int[i2];
                    int size = bitArray.getSize() / i;
                    for (int i3 = 0; i3 < size; i3++) {
                        int i4 = 0;
                        for (int i5 = 0; i5 < i; i5++) {
                            i4 |= bitArray.get((i3 * i) + i5) ? 1 << ((i - i5) - 1) : 0;
                        }
                        i2[i3] = i4;
                    }
                    return i2;
                }

                private static GenericGF getGF(int i) {
                    if (i == 4) {
                        return GenericGF.AZTEC_PARAM;
                    }
                    if (i == 6) {
                        return GenericGF.AZTEC_DATA_6;
                    }
                    if (i == 8) {
                        return GenericGF.AZTEC_DATA_8;
                    }
                    if (i == 10) {
                        return GenericGF.AZTEC_DATA_10;
                    }
                    if (i == 12) {
                        return GenericGF.AZTEC_DATA_12;
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Unsupported word size ");
                    stringBuilder.append(i);
                    throw new IllegalArgumentException(stringBuilder.toString());
                }

                static BitArray stuffBits(BitArray bitArray, int i) {
                    BitArray bitArray2 = new BitArray();
                    int size = bitArray.getSize();
                    int i2 = (1 << i) - 2;
                    int i3 = 0;
                    while (i3 < size) {
                        int i4;
                        int i5 = 0;
                        for (i4 = 0; i4 < i; i4++) {
                            int i6 = i3 + i4;
                            if (i6 >= size || bitArray.get(i6)) {
                                i5 |= 1 << ((i - 1) - i4);
                            }
                        }
                        i4 = i5 & i2;
                        if (i4 == i2) {
                            bitArray2.appendBits(i4, i);
                            i3--;
                        } else if (i4 == 0) {
                            bitArray2.appendBits(i5 | 1, i);
                            i3--;
                        } else {
                            bitArray2.appendBits(i5, i);
                        }
                        i3 += i;
                    }
                    return bitArray2;
                }
            }
