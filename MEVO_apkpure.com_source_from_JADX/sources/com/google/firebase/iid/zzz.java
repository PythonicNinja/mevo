package com.google.firebase.iid;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.concurrent.GuardedBy;

public final class zzz {
    @GuardedBy("MessengerIpcClient.class")
    private static zzz zzbk;
    private final ScheduledExecutorService zzbl;
    @GuardedBy("this")
    private zzab zzbm = new zzab();
    @GuardedBy("this")
    private int zzbn = 1;
    private final Context zzv;

    @VisibleForTesting
    private zzz(Context context, ScheduledExecutorService scheduledExecutorService) {
        this.zzbl = scheduledExecutorService;
        this.zzv = context.getApplicationContext();
    }

    private final synchronized <T> Task<T> zza(zzai<T> zzai) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(zzai);
            StringBuilder stringBuilder = new StringBuilder(String.valueOf(valueOf).length() + 9);
            stringBuilder.append("Queueing ");
            stringBuilder.append(valueOf);
            Log.d("MessengerIpcClient", stringBuilder.toString());
        }
        if (!this.zzbm.zzb(zzai)) {
            this.zzbm = new zzab();
            this.zzbm.zzb(zzai);
        }
        return zzai.zzbx.getTask();
    }

    public static synchronized zzz zzc(Context context) {
        zzz zzz;
        synchronized (zzz.class) {
            if (zzbk == null) {
                zzbk = new zzz(context, Executors.newSingleThreadScheduledExecutor());
            }
            zzz = zzbk;
        }
        return zzz;
    }

    private final synchronized int zzw() {
        int i;
        i = this.zzbn;
        this.zzbn = i + 1;
        return i;
    }

    public final Task<Void> zza(int i, Bundle bundle) {
        return zza(new zzah(zzw(), 2, bundle));
    }

    public final Task<Bundle> zzb(int i, Bundle bundle) {
        return zza(new zzak(zzw(), 1, bundle));
    }
}
