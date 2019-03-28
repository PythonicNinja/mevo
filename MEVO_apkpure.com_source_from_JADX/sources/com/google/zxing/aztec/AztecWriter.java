package com.google.zxing.aztec;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.aztec.encoder.AztecCode;
import com.google.zxing.aztec.encoder.Encoder;
import com.google.zxing.common.BitMatrix;
import java.nio.charset.Charset;
import java.util.Map;

public final class AztecWriter implements Writer {
    private static final Charset DEFAULT_CHARSET = Charset.forName("ISO-8859-1");

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2) {
        return encode(str, barcodeFormat, i, i2, null);
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) {
        Number number;
        int i3;
        int i4;
        Number number2 = null;
        String str2 = map == null ? null : (String) map.get(EncodeHintType.CHARACTER_SET);
        if (map == null) {
            number = null;
        } else {
            number = (Number) map.get(EncodeHintType.ERROR_CORRECTION);
        }
        if (map != null) {
            number2 = (Number) map.get(EncodeHintType.AZTEC_LAYERS);
        }
        if (str2 == null) {
            map = DEFAULT_CHARSET;
        } else {
            map = Charset.forName(str2);
        }
        Map<EncodeHintType, ?> map2 = map;
        if (number == null) {
            i3 = 33;
        } else {
            i3 = number.intValue();
        }
        if (number2 == null) {
            i4 = 0;
        } else {
            i4 = number2.intValue();
        }
        return encode(str, barcodeFormat, i, i2, map2, i3, i4);
    }

    private static BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Charset charset, int i3, int i4) {
        if (barcodeFormat == BarcodeFormat.AZTEC) {
            return renderResult(Encoder.encode(str.getBytes(charset), i3, i4), i, i2);
        }
        i = new StringBuilder();
        i.append("Can only encode AZTEC, but got ");
        i.append(barcodeFormat);
        throw new IllegalArgumentException(i.toString());
    }

    private static BitMatrix renderResult(AztecCode aztecCode, int i, int i2) {
        aztecCode = aztecCode.getMatrix();
        if (aztecCode == null) {
            throw new IllegalStateException();
        }
        int width = aztecCode.getWidth();
        int height = aztecCode.getHeight();
        i = Math.max(i, width);
        i2 = Math.max(i2, height);
        int min = Math.min(i / width, i2 / height);
        int i3 = (i - (width * min)) / 2;
        int i4 = (i2 - (height * min)) / 2;
        BitMatrix bitMatrix = new BitMatrix(i, i2);
        i2 = 0;
        while (i2 < height) {
            int i5 = i3;
            int i6 = 0;
            while (i6 < width) {
                if (aztecCode.get(i6, i2)) {
                    bitMatrix.setRegion(i5, i4, min, min);
                }
                i6++;
                i5 += min;
            }
            i2++;
            i4 += min;
        }
        return bitMatrix;
    }
}
