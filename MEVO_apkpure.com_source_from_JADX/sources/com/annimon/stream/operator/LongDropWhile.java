package com.annimon.stream.operator;

import com.annimon.stream.function.LongPredicate;
import com.annimon.stream.iterator.PrimitiveExtIterator.OfLong;
import com.annimon.stream.iterator.PrimitiveIterator;

public class LongDropWhile extends OfLong {
    private final PrimitiveIterator.OfLong iterator;
    private final LongPredicate predicate;

    public LongDropWhile(PrimitiveIterator.OfLong ofLong, LongPredicate longPredicate) {
        this.iterator = ofLong;
        this.predicate = longPredicate;
    }

    protected void nextIteration() {
        boolean hasNext;
        if (!this.isInit) {
            do {
                hasNext = this.iterator.hasNext();
                this.hasNext = hasNext;
                if (hasNext) {
                    this.next = this.iterator.next().longValue();
                }
            } while (this.predicate.test(this.next));
            return;
        }
        hasNext = this.hasNext && this.iterator.hasNext();
        this.hasNext = hasNext;
        if (this.hasNext) {
            this.next = this.iterator.next().longValue();
        }
    }
}
