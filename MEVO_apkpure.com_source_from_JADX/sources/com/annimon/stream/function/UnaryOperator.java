package com.annimon.stream.function;

@FunctionalInterface
public interface UnaryOperator<T> extends Function<T, T> {

    public static class Util {

        /* renamed from: com.annimon.stream.function.UnaryOperator$Util$1 */
        static class C09521 implements UnaryOperator<T> {
            public T apply(T t) {
                return t;
            }

            C09521() {
            }
        }

        private Util() {
        }

        public static <T> UnaryOperator<T> identity() {
            return new C09521();
        }
    }
}
