package com.annimon.stream.operator;

import com.annimon.stream.function.IntToLongFunction;
import com.annimon.stream.iterator.PrimitiveIterator.OfInt;
import com.annimon.stream.iterator.PrimitiveIterator.OfLong;

public class IntMapToLong extends OfLong {
    private final OfInt iterator;
    private final IntToLongFunction mapper;

    public IntMapToLong(OfInt ofInt, IntToLongFunction intToLongFunction) {
        this.iterator = ofInt;
        this.mapper = intToLongFunction;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public long nextLong() {
        return this.mapper.applyAsLong(this.iterator.nextInt());
    }
}
