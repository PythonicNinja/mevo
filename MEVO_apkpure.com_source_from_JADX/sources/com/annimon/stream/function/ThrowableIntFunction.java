package com.annimon.stream.function;

@FunctionalInterface
public interface ThrowableIntFunction<R, E extends Throwable> {
    R apply(int i) throws Throwable;
}
