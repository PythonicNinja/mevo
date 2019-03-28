package com.mevo.app.broadcast_receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.mevo.app.presentation.dialogs.NoInternetDialog;
import com.mevo.app.tools.Log;
import com.mevo.app.tools.NetTools;

public class ConnectivityBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "ConnectivityBroadcastReceiver";
    private NetworkListener networkListener;

    public interface NetworkListener {
        void onNetworkStatusChanged(boolean z);
    }

    public static IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        return intentFilter;
    }

    public ConnectivityBroadcastReceiver(NetworkListener networkListener) {
        this.networkListener = networkListener;
    }

    public void onReceive(Context context, Intent intent) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Internet connection chaned: ");
        stringBuilder.append(NetTools.isNetworkAvailable());
        Log.m94i(str, stringBuilder.toString());
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction()) != null && NetTools.isNetworkAvailable() == null) {
            if (NoInternetDialog.isShown == null) {
                NoInternetDialog.newInstance().show();
            }
            if (this.networkListener != null) {
                this.networkListener.onNetworkStatusChanged(null);
            }
        } else if (this.networkListener != null) {
            this.networkListener.onNetworkStatusChanged(true);
        }
    }

    public void setListener(NetworkListener networkListener) {
        this.networkListener = networkListener;
    }
}
