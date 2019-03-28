package com.annimon.stream.function;

@FunctionalInterface
public interface Function<T, R> {

    public static class Util {
        private Util() {
        }

        public static <V, T, R> Function<V, R> compose(Function<? super T, ? extends R> function, Function<? super V, ? extends T> function2) {
            return andThen(function2, function);
        }

        public static <T, R, V> Function<T, V> andThen(final Function<? super T, ? extends R> function, final Function<? super R, ? extends V> function2) {
            return new Function<T, V>() {
                public V apply(T t) {
                    return function2.apply(function.apply(t));
                }
            };
        }

        public static <T, R> Function<T, R> safe(ThrowableFunction<? super T, ? extends R, Throwable> throwableFunction) {
            return safe(throwableFunction, null);
        }

        public static <T, R> Function<T, R> safe(final ThrowableFunction<? super T, ? extends R, Throwable> throwableFunction, final R r) {
            return new Function<T, R>() {
                public R apply(T r2) {
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
                    r2 = r0.apply(r2);	 Catch:{ Throwable -> 0x0007 }
                    return r2;
                L_0x0007:
                    r2 = r2;
                    return r2;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.annimon.stream.function.Function.Util.2.apply(java.lang.Object):R");
                }
            };
        }
    }

    R apply(T t);
}
