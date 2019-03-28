package com.annimon.stream.operator;

import com.annimon.stream.function.LongBinaryOperator;
import com.annimon.stream.iterator.PrimitiveExtIterator.OfLong;
import com.annimon.stream.iterator.PrimitiveIterator;

public class LongScan extends OfLong {
    private final LongBinaryOperator accumulator;
    private final PrimitiveIterator.OfLong iterator;

    public LongScan(PrimitiveIterator.OfLong ofLong, LongBinaryOperator longBinaryOperator) {
        this.iterator = ofLong;
        this.accumulator = longBinaryOperator;
    }

    protected void nextIteration() {
        this.hasNext = this.iterator.hasNext();
        if (this.hasNext) {
            long nextLong = this.iterator.nextLong();
            if (this.isInit) {
                this.next = this.accumulator.applyAsLong(this.next, nextLong);
            } else {
                this.next = nextLong;
            }
        }
    }
}
