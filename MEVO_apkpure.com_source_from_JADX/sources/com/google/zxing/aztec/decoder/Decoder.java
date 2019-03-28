package com.google.zxing.aztec.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.mevo.app.constants.BikeTypes;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.Arrays;

public final class Decoder {
    private static final String[] DIGIT_TABLE = new String[]{"CTRL_PS", " ", BikeTypes.DEFAULT, "1", BikeTypes.TOURING_BIKE_28, BikeTypes.USE_BIKE, BikeTypes.COMFORT, BikeTypes.CLASSIC, BikeTypes.PEDELEC, BikeTypes.KIDS_BIKE_20, BikeTypes.KIDS_BIKE_24, BikeTypes.TANDEM, ",", ".", "CTRL_UL", "CTRL_US"};
    private static final String[] LOWER_TABLE = new String[]{"CTRL_PS", " ", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "CTRL_US", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] MIXED_TABLE = new String[]{"CTRL_PS", " ", "\u0001", "\u0002", "\u0003", "\u0004", "\u0005", "\u0006", "\u0007", "\b", "\t", "\n", "\u000b", "\f", "\r", "\u001b", "\u001c", "\u001d", "\u001e", "\u001f", "@", "\\", "^", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR, "`", "|", "~", "", "CTRL_LL", "CTRL_UL", "CTRL_PL", "CTRL_BS"};
    private static final String[] PUNCT_TABLE = new String[]{"", "\r", "\r\n", ". ", ", ", ": ", "!", "\"", "#", "$", Operation.MOD, "&", "'", "(", ")", Operation.MULTIPLY, Operation.PLUS, ",", Operation.MINUS, ".", Operation.DIVISION, ":", ";", Operation.LESS_THAN, Operation.EQUALS, Operation.GREATER_THAN, Operation.EMPTY_PARAM, "[", "]", "{", "}", "CTRL_UL"};
    private static final String[] UPPER_TABLE = new String[]{"CTRL_PS", " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "CTRL_LL", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private AztecDetectorResult ddata;

    private enum Table {
        UPPER,
        LOWER,
        MIXED,
        DIGIT,
        PUNCT,
        BINARY
    }

    private static int totalBitsInLayer(int i, boolean z) {
        return ((z ? 88 : 112) + (i * 16)) * i;
    }

    public DecoderResult decode(AztecDetectorResult aztecDetectorResult) throws FormatException {
        this.ddata = aztecDetectorResult;
        return new DecoderResult(null, getEncodedData(correctBits(extractBits(aztecDetectorResult.getBits()))), null, null);
    }

    public static String highLevelDecode(boolean[] zArr) {
        return getEncodedData(zArr);
    }

    private static String getEncodedData(boolean[] zArr) {
        int length = zArr.length;
        Table table = Table.UPPER;
        Table table2 = Table.UPPER;
        StringBuilder stringBuilder = new StringBuilder(20);
        Table table3 = table;
        int i = 0;
        while (i < length) {
            int i2;
            if (table2 != Table.BINARY) {
                i2 = table2 == Table.DIGIT ? 4 : 5;
                if (length - i < i2) {
                    break;
                }
                int readCode = readCode(zArr, i, i2);
                i += i2;
                String character = getCharacter(table2, readCode);
                if (character.startsWith("CTRL_")) {
                    Table table4 = getTable(character.charAt(5));
                    if (character.charAt(6) == 'L') {
                        table2 = table4;
                        table3 = table2;
                    } else {
                        table2 = table4;
                    }
                } else {
                    stringBuilder.append(character);
                }
            } else if (length - i < 5) {
                break;
            } else {
                int readCode2 = readCode(zArr, i, 5);
                i += 5;
                if (readCode2 == 0) {
                    if (length - i < 11) {
                        break;
                    }
                    readCode2 = readCode(zArr, i, 11) + 31;
                    i += 11;
                }
                i2 = i;
                for (i = 0; i < readCode2; i++) {
                    if (length - i2 < 8) {
                        i = length;
                        break;
                    }
                    stringBuilder.append((char) readCode(zArr, i2, 8));
                    i2 += 8;
                }
                i = i2;
            }
            table2 = table3;
        }
        return stringBuilder.toString();
    }

    private static Table getTable(char c) {
        if (c == 'B') {
            return Table.BINARY;
        }
        if (c == 'D') {
            return Table.DIGIT;
        }
        if (c == 'P') {
            return Table.PUNCT;
        }
        switch (c) {
            case 'L':
                return Table.LOWER;
            case 'M':
                return Table.MIXED;
            default:
                return Table.UPPER;
        }
    }

    private static String getCharacter(Table table, int i) {
        switch (table) {
            case UPPER:
                return UPPER_TABLE[i];
            case LOWER:
                return LOWER_TABLE[i];
            case MIXED:
                return MIXED_TABLE[i];
            case PUNCT:
                return PUNCT_TABLE[i];
            case DIGIT:
                return DIGIT_TABLE[i];
            default:
                throw new IllegalStateException("Bad table");
        }
    }

    private boolean[] correctBits(boolean[] zArr) throws FormatException {
        GenericGF genericGF;
        int i = 8;
        if (this.ddata.getNbLayers() <= 2) {
            i = 6;
            genericGF = GenericGF.AZTEC_DATA_6;
        } else if (this.ddata.getNbLayers() <= 8) {
            genericGF = GenericGF.AZTEC_DATA_8;
        } else if (this.ddata.getNbLayers() <= 22) {
            i = 10;
            genericGF = GenericGF.AZTEC_DATA_10;
        } else {
            i = 12;
            genericGF = GenericGF.AZTEC_DATA_12;
        }
        int nbDatablocks = this.ddata.getNbDatablocks();
        int length = zArr.length / i;
        if (length < nbDatablocks) {
            throw FormatException.getFormatInstance();
        }
        int i2 = length - nbDatablocks;
        int[] iArr = new int[length];
        int length2 = zArr.length % i;
        int i3 = 0;
        while (i3 < length) {
            iArr[i3] = readCode(zArr, length2, i);
            i3++;
            length2 += i;
        }
        try {
            new ReedSolomonDecoder(genericGF).decode(iArr, i2);
            int i4 = (1 << i) - 1;
            length = 0;
            i3 = 0;
            while (length < nbDatablocks) {
                i2 = iArr[length];
                if (i2 != 0) {
                    if (i2 != i4) {
                        if (i2 == 1 || i2 == i4 - 1) {
                            i3++;
                        }
                        length++;
                    }
                }
                throw FormatException.getFormatInstance();
            }
            boolean[] zArr2 = new boolean[((nbDatablocks * i) - i3)];
            i2 = 0;
            for (i3 = 0; i3 < nbDatablocks; i3++) {
                length2 = iArr[i3];
                if (length2 != 1) {
                    if (length2 != i4 - 1) {
                        int i5 = i - 1;
                        while (i5 >= 0) {
                            int i6 = i2 + 1;
                            zArr2[i2] = ((1 << i5) & length2) != 0;
                            i5--;
                            i2 = i6;
                        }
                    }
                }
                Arrays.fill(zArr2, i2, (i2 + i) - 1, length2 > 1);
                i2 += i - 1;
            }
            return zArr2;
        } catch (boolean[] zArr3) {
            throw FormatException.getFormatInstance(zArr3);
        }
    }

    boolean[] extractBits(BitMatrix bitMatrix) {
        int i;
        int i2;
        int i3;
        BitMatrix bitMatrix2 = bitMatrix;
        boolean isCompact = this.ddata.isCompact();
        int nbLayers = this.ddata.getNbLayers();
        int i4 = isCompact ? (nbLayers * 4) + 11 : (nbLayers * 4) + 14;
        int[] iArr = new int[i4];
        boolean[] zArr = new boolean[totalBitsInLayer(nbLayers, isCompact)];
        int i5 = 2;
        if (isCompact) {
            for (i = 0; i < iArr.length; i++) {
                iArr[i] = i;
            }
        } else {
            i2 = i4 / 2;
            i = ((i4 + 1) + (((i2 - 1) / 15) * 2)) / 2;
            for (i3 = 0; i3 < i2; i3++) {
                int i6 = (i3 / 15) + i3;
                iArr[(i2 - i3) - 1] = (i - i6) - 1;
                iArr[i2 + i3] = (i6 + i) + 1;
            }
        }
        i = 0;
        i2 = 0;
        while (i < nbLayers) {
            boolean z;
            int i7;
            Decoder decoder;
            i3 = isCompact ? ((nbLayers - i) * 4) + 9 : ((nbLayers - i) * 4) + 12;
            i6 = i * 2;
            int i8 = (i4 - 1) - i6;
            int i9 = 0;
            while (i9 < i3) {
                int i10 = i9 * 2;
                int i11 = 0;
                for (i5 = 
/*
Method generation error in method: com.google.zxing.aztec.decoder.Decoder.extractBits(com.google.zxing.common.BitMatrix):boolean[], dex: classes.dex
jadx.core.utils.exceptions.CodegenException: Error generate insn: PHI: (r8_2 'i5' int) = (r8_1 'i5' int), (r8_8 'i5' int) binds: {(r8_1 'i5' int)=B:18:0x0068, (r8_8 'i5' int)=B:23:0x00c6} in method: com.google.zxing.aztec.decoder.Decoder.extractBits(com.google.zxing.common.BitMatrix):boolean[], dex: classes.dex
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

                private static int readCode(boolean[] zArr, int i, int i2) {
                    int i3 = 0;
                    for (int i4 = i; i4 < i + i2; i4++) {
                        i3 <<= 1;
                        if (zArr[i4]) {
                            i3 |= 1;
                        }
                    }
                    return i3;
                }
            }
