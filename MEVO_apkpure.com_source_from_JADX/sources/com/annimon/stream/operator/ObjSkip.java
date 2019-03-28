package com.annimon.stream.operator;

import com.annimon.stream.iterator.LsaIterator;
import java.util.Iterator;

public class ObjSkip<T> extends LsaIterator<T> {
    private final Iterator<? extends T> iterator;
    /* renamed from: n */
    private final long f36n;
    private long skipped = null;

    public ObjSkip(Iterator<? extends T> it, long j) {
        this.iterator = it;
        this.f36n = j;
    }

    public boolean hasNext() {
        while (this.skipped < this.f36n) {
            if (!this.iterator.hasNext()) {
                return false;
            }
            this.iterator.next();
            this.skipped++;
        }
        return this.iterator.hasNext();
    }

    public T nextIteration() {
        return this.iterator.next();
    }
}
