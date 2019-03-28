package com.annimon.stream.operator;

import com.annimon.stream.function.BiFunction;
import com.annimon.stream.iterator.LsaExtIterator;
import java.util.Iterator;

public class ObjScanIdentity<T, R> extends LsaExtIterator<R> {
    private final BiFunction<? super R, ? super T, ? extends R> accumulator;
    private final R identity;
    private final Iterator<? extends T> iterator;

    public ObjScanIdentity(Iterator<? extends T> it, R r, BiFunction<? super R, ? super T, ? extends R> biFunction) {
        this.iterator = it;
        this.identity = r;
        this.accumulator = biFunction;
    }

    protected void nextIteration() {
        if (this.isInit) {
            this.hasNext = this.iterator.hasNext();
            if (this.hasNext) {
                this.next = this.accumulator.apply(this.next, this.iterator.next());
            }
            return;
        }
        this.hasNext = true;
        this.next = this.identity;
    }
}
