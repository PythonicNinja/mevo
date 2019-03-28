package com.annimon.stream.operator;

import com.annimon.stream.function.UnaryOperator;
import com.annimon.stream.iterator.LsaIterator;

public class ObjIterate<T> extends LsaIterator<T> {
    private T current;
    private final UnaryOperator<T> op;

    public boolean hasNext() {
        return true;
    }

    public ObjIterate(T t, UnaryOperator<T> unaryOperator) {
        this.op = unaryOperator;
        this.current = t;
    }

    public T nextIteration() {
        T t = this.current;
        this.current = this.op.apply(this.current);
        return t;
    }
}
