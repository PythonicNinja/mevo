package com.annimon.stream.operator;

import com.annimon.stream.function.IntUnaryOperator;
import com.annimon.stream.iterator.PrimitiveIterator.OfInt;

public class IntIterate extends OfInt {
    private int current;
    private final IntUnaryOperator op;

    public boolean hasNext() {
        return true;
    }

    public IntIterate(int i, IntUnaryOperator intUnaryOperator) {
        this.op = intUnaryOperator;
        this.current = i;
    }

    public int nextInt() {
        int i = this.current;
        this.current = this.op.applyAsInt(this.current);
        return i;
    }
}
