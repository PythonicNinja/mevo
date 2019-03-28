package com.google.zxing.common;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;

public class GlobalHistogramBinarizer extends Binarizer {
    private static final byte[] EMPTY = new byte[0];
    private static final int LUMINANCE_BITS = 5;
    private static final int LUMINANCE_BUCKETS = 32;
    private static final int LUMINANCE_SHIFT = 3;
    private final int[] buckets = new int[32];
    private byte[] luminances = EMPTY;

    public GlobalHistogramBinarizer(LuminanceSource luminanceSource) {
        super(luminanceSource);
    }

    public BitArray getBlackRow(int i, BitArray bitArray) throws NotFoundException {
        int[] iArr;
        int i2;
        int i3;
        int estimateBlackPoint;
        int i4;
        int i5;
        LuminanceSource luminanceSource = getLuminanceSource();
        int width = luminanceSource.getWidth();
        if (bitArray != null) {
            if (bitArray.getSize() >= width) {
                bitArray.clear();
                initArrays(width);
                i = luminanceSource.getRow(i, this.luminances);
                iArr = this.buckets;
                for (i2 = 0; i2 < width; i2++) {
                    i3 = (i[i2] & 255) >> 3;
                    iArr[i3] = iArr[i3] + 1;
                }
                estimateBlackPoint = estimateBlackPoint(iArr);
                i2 = i[1] & 255;
                i3 = i[0] & 255;
                i4 = 1;
                while (i4 < width - 1) {
                    int i6 = i4 + 1;
                    i5 = i[i6] & 255;
                    if ((((i2 * 4) - i3) - i5) / 2 < estimateBlackPoint) {
                        bitArray.set(i4);
                    }
                    i3 = i2;
                    i4 = i6;
                    i2 = i5;
                }
                return bitArray;
            }
        }
        bitArray = new BitArray(width);
        initArrays(width);
        i = luminanceSource.getRow(i, this.luminances);
        iArr = this.buckets;
        for (i2 = 0; i2 < width; i2++) {
            i3 = (i[i2] & 255) >> 3;
            iArr[i3] = iArr[i3] + 1;
        }
        estimateBlackPoint = estimateBlackPoint(iArr);
        i2 = i[1] & 255;
        i3 = i[0] & 255;
        i4 = 1;
        while (i4 < width - 1) {
            int i62 = i4 + 1;
            i5 = i[i62] & 255;
            if ((((i2 * 4) - i3) - i5) / 2 < estimateBlackPoint) {
                bitArray.set(i4);
            }
            i3 = i2;
            i4 = i62;
            i2 = i5;
        }
        return bitArray;
    }

    public BitMatrix getBlackMatrix() throws NotFoundException {
        int i;
        LuminanceSource luminanceSource = getLuminanceSource();
        int width = luminanceSource.getWidth();
        int height = luminanceSource.getHeight();
        BitMatrix bitMatrix = new BitMatrix(width, height);
        initArrays(width);
        int[] iArr = this.buckets;
        for (i = 1; i < 5; i++) {
            int i2;
            byte[] row = luminanceSource.getRow((height * i) / 5, this.luminances);
            int i3 = (width * 4) / 5;
            for (i2 = width / 5; i2 < i3; i2++) {
                int i4 = (row[i2] & 255) >> 3;
                iArr[i4] = iArr[i4] + 1;
            }
        }
        int estimateBlackPoint = estimateBlackPoint(iArr);
        byte[] matrix = luminanceSource.getMatrix();
        for (i = 0; i < height; i++) {
            i2 = i * width;
            for (int i5 = 0; i5 < width; i5++) {
                if ((matrix[i2 + i5] & 255) < estimateBlackPoint) {
                    bitMatrix.set(i5, i);
                }
            }
        }
        return bitMatrix;
    }

    public Binarizer createBinarizer(LuminanceSource luminanceSource) {
        return new GlobalHistogramBinarizer(luminanceSource);
    }

    private void initArrays(int i) {
        if (this.luminances.length < i) {
            this.luminances = new byte[i];
        }
        for (int i2 = 0; i2 < 32; i2++) {
            this.buckets[i2] = 0;
        }
    }

    private static int estimateBlackPoint(int[] iArr) throws NotFoundException {
        int i;
        int i2;
        int length = iArr.length;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (i = 0; i < length; i++) {
            if (iArr[i] > i3) {
                i3 = iArr[i];
                i5 = i;
            }
            if (iArr[i] > i4) {
                i4 = iArr[i];
            }
        }
        i = 0;
        i3 = 0;
        for (i2 = 0; i2 < length; i2++) {
            int i6 = i2 - i5;
            int i7 = (iArr[i2] * i6) * i6;
            if (i7 > i) {
                i3 = i2;
                i = i7;
            }
        }
        if (i5 > i3) {
            int i8 = i5;
            i5 = i3;
            i3 = i8;
        }
        if (i3 - i5 <= length / 16) {
            throw NotFoundException.getNotFoundInstance();
        }
        length = i3 - 1;
        i2 = -1;
        i = length;
        while (length > i5) {
            i6 = length - i5;
            i6 = ((i6 * i6) * (i3 - length)) * (i4 - iArr[length]);
            if (i6 > i2) {
                i = length;
                i2 = i6;
            }
            length--;
        }
        return i << 3;
    }
}
