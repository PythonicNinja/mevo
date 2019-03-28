package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzkw extends zzacd<zzkw> {
    private static volatile zzkw[] zzawp;
    private Integer zzaux;
    private long[] zzawq;

    public zzkw() {
        this.zzaux = null;
        this.zzawq = zzacm.zzbzt;
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public static zzkw[] zzmb() {
        if (zzawp == null) {
            synchronized (zzach.zzbzn) {
                if (zzawp == null) {
                    zzawp = new zzkw[0];
                }
            }
        }
        return zzawp;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkw)) {
            return false;
        }
        zzkw zzkw = (zzkw) obj;
        if (this.zzaux == null) {
            if (zzkw.zzaux != null) {
                return false;
            }
        } else if (!this.zzaux.equals(zzkw.zzaux)) {
            return false;
        }
        if (!zzach.equals(this.zzawq, zzkw.zzawq)) {
            return false;
        }
        if (this.zzbzd != null) {
            if (!this.zzbzd.isEmpty()) {
                return this.zzbzd.equals(zzkw.zzbzd);
            }
        }
        return zzkw.zzbzd == null || zzkw.zzbzd.isEmpty();
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.zzaux == null ? 0 : this.zzaux.hashCode())) * 31) + zzach.hashCode(this.zzawq)) * 31;
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
        if (this.zzawq == null || this.zzawq.length <= 0) {
            return zza;
        }
        int i = 0;
        for (long zzat : this.zzawq) {
            i += zzacb.zzat(zzat);
        }
        return (zza + i) + (this.zzawq.length * 1);
    }

    public final void zza(zzacb zzacb) throws IOException {
        if (this.zzaux != null) {
            zzacb.zze(1, this.zzaux.intValue());
        }
        if (this.zzawq != null && this.zzawq.length > 0) {
            for (long zzb : this.zzawq) {
                zzacb.zzb(2, zzb);
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
            if (zzvl == 8) {
                this.zzaux = Integer.valueOf(zzaca.zzvn());
            } else if (zzvl == 16) {
                zzvl = zzacm.zzb(zzaca, 16);
                r1 = this.zzawq == null ? 0 : this.zzawq.length;
                Object obj = new long[(zzvl + r1)];
                if (r1 != 0) {
                    System.arraycopy(this.zzawq, 0, obj, 0, r1);
                }
                while (r1 < obj.length - 1) {
                    obj[r1] = zzaca.zzvo();
                    zzaca.zzvl();
                    r1++;
                }
                obj[r1] = zzaca.zzvo();
                this.zzawq = obj;
            } else if (zzvl == 18) {
                zzvl = zzaca.zzaf(zzaca.zzvn());
                r1 = zzaca.getPosition();
                int i = 0;
                while (zzaca.zzvr() > 0) {
                    zzaca.zzvo();
                    i++;
                }
                zzaca.zzam(r1);
                r1 = this.zzawq == null ? 0 : this.zzawq.length;
                Object obj2 = new long[(i + r1)];
                if (r1 != 0) {
                    System.arraycopy(this.zzawq, 0, obj2, 0, r1);
                }
                while (r1 < obj2.length) {
                    obj2[r1] = zzaca.zzvo();
                    r1++;
                }
                this.zzawq = obj2;
                zzaca.zzal(zzvl);
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
