package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzkt extends zzacd<zzkt> {
    public zzku[] zzavf;

    public zzkt() {
        this.zzavf = zzku.zzma();
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkt)) {
            return false;
        }
        zzkt zzkt = (zzkt) obj;
        if (!zzach.equals(this.zzavf, zzkt.zzavf)) {
            return false;
        }
        if (this.zzbzd != null) {
            if (!this.zzbzd.isEmpty()) {
                return this.zzbzd.equals(zzkt.zzbzd);
            }
        }
        return zzkt.zzbzd == null || zzkt.zzbzd.isEmpty();
    }

    public final int hashCode() {
        int hashCode;
        int hashCode2 = (((getClass().getName().hashCode() + 527) * 31) + zzach.hashCode(this.zzavf)) * 31;
        if (this.zzbzd != null) {
            if (!this.zzbzd.isEmpty()) {
                hashCode = this.zzbzd.hashCode();
                return hashCode2 + hashCode;
            }
        }
        hashCode = 0;
        return hashCode2 + hashCode;
    }

    protected final int zza() {
        int zza = super.zza();
        if (this.zzavf != null && this.zzavf.length > 0) {
            for (zzacj zzacj : this.zzavf) {
                if (zzacj != null) {
                    zza += zzacb.zzb(1, zzacj);
                }
            }
        }
        return zza;
    }

    public final void zza(zzacb zzacb) throws IOException {
        if (this.zzavf != null && this.zzavf.length > 0) {
            for (zzacj zzacj : this.zzavf) {
                if (zzacj != null) {
                    zzacb.zza(1, zzacj);
                }
            }
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
                zzvl = zzacm.zzb(zzaca, 10);
                int length = this.zzavf == null ? 0 : this.zzavf.length;
                Object obj = new zzku[(zzvl + length)];
                if (length != 0) {
                    System.arraycopy(this.zzavf, 0, obj, 0, length);
                }
                while (length < obj.length - 1) {
                    obj[length] = new zzku();
                    zzaca.zza(obj[length]);
                    zzaca.zzvl();
                    length++;
                }
                obj[length] = new zzku();
                zzaca.zza(obj[length]);
                this.zzavf = obj;
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
