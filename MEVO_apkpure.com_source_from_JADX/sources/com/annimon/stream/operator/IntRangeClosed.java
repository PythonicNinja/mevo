package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator.OfInt;

public class IntRangeClosed extends OfInt {
    private int current;
    private final int endInclusive;
    private boolean hasNext;

    public IntRangeClosed(int i, int i2) {
        this.endInclusive = i2;
        this.current = i;
        this.hasNext = this.current <= i2 ? 1 : 0;
    }

    public boolean hasNext() {
        return this.hasNext;
    }

    public int nextInt() {
        if (this.current >= this.endInclusive) {
            this.hasNext = false;
            return this.endInclusive;
        }
        int i = this.current;
        this.current = i + 1;
        return i;
    }
}
