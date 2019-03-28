package com.annimon.stream.operator;

import com.annimon.stream.function.DoublePredicate;
import com.annimon.stream.iterator.PrimitiveExtIterator.OfDouble;
import com.annimon.stream.iterator.PrimitiveIterator;

public class DoubleTakeUntil extends OfDouble {
    private final PrimitiveIterator.OfDouble iterator;
    private final DoublePredicate stopPredicate;

    public DoubleTakeUntil(PrimitiveIterator.OfDouble ofDouble, DoublePredicate doublePredicate) {
        this.iterator = ofDouble;
        this.stopPredicate = doublePredicate;
    }

    protected void nextIteration() {
        boolean z = this.iterator.hasNext() && !(this.isInit && this.stopPredicate.test(this.next));
        this.hasNext = z;
        if (this.hasNext) {
            this.next = this.iterator.next().doubleValue();
        }
    }
}
