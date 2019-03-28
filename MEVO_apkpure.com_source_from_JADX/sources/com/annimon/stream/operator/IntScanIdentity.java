package com.annimon.stream.operator;

import com.annimon.stream.function.IntBinaryOperator;
import com.annimon.stream.iterator.PrimitiveExtIterator.OfInt;
import com.annimon.stream.iterator.PrimitiveIterator;

public class IntScanIdentity extends OfInt {
    private final IntBinaryOperator accumulator;
    private final int identity;
    private final PrimitiveIterator.OfInt iterator;

    public IntScanIdentity(PrimitiveIterator.OfInt ofInt, int i, IntBinaryOperator intBinaryOperator) {
        this.iterator = ofInt;
        this.identity = i;
        this.accumulator = intBinaryOperator;
    }

    protected void nextIteration() {
        if (this.isInit) {
            this.hasNext = this.iterator.hasNext();
            if (this.hasNext) {
                this.next = this.accumulator.applyAsInt(this.next, this.iterator.next().intValue());
            }
            return;
        }
        this.hasNext = true;
        this.next = this.identity;
    }
}
