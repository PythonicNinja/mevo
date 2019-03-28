package com.annimon.stream.operator;

import com.annimon.stream.LongStream;
import com.annimon.stream.function.Function;
import com.annimon.stream.iterator.PrimitiveExtIterator.OfLong;
import com.annimon.stream.iterator.PrimitiveIterator;
import java.util.Iterator;

public class ObjFlatMapToLong<T> extends OfLong {
    private PrimitiveIterator.OfLong inner;
    private final Iterator<? extends T> iterator;
    private final Function<? super T, ? extends LongStream> mapper;

    public ObjFlatMapToLong(Iterator<? extends T> it, Function<? super T, ? extends LongStream> function) {
        this.iterator = it;
        this.mapper = function;
    }

    protected void nextIteration() {
        if (this.inner == null || !this.inner.hasNext()) {
            while (this.iterator.hasNext()) {
                if (this.inner == null || !this.inner.hasNext()) {
                    LongStream longStream = (LongStream) this.mapper.apply(this.iterator.next());
                    if (longStream != null) {
                        this.inner = longStream.iterator();
                    }
                }
                if (this.inner != null && this.inner.hasNext()) {
                    this.next = this.inner.next().longValue();
                    this.hasNext = true;
                    return;
                }
            }
            this.hasNext = false;
            return;
        }
        this.next = this.inner.next().longValue();
        this.hasNext = true;
    }
}
