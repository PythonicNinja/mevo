package com.google.zxing.pdf417.decoder.ec;

import com.google.zxing.ChecksumException;

public final class ErrorCorrection {
    private final ModulusGF field = ModulusGF.PDF417_GF;

    public int decode(int[] iArr, int i, int[] iArr2) throws ChecksumException {
        ModulusPoly modulusPoly = new ModulusPoly(this.field, iArr);
        int[] iArr3 = new int[i];
        int i2 = 0;
        Object obj = null;
        for (int i3 = i; i3 > 0; i3--) {
            int evaluateAt = modulusPoly.evaluateAt(this.field.exp(i3));
            iArr3[i - i3] = evaluateAt;
            if (evaluateAt != 0) {
                obj = 1;
            }
        }
        if (obj == null) {
            return 0;
        }
        modulusPoly = this.field.getOne();
        if (iArr2 != null) {
            ModulusPoly modulusPoly2 = modulusPoly;
            for (int evaluateAt2 : iArr2) {
                evaluateAt2 = this.field.exp((iArr.length - 1) - evaluateAt2);
                modulusPoly2 = modulusPoly2.multiply(new ModulusPoly(this.field, new int[]{this.field.subtract(0, evaluateAt2), 1}));
            }
        }
        i = runEuclideanAlgorithm(this.field.buildMonomial(i, 1), new ModulusPoly(this.field, iArr3), i);
        iArr2 = i[0];
        i = i[1];
        int[] findErrorLocations = findErrorLocations(iArr2);
        i = findErrorMagnitudes(i, iArr2, findErrorLocations);
        while (i2 < findErrorLocations.length) {
            iArr2 = (iArr.length - 1) - this.field.log(findErrorLocations[i2]);
            if (iArr2 < null) {
                throw ChecksumException.getChecksumInstance();
            }
            iArr[iArr2] = this.field.subtract(iArr[iArr2], i[i2]);
            i2++;
        }
        return findErrorLocations.length;
    }

    private ModulusPoly[] runEuclideanAlgorithm(ModulusPoly modulusPoly, ModulusPoly modulusPoly2, int i) throws ChecksumException {
        ModulusPoly modulusPoly3;
        if (modulusPoly.getDegree() < modulusPoly2.getDegree()) {
            modulusPoly3 = modulusPoly2;
            modulusPoly2 = modulusPoly;
            modulusPoly = modulusPoly3;
        }
        ModulusPoly zero = this.field.getZero();
        ModulusPoly one = this.field.getOne();
        modulusPoly3 = modulusPoly2;
        modulusPoly2 = modulusPoly;
        modulusPoly = modulusPoly3;
        while (modulusPoly.getDegree() >= i / 2) {
            if (modulusPoly.isZero()) {
                throw ChecksumException.getChecksumInstance();
            }
            ModulusPoly zero2 = this.field.getZero();
            int inverse = this.field.inverse(modulusPoly.getCoefficient(modulusPoly.getDegree()));
            while (modulusPoly2.getDegree() >= modulusPoly.getDegree() && !modulusPoly2.isZero()) {
                int degree = modulusPoly2.getDegree() - modulusPoly.getDegree();
                int multiply = this.field.multiply(modulusPoly2.getCoefficient(modulusPoly2.getDegree()), inverse);
                zero2 = zero2.add(this.field.buildMonomial(degree, multiply));
                modulusPoly2 = modulusPoly2.subtract(modulusPoly.multiplyByMonomial(degree, multiply));
            }
            modulusPoly3 = modulusPoly2;
            modulusPoly2 = modulusPoly;
            modulusPoly = modulusPoly3;
            ModulusPoly modulusPoly4 = one;
            one = zero2.multiply(one).subtract(zero).negative();
            zero = modulusPoly4;
        }
        i = one.getCoefficient(0);
        if (i == 0) {
            throw ChecksumException.getChecksumInstance();
        }
        i = this.field.inverse(i);
        zero = one.multiply(i);
        modulusPoly = modulusPoly.multiply(i);
        return new ModulusPoly[]{zero, modulusPoly};
    }

    private int[] findErrorLocations(ModulusPoly modulusPoly) throws ChecksumException {
        int degree = modulusPoly.getDegree();
        int[] iArr = new int[degree];
        int i = 0;
        for (int i2 = 1; i2 < this.field.getSize() && i < degree; i2++) {
            if (modulusPoly.evaluateAt(i2) == 0) {
                iArr[i] = this.field.inverse(i2);
                i++;
            }
        }
        if (i == degree) {
            return iArr;
        }
        throw ChecksumException.getChecksumInstance();
    }

    private int[] findErrorMagnitudes(ModulusPoly modulusPoly, ModulusPoly modulusPoly2, int[] iArr) {
        int degree = modulusPoly2.getDegree();
        int[] iArr2 = new int[degree];
        for (int i = 1; i <= degree; i++) {
            iArr2[degree - i] = this.field.multiply(i, modulusPoly2.getCoefficient(i));
        }
        modulusPoly2 = new ModulusPoly(this.field, iArr2);
        degree = iArr.length;
        iArr2 = new int[degree];
        for (int i2 = 0; i2 < degree; i2++) {
            int inverse = this.field.inverse(iArr[i2]);
            iArr2[i2] = this.field.multiply(this.field.subtract(0, modulusPoly.evaluateAt(inverse)), this.field.inverse(modulusPoly2.evaluateAt(inverse)));
        }
        return iArr2;
    }
}
