package com.annimon.stream.function;

@FunctionalInterface
public interface LongPredicate {

    public static class Util {
        private Util() {
        }

        public static LongPredicate and(final LongPredicate longPredicate, final LongPredicate longPredicate2) {
            return new LongPredicate() {
                public boolean test(long j) {
                    return (!longPredicate.test(j) || longPredicate2.test(j) == null) ? 0 : 1;
                }
            };
        }

        public static LongPredicate or(final LongPredicate longPredicate, final LongPredicate longPredicate2) {
            return new LongPredicate() {
                public boolean test(long j) {
                    if (!longPredicate.test(j)) {
                        if (longPredicate2.test(j) == null) {
                            return 0;
                        }
                    }
                    return 1;
                }
            };
        }

        public static LongPredicate xor(final LongPredicate longPredicate, final LongPredicate longPredicate2) {
            return new LongPredicate() {
                public boolean test(long j) {
                    return longPredicate2.test(j) ^ longPredicate.test(j);
                }
            };
        }

        public static LongPredicate negate(final LongPredicate longPredicate) {
            return new LongPredicate() {
                public boolean test(long j) {
                    return longPredicate.test(j) ^ 1;
                }
            };
        }

        public static LongPredicate safe(ThrowableLongPredicate<Throwable> throwableLongPredicate) {
            return safe(throwableLongPredicate, false);
        }

        public static LongPredicate safe(final ThrowableLongPredicate<Throwable> throwableLongPredicate, final boolean z) {
            return new LongPredicate() {
                public boolean test(long r2) {
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
                    throw new UnsupportedOperationException("Method not decompiled: com.annimon.stream.function.LongPredicate.Util.5.test(long):boolean");
                }
            };
        }
    }

    boolean test(long j);
}
