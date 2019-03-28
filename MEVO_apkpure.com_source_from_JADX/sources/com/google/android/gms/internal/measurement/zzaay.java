package com.google.android.gms.internal.measurement;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzaay {
    private static final zzaay zzbvx = new zzaay();
    private final zzabb zzbvy;
    private final ConcurrentMap<Class<?>, zzaba<?>> zzbvz = new ConcurrentHashMap();

    private zzaay() {
        String[] strArr = new String[]{"com.google.protobuf.AndroidProto3SchemaFactory"};
        zzabb zzabb = null;
        for (int i = 0; i <= 0; i++) {
            zzabb = zzfq(strArr[0]);
            if (zzabb != null) {
                break;
            }
        }
        if (zzabb == null) {
            zzabb = new zzaai();
        }
        this.zzbvy = zzabb;
    }

    private static com.google.android.gms.internal.measurement.zzabb zzfq(java.lang.String r2) {
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
        r2 = java.lang.Class.forName(r2);	 Catch:{ Throwable -> 0x0014 }
        r0 = 0;	 Catch:{ Throwable -> 0x0014 }
        r1 = new java.lang.Class[r0];	 Catch:{ Throwable -> 0x0014 }
        r2 = r2.getConstructor(r1);	 Catch:{ Throwable -> 0x0014 }
        r0 = new java.lang.Object[r0];	 Catch:{ Throwable -> 0x0014 }
        r2 = r2.newInstance(r0);	 Catch:{ Throwable -> 0x0014 }
        r2 = (com.google.android.gms.internal.measurement.zzabb) r2;	 Catch:{ Throwable -> 0x0014 }
        return r2;
    L_0x0014:
        r2 = 0;
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzaay.zzfq(java.lang.String):com.google.android.gms.internal.measurement.zzabb");
    }

    public static zzaay zzus() {
        return zzbvx;
    }

    public final <T> zzaba<T> zzt(T t) {
        Class cls = t.getClass();
        zzzw.zza(cls, "messageType");
        zzaba<T> zzaba = (zzaba) this.zzbvz.get(cls);
        if (zzaba != null) {
            return zzaba;
        }
        zzaba = this.zzbvy.zzg(cls);
        zzzw.zza(cls, "messageType");
        zzzw.zza(zzaba, "schema");
        zzaba<T> zzaba2 = (zzaba) this.zzbvz.putIfAbsent(cls, zzaba);
        return zzaba2 != null ? zzaba2 : zzaba;
    }
}
