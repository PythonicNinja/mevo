package com.annimon.stream.function;

@FunctionalInterface
public interface Predicate<T> {

    public static class Util {

        /* renamed from: com.annimon.stream.function.Predicate$Util$5 */
        static class C07265 implements Predicate<T> {
            public boolean test(T t) {
                return t != null;
            }

            C07265() {
            }
        }

        private Util() {
        }

        public static <T> Predicate<T> and(final Predicate<? super T> predicate, final Predicate<? super T> predicate2) {
            return new Predicate<T>() {
                public boolean test(T t) {
                    return (!predicate.test(t) || predicate2.test(t) == null) ? null : true;
                }
            };
        }

        public static <T> Predicate<T> or(final Predicate<? super T> predicate, final Predicate<? super T> predicate2) {
            return new Predicate<T>() {
                public boolean test(T t) {
                    if (!predicate.test(t)) {
                        if (predicate2.test(t) == null) {
                            return null;
                        }
                    }
                    return true;
                }
            };
        }

        public static <T> Predicate<T> xor(final Predicate<? super T> predicate, final Predicate<? super T> predicate2) {
            return new Predicate<T>() {
                public boolean test(T t) {
                    return predicate2.test(t) ^ predicate.test(t);
                }
            };
        }

        public static <T> Predicate<T> negate(final Predicate<? super T> predicate) {
            return new Predicate<T>() {
                public boolean test(T t) {
                    return predicate.test(t) ^ 1;
                }
            };
        }

        public static <T> Predicate<T> notNull() {
            return new C07265();
        }

        public static <T> Predicate<T> safe(ThrowablePredicate<? super T, Throwable> throwablePredicate) {
            return safe(throwablePredicate, false);
        }

        public static <T> Predicate<T> safe(final ThrowablePredicate<? super T, Throwable> throwablePredicate, final boolean z) {
            return new Predicate<T>() {
                public boolean test(T r2) {
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
                    throw new UnsupportedOperationException("Method not decompiled: com.annimon.stream.function.Predicate.Util.6.test(java.lang.Object):boolean");
                }
            };
        }
    }

    boolean test(T t);
}
