package com.annimon.stream.function;

@FunctionalInterface
public interface IntPredicate {

    public static class Util {
        private Util() {
        }

        public static IntPredicate and(final IntPredicate intPredicate, final IntPredicate intPredicate2) {
            return new IntPredicate() {
                public boolean test(int i) {
                    return intPredicate.test(i) && intPredicate2.test(i) != 0;
                }
            };
        }

        public static IntPredicate or(final IntPredicate intPredicate, final IntPredicate intPredicate2) {
            return new IntPredicate() {
                public boolean test(int i) {
                    if (!intPredicate.test(i)) {
                        if (intPredicate2.test(i) == 0) {
                            return false;
                        }
                    }
                    return true;
                }
            };
        }

        public static IntPredicate xor(final IntPredicate intPredicate, final IntPredicate intPredicate2) {
            return new IntPredicate() {
                public boolean test(int i) {
                    return intPredicate2.test(i) ^ intPredicate.test(i);
                }
            };
        }

        public static IntPredicate negate(final IntPredicate intPredicate) {
            return new IntPredicate() {
                public boolean test(int i) {
                    return intPredicate.test(i) ^ 1;
                }
            };
        }

        public static IntPredicate safe(ThrowableIntPredicate<Throwable> throwableIntPredicate) {
            return safe(throwableIntPredicate, false);
        }

        public static IntPredicate safe(final ThrowableIntPredicate<Throwable> throwableIntPredicate, final boolean z) {
            return new IntPredicate() {
                public boolean test(int r2) {
                    /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
                    /*
                    r1 = this;
                    r0 = r1;	 Catch:{ Throwable -> 0x0007 }
                    r2 = r0.test(r2);	 Catch:{ Throwable -> 0x0007 }
                    return r2;
                L_0x0007:
                    r2 = r2;
                    return r2;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.annimon.stream.function.IntPredicate.Util.5.test(int):boolean");
                }
            };
        }
    }

    boolean test(int i);
}
