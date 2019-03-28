package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator.OfInt;

public class IntLimit extends OfInt {
    private long index = null;
    private final OfInt iterator;
    private final long maxSize;

    public IntLimit(OfInt ofInt, long j) {
        this.iterator = ofInt;
        this.maxSize = j;
    }

    public boolean hasNext() {
        return this.index < this.maxSize && this.iterator.hasNext();
    }

    public int nextInt() {
        this.index++;
        return this.iterator.nextInt();
    }
}
