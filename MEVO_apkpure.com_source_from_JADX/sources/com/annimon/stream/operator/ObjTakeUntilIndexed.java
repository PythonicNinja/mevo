package com.annimon.stream.operator;

import com.annimon.stream.function.IndexedPredicate;
import com.annimon.stream.iterator.IndexedIterator;
import com.annimon.stream.iterator.LsaExtIterator;

public class ObjTakeUntilIndexed<T> extends LsaExtIterator<T> {
    private final IndexedIterator<? extends T> iterator;
    private final IndexedPredicate<? super T> stopPredicate;

    public ObjTakeUntilIndexed(IndexedIterator<? extends T> indexedIterator, IndexedPredicate<? super T> indexedPredicate) {
        this.iterator = indexedIterator;
        this.stopPredicate = indexedPredicate;
    }

    protected void nextIteration() {
        boolean z = this.iterator.hasNext() && !(this.isInit && this.stopPredicate.test(this.iterator.getIndex(), this.next));
        this.hasNext = z;
        if (this.hasNext) {
            this.next = this.iterator.next();
        }
    }
}
