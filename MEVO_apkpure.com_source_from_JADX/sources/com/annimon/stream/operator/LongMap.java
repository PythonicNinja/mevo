package com.annimon.stream.operator;

import com.annimon.stream.function.LongUnaryOperator;
import com.annimon.stream.iterator.PrimitiveIterator.OfLong;

public class LongMap extends OfLong {
    private final OfLong iterator;
    private final LongUnaryOperator mapper;

    public LongMap(OfLong ofLong, LongUnaryOperator longUnaryOperator) {
        this.iterator = ofLong;
        this.mapper = longUnaryOperator;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public long nextLong() {
        return this.mapper.applyAsLong(this.iterator.nextLong());
    }
}
