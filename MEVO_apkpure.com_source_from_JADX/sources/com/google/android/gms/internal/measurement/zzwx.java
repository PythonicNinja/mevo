package com.google.android.gms.internal.measurement;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import javax.annotation.Nullable;

public abstract class zzwx<T> {
    private static final Object zzbpg = new Object();
    private static boolean zzbph = false;
    private static volatile Boolean zzbpi;
    @SuppressLint({"StaticFieldLeak"})
    private static Context zzqx;
    private final zzxh zzbpj;
    final String zzbpk;
    private final String zzbpl;
    private final T zzbpm;
    private T zzbpn;
    private volatile zzwu zzbpo;
    private volatile SharedPreferences zzbpp;

    private zzwx(zzxh zzxh, String str, T t) {
        this.zzbpn = null;
        this.zzbpo = null;
        this.zzbpp = null;
        if (zzxh.zzbpv == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        this.zzbpj = zzxh;
        String valueOf = String.valueOf(zzxh.zzbpw);
        String valueOf2 = String.valueOf(str);
        this.zzbpl = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        String valueOf3 = String.valueOf(zzxh.zzbpx);
        str = String.valueOf(str);
        this.zzbpk = str.length() != 0 ? valueOf3.concat(str) : new String(valueOf3);
        this.zzbpm = t;
    }

    public static void init(Context context) {
        synchronized (zzbpg) {
            if (VERSION.SDK_INT < 24 || !context.isDeviceProtectedStorage()) {
                Context applicationContext = context.getApplicationContext();
                if (applicationContext != null) {
                    context = applicationContext;
                }
            }
            if (zzqx != context) {
                zzbpi = null;
            }
            zzqx = context;
        }
        zzbph = false;
    }

    private static zzwx<Double> zza(zzxh zzxh, String str, double d) {
        return new zzxe(zzxh, str, Double.valueOf(d));
    }

    private static zzwx<Integer> zza(zzxh zzxh, String str, int i) {
        return new zzxc(zzxh, str, Integer.valueOf(i));
    }

    private static zzwx<Long> zza(zzxh zzxh, String str, long j) {
        return new zzxb(zzxh, str, Long.valueOf(j));
    }

    private static zzwx<String> zza(zzxh zzxh, String str, String str2) {
        return new zzxf(zzxh, str, str2);
    }

    private static zzwx<Boolean> zza(zzxh zzxh, String str, boolean z) {
        return new zzxd(zzxh, str, Boolean.valueOf(z));
    }

    private static <V> V zza(com.google.android.gms.internal.measurement.zzxg<V> r2) {
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
        r0 = r2.zzsq();	 Catch:{ SecurityException -> 0x0006 }
        r2 = r0;
        return r2;
    L_0x0006:
        r0 = android.os.Binder.clearCallingIdentity();
        r2 = r2.zzsq();	 Catch:{ all -> 0x0012 }
        android.os.Binder.restoreCallingIdentity(r0);
        return r2;
    L_0x0012:
        r2 = move-exception;
        android.os.Binder.restoreCallingIdentity(r0);
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzwx.zza(com.google.android.gms.internal.measurement.zzxg):V");
    }

    static boolean zzd(String str, boolean z) {
        try {
            return zzso() ? ((Boolean) zza(new zzxa(str, false))).booleanValue() : false;
        } catch (Throwable e) {
            Log.e("PhenotypeFlag", "Unable to read GServices, returning default value.", e);
            return false;
        }
    }

    @TargetApi(24)
    @Nullable
    private final T zzsm() {
        String str;
        if (zzd("gms:phenotype:phenotype_flag:debug_bypass_phenotype", false)) {
            str = "PhenotypeFlag";
            String str2 = "Bypass reading Phenotype values for flag: ";
            String valueOf = String.valueOf(this.zzbpk);
            Log.w(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        } else if (this.zzbpj.zzbpv != null) {
            if (this.zzbpo == null) {
                this.zzbpo = zzwu.zza(zzqx.getContentResolver(), this.zzbpj.zzbpv);
            }
            str = (String) zza(new zzwy(this, this.zzbpo));
            if (str != null) {
                return zzfa(str);
            }
        } else {
            zzxh zzxh = this.zzbpj;
        }
        return null;
    }

    @Nullable
    private final T zzsn() {
        zzxh zzxh = this.zzbpj;
        if (zzso()) {
            try {
                String str = (String) zza(new zzwz(this));
                if (str != null) {
                    return zzfa(str);
                }
            } catch (Throwable e) {
                String str2 = "PhenotypeFlag";
                String str3 = "Unable to read GServices for flag: ";
                String valueOf = String.valueOf(this.zzbpk);
                Log.e(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3), e);
            }
        }
        return null;
    }

    private static boolean zzso() {
        if (zzbpi == null) {
            boolean z = false;
            if (zzqx == null) {
                return false;
            }
            if (PermissionChecker.checkSelfPermission(zzqx, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0) {
                z = true;
            }
            zzbpi = Boolean.valueOf(z);
        }
        return zzbpi.booleanValue();
    }

    public final T get() {
        if (zzqx == null) {
            throw new IllegalStateException("Must call PhenotypeFlag.init() first");
        }
        zzxh zzxh = this.zzbpj;
        T zzsm = zzsm();
        if (zzsm != null) {
            return zzsm;
        }
        zzsm = zzsn();
        return zzsm != null ? zzsm : this.zzbpm;
    }

    protected abstract T zzfa(String str);

    final /* synthetic */ String zzsp() {
        return zzws.zza(zzqx.getContentResolver(), this.zzbpl, null);
    }
}
