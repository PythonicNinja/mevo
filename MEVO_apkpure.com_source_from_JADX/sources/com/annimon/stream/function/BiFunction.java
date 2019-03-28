package com.annimon.stream.function;

import com.annimon.stream.Objects;

@FunctionalInterface
public interface BiFunction<T, U, R> {

    public static class Util {
        private Util() {
        }

        public static <T, U, R, V> BiFunction<T, U, V> andThen(final BiFunction<? super T, ? super U, ? extends R> biFunction, final Function<? super R, ? extends V> function) {
            return new BiFunction<T, U, V>() {
                public V apply(T t, U u) {
                    return function.apply(biFunction.apply(t, u));
                }
            };
        }

        public static <T, U, R> BiFunction<U, T, R> reverse(final BiFunction<? super T, ? super U, ? extends R> biFunction) {
            Objects.requireNonNull(biFunction);
            return new BiFunction<U, T, R>() {
                public R apply(U u, T t) {
                    return biFunction.apply(t, u);
                }
            };
        }
    }

    R apply(T t, U u);
}
