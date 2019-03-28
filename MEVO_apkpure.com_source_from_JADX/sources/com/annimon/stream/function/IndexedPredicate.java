package com.annimon.stream.function;

import com.annimon.stream.Objects;

@FunctionalInterface
public interface IndexedPredicate<T> {

    public static class Util {
        private Util() {
        }

        public static <T> IndexedPredicate<T> wrap(final Predicate<? super T> predicate) {
            Objects.requireNonNull(predicate);
            return new IndexedPredicate<T>() {
                public boolean test(int i, T t) {
                    return predicate.test(t);
                }
            };
        }
    }

    boolean test(int i, T t);
}
