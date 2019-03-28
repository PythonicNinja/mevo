package com.google.android.gms.internal.measurement;

import java.io.Serializable;
import java.util.Iterator;

public abstract class zzzb implements Serializable, Iterable<Byte> {
    public static final zzzb zzbte = new zzzh(zzzw.zzbux);
    private static final zzzf zzbtf = (zzza.zztk() ? new zzzi() : new zzzd());
    private int zzbqf = 0;

    zzzb() {
    }

    static int zzb(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((((i | i2) | i4) | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            StringBuilder stringBuilder = new StringBuilder(32);
            stringBuilder.append("Beginning index: ");
            stringBuilder.append(i);
            stringBuilder.append(" < 0");
            throw new IndexOutOfBoundsException(stringBuilder.toString());
        } else if (i2 < i) {
            r1 = new StringBuilder(66);
            r1.append("Beginning index larger than ending index: ");
            r1.append(i);
            r1.append(", ");
            r1.append(i2);
            throw new IndexOutOfBoundsException(r1.toString());
        } else {
            r1 = new StringBuilder(37);
            r1.append("End index: ");
            r1.append(i2);
            r1.append(" >= ");
            r1.append(i3);
            throw new IndexOutOfBoundsException(r1.toString());
        }
    }

    public static zzzb zzfn(String str) {
        return new zzzh(str.getBytes(zzzw.UTF_8));
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int i = this.zzbqf;
        if (i == 0) {
            i = size();
            i = zza(i, 0, i);
            if (i == 0) {
                i = 1;
            }
            this.zzbqf = i;
        }
        return i;
    }

    public /* synthetic */ Iterator iterator() {
        return new zzzc(this);
    }

    public abstract int size();

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", new Object[]{Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size())});
    }

    protected abstract int zza(int i, int i2, int i3);

    public abstract byte zzae(int i);

    public abstract zzzb zzb(int i, int i2);

    protected final int zztm() {
        return this.zzbqf;
    }
}
