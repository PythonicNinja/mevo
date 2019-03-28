package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator.OfDouble;

public class DoubleSkip extends OfDouble {
    private final OfDouble iterator;
    /* renamed from: n */
    private final long f33n;
    private long skipped = null;

    public DoubleSkip(OfDouble ofDouble, long j) {
        this.iterator = ofDouble;
        this.f33n = j;
    }

    public boolean hasNext() {
        while (this.iterator.hasNext()) {
            if (this.skipped == this.f33n) {
                break;
            }
            this.iterator.nextDouble();
            this.skipped++;
        }
        return this.iterator.hasNext();
    }

    public double nextDouble() {
        return this.iterator.nextDouble();
    }
}
