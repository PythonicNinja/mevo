package com.google.android.gms.internal.measurement;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

final class zzhq implements Callable<String> {
    private final /* synthetic */ zzhm zzaps;

    zzhq(zzhm zzhm) {
        this.zzaps = zzhm;
    }

    public final /* synthetic */ Object call() throws Exception {
        String zzjk = this.zzaps.zzgj().zzjk();
        if (zzjk != null) {
            return zzjk;
        }
        zzfk zziv;
        String str;
        zzhi zzfy = this.zzaps.zzfy();
        String str2 = null;
        if (zzfy.zzgh().zzju()) {
            zziv = zzfy.zzgi().zziv();
            str = "Cannot retrieve app instance id from analytics worker thread";
        } else if (zzee.isMainThread()) {
            zziv = zzfy.zzgi().zziv();
            str = "Cannot retrieve app instance id from main thread";
        } else {
            long elapsedRealtime = zzfy.zzbt().elapsedRealtime();
            String zzaj = zzfy.zzaj(120000);
            long elapsedRealtime2 = zzfy.zzbt().elapsedRealtime() - elapsedRealtime;
            str2 = (zzaj != null || elapsedRealtime2 >= 120000) ? zzaj : zzfy.zzaj(120000 - elapsedRealtime2);
            if (str2 != null) {
                throw new TimeoutException();
            }
            this.zzaps.zzgj().zzbu(str2);
            return str2;
        }
        zziv.log(str);
        if (str2 != null) {
            this.zzaps.zzgj().zzbu(str2);
            return str2;
        }
        throw new TimeoutException();
    }
}
