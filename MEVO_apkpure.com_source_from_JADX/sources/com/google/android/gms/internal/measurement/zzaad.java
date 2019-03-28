package com.google.android.gms.internal.measurement;

public class zzaad {
    private static final zzzn zzbvd = zzzn.zztt();
    private zzzb zzbve;
    private volatile zzaaq zzbvf;
    private volatile zzzb zzbvg;

    private final com.google.android.gms.internal.measurement.zzaaq zzb(com.google.android.gms.internal.measurement.zzaaq r2) {
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
        r1 = this;
        r0 = r1.zzbvf;
        if (r0 != 0) goto L_0x001c;
    L_0x0004:
        monitor-enter(r1);
        r0 = r1.zzbvf;	 Catch:{ all -> 0x0019 }
        if (r0 == 0) goto L_0x000b;	 Catch:{ all -> 0x0019 }
    L_0x0009:
        monitor-exit(r1);	 Catch:{ all -> 0x0019 }
        goto L_0x001c;
    L_0x000b:
        r1.zzbvf = r2;	 Catch:{ zzzy -> 0x0012 }
        r0 = com.google.android.gms.internal.measurement.zzzb.zzbte;	 Catch:{ zzzy -> 0x0012 }
        r1.zzbvg = r0;	 Catch:{ zzzy -> 0x0012 }
        goto L_0x0009;
    L_0x0012:
        r1.zzbvf = r2;	 Catch:{ all -> 0x0019 }
        r2 = com.google.android.gms.internal.measurement.zzzb.zzbte;	 Catch:{ all -> 0x0019 }
        r1.zzbvg = r2;	 Catch:{ all -> 0x0019 }
        goto L_0x0009;	 Catch:{ all -> 0x0019 }
    L_0x0019:
        r2 = move-exception;	 Catch:{ all -> 0x0019 }
        monitor-exit(r1);	 Catch:{ all -> 0x0019 }
        throw r2;
    L_0x001c:
        r2 = r1.zzbvf;
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzaad.zzb(com.google.android.gms.internal.measurement.zzaaq):com.google.android.gms.internal.measurement.zzaaq");
    }

    private final zzzb zzue() {
        if (this.zzbvg != null) {
            return this.zzbvg;
        }
        synchronized (this) {
            if (this.zzbvg != null) {
                zzzb zzzb = this.zzbvg;
                return zzzb;
            }
            this.zzbvg = this.zzbvf == null ? zzzb.zzbte : this.zzbvf.zzue();
            zzzb = this.zzbvg;
            return zzzb;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzaad)) {
            return false;
        }
        zzaad zzaad = (zzaad) obj;
        zzaaq zzaaq = this.zzbvf;
        zzaaq zzaaq2 = zzaad.zzbvf;
        return (zzaaq == null && zzaaq2 == null) ? zzue().equals(zzaad.zzue()) : (zzaaq == null || zzaaq2 == null) ? zzaaq != null ? zzaaq.equals(zzaad.zzb(zzaaq.zzuo())) : zzb(zzaaq2.zzuo()).equals(zzaaq2) : zzaaq.equals(zzaaq2);
    }

    public int hashCode() {
        return 1;
    }

    public final zzaaq zzc(zzaaq zzaaq) {
        zzaaq zzaaq2 = this.zzbvf;
        this.zzbve = null;
        this.zzbvg = null;
        this.zzbvf = zzaaq;
        return zzaaq2;
    }
}
