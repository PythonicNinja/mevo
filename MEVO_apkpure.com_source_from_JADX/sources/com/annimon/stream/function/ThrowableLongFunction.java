package com.annimon.stream.function;

@FunctionalInterface
public interface ThrowableLongFunction<R, E extends Throwable> {
    R apply(long j) throws Throwable;
}
