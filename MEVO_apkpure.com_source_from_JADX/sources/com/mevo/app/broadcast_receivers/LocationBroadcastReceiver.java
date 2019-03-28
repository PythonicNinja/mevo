package com.mevo.app.broadcast_receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.mevo.app.tools.Log;
import com.mevo.app.tools.Utils;

public class LocationBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "ConnectivityBroadcastReceiver";
    private LocationSettingsListener listener;

    public interface LocationSettingsListener {
        void onLocationSettingsChanged(boolean z);
    }

    public static IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.location.PROVIDERS_CHANGED");
        return intentFilter;
    }

    public LocationBroadcastReceiver(LocationSettingsListener locationSettingsListener) {
        this.listener = locationSettingsListener;
    }

    public void onReceive(Context context, Intent intent) {
        context = Utils.isLocationEnabled(context);
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Location enabled: ");
        stringBuilder.append(context);
        Log.m94i(str, stringBuilder.toString());
        if (this.listener != null) {
            this.listener.onLocationSettingsChanged(context);
        }
    }

    public void setListener(LocationSettingsListener locationSettingsListener) {
        this.listener = locationSettingsListener;
    }
}
