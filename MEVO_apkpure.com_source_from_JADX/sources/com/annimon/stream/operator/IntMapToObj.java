package com.annimon.stream.operator;

import com.annimon.stream.function.IntFunction;
import com.annimon.stream.iterator.LsaIterator;
import com.annimon.stream.iterator.PrimitiveIterator.OfInt;

public class IntMapToObj<R> extends LsaIterator<R> {
    private final OfInt iterator;
    private final IntFunction<? extends R> mapper;

    public IntMapToObj(OfInt ofInt, IntFunction<? extends R> intFunction) {
        this.iterator = ofInt;
        this.mapper = intFunction;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public R nextIteration() {
        return this.mapper.apply(this.iterator.nextInt());
    }
}
