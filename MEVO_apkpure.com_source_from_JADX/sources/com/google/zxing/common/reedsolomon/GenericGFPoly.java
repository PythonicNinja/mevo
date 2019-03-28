package com.google.zxing.common.reedsolomon;

final class GenericGFPoly {
    private final int[] coefficients;
    private final GenericGF field;

    GenericGFPoly(GenericGF genericGF, int[] iArr) {
        if (iArr.length == 0) {
            throw new IllegalArgumentException();
        }
        this.field = genericGF;
        genericGF = iArr.length;
        if (genericGF <= 1 || iArr[0] != 0) {
            this.coefficients = iArr;
            return;
        }
        int i = 1;
        while (i < genericGF && iArr[i] == 0) {
            i++;
        }
        if (i == genericGF) {
            this.coefficients = new int[]{null};
            return;
        }
        this.coefficients = new int[(genericGF - i)];
        System.arraycopy(iArr, i, this.coefficients, 0, this.coefficients.length);
    }

    int[] getCoefficients() {
        return this.coefficients;
    }

    int getDegree() {
        return this.coefficients.length - 1;
    }

    boolean isZero() {
        return this.coefficients[0] == 0;
    }

    int getCoefficient(int i) {
        return this.coefficients[(this.coefficients.length - 1) - i];
    }

    int evaluateAt(int i) {
        int i2 = 0;
        if (i == 0) {
            return getCoefficient(0);
        }
        int length = this.coefficients.length;
        int i3 = 1;
        if (i == 1) {
            i = this.coefficients;
            length = i.length;
            i3 = 0;
            while (i2 < length) {
                i3 = GenericGF.addOrSubtract(i3, i[i2]);
                i2++;
            }
            return i3;
        }
        i2 = this.coefficients[0];
        while (i3 < length) {
            i2 = GenericGF.addOrSubtract(this.field.multiply(i, i2), this.coefficients[i3]);
            i3++;
        }
        return i2;
    }

    GenericGFPoly addOrSubtract(GenericGFPoly genericGFPoly) {
        if (!this.field.equals(genericGFPoly.field)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (isZero()) {
            return genericGFPoly;
        } else {
            if (genericGFPoly.isZero()) {
                return this;
            }
            int[] iArr = this.coefficients;
            genericGFPoly = genericGFPoly.coefficients;
            if (iArr.length > genericGFPoly.length) {
                int[] iArr2 = iArr;
                iArr = genericGFPoly;
                genericGFPoly = iArr2;
            }
            Object obj = new int[genericGFPoly.length];
            int length = genericGFPoly.length - iArr.length;
            System.arraycopy(genericGFPoly, 0, obj, 0, length);
            for (int i = length; i < genericGFPoly.length; i++) {
                obj[i] = GenericGF.addOrSubtract(iArr[i - length], genericGFPoly[i]);
            }
            return new GenericGFPoly(this.field, obj);
        }
    }

    GenericGFPoly multiply(GenericGFPoly genericGFPoly) {
        if (this.field.equals(genericGFPoly.field)) {
            if (!isZero()) {
                if (!genericGFPoly.isZero()) {
                    int[] iArr = this.coefficients;
                    int length = iArr.length;
                    genericGFPoly = genericGFPoly.coefficients;
                    int length2 = genericGFPoly.length;
                    int[] iArr2 = new int[((length + length2) - 1)];
                    for (int i = 0; i < length; i++) {
                        int i2 = iArr[i];
                        for (int i3 = 0; i3 < length2; i3++) {
                            int i4 = i + i3;
                            iArr2[i4] = GenericGF.addOrSubtract(iArr2[i4], this.field.multiply(i2, genericGFPoly[i3]));
                        }
                    }
                    return new GenericGFPoly(this.field, iArr2);
                }
            }
            return this.field.getZero();
        }
        throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
    }

    GenericGFPoly multiply(int i) {
        if (i == 0) {
            return this.field.getZero();
        }
        if (i == 1) {
            return this;
        }
        int length = this.coefficients.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = this.field.multiply(this.coefficients[i2], i);
        }
        return new GenericGFPoly(this.field, iArr);
    }

    GenericGFPoly multiplyByMonomial(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.field.getZero();
        } else {
            int length = this.coefficients.length;
            i = new int[(i + length)];
            for (int i3 = 0; i3 < length; i3++) {
                i[i3] = this.field.multiply(this.coefficients[i3], i2);
            }
            return new GenericGFPoly(this.field, i);
        }
    }

    GenericGFPoly[] divide(GenericGFPoly genericGFPoly) {
        if (!this.field.equals(genericGFPoly.field)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (genericGFPoly.isZero()) {
            throw new IllegalArgumentException("Divide by 0");
        } else {
            GenericGFPoly zero = this.field.getZero();
            int inverse = this.field.inverse(genericGFPoly.getCoefficient(genericGFPoly.getDegree()));
            GenericGFPoly genericGFPoly2 = zero;
            zero = this;
            while (zero.getDegree() >= genericGFPoly.getDegree() && !zero.isZero()) {
                int degree = zero.getDegree() - genericGFPoly.getDegree();
                int multiply = this.field.multiply(zero.getCoefficient(zero.getDegree()), inverse);
                GenericGFPoly multiplyByMonomial = genericGFPoly.multiplyByMonomial(degree, multiply);
                genericGFPoly2 = genericGFPoly2.addOrSubtract(this.field.buildMonomial(degree, multiply));
                zero = zero.addOrSubtract(multiplyByMonomial);
            }
            return new GenericGFPoly[]{genericGFPoly2, zero};
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(getDegree() * 8);
        for (int degree = getDegree(); degree >= 0; degree--) {
            int coefficient = getCoefficient(degree);
            if (coefficient != 0) {
                if (coefficient < 0) {
                    stringBuilder.append(" - ");
                    coefficient = -coefficient;
                } else if (stringBuilder.length() > 0) {
                    stringBuilder.append(" + ");
                }
                if (degree == 0 || coefficient != 1) {
                    coefficient = this.field.log(coefficient);
                    if (coefficient == 0) {
                        stringBuilder.append('1');
                    } else if (coefficient == 1) {
                        stringBuilder.append('a');
                    } else {
                        stringBuilder.append("a^");
                        stringBuilder.append(coefficient);
                    }
                }
                if (degree != 0) {
                    if (degree == 1) {
                        stringBuilder.append('x');
                    } else {
                        stringBuilder.append("x^");
                        stringBuilder.append(degree);
                    }
                }
            }
        }
        return stringBuilder.toString();
    }
}
