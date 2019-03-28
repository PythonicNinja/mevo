package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzzv.zzb;

final class zzaai implements zzabb {
    private static final zzaap zzbvl = new zzaaj();
    private final zzaap zzbvk;

    public zzaai() {
        this(new zzaak(zzzu.zzua(), zzuh()));
    }

    private zzaai(zzaap zzaap) {
        this.zzbvk = (zzaap) zzzw.zza(zzaap, "messageInfoFactory");
    }

    private static boolean zza(zzaao zzaao) {
        return zzaao.zzul() == zzb.zzbur;
    }

    private static com.google.android.gms.internal.measurement.zzaap zzuh() {
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
        r0 = "com.google.protobuf.DescriptorMessageInfoFactory";	 Catch:{ Exception -> 0x0019 }
        r0 = java.lang.Class.forName(r0);	 Catch:{ Exception -> 0x0019 }
        r1 = "getInstance";	 Catch:{ Exception -> 0x0019 }
        r2 = 0;	 Catch:{ Exception -> 0x0019 }
        r3 = new java.lang.Class[r2];	 Catch:{ Exception -> 0x0019 }
        r0 = r0.getDeclaredMethod(r1, r3);	 Catch:{ Exception -> 0x0019 }
        r1 = 0;	 Catch:{ Exception -> 0x0019 }
        r2 = new java.lang.Object[r2];	 Catch:{ Exception -> 0x0019 }
        r0 = r0.invoke(r1, r2);	 Catch:{ Exception -> 0x0019 }
        r0 = (com.google.android.gms.internal.measurement.zzaap) r0;	 Catch:{ Exception -> 0x0019 }
        return r0;
    L_0x0019:
        r0 = zzbvl;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzaai.zzuh():com.google.android.gms.internal.measurement.zzaap");
    }

    public final <T> zzaba<T> zzg(Class<T> cls) {
        zzabc.zzh(cls);
        zzaao zze = this.zzbvk.zze(cls);
        if (zze.zzum()) {
            return zzzv.class.isAssignableFrom(cls) ? zzaau.zza(zzabc.zzuv(), zzzq.zztv(), zze.zzun()) : zzaau.zza(zzabc.zzut(), zzzq.zztw(), zze.zzun());
        } else {
            if (zzzv.class.isAssignableFrom(cls)) {
                if (zza(zze)) {
                    return zzaat.zza(cls, zze, zzaax.zzuq(), zzaae.zzug(), zzabc.zzuv(), zzzq.zztv(), zzaan.zzuj());
                }
                return zzaat.zza(cls, zze, zzaax.zzuq(), zzaae.zzug(), zzabc.zzuv(), null, zzaan.zzuj());
            } else if (zza(zze)) {
                return zzaat.zza(cls, zze, zzaax.zzup(), zzaae.zzuf(), zzabc.zzut(), zzzq.zztw(), zzaan.zzui());
            } else {
                return zzaat.zza(cls, zze, zzaax.zzup(), zzaae.zzuf(), zzabc.zzuu(), null, zzaan.zzui());
            }
        }
    }
}
