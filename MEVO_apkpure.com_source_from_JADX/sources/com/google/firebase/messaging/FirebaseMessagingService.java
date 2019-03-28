package com.google.firebase.messaging;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.iid.zzat;
import com.google.firebase.iid.zzb;
import com.google.firebase.iid.zzz;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class FirebaseMessagingService extends zzb {
    private static final Queue<String> zzdl = new ArrayDeque(10);

    static void zzj(Bundle bundle) {
        Iterator it = bundle.keySet().iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str != null && str.startsWith("google.c.")) {
                it.remove();
            }
        }
    }

    static boolean zzk(Bundle bundle) {
        return bundle == null ? false : "1".equals(bundle.getString("google.c.a.e"));
    }

    @WorkerThread
    public void onDeletedMessages() {
    }

    @WorkerThread
    public void onMessageReceived(RemoteMessage remoteMessage) {
    }

    @WorkerThread
    public void onMessageSent(String str) {
    }

    @WorkerThread
    public void onNewToken(String str) {
    }

    @WorkerThread
    public void onSendError(String str, Exception exception) {
    }

    protected final Intent zzb(Intent intent) {
        return zzat.zzah().zzai();
    }

    public final boolean zzc(android.content.Intent r3) {
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
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r2 = this;
        r0 = "com.google.firebase.messaging.NOTIFICATION_OPEN";
        r1 = r3.getAction();
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0030;
    L_0x000c:
        r0 = "pending_intent";
        r0 = r3.getParcelableExtra(r0);
        r0 = (android.app.PendingIntent) r0;
        if (r0 == 0) goto L_0x0021;
    L_0x0016:
        r0.send();	 Catch:{ CanceledException -> 0x001a }
        goto L_0x0021;
    L_0x001a:
        r0 = "FirebaseMessaging";
        r1 = "Notification pending intent canceled";
        android.util.Log.e(r0, r1);
    L_0x0021:
        r0 = r3.getExtras();
        r0 = zzk(r0);
        if (r0 == 0) goto L_0x002e;
    L_0x002b:
        com.google.firebase.messaging.zzb.zzf(r2, r3);
    L_0x002e:
        r3 = 1;
        return r3;
    L_0x0030:
        r3 = 0;
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.FirebaseMessagingService.zzc(android.content.Intent):boolean");
    }

    public final void zzd(Intent intent) {
        String action = intent.getAction();
        String str;
        if ("com.google.android.c2dm.intent.RECEIVE".equals(action)) {
            Task forResult;
            Object obj;
            int hashCode;
            Bundle extras;
            String str2;
            CharSequence stringExtra = intent.getStringExtra("google.message_id");
            int i = 2;
            if (TextUtils.isEmpty(stringExtra)) {
                forResult = Tasks.forResult(null);
            } else {
                Bundle bundle = new Bundle();
                bundle.putString("google.message_id", stringExtra);
                forResult = zzz.zzc(this).zza(2, bundle);
            }
            if (!TextUtils.isEmpty(stringExtra)) {
                if (zzdl.contains(stringExtra)) {
                    if (Log.isLoggable("FirebaseMessaging", 3)) {
                        String str3 = "FirebaseMessaging";
                        String str4 = "Received duplicate message: ";
                        action = String.valueOf(stringExtra);
                        Log.d(str3, action.length() != 0 ? str4.concat(action) : new String(str4));
                    }
                    obj = 1;
                    if (obj == null) {
                        action = intent.getStringExtra("message_type");
                        if (action == null) {
                            action = "gcm";
                        }
                        hashCode = action.hashCode();
                        if (hashCode != -2062414158) {
                            if (action.equals("deleted_messages")) {
                                i = 1;
                                switch (i) {
                                    case 0:
                                        if (zzk(intent.getExtras())) {
                                            zzb.zze(this, intent);
                                        }
                                        extras = intent.getExtras();
                                        if (extras == null) {
                                            extras = new Bundle();
                                        }
                                        extras.remove("android.support.content.wakelockid");
                                        if (zza.zzf(extras)) {
                                            if (!zza.zzd(this).zzh(extras)) {
                                                if (zzk(extras)) {
                                                    zzb.zzh(this, intent);
                                                }
                                            }
                                        }
                                        onMessageReceived(new RemoteMessage(extras));
                                        break;
                                    case 1:
                                        onDeletedMessages();
                                        break;
                                    case 2:
                                        onMessageSent(intent.getStringExtra("google.message_id"));
                                        break;
                                    case 3:
                                        action = intent.getStringExtra("google.message_id");
                                        if (action == null) {
                                            action = intent.getStringExtra("message_id");
                                        }
                                        onSendError(action, new SendException(intent.getStringExtra("error")));
                                        break;
                                    default:
                                        str = "FirebaseMessaging";
                                        str2 = "Received message with unknown type: ";
                                        action = String.valueOf(action);
                                        if (action.length() == 0) {
                                        }
                                        Log.w(str, action.length() == 0 ? str2.concat(action) : new String(str2));
                                        break;
                                }
                            }
                        } else if (hashCode != 102161) {
                            if (action.equals("gcm")) {
                                i = 0;
                                switch (i) {
                                    case 0:
                                        if (zzk(intent.getExtras())) {
                                            zzb.zze(this, intent);
                                        }
                                        extras = intent.getExtras();
                                        if (extras == null) {
                                            extras = new Bundle();
                                        }
                                        extras.remove("android.support.content.wakelockid");
                                        if (zza.zzf(extras)) {
                                            if (zza.zzd(this).zzh(extras)) {
                                                if (zzk(extras)) {
                                                    zzb.zzh(this, intent);
                                                }
                                            }
                                        }
                                        onMessageReceived(new RemoteMessage(extras));
                                        break;
                                    case 1:
                                        onDeletedMessages();
                                        break;
                                    case 2:
                                        onMessageSent(intent.getStringExtra("google.message_id"));
                                        break;
                                    case 3:
                                        action = intent.getStringExtra("google.message_id");
                                        if (action == null) {
                                            action = intent.getStringExtra("message_id");
                                        }
                                        onSendError(action, new SendException(intent.getStringExtra("error")));
                                        break;
                                    default:
                                        str = "FirebaseMessaging";
                                        str2 = "Received message with unknown type: ";
                                        action = String.valueOf(action);
                                        if (action.length() == 0) {
                                        }
                                        Log.w(str, action.length() == 0 ? str2.concat(action) : new String(str2));
                                        break;
                                }
                            }
                        } else if (hashCode != 814694033) {
                            if (action.equals("send_error")) {
                                i = 3;
                                switch (i) {
                                    case 0:
                                        if (zzk(intent.getExtras())) {
                                            zzb.zze(this, intent);
                                        }
                                        extras = intent.getExtras();
                                        if (extras == null) {
                                            extras = new Bundle();
                                        }
                                        extras.remove("android.support.content.wakelockid");
                                        if (zza.zzf(extras)) {
                                            if (zza.zzd(this).zzh(extras)) {
                                                if (zzk(extras)) {
                                                    zzb.zzh(this, intent);
                                                }
                                            }
                                        }
                                        onMessageReceived(new RemoteMessage(extras));
                                        break;
                                    case 1:
                                        onDeletedMessages();
                                        break;
                                    case 2:
                                        onMessageSent(intent.getStringExtra("google.message_id"));
                                        break;
                                    case 3:
                                        action = intent.getStringExtra("google.message_id");
                                        if (action == null) {
                                            action = intent.getStringExtra("message_id");
                                        }
                                        onSendError(action, new SendException(intent.getStringExtra("error")));
                                        break;
                                    default:
                                        str = "FirebaseMessaging";
                                        str2 = "Received message with unknown type: ";
                                        action = String.valueOf(action);
                                        if (action.length() == 0) {
                                        }
                                        Log.w(str, action.length() == 0 ? str2.concat(action) : new String(str2));
                                        break;
                                }
                            }
                        } else if (hashCode != 814800675) {
                            if (action.equals("send_event")) {
                                switch (i) {
                                    case 0:
                                        if (zzk(intent.getExtras())) {
                                            zzb.zze(this, intent);
                                        }
                                        extras = intent.getExtras();
                                        if (extras == null) {
                                            extras = new Bundle();
                                        }
                                        extras.remove("android.support.content.wakelockid");
                                        if (zza.zzf(extras)) {
                                            if (zza.zzd(this).zzh(extras)) {
                                                if (zzk(extras)) {
                                                    zzb.zzh(this, intent);
                                                }
                                            }
                                        }
                                        onMessageReceived(new RemoteMessage(extras));
                                        break;
                                    case 1:
                                        onDeletedMessages();
                                        break;
                                    case 2:
                                        onMessageSent(intent.getStringExtra("google.message_id"));
                                        break;
                                    case 3:
                                        action = intent.getStringExtra("google.message_id");
                                        if (action == null) {
                                            action = intent.getStringExtra("message_id");
                                        }
                                        onSendError(action, new SendException(intent.getStringExtra("error")));
                                        break;
                                    default:
                                        str = "FirebaseMessaging";
                                        str2 = "Received message with unknown type: ";
                                        action = String.valueOf(action);
                                        Log.w(str, action.length() == 0 ? str2.concat(action) : new String(str2));
                                        break;
                                }
                            }
                        }
                        i = -1;
                        switch (i) {
                            case 0:
                                if (zzk(intent.getExtras())) {
                                    zzb.zze(this, intent);
                                }
                                extras = intent.getExtras();
                                if (extras == null) {
                                    extras = new Bundle();
                                }
                                extras.remove("android.support.content.wakelockid");
                                if (zza.zzf(extras)) {
                                    if (zza.zzd(this).zzh(extras)) {
                                        if (zzk(extras)) {
                                            zzb.zzh(this, intent);
                                        }
                                    }
                                }
                                onMessageReceived(new RemoteMessage(extras));
                                break;
                            case 1:
                                onDeletedMessages();
                                break;
                            case 2:
                                onMessageSent(intent.getStringExtra("google.message_id"));
                                break;
                            case 3:
                                action = intent.getStringExtra("google.message_id");
                                if (action == null) {
                                    action = intent.getStringExtra("message_id");
                                }
                                onSendError(action, new SendException(intent.getStringExtra("error")));
                                break;
                            default:
                                str = "FirebaseMessaging";
                                str2 = "Received message with unknown type: ";
                                action = String.valueOf(action);
                                if (action.length() == 0) {
                                }
                                Log.w(str, action.length() == 0 ? str2.concat(action) : new String(str2));
                                break;
                        }
                    }
                    Tasks.await(forResult, 1, TimeUnit.SECONDS);
                    return;
                }
                if (zzdl.size() >= 10) {
                    zzdl.remove();
                }
                zzdl.add(stringExtra);
            }
            obj = null;
            if (obj == null) {
                action = intent.getStringExtra("message_type");
                if (action == null) {
                    action = "gcm";
                }
                hashCode = action.hashCode();
                if (hashCode != -2062414158) {
                    if (action.equals("deleted_messages")) {
                        i = 1;
                        switch (i) {
                            case 0:
                                if (zzk(intent.getExtras())) {
                                    zzb.zze(this, intent);
                                }
                                extras = intent.getExtras();
                                if (extras == null) {
                                    extras = new Bundle();
                                }
                                extras.remove("android.support.content.wakelockid");
                                if (zza.zzf(extras)) {
                                    if (zza.zzd(this).zzh(extras)) {
                                        if (zzk(extras)) {
                                            zzb.zzh(this, intent);
                                        }
                                    }
                                }
                                onMessageReceived(new RemoteMessage(extras));
                                break;
                            case 1:
                                onDeletedMessages();
                                break;
                            case 2:
                                onMessageSent(intent.getStringExtra("google.message_id"));
                                break;
                            case 3:
                                action = intent.getStringExtra("google.message_id");
                                if (action == null) {
                                    action = intent.getStringExtra("message_id");
                                }
                                onSendError(action, new SendException(intent.getStringExtra("error")));
                                break;
                            default:
                                str = "FirebaseMessaging";
                                str2 = "Received message with unknown type: ";
                                action = String.valueOf(action);
                                if (action.length() == 0) {
                                }
                                Log.w(str, action.length() == 0 ? str2.concat(action) : new String(str2));
                                break;
                        }
                    }
                } else if (hashCode != 102161) {
                    if (action.equals("gcm")) {
                        i = 0;
                        switch (i) {
                            case 0:
                                if (zzk(intent.getExtras())) {
                                    zzb.zze(this, intent);
                                }
                                extras = intent.getExtras();
                                if (extras == null) {
                                    extras = new Bundle();
                                }
                                extras.remove("android.support.content.wakelockid");
                                if (zza.zzf(extras)) {
                                    if (zza.zzd(this).zzh(extras)) {
                                        if (zzk(extras)) {
                                            zzb.zzh(this, intent);
                                        }
                                    }
                                }
                                onMessageReceived(new RemoteMessage(extras));
                                break;
                            case 1:
                                onDeletedMessages();
                                break;
                            case 2:
                                onMessageSent(intent.getStringExtra("google.message_id"));
                                break;
                            case 3:
                                action = intent.getStringExtra("google.message_id");
                                if (action == null) {
                                    action = intent.getStringExtra("message_id");
                                }
                                onSendError(action, new SendException(intent.getStringExtra("error")));
                                break;
                            default:
                                str = "FirebaseMessaging";
                                str2 = "Received message with unknown type: ";
                                action = String.valueOf(action);
                                if (action.length() == 0) {
                                }
                                Log.w(str, action.length() == 0 ? str2.concat(action) : new String(str2));
                                break;
                        }
                    }
                } else if (hashCode != 814694033) {
                    if (action.equals("send_error")) {
                        i = 3;
                        switch (i) {
                            case 0:
                                if (zzk(intent.getExtras())) {
                                    zzb.zze(this, intent);
                                }
                                extras = intent.getExtras();
                                if (extras == null) {
                                    extras = new Bundle();
                                }
                                extras.remove("android.support.content.wakelockid");
                                if (zza.zzf(extras)) {
                                    if (zza.zzd(this).zzh(extras)) {
                                        if (zzk(extras)) {
                                            zzb.zzh(this, intent);
                                        }
                                    }
                                }
                                onMessageReceived(new RemoteMessage(extras));
                                break;
                            case 1:
                                onDeletedMessages();
                                break;
                            case 2:
                                onMessageSent(intent.getStringExtra("google.message_id"));
                                break;
                            case 3:
                                action = intent.getStringExtra("google.message_id");
                                if (action == null) {
                                    action = intent.getStringExtra("message_id");
                                }
                                onSendError(action, new SendException(intent.getStringExtra("error")));
                                break;
                            default:
                                str = "FirebaseMessaging";
                                str2 = "Received message with unknown type: ";
                                action = String.valueOf(action);
                                if (action.length() == 0) {
                                }
                                Log.w(str, action.length() == 0 ? str2.concat(action) : new String(str2));
                                break;
                        }
                    }
                } else if (hashCode != 814800675) {
                    if (action.equals("send_event")) {
                        switch (i) {
                            case 0:
                                if (zzk(intent.getExtras())) {
                                    zzb.zze(this, intent);
                                }
                                extras = intent.getExtras();
                                if (extras == null) {
                                    extras = new Bundle();
                                }
                                extras.remove("android.support.content.wakelockid");
                                if (zza.zzf(extras)) {
                                    if (zza.zzd(this).zzh(extras)) {
                                        if (zzk(extras)) {
                                            zzb.zzh(this, intent);
                                        }
                                    }
                                }
                                onMessageReceived(new RemoteMessage(extras));
                                break;
                            case 1:
                                onDeletedMessages();
                                break;
                            case 2:
                                onMessageSent(intent.getStringExtra("google.message_id"));
                                break;
                            case 3:
                                action = intent.getStringExtra("google.message_id");
                                if (action == null) {
                                    action = intent.getStringExtra("message_id");
                                }
                                onSendError(action, new SendException(intent.getStringExtra("error")));
                                break;
                            default:
                                str = "FirebaseMessaging";
                                str2 = "Received message with unknown type: ";
                                action = String.valueOf(action);
                                if (action.length() == 0) {
                                }
                                Log.w(str, action.length() == 0 ? str2.concat(action) : new String(str2));
                                break;
                        }
                    }
                }
                i = -1;
                switch (i) {
                    case 0:
                        if (zzk(intent.getExtras())) {
                            zzb.zze(this, intent);
                        }
                        extras = intent.getExtras();
                        if (extras == null) {
                            extras = new Bundle();
                        }
                        extras.remove("android.support.content.wakelockid");
                        if (zza.zzf(extras)) {
                            if (zza.zzd(this).zzh(extras)) {
                                if (zzk(extras)) {
                                    zzb.zzh(this, intent);
                                }
                            }
                        }
                        onMessageReceived(new RemoteMessage(extras));
                        break;
                    case 1:
                        onDeletedMessages();
                        break;
                    case 2:
                        onMessageSent(intent.getStringExtra("google.message_id"));
                        break;
                    case 3:
                        action = intent.getStringExtra("google.message_id");
                        if (action == null) {
                            action = intent.getStringExtra("message_id");
                        }
                        onSendError(action, new SendException(intent.getStringExtra("error")));
                        break;
                    default:
                        str = "FirebaseMessaging";
                        str2 = "Received message with unknown type: ";
                        action = String.valueOf(action);
                        if (action.length() == 0) {
                        }
                        Log.w(str, action.length() == 0 ? str2.concat(action) : new String(str2));
                        break;
                }
            }
            try {
                Tasks.await(forResult, 1, TimeUnit.SECONDS);
                return;
            } catch (ExecutionException e) {
                str = String.valueOf(e);
                StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 20);
                stringBuilder.append("Message ack failed: ");
                stringBuilder.append(str);
                Log.w("FirebaseMessaging", stringBuilder.toString());
                return;
            }
        }
        if ("com.google.firebase.messaging.NOTIFICATION_DISMISS".equals(action)) {
            if (zzk(intent.getExtras())) {
                zzb.zzg(this, intent);
            }
        } else if ("com.google.firebase.messaging.NEW_TOKEN".equals(action)) {
            onNewToken(intent.getStringExtra("token"));
        } else {
            action = "FirebaseMessaging";
            String str5 = "Unknown intent action: ";
            str = String.valueOf(intent.getAction());
            Log.d(action, str.length() != 0 ? str5.concat(str) : new String(str5));
        }
    }
}
