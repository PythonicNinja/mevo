package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator.OfLong;

public class LongSample extends OfLong {
    private final OfLong iterator;
    private final int stepWidth;

    public LongSample(OfLong ofLong, int i) {
        this.iterator = ofLong;
        this.stepWidth = i;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public long nextLong() {
        long nextLong = this.iterator.nextLong();
        for (int i = 1; i < this.stepWidth && this.iterator.hasNext(); i++) {
            this.iterator.nextLong();
        }
        return nextLong;
    }
}
