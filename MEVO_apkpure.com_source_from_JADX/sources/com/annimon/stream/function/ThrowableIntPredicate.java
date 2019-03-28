package com.annimon.stream.function;

@FunctionalInterface
public interface ThrowableIntPredicate<E extends Throwable> {
    boolean test(int i) throws Throwable;
}
