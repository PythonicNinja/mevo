package com.annimon.stream.function;

@FunctionalInterface
public interface BiConsumer<T, U> {

    public static class Util {
        private Util() {
        }

        public static <T, U> BiConsumer<T, U> andThen(final BiConsumer<? super T, ? super U> biConsumer, final BiConsumer<? super T, ? super U> biConsumer2) {
            return new BiConsumer<T, U>() {
                public void accept(T t, U u) {
                    biConsumer.accept(t, u);
                    biConsumer2.accept(t, u);
                }
            };
        }
    }

    void accept(T t, U u);
}
