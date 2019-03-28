package com.annimon.stream.function;

@FunctionalInterface
public interface BooleanConsumer {

    public static class Util {
        private Util() {
        }

        public static BooleanConsumer andThen(final BooleanConsumer booleanConsumer, final BooleanConsumer booleanConsumer2) {
            return new BooleanConsumer() {
                public void accept(boolean z) {
                    booleanConsumer.accept(z);
                    booleanConsumer2.accept(z);
                }
            };
        }
    }

    void accept(boolean z);
}
