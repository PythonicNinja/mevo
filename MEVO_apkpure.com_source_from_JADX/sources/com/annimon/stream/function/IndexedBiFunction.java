package com.annimon.stream.function;

import com.annimon.stream.Objects;

@FunctionalInterface
public interface IndexedBiFunction<T, U, R> {

    public static class Util {
        private Util() {
        }

        public static <T, U, R> IndexedBiFunction<T, U, R> wrap(final BiFunction<? super T, ? super U, ? extends R> biFunction) {
            Objects.requireNonNull(biFunction);
            return new IndexedBiFunction<T, U, R>() {
                public R apply(int i, T t, U u) {
                    return biFunction.apply(t, u);
                }
            };
        }
    }

    R apply(int i, T t, U u);
}
