package com.google.android.gms.internal.measurement;

public abstract class zzzj {
    private static volatile boolean zzbtn = false;
    int zzbtk;
    private int zzbtl;
    private boolean zzbtm;

    private zzzj() {
        this.zzbtk = 100;
        this.zzbtl = Integer.MAX_VALUE;
        this.zzbtm = false;
    }

    static zzzj zza(byte[] bArr, int i, int i2, boolean z) {
        zzzj zzzl = new zzzl(bArr, i, i2);
        try {
            zzzl.zzaf(i2);
            return zzzl;
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    public abstract int zzto();
}
