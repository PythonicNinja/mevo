package com.annimon.stream.function;

@FunctionalInterface
public interface Consumer<T> {

    public static class Util {
        private Util() {
        }

        public static <T> Consumer<T> andThen(final Consumer<? super T> consumer, final Consumer<? super T> consumer2) {
            return new Consumer<T>() {
                public void accept(T t) {
                    consumer.accept(t);
                    consumer2.accept(t);
                }
            };
        }

        public static <T> Consumer<T> safe(ThrowableConsumer<? super T, Throwable> throwableConsumer) {
            return safe(throwableConsumer, null);
        }

        public static <T> Consumer<T> safe(final ThrowableConsumer<? super T, Throwable> throwableConsumer, final Consumer<? super T> consumer) {
            return new Consumer<T>() {
                public void accept(T r2) {
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
                    r0 = r1;
                    com.annimon.stream.Objects.requireNonNull(r0);
                    r0 = r1;	 Catch:{ Throwable -> 0x000b }
                    r0.accept(r2);	 Catch:{ Throwable -> 0x000b }
                    goto L_0x0014;
                L_0x000b:
                    r0 = r2;
                    if (r0 == 0) goto L_0x0014;
                L_0x000f:
                    r0 = r2;
                    r0.accept(r2);
                L_0x0014:
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.annimon.stream.function.Consumer.Util.2.accept(java.lang.Object):void");
                }
            };
        }
    }

    void accept(T t);
}
