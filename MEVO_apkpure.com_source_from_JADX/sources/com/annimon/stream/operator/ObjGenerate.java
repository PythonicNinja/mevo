package com.annimon.stream.operator;

import com.annimon.stream.function.Supplier;
import com.annimon.stream.iterator.LsaIterator;

public class ObjGenerate<T> extends LsaIterator<T> {
    private final Supplier<T> supplier;

    public boolean hasNext() {
        return true;
    }

    public ObjGenerate(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public T nextIteration() {
        return this.supplier.get();
    }
}
