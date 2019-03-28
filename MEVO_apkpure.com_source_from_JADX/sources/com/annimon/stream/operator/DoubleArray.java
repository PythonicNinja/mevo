package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator.OfDouble;

public class DoubleArray extends OfDouble {
    private int index = null;
    private final double[] values;

    public DoubleArray(double[] dArr) {
        this.values = dArr;
    }

    public double nextDouble() {
        double[] dArr = this.values;
        int i = this.index;
        this.index = i + 1;
        return dArr[i];
    }

    public boolean hasNext() {
        return this.index < this.values.length;
    }
}
