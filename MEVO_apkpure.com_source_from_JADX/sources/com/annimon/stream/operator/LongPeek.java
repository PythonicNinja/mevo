package com.annimon.stream.operator;

import com.annimon.stream.function.LongConsumer;
import com.annimon.stream.iterator.PrimitiveIterator.OfLong;

public class LongPeek extends OfLong {
    private final LongConsumer action;
    private final OfLong iterator;

    public LongPeek(OfLong ofLong, LongConsumer longConsumer) {
        this.iterator = ofLong;
        this.action = longConsumer;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public long nextLong() {
        long nextLong = this.iterator.nextLong();
        this.action.accept(nextLong);
        return nextLong;
    }
}
