package com.annimon.stream.operator;

import com.annimon.stream.function.DoubleUnaryOperator;
import com.annimon.stream.iterator.PrimitiveIterator.OfDouble;

public class DoubleIterate extends OfDouble {
    private double current;
    private final DoubleUnaryOperator op;

    public boolean hasNext() {
        return true;
    }

    public DoubleIterate(double d, DoubleUnaryOperator doubleUnaryOperator) {
        this.op = doubleUnaryOperator;
        this.current = d;
    }

    public double nextDouble() {
        double d = this.current;
        this.current = this.op.applyAsDouble(this.current);
        return d;
    }
}
