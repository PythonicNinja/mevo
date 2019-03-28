package com.google.android.gms.stats;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.os.WorkSource;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.providers.PooledExecutorsProvider;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.common.util.WorkSourceUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class WakeLock {
    private static ScheduledExecutorService zzaeg;
    private static Configuration zzaeh = new zza();
    private final android.os.PowerManager.WakeLock zzadv;
    private WorkSource zzadw;
    private String zzadx;
    private final int zzady;
    private final String zzadz;
    private final String zzaea;
    private final String zzaeb;
    private boolean zzaec;
    private final Map<String, Integer[]> zzaed;
    private int zzaee;
    private AtomicInteger zzaef;
    private final Context zzjp;

    public interface Configuration {
        long getMaximumTimeout(String str, String str2);

        boolean isWorkChainsEnabled();
    }

    public class HeldLock {
        private final /* synthetic */ WakeLock zzaei;
        private boolean zzaek;
        private Future zzael;
        private final String zzaem;

        private HeldLock(WakeLock wakeLock, String str) {
            this.zzaei = wakeLock;
            this.zzaek = true;
            this.zzaem = str;
        }

        public void finalize() {
            if (this.zzaek) {
                String str = "WakeLock";
                String str2 = "HeldLock finalized while still holding the WakeLock! Reason: ";
                String valueOf = String.valueOf(this.zzaem);
                Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                release(0);
            }
        }

        public void release() {
            release(0);
        }

        public synchronized void release(int i) {
            if (this.zzaek) {
                this.zzaek = false;
                if (this.zzael != null) {
                    this.zzael.cancel(false);
                    this.zzael = null;
                }
                this.zzaei.zzc(this.zzaem, i);
            }
        }
    }

    public WakeLock(Context context, int i, @Nonnull String str) {
        this(context, i, str, null, context == null ? null : context.getPackageName());
    }

    public WakeLock(Context context, int i, @Nonnull String str, @Nullable String str2) {
        this(context, i, str, str2, context == null ? null : context.getPackageName());
    }

    @SuppressLint({"UnwrappedWakeLock"})
    public WakeLock(Context context, int i, @Nonnull String str, @Nullable String str2, @Nonnull String str3) {
        this(context, i, str, str2, str3, null);
    }

    @SuppressLint({"UnwrappedWakeLock"})
    public WakeLock(Context context, int i, @Nonnull String str, @Nullable String str2, @Nonnull String str3, @Nullable String str4) {
        this.zzaec = true;
        this.zzaed = new HashMap();
        this.zzaef = new AtomicInteger(0);
        Preconditions.checkNotEmpty(str, "Wake lock name can NOT be empty");
        this.zzady = i;
        this.zzaea = str2;
        this.zzaeb = str4;
        this.zzjp = context.getApplicationContext();
        if ("com.google.android.gms".equals(context.getPackageName())) {
            this.zzadz = str;
        } else {
            str2 = String.valueOf("*gcore*:");
            String valueOf = String.valueOf(str);
            this.zzadz = valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2);
        }
        this.zzadv = ((PowerManager) context.getSystemService("power")).newWakeLock(i, str);
        if (WorkSourceUtil.hasWorkSourcePermission(context)) {
            WorkSource fromPackage;
            if (Strings.isEmptyOrWhitespace(str3)) {
                str3 = context.getPackageName();
            }
            if (!zzaeh.isWorkChainsEnabled() || str3 == null || str4 == null) {
                fromPackage = WorkSourceUtil.fromPackage(context, str3);
            } else {
                StringBuilder stringBuilder = new StringBuilder((String.valueOf(str3).length() + 42) + String.valueOf(str4).length());
                stringBuilder.append("Using experimental Pi WorkSource chains: ");
                stringBuilder.append(str3);
                stringBuilder.append(",");
                stringBuilder.append(str4);
                Log.d("WakeLock", stringBuilder.toString());
                this.zzadx = str3;
                fromPackage = WorkSourceUtil.fromPackageAndModuleExperimentalPi(context, str3, str4);
            }
            this.zzadw = fromPackage;
            addWorkSource(this.zzadw);
        }
        if (zzaeg == null) {
            zzaeg = PooledExecutorsProvider.getInstance().newSingleThreadScheduledExecutor();
        }
    }

    public static void setConfiguration(Configuration configuration) {
        zzaeh = configuration;
    }

    private final void zza(WorkSource workSource) {
        try {
            this.zzadv.setWorkSource(workSource);
        } catch (RuntimeException e) {
            Log.wtf("WakeLock", e.toString());
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.SuppressLint({"WakelockTimeout"})
    private final void zza(java.lang.String r13, long r14) {
        /*
        r12 = this;
        r5 = r12.zzn(r13);
        monitor-enter(r12);
        r13 = r12.zzaed;	 Catch:{ all -> 0x00b7 }
        r13 = r13.isEmpty();	 Catch:{ all -> 0x00b7 }
        r0 = 0;
        if (r13 == 0) goto L_0x0012;
    L_0x000e:
        r13 = r12.zzaee;	 Catch:{ all -> 0x00b7 }
        if (r13 <= 0) goto L_0x0021;
    L_0x0012:
        r13 = r12.zzadv;	 Catch:{ all -> 0x00b7 }
        r13 = r13.isHeld();	 Catch:{ all -> 0x00b7 }
        if (r13 != 0) goto L_0x0021;
    L_0x001a:
        r13 = r12.zzaed;	 Catch:{ all -> 0x00b7 }
        r13.clear();	 Catch:{ all -> 0x00b7 }
        r12.zzaee = r0;	 Catch:{ all -> 0x00b7 }
    L_0x0021:
        r13 = r12.zzaec;	 Catch:{ all -> 0x00b7 }
        r11 = 1;
        if (r13 == 0) goto L_0x004e;
    L_0x0026:
        r13 = r12.zzaed;	 Catch:{ all -> 0x00b7 }
        r13 = r13.get(r5);	 Catch:{ all -> 0x00b7 }
        r13 = (java.lang.Integer[]) r13;	 Catch:{ all -> 0x00b7 }
        if (r13 != 0) goto L_0x003f;
    L_0x0030:
        r13 = r12.zzaed;	 Catch:{ all -> 0x00b7 }
        r1 = new java.lang.Integer[r11];	 Catch:{ all -> 0x00b7 }
        r2 = java.lang.Integer.valueOf(r11);	 Catch:{ all -> 0x00b7 }
        r1[r0] = r2;	 Catch:{ all -> 0x00b7 }
        r13.put(r5, r1);	 Catch:{ all -> 0x00b7 }
        r0 = 1;
        goto L_0x004c;
    L_0x003f:
        r1 = r13[r0];	 Catch:{ all -> 0x00b7 }
        r1 = r1.intValue();	 Catch:{ all -> 0x00b7 }
        r1 = r1 + r11;
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ all -> 0x00b7 }
        r13[r0] = r1;	 Catch:{ all -> 0x00b7 }
    L_0x004c:
        if (r0 != 0) goto L_0x0056;
    L_0x004e:
        r13 = r12.zzaec;	 Catch:{ all -> 0x00b7 }
        if (r13 != 0) goto L_0x0076;
    L_0x0052:
        r13 = r12.zzaee;	 Catch:{ all -> 0x00b7 }
        if (r13 != 0) goto L_0x0076;
    L_0x0056:
        r0 = com.google.android.gms.common.stats.WakeLockTracker.getInstance();	 Catch:{ all -> 0x00b7 }
        r1 = r12.zzjp;	 Catch:{ all -> 0x00b7 }
        r13 = r12.zzadv;	 Catch:{ all -> 0x00b7 }
        r2 = com.google.android.gms.common.stats.StatsUtils.getEventKey(r13, r5);	 Catch:{ all -> 0x00b7 }
        r3 = 7;
        r4 = r12.zzadz;	 Catch:{ all -> 0x00b7 }
        r6 = r12.zzaeb;	 Catch:{ all -> 0x00b7 }
        r7 = r12.zzady;	 Catch:{ all -> 0x00b7 }
        r8 = r12.zzdo();	 Catch:{ all -> 0x00b7 }
        r9 = r14;
        r0.registerEvent(r1, r2, r3, r4, r5, r6, r7, r8, r9);	 Catch:{ all -> 0x00b7 }
        r13 = r12.zzaee;	 Catch:{ all -> 0x00b7 }
        r13 = r13 + r11;
        r12.zzaee = r13;	 Catch:{ all -> 0x00b7 }
    L_0x0076:
        monitor-exit(r12);	 Catch:{ all -> 0x00b7 }
        r13 = r12.zzadv;
        r13.acquire();
        r0 = 0;
        r13 = (r14 > r0 ? 1 : (r14 == r0 ? 0 : -1));
        if (r13 <= 0) goto L_0x00b6;
    L_0x0082:
        r13 = zzaeg;
        r0 = new com.google.android.gms.stats.zzb;
        r0.<init>(r12);
        r1 = java.util.concurrent.TimeUnit.MILLISECONDS;
        r13.schedule(r0, r14, r1);
        r13 = com.google.android.gms.common.util.PlatformVersion.isAtLeastIceCreamSandwich();
        if (r13 != 0) goto L_0x00b6;
    L_0x0094:
        r13 = r12.zzaec;
        if (r13 == 0) goto L_0x00b6;
    L_0x0098:
        r13 = "WakeLock";
        r14 = "Do not acquire with timeout on reference counted wakeLocks before ICS. wakelock: ";
        r15 = r12.zzadz;
        r15 = java.lang.String.valueOf(r15);
        r0 = r15.length();
        if (r0 == 0) goto L_0x00ad;
    L_0x00a8:
        r14 = r14.concat(r15);
        goto L_0x00b3;
    L_0x00ad:
        r15 = new java.lang.String;
        r15.<init>(r14);
        r14 = r15;
    L_0x00b3:
        android.util.Log.wtf(r13, r14);
    L_0x00b6:
        return;
    L_0x00b7:
        r13 = move-exception;
        monitor-exit(r12);	 Catch:{ all -> 0x00b7 }
        throw r13;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.stats.WakeLock.zza(java.lang.String, long):void");
    }

    private final void zzb(String str, int i) {
        if (this.zzaef.decrementAndGet() < 0) {
            Log.e("WakeLock", "release without a matched acquire!");
        }
        zzc(str, i);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzc(java.lang.String r11, int r12) {
        /*
        r10 = this;
        r5 = r10.zzn(r11);
        monitor-enter(r10);
        r11 = r10.zzaec;	 Catch:{ all -> 0x0061 }
        r9 = 1;
        if (r11 == 0) goto L_0x0034;
    L_0x000a:
        r11 = r10.zzaed;	 Catch:{ all -> 0x0061 }
        r11 = r11.get(r5);	 Catch:{ all -> 0x0061 }
        r11 = (java.lang.Integer[]) r11;	 Catch:{ all -> 0x0061 }
        r0 = 0;
        if (r11 != 0) goto L_0x0016;
    L_0x0015:
        goto L_0x0032;
    L_0x0016:
        r1 = r11[r0];	 Catch:{ all -> 0x0061 }
        r1 = r1.intValue();	 Catch:{ all -> 0x0061 }
        if (r1 != r9) goto L_0x0025;
    L_0x001e:
        r11 = r10.zzaed;	 Catch:{ all -> 0x0061 }
        r11.remove(r5);	 Catch:{ all -> 0x0061 }
        r0 = 1;
        goto L_0x0032;
    L_0x0025:
        r1 = r11[r0];	 Catch:{ all -> 0x0061 }
        r1 = r1.intValue();	 Catch:{ all -> 0x0061 }
        r1 = r1 - r9;
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ all -> 0x0061 }
        r11[r0] = r1;	 Catch:{ all -> 0x0061 }
    L_0x0032:
        if (r0 != 0) goto L_0x003c;
    L_0x0034:
        r11 = r10.zzaec;	 Catch:{ all -> 0x0061 }
        if (r11 != 0) goto L_0x005c;
    L_0x0038:
        r11 = r10.zzaee;	 Catch:{ all -> 0x0061 }
        if (r11 != r9) goto L_0x005c;
    L_0x003c:
        r0 = com.google.android.gms.common.stats.WakeLockTracker.getInstance();	 Catch:{ all -> 0x0061 }
        r1 = r10.zzjp;	 Catch:{ all -> 0x0061 }
        r11 = r10.zzadv;	 Catch:{ all -> 0x0061 }
        r2 = com.google.android.gms.common.stats.StatsUtils.getEventKey(r11, r5);	 Catch:{ all -> 0x0061 }
        r3 = 8;
        r4 = r10.zzadz;	 Catch:{ all -> 0x0061 }
        r6 = r10.zzaeb;	 Catch:{ all -> 0x0061 }
        r7 = r10.zzady;	 Catch:{ all -> 0x0061 }
        r8 = r10.zzdo();	 Catch:{ all -> 0x0061 }
        r0.registerEvent(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ all -> 0x0061 }
        r11 = r10.zzaee;	 Catch:{ all -> 0x0061 }
        r11 = r11 - r9;
        r10.zzaee = r11;	 Catch:{ all -> 0x0061 }
    L_0x005c:
        monitor-exit(r10);	 Catch:{ all -> 0x0061 }
        r10.zzn(r12);
        return;
    L_0x0061:
        r11 = move-exception;
        monitor-exit(r10);	 Catch:{ all -> 0x0061 }
        throw r11;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.stats.WakeLock.zzc(java.lang.String, int):void");
    }

    private final List<String> zzdo() {
        Collection names = WorkSourceUtil.getNames(this.zzadw);
        if (this.zzadx == null) {
            return names;
        }
        List<String> arrayList = new ArrayList(names);
        arrayList.add(this.zzadx);
        return arrayList;
    }

    private final String zzn(String str) {
        return this.zzaec ? !TextUtils.isEmpty(str) ? str : this.zzaea : this.zzaea;
    }

    private final void zzn(int i) {
        if (this.zzadv.isHeld()) {
            try {
                if (VERSION.SDK_INT < 21 || i <= 0) {
                    this.zzadv.release();
                } else {
                    this.zzadv.release(i);
                }
            } catch (Throwable e) {
                if (e.getClass().equals(RuntimeException.class)) {
                    Log.e("WakeLock", String.valueOf(this.zzadz).concat(" was already released!"), e);
                    return;
                }
                throw e;
            }
        }
    }

    public void acquire() {
        this.zzaef.incrementAndGet();
        zza(null, 0);
    }

    public void acquire(long j) {
        this.zzaef.incrementAndGet();
        zza(null, j);
    }

    public void acquire(String str) {
        this.zzaef.incrementAndGet();
        zza(str, 0);
    }

    public void acquire(String str, long j) {
        this.zzaef.incrementAndGet();
        zza(str, j);
    }

    public HeldLock acquireLock(long j, String str) {
        j = Math.min(j, zzaeh.getMaximumTimeout(this.zzadz, str));
        HeldLock heldLock = new HeldLock(str);
        zza(str, 0);
        heldLock.zzael = zzaeg.schedule(new zzc(new WeakReference(heldLock)), j, TimeUnit.MILLISECONDS);
        return heldLock;
    }

    public void addWorkSource(WorkSource workSource) {
        if (workSource != null && WorkSourceUtil.hasWorkSourcePermission(this.zzjp)) {
            if (this.zzadw != null) {
                this.zzadw.add(workSource);
            } else {
                this.zzadw = workSource;
            }
            zza(this.zzadw);
        }
    }

    public android.os.PowerManager.WakeLock getWakeLock() {
        return this.zzadv;
    }

    public boolean isHeld() {
        return this.zzadv.isHeld();
    }

    public void release() {
        zzb(null, 0);
    }

    public void release(int i) {
        zzb(null, i);
    }

    public void release(String str) {
        zzb(str, 0);
    }

    public void release(String str, int i) {
        zzb(str, i);
    }

    public void removeWorkSource(WorkSource workSource) {
        if (workSource != null && WorkSourceUtil.hasWorkSourcePermission(this.zzjp)) {
            try {
                if (this.zzadw != null) {
                    this.zzadw.remove(workSource);
                }
                zza(this.zzadw);
            } catch (ArrayIndexOutOfBoundsException e) {
                Log.e("WakeLock", e.toString());
            }
        }
    }

    public void setReferenceCounted(boolean z) {
        this.zzadv.setReferenceCounted(z);
        this.zzaec = z;
    }

    public void setWorkSource(WorkSource workSource) {
        if (WorkSourceUtil.hasWorkSourcePermission(this.zzjp)) {
            zza(workSource);
            this.zzadw = workSource;
            this.zzadx = null;
        }
    }
}
