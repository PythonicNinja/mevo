package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.util.Clock;
import java.math.BigInteger;
import java.util.Locale;

public final class zzfd extends zzdz {
    private String zzafa;
    private String zzafh;
    private long zzafl;
    private int zzagb;
    private int zzakd;
    private long zzake;
    private String zztf;
    private String zztg;
    private String zzth;

    zzfd(zzgn zzgn) {
        super(zzgn);
    }

    @android.support.annotation.WorkerThread
    private final java.lang.String zzgr() {
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
        r3 = this;
        r3.zzab();
        r3.zzfv();
        r0 = r3.zzgk();
        r1 = r3.zzth;
        r0 = r0.zzbb(r1);
        r1 = 0;
        if (r0 == 0) goto L_0x001c;
    L_0x0013:
        r0 = r3.zzacv;
        r0 = r0.isEnabled();
        if (r0 != 0) goto L_0x001c;
    L_0x001b:
        return r1;
    L_0x001c:
        r0 = com.google.firebase.iid.FirebaseInstanceId.getInstance();	 Catch:{ IllegalStateException -> 0x0025 }
        r0 = r0.getId();	 Catch:{ IllegalStateException -> 0x0025 }
        return r0;
    L_0x0025:
        r0 = r3.zzgi();
        r0 = r0.zziy();
        r2 = "Failed to retrieve Firebase Instance Id";
        r0.log(r2);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzfd.zzgr():java.lang.String");
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    final String getGmpAppId() {
        zzch();
        return this.zzafa;
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    final String zzah() {
        zzch();
        return this.zzth;
    }

    @WorkerThread
    final zzeb zzbl(String str) {
        boolean z;
        boolean booleanValue;
        long j;
        zzab();
        zzfv();
        String zzah = zzah();
        String gmpAppId = getGmpAppId();
        zzch();
        String str2 = this.zztg;
        long zzis = (long) zzis();
        zzch();
        String str3 = this.zzafh;
        long zzgw = zzgk().zzgw();
        zzch();
        zzab();
        if (this.zzake == 0) {
            r0.zzake = r0.zzacv.zzgg().zzd(getContext(), getContext().getPackageName());
        }
        long j2 = r0.zzake;
        boolean isEnabled = r0.zzacv.isEnabled();
        boolean z2 = zzgj().zzamm ^ 1;
        String zzgr = zzgr();
        zzch();
        boolean z3 = z2;
        String str4 = zzgr;
        long j3 = r0.zzafl;
        long zzke = r0.zzacv.zzke();
        int zzit = zzit();
        zzhi zzgk = zzgk();
        zzgk.zzfv();
        Boolean zzat = zzgk.zzat("google_analytics_adid_collection_enabled");
        if (zzat != null) {
            if (!zzat.booleanValue()) {
                z = false;
                booleanValue = Boolean.valueOf(z).booleanValue();
                zzgk = zzgk();
                zzgk.zzfv();
                zzat = zzgk.zzat("google_analytics_ssaid_collection_enabled");
                if (zzat != null) {
                    if (zzat.booleanValue()) {
                        z = false;
                        j = j3;
                        return new zzeb(zzah, gmpAppId, str2, zzis, str3, zzgw, j2, str, isEnabled, z3, str4, j, zzke, zzit, booleanValue, Boolean.valueOf(z).booleanValue(), zzgj().zzjo());
                    }
                }
                z = true;
                j = j3;
                return new zzeb(zzah, gmpAppId, str2, zzis, str3, zzgw, j2, str, isEnabled, z3, str4, j, zzke, zzit, booleanValue, Boolean.valueOf(z).booleanValue(), zzgj().zzjo());
            }
        }
        z = true;
        booleanValue = Boolean.valueOf(z).booleanValue();
        zzgk = zzgk();
        zzgk.zzfv();
        zzat = zzgk.zzat("google_analytics_ssaid_collection_enabled");
        if (zzat != null) {
            if (zzat.booleanValue()) {
                z = false;
                j = j3;
                return new zzeb(zzah, gmpAppId, str2, zzis, str3, zzgw, j2, str, isEnabled, z3, str4, j, zzke, zzit, booleanValue, Boolean.valueOf(z).booleanValue(), zzgj().zzjo());
            }
        }
        z = true;
        j = j3;
        return new zzeb(zzah, gmpAppId, str2, zzis, str3, zzgw, j2, str, isEnabled, z3, str4, j, zzke, zzit, booleanValue, Boolean.valueOf(z).booleanValue(), zzgj().zzjo());
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
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

    public final /* bridge */ /* synthetic */ zzdu zzfx() {
        return super.zzfx();
    }

    public final /* bridge */ /* synthetic */ zzhm zzfy() {
        return super.zzfy();
    }

    public final /* bridge */ /* synthetic */ zzfd zzfz() {
        return super.zzfz();
    }

    public final /* bridge */ /* synthetic */ zzik zzga() {
        return super.zzga();
    }

    public final /* bridge */ /* synthetic */ zzih zzgb() {
        return super.zzgb();
    }

    public final /* bridge */ /* synthetic */ zzfe zzgc() {
        return super.zzgc();
    }

    public final /* bridge */ /* synthetic */ zzjj zzgd() {
        return super.zzgd();
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

    protected final boolean zzgn() {
        return true;
    }

    protected final void zzgo() {
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
        r10 = this;
        r0 = "unknown";
        r1 = "Unknown";
        r2 = "Unknown";
        r3 = r10.getContext();
        r3 = r3.getPackageName();
        r4 = r10.getContext();
        r4 = r4.getPackageManager();
        r5 = 0;
        r6 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        if (r4 != 0) goto L_0x002d;
    L_0x001b:
        r4 = r10.zzgi();
        r4 = r4.zziv();
        r7 = "PackageManager is null, app identity information might be inaccurate. appId";
        r8 = com.google.android.gms.internal.measurement.zzfi.zzbp(r3);
        r4.zzg(r7, r8);
        goto L_0x008b;
    L_0x002d:
        r7 = r4.getInstallerPackageName(r3);	 Catch:{ IllegalArgumentException -> 0x0033 }
        r0 = r7;
        goto L_0x0044;
    L_0x0033:
        r7 = r10.zzgi();
        r7 = r7.zziv();
        r8 = "Error retrieving app installer package name. appId";
        r9 = com.google.android.gms.internal.measurement.zzfi.zzbp(r3);
        r7.zzg(r8, r9);
    L_0x0044:
        if (r0 != 0) goto L_0x0049;
    L_0x0046:
        r0 = "manual_install";
        goto L_0x0053;
    L_0x0049:
        r7 = "com.android.vending";
        r7 = r7.equals(r0);
        if (r7 == 0) goto L_0x0053;
    L_0x0051:
        r0 = "";
    L_0x0053:
        r7 = r10.getContext();	 Catch:{ NameNotFoundException -> 0x007a }
        r7 = r7.getPackageName();	 Catch:{ NameNotFoundException -> 0x007a }
        r7 = r4.getPackageInfo(r7, r5);	 Catch:{ NameNotFoundException -> 0x007a }
        if (r7 == 0) goto L_0x008b;	 Catch:{ NameNotFoundException -> 0x007a }
    L_0x0061:
        r8 = r7.applicationInfo;	 Catch:{ NameNotFoundException -> 0x007a }
        r4 = r4.getApplicationLabel(r8);	 Catch:{ NameNotFoundException -> 0x007a }
        r8 = android.text.TextUtils.isEmpty(r4);	 Catch:{ NameNotFoundException -> 0x007a }
        if (r8 != 0) goto L_0x0072;	 Catch:{ NameNotFoundException -> 0x007a }
    L_0x006d:
        r4 = r4.toString();	 Catch:{ NameNotFoundException -> 0x007a }
        r2 = r4;	 Catch:{ NameNotFoundException -> 0x007a }
    L_0x0072:
        r4 = r7.versionName;	 Catch:{ NameNotFoundException -> 0x007a }
        r1 = r7.versionCode;	 Catch:{ NameNotFoundException -> 0x0079 }
        r6 = r1;
        r1 = r4;
        goto L_0x008b;
    L_0x0079:
        r1 = r4;
    L_0x007a:
        r4 = r10.zzgi();
        r4 = r4.zziv();
        r7 = "Error retrieving package info. appId, appName";
        r8 = com.google.android.gms.internal.measurement.zzfi.zzbp(r3);
        r4.zze(r7, r8, r2);
    L_0x008b:
        r10.zzth = r3;
        r10.zzafh = r0;
        r10.zztg = r1;
        r10.zzakd = r6;
        r10.zztf = r2;
        r0 = 0;
        r10.zzake = r0;
        r10.zzgl();
        r2 = r10.getContext();
        r2 = com.google.android.gms.common.api.internal.GoogleServices.initialize(r2);
        r4 = 1;
        if (r2 == 0) goto L_0x00af;
    L_0x00a7:
        r6 = r2.isSuccess();
        if (r6 == 0) goto L_0x00af;
    L_0x00ad:
        r6 = 1;
        goto L_0x00b0;
    L_0x00af:
        r6 = 0;
    L_0x00b0:
        if (r6 != 0) goto L_0x00db;
    L_0x00b2:
        if (r2 != 0) goto L_0x00c2;
    L_0x00b4:
        r2 = r10.zzgi();
        r2 = r2.zziv();
        r7 = "GoogleService failed to initialize (no status)";
        r2.log(r7);
        goto L_0x00db;
    L_0x00c2:
        r7 = r10.zzgi();
        r7 = r7.zziv();
        r8 = "GoogleService failed to initialize, status";
        r9 = r2.getStatusCode();
        r9 = java.lang.Integer.valueOf(r9);
        r2 = r2.getStatusMessage();
        r7.zze(r8, r9, r2);
    L_0x00db:
        if (r6 == 0) goto L_0x0131;
    L_0x00dd:
        r2 = r10.zzgk();
        r2 = r2.zzhp();
        r6 = r10.zzgk();
        r6 = r6.zzho();
        if (r6 == 0) goto L_0x00fd;
    L_0x00ef:
        r2 = r10.zzgi();
        r2 = r2.zzja();
        r4 = "Collection disabled with firebase_analytics_collection_deactivated=1";
    L_0x00f9:
        r2.log(r4);
        goto L_0x0131;
    L_0x00fd:
        if (r2 == 0) goto L_0x0110;
    L_0x00ff:
        r6 = r2.booleanValue();
        if (r6 != 0) goto L_0x0110;
    L_0x0105:
        r2 = r10.zzgi();
        r2 = r2.zzja();
        r4 = "Collection disabled with firebase_analytics_collection_enabled=0";
        goto L_0x00f9;
    L_0x0110:
        if (r2 != 0) goto L_0x0123;
    L_0x0112:
        r2 = com.google.android.gms.common.api.internal.GoogleServices.isMeasurementExplicitlyDisabled();
        if (r2 == 0) goto L_0x0123;
    L_0x0118:
        r2 = r10.zzgi();
        r2 = r2.zzja();
        r4 = "Collection disabled with google_app_measurement_enable=0";
        goto L_0x00f9;
    L_0x0123:
        r2 = r10.zzgi();
        r2 = r2.zzjc();
        r6 = "Collection enabled";
        r2.log(r6);
        goto L_0x0132;
    L_0x0131:
        r4 = 0;
    L_0x0132:
        r2 = "";
        r10.zzafa = r2;
        r10.zzafl = r0;
        r10.zzgl();
        r0 = r10.zzacv;
        r0 = r0.zzkd();
        if (r0 == 0) goto L_0x014c;
    L_0x0143:
        r0 = r10.zzacv;
        r0 = r0.zzkd();
        r10.zzafa = r0;
        goto L_0x0180;
    L_0x014c:
        r0 = com.google.android.gms.common.api.internal.GoogleServices.getGoogleAppId();	 Catch:{ IllegalStateException -> 0x016e }
        r1 = android.text.TextUtils.isEmpty(r0);	 Catch:{ IllegalStateException -> 0x016e }
        if (r1 == 0) goto L_0x0158;	 Catch:{ IllegalStateException -> 0x016e }
    L_0x0156:
        r0 = "";	 Catch:{ IllegalStateException -> 0x016e }
    L_0x0158:
        r10.zzafa = r0;	 Catch:{ IllegalStateException -> 0x016e }
        if (r4 == 0) goto L_0x0180;	 Catch:{ IllegalStateException -> 0x016e }
    L_0x015c:
        r0 = r10.zzgi();	 Catch:{ IllegalStateException -> 0x016e }
        r0 = r0.zzjc();	 Catch:{ IllegalStateException -> 0x016e }
        r1 = "App package, google app id";	 Catch:{ IllegalStateException -> 0x016e }
        r2 = r10.zzth;	 Catch:{ IllegalStateException -> 0x016e }
        r4 = r10.zzafa;	 Catch:{ IllegalStateException -> 0x016e }
        r0.zze(r1, r2, r4);	 Catch:{ IllegalStateException -> 0x016e }
        goto L_0x0180;
    L_0x016e:
        r0 = move-exception;
        r1 = r10.zzgi();
        r1 = r1.zziv();
        r2 = "getGoogleAppId or isMeasurementEnabled failed with exception. appId";
        r3 = com.google.android.gms.internal.measurement.zzfi.zzbp(r3);
        r1.zze(r2, r3, r0);
    L_0x0180:
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 16;
        if (r0 < r1) goto L_0x0191;
    L_0x0186:
        r0 = r10.getContext();
        r0 = com.google.android.gms.common.wrappers.InstantApps.isInstantApp(r0);
        r10.zzagb = r0;
        return;
    L_0x0191:
        r10.zzagb = r5;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzfd.zzgo():void");
    }

    @WorkerThread
    final String zzir() {
        zzgg().zzlo().nextBytes(new byte[16]);
        return String.format(Locale.US, "%032x", new Object[]{new BigInteger(1, r0)});
    }

    final int zzis() {
        zzch();
        return this.zzakd;
    }

    final int zzit() {
        zzch();
        return this.zzagb;
    }
}
