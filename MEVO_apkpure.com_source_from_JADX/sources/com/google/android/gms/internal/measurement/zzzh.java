package com.google.android.gms.internal.measurement;

class zzzh extends zzzg {
    protected final byte[] zzbtj;

    zzzh(byte[] bArr) {
        this.zzbtj = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzzb) || size() != ((zzzb) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof zzzh)) {
            return obj.equals(this);
        }
        zzzh zzzh = (zzzh) obj;
        int zztm = zztm();
        int zztm2 = zzzh.zztm();
        return (zztm == 0 || zztm2 == 0 || zztm == zztm2) ? zza(zzzh, 0, size()) : false;
    }

    public int size() {
        return this.zzbtj.length;
    }

    protected final int zza(int i, int i2, int i3) {
        return zzzw.zza(i, this.zzbtj, zztn(), i3);
    }

    final boolean zza(zzzb zzzb, int i, int i2) {
        StringBuilder stringBuilder;
        if (i2 > zzzb.size()) {
            i = size();
            stringBuilder = new StringBuilder(40);
            stringBuilder.append("Length too large: ");
            stringBuilder.append(i2);
            stringBuilder.append(i);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (i2 > zzzb.size()) {
            r6 = zzzb.size();
            stringBuilder = new StringBuilder(59);
            stringBuilder.append("Ran off end of other: 0, ");
            stringBuilder.append(i2);
            stringBuilder.append(", ");
            stringBuilder.append(r6);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (!(zzzb instanceof zzzh)) {
            return zzzb.zzb(0, i2).equals(zzb(0, i2));
        } else {
            zzzh zzzh = (zzzh) zzzb;
            byte[] bArr = this.zzbtj;
            byte[] bArr2 = zzzh.zzbtj;
            int zztn = zztn() + i2;
            i2 = zztn();
            r6 = zzzh.zztn();
            while (i2 < zztn) {
                if (bArr[i2] != bArr2[r6]) {
                    return false;
                }
                i2++;
                r6++;
            }
            return true;
        }
    }

    public byte zzae(int i) {
        return this.zzbtj[i];
    }

    public final zzzb zzb(int i, int i2) {
        i = zzzb.zzb(0, i2, size());
        return i == 0 ? zzzb.zzbte : new zzze(this.zzbtj, zztn(), i);
    }

    protected int zztn() {
        return 0;
    }
}
