package com.annimon.stream.operator;

import com.annimon.stream.function.DoubleBinaryOperator;
import com.annimon.stream.iterator.PrimitiveExtIterator.OfDouble;
import com.annimon.stream.iterator.PrimitiveIterator;

public class DoubleScanIdentity extends OfDouble {
    private final DoubleBinaryOperator accumulator;
    private final double identity;
    private final PrimitiveIterator.OfDouble iterator;

    public DoubleScanIdentity(PrimitiveIterator.OfDouble ofDouble, double d, DoubleBinaryOperator doubleBinaryOperator) {
        this.iterator = ofDouble;
        this.identity = d;
        this.accumulator = doubleBinaryOperator;
    }

    protected void nextIteration() {
        if (this.isInit) {
            this.hasNext = this.iterator.hasNext();
            if (this.hasNext) {
                this.next = this.accumulator.applyAsDouble(this.next, this.iterator.next().doubleValue());
            }
            return;
        }
        this.hasNext = true;
        this.next = this.identity;
    }
}
