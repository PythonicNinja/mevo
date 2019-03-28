package com.annimon.stream.function;

@FunctionalInterface
public interface DoublePredicate {

    public static class Util {
        private Util() {
        }

        public static DoublePredicate and(final DoublePredicate doublePredicate, final DoublePredicate doublePredicate2) {
            return new DoublePredicate() {
                public boolean test(double d) {
                    return (!doublePredicate.test(d) || doublePredicate2.test(d) == null) ? 0.0d : Double.MIN_VALUE;
                }
            };
        }

        public static DoublePredicate or(final DoublePredicate doublePredicate, final DoublePredicate doublePredicate2) {
            return new DoublePredicate() {
                public boolean test(double d) {
                    if (!doublePredicate.test(d)) {
                        if (doublePredicate2.test(d) == null) {
                            return 0.0d;
                        }
                    }
                    return Double.MIN_VALUE;
                }
            };
        }

        public static DoublePredicate xor(final DoublePredicate doublePredicate, final DoublePredicate doublePredicate2) {
            return new DoublePredicate() {
                public boolean test(double d) {
                    return doublePredicate2.test(d) ^ doublePredicate.test(d);
                }
            };
        }

        public static DoublePredicate negate(final DoublePredicate doublePredicate) {
            return new DoublePredicate() {
                public boolean test(double d) {
                    return doublePredicate.test(d) ^ 1;
                }
            };
        }

        public static DoublePredicate safe(ThrowableDoublePredicate<Throwable> throwableDoublePredicate) {
            return safe(throwableDoublePredicate, false);
        }

        public static DoublePredicate safe(final ThrowableDoublePredicate<Throwable> throwableDoublePredicate, final boolean z) {
            return new DoublePredicate() {
                public boolean test(double r2) {
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
                    throw new UnsupportedOperationException("Method not decompiled: com.annimon.stream.function.DoublePredicate.Util.5.test(double):boolean");
                }
            };
        }
    }

    boolean test(double d);
}
