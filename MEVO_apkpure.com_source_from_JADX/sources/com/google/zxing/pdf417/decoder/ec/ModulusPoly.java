package com.google.zxing.pdf417.decoder.ec;

final class ModulusPoly {
    private final int[] coefficients;
    private final ModulusGF field;

    ModulusPoly(ModulusGF modulusGF, int[] iArr) {
        if (iArr.length == 0) {
            throw new IllegalArgumentException();
        }
        this.field = modulusGF;
        modulusGF = iArr.length;
        if (modulusGF <= 1 || iArr[0] != 0) {
            this.coefficients = iArr;
            return;
        }
        int i = 1;
        while (i < modulusGF && iArr[i] == 0) {
            i++;
        }
        if (i == modulusGF) {
            this.coefficients = new int[]{null};
            return;
        }
        this.coefficients = new int[(modulusGF - i)];
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
                i3 = this.field.add(i3, i[i2]);
                i2++;
            }
            return i3;
        }
        i2 = this.coefficients[0];
        while (i3 < length) {
            i2 = this.field.add(this.field.multiply(i, i2), this.coefficients[i3]);
            i3++;
        }
        return i2;
    }

    ModulusPoly add(ModulusPoly modulusPoly) {
        if (!this.field.equals(modulusPoly.field)) {
            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
        } else if (isZero()) {
            return modulusPoly;
        } else {
            if (modulusPoly.isZero()) {
                return this;
            }
            int[] iArr = this.coefficients;
            modulusPoly = modulusPoly.coefficients;
            if (iArr.length > modulusPoly.length) {
                int[] iArr2 = iArr;
                iArr = modulusPoly;
                modulusPoly = iArr2;
            }
            Object obj = new int[modulusPoly.length];
            int length = modulusPoly.length - iArr.length;
            System.arraycopy(modulusPoly, 0, obj, 0, length);
            for (int i = length; i < modulusPoly.length; i++) {
                obj[i] = this.field.add(iArr[i - length], modulusPoly[i]);
            }
            return new ModulusPoly(this.field, obj);
        }
    }

    ModulusPoly subtract(ModulusPoly modulusPoly) {
        if (!this.field.equals(modulusPoly.field)) {
            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
        } else if (modulusPoly.isZero()) {
            return this;
        } else {
            return add(modulusPoly.negative());
        }
    }

    ModulusPoly multiply(ModulusPoly modulusPoly) {
        if (this.field.equals(modulusPoly.field)) {
            if (!isZero()) {
                if (!modulusPoly.isZero()) {
                    int[] iArr = this.coefficients;
                    int length = iArr.length;
                    modulusPoly = modulusPoly.coefficients;
                    int length2 = modulusPoly.length;
                    int[] iArr2 = new int[((length + length2) - 1)];
                    for (int i = 0; i < length; i++) {
                        int i2 = iArr[i];
                        for (int i3 = 0; i3 < length2; i3++) {
                            int i4 = i + i3;
                            iArr2[i4] = this.field.add(iArr2[i4], this.field.multiply(i2, modulusPoly[i3]));
                        }
                    }
                    return new ModulusPoly(this.field, iArr2);
                }
            }
            return this.field.getZero();
        }
        throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
    }

    ModulusPoly negative() {
        int length = this.coefficients.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = this.field.subtract(0, this.coefficients[i]);
        }
        return new ModulusPoly(this.field, iArr);
    }

    ModulusPoly multiply(int i) {
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
        return new ModulusPoly(this.field, iArr);
    }

    ModulusPoly multiplyByMonomial(int i, int i2) {
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
            return new ModulusPoly(this.field, i);
        }
    }

    ModulusPoly[] divide(ModulusPoly modulusPoly) {
        if (!this.field.equals(modulusPoly.field)) {
            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
        } else if (modulusPoly.isZero()) {
            throw new IllegalArgumentException("Divide by 0");
        } else {
            ModulusPoly zero = this.field.getZero();
            int inverse = this.field.inverse(modulusPoly.getCoefficient(modulusPoly.getDegree()));
            ModulusPoly modulusPoly2 = zero;
            zero = this;
            while (zero.getDegree() >= modulusPoly.getDegree() && !zero.isZero()) {
                int degree = zero.getDegree() - modulusPoly.getDegree();
                int multiply = this.field.multiply(zero.getCoefficient(zero.getDegree()), inverse);
                ModulusPoly multiplyByMonomial = modulusPoly.multiplyByMonomial(degree, multiply);
                modulusPoly2 = modulusPoly2.add(this.field.buildMonomial(degree, multiply));
                zero = zero.subtract(multiplyByMonomial);
            }
            return new ModulusPoly[]{modulusPoly2, zero};
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
                    stringBuilder.append(coefficient);
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
