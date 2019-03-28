package com.annimon.stream.operator;

import com.annimon.stream.function.IntSupplier;
import com.annimon.stream.iterator.PrimitiveIterator.OfInt;

public class IntGenerate extends OfInt {
    private final IntSupplier supplier;

    public boolean hasNext() {
        return true;
    }

    public IntGenerate(IntSupplier intSupplier) {
        this.supplier = intSupplier;
    }

    public int nextInt() {
        return this.supplier.getAsInt();
    }
}
