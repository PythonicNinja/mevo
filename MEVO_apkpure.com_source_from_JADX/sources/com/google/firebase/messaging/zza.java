package com.google.firebase.messaging;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.firebase.iid.zzat;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

final class zza {
    private static zza zzde;
    private Bundle zzdf;
    private Method zzdg;
    private Method zzdh;
    private final AtomicInteger zzdi = new AtomicInteger((int) SystemClock.elapsedRealtime());
    private final Context zzv;

    private zza(Context context) {
        this.zzv = context.getApplicationContext();
    }

    @TargetApi(26)
    private final Notification zza(CharSequence charSequence, String str, int i, Integer num, Uri uri, PendingIntent pendingIntent, PendingIntent pendingIntent2, String str2) {
        Builder smallIcon = new Builder(this.zzv).setAutoCancel(true).setSmallIcon(i);
        if (!TextUtils.isEmpty(charSequence)) {
            smallIcon.setContentTitle(charSequence);
        }
        if (!TextUtils.isEmpty(str)) {
            smallIcon.setContentText(str);
            smallIcon.setStyle(new BigTextStyle().bigText(str));
        }
        if (num != null) {
            smallIcon.setColor(num.intValue());
        }
        if (uri != null) {
            smallIcon.setSound(uri);
        }
        if (pendingIntent != null) {
            smallIcon.setContentIntent(pendingIntent);
        }
        if (pendingIntent2 != null) {
            smallIcon.setDeleteIntent(pendingIntent2);
        }
        if (str2 != null) {
            if (this.zzdg == null) {
                this.zzdg = zzl("setChannelId");
            }
            if (this.zzdg == null) {
                this.zzdg = zzl("setChannel");
            }
            if (this.zzdg == null) {
                Log.e("FirebaseMessaging", "Error while setting the notification channel");
            } else {
                try {
                    this.zzdg.invoke(smallIcon, new Object[]{str2});
                } catch (Throwable e) {
                    Log.e("FirebaseMessaging", "Error while setting the notification channel", e);
                }
            }
        }
        return smallIcon.build();
    }

    static String zza(Bundle bundle, String str) {
        String string = bundle.getString(str);
        return string == null ? bundle.getString(str.replace("gcm.n.", "gcm.notification.")) : string;
    }

    private static void zza(Intent intent, Bundle bundle) {
        for (String str : bundle.keySet()) {
            if (str.startsWith("google.c.a.") || str.equals("from")) {
                intent.putExtra(str, bundle.getString(str));
            }
        }
    }

    private final android.os.Bundle zzar() {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r4 = this;
        r0 = r4.zzdf;
        if (r0 == 0) goto L_0x0007;
    L_0x0004:
        r0 = r4.zzdf;
        return r0;
    L_0x0007:
        r0 = 0;
        r1 = r4.zzv;	 Catch:{ NameNotFoundException -> 0x001b }
        r1 = r1.getPackageManager();	 Catch:{ NameNotFoundException -> 0x001b }
        r2 = r4.zzv;	 Catch:{ NameNotFoundException -> 0x001b }
        r2 = r2.getPackageName();	 Catch:{ NameNotFoundException -> 0x001b }
        r3 = 128; // 0x80 float:1.794E-43 double:6.32E-322;	 Catch:{ NameNotFoundException -> 0x001b }
        r1 = r1.getApplicationInfo(r2, r3);	 Catch:{ NameNotFoundException -> 0x001b }
        r0 = r1;
    L_0x001b:
        if (r0 == 0) goto L_0x0028;
    L_0x001d:
        r1 = r0.metaData;
        if (r1 == 0) goto L_0x0028;
    L_0x0021:
        r0 = r0.metaData;
        r4.zzdf = r0;
        r0 = r4.zzdf;
        return r0;
    L_0x0028:
        r0 = android.os.Bundle.EMPTY;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.zza.zzar():android.os.Bundle");
    }

    static String zzb(Bundle bundle, String str) {
        str = String.valueOf(str);
        String valueOf = String.valueOf("_loc_key");
        return zza(bundle, valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    @android.annotation.TargetApi(26)
    private final boolean zzb(int r5) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r4 = this;
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 1;
        r2 = 26;
        if (r0 == r2) goto L_0x0008;
    L_0x0007:
        return r1;
    L_0x0008:
        r0 = 0;
        r2 = r4.zzv;	 Catch:{ NotFoundException -> 0x0032 }
        r2 = r2.getResources();	 Catch:{ NotFoundException -> 0x0032 }
        r3 = 0;	 Catch:{ NotFoundException -> 0x0032 }
        r2 = r2.getDrawable(r5, r3);	 Catch:{ NotFoundException -> 0x0032 }
        r2 = r2 instanceof android.graphics.drawable.AdaptiveIconDrawable;	 Catch:{ NotFoundException -> 0x0032 }
        if (r2 == 0) goto L_0x0031;	 Catch:{ NotFoundException -> 0x0032 }
    L_0x0018:
        r1 = "FirebaseMessaging";	 Catch:{ NotFoundException -> 0x0032 }
        r2 = 77;	 Catch:{ NotFoundException -> 0x0032 }
        r3 = new java.lang.StringBuilder;	 Catch:{ NotFoundException -> 0x0032 }
        r3.<init>(r2);	 Catch:{ NotFoundException -> 0x0032 }
        r2 = "Adaptive icons cannot be used in notifications. Ignoring icon id: ";	 Catch:{ NotFoundException -> 0x0032 }
        r3.append(r2);	 Catch:{ NotFoundException -> 0x0032 }
        r3.append(r5);	 Catch:{ NotFoundException -> 0x0032 }
        r5 = r3.toString();	 Catch:{ NotFoundException -> 0x0032 }
        android.util.Log.e(r1, r5);	 Catch:{ NotFoundException -> 0x0032 }
        return r0;
    L_0x0031:
        return r1;
    L_0x0032:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.zza.zzb(int):boolean");
    }

    static java.lang.Object[] zzc(android.os.Bundle r5, java.lang.String r6) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r0 = java.lang.String.valueOf(r6);
        r1 = "_loc_args";
        r1 = java.lang.String.valueOf(r1);
        r2 = r1.length();
        if (r2 == 0) goto L_0x0015;
    L_0x0010:
        r0 = r0.concat(r1);
        goto L_0x001b;
    L_0x0015:
        r1 = new java.lang.String;
        r1.<init>(r0);
        r0 = r1;
    L_0x001b:
        r5 = zza(r5, r0);
        r0 = android.text.TextUtils.isEmpty(r5);
        r1 = 0;
        if (r0 == 0) goto L_0x0027;
    L_0x0026:
        return r1;
    L_0x0027:
        r0 = new org.json.JSONArray;	 Catch:{ JSONException -> 0x0040 }
        r0.<init>(r5);	 Catch:{ JSONException -> 0x0040 }
        r2 = r0.length();	 Catch:{ JSONException -> 0x0040 }
        r2 = new java.lang.String[r2];	 Catch:{ JSONException -> 0x0040 }
        r3 = 0;	 Catch:{ JSONException -> 0x0040 }
    L_0x0033:
        r4 = r2.length;	 Catch:{ JSONException -> 0x0040 }
        if (r3 >= r4) goto L_0x003f;	 Catch:{ JSONException -> 0x0040 }
    L_0x0036:
        r4 = r0.opt(r3);	 Catch:{ JSONException -> 0x0040 }
        r2[r3] = r4;	 Catch:{ JSONException -> 0x0040 }
        r3 = r3 + 1;
        goto L_0x0033;
    L_0x003f:
        return r2;
    L_0x0040:
        r0 = "FirebaseMessaging";
        r6 = java.lang.String.valueOf(r6);
        r2 = "_loc_args";
        r2 = java.lang.String.valueOf(r2);
        r3 = r2.length();
        if (r3 == 0) goto L_0x0057;
    L_0x0052:
        r6 = r6.concat(r2);
        goto L_0x005d;
    L_0x0057:
        r2 = new java.lang.String;
        r2.<init>(r6);
        r6 = r2;
    L_0x005d:
        r2 = 6;
        r6 = r6.substring(r2);
        r2 = java.lang.String.valueOf(r6);
        r2 = r2.length();
        r2 = r2 + 41;
        r3 = java.lang.String.valueOf(r5);
        r3 = r3.length();
        r2 = r2 + r3;
        r3 = new java.lang.StringBuilder;
        r3.<init>(r2);
        r2 = "Malformed ";
        r3.append(r2);
        r3.append(r6);
        r6 = ": ";
        r3.append(r6);
        r3.append(r5);
        r5 = "  Default value will be used.";
        r3.append(r5);
        r5 = r3.toString();
        android.util.Log.w(r0, r5);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.zza.zzc(android.os.Bundle, java.lang.String):java.lang.Object[]");
    }

    static synchronized zza zzd(Context context) {
        zza zza;
        synchronized (zza.class) {
            if (zzde == null) {
                zzde = new zza(context);
            }
            zza = zzde;
        }
        return zza;
    }

    private final String zzd(Bundle bundle, String str) {
        Object zza = zza(bundle, str);
        if (!TextUtils.isEmpty(zza)) {
            return zza;
        }
        String zzb = zzb(bundle, str);
        if (TextUtils.isEmpty(zzb)) {
            return null;
        }
        Resources resources = this.zzv.getResources();
        int identifier = resources.getIdentifier(zzb, "string", this.zzv.getPackageName());
        if (identifier == 0) {
            String str2 = "FirebaseMessaging";
            str = String.valueOf(str);
            String valueOf = String.valueOf("_loc_key");
            str = (valueOf.length() != 0 ? str.concat(valueOf) : new String(str)).substring(6);
            StringBuilder stringBuilder = new StringBuilder((String.valueOf(str).length() + 49) + String.valueOf(zzb).length());
            stringBuilder.append(str);
            stringBuilder.append(" resource not found: ");
            stringBuilder.append(zzb);
            stringBuilder.append(" Default value will be used.");
            Log.w(str2, stringBuilder.toString());
            return null;
        }
        Object[] zzc = zzc(bundle, str);
        if (zzc == null) {
            return resources.getString(identifier);
        }
        try {
            return resources.getString(identifier, zzc);
        } catch (Throwable e) {
            str2 = Arrays.toString(zzc);
            StringBuilder stringBuilder2 = new StringBuilder((String.valueOf(zzb).length() + 58) + String.valueOf(str2).length());
            stringBuilder2.append("Missing format argument for ");
            stringBuilder2.append(zzb);
            stringBuilder2.append(": ");
            stringBuilder2.append(str2);
            stringBuilder2.append(" Default value will be used.");
            Log.w("FirebaseMessaging", stringBuilder2.toString(), e);
            return null;
        }
    }

    static boolean zzf(Bundle bundle) {
        if (!"1".equals(zza(bundle, "gcm.n.e"))) {
            if (zza(bundle, "gcm.n.icon") == null) {
                return false;
            }
        }
        return true;
    }

    @Nullable
    static Uri zzg(@NonNull Bundle bundle) {
        Object zza = zza(bundle, "gcm.n.link_android");
        if (TextUtils.isEmpty(zza)) {
            zza = zza(bundle, "gcm.n.link");
        }
        return !TextUtils.isEmpty(zza) ? Uri.parse(zza) : null;
    }

    static String zzi(Bundle bundle) {
        Object zza = zza(bundle, "gcm.n.sound2");
        return TextUtils.isEmpty(zza) ? zza(bundle, "gcm.n.sound") : zza;
    }

    @android.annotation.TargetApi(26)
    private static java.lang.reflect.Method zzl(java.lang.String r4) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r0 = android.app.Notification.Builder.class;	 Catch:{ NoSuchMethodException -> 0x000f, NoSuchMethodException -> 0x000f }
        r1 = 1;	 Catch:{ NoSuchMethodException -> 0x000f, NoSuchMethodException -> 0x000f }
        r1 = new java.lang.Class[r1];	 Catch:{ NoSuchMethodException -> 0x000f, NoSuchMethodException -> 0x000f }
        r2 = 0;	 Catch:{ NoSuchMethodException -> 0x000f, NoSuchMethodException -> 0x000f }
        r3 = java.lang.String.class;	 Catch:{ NoSuchMethodException -> 0x000f, NoSuchMethodException -> 0x000f }
        r1[r2] = r3;	 Catch:{ NoSuchMethodException -> 0x000f, NoSuchMethodException -> 0x000f }
        r4 = r0.getMethod(r4, r1);	 Catch:{ NoSuchMethodException -> 0x000f, NoSuchMethodException -> 0x000f }
        return r4;
    L_0x000f:
        r4 = 0;
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.zza.zzl(java.lang.String):java.lang.reflect.Method");
    }

    private final java.lang.Integer zzm(java.lang.String r5) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r4 = this;
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 0;
        r2 = 21;
        if (r0 >= r2) goto L_0x0008;
    L_0x0007:
        return r1;
    L_0x0008:
        r0 = android.text.TextUtils.isEmpty(r5);
        if (r0 != 0) goto L_0x003c;
    L_0x000e:
        r0 = android.graphics.Color.parseColor(r5);	 Catch:{ IllegalArgumentException -> 0x0017 }
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ IllegalArgumentException -> 0x0017 }
        return r0;
    L_0x0017:
        r0 = "FirebaseMessaging";
        r2 = java.lang.String.valueOf(r5);
        r2 = r2.length();
        r2 = r2 + 54;
        r3 = new java.lang.StringBuilder;
        r3.<init>(r2);
        r2 = "Color ";
        r3.append(r2);
        r3.append(r5);
        r5 = " not valid. Notification will use default color.";
        r3.append(r5);
        r5 = r3.toString();
        android.util.Log.w(r0, r5);
    L_0x003c:
        r5 = r4.zzar();
        r0 = "com.google.firebase.messaging.default_notification_color";
        r2 = 0;
        r5 = r5.getInt(r0, r2);
        if (r5 == 0) goto L_0x005b;
    L_0x0049:
        r0 = r4.zzv;	 Catch:{ NotFoundException -> 0x0054 }
        r5 = android.support.v4.content.ContextCompat.getColor(r0, r5);	 Catch:{ NotFoundException -> 0x0054 }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ NotFoundException -> 0x0054 }
        return r5;
    L_0x0054:
        r5 = "FirebaseMessaging";
        r0 = "Cannot find the color resource referenced in AndroidManifest.";
        android.util.Log.w(r5, r0);
    L_0x005b:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.zza.zzm(java.lang.String):java.lang.Integer");
    }

    @TargetApi(26)
    private final String zzn(String str) {
        if (!PlatformVersion.isAtLeastO()) {
            return null;
        }
        NotificationManager notificationManager = (NotificationManager) this.zzv.getSystemService(NotificationManager.class);
        try {
            String str2;
            if (this.zzdh == null) {
                this.zzdh = notificationManager.getClass().getMethod("getNotificationChannel", new Class[]{String.class});
            }
            if (!TextUtils.isEmpty(str)) {
                if (this.zzdh.invoke(notificationManager, new Object[]{str}) != null) {
                    return str;
                }
                StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 122);
                stringBuilder.append("Notification Channel requested (");
                stringBuilder.append(str);
                stringBuilder.append(") has not been created by the app. Manifest configuration, or default, value will be used.");
                Log.w("FirebaseMessaging", stringBuilder.toString());
            }
            Object string = zzar().getString("com.google.firebase.messaging.default_notification_channel_id");
            if (TextUtils.isEmpty(string)) {
                str = "FirebaseMessaging";
                str2 = "Missing Default Notification Channel metadata in AndroidManifest. Default value will be used.";
            } else {
                if (this.zzdh.invoke(notificationManager, new Object[]{string}) != null) {
                    return string;
                }
                str = "FirebaseMessaging";
                str2 = "Notification Channel set in AndroidManifest.xml has not been created by the app. Default value will be used.";
            }
            Log.w(str, str2);
            if (this.zzdh.invoke(notificationManager, new Object[]{"fcm_fallback_notification_channel"}) == null) {
                Object newInstance = Class.forName("android.app.NotificationChannel").getConstructor(new Class[]{String.class, CharSequence.class, Integer.TYPE}).newInstance(new Object[]{"fcm_fallback_notification_channel", this.zzv.getString(C0397R.string.fcm_fallback_notification_channel_label), Integer.valueOf(3)});
                notificationManager.getClass().getMethod("createNotificationChannel", new Class[]{r11}).invoke(notificationManager, new Object[]{newInstance});
            }
            return "fcm_fallback_notification_channel";
        } catch (Throwable e) {
            Log.e("FirebaseMessaging", "Error while setting the notification channel", e);
            return null;
        }
    }

    final boolean zzh(Bundle bundle) {
        if ("1".equals(zza(bundle, "gcm.n.noui"))) {
            return true;
        }
        int myPid;
        Object obj;
        CharSequence zzd;
        CharSequence charSequence;
        CharSequence zzd2;
        String zza;
        Resources resources;
        int identifier;
        Integer zzm;
        Uri uri;
        Object zza2;
        Uri zzg;
        Intent launchIntentForPackage;
        Bundle bundle2;
        PendingIntent activity;
        Intent intent;
        PendingIntent zza3;
        PendingIntent zza4;
        NotificationCompat.Builder smallIcon;
        Notification build;
        String zza5;
        NotificationManager notificationManager;
        long uptimeMillis;
        StringBuilder stringBuilder;
        int identifier2;
        if (!((KeyguardManager) this.zzv.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
            if (!PlatformVersion.isAtLeastLollipop()) {
                SystemClock.sleep(10);
            }
            myPid = Process.myPid();
            List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) this.zzv.getSystemService("activity")).getRunningAppProcesses();
            if (runningAppProcesses != null) {
                for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    if (runningAppProcessInfo.pid == myPid) {
                        if (runningAppProcessInfo.importance == 100) {
                            obj = 1;
                            if (obj != null) {
                                return false;
                            }
                            zzd = zzd(bundle, "gcm.n.title");
                            if (TextUtils.isEmpty(zzd)) {
                                zzd = this.zzv.getApplicationInfo().loadLabel(this.zzv.getPackageManager());
                            }
                            charSequence = zzd;
                            zzd2 = zzd(bundle, "gcm.n.body");
                            zza = zza(bundle, "gcm.n.icon");
                            if (!TextUtils.isEmpty(zza)) {
                                resources = this.zzv.getResources();
                                identifier = resources.getIdentifier(zza, "drawable", this.zzv.getPackageName());
                                if (identifier == 0 && zzb(identifier)) {
                                    zzm = zzm(zza(bundle, "gcm.n.color"));
                                    zza = zzi(bundle);
                                    if (TextUtils.isEmpty(zza)) {
                                        uri = null;
                                    } else {
                                        if (!"default".equals(zza)) {
                                        }
                                        uri = RingtoneManager.getDefaultUri(2);
                                    }
                                    zza2 = zza(bundle, "gcm.n.click_action");
                                    if (TextUtils.isEmpty(zza2)) {
                                        zzg = zzg(bundle);
                                        if (zzg == null) {
                                            launchIntentForPackage = this.zzv.getPackageManager().getLaunchIntentForPackage(this.zzv.getPackageName());
                                            if (launchIntentForPackage == null) {
                                                Log.w("FirebaseMessaging", "No activity found to launch app");
                                            }
                                        } else {
                                            launchIntentForPackage = new Intent("android.intent.action.VIEW");
                                            launchIntentForPackage.setPackage(this.zzv.getPackageName());
                                            launchIntentForPackage.setData(zzg);
                                        }
                                    } else {
                                        launchIntentForPackage = new Intent(zza2);
                                        launchIntentForPackage.setPackage(this.zzv.getPackageName());
                                        launchIntentForPackage.setFlags(ErrorDialogData.BINDER_CRASH);
                                    }
                                    if (launchIntentForPackage != null) {
                                        launchIntentForPackage.addFlags(67108864);
                                        bundle2 = new Bundle(bundle);
                                        FirebaseMessagingService.zzj(bundle2);
                                        launchIntentForPackage.putExtras(bundle2);
                                        for (String str : bundle2.keySet()) {
                                            if (!str.startsWith("gcm.n.")) {
                                            }
                                            launchIntentForPackage.removeExtra(str);
                                        }
                                        activity = PendingIntent.getActivity(this.zzv, this.zzdi.incrementAndGet(), launchIntentForPackage, ErrorDialogData.SUPPRESSED);
                                    } else {
                                        activity = null;
                                    }
                                    if (FirebaseMessagingService.zzk(bundle)) {
                                        intent = new Intent("com.google.firebase.messaging.NOTIFICATION_OPEN");
                                        zza(intent, bundle);
                                        intent.putExtra("pending_intent", activity);
                                        zza3 = zzat.zza(this.zzv, this.zzdi.incrementAndGet(), intent, ErrorDialogData.SUPPRESSED);
                                        launchIntentForPackage = new Intent("com.google.firebase.messaging.NOTIFICATION_DISMISS");
                                        zza(launchIntentForPackage, bundle);
                                        zza4 = zzat.zza(this.zzv, this.zzdi.incrementAndGet(), launchIntentForPackage, ErrorDialogData.SUPPRESSED);
                                        activity = zza3;
                                    } else {
                                        zza4 = null;
                                    }
                                    if (PlatformVersion.isAtLeastO()) {
                                    }
                                    smallIcon = new NotificationCompat.Builder(this.zzv).setAutoCancel(true).setSmallIcon(identifier);
                                    if (!TextUtils.isEmpty(charSequence)) {
                                        smallIcon.setContentTitle(charSequence);
                                    }
                                    if (!TextUtils.isEmpty(zzd2)) {
                                        smallIcon.setContentText(zzd2);
                                        smallIcon.setStyle(new NotificationCompat.BigTextStyle().bigText(zzd2));
                                    }
                                    if (zzm != null) {
                                        smallIcon.setColor(zzm.intValue());
                                    }
                                    if (uri != null) {
                                        smallIcon.setSound(uri);
                                    }
                                    if (activity != null) {
                                        smallIcon.setContentIntent(activity);
                                    }
                                    if (zza4 != null) {
                                        smallIcon.setDeleteIntent(zza4);
                                    }
                                    build = smallIcon.build();
                                    zza5 = zza(bundle, "gcm.n.tag");
                                    if (Log.isLoggable("FirebaseMessaging", 3)) {
                                        Log.d("FirebaseMessaging", "Showing notification");
                                    }
                                    notificationManager = (NotificationManager) this.zzv.getSystemService("notification");
                                    if (TextUtils.isEmpty(zza5)) {
                                        uptimeMillis = SystemClock.uptimeMillis();
                                        stringBuilder = new StringBuilder(37);
                                        stringBuilder.append("FCM-Notification:");
                                        stringBuilder.append(uptimeMillis);
                                        zza5 = stringBuilder.toString();
                                    }
                                    notificationManager.notify(zza5, 0, build);
                                    return true;
                                }
                                identifier2 = resources.getIdentifier(zza, "mipmap", this.zzv.getPackageName());
                                if (identifier2 == 0 && zzb(identifier2)) {
                                    identifier = identifier2;
                                    zzm = zzm(zza(bundle, "gcm.n.color"));
                                    zza = zzi(bundle);
                                    if (TextUtils.isEmpty(zza)) {
                                        uri = null;
                                    } else if ("default".equals(zza) || this.zzv.getResources().getIdentifier(zza, "raw", this.zzv.getPackageName()) == 0) {
                                        uri = RingtoneManager.getDefaultUri(2);
                                    } else {
                                        String packageName = this.zzv.getPackageName();
                                        StringBuilder stringBuilder2 = new StringBuilder((String.valueOf(packageName).length() + 24) + String.valueOf(zza).length());
                                        stringBuilder2.append("android.resource://");
                                        stringBuilder2.append(packageName);
                                        stringBuilder2.append("/raw/");
                                        stringBuilder2.append(zza);
                                        uri = Uri.parse(stringBuilder2.toString());
                                    }
                                    zza2 = zza(bundle, "gcm.n.click_action");
                                    if (TextUtils.isEmpty(zza2)) {
                                        launchIntentForPackage = new Intent(zza2);
                                        launchIntentForPackage.setPackage(this.zzv.getPackageName());
                                        launchIntentForPackage.setFlags(ErrorDialogData.BINDER_CRASH);
                                    } else {
                                        zzg = zzg(bundle);
                                        if (zzg == null) {
                                            launchIntentForPackage = new Intent("android.intent.action.VIEW");
                                            launchIntentForPackage.setPackage(this.zzv.getPackageName());
                                            launchIntentForPackage.setData(zzg);
                                        } else {
                                            launchIntentForPackage = this.zzv.getPackageManager().getLaunchIntentForPackage(this.zzv.getPackageName());
                                            if (launchIntentForPackage == null) {
                                                Log.w("FirebaseMessaging", "No activity found to launch app");
                                            }
                                        }
                                    }
                                    if (launchIntentForPackage != null) {
                                        activity = null;
                                    } else {
                                        launchIntentForPackage.addFlags(67108864);
                                        bundle2 = new Bundle(bundle);
                                        FirebaseMessagingService.zzj(bundle2);
                                        launchIntentForPackage.putExtras(bundle2);
                                        for (String str2 : bundle2.keySet()) {
                                            if (str2.startsWith("gcm.n.") || str2.startsWith("gcm.notification.")) {
                                                launchIntentForPackage.removeExtra(str2);
                                            }
                                        }
                                        activity = PendingIntent.getActivity(this.zzv, this.zzdi.incrementAndGet(), launchIntentForPackage, ErrorDialogData.SUPPRESSED);
                                    }
                                    if (FirebaseMessagingService.zzk(bundle)) {
                                        intent = new Intent("com.google.firebase.messaging.NOTIFICATION_OPEN");
                                        zza(intent, bundle);
                                        intent.putExtra("pending_intent", activity);
                                        zza3 = zzat.zza(this.zzv, this.zzdi.incrementAndGet(), intent, ErrorDialogData.SUPPRESSED);
                                        launchIntentForPackage = new Intent("com.google.firebase.messaging.NOTIFICATION_DISMISS");
                                        zza(launchIntentForPackage, bundle);
                                        zza4 = zzat.zza(this.zzv, this.zzdi.incrementAndGet(), launchIntentForPackage, ErrorDialogData.SUPPRESSED);
                                        activity = zza3;
                                    } else {
                                        zza4 = null;
                                    }
                                    if (PlatformVersion.isAtLeastO() || this.zzv.getApplicationInfo().targetSdkVersion <= 25) {
                                        smallIcon = new NotificationCompat.Builder(this.zzv).setAutoCancel(true).setSmallIcon(identifier);
                                        if (TextUtils.isEmpty(charSequence)) {
                                            smallIcon.setContentTitle(charSequence);
                                        }
                                        if (TextUtils.isEmpty(zzd2)) {
                                            smallIcon.setContentText(zzd2);
                                            smallIcon.setStyle(new NotificationCompat.BigTextStyle().bigText(zzd2));
                                        }
                                        if (zzm != null) {
                                            smallIcon.setColor(zzm.intValue());
                                        }
                                        if (uri != null) {
                                            smallIcon.setSound(uri);
                                        }
                                        if (activity != null) {
                                            smallIcon.setContentIntent(activity);
                                        }
                                        if (zza4 != null) {
                                            smallIcon.setDeleteIntent(zza4);
                                        }
                                        build = smallIcon.build();
                                    } else {
                                        build = zza(charSequence, zzd2, identifier, zzm, uri, activity, zza4, zzn(zza(bundle, "gcm.n.android_channel_id")));
                                    }
                                    zza5 = zza(bundle, "gcm.n.tag");
                                    if (Log.isLoggable("FirebaseMessaging", 3)) {
                                        Log.d("FirebaseMessaging", "Showing notification");
                                    }
                                    notificationManager = (NotificationManager) this.zzv.getSystemService("notification");
                                    if (TextUtils.isEmpty(zza5)) {
                                        uptimeMillis = SystemClock.uptimeMillis();
                                        stringBuilder = new StringBuilder(37);
                                        stringBuilder.append("FCM-Notification:");
                                        stringBuilder.append(uptimeMillis);
                                        zza5 = stringBuilder.toString();
                                    }
                                    notificationManager.notify(zza5, 0, build);
                                    return true;
                                }
                                StringBuilder stringBuilder3 = new StringBuilder(String.valueOf(zza).length() + 61);
                                stringBuilder3.append("Icon resource ");
                                stringBuilder3.append(zza);
                                stringBuilder3.append(" not found. Notification will use default icon.");
                                Log.w("FirebaseMessaging", stringBuilder3.toString());
                            }
                            myPid = zzar().getInt("com.google.firebase.messaging.default_notification_icon", 0);
                            if (myPid == 0 || !zzb(myPid)) {
                                myPid = this.zzv.getApplicationInfo().icon;
                            }
                            if (myPid == 0 || !zzb(myPid)) {
                                myPid = 17301651;
                            }
                            identifier = myPid;
                            zzm = zzm(zza(bundle, "gcm.n.color"));
                            zza = zzi(bundle);
                            if (TextUtils.isEmpty(zza)) {
                                uri = null;
                            } else {
                                if ("default".equals(zza)) {
                                }
                                uri = RingtoneManager.getDefaultUri(2);
                            }
                            zza2 = zza(bundle, "gcm.n.click_action");
                            if (TextUtils.isEmpty(zza2)) {
                                launchIntentForPackage = new Intent(zza2);
                                launchIntentForPackage.setPackage(this.zzv.getPackageName());
                                launchIntentForPackage.setFlags(ErrorDialogData.BINDER_CRASH);
                            } else {
                                zzg = zzg(bundle);
                                if (zzg == null) {
                                    launchIntentForPackage = new Intent("android.intent.action.VIEW");
                                    launchIntentForPackage.setPackage(this.zzv.getPackageName());
                                    launchIntentForPackage.setData(zzg);
                                } else {
                                    launchIntentForPackage = this.zzv.getPackageManager().getLaunchIntentForPackage(this.zzv.getPackageName());
                                    if (launchIntentForPackage == null) {
                                        Log.w("FirebaseMessaging", "No activity found to launch app");
                                    }
                                }
                            }
                            if (launchIntentForPackage != null) {
                                activity = null;
                            } else {
                                launchIntentForPackage.addFlags(67108864);
                                bundle2 = new Bundle(bundle);
                                FirebaseMessagingService.zzj(bundle2);
                                launchIntentForPackage.putExtras(bundle2);
                                for (String str22 : bundle2.keySet()) {
                                    if (str22.startsWith("gcm.n.")) {
                                    }
                                    launchIntentForPackage.removeExtra(str22);
                                }
                                activity = PendingIntent.getActivity(this.zzv, this.zzdi.incrementAndGet(), launchIntentForPackage, ErrorDialogData.SUPPRESSED);
                            }
                            if (FirebaseMessagingService.zzk(bundle)) {
                                intent = new Intent("com.google.firebase.messaging.NOTIFICATION_OPEN");
                                zza(intent, bundle);
                                intent.putExtra("pending_intent", activity);
                                zza3 = zzat.zza(this.zzv, this.zzdi.incrementAndGet(), intent, ErrorDialogData.SUPPRESSED);
                                launchIntentForPackage = new Intent("com.google.firebase.messaging.NOTIFICATION_DISMISS");
                                zza(launchIntentForPackage, bundle);
                                zza4 = zzat.zza(this.zzv, this.zzdi.incrementAndGet(), launchIntentForPackage, ErrorDialogData.SUPPRESSED);
                                activity = zza3;
                            } else {
                                zza4 = null;
                            }
                            if (PlatformVersion.isAtLeastO()) {
                            }
                            smallIcon = new NotificationCompat.Builder(this.zzv).setAutoCancel(true).setSmallIcon(identifier);
                            if (TextUtils.isEmpty(charSequence)) {
                                smallIcon.setContentTitle(charSequence);
                            }
                            if (TextUtils.isEmpty(zzd2)) {
                                smallIcon.setContentText(zzd2);
                                smallIcon.setStyle(new NotificationCompat.BigTextStyle().bigText(zzd2));
                            }
                            if (zzm != null) {
                                smallIcon.setColor(zzm.intValue());
                            }
                            if (uri != null) {
                                smallIcon.setSound(uri);
                            }
                            if (activity != null) {
                                smallIcon.setContentIntent(activity);
                            }
                            if (zza4 != null) {
                                smallIcon.setDeleteIntent(zza4);
                            }
                            build = smallIcon.build();
                            zza5 = zza(bundle, "gcm.n.tag");
                            if (Log.isLoggable("FirebaseMessaging", 3)) {
                                Log.d("FirebaseMessaging", "Showing notification");
                            }
                            notificationManager = (NotificationManager) this.zzv.getSystemService("notification");
                            if (TextUtils.isEmpty(zza5)) {
                                uptimeMillis = SystemClock.uptimeMillis();
                                stringBuilder = new StringBuilder(37);
                                stringBuilder.append("FCM-Notification:");
                                stringBuilder.append(uptimeMillis);
                                zza5 = stringBuilder.toString();
                            }
                            notificationManager.notify(zza5, 0, build);
                            return true;
                        }
                    }
                }
            }
        }
        obj = null;
        if (obj != null) {
            return false;
        }
        zzd = zzd(bundle, "gcm.n.title");
        if (TextUtils.isEmpty(zzd)) {
            zzd = this.zzv.getApplicationInfo().loadLabel(this.zzv.getPackageManager());
        }
        charSequence = zzd;
        zzd2 = zzd(bundle, "gcm.n.body");
        zza = zza(bundle, "gcm.n.icon");
        if (TextUtils.isEmpty(zza)) {
            resources = this.zzv.getResources();
            identifier = resources.getIdentifier(zza, "drawable", this.zzv.getPackageName());
            if (identifier == 0) {
            }
            identifier2 = resources.getIdentifier(zza, "mipmap", this.zzv.getPackageName());
            if (identifier2 == 0) {
            }
            StringBuilder stringBuilder32 = new StringBuilder(String.valueOf(zza).length() + 61);
            stringBuilder32.append("Icon resource ");
            stringBuilder32.append(zza);
            stringBuilder32.append(" not found. Notification will use default icon.");
            Log.w("FirebaseMessaging", stringBuilder32.toString());
        }
        myPid = zzar().getInt("com.google.firebase.messaging.default_notification_icon", 0);
        myPid = this.zzv.getApplicationInfo().icon;
        myPid = 17301651;
        identifier = myPid;
        zzm = zzm(zza(bundle, "gcm.n.color"));
        zza = zzi(bundle);
        if (TextUtils.isEmpty(zza)) {
            if ("default".equals(zza)) {
            }
            uri = RingtoneManager.getDefaultUri(2);
        } else {
            uri = null;
        }
        zza2 = zza(bundle, "gcm.n.click_action");
        if (TextUtils.isEmpty(zza2)) {
            zzg = zzg(bundle);
            if (zzg == null) {
                launchIntentForPackage = this.zzv.getPackageManager().getLaunchIntentForPackage(this.zzv.getPackageName());
                if (launchIntentForPackage == null) {
                    Log.w("FirebaseMessaging", "No activity found to launch app");
                }
            } else {
                launchIntentForPackage = new Intent("android.intent.action.VIEW");
                launchIntentForPackage.setPackage(this.zzv.getPackageName());
                launchIntentForPackage.setData(zzg);
            }
        } else {
            launchIntentForPackage = new Intent(zza2);
            launchIntentForPackage.setPackage(this.zzv.getPackageName());
            launchIntentForPackage.setFlags(ErrorDialogData.BINDER_CRASH);
        }
        if (launchIntentForPackage != null) {
            launchIntentForPackage.addFlags(67108864);
            bundle2 = new Bundle(bundle);
            FirebaseMessagingService.zzj(bundle2);
            launchIntentForPackage.putExtras(bundle2);
            for (String str222 : bundle2.keySet()) {
                if (str222.startsWith("gcm.n.")) {
                }
                launchIntentForPackage.removeExtra(str222);
            }
            activity = PendingIntent.getActivity(this.zzv, this.zzdi.incrementAndGet(), launchIntentForPackage, ErrorDialogData.SUPPRESSED);
        } else {
            activity = null;
        }
        if (FirebaseMessagingService.zzk(bundle)) {
            zza4 = null;
        } else {
            intent = new Intent("com.google.firebase.messaging.NOTIFICATION_OPEN");
            zza(intent, bundle);
            intent.putExtra("pending_intent", activity);
            zza3 = zzat.zza(this.zzv, this.zzdi.incrementAndGet(), intent, ErrorDialogData.SUPPRESSED);
            launchIntentForPackage = new Intent("com.google.firebase.messaging.NOTIFICATION_DISMISS");
            zza(launchIntentForPackage, bundle);
            zza4 = zzat.zza(this.zzv, this.zzdi.incrementAndGet(), launchIntentForPackage, ErrorDialogData.SUPPRESSED);
            activity = zza3;
        }
        if (PlatformVersion.isAtLeastO()) {
        }
        smallIcon = new NotificationCompat.Builder(this.zzv).setAutoCancel(true).setSmallIcon(identifier);
        if (TextUtils.isEmpty(charSequence)) {
            smallIcon.setContentTitle(charSequence);
        }
        if (TextUtils.isEmpty(zzd2)) {
            smallIcon.setContentText(zzd2);
            smallIcon.setStyle(new NotificationCompat.BigTextStyle().bigText(zzd2));
        }
        if (zzm != null) {
            smallIcon.setColor(zzm.intValue());
        }
        if (uri != null) {
            smallIcon.setSound(uri);
        }
        if (activity != null) {
            smallIcon.setContentIntent(activity);
        }
        if (zza4 != null) {
            smallIcon.setDeleteIntent(zza4);
        }
        build = smallIcon.build();
        zza5 = zza(bundle, "gcm.n.tag");
        if (Log.isLoggable("FirebaseMessaging", 3)) {
            Log.d("FirebaseMessaging", "Showing notification");
        }
        notificationManager = (NotificationManager) this.zzv.getSystemService("notification");
        if (TextUtils.isEmpty(zza5)) {
            uptimeMillis = SystemClock.uptimeMillis();
            stringBuilder = new StringBuilder(37);
            stringBuilder.append("FCM-Notification:");
            stringBuilder.append(uptimeMillis);
            zza5 = stringBuilder.toString();
        }
        notificationManager.notify(zza5, 0, build);
        return true;
    }
}
