package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator.OfDouble;

public class DoubleLimit extends OfDouble {
    private long index = null;
    private final OfDouble iterator;
    private final long maxSize;

    public DoubleLimit(OfDouble ofDouble, long j) {
        this.iterator = ofDouble;
        this.maxSize = j;
    }

    public boolean hasNext() {
        return this.index < this.maxSize && this.iterator.hasNext();
    }

    public double nextDouble() {
        this.index++;
        return this.iterator.nextDouble();
    }
}
