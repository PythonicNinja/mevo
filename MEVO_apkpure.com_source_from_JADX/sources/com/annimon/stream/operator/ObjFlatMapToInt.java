package com.annimon.stream.operator;

import com.annimon.stream.IntStream;
import com.annimon.stream.function.Function;
import com.annimon.stream.iterator.PrimitiveExtIterator.OfInt;
import com.annimon.stream.iterator.PrimitiveIterator;
import java.util.Iterator;

public class ObjFlatMapToInt<T> extends OfInt {
    private PrimitiveIterator.OfInt inner;
    private final Iterator<? extends T> iterator;
    private final Function<? super T, ? extends IntStream> mapper;

    public ObjFlatMapToInt(Iterator<? extends T> it, Function<? super T, ? extends IntStream> function) {
        this.iterator = it;
        this.mapper = function;
    }

    protected void nextIteration() {
        if (this.inner == null || !this.inner.hasNext()) {
            while (this.iterator.hasNext()) {
                if (this.inner == null || !this.inner.hasNext()) {
                    IntStream intStream = (IntStream) this.mapper.apply(this.iterator.next());
                    if (intStream != null) {
                        this.inner = intStream.iterator();
                    }
                }
                if (this.inner != null && this.inner.hasNext()) {
                    this.next = this.inner.next().intValue();
                    this.hasNext = true;
                    return;
                }
            }
            this.hasNext = false;
            return;
        }
        this.next = this.inner.next().intValue();
        this.hasNext = true;
    }
}
