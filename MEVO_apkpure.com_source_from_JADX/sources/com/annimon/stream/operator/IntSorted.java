package com.annimon.stream.operator;

import com.annimon.stream.internal.Operators;
import com.annimon.stream.iterator.PrimitiveExtIterator.OfInt;
import com.annimon.stream.iterator.PrimitiveIterator;
import java.util.Arrays;

public class IntSorted extends OfInt {
    private int[] array;
    private int index = null;
    private final PrimitiveIterator.OfInt iterator;

    public IntSorted(PrimitiveIterator.OfInt ofInt) {
        this.iterator = ofInt;
    }

    protected void nextIteration() {
        if (!this.isInit) {
            this.array = Operators.toIntArray(this.iterator);
            Arrays.sort(this.array);
        }
        this.hasNext = this.index < this.array.length;
        if (this.hasNext) {
            int[] iArr = this.array;
            int i = this.index;
            this.index = i + 1;
            this.next = iArr[i];
        }
    }
}
