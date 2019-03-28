package com.annimon.stream.operator;

import com.annimon.stream.function.BiFunction;
import com.annimon.stream.iterator.LsaExtIterator;
import java.util.Iterator;

public class ObjScan<T> extends LsaExtIterator<T> {
    private final BiFunction<T, T, T> accumulator;
    private final Iterator<? extends T> iterator;

    public ObjScan(Iterator<? extends T> it, BiFunction<T, T, T> biFunction) {
        this.iterator = it;
        this.accumulator = biFunction;
    }

    protected void nextIteration() {
        this.hasNext = this.iterator.hasNext();
        if (this.hasNext) {
            Object next = this.iterator.next();
            if (this.isInit) {
                this.next = this.accumulator.apply(this.next, next);
            } else {
                this.next = next;
            }
        }
    }
}
