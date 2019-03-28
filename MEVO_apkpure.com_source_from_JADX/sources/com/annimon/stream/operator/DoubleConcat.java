package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator.OfDouble;

public class DoubleConcat extends OfDouble {
    private boolean firstStreamIsCurrent = true;
    private final OfDouble iterator1;
    private final OfDouble iterator2;

    public DoubleConcat(OfDouble ofDouble, OfDouble ofDouble2) {
        this.iterator1 = ofDouble;
        this.iterator2 = ofDouble2;
    }

    public boolean hasNext() {
        if (this.firstStreamIsCurrent) {
            if (this.iterator1.hasNext()) {
                return true;
            }
            this.firstStreamIsCurrent = false;
        }
        return this.iterator2.hasNext();
    }

    public double nextDouble() {
        return (this.firstStreamIsCurrent ? this.iterator1 : this.iterator2).nextDouble();
    }
}
