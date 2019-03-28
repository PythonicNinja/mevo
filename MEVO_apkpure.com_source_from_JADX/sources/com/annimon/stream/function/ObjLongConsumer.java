package com.annimon.stream.function;

@FunctionalInterface
public interface ObjLongConsumer<T> {
    void accept(T t, long j);
}
