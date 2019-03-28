package com.annimon.stream.function;

@FunctionalInterface
public interface IntUnaryOperator {

    public static class Util {

        /* renamed from: com.annimon.stream.function.IntUnaryOperator$Util$1 */
        static class C07111 implements IntUnaryOperator {
            public int applyAsInt(int i) {
                return i;
            }

            C07111() {
            }
        }

        private Util() {
        }

        public static IntUnaryOperator identity() {
            return new C07111();
        }
    }

    int applyAsInt(int i);
}
