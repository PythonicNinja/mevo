package com.annimon.stream.operator;

import com.annimon.stream.LongStream;
import com.annimon.stream.function.LongFunction;
import com.annimon.stream.iterator.PrimitiveIterator.OfLong;
import java.util.NoSuchElementException;

public class LongFlatMap extends OfLong {
    private OfLong inner;
    private LongStream innerStream;
    private final OfLong iterator;
    private final LongFunction<? extends LongStream> mapper;

    public LongFlatMap(OfLong ofLong, LongFunction<? extends LongStream> longFunction) {
        this.iterator = ofLong;
        this.mapper = longFunction;
    }

    public boolean hasNext() {
        if (this.inner != null && this.inner.hasNext()) {
            return true;
        }
        while (this.iterator.hasNext()) {
            if (this.innerStream != null) {
                this.innerStream.close();
                this.innerStream = null;
            }
            LongStream longStream = (LongStream) this.mapper.apply(this.iterator.nextLong());
            if (longStream != null) {
                this.innerStream = longStream;
                if (longStream.iterator().hasNext()) {
                    this.inner = longStream.iterator();
                    return true;
                }
            }
        }
        if (this.innerStream != null) {
            this.innerStream.close();
            this.innerStream = null;
        }
        return false;
    }

    public long nextLong() {
        if (this.inner != null) {
            return this.inner.nextLong();
        }
        throw new NoSuchElementException();
    }
}
