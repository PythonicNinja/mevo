package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzkv extends zzacd<zzkv> {
    public long[] zzawl;
    public long[] zzawm;
    public zzkq[] zzawn;
    private zzkw[] zzawo;

    public zzkv() {
        this.zzawl = zzacm.zzbzt;
        this.zzawm = zzacm.zzbzt;
        this.zzawn = zzkq.zzlx();
        this.zzawo = zzkw.zzmb();
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkv)) {
            return false;
        }
        zzkv zzkv = (zzkv) obj;
        if (!zzach.equals(this.zzawl, zzkv.zzawl) || !zzach.equals(this.zzawm, zzkv.zzawm) || !zzach.equals(this.zzawn, zzkv.zzawn) || !zzach.equals(this.zzawo, zzkv.zzawo)) {
            return false;
        }
        if (this.zzbzd != null) {
            if (!this.zzbzd.isEmpty()) {
                return this.zzbzd.equals(zzkv.zzbzd);
            }
        }
        return zzkv.zzbzd == null || zzkv.zzbzd.isEmpty();
    }

    public final int hashCode() {
        int hashCode;
        int hashCode2 = (((((((((getClass().getName().hashCode() + 527) * 31) + zzach.hashCode(this.zzawl)) * 31) + zzach.hashCode(this.zzawm)) * 31) + zzach.hashCode(this.zzawn)) * 31) + zzach.hashCode(this.zzawo)) * 31;
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
        int i;
        int i2;
        int zza = super.zza();
        if (this.zzawl != null && this.zzawl.length > 0) {
            i = 0;
            for (long zzat : this.zzawl) {
                i += zzacb.zzat(zzat);
            }
            zza = (zza + i) + (this.zzawl.length * 1);
        }
        if (this.zzawm != null && this.zzawm.length > 0) {
            i = 0;
            for (long zzat2 : this.zzawm) {
                i += zzacb.zzat(zzat2);
            }
            zza = (zza + i) + (this.zzawm.length * 1);
        }
        if (this.zzawn != null && this.zzawn.length > 0) {
            i2 = zza;
            for (zzacj zzacj : this.zzawn) {
                if (zzacj != null) {
                    i2 += zzacb.zzb(3, zzacj);
                }
            }
            zza = i2;
        }
        if (this.zzawo != null && this.zzawo.length > 0) {
            for (zzacj zzacj2 : this.zzawo) {
                if (zzacj2 != null) {
                    zza += zzacb.zzb(4, zzacj2);
                }
            }
        }
        return zza;
    }

    public final void zza(zzacb zzacb) throws IOException {
        if (this.zzawl != null && this.zzawl.length > 0) {
            for (long zza : this.zzawl) {
                zzacb.zza(1, zza);
            }
        }
        if (this.zzawm != null && this.zzawm.length > 0) {
            for (long zza2 : this.zzawm) {
                zzacb.zza(2, zza2);
            }
        }
        if (this.zzawn != null && this.zzawn.length > 0) {
            for (zzacj zzacj : this.zzawn) {
                if (zzacj != null) {
                    zzacb.zza(3, zzacj);
                }
            }
        }
        if (this.zzawo != null && this.zzawo.length > 0) {
            for (zzacj zzacj2 : this.zzawo) {
                if (zzacj2 != null) {
                    zzacb.zza(4, zzacj2);
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
            int position;
            Object obj;
            if (zzvl != 8) {
                int i;
                Object obj2;
                if (zzvl == 10) {
                    zzvl = zzaca.zzaf(zzaca.zzvn());
                    position = zzaca.getPosition();
                    i = 0;
                    while (zzaca.zzvr() > 0) {
                        zzaca.zzvo();
                        i++;
                    }
                    zzaca.zzam(position);
                    position = this.zzawl == null ? 0 : this.zzawl.length;
                    obj2 = new long[(i + position)];
                    if (position != 0) {
                        System.arraycopy(this.zzawl, 0, obj2, 0, position);
                    }
                    while (position < obj2.length) {
                        obj2[position] = zzaca.zzvo();
                        position++;
                    }
                    this.zzawl = obj2;
                } else if (zzvl == 16) {
                    zzvl = zzacm.zzb(zzaca, 16);
                    position = this.zzawm == null ? 0 : this.zzawm.length;
                    obj = new long[(zzvl + position)];
                    if (position != 0) {
                        System.arraycopy(this.zzawm, 0, obj, 0, position);
                    }
                    while (position < obj.length - 1) {
                        obj[position] = zzaca.zzvo();
                        zzaca.zzvl();
                        position++;
                    }
                    obj[position] = zzaca.zzvo();
                    this.zzawm = obj;
                } else if (zzvl == 18) {
                    zzvl = zzaca.zzaf(zzaca.zzvn());
                    position = zzaca.getPosition();
                    i = 0;
                    while (zzaca.zzvr() > 0) {
                        zzaca.zzvo();
                        i++;
                    }
                    zzaca.zzam(position);
                    position = this.zzawm == null ? 0 : this.zzawm.length;
                    obj2 = new long[(i + position)];
                    if (position != 0) {
                        System.arraycopy(this.zzawm, 0, obj2, 0, position);
                    }
                    while (position < obj2.length) {
                        obj2[position] = zzaca.zzvo();
                        position++;
                    }
                    this.zzawm = obj2;
                } else if (zzvl == 26) {
                    zzvl = zzacm.zzb(zzaca, 26);
                    position = this.zzawn == null ? 0 : this.zzawn.length;
                    obj = new zzkq[(zzvl + position)];
                    if (position != 0) {
                        System.arraycopy(this.zzawn, 0, obj, 0, position);
                    }
                    while (position < obj.length - 1) {
                        obj[position] = new zzkq();
                        zzaca.zza(obj[position]);
                        zzaca.zzvl();
                        position++;
                    }
                    obj[position] = new zzkq();
                    zzaca.zza(obj[position]);
                    this.zzawn = obj;
                } else if (zzvl == 34) {
                    zzvl = zzacm.zzb(zzaca, 34);
                    position = this.zzawo == null ? 0 : this.zzawo.length;
                    obj = new zzkw[(zzvl + position)];
                    if (position != 0) {
                        System.arraycopy(this.zzawo, 0, obj, 0, position);
                    }
                    while (position < obj.length - 1) {
                        obj[position] = new zzkw();
                        zzaca.zza(obj[position]);
                        zzaca.zzvl();
                        position++;
                    }
                    obj[position] = new zzkw();
                    zzaca.zza(obj[position]);
                    this.zzawo = obj;
                } else if (!super.zza(zzaca, zzvl)) {
                    return this;
                }
                zzaca.zzal(zzvl);
            } else {
                zzvl = zzacm.zzb(zzaca, 8);
                position = this.zzawl == null ? 0 : this.zzawl.length;
                obj = new long[(zzvl + position)];
                if (position != 0) {
                    System.arraycopy(this.zzawl, 0, obj, 0, position);
                }
                while (position < obj.length - 1) {
                    obj[position] = zzaca.zzvo();
                    zzaca.zzvl();
                    position++;
                }
                obj[position] = zzaca.zzvo();
                this.zzawl = obj;
            }
        }
    }
}
