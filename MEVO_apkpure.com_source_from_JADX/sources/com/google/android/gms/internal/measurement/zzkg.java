package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzkg extends zzacd<zzkg> {
    private static volatile zzkg[] zzatd;
    public Integer zzate;
    public zzkk[] zzatf;
    public zzkh[] zzatg;
    public Boolean zzath;
    public Boolean zzati;

    public zzkg() {
        this.zzate = null;
        this.zzatf = zzkk.zzlt();
        this.zzatg = zzkh.zzlr();
        this.zzath = null;
        this.zzati = null;
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public static zzkg[] zzlq() {
        if (zzatd == null) {
            synchronized (zzach.zzbzn) {
                if (zzatd == null) {
                    zzatd = new zzkg[0];
                }
            }
        }
        return zzatd;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkg)) {
            return false;
        }
        zzkg zzkg = (zzkg) obj;
        if (this.zzate == null) {
            if (zzkg.zzate != null) {
                return false;
            }
        } else if (!this.zzate.equals(zzkg.zzate)) {
            return false;
        }
        if (!zzach.equals(this.zzatf, zzkg.zzatf) || !zzach.equals(this.zzatg, zzkg.zzatg)) {
            return false;
        }
        if (this.zzath == null) {
            if (zzkg.zzath != null) {
                return false;
            }
        } else if (!this.zzath.equals(zzkg.zzath)) {
            return false;
        }
        if (this.zzati == null) {
            if (zzkg.zzati != null) {
                return false;
            }
        } else if (!this.zzati.equals(zzkg.zzati)) {
            return false;
        }
        if (this.zzbzd != null) {
            if (!this.zzbzd.isEmpty()) {
                return this.zzbzd.equals(zzkg.zzbzd);
            }
        }
        return zzkg.zzbzd == null || zzkg.zzbzd.isEmpty();
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + (this.zzate == null ? 0 : this.zzate.hashCode())) * 31) + zzach.hashCode(this.zzatf)) * 31) + zzach.hashCode(this.zzatg)) * 31) + (this.zzath == null ? 0 : this.zzath.hashCode())) * 31) + (this.zzati == null ? 0 : this.zzati.hashCode())) * 31;
        if (this.zzbzd != null) {
            if (!this.zzbzd.isEmpty()) {
                i = this.zzbzd.hashCode();
            }
        }
        return hashCode + i;
    }

    protected final int zza() {
        int zza = super.zza();
        if (this.zzate != null) {
            zza += zzacb.zzf(1, this.zzate.intValue());
        }
        if (this.zzatf != null && this.zzatf.length > 0) {
            int i = zza;
            for (zzacj zzacj : this.zzatf) {
                if (zzacj != null) {
                    i += zzacb.zzb(2, zzacj);
                }
            }
            zza = i;
        }
        if (this.zzatg != null && this.zzatg.length > 0) {
            for (zzacj zzacj2 : this.zzatg) {
                if (zzacj2 != null) {
                    zza += zzacb.zzb(3, zzacj2);
                }
            }
        }
        if (this.zzath != null) {
            this.zzath.booleanValue();
            zza += zzacb.zzaq(4) + 1;
        }
        if (this.zzati == null) {
            return zza;
        }
        this.zzati.booleanValue();
        return zza + (zzacb.zzaq(5) + 1);
    }

    public final void zza(zzacb zzacb) throws IOException {
        if (this.zzate != null) {
            zzacb.zze(1, this.zzate.intValue());
        }
        if (this.zzatf != null && this.zzatf.length > 0) {
            for (zzacj zzacj : this.zzatf) {
                if (zzacj != null) {
                    zzacb.zza(2, zzacj);
                }
            }
        }
        if (this.zzatg != null && this.zzatg.length > 0) {
            for (zzacj zzacj2 : this.zzatg) {
                if (zzacj2 != null) {
                    zzacb.zza(3, zzacj2);
                }
            }
        }
        if (this.zzath != null) {
            zzacb.zza(4, this.zzath.booleanValue());
        }
        if (this.zzati != null) {
            zzacb.zza(5, this.zzati.booleanValue());
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
                this.zzate = Integer.valueOf(zzaca.zzvn());
            } else if (zzvl == 18) {
                zzvl = zzacm.zzb(zzaca, 18);
                r1 = this.zzatf == null ? 0 : this.zzatf.length;
                r0 = new zzkk[(zzvl + r1)];
                if (r1 != 0) {
                    System.arraycopy(this.zzatf, 0, r0, 0, r1);
                }
                while (r1 < r0.length - 1) {
                    r0[r1] = new zzkk();
                    zzaca.zza(r0[r1]);
                    zzaca.zzvl();
                    r1++;
                }
                r0[r1] = new zzkk();
                zzaca.zza(r0[r1]);
                this.zzatf = r0;
            } else if (zzvl == 26) {
                zzvl = zzacm.zzb(zzaca, 26);
                r1 = this.zzatg == null ? 0 : this.zzatg.length;
                r0 = new zzkh[(zzvl + r1)];
                if (r1 != 0) {
                    System.arraycopy(this.zzatg, 0, r0, 0, r1);
                }
                while (r1 < r0.length - 1) {
                    r0[r1] = new zzkh();
                    zzaca.zza(r0[r1]);
                    zzaca.zzvl();
                    r1++;
                }
                r0[r1] = new zzkh();
                zzaca.zza(r0[r1]);
                this.zzatg = r0;
            } else if (zzvl == 32) {
                this.zzath = Boolean.valueOf(zzaca.zzvm());
            } else if (zzvl == 40) {
                this.zzati = Boolean.valueOf(zzaca.zzvm());
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
