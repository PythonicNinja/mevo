package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzkm extends zzacd<zzkm> {
    private static volatile zzkm[] zzaui;
    public String name;
    public Boolean zzauj;
    public Boolean zzauk;
    public Integer zzaul;

    public zzkm() {
        this.name = null;
        this.zzauj = null;
        this.zzauk = null;
        this.zzaul = null;
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public static zzkm[] zzlu() {
        if (zzaui == null) {
            synchronized (zzach.zzbzn) {
                if (zzaui == null) {
                    zzaui = new zzkm[0];
                }
            }
        }
        return zzaui;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkm)) {
            return false;
        }
        zzkm zzkm = (zzkm) obj;
        if (this.name == null) {
            if (zzkm.name != null) {
                return false;
            }
        } else if (!this.name.equals(zzkm.name)) {
            return false;
        }
        if (this.zzauj == null) {
            if (zzkm.zzauj != null) {
                return false;
            }
        } else if (!this.zzauj.equals(zzkm.zzauj)) {
            return false;
        }
        if (this.zzauk == null) {
            if (zzkm.zzauk != null) {
                return false;
            }
        } else if (!this.zzauk.equals(zzkm.zzauk)) {
            return false;
        }
        if (this.zzaul == null) {
            if (zzkm.zzaul != null) {
                return false;
            }
        } else if (!this.zzaul.equals(zzkm.zzaul)) {
            return false;
        }
        if (this.zzbzd != null) {
            if (!this.zzbzd.isEmpty()) {
                return this.zzbzd.equals(zzkm.zzbzd);
            }
        }
        return zzkm.zzbzd == null || zzkm.zzbzd.isEmpty();
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.zzauj == null ? 0 : this.zzauj.hashCode())) * 31) + (this.zzauk == null ? 0 : this.zzauk.hashCode())) * 31) + (this.zzaul == null ? 0 : this.zzaul.hashCode())) * 31;
        if (this.zzbzd != null) {
            if (!this.zzbzd.isEmpty()) {
                i = this.zzbzd.hashCode();
            }
        }
        return hashCode + i;
    }

    protected final int zza() {
        int zza = super.zza();
        if (this.name != null) {
            zza += zzacb.zzc(1, this.name);
        }
        if (this.zzauj != null) {
            this.zzauj.booleanValue();
            zza += zzacb.zzaq(2) + 1;
        }
        if (this.zzauk != null) {
            this.zzauk.booleanValue();
            zza += zzacb.zzaq(3) + 1;
        }
        return this.zzaul != null ? zza + zzacb.zzf(4, this.zzaul.intValue()) : zza;
    }

    public final void zza(zzacb zzacb) throws IOException {
        if (this.name != null) {
            zzacb.zzb(1, this.name);
        }
        if (this.zzauj != null) {
            zzacb.zza(2, this.zzauj.booleanValue());
        }
        if (this.zzauk != null) {
            zzacb.zza(3, this.zzauk.booleanValue());
        }
        if (this.zzaul != null) {
            zzacb.zze(4, this.zzaul.intValue());
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
                this.name = zzaca.readString();
            } else if (zzvl == 16) {
                this.zzauj = Boolean.valueOf(zzaca.zzvm());
            } else if (zzvl == 24) {
                this.zzauk = Boolean.valueOf(zzaca.zzvm());
            } else if (zzvl == 32) {
                this.zzaul = Integer.valueOf(zzaca.zzvn());
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
