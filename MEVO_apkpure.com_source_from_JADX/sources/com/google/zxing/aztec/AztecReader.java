package com.google.zxing.aztec;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.aztec.decoder.Decoder;
import com.google.zxing.aztec.detector.Detector;
import com.google.zxing.common.DecoderResult;
import java.util.Map;

public final class AztecReader implements Reader {
    public void reset() {
    }

    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, FormatException {
        return decode(binaryBitmap, null);
    }

    public Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        ResultPoint[] points;
        ResultPoint[] resultPointArr;
        NotFoundException notFoundException;
        NotFoundException notFoundException2;
        AztecDetectorResult detect;
        ResultPointCallback resultPointCallback;
        int length;
        ReaderException e;
        Detector detector = new Detector(binaryBitmap.getBlackMatrix());
        binaryBitmap = null;
        DecoderResult decoderResult = null;
        try {
            AztecDetectorResult detect2 = detector.detect(false);
            points = detect2.getPoints();
            try {
                DecoderResult decode = new Decoder().decode(detect2);
                resultPointArr = points;
                notFoundException = null;
                decoderResult = decode;
                notFoundException2 = notFoundException;
            } catch (NotFoundException e2) {
                notFoundException2 = e2;
                resultPointArr = points;
                notFoundException = null;
                if (decoderResult == null) {
                    try {
                        detect = detector.detect(true);
                        resultPointArr = detect.getPoints();
                        decoderResult = new Decoder().decode(detect);
                    } catch (BinaryBitmap binaryBitmap2) {
                        if (notFoundException2 != null) {
                            throw notFoundException2;
                        } else if (notFoundException == null) {
                            throw binaryBitmap2;
                        } else {
                            throw notFoundException;
                        }
                    }
                }
                if (map != null) {
                    resultPointCallback = (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
                    if (resultPointCallback != null) {
                        length = resultPointArr.length;
                        while (binaryBitmap2 < length) {
                            resultPointCallback.foundPossibleResultPoint(resultPointArr[binaryBitmap2]);
                            binaryBitmap2++;
                        }
                    }
                }
                binaryBitmap2 = new Result(decoderResult.getText(), decoderResult.getRawBytes(), resultPointArr, BarcodeFormat.AZTEC);
                map = decoderResult.getByteSegments();
                if (map != null) {
                    binaryBitmap2.putMetadata(ResultMetadataType.BYTE_SEGMENTS, map);
                }
                map = decoderResult.getECLevel();
                if (map != null) {
                    binaryBitmap2.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, map);
                }
                return binaryBitmap2;
            } catch (FormatException e3) {
                e = e3;
                resultPointArr = points;
                notFoundException = e;
                notFoundException2 = null;
                if (decoderResult == null) {
                    detect = detector.detect(true);
                    resultPointArr = detect.getPoints();
                    decoderResult = new Decoder().decode(detect);
                }
                if (map != null) {
                    resultPointCallback = (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
                    if (resultPointCallback != null) {
                        length = resultPointArr.length;
                        while (binaryBitmap2 < length) {
                            resultPointCallback.foundPossibleResultPoint(resultPointArr[binaryBitmap2]);
                            binaryBitmap2++;
                        }
                    }
                }
                binaryBitmap2 = new Result(decoderResult.getText(), decoderResult.getRawBytes(), resultPointArr, BarcodeFormat.AZTEC);
                map = decoderResult.getByteSegments();
                if (map != null) {
                    binaryBitmap2.putMetadata(ResultMetadataType.BYTE_SEGMENTS, map);
                }
                map = decoderResult.getECLevel();
                if (map != null) {
                    binaryBitmap2.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, map);
                }
                return binaryBitmap2;
            }
        } catch (NotFoundException e4) {
            notFoundException2 = e4;
            points = null;
            resultPointArr = points;
            notFoundException = null;
            if (decoderResult == null) {
                detect = detector.detect(true);
                resultPointArr = detect.getPoints();
                decoderResult = new Decoder().decode(detect);
            }
            if (map != null) {
                resultPointCallback = (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
                if (resultPointCallback != null) {
                    length = resultPointArr.length;
                    while (binaryBitmap2 < length) {
                        resultPointCallback.foundPossibleResultPoint(resultPointArr[binaryBitmap2]);
                        binaryBitmap2++;
                    }
                }
            }
            binaryBitmap2 = new Result(decoderResult.getText(), decoderResult.getRawBytes(), resultPointArr, BarcodeFormat.AZTEC);
            map = decoderResult.getByteSegments();
            if (map != null) {
                binaryBitmap2.putMetadata(ResultMetadataType.BYTE_SEGMENTS, map);
            }
            map = decoderResult.getECLevel();
            if (map != null) {
                binaryBitmap2.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, map);
            }
            return binaryBitmap2;
        } catch (FormatException e5) {
            e = e5;
            points = null;
            resultPointArr = points;
            notFoundException = e;
            notFoundException2 = null;
            if (decoderResult == null) {
                detect = detector.detect(true);
                resultPointArr = detect.getPoints();
                decoderResult = new Decoder().decode(detect);
            }
            if (map != null) {
                resultPointCallback = (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
                if (resultPointCallback != null) {
                    length = resultPointArr.length;
                    while (binaryBitmap2 < length) {
                        resultPointCallback.foundPossibleResultPoint(resultPointArr[binaryBitmap2]);
                        binaryBitmap2++;
                    }
                }
            }
            binaryBitmap2 = new Result(decoderResult.getText(), decoderResult.getRawBytes(), resultPointArr, BarcodeFormat.AZTEC);
            map = decoderResult.getByteSegments();
            if (map != null) {
                binaryBitmap2.putMetadata(ResultMetadataType.BYTE_SEGMENTS, map);
            }
            map = decoderResult.getECLevel();
            if (map != null) {
                binaryBitmap2.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, map);
            }
            return binaryBitmap2;
        }
        if (decoderResult == null) {
            detect = detector.detect(true);
            resultPointArr = detect.getPoints();
            decoderResult = new Decoder().decode(detect);
        }
        if (map != null) {
            resultPointCallback = (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
            if (resultPointCallback != null) {
                length = resultPointArr.length;
                while (binaryBitmap2 < length) {
                    resultPointCallback.foundPossibleResultPoint(resultPointArr[binaryBitmap2]);
                    binaryBitmap2++;
                }
            }
        }
        binaryBitmap2 = new Result(decoderResult.getText(), decoderResult.getRawBytes(), resultPointArr, BarcodeFormat.AZTEC);
        map = decoderResult.getByteSegments();
        if (map != null) {
            binaryBitmap2.putMetadata(ResultMetadataType.BYTE_SEGMENTS, map);
        }
        map = decoderResult.getECLevel();
        if (map != null) {
            binaryBitmap2.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, map);
        }
        return binaryBitmap2;
    }
}
