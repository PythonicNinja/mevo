package com.annimon.stream.function;

@FunctionalInterface
public interface ThrowableDoublePredicate<E extends Throwable> {
    boolean test(double d) throws Throwable;
}
