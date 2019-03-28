package com.annimon.stream.function;

@FunctionalInterface
public interface ThrowableLongSupplier<E extends Throwable> {
    long getAsLong() throws Throwable;
}
