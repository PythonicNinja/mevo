package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzkr extends zzacd<zzkr> {
    private static volatile zzkr[] zzauz;
    public Integer count;
    public String name;
    public zzks[] zzava;
    public Long zzavb;
    public Long zzavc;

    public zzkr() {
        this.zzava = zzks.zzlz();
        this.name = null;
        this.zzavb = null;
        this.zzavc = null;
        this.count = null;
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public static zzkr[] zzly() {
        if (zzauz == null) {
            synchronized (zzach.zzbzn) {
                if (zzauz == null) {
                    zzauz = new zzkr[0];
                }
            }
        }
        return zzauz;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkr)) {
            return false;
        }
        zzkr zzkr = (zzkr) obj;
        if (!zzach.equals(this.zzava, zzkr.zzava)) {
            return false;
        }
        if (this.name == null) {
            if (zzkr.name != null) {
                return false;
            }
        } else if (!this.name.equals(zzkr.name)) {
            return false;
        }
        if (this.zzavb == null) {
            if (zzkr.zzavb != null) {
                return false;
            }
        } else if (!this.zzavb.equals(zzkr.zzavb)) {
            return false;
        }
        if (this.zzavc == null) {
            if (zzkr.zzavc != null) {
                return false;
            }
        } else if (!this.zzavc.equals(zzkr.zzavc)) {
            return false;
        }
        if (this.count == null) {
            if (zzkr.count != null) {
                return false;
            }
        } else if (!this.count.equals(zzkr.count)) {
            return false;
        }
        if (this.zzbzd != null) {
            if (!this.zzbzd.isEmpty()) {
                return this.zzbzd.equals(zzkr.zzbzd);
            }
        }
        return zzkr.zzbzd == null || zzkr.zzbzd.isEmpty();
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + zzach.hashCode(this.zzava)) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.zzavb == null ? 0 : this.zzavb.hashCode())) * 31) + (this.zzavc == null ? 0 : this.zzavc.hashCode())) * 31) + (this.count == null ? 0 : this.count.hashCode())) * 31;
        if (this.zzbzd != null) {
            if (!this.zzbzd.isEmpty()) {
                i = this.zzbzd.hashCode();
            }
        }
        return hashCode + i;
    }

    protected final int zza() {
        int zza = super.zza();
        if (this.zzava != null && this.zzava.length > 0) {
            for (zzacj zzacj : this.zzava) {
                if (zzacj != null) {
                    zza += zzacb.zzb(1, zzacj);
                }
            }
        }
        if (this.name != null) {
            zza += zzacb.zzc(2, this.name);
        }
        if (this.zzavb != null) {
            zza += zzacb.zzc(3, this.zzavb.longValue());
        }
        if (this.zzavc != null) {
            zza += zzacb.zzc(4, this.zzavc.longValue());
        }
        return this.count != null ? zza + zzacb.zzf(5, this.count.intValue()) : zza;
    }

    public final void zza(zzacb zzacb) throws IOException {
        if (this.zzava != null && this.zzava.length > 0) {
            for (zzacj zzacj : this.zzava) {
                if (zzacj != null) {
                    zzacb.zza(1, zzacj);
                }
            }
        }
        if (this.name != null) {
            zzacb.zzb(2, this.name);
        }
        if (this.zzavb != null) {
            zzacb.zzb(3, this.zzavb.longValue());
        }
        if (this.zzavc != null) {
            zzacb.zzb(4, this.zzavc.longValue());
        }
        if (this.count != null) {
            zzacb.zze(5, this.count.intValue());
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
                int length = this.zzava == null ? 0 : this.zzava.length;
                Object obj = new zzks[(zzvl + length)];
                if (length != 0) {
                    System.arraycopy(this.zzava, 0, obj, 0, length);
                }
                while (length < obj.length - 1) {
                    obj[length] = new zzks();
                    zzaca.zza(obj[length]);
                    zzaca.zzvl();
                    length++;
                }
                obj[length] = new zzks();
                zzaca.zza(obj[length]);
                this.zzava = obj;
            } else if (zzvl == 18) {
                this.name = zzaca.readString();
            } else if (zzvl == 24) {
                this.zzavb = Long.valueOf(zzaca.zzvo());
            } else if (zzvl == 32) {
                this.zzavc = Long.valueOf(zzaca.zzvo());
            } else if (zzvl == 40) {
                this.count = Integer.valueOf(zzaca.zzvn());
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
