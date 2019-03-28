package com.annimon.stream.operator;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.annimon.stream.iterator.LsaExtIterator;
import java.util.Iterator;

public class ObjFlatMap<T, R> extends LsaExtIterator<R> {
    private Iterator<? extends R> inner;
    private Stream<? extends R> innerStream;
    private final Iterator<? extends T> iterator;
    private final Function<? super T, ? extends Stream<? extends R>> mapper;

    public ObjFlatMap(Iterator<? extends T> it, Function<? super T, ? extends Stream<? extends R>> function) {
        this.iterator = it;
        this.mapper = function;
    }

    protected void nextIteration() {
        if (this.inner == null || !this.inner.hasNext()) {
            while (this.iterator.hasNext()) {
                if (this.inner == null || !this.inner.hasNext()) {
                    if (this.innerStream != null) {
                        this.innerStream.close();
                        this.innerStream = null;
                    }
                    Stream stream = (Stream) this.mapper.apply(this.iterator.next());
                    if (stream != null) {
                        this.inner = stream.iterator();
                        this.innerStream = stream;
                    }
                }
                if (this.inner != null && this.inner.hasNext()) {
                    this.next = this.inner.next();
                    this.hasNext = true;
                    return;
                }
            }
            this.hasNext = false;
            if (this.innerStream != null) {
                this.innerStream.close();
                this.innerStream = null;
            }
            return;
        }
        this.next = this.inner.next();
        this.hasNext = true;
    }
}
