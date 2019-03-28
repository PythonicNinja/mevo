package com.annimon.stream.function;

@FunctionalInterface
public interface ThrowableConsumer<T, E extends Throwable> {
    void accept(T t) throws Throwable;
}
