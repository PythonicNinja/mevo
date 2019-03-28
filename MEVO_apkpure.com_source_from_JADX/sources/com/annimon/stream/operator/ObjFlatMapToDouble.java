package com.annimon.stream.operator;

import com.annimon.stream.DoubleStream;
import com.annimon.stream.function.Function;
import com.annimon.stream.iterator.PrimitiveExtIterator.OfDouble;
import com.annimon.stream.iterator.PrimitiveIterator;
import java.util.Iterator;

public class ObjFlatMapToDouble<T> extends OfDouble {
    private PrimitiveIterator.OfDouble inner;
    private final Iterator<? extends T> iterator;
    private final Function<? super T, ? extends DoubleStream> mapper;

    public ObjFlatMapToDouble(Iterator<? extends T> it, Function<? super T, ? extends DoubleStream> function) {
        this.iterator = it;
        this.mapper = function;
    }

    protected void nextIteration() {
        if (this.inner == null || !this.inner.hasNext()) {
            while (this.iterator.hasNext()) {
                if (this.inner == null || !this.inner.hasNext()) {
                    DoubleStream doubleStream = (DoubleStream) this.mapper.apply(this.iterator.next());
                    if (doubleStream != null) {
                        this.inner = doubleStream.iterator();
                    }
                }
                if (this.inner != null && this.inner.hasNext()) {
                    this.next = this.inner.next().doubleValue();
                    this.hasNext = true;
                    return;
                }
            }
            this.hasNext = false;
            return;
        }
        this.next = this.inner.next().doubleValue();
        this.hasNext = true;
    }
}
