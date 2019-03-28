package com.annimon.stream.operator;

import com.annimon.stream.function.LongSupplier;
import com.annimon.stream.iterator.PrimitiveIterator.OfLong;

public class LongGenerate extends OfLong {
    private final LongSupplier supplier;

    public boolean hasNext() {
        return true;
    }

    public LongGenerate(LongSupplier longSupplier) {
        this.supplier = longSupplier;
    }

    public long nextLong() {
        return this.supplier.getAsLong();
    }
}
