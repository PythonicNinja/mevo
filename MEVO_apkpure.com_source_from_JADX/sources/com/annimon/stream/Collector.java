package com.annimon.stream;

import com.annimon.stream.function.BiConsumer;
import com.annimon.stream.function.Function;
import com.annimon.stream.function.Supplier;

public interface Collector<T, A, R> {
    BiConsumer<A, T> accumulator();

    Function<A, R> finisher();

    Supplier<A> supplier();
}
