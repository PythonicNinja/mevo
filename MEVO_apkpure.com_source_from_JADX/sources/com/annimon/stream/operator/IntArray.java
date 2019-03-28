package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator.OfInt;

public class IntArray extends OfInt {
    private int index = null;
    private final int[] values;

    public IntArray(int[] iArr) {
        this.values = iArr;
    }

    public boolean hasNext() {
        return this.index < this.values.length;
    }

    public int nextInt() {
        int[] iArr = this.values;
        int i = this.index;
        this.index = i + 1;
        return iArr[i];
    }
}
