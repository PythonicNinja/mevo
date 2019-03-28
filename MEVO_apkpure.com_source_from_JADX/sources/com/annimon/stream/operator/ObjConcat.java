package com.annimon.stream.operator;

import com.annimon.stream.iterator.LsaExtIterator;
import java.util.Iterator;

public class ObjConcat<T> extends LsaExtIterator<T> {
    private boolean firstStreamIsCurrent = true;
    private final Iterator<? extends T> iterator1;
    private final Iterator<? extends T> iterator2;

    public ObjConcat(Iterator<? extends T> it, Iterator<? extends T> it2) {
        this.iterator1 = it;
        this.iterator2 = it2;
    }

    protected void nextIteration() {
        if (this.firstStreamIsCurrent) {
            if (this.iterator1.hasNext()) {
                this.next = this.iterator1.next();
                this.hasNext = true;
                return;
            }
            this.firstStreamIsCurrent = false;
        }
        if (this.iterator2.hasNext()) {
            this.next = this.iterator2.next();
            this.hasNext = true;
            return;
        }
        this.hasNext = false;
    }
}
