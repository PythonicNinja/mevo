package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator.OfLong;

public class LongArray extends OfLong {
    private int index = null;
    private final long[] values;

    public LongArray(long[] jArr) {
        this.values = jArr;
    }

    public long nextLong() {
        long[] jArr = this.values;
        int i = this.index;
        this.index = i + 1;
        return jArr[i];
    }

    public boolean hasNext() {
        return this.index < this.values.length;
    }
}
