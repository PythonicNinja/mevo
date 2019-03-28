package com.annimon.stream.operator;

import com.annimon.stream.function.ToDoubleFunction;
import com.annimon.stream.iterator.PrimitiveIterator.OfDouble;
import java.util.Iterator;

public class ObjMapToDouble<T> extends OfDouble {
    private final Iterator<? extends T> iterator;
    private final ToDoubleFunction<? super T> mapper;

    public ObjMapToDouble(Iterator<? extends T> it, ToDoubleFunction<? super T> toDoubleFunction) {
        this.iterator = it;
        this.mapper = toDoubleFunction;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public double nextDouble() {
        return this.mapper.applyAsDouble(this.iterator.next());
    }
}
