package com.annimon.stream.operator;

import com.annimon.stream.iterator.LsaIterator;

public class ObjArray<T> extends LsaIterator<T> {
    private final T[] elements;
    private int index = null;

    public ObjArray(T[] tArr) {
        this.elements = tArr;
    }

    public boolean hasNext() {
        return this.index < this.elements.length;
    }

    public T nextIteration() {
        Object[] objArr = this.elements;
        int i = this.index;
        this.index = i + 1;
        return objArr[i];
    }
}
