package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.support.annotation.WorkerThread;

final class zzjk extends zzep {
    private final /* synthetic */ zzjj zzaro;

    zzjk(zzjj zzjj, zzhk zzhk) {
        this.zzaro = zzjj;
        super(zzhk);
    }

    @WorkerThread
    public final void run() {
        zzhi zzhi = this.zzaro;
        zzhi.zzab();
        zzhi.zzgi().zzjc().zzg("Session started, time", Long.valueOf(zzhi.zzbt().elapsedRealtime()));
        zzhi.zzgj().zzamj.set(false);
        zzhi.zzfy().zza("auto", "_s", new Bundle());
        zzhi.zzgj().zzamk.set(zzhi.zzbt().currentTimeMillis());
    }
}
