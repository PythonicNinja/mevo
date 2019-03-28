package com.annimon.stream.function;

import com.annimon.stream.Objects;
import java.util.Comparator;

@FunctionalInterface
public interface BinaryOperator<T> extends BiFunction<T, T, T> {

    public static class Util {
        private Util() {
        }

        public static <T> BinaryOperator<T> minBy(final Comparator<? super T> comparator) {
            Objects.requireNonNull(comparator);
            return new BinaryOperator<T>() {
                public T apply(T t, T t2) {
                    return comparator.compare(t, t2) <= 0 ? t : t2;
                }
            };
        }

        public static <T> BinaryOperator<T> maxBy(final Comparator<? super T> comparator) {
            Objects.requireNonNull(comparator);
            return new BinaryOperator<T>() {
                public T apply(T t, T t2) {
                    return comparator.compare(t, t2) >= 0 ? t : t2;
                }
            };
        }
    }
}
