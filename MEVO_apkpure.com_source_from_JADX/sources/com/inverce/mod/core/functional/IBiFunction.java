package com.inverce.mod.core.functional;

@FunctionalInterface
public interface IBiFunction<T, U, R> {
    R apply(T t, U u);
}
