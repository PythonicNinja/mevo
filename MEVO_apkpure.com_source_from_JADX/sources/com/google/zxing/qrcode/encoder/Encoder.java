package com.google.zxing.qrcode.encoder;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class Encoder {
    private static final int[] ALPHANUMERIC_TABLE = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};
    static final String DEFAULT_BYTE_MODE_ENCODING = "ISO-8859-1";

    private Encoder() {
    }

    private static int calculateMaskPenalty(ByteMatrix byteMatrix) {
        return ((MaskUtil.applyMaskPenaltyRule1(byteMatrix) + MaskUtil.applyMaskPenaltyRule2(byteMatrix)) + MaskUtil.applyMaskPenaltyRule3(byteMatrix)) + MaskUtil.applyMaskPenaltyRule4(byteMatrix);
    }

    public static QRCode encode(String str, ErrorCorrectionLevel errorCorrectionLevel) throws WriterException {
        return encode(str, errorCorrectionLevel, null);
    }

    public static QRCode encode(String str, ErrorCorrectionLevel errorCorrectionLevel, Map<EncodeHintType, ?> map) throws WriterException {
        map = map == null ? null : (String) map.get(EncodeHintType.CHARACTER_SET);
        if (map == null) {
            map = DEFAULT_BYTE_MODE_ENCODING;
        }
        Mode chooseMode = chooseMode(str, map);
        BitArray bitArray = new BitArray();
        if (chooseMode == Mode.BYTE && !DEFAULT_BYTE_MODE_ENCODING.equals(map)) {
            CharacterSetECI characterSetECIByName = CharacterSetECI.getCharacterSetECIByName(map);
            if (characterSetECIByName != null) {
                appendECI(characterSetECIByName, bitArray);
            }
        }
        appendModeInfo(chooseMode, bitArray);
        BitArray bitArray2 = new BitArray();
        appendBytes(str, chooseMode, bitArray2, map);
        map = chooseVersion((bitArray.getSize() + chooseMode.getCharacterCountBits(chooseVersion((bitArray.getSize() + chooseMode.getCharacterCountBits(Version.getVersionForNumber(1))) + bitArray2.getSize(), errorCorrectionLevel))) + bitArray2.getSize(), errorCorrectionLevel);
        BitArray bitArray3 = new BitArray();
        bitArray3.appendBitArray(bitArray);
        appendLengthInfo(chooseMode == Mode.BYTE ? bitArray2.getSizeInBytes() : str.length(), map, chooseMode, bitArray3);
        bitArray3.appendBitArray(bitArray2);
        str = map.getECBlocksForLevel(errorCorrectionLevel);
        int totalCodewords = map.getTotalCodewords() - str.getTotalECCodewords();
        terminateBits(totalCodewords, bitArray3);
        str = interleaveWithECBytes(bitArray3, map.getTotalCodewords(), totalCodewords, str.getNumBlocks());
        QRCode qRCode = new QRCode();
        qRCode.setECLevel(errorCorrectionLevel);
        qRCode.setMode(chooseMode);
        qRCode.setVersion(map);
        int dimensionForVersion = map.getDimensionForVersion();
        ByteMatrix byteMatrix = new ByteMatrix(dimensionForVersion, dimensionForVersion);
        dimensionForVersion = chooseMaskPattern(str, errorCorrectionLevel, map, byteMatrix);
        qRCode.setMaskPattern(dimensionForVersion);
        MatrixUtil.buildMatrix(str, errorCorrectionLevel, map, dimensionForVersion, byteMatrix);
        qRCode.setMatrix(byteMatrix);
        return qRCode;
    }

    static int getAlphanumericCode(int i) {
        return i < ALPHANUMERIC_TABLE.length ? ALPHANUMERIC_TABLE[i] : -1;
    }

    public static Mode chooseMode(String str) {
        return chooseMode(str, null);
    }

    private static Mode chooseMode(String str, String str2) {
        if ("Shift_JIS".equals(str2) != null) {
            return isOnlyDoubleByteKanji(str) != null ? Mode.KANJI : Mode.BYTE;
        }
        Object obj = null;
        Object obj2 = null;
        for (str2 = null; str2 < str.length(); str2++) {
            char charAt = str.charAt(str2);
            if (charAt >= '0' && charAt <= '9') {
                obj2 = 1;
            } else if (getAlphanumericCode(charAt) == -1) {
                return Mode.BYTE;
            } else {
                obj = 1;
            }
        }
        if (obj != null) {
            return Mode.ALPHANUMERIC;
        }
        if (obj2 != null) {
            return Mode.NUMERIC;
        }
        return Mode.BYTE;
    }

    private static boolean isOnlyDoubleByteKanji(java.lang.String r5) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r0 = 0;
        r1 = "Shift_JIS";	 Catch:{ UnsupportedEncodingException -> 0x002b }
        r5 = r5.getBytes(r1);	 Catch:{ UnsupportedEncodingException -> 0x002b }
        r1 = r5.length;
        r2 = r1 % 2;
        if (r2 == 0) goto L_0x000d;
    L_0x000c:
        return r0;
    L_0x000d:
        r2 = 0;
    L_0x000e:
        if (r2 >= r1) goto L_0x0029;
    L_0x0010:
        r3 = r5[r2];
        r3 = r3 & 255;
        r4 = 129; // 0x81 float:1.81E-43 double:6.37E-322;
        if (r3 < r4) goto L_0x001c;
    L_0x0018:
        r4 = 159; // 0x9f float:2.23E-43 double:7.86E-322;
        if (r3 <= r4) goto L_0x0025;
    L_0x001c:
        r4 = 224; // 0xe0 float:3.14E-43 double:1.107E-321;
        if (r3 < r4) goto L_0x0028;
    L_0x0020:
        r4 = 235; // 0xeb float:3.3E-43 double:1.16E-321;
        if (r3 <= r4) goto L_0x0025;
    L_0x0024:
        goto L_0x0028;
    L_0x0025:
        r2 = r2 + 2;
        goto L_0x000e;
    L_0x0028:
        return r0;
    L_0x0029:
        r5 = 1;
        return r5;
    L_0x002b:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.encoder.Encoder.isOnlyDoubleByteKanji(java.lang.String):boolean");
    }

    private static int chooseMaskPattern(BitArray bitArray, ErrorCorrectionLevel errorCorrectionLevel, Version version, ByteMatrix byteMatrix) throws WriterException {
        int i = Integer.MAX_VALUE;
        int i2 = -1;
        for (int i3 = 0; i3 < 8; i3++) {
            MatrixUtil.buildMatrix(bitArray, errorCorrectionLevel, version, i3, byteMatrix);
            int calculateMaskPenalty = calculateMaskPenalty(byteMatrix);
            if (calculateMaskPenalty < i) {
                i2 = i3;
                i = calculateMaskPenalty;
            }
        }
        return i2;
    }

    private static Version chooseVersion(int i, ErrorCorrectionLevel errorCorrectionLevel) throws WriterException {
        for (int i2 = 1; i2 <= 40; i2++) {
            Version versionForNumber = Version.getVersionForNumber(i2);
            if (versionForNumber.getTotalCodewords() - versionForNumber.getECBlocksForLevel(errorCorrectionLevel).getTotalECCodewords() >= (i + 7) / 8) {
                return versionForNumber;
            }
        }
        throw new WriterException("Data too big");
    }

    static void terminateBits(int i, BitArray bitArray) throws WriterException {
        int i2 = i * 8;
        if (bitArray.getSize() > i2) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("data bits cannot fit in the QR Code");
            stringBuilder.append(bitArray.getSize());
            stringBuilder.append(" > ");
            stringBuilder.append(i2);
            throw new WriterException(stringBuilder.toString());
        }
        int i3;
        int i4 = 0;
        for (i3 = 0; i3 < 4 && bitArray.getSize() < i2; i3++) {
            bitArray.appendBit(false);
        }
        i3 = bitArray.getSize() & 7;
        if (i3 > 0) {
            while (i3 < 8) {
                bitArray.appendBit(false);
                i3++;
            }
        }
        i -= bitArray.getSizeInBytes();
        while (i4 < i) {
            bitArray.appendBits((i4 & 1) == 0 ? 236 : 17, 8);
            i4++;
        }
        if (bitArray.getSize() != i2) {
            throw new WriterException("Bits size does not equal capacity");
        }
    }

    static void getNumDataBytesAndNumECBytesForBlockID(int i, int i2, int i3, int i4, int[] iArr, int[] iArr2) throws WriterException {
        if (i4 >= i3) {
            throw new WriterException("Block ID too large");
        }
        int i5 = i % i3;
        int i6 = i3 - i5;
        int i7 = i / i3;
        int i8 = i7 + 1;
        i2 /= i3;
        int i9 = i2 + 1;
        i7 -= i2;
        i8 -= i9;
        if (i7 != i8) {
            throw new WriterException("EC bytes mismatch");
        } else if (i3 != i6 + i5) {
            throw new WriterException("RS blocks mismatch");
        } else if (i != ((i2 + i7) * i6) + ((i9 + i8) * i5)) {
            throw new WriterException("Total bytes mismatch");
        } else if (i4 < i6) {
            iArr[0] = i2;
            iArr2[0] = i7;
        } else {
            iArr[0] = i9;
            iArr2[0] = i8;
        }
    }

    static BitArray interleaveWithECBytes(BitArray bitArray, int i, int i2, int i3) throws WriterException {
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        if (bitArray.getSizeInBytes() != i5) {
            throw new WriterException("Number of bits and data bytes does not match");
        }
        Collection<BlockPair> arrayList = new ArrayList(i6);
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        for (int i11 = 0; i11 < i6; i11++) {
            int[] iArr = new int[1];
            int[] iArr2 = new int[1];
            int[] iArr3 = iArr2;
            getNumDataBytesAndNumECBytesForBlockID(i4, i5, i6, i11, iArr, iArr2);
            int i12 = iArr[0];
            byte[] bArr = new byte[i12];
            bitArray.toBytes(i8 * 8, bArr, 0, i12);
            byte[] generateECBytes = generateECBytes(bArr, iArr3[0]);
            arrayList.add(new BlockPair(bArr, generateECBytes));
            i9 = Math.max(i9, i12);
            i10 = Math.max(i10, generateECBytes.length);
            i8 += iArr[0];
        }
        if (i5 != i8) {
            throw new WriterException("Data bytes does not match offset");
        }
        BitArray bitArray2 = new BitArray();
        for (int i13 = 0; i13 < i9; i13++) {
            for (BlockPair dataBytes : arrayList) {
                byte[] dataBytes2 = dataBytes.getDataBytes();
                if (i13 < dataBytes2.length) {
                    bitArray2.appendBits(dataBytes2[i13], 8);
                }
            }
        }
        while (i7 < i10) {
            for (BlockPair errorCorrectionBytes : arrayList) {
                byte[] errorCorrectionBytes2 = errorCorrectionBytes.getErrorCorrectionBytes();
                if (i7 < errorCorrectionBytes2.length) {
                    bitArray2.appendBits(errorCorrectionBytes2[i7], 8);
                }
            }
            i7++;
        }
        if (i4 == bitArray2.getSizeInBytes()) {
            return bitArray2;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Interleaving error: ");
        stringBuilder.append(i4);
        stringBuilder.append(" and ");
        stringBuilder.append(bitArray2.getSizeInBytes());
        stringBuilder.append(" differ.");
        throw new WriterException(stringBuilder.toString());
    }

    static byte[] generateECBytes(byte[] bArr, int i) {
        int length = bArr.length;
        int[] iArr = new int[(length + i)];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        new ReedSolomonEncoder(GenericGF.QR_CODE_FIELD_256).encode(iArr, i);
        bArr = new byte[i];
        for (int i3 = 0; i3 < i; i3++) {
            bArr[i3] = (byte) iArr[length + i3];
        }
        return bArr;
    }

    static void appendModeInfo(Mode mode, BitArray bitArray) {
        bitArray.appendBits(mode.getBits(), 4);
    }

    static void appendLengthInfo(int i, Version version, Mode mode, BitArray bitArray) throws WriterException {
        version = mode.getCharacterCountBits(version);
        int i2 = 1 << version;
        if (i >= i2) {
            bitArray = new StringBuilder();
            bitArray.append(i);
            bitArray.append(" is bigger than ");
            bitArray.append(i2 - 1);
            throw new WriterException(bitArray.toString());
        }
        bitArray.appendBits(i, version);
    }

    static void appendBytes(String str, Mode mode, BitArray bitArray, String str2) throws WriterException {
        switch (mode) {
            case NUMERIC:
                appendNumericBytes(str, bitArray);
                return;
            case ALPHANUMERIC:
                appendAlphanumericBytes(str, bitArray);
                return;
            case BYTE:
                append8BitBytes(str, bitArray, str2);
                return;
            case KANJI:
                appendKanjiBytes(str, bitArray);
                return;
            default:
                bitArray = new StringBuilder();
                bitArray.append("Invalid mode: ");
                bitArray.append(mode);
                throw new WriterException(bitArray.toString());
        }
    }

    static void appendNumericBytes(CharSequence charSequence, BitArray bitArray) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int charAt = charSequence.charAt(i) - 48;
            int i2 = i + 2;
            if (i2 < length) {
                bitArray.appendBits(((charAt * 100) + ((charSequence.charAt(i + 1) - 48) * 10)) + (charSequence.charAt(i2) - 48), 10);
                i += 3;
            } else {
                i++;
                if (i < length) {
                    bitArray.appendBits((charAt * 10) + (charSequence.charAt(i) - 48), 7);
                    i = i2;
                } else {
                    bitArray.appendBits(charAt, 4);
                }
            }
        }
    }

    static void appendAlphanumericBytes(CharSequence charSequence, BitArray bitArray) throws WriterException {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int alphanumericCode = getAlphanumericCode(charSequence.charAt(i));
            if (alphanumericCode == -1) {
                throw new WriterException();
            }
            int i2 = i + 1;
            if (i2 < length) {
                i2 = getAlphanumericCode(charSequence.charAt(i2));
                if (i2 == -1) {
                    throw new WriterException();
                }
                bitArray.appendBits((alphanumericCode * 45) + i2, 11);
                i += 2;
            } else {
                bitArray.appendBits(alphanumericCode, 6);
                i = i2;
            }
        }
    }

    static void append8BitBytes(String str, BitArray bitArray, String str2) throws WriterException {
        try {
            for (byte appendBits : str.getBytes(str2)) {
                bitArray.appendBits(appendBits, 8);
            }
        } catch (Throwable e) {
            throw new WriterException(e);
        }
    }

    static void appendKanjiBytes(String str, BitArray bitArray) throws WriterException {
        try {
            str = str.getBytes("Shift_JIS");
            int length = str.length;
            for (int i = 0; i < length; i += 2) {
                int i2 = ((str[i] & 255) << 8) | (str[i + 1] & 255);
                i2 = (i2 < 33088 || i2 > 40956) ? (i2 < 57408 || i2 > 60351) ? -1 : i2 - 49472 : i2 - 33088;
                if (i2 == -1) {
                    throw new WriterException("Invalid byte sequence");
                }
                bitArray.appendBits(((i2 >> 8) * 192) + (i2 & 255), 13);
            }
        } catch (Throwable e) {
            throw new WriterException(e);
        }
    }

    private static void appendECI(CharacterSetECI characterSetECI, BitArray bitArray) {
        bitArray.appendBits(Mode.ECI.getBits(), 4);
        bitArray.appendBits(characterSetECI.getValue(), 8);
    }
}
