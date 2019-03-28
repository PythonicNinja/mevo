package com.annimon.stream.operator;

import com.annimon.stream.function.LongToIntFunction;
import com.annimon.stream.iterator.PrimitiveIterator.OfInt;
import com.annimon.stream.iterator.PrimitiveIterator.OfLong;

public class LongMapToInt extends OfInt {
    private final OfLong iterator;
    private final LongToIntFunction mapper;

    public LongMapToInt(OfLong ofLong, LongToIntFunction longToIntFunction) {
        this.iterator = ofLong;
        this.mapper = longToIntFunction;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public int nextInt() {
        return this.mapper.applyAsInt(this.iterator.nextLong());
    }
}
