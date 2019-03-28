package com.annimon.stream.function;

@FunctionalInterface
public interface BooleanPredicate {

    public static class Util {

        /* renamed from: com.annimon.stream.function.BooleanPredicate$Util$1 */
        static class C06781 implements BooleanPredicate {
            public boolean test(boolean z) {
                return z;
            }

            C06781() {
            }
        }

        private Util() {
        }

        public static BooleanPredicate identity() {
            return new C06781();
        }

        public static BooleanPredicate and(final BooleanPredicate booleanPredicate, final BooleanPredicate booleanPredicate2) {
            return new BooleanPredicate() {
                public boolean test(boolean z) {
                    return booleanPredicate.test(z) && booleanPredicate2.test(z);
                }
            };
        }

        public static BooleanPredicate or(final BooleanPredicate booleanPredicate, final BooleanPredicate booleanPredicate2) {
            return new BooleanPredicate() {
                public boolean test(boolean z) {
                    if (!booleanPredicate.test(z)) {
                        if (!booleanPredicate2.test(z)) {
                            return false;
                        }
                    }
                    return true;
                }
            };
        }

        public static BooleanPredicate xor(final BooleanPredicate booleanPredicate, final BooleanPredicate booleanPredicate2) {
            return new BooleanPredicate() {
                public boolean test(boolean z) {
                    return booleanPredicate2.test(z) ^ booleanPredicate.test(z);
                }
            };
        }

        public static BooleanPredicate negate(final BooleanPredicate booleanPredicate) {
            return new BooleanPredicate() {
                public boolean test(boolean z) {
                    return booleanPredicate.test(z) ^ 1;
                }
            };
        }
    }

    boolean test(boolean z);
}
