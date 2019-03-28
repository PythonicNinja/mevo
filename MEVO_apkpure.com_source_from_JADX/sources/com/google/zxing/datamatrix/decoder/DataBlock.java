package com.google.zxing.datamatrix.decoder;

final class DataBlock {
    private final byte[] codewords;
    private final int numDataCodewords;

    private DataBlock(int i, byte[] bArr) {
        this.numDataCodewords = i;
        this.codewords = bArr;
    }

    static DataBlock[] getDataBlocks(byte[] bArr, Version version) {
        int i;
        ECBlocks eCBlocks = version.getECBlocks();
        ECB[] eCBlocks2 = eCBlocks.getECBlocks();
        int i2 = 0;
        for (ECB count : eCBlocks2) {
            i2 += count.getCount();
        }
        DataBlock[] dataBlockArr = new DataBlock[i2];
        int length = eCBlocks2.length;
        i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            ECB ecb = eCBlocks2[i2];
            int i4 = i3;
            i3 = 0;
            while (i3 < ecb.getCount()) {
                int dataCodewords = ecb.getDataCodewords();
                int i5 = i4 + 1;
                dataBlockArr[i4] = new DataBlock(dataCodewords, new byte[(eCBlocks.getECCodewords() + dataCodewords)]);
                i3++;
                i4 = i5;
            }
            i2++;
            i3 = i4;
        }
        int length2 = dataBlockArr[0].codewords.length - eCBlocks.getECCodewords();
        int i6 = length2 - 1;
        length = 0;
        i2 = 0;
        while (length < i6) {
            i = i2;
            i2 = 0;
            while (i2 < i3) {
                dataCodewords = i + 1;
                dataBlockArr[i2].codewords[length] = bArr[i];
                i2++;
                i = dataCodewords;
            }
            length++;
            i2 = i;
        }
        version = version.getVersionNumber() == 24 ? true : null;
        length = version != null ? 8 : i3;
        i = i2;
        i2 = 0;
        while (i2 < length) {
            dataCodewords = i + 1;
            dataBlockArr[i2].codewords[i6] = bArr[i];
            i2++;
            i = dataCodewords;
        }
        i6 = dataBlockArr[0].codewords.length;
        while (length2 < i6) {
            length = 0;
            while (length < i3) {
                i2 = version != null ? (length + 8) % i3 : length;
                i4 = (version == null || i2 <= 7) ? length2 : length2 - 1;
                dataCodewords = i + 1;
                dataBlockArr[i2].codewords[i4] = bArr[i];
                length++;
                i = dataCodewords;
            }
            length2++;
        }
        if (i == bArr.length) {
            return dataBlockArr;
        }
        throw new IllegalArgumentException();
    }

    int getNumDataCodewords() {
        return this.numDataCodewords;
    }

    byte[] getCodewords() {
        return this.codewords;
    }
}
