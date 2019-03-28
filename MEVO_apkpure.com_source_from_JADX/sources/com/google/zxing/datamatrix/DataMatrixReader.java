package com.google.zxing.datamatrix;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.decoder.Decoder;
import com.google.zxing.datamatrix.detector.Detector;
import java.util.Map;

public final class DataMatrixReader implements Reader {
    private static final ResultPoint[] NO_POINTS = new ResultPoint[0];
    private final Decoder decoder = new Decoder();

    public void reset() {
    }

    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, ChecksumException, FormatException {
        return decode(binaryBitmap, null);
    }

    public Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        if (map == null || map.containsKey(DecodeHintType.PURE_BARCODE) == null) {
            binaryBitmap = new Detector(binaryBitmap.getBlackMatrix()).detect();
            Map<DecodeHintType, ?> decode = this.decoder.decode(binaryBitmap.getBits());
            map = binaryBitmap.getPoints();
            binaryBitmap = decode;
        } else {
            binaryBitmap = this.decoder.decode(extractPureBits(binaryBitmap.getBlackMatrix()));
            map = NO_POINTS;
        }
        Result result = new Result(binaryBitmap.getText(), binaryBitmap.getRawBytes(), map, BarcodeFormat.DATA_MATRIX);
        map = binaryBitmap.getByteSegments();
        if (map != null) {
            result.putMetadata(ResultMetadataType.BYTE_SEGMENTS, map);
        }
        binaryBitmap = binaryBitmap.getECLevel();
        if (binaryBitmap != null) {
            result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, binaryBitmap);
        }
        return result;
    }

    private static BitMatrix extractPureBits(BitMatrix bitMatrix) throws NotFoundException {
        int[] topLeftOnBit = bitMatrix.getTopLeftOnBit();
        int[] bottomRightOnBit = bitMatrix.getBottomRightOnBit();
        if (topLeftOnBit != null) {
            if (bottomRightOnBit != null) {
                int moduleSize = moduleSize(topLeftOnBit, bitMatrix);
                int i = topLeftOnBit[1];
                int i2 = bottomRightOnBit[1];
                int i3 = topLeftOnBit[0];
                int i4 = ((bottomRightOnBit[0] - i3) + 1) / moduleSize;
                i2 = ((i2 - i) + 1) / moduleSize;
                if (i4 > 0) {
                    if (i2 > 0) {
                        int i5 = moduleSize / 2;
                        i += i5;
                        i3 += i5;
                        BitMatrix bitMatrix2 = new BitMatrix(i4, i2);
                        for (int i6 = 0; i6 < i2; i6++) {
                            int i7 = (i6 * moduleSize) + i;
                            for (int i8 = 0; i8 < i4; i8++) {
                                if (bitMatrix.get((i8 * moduleSize) + i3, i7)) {
                                    bitMatrix2.set(i8, i6);
                                }
                            }
                        }
                        return bitMatrix2;
                    }
                }
                throw NotFoundException.getNotFoundInstance();
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int moduleSize(int[] iArr, BitMatrix bitMatrix) throws NotFoundException {
        int width = bitMatrix.getWidth();
        int i = iArr[0];
        int i2 = iArr[1];
        while (i < width && bitMatrix.get(i, i2)) {
            i++;
        }
        if (i == width) {
            throw NotFoundException.getNotFoundInstance();
        }
        i -= iArr[0];
        if (i != 0) {
            return i;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
