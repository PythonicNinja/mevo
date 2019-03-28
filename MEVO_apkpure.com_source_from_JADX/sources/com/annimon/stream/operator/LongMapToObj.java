package com.annimon.stream.operator;

import com.annimon.stream.function.LongFunction;
import com.annimon.stream.iterator.LsaIterator;
import com.annimon.stream.iterator.PrimitiveIterator.OfLong;

public class LongMapToObj<R> extends LsaIterator<R> {
    private final OfLong iterator;
    private final LongFunction<? extends R> mapper;

    public LongMapToObj(OfLong ofLong, LongFunction<? extends R> longFunction) {
        this.iterator = ofLong;
        this.mapper = longFunction;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public R nextIteration() {
        return this.mapper.apply(this.iterator.nextLong());
    }
}
