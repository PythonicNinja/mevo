package com.annimon.stream.operator;

import com.annimon.stream.function.DoubleFunction;
import com.annimon.stream.iterator.LsaIterator;
import com.annimon.stream.iterator.PrimitiveIterator.OfDouble;

public class DoubleMapToObj<R> extends LsaIterator<R> {
    private final OfDouble iterator;
    private final DoubleFunction<? extends R> mapper;

    public DoubleMapToObj(OfDouble ofDouble, DoubleFunction<? extends R> doubleFunction) {
        this.iterator = ofDouble;
        this.mapper = doubleFunction;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public R nextIteration() {
        return this.mapper.apply(this.iterator.nextDouble());
    }
}
