package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.internal.firebase_messaging.zza;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Properties;

final class zzw {
    zzw() {
    }

    @Nullable
    private static zzx zza(SharedPreferences sharedPreferences, String str) throws zzy {
        zzx zzx = null;
        String string = sharedPreferences.getString(zzau.zzc(str, "|P|"), null);
        String string2 = sharedPreferences.getString(zzau.zzc(str, "|K|"), null);
        if (string != null) {
            if (string2 == null) {
                return null;
            }
            zzx = new zzx(zzb(string, string2), zzb(sharedPreferences, str));
        }
        return zzx;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.Nullable
    private static com.google.firebase.iid.zzx zza(java.io.File r5) throws com.google.firebase.iid.zzy, java.io.IOException {
        /*
        r0 = new java.io.FileInputStream;
        r0.<init>(r5);
        r5 = 0;
        r1 = new java.util.Properties;	 Catch:{ Throwable -> 0x0043 }
        r1.<init>();	 Catch:{ Throwable -> 0x0043 }
        r1.load(r0);	 Catch:{ Throwable -> 0x0043 }
        r2 = "pub";
        r2 = r1.getProperty(r2);	 Catch:{ Throwable -> 0x0043 }
        r3 = "pri";
        r3 = r1.getProperty(r3);	 Catch:{ Throwable -> 0x0043 }
        if (r2 == 0) goto L_0x003d;
    L_0x001c:
        if (r3 != 0) goto L_0x001f;
    L_0x001e:
        goto L_0x003d;
    L_0x001f:
        r2 = zzb(r2, r3);	 Catch:{ Throwable -> 0x0043 }
        r3 = "cre";
        r1 = r1.getProperty(r3);	 Catch:{ NumberFormatException -> 0x0036 }
        r3 = java.lang.Long.parseLong(r1);	 Catch:{ NumberFormatException -> 0x0036 }
        r1 = new com.google.firebase.iid.zzx;	 Catch:{ Throwable -> 0x0043 }
        r1.<init>(r2, r3);	 Catch:{ Throwable -> 0x0043 }
        zza(r5, r0);
        return r1;
    L_0x0036:
        r1 = move-exception;
        r2 = new com.google.firebase.iid.zzy;	 Catch:{ Throwable -> 0x0043 }
        r2.<init>(r1);	 Catch:{ Throwable -> 0x0043 }
        throw r2;	 Catch:{ Throwable -> 0x0043 }
    L_0x003d:
        zza(r5, r0);
        return r5;
    L_0x0041:
        r1 = move-exception;
        goto L_0x0045;
    L_0x0043:
        r5 = move-exception;
        throw r5;	 Catch:{ all -> 0x0041 }
    L_0x0045:
        zza(r5, r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzw.zza(java.io.File):com.google.firebase.iid.zzx");
    }

    static void zza(Context context) {
        for (File file : zzb(context).listFiles()) {
            if (file.getName().startsWith("com.google.InstanceId")) {
                file.delete();
            }
        }
    }

    private static void zza(Context context, String str, zzx zzx) {
        FileOutputStream fileOutputStream;
        try {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                Log.d("FirebaseInstanceId", "Writing key to properties file");
            }
            File zzf = zzf(context, str);
            zzf.createNewFile();
            Properties properties = new Properties();
            properties.setProperty("pub", zzx.zzu());
            properties.setProperty("pri", zzx.zzv());
            properties.setProperty("cre", String.valueOf(zzx.zzbj));
            fileOutputStream = new FileOutputStream(zzf);
            properties.store(fileOutputStream, null);
            zza(null, fileOutputStream);
        } catch (IOException e) {
            String valueOf = String.valueOf(e);
            StringBuilder stringBuilder = new StringBuilder(String.valueOf(valueOf).length() + 21);
            stringBuilder.append("Failed to write key: ");
            stringBuilder.append(valueOf);
            Log.w("FirebaseInstanceId", stringBuilder.toString());
        } catch (Throwable th) {
            zza(r3, fileOutputStream);
        }
    }

    private static /* synthetic */ void zza(Throwable th, FileInputStream fileInputStream) {
        if (th != null) {
            try {
                fileInputStream.close();
                return;
            } catch (Throwable th2) {
                zza.zza(th, th2);
                return;
            }
        }
        fileInputStream.close();
    }

    private static /* synthetic */ void zza(Throwable th, FileOutputStream fileOutputStream) {
        if (th != null) {
            try {
                fileOutputStream.close();
                return;
            } catch (Throwable th2) {
                zza.zza(th, th2);
                return;
            }
        }
        fileOutputStream.close();
    }

    private static long zzb(android.content.SharedPreferences r1, java.lang.String r2) {
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
        r0 = "cre";
        r2 = com.google.firebase.iid.zzau.zzc(r2, r0);
        r0 = 0;
        r1 = r1.getString(r2, r0);
        if (r1 == 0) goto L_0x0012;
    L_0x000d:
        r1 = java.lang.Long.parseLong(r1);	 Catch:{ NumberFormatException -> 0x0012 }
        return r1;
    L_0x0012:
        r1 = 0;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzw.zzb(android.content.SharedPreferences, java.lang.String):long");
    }

    private static File zzb(Context context) {
        File noBackupFilesDir = ContextCompat.getNoBackupFilesDir(context);
        if (noBackupFilesDir != null && noBackupFilesDir.isDirectory()) {
            return noBackupFilesDir;
        }
        Log.w("FirebaseInstanceId", "noBackupFilesDir doesn't exist, using regular files directory instead");
        return context.getFilesDir();
    }

    private static KeyPair zzb(String str, String str2) throws zzy {
        try {
            byte[] decode = Base64.decode(str, 8);
            byte[] decode2 = Base64.decode(str2, 8);
            try {
                KeyFactory instance = KeyFactory.getInstance("RSA");
                return new KeyPair(instance.generatePublic(new X509EncodedKeySpec(decode)), instance.generatePrivate(new PKCS8EncodedKeySpec(decode2)));
            } catch (Exception e) {
                String valueOf = String.valueOf(e);
                StringBuilder stringBuilder = new StringBuilder(String.valueOf(valueOf).length() + 19);
                stringBuilder.append("Invalid key stored ");
                stringBuilder.append(valueOf);
                Log.w("FirebaseInstanceId", stringBuilder.toString());
                throw new zzy(e);
            }
        } catch (Exception e2) {
            throw new zzy(e2);
        }
    }

    private final void zzb(android.content.Context r3, java.lang.String r4, com.google.firebase.iid.zzx r5) {
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
        r0 = "com.google.android.gms.appid";
        r1 = 0;
        r3 = r3.getSharedPreferences(r0, r1);
        r0 = zza(r3, r4);	 Catch:{ zzy -> 0x0012 }
        r0 = r5.equals(r0);	 Catch:{ zzy -> 0x0012 }
        if (r0 == 0) goto L_0x0012;
    L_0x0011:
        return;
    L_0x0012:
        r0 = "FirebaseInstanceId";
        r1 = 3;
        r0 = android.util.Log.isLoggable(r0, r1);
        if (r0 == 0) goto L_0x0022;
    L_0x001b:
        r0 = "FirebaseInstanceId";
        r1 = "Writing key to shared preferences";
        android.util.Log.d(r0, r1);
    L_0x0022:
        r3 = r3.edit();
        r0 = "|P|";
        r0 = com.google.firebase.iid.zzau.zzc(r4, r0);
        r1 = r5.zzu();
        r3.putString(r0, r1);
        r0 = "|K|";
        r0 = com.google.firebase.iid.zzau.zzc(r4, r0);
        r1 = r5.zzv();
        r3.putString(r0, r1);
        r0 = "cre";
        r4 = com.google.firebase.iid.zzau.zzc(r4, r0);
        r0 = r5.zzbj;
        r5 = java.lang.String.valueOf(r0);
        r3.putString(r4, r5);
        r3.commit();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzw.zzb(android.content.Context, java.lang.String, com.google.firebase.iid.zzx):void");
    }

    @Nullable
    private final zzx zzd(Context context, String str) throws zzy {
        zzy zzy;
        try {
            zzx zze = zze(context, str);
            if (zze != null) {
                zzb(context, str, zze);
                return zze;
            }
            zzy = null;
            try {
                zzx zza = zza(context.getSharedPreferences("com.google.android.gms.appid", 0), str);
                if (zza != null) {
                    zza(context, str, zza);
                    return zza;
                }
            } catch (zzy e) {
                zzy = e;
            }
            if (zzy == null) {
                return null;
            }
            throw zzy;
        } catch (zzy e2) {
            zzy = e2;
        }
    }

    @Nullable
    private final zzx zze(Context context, String str) throws zzy {
        StringBuilder stringBuilder;
        File zzf = zzf(context, str);
        if (!zzf.exists()) {
            return null;
        }
        try {
            return zza(zzf);
        } catch (IOException e) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                str = String.valueOf(e);
                stringBuilder = new StringBuilder(String.valueOf(str).length() + 40);
                stringBuilder.append("Failed to read key from file, retrying: ");
                stringBuilder.append(str);
                Log.d("FirebaseInstanceId", stringBuilder.toString());
            }
            try {
                return zza(zzf);
            } catch (Exception e2) {
                String valueOf = String.valueOf(e2);
                stringBuilder = new StringBuilder(String.valueOf(valueOf).length() + 45);
                stringBuilder.append("IID file exists, but failed to read from it: ");
                stringBuilder.append(valueOf);
                Log.w("FirebaseInstanceId", stringBuilder.toString());
                throw new zzy(e2);
            }
        }
    }

    private static File zzf(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            str = "com.google.InstanceId.properties";
        } else {
            try {
                str = Base64.encodeToString(str.getBytes(HttpRequest.CHARSET_UTF8), 11);
                StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 33);
                stringBuilder.append("com.google.InstanceId_");
                stringBuilder.append(str);
                stringBuilder.append(".properties");
                str = stringBuilder.toString();
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
        }
        return new File(zzb(context), str);
    }

    @WorkerThread
    final zzx zzb(Context context, String str) throws zzy {
        zzx zzd = zzd(context, str);
        return zzd != null ? zzd : zzc(context, str);
    }

    @android.support.annotation.WorkerThread
    final com.google.firebase.iid.zzx zzc(android.content.Context r6, java.lang.String r7) {
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
        r5 = this;
        r0 = new com.google.firebase.iid.zzx;
        r1 = com.google.firebase.iid.zza.zzb();
        r2 = java.lang.System.currentTimeMillis();
        r0.<init>(r1, r2);
        r1 = 3;
        r2 = r5.zzd(r6, r7);	 Catch:{ zzy -> 0x0024 }
        if (r2 == 0) goto L_0x0024;	 Catch:{ zzy -> 0x0024 }
    L_0x0014:
        r3 = "FirebaseInstanceId";	 Catch:{ zzy -> 0x0024 }
        r3 = android.util.Log.isLoggable(r3, r1);	 Catch:{ zzy -> 0x0024 }
        if (r3 == 0) goto L_0x0023;	 Catch:{ zzy -> 0x0024 }
    L_0x001c:
        r3 = "FirebaseInstanceId";	 Catch:{ zzy -> 0x0024 }
        r4 = "Loaded key after generating new one, using loaded one";	 Catch:{ zzy -> 0x0024 }
        android.util.Log.d(r3, r4);	 Catch:{ zzy -> 0x0024 }
    L_0x0023:
        return r2;
    L_0x0024:
        r2 = "FirebaseInstanceId";
        r1 = android.util.Log.isLoggable(r2, r1);
        if (r1 == 0) goto L_0x0033;
    L_0x002c:
        r1 = "FirebaseInstanceId";
        r2 = "Generated new key";
        android.util.Log.d(r1, r2);
    L_0x0033:
        zza(r6, r7, r0);
        r5.zzb(r6, r7, r0);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzw.zzc(android.content.Context, java.lang.String):com.google.firebase.iid.zzx");
    }
}
