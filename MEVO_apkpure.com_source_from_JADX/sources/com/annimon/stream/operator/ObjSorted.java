package com.annimon.stream.operator;

import com.annimon.stream.internal.Operators;
import com.annimon.stream.iterator.LsaExtIterator;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ObjSorted<T> extends LsaExtIterator<T> {
    private final Comparator<? super T> comparator;
    private final Iterator<? extends T> iterator;
    private Iterator<T> sortedIterator;

    public ObjSorted(Iterator<? extends T> it, Comparator<? super T> comparator) {
        this.iterator = it;
        this.comparator = comparator;
    }

    protected void nextIteration() {
        if (!this.isInit) {
            List toList = Operators.toList(this.iterator);
            Collections.sort(toList, this.comparator);
            this.sortedIterator = toList.iterator();
        }
        this.hasNext = this.sortedIterator.hasNext();
        if (this.hasNext) {
            this.next = this.sortedIterator.next();
        }
    }
}
