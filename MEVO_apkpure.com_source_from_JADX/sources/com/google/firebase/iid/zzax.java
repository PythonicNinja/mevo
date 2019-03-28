package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.Nullable;

@VisibleForTesting
final class zzax extends BroadcastReceiver {
    @Nullable
    private zzaw zzdb;

    public zzax(zzaw zzaw) {
        this.zzdb = zzaw;
    }

    public final void onReceive(Context context, Intent intent) {
        if (this.zzdb != null && this.zzdb.zzan()) {
            if (FirebaseInstanceId.zzk()) {
                Log.d("FirebaseInstanceId", "Connectivity changed. Starting background sync.");
            }
            FirebaseInstanceId.zza(this.zzdb, 0);
            this.zzdb.getContext().unregisterReceiver(this);
            this.zzdb = null;
        }
    }

    public final void zzao() {
        if (FirebaseInstanceId.zzk()) {
            Log.d("FirebaseInstanceId", "Connectivity change received registered");
        }
        this.zzdb.getContext().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }
}
