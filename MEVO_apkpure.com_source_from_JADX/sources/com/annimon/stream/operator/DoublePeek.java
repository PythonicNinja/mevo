package com.annimon.stream.operator;

import com.annimon.stream.function.DoubleConsumer;
import com.annimon.stream.iterator.PrimitiveIterator.OfDouble;

public class DoublePeek extends OfDouble {
    private final DoubleConsumer action;
    private final OfDouble iterator;

    public DoublePeek(OfDouble ofDouble, DoubleConsumer doubleConsumer) {
        this.iterator = ofDouble;
        this.action = doubleConsumer;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public double nextDouble() {
        double nextDouble = this.iterator.nextDouble();
        this.action.accept(nextDouble);
        return nextDouble;
    }
}
