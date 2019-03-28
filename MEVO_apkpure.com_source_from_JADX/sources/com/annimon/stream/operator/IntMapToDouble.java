package com.annimon.stream.operator;

import com.annimon.stream.function.IntToDoubleFunction;
import com.annimon.stream.iterator.PrimitiveIterator.OfDouble;
import com.annimon.stream.iterator.PrimitiveIterator.OfInt;

public class IntMapToDouble extends OfDouble {
    private final OfInt iterator;
    private final IntToDoubleFunction mapper;

    public IntMapToDouble(OfInt ofInt, IntToDoubleFunction intToDoubleFunction) {
        this.iterator = ofInt;
        this.mapper = intToDoubleFunction;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public double nextDouble() {
        return this.mapper.applyAsDouble(this.iterator.nextInt());
    }
}
