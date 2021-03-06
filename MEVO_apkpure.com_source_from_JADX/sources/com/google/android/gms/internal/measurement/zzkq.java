package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzkq extends zzacd<zzkq> {
    private static volatile zzkq[] zzauw;
    public Integer zzaux;
    public Long zzauy;

    public zzkq() {
        this.zzaux = null;
        this.zzauy = null;
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public static zzkq[] zzlx() {
        if (zzauw == null) {
            synchronized (zzach.zzbzn) {
                if (zzauw == null) {
                    zzauw = new zzkq[0];
                }
            }
        }
        return zzauw;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkq)) {
            return false;
        }
        zzkq zzkq = (zzkq) obj;
        if (this.zzaux == null) {
            if (zzkq.zzaux != null) {
                return false;
            }
        } else if (!this.zzaux.equals(zzkq.zzaux)) {
            return false;
        }
        if (this.zzauy == null) {
            if (zzkq.zzauy != null) {
                return false;
            }
        } else if (!this.zzauy.equals(zzkq.zzauy)) {
            return false;
        }
        if (this.zzbzd != null) {
            if (!this.zzbzd.isEmpty()) {
                return this.zzbzd.equals(zzkq.zzbzd);
            }
        }
        return zzkq.zzbzd == null || zzkq.zzbzd.isEmpty();
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.zzaux == null ? 0 : this.zzaux.hashCode())) * 31) + (this.zzauy == null ? 0 : this.zzauy.hashCode())) * 31;
        if (this.zzbzd != null) {
            if (!this.zzbzd.isEmpty()) {
                i = this.zzbzd.hashCode();
            }
        }
        return hashCode + i;
    }

    protected final int zza() {
        int zza = super.zza();
        if (this.zzaux != null) {
            zza += zzacb.zzf(1, this.zzaux.intValue());
        }
        return this.zzauy != null ? zza + zzacb.zzc(2, this.zzauy.longValue()) : zza;
    }

    public final void zza(zzacb zzacb) throws IOException {
        if (this.zzaux != null) {
            zzacb.zze(1, this.zzaux.intValue());
        }
        if (this.zzauy != null) {
            zzacb.zzb(2, this.zzauy.longValue());
        }
        super.zza(zzacb);
    }

    public final /* synthetic */ zzacj zzb(zzaca zzaca) throws IOException {
        while (true) {
            int zzvl = zzaca.zzvl();
            if (zzvl == 0) {
                return this;
            }
            if (zzvl == 8) {
                this.zzaux = Integer.valueOf(zzaca.zzvn());
            } else if (zzvl == 16) {
                this.zzauy = Long.valueOf(zzaca.zzvo());
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
