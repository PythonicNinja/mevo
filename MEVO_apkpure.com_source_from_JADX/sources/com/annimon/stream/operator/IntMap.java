package com.annimon.stream.operator;

import com.annimon.stream.function.IntUnaryOperator;
import com.annimon.stream.iterator.PrimitiveIterator.OfInt;

public class IntMap extends OfInt {
    private final OfInt iterator;
    private final IntUnaryOperator mapper;

    public IntMap(OfInt ofInt, IntUnaryOperator intUnaryOperator) {
        this.iterator = ofInt;
        this.mapper = intUnaryOperator;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public int nextInt() {
        return this.mapper.applyAsInt(this.iterator.nextInt());
    }
}
