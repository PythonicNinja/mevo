package com.annimon.stream.function;

@FunctionalInterface
public interface ThrowableDoubleSupplier<E extends Throwable> {
    double getAsDouble() throws Throwable;
}
