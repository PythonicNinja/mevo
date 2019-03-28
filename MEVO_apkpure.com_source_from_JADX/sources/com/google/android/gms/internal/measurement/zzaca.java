package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzaca {
    private final byte[] buffer;
    private int zzbtk = 64;
    private int zzbtl = 67108864;
    private int zzbtp;
    private int zzbtr = Integer.MAX_VALUE;
    private final int zzbyw;
    private final int zzbyx;
    private int zzbyy;
    private int zzbyz;
    private int zzbza;
    private int zzbzb;

    private zzaca(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.zzbyw = i;
        i2 += i;
        this.zzbyy = i2;
        this.zzbyx = i2;
        this.zzbyz = i;
    }

    public static zzaca zza(byte[] bArr, int i, int i2) {
        return new zzaca(bArr, 0, i2);
    }

    private final void zzan(int i) throws IOException {
        if (i < 0) {
            throw zzaci.zzvx();
        } else if (this.zzbyz + i > this.zzbtr) {
            zzan(this.zzbtr - this.zzbyz);
            throw zzaci.zzvw();
        } else if (i <= this.zzbyy - this.zzbyz) {
            this.zzbyz += i;
        } else {
            throw zzaci.zzvw();
        }
    }

    public static zzaca zzi(byte[] bArr) {
        return zza(bArr, 0, bArr.length);
    }

    private final void zztp() {
        this.zzbyy += this.zzbtp;
        int i = this.zzbyy;
        if (i > this.zzbtr) {
            this.zzbtp = i - this.zzbtr;
            this.zzbyy -= this.zzbtp;
            return;
        }
        this.zzbtp = 0;
    }

    private final byte zzvs() throws IOException {
        if (this.zzbyz == this.zzbyy) {
            throw zzaci.zzvw();
        }
        byte[] bArr = this.buffer;
        int i = this.zzbyz;
        this.zzbyz = i + 1;
        return bArr[i];
    }

    public final int getPosition() {
        return this.zzbyz - this.zzbyw;
    }

    public final String readString() throws IOException {
        int zzvn = zzvn();
        if (zzvn < 0) {
            throw zzaci.zzvx();
        } else if (zzvn > this.zzbyy - this.zzbyz) {
            throw zzaci.zzvw();
        } else {
            String str = new String(this.buffer, this.zzbyz, zzvn, zzach.UTF_8);
            this.zzbyz += zzvn;
            return str;
        }
    }

    public final void zza(zzacj zzacj) throws IOException {
        int zzvn = zzvn();
        if (this.zzbzb >= this.zzbtk) {
            throw zzaci.zzvz();
        }
        zzvn = zzaf(zzvn);
        this.zzbzb++;
        zzacj.zzb(this);
        zzaj(0);
        this.zzbzb--;
        zzal(zzvn);
    }

    public final void zza(zzacj zzacj, int i) throws IOException {
        if (this.zzbzb >= this.zzbtk) {
            throw zzaci.zzvz();
        }
        this.zzbzb++;
        zzacj.zzb(this);
        zzaj((i << 3) | 4);
        this.zzbzb--;
    }

    public final int zzaf(int i) throws zzaci {
        if (i < 0) {
            throw zzaci.zzvx();
        }
        i += this.zzbyz;
        int i2 = this.zzbtr;
        if (i > i2) {
            throw zzaci.zzvw();
        }
        this.zzbtr = i;
        zztp();
        return i2;
    }

    public final void zzaj(int i) throws zzaci {
        if (this.zzbza != i) {
            throw new zzaci("Protocol message end-group tag did not match expected tag.");
        }
    }

    public final boolean zzak(int i) throws IOException {
        switch (i & 7) {
            case 0:
                zzvn();
                return true;
            case 1:
                zzvq();
                return true;
            case 2:
                zzan(zzvn());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzvp();
                return true;
            default:
                throw new zzaci("Protocol message tag had invalid wire type.");
        }
        int zzvl;
        do {
            zzvl = zzvl();
            if (zzvl != 0) {
            }
            zzaj(((i >>> 3) << 3) | 4);
            return true;
        } while (zzak(zzvl));
        zzaj(((i >>> 3) << 3) | 4);
        return true;
    }

    public final void zzal(int i) {
        this.zzbtr = i;
        zztp();
    }

    public final void zzam(int i) {
        zzd(i, this.zzbza);
    }

    public final byte[] zzc(int i, int i2) {
        if (i2 == 0) {
            return zzacm.zzbzz;
        }
        Object obj = new byte[i2];
        System.arraycopy(this.buffer, this.zzbyw + i, obj, 0, i2);
        return obj;
    }

    final void zzd(int i, int i2) {
        if (i > this.zzbyz - this.zzbyw) {
            int i3 = this.zzbyz - this.zzbyw;
            StringBuilder stringBuilder = new StringBuilder(50);
            stringBuilder.append("Position ");
            stringBuilder.append(i);
            stringBuilder.append(" is beyond current ");
            stringBuilder.append(i3);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (i < 0) {
            StringBuilder stringBuilder2 = new StringBuilder(24);
            stringBuilder2.append("Bad position ");
            stringBuilder2.append(i);
            throw new IllegalArgumentException(stringBuilder2.toString());
        } else {
            this.zzbyz = this.zzbyw + i;
            this.zzbza = i2;
        }
    }

    public final int zzvl() throws IOException {
        if (this.zzbyz == this.zzbyy) {
            this.zzbza = 0;
            return 0;
        }
        this.zzbza = zzvn();
        if (this.zzbza != 0) {
            return this.zzbza;
        }
        throw new zzaci("Protocol message contained an invalid tag (zero).");
    }

    public final boolean zzvm() throws IOException {
        return zzvn() != 0;
    }

    public final int zzvn() throws IOException {
        byte zzvs = zzvs();
        if (zzvs >= (byte) 0) {
            return zzvs;
        }
        int i;
        int i2 = zzvs & 127;
        byte zzvs2 = zzvs();
        if (zzvs2 >= (byte) 0) {
            i = zzvs2 << 7;
        } else {
            i2 |= (zzvs2 & 127) << 7;
            zzvs2 = zzvs();
            if (zzvs2 >= (byte) 0) {
                i = zzvs2 << 14;
            } else {
                i2 |= (zzvs2 & 127) << 14;
                zzvs2 = zzvs();
                if (zzvs2 >= (byte) 0) {
                    i = zzvs2 << 21;
                } else {
                    i2 |= (zzvs2 & 127) << 21;
                    zzvs2 = zzvs();
                    i2 |= zzvs2 << 28;
                    if (zzvs2 >= (byte) 0) {
                        return i2;
                    }
                    for (i = 0; i < 5; i++) {
                        if (zzvs() >= (byte) 0) {
                            return i2;
                        }
                    }
                    throw zzaci.zzvy();
                }
            }
        }
        return i2 | i;
    }

    public final long zzvo() throws IOException {
        int i = 0;
        long j = 0;
        while (i < 64) {
            byte zzvs = zzvs();
            long j2 = j | (((long) (zzvs & 127)) << i);
            if ((zzvs & 128) == 0) {
                return j2;
            }
            i += 7;
            j = j2;
        }
        throw zzaci.zzvy();
    }

    public final int zzvp() throws IOException {
        return (((zzvs() & 255) | ((zzvs() & 255) << 8)) | ((zzvs() & 255) << 16)) | ((zzvs() & 255) << 24);
    }

    public final long zzvq() throws IOException {
        return (((((((((long) zzvs()) & 255) | ((((long) zzvs()) & 255) << 8)) | ((((long) zzvs()) & 255) << 16)) | ((((long) zzvs()) & 255) << 24)) | ((((long) zzvs()) & 255) << 32)) | ((((long) zzvs()) & 255) << 40)) | ((((long) zzvs()) & 255) << 48)) | ((((long) zzvs()) & 255) << 56);
    }

    public final int zzvr() {
        if (this.zzbtr == Integer.MAX_VALUE) {
            return -1;
        }
        return this.zzbtr - this.zzbyz;
    }
}
