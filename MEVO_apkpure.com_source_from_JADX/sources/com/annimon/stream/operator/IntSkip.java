package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator.OfInt;

public class IntSkip extends OfInt {
    private final OfInt iterator;
    /* renamed from: n */
    private final long f34n;
    private long skipped = null;

    public IntSkip(OfInt ofInt, long j) {
        this.iterator = ofInt;
        this.f34n = j;
    }

    public boolean hasNext() {
        while (this.iterator.hasNext()) {
            if (this.skipped == this.f34n) {
                break;
            }
            this.iterator.nextInt();
            this.skipped++;
        }
        return this.iterator.hasNext();
    }

    public int nextInt() {
        return this.iterator.nextInt();
    }
}
