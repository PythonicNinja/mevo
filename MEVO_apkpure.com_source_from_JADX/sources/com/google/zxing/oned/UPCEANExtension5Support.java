package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import com.mevo.app.constants.BikeTypes;
import java.util.EnumMap;
import java.util.Map;

final class UPCEANExtension5Support {
    private static final int[] CHECK_DIGIT_ENCODINGS = new int[]{24, 20, 18, 17, 12, 6, 3, 10, 9, 5};
    private final int[] decodeMiddleCounters = new int[4];
    private final StringBuilder decodeRowStringBuffer = new StringBuilder();

    UPCEANExtension5Support() {
    }

    Result decodeRow(int i, BitArray bitArray, int[] iArr) throws NotFoundException {
        StringBuilder stringBuilder = this.decodeRowStringBuffer;
        stringBuilder.setLength(0);
        bitArray = decodeMiddle(bitArray, iArr, stringBuilder);
        String stringBuilder2 = stringBuilder.toString();
        Map parseExtensionString = parseExtensionString(stringBuilder2);
        r4 = new ResultPoint[2];
        i = (float) i;
        r4[0] = new ResultPoint(((float) (iArr[0] + iArr[1])) / 2.0f, i);
        r4[1] = new ResultPoint((float) bitArray, i);
        Result result = new Result(stringBuilder2, null, r4, BarcodeFormat.UPC_EAN_EXTENSION);
        if (parseExtensionString != null) {
            result.putAllMetadata(parseExtensionString);
        }
        return result;
    }

    int decodeMiddle(BitArray bitArray, int[] iArr, StringBuilder stringBuilder) throws NotFoundException {
        int[] iArr2 = this.decodeMiddleCounters;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int size = bitArray.getSize();
        int i = iArr[1];
        iArr = null;
        int i2 = 0;
        while (iArr < 5 && i < size) {
            int decodeDigit = UPCEANReader.decodeDigit(bitArray, iArr2, i, UPCEANReader.L_AND_G_PATTERNS);
            stringBuilder.append((char) ((decodeDigit % 10) + 48));
            int i3 = i;
            for (int i4 : iArr2) {
                i3 += i4;
            }
            if (decodeDigit >= 10) {
                i2 |= 1 << (4 - iArr);
            }
            i = iArr != 4 ? bitArray.getNextUnset(bitArray.getNextSet(i3)) : i3;
            iArr++;
        }
        if (stringBuilder.length() != 5) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (extensionChecksum(stringBuilder.toString()) == determineCheckDigit(i2)) {
            return i;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int extensionChecksum(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        for (int i2 = length - 2; i2 >= 0; i2 -= 2) {
            i += charSequence.charAt(i2) - 48;
        }
        i *= 3;
        for (length--; length >= 0; length -= 2) {
            i += charSequence.charAt(length) - 48;
        }
        return (i * 3) % 10;
    }

    private static int determineCheckDigit(int i) throws NotFoundException {
        for (int i2 = 0; i2 < 10; i2++) {
            if (i == CHECK_DIGIT_ENCODINGS[i2]) {
                return i2;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static Map<ResultMetadataType, Object> parseExtensionString(String str) {
        if (str.length() != 5) {
            return null;
        }
        str = parseExtension5String(str);
        if (str == null) {
            return null;
        }
        Map<ResultMetadataType, Object> enumMap = new EnumMap(ResultMetadataType.class);
        enumMap.put(ResultMetadataType.SUGGESTED_PRICE, str);
        return enumMap;
    }

    private static String parseExtension5String(String str) {
        String str2;
        StringBuilder stringBuilder;
        char charAt = str.charAt(0);
        if (charAt == '0') {
            str2 = "£";
        } else if (charAt == '5') {
            str2 = "$";
        } else if (charAt != '9') {
            str2 = "";
        } else if ("90000".equals(str)) {
            return null;
        } else {
            if ("99991".equals(str)) {
                return "0.00";
            }
            if ("99990".equals(str)) {
                return "Used";
            }
            str2 = "";
        }
        str = Integer.parseInt(str.substring(1));
        String valueOf = String.valueOf(str / 100);
        str %= 100;
        if (str < 10) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(BikeTypes.DEFAULT);
            stringBuilder.append(str);
            str = stringBuilder.toString();
        } else {
            str = String.valueOf(str);
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(str2);
        stringBuilder.append(valueOf);
        stringBuilder.append('.');
        stringBuilder.append(str);
        return stringBuilder.toString();
    }
}
