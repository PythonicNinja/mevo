package com.annimon.stream.operator;

import com.annimon.stream.function.DoublePredicate;
import com.annimon.stream.iterator.PrimitiveExtIterator.OfDouble;
import com.annimon.stream.iterator.PrimitiveIterator;

public class DoubleDropWhile extends OfDouble {
    private final PrimitiveIterator.OfDouble iterator;
    private final DoublePredicate predicate;

    public DoubleDropWhile(PrimitiveIterator.OfDouble ofDouble, DoublePredicate doublePredicate) {
        this.iterator = ofDouble;
        this.predicate = doublePredicate;
    }

    protected void nextIteration() {
        boolean hasNext;
        if (!this.isInit) {
            do {
                hasNext = this.iterator.hasNext();
                this.hasNext = hasNext;
                if (hasNext) {
                    this.next = this.iterator.next().doubleValue();
                }
            } while (this.predicate.test(this.next));
            return;
        }
        hasNext = this.hasNext && this.iterator.hasNext();
        this.hasNext = hasNext;
        if (this.hasNext) {
            this.next = this.iterator.next().doubleValue();
        }
    }
}
