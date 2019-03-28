package com.annimon.stream.function;

@FunctionalInterface
public interface LongUnaryOperator {

    public static class Util {

        /* renamed from: com.annimon.stream.function.LongUnaryOperator$Util$1 */
        static class C07211 implements LongUnaryOperator {
            public long applyAsLong(long j) {
                return j;
            }

            C07211() {
            }
        }

        private Util() {
        }

        public static LongUnaryOperator identity() {
            return new C07211();
        }
    }

    long applyAsLong(long j);
}
