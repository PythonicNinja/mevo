package com.google.firebase;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.components.Component;
import com.google.firebase.components.zzc;
import com.google.firebase.components.zzd;
import com.google.firebase.events.Event;
import com.google.firebase.events.Publisher;
import com.google.firebase.internal.InternalTokenProvider;
import com.google.firebase.internal.InternalTokenResult;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.concurrent.GuardedBy;

public class FirebaseApp {
    public static final String DEFAULT_APP_NAME = "[DEFAULT]";
    @GuardedBy("LOCK")
    static final Map<String, FirebaseApp> zza = new ArrayMap();
    private static final List<String> zzb = Arrays.asList(new String[]{"com.google.firebase.auth.FirebaseAuth", "com.google.firebase.iid.FirebaseInstanceId"});
    private static final List<String> zzc = Collections.singletonList("com.google.firebase.crash.FirebaseCrash");
    private static final List<String> zzd = Arrays.asList(new String[]{"com.google.android.gms.measurement.AppMeasurement"});
    private static final List<String> zze = Arrays.asList(new String[0]);
    private static final Set<String> zzf = Collections.emptySet();
    private static final Object zzg = new Object();
    private static final Executor zzh = new zza();
    private final Context zzi;
    private final String zzj;
    private final FirebaseOptions zzk;
    private final zzd zzl;
    private final SharedPreferences zzm;
    private final Publisher zzn;
    private final AtomicBoolean zzo = new AtomicBoolean(false);
    private final AtomicBoolean zzp = new AtomicBoolean();
    private final AtomicBoolean zzq;
    private final List<IdTokenListener> zzr = new CopyOnWriteArrayList();
    private final List<BackgroundStateChangeListener> zzs = new CopyOnWriteArrayList();
    private final List<zza> zzt = new CopyOnWriteArrayList();
    private InternalTokenProvider zzu;
    private IdTokenListenersCountChangedListener zzv;

    @KeepForSdk
    public interface BackgroundStateChangeListener {
        void onBackgroundStateChanged(boolean z);
    }

    @KeepForSdk
    public interface IdTokenListener {
        void onIdTokenChanged(@NonNull InternalTokenResult internalTokenResult);
    }

    @KeepForSdk
    public interface IdTokenListenersCountChangedListener {
        void onListenerCountChanged(int i);
    }

    static class zza implements Executor {
        private static final Handler zza = new Handler(Looper.getMainLooper());

        private zza() {
        }

        public final void execute(@NonNull Runnable runnable) {
            zza.post(runnable);
        }
    }

    @TargetApi(24)
    static class zzb extends BroadcastReceiver {
        private static AtomicReference<zzb> zza = new AtomicReference();
        private final Context zzb;

        private zzb(Context context) {
            this.zzb = context;
        }

        public final void onReceive(Context context, Intent intent) {
            synchronized (FirebaseApp.zzg) {
                for (FirebaseApp zza : FirebaseApp.zza.values()) {
                    zza.zze();
                }
            }
            this.zzb.unregisterReceiver(this);
        }

        static /* synthetic */ void zza(Context context) {
            if (zza.get() == null) {
                BroadcastReceiver zzb = new zzb(context);
                if (zza.compareAndSet(null, zzb)) {
                    context.registerReceiver(zzb, new IntentFilter("android.intent.action.USER_UNLOCKED"));
                }
            }
        }
    }

    /* renamed from: com.google.firebase.FirebaseApp$1 */
    class C07401 implements com.google.android.gms.common.api.internal.BackgroundDetector.BackgroundStateChangeListener {
        C07401() {
        }

        public final void onBackgroundStateChanged(boolean z) {
            FirebaseApp.onBackgroundStateChanged(z);
        }
    }

    @NonNull
    public Context getApplicationContext() {
        zzc();
        return this.zzi;
    }

    @NonNull
    public String getName() {
        zzc();
        return this.zzj;
    }

    @NonNull
    public FirebaseOptions getOptions() {
        zzc();
        return this.zzk;
    }

    public boolean equals(Object obj) {
        if (obj instanceof FirebaseApp) {
            return this.zzj.equals(((FirebaseApp) obj).getName());
        }
        return null;
    }

    public int hashCode() {
        return this.zzj.hashCode();
    }

    public String toString() {
        return Objects.toStringHelper(this).add("name", this.zzj).add("options", this.zzk).toString();
    }

    public static List<FirebaseApp> getApps(Context context) {
        List arrayList;
        synchronized (zzg) {
            arrayList = new ArrayList(zza.values());
        }
        return arrayList;
    }

    @Nullable
    public static FirebaseApp getInstance() {
        FirebaseApp firebaseApp;
        synchronized (zzg) {
            firebaseApp = (FirebaseApp) zza.get(DEFAULT_APP_NAME);
            if (firebaseApp == null) {
                StringBuilder stringBuilder = new StringBuilder("Default FirebaseApp is not initialized in this process ");
                stringBuilder.append(ProcessUtils.getMyProcessName());
                stringBuilder.append(". Make sure to call FirebaseApp.initializeApp(Context) first.");
                throw new IllegalStateException(stringBuilder.toString());
            }
        }
        return firebaseApp;
    }

    public static FirebaseApp getInstance(@NonNull String str) {
        FirebaseApp firebaseApp;
        synchronized (zzg) {
            firebaseApp = (FirebaseApp) zza.get(str.trim());
            if (firebaseApp != null) {
            } else {
                String str2;
                Iterable zzd = zzd();
                if (zzd.isEmpty()) {
                    str2 = "";
                } else {
                    StringBuilder stringBuilder = new StringBuilder("Available app names: ");
                    stringBuilder.append(TextUtils.join(", ", zzd));
                    str2 = stringBuilder.toString();
                }
                throw new IllegalStateException(String.format("FirebaseApp with name %s doesn't exist. %s", new Object[]{str, str2}));
            }
        }
        return firebaseApp;
    }

    @Nullable
    public static FirebaseApp initializeApp(Context context) {
        synchronized (zzg) {
            if (zza.containsKey(DEFAULT_APP_NAME)) {
                context = getInstance();
                return context;
            }
            FirebaseOptions fromResource = FirebaseOptions.fromResource(context);
            if (fromResource == null) {
                return null;
            }
            context = initializeApp(context, fromResource);
            return context;
        }
    }

    public static FirebaseApp initializeApp(Context context, FirebaseOptions firebaseOptions) {
        return initializeApp(context, firebaseOptions, DEFAULT_APP_NAME);
    }

    public static void onBackgroundStateChanged(boolean z) {
        synchronized (zzg) {
            Iterator it = new ArrayList(zza.values()).iterator();
            while (it.hasNext()) {
                FirebaseApp firebaseApp = (FirebaseApp) it.next();
                if (firebaseApp.zzo.get()) {
                    firebaseApp.zza(z);
                }
            }
        }
    }

    public void setTokenProvider(@NonNull InternalTokenProvider internalTokenProvider) {
        this.zzu = (InternalTokenProvider) Preconditions.checkNotNull(internalTokenProvider);
    }

    public void setIdTokenListenersCountChangedListener(@NonNull IdTokenListenersCountChangedListener idTokenListenersCountChangedListener) {
        this.zzv = (IdTokenListenersCountChangedListener) Preconditions.checkNotNull(idTokenListenersCountChangedListener);
        this.zzv.onListenerCountChanged(this.zzr.size());
    }

    public Task<GetTokenResult> getToken(boolean z) {
        zzc();
        if (this.zzu == null) {
            return Tasks.forException(new FirebaseApiNotAvailableException("firebase-auth is not linked, please fall back to unauthenticated mode."));
        }
        return this.zzu.getAccessToken(z);
    }

    @Nullable
    public String getUid() throws FirebaseApiNotAvailableException {
        zzc();
        if (this.zzu != null) {
            return this.zzu.getUid();
        }
        throw new FirebaseApiNotAvailableException("firebase-auth is not linked, please fall back to unauthenticated mode.");
    }

    public void delete() {
        if (this.zzp.compareAndSet(false, true)) {
            synchronized (zzg) {
                zza.remove(this.zzj);
            }
            Iterator it = this.zzt.iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
    }

    public <T> T get(Class<T> cls) {
        zzc();
        return this.zzl.get(cls);
    }

    public void setAutomaticResourceManagementEnabled(boolean z) {
        zzc();
        if (this.zzo.compareAndSet(z ^ 1, z)) {
            boolean isInBackground = BackgroundDetector.getInstance().isInBackground();
            if (z && isInBackground) {
                zza(true);
            } else if (!z && isInBackground) {
                zza(false);
            }
        }
    }

    public boolean isAutomaticDataCollectionEnabled() {
        zzc();
        return this.zzq.get();
    }

    public void setAutomaticDataCollectionEnabled(boolean z) {
        zzc();
        if (this.zzq.compareAndSet(z ^ 1, z)) {
            this.zzm.edit().putBoolean("firebase_automatic_data_collection_enabled", z).commit();
            this.zzn.publish(new Event(AutomaticDataCollectionChange.class, new AutomaticDataCollectionChange(z)));
        }
    }

    protected FirebaseApp(Context context, String str, FirebaseOptions firebaseOptions) {
        this.zzi = (Context) Preconditions.checkNotNull(context);
        this.zzj = Preconditions.checkNotEmpty(str);
        this.zzk = (FirebaseOptions) Preconditions.checkNotNull(firebaseOptions);
        this.zzv = new com.google.firebase.internal.zza();
        this.zzm = context.getSharedPreferences("com.google.firebase.common.prefs", 0);
        this.zzq = new AtomicBoolean(zzb());
        str = new zzc(context).zza();
        this.zzl = new zzd(zzh, str, Component.of(context, Context.class, new Class[0]), Component.of(this, FirebaseApp.class, new Class[0]), Component.of(firebaseOptions, FirebaseOptions.class, new Class[0]));
        this.zzn = (Publisher) this.zzl.get(Publisher.class);
    }

    private boolean zzb() {
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
        r0 = r4.zzm;
        r1 = "firebase_automatic_data_collection_enabled";
        r0 = r0.contains(r1);
        r1 = 1;
        if (r0 == 0) goto L_0x0014;
    L_0x000b:
        r0 = r4.zzm;
        r2 = "firebase_automatic_data_collection_enabled";
        r0 = r0.getBoolean(r2, r1);
        return r0;
    L_0x0014:
        r0 = r4.zzi;	 Catch:{ NameNotFoundException -> 0x0041 }
        r0 = r0.getPackageManager();	 Catch:{ NameNotFoundException -> 0x0041 }
        if (r0 == 0) goto L_0x0041;	 Catch:{ NameNotFoundException -> 0x0041 }
    L_0x001c:
        r2 = r4.zzi;	 Catch:{ NameNotFoundException -> 0x0041 }
        r2 = r2.getPackageName();	 Catch:{ NameNotFoundException -> 0x0041 }
        r3 = 128; // 0x80 float:1.794E-43 double:6.32E-322;	 Catch:{ NameNotFoundException -> 0x0041 }
        r0 = r0.getApplicationInfo(r2, r3);	 Catch:{ NameNotFoundException -> 0x0041 }
        if (r0 == 0) goto L_0x0041;	 Catch:{ NameNotFoundException -> 0x0041 }
    L_0x002a:
        r2 = r0.metaData;	 Catch:{ NameNotFoundException -> 0x0041 }
        if (r2 == 0) goto L_0x0041;	 Catch:{ NameNotFoundException -> 0x0041 }
    L_0x002e:
        r2 = r0.metaData;	 Catch:{ NameNotFoundException -> 0x0041 }
        r3 = "firebase_automatic_data_collection_enabled";	 Catch:{ NameNotFoundException -> 0x0041 }
        r2 = r2.containsKey(r3);	 Catch:{ NameNotFoundException -> 0x0041 }
        if (r2 == 0) goto L_0x0041;	 Catch:{ NameNotFoundException -> 0x0041 }
    L_0x0038:
        r0 = r0.metaData;	 Catch:{ NameNotFoundException -> 0x0041 }
        r2 = "firebase_automatic_data_collection_enabled";	 Catch:{ NameNotFoundException -> 0x0041 }
        r0 = r0.getBoolean(r2);	 Catch:{ NameNotFoundException -> 0x0041 }
        return r0;
    L_0x0041:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.FirebaseApp.zzb():boolean");
    }

    private void zzc() {
        Preconditions.checkState(this.zzp.get() ^ 1, "FirebaseApp was deleted");
    }

    public List<IdTokenListener> getListeners() {
        zzc();
        return this.zzr;
    }

    @VisibleForTesting
    public boolean isDefaultApp() {
        return DEFAULT_APP_NAME.equals(getName());
    }

    @UiThread
    public void notifyIdTokenListeners(@NonNull InternalTokenResult internalTokenResult) {
        Log.d("FirebaseApp", "Notifying auth state listeners.");
        int i = 0;
        for (IdTokenListener onIdTokenChanged : this.zzr) {
            onIdTokenChanged.onIdTokenChanged(internalTokenResult);
            i++;
        }
        Log.d("FirebaseApp", String.format("Notified %d auth state listeners.", new Object[]{Integer.valueOf(i)}));
    }

    private void zza(boolean z) {
        Log.d("FirebaseApp", "Notifying background state change listeners.");
        for (BackgroundStateChangeListener onBackgroundStateChanged : this.zzs) {
            onBackgroundStateChanged.onBackgroundStateChanged(z);
        }
    }

    public void addIdTokenListener(@NonNull IdTokenListener idTokenListener) {
        zzc();
        Preconditions.checkNotNull(idTokenListener);
        this.zzr.add(idTokenListener);
        this.zzv.onListenerCountChanged(this.zzr.size());
    }

    public void removeIdTokenListener(@NonNull IdTokenListener idTokenListener) {
        zzc();
        Preconditions.checkNotNull(idTokenListener);
        this.zzr.remove(idTokenListener);
        this.zzv.onListenerCountChanged(this.zzr.size());
    }

    public void addBackgroundStateChangeListener(BackgroundStateChangeListener backgroundStateChangeListener) {
        zzc();
        if (this.zzo.get() && BackgroundDetector.getInstance().isInBackground()) {
            backgroundStateChangeListener.onBackgroundStateChanged(true);
        }
        this.zzs.add(backgroundStateChangeListener);
    }

    public void removeBackgroundStateChangeListener(BackgroundStateChangeListener backgroundStateChangeListener) {
        zzc();
        this.zzs.remove(backgroundStateChangeListener);
    }

    public String getPersistenceKey() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Base64Utils.encodeUrlSafeNoPadding(getName().getBytes()));
        stringBuilder.append(Operation.PLUS);
        stringBuilder.append(Base64Utils.encodeUrlSafeNoPadding(getOptions().getApplicationId().getBytes()));
        return stringBuilder.toString();
    }

    public void addLifecycleEventListener(@NonNull zza zza) {
        zzc();
        Preconditions.checkNotNull(zza);
        this.zzt.add(zza);
    }

    public void removeLifecycleEventListener(@NonNull zza zza) {
        zzc();
        Preconditions.checkNotNull(zza);
        this.zzt.remove(zza);
    }

    @VisibleForTesting
    public static void clearInstancesForTest() {
        synchronized (zzg) {
            zza.clear();
        }
    }

    public static String getPersistenceKey(String str, FirebaseOptions firebaseOptions) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Base64Utils.encodeUrlSafeNoPadding(str.getBytes()));
        stringBuilder.append(Operation.PLUS);
        stringBuilder.append(Base64Utils.encodeUrlSafeNoPadding(firebaseOptions.getApplicationId().getBytes()));
        return stringBuilder.toString();
    }

    private static List<String> zzd() {
        List<String> arrayList = new ArrayList();
        synchronized (zzg) {
            for (FirebaseApp name : zza.values()) {
                arrayList.add(name.getName());
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private void zze() {
        boolean isDeviceProtectedStorage = ContextCompat.isDeviceProtectedStorage(this.zzi);
        if (isDeviceProtectedStorage) {
            zzb.zza(this.zzi);
        } else {
            this.zzl.zza(isDefaultApp());
        }
        zza(FirebaseApp.class, this, zzb, isDeviceProtectedStorage);
        if (isDefaultApp()) {
            zza(FirebaseApp.class, this, zzc, isDeviceProtectedStorage);
            zza(Context.class, this.zzi, zzd, isDeviceProtectedStorage);
        }
    }

    private static <T> void zza(java.lang.Class<T> r6, T r7, java.lang.Iterable<java.lang.String> r8, boolean r9) {
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
        r8 = r8.iterator();
    L_0x0004:
        r0 = r8.hasNext();
        if (r0 == 0) goto L_0x00b2;
    L_0x000a:
        r0 = r8.next();
        r0 = (java.lang.String) r0;
        if (r9 == 0) goto L_0x001f;
    L_0x0012:
        r1 = zze;	 Catch:{ ClassNotFoundException -> 0x007b, NoSuchMethodException -> 0x0064, InvocationTargetException -> 0x001d, IllegalAccessException -> 0x001b }
        r1 = r1.contains(r0);	 Catch:{ ClassNotFoundException -> 0x007b, NoSuchMethodException -> 0x0064, InvocationTargetException -> 0x001d, IllegalAccessException -> 0x001b }
        if (r1 == 0) goto L_0x0004;	 Catch:{ ClassNotFoundException -> 0x007b, NoSuchMethodException -> 0x0064, InvocationTargetException -> 0x001d, IllegalAccessException -> 0x001b }
    L_0x001a:
        goto L_0x001f;	 Catch:{ ClassNotFoundException -> 0x007b, NoSuchMethodException -> 0x0064, InvocationTargetException -> 0x001d, IllegalAccessException -> 0x001b }
    L_0x001b:
        r1 = move-exception;	 Catch:{ ClassNotFoundException -> 0x007b, NoSuchMethodException -> 0x0064, InvocationTargetException -> 0x001d, IllegalAccessException -> 0x001b }
        goto L_0x0048;	 Catch:{ ClassNotFoundException -> 0x007b, NoSuchMethodException -> 0x0064, InvocationTargetException -> 0x001d, IllegalAccessException -> 0x001b }
    L_0x001d:
        r0 = move-exception;	 Catch:{ ClassNotFoundException -> 0x007b, NoSuchMethodException -> 0x0064, InvocationTargetException -> 0x001d, IllegalAccessException -> 0x001b }
        goto L_0x005c;	 Catch:{ ClassNotFoundException -> 0x007b, NoSuchMethodException -> 0x0064, InvocationTargetException -> 0x001d, IllegalAccessException -> 0x001b }
    L_0x001f:
        r1 = java.lang.Class.forName(r0);	 Catch:{ ClassNotFoundException -> 0x007b, NoSuchMethodException -> 0x0064, InvocationTargetException -> 0x001d, IllegalAccessException -> 0x001b }
        r2 = "getInstance";	 Catch:{ ClassNotFoundException -> 0x007b, NoSuchMethodException -> 0x0064, InvocationTargetException -> 0x001d, IllegalAccessException -> 0x001b }
        r3 = 1;	 Catch:{ ClassNotFoundException -> 0x007b, NoSuchMethodException -> 0x0064, InvocationTargetException -> 0x001d, IllegalAccessException -> 0x001b }
        r4 = new java.lang.Class[r3];	 Catch:{ ClassNotFoundException -> 0x007b, NoSuchMethodException -> 0x0064, InvocationTargetException -> 0x001d, IllegalAccessException -> 0x001b }
        r5 = 0;	 Catch:{ ClassNotFoundException -> 0x007b, NoSuchMethodException -> 0x0064, InvocationTargetException -> 0x001d, IllegalAccessException -> 0x001b }
        r4[r5] = r6;	 Catch:{ ClassNotFoundException -> 0x007b, NoSuchMethodException -> 0x0064, InvocationTargetException -> 0x001d, IllegalAccessException -> 0x001b }
        r1 = r1.getMethod(r2, r4);	 Catch:{ ClassNotFoundException -> 0x007b, NoSuchMethodException -> 0x0064, InvocationTargetException -> 0x001d, IllegalAccessException -> 0x001b }
        r2 = r1.getModifiers();	 Catch:{ ClassNotFoundException -> 0x007b, NoSuchMethodException -> 0x0064, InvocationTargetException -> 0x001d, IllegalAccessException -> 0x001b }
        r4 = java.lang.reflect.Modifier.isPublic(r2);	 Catch:{ ClassNotFoundException -> 0x007b, NoSuchMethodException -> 0x0064, InvocationTargetException -> 0x001d, IllegalAccessException -> 0x001b }
        if (r4 == 0) goto L_0x0004;	 Catch:{ ClassNotFoundException -> 0x007b, NoSuchMethodException -> 0x0064, InvocationTargetException -> 0x001d, IllegalAccessException -> 0x001b }
    L_0x0039:
        r2 = java.lang.reflect.Modifier.isStatic(r2);	 Catch:{ ClassNotFoundException -> 0x007b, NoSuchMethodException -> 0x0064, InvocationTargetException -> 0x001d, IllegalAccessException -> 0x001b }
        if (r2 == 0) goto L_0x0004;	 Catch:{ ClassNotFoundException -> 0x007b, NoSuchMethodException -> 0x0064, InvocationTargetException -> 0x001d, IllegalAccessException -> 0x001b }
    L_0x003f:
        r2 = 0;	 Catch:{ ClassNotFoundException -> 0x007b, NoSuchMethodException -> 0x0064, InvocationTargetException -> 0x001d, IllegalAccessException -> 0x001b }
        r3 = new java.lang.Object[r3];	 Catch:{ ClassNotFoundException -> 0x007b, NoSuchMethodException -> 0x0064, InvocationTargetException -> 0x001d, IllegalAccessException -> 0x001b }
        r3[r5] = r7;	 Catch:{ ClassNotFoundException -> 0x007b, NoSuchMethodException -> 0x0064, InvocationTargetException -> 0x001d, IllegalAccessException -> 0x001b }
        r1.invoke(r2, r3);	 Catch:{ ClassNotFoundException -> 0x007b, NoSuchMethodException -> 0x0064, InvocationTargetException -> 0x001d, IllegalAccessException -> 0x001b }
        goto L_0x0004;
    L_0x0048:
        r2 = "FirebaseApp";
        r3 = new java.lang.StringBuilder;
        r4 = "Failed to initialize ";
        r3.<init>(r4);
        r3.append(r0);
        r0 = r3.toString();
        android.util.Log.wtf(r2, r0, r1);
        goto L_0x0004;
    L_0x005c:
        r1 = "FirebaseApp";
        r2 = "Firebase API initialization failure.";
        android.util.Log.wtf(r1, r2, r0);
        goto L_0x0004;
    L_0x0064:
        r6 = new java.lang.IllegalStateException;
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r7.append(r0);
        r8 = "#getInstance has been removed by Proguard. Add keep rule to prevent it.";
        r7.append(r8);
        r7 = r7.toString();
        r6.<init>(r7);
        throw r6;
    L_0x007b:
        r1 = zzf;
        r1 = r1.contains(r0);
        if (r1 == 0) goto L_0x009a;
    L_0x0083:
        r6 = new java.lang.IllegalStateException;
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r7.append(r0);
        r8 = " is missing, but is required. Check if it has been removed by Proguard.";
        r7.append(r8);
        r7 = r7.toString();
        r6.<init>(r7);
        throw r6;
    L_0x009a:
        r1 = "FirebaseApp";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2.append(r0);
        r0 = " is not linked. Skipping initialization.";
        r2.append(r0);
        r0 = r2.toString();
        android.util.Log.d(r1, r0);
        goto L_0x0004;
    L_0x00b2:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.FirebaseApp.zza(java.lang.Class, java.lang.Object, java.lang.Iterable, boolean):void");
    }

    public static FirebaseApp initializeApp(Context context, FirebaseOptions firebaseOptions, String str) {
        FirebaseApp firebaseApp;
        if (PlatformVersion.isAtLeastIceCreamSandwich() && (context.getApplicationContext() instanceof Application)) {
            BackgroundDetector.initialize((Application) context.getApplicationContext());
            BackgroundDetector.getInstance().addListener(new C07401());
        }
        str = str.trim();
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        synchronized (zzg) {
            boolean containsKey = zza.containsKey(str) ^ 1;
            StringBuilder stringBuilder = new StringBuilder("FirebaseApp name ");
            stringBuilder.append(str);
            stringBuilder.append(" already exists!");
            Preconditions.checkState(containsKey, stringBuilder.toString());
            Preconditions.checkNotNull(context, "Application context cannot be null.");
            firebaseApp = new FirebaseApp(context, str, firebaseOptions);
            zza.put(str, firebaseApp);
        }
        firebaseApp.zze();
        return firebaseApp;
    }
}
