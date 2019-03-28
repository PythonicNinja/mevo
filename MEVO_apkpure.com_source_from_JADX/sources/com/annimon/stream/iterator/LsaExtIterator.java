package com.annimon.stream.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class LsaExtIterator<T> implements Iterator<T> {
    protected boolean hasNext;
    protected boolean isInit;
    protected T next;

    protected abstract void nextIteration();

    public boolean hasNext() {
        if (!this.isInit) {
            nextIteration();
            this.isInit = true;
        }
        return this.hasNext;
    }

    public T next() {
        if (!this.isInit) {
            hasNext();
        }
        if (this.hasNext) {
            T t = this.next;
            nextIteration();
            if (!this.hasNext) {
                this.next = null;
            }
            return t;
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        throw new UnsupportedOperationException("remove not supported");
    }
}
