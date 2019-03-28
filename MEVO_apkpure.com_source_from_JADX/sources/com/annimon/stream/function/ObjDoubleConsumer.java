package com.annimon.stream.function;

@FunctionalInterface
public interface ObjDoubleConsumer<T> {
    void accept(T t, double d);
}
