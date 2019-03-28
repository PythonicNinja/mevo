package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator.OfLong;

public class LongSkip extends OfLong {
    private final OfLong iterator;
    /* renamed from: n */
    private final long f35n;
    private long skipped = null;

    public LongSkip(OfLong ofLong, long j) {
        this.iterator = ofLong;
        this.f35n = j;
    }

    public boolean hasNext() {
        while (this.iterator.hasNext()) {
            if (this.skipped == this.f35n) {
                break;
            }
            this.iterator.nextLong();
            this.skipped++;
        }
        return this.iterator.hasNext();
    }

    public long nextLong() {
        return this.iterator.nextLong();
    }
}
