package com.annimon.stream.operator;

import com.annimon.stream.function.IntPredicate;
import com.annimon.stream.iterator.PrimitiveExtIterator.OfInt;
import com.annimon.stream.iterator.PrimitiveIterator;

public class IntTakeUntil extends OfInt {
    private final PrimitiveIterator.OfInt iterator;
    private final IntPredicate stopPredicate;

    public IntTakeUntil(PrimitiveIterator.OfInt ofInt, IntPredicate intPredicate) {
        this.iterator = ofInt;
        this.stopPredicate = intPredicate;
    }

    protected void nextIteration() {
        boolean z = this.iterator.hasNext() && !(this.isInit && this.stopPredicate.test(this.next));
        this.hasNext = z;
        if (this.hasNext) {
            this.next = this.iterator.next().intValue();
        }
    }
}
