package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzkj extends zzacd<zzkj> {
    public Integer zzatw;
    public Boolean zzatx;
    public String zzaty;
    public String zzatz;
    public String zzaua;

    public zzkj() {
        this.zzatw = null;
        this.zzatx = null;
        this.zzaty = null;
        this.zzatz = null;
        this.zzaua = null;
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    private final com.google.android.gms.internal.measurement.zzkj zzd(com.google.android.gms.internal.measurement.zzaca r7) throws java.io.IOException {
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
        r6 = this;
    L_0x0000:
        r0 = r7.zzvl();
        if (r0 == 0) goto L_0x0075;
    L_0x0006:
        r1 = 8;
        if (r0 == r1) goto L_0x0041;
    L_0x000a:
        r1 = 16;
        if (r0 == r1) goto L_0x0036;
    L_0x000e:
        r1 = 26;
        if (r0 == r1) goto L_0x002f;
    L_0x0012:
        r1 = 34;
        if (r0 == r1) goto L_0x0028;
    L_0x0016:
        r1 = 42;
        if (r0 == r1) goto L_0x0021;
    L_0x001a:
        r0 = super.zza(r7, r0);
        if (r0 != 0) goto L_0x0000;
    L_0x0020:
        return r6;
    L_0x0021:
        r0 = r7.readString();
        r6.zzaua = r0;
        goto L_0x0000;
    L_0x0028:
        r0 = r7.readString();
        r6.zzatz = r0;
        goto L_0x0000;
    L_0x002f:
        r0 = r7.readString();
        r6.zzaty = r0;
        goto L_0x0000;
    L_0x0036:
        r0 = r7.zzvm();
        r0 = java.lang.Boolean.valueOf(r0);
        r6.zzatx = r0;
        goto L_0x0000;
    L_0x0041:
        r1 = r7.getPosition();
        r2 = r7.zzvn();	 Catch:{ IllegalArgumentException -> 0x006e }
        if (r2 < 0) goto L_0x0055;	 Catch:{ IllegalArgumentException -> 0x006e }
    L_0x004b:
        r3 = 4;	 Catch:{ IllegalArgumentException -> 0x006e }
        if (r2 > r3) goto L_0x0055;	 Catch:{ IllegalArgumentException -> 0x006e }
    L_0x004e:
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ IllegalArgumentException -> 0x006e }
        r6.zzatw = r2;	 Catch:{ IllegalArgumentException -> 0x006e }
        goto L_0x0000;	 Catch:{ IllegalArgumentException -> 0x006e }
    L_0x0055:
        r3 = new java.lang.IllegalArgumentException;	 Catch:{ IllegalArgumentException -> 0x006e }
        r4 = 46;	 Catch:{ IllegalArgumentException -> 0x006e }
        r5 = new java.lang.StringBuilder;	 Catch:{ IllegalArgumentException -> 0x006e }
        r5.<init>(r4);	 Catch:{ IllegalArgumentException -> 0x006e }
        r5.append(r2);	 Catch:{ IllegalArgumentException -> 0x006e }
        r2 = " is not a valid enum ComparisonType";	 Catch:{ IllegalArgumentException -> 0x006e }
        r5.append(r2);	 Catch:{ IllegalArgumentException -> 0x006e }
        r2 = r5.toString();	 Catch:{ IllegalArgumentException -> 0x006e }
        r3.<init>(r2);	 Catch:{ IllegalArgumentException -> 0x006e }
        throw r3;	 Catch:{ IllegalArgumentException -> 0x006e }
    L_0x006e:
        r7.zzam(r1);
        r6.zza(r7, r0);
        goto L_0x0000;
    L_0x0075:
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzkj.zzd(com.google.android.gms.internal.measurement.zzaca):com.google.android.gms.internal.measurement.zzkj");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkj)) {
            return false;
        }
        zzkj zzkj = (zzkj) obj;
        if (this.zzatw == null) {
            if (zzkj.zzatw != null) {
                return false;
            }
        } else if (!this.zzatw.equals(zzkj.zzatw)) {
            return false;
        }
        if (this.zzatx == null) {
            if (zzkj.zzatx != null) {
                return false;
            }
        } else if (!this.zzatx.equals(zzkj.zzatx)) {
            return false;
        }
        if (this.zzaty == null) {
            if (zzkj.zzaty != null) {
                return false;
            }
        } else if (!this.zzaty.equals(zzkj.zzaty)) {
            return false;
        }
        if (this.zzatz == null) {
            if (zzkj.zzatz != null) {
                return false;
            }
        } else if (!this.zzatz.equals(zzkj.zzatz)) {
            return false;
        }
        if (this.zzaua == null) {
            if (zzkj.zzaua != null) {
                return false;
            }
        } else if (!this.zzaua.equals(zzkj.zzaua)) {
            return false;
        }
        if (this.zzbzd != null) {
            if (!this.zzbzd.isEmpty()) {
                return this.zzbzd.equals(zzkj.zzbzd);
            }
        }
        return zzkj.zzbzd == null || zzkj.zzbzd.isEmpty();
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + (this.zzatw == null ? 0 : this.zzatw.intValue())) * 31) + (this.zzatx == null ? 0 : this.zzatx.hashCode())) * 31) + (this.zzaty == null ? 0 : this.zzaty.hashCode())) * 31) + (this.zzatz == null ? 0 : this.zzatz.hashCode())) * 31) + (this.zzaua == null ? 0 : this.zzaua.hashCode())) * 31;
        if (this.zzbzd != null) {
            if (!this.zzbzd.isEmpty()) {
                i = this.zzbzd.hashCode();
            }
        }
        return hashCode + i;
    }

    protected final int zza() {
        int zza = super.zza();
        if (this.zzatw != null) {
            zza += zzacb.zzf(1, this.zzatw.intValue());
        }
        if (this.zzatx != null) {
            this.zzatx.booleanValue();
            zza += zzacb.zzaq(2) + 1;
        }
        if (this.zzaty != null) {
            zza += zzacb.zzc(3, this.zzaty);
        }
        if (this.zzatz != null) {
            zza += zzacb.zzc(4, this.zzatz);
        }
        return this.zzaua != null ? zza + zzacb.zzc(5, this.zzaua) : zza;
    }

    public final void zza(zzacb zzacb) throws IOException {
        if (this.zzatw != null) {
            zzacb.zze(1, this.zzatw.intValue());
        }
        if (this.zzatx != null) {
            zzacb.zza(2, this.zzatx.booleanValue());
        }
        if (this.zzaty != null) {
            zzacb.zzb(3, this.zzaty);
        }
        if (this.zzatz != null) {
            zzacb.zzb(4, this.zzatz);
        }
        if (this.zzaua != null) {
            zzacb.zzb(5, this.zzaua);
        }
        super.zza(zzacb);
    }

    public final /* synthetic */ zzacj zzb(zzaca zzaca) throws IOException {
        return zzd(zzaca);
    }
}
