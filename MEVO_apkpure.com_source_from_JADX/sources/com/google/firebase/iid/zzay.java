package com.google.firebase.iid;

import android.support.annotation.GuardedBy;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Map;

final class zzay {
    @GuardedBy("itself")
    private final zzau zzag;
    @GuardedBy("this")
    private int zzdc = 0;
    @GuardedBy("this")
    private final Map<Integer, TaskCompletionSource<Void>> zzdd = new ArrayMap();

    zzay(zzau zzau) {
        this.zzag = zzau;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.WorkerThread
    private static boolean zza(com.google.firebase.iid.FirebaseInstanceId r6, java.lang.String r7) {
        /*
        r0 = "!";
        r7 = r7.split(r0);
        r0 = r7.length;
        r1 = 1;
        r2 = 2;
        if (r0 != r2) goto L_0x0075;
    L_0x000b:
        r0 = 0;
        r2 = r7[r0];
        r7 = r7[r1];
        r3 = -1;
        r4 = r2.hashCode();	 Catch:{ IOException -> 0x0054 }
        r5 = 83;
        if (r4 == r5) goto L_0x0028;
    L_0x0019:
        r5 = 85;
        if (r4 == r5) goto L_0x001e;
    L_0x001d:
        goto L_0x0031;
    L_0x001e:
        r4 = "U";
        r2 = r2.equals(r4);	 Catch:{ IOException -> 0x0054 }
        if (r2 == 0) goto L_0x0031;
    L_0x0026:
        r3 = 1;
        goto L_0x0031;
    L_0x0028:
        r4 = "S";
        r2 = r2.equals(r4);	 Catch:{ IOException -> 0x0054 }
        if (r2 == 0) goto L_0x0031;
    L_0x0030:
        r3 = 0;
    L_0x0031:
        switch(r3) {
            case 0: goto L_0x0046;
            case 1: goto L_0x0035;
            default: goto L_0x0034;
        };	 Catch:{ IOException -> 0x0054 }
    L_0x0034:
        return r1;
    L_0x0035:
        r6.zzc(r7);	 Catch:{ IOException -> 0x0054 }
        r6 = com.google.firebase.iid.FirebaseInstanceId.zzk();	 Catch:{ IOException -> 0x0054 }
        if (r6 == 0) goto L_0x0075;
    L_0x003e:
        r6 = "FirebaseInstanceId";
        r7 = "unsubscribe operation succeeded";
    L_0x0042:
        android.util.Log.d(r6, r7);	 Catch:{ IOException -> 0x0054 }
        return r1;
    L_0x0046:
        r6.zzb(r7);	 Catch:{ IOException -> 0x0054 }
        r6 = com.google.firebase.iid.FirebaseInstanceId.zzk();	 Catch:{ IOException -> 0x0054 }
        if (r6 == 0) goto L_0x0075;
    L_0x004f:
        r6 = "FirebaseInstanceId";
        r7 = "subscribe operation succeeded";
        goto L_0x0042;
    L_0x0054:
        r6 = move-exception;
        r7 = "FirebaseInstanceId";
        r1 = "Topic sync failed: ";
        r6 = r6.getMessage();
        r6 = java.lang.String.valueOf(r6);
        r2 = r6.length();
        if (r2 == 0) goto L_0x006c;
    L_0x0067:
        r6 = r1.concat(r6);
        goto L_0x0071;
    L_0x006c:
        r6 = new java.lang.String;
        r6.<init>(r1);
    L_0x0071:
        android.util.Log.e(r7, r6);
        return r0;
    L_0x0075:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzay.zza(com.google.firebase.iid.FirebaseInstanceId, java.lang.String):boolean");
    }

    @Nullable
    @GuardedBy("this")
    private final String zzaq() {
        synchronized (this.zzag) {
            Object zzaj = this.zzag.zzaj();
        }
        if (!TextUtils.isEmpty(zzaj)) {
            String[] split = zzaj.split(",");
            if (split.length > 1 && !TextUtils.isEmpty(split[1])) {
                return split[1];
            }
        }
        return null;
    }

    private final synchronized boolean zzk(String str) {
        synchronized (this.zzag) {
            String zzaj = this.zzag.zzaj();
            String valueOf = String.valueOf(",");
            String valueOf2 = String.valueOf(str);
            if (zzaj.startsWith(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf))) {
                valueOf = String.valueOf(",");
                str = String.valueOf(str);
                this.zzag.zzf(zzaj.substring((str.length() != 0 ? valueOf.concat(str) : new String(valueOf)).length()));
                return true;
            }
            return false;
        }
    }

    final synchronized Task<Void> zza(String str) {
        TaskCompletionSource taskCompletionSource;
        Object zzaj;
        synchronized (this.zzag) {
            zzaj = this.zzag.zzaj();
            zzau zzau = this.zzag;
            StringBuilder stringBuilder = new StringBuilder((String.valueOf(zzaj).length() + 1) + String.valueOf(str).length());
            stringBuilder.append(zzaj);
            stringBuilder.append(",");
            stringBuilder.append(str);
            zzau.zzf(stringBuilder.toString());
        }
        taskCompletionSource = new TaskCompletionSource();
        this.zzdd.put(Integer.valueOf(this.zzdc + (TextUtils.isEmpty(zzaj) ? 0 : zzaj.split(",").length - 1)), taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.WorkerThread
    final boolean zza(com.google.firebase.iid.FirebaseInstanceId r5) {
        /*
        r4 = this;
    L_0x0000:
        monitor-enter(r4);
        r0 = r4.zzaq();	 Catch:{ all -> 0x003c }
        r1 = 1;
        if (r0 != 0) goto L_0x0011;
    L_0x0008:
        r5 = "FirebaseInstanceId";
        r0 = "topic sync succeeded";
        android.util.Log.d(r5, r0);	 Catch:{ all -> 0x003c }
        monitor-exit(r4);	 Catch:{ all -> 0x003c }
        return r1;
    L_0x0011:
        monitor-exit(r4);	 Catch:{ all -> 0x003c }
        r2 = zza(r5, r0);
        if (r2 != 0) goto L_0x001a;
    L_0x0018:
        r5 = 0;
        return r5;
    L_0x001a:
        monitor-enter(r4);
        r2 = r4.zzdd;	 Catch:{ all -> 0x0039 }
        r3 = r4.zzdc;	 Catch:{ all -> 0x0039 }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ all -> 0x0039 }
        r2 = r2.remove(r3);	 Catch:{ all -> 0x0039 }
        r2 = (com.google.android.gms.tasks.TaskCompletionSource) r2;	 Catch:{ all -> 0x0039 }
        r4.zzk(r0);	 Catch:{ all -> 0x0039 }
        r0 = r4.zzdc;	 Catch:{ all -> 0x0039 }
        r0 = r0 + r1;
        r4.zzdc = r0;	 Catch:{ all -> 0x0039 }
        monitor-exit(r4);	 Catch:{ all -> 0x0039 }
        if (r2 == 0) goto L_0x0000;
    L_0x0034:
        r0 = 0;
        r2.setResult(r0);
        goto L_0x0000;
    L_0x0039:
        r5 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0039 }
        throw r5;
    L_0x003c:
        r5 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x003c }
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzay.zza(com.google.firebase.iid.FirebaseInstanceId):boolean");
    }

    final synchronized boolean zzap() {
        return zzaq() != null;
    }
}
