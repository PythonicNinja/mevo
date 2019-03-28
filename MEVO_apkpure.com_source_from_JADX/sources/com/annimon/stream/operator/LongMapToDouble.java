package com.annimon.stream.operator;

import com.annimon.stream.function.LongToDoubleFunction;
import com.annimon.stream.iterator.PrimitiveIterator.OfDouble;
import com.annimon.stream.iterator.PrimitiveIterator.OfLong;

public class LongMapToDouble extends OfDouble {
    private final OfLong iterator;
    private final LongToDoubleFunction mapper;

    public LongMapToDouble(OfLong ofLong, LongToDoubleFunction longToDoubleFunction) {
        this.iterator = ofLong;
        this.mapper = longToDoubleFunction;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public double nextDouble() {
        return this.mapper.applyAsDouble(this.iterator.nextLong());
    }
}
