package com.annimon.stream.operator;

import com.annimon.stream.function.IntPredicate;
import com.annimon.stream.iterator.PrimitiveExtIterator.OfInt;
import com.annimon.stream.iterator.PrimitiveIterator;

public class IntDropWhile extends OfInt {
    private final PrimitiveIterator.OfInt iterator;
    private final IntPredicate predicate;

    public IntDropWhile(PrimitiveIterator.OfInt ofInt, IntPredicate intPredicate) {
        this.iterator = ofInt;
        this.predicate = intPredicate;
    }

    protected void nextIteration() {
        boolean hasNext;
        if (!this.isInit) {
            do {
                hasNext = this.iterator.hasNext();
                this.hasNext = hasNext;
                if (hasNext) {
                    this.next = this.iterator.next().intValue();
                }
            } while (this.predicate.test(this.next));
            return;
        }
        hasNext = this.hasNext && this.iterator.hasNext();
        this.hasNext = hasNext;
        if (this.hasNext) {
            this.next = this.iterator.next().intValue();
        }
    }
}
