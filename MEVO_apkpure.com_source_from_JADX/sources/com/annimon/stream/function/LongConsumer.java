package com.annimon.stream.function;

@FunctionalInterface
public interface LongConsumer {

    public static class Util {
        private Util() {
        }

        public static LongConsumer andThen(final LongConsumer longConsumer, final LongConsumer longConsumer2) {
            return new LongConsumer() {
                public void accept(long j) {
                    longConsumer.accept(j);
                    longConsumer2.accept(j);
                }
            };
        }

        public static LongConsumer safe(ThrowableLongConsumer<Throwable> throwableLongConsumer) {
            return safe(throwableLongConsumer, null);
        }

        public static LongConsumer safe(final ThrowableLongConsumer<Throwable> throwableLongConsumer, final LongConsumer longConsumer) {
            return new LongConsumer() {
                public void accept(long r2) {
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
                    r0 = r1;	 Catch:{ Throwable -> 0x0006 }
                    r0.accept(r2);	 Catch:{ Throwable -> 0x0006 }
                    goto L_0x000f;
                L_0x0006:
                    r0 = r2;
                    if (r0 == 0) goto L_0x000f;
                L_0x000a:
                    r0 = r2;
                    r0.accept(r2);
                L_0x000f:
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.annimon.stream.function.LongConsumer.Util.2.accept(long):void");
                }
            };
        }
    }

    void accept(long j);
}
