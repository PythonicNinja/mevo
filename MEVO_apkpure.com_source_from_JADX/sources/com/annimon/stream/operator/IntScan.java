package com.annimon.stream.operator;

import com.annimon.stream.function.IntBinaryOperator;
import com.annimon.stream.iterator.PrimitiveExtIterator.OfInt;
import com.annimon.stream.iterator.PrimitiveIterator;

public class IntScan extends OfInt {
    private final IntBinaryOperator accumulator;
    private final PrimitiveIterator.OfInt iterator;

    public IntScan(PrimitiveIterator.OfInt ofInt, IntBinaryOperator intBinaryOperator) {
        this.iterator = ofInt;
        this.accumulator = intBinaryOperator;
    }

    protected void nextIteration() {
        this.hasNext = this.iterator.hasNext();
        if (this.hasNext) {
            int intValue = this.iterator.next().intValue();
            if (this.isInit) {
                this.next = this.accumulator.applyAsInt(this.next, intValue);
            } else {
                this.next = intValue;
            }
        }
    }
}
