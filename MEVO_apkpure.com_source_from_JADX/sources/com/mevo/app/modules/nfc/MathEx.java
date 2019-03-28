package com.mevo.app.modules.nfc;

import java.util.Arrays;

class MathEx {
    MathEx() {
    }

    public static String ByteArrayToHexString(byte[] bArr) {
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] cArr2 = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            cArr2[i3] = cArr[i2 >>> 4];
            cArr2[i3 + 1] = cArr[i2 & 15];
        }
        return new String(cArr2);
    }

    public static byte[] HexStringToByteArray(String str) throws IllegalArgumentException {
        int length = str.length();
        if (length % 2 == 1) {
            throw new IllegalArgumentException("Hex string must have even number of characters");
        }
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public static byte[] ConcatArrays(byte[] bArr, byte[]... bArr2) {
        int length = bArr.length;
        for (byte[] length2 : bArr2) {
            length += length2.length;
        }
        Object copyOf = Arrays.copyOf(bArr, length);
        bArr = bArr.length;
        length = bArr;
        for (Object obj : bArr2) {
            System.arraycopy(obj, 0, copyOf, length, obj.length);
            length += obj.length;
        }
        return copyOf;
    }
}
