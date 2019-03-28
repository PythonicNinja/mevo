package com.annimon.stream.function;

@FunctionalInterface
public interface ThrowableFunction<I, R, E extends Throwable> {
    R apply(I i) throws Throwable;
}
