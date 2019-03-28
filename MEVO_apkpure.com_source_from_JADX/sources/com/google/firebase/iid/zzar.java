package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.iid.zzk.zza;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.concurrent.GuardedBy;

final class zzar {
    private static int zzbw;
    private static PendingIntent zzci;
    private final zzal zzak;
    @GuardedBy("responseCallbacks")
    private final SimpleArrayMap<String, TaskCompletionSource<Bundle>> zzcj = new SimpleArrayMap();
    private Messenger zzck;
    private Messenger zzcl;
    private zzk zzcm;
    private final Context zzv;

    public zzar(Context context, zzal zzal) {
        this.zzv = context;
        this.zzak = zzal;
        this.zzck = new Messenger(new zzas(this, Looper.getMainLooper()));
    }

    private static synchronized void zza(Context context, Intent intent) {
        synchronized (zzar.class) {
            if (zzci == null) {
                Intent intent2 = new Intent();
                intent2.setPackage("com.google.example.invalidpackage");
                zzci = PendingIntent.getBroadcast(context, 0, intent2, 0);
            }
            intent.putExtra(SettingsJsonConstants.APP_KEY, zzci);
        }
    }

    private final void zza(String str, Bundle bundle) {
        synchronized (this.zzcj) {
            TaskCompletionSource taskCompletionSource = (TaskCompletionSource) this.zzcj.remove(str);
            if (taskCompletionSource == null) {
                String str2 = "FirebaseInstanceId";
                String str3 = "Missing callback for ";
                str = String.valueOf(str);
                Log.w(str2, str.length() != 0 ? str3.concat(str) : new String(str3));
                return;
            }
            taskCompletionSource.setResult(bundle);
        }
    }

    private static synchronized String zzag() {
        String num;
        synchronized (zzar.class) {
            int i = zzbw;
            zzbw = i + 1;
            num = Integer.toString(i);
        }
        return num;
    }

    private final void zzb(Message message) {
        String str;
        String str2;
        if (message == null || !(message.obj instanceof Intent)) {
            str = "FirebaseInstanceId";
            str2 = "Dropping invalid message";
        } else {
            Intent intent = (Intent) message.obj;
            intent.setExtrasClassLoader(new zza());
            if (intent.hasExtra("google.messenger")) {
                Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
                if (parcelableExtra instanceof zzk) {
                    this.zzcm = (zzk) parcelableExtra;
                }
                if (parcelableExtra instanceof Messenger) {
                    this.zzcl = (Messenger) parcelableExtra;
                }
            }
            Intent intent2 = (Intent) message.obj;
            str2 = intent2.getAction();
            String str3;
            if ("com.google.android.c2dm.intent.REGISTRATION".equals(str2)) {
                CharSequence stringExtra = intent2.getStringExtra("registration_id");
                if (stringExtra == null) {
                    stringExtra = intent2.getStringExtra("unregistered");
                }
                if (stringExtra == null) {
                    str2 = intent2.getStringExtra("error");
                    if (str2 == null) {
                        str = String.valueOf(intent2.getExtras());
                        StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 49);
                        stringBuilder.append("Unexpected response, no error or registration id ");
                        stringBuilder.append(str);
                        Log.w("FirebaseInstanceId", stringBuilder.toString());
                        return;
                    }
                    if (Log.isLoggable("FirebaseInstanceId", 3)) {
                        String str4 = "FirebaseInstanceId";
                        String str5 = "Received InstanceID error ";
                        String valueOf = String.valueOf(str2);
                        Log.d(str4, valueOf.length() != 0 ? str5.concat(valueOf) : new String(str5));
                    }
                    if (str2.startsWith("|")) {
                        String[] split = str2.split("\\|");
                        if (split.length > 2) {
                            if ("ID".equals(split[1])) {
                                str2 = split[2];
                                str3 = split[3];
                                if (str3.startsWith(":")) {
                                    str3 = str3.substring(1);
                                }
                                zza(str2, intent2.putExtra("error", str3).getExtras());
                                return;
                            }
                        }
                        str = "FirebaseInstanceId";
                        str3 = "Unexpected structured response ";
                        str2 = String.valueOf(str2);
                        str2 = str2.length() != 0 ? str3.concat(str2) : new String(str3);
                    } else {
                        synchronized (this.zzcj) {
                            for (int i = 0; i < this.zzcj.size(); i++) {
                                zza((String) this.zzcj.keyAt(i), intent2.getExtras());
                            }
                        }
                        return;
                    }
                }
                Matcher matcher = Pattern.compile("\\|ID\\|([^|]+)\\|:?+(.*)").matcher(stringExtra);
                if (matcher.matches()) {
                    str2 = matcher.group(1);
                    str3 = matcher.group(2);
                    Bundle extras = intent2.getExtras();
                    extras.putString("registration_id", str3);
                    zza(str2, extras);
                    return;
                }
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    str = "FirebaseInstanceId";
                    str3 = "Unexpected response string: ";
                    str2 = String.valueOf(stringExtra);
                    Log.d(str, str2.length() != 0 ? str3.concat(str2) : new String(str3));
                }
                return;
            }
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                str = "FirebaseInstanceId";
                str3 = "Unexpected response action: ";
                str2 = String.valueOf(str2);
                Log.d(str, str2.length() != 0 ? str3.concat(str2) : new String(str3));
            }
            return;
        }
        Log.w(str, str2);
    }

    private final Bundle zzd(Bundle bundle) throws IOException {
        Bundle zze = zze(bundle);
        if (zze == null || !zze.containsKey("google.messenger")) {
            return zze;
        }
        zze = zze(bundle);
        return (zze == null || !zze.containsKey("google.messenger")) ? zze : null;
    }

    private final android.os.Bundle zze(android.os.Bundle r9) throws java.io.IOException {
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
        r8 = this;
        r0 = zzag();
        r1 = new com.google.android.gms.tasks.TaskCompletionSource;
        r1.<init>();
        r2 = r8.zzcj;
        monitor-enter(r2);
        r3 = r8.zzcj;	 Catch:{ all -> 0x0123 }
        r3.put(r0, r1);	 Catch:{ all -> 0x0123 }
        monitor-exit(r2);	 Catch:{ all -> 0x0123 }
        r2 = r8.zzak;
        r2 = r2.zzab();
        if (r2 != 0) goto L_0x0022;
    L_0x001a:
        r9 = new java.io.IOException;
        r0 = "MISSING_INSTANCEID_SERVICE";
        r9.<init>(r0);
        throw r9;
    L_0x0022:
        r2 = new android.content.Intent;
        r2.<init>();
        r3 = "com.google.android.gms";
        r2.setPackage(r3);
        r3 = r8.zzak;
        r3 = r3.zzab();
        r4 = 2;
        if (r3 != r4) goto L_0x003b;
    L_0x0035:
        r3 = "com.google.iid.TOKEN_REQUEST";
    L_0x0037:
        r2.setAction(r3);
        goto L_0x003e;
    L_0x003b:
        r3 = "com.google.android.c2dm.intent.REGISTER";
        goto L_0x0037;
    L_0x003e:
        r2.putExtras(r9);
        r9 = r8.zzv;
        zza(r9, r2);
        r9 = "kid";
        r3 = java.lang.String.valueOf(r0);
        r3 = r3.length();
        r3 = r3 + 5;
        r5 = new java.lang.StringBuilder;
        r5.<init>(r3);
        r3 = "|ID|";
        r5.append(r3);
        r5.append(r0);
        r3 = "|";
        r5.append(r3);
        r3 = r5.toString();
        r2.putExtra(r9, r3);
        r9 = "FirebaseInstanceId";
        r3 = 3;
        r9 = android.util.Log.isLoggable(r9, r3);
        if (r9 == 0) goto L_0x009c;
    L_0x0074:
        r9 = "FirebaseInstanceId";
        r5 = r2.getExtras();
        r5 = java.lang.String.valueOf(r5);
        r6 = java.lang.String.valueOf(r5);
        r6 = r6.length();
        r6 = r6 + 8;
        r7 = new java.lang.StringBuilder;
        r7.<init>(r6);
        r6 = "Sending ";
        r7.append(r6);
        r7.append(r5);
        r5 = r7.toString();
        android.util.Log.d(r9, r5);
    L_0x009c:
        r9 = "google.messenger";
        r5 = r8.zzck;
        r2.putExtra(r9, r5);
        r9 = r8.zzcl;
        if (r9 != 0) goto L_0x00ab;
    L_0x00a7:
        r9 = r8.zzcm;
        if (r9 == 0) goto L_0x00d0;
    L_0x00ab:
        r9 = android.os.Message.obtain();
        r9.obj = r2;
        r5 = r8.zzcl;	 Catch:{ RemoteException -> 0x00c1 }
        if (r5 == 0) goto L_0x00bb;	 Catch:{ RemoteException -> 0x00c1 }
    L_0x00b5:
        r5 = r8.zzcl;	 Catch:{ RemoteException -> 0x00c1 }
        r5.send(r9);	 Catch:{ RemoteException -> 0x00c1 }
        goto L_0x00e3;	 Catch:{ RemoteException -> 0x00c1 }
    L_0x00bb:
        r5 = r8.zzcm;	 Catch:{ RemoteException -> 0x00c1 }
        r5.send(r9);	 Catch:{ RemoteException -> 0x00c1 }
        goto L_0x00e3;
    L_0x00c1:
        r9 = "FirebaseInstanceId";
        r9 = android.util.Log.isLoggable(r9, r3);
        if (r9 == 0) goto L_0x00d0;
    L_0x00c9:
        r9 = "FirebaseInstanceId";
        r3 = "Messenger failed, fallback to startService";
        android.util.Log.d(r9, r3);
    L_0x00d0:
        r9 = r8.zzak;
        r9 = r9.zzab();
        if (r9 != r4) goto L_0x00de;
    L_0x00d8:
        r9 = r8.zzv;
        r9.sendBroadcast(r2);
        goto L_0x00e3;
    L_0x00de:
        r9 = r8.zzv;
        r9.startService(r2);
    L_0x00e3:
        r9 = r1.getTask();	 Catch:{ InterruptedException -> 0x0107, InterruptedException -> 0x0107, ExecutionException -> 0x0100 }
        r1 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;	 Catch:{ InterruptedException -> 0x0107, InterruptedException -> 0x0107, ExecutionException -> 0x0100 }
        r3 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ InterruptedException -> 0x0107, InterruptedException -> 0x0107, ExecutionException -> 0x0100 }
        r9 = com.google.android.gms.tasks.Tasks.await(r9, r1, r3);	 Catch:{ InterruptedException -> 0x0107, InterruptedException -> 0x0107, ExecutionException -> 0x0100 }
        r9 = (android.os.Bundle) r9;	 Catch:{ InterruptedException -> 0x0107, InterruptedException -> 0x0107, ExecutionException -> 0x0100 }
        r1 = r8.zzcj;
        monitor-enter(r1);
        r2 = r8.zzcj;	 Catch:{ all -> 0x00fb }
        r2.remove(r0);	 Catch:{ all -> 0x00fb }
        monitor-exit(r1);	 Catch:{ all -> 0x00fb }
        return r9;	 Catch:{ all -> 0x00fb }
    L_0x00fb:
        r9 = move-exception;	 Catch:{ all -> 0x00fb }
        monitor-exit(r1);	 Catch:{ all -> 0x00fb }
        throw r9;
    L_0x00fe:
        r9 = move-exception;
        goto L_0x0116;
    L_0x0100:
        r9 = move-exception;
        r1 = new java.io.IOException;	 Catch:{ all -> 0x00fe }
        r1.<init>(r9);	 Catch:{ all -> 0x00fe }
        throw r1;	 Catch:{ all -> 0x00fe }
    L_0x0107:
        r9 = "FirebaseInstanceId";	 Catch:{ all -> 0x00fe }
        r1 = "No response";	 Catch:{ all -> 0x00fe }
        android.util.Log.w(r9, r1);	 Catch:{ all -> 0x00fe }
        r9 = new java.io.IOException;	 Catch:{ all -> 0x00fe }
        r1 = "TIMEOUT";	 Catch:{ all -> 0x00fe }
        r9.<init>(r1);	 Catch:{ all -> 0x00fe }
        throw r9;	 Catch:{ all -> 0x00fe }
    L_0x0116:
        r1 = r8.zzcj;
        monitor-enter(r1);
        r2 = r8.zzcj;	 Catch:{ all -> 0x0120 }
        r2.remove(r0);	 Catch:{ all -> 0x0120 }
        monitor-exit(r1);	 Catch:{ all -> 0x0120 }
        throw r9;
    L_0x0120:
        r9 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0120 }
        throw r9;
    L_0x0123:
        r9 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0123 }
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzar.zze(android.os.Bundle):android.os.Bundle");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final android.os.Bundle zzc(android.os.Bundle r6) throws java.io.IOException {
        /*
        r5 = this;
        r0 = r5.zzak;
        r0 = r0.zzae();
        r1 = 12000000; // 0xb71b00 float:1.6815582E-38 double:5.9287878E-317;
        if (r0 < r1) goto L_0x0067;
    L_0x000b:
        r0 = r5.zzv;
        r0 = com.google.firebase.iid.zzz.zzc(r0);
        r1 = 1;
        r0 = r0.zzb(r1, r6);
        r0 = com.google.android.gms.tasks.Tasks.await(r0);	 Catch:{ InterruptedException -> 0x001d, InterruptedException -> 0x001d }
        r0 = (android.os.Bundle) r0;	 Catch:{ InterruptedException -> 0x001d, InterruptedException -> 0x001d }
        return r0;
    L_0x001d:
        r0 = move-exception;
        r1 = "FirebaseInstanceId";
        r2 = 3;
        r1 = android.util.Log.isLoggable(r1, r2);
        if (r1 == 0) goto L_0x004b;
    L_0x0027:
        r1 = "FirebaseInstanceId";
        r2 = java.lang.String.valueOf(r0);
        r3 = java.lang.String.valueOf(r2);
        r3 = r3.length();
        r3 = r3 + 22;
        r4 = new java.lang.StringBuilder;
        r4.<init>(r3);
        r3 = "Error making request: ";
        r4.append(r3);
        r4.append(r2);
        r2 = r4.toString();
        android.util.Log.d(r1, r2);
    L_0x004b:
        r1 = r0.getCause();
        r1 = r1 instanceof com.google.firebase.iid.zzaj;
        if (r1 == 0) goto L_0x0065;
    L_0x0053:
        r0 = r0.getCause();
        r0 = (com.google.firebase.iid.zzaj) r0;
        r0 = r0.getErrorCode();
        r1 = 4;
        if (r0 != r1) goto L_0x0065;
    L_0x0060:
        r6 = r5.zzd(r6);
        return r6;
    L_0x0065:
        r6 = 0;
        return r6;
    L_0x0067:
        r6 = r5.zzd(r6);
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzar.zzc(android.os.Bundle):android.os.Bundle");
    }
}
