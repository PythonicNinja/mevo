package com.annimon.stream.operator;

import com.annimon.stream.function.IndexedFunction;
import com.annimon.stream.iterator.IndexedIterator;
import com.annimon.stream.iterator.LsaIterator;

public class ObjMapIndexed<T, R> extends LsaIterator<R> {
    private final IndexedIterator<? extends T> iterator;
    private final IndexedFunction<? super T, ? extends R> mapper;

    public ObjMapIndexed(IndexedIterator<? extends T> indexedIterator, IndexedFunction<? super T, ? extends R> indexedFunction) {
        this.iterator = indexedIterator;
        this.mapper = indexedFunction;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public R nextIteration() {
        return this.mapper.apply(this.iterator.getIndex(), this.iterator.next());
    }
}
