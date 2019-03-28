package com.annimon.stream.function;

@FunctionalInterface
public interface ThrowableIntSupplier<E extends Throwable> {
    int getAsInt() throws Throwable;
}
