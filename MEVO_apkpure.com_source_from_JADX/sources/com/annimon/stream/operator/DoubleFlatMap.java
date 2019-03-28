package com.annimon.stream.operator;

import com.annimon.stream.DoubleStream;
import com.annimon.stream.function.DoubleFunction;
import com.annimon.stream.iterator.PrimitiveIterator.OfDouble;
import java.util.NoSuchElementException;

public class DoubleFlatMap extends OfDouble {
    private OfDouble inner;
    private DoubleStream innerStream;
    private final OfDouble iterator;
    private final DoubleFunction<? extends DoubleStream> mapper;

    public DoubleFlatMap(OfDouble ofDouble, DoubleFunction<? extends DoubleStream> doubleFunction) {
        this.iterator = ofDouble;
        this.mapper = doubleFunction;
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
            DoubleStream doubleStream = (DoubleStream) this.mapper.apply(this.iterator.nextDouble());
            if (doubleStream != null) {
                this.innerStream = doubleStream;
                if (doubleStream.iterator().hasNext()) {
                    this.inner = doubleStream.iterator();
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

    public double nextDouble() {
        if (this.inner != null) {
            return this.inner.nextDouble();
        }
        throw new NoSuchElementException();
    }
}
