package com.mevo.app.data.database.adapters;

public class EncodedBatteryTimestamps {
    public static java.lang.String getStringValue(java.util.List<java.lang.Long> r1) {
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
        r0 = ",";	 Catch:{ Exception -> 0x0007 }
        r1 = android.text.TextUtils.join(r0, r1);	 Catch:{ Exception -> 0x0007 }
        return r1;
    L_0x0007:
        r1 = "";
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mevo.app.data.database.adapters.EncodedBatteryTimestamps.getStringValue(java.util.List):java.lang.String");
    }

    public static java.util.List<java.lang.Long> getModelValue(java.lang.String r1) {
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
        r0 = ",";	 Catch:{ Exception -> 0x0019 }
        r1 = r1.split(r0);	 Catch:{ Exception -> 0x0019 }
        r1 = com.annimon.stream.Stream.of(r1);	 Catch:{ Exception -> 0x0019 }
        r0 = com.mevo.app.data.database.adapters.EncodedBatteryTimestamps$$Lambda$0.$instance;	 Catch:{ Exception -> 0x0019 }
        r1 = r1.mapToLong(r0);	 Catch:{ Exception -> 0x0019 }
        r1 = r1.boxed();	 Catch:{ Exception -> 0x0019 }
        r1 = r1.toList();	 Catch:{ Exception -> 0x0019 }
        return r1;
    L_0x0019:
        r1 = new java.util.ArrayList;
        r0 = 0;
        r1.<init>(r0);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mevo.app.data.database.adapters.EncodedBatteryTimestamps.getModelValue(java.lang.String):java.util.List<java.lang.Long>");
    }
}
