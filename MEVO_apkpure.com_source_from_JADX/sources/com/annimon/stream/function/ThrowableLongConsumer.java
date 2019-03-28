package com.annimon.stream.function;

@FunctionalInterface
public interface ThrowableLongConsumer<E extends Throwable> {
    void accept(long j) throws Throwable;
}
