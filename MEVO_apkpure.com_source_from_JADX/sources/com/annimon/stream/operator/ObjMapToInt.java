package com.annimon.stream.operator;

import com.annimon.stream.function.ToIntFunction;
import com.annimon.stream.iterator.PrimitiveIterator.OfInt;
import java.util.Iterator;

public class ObjMapToInt<T> extends OfInt {
    private final Iterator<? extends T> iterator;
    private final ToIntFunction<? super T> mapper;

    public ObjMapToInt(Iterator<? extends T> it, ToIntFunction<? super T> toIntFunction) {
        this.iterator = it;
        this.mapper = toIntFunction;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public int nextInt() {
        return this.mapper.applyAsInt(this.iterator.next());
    }
}
