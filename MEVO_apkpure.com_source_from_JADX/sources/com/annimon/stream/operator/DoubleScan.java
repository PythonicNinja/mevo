package com.annimon.stream.operator;

import com.annimon.stream.function.DoubleBinaryOperator;
import com.annimon.stream.iterator.PrimitiveExtIterator.OfDouble;
import com.annimon.stream.iterator.PrimitiveIterator;

public class DoubleScan extends OfDouble {
    private final DoubleBinaryOperator accumulator;
    private final PrimitiveIterator.OfDouble iterator;

    public DoubleScan(PrimitiveIterator.OfDouble ofDouble, DoubleBinaryOperator doubleBinaryOperator) {
        this.iterator = ofDouble;
        this.accumulator = doubleBinaryOperator;
    }

    protected void nextIteration() {
        this.hasNext = this.iterator.hasNext();
        if (this.hasNext) {
            double nextDouble = this.iterator.nextDouble();
            if (this.isInit) {
                this.next = this.accumulator.applyAsDouble(this.next, nextDouble);
            } else {
                this.next = nextDouble;
            }
        }
    }
}
