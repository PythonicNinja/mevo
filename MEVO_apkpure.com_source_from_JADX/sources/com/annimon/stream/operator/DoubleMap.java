package com.annimon.stream.operator;

import com.annimon.stream.function.DoubleUnaryOperator;
import com.annimon.stream.iterator.PrimitiveIterator.OfDouble;

public class DoubleMap extends OfDouble {
    private final OfDouble iterator;
    private final DoubleUnaryOperator mapper;

    public DoubleMap(OfDouble ofDouble, DoubleUnaryOperator doubleUnaryOperator) {
        this.iterator = ofDouble;
        this.mapper = doubleUnaryOperator;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public double nextDouble() {
        return this.mapper.applyAsDouble(this.iterator.nextDouble());
    }
}
