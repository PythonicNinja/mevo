package com.annimon.stream.operator;

import com.annimon.stream.function.DoubleSupplier;
import com.annimon.stream.iterator.PrimitiveIterator.OfDouble;

public class DoubleGenerate extends OfDouble {
    private final DoubleSupplier supplier;

    public boolean hasNext() {
        return true;
    }

    public DoubleGenerate(DoubleSupplier doubleSupplier) {
        this.supplier = doubleSupplier;
    }

    public double nextDouble() {
        return this.supplier.getAsDouble();
    }
}
