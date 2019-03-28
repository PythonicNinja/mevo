package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator.OfInt;

public class IntSample extends OfInt {
    private final OfInt iterator;
    private final int stepWidth;

    public IntSample(OfInt ofInt, int i) {
        this.iterator = ofInt;
        this.stepWidth = i;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public int nextInt() {
        int nextInt = this.iterator.nextInt();
        for (int i = 1; i < this.stepWidth && this.iterator.hasNext(); i++) {
            this.iterator.nextInt();
        }
        return nextInt;
    }
}
