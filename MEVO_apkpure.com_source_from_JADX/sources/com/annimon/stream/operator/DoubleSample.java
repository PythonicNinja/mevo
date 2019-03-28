package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator.OfDouble;

public class DoubleSample extends OfDouble {
    private final OfDouble iterator;
    private final int stepWidth;

    public DoubleSample(OfDouble ofDouble, int i) {
        this.iterator = ofDouble;
        this.stepWidth = i;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public double nextDouble() {
        double nextDouble = this.iterator.nextDouble();
        for (int i = 1; i < this.stepWidth && this.iterator.hasNext(); i++) {
            this.iterator.nextDouble();
        }
        return nextDouble;
    }
}
