package com.annimon.stream.operator;

import com.annimon.stream.function.DoublePredicate;
import com.annimon.stream.iterator.PrimitiveExtIterator.OfDouble;
import com.annimon.stream.iterator.PrimitiveIterator;

public class DoubleTakeWhile extends OfDouble {
    private final PrimitiveIterator.OfDouble iterator;
    private final DoublePredicate predicate;

    public DoubleTakeWhile(PrimitiveIterator.OfDouble ofDouble, DoublePredicate doublePredicate) {
        this.iterator = ofDouble;
        this.predicate = doublePredicate;
    }

    protected void nextIteration() {
        boolean z;
        if (this.iterator.hasNext()) {
            DoublePredicate doublePredicate = this.predicate;
            double doubleValue = this.iterator.next().doubleValue();
            this.next = doubleValue;
            if (doublePredicate.test(doubleValue)) {
                z = true;
                this.hasNext = z;
            }
        }
        z = false;
        this.hasNext = z;
    }
}
