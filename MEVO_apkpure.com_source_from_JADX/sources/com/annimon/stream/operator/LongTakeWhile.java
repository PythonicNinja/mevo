package com.annimon.stream.operator;

import com.annimon.stream.function.LongPredicate;
import com.annimon.stream.iterator.PrimitiveExtIterator.OfLong;
import com.annimon.stream.iterator.PrimitiveIterator;

public class LongTakeWhile extends OfLong {
    private final PrimitiveIterator.OfLong iterator;
    private final LongPredicate predicate;

    public LongTakeWhile(PrimitiveIterator.OfLong ofLong, LongPredicate longPredicate) {
        this.iterator = ofLong;
        this.predicate = longPredicate;
    }

    protected void nextIteration() {
        boolean z;
        if (this.iterator.hasNext()) {
            LongPredicate longPredicate = this.predicate;
            long longValue = this.iterator.next().longValue();
            this.next = longValue;
            if (longPredicate.test(longValue)) {
                z = true;
                this.hasNext = z;
            }
        }
        z = false;
        this.hasNext = z;
    }
}
