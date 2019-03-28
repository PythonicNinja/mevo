package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator.OfLong;

public class LongRangeClosed extends OfLong {
    private long current;
    private final long endInclusive;
    private boolean hasNext;

    public LongRangeClosed(long j, long j2) {
        this.endInclusive = j2;
        this.current = j;
        this.hasNext = this.current <= j2 ? 1 : null;
    }

    public boolean hasNext() {
        return this.hasNext;
    }

    public long nextLong() {
        if (this.current >= this.endInclusive) {
            this.hasNext = false;
            return this.endInclusive;
        }
        long j = this.current;
        this.current = j + 1;
        return j;
    }
}
