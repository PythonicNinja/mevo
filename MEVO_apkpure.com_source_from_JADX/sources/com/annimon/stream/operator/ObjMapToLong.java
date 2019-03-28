package com.annimon.stream.operator;

import com.annimon.stream.function.ToLongFunction;
import com.annimon.stream.iterator.PrimitiveIterator.OfLong;
import java.util.Iterator;

public class ObjMapToLong<T> extends OfLong {
    private final Iterator<? extends T> iterator;
    private final ToLongFunction<? super T> mapper;

    public ObjMapToLong(Iterator<? extends T> it, ToLongFunction<? super T> toLongFunction) {
        this.iterator = it;
        this.mapper = toLongFunction;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public long nextLong() {
        return this.mapper.applyAsLong(this.iterator.next());
    }
}
