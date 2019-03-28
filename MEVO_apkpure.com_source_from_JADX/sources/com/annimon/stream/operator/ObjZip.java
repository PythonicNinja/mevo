package com.annimon.stream.operator;

import com.annimon.stream.function.BiFunction;
import com.annimon.stream.iterator.LsaIterator;
import java.util.Iterator;

public class ObjZip<F, S, R> extends LsaIterator<R> {
    private final BiFunction<? super F, ? super S, ? extends R> combiner;
    private final Iterator<? extends F> iterator1;
    private final Iterator<? extends S> iterator2;

    public ObjZip(Iterator<? extends F> it, Iterator<? extends S> it2, BiFunction<? super F, ? super S, ? extends R> biFunction) {
        this.iterator1 = it;
        this.iterator2 = it2;
        this.combiner = biFunction;
    }

    public boolean hasNext() {
        return this.iterator1.hasNext() && this.iterator2.hasNext();
    }

    public R nextIteration() {
        return this.combiner.apply(this.iterator1.next(), this.iterator2.next());
    }
}
