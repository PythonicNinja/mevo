package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;

final class DecodedNumeric extends DecodedObject {
    static final int FNC1 = 10;
    private final int firstDigit;
    private final int secondDigit;

    DecodedNumeric(int i, int i2, int i3) throws FormatException {
        super(i);
        if (i2 >= 0 && i2 <= 10 && i3 >= 0) {
            if (i3 <= 10) {
                this.firstDigit = i2;
                this.secondDigit = i3;
                return;
            }
        }
        throw FormatException.getFormatInstance();
    }

    int getFirstDigit() {
        return this.firstDigit;
    }

    int getSecondDigit() {
        return this.secondDigit;
    }

    int getValue() {
        return (this.firstDigit * 10) + this.secondDigit;
    }

    boolean isFirstDigitFNC1() {
        return this.firstDigit == 10;
    }

    boolean isSecondDigitFNC1() {
        return this.secondDigit == 10;
    }

    boolean isAnyFNC1() {
        if (this.firstDigit != 10) {
            if (this.secondDigit != 10) {
                return false;
            }
        }
        return true;
    }
}
