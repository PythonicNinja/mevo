package com.annimon.stream.function;

@FunctionalInterface
public interface ThrowablePredicate<T, E extends Throwable> {
    boolean test(T t) throws Throwable;
}
