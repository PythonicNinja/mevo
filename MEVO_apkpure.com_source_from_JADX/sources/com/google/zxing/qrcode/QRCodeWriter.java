package com.google.zxing.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;
import java.util.Map;

public final class QRCodeWriter implements Writer {
    private static final int QUIET_ZONE_SIZE = 4;

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2) throws WriterException {
        return encode(str, barcodeFormat, i, i2, null);
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (barcodeFormat != BarcodeFormat.QR_CODE) {
            i = new StringBuilder();
            i.append("Can only encode QR_CODE, but got ");
            i.append(barcodeFormat);
            throw new IllegalArgumentException(i.toString());
        } else {
            if (i >= 0) {
                if (i2 >= 0) {
                    barcodeFormat = ErrorCorrectionLevel.L;
                    int i3 = 4;
                    if (map != null) {
                        Enum enumR = (ErrorCorrectionLevel) map.get(EncodeHintType.ERROR_CORRECTION);
                        if (enumR != null) {
                            barcodeFormat = enumR;
                        }
                        Integer num = (Integer) map.get(EncodeHintType.MARGIN);
                        if (num != null) {
                            i3 = num.intValue();
                        }
                    }
                    return renderResult(Encoder.encode(str, barcodeFormat, map), i, i2, i3);
                }
            }
            barcodeFormat = new StringBuilder();
            barcodeFormat.append("Requested dimensions are too small: ");
            barcodeFormat.append(i);
            barcodeFormat.append(120);
            barcodeFormat.append(i2);
            throw new IllegalArgumentException(barcodeFormat.toString());
        }
    }

    private static BitMatrix renderResult(QRCode qRCode, int i, int i2, int i3) {
        qRCode = qRCode.getMatrix();
        if (qRCode == null) {
            throw new IllegalStateException();
        }
        int width = qRCode.getWidth();
        int height = qRCode.getHeight();
        i3 *= 2;
        int i4 = width + i3;
        i3 += height;
        i = Math.max(i, i4);
        i2 = Math.max(i2, i3);
        i3 = Math.min(i / i4, i2 / i3);
        i4 = (i - (width * i3)) / 2;
        int i5 = (i2 - (height * i3)) / 2;
        BitMatrix bitMatrix = new BitMatrix(i, i2);
        i2 = 0;
        while (i2 < height) {
            int i6 = i4;
            int i7 = 0;
            while (i7 < width) {
                if (qRCode.get(i7, i2) == (byte) 1) {
                    bitMatrix.setRegion(i6, i5, i3, i3);
                }
                i7++;
                i6 += i3;
            }
            i2++;
            i5 += i3;
        }
        return bitMatrix;
    }
}
