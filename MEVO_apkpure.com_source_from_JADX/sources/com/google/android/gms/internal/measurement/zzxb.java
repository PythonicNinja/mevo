package com.google.android.gms.internal.measurement;

final class zzxb extends zzwx<Long> {
    zzxb(zzxh zzxh, String str, Long l) {
        super(zzxh, str, l);
    }

    private final java.lang.Long zzfb(java.lang.String r5) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r4 = this;
        r0 = java.lang.Long.parseLong(r5);	 Catch:{ NumberFormatException -> 0x0009 }
        r0 = java.lang.Long.valueOf(r0);	 Catch:{ NumberFormatException -> 0x0009 }
        return r0;
    L_0x0009:
        r0 = "PhenotypeFlag";
        r1 = r4.zzbpk;
        r2 = java.lang.String.valueOf(r1);
        r2 = r2.length();
        r2 = r2 + 25;
        r3 = java.lang.String.valueOf(r5);
        r3 = r3.length();
        r2 = r2 + r3;
        r3 = new java.lang.StringBuilder;
        r3.<init>(r2);
        r2 = "Invalid long value for ";
        r3.append(r2);
        r3.append(r1);
        r1 = ": ";
        r3.append(r1);
        r3.append(r5);
        r5 = r3.toString();
        android.util.Log.e(r0, r5);
        r5 = 0;
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzxb.zzfb(java.lang.String):java.lang.Long");
    }

    protected final /* synthetic */ Object zzfa(String str) {
        return zzfb(str);
    }
}
