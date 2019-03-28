package com.google.zxing.qrcode.decoder;

import com.google.zxing.qrcode.decoder.Version.ECB;

final class DataBlock {
    private final byte[] codewords;
    private final int numDataCodewords;

    private DataBlock(int i, byte[] bArr) {
        this.numDataCodewords = i;
        this.codewords = bArr;
    }

    static DataBlock[] getDataBlocks(byte[] bArr, Version version, ErrorCorrectionLevel errorCorrectionLevel) {
        if (bArr.length != version.getTotalCodewords()) {
            throw new IllegalArgumentException();
        }
        version = version.getECBlocksForLevel(errorCorrectionLevel);
        errorCorrectionLevel = version.getECBlocks();
        int i = 0;
        for (ECB count : errorCorrectionLevel) {
            i += count.getCount();
        }
        DataBlock[] dataBlockArr = new DataBlock[i];
        int length = errorCorrectionLevel.length;
        i = 0;
        int i2 = 0;
        while (i < length) {
            ECB ecb = errorCorrectionLevel[i];
            int i3 = i2;
            i2 = 0;
            while (i2 < ecb.getCount()) {
                int dataCodewords = ecb.getDataCodewords();
                int i4 = i3 + 1;
                dataBlockArr[i3] = new DataBlock(dataCodewords, new byte[(version.getECCodewordsPerBlock() + dataCodewords)]);
                i2++;
                i3 = i4;
            }
            i++;
            i2 = i3;
        }
        errorCorrectionLevel = dataBlockArr[0].codewords.length;
        length = dataBlockArr.length - 1;
        while (length >= 0) {
            if (dataBlockArr[length].codewords.length == errorCorrectionLevel) {
                break;
            }
            length--;
        }
        length++;
        errorCorrectionLevel -= version.getECCodewordsPerBlock();
        version = null;
        i = 0;
        while (version < errorCorrectionLevel) {
            int i5 = i;
            i = 0;
            while (i < i2) {
                dataCodewords = i5 + 1;
                dataBlockArr[i].codewords[version] = bArr[i5];
                i++;
                i5 = dataCodewords;
            }
            version++;
            i = i5;
        }
        version = length;
        while (version < i2) {
            i3 = i + 1;
            dataBlockArr[version].codewords[errorCorrectionLevel] = bArr[i];
            version++;
            i = i3;
        }
        ErrorCorrectionLevel length2 = dataBlockArr[0].codewords.length;
        while (errorCorrectionLevel < length2) {
            i5 = i;
            i = 0;
            while (i < i2) {
                int i6 = i5 + 1;
                dataBlockArr[i].codewords[i < length ? errorCorrectionLevel : errorCorrectionLevel + 1] = bArr[i5];
                i++;
                i5 = i6;
            }
            errorCorrectionLevel++;
            i = i5;
        }
        return dataBlockArr;
    }

    int getNumDataCodewords() {
        return this.numDataCodewords;
    }

    byte[] getCodewords() {
        return this.codewords;
    }
}
