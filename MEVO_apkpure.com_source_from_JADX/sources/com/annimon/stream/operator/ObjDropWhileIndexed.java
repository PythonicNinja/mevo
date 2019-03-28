package com.annimon.stream.operator;

import com.annimon.stream.function.IndexedPredicate;
import com.annimon.stream.iterator.IndexedIterator;
import com.annimon.stream.iterator.LsaExtIterator;

public class ObjDropWhileIndexed<T> extends LsaExtIterator<T> {
    private final IndexedIterator<? extends T> iterator;
    private final IndexedPredicate<? super T> predicate;

    public ObjDropWhileIndexed(IndexedIterator<? extends T> indexedIterator, IndexedPredicate<? super T> indexedPredicate) {
        this.iterator = indexedIterator;
        this.predicate = indexedPredicate;
    }

    protected void nextIteration() {
        boolean hasNext;
        if (!this.isInit) {
            int index;
            do {
                hasNext = this.iterator.hasNext();
                this.hasNext = hasNext;
                if (hasNext) {
                    index = this.iterator.getIndex();
                    this.next = this.iterator.next();
                }
            } while (this.predicate.test(index, this.next));
            return;
        }
        hasNext = this.hasNext && this.iterator.hasNext();
        this.hasNext = hasNext;
        if (this.hasNext) {
            this.next = this.iterator.next();
        }
    }
}
