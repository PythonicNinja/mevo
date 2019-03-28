package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator.OfLong;

public class LongLimit extends OfLong {
    private long index = null;
    private final OfLong iterator;
    private final long maxSize;

    public LongLimit(OfLong ofLong, long j) {
        this.iterator = ofLong;
        this.maxSize = j;
    }

    public boolean hasNext() {
        return this.index < this.maxSize && this.iterator.hasNext();
    }

    public long nextLong() {
        this.index++;
        return this.iterator.nextLong();
    }
}
