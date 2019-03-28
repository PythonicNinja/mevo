package com.google.firebase.iid;

import android.util.Log;

final class zzg implements Runnable {
    private final /* synthetic */ zzd zzt;
    private final /* synthetic */ zzf zzu;

    zzg(zzf zzf, zzd zzd) {
        this.zzu = zzf;
        this.zzt = zzd;
    }

    public final void run() {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "bg processing of the intent starting now");
        }
        this.zzu.zzs.zzd(this.zzt.intent);
        this.zzt.finish();
    }
}
