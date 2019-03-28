package com.annimon.stream.function;

@FunctionalInterface
public interface ThrowableDoubleConsumer<E extends Throwable> {
    void accept(double d) throws Throwable;
}
