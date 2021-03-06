package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public abstract class OneDimensionalCodeWriter implements Writer {
    public abstract boolean[] encode(String str);

    public int getDefaultMargin() {
        return 10;
    }

    public final BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2) throws WriterException {
        return encode(str, barcodeFormat, i, i2, null);
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (str.isEmpty() != null) {
            throw new IllegalArgumentException("Found empty contents");
        }
        if (i >= 0) {
            if (i2 >= 0) {
                barcodeFormat = getDefaultMargin();
                if (map != null) {
                    Integer num = (Integer) map.get(EncodeHintType.MARGIN);
                    if (num != null) {
                        barcodeFormat = num.intValue();
                    }
                }
                return renderResult(encode(str), i, i2, barcodeFormat);
            }
        }
        barcodeFormat = new StringBuilder();
        barcodeFormat.append("Negative size is not allowed. Input: ");
        barcodeFormat.append(i);
        barcodeFormat.append(120);
        barcodeFormat.append(i2);
        throw new IllegalArgumentException(barcodeFormat.toString());
    }

    private static BitMatrix renderResult(boolean[] zArr, int i, int i2, int i3) {
        int length = zArr.length;
        i3 += length;
        i = Math.max(i, i3);
        i2 = Math.max(1, i2);
        i3 = i / i3;
        int i4 = (i - (length * i3)) / 2;
        BitMatrix bitMatrix = new BitMatrix(i, i2);
        int i5 = i4;
        i4 = 0;
        while (i4 < length) {
            if (zArr[i4]) {
                bitMatrix.setRegion(i5, 0, i3, i2);
            }
            i4++;
            i5 += i3;
        }
        return bitMatrix;
    }

    protected static int appendPattern(boolean[] zArr, int i, int[] iArr, boolean z) {
        int length = iArr.length;
        int i2 = i;
        boolean z2 = z;
        i = 0;
        z = false;
        while (i < length) {
            int i3 = iArr[i];
            int i4 = i2;
            i2 = 0;
            while (i2 < i3) {
                int i5 = i4 + 1;
                zArr[i4] = z2;
                i2++;
                i4 = i5;
            }
            z += i3;
            z2 = !z2;
            i++;
            i2 = i4;
        }
        return z;
    }
}
