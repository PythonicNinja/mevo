package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzki extends zzacd<zzki> {
    private static volatile zzki[] zzatp;
    public zzkl zzatq;
    public zzkj zzatr;
    public Boolean zzats;
    public String zzatt;
    public Boolean zzatu;
    public Boolean zzatv;

    public zzki() {
        this.zzatq = null;
        this.zzatr = null;
        this.zzats = null;
        this.zzatt = null;
        this.zzatu = null;
        this.zzatv = null;
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public static zzki[] zzls() {
        if (zzatp == null) {
            synchronized (zzach.zzbzn) {
                if (zzatp == null) {
                    zzatp = new zzki[0];
                }
            }
        }
        return zzatp;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzki)) {
            return false;
        }
        zzki zzki = (zzki) obj;
        if (this.zzatq == null) {
            if (zzki.zzatq != null) {
                return false;
            }
        } else if (!this.zzatq.equals(zzki.zzatq)) {
            return false;
        }
        if (this.zzatr == null) {
            if (zzki.zzatr != null) {
                return false;
            }
        } else if (!this.zzatr.equals(zzki.zzatr)) {
            return false;
        }
        if (this.zzats == null) {
            if (zzki.zzats != null) {
                return false;
            }
        } else if (!this.zzats.equals(zzki.zzats)) {
            return false;
        }
        if (this.zzatt == null) {
            if (zzki.zzatt != null) {
                return false;
            }
        } else if (!this.zzatt.equals(zzki.zzatt)) {
            return false;
        }
        if (this.zzatu == null) {
            if (zzki.zzatu != null) {
                return false;
            }
        } else if (!this.zzatu.equals(zzki.zzatu)) {
            return false;
        }
        if (this.zzatv == null) {
            if (zzki.zzatv != null) {
                return false;
            }
        } else if (!this.zzatv.equals(zzki.zzatv)) {
            return false;
        }
        if (this.zzbzd != null) {
            if (!this.zzbzd.isEmpty()) {
                return this.zzbzd.equals(zzki.zzbzd);
            }
        }
        return zzki.zzbzd == null || zzki.zzbzd.isEmpty();
    }

    public final int hashCode() {
        int hashCode = getClass().getName().hashCode() + 527;
        zzkl zzkl = this.zzatq;
        int i = 0;
        hashCode = (hashCode * 31) + (zzkl == null ? 0 : zzkl.hashCode());
        zzkj zzkj = this.zzatr;
        hashCode = ((((((((((hashCode * 31) + (zzkj == null ? 0 : zzkj.hashCode())) * 31) + (this.zzats == null ? 0 : this.zzats.hashCode())) * 31) + (this.zzatt == null ? 0 : this.zzatt.hashCode())) * 31) + (this.zzatu == null ? 0 : this.zzatu.hashCode())) * 31) + (this.zzatv == null ? 0 : this.zzatv.hashCode())) * 31;
        if (this.zzbzd != null) {
            if (!this.zzbzd.isEmpty()) {
                i = this.zzbzd.hashCode();
            }
        }
        return hashCode + i;
    }

    protected final int zza() {
        int zza = super.zza();
        if (this.zzatq != null) {
            zza += zzacb.zzb(1, this.zzatq);
        }
        if (this.zzatr != null) {
            zza += zzacb.zzb(2, this.zzatr);
        }
        if (this.zzats != null) {
            this.zzats.booleanValue();
            zza += zzacb.zzaq(3) + 1;
        }
        if (this.zzatt != null) {
            zza += zzacb.zzc(4, this.zzatt);
        }
        if (this.zzatu != null) {
            this.zzatu.booleanValue();
            zza += zzacb.zzaq(5) + 1;
        }
        if (this.zzatv == null) {
            return zza;
        }
        this.zzatv.booleanValue();
        return zza + (zzacb.zzaq(6) + 1);
    }

    public final void zza(zzacb zzacb) throws IOException {
        if (this.zzatq != null) {
            zzacb.zza(1, this.zzatq);
        }
        if (this.zzatr != null) {
            zzacb.zza(2, this.zzatr);
        }
        if (this.zzats != null) {
            zzacb.zza(3, this.zzats.booleanValue());
        }
        if (this.zzatt != null) {
            zzacb.zzb(4, this.zzatt);
        }
        if (this.zzatu != null) {
            zzacb.zza(5, this.zzatu.booleanValue());
        }
        if (this.zzatv != null) {
            zzacb.zza(6, this.zzatv.booleanValue());
        }
        super.zza(zzacb);
    }

    public final /* synthetic */ zzacj zzb(zzaca zzaca) throws IOException {
        while (true) {
            int zzvl = zzaca.zzvl();
            if (zzvl == 0) {
                return this;
            }
            zzacj zzacj;
            if (zzvl == 10) {
                if (this.zzatq == null) {
                    this.zzatq = new zzkl();
                }
                zzacj = this.zzatq;
            } else if (zzvl == 18) {
                if (this.zzatr == null) {
                    this.zzatr = new zzkj();
                }
                zzacj = this.zzatr;
            } else if (zzvl == 24) {
                this.zzats = Boolean.valueOf(zzaca.zzvm());
            } else if (zzvl == 34) {
                this.zzatt = zzaca.readString();
            } else if (zzvl == 40) {
                this.zzatu = Boolean.valueOf(zzaca.zzvm());
            } else if (zzvl == 48) {
                this.zzatv = Boolean.valueOf(zzaca.zzvm());
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
            zzaca.zza(zzacj);
        }
    }
}
