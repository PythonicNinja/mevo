package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.util.PlatformVersion;
import javax.annotation.concurrent.GuardedBy;

public final class FirebaseInstanceIdReceiver extends WakefulBroadcastReceiver {
    private static boolean zzaw = false;
    @GuardedBy("FirebaseInstanceIdReceiver.class")
    private static zzh zzax;
    @GuardedBy("FirebaseInstanceIdReceiver.class")
    private static zzh zzay;

    private static synchronized zzh zza(Context context, String str) {
        synchronized (FirebaseInstanceIdReceiver.class) {
            if ("com.google.firebase.MESSAGING_EVENT".equals(str)) {
                if (zzay == null) {
                    zzay = new zzh(context, str);
                }
                zzh zzh = zzay;
                return zzh;
            }
            if (zzax == null) {
                zzax = new zzh(context, str);
            }
            zzh = zzax;
            return zzh;
        }
    }

    private final void zza(Context context, Intent intent, String str) {
        int i;
        String str2;
        String valueOf;
        String str3 = null;
        intent.setComponent(null);
        intent.setPackage(context.getPackageName());
        if (VERSION.SDK_INT <= 18) {
            intent.removeCategory(context.getPackageName());
        }
        String stringExtra = intent.getStringExtra("gcm.rawData64");
        int i2 = 0;
        if (stringExtra != null) {
            intent.putExtra("rawData", Base64.decode(stringExtra, 0));
            intent.removeExtra("gcm.rawData64");
        }
        if (!"google.com/iid".equals(intent.getStringExtra("from"))) {
            if (!"com.google.firebase.INSTANCE_ID_EVENT".equals(str)) {
                if (!"com.google.android.c2dm.intent.RECEIVE".equals(str)) {
                    if (!"com.google.firebase.MESSAGING_EVENT".equals(str)) {
                        Log.d("FirebaseInstanceId", "Unexpected intent");
                        i = -1;
                        if (str3 != null) {
                            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                                stringExtra = "FirebaseInstanceId";
                                str2 = "Starting service: ";
                                valueOf = String.valueOf(str3);
                                Log.d(stringExtra, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                            }
                            if (PlatformVersion.isAtLeastO() && context.getApplicationInfo().targetSdkVersion >= 26) {
                                i2 = 1;
                            }
                            if (i2 != 0) {
                                if (isOrderedBroadcast()) {
                                    setResultCode(-1);
                                }
                                zza(context, str3).zza(intent, goAsync());
                            } else {
                                i = zzat.zzah().zzb(context, str3, intent);
                            }
                        }
                        if (!isOrderedBroadcast()) {
                            setResultCode(i);
                        }
                    }
                }
                str3 = "com.google.firebase.MESSAGING_EVENT";
                i = -1;
                if (str3 != null) {
                    if (Log.isLoggable("FirebaseInstanceId", 3)) {
                        stringExtra = "FirebaseInstanceId";
                        str2 = "Starting service: ";
                        valueOf = String.valueOf(str3);
                        if (valueOf.length() != 0) {
                        }
                        Log.d(stringExtra, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                    }
                    i2 = 1;
                    if (i2 != 0) {
                        i = zzat.zzah().zzb(context, str3, intent);
                    } else {
                        if (isOrderedBroadcast()) {
                            setResultCode(-1);
                        }
                        zza(context, str3).zza(intent, goAsync());
                    }
                }
                if (!isOrderedBroadcast()) {
                    setResultCode(i);
                }
            }
        }
        str3 = "com.google.firebase.INSTANCE_ID_EVENT";
        i = -1;
        if (str3 != null) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                stringExtra = "FirebaseInstanceId";
                str2 = "Starting service: ";
                valueOf = String.valueOf(str3);
                if (valueOf.length() != 0) {
                }
                Log.d(stringExtra, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            }
            i2 = 1;
            if (i2 != 0) {
                if (isOrderedBroadcast()) {
                    setResultCode(-1);
                }
                zza(context, str3).zza(intent, goAsync());
            } else {
                i = zzat.zzah().zzb(context, str3, intent);
            }
        }
        if (!isOrderedBroadcast()) {
            setResultCode(i);
        }
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent != null) {
            Parcelable parcelableExtra = intent.getParcelableExtra("wrapped_intent");
            Intent intent2 = parcelableExtra instanceof Intent ? (Intent) parcelableExtra : null;
            if (intent2 != null) {
                zza(context, intent2, intent.getAction());
            } else {
                zza(context, intent, intent.getAction());
            }
        }
    }
}
