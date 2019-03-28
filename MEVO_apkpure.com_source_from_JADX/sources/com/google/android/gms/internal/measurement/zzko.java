package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzko extends zzacd<zzko> {
    private static volatile zzko[] zzaur;
    public String value;
    public String zzny;

    public zzko() {
        this.zzny = null;
        this.value = null;
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public static zzko[] zzlv() {
        if (zzaur == null) {
            synchronized (zzach.zzbzn) {
                if (zzaur == null) {
                    zzaur = new zzko[0];
                }
            }
        }
        return zzaur;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzko)) {
            return false;
        }
        zzko zzko = (zzko) obj;
        if (this.zzny == null) {
            if (zzko.zzny != null) {
                return false;
            }
        } else if (!this.zzny.equals(zzko.zzny)) {
            return false;
        }
        if (this.value == null) {
            if (zzko.value != null) {
                return false;
            }
        } else if (!this.value.equals(zzko.value)) {
            return false;
        }
        if (this.zzbzd != null) {
            if (!this.zzbzd.isEmpty()) {
                return this.zzbzd.equals(zzko.zzbzd);
            }
        }
        return zzko.zzbzd == null || zzko.zzbzd.isEmpty();
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.zzny == null ? 0 : this.zzny.hashCode())) * 31) + (this.value == null ? 0 : this.value.hashCode())) * 31;
        if (this.zzbzd != null) {
            if (!this.zzbzd.isEmpty()) {
                i = this.zzbzd.hashCode();
            }
        }
        return hashCode + i;
    }

    protected final int zza() {
        int zza = super.zza();
        if (this.zzny != null) {
            zza += zzacb.zzc(1, this.zzny);
        }
        return this.value != null ? zza + zzacb.zzc(2, this.value) : zza;
    }

    public final void zza(zzacb zzacb) throws IOException {
        if (this.zzny != null) {
            zzacb.zzb(1, this.zzny);
        }
        if (this.value != null) {
            zzacb.zzb(2, this.value);
        }
        super.zza(zzacb);
    }

    public final /* synthetic */ zzacj zzb(zzaca zzaca) throws IOException {
        while (true) {
            int zzvl = zzaca.zzvl();
            if (zzvl == 0) {
                return this;
            }
            if (zzvl == 10) {
                this.zzny = zzaca.readString();
            } else if (zzvl == 18) {
                this.value = zzaca.readString();
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
