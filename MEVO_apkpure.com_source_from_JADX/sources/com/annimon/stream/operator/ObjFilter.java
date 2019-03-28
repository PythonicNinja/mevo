package com.annimon.stream.operator;

import com.annimon.stream.function.Predicate;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ObjFilter<T> implements Iterator<T> {
    private boolean hasNext;
    private boolean hasNextEvaluated;
    private final Iterator<? extends T> iterator;
    private T next;
    private final Predicate<? super T> predicate;

    public ObjFilter(Iterator<? extends T> it, Predicate<? super T> predicate) {
        this.iterator = it;
        this.predicate = predicate;
    }

    public boolean hasNext() {
        if (!this.hasNextEvaluated) {
            nextIteration();
            this.hasNextEvaluated = true;
        }
        return this.hasNext;
    }

    public T next() {
        if (!this.hasNextEvaluated) {
            this.hasNext = hasNext();
        }
        if (this.hasNext) {
            this.hasNextEvaluated = false;
            return this.next;
        }
        throw new NoSuchElementException();
    }

    private void nextIteration() {
        while (this.iterator.hasNext()) {
            this.next = this.iterator.next();
            if (this.predicate.test(this.next)) {
                this.hasNext = true;
                return;
            }
        }
        this.hasNext = false;
    }

    public void remove() {
        throw new UnsupportedOperationException("remove not supported");
    }
}
