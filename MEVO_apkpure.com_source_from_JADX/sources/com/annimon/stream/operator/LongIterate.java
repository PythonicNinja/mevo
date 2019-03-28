package com.annimon.stream.operator;

import com.annimon.stream.function.LongUnaryOperator;
import com.annimon.stream.iterator.PrimitiveIterator.OfLong;

public class LongIterate extends OfLong {
    private long current;
    private final LongUnaryOperator op;

    public boolean hasNext() {
        return true;
    }

    public LongIterate(long j, LongUnaryOperator longUnaryOperator) {
        this.op = longUnaryOperator;
        this.current = j;
    }

    public long nextLong() {
        long j = this.current;
        this.current = this.op.applyAsLong(this.current);
        return j;
    }
}
