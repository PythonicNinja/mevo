package com.annimon.stream.operator;

import com.annimon.stream.function.LongBinaryOperator;
import com.annimon.stream.iterator.PrimitiveExtIterator.OfLong;
import com.annimon.stream.iterator.PrimitiveIterator;

public class LongScanIdentity extends OfLong {
    private final LongBinaryOperator accumulator;
    private final long identity;
    private final PrimitiveIterator.OfLong iterator;

    public LongScanIdentity(PrimitiveIterator.OfLong ofLong, long j, LongBinaryOperator longBinaryOperator) {
        this.iterator = ofLong;
        this.identity = j;
        this.accumulator = longBinaryOperator;
    }

    protected void nextIteration() {
        if (this.isInit) {
            this.hasNext = this.iterator.hasNext();
            if (this.hasNext) {
                this.next = this.accumulator.applyAsLong(this.next, this.iterator.next().longValue());
            }
            return;
        }
        this.hasNext = true;
        this.next = this.identity;
    }
}
