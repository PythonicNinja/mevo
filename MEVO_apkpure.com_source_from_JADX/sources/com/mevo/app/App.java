package com.mevo.app;

import android.app.Application;
import android.content.Context;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.google.firebase.iid.FirebaseInstanceId;
import com.mevo.app.broadcast_receivers.ConnectivityBroadcastReceiver;
import com.mevo.app.data.network.Rest;
import com.mevo.app.tools.Lifecycle;
import com.mevo.app.tools.LocaleHelper;
import com.mevo.app.tools.Log;
import com.raizlabs.android.dbflow.config.FlowConfig.Builder;
import com.raizlabs.android.dbflow.config.FlowManager;
import io.fabric.sdk.android.Fabric;

public class App extends Application {
    private static final String TAG = "App";
    private static Context appContext;

    public static Context getAppContext() {
        return appContext;
    }

    protected void attachBaseContext(Context context) {
        super.attachBaseContext(LocaleHelper.onAttach(context));
    }

    public void onCreate() {
        super.onCreate();
        appContext = getBaseContext();
        if (Cfg.get().fabricEnabled()) {
            Fabric.with(this, new Answers(), new Crashlytics());
        }
        FlowManager.init(new Builder(this).build());
        Lifecycle.init(this);
        Log.init("Veturilo", Cfg.get().loggerEnabled());
        Rest.init();
        Log.registerHiddenActivationBroadcastReceiver(this);
        appContext.registerReceiver(new ConnectivityBroadcastReceiver(null), ConnectivityBroadcastReceiver.getIntentFilter());
        try {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("FCM token:\n");
            stringBuilder.append(FirebaseInstanceId.getInstance().getToken());
            Log.m94i(str, stringBuilder.toString());
        } catch (Throwable e) {
            Log.ex(TAG, e);
        }
    }
}
