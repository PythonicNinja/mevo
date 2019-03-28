package com.annimon.stream.operator;

import com.annimon.stream.function.IntConsumer;
import com.annimon.stream.iterator.PrimitiveIterator.OfInt;

public class IntPeek extends OfInt {
    private final IntConsumer action;
    private final OfInt iterator;

    public IntPeek(OfInt ofInt, IntConsumer intConsumer) {
        this.iterator = ofInt;
        this.action = intConsumer;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public int nextInt() {
        int nextInt = this.iterator.nextInt();
        this.action.accept(nextInt);
        return nextInt;
    }
}
