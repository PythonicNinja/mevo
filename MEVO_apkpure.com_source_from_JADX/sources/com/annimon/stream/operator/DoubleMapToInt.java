package com.annimon.stream.operator;

import com.annimon.stream.function.DoubleToIntFunction;
import com.annimon.stream.iterator.PrimitiveIterator.OfDouble;
import com.annimon.stream.iterator.PrimitiveIterator.OfInt;

public class DoubleMapToInt extends OfInt {
    private final OfDouble iterator;
    private final DoubleToIntFunction mapper;

    public DoubleMapToInt(OfDouble ofDouble, DoubleToIntFunction doubleToIntFunction) {
        this.iterator = ofDouble;
        this.mapper = doubleToIntFunction;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public int nextInt() {
        return this.mapper.applyAsInt(this.iterator.nextDouble());
    }
}
