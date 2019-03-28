package com.annimon.stream.operator;

import com.annimon.stream.function.DoubleToLongFunction;
import com.annimon.stream.iterator.PrimitiveIterator.OfDouble;
import com.annimon.stream.iterator.PrimitiveIterator.OfLong;

public class DoubleMapToLong extends OfLong {
    private final OfDouble iterator;
    private final DoubleToLongFunction mapper;

    public DoubleMapToLong(OfDouble ofDouble, DoubleToLongFunction doubleToLongFunction) {
        this.iterator = ofDouble;
        this.mapper = doubleToLongFunction;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public long nextLong() {
        return this.mapper.applyAsLong(this.iterator.nextDouble());
    }
}
