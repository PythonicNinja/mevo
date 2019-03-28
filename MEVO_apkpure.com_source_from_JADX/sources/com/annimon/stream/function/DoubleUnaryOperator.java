package com.annimon.stream.function;

@FunctionalInterface
public interface DoubleUnaryOperator {

    public static class Util {

        /* renamed from: com.annimon.stream.function.DoubleUnaryOperator$Util$1 */
        static class C06941 implements DoubleUnaryOperator {
            public double applyAsDouble(double d) {
                return d;
            }

            C06941() {
            }
        }

        private Util() {
        }

        public static DoubleUnaryOperator identity() {
            return new C06941();
        }
    }

    double applyAsDouble(double d);
}
