package com.annimon.stream.operator;

import com.annimon.stream.function.Predicate;
import com.annimon.stream.iterator.LsaExtIterator;
import java.util.Iterator;

public class ObjTakeWhile<T> extends LsaExtIterator<T> {
    private final Iterator<? extends T> iterator;
    private final Predicate<? super T> predicate;

    public ObjTakeWhile(Iterator<? extends T> it, Predicate<? super T> predicate) {
        this.iterator = it;
        this.predicate = predicate;
    }

    protected void nextIteration() {
        boolean z;
        if (this.iterator.hasNext()) {
            Predicate predicate = this.predicate;
            Object next = this.iterator.next();
            this.next = next;
            if (predicate.test(next)) {
                z = true;
                this.hasNext = z;
            }
        }
        z = false;
        this.hasNext = z;
    }
}
