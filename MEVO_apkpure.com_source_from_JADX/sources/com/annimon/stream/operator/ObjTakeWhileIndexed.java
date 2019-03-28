package com.annimon.stream.operator;

import com.annimon.stream.function.IndexedPredicate;
import com.annimon.stream.iterator.IndexedIterator;
import com.annimon.stream.iterator.LsaExtIterator;

public class ObjTakeWhileIndexed<T> extends LsaExtIterator<T> {
    private final IndexedIterator<? extends T> iterator;
    private final IndexedPredicate<? super T> predicate;

    public ObjTakeWhileIndexed(IndexedIterator<? extends T> indexedIterator, IndexedPredicate<? super T> indexedPredicate) {
        this.iterator = indexedIterator;
        this.predicate = indexedPredicate;
    }

    protected void nextIteration() {
        boolean z;
        if (this.iterator.hasNext()) {
            IndexedPredicate indexedPredicate = this.predicate;
            int index = this.iterator.getIndex();
            Object next = this.iterator.next();
            this.next = next;
            if (indexedPredicate.test(index, next)) {
                z = true;
                this.hasNext = z;
            }
        }
        z = false;
        this.hasNext = z;
    }
}
