package com.annimon.stream.operator;

import com.annimon.stream.IntStream;
import com.annimon.stream.function.IntFunction;
import com.annimon.stream.iterator.PrimitiveIterator.OfInt;
import java.util.NoSuchElementException;

public class IntFlatMap extends OfInt {
    private OfInt inner;
    private IntStream innerStream;
    private final OfInt iterator;
    private final IntFunction<? extends IntStream> mapper;

    public IntFlatMap(OfInt ofInt, IntFunction<? extends IntStream> intFunction) {
        this.iterator = ofInt;
        this.mapper = intFunction;
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
            IntStream intStream = (IntStream) this.mapper.apply(this.iterator.nextInt());
            if (intStream != null) {
                this.innerStream = intStream;
                if (intStream.iterator().hasNext()) {
                    this.inner = intStream.iterator();
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

    public int nextInt() {
        if (this.inner != null) {
            return this.inner.nextInt();
        }
        throw new NoSuchElementException();
    }
}
