package com.annimon.stream.operator;

import com.annimon.stream.function.Predicate;
import com.annimon.stream.iterator.LsaExtIterator;
import java.util.Iterator;

public class ObjDropWhile<T> extends LsaExtIterator<T> {
    private final Iterator<? extends T> iterator;
    private final Predicate<? super T> predicate;

    public ObjDropWhile(Iterator<? extends T> it, Predicate<? super T> predicate) {
        this.iterator = it;
        this.predicate = predicate;
    }

    protected void nextIteration() {
        boolean hasNext;
        if (!this.isInit) {
            do {
                hasNext = this.iterator.hasNext();
                this.hasNext = hasNext;
                if (hasNext) {
                    this.next = this.iterator.next();
                }
            } while (this.predicate.test(this.next));
            return;
        }
        hasNext = this.hasNext && this.iterator.hasNext();
        this.hasNext = hasNext;
        if (this.hasNext) {
            this.next = this.iterator.next();
        }
    }
}
