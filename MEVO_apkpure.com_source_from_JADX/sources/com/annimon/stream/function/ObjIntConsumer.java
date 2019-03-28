package com.annimon.stream.function;

@FunctionalInterface
public interface ObjIntConsumer<T> {
    void accept(T t, int i);
}
