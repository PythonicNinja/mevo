package com.annimon.stream.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class LsaIterator<T> implements Iterator<T> {
    public abstract T nextIteration();

    public void remove() {
        throw new UnsupportedOperationException("remove not supported");
    }

    public final T next() {
        if (hasNext()) {
            return nextIteration();
        }
        throw new NoSuchElementException();
    }
}
