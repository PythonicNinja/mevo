package com.annimon.stream.operator;

import com.annimon.stream.internal.Operators;
import com.annimon.stream.iterator.PrimitiveExtIterator.OfDouble;
import com.annimon.stream.iterator.PrimitiveIterator;
import java.util.Arrays;

public class DoubleSorted extends OfDouble {
    private double[] array;
    private int index = null;
    private final PrimitiveIterator.OfDouble iterator;

    public DoubleSorted(PrimitiveIterator.OfDouble ofDouble) {
        this.iterator = ofDouble;
    }

    protected void nextIteration() {
        if (!this.isInit) {
            this.array = Operators.toDoubleArray(this.iterator);
            Arrays.sort(this.array);
        }
        this.hasNext = this.index < this.array.length;
        if (this.hasNext) {
            double[] dArr = this.array;
            int i = this.index;
            this.index = i + 1;
            this.next = dArr[i];
        }
    }
}
