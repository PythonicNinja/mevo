package com.annimon.stream.iterator;

import java.util.Iterator;

public class LazyIterator<T> implements Iterator<T> {
    private final Iterable<? extends T> iterable;
    private Iterator<? extends T> iterator;

    public LazyIterator(Iterable<? extends T> iterable) {
        this.iterable = iterable;
    }

    private void ensureIterator() {
        if (this.iterator == null) {
            this.iterator = this.iterable.iterator();
        }
    }

    public boolean hasNext() {
        ensureIterator();
        return this.iterator.hasNext();
    }

    public T next() {
        ensureIterator();
        return this.iterator.next();
    }

    public void remove() {
        ensureIterator();
        this.iterator.remove();
    }
}
