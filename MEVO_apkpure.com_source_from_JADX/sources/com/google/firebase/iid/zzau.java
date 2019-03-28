package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.GuardedBy;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Map;

final class zzau {
    private final SharedPreferences zzct;
    private final zzw zzcu;
    @GuardedBy("this")
    private final Map<String, zzx> zzcv;
    private final Context zzv;

    public zzau(Context context) {
        this(context, new zzw());
    }

    private zzau(Context context, zzw zzw) {
        this.zzcv = new ArrayMap();
        this.zzv = context;
        this.zzct = context.getSharedPreferences("com.google.android.gms.appid", 0);
        this.zzcu = zzw;
        File file = new File(ContextCompat.getNoBackupFilesDir(this.zzv), "com.google.android.gms.appid-no-backup");
        if (!file.exists()) {
            try {
                if (file.createNewFile() && !isEmpty()) {
                    Log.i("FirebaseInstanceId", "App restored, clearing state");
                    zzak();
                    FirebaseInstanceId.getInstance().zzl();
                }
            } catch (IOException e) {
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    String str = "FirebaseInstanceId";
                    String str2 = "Error creating file in no backup dir: ";
                    String valueOf = String.valueOf(e.getMessage());
                    Log.d(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                }
            }
        }
    }

    private final synchronized boolean isEmpty() {
        return this.zzct.getAll().isEmpty();
    }

    private static String zzb(String str, String str2, String str3) {
        StringBuilder stringBuilder = new StringBuilder(((String.valueOf(str).length() + 4) + String.valueOf(str2).length()) + String.valueOf(str3).length());
        stringBuilder.append(str);
        stringBuilder.append("|T|");
        stringBuilder.append(str2);
        stringBuilder.append("|");
        stringBuilder.append(str3);
        return stringBuilder.toString();
    }

    static String zzc(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder((String.valueOf(str).length() + 3) + String.valueOf(str2).length());
        stringBuilder.append(str);
        stringBuilder.append("|S|");
        stringBuilder.append(str2);
        return stringBuilder.toString();
    }

    public final synchronized void zza(String str, String str2, String str3, String str4, String str5) {
        str4 = zzav.zza(str4, str5, System.currentTimeMillis());
        if (str4 != null) {
            Editor edit = this.zzct.edit();
            edit.putString(zzb(str, str2, str3), str4);
            edit.commit();
        }
    }

    public final synchronized String zzaj() {
        return this.zzct.getString("topic_operaion_queue", "");
    }

    public final synchronized void zzak() {
        this.zzcv.clear();
        zzw.zza(this.zzv);
        this.zzct.edit().clear().commit();
    }

    public final synchronized zzav zzc(String str, String str2, String str3) {
        return zzav.zzi(this.zzct.getString(zzb(str, str2, str3), null));
    }

    public final synchronized void zzd(String str, String str2, String str3) {
        str = zzb(str, str2, str3);
        Editor edit = this.zzct.edit();
        edit.remove(str);
        edit.commit();
    }

    public final synchronized void zzf(String str) {
        this.zzct.edit().putString("topic_operaion_queue", str).apply();
    }

    public final synchronized com.google.firebase.iid.zzx zzg(java.lang.String r3) {
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
        monitor-enter(r2);
        r0 = r2.zzcv;	 Catch:{ all -> 0x0033 }
        r0 = r0.get(r3);	 Catch:{ all -> 0x0033 }
        r0 = (com.google.firebase.iid.zzx) r0;	 Catch:{ all -> 0x0033 }
        if (r0 == 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r2);
        return r0;
    L_0x000d:
        r0 = r2.zzcu;	 Catch:{ zzy -> 0x0016 }
        r1 = r2.zzv;	 Catch:{ zzy -> 0x0016 }
        r0 = r0.zzb(r1, r3);	 Catch:{ zzy -> 0x0016 }
        goto L_0x002c;
    L_0x0016:
        r0 = "FirebaseInstanceId";	 Catch:{ all -> 0x0033 }
        r1 = "Stored data is corrupt, generating new identity";	 Catch:{ all -> 0x0033 }
        android.util.Log.w(r0, r1);	 Catch:{ all -> 0x0033 }
        r0 = com.google.firebase.iid.FirebaseInstanceId.getInstance();	 Catch:{ all -> 0x0033 }
        r0.zzl();	 Catch:{ all -> 0x0033 }
        r0 = r2.zzcu;	 Catch:{ all -> 0x0033 }
        r1 = r2.zzv;	 Catch:{ all -> 0x0033 }
        r0 = r0.zzc(r1, r3);	 Catch:{ all -> 0x0033 }
    L_0x002c:
        r1 = r2.zzcv;	 Catch:{ all -> 0x0033 }
        r1.put(r3, r0);	 Catch:{ all -> 0x0033 }
        monitor-exit(r2);
        return r0;
    L_0x0033:
        r3 = move-exception;
        monitor-exit(r2);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzau.zzg(java.lang.String):com.google.firebase.iid.zzx");
    }

    public final synchronized void zzh(String str) {
        str = String.valueOf(str).concat("|T|");
        Editor edit = this.zzct.edit();
        for (String str2 : this.zzct.getAll().keySet()) {
            if (str2.startsWith(str)) {
                edit.remove(str2);
            }
        }
        edit.commit();
    }
}
