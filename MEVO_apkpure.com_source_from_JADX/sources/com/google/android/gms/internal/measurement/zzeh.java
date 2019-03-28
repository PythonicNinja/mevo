package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzez.zza;
import java.lang.reflect.InvocationTargetException;

public final class zzeh extends zzhi {
    private Boolean zzagi;
    @NonNull
    private zzej zzagj = zzei.zzagk;
    private Boolean zzxy;

    zzeh(zzgn zzgn) {
        super(zzgn);
    }

    static String zzhn() {
        return (String) zzez.zzaie.get();
    }

    public static long zzhq() {
        return ((Long) zzez.zzajh.get()).longValue();
    }

    public static long zzhr() {
        return ((Long) zzez.zzaih.get()).longValue();
    }

    public static boolean zzht() {
        return ((Boolean) zzez.zzaid.get()).booleanValue();
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @android.support.annotation.WorkerThread
    public final long zza(java.lang.String r3, @android.support.annotation.NonNull com.google.android.gms.internal.measurement.zzez.zza<java.lang.Long> r4) {
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
        if (r3 != 0) goto L_0x000d;
    L_0x0002:
        r3 = r4.get();
        r3 = (java.lang.Long) r3;
        r3 = r3.longValue();
        return r3;
    L_0x000d:
        r0 = r2.zzagj;
        r1 = r4.getKey();
        r3 = r0.zze(r3, r1);
        r0 = android.text.TextUtils.isEmpty(r3);
        if (r0 == 0) goto L_0x001e;
    L_0x001d:
        goto L_0x0002;
    L_0x001e:
        r0 = java.lang.Long.parseLong(r3);	 Catch:{ NumberFormatException -> 0x0002 }
        r3 = java.lang.Long.valueOf(r0);	 Catch:{ NumberFormatException -> 0x0002 }
        r3 = r4.get(r3);	 Catch:{ NumberFormatException -> 0x0002 }
        r3 = (java.lang.Long) r3;	 Catch:{ NumberFormatException -> 0x0002 }
        r0 = r3.longValue();	 Catch:{ NumberFormatException -> 0x0002 }
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzeh.zza(java.lang.String, com.google.android.gms.internal.measurement.zzez$zza):long");
    }

    final void zza(@NonNull zzej zzej) {
        this.zzagj = zzej;
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    @WorkerThread
    public final int zzas(@Size(min = 1) String str) {
        return zzb(str, zzez.zzais);
    }

    @Nullable
    @VisibleForTesting
    final Boolean zzat(@Size(min = 1) String str) {
        Preconditions.checkNotEmpty(str);
        try {
            if (getContext().getPackageManager() == null) {
                zzgi().zziv().log("Failed to load metadata: PackageManager is null");
                return null;
            }
            ApplicationInfo applicationInfo = Wrappers.packageManager(getContext()).getApplicationInfo(getContext().getPackageName(), 128);
            if (applicationInfo == null) {
                zzgi().zziv().log("Failed to load metadata: ApplicationInfo is null");
                return null;
            } else if (applicationInfo.metaData != null) {
                return !applicationInfo.metaData.containsKey(str) ? null : Boolean.valueOf(applicationInfo.metaData.getBoolean(str));
            } else {
                zzgi().zziv().log("Failed to load metadata: Metadata bundle is null");
                return null;
            }
        } catch (NameNotFoundException e) {
            zzgi().zziv().zzg("Failed to load metadata: Package name not found", e);
            return null;
        }
    }

    public final boolean zzau(String str) {
        return "1".equals(this.zzagj.zze(str, "gaia_collection_enabled"));
    }

    public final boolean zzav(String str) {
        return "1".equals(this.zzagj.zze(str, "measurement.event_sampling_enabled"));
    }

    @WorkerThread
    final boolean zzaw(String str) {
        return zzd(str, zzez.zzajq);
    }

    @WorkerThread
    final boolean zzax(String str) {
        return zzd(str, zzez.zzajs);
    }

    @WorkerThread
    final boolean zzay(String str) {
        return zzd(str, zzez.zzajt);
    }

    @WorkerThread
    final boolean zzaz(String str) {
        return zzd(str, zzez.zzajl);
    }

    @android.support.annotation.WorkerThread
    public final int zzb(java.lang.String r3, @android.support.annotation.NonNull com.google.android.gms.internal.measurement.zzez.zza<java.lang.Integer> r4) {
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
        if (r3 != 0) goto L_0x000d;
    L_0x0002:
        r3 = r4.get();
        r3 = (java.lang.Integer) r3;
        r3 = r3.intValue();
        return r3;
    L_0x000d:
        r0 = r2.zzagj;
        r1 = r4.getKey();
        r3 = r0.zze(r3, r1);
        r0 = android.text.TextUtils.isEmpty(r3);
        if (r0 == 0) goto L_0x001e;
    L_0x001d:
        goto L_0x0002;
    L_0x001e:
        r3 = java.lang.Integer.parseInt(r3);	 Catch:{ NumberFormatException -> 0x0002 }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ NumberFormatException -> 0x0002 }
        r3 = r4.get(r3);	 Catch:{ NumberFormatException -> 0x0002 }
        r3 = (java.lang.Integer) r3;	 Catch:{ NumberFormatException -> 0x0002 }
        r3 = r3.intValue();	 Catch:{ NumberFormatException -> 0x0002 }
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzeh.zzb(java.lang.String, com.google.android.gms.internal.measurement.zzez$zza):int");
    }

    @WorkerThread
    final String zzba(String str) {
        zza zza = zzez.zzajm;
        return (String) (str == null ? zza.get() : zza.get(this.zzagj.zze(str, zza.getKey())));
    }

    final boolean zzbb(String str) {
        return zzd(str, zzez.zzaju);
    }

    @WorkerThread
    final boolean zzbc(String str) {
        return zzd(str, zzez.zzajv);
    }

    @WorkerThread
    final boolean zzbd(String str) {
        return zzd(str, zzez.zzajy);
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    @android.support.annotation.WorkerThread
    public final double zzc(java.lang.String r3, @android.support.annotation.NonNull com.google.android.gms.internal.measurement.zzez.zza<java.lang.Double> r4) {
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
        if (r3 != 0) goto L_0x000d;
    L_0x0002:
        r3 = r4.get();
        r3 = (java.lang.Double) r3;
        r3 = r3.doubleValue();
        return r3;
    L_0x000d:
        r0 = r2.zzagj;
        r1 = r4.getKey();
        r3 = r0.zze(r3, r1);
        r0 = android.text.TextUtils.isEmpty(r3);
        if (r0 == 0) goto L_0x001e;
    L_0x001d:
        goto L_0x0002;
    L_0x001e:
        r0 = java.lang.Double.parseDouble(r3);	 Catch:{ NumberFormatException -> 0x0002 }
        r3 = java.lang.Double.valueOf(r0);	 Catch:{ NumberFormatException -> 0x0002 }
        r3 = r4.get(r3);	 Catch:{ NumberFormatException -> 0x0002 }
        r3 = (java.lang.Double) r3;	 Catch:{ NumberFormatException -> 0x0002 }
        r0 = r3.doubleValue();	 Catch:{ NumberFormatException -> 0x0002 }
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzeh.zzc(java.lang.String, com.google.android.gms.internal.measurement.zzez$zza):double");
    }

    @WorkerThread
    public final boolean zzd(String str, @NonNull zza<Boolean> zza) {
        Object zze;
        if (str != null) {
            zze = this.zzagj.zze(str, zza.getKey());
            if (!TextUtils.isEmpty(zze)) {
                zze = zza.get(Boolean.valueOf(Boolean.parseBoolean(zze)));
                return ((Boolean) zze).booleanValue();
            }
        }
        zze = zza.get();
        return ((Boolean) zze).booleanValue();
    }

    public final boolean zzds() {
        if (this.zzxy == null) {
            synchronized (this) {
                if (this.zzxy == null) {
                    ApplicationInfo applicationInfo = getContext().getApplicationInfo();
                    String myProcessName = ProcessUtils.getMyProcessName();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        boolean z = str != null && str.equals(myProcessName);
                        this.zzxy = Boolean.valueOf(z);
                    }
                    if (this.zzxy == null) {
                        this.zzxy = Boolean.TRUE;
                        zzgi().zziv().log("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzxy.booleanValue();
    }

    public final /* bridge */ /* synthetic */ void zzfu() {
        super.zzfu();
    }

    public final /* bridge */ /* synthetic */ void zzfv() {
        super.zzfv();
    }

    public final /* bridge */ /* synthetic */ void zzfw() {
        super.zzfw();
    }

    public final /* bridge */ /* synthetic */ zzer zzge() {
        return super.zzge();
    }

    public final /* bridge */ /* synthetic */ zzfg zzgf() {
        return super.zzgf();
    }

    public final /* bridge */ /* synthetic */ zzkd zzgg() {
        return super.zzgg();
    }

    public final /* bridge */ /* synthetic */ zzgi zzgh() {
        return super.zzgh();
    }

    public final /* bridge */ /* synthetic */ zzfi zzgi() {
        return super.zzgi();
    }

    public final /* bridge */ /* synthetic */ zzft zzgj() {
        return super.zzgj();
    }

    public final /* bridge */ /* synthetic */ zzeh zzgk() {
        return super.zzgk();
    }

    public final /* bridge */ /* synthetic */ zzee zzgl() {
        return super.zzgl();
    }

    public final long zzgw() {
        zzgl();
        return 12780;
    }

    public final boolean zzho() {
        zzgl();
        Boolean zzat = zzat("firebase_analytics_collection_deactivated");
        return zzat != null && zzat.booleanValue();
    }

    public final Boolean zzhp() {
        zzgl();
        return zzat("firebase_analytics_collection_enabled");
    }

    public final String zzhs() {
        Object e;
        zzfk zziv;
        String str;
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke(null, new Object[]{"debug.firebase.analytics.app", ""});
        } catch (ClassNotFoundException e2) {
            e = e2;
            zziv = zzgi().zziv();
            str = "Could not find SystemProperties class";
            zziv.zzg(str, e);
            return "";
        } catch (NoSuchMethodException e3) {
            e = e3;
            zziv = zzgi().zziv();
            str = "Could not find SystemProperties.get() method";
            zziv.zzg(str, e);
            return "";
        } catch (IllegalAccessException e4) {
            e = e4;
            zziv = zzgi().zziv();
            str = "Could not access SystemProperties.get()";
            zziv.zzg(str, e);
            return "";
        } catch (InvocationTargetException e5) {
            e = e5;
            zziv = zzgi().zziv();
            str = "SystemProperties.get() threw an exception";
            zziv.zzg(str, e);
            return "";
        }
    }

    @WorkerThread
    final boolean zzhu() {
        if (this.zzagi == null) {
            this.zzagi = zzat("app_measurement_lite");
            if (this.zzagi == null) {
                this.zzagi = Boolean.valueOf(false);
            }
        }
        return this.zzagi.booleanValue();
    }
}
