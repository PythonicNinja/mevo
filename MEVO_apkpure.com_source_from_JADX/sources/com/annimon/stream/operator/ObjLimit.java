package com.annimon.stream.operator;

import com.annimon.stream.iterator.LsaIterator;
import java.util.Iterator;

public class ObjLimit<T> extends LsaIterator<T> {
    private long index = null;
    private final Iterator<? extends T> iterator;
    private final long maxSize;

    public ObjLimit(Iterator<? extends T> it, long j) {
        this.iterator = it;
        this.maxSize = j;
    }

    public boolean hasNext() {
        return this.index < this.maxSize && this.iterator.hasNext();
    }

    public T nextIteration() {
        this.index++;
        return this.iterator.next();
    }
}
