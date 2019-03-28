package com.google.firebase.iid;

import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.Looper;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseApp;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

public class FirebaseInstanceId {
    private static final long zzaf = TimeUnit.HOURS.toSeconds(8);
    private static zzau zzag;
    @GuardedBy("FirebaseInstanceId.class")
    @VisibleForTesting
    private static ScheduledThreadPoolExecutor zzah;
    private final Executor zzai;
    private final FirebaseApp zzaj;
    private final zzal zzak;
    private MessagingChannel zzal;
    private final zzao zzam;
    private final zzay zzan;
    @GuardedBy("this")
    private boolean zzao;
    @GuardedBy("this")
    private boolean zzap;

    FirebaseInstanceId(FirebaseApp firebaseApp) {
        this(firebaseApp, new zzal(firebaseApp.getApplicationContext()), zzi.zze(), zzi.zze());
    }

    private FirebaseInstanceId(FirebaseApp firebaseApp, zzal zzal, Executor executor, Executor executor2) {
        this.zzam = new zzao();
        this.zzao = false;
        if (zzal.zza(firebaseApp) == null) {
            throw new IllegalStateException("FirebaseInstanceId failed to initialize, FirebaseApp is missing project ID");
        }
        synchronized (FirebaseInstanceId.class) {
            if (zzag == null) {
                zzag = new zzau(firebaseApp.getApplicationContext());
            }
        }
        this.zzaj = firebaseApp;
        this.zzak = zzal;
        if (this.zzal == null) {
            MessagingChannel messagingChannel = (MessagingChannel) firebaseApp.get(MessagingChannel.class);
            if (messagingChannel == null || !messagingChannel.isAvailable()) {
                messagingChannel = new zzp(firebaseApp, zzal, executor);
            }
            this.zzal = messagingChannel;
        }
        this.zzal = this.zzal;
        this.zzai = executor2;
        this.zzan = new zzay(zzag);
        this.zzap = zzq();
        if (zzs()) {
            zzf();
        }
    }

    public static FirebaseInstanceId getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    @Keep
    public static synchronized FirebaseInstanceId getInstance(@NonNull FirebaseApp firebaseApp) {
        FirebaseInstanceId firebaseInstanceId;
        synchronized (FirebaseInstanceId.class) {
            firebaseInstanceId = (FirebaseInstanceId) firebaseApp.get(FirebaseInstanceId.class);
        }
        return firebaseInstanceId;
    }

    private final synchronized void startSync() {
        if (!this.zzao) {
            zza(0);
        }
    }

    private final Task<InstanceIdResult> zza(String str, String str2) {
        String zzd = zzd(str2);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzai.execute(new zzm(this, str, str2, taskCompletionSource, zzd));
        return taskCompletionSource.getTask();
    }

    private final <T> T zza(com.google.android.gms.tasks.Task<T> r4) throws java.io.IOException {
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
        r3 = this;
        r0 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r2 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ ExecutionException -> 0x0011, InterruptedException -> 0x0009, InterruptedException -> 0x0009 }
        r4 = com.google.android.gms.tasks.Tasks.await(r4, r0, r2);	 Catch:{ ExecutionException -> 0x0011, InterruptedException -> 0x0009, InterruptedException -> 0x0009 }
        return r4;
    L_0x0009:
        r4 = new java.io.IOException;
        r0 = "SERVICE_NOT_AVAILABLE";
        r4.<init>(r0);
        throw r4;
    L_0x0011:
        r4 = move-exception;
        r0 = r4.getCause();
        r1 = r0 instanceof java.io.IOException;
        if (r1 == 0) goto L_0x002c;
    L_0x001a:
        r4 = "INSTANCE_ID_RESET";
        r1 = r0.getMessage();
        r4 = r4.equals(r1);
        if (r4 == 0) goto L_0x0029;
    L_0x0026:
        r3.zzl();
    L_0x0029:
        r0 = (java.io.IOException) r0;
        throw r0;
    L_0x002c:
        r1 = r0 instanceof java.lang.RuntimeException;
        if (r1 == 0) goto L_0x0033;
    L_0x0030:
        r0 = (java.lang.RuntimeException) r0;
        throw r0;
    L_0x0033:
        r0 = new java.io.IOException;
        r0.<init>(r4);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.FirebaseInstanceId.zza(com.google.android.gms.tasks.Task):T");
    }

    static void zza(Runnable runnable, long j) {
        synchronized (FirebaseInstanceId.class) {
            if (zzah == null) {
                zzah = new ScheduledThreadPoolExecutor(1);
            }
            zzah.schedule(runnable, j, TimeUnit.SECONDS);
        }
    }

    private static String zzd(String str) {
        if (!(str.isEmpty() || str.equalsIgnoreCase(AppMeasurement.FCM_ORIGIN))) {
            if (!str.equalsIgnoreCase("gcm")) {
                return str;
            }
        }
        return Operation.MULTIPLY;
    }

    private final void zzf() {
        zzav zzi = zzi();
        if (!zzn() || zzi == null || zzi.zzj(this.zzak.zzac()) || this.zzan.zzap()) {
            startSync();
        }
    }

    private static String zzh() {
        return zzal.zza(zzag.zzg("").getKeyPair());
    }

    static boolean zzk() {
        if (!Log.isLoggable("FirebaseInstanceId", 3)) {
            if (VERSION.SDK_INT != 23 || !Log.isLoggable("FirebaseInstanceId", 3)) {
                return false;
            }
        }
        return true;
    }

    private final boolean zzq() {
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
        r3 = this;
        r0 = r3.zzaj;
        r0 = r0.getApplicationContext();
        r1 = "com.google.firebase.messaging";
        r2 = 0;
        r1 = r0.getSharedPreferences(r1, r2);
        r2 = "auto_init";
        r2 = r1.contains(r2);
        if (r2 == 0) goto L_0x001d;
    L_0x0015:
        r0 = "auto_init";
        r2 = 1;
        r0 = r1.getBoolean(r0, r2);
        return r0;
    L_0x001d:
        r1 = r0.getPackageManager();	 Catch:{ NameNotFoundException -> 0x0046 }
        if (r1 == 0) goto L_0x0046;	 Catch:{ NameNotFoundException -> 0x0046 }
    L_0x0023:
        r0 = r0.getPackageName();	 Catch:{ NameNotFoundException -> 0x0046 }
        r2 = 128; // 0x80 float:1.794E-43 double:6.32E-322;	 Catch:{ NameNotFoundException -> 0x0046 }
        r0 = r1.getApplicationInfo(r0, r2);	 Catch:{ NameNotFoundException -> 0x0046 }
        if (r0 == 0) goto L_0x0046;	 Catch:{ NameNotFoundException -> 0x0046 }
    L_0x002f:
        r1 = r0.metaData;	 Catch:{ NameNotFoundException -> 0x0046 }
        if (r1 == 0) goto L_0x0046;	 Catch:{ NameNotFoundException -> 0x0046 }
    L_0x0033:
        r1 = r0.metaData;	 Catch:{ NameNotFoundException -> 0x0046 }
        r2 = "firebase_messaging_auto_init_enabled";	 Catch:{ NameNotFoundException -> 0x0046 }
        r1 = r1.containsKey(r2);	 Catch:{ NameNotFoundException -> 0x0046 }
        if (r1 == 0) goto L_0x0046;	 Catch:{ NameNotFoundException -> 0x0046 }
    L_0x003d:
        r0 = r0.metaData;	 Catch:{ NameNotFoundException -> 0x0046 }
        r1 = "firebase_messaging_auto_init_enabled";	 Catch:{ NameNotFoundException -> 0x0046 }
        r0 = r0.getBoolean(r1);	 Catch:{ NameNotFoundException -> 0x0046 }
        return r0;
    L_0x0046:
        r0 = r3.zzr();
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.FirebaseInstanceId.zzq():boolean");
    }

    private final boolean zzr() {
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
        r0 = 1;
        r1 = "com.google.firebase.messaging.FirebaseMessaging";	 Catch:{ ClassNotFoundException -> 0x0007 }
        java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x0007 }
        return r0;
    L_0x0007:
        r1 = r4.zzaj;
        r1 = r1.getApplicationContext();
        r2 = new android.content.Intent;
        r3 = "com.google.firebase.MESSAGING_EVENT";
        r2.<init>(r3);
        r3 = r1.getPackageName();
        r2.setPackage(r3);
        r1 = r1.getPackageManager();
        r3 = 0;
        r1 = r1.resolveService(r2, r3);
        if (r1 == 0) goto L_0x002b;
    L_0x0026:
        r1 = r1.serviceInfo;
        if (r1 == 0) goto L_0x002b;
    L_0x002a:
        return r0;
    L_0x002b:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.FirebaseInstanceId.zzr():boolean");
    }

    @WorkerThread
    public void deleteInstanceId() throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        zza(this.zzal.deleteInstanceId(zzh()));
        zzl();
    }

    @WorkerThread
    public void deleteToken(String str, String str2) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        str2 = zzd(str2);
        zza(this.zzal.deleteToken(zzh(), str, str2));
        zzag.zzd("", str, str2);
    }

    public long getCreationTime() {
        return zzag.zzg("").getCreationTime();
    }

    @WorkerThread
    public String getId() {
        zzf();
        return zzh();
    }

    @NonNull
    public Task<InstanceIdResult> getInstanceId() {
        return zza(zzal.zza(this.zzaj), Operation.MULTIPLY);
    }

    @Nullable
    @Deprecated
    public String getToken() {
        zzav zzi = zzi();
        if (zzi == null || zzi.zzj(this.zzak.zzac())) {
            startSync();
        }
        return zzi != null ? zzi.zzbh : null;
    }

    @WorkerThread
    public String getToken(String str, String str2) throws IOException {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            return ((InstanceIdResult) zza(zza(str, str2))).getToken();
        }
        throw new IOException("MAIN_THREAD");
    }

    public final synchronized Task<Void> zza(String str) {
        Task<Void> zza;
        zza = this.zzan.zza(str);
        startSync();
        return zza;
    }

    final /* synthetic */ Task zza(String str, String str2, String str3) {
        return this.zzal.getToken(str, str2, str3);
    }

    final synchronized void zza(long j) {
        zza(new zzaw(this, this.zzak, this.zzan, Math.min(Math.max(30, j << 1), zzaf)), j);
        this.zzao = true;
    }

    final /* synthetic */ void zza(String str, String str2, TaskCompletionSource taskCompletionSource, String str3) {
        String zzh = zzh();
        zzav zzc = zzag.zzc("", str, str2);
        if (zzc == null || zzc.zzj(this.zzak.zzac())) {
            this.zzam.zza(str, str3, new zzn(this, zzh, str, str3)).addOnCompleteListener(this.zzai, new zzo(this, str, str3, taskCompletionSource, zzh));
        } else {
            taskCompletionSource.setResult(new zzv(zzh, zzc.zzbh));
        }
    }

    final /* synthetic */ void zza(String str, String str2, TaskCompletionSource taskCompletionSource, String str3, Task task) {
        if (task.isSuccessful()) {
            String str4 = (String) task.getResult();
            zzag.zza("", str, str2, str4, this.zzak.zzac());
            taskCompletionSource.setResult(new zzv(str3, str4));
            return;
        }
        taskCompletionSource.setException(task.getException());
    }

    final synchronized void zza(boolean z) {
        this.zzao = z;
    }

    final void zzb(String str) throws IOException {
        zzav zzi = zzi();
        if (zzi != null) {
            if (!zzi.zzj(this.zzak.zzac())) {
                zza(this.zzal.subscribeToTopic(zzh(), zzi.zzbh, str));
                return;
            }
        }
        throw new IOException("token not available");
    }

    @VisibleForTesting
    public final synchronized void zzb(boolean z) {
        Editor edit = this.zzaj.getApplicationContext().getSharedPreferences("com.google.firebase.messaging", 0).edit();
        edit.putBoolean("auto_init", z);
        edit.apply();
        if (!this.zzap && z) {
            zzf();
        }
        this.zzap = z;
    }

    final void zzc(String str) throws IOException {
        zzav zzi = zzi();
        if (zzi != null) {
            if (!zzi.zzj(this.zzak.zzac())) {
                zza(this.zzal.unsubscribeFromTopic(zzh(), zzi.zzbh, str));
                return;
            }
        }
        throw new IOException("token not available");
    }

    final FirebaseApp zzg() {
        return this.zzaj;
    }

    @Nullable
    final zzav zzi() {
        return zzag.zzc("", zzal.zza(this.zzaj), Operation.MULTIPLY);
    }

    final String zzj() throws IOException {
        return getToken(zzal.zza(this.zzaj), Operation.MULTIPLY);
    }

    final synchronized void zzl() {
        zzag.zzak();
        if (zzs()) {
            startSync();
        }
    }

    final boolean zzm() {
        return this.zzal.isAvailable();
    }

    final boolean zzn() {
        return this.zzal.isChannelBuilt();
    }

    final void zzo() throws IOException {
        String zzh = zzh();
        zzav zzi = zzi();
        zza(this.zzal.buildChannel(zzh, zzi == null ? null : zzi.zzbh));
    }

    final void zzp() {
        zzag.zzh("");
        startSync();
    }

    @VisibleForTesting
    public final synchronized boolean zzs() {
        return this.zzap;
    }
}
