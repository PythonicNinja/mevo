package com.annimon.stream.function;

@FunctionalInterface
public interface ThrowableDoubleFunction<R, E extends Throwable> {
    R apply(double d) throws Throwable;
}
