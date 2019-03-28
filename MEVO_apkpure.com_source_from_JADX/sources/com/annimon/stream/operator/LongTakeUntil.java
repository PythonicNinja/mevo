package com.annimon.stream.operator;

import com.annimon.stream.function.LongPredicate;
import com.annimon.stream.iterator.PrimitiveExtIterator.OfLong;
import com.annimon.stream.iterator.PrimitiveIterator;

public class LongTakeUntil extends OfLong {
    private final PrimitiveIterator.OfLong iterator;
    private final LongPredicate stopPredicate;

    public LongTakeUntil(PrimitiveIterator.OfLong ofLong, LongPredicate longPredicate) {
        this.iterator = ofLong;
        this.stopPredicate = longPredicate;
    }

    protected void nextIteration() {
        boolean z = this.iterator.hasNext() && !(this.isInit && this.stopPredicate.test(this.next));
        this.hasNext = z;
        if (this.hasNext) {
            this.next = this.iterator.next().longValue();
        }
    }
}
