package com.google.firebase.messaging;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.regex.Pattern;

public class FirebaseMessaging {
    public static final String INSTANCE_ID_SCOPE = "FCM";
    private static final Pattern zzdj = Pattern.compile("[a-zA-Z0-9-_.~%]{1,900}");
    private static FirebaseMessaging zzdk;
    private final FirebaseInstanceId zzda;

    private FirebaseMessaging(FirebaseInstanceId firebaseInstanceId) {
        this.zzda = firebaseInstanceId;
    }

    public static synchronized FirebaseMessaging getInstance() {
        FirebaseMessaging firebaseMessaging;
        synchronized (FirebaseMessaging.class) {
            if (zzdk == null) {
                zzdk = new FirebaseMessaging(FirebaseInstanceId.getInstance());
            }
            firebaseMessaging = zzdk;
        }
        return firebaseMessaging;
    }

    public boolean isAutoInitEnabled() {
        return this.zzda.zzs();
    }

    public void send(RemoteMessage remoteMessage) {
        if (TextUtils.isEmpty(remoteMessage.getTo())) {
            throw new IllegalArgumentException("Missing 'to'");
        }
        Context applicationContext = FirebaseApp.getInstance().getApplicationContext();
        Intent intent = new Intent("com.google.android.gcm.intent.SEND");
        Intent intent2 = new Intent();
        intent2.setPackage("com.google.example.invalidpackage");
        intent.putExtra(SettingsJsonConstants.APP_KEY, PendingIntent.getBroadcast(applicationContext, 0, intent2, 0));
        intent.setPackage("com.google.android.gms");
        intent.putExtras(remoteMessage.zzdm);
        applicationContext.sendOrderedBroadcast(intent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
    }

    public void setAutoInitEnabled(boolean z) {
        this.zzda.zzb(z);
    }

    public Task<Void> subscribeToTopic(String str) {
        Object substring;
        if (str != null && str.startsWith("/topics/")) {
            Log.w("FirebaseMessaging", "Format /topics/topic-name is deprecated. Only 'topic-name' should be used in subscribeToTopic.");
            substring = str.substring(8);
        }
        if (substring != null) {
            if (zzdj.matcher(substring).matches()) {
                FirebaseInstanceId firebaseInstanceId = this.zzda;
                String valueOf = String.valueOf("S!");
                str = String.valueOf(substring);
                return firebaseInstanceId.zza(str.length() != 0 ? valueOf.concat(str) : new String(valueOf));
            }
        }
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(substring).length() + 78);
        stringBuilder.append("Invalid topic name: ");
        stringBuilder.append(substring);
        stringBuilder.append(" does not match the allowed format [a-zA-Z0-9-_.~%]{1,900}");
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public Task<Void> unsubscribeFromTopic(String str) {
        Object substring;
        if (str != null && str.startsWith("/topics/")) {
            Log.w("FirebaseMessaging", "Format /topics/topic-name is deprecated. Only 'topic-name' should be used in unsubscribeFromTopic.");
            substring = str.substring(8);
        }
        if (substring != null) {
            if (zzdj.matcher(substring).matches()) {
                FirebaseInstanceId firebaseInstanceId = this.zzda;
                String valueOf = String.valueOf("U!");
                str = String.valueOf(substring);
                return firebaseInstanceId.zza(str.length() != 0 ? valueOf.concat(str) : new String(valueOf));
            }
        }
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(substring).length() + 78);
        stringBuilder.append("Invalid topic name: ");
        stringBuilder.append(substring);
        stringBuilder.append(" does not match the allowed format [a-zA-Z0-9-_.~%]{1,900}");
        throw new IllegalArgumentException(stringBuilder.toString());
    }
}
