package com.annimon.stream.function;

@FunctionalInterface
public interface ThrowableLongPredicate<E extends Throwable> {
    boolean test(long j) throws Throwable;
}
