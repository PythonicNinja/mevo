package com.google.android.gms.internal.measurement;

public final class zzacf implements Cloneable {
    private static final zzacg zzbzh = new zzacg();
    private int mSize;
    private boolean zzbzi;
    private int[] zzbzj;
    private zzacg[] zzbzk;

    zzacf() {
        this(10);
    }

    private zzacf(int i) {
        this.zzbzi = false;
        i = idealIntArraySize(i);
        this.zzbzj = new int[i];
        this.zzbzk = new zzacg[i];
        this.mSize = 0;
    }

    private static int idealIntArraySize(int i) {
        i <<= 2;
        for (int i2 = 4; i2 < 32; i2++) {
            int i3 = (1 << i2) - 12;
            if (i <= i3) {
                i = i3;
                break;
            }
        }
        return i / 4;
    }

    private final int zzav(int i) {
        int i2 = this.mSize - 1;
        int i3 = 0;
        while (i3 <= i2) {
            int i4 = (i3 + i2) >>> 1;
            int i5 = this.zzbzj[i4];
            if (i5 < i) {
                i3 = i4 + 1;
            } else if (i5 <= i) {
                return i4;
            } else {
                i2 = i4 - 1;
            }
        }
        return i3 ^ -1;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        int i = this.mSize;
        zzacf zzacf = new zzacf(i);
        int i2 = 0;
        System.arraycopy(this.zzbzj, 0, zzacf.zzbzj, 0, i);
        while (i2 < i) {
            if (this.zzbzk[i2] != null) {
                zzacf.zzbzk[i2] = (zzacg) this.zzbzk[i2].clone();
            }
            i2++;
        }
        zzacf.mSize = i;
        return zzacf;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzacf)) {
            return false;
        }
        zzacf zzacf = (zzacf) obj;
        if (this.mSize != zzacf.mSize) {
            return false;
        }
        Object obj2;
        int[] iArr = this.zzbzj;
        int[] iArr2 = zzacf.zzbzj;
        int i = this.mSize;
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2] != iArr2[i2]) {
                obj2 = null;
                break;
            }
        }
        obj2 = 1;
        if (obj2 != null) {
            zzacg[] zzacgArr = this.zzbzk;
            zzacg[] zzacgArr2 = zzacf.zzbzk;
            int i3 = this.mSize;
            for (i = 0; i < i3; i++) {
                if (!zzacgArr[i].equals(zzacgArr2[i])) {
                    obj = null;
                    break;
                }
            }
            obj = 1;
            if (obj != null) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = 17;
        for (int i2 = 0; i2 < this.mSize; i2++) {
            i = (((i * 31) + this.zzbzj[i2]) * 31) + this.zzbzk[i2].hashCode();
        }
        return i;
    }

    public final boolean isEmpty() {
        return this.mSize == 0;
    }

    final int size() {
        return this.mSize;
    }

    final void zza(int i, zzacg zzacg) {
        int zzav = zzav(i);
        if (zzav >= 0) {
            this.zzbzk[zzav] = zzacg;
            return;
        }
        zzav ^= -1;
        if (zzav >= this.mSize || this.zzbzk[zzav] != zzbzh) {
            if (this.mSize >= this.zzbzj.length) {
                int idealIntArraySize = idealIntArraySize(this.mSize + 1);
                Object obj = new int[idealIntArraySize];
                Object obj2 = new zzacg[idealIntArraySize];
                System.arraycopy(this.zzbzj, 0, obj, 0, this.zzbzj.length);
                System.arraycopy(this.zzbzk, 0, obj2, 0, this.zzbzk.length);
                this.zzbzj = obj;
                this.zzbzk = obj2;
            }
            if (this.mSize - zzav != 0) {
                int i2 = zzav + 1;
                System.arraycopy(this.zzbzj, zzav, this.zzbzj, i2, this.mSize - zzav);
                System.arraycopy(this.zzbzk, zzav, this.zzbzk, i2, this.mSize - zzav);
            }
            this.zzbzj[zzav] = i;
            this.zzbzk[zzav] = zzacg;
            this.mSize++;
            return;
        }
        this.zzbzj[zzav] = i;
        this.zzbzk[zzav] = zzacg;
    }

    final zzacg zzat(int i) {
        i = zzav(i);
        if (i >= 0) {
            if (this.zzbzk[i] != zzbzh) {
                return this.zzbzk[i];
            }
        }
        return null;
    }

    final zzacg zzau(int i) {
        return this.zzbzk[i];
    }
}
