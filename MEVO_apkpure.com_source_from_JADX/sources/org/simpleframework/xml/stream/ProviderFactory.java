package org.simpleframework.xml.stream;

final class ProviderFactory {
    ProviderFactory() {
    }

    public static org.simpleframework.xml.stream.Provider getInstance() {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r0 = new org.simpleframework.xml.stream.StreamProvider;	 Catch:{ Throwable -> 0x0006 }
        r0.<init>();	 Catch:{ Throwable -> 0x0006 }
        return r0;
    L_0x0006:
        r0 = new org.simpleframework.xml.stream.PullProvider;	 Catch:{ Throwable -> 0x000c }
        r0.<init>();	 Catch:{ Throwable -> 0x000c }
        return r0;
    L_0x000c:
        r0 = new org.simpleframework.xml.stream.DocumentProvider;
        r0.<init>();
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.simpleframework.xml.stream.ProviderFactory.getInstance():org.simpleframework.xml.stream.Provider");
    }
}
