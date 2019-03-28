package com.mevo.app.modules.firebase_cloud_messaging;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import com.mevo.app.C0434R;

public class NotificationsHelper {
    private static final int NOTIF_ID_END = 5100;
    private static final int NOTIF_ID_START = 5000;
    private static int notifId = 5000;

    public static void sendNotification(String str, String str2, Context context) {
        sendNotification(str, str2, context, null);
    }

    public static void sendNotification(String str, String str2, Context context, PendingIntent pendingIntent) {
        if (pendingIntent == null) {
            pendingIntent = PendingIntent.getActivity(context, 2000, new Intent(), 134217728);
        }
        ((NotificationManager) context.getSystemService("notification")).notify(getNextNotifiId(), new Builder(context, "DEFAULT_CHANNEL").setSmallIcon(C0434R.mipmap.ic_launcher).setContentTitle(str).setContentText(str2).setStyle(new BigTextStyle().bigText(str2)).setDefaults(-1).setContentIntent(pendingIntent).setAutoCancel(true).build());
    }

    private static int getNextNotifiId() {
        notifId++;
        if (notifId > NOTIF_ID_END) {
            notifId = NOTIF_ID_START;
        }
        return notifId;
    }
}
