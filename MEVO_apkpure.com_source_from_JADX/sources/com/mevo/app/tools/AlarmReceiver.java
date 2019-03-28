package com.mevo.app.tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.mevo.app.C0434R;
import com.mevo.app.modules.firebase_cloud_messaging.NotificationsHelper;

public class AlarmReceiver extends BroadcastReceiver {
    private static final String BIKENUMBER_KEY = "bikeNumber";

    public void onReceive(Context context, Intent intent) {
        NotificationsHelper.sendNotification(context.getString(C0434R.string.app_name), context.getString(C0434R.string.too_long_park_message, new Object[]{intent.getExtras().getString(BIKENUMBER_KEY)}), context);
    }
}
