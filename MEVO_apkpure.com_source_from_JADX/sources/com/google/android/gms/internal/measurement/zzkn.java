package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzkn extends zzacd<zzkn> {
    public String zzafa;
    public Long zzaum;
    private Integer zzaun;
    public zzko[] zzauo;
    public zzkm[] zzaup;
    public zzkg[] zzauq;

    public zzkn() {
        this.zzaum = null;
        this.zzafa = null;
        this.zzaun = null;
        this.zzauo = zzko.zzlv();
        this.zzaup = zzkm.zzlu();
        this.zzauq = zzkg.zzlq();
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkn)) {
            return false;
        }
        zzkn zzkn = (zzkn) obj;
        if (this.zzaum == null) {
            if (zzkn.zzaum != null) {
                return false;
            }
        } else if (!this.zzaum.equals(zzkn.zzaum)) {
            return false;
        }
        if (this.zzafa == null) {
            if (zzkn.zzafa != null) {
                return false;
            }
        } else if (!this.zzafa.equals(zzkn.zzafa)) {
            return false;
        }
        if (this.zzaun == null) {
            if (zzkn.zzaun != null) {
                return false;
            }
        } else if (!this.zzaun.equals(zzkn.zzaun)) {
            return false;
        }
        if (!zzach.equals(this.zzauo, zzkn.zzauo) || !zzach.equals(this.zzaup, zzkn.zzaup) || !zzach.equals(this.zzauq, zzkn.zzauq)) {
            return false;
        }
        if (this.zzbzd != null) {
            if (!this.zzbzd.isEmpty()) {
                return this.zzbzd.equals(zzkn.zzbzd);
            }
        }
        return zzkn.zzbzd == null || zzkn.zzbzd.isEmpty();
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.zzaum == null ? 0 : this.zzaum.hashCode())) * 31) + (this.zzafa == null ? 0 : this.zzafa.hashCode())) * 31) + (this.zzaun == null ? 0 : this.zzaun.hashCode())) * 31) + zzach.hashCode(this.zzauo)) * 31) + zzach.hashCode(this.zzaup)) * 31) + zzach.hashCode(this.zzauq)) * 31;
        if (this.zzbzd != null) {
            if (!this.zzbzd.isEmpty()) {
                i = this.zzbzd.hashCode();
            }
        }
        return hashCode + i;
    }

    protected final int zza() {
        int i;
        int zza = super.zza();
        if (this.zzaum != null) {
            zza += zzacb.zzc(1, this.zzaum.longValue());
        }
        if (this.zzafa != null) {
            zza += zzacb.zzc(2, this.zzafa);
        }
        if (this.zzaun != null) {
            zza += zzacb.zzf(3, this.zzaun.intValue());
        }
        if (this.zzauo != null && this.zzauo.length > 0) {
            i = zza;
            for (zzacj zzacj : this.zzauo) {
                if (zzacj != null) {
                    i += zzacb.zzb(4, zzacj);
                }
            }
            zza = i;
        }
        if (this.zzaup != null && this.zzaup.length > 0) {
            i = zza;
            for (zzacj zzacj2 : this.zzaup) {
                if (zzacj2 != null) {
                    i += zzacb.zzb(5, zzacj2);
                }
            }
            zza = i;
        }
        if (this.zzauq != null && this.zzauq.length > 0) {
            for (zzacj zzacj3 : this.zzauq) {
                if (zzacj3 != null) {
                    zza += zzacb.zzb(6, zzacj3);
                }
            }
        }
        return zza;
    }

    public final void zza(zzacb zzacb) throws IOException {
        if (this.zzaum != null) {
            zzacb.zzb(1, this.zzaum.longValue());
        }
        if (this.zzafa != null) {
            zzacb.zzb(2, this.zzafa);
        }
        if (this.zzaun != null) {
            zzacb.zze(3, this.zzaun.intValue());
        }
        if (this.zzauo != null && this.zzauo.length > 0) {
            for (zzacj zzacj : this.zzauo) {
                if (zzacj != null) {
                    zzacb.zza(4, zzacj);
                }
            }
        }
        if (this.zzaup != null && this.zzaup.length > 0) {
            for (zzacj zzacj2 : this.zzaup) {
                if (zzacj2 != null) {
                    zzacb.zza(5, zzacj2);
                }
            }
        }
        if (this.zzauq != null && this.zzauq.length > 0) {
            for (zzacj zzacj3 : this.zzauq) {
                if (zzacj3 != null) {
                    zzacb.zza(6, zzacj3);
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
            if (zzvl == 8) {
                this.zzaum = Long.valueOf(zzaca.zzvo());
            } else if (zzvl == 18) {
                this.zzafa = zzaca.readString();
            } else if (zzvl == 24) {
                this.zzaun = Integer.valueOf(zzaca.zzvn());
            } else if (zzvl == 34) {
                zzvl = zzacm.zzb(zzaca, 34);
                r1 = this.zzauo == null ? 0 : this.zzauo.length;
                r0 = new zzko[(zzvl + r1)];
                if (r1 != 0) {
                    System.arraycopy(this.zzauo, 0, r0, 0, r1);
                }
                while (r1 < r0.length - 1) {
                    r0[r1] = new zzko();
                    zzaca.zza(r0[r1]);
                    zzaca.zzvl();
                    r1++;
                }
                r0[r1] = new zzko();
                zzaca.zza(r0[r1]);
                this.zzauo = r0;
            } else if (zzvl == 42) {
                zzvl = zzacm.zzb(zzaca, 42);
                r1 = this.zzaup == null ? 0 : this.zzaup.length;
                r0 = new zzkm[(zzvl + r1)];
                if (r1 != 0) {
                    System.arraycopy(this.zzaup, 0, r0, 0, r1);
                }
                while (r1 < r0.length - 1) {
                    r0[r1] = new zzkm();
                    zzaca.zza(r0[r1]);
                    zzaca.zzvl();
                    r1++;
                }
                r0[r1] = new zzkm();
                zzaca.zza(r0[r1]);
                this.zzaup = r0;
            } else if (zzvl == 50) {
                zzvl = zzacm.zzb(zzaca, 50);
                r1 = this.zzauq == null ? 0 : this.zzauq.length;
                r0 = new zzkg[(zzvl + r1)];
                if (r1 != 0) {
                    System.arraycopy(this.zzauq, 0, r0, 0, r1);
                }
                while (r1 < r0.length - 1) {
                    r0[r1] = new zzkg();
                    zzaca.zza(r0[r1]);
                    zzaca.zzvl();
                    r1++;
                }
                r0[r1] = new zzkg();
                zzaca.zza(r0[r1]);
                this.zzauq = r0;
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
