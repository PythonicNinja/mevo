package com.annimon.stream.operator;

import com.annimon.stream.iterator.LsaExtIterator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ObjDistinct<T> extends LsaExtIterator<T> {
    private final Iterator<? extends T> iterator;
    private final Set<T> set = new HashSet();

    public ObjDistinct(Iterator<? extends T> it) {
        this.iterator = it;
    }

    protected void nextIteration() {
        do {
            boolean hasNext = this.iterator.hasNext();
            this.hasNext = hasNext;
            if (hasNext) {
                this.next = this.iterator.next();
            } else {
                return;
            }
        } while (!this.set.add(this.next));
    }
}
