package com.annimon.stream.operator;

import com.annimon.stream.function.Predicate;
import com.annimon.stream.iterator.LsaExtIterator;
import java.util.Iterator;

public class ObjTakeUntil<T> extends LsaExtIterator<T> {
    private final Iterator<? extends T> iterator;
    private final Predicate<? super T> stopPredicate;

    public ObjTakeUntil(Iterator<? extends T> it, Predicate<? super T> predicate) {
        this.iterator = it;
        this.stopPredicate = predicate;
    }

    protected void nextIteration() {
        boolean z = this.iterator.hasNext() && !(this.isInit && this.stopPredicate.test(this.next));
        this.hasNext = z;
        if (this.hasNext) {
            this.next = this.iterator.next();
        }
    }
}
