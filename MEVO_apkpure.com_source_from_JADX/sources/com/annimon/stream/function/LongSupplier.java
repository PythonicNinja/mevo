package com.annimon.stream.function;

@FunctionalInterface
public interface LongSupplier {

    public static class Util {
        private Util() {
        }

        public static LongSupplier safe(ThrowableLongSupplier<Throwable> throwableLongSupplier) {
            return safe(throwableLongSupplier, 0);
        }

        public static LongSupplier safe(final ThrowableLongSupplier<Throwable> throwableLongSupplier, final long j) {
            return new LongSupplier() {
                public long getAsLong() {
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
                    r2 = this;
                    r0 = r1;	 Catch:{ Throwable -> 0x0007 }
                    r0 = r0.getAsLong();	 Catch:{ Throwable -> 0x0007 }
                    return r0;
                L_0x0007:
                    r0 = r2;
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.annimon.stream.function.LongSupplier.Util.1.getAsLong():long");
                }
            };
        }
    }

    long getAsLong();
}
