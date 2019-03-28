package com.annimon.stream.iterator;

import java.util.Iterator;

public class IndexedIterator<T> implements Iterator<T> {
    private int index;
    private final Iterator<? extends T> iterator;
    private final int step;

    public IndexedIterator(Iterator<? extends T> it) {
        this(0, 1, it);
    }

    public IndexedIterator(int i, int i2, Iterator<? extends T> it) {
        this.iterator = it;
        this.step = i2;
        this.index = i;
    }

    public int getIndex() {
        return this.index;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public T next() {
        T next = this.iterator.next();
        this.index += this.step;
        return next;
    }

    public void remove() {
        this.iterator.remove();
    }
}
