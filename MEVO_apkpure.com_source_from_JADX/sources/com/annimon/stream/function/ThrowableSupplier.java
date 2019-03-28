package com.annimon.stream.function;

@FunctionalInterface
public interface ThrowableSupplier<T, E extends Throwable> {
    T get() throws Throwable;
}
