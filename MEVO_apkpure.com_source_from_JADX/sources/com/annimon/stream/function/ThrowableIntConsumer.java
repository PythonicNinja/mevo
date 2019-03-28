package com.annimon.stream.function;

@FunctionalInterface
public interface ThrowableIntConsumer<E extends Throwable> {
    void accept(int i) throws Throwable;
}
