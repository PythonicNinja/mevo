package com.annimon.stream.function;

import com.annimon.stream.Objects;

@FunctionalInterface
public interface IndexedConsumer<T> {

    public static class Util {
        private Util() {
        }

        public static <T> IndexedConsumer<T> wrap(final Consumer<? super T> consumer) {
            Objects.requireNonNull(consumer);
            return new IndexedConsumer<T>() {
                public void accept(int i, T t) {
                    consumer.accept(t);
                }
            };
        }

        public static <T> IndexedConsumer<T> accept(final IntConsumer intConsumer, final Consumer<? super T> consumer) {
            return new IndexedConsumer<T>() {
                public void accept(int i, T t) {
                    if (intConsumer != null) {
                        intConsumer.accept(i);
                    }
                    if (consumer != 0) {
                        consumer.accept(t);
                    }
                }
            };
        }
    }

    void accept(int i, T t);
}
