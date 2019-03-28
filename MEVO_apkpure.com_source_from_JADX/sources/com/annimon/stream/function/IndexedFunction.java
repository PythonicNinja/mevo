package com.annimon.stream.function;

import com.annimon.stream.Objects;

@FunctionalInterface
public interface IndexedFunction<T, R> {

    public static class Util {
        private Util() {
        }

        public static <T, R> IndexedFunction<T, R> wrap(final Function<? super T, ? extends R> function) {
            Objects.requireNonNull(function);
            return new IndexedFunction<T, R>() {
                public R apply(int i, T t) {
                    return function.apply(t);
                }
            };
        }
    }

    R apply(int i, T t);
}
