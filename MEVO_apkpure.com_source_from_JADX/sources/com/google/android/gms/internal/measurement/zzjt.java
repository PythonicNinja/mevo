package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzjt implements zzhk {
    private static volatile zzjt zzarr;
    private final zzgn zzacv;
    private zzgh zzars;
    private zzfm zzart;
    private zzek zzaru;
    private zzfr zzarv;
    private zzjp zzarw;
    private zzed zzarx;
    private final zzjz zzary;
    private boolean zzarz;
    @VisibleForTesting
    private long zzasa;
    private List<Runnable> zzasb;
    private int zzasc;
    private int zzasd;
    private boolean zzase;
    private boolean zzasf;
    private boolean zzasg;
    private FileLock zzash;
    private FileChannel zzasi;
    private List<Long> zzasj;
    private List<Long> zzask;
    private long zzasl;
    private boolean zzvn;

    class zza implements zzem {
        private final /* synthetic */ zzjt zzasn;
        zzku zzasp;
        List<Long> zzasq;
        List<zzkr> zzasr;
        private long zzass;

        private zza(zzjt zzjt) {
            this.zzasn = zzjt;
        }

        private static long zza(zzkr zzkr) {
            return ((zzkr.zzavb.longValue() / 1000) / 60) / 60;
        }

        public final boolean zza(long j, zzkr zzkr) {
            Preconditions.checkNotNull(zzkr);
            if (this.zzasr == null) {
                this.zzasr = new ArrayList();
            }
            if (this.zzasq == null) {
                this.zzasq = new ArrayList();
            }
            if (this.zzasr.size() > 0 && zza((zzkr) this.zzasr.get(0)) != zza(zzkr)) {
                return false;
            }
            long zzwb = this.zzass + ((long) zzkr.zzwb());
            if (zzwb >= ((long) Math.max(0, ((Integer) zzez.zzaim.get()).intValue()))) {
                return false;
            }
            this.zzass = zzwb;
            this.zzasr.add(zzkr);
            this.zzasq.add(Long.valueOf(j));
            return this.zzasr.size() < Math.max(1, ((Integer) zzez.zzain.get()).intValue());
        }

        public final void zzb(zzku zzku) {
            Preconditions.checkNotNull(zzku);
            this.zzasp = zzku;
        }
    }

    private zzjt(zzjy zzjy) {
        this(zzjy, null);
    }

    private zzjt(zzjy zzjy, zzgn zzgn) {
        this.zzvn = false;
        Preconditions.checkNotNull(zzjy);
        this.zzacv = zzgn.zza(zzjy.zzqx, null, null);
        this.zzasl = -1;
        zzjs zzjz = new zzjz(this);
        zzjz.zzm();
        this.zzary = zzjz;
        zzjz = new zzfm(this);
        zzjz.zzm();
        this.zzart = zzjz;
        zzjz = new zzgh(this);
        zzjz.zzm();
        this.zzars = zzjz;
        this.zzacv.zzgh().zzc(new zzju(this, zzjy));
    }

    @WorkerThread
    @VisibleForTesting
    private final int zza(FileChannel fileChannel) {
        zzab();
        if (fileChannel != null) {
            if (fileChannel.isOpen()) {
                ByteBuffer allocate = ByteBuffer.allocate(4);
                try {
                    fileChannel.position(0);
                    int read = fileChannel.read(allocate);
                    if (read != 4) {
                        if (read != -1) {
                            this.zzacv.zzgi().zziy().zzg("Unexpected data length. Bytes read", Integer.valueOf(read));
                        }
                        return 0;
                    }
                    allocate.flip();
                    return allocate.getInt();
                } catch (IOException e) {
                    this.zzacv.zzgi().zziv().zzg("Failed to read from channel", e);
                    return 0;
                }
            }
        }
        this.zzacv.zzgi().zziv().log("Bad channel to read from");
        return 0;
    }

    private final com.google.android.gms.internal.measurement.zzeb zza(android.content.Context r26, java.lang.String r27, java.lang.String r28, boolean r29, boolean r30, boolean r31, long r32) {
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
        r25 = this;
        r0 = r25;
        r2 = r27;
        r1 = "Unknown";
        r3 = "Unknown";
        r4 = "Unknown";
        r5 = r26.getPackageManager();
        r6 = 0;
        if (r5 != 0) goto L_0x0021;
    L_0x0011:
        r1 = r0.zzacv;
        r1 = r1.zzgi();
        r1 = r1.zziv();
        r2 = "PackageManager is null, can not log app install information";
        r1.log(r2);
        return r6;
    L_0x0021:
        r5 = r5.getInstallerPackageName(r2);	 Catch:{ IllegalArgumentException -> 0x0026 }
        goto L_0x003a;
    L_0x0026:
        r5 = r0.zzacv;
        r5 = r5.zzgi();
        r5 = r5.zziv();
        r7 = "Error retrieving installer package name. appId";
        r8 = com.google.android.gms.internal.measurement.zzfi.zzbp(r27);
        r5.zzg(r7, r8);
        r5 = r1;
    L_0x003a:
        if (r5 != 0) goto L_0x0040;
    L_0x003c:
        r1 = "manual_install";
    L_0x003e:
        r7 = r1;
        goto L_0x004c;
    L_0x0040:
        r1 = "com.android.vending";
        r1 = r1.equals(r5);
        if (r1 == 0) goto L_0x004b;
    L_0x0048:
        r1 = "";
        goto L_0x003e;
    L_0x004b:
        r7 = r5;
    L_0x004c:
        r1 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r26);	 Catch:{ NameNotFoundException -> 0x00bb }
        r5 = 0;	 Catch:{ NameNotFoundException -> 0x00bb }
        r1 = r1.getPackageInfo(r2, r5);	 Catch:{ NameNotFoundException -> 0x00bb }
        if (r1 == 0) goto L_0x006f;	 Catch:{ NameNotFoundException -> 0x00bb }
    L_0x0057:
        r3 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r26);	 Catch:{ NameNotFoundException -> 0x00bb }
        r3 = r3.getApplicationLabel(r2);	 Catch:{ NameNotFoundException -> 0x00bb }
        r5 = android.text.TextUtils.isEmpty(r3);	 Catch:{ NameNotFoundException -> 0x00bb }
        if (r5 != 0) goto L_0x006a;	 Catch:{ NameNotFoundException -> 0x00bb }
    L_0x0065:
        r3 = r3.toString();	 Catch:{ NameNotFoundException -> 0x00bb }
        r4 = r3;	 Catch:{ NameNotFoundException -> 0x00bb }
    L_0x006a:
        r3 = r1.versionName;	 Catch:{ NameNotFoundException -> 0x00bb }
        r1 = r1.versionCode;	 Catch:{ NameNotFoundException -> 0x00bb }
        goto L_0x0071;
    L_0x006f:
        r1 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
    L_0x0071:
        r4 = r3;
        r16 = 0;
        r3 = r0.zzacv;
        r3.zzgl();
        r5 = 0;
        r3 = r0.zzacv;
        r3 = r3.zzgk();
        r3 = r3.zzbd(r2);
        if (r3 == 0) goto L_0x008a;
    L_0x0087:
        r18 = r32;
        goto L_0x008c;
    L_0x008a:
        r18 = r5;
    L_0x008c:
        r24 = new com.google.android.gms.internal.measurement.zzeb;
        r5 = (long) r1;
        r1 = r0.zzacv;
        r1 = r1.zzgk();
        r8 = r1.zzgw();
        r1 = r0.zzacv;
        r1 = r1.zzgg();
        r3 = r26;
        r10 = r1.zzd(r3, r2);
        r12 = 0;
        r14 = 0;
        r15 = "";
        r20 = 0;
        r23 = 0;
        r1 = r24;
        r3 = r28;
        r13 = r29;
        r21 = r30;
        r22 = r31;
        r1.<init>(r2, r3, r4, r5, r7, r8, r10, r12, r13, r14, r15, r16, r18, r20, r21, r22, r23);
        return r24;
    L_0x00bb:
        r1 = r0.zzacv;
        r1 = r1.zzgi();
        r1 = r1.zziv();
        r3 = "Error retrieving newly installed package info. appId, appName";
        r2 = com.google.android.gms.internal.measurement.zzfi.zzbp(r27);
        r1.zze(r3, r2, r4);
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjt.zza(android.content.Context, java.lang.String, java.lang.String, boolean, boolean, boolean, long):com.google.android.gms.internal.measurement.zzeb");
    }

    private static void zza(zzjs zzjs) {
        if (zzjs == null) {
            throw new IllegalStateException("Upload Component not created");
        } else if (!zzjs.isInitialized()) {
            String valueOf = String.valueOf(zzjs.getClass());
            StringBuilder stringBuilder = new StringBuilder(String.valueOf(valueOf).length() + 27);
            stringBuilder.append("Component not initialized: ");
            stringBuilder.append(valueOf);
            throw new IllegalStateException(stringBuilder.toString());
        }
    }

    @WorkerThread
    private final void zza(zzjy zzjy) {
        this.zzacv.zzgh().zzab();
        zzjs zzek = new zzek(this);
        zzek.zzm();
        this.zzaru = zzek;
        this.zzacv.zzgk().zza(this.zzars);
        zzek = new zzed(this);
        zzek.zzm();
        this.zzarx = zzek;
        zzek = new zzjp(this);
        zzek.zzm();
        this.zzarw = zzek;
        this.zzarv = new zzfr(this);
        if (this.zzasc != this.zzasd) {
            this.zzacv.zzgi().zziv().zze("Not all upload components initialized", Integer.valueOf(this.zzasc), Integer.valueOf(this.zzasd));
        }
        this.zzvn = true;
    }

    @WorkerThread
    @VisibleForTesting
    private final boolean zza(int i, FileChannel fileChannel) {
        zzab();
        if (fileChannel != null) {
            if (fileChannel.isOpen()) {
                ByteBuffer allocate = ByteBuffer.allocate(4);
                allocate.putInt(i);
                allocate.flip();
                try {
                    fileChannel.truncate(0);
                    fileChannel.write(allocate);
                    fileChannel.force(true);
                    if (fileChannel.size() != 4) {
                        this.zzacv.zzgi().zziv().zzg("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
                    }
                    return true;
                } catch (IOException e) {
                    this.zzacv.zzgi().zziv().zzg("Failed to write to channel", e);
                    return false;
                }
            }
        }
        this.zzacv.zzgi().zziv().log("Bad channel to read from");
        return false;
    }

    private final boolean zza(String str, zzex zzex) {
        long round;
        Object string = zzex.zzahg.getString(Param.CURRENCY);
        if (Event.ECOMMERCE_PURCHASE.equals(zzex.name)) {
            double doubleValue = zzex.zzahg.zzbk(Param.VALUE).doubleValue() * 1000000.0d;
            if (doubleValue == 0.0d) {
                doubleValue = ((double) zzex.zzahg.getLong(Param.VALUE).longValue()) * 1000000.0d;
            }
            if (doubleValue > 9.223372036854776E18d || doubleValue < -9.223372036854776E18d) {
                this.zzacv.zzgi().zziy().zze("Data lost. Currency value is too big. appId", zzfi.zzbp(str), Double.valueOf(doubleValue));
                return false;
            }
            round = Math.round(doubleValue);
        } else {
            round = zzex.zzahg.getLong(Param.VALUE).longValue();
        }
        if (!TextUtils.isEmpty(string)) {
            String toUpperCase = string.toUpperCase(Locale.US);
            if (toUpperCase.matches("[A-Z]{3}")) {
                String valueOf = String.valueOf("_ltv_");
                toUpperCase = String.valueOf(toUpperCase);
                String concat = toUpperCase.length() != 0 ? valueOf.concat(toUpperCase) : new String(valueOf);
                zzkc zzh = zzjh().zzh(str, concat);
                if (zzh != null) {
                    if (zzh.value instanceof Long) {
                        zzh = new zzkc(str, zzex.origin, concat, this.zzacv.zzbt().currentTimeMillis(), Long.valueOf(((Long) zzh.value).longValue() + round));
                        if (!zzjh().zza(zzh)) {
                            this.zzacv.zzgi().zziv().zzd("Too many unique user properties are set. Ignoring user property. appId", zzfi.zzbp(str), this.zzacv.zzgf().zzbo(zzh.name), zzh.value);
                            this.zzacv.zzgg().zza(str, 9, null, null, 0);
                        }
                    }
                }
                zzhi zzjh = zzjh();
                int zzb = this.zzacv.zzgk().zzb(str, zzez.zzaji) - 1;
                Preconditions.checkNotEmpty(str);
                zzjh.zzab();
                zzjh.zzch();
                try {
                    zzjh.getWritableDatabase().execSQL("delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);", new String[]{str, str, String.valueOf(zzb)});
                } catch (SQLiteException e) {
                    zzjh.zzgi().zziv().zze("Error pruning currencies. appId", zzfi.zzbp(str), e);
                }
                zzkc zzkc = new zzkc(str, zzex.origin, concat, this.zzacv.zzbt().currentTimeMillis(), Long.valueOf(round));
                if (zzjh().zza(zzh)) {
                    this.zzacv.zzgi().zziv().zzd("Too many unique user properties are set. Ignoring user property. appId", zzfi.zzbp(str), this.zzacv.zzgf().zzbo(zzh.name), zzh.value);
                    this.zzacv.zzgg().zza(str, 9, null, null, 0);
                }
            }
        }
        return true;
    }

    private final zzkp[] zza(String str, zzkx[] zzkxArr, zzkr[] zzkrArr) {
        Preconditions.checkNotEmpty(str);
        return zzjg().zza(str, zzkrArr, zzkxArr);
    }

    @WorkerThread
    private final void zzab() {
        this.zzacv.zzgh().zzab();
    }

    @android.support.annotation.WorkerThread
    private final void zzb(com.google.android.gms.internal.measurement.zzea r11) {
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
        r10.zzab();
        r0 = r11.getGmpAppId();
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 == 0) goto L_0x001b;
    L_0x000d:
        r2 = r11.zzah();
        r3 = 204; // 0xcc float:2.86E-43 double:1.01E-321;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r1 = r10;
        r1.zzb(r2, r3, r4, r5, r6);
        return;
    L_0x001b:
        r0 = r10.zzacv;
        r0 = r0.zzgk();
        r1 = r11.getGmpAppId();
        r2 = r11.getAppInstanceId();
        r3 = new android.net.Uri$Builder;
        r3.<init>();
        r4 = com.google.android.gms.internal.measurement.zzez.zzaii;
        r4 = r4.get();
        r4 = (java.lang.String) r4;
        r4 = r3.scheme(r4);
        r5 = com.google.android.gms.internal.measurement.zzez.zzaij;
        r5 = r5.get();
        r5 = (java.lang.String) r5;
        r4 = r4.encodedAuthority(r5);
        r5 = "config/app/";
        r1 = java.lang.String.valueOf(r1);
        r6 = r1.length();
        if (r6 == 0) goto L_0x0057;
    L_0x0052:
        r1 = r5.concat(r1);
        goto L_0x005c;
    L_0x0057:
        r1 = new java.lang.String;
        r1.<init>(r5);
    L_0x005c:
        r1 = r4.path(r1);
        r4 = "app_instance_id";
        r1 = r1.appendQueryParameter(r4, r2);
        r2 = "platform";
        r4 = "android";
        r1 = r1.appendQueryParameter(r2, r4);
        r2 = "gmp_version";
        r4 = r0.zzgw();
        r0 = java.lang.String.valueOf(r4);
        r1.appendQueryParameter(r2, r0);
        r0 = r3.build();
        r0 = r0.toString();
        r4 = new java.net.URL;	 Catch:{ MalformedURLException -> 0x00f4 }
        r4.<init>(r0);	 Catch:{ MalformedURLException -> 0x00f4 }
        r1 = r10.zzacv;	 Catch:{ MalformedURLException -> 0x00f4 }
        r1 = r1.zzgi();	 Catch:{ MalformedURLException -> 0x00f4 }
        r1 = r1.zzjc();	 Catch:{ MalformedURLException -> 0x00f4 }
        r2 = "Fetching remote configuration";	 Catch:{ MalformedURLException -> 0x00f4 }
        r3 = r11.zzah();	 Catch:{ MalformedURLException -> 0x00f4 }
        r1.zzg(r2, r3);	 Catch:{ MalformedURLException -> 0x00f4 }
        r1 = r10.zzky();	 Catch:{ MalformedURLException -> 0x00f4 }
        r2 = r11.zzah();	 Catch:{ MalformedURLException -> 0x00f4 }
        r1 = r1.zzbx(r2);	 Catch:{ MalformedURLException -> 0x00f4 }
        r2 = 0;	 Catch:{ MalformedURLException -> 0x00f4 }
        r3 = r10.zzky();	 Catch:{ MalformedURLException -> 0x00f4 }
        r5 = r11.zzah();	 Catch:{ MalformedURLException -> 0x00f4 }
        r3 = r3.zzby(r5);	 Catch:{ MalformedURLException -> 0x00f4 }
        if (r1 == 0) goto L_0x00c8;	 Catch:{ MalformedURLException -> 0x00f4 }
    L_0x00b6:
        r1 = android.text.TextUtils.isEmpty(r3);	 Catch:{ MalformedURLException -> 0x00f4 }
        if (r1 != 0) goto L_0x00c8;	 Catch:{ MalformedURLException -> 0x00f4 }
    L_0x00bc:
        r1 = new android.support.v4.util.ArrayMap;	 Catch:{ MalformedURLException -> 0x00f4 }
        r1.<init>();	 Catch:{ MalformedURLException -> 0x00f4 }
        r2 = "If-Modified-Since";	 Catch:{ MalformedURLException -> 0x00f4 }
        r1.put(r2, r3);	 Catch:{ MalformedURLException -> 0x00f4 }
        r6 = r1;	 Catch:{ MalformedURLException -> 0x00f4 }
        goto L_0x00c9;	 Catch:{ MalformedURLException -> 0x00f4 }
    L_0x00c8:
        r6 = r2;	 Catch:{ MalformedURLException -> 0x00f4 }
    L_0x00c9:
        r1 = 1;	 Catch:{ MalformedURLException -> 0x00f4 }
        r10.zzase = r1;	 Catch:{ MalformedURLException -> 0x00f4 }
        r2 = r10.zzkz();	 Catch:{ MalformedURLException -> 0x00f4 }
        r3 = r11.zzah();	 Catch:{ MalformedURLException -> 0x00f4 }
        r7 = new com.google.android.gms.internal.measurement.zzjw;	 Catch:{ MalformedURLException -> 0x00f4 }
        r7.<init>(r10);	 Catch:{ MalformedURLException -> 0x00f4 }
        r2.zzab();	 Catch:{ MalformedURLException -> 0x00f4 }
        r2.zzch();	 Catch:{ MalformedURLException -> 0x00f4 }
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r4);	 Catch:{ MalformedURLException -> 0x00f4 }
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r7);	 Catch:{ MalformedURLException -> 0x00f4 }
        r8 = r2.zzgh();	 Catch:{ MalformedURLException -> 0x00f4 }
        r9 = new com.google.android.gms.internal.measurement.zzfq;	 Catch:{ MalformedURLException -> 0x00f4 }
        r5 = 0;	 Catch:{ MalformedURLException -> 0x00f4 }
        r1 = r9;	 Catch:{ MalformedURLException -> 0x00f4 }
        r1.<init>(r2, r3, r4, r5, r6, r7);	 Catch:{ MalformedURLException -> 0x00f4 }
        r8.zzd(r9);	 Catch:{ MalformedURLException -> 0x00f4 }
        return;
    L_0x00f4:
        r1 = r10.zzacv;
        r1 = r1.zzgi();
        r1 = r1.zziv();
        r2 = "Failed to parse config URL. Not fetching. appId";
        r11 = r11.zzah();
        r11 = com.google.android.gms.internal.measurement.zzfi.zzbp(r11);
        r1.zze(r2, r11, r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjt.zzb(com.google.android.gms.internal.measurement.zzea):void");
    }

    @android.support.annotation.WorkerThread
    private final java.lang.Boolean zzc(com.google.android.gms.internal.measurement.zzea r8) {
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
        r7 = this;
        r0 = r8.zzgu();	 Catch:{ NameNotFoundException -> 0x005d }
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;	 Catch:{ NameNotFoundException -> 0x005d }
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));	 Catch:{ NameNotFoundException -> 0x005d }
        r0 = 1;	 Catch:{ NameNotFoundException -> 0x005d }
        r1 = 0;	 Catch:{ NameNotFoundException -> 0x005d }
        if (r4 == 0) goto L_0x002f;	 Catch:{ NameNotFoundException -> 0x005d }
    L_0x000d:
        r2 = r7.zzacv;	 Catch:{ NameNotFoundException -> 0x005d }
        r2 = r2.getContext();	 Catch:{ NameNotFoundException -> 0x005d }
        r2 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r2);	 Catch:{ NameNotFoundException -> 0x005d }
        r3 = r8.zzah();	 Catch:{ NameNotFoundException -> 0x005d }
        r2 = r2.getPackageInfo(r3, r1);	 Catch:{ NameNotFoundException -> 0x005d }
        r2 = r2.versionCode;	 Catch:{ NameNotFoundException -> 0x005d }
        r3 = r8.zzgu();	 Catch:{ NameNotFoundException -> 0x005d }
        r5 = (long) r2;	 Catch:{ NameNotFoundException -> 0x005d }
        r8 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));	 Catch:{ NameNotFoundException -> 0x005d }
        if (r8 != 0) goto L_0x0058;	 Catch:{ NameNotFoundException -> 0x005d }
    L_0x002a:
        r8 = java.lang.Boolean.valueOf(r0);	 Catch:{ NameNotFoundException -> 0x005d }
        return r8;	 Catch:{ NameNotFoundException -> 0x005d }
    L_0x002f:
        r2 = r7.zzacv;	 Catch:{ NameNotFoundException -> 0x005d }
        r2 = r2.getContext();	 Catch:{ NameNotFoundException -> 0x005d }
        r2 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r2);	 Catch:{ NameNotFoundException -> 0x005d }
        r3 = r8.zzah();	 Catch:{ NameNotFoundException -> 0x005d }
        r2 = r2.getPackageInfo(r3, r1);	 Catch:{ NameNotFoundException -> 0x005d }
        r2 = r2.versionName;	 Catch:{ NameNotFoundException -> 0x005d }
        r3 = r8.zzag();	 Catch:{ NameNotFoundException -> 0x005d }
        if (r3 == 0) goto L_0x0058;	 Catch:{ NameNotFoundException -> 0x005d }
    L_0x0049:
        r8 = r8.zzag();	 Catch:{ NameNotFoundException -> 0x005d }
        r8 = r8.equals(r2);	 Catch:{ NameNotFoundException -> 0x005d }
        if (r8 == 0) goto L_0x0058;	 Catch:{ NameNotFoundException -> 0x005d }
    L_0x0053:
        r8 = java.lang.Boolean.valueOf(r0);	 Catch:{ NameNotFoundException -> 0x005d }
        return r8;
    L_0x0058:
        r8 = java.lang.Boolean.valueOf(r1);
        return r8;
    L_0x005d:
        r8 = 0;
        return r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjt.zzc(com.google.android.gms.internal.measurement.zzea):java.lang.Boolean");
    }

    @WorkerThread
    private final void zzc(zzex zzex, zzeb zzeb) {
        zzjt zzjt = this;
        zzex zzex2 = zzex;
        zzeb zzeb2 = zzeb;
        Preconditions.checkNotNull(zzeb);
        Preconditions.checkNotEmpty(zzeb2.packageName);
        long nanoTime = System.nanoTime();
        zzab();
        zzlc();
        String str = zzeb2.packageName;
        if (!zzjf().zzd(zzex2, zzeb2)) {
            return;
        }
        if (zzeb2.zzafk) {
            Object obj = 1;
            if (zzky().zzn(str, zzex2.name)) {
                zzjt.zzacv.zzgi().zziy().zze("Dropping blacklisted event. appId", zzfi.zzbp(str), zzjt.zzacv.zzgf().zzbm(zzex2.name));
                if (!zzky().zzcb(str)) {
                    if (!zzky().zzcc(str)) {
                        obj = null;
                    }
                }
                if (obj == null && !"_err".equals(zzex2.name)) {
                    zzjt.zzacv.zzgg().zza(str, 11, "_ev", zzex2.name, 0);
                }
                if (obj != null) {
                    zzea zzbf = zzjh().zzbf(str);
                    if (zzbf != null) {
                        if (Math.abs(zzjt.zzacv.zzbt().currentTimeMillis() - Math.max(zzbf.zzha(), zzbf.zzgz())) > ((Long) zzez.zzajd.get()).longValue()) {
                            zzjt.zzacv.zzgi().zzjb().log("Fetching config for blacklisted app");
                            zzb(zzbf);
                        }
                    }
                }
                return;
            }
            if (zzjt.zzacv.zzgi().isLoggable(2)) {
                zzjt.zzacv.zzgi().zzjc().zzg("Logging event", zzjt.zzacv.zzgf().zzb(zzex2));
            }
            zzjh().beginTransaction();
            zzg(zzeb2);
            if (("_iap".equals(zzex2.name) || Event.ECOMMERCE_PURCHASE.equals(zzex2.name)) && !zza(str, zzex2)) {
                zzjh().setTransactionSuccessful();
                zzjh().endTransaction();
                return;
            }
            zzku zzku;
            try {
                boolean zzcg = zzkd.zzcg(zzex2.name);
                boolean equals = "_err".equals(zzex2.name);
                long j = nanoTime;
                zzel zza = zzjh().zza(zzld(), str, true, zzcg, false, equals, false);
                long intValue = zza.zzagu - ((long) ((Integer) zzez.zzaio.get()).intValue());
                if (intValue > 0) {
                    if (intValue % 1000 == 1) {
                        zzjt.zzacv.zzgi().zziv().zze("Data loss. Too many events logged. appId, count", zzfi.zzbp(str), Long.valueOf(zza.zzagu));
                    }
                    zzjh().setTransactionSuccessful();
                    zzjh().endTransaction();
                    return;
                }
                zzes zza2;
                if (zzcg) {
                    long intValue2 = zza.zzagt - ((long) ((Integer) zzez.zzaiq.get()).intValue());
                    if (intValue2 > 0) {
                        if (intValue2 % 1000 == 1) {
                            zzjt.zzacv.zzgi().zziv().zze("Data loss. Too many public events logged. appId, count", zzfi.zzbp(str), Long.valueOf(zza.zzagt));
                        }
                        zzjt.zzacv.zzgg().zza(str, 16, "_ev", zzex2.name, 0);
                        zzjh().setTransactionSuccessful();
                        zzjh().endTransaction();
                        return;
                    }
                }
                if (equals) {
                    intValue = zza.zzagw - ((long) Math.max(0, Math.min(1000000, zzjt.zzacv.zzgk().zzb(zzeb2.packageName, zzez.zzaip))));
                    if (intValue > 0) {
                        if (intValue == 1) {
                            zzjt.zzacv.zzgi().zziv().zze("Too many error events logged. appId, count", zzfi.zzbp(str), Long.valueOf(zza.zzagw));
                        }
                        zzjh().setTransactionSuccessful();
                        zzjh().endTransaction();
                        return;
                    }
                }
                Bundle zzin = zzex2.zzahg.zzin();
                zzjt.zzacv.zzgg().zza(zzin, "_o", zzex2.origin);
                if (zzjt.zzacv.zzgg().zzcn(str)) {
                    zzjt.zzacv.zzgg().zza(zzin, "_dbg", Long.valueOf(1));
                    zzjt.zzacv.zzgg().zza(zzin, "_r", Long.valueOf(1));
                }
                long zzbg = zzjh().zzbg(str);
                if (zzbg > 0) {
                    zzjt.zzacv.zzgi().zziy().zze("Data lost. Too many events stored on disk, deleted. appId", zzfi.zzbp(str), Long.valueOf(zzbg));
                }
                zzgn zzgn = zzjt.zzacv;
                String str2 = zzex2.origin;
                String str3 = zzex2.name;
                long j2 = zzex2.zzahr;
                zzes zzes = r6;
                String str4 = str;
                zzes zzes2 = new zzes(zzgn, str2, str, str3, j2, 0, zzin);
                zzet zzf = zzjh().zzf(str4, zzes.name);
                if (zzf != null) {
                    zza2 = zzes.zza(zzjt.zzacv, zzf.zzahj);
                    zzf = zzf.zzah(zza2.timestamp);
                } else if (zzjh().zzbj(str4) < 500 || !zzcg) {
                    zzet zzet = new zzet(str4, zzes.name, 0, 0, zzes.timestamp, 0, null, null, null);
                    zza2 = zzes;
                } else {
                    zzjt.zzacv.zzgi().zziv().zzd("Too many event names used, ignoring event. appId, name, supported count", zzfi.zzbp(str4), zzjt.zzacv.zzgf().zzbm(zzes.name), Integer.valueOf(500));
                    zzjt.zzacv.zzgg().zza(str4, 8, null, null, 0);
                    zzjh().endTransaction();
                    return;
                }
                zzjh().zza(zzf);
                zzab();
                zzlc();
                Preconditions.checkNotNull(zza2);
                Preconditions.checkNotNull(zzeb);
                Preconditions.checkNotEmpty(zza2.zzth);
                Preconditions.checkArgument(zza2.zzth.equals(zzeb2.packageName));
                zzku = new zzku();
                boolean z = true;
                zzku.zzavh = Integer.valueOf(1);
                zzku.zzavp = "android";
                zzku.zzth = zzeb2.packageName;
                zzku.zzafh = zzeb2.zzafh;
                zzku.zztg = zzeb2.zztg;
                zzku.zzawb = zzeb2.zzafg == -2147483648L ? null : Integer.valueOf((int) zzeb2.zzafg);
                zzku.zzavt = Long.valueOf(zzeb2.zzafi);
                zzku.zzafa = zzeb2.zzafa;
                zzku.zzavx = zzeb2.zzafj == 0 ? null : Long.valueOf(zzeb2.zzafj);
                Pair zzbr = zzjt.zzacv.zzgj().zzbr(zzeb2.packageName);
                if (zzbr == null || TextUtils.isEmpty((CharSequence) zzbr.first)) {
                    if (!zzjt.zzacv.zzge().zzf(zzjt.zzacv.getContext()) && zzeb2.zzafn) {
                        String string = Secure.getString(zzjt.zzacv.getContext().getContentResolver(), "android_id");
                        if (string == null) {
                            zzjt.zzacv.zzgi().zziy().zzg("null secure ID. appId", zzfi.zzbp(zzku.zzth));
                            string = "null";
                        } else if (string.isEmpty()) {
                            zzjt.zzacv.zzgi().zziy().zzg("empty secure ID. appId", zzfi.zzbp(zzku.zzth));
                        }
                        zzku.zzawe = string;
                    }
                } else if (zzeb2.zzafm) {
                    zzku.zzavv = (String) zzbr.first;
                    zzku.zzavw = (Boolean) zzbr.second;
                }
                zzjt.zzacv.zzge().zzch();
                zzku.zzavr = Build.MODEL;
                zzjt.zzacv.zzge().zzch();
                zzku.zzavq = VERSION.RELEASE;
                zzku.zzavs = Integer.valueOf((int) zzjt.zzacv.zzge().zzik());
                zzku.zzahd = zzjt.zzacv.zzge().zzil();
                zzku.zzavu = null;
                zzku.zzavk = null;
                zzku.zzavl = null;
                zzku.zzavm = null;
                zzku.zzawg = Long.valueOf(zzeb2.zzafl);
                if (zzjt.zzacv.isEnabled() && zzeh.zzht()) {
                    zzku.zzawh = null;
                }
                zzea zzbf2 = zzjh().zzbf(zzeb2.packageName);
                if (zzbf2 == null) {
                    zzbf2 = new zzea(zzjt.zzacv, zzeb2.packageName);
                    zzbf2.zzam(zzjt.zzacv.zzfz().zzir());
                    zzbf2.zzap(zzeb2.zzafc);
                    zzbf2.zzan(zzeb2.zzafa);
                    zzbf2.zzao(zzjt.zzacv.zzgj().zzbs(zzeb2.packageName));
                    zzbf2.zzw(0);
                    zzbf2.zzr(0);
                    zzbf2.zzs(0);
                    zzbf2.setAppVersion(zzeb2.zztg);
                    zzbf2.zzt(zzeb2.zzafg);
                    zzbf2.zzaq(zzeb2.zzafh);
                    zzbf2.zzu(zzeb2.zzafi);
                    zzbf2.zzv(zzeb2.zzafj);
                    zzbf2.setMeasurementEnabled(zzeb2.zzafk);
                    zzbf2.zzaf(zzeb2.zzafl);
                    zzjh().zza(zzbf2);
                }
                zzku.zzaez = zzbf2.getAppInstanceId();
                zzku.zzafc = zzbf2.zzgr();
                List zzbe = zzjh().zzbe(zzeb2.packageName);
                zzku.zzavj = new zzkx[zzbe.size()];
                for (int i = 0; i < zzbe.size(); i++) {
                    zzkx zzkx = new zzkx();
                    zzku.zzavj[i] = zzkx;
                    zzkx.name = ((zzkc) zzbe.get(i)).name;
                    zzkx.zzaws = Long.valueOf(((zzkc) zzbe.get(i)).zzast);
                    zzjf().zza(zzkx, ((zzkc) zzbe.get(i)).value);
                }
                long zza3 = zzjh().zza(zzku);
                zzek zzjh = zzjh();
                if (zza2.zzahg != null) {
                    Iterator it = zza2.zzahg.iterator();
                    while (it.hasNext()) {
                        if ("_r".equals((String) it.next())) {
                            break;
                        }
                    }
                    boolean zzo = zzky().zzo(zza2.zzth, zza2.name);
                    zzel zza4 = zzjh().zza(zzld(), zza2.zzth, false, false, false, false, false);
                    if (zzo && zza4.zzagx < ((long) zzjt.zzacv.zzgk().zzas(zza2.zzth))) {
                        if (zzjh.zza(zza2, zza3, z)) {
                            zzjt.zzasa = 0;
                        }
                        zzjh().setTransactionSuccessful();
                        if (zzjt.zzacv.zzgi().isLoggable(2)) {
                            zzjt.zzacv.zzgi().zzjc().zzg("Event recorded", zzjt.zzacv.zzgf().zza(zza2));
                        }
                        zzjh().endTransaction();
                        zzlg();
                        zzjt.zzacv.zzgi().zzjc().zzg("Background event processing time, ms", Long.valueOf(((System.nanoTime() - j) + 500000) / 1000000));
                    }
                }
                z = false;
                if (zzjh.zza(zza2, zza3, z)) {
                    zzjt.zzasa = 0;
                }
                zzjh().setTransactionSuccessful();
                if (zzjt.zzacv.zzgi().isLoggable(2)) {
                    zzjt.zzacv.zzgi().zzjc().zzg("Event recorded", zzjt.zzacv.zzgf().zza(zza2));
                }
                zzjh().endTransaction();
                zzlg();
                zzjt.zzacv.zzgi().zzjc().zzg("Background event processing time, ms", Long.valueOf(((System.nanoTime() - j) + 500000) / 1000000));
            } catch (IOException e) {
                zzjt.zzacv.zzgi().zziv().zze("Data loss. Failed to insert raw event metadata. appId", zzfi.zzbp(zzku.zzth), e);
            } catch (Throwable th) {
                Throwable th2 = th;
                zzjh().endTransaction();
            }
        } else {
            zzg(zzeb2);
        }
    }

    @WorkerThread
    private final zzeb zzce(String str) {
        zzfk zziv;
        String str2;
        Object zzbp;
        zzjt zzjt = this;
        String str3 = str;
        zzea zzbf = zzjh().zzbf(str3);
        if (zzbf != null) {
            if (!TextUtils.isEmpty(zzbf.zzag())) {
                Boolean zzc = zzc(zzbf);
                if (zzc == null || zzc.booleanValue()) {
                    return new zzeb(str3, zzbf.getGmpAppId(), zzbf.zzag(), zzbf.zzgu(), zzbf.zzgv(), zzbf.zzgw(), zzbf.zzgx(), null, zzbf.isMeasurementEnabled(), false, zzbf.zzgr(), zzbf.zzhk(), 0, 0, zzbf.zzhl(), zzbf.zzhm(), false);
                }
                zziv = zzjt.zzacv.zzgi().zziv();
                str2 = "App version does not match; dropping. appId";
                zzbp = zzfi.zzbp(str);
                zziv.zzg(str2, zzbp);
                return null;
            }
        }
        zziv = zzjt.zzacv.zzgi().zzjb();
        str2 = "No app data available; dropping";
        zziv.zzg(str2, zzbp);
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.WorkerThread
    private final boolean zzd(java.lang.String r60, long r61) {
        /*
        r59 = this;
        r1 = r59;
        r2 = r59.zzjh();
        r2.beginTransaction();
        r2 = new com.google.android.gms.internal.measurement.zzjt$zza;	 Catch:{ all -> 0x0b8f }
        r3 = 0;
        r2.<init>();	 Catch:{ all -> 0x0b8f }
        r4 = r59.zzjh();	 Catch:{ all -> 0x0b8f }
        r5 = r1.zzasl;	 Catch:{ all -> 0x0b8f }
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r2);	 Catch:{ all -> 0x0b8f }
        r4.zzab();	 Catch:{ all -> 0x0b8f }
        r4.zzch();	 Catch:{ all -> 0x0b8f }
        r7 = -1;
        r9 = 2;
        r10 = 0;
        r11 = 1;
        r15 = r4.getWritableDatabase();	 Catch:{ SQLiteException -> 0x0266, all -> 0x0261 }
        r12 = android.text.TextUtils.isEmpty(r3);	 Catch:{ SQLiteException -> 0x0266, all -> 0x0261 }
        if (r12 == 0) goto L_0x00a1;
    L_0x002d:
        r12 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1));
        if (r12 == 0) goto L_0x004b;
    L_0x0031:
        r12 = new java.lang.String[r9];	 Catch:{ SQLiteException -> 0x0045, all -> 0x0040 }
        r13 = java.lang.String.valueOf(r5);	 Catch:{ SQLiteException -> 0x0045, all -> 0x0040 }
        r12[r10] = r13;	 Catch:{ SQLiteException -> 0x0045, all -> 0x0040 }
        r13 = java.lang.String.valueOf(r61);	 Catch:{ SQLiteException -> 0x0045, all -> 0x0040 }
        r12[r11] = r13;	 Catch:{ SQLiteException -> 0x0045, all -> 0x0040 }
        goto L_0x0053;
    L_0x0040:
        r0 = move-exception;
        r2 = r0;
        r9 = r3;
        goto L_0x0b89;
    L_0x0045:
        r0 = move-exception;
        r9 = r3;
        r12 = r9;
    L_0x0048:
        r3 = r0;
        goto L_0x026a;
    L_0x004b:
        r12 = new java.lang.String[r11];	 Catch:{ SQLiteException -> 0x0266, all -> 0x0261 }
        r13 = java.lang.String.valueOf(r61);	 Catch:{ SQLiteException -> 0x0266, all -> 0x0261 }
        r12[r10] = r13;	 Catch:{ SQLiteException -> 0x0266, all -> 0x0261 }
    L_0x0053:
        r13 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1));
        if (r13 == 0) goto L_0x005a;
    L_0x0057:
        r13 = "rowid <= ? and ";
        goto L_0x005c;
    L_0x005a:
        r13 = "";
    L_0x005c:
        r14 = java.lang.String.valueOf(r13);	 Catch:{ SQLiteException -> 0x0266, all -> 0x0261 }
        r14 = r14.length();	 Catch:{ SQLiteException -> 0x0266, all -> 0x0261 }
        r14 = r14 + 148;
        r3 = new java.lang.StringBuilder;	 Catch:{ SQLiteException -> 0x0266, all -> 0x0261 }
        r3.<init>(r14);	 Catch:{ SQLiteException -> 0x0266, all -> 0x0261 }
        r14 = "select app_id, metadata_fingerprint from raw_events where ";
        r3.append(r14);	 Catch:{ SQLiteException -> 0x0266, all -> 0x0261 }
        r3.append(r13);	 Catch:{ SQLiteException -> 0x0266, all -> 0x0261 }
        r13 = "app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;";
        r3.append(r13);	 Catch:{ SQLiteException -> 0x0266, all -> 0x0261 }
        r3 = r3.toString();	 Catch:{ SQLiteException -> 0x0266, all -> 0x0261 }
        r3 = r15.rawQuery(r3, r12);	 Catch:{ SQLiteException -> 0x0266, all -> 0x0261 }
        r12 = r3.moveToFirst();	 Catch:{ SQLiteException -> 0x025c, all -> 0x0040 }
        if (r12 != 0) goto L_0x008d;
    L_0x0086:
        if (r3 == 0) goto L_0x027e;
    L_0x0088:
        r3.close();	 Catch:{ all -> 0x0b8f }
        goto L_0x027e;
    L_0x008d:
        r12 = r3.getString(r10);	 Catch:{ SQLiteException -> 0x025c, all -> 0x0040 }
        r13 = r3.getString(r11);	 Catch:{ SQLiteException -> 0x009e, all -> 0x0040 }
        r3.close();	 Catch:{ SQLiteException -> 0x009e, all -> 0x0040 }
        r22 = r3;
        r3 = r12;
        r21 = r13;
        goto L_0x00f9;
    L_0x009e:
        r0 = move-exception;
        r9 = r3;
        goto L_0x0048;
    L_0x00a1:
        r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1));
        if (r3 == 0) goto L_0x00b2;
    L_0x00a5:
        r3 = new java.lang.String[r9];	 Catch:{ SQLiteException -> 0x0266, all -> 0x0261 }
        r12 = 0;
        r3[r10] = r12;	 Catch:{ SQLiteException -> 0x0266, all -> 0x0261 }
        r12 = java.lang.String.valueOf(r5);	 Catch:{ SQLiteException -> 0x0266, all -> 0x0261 }
        r3[r11] = r12;	 Catch:{ SQLiteException -> 0x0266, all -> 0x0261 }
        r12 = r3;
        goto L_0x00b7;
    L_0x00b2:
        r3 = 0;
        r12 = new java.lang.String[]{r3};	 Catch:{ SQLiteException -> 0x0266, all -> 0x0261 }
    L_0x00b7:
        r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1));
        if (r3 == 0) goto L_0x00be;
    L_0x00bb:
        r3 = " and rowid <= ?";
        goto L_0x00c0;
    L_0x00be:
        r3 = "";
    L_0x00c0:
        r13 = java.lang.String.valueOf(r3);	 Catch:{ SQLiteException -> 0x0266, all -> 0x0261 }
        r13 = r13.length();	 Catch:{ SQLiteException -> 0x0266, all -> 0x0261 }
        r13 = r13 + 84;
        r14 = new java.lang.StringBuilder;	 Catch:{ SQLiteException -> 0x0266, all -> 0x0261 }
        r14.<init>(r13);	 Catch:{ SQLiteException -> 0x0266, all -> 0x0261 }
        r13 = "select metadata_fingerprint from raw_events where app_id = ?";
        r14.append(r13);	 Catch:{ SQLiteException -> 0x0266, all -> 0x0261 }
        r14.append(r3);	 Catch:{ SQLiteException -> 0x0266, all -> 0x0261 }
        r3 = " order by rowid limit 1;";
        r14.append(r3);	 Catch:{ SQLiteException -> 0x0266, all -> 0x0261 }
        r3 = r14.toString();	 Catch:{ SQLiteException -> 0x0266, all -> 0x0261 }
        r3 = r15.rawQuery(r3, r12);	 Catch:{ SQLiteException -> 0x0266, all -> 0x0261 }
        r12 = r3.moveToFirst();	 Catch:{ SQLiteException -> 0x025c, all -> 0x0040 }
        if (r12 != 0) goto L_0x00ed;
    L_0x00ea:
        if (r3 == 0) goto L_0x027e;
    L_0x00ec:
        goto L_0x0088;
    L_0x00ed:
        r13 = r3.getString(r10);	 Catch:{ SQLiteException -> 0x025c, all -> 0x0040 }
        r3.close();	 Catch:{ SQLiteException -> 0x025c, all -> 0x0040 }
        r22 = r3;
        r21 = r13;
        r3 = 0;
    L_0x00f9:
        r13 = "raw_events_metadata";
        r12 = "metadata";
        r14 = new java.lang.String[]{r12};	 Catch:{ SQLiteException -> 0x0256, all -> 0x0250 }
        r16 = "app_id = ? and metadata_fingerprint = ?";
        r12 = new java.lang.String[r9];	 Catch:{ SQLiteException -> 0x0256, all -> 0x0250 }
        r12[r10] = r3;	 Catch:{ SQLiteException -> 0x0256, all -> 0x0250 }
        r12[r11] = r21;	 Catch:{ SQLiteException -> 0x0256, all -> 0x0250 }
        r17 = 0;
        r18 = 0;
        r19 = "rowid";
        r20 = "2";
        r23 = r12;
        r12 = r15;
        r24 = r15;
        r15 = r16;
        r16 = r23;
        r15 = r12.query(r13, r14, r15, r16, r17, r18, r19, r20);	 Catch:{ SQLiteException -> 0x0256, all -> 0x0250 }
        r12 = r15.moveToFirst();	 Catch:{ SQLiteException -> 0x024b, all -> 0x0247 }
        if (r12 != 0) goto L_0x0146;
    L_0x0124:
        r5 = r4.zzgi();	 Catch:{ SQLiteException -> 0x0141, all -> 0x013c }
        r5 = r5.zziv();	 Catch:{ SQLiteException -> 0x0141, all -> 0x013c }
        r6 = "Raw event metadata record is missing. appId";
        r12 = com.google.android.gms.internal.measurement.zzfi.zzbp(r3);	 Catch:{ SQLiteException -> 0x0141, all -> 0x013c }
        r5.zzg(r6, r12);	 Catch:{ SQLiteException -> 0x0141, all -> 0x013c }
        if (r15 == 0) goto L_0x027e;
    L_0x0137:
        r15.close();	 Catch:{ all -> 0x0b8f }
        goto L_0x027e;
    L_0x013c:
        r0 = move-exception;
        r2 = r0;
        r9 = r15;
        goto L_0x0b89;
    L_0x0141:
        r0 = move-exception;
        r12 = r3;
        r9 = r15;
        goto L_0x0048;
    L_0x0146:
        r12 = r15.getBlob(r10);	 Catch:{ SQLiteException -> 0x024b, all -> 0x0247 }
        r13 = r12.length;	 Catch:{ SQLiteException -> 0x024b, all -> 0x0247 }
        r12 = com.google.android.gms.internal.measurement.zzaca.zza(r12, r10, r13);	 Catch:{ SQLiteException -> 0x024b, all -> 0x0247 }
        r13 = new com.google.android.gms.internal.measurement.zzku;	 Catch:{ SQLiteException -> 0x024b, all -> 0x0247 }
        r13.<init>();	 Catch:{ SQLiteException -> 0x024b, all -> 0x0247 }
        r13.zzb(r12);	 Catch:{ IOException -> 0x022c }
        r12 = r15.moveToNext();	 Catch:{ SQLiteException -> 0x024b, all -> 0x0247 }
        if (r12 == 0) goto L_0x016e;
    L_0x015d:
        r12 = r4.zzgi();	 Catch:{ SQLiteException -> 0x0141, all -> 0x013c }
        r12 = r12.zziy();	 Catch:{ SQLiteException -> 0x0141, all -> 0x013c }
        r14 = "Get multiple raw event metadata records, expected one. appId";
        r9 = com.google.android.gms.internal.measurement.zzfi.zzbp(r3);	 Catch:{ SQLiteException -> 0x0141, all -> 0x013c }
        r12.zzg(r14, r9);	 Catch:{ SQLiteException -> 0x0141, all -> 0x013c }
    L_0x016e:
        r15.close();	 Catch:{ SQLiteException -> 0x024b, all -> 0x0247 }
        r2.zzb(r13);	 Catch:{ SQLiteException -> 0x024b, all -> 0x0247 }
        r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1));
        r14 = 3;
        if (r9 == 0) goto L_0x018c;
    L_0x0179:
        r9 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?";
        r12 = new java.lang.String[r14];	 Catch:{ SQLiteException -> 0x0141, all -> 0x013c }
        r12[r10] = r3;	 Catch:{ SQLiteException -> 0x0141, all -> 0x013c }
        r12[r11] = r21;	 Catch:{ SQLiteException -> 0x0141, all -> 0x013c }
        r5 = java.lang.String.valueOf(r5);	 Catch:{ SQLiteException -> 0x0141, all -> 0x013c }
        r6 = 2;
        r12[r6] = r5;	 Catch:{ SQLiteException -> 0x0141, all -> 0x013c }
        r5 = r9;
        r16 = r12;
        goto L_0x0197;
    L_0x018c:
        r5 = "app_id = ? and metadata_fingerprint = ?";
        r6 = 2;
        r9 = new java.lang.String[r6];	 Catch:{ SQLiteException -> 0x024b, all -> 0x0247 }
        r9[r10] = r3;	 Catch:{ SQLiteException -> 0x024b, all -> 0x0247 }
        r9[r11] = r21;	 Catch:{ SQLiteException -> 0x024b, all -> 0x0247 }
        r16 = r9;
    L_0x0197:
        r13 = "raw_events";
        r6 = "rowid";
        r9 = "name";
        r12 = "timestamp";
        r14 = "data";
        r14 = new java.lang.String[]{r6, r9, r12, r14};	 Catch:{ SQLiteException -> 0x024b, all -> 0x0247 }
        r17 = 0;
        r18 = 0;
        r19 = "rowid";
        r20 = 0;
        r12 = r24;
        r6 = 3;
        r9 = r15;
        r15 = r5;
        r5 = r12.query(r13, r14, r15, r16, r17, r18, r19, r20);	 Catch:{ SQLiteException -> 0x0245 }
        r9 = r5.moveToFirst();	 Catch:{ SQLiteException -> 0x0227, all -> 0x0222 }
        if (r9 != 0) goto L_0x01d4;
    L_0x01bc:
        r6 = r4.zzgi();	 Catch:{ SQLiteException -> 0x0227, all -> 0x0222 }
        r6 = r6.zziy();	 Catch:{ SQLiteException -> 0x0227, all -> 0x0222 }
        r9 = "Raw event data disappeared while in transaction. appId";
        r12 = com.google.android.gms.internal.measurement.zzfi.zzbp(r3);	 Catch:{ SQLiteException -> 0x0227, all -> 0x0222 }
        r6.zzg(r9, r12);	 Catch:{ SQLiteException -> 0x0227, all -> 0x0222 }
        if (r5 == 0) goto L_0x027e;
    L_0x01cf:
        r5.close();	 Catch:{ all -> 0x0b8f }
        goto L_0x027e;
    L_0x01d4:
        r12 = r5.getLong(r10);	 Catch:{ SQLiteException -> 0x0227, all -> 0x0222 }
        r9 = r5.getBlob(r6);	 Catch:{ SQLiteException -> 0x0227, all -> 0x0222 }
        r14 = r9.length;	 Catch:{ SQLiteException -> 0x0227, all -> 0x0222 }
        r9 = com.google.android.gms.internal.measurement.zzaca.zza(r9, r10, r14);	 Catch:{ SQLiteException -> 0x0227, all -> 0x0222 }
        r14 = new com.google.android.gms.internal.measurement.zzkr;	 Catch:{ SQLiteException -> 0x0227, all -> 0x0222 }
        r14.<init>();	 Catch:{ SQLiteException -> 0x0227, all -> 0x0222 }
        r14.zzb(r9);	 Catch:{ IOException -> 0x0203 }
        r9 = r5.getString(r11);	 Catch:{ SQLiteException -> 0x0227, all -> 0x0222 }
        r14.name = r9;	 Catch:{ SQLiteException -> 0x0227, all -> 0x0222 }
        r9 = 2;
        r6 = r5.getLong(r9);	 Catch:{ SQLiteException -> 0x0227, all -> 0x0222 }
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ SQLiteException -> 0x0227, all -> 0x0222 }
        r14.zzavb = r6;	 Catch:{ SQLiteException -> 0x0227, all -> 0x0222 }
        r6 = r2.zza(r12, r14);	 Catch:{ SQLiteException -> 0x0227, all -> 0x0222 }
        if (r6 != 0) goto L_0x0215;
    L_0x0200:
        if (r5 == 0) goto L_0x027e;
    L_0x0202:
        goto L_0x01cf;
    L_0x0203:
        r0 = move-exception;
        r6 = r4.zzgi();	 Catch:{ SQLiteException -> 0x0227, all -> 0x0222 }
        r6 = r6.zziv();	 Catch:{ SQLiteException -> 0x0227, all -> 0x0222 }
        r7 = "Data loss. Failed to merge raw event. appId";
        r8 = com.google.android.gms.internal.measurement.zzfi.zzbp(r3);	 Catch:{ SQLiteException -> 0x0227, all -> 0x0222 }
        r6.zze(r7, r8, r0);	 Catch:{ SQLiteException -> 0x0227, all -> 0x0222 }
    L_0x0215:
        r6 = r5.moveToNext();	 Catch:{ SQLiteException -> 0x0227, all -> 0x0222 }
        if (r6 != 0) goto L_0x021e;
    L_0x021b:
        if (r5 == 0) goto L_0x027e;
    L_0x021d:
        goto L_0x01cf;
    L_0x021e:
        r6 = 3;
        r7 = -1;
        goto L_0x01d4;
    L_0x0222:
        r0 = move-exception;
        r2 = r0;
        r9 = r5;
        goto L_0x0b89;
    L_0x0227:
        r0 = move-exception;
        r12 = r3;
        r9 = r5;
        goto L_0x0048;
    L_0x022c:
        r0 = move-exception;
        r9 = r15;
        r5 = r4.zzgi();	 Catch:{ SQLiteException -> 0x0245 }
        r5 = r5.zziv();	 Catch:{ SQLiteException -> 0x0245 }
        r6 = "Data loss. Failed to merge raw event metadata. appId";
        r7 = com.google.android.gms.internal.measurement.zzfi.zzbp(r3);	 Catch:{ SQLiteException -> 0x0245 }
        r5.zze(r6, r7, r0);	 Catch:{ SQLiteException -> 0x0245 }
        if (r9 == 0) goto L_0x027e;
    L_0x0241:
        r9.close();	 Catch:{ all -> 0x0b8f }
        goto L_0x027e;
    L_0x0245:
        r0 = move-exception;
        goto L_0x024d;
    L_0x0247:
        r0 = move-exception;
        r9 = r15;
        goto L_0x0b88;
    L_0x024b:
        r0 = move-exception;
        r9 = r15;
    L_0x024d:
        r12 = r3;
        goto L_0x0048;
    L_0x0250:
        r0 = move-exception;
        r2 = r0;
        r9 = r22;
        goto L_0x0b89;
    L_0x0256:
        r0 = move-exception;
        r12 = r3;
        r9 = r22;
        goto L_0x0048;
    L_0x025c:
        r0 = move-exception;
        r9 = r3;
        r12 = 0;
        goto L_0x0048;
    L_0x0261:
        r0 = move-exception;
        r2 = r0;
        r9 = 0;
        goto L_0x0b89;
    L_0x0266:
        r0 = move-exception;
        r3 = r0;
        r9 = 0;
        r12 = 0;
    L_0x026a:
        r4 = r4.zzgi();	 Catch:{ all -> 0x0b87 }
        r4 = r4.zziv();	 Catch:{ all -> 0x0b87 }
        r5 = "Data loss. Error selecting raw event. appId";
        r6 = com.google.android.gms.internal.measurement.zzfi.zzbp(r12);	 Catch:{ all -> 0x0b87 }
        r4.zze(r5, r6, r3);	 Catch:{ all -> 0x0b87 }
        if (r9 == 0) goto L_0x027e;
    L_0x027d:
        goto L_0x0241;
    L_0x027e:
        r3 = r2.zzasr;	 Catch:{ all -> 0x0b8f }
        if (r3 == 0) goto L_0x028d;
    L_0x0282:
        r3 = r2.zzasr;	 Catch:{ all -> 0x0b8f }
        r3 = r3.isEmpty();	 Catch:{ all -> 0x0b8f }
        if (r3 == 0) goto L_0x028b;
    L_0x028a:
        goto L_0x028d;
    L_0x028b:
        r3 = 0;
        goto L_0x028e;
    L_0x028d:
        r3 = 1;
    L_0x028e:
        if (r3 != 0) goto L_0x0b77;
    L_0x0290:
        r3 = r2.zzasp;	 Catch:{ all -> 0x0b8f }
        r4 = r2.zzasr;	 Catch:{ all -> 0x0b8f }
        r4 = r4.size();	 Catch:{ all -> 0x0b8f }
        r4 = new com.google.android.gms.internal.measurement.zzkr[r4];	 Catch:{ all -> 0x0b8f }
        r3.zzavi = r4;	 Catch:{ all -> 0x0b8f }
        r4 = r1.zzacv;	 Catch:{ all -> 0x0b8f }
        r4 = r4.zzgk();	 Catch:{ all -> 0x0b8f }
        r5 = r3.zzth;	 Catch:{ all -> 0x0b8f }
        r4 = r4.zzaw(r5);	 Catch:{ all -> 0x0b8f }
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r12 = 0;
    L_0x02ad:
        r14 = r2.zzasr;	 Catch:{ all -> 0x0b8f }
        r14 = r14.size();	 Catch:{ all -> 0x0b8f }
        if (r7 >= r14) goto L_0x05f7;
    L_0x02b5:
        r14 = r2.zzasr;	 Catch:{ all -> 0x0b8f }
        r14 = r14.get(r7);	 Catch:{ all -> 0x0b8f }
        r14 = (com.google.android.gms.internal.measurement.zzkr) r14;	 Catch:{ all -> 0x0b8f }
        r15 = r59.zzky();	 Catch:{ all -> 0x0b8f }
        r11 = r2.zzasp;	 Catch:{ all -> 0x0b8f }
        r11 = r11.zzth;	 Catch:{ all -> 0x0b8f }
        r5 = r14.name;	 Catch:{ all -> 0x0b8f }
        r5 = r15.zzn(r11, r5);	 Catch:{ all -> 0x0b8f }
        if (r5 == 0) goto L_0x0339;
    L_0x02cd:
        r5 = r1.zzacv;	 Catch:{ all -> 0x0b8f }
        r5 = r5.zzgi();	 Catch:{ all -> 0x0b8f }
        r5 = r5.zziy();	 Catch:{ all -> 0x0b8f }
        r6 = "Dropping blacklisted raw event. appId";
        r11 = r2.zzasp;	 Catch:{ all -> 0x0b8f }
        r11 = r11.zzth;	 Catch:{ all -> 0x0b8f }
        r11 = com.google.android.gms.internal.measurement.zzfi.zzbp(r11);	 Catch:{ all -> 0x0b8f }
        r15 = r1.zzacv;	 Catch:{ all -> 0x0b8f }
        r15 = r15.zzgf();	 Catch:{ all -> 0x0b8f }
        r10 = r14.name;	 Catch:{ all -> 0x0b8f }
        r10 = r15.zzbm(r10);	 Catch:{ all -> 0x0b8f }
        r5.zze(r6, r11, r10);	 Catch:{ all -> 0x0b8f }
        r5 = r59.zzky();	 Catch:{ all -> 0x0b8f }
        r6 = r2.zzasp;	 Catch:{ all -> 0x0b8f }
        r6 = r6.zzth;	 Catch:{ all -> 0x0b8f }
        r5 = r5.zzcb(r6);	 Catch:{ all -> 0x0b8f }
        if (r5 != 0) goto L_0x030f;
    L_0x02fe:
        r5 = r59.zzky();	 Catch:{ all -> 0x0b8f }
        r6 = r2.zzasp;	 Catch:{ all -> 0x0b8f }
        r6 = r6.zzth;	 Catch:{ all -> 0x0b8f }
        r5 = r5.zzcc(r6);	 Catch:{ all -> 0x0b8f }
        if (r5 == 0) goto L_0x030d;
    L_0x030c:
        goto L_0x030f;
    L_0x030d:
        r5 = 0;
        goto L_0x0310;
    L_0x030f:
        r5 = 1;
    L_0x0310:
        if (r5 != 0) goto L_0x0335;
    L_0x0312:
        r5 = "_err";
        r6 = r14.name;	 Catch:{ all -> 0x0b8f }
        r5 = r5.equals(r6);	 Catch:{ all -> 0x0b8f }
        if (r5 != 0) goto L_0x0335;
    L_0x031c:
        r5 = r1.zzacv;	 Catch:{ all -> 0x0b8f }
        r15 = r5.zzgg();	 Catch:{ all -> 0x0b8f }
        r5 = r2.zzasp;	 Catch:{ all -> 0x0b8f }
        r5 = r5.zzth;	 Catch:{ all -> 0x0b8f }
        r17 = 11;
        r18 = "_ev";
        r6 = r14.name;	 Catch:{ all -> 0x0b8f }
        r20 = 0;
        r16 = r5;
        r19 = r6;
        r15.zza(r16, r17, r18, r19, r20);	 Catch:{ all -> 0x0b8f }
    L_0x0335:
        r28 = r7;
        goto L_0x05f1;
    L_0x0339:
        r5 = r59.zzky();	 Catch:{ all -> 0x0b8f }
        r6 = r2.zzasp;	 Catch:{ all -> 0x0b8f }
        r6 = r6.zzth;	 Catch:{ all -> 0x0b8f }
        r10 = r14.name;	 Catch:{ all -> 0x0b8f }
        r5 = r5.zzo(r6, r10);	 Catch:{ all -> 0x0b8f }
        if (r5 != 0) goto L_0x0390;
    L_0x0349:
        r59.zzjf();	 Catch:{ all -> 0x0b8f }
        r6 = r14.name;	 Catch:{ all -> 0x0b8f }
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r6);	 Catch:{ all -> 0x0b8f }
        r10 = -1;
        r11 = r6.hashCode();	 Catch:{ all -> 0x0b8f }
        r15 = 94660; // 0x171c4 float:1.32647E-40 double:4.67683E-319;
        if (r11 == r15) goto L_0x037a;
    L_0x035b:
        r15 = 95025; // 0x17331 float:1.33158E-40 double:4.69486E-319;
        if (r11 == r15) goto L_0x0370;
    L_0x0360:
        r15 = 95027; // 0x17333 float:1.33161E-40 double:4.69496E-319;
        if (r11 == r15) goto L_0x0366;
    L_0x0365:
        goto L_0x0383;
    L_0x0366:
        r11 = "_ui";
        r6 = r6.equals(r11);	 Catch:{ all -> 0x0b8f }
        if (r6 == 0) goto L_0x0383;
    L_0x036e:
        r10 = 1;
        goto L_0x0383;
    L_0x0370:
        r11 = "_ug";
        r6 = r6.equals(r11);	 Catch:{ all -> 0x0b8f }
        if (r6 == 0) goto L_0x0383;
    L_0x0378:
        r10 = 2;
        goto L_0x0383;
    L_0x037a:
        r11 = "_in";
        r6 = r6.equals(r11);	 Catch:{ all -> 0x0b8f }
        if (r6 == 0) goto L_0x0383;
    L_0x0382:
        r10 = 0;
    L_0x0383:
        switch(r10) {
            case 0: goto L_0x0388;
            case 1: goto L_0x0388;
            case 2: goto L_0x0388;
            default: goto L_0x0386;
        };	 Catch:{ all -> 0x0b8f }
    L_0x0386:
        r6 = 0;
        goto L_0x0389;
    L_0x0388:
        r6 = 1;
    L_0x0389:
        if (r6 == 0) goto L_0x038c;
    L_0x038b:
        goto L_0x0390;
    L_0x038c:
        r28 = r7;
        goto L_0x0590;
    L_0x0390:
        r6 = r14.zzava;	 Catch:{ all -> 0x0b8f }
        if (r6 != 0) goto L_0x0399;
    L_0x0394:
        r6 = 0;
        r10 = new com.google.android.gms.internal.measurement.zzks[r6];	 Catch:{ all -> 0x0b8f }
        r14.zzava = r10;	 Catch:{ all -> 0x0b8f }
    L_0x0399:
        r6 = r14.zzava;	 Catch:{ all -> 0x0b8f }
        r10 = r6.length;	 Catch:{ all -> 0x0b8f }
        r11 = 0;
        r15 = 0;
        r16 = 0;
    L_0x03a0:
        if (r11 >= r10) goto L_0x03e1;
    L_0x03a2:
        r25 = r10;
        r10 = r6[r11];	 Catch:{ all -> 0x0b8f }
        r26 = r6;
        r6 = "_c";
        r27 = r8;
        r8 = r10.name;	 Catch:{ all -> 0x0b8f }
        r6 = r6.equals(r8);	 Catch:{ all -> 0x0b8f }
        if (r6 == 0) goto L_0x03c0;
    L_0x03b4:
        r28 = r7;
        r6 = 1;
        r8 = java.lang.Long.valueOf(r6);	 Catch:{ all -> 0x0b8f }
        r10.zzave = r8;	 Catch:{ all -> 0x0b8f }
        r15 = 1;
        goto L_0x03d6;
    L_0x03c0:
        r28 = r7;
        r6 = "_r";
        r7 = r10.name;	 Catch:{ all -> 0x0b8f }
        r6 = r6.equals(r7);	 Catch:{ all -> 0x0b8f }
        if (r6 == 0) goto L_0x03d6;
    L_0x03cc:
        r6 = 1;
        r8 = java.lang.Long.valueOf(r6);	 Catch:{ all -> 0x0b8f }
        r10.zzave = r8;	 Catch:{ all -> 0x0b8f }
        r16 = 1;
    L_0x03d6:
        r11 = r11 + 1;
        r10 = r25;
        r6 = r26;
        r8 = r27;
        r7 = r28;
        goto L_0x03a0;
    L_0x03e1:
        r28 = r7;
        r27 = r8;
        if (r15 != 0) goto L_0x0429;
    L_0x03e7:
        if (r5 == 0) goto L_0x0429;
    L_0x03e9:
        r6 = r1.zzacv;	 Catch:{ all -> 0x0b8f }
        r6 = r6.zzgi();	 Catch:{ all -> 0x0b8f }
        r6 = r6.zzjc();	 Catch:{ all -> 0x0b8f }
        r7 = "Marking event as conversion";
        r8 = r1.zzacv;	 Catch:{ all -> 0x0b8f }
        r8 = r8.zzgf();	 Catch:{ all -> 0x0b8f }
        r10 = r14.name;	 Catch:{ all -> 0x0b8f }
        r8 = r8.zzbm(r10);	 Catch:{ all -> 0x0b8f }
        r6.zzg(r7, r8);	 Catch:{ all -> 0x0b8f }
        r6 = r14.zzava;	 Catch:{ all -> 0x0b8f }
        r7 = r14.zzava;	 Catch:{ all -> 0x0b8f }
        r7 = r7.length;	 Catch:{ all -> 0x0b8f }
        r8 = 1;
        r7 = r7 + r8;
        r6 = java.util.Arrays.copyOf(r6, r7);	 Catch:{ all -> 0x0b8f }
        r6 = (com.google.android.gms.internal.measurement.zzks[]) r6;	 Catch:{ all -> 0x0b8f }
        r7 = new com.google.android.gms.internal.measurement.zzks;	 Catch:{ all -> 0x0b8f }
        r7.<init>();	 Catch:{ all -> 0x0b8f }
        r8 = "_c";
        r7.name = r8;	 Catch:{ all -> 0x0b8f }
        r10 = 1;
        r8 = java.lang.Long.valueOf(r10);	 Catch:{ all -> 0x0b8f }
        r7.zzave = r8;	 Catch:{ all -> 0x0b8f }
        r8 = r6.length;	 Catch:{ all -> 0x0b8f }
        r10 = 1;
        r8 = r8 - r10;
        r6[r8] = r7;	 Catch:{ all -> 0x0b8f }
        r14.zzava = r6;	 Catch:{ all -> 0x0b8f }
    L_0x0429:
        if (r16 != 0) goto L_0x046b;
    L_0x042b:
        r6 = r1.zzacv;	 Catch:{ all -> 0x0b8f }
        r6 = r6.zzgi();	 Catch:{ all -> 0x0b8f }
        r6 = r6.zzjc();	 Catch:{ all -> 0x0b8f }
        r7 = "Marking event as real-time";
        r8 = r1.zzacv;	 Catch:{ all -> 0x0b8f }
        r8 = r8.zzgf();	 Catch:{ all -> 0x0b8f }
        r10 = r14.name;	 Catch:{ all -> 0x0b8f }
        r8 = r8.zzbm(r10);	 Catch:{ all -> 0x0b8f }
        r6.zzg(r7, r8);	 Catch:{ all -> 0x0b8f }
        r6 = r14.zzava;	 Catch:{ all -> 0x0b8f }
        r7 = r14.zzava;	 Catch:{ all -> 0x0b8f }
        r7 = r7.length;	 Catch:{ all -> 0x0b8f }
        r8 = 1;
        r7 = r7 + r8;
        r6 = java.util.Arrays.copyOf(r6, r7);	 Catch:{ all -> 0x0b8f }
        r6 = (com.google.android.gms.internal.measurement.zzks[]) r6;	 Catch:{ all -> 0x0b8f }
        r7 = new com.google.android.gms.internal.measurement.zzks;	 Catch:{ all -> 0x0b8f }
        r7.<init>();	 Catch:{ all -> 0x0b8f }
        r8 = "_r";
        r7.name = r8;	 Catch:{ all -> 0x0b8f }
        r10 = 1;
        r8 = java.lang.Long.valueOf(r10);	 Catch:{ all -> 0x0b8f }
        r7.zzave = r8;	 Catch:{ all -> 0x0b8f }
        r8 = r6.length;	 Catch:{ all -> 0x0b8f }
        r10 = 1;
        r8 = r8 - r10;
        r6[r8] = r7;	 Catch:{ all -> 0x0b8f }
        r14.zzava = r6;	 Catch:{ all -> 0x0b8f }
    L_0x046b:
        r29 = r59.zzjh();	 Catch:{ all -> 0x0b8f }
        r30 = r59.zzld();	 Catch:{ all -> 0x0b8f }
        r6 = r2.zzasp;	 Catch:{ all -> 0x0b8f }
        r6 = r6.zzth;	 Catch:{ all -> 0x0b8f }
        r33 = 0;
        r34 = 0;
        r35 = 0;
        r36 = 0;
        r37 = 1;
        r32 = r6;
        r6 = r29.zza(r30, r32, r33, r34, r35, r36, r37);	 Catch:{ all -> 0x0b8f }
        r6 = r6.zzagx;	 Catch:{ all -> 0x0b8f }
        r8 = r1.zzacv;	 Catch:{ all -> 0x0b8f }
        r8 = r8.zzgk();	 Catch:{ all -> 0x0b8f }
        r10 = r2.zzasp;	 Catch:{ all -> 0x0b8f }
        r10 = r10.zzth;	 Catch:{ all -> 0x0b8f }
        r8 = r8.zzas(r10);	 Catch:{ all -> 0x0b8f }
        r10 = (long) r8;	 Catch:{ all -> 0x0b8f }
        r8 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1));
        if (r8 <= 0) goto L_0x04d4;
    L_0x049c:
        r6 = 0;
    L_0x049d:
        r7 = r14.zzava;	 Catch:{ all -> 0x0b8f }
        r7 = r7.length;	 Catch:{ all -> 0x0b8f }
        if (r6 >= r7) goto L_0x04d1;
    L_0x04a2:
        r7 = "_r";
        r8 = r14.zzava;	 Catch:{ all -> 0x0b8f }
        r8 = r8[r6];	 Catch:{ all -> 0x0b8f }
        r8 = r8.name;	 Catch:{ all -> 0x0b8f }
        r7 = r7.equals(r8);	 Catch:{ all -> 0x0b8f }
        if (r7 == 0) goto L_0x04ce;
    L_0x04b0:
        r7 = r14.zzava;	 Catch:{ all -> 0x0b8f }
        r7 = r7.length;	 Catch:{ all -> 0x0b8f }
        r8 = 1;
        r7 = r7 - r8;
        r7 = new com.google.android.gms.internal.measurement.zzks[r7];	 Catch:{ all -> 0x0b8f }
        if (r6 <= 0) goto L_0x04bf;
    L_0x04b9:
        r8 = r14.zzava;	 Catch:{ all -> 0x0b8f }
        r10 = 0;
        java.lang.System.arraycopy(r8, r10, r7, r10, r6);	 Catch:{ all -> 0x0b8f }
    L_0x04bf:
        r8 = r7.length;	 Catch:{ all -> 0x0b8f }
        if (r6 >= r8) goto L_0x04cb;
    L_0x04c2:
        r8 = r14.zzava;	 Catch:{ all -> 0x0b8f }
        r10 = r6 + 1;
        r11 = r7.length;	 Catch:{ all -> 0x0b8f }
        r11 = r11 - r6;
        java.lang.System.arraycopy(r8, r10, r7, r6, r11);	 Catch:{ all -> 0x0b8f }
    L_0x04cb:
        r14.zzava = r7;	 Catch:{ all -> 0x0b8f }
        goto L_0x04d1;
    L_0x04ce:
        r6 = r6 + 1;
        goto L_0x049d;
    L_0x04d1:
        r8 = r27;
        goto L_0x04d5;
    L_0x04d4:
        r8 = 1;
    L_0x04d5:
        r6 = r14.name;	 Catch:{ all -> 0x0b8f }
        r6 = com.google.android.gms.internal.measurement.zzkd.zzcg(r6);	 Catch:{ all -> 0x0b8f }
        if (r6 == 0) goto L_0x0590;
    L_0x04dd:
        if (r5 == 0) goto L_0x0590;
    L_0x04df:
        r29 = r59.zzjh();	 Catch:{ all -> 0x0b8f }
        r30 = r59.zzld();	 Catch:{ all -> 0x0b8f }
        r5 = r2.zzasp;	 Catch:{ all -> 0x0b8f }
        r5 = r5.zzth;	 Catch:{ all -> 0x0b8f }
        r33 = 0;
        r34 = 0;
        r35 = 1;
        r36 = 0;
        r37 = 0;
        r32 = r5;
        r5 = r29.zza(r30, r32, r33, r34, r35, r36, r37);	 Catch:{ all -> 0x0b8f }
        r5 = r5.zzagv;	 Catch:{ all -> 0x0b8f }
        r7 = r1.zzacv;	 Catch:{ all -> 0x0b8f }
        r7 = r7.zzgk();	 Catch:{ all -> 0x0b8f }
        r10 = r2.zzasp;	 Catch:{ all -> 0x0b8f }
        r10 = r10.zzth;	 Catch:{ all -> 0x0b8f }
        r11 = com.google.android.gms.internal.measurement.zzez.zzair;	 Catch:{ all -> 0x0b8f }
        r7 = r7.zzb(r10, r11);	 Catch:{ all -> 0x0b8f }
        r10 = (long) r7;	 Catch:{ all -> 0x0b8f }
        r7 = (r5 > r10 ? 1 : (r5 == r10 ? 0 : -1));
        if (r7 <= 0) goto L_0x0590;
    L_0x0512:
        r5 = r1.zzacv;	 Catch:{ all -> 0x0b8f }
        r5 = r5.zzgi();	 Catch:{ all -> 0x0b8f }
        r5 = r5.zziy();	 Catch:{ all -> 0x0b8f }
        r6 = "Too many conversions. Not logging as conversion. appId";
        r7 = r2.zzasp;	 Catch:{ all -> 0x0b8f }
        r7 = r7.zzth;	 Catch:{ all -> 0x0b8f }
        r7 = com.google.android.gms.internal.measurement.zzfi.zzbp(r7);	 Catch:{ all -> 0x0b8f }
        r5.zzg(r6, r7);	 Catch:{ all -> 0x0b8f }
        r5 = r14.zzava;	 Catch:{ all -> 0x0b8f }
        r6 = r5.length;	 Catch:{ all -> 0x0b8f }
        r7 = 0;
        r10 = 0;
        r11 = 0;
    L_0x052f:
        if (r7 >= r6) goto L_0x0555;
    L_0x0531:
        r15 = r5[r7];	 Catch:{ all -> 0x0b8f }
        r38 = r5;
        r5 = "_c";
        r39 = r6;
        r6 = r15.name;	 Catch:{ all -> 0x0b8f }
        r5 = r5.equals(r6);	 Catch:{ all -> 0x0b8f }
        if (r5 == 0) goto L_0x0543;
    L_0x0541:
        r11 = r15;
        goto L_0x054e;
    L_0x0543:
        r5 = "_err";
        r6 = r15.name;	 Catch:{ all -> 0x0b8f }
        r5 = r5.equals(r6);	 Catch:{ all -> 0x0b8f }
        if (r5 == 0) goto L_0x054e;
    L_0x054d:
        r10 = 1;
    L_0x054e:
        r7 = r7 + 1;
        r5 = r38;
        r6 = r39;
        goto L_0x052f;
    L_0x0555:
        if (r10 == 0) goto L_0x056a;
    L_0x0557:
        if (r11 == 0) goto L_0x056a;
    L_0x0559:
        r5 = r14.zzava;	 Catch:{ all -> 0x0b8f }
        r6 = 1;
        r7 = new com.google.android.gms.internal.measurement.zzks[r6];	 Catch:{ all -> 0x0b8f }
        r6 = 0;
        r7[r6] = r11;	 Catch:{ all -> 0x0b8f }
        r5 = com.google.android.gms.common.util.ArrayUtils.removeAll(r5, r7);	 Catch:{ all -> 0x0b8f }
        r5 = (com.google.android.gms.internal.measurement.zzks[]) r5;	 Catch:{ all -> 0x0b8f }
        r14.zzava = r5;	 Catch:{ all -> 0x0b8f }
        goto L_0x0590;
    L_0x056a:
        if (r11 == 0) goto L_0x0579;
    L_0x056c:
        r5 = "_err";
        r11.name = r5;	 Catch:{ all -> 0x0b8f }
        r5 = 10;
        r5 = java.lang.Long.valueOf(r5);	 Catch:{ all -> 0x0b8f }
        r11.zzave = r5;	 Catch:{ all -> 0x0b8f }
        goto L_0x0590;
    L_0x0579:
        r5 = r1.zzacv;	 Catch:{ all -> 0x0b8f }
        r5 = r5.zzgi();	 Catch:{ all -> 0x0b8f }
        r5 = r5.zziv();	 Catch:{ all -> 0x0b8f }
        r6 = "Did not find conversion parameter. appId";
        r7 = r2.zzasp;	 Catch:{ all -> 0x0b8f }
        r7 = r7.zzth;	 Catch:{ all -> 0x0b8f }
        r7 = com.google.android.gms.internal.measurement.zzfi.zzbp(r7);	 Catch:{ all -> 0x0b8f }
        r5.zzg(r6, r7);	 Catch:{ all -> 0x0b8f }
    L_0x0590:
        if (r4 == 0) goto L_0x05e8;
    L_0x0592:
        r5 = "_e";
        r6 = r14.name;	 Catch:{ all -> 0x0b8f }
        r5 = r5.equals(r6);	 Catch:{ all -> 0x0b8f }
        if (r5 == 0) goto L_0x05e8;
    L_0x059c:
        r5 = r14.zzava;	 Catch:{ all -> 0x0b8f }
        if (r5 == 0) goto L_0x05d3;
    L_0x05a0:
        r5 = r14.zzava;	 Catch:{ all -> 0x0b8f }
        r5 = r5.length;	 Catch:{ all -> 0x0b8f }
        if (r5 != 0) goto L_0x05a6;
    L_0x05a5:
        goto L_0x05d3;
    L_0x05a6:
        r59.zzjf();	 Catch:{ all -> 0x0b8f }
        r5 = "_et";
        r5 = com.google.android.gms.internal.measurement.zzjz.zzb(r14, r5);	 Catch:{ all -> 0x0b8f }
        r5 = (java.lang.Long) r5;	 Catch:{ all -> 0x0b8f }
        if (r5 != 0) goto L_0x05cb;
    L_0x05b3:
        r5 = r1.zzacv;	 Catch:{ all -> 0x0b8f }
        r5 = r5.zzgi();	 Catch:{ all -> 0x0b8f }
        r5 = r5.zziy();	 Catch:{ all -> 0x0b8f }
        r6 = "Engagement event does not include duration. appId";
        r7 = r2.zzasp;	 Catch:{ all -> 0x0b8f }
        r7 = r7.zzth;	 Catch:{ all -> 0x0b8f }
        r7 = com.google.android.gms.internal.measurement.zzfi.zzbp(r7);	 Catch:{ all -> 0x0b8f }
    L_0x05c7:
        r5.zzg(r6, r7);	 Catch:{ all -> 0x0b8f }
        goto L_0x05e8;
    L_0x05cb:
        r5 = r5.longValue();	 Catch:{ all -> 0x0b8f }
        r7 = 0;
        r10 = r12 + r5;
        goto L_0x05e9;
    L_0x05d3:
        r5 = r1.zzacv;	 Catch:{ all -> 0x0b8f }
        r5 = r5.zzgi();	 Catch:{ all -> 0x0b8f }
        r5 = r5.zziy();	 Catch:{ all -> 0x0b8f }
        r6 = "Engagement event does not contain any parameters. appId";
        r7 = r2.zzasp;	 Catch:{ all -> 0x0b8f }
        r7 = r7.zzth;	 Catch:{ all -> 0x0b8f }
        r7 = com.google.android.gms.internal.measurement.zzfi.zzbp(r7);	 Catch:{ all -> 0x0b8f }
        goto L_0x05c7;
    L_0x05e8:
        r10 = r12;
    L_0x05e9:
        r5 = r3.zzavi;	 Catch:{ all -> 0x0b8f }
        r6 = r9 + 1;
        r5[r9] = r14;	 Catch:{ all -> 0x0b8f }
        r9 = r6;
        r12 = r10;
    L_0x05f1:
        r7 = r28 + 1;
        r10 = 0;
        r11 = 1;
        goto L_0x02ad;
    L_0x05f7:
        r27 = r8;
        r5 = r2.zzasr;	 Catch:{ all -> 0x0b8f }
        r5 = r5.size();	 Catch:{ all -> 0x0b8f }
        if (r9 >= r5) goto L_0x060b;
    L_0x0601:
        r5 = r3.zzavi;	 Catch:{ all -> 0x0b8f }
        r5 = java.util.Arrays.copyOf(r5, r9);	 Catch:{ all -> 0x0b8f }
        r5 = (com.google.android.gms.internal.measurement.zzkr[]) r5;	 Catch:{ all -> 0x0b8f }
        r3.zzavi = r5;	 Catch:{ all -> 0x0b8f }
    L_0x060b:
        if (r4 == 0) goto L_0x06db;
    L_0x060d:
        r4 = r59.zzjh();	 Catch:{ all -> 0x0b8f }
        r5 = r3.zzth;	 Catch:{ all -> 0x0b8f }
        r6 = "_lte";
        r4 = r4.zzh(r5, r6);	 Catch:{ all -> 0x0b8f }
        if (r4 == 0) goto L_0x0647;
    L_0x061b:
        r5 = r4.value;	 Catch:{ all -> 0x0b8f }
        if (r5 != 0) goto L_0x0620;
    L_0x061f:
        goto L_0x0647;
    L_0x0620:
        r5 = new com.google.android.gms.internal.measurement.zzkc;	 Catch:{ all -> 0x0b8f }
        r15 = r3.zzth;	 Catch:{ all -> 0x0b8f }
        r16 = "auto";
        r17 = "_lte";
        r6 = r1.zzacv;	 Catch:{ all -> 0x0b8f }
        r6 = r6.zzbt();	 Catch:{ all -> 0x0b8f }
        r18 = r6.currentTimeMillis();	 Catch:{ all -> 0x0b8f }
        r4 = r4.value;	 Catch:{ all -> 0x0b8f }
        r4 = (java.lang.Long) r4;	 Catch:{ all -> 0x0b8f }
        r6 = r4.longValue();	 Catch:{ all -> 0x0b8f }
        r4 = 0;
        r8 = r6 + r12;
        r20 = java.lang.Long.valueOf(r8);	 Catch:{ all -> 0x0b8f }
        r14 = r5;
        r14.<init>(r15, r16, r17, r18, r20);	 Catch:{ all -> 0x0b8f }
        r4 = r5;
        goto L_0x0664;
    L_0x0647:
        r4 = new com.google.android.gms.internal.measurement.zzkc;	 Catch:{ all -> 0x0b8f }
        r5 = r3.zzth;	 Catch:{ all -> 0x0b8f }
        r30 = "auto";
        r31 = "_lte";
        r6 = r1.zzacv;	 Catch:{ all -> 0x0b8f }
        r6 = r6.zzbt();	 Catch:{ all -> 0x0b8f }
        r32 = r6.currentTimeMillis();	 Catch:{ all -> 0x0b8f }
        r34 = java.lang.Long.valueOf(r12);	 Catch:{ all -> 0x0b8f }
        r28 = r4;
        r29 = r5;
        r28.<init>(r29, r30, r31, r32, r34);	 Catch:{ all -> 0x0b8f }
    L_0x0664:
        r5 = new com.google.android.gms.internal.measurement.zzkx;	 Catch:{ all -> 0x0b8f }
        r5.<init>();	 Catch:{ all -> 0x0b8f }
        r6 = "_lte";
        r5.name = r6;	 Catch:{ all -> 0x0b8f }
        r6 = r1.zzacv;	 Catch:{ all -> 0x0b8f }
        r6 = r6.zzbt();	 Catch:{ all -> 0x0b8f }
        r6 = r6.currentTimeMillis();	 Catch:{ all -> 0x0b8f }
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ all -> 0x0b8f }
        r5.zzaws = r6;	 Catch:{ all -> 0x0b8f }
        r6 = r4.value;	 Catch:{ all -> 0x0b8f }
        r6 = (java.lang.Long) r6;	 Catch:{ all -> 0x0b8f }
        r5.zzave = r6;	 Catch:{ all -> 0x0b8f }
        r6 = 0;
    L_0x0684:
        r7 = r3.zzavj;	 Catch:{ all -> 0x0b8f }
        r7 = r7.length;	 Catch:{ all -> 0x0b8f }
        if (r6 >= r7) goto L_0x06a0;
    L_0x0689:
        r7 = "_lte";
        r8 = r3.zzavj;	 Catch:{ all -> 0x0b8f }
        r8 = r8[r6];	 Catch:{ all -> 0x0b8f }
        r8 = r8.name;	 Catch:{ all -> 0x0b8f }
        r7 = r7.equals(r8);	 Catch:{ all -> 0x0b8f }
        if (r7 == 0) goto L_0x069d;
    L_0x0697:
        r7 = r3.zzavj;	 Catch:{ all -> 0x0b8f }
        r7[r6] = r5;	 Catch:{ all -> 0x0b8f }
        r6 = 1;
        goto L_0x06a1;
    L_0x069d:
        r6 = r6 + 1;
        goto L_0x0684;
    L_0x06a0:
        r6 = 0;
    L_0x06a1:
        if (r6 != 0) goto L_0x06bd;
    L_0x06a3:
        r6 = r3.zzavj;	 Catch:{ all -> 0x0b8f }
        r7 = r3.zzavj;	 Catch:{ all -> 0x0b8f }
        r7 = r7.length;	 Catch:{ all -> 0x0b8f }
        r8 = 1;
        r7 = r7 + r8;
        r6 = java.util.Arrays.copyOf(r6, r7);	 Catch:{ all -> 0x0b8f }
        r6 = (com.google.android.gms.internal.measurement.zzkx[]) r6;	 Catch:{ all -> 0x0b8f }
        r3.zzavj = r6;	 Catch:{ all -> 0x0b8f }
        r6 = r3.zzavj;	 Catch:{ all -> 0x0b8f }
        r7 = r2.zzasp;	 Catch:{ all -> 0x0b8f }
        r7 = r7.zzavj;	 Catch:{ all -> 0x0b8f }
        r7 = r7.length;	 Catch:{ all -> 0x0b8f }
        r8 = 1;
        r7 = r7 - r8;
        r6[r7] = r5;	 Catch:{ all -> 0x0b8f }
    L_0x06bd:
        r5 = 0;
        r7 = (r12 > r5 ? 1 : (r12 == r5 ? 0 : -1));
        if (r7 <= 0) goto L_0x06db;
    L_0x06c3:
        r5 = r59.zzjh();	 Catch:{ all -> 0x0b8f }
        r5.zza(r4);	 Catch:{ all -> 0x0b8f }
        r5 = r1.zzacv;	 Catch:{ all -> 0x0b8f }
        r5 = r5.zzgi();	 Catch:{ all -> 0x0b8f }
        r5 = r5.zzjb();	 Catch:{ all -> 0x0b8f }
        r6 = "Updated lifetime engagement user property with value. Value";
        r4 = r4.value;	 Catch:{ all -> 0x0b8f }
        r5.zzg(r6, r4);	 Catch:{ all -> 0x0b8f }
    L_0x06db:
        r4 = r3.zzth;	 Catch:{ all -> 0x0b8f }
        r5 = r3.zzavj;	 Catch:{ all -> 0x0b8f }
        r6 = r3.zzavi;	 Catch:{ all -> 0x0b8f }
        r4 = r1.zza(r4, r5, r6);	 Catch:{ all -> 0x0b8f }
        r3.zzawa = r4;	 Catch:{ all -> 0x0b8f }
        r4 = r1.zzacv;	 Catch:{ all -> 0x0b8f }
        r4 = r4.zzgk();	 Catch:{ all -> 0x0b8f }
        r5 = r2.zzasp;	 Catch:{ all -> 0x0b8f }
        r5 = r5.zzth;	 Catch:{ all -> 0x0b8f }
        r4 = r4.zzav(r5);	 Catch:{ all -> 0x0b8f }
        if (r4 == 0) goto L_0x09b4;
    L_0x06f7:
        r4 = new java.util.HashMap;	 Catch:{ all -> 0x0b8f }
        r4.<init>();	 Catch:{ all -> 0x0b8f }
        r5 = r3.zzavi;	 Catch:{ all -> 0x0b8f }
        r5 = r5.length;	 Catch:{ all -> 0x0b8f }
        r5 = new com.google.android.gms.internal.measurement.zzkr[r5];	 Catch:{ all -> 0x0b8f }
        r6 = r1.zzacv;	 Catch:{ all -> 0x0b8f }
        r6 = r6.zzgg();	 Catch:{ all -> 0x0b8f }
        r6 = r6.zzlo();	 Catch:{ all -> 0x0b8f }
        r7 = r3.zzavi;	 Catch:{ all -> 0x0b8f }
        r8 = r7.length;	 Catch:{ all -> 0x0b8f }
        r9 = 0;
        r10 = 0;
    L_0x0710:
        if (r9 >= r8) goto L_0x0982;
    L_0x0712:
        r11 = r7[r9];	 Catch:{ all -> 0x0b8f }
        r12 = r11.name;	 Catch:{ all -> 0x0b8f }
        r13 = "_ep";
        r12 = r12.equals(r13);	 Catch:{ all -> 0x0b8f }
        if (r12 == 0) goto L_0x079d;
    L_0x071e:
        r59.zzjf();	 Catch:{ all -> 0x0b8f }
        r12 = "_en";
        r12 = com.google.android.gms.internal.measurement.zzjz.zzb(r11, r12);	 Catch:{ all -> 0x0b8f }
        r12 = (java.lang.String) r12;	 Catch:{ all -> 0x0b8f }
        r13 = r4.get(r12);	 Catch:{ all -> 0x0b8f }
        r13 = (com.google.android.gms.internal.measurement.zzet) r13;	 Catch:{ all -> 0x0b8f }
        if (r13 != 0) goto L_0x0740;
    L_0x0731:
        r13 = r59.zzjh();	 Catch:{ all -> 0x0b8f }
        r14 = r2.zzasp;	 Catch:{ all -> 0x0b8f }
        r14 = r14.zzth;	 Catch:{ all -> 0x0b8f }
        r13 = r13.zzf(r14, r12);	 Catch:{ all -> 0x0b8f }
        r4.put(r12, r13);	 Catch:{ all -> 0x0b8f }
    L_0x0740:
        r12 = r13.zzahl;	 Catch:{ all -> 0x0b8f }
        if (r12 != 0) goto L_0x0794;
    L_0x0744:
        r12 = r13.zzahm;	 Catch:{ all -> 0x0b8f }
        r14 = r12.longValue();	 Catch:{ all -> 0x0b8f }
        r16 = 1;
        r12 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1));
        if (r12 <= 0) goto L_0x075f;
    L_0x0750:
        r59.zzjf();	 Catch:{ all -> 0x0b8f }
        r12 = r11.zzava;	 Catch:{ all -> 0x0b8f }
        r14 = "_sr";
        r15 = r13.zzahm;	 Catch:{ all -> 0x0b8f }
        r12 = com.google.android.gms.internal.measurement.zzjz.zza(r12, r14, r15);	 Catch:{ all -> 0x0b8f }
        r11.zzava = r12;	 Catch:{ all -> 0x0b8f }
    L_0x075f:
        r12 = r13.zzahn;	 Catch:{ all -> 0x0b8f }
        if (r12 == 0) goto L_0x0781;
    L_0x0763:
        r12 = r13.zzahn;	 Catch:{ all -> 0x0b8f }
        r12 = r12.booleanValue();	 Catch:{ all -> 0x0b8f }
        if (r12 == 0) goto L_0x0781;
    L_0x076b:
        r59.zzjf();	 Catch:{ all -> 0x0b8f }
        r12 = r11.zzava;	 Catch:{ all -> 0x0b8f }
        r13 = "_efs";
        r40 = r7;
        r14 = 1;
        r7 = java.lang.Long.valueOf(r14);	 Catch:{ all -> 0x0b8f }
        r7 = com.google.android.gms.internal.measurement.zzjz.zza(r12, r13, r7);	 Catch:{ all -> 0x0b8f }
        r11.zzava = r7;	 Catch:{ all -> 0x0b8f }
        goto L_0x0783;
    L_0x0781:
        r40 = r7;
    L_0x0783:
        r7 = r10 + 1;
        r5[r10] = r11;	 Catch:{ all -> 0x0b8f }
        r57 = r2;
        r58 = r3;
        r56 = r6;
        r10 = r7;
    L_0x078e:
        r41 = r8;
    L_0x0790:
        r14 = 1;
        goto L_0x0974;
    L_0x0794:
        r40 = r7;
        r57 = r2;
        r58 = r3;
        r56 = r6;
        goto L_0x078e;
    L_0x079d:
        r40 = r7;
        r7 = "_dbg";
        r12 = 1;
        r14 = java.lang.Long.valueOf(r12);	 Catch:{ all -> 0x0b8f }
        r12 = android.text.TextUtils.isEmpty(r7);	 Catch:{ all -> 0x0b8f }
        if (r12 != 0) goto L_0x07f1;
    L_0x07ad:
        if (r14 != 0) goto L_0x07b0;
    L_0x07af:
        goto L_0x07f1;
    L_0x07b0:
        r12 = r11.zzava;	 Catch:{ all -> 0x0b8f }
        r13 = r12.length;	 Catch:{ all -> 0x0b8f }
        r15 = 0;
    L_0x07b4:
        if (r15 >= r13) goto L_0x07f1;
    L_0x07b6:
        r41 = r8;
        r8 = r12[r15];	 Catch:{ all -> 0x0b8f }
        r42 = r12;
        r12 = r8.name;	 Catch:{ all -> 0x0b8f }
        r12 = r7.equals(r12);	 Catch:{ all -> 0x0b8f }
        if (r12 == 0) goto L_0x07ea;
    L_0x07c4:
        r7 = r14 instanceof java.lang.Long;	 Catch:{ all -> 0x0b8f }
        if (r7 == 0) goto L_0x07d0;
    L_0x07c8:
        r7 = r8.zzave;	 Catch:{ all -> 0x0b8f }
        r7 = r14.equals(r7);	 Catch:{ all -> 0x0b8f }
        if (r7 != 0) goto L_0x07e8;
    L_0x07d0:
        r7 = r14 instanceof java.lang.String;	 Catch:{ all -> 0x0b8f }
        if (r7 == 0) goto L_0x07dc;
    L_0x07d4:
        r7 = r8.zzale;	 Catch:{ all -> 0x0b8f }
        r7 = r14.equals(r7);	 Catch:{ all -> 0x0b8f }
        if (r7 != 0) goto L_0x07e8;
    L_0x07dc:
        r7 = r14 instanceof java.lang.Double;	 Catch:{ all -> 0x0b8f }
        if (r7 == 0) goto L_0x07f3;
    L_0x07e0:
        r7 = r8.zzasw;	 Catch:{ all -> 0x0b8f }
        r7 = r14.equals(r7);	 Catch:{ all -> 0x0b8f }
        if (r7 == 0) goto L_0x07f3;
    L_0x07e8:
        r7 = 1;
        goto L_0x07f4;
    L_0x07ea:
        r15 = r15 + 1;
        r8 = r41;
        r12 = r42;
        goto L_0x07b4;
    L_0x07f1:
        r41 = r8;
    L_0x07f3:
        r7 = 0;
    L_0x07f4:
        if (r7 != 0) goto L_0x0805;
    L_0x07f6:
        r7 = r59.zzky();	 Catch:{ all -> 0x0b8f }
        r8 = r2.zzasp;	 Catch:{ all -> 0x0b8f }
        r8 = r8.zzth;	 Catch:{ all -> 0x0b8f }
        r12 = r11.name;	 Catch:{ all -> 0x0b8f }
        r7 = r7.zzp(r8, r12);	 Catch:{ all -> 0x0b8f }
        goto L_0x0806;
    L_0x0805:
        r7 = 1;
    L_0x0806:
        if (r7 > 0) goto L_0x082a;
    L_0x0808:
        r8 = r1.zzacv;	 Catch:{ all -> 0x0b8f }
        r8 = r8.zzgi();	 Catch:{ all -> 0x0b8f }
        r8 = r8.zziy();	 Catch:{ all -> 0x0b8f }
        r12 = "Sample rate must be positive. event, rate";
        r13 = r11.name;	 Catch:{ all -> 0x0b8f }
        r7 = java.lang.Integer.valueOf(r7);	 Catch:{ all -> 0x0b8f }
        r8.zze(r12, r13, r7);	 Catch:{ all -> 0x0b8f }
        r7 = r10 + 1;
        r5[r10] = r11;	 Catch:{ all -> 0x0b8f }
    L_0x0821:
        r57 = r2;
        r58 = r3;
        r56 = r6;
        r10 = r7;
        goto L_0x0790;
    L_0x082a:
        r8 = r11.name;	 Catch:{ all -> 0x0b8f }
        r8 = r4.get(r8);	 Catch:{ all -> 0x0b8f }
        r8 = (com.google.android.gms.internal.measurement.zzet) r8;	 Catch:{ all -> 0x0b8f }
        if (r8 != 0) goto L_0x087c;
    L_0x0834:
        r8 = r59.zzjh();	 Catch:{ all -> 0x0b8f }
        r12 = r2.zzasp;	 Catch:{ all -> 0x0b8f }
        r12 = r12.zzth;	 Catch:{ all -> 0x0b8f }
        r13 = r11.name;	 Catch:{ all -> 0x0b8f }
        r8 = r8.zzf(r12, r13);	 Catch:{ all -> 0x0b8f }
        if (r8 != 0) goto L_0x087c;
    L_0x0844:
        r8 = r1.zzacv;	 Catch:{ all -> 0x0b8f }
        r8 = r8.zzgi();	 Catch:{ all -> 0x0b8f }
        r8 = r8.zziy();	 Catch:{ all -> 0x0b8f }
        r12 = "Event being bundled has no eventAggregate. appId, eventName";
        r13 = r2.zzasp;	 Catch:{ all -> 0x0b8f }
        r13 = r13.zzth;	 Catch:{ all -> 0x0b8f }
        r14 = r11.name;	 Catch:{ all -> 0x0b8f }
        r8.zze(r12, r13, r14);	 Catch:{ all -> 0x0b8f }
        r8 = new com.google.android.gms.internal.measurement.zzet;	 Catch:{ all -> 0x0b8f }
        r12 = r2.zzasp;	 Catch:{ all -> 0x0b8f }
        r12 = r12.zzth;	 Catch:{ all -> 0x0b8f }
        r13 = r11.name;	 Catch:{ all -> 0x0b8f }
        r45 = 1;
        r47 = 1;
        r14 = r11.zzavb;	 Catch:{ all -> 0x0b8f }
        r49 = r14.longValue();	 Catch:{ all -> 0x0b8f }
        r51 = 0;
        r53 = 0;
        r54 = 0;
        r55 = 0;
        r42 = r8;
        r43 = r12;
        r44 = r13;
        r42.<init>(r43, r44, r45, r47, r49, r51, r53, r54, r55);	 Catch:{ all -> 0x0b8f }
    L_0x087c:
        r59.zzjf();	 Catch:{ all -> 0x0b8f }
        r12 = "_eid";
        r12 = com.google.android.gms.internal.measurement.zzjz.zzb(r11, r12);	 Catch:{ all -> 0x0b8f }
        r12 = (java.lang.Long) r12;	 Catch:{ all -> 0x0b8f }
        if (r12 == 0) goto L_0x088b;
    L_0x0889:
        r13 = 1;
        goto L_0x088c;
    L_0x088b:
        r13 = 0;
    L_0x088c:
        r13 = java.lang.Boolean.valueOf(r13);	 Catch:{ all -> 0x0b8f }
        r14 = 1;
        if (r7 != r14) goto L_0x08b5;
    L_0x0893:
        r7 = r10 + 1;
        r5[r10] = r11;	 Catch:{ all -> 0x0b8f }
        r10 = r13.booleanValue();	 Catch:{ all -> 0x0b8f }
        if (r10 == 0) goto L_0x0821;
    L_0x089d:
        r10 = r8.zzahl;	 Catch:{ all -> 0x0b8f }
        if (r10 != 0) goto L_0x08a9;
    L_0x08a1:
        r10 = r8.zzahm;	 Catch:{ all -> 0x0b8f }
        if (r10 != 0) goto L_0x08a9;
    L_0x08a5:
        r10 = r8.zzahn;	 Catch:{ all -> 0x0b8f }
        if (r10 == 0) goto L_0x0821;
    L_0x08a9:
        r10 = 0;
        r8 = r8.zza(r10, r10, r10);	 Catch:{ all -> 0x0b8f }
        r10 = r11.name;	 Catch:{ all -> 0x0b8f }
        r4.put(r10, r8);	 Catch:{ all -> 0x0b8f }
        goto L_0x0821;
    L_0x08b5:
        r14 = r6.nextInt(r7);	 Catch:{ all -> 0x0b8f }
        if (r14 != 0) goto L_0x08f8;
    L_0x08bb:
        r59.zzjf();	 Catch:{ all -> 0x0b8f }
        r12 = r11.zzava;	 Catch:{ all -> 0x0b8f }
        r14 = "_sr";
        r56 = r6;
        r6 = (long) r7;	 Catch:{ all -> 0x0b8f }
        r15 = java.lang.Long.valueOf(r6);	 Catch:{ all -> 0x0b8f }
        r12 = com.google.android.gms.internal.measurement.zzjz.zza(r12, r14, r15);	 Catch:{ all -> 0x0b8f }
        r11.zzava = r12;	 Catch:{ all -> 0x0b8f }
        r12 = r10 + 1;
        r5[r10] = r11;	 Catch:{ all -> 0x0b8f }
        r10 = r13.booleanValue();	 Catch:{ all -> 0x0b8f }
        if (r10 == 0) goto L_0x08e2;
    L_0x08d9:
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ all -> 0x0b8f }
        r7 = 0;
        r8 = r8.zza(r7, r6, r7);	 Catch:{ all -> 0x0b8f }
    L_0x08e2:
        r6 = r11.name;	 Catch:{ all -> 0x0b8f }
        r7 = r11.zzavb;	 Catch:{ all -> 0x0b8f }
        r10 = r7.longValue();	 Catch:{ all -> 0x0b8f }
        r7 = r8.zzai(r10);	 Catch:{ all -> 0x0b8f }
        r4.put(r6, r7);	 Catch:{ all -> 0x0b8f }
        r57 = r2;
        r58 = r3;
        r10 = r12;
        goto L_0x0790;
    L_0x08f8:
        r56 = r6;
        r14 = r8.zzahk;	 Catch:{ all -> 0x0b8f }
        r6 = r11.zzavb;	 Catch:{ all -> 0x0b8f }
        r16 = r6.longValue();	 Catch:{ all -> 0x0b8f }
        r6 = 0;
        r57 = r2;
        r58 = r3;
        r2 = r16 - r14;
        r2 = java.lang.Math.abs(r2);	 Catch:{ all -> 0x0b8f }
        r14 = 86400000; // 0x5265c00 float:7.82218E-36 double:4.2687272E-316;
        r6 = (r2 > r14 ? 1 : (r2 == r14 ? 0 : -1));
        if (r6 < 0) goto L_0x0962;
    L_0x0914:
        r59.zzjf();	 Catch:{ all -> 0x0b8f }
        r2 = r11.zzava;	 Catch:{ all -> 0x0b8f }
        r3 = "_efs";
        r14 = 1;
        r6 = java.lang.Long.valueOf(r14);	 Catch:{ all -> 0x0b8f }
        r2 = com.google.android.gms.internal.measurement.zzjz.zza(r2, r3, r6);	 Catch:{ all -> 0x0b8f }
        r11.zzava = r2;	 Catch:{ all -> 0x0b8f }
        r59.zzjf();	 Catch:{ all -> 0x0b8f }
        r2 = r11.zzava;	 Catch:{ all -> 0x0b8f }
        r3 = "_sr";
        r6 = (long) r7;	 Catch:{ all -> 0x0b8f }
        r12 = java.lang.Long.valueOf(r6);	 Catch:{ all -> 0x0b8f }
        r2 = com.google.android.gms.internal.measurement.zzjz.zza(r2, r3, r12);	 Catch:{ all -> 0x0b8f }
        r11.zzava = r2;	 Catch:{ all -> 0x0b8f }
        r2 = r10 + 1;
        r5[r10] = r11;	 Catch:{ all -> 0x0b8f }
        r3 = r13.booleanValue();	 Catch:{ all -> 0x0b8f }
        if (r3 == 0) goto L_0x0951;
    L_0x0943:
        r3 = java.lang.Long.valueOf(r6);	 Catch:{ all -> 0x0b8f }
        r6 = 1;
        r7 = java.lang.Boolean.valueOf(r6);	 Catch:{ all -> 0x0b8f }
        r6 = 0;
        r8 = r8.zza(r6, r3, r7);	 Catch:{ all -> 0x0b8f }
    L_0x0951:
        r3 = r11.name;	 Catch:{ all -> 0x0b8f }
        r6 = r11.zzavb;	 Catch:{ all -> 0x0b8f }
        r6 = r6.longValue();	 Catch:{ all -> 0x0b8f }
        r6 = r8.zzai(r6);	 Catch:{ all -> 0x0b8f }
        r4.put(r3, r6);	 Catch:{ all -> 0x0b8f }
        r10 = r2;
        goto L_0x0974;
    L_0x0962:
        r14 = 1;
        r2 = r13.booleanValue();	 Catch:{ all -> 0x0b8f }
        if (r2 == 0) goto L_0x0974;
    L_0x096a:
        r2 = r11.name;	 Catch:{ all -> 0x0b8f }
        r3 = 0;
        r6 = r8.zza(r12, r3, r3);	 Catch:{ all -> 0x0b8f }
        r4.put(r2, r6);	 Catch:{ all -> 0x0b8f }
    L_0x0974:
        r9 = r9 + 1;
        r7 = r40;
        r8 = r41;
        r6 = r56;
        r2 = r57;
        r3 = r58;
        goto L_0x0710;
    L_0x0982:
        r57 = r2;
        r2 = r3;
        r3 = r2.zzavi;	 Catch:{ all -> 0x0b8f }
        r3 = r3.length;	 Catch:{ all -> 0x0b8f }
        if (r10 >= r3) goto L_0x0992;
    L_0x098a:
        r3 = java.util.Arrays.copyOf(r5, r10);	 Catch:{ all -> 0x0b8f }
        r3 = (com.google.android.gms.internal.measurement.zzkr[]) r3;	 Catch:{ all -> 0x0b8f }
        r2.zzavi = r3;	 Catch:{ all -> 0x0b8f }
    L_0x0992:
        r3 = r4.entrySet();	 Catch:{ all -> 0x0b8f }
        r3 = r3.iterator();	 Catch:{ all -> 0x0b8f }
    L_0x099a:
        r4 = r3.hasNext();	 Catch:{ all -> 0x0b8f }
        if (r4 == 0) goto L_0x09b7;
    L_0x09a0:
        r4 = r3.next();	 Catch:{ all -> 0x0b8f }
        r4 = (java.util.Map.Entry) r4;	 Catch:{ all -> 0x0b8f }
        r5 = r59.zzjh();	 Catch:{ all -> 0x0b8f }
        r4 = r4.getValue();	 Catch:{ all -> 0x0b8f }
        r4 = (com.google.android.gms.internal.measurement.zzet) r4;	 Catch:{ all -> 0x0b8f }
        r5.zza(r4);	 Catch:{ all -> 0x0b8f }
        goto L_0x099a;
    L_0x09b4:
        r57 = r2;
        r2 = r3;
    L_0x09b7:
        r3 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
        r3 = java.lang.Long.valueOf(r3);	 Catch:{ all -> 0x0b8f }
        r2.zzavl = r3;	 Catch:{ all -> 0x0b8f }
        r3 = -9223372036854775808;
        r3 = java.lang.Long.valueOf(r3);	 Catch:{ all -> 0x0b8f }
        r2.zzavm = r3;	 Catch:{ all -> 0x0b8f }
        r3 = 0;
    L_0x09cb:
        r4 = r2.zzavi;	 Catch:{ all -> 0x0b8f }
        r4 = r4.length;	 Catch:{ all -> 0x0b8f }
        if (r3 >= r4) goto L_0x09ff;
    L_0x09d0:
        r4 = r2.zzavi;	 Catch:{ all -> 0x0b8f }
        r4 = r4[r3];	 Catch:{ all -> 0x0b8f }
        r5 = r4.zzavb;	 Catch:{ all -> 0x0b8f }
        r5 = r5.longValue();	 Catch:{ all -> 0x0b8f }
        r7 = r2.zzavl;	 Catch:{ all -> 0x0b8f }
        r7 = r7.longValue();	 Catch:{ all -> 0x0b8f }
        r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1));
        if (r9 >= 0) goto L_0x09e8;
    L_0x09e4:
        r5 = r4.zzavb;	 Catch:{ all -> 0x0b8f }
        r2.zzavl = r5;	 Catch:{ all -> 0x0b8f }
    L_0x09e8:
        r5 = r4.zzavb;	 Catch:{ all -> 0x0b8f }
        r5 = r5.longValue();	 Catch:{ all -> 0x0b8f }
        r7 = r2.zzavm;	 Catch:{ all -> 0x0b8f }
        r7 = r7.longValue();	 Catch:{ all -> 0x0b8f }
        r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1));
        if (r9 <= 0) goto L_0x09fc;
    L_0x09f8:
        r4 = r4.zzavb;	 Catch:{ all -> 0x0b8f }
        r2.zzavm = r4;	 Catch:{ all -> 0x0b8f }
    L_0x09fc:
        r3 = r3 + 1;
        goto L_0x09cb;
    L_0x09ff:
        r3 = r57;
        r4 = r3.zzasp;	 Catch:{ all -> 0x0b8f }
        r4 = r4.zzth;	 Catch:{ all -> 0x0b8f }
        r5 = r59.zzjh();	 Catch:{ all -> 0x0b8f }
        r5 = r5.zzbf(r4);	 Catch:{ all -> 0x0b8f }
        if (r5 != 0) goto L_0x0a27;
    L_0x0a0f:
        r5 = r1.zzacv;	 Catch:{ all -> 0x0b8f }
        r5 = r5.zzgi();	 Catch:{ all -> 0x0b8f }
        r5 = r5.zziv();	 Catch:{ all -> 0x0b8f }
        r6 = "Bundling raw events w/o app info. appId";
        r7 = r3.zzasp;	 Catch:{ all -> 0x0b8f }
        r7 = r7.zzth;	 Catch:{ all -> 0x0b8f }
        r7 = com.google.android.gms.internal.measurement.zzfi.zzbp(r7);	 Catch:{ all -> 0x0b8f }
        r5.zzg(r6, r7);	 Catch:{ all -> 0x0b8f }
        goto L_0x0a83;
    L_0x0a27:
        r6 = r2.zzavi;	 Catch:{ all -> 0x0b8f }
        r6 = r6.length;	 Catch:{ all -> 0x0b8f }
        if (r6 <= 0) goto L_0x0a83;
    L_0x0a2c:
        r6 = r5.zzgt();	 Catch:{ all -> 0x0b8f }
        r8 = 0;
        r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r10 == 0) goto L_0x0a3b;
    L_0x0a36:
        r8 = java.lang.Long.valueOf(r6);	 Catch:{ all -> 0x0b8f }
        goto L_0x0a3c;
    L_0x0a3b:
        r8 = 0;
    L_0x0a3c:
        r2.zzavo = r8;	 Catch:{ all -> 0x0b8f }
        r8 = r5.zzgs();	 Catch:{ all -> 0x0b8f }
        r10 = 0;
        r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r12 != 0) goto L_0x0a49;
    L_0x0a48:
        goto L_0x0a4a;
    L_0x0a49:
        r6 = r8;
    L_0x0a4a:
        r8 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1));
        if (r8 == 0) goto L_0x0a53;
    L_0x0a4e:
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ all -> 0x0b8f }
        goto L_0x0a54;
    L_0x0a53:
        r6 = 0;
    L_0x0a54:
        r2.zzavn = r6;	 Catch:{ all -> 0x0b8f }
        r5.zzhb();	 Catch:{ all -> 0x0b8f }
        r6 = r5.zzgy();	 Catch:{ all -> 0x0b8f }
        r6 = (int) r6;	 Catch:{ all -> 0x0b8f }
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ all -> 0x0b8f }
        r2.zzavy = r6;	 Catch:{ all -> 0x0b8f }
        r6 = r2.zzavl;	 Catch:{ all -> 0x0b8f }
        r6 = r6.longValue();	 Catch:{ all -> 0x0b8f }
        r5.zzr(r6);	 Catch:{ all -> 0x0b8f }
        r6 = r2.zzavm;	 Catch:{ all -> 0x0b8f }
        r6 = r6.longValue();	 Catch:{ all -> 0x0b8f }
        r5.zzs(r6);	 Catch:{ all -> 0x0b8f }
        r6 = r5.zzhj();	 Catch:{ all -> 0x0b8f }
        r2.zzafy = r6;	 Catch:{ all -> 0x0b8f }
        r6 = r59.zzjh();	 Catch:{ all -> 0x0b8f }
        r6.zza(r5);	 Catch:{ all -> 0x0b8f }
    L_0x0a83:
        r5 = r2.zzavi;	 Catch:{ all -> 0x0b8f }
        r5 = r5.length;	 Catch:{ all -> 0x0b8f }
        if (r5 <= 0) goto L_0x0ad6;
    L_0x0a88:
        r5 = r1.zzacv;	 Catch:{ all -> 0x0b8f }
        r5.zzgl();	 Catch:{ all -> 0x0b8f }
        r5 = r59.zzky();	 Catch:{ all -> 0x0b8f }
        r6 = r3.zzasp;	 Catch:{ all -> 0x0b8f }
        r6 = r6.zzth;	 Catch:{ all -> 0x0b8f }
        r5 = r5.zzbx(r6);	 Catch:{ all -> 0x0b8f }
        if (r5 == 0) goto L_0x0aa5;
    L_0x0a9b:
        r6 = r5.zzaum;	 Catch:{ all -> 0x0b8f }
        if (r6 != 0) goto L_0x0aa0;
    L_0x0a9f:
        goto L_0x0aa5;
    L_0x0aa0:
        r5 = r5.zzaum;	 Catch:{ all -> 0x0b8f }
    L_0x0aa2:
        r2.zzawf = r5;	 Catch:{ all -> 0x0b8f }
        goto L_0x0acd;
    L_0x0aa5:
        r5 = r3.zzasp;	 Catch:{ all -> 0x0b8f }
        r5 = r5.zzafa;	 Catch:{ all -> 0x0b8f }
        r5 = android.text.TextUtils.isEmpty(r5);	 Catch:{ all -> 0x0b8f }
        if (r5 == 0) goto L_0x0ab6;
    L_0x0aaf:
        r5 = -1;
        r5 = java.lang.Long.valueOf(r5);	 Catch:{ all -> 0x0b8f }
        goto L_0x0aa2;
    L_0x0ab6:
        r5 = r1.zzacv;	 Catch:{ all -> 0x0b8f }
        r5 = r5.zzgi();	 Catch:{ all -> 0x0b8f }
        r5 = r5.zziy();	 Catch:{ all -> 0x0b8f }
        r6 = "Did not find measurement config or missing version info. appId";
        r7 = r3.zzasp;	 Catch:{ all -> 0x0b8f }
        r7 = r7.zzth;	 Catch:{ all -> 0x0b8f }
        r7 = com.google.android.gms.internal.measurement.zzfi.zzbp(r7);	 Catch:{ all -> 0x0b8f }
        r5.zzg(r6, r7);	 Catch:{ all -> 0x0b8f }
    L_0x0acd:
        r5 = r59.zzjh();	 Catch:{ all -> 0x0b8f }
        r10 = r27;
        r5.zza(r2, r10);	 Catch:{ all -> 0x0b8f }
    L_0x0ad6:
        r2 = r59.zzjh();	 Catch:{ all -> 0x0b8f }
        r3 = r3.zzasq;	 Catch:{ all -> 0x0b8f }
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r3);	 Catch:{ all -> 0x0b8f }
        r2.zzab();	 Catch:{ all -> 0x0b8f }
        r2.zzch();	 Catch:{ all -> 0x0b8f }
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0b8f }
        r6 = "rowid in (";
        r5.<init>(r6);	 Catch:{ all -> 0x0b8f }
        r6 = 0;
    L_0x0aed:
        r7 = r3.size();	 Catch:{ all -> 0x0b8f }
        if (r6 >= r7) goto L_0x0b0a;
    L_0x0af3:
        if (r6 == 0) goto L_0x0afa;
    L_0x0af5:
        r7 = ",";
        r5.append(r7);	 Catch:{ all -> 0x0b8f }
    L_0x0afa:
        r7 = r3.get(r6);	 Catch:{ all -> 0x0b8f }
        r7 = (java.lang.Long) r7;	 Catch:{ all -> 0x0b8f }
        r7 = r7.longValue();	 Catch:{ all -> 0x0b8f }
        r5.append(r7);	 Catch:{ all -> 0x0b8f }
        r6 = r6 + 1;
        goto L_0x0aed;
    L_0x0b0a:
        r6 = ")";
        r5.append(r6);	 Catch:{ all -> 0x0b8f }
        r6 = r2.getWritableDatabase();	 Catch:{ all -> 0x0b8f }
        r7 = "raw_events";
        r5 = r5.toString();	 Catch:{ all -> 0x0b8f }
        r8 = 0;
        r5 = r6.delete(r7, r5, r8);	 Catch:{ all -> 0x0b8f }
        r6 = r3.size();	 Catch:{ all -> 0x0b8f }
        if (r5 == r6) goto L_0x0b3d;
    L_0x0b24:
        r2 = r2.zzgi();	 Catch:{ all -> 0x0b8f }
        r2 = r2.zziv();	 Catch:{ all -> 0x0b8f }
        r6 = "Deleted fewer rows from raw events table than expected";
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ all -> 0x0b8f }
        r3 = r3.size();	 Catch:{ all -> 0x0b8f }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ all -> 0x0b8f }
        r2.zze(r6, r5, r3);	 Catch:{ all -> 0x0b8f }
    L_0x0b3d:
        r2 = r59.zzjh();	 Catch:{ all -> 0x0b8f }
        r3 = r2.getWritableDatabase();	 Catch:{ all -> 0x0b8f }
        r5 = "delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)";
        r6 = 2;
        r6 = new java.lang.String[r6];	 Catch:{ SQLiteException -> 0x0b54 }
        r7 = 0;
        r6[r7] = r4;	 Catch:{ SQLiteException -> 0x0b54 }
        r7 = 1;
        r6[r7] = r4;	 Catch:{ SQLiteException -> 0x0b54 }
        r3.execSQL(r5, r6);	 Catch:{ SQLiteException -> 0x0b54 }
        goto L_0x0b67;
    L_0x0b54:
        r0 = move-exception;
        r3 = r0;
        r2 = r2.zzgi();	 Catch:{ all -> 0x0b8f }
        r2 = r2.zziv();	 Catch:{ all -> 0x0b8f }
        r5 = "Failed to remove unused event metadata. appId";
        r4 = com.google.android.gms.internal.measurement.zzfi.zzbp(r4);	 Catch:{ all -> 0x0b8f }
        r2.zze(r5, r4, r3);	 Catch:{ all -> 0x0b8f }
    L_0x0b67:
        r2 = r59.zzjh();	 Catch:{ all -> 0x0b8f }
        r2.setTransactionSuccessful();	 Catch:{ all -> 0x0b8f }
        r2 = r59.zzjh();
        r2.endTransaction();
        r2 = 1;
        return r2;
    L_0x0b77:
        r2 = r59.zzjh();	 Catch:{ all -> 0x0b8f }
        r2.setTransactionSuccessful();	 Catch:{ all -> 0x0b8f }
        r2 = r59.zzjh();
        r2.endTransaction();
        r2 = 0;
        return r2;
    L_0x0b87:
        r0 = move-exception;
    L_0x0b88:
        r2 = r0;
    L_0x0b89:
        if (r9 == 0) goto L_0x0b8e;
    L_0x0b8b:
        r9.close();	 Catch:{ all -> 0x0b8f }
    L_0x0b8e:
        throw r2;	 Catch:{ all -> 0x0b8f }
    L_0x0b8f:
        r0 = move-exception;
        r2 = r0;
        r3 = r59.zzjh();
        r3.endTransaction();
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjt.zzd(java.lang.String, long):boolean");
    }

    @WorkerThread
    private final zzea zzg(zzeb zzeb) {
        Object obj;
        zzab();
        zzlc();
        Preconditions.checkNotNull(zzeb);
        Preconditions.checkNotEmpty(zzeb.packageName);
        zzea zzbf = zzjh().zzbf(zzeb.packageName);
        String zzbs = this.zzacv.zzgj().zzbs(zzeb.packageName);
        if (zzbf == null) {
            zzbf = new zzea(this.zzacv, zzeb.packageName);
            zzbf.zzam(this.zzacv.zzfz().zzir());
            zzbf.zzao(zzbs);
        } else if (zzbs.equals(zzbf.zzgq())) {
            obj = null;
            if (!(TextUtils.isEmpty(zzeb.zzafa) || zzeb.zzafa.equals(zzbf.getGmpAppId()))) {
                zzbf.zzan(zzeb.zzafa);
                obj = 1;
            }
            if (!(TextUtils.isEmpty(zzeb.zzafc) || zzeb.zzafc.equals(zzbf.zzgr()))) {
                zzbf.zzap(zzeb.zzafc);
                obj = 1;
            }
            if (!(zzeb.zzafi == 0 || zzeb.zzafi == zzbf.zzgw())) {
                zzbf.zzu(zzeb.zzafi);
                obj = 1;
            }
            if (!(TextUtils.isEmpty(zzeb.zztg) || zzeb.zztg.equals(zzbf.zzag()))) {
                zzbf.setAppVersion(zzeb.zztg);
                obj = 1;
            }
            if (zzeb.zzafg != zzbf.zzgu()) {
                zzbf.zzt(zzeb.zzafg);
                obj = 1;
            }
            if (!(zzeb.zzafh == null || zzeb.zzafh.equals(zzbf.zzgv()))) {
                zzbf.zzaq(zzeb.zzafh);
                obj = 1;
            }
            if (zzeb.zzafj != zzbf.zzgx()) {
                zzbf.zzv(zzeb.zzafj);
                obj = 1;
            }
            if (zzeb.zzafk != zzbf.isMeasurementEnabled()) {
                zzbf.setMeasurementEnabled(zzeb.zzafk);
                obj = 1;
            }
            if (!(TextUtils.isEmpty(zzeb.zzafy) || zzeb.zzafy.equals(zzbf.zzhi()))) {
                zzbf.zzar(zzeb.zzafy);
                obj = 1;
            }
            if (zzeb.zzafl != zzbf.zzhk()) {
                zzbf.zzaf(zzeb.zzafl);
                obj = 1;
            }
            if (zzeb.zzafm != zzbf.zzhl()) {
                zzbf.zzd(zzeb.zzafm);
                obj = 1;
            }
            if (zzeb.zzafn != zzbf.zzhm()) {
                zzbf.zze(zzeb.zzafn);
                obj = 1;
            }
            if (obj != null) {
                zzjh().zza(zzbf);
            }
            return zzbf;
        } else {
            zzbf.zzao(zzbs);
            zzbf.zzam(this.zzacv.zzfz().zzir());
        }
        obj = 1;
        zzbf.zzan(zzeb.zzafa);
        obj = 1;
        zzbf.zzap(zzeb.zzafc);
        obj = 1;
        zzbf.zzu(zzeb.zzafi);
        obj = 1;
        zzbf.setAppVersion(zzeb.zztg);
        obj = 1;
        if (zzeb.zzafg != zzbf.zzgu()) {
            zzbf.zzt(zzeb.zzafg);
            obj = 1;
        }
        zzbf.zzaq(zzeb.zzafh);
        obj = 1;
        if (zzeb.zzafj != zzbf.zzgx()) {
            zzbf.zzv(zzeb.zzafj);
            obj = 1;
        }
        if (zzeb.zzafk != zzbf.isMeasurementEnabled()) {
            zzbf.setMeasurementEnabled(zzeb.zzafk);
            obj = 1;
        }
        zzbf.zzar(zzeb.zzafy);
        obj = 1;
        if (zzeb.zzafl != zzbf.zzhk()) {
            zzbf.zzaf(zzeb.zzafl);
            obj = 1;
        }
        if (zzeb.zzafm != zzbf.zzhl()) {
            zzbf.zzd(zzeb.zzafm);
            obj = 1;
        }
        if (zzeb.zzafn != zzbf.zzhm()) {
            zzbf.zze(zzeb.zzafn);
            obj = 1;
        }
        if (obj != null) {
            zzjh().zza(zzbf);
        }
        return zzbf;
    }

    public static zzjt zzg(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzarr == null) {
            synchronized (zzjt.class) {
                if (zzarr == null) {
                    zzarr = new zzjt(new zzjy(context));
                }
            }
        }
        return zzarr;
    }

    private final zzgh zzky() {
        zza(this.zzars);
        return this.zzars;
    }

    private final zzfr zzla() {
        if (this.zzarv != null) {
            return this.zzarv;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    private final zzjp zzlb() {
        zza(this.zzarw);
        return this.zzarw;
    }

    private final long zzld() {
        long currentTimeMillis = this.zzacv.zzbt().currentTimeMillis();
        zzhi zzgj = this.zzacv.zzgj();
        zzgj.zzch();
        zzgj.zzab();
        long j = zzgj.zzalx.get();
        if (j == 0) {
            long nextInt = ((long) zzgj.zzgg().zzlo().nextInt(86400000)) + 1;
            zzgj.zzalx.set(nextInt);
            j = nextInt;
        }
        return ((((currentTimeMillis + j) / 1000) / 60) / 60) / 24;
    }

    private final boolean zzlf() {
        zzab();
        zzlc();
        if (!zzjh().zzia()) {
            if (TextUtils.isEmpty(zzjh().zzhv())) {
                return false;
            }
        }
        return true;
    }

    @WorkerThread
    private final void zzlg() {
        zzjt zzjt = this;
        zzab();
        zzlc();
        if (zzlk()) {
            long abs;
            if (zzjt.zzasa > 0) {
                abs = 3600000 - Math.abs(zzjt.zzacv.zzbt().elapsedRealtime() - zzjt.zzasa);
                if (abs > 0) {
                    zzjt.zzacv.zzgi().zzjc().zzg("Upload has been suspended. Will update scheduling later in approximately ms", Long.valueOf(abs));
                    zzla().unregister();
                    zzlb().cancel();
                    return;
                }
                zzjt.zzasa = 0;
            }
            if (zzjt.zzacv.zzkg()) {
                if (zzlf()) {
                    Object obj;
                    CharSequence zzhs;
                    com.google.android.gms.internal.measurement.zzez.zza zza;
                    long max;
                    long j;
                    long j2;
                    long j3;
                    long j4;
                    long abs2;
                    long abs3;
                    long j5;
                    int i;
                    long currentTimeMillis = zzjt.zzacv.zzbt().currentTimeMillis();
                    long max2 = Math.max(0, ((Long) zzez.zzaje.get()).longValue());
                    if (!zzjh().zzib()) {
                        if (!zzjh().zzhw()) {
                            obj = null;
                            if (obj == null) {
                                zzhs = zzjt.zzacv.zzgk().zzhs();
                                zza = (TextUtils.isEmpty(zzhs) || ".none.".equals(zzhs)) ? zzez.zzaiy : zzez.zzaiz;
                            } else {
                                zza = zzez.zzaix;
                            }
                            max = Math.max(0, ((Long) zza.get()).longValue());
                            j = zzjt.zzacv.zzgj().zzalt.get();
                            j2 = zzjt.zzacv.zzgj().zzalu.get();
                            j3 = max;
                            j4 = max2;
                            max2 = Math.max(zzjh().zzhy(), zzjh().zzhz());
                            if (max2 != 0) {
                                abs2 = currentTimeMillis - Math.abs(max2 - currentTimeMillis);
                                abs3 = currentTimeMillis - Math.abs(j2 - currentTimeMillis);
                                currentTimeMillis = Math.max(currentTimeMillis - Math.abs(j - currentTimeMillis), abs3);
                                max2 = abs2 + j4;
                                if (obj != null && currentTimeMillis > 0) {
                                    max2 = Math.min(abs2, currentTimeMillis) + j3;
                                }
                                j5 = j3;
                                if (!zzjf().zza(currentTimeMillis, j5)) {
                                    max2 = currentTimeMillis + j5;
                                }
                                if (abs3 != 0 && abs3 >= abs2) {
                                    i = 0;
                                    while (i < Math.min(20, Math.max(0, ((Integer) zzez.zzajg.get()).intValue()))) {
                                        max = max2 + (Math.max(0, ((Long) zzez.zzajf.get()).longValue()) * (1 << i));
                                        if (max > abs3) {
                                            max2 = max;
                                            break;
                                        } else {
                                            i++;
                                            max2 = max;
                                        }
                                    }
                                }
                                if (max2 != 0) {
                                    zzjt.zzacv.zzgi().zzjc().log("Next upload time is 0");
                                    zzla().unregister();
                                    zzlb().cancel();
                                    return;
                                } else if (zzkz().zzex()) {
                                    currentTimeMillis = zzjt.zzacv.zzgj().zzalv.get();
                                    abs = Math.max(0, ((Long) zzez.zzaiv.get()).longValue());
                                    if (!zzjf().zza(currentTimeMillis, abs)) {
                                        max2 = Math.max(max2, currentTimeMillis + abs);
                                    }
                                    zzla().unregister();
                                    abs = max2 - zzjt.zzacv.zzbt().currentTimeMillis();
                                    if (abs <= 0) {
                                        abs = Math.max(0, ((Long) zzez.zzaja.get()).longValue());
                                        zzjt.zzacv.zzgj().zzalt.set(zzjt.zzacv.zzbt().currentTimeMillis());
                                    }
                                    zzjt.zzacv.zzgi().zzjc().zzg("Upload scheduled in approximately ms", Long.valueOf(abs));
                                    zzlb().zzh(abs);
                                    return;
                                } else {
                                    zzjt.zzacv.zzgi().zzjc().log("No network");
                                    zzla().zzeu();
                                    zzlb().cancel();
                                    return;
                                }
                            }
                            max2 = 0;
                            if (max2 != 0) {
                                zzjt.zzacv.zzgi().zzjc().log("Next upload time is 0");
                                zzla().unregister();
                                zzlb().cancel();
                                return;
                            } else if (zzkz().zzex()) {
                                currentTimeMillis = zzjt.zzacv.zzgj().zzalv.get();
                                abs = Math.max(0, ((Long) zzez.zzaiv.get()).longValue());
                                if (zzjf().zza(currentTimeMillis, abs)) {
                                    max2 = Math.max(max2, currentTimeMillis + abs);
                                }
                                zzla().unregister();
                                abs = max2 - zzjt.zzacv.zzbt().currentTimeMillis();
                                if (abs <= 0) {
                                    abs = Math.max(0, ((Long) zzez.zzaja.get()).longValue());
                                    zzjt.zzacv.zzgj().zzalt.set(zzjt.zzacv.zzbt().currentTimeMillis());
                                }
                                zzjt.zzacv.zzgi().zzjc().zzg("Upload scheduled in approximately ms", Long.valueOf(abs));
                                zzlb().zzh(abs);
                                return;
                            } else {
                                zzjt.zzacv.zzgi().zzjc().log("No network");
                                zzla().zzeu();
                                zzlb().cancel();
                                return;
                            }
                        }
                    }
                    obj = 1;
                    if (obj == null) {
                        zza = zzez.zzaix;
                    } else {
                        zzhs = zzjt.zzacv.zzgk().zzhs();
                        if (!TextUtils.isEmpty(zzhs)) {
                        }
                    }
                    max = Math.max(0, ((Long) zza.get()).longValue());
                    j = zzjt.zzacv.zzgj().zzalt.get();
                    j2 = zzjt.zzacv.zzgj().zzalu.get();
                    j3 = max;
                    j4 = max2;
                    max2 = Math.max(zzjh().zzhy(), zzjh().zzhz());
                    if (max2 != 0) {
                        abs2 = currentTimeMillis - Math.abs(max2 - currentTimeMillis);
                        abs3 = currentTimeMillis - Math.abs(j2 - currentTimeMillis);
                        currentTimeMillis = Math.max(currentTimeMillis - Math.abs(j - currentTimeMillis), abs3);
                        max2 = abs2 + j4;
                        max2 = Math.min(abs2, currentTimeMillis) + j3;
                        j5 = j3;
                        if (zzjf().zza(currentTimeMillis, j5)) {
                            max2 = currentTimeMillis + j5;
                        }
                        i = 0;
                        while (i < Math.min(20, Math.max(0, ((Integer) zzez.zzajg.get()).intValue()))) {
                            max = max2 + (Math.max(0, ((Long) zzez.zzajf.get()).longValue()) * (1 << i));
                            if (max > abs3) {
                                max2 = max;
                                break;
                            } else {
                                i++;
                                max2 = max;
                            }
                        }
                    }
                    max2 = 0;
                    if (max2 != 0) {
                        zzjt.zzacv.zzgi().zzjc().log("Next upload time is 0");
                        zzla().unregister();
                        zzlb().cancel();
                        return;
                    } else if (zzkz().zzex()) {
                        zzjt.zzacv.zzgi().zzjc().log("No network");
                        zzla().zzeu();
                        zzlb().cancel();
                        return;
                    } else {
                        currentTimeMillis = zzjt.zzacv.zzgj().zzalv.get();
                        abs = Math.max(0, ((Long) zzez.zzaiv.get()).longValue());
                        if (zzjf().zza(currentTimeMillis, abs)) {
                            max2 = Math.max(max2, currentTimeMillis + abs);
                        }
                        zzla().unregister();
                        abs = max2 - zzjt.zzacv.zzbt().currentTimeMillis();
                        if (abs <= 0) {
                            abs = Math.max(0, ((Long) zzez.zzaja.get()).longValue());
                            zzjt.zzacv.zzgj().zzalt.set(zzjt.zzacv.zzbt().currentTimeMillis());
                        }
                        zzjt.zzacv.zzgi().zzjc().zzg("Upload scheduled in approximately ms", Long.valueOf(abs));
                        zzlb().zzh(abs);
                        return;
                    }
                }
            }
            zzjt.zzacv.zzgi().zzjc().log("Nothing to upload or uploading impossible");
            zzla().unregister();
            zzlb().cancel();
        }
    }

    @WorkerThread
    private final void zzlh() {
        zzab();
        if (!(this.zzase || this.zzasf)) {
            if (!this.zzasg) {
                this.zzacv.zzgi().zzjc().log("Stopping uploading service(s)");
                if (this.zzasb != null) {
                    for (Runnable run : this.zzasb) {
                        run.run();
                    }
                    this.zzasb.clear();
                    return;
                }
                return;
            }
        }
        this.zzacv.zzgi().zzjc().zzd("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzase), Boolean.valueOf(this.zzasf), Boolean.valueOf(this.zzasg));
    }

    @WorkerThread
    @VisibleForTesting
    private final boolean zzli() {
        Object e;
        zzfk zziv;
        String str;
        zzab();
        try {
            this.zzasi = new RandomAccessFile(new File(this.zzacv.getContext().getFilesDir(), "google_app_measurement.db"), "rw").getChannel();
            this.zzash = this.zzasi.tryLock();
            if (this.zzash != null) {
                this.zzacv.zzgi().zzjc().log("Storage concurrent access okay");
                return true;
            }
            this.zzacv.zzgi().zziv().log("Storage concurrent data access panic");
            return false;
        } catch (FileNotFoundException e2) {
            e = e2;
            zziv = this.zzacv.zzgi().zziv();
            str = "Failed to acquire storage lock";
            zziv.zzg(str, e);
            return false;
        } catch (IOException e3) {
            e = e3;
            zziv = this.zzacv.zzgi().zziv();
            str = "Failed to access storage lock file";
            zziv.zzg(str, e);
            return false;
        }
    }

    @WorkerThread
    private final boolean zzlk() {
        zzab();
        zzlc();
        return this.zzarz;
    }

    public final Context getContext() {
        return this.zzacv.getContext();
    }

    @WorkerThread
    protected final void start() {
        this.zzacv.zzgh().zzab();
        zzjh().zzhx();
        if (this.zzacv.zzgj().zzalt.get() == 0) {
            this.zzacv.zzgj().zzalt.set(this.zzacv.zzbt().currentTimeMillis());
        }
        zzlg();
    }

    @WorkerThread
    @VisibleForTesting
    final void zza(int i, Throwable th, byte[] bArr, String str) {
        zzab();
        zzlc();
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Throwable th2) {
                this.zzasf = false;
                zzlh();
            }
        }
        List<Long> list = this.zzasj;
        this.zzasj = null;
        int i2 = 1;
        if ((i == 200 || i == 204) && th == null) {
            try {
                this.zzacv.zzgj().zzalt.set(this.zzacv.zzbt().currentTimeMillis());
                this.zzacv.zzgj().zzalu.set(0);
                zzlg();
                this.zzacv.zzgi().zzjc().zze("Successful upload. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                zzjh().beginTransaction();
                try {
                    for (Long l : list) {
                        zzhi zzjh;
                        try {
                            zzjh = zzjh();
                            long longValue = l.longValue();
                            zzjh.zzab();
                            zzjh.zzch();
                            if (zzjh.getWritableDatabase().delete("queue", "rowid=?", new String[]{String.valueOf(longValue)}) != 1) {
                                throw new SQLiteException("Deleted fewer rows from queue than expected");
                            }
                        } catch (SQLiteException e) {
                            zzjh.zzgi().zziv().zzg("Failed to delete a bundle in a queue table", e);
                            throw e;
                        } catch (SQLiteException e2) {
                            if (this.zzask == null || !this.zzask.contains(l)) {
                                throw e2;
                            }
                        }
                    }
                    zzjh().setTransactionSuccessful();
                    this.zzask = null;
                    if (zzkz().zzex() && zzlf()) {
                        zzle();
                    } else {
                        this.zzasl = -1;
                        zzlg();
                    }
                    this.zzasa = 0;
                } finally {
                    zzjh().endTransaction();
                }
            } catch (SQLiteException e3) {
                this.zzacv.zzgi().zziv().zzg("Database error while trying to delete uploaded bundles", e3);
                this.zzasa = this.zzacv.zzbt().elapsedRealtime();
                this.zzacv.zzgi().zzjc().zzg("Disable upload, time", Long.valueOf(this.zzasa));
            }
        } else {
            this.zzacv.zzgi().zzjc().zze("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
            this.zzacv.zzgj().zzalu.set(this.zzacv.zzbt().currentTimeMillis());
            if (i != 503) {
                if (i != 429) {
                    i2 = 0;
                }
            }
            if (i2 != 0) {
                this.zzacv.zzgj().zzalv.set(this.zzacv.zzbt().currentTimeMillis());
            }
            if (this.zzacv.zzgk().zzay(str)) {
                zzjh().zzc(list);
            }
            zzlg();
        }
        this.zzasf = false;
        zzlh();
    }

    @WorkerThread
    public final byte[] zza(@NonNull zzex zzex, @Size(min = 1) String str) {
        byte[] bArr;
        zzex zzex2 = zzex;
        String str2 = str;
        zzlc();
        zzab();
        this.zzacv.zzfu();
        Preconditions.checkNotNull(zzex);
        Preconditions.checkNotEmpty(str);
        zzacj zzkt = new zzkt();
        zzjh().beginTransaction();
        try {
            zzea zzbf = zzjh().zzbf(str2);
            if (zzbf == null) {
                r1.zzacv.zzgi().zzjb().zzg("Log and bundle not available. package_name", str2);
            } else if (zzbf.isMeasurementEnabled()) {
                zzkc zzh;
                zzku zzku;
                long j;
                zzea zzea;
                Bundle bundle;
                zzacj zzacj;
                long j2;
                if (("_iap".equals(zzex2.name) || Event.ECOMMERCE_PURCHASE.equals(zzex2.name)) && !zza(str2, zzex2)) {
                    r1.zzacv.zzgi().zziy().zzg("Failed to handle purchase event at single event bundle creation. appId", zzfi.zzbp(str));
                }
                boolean zzaw = r1.zzacv.zzgk().zzaw(str2);
                Long valueOf = Long.valueOf(0);
                if (zzaw && "_e".equals(zzex2.name)) {
                    zzfk zziy;
                    String str3;
                    Object zzbp;
                    if (zzex2.zzahg != null) {
                        if (zzex2.zzahg.size() != 0) {
                            if (zzex2.zzahg.getLong("_et") == null) {
                                zziy = r1.zzacv.zzgi().zziy();
                                str3 = "The engagement event does not include duration. appId";
                                zzbp = zzfi.zzbp(str);
                                zziy.zzg(str3, zzbp);
                            } else {
                                valueOf = zzex2.zzahg.getLong("_et");
                            }
                        }
                    }
                    zziy = r1.zzacv.zzgi().zziy();
                    str3 = "The engagement event does not contain any parameters. appId";
                    zzbp = zzfi.zzbp(str);
                    zziy.zzg(str3, zzbp);
                }
                zzku zzku2 = new zzku();
                zzkt.zzavf = new zzku[]{zzku2};
                zzku2.zzavh = Integer.valueOf(1);
                zzku2.zzavp = "android";
                zzku2.zzth = zzbf.zzah();
                zzku2.zzafh = zzbf.zzgv();
                zzku2.zztg = zzbf.zzag();
                long zzgu = zzbf.zzgu();
                zzacj zzacj2 = zzkt;
                zzku2.zzawb = zzgu == -2147483648L ? null : Integer.valueOf((int) zzgu);
                zzku2.zzavt = Long.valueOf(zzbf.zzgw());
                zzku2.zzafa = zzbf.getGmpAppId();
                zzku2.zzavx = Long.valueOf(zzbf.zzgx());
                if (r1.zzacv.isEnabled() && zzeh.zzht() && r1.zzacv.zzgk().zzau(zzku2.zzth)) {
                    zzku2.zzawh = null;
                }
                Pair zzbr = r1.zzacv.zzgj().zzbr(zzbf.zzah());
                if (!(!zzbf.zzhl() || zzbr == null || TextUtils.isEmpty((CharSequence) zzbr.first))) {
                    zzku2.zzavv = (String) zzbr.first;
                    zzku2.zzavw = (Boolean) zzbr.second;
                }
                r1.zzacv.zzge().zzch();
                zzku2.zzavr = Build.MODEL;
                r1.zzacv.zzge().zzch();
                zzku2.zzavq = VERSION.RELEASE;
                zzku2.zzavs = Integer.valueOf((int) r1.zzacv.zzge().zzik());
                zzku2.zzahd = r1.zzacv.zzge().zzil();
                zzku2.zzaez = zzbf.getAppInstanceId();
                zzku2.zzafc = zzbf.zzgr();
                List zzbe = zzjh().zzbe(zzbf.zzah());
                zzku2.zzavj = new zzkx[zzbe.size()];
                if (zzaw) {
                    zzh = zzjh().zzh(zzku2.zzth, "_lte");
                    if (zzh != null) {
                        if (zzh.value != null) {
                            if (valueOf.longValue() > 0) {
                                zzh = new zzkc(zzku2.zzth, "auto", "_lte", r1.zzacv.zzbt().currentTimeMillis(), Long.valueOf(((Long) zzh.value).longValue() + valueOf.longValue()));
                            }
                        }
                    }
                    zzkc zzkc = new zzkc(zzku2.zzth, "auto", "_lte", r1.zzacv.zzbt().currentTimeMillis(), valueOf);
                } else {
                    zzh = null;
                }
                zzkx zzkx = null;
                for (int i = 0; i < zzbe.size(); i++) {
                    zzkx zzkx2 = new zzkx();
                    zzku2.zzavj[i] = zzkx2;
                    zzkx2.name = ((zzkc) zzbe.get(i)).name;
                    zzkx2.zzaws = Long.valueOf(((zzkc) zzbe.get(i)).zzast);
                    zzjf().zza(zzkx2, ((zzkc) zzbe.get(i)).value);
                    if (zzaw && "_lte".equals(zzkx2.name)) {
                        zzkx2.zzave = (Long) zzh.value;
                        zzkx2.zzaws = Long.valueOf(r1.zzacv.zzbt().currentTimeMillis());
                        zzkx = zzkx2;
                    }
                }
                if (zzaw && r10 == null) {
                    zzkx zzkx3 = new zzkx();
                    zzkx3.name = "_lte";
                    zzkx3.zzaws = Long.valueOf(r1.zzacv.zzbt().currentTimeMillis());
                    zzkx3.zzave = (Long) zzh.value;
                    zzku2.zzavj = (zzkx[]) Arrays.copyOf(zzku2.zzavj, zzku2.zzavj.length + 1);
                    zzku2.zzavj[zzku2.zzavj.length - 1] = zzkx3;
                }
                if (valueOf.longValue() > 0) {
                    zzjh().zza(zzh);
                }
                Bundle zzin = zzex2.zzahg.zzin();
                if ("_iap".equals(zzex2.name)) {
                    zzin.putLong("_c", 1);
                    r1.zzacv.zzgi().zzjb().log("Marking in-app purchase as real-time");
                    zzin.putLong("_r", 1);
                }
                zzin.putString("_o", zzex2.origin);
                if (r1.zzacv.zzgg().zzcn(zzku2.zzth)) {
                    r1.zzacv.zzgg().zza(zzin, "_dbg", Long.valueOf(1));
                    r1.zzacv.zzgg().zza(zzin, "_r", Long.valueOf(1));
                }
                zzet zzf = zzjh().zzf(str2, zzex2.name);
                if (zzf == null) {
                    zzku = zzku2;
                    j = 0;
                    zzea = zzbf;
                    zzet zzet = zzf;
                    bundle = zzin;
                    zzacj = zzacj2;
                    bArr = null;
                    zzf = new zzet(str2, zzex2.name, 1, 0, zzex2.zzahr, 0, null, null, null);
                    zzjh().zza(zzet);
                    j2 = j;
                } else {
                    zzku = zzku2;
                    j = 0;
                    zzea = zzbf;
                    bundle = zzin;
                    zzacj = zzacj2;
                    bArr = null;
                    long j3 = zzf.zzahj;
                    zzjh().zza(zzf.zzah(zzex2.zzahr).zzim());
                    j2 = j3;
                }
                zzes zzes = new zzes(r1.zzacv, zzex2.origin, str, zzex2.name, zzex2.zzahr, j2, bundle);
                zzkr zzkr = new zzkr();
                zzku zzku3 = zzku;
                zzku3.zzavi = new zzkr[]{zzkr};
                zzkr.zzavb = Long.valueOf(zzes.timestamp);
                zzkr.name = zzes.name;
                zzkr.zzavc = Long.valueOf(zzes.zzahf);
                zzkr.zzava = new zzks[zzes.zzahg.size()];
                Iterator it = zzes.zzahg.iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    String str4 = (String) it.next();
                    zzks zzks = new zzks();
                    int i3 = i2 + 1;
                    zzkr.zzava[i2] = zzks;
                    zzks.name = str4;
                    zzjf().zza(zzks, zzes.zzahg.get(str4));
                    i2 = i3;
                }
                zzea zzea2 = zzea;
                zzku3.zzawa = zza(zzea2.zzah(), zzku3.zzavj, zzku3.zzavi);
                zzku3.zzavl = zzkr.zzavb;
                zzku3.zzavm = zzkr.zzavb;
                long zzgt = zzea2.zzgt();
                zzku3.zzavo = zzgt != j ? Long.valueOf(zzgt) : bArr;
                long zzgs = zzea2.zzgs();
                if (zzgs != j) {
                    zzgt = zzgs;
                }
                zzku3.zzavn = zzgt != j ? Long.valueOf(zzgt) : bArr;
                zzea2.zzhb();
                zzku3.zzavy = Integer.valueOf((int) zzea2.zzgy());
                zzku3.zzavu = Long.valueOf(r1.zzacv.zzgk().zzgw());
                zzku3.zzavk = Long.valueOf(r1.zzacv.zzbt().currentTimeMillis());
                zzku3.zzavz = Boolean.TRUE;
                zzea2.zzr(zzku3.zzavl.longValue());
                zzea2.zzs(zzku3.zzavm.longValue());
                zzjh().zza(zzea2);
                zzjh().setTransactionSuccessful();
                zzjh().endTransaction();
                zzacj zzacj3 = zzacj;
                try {
                    byte[] bArr2 = new byte[zzacj3.zzwb()];
                    zzacb zzb = zzacb.zzb(bArr2, 0, bArr2.length);
                    zzacj3.zza(zzb);
                    zzb.zzvt();
                    return zzjf().zzb(bArr2);
                } catch (IOException e) {
                    r1.zzacv.zzgi().zziv().zze("Data loss. Failed to bundle and serialize. appId", zzfi.zzbp(str), e);
                    return bArr;
                }
            } else {
                r1.zzacv.zzgi().zzjb().zzg("Log and bundle disabled. package_name", str2);
            }
            byte[] bArr3 = new byte[0];
            zzjh().endTransaction();
            return bArr3;
        } catch (Throwable th) {
            Throwable th2 = th;
            zzjh().endTransaction();
        }
    }

    @WorkerThread
    final void zzb(zzef zzef, zzeb zzeb) {
        Preconditions.checkNotNull(zzef);
        Preconditions.checkNotEmpty(zzef.packageName);
        Preconditions.checkNotNull(zzef.origin);
        Preconditions.checkNotNull(zzef.zzage);
        Preconditions.checkNotEmpty(zzef.zzage.name);
        zzab();
        zzlc();
        if (!TextUtils.isEmpty(zzeb.zzafa)) {
            if (zzeb.zzafk) {
                zzef zzef2 = new zzef(zzef);
                boolean z = false;
                zzef2.active = false;
                zzjh().beginTransaction();
                try {
                    zzfk zzjb;
                    String str;
                    Object obj;
                    Object zzbo;
                    Object value;
                    zzef zzi = zzjh().zzi(zzef2.packageName, zzef2.zzage.name);
                    if (!(zzi == null || zzi.origin.equals(zzef2.origin))) {
                        this.zzacv.zzgi().zziy().zzd("Updating a conditional user property with different origin. name, origin, origin (from DB)", this.zzacv.zzgf().zzbo(zzef2.zzage.name), zzef2.origin, zzi.origin);
                    }
                    if (zzi != null && zzi.active) {
                        zzef2.origin = zzi.origin;
                        zzef2.creationTimestamp = zzi.creationTimestamp;
                        zzef2.triggerTimeout = zzi.triggerTimeout;
                        zzef2.triggerEventName = zzi.triggerEventName;
                        zzef2.zzagg = zzi.zzagg;
                        zzef2.active = zzi.active;
                        zzef2.zzage = new zzka(zzef2.zzage.name, zzi.zzage.zzast, zzef2.zzage.getValue(), zzi.zzage.origin);
                    } else if (TextUtils.isEmpty(zzef2.triggerEventName)) {
                        zzef2.zzage = new zzka(zzef2.zzage.name, zzef2.creationTimestamp, zzef2.zzage.getValue(), zzef2.zzage.origin);
                        zzef2.active = true;
                        z = true;
                    }
                    if (zzef2.active) {
                        zzfk zzjb2;
                        String str2;
                        Object obj2;
                        Object zzbo2;
                        Object obj3;
                        zzka zzka = zzef2.zzage;
                        zzkc zzkc = new zzkc(zzef2.packageName, zzef2.origin, zzka.name, zzka.zzast, zzka.getValue());
                        if (zzjh().zza(zzkc)) {
                            zzjb2 = this.zzacv.zzgi().zzjb();
                            str2 = "User property updated immediately";
                            obj2 = zzef2.packageName;
                            zzbo2 = this.zzacv.zzgf().zzbo(zzkc.name);
                            obj3 = zzkc.value;
                        } else {
                            zzjb2 = this.zzacv.zzgi().zziv();
                            str2 = "(2)Too many active user properties, ignoring";
                            obj2 = zzfi.zzbp(zzef2.packageName);
                            zzbo2 = this.zzacv.zzgf().zzbo(zzkc.name);
                            obj3 = zzkc.value;
                        }
                        zzjb2.zzd(str2, obj2, zzbo2, obj3);
                        if (z && zzef2.zzagg != null) {
                            zzc(new zzex(zzef2.zzagg, zzef2.creationTimestamp), zzeb);
                        }
                    }
                    if (zzjh().zza(zzef2)) {
                        zzjb = this.zzacv.zzgi().zzjb();
                        str = "Conditional property added";
                        obj = zzef2.packageName;
                        zzbo = this.zzacv.zzgf().zzbo(zzef2.zzage.name);
                        value = zzef2.zzage.getValue();
                    } else {
                        zzjb = this.zzacv.zzgi().zziv();
                        str = "Too many conditional properties, ignoring";
                        obj = zzfi.zzbp(zzef2.packageName);
                        zzbo = this.zzacv.zzgf().zzbo(zzef2.zzage.name);
                        value = zzef2.zzage.getValue();
                    }
                    zzjb.zzd(str, obj, zzbo, value);
                    zzjh().setTransactionSuccessful();
                } finally {
                    zzjh().endTransaction();
                }
            } else {
                zzg(zzeb);
            }
        }
    }

    @WorkerThread
    final void zzb(zzex zzex, zzeb zzeb) {
        zzjt zzjt = this;
        zzex zzex2 = zzex;
        zzeb zzeb2 = zzeb;
        Preconditions.checkNotNull(zzeb);
        Preconditions.checkNotEmpty(zzeb2.packageName);
        zzab();
        zzlc();
        String str = zzeb2.packageName;
        long j = zzex2.zzahr;
        if (!zzjf().zzd(zzex2, zzeb2)) {
            return;
        }
        if (zzeb2.zzafk) {
            zzjh().beginTransaction();
            try {
                List emptyList;
                Object obj;
                List emptyList2;
                zzhi zzjh = zzjh();
                Preconditions.checkNotEmpty(str);
                zzjh.zzab();
                zzjh.zzch();
                if (j < 0) {
                    zzjh.zzgi().zziy().zze("Invalid time querying timed out conditional properties", zzfi.zzbp(str), Long.valueOf(j));
                    emptyList = Collections.emptyList();
                } else {
                    emptyList = zzjh.zzb("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str, String.valueOf(j)});
                }
                for (zzef zzef : r5) {
                    if (zzef != null) {
                        zzjt.zzacv.zzgi().zzjb().zzd("User property timed out", zzef.packageName, zzjt.zzacv.zzgf().zzbo(zzef.zzage.name), zzef.zzage.getValue());
                        if (zzef.zzagf != null) {
                            zzc(new zzex(zzef.zzagf, j), zzeb2);
                        }
                        zzjh().zzj(str, zzef.zzage.name);
                    }
                }
                zzjh = zzjh();
                Preconditions.checkNotEmpty(str);
                zzjh.zzab();
                zzjh.zzch();
                if (j < 0) {
                    zzjh.zzgi().zziy().zze("Invalid time querying expired conditional properties", zzfi.zzbp(str), Long.valueOf(j));
                    emptyList = Collections.emptyList();
                } else {
                    emptyList = zzjh.zzb("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str, String.valueOf(j)});
                }
                List arrayList = new ArrayList(r5.size());
                for (zzef zzef2 : r5) {
                    if (zzef2 != null) {
                        zzjt.zzacv.zzgi().zzjb().zzd("User property expired", zzef2.packageName, zzjt.zzacv.zzgf().zzbo(zzef2.zzage.name), zzef2.zzage.getValue());
                        zzjh().zzg(str, zzef2.zzage.name);
                        if (zzef2.zzagh != null) {
                            arrayList.add(zzef2.zzagh);
                        }
                        zzjh().zzj(str, zzef2.zzage.name);
                    }
                }
                ArrayList arrayList2 = (ArrayList) arrayList;
                int size = arrayList2.size();
                int i = 0;
                while (i < size) {
                    obj = arrayList2.get(i);
                    i++;
                    zzc(new zzex((zzex) obj, j), zzeb2);
                }
                zzjh = zzjh();
                String str2 = zzex2.name;
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotEmpty(str2);
                zzjh.zzab();
                zzjh.zzch();
                if (j < 0) {
                    zzjh.zzgi().zziy().zzd("Invalid time querying triggered conditional properties", zzfi.zzbp(str), zzjh.zzgf().zzbm(str2), Long.valueOf(j));
                    emptyList2 = Collections.emptyList();
                } else {
                    emptyList2 = zzjh.zzb("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str, str2, String.valueOf(j)});
                }
                List arrayList3 = new ArrayList(emptyList2.size());
                Iterator it = emptyList2.iterator();
                while (it.hasNext()) {
                    zzef zzef3 = (zzef) it.next();
                    if (zzef3 != null) {
                        zzfk zzjb;
                        Object zzbo;
                        Object obj2;
                        zzka zzka = zzef3.zzage;
                        zzkc zzkc = r5;
                        Iterator it2 = it;
                        zzef zzef4 = zzef3;
                        zzkc zzkc2 = new zzkc(zzef3.packageName, zzef3.origin, zzka.name, j, zzka.getValue());
                        if (zzjh().zza(zzkc)) {
                            zzjb = zzjt.zzacv.zzgi().zzjb();
                            str2 = "User property triggered";
                            obj = zzef4.packageName;
                            zzbo = zzjt.zzacv.zzgf().zzbo(zzkc.name);
                            obj2 = zzkc.value;
                        } else {
                            zzjb = zzjt.zzacv.zzgi().zziv();
                            str2 = "Too many active user properties, ignoring";
                            obj = zzfi.zzbp(zzef4.packageName);
                            zzbo = zzjt.zzacv.zzgf().zzbo(zzkc.name);
                            obj2 = zzkc.value;
                        }
                        zzjb.zzd(str2, obj, zzbo, obj2);
                        if (zzef4.zzagg != null) {
                            arrayList3.add(zzef4.zzagg);
                        }
                        zzef4.zzage = new zzka(zzkc);
                        zzef4.active = true;
                        zzjh().zza(zzef4);
                        it = it2;
                    }
                }
                zzc(zzex, zzeb);
                ArrayList arrayList4 = (ArrayList) arrayList3;
                int size2 = arrayList4.size();
                int i2 = 0;
                while (i2 < size2) {
                    Object obj3 = arrayList4.get(i2);
                    i2++;
                    zzc(new zzex((zzex) obj3, j), zzeb2);
                }
                zzjh().setTransactionSuccessful();
                zzjh().endTransaction();
            } catch (Throwable th) {
                Throwable th2 = th;
                zzjh().endTransaction();
            }
        } else {
            zzg(zzeb2);
        }
    }

    final void zzb(zzjs zzjs) {
        this.zzasc++;
    }

    @WorkerThread
    final void zzb(zzka zzka, zzeb zzeb) {
        zzab();
        zzlc();
        if (!TextUtils.isEmpty(zzeb.zzafa)) {
            if (zzeb.zzafk) {
                int zzcj = this.zzacv.zzgg().zzcj(zzka.name);
                if (zzcj != 0) {
                    this.zzacv.zzgg();
                    this.zzacv.zzgg().zza(zzeb.packageName, zzcj, "_ev", zzkd.zza(zzka.name, 24, true), zzka.name != null ? zzka.name.length() : 0);
                    return;
                }
                int zzi = this.zzacv.zzgg().zzi(zzka.name, zzka.getValue());
                if (zzi != 0) {
                    this.zzacv.zzgg();
                    String zza = zzkd.zza(zzka.name, 24, true);
                    Object value = zzka.getValue();
                    int length = (value == null || !((value instanceof String) || (value instanceof CharSequence))) ? 0 : String.valueOf(value).length();
                    this.zzacv.zzgg().zza(zzeb.packageName, zzi, "_ev", zza, length);
                    return;
                }
                Object zzj = this.zzacv.zzgg().zzj(zzka.name, zzka.getValue());
                if (zzj != null) {
                    zzkc zzkc = new zzkc(zzeb.packageName, zzka.origin, zzka.name, zzka.zzast, zzj);
                    this.zzacv.zzgi().zzjb().zze("Setting user property", this.zzacv.zzgf().zzbo(zzkc.name), zzj);
                    zzjh().beginTransaction();
                    try {
                        zzg(zzeb);
                        boolean zza2 = zzjh().zza(zzkc);
                        zzjh().setTransactionSuccessful();
                        if (zza2) {
                            this.zzacv.zzgi().zzjb().zze("User property set", this.zzacv.zzgf().zzbo(zzkc.name), zzkc.value);
                        } else {
                            this.zzacv.zzgi().zziv().zze("Too many unique user properties are set. Ignoring user property", this.zzacv.zzgf().zzbo(zzkc.name), zzkc.value);
                            this.zzacv.zzgg().zza(zzeb.packageName, 9, null, null, 0);
                        }
                        zzjh().endTransaction();
                        return;
                    } catch (Throwable th) {
                        zzjh().endTransaction();
                    }
                } else {
                    return;
                }
            }
            zzg(zzeb);
        }
    }

    @WorkerThread
    @VisibleForTesting
    final void zzb(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        zzek zzjh;
        zzab();
        zzlc();
        Preconditions.checkNotEmpty(str);
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Throwable th2) {
                this.zzase = false;
                zzlh();
            }
        }
        this.zzacv.zzgi().zzjc().zzg("onConfigFetched. Response size", Integer.valueOf(bArr.length));
        zzjh().beginTransaction();
        zzea zzbf = zzjh().zzbf(str);
        Object obj = 1;
        Object obj2 = ((i == 200 || i == 204 || i == 304) && th == null) ? 1 : null;
        if (zzbf == null) {
            this.zzacv.zzgi().zziy().zzg("App does not exist in onConfigFetched. appId", zzfi.zzbp(str));
            zzjh().setTransactionSuccessful();
            zzjh = zzjh();
        } else {
            if (obj2 == null) {
                if (i != 404) {
                    zzbf.zzy(this.zzacv.zzbt().currentTimeMillis());
                    zzjh().zza(zzbf);
                    this.zzacv.zzgi().zzjc().zze("Fetching config failed. code, error", Integer.valueOf(i), th);
                    zzky().zzbz(str);
                    this.zzacv.zzgj().zzalu.set(this.zzacv.zzbt().currentTimeMillis());
                    if (i != 503) {
                        if (i != 429) {
                            obj = null;
                        }
                    }
                    if (obj != null) {
                        this.zzacv.zzgj().zzalv.set(this.zzacv.zzbt().currentTimeMillis());
                    }
                    zzlg();
                    zzjh().setTransactionSuccessful();
                    zzjh = zzjh();
                }
            }
            List list = map != null ? (List) map.get(HttpRequest.HEADER_LAST_MODIFIED) : null;
            String str2 = (list == null || list.size() <= 0) ? null : (String) list.get(0);
            if (i != 404) {
                if (i != 304) {
                    if (!zzky().zza(str, bArr, str2)) {
                        zzjh = zzjh();
                    }
                    zzbf.zzx(this.zzacv.zzbt().currentTimeMillis());
                    zzjh().zza(zzbf);
                    if (i != 404) {
                        this.zzacv.zzgi().zziz().zzg("Config not found. Using empty config. appId", str);
                    } else {
                        this.zzacv.zzgi().zzjc().zze("Successfully fetched config. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                    }
                    if (zzkz().zzex() && zzlf()) {
                        zzle();
                        zzjh().setTransactionSuccessful();
                        zzjh = zzjh();
                    }
                    zzlg();
                    zzjh().setTransactionSuccessful();
                    zzjh = zzjh();
                }
            }
            if (zzky().zzbx(str) == null && !zzky().zza(str, null, null)) {
                zzjh = zzjh();
            }
            zzbf.zzx(this.zzacv.zzbt().currentTimeMillis());
            zzjh().zza(zzbf);
            if (i != 404) {
                this.zzacv.zzgi().zzjc().zze("Successfully fetched config. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
            } else {
                this.zzacv.zzgi().zziz().zzg("Config not found. Using empty config. appId", str);
            }
            zzle();
            zzjh().setTransactionSuccessful();
            zzjh = zzjh();
        }
        zzjh.endTransaction();
        this.zzase = false;
        zzlh();
    }

    public final Clock zzbt() {
        return this.zzacv.zzbt();
    }

    @WorkerThread
    final void zzc(zzef zzef, zzeb zzeb) {
        Preconditions.checkNotNull(zzef);
        Preconditions.checkNotEmpty(zzef.packageName);
        Preconditions.checkNotNull(zzef.zzage);
        Preconditions.checkNotEmpty(zzef.zzage.name);
        zzab();
        zzlc();
        if (!TextUtils.isEmpty(zzeb.zzafa)) {
            if (zzeb.zzafk) {
                zzjh().beginTransaction();
                try {
                    zzg(zzeb);
                    zzef zzi = zzjh().zzi(zzef.packageName, zzef.zzage.name);
                    if (zzi != null) {
                        this.zzacv.zzgi().zzjb().zze("Removing conditional user property", zzef.packageName, this.zzacv.zzgf().zzbo(zzef.zzage.name));
                        zzjh().zzj(zzef.packageName, zzef.zzage.name);
                        if (zzi.active) {
                            zzjh().zzg(zzef.packageName, zzef.zzage.name);
                        }
                        if (zzef.zzagh != null) {
                            Bundle bundle = null;
                            if (zzef.zzagh.zzahg != null) {
                                bundle = zzef.zzagh.zzahg.zzin();
                            }
                            Bundle bundle2 = bundle;
                            zzc(this.zzacv.zzgg().zza(zzef.packageName, zzef.zzagh.name, bundle2, zzi.origin, zzef.zzagh.zzahr, true, false), zzeb);
                        }
                    } else {
                        this.zzacv.zzgi().zziy().zze("Conditional user property doesn't exist", zzfi.zzbp(zzef.packageName), this.zzacv.zzgf().zzbo(zzef.zzage.name));
                    }
                    zzjh().setTransactionSuccessful();
                } finally {
                    zzjh().endTransaction();
                }
            } else {
                zzg(zzeb);
            }
        }
    }

    @WorkerThread
    final void zzc(zzex zzex, String str) {
        zzjt zzjt = this;
        zzex zzex2 = zzex;
        String str2 = str;
        zzea zzbf = zzjh().zzbf(str2);
        if (zzbf != null) {
            if (!TextUtils.isEmpty(zzbf.zzag())) {
                Boolean zzc = zzc(zzbf);
                if (zzc == null) {
                    if (!"_ui".equals(zzex2.name)) {
                        zzjt.zzacv.zzgi().zziy().zzg("Could not find package. appId", zzfi.zzbp(str));
                    }
                } else if (!zzc.booleanValue()) {
                    zzjt.zzacv.zzgi().zziv().zzg("App version does not match; dropping event. appId", zzfi.zzbp(str));
                    return;
                }
                zzeb zzeb = r2;
                zzeb zzeb2 = new zzeb(str2, zzbf.getGmpAppId(), zzbf.zzag(), zzbf.zzgu(), zzbf.zzgv(), zzbf.zzgw(), zzbf.zzgx(), null, zzbf.isMeasurementEnabled(), false, zzbf.zzgr(), zzbf.zzhk(), 0, 0, zzbf.zzhl(), zzbf.zzhm(), false);
                zzb(zzex2, zzeb);
                return;
            }
        }
        zzjt.zzacv.zzgi().zzjb().zzg("No app data available; dropping event", str2);
    }

    @WorkerThread
    final void zzc(zzka zzka, zzeb zzeb) {
        zzab();
        zzlc();
        if (!TextUtils.isEmpty(zzeb.zzafa)) {
            if (zzeb.zzafk) {
                this.zzacv.zzgi().zzjb().zzg("Removing user property", this.zzacv.zzgf().zzbo(zzka.name));
                zzjh().beginTransaction();
                try {
                    zzg(zzeb);
                    zzjh().zzg(zzeb.packageName, zzka.name);
                    zzjh().setTransactionSuccessful();
                    this.zzacv.zzgi().zzjb().zzg("User property removed", this.zzacv.zzgf().zzbo(zzka.name));
                } finally {
                    zzjh().endTransaction();
                }
            } else {
                zzg(zzeb);
            }
        }
    }

    @WorkerThread
    @VisibleForTesting
    final void zzd(zzeb zzeb) {
        if (this.zzasj != null) {
            this.zzask = new ArrayList();
            this.zzask.addAll(this.zzasj);
        }
        zzhi zzjh = zzjh();
        String str = zzeb.packageName;
        Preconditions.checkNotEmpty(str);
        zzjh.zzab();
        zzjh.zzch();
        try {
            SQLiteDatabase writableDatabase = zzjh.getWritableDatabase();
            String[] strArr = new String[]{str};
            int delete = ((((((((writableDatabase.delete("apps", "app_id=?", strArr) + 0) + writableDatabase.delete("events", "app_id=?", strArr)) + writableDatabase.delete("user_attributes", "app_id=?", strArr)) + writableDatabase.delete("conditional_properties", "app_id=?", strArr)) + writableDatabase.delete("raw_events", "app_id=?", strArr)) + writableDatabase.delete("raw_events_metadata", "app_id=?", strArr)) + writableDatabase.delete("queue", "app_id=?", strArr)) + writableDatabase.delete("audience_filter_values", "app_id=?", strArr)) + writableDatabase.delete("main_event_params", "app_id=?", strArr);
            if (delete > 0) {
                zzjh.zzgi().zzjc().zze("Reset analytics data. app, records", str, Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzjh.zzgi().zziv().zze("Error resetting analytics data. appId, error", zzfi.zzbp(str), e);
        }
        zzeb zza = zza(this.zzacv.getContext(), zzeb.packageName, zzeb.zzafa, zzeb.zzafk, zzeb.zzafm, zzeb.zzafn, zzeb.zzaga);
        if (!this.zzacv.zzgk().zzbc(zzeb.packageName) || zzeb.zzafk) {
            zzf(zza);
        }
    }

    final void zze(zzeb zzeb) {
        zzab();
        zzlc();
        Preconditions.checkNotEmpty(zzeb.packageName);
        zzg(zzeb);
    }

    @WorkerThread
    final void zze(zzef zzef) {
        zzeb zzce = zzce(zzef.packageName);
        if (zzce != null) {
            zzb(zzef, zzce);
        }
    }

    @WorkerThread
    final void zzf(zzeb zzeb) {
        zzjt zzjt = this;
        zzeb zzeb2 = zzeb;
        zzab();
        zzlc();
        Preconditions.checkNotNull(zzeb);
        Preconditions.checkNotEmpty(zzeb2.packageName);
        if (!TextUtils.isEmpty(zzeb2.zzafa)) {
            zzea zzbf = zzjh().zzbf(zzeb2.packageName);
            if (!(zzbf == null || !TextUtils.isEmpty(zzbf.getGmpAppId()) || TextUtils.isEmpty(zzeb2.zzafa))) {
                zzbf.zzx(0);
                zzjh().zza(zzbf);
                zzky().zzca(zzeb2.packageName);
            }
            if (zzeb2.zzafk) {
                zzea zzbf2;
                zzex zzex;
                zzek zzjh;
                String str;
                String str2;
                zzet zzet;
                long j;
                long j2;
                Bundle bundle;
                PackageInfo packageInfo;
                Object obj;
                ApplicationInfo applicationInfo;
                zzhi zzjh2;
                long j3 = zzeb2.zzaga;
                if (j3 == 0) {
                    j3 = zzjt.zzacv.zzbt().currentTimeMillis();
                }
                int i = zzeb2.zzagb;
                if (!(i == 0 || i == 1)) {
                    zzjt.zzacv.zzgi().zziy().zze("Incorrect app type, assuming installed app. appId, appType", zzfi.zzbp(zzeb2.packageName), Integer.valueOf(i));
                    i = 0;
                }
                zzjh().beginTransaction();
                zzhi zzjh3;
                String zzah;
                try {
                    zzbf2 = zzjh().zzbf(zzeb2.packageName);
                    if (!(zzbf2 == null || zzbf2.getGmpAppId() == null || zzbf2.getGmpAppId().equals(zzeb2.zzafa))) {
                        zzjt.zzacv.zzgi().zziy().zzg("New GMP App Id passed in. Removing cached database data. appId", zzfi.zzbp(zzbf2.zzah()));
                        zzjh3 = zzjh();
                        zzah = zzbf2.zzah();
                        zzjh3.zzch();
                        zzjh3.zzab();
                        Preconditions.checkNotEmpty(zzah);
                        SQLiteDatabase writableDatabase = zzjh3.getWritableDatabase();
                        String[] strArr = new String[]{zzah};
                        int delete = ((((((((writableDatabase.delete("events", "app_id=?", strArr) + 0) + writableDatabase.delete("user_attributes", "app_id=?", strArr)) + writableDatabase.delete("conditional_properties", "app_id=?", strArr)) + writableDatabase.delete("apps", "app_id=?", strArr)) + writableDatabase.delete("raw_events", "app_id=?", strArr)) + writableDatabase.delete("raw_events_metadata", "app_id=?", strArr)) + writableDatabase.delete("event_filters", "app_id=?", strArr)) + writableDatabase.delete("property_filters", "app_id=?", strArr)) + writableDatabase.delete("audience_filter_values", "app_id=?", strArr);
                        if (delete > 0) {
                            zzjh3.zzgi().zzjc().zze("Deleted application data. app, records", zzah, Integer.valueOf(delete));
                        }
                        zzbf2 = null;
                    }
                } catch (SQLiteException e) {
                    zzjh3.zzgi().zziv().zze("Error deleting application data. appId, error", zzfi.zzbp(zzah), e);
                } catch (Throwable th) {
                    Throwable th2 = th;
                    zzjh().endTransaction();
                }
                if (zzbf2 != null) {
                    Bundle bundle2;
                    if (zzbf2.zzgu() != -2147483648L) {
                        if (zzbf2.zzgu() != zzeb2.zzafg) {
                            bundle2 = new Bundle();
                            bundle2.putString("_pv", zzbf2.zzag());
                            zzex = new zzex("_au", new zzeu(bundle2), "auto", j3);
                        }
                    } else if (!(zzbf2.zzag() == null || zzbf2.zzag().equals(zzeb2.zztg))) {
                        bundle2 = new Bundle();
                        bundle2.putString("_pv", zzbf2.zzag());
                        zzex = new zzex("_au", new zzeu(bundle2), "auto", j3);
                    }
                    zzb(r14, zzeb2);
                }
                zzg(zzeb);
                if (i == 0) {
                    zzjh = zzjh();
                    str = zzeb2.packageName;
                    str2 = "_f";
                } else if (i == 1) {
                    zzjh = zzjh();
                    str = zzeb2.packageName;
                    str2 = "_v";
                } else {
                    zzet = null;
                    if (zzet != null) {
                        j = 3600000 * ((j3 / 3600000) + 1);
                        if (i != 0) {
                            j2 = 1;
                            zzb(new zzka("_fot", j3, Long.valueOf(j), "auto"), zzeb2);
                            zzab();
                            zzlc();
                            bundle = new Bundle();
                            bundle.putLong("_c", j2);
                            bundle.putLong("_r", j2);
                            bundle.putLong("_uwa", 0);
                            bundle.putLong("_pfo", 0);
                            bundle.putLong("_sys", 0);
                            bundle.putLong("_sysu", 0);
                            if (zzjt.zzacv.zzgk().zzbc(zzeb2.packageName) && zzeb2.zzagc) {
                                bundle.putLong("_dac", j2);
                            }
                            if (zzjt.zzacv.getContext().getPackageManager() != null) {
                                zzjt.zzacv.zzgi().zziv().zzg("PackageManager is null, first open report might be inaccurate. appId", zzfi.zzbp(zzeb2.packageName));
                            } else {
                                try {
                                    packageInfo = Wrappers.packageManager(zzjt.zzacv.getContext()).getPackageInfo(zzeb2.packageName, 0);
                                } catch (NameNotFoundException e2) {
                                    zzjt.zzacv.zzgi().zziv().zze("Package info is null, first open report might be inaccurate. appId", zzfi.zzbp(zzeb2.packageName), e2);
                                    packageInfo = null;
                                }
                                if (!(packageInfo == null || packageInfo.firstInstallTime == 0)) {
                                    if (packageInfo.firstInstallTime == packageInfo.lastUpdateTime) {
                                        bundle.putLong("_uwa", j2);
                                        obj = null;
                                    } else {
                                        obj = 1;
                                    }
                                    zzb(new zzka("_fi", j3, Long.valueOf(obj == null ? j2 : 0), "auto"), zzeb2);
                                }
                                try {
                                    applicationInfo = Wrappers.packageManager(zzjt.zzacv.getContext()).getApplicationInfo(zzeb2.packageName, 0);
                                } catch (NameNotFoundException e22) {
                                    zzjt.zzacv.zzgi().zziv().zze("Application info is null, first open report might be inaccurate. appId", zzfi.zzbp(zzeb2.packageName), e22);
                                    applicationInfo = null;
                                }
                                if (applicationInfo != null) {
                                    if ((applicationInfo.flags & 1) != 0) {
                                        bundle.putLong("_sys", j2);
                                    }
                                    if ((applicationInfo.flags & 128) != 0) {
                                        bundle.putLong("_sysu", j2);
                                    }
                                }
                            }
                            zzjh2 = zzjh();
                            str = zzeb2.packageName;
                            Preconditions.checkNotEmpty(str);
                            zzjh2.zzab();
                            zzjh2.zzch();
                            j = zzjh2.zzm(str, "first_open_count");
                            if (j >= 0) {
                                bundle.putLong("_pfo", j);
                            }
                            zzex = new zzex("_f", new zzeu(bundle), "auto", j3);
                        } else {
                            j2 = 1;
                            if (i == 1) {
                                zzb(new zzka("_fvt", j3, Long.valueOf(j), "auto"), zzeb2);
                                zzab();
                                zzlc();
                                bundle = new Bundle();
                                bundle.putLong("_c", j2);
                                bundle.putLong("_r", j2);
                                if (zzjt.zzacv.zzgk().zzbc(zzeb2.packageName) && zzeb2.zzagc) {
                                    bundle.putLong("_dac", j2);
                                }
                                zzex = new zzex("_v", new zzeu(bundle), "auto", j3);
                            }
                            bundle = new Bundle();
                            bundle.putLong("_et", j2);
                            zzex = new zzex("_e", new zzeu(bundle), "auto", j3);
                        }
                        zzb(r14, zzeb2);
                        bundle = new Bundle();
                        bundle.putLong("_et", j2);
                        zzex = new zzex("_e", new zzeu(bundle), "auto", j3);
                    } else {
                        if (zzeb2.zzafz) {
                            zzex = new zzex("_cd", new zzeu(new Bundle()), "auto", j3);
                        }
                        zzjh().setTransactionSuccessful();
                        zzjh().endTransaction();
                        return;
                    }
                    zzb(r4, zzeb2);
                    zzjh().setTransactionSuccessful();
                    zzjh().endTransaction();
                    return;
                }
                zzet = zzjh.zzf(str, str2);
                if (zzet != null) {
                    if (zzeb2.zzafz) {
                        zzex = new zzex("_cd", new zzeu(new Bundle()), "auto", j3);
                    }
                    zzjh().setTransactionSuccessful();
                    zzjh().endTransaction();
                    return;
                }
                j = 3600000 * ((j3 / 3600000) + 1);
                if (i != 0) {
                    j2 = 1;
                    if (i == 1) {
                        zzb(new zzka("_fvt", j3, Long.valueOf(j), "auto"), zzeb2);
                        zzab();
                        zzlc();
                        bundle = new Bundle();
                        bundle.putLong("_c", j2);
                        bundle.putLong("_r", j2);
                        bundle.putLong("_dac", j2);
                        zzex = new zzex("_v", new zzeu(bundle), "auto", j3);
                    }
                    bundle = new Bundle();
                    bundle.putLong("_et", j2);
                    zzex = new zzex("_e", new zzeu(bundle), "auto", j3);
                } else {
                    j2 = 1;
                    zzb(new zzka("_fot", j3, Long.valueOf(j), "auto"), zzeb2);
                    zzab();
                    zzlc();
                    bundle = new Bundle();
                    bundle.putLong("_c", j2);
                    bundle.putLong("_r", j2);
                    bundle.putLong("_uwa", 0);
                    bundle.putLong("_pfo", 0);
                    bundle.putLong("_sys", 0);
                    bundle.putLong("_sysu", 0);
                    bundle.putLong("_dac", j2);
                    if (zzjt.zzacv.getContext().getPackageManager() != null) {
                        packageInfo = Wrappers.packageManager(zzjt.zzacv.getContext()).getPackageInfo(zzeb2.packageName, 0);
                        if (packageInfo.firstInstallTime == packageInfo.lastUpdateTime) {
                            obj = 1;
                        } else {
                            bundle.putLong("_uwa", j2);
                            obj = null;
                        }
                        if (obj == null) {
                        }
                        zzb(new zzka("_fi", j3, Long.valueOf(obj == null ? j2 : 0), "auto"), zzeb2);
                        applicationInfo = Wrappers.packageManager(zzjt.zzacv.getContext()).getApplicationInfo(zzeb2.packageName, 0);
                        if (applicationInfo != null) {
                            if ((applicationInfo.flags & 1) != 0) {
                                bundle.putLong("_sys", j2);
                            }
                            if ((applicationInfo.flags & 128) != 0) {
                                bundle.putLong("_sysu", j2);
                            }
                        }
                    } else {
                        zzjt.zzacv.zzgi().zziv().zzg("PackageManager is null, first open report might be inaccurate. appId", zzfi.zzbp(zzeb2.packageName));
                    }
                    zzjh2 = zzjh();
                    str = zzeb2.packageName;
                    Preconditions.checkNotEmpty(str);
                    zzjh2.zzab();
                    zzjh2.zzch();
                    j = zzjh2.zzm(str, "first_open_count");
                    if (j >= 0) {
                        bundle.putLong("_pfo", j);
                    }
                    zzex = new zzex("_f", new zzeu(bundle), "auto", j3);
                }
                zzb(r14, zzeb2);
                bundle = new Bundle();
                bundle.putLong("_et", j2);
                zzex = new zzex("_e", new zzeu(bundle), "auto", j3);
                zzb(r4, zzeb2);
                zzjh().setTransactionSuccessful();
                zzjh().endTransaction();
                return;
            }
            zzg(zzeb);
        }
    }

    @WorkerThread
    final void zzf(zzef zzef) {
        zzeb zzce = zzce(zzef.packageName);
        if (zzce != null) {
            zzc(zzef, zzce);
        }
    }

    @WorkerThread
    final void zzg(Runnable runnable) {
        zzab();
        if (this.zzasb == null) {
            this.zzasb = new ArrayList();
        }
        this.zzasb.add(runnable);
    }

    public final zzfg zzgf() {
        return this.zzacv.zzgf();
    }

    public final zzkd zzgg() {
        return this.zzacv.zzgg();
    }

    public final zzgi zzgh() {
        return this.zzacv.zzgh();
    }

    public final zzfi zzgi() {
        return this.zzacv.zzgi();
    }

    public final zzeh zzgk() {
        return this.zzacv.zzgk();
    }

    public final zzee zzgl() {
        return this.zzacv.zzgl();
    }

    final String zzh(zzeb zzeb) {
        try {
            return (String) this.zzacv.zzgh().zzb(new zzjx(this, zzeb)).get(30000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            this.zzacv.zzgi().zziv().zze("Failed to get app instance id. appId", zzfi.zzbp(zzeb.packageName), e);
            return null;
        }
    }

    public final zzjz zzjf() {
        zza(this.zzary);
        return this.zzary;
    }

    public final zzed zzjg() {
        zza(this.zzarx);
        return this.zzarx;
    }

    public final zzek zzjh() {
        zza(this.zzaru);
        return this.zzaru;
    }

    public final zzfm zzkz() {
        zza(this.zzart);
        return this.zzart;
    }

    final void zzlc() {
        if (!this.zzvn) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }

    @android.support.annotation.WorkerThread
    final void zzle() {
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
        r14 = this;
        r14.zzab();
        r14.zzlc();
        r0 = 1;
        r14.zzasg = r0;
        r1 = 0;
        r2 = r14.zzacv;	 Catch:{ all -> 0x02b9 }
        r2.zzgl();	 Catch:{ all -> 0x02b9 }
        r2 = r14.zzacv;	 Catch:{ all -> 0x02b9 }
        r2 = r2.zzga();	 Catch:{ all -> 0x02b9 }
        r2 = r2.zzkr();	 Catch:{ all -> 0x02b9 }
        if (r2 != 0) goto L_0x0030;	 Catch:{ all -> 0x02b9 }
    L_0x001b:
        r0 = r14.zzacv;	 Catch:{ all -> 0x02b9 }
        r0 = r0.zzgi();	 Catch:{ all -> 0x02b9 }
        r0 = r0.zziy();	 Catch:{ all -> 0x02b9 }
        r2 = "Upload data called on the client side before use of service was decided";	 Catch:{ all -> 0x02b9 }
    L_0x0027:
        r0.log(r2);	 Catch:{ all -> 0x02b9 }
    L_0x002a:
        r14.zzasg = r1;
        r14.zzlh();
        return;
    L_0x0030:
        r2 = r2.booleanValue();	 Catch:{ all -> 0x02b9 }
        if (r2 == 0) goto L_0x0043;	 Catch:{ all -> 0x02b9 }
    L_0x0036:
        r0 = r14.zzacv;	 Catch:{ all -> 0x02b9 }
        r0 = r0.zzgi();	 Catch:{ all -> 0x02b9 }
        r0 = r0.zziv();	 Catch:{ all -> 0x02b9 }
        r2 = "Upload called in the client side when service should be used";	 Catch:{ all -> 0x02b9 }
        goto L_0x0027;	 Catch:{ all -> 0x02b9 }
    L_0x0043:
        r2 = r14.zzasa;	 Catch:{ all -> 0x02b9 }
        r4 = 0;	 Catch:{ all -> 0x02b9 }
        r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));	 Catch:{ all -> 0x02b9 }
        if (r6 <= 0) goto L_0x004f;	 Catch:{ all -> 0x02b9 }
    L_0x004b:
        r14.zzlg();	 Catch:{ all -> 0x02b9 }
        goto L_0x002a;	 Catch:{ all -> 0x02b9 }
    L_0x004f:
        r14.zzab();	 Catch:{ all -> 0x02b9 }
        r2 = r14.zzasj;	 Catch:{ all -> 0x02b9 }
        if (r2 == 0) goto L_0x0058;	 Catch:{ all -> 0x02b9 }
    L_0x0056:
        r2 = 1;	 Catch:{ all -> 0x02b9 }
        goto L_0x0059;	 Catch:{ all -> 0x02b9 }
    L_0x0058:
        r2 = 0;	 Catch:{ all -> 0x02b9 }
    L_0x0059:
        if (r2 == 0) goto L_0x0068;	 Catch:{ all -> 0x02b9 }
    L_0x005b:
        r0 = r14.zzacv;	 Catch:{ all -> 0x02b9 }
        r0 = r0.zzgi();	 Catch:{ all -> 0x02b9 }
        r0 = r0.zzjc();	 Catch:{ all -> 0x02b9 }
        r2 = "Uploading requested multiple times";	 Catch:{ all -> 0x02b9 }
        goto L_0x0027;	 Catch:{ all -> 0x02b9 }
    L_0x0068:
        r2 = r14.zzkz();	 Catch:{ all -> 0x02b9 }
        r2 = r2.zzex();	 Catch:{ all -> 0x02b9 }
        if (r2 != 0) goto L_0x0082;	 Catch:{ all -> 0x02b9 }
    L_0x0072:
        r0 = r14.zzacv;	 Catch:{ all -> 0x02b9 }
        r0 = r0.zzgi();	 Catch:{ all -> 0x02b9 }
        r0 = r0.zzjc();	 Catch:{ all -> 0x02b9 }
        r2 = "Network not connected, ignoring upload request";	 Catch:{ all -> 0x02b9 }
        r0.log(r2);	 Catch:{ all -> 0x02b9 }
        goto L_0x004b;	 Catch:{ all -> 0x02b9 }
    L_0x0082:
        r2 = r14.zzacv;	 Catch:{ all -> 0x02b9 }
        r2 = r2.zzbt();	 Catch:{ all -> 0x02b9 }
        r2 = r2.currentTimeMillis();	 Catch:{ all -> 0x02b9 }
        r6 = com.google.android.gms.internal.measurement.zzeh.zzhr();	 Catch:{ all -> 0x02b9 }
        r8 = 0;	 Catch:{ all -> 0x02b9 }
        r8 = r2 - r6;	 Catch:{ all -> 0x02b9 }
        r6 = 0;	 Catch:{ all -> 0x02b9 }
        r14.zzd(r6, r8);	 Catch:{ all -> 0x02b9 }
        r7 = r14.zzacv;	 Catch:{ all -> 0x02b9 }
        r7 = r7.zzgj();	 Catch:{ all -> 0x02b9 }
        r7 = r7.zzalt;	 Catch:{ all -> 0x02b9 }
        r7 = r7.get();	 Catch:{ all -> 0x02b9 }
        r9 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1));	 Catch:{ all -> 0x02b9 }
        if (r9 == 0) goto L_0x00c1;	 Catch:{ all -> 0x02b9 }
    L_0x00a7:
        r4 = r14.zzacv;	 Catch:{ all -> 0x02b9 }
        r4 = r4.zzgi();	 Catch:{ all -> 0x02b9 }
        r4 = r4.zzjb();	 Catch:{ all -> 0x02b9 }
        r5 = "Uploading events. Elapsed time since last upload attempt (ms)";	 Catch:{ all -> 0x02b9 }
        r9 = 0;	 Catch:{ all -> 0x02b9 }
        r9 = r2 - r7;	 Catch:{ all -> 0x02b9 }
        r7 = java.lang.Math.abs(r9);	 Catch:{ all -> 0x02b9 }
        r7 = java.lang.Long.valueOf(r7);	 Catch:{ all -> 0x02b9 }
        r4.zzg(r5, r7);	 Catch:{ all -> 0x02b9 }
    L_0x00c1:
        r4 = r14.zzjh();	 Catch:{ all -> 0x02b9 }
        r4 = r4.zzhv();	 Catch:{ all -> 0x02b9 }
        r5 = android.text.TextUtils.isEmpty(r4);	 Catch:{ all -> 0x02b9 }
        r7 = -1;	 Catch:{ all -> 0x02b9 }
        if (r5 != 0) goto L_0x0293;	 Catch:{ all -> 0x02b9 }
    L_0x00d1:
        r9 = r14.zzasl;	 Catch:{ all -> 0x02b9 }
        r5 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1));	 Catch:{ all -> 0x02b9 }
        if (r5 != 0) goto L_0x00e1;	 Catch:{ all -> 0x02b9 }
    L_0x00d7:
        r5 = r14.zzjh();	 Catch:{ all -> 0x02b9 }
        r7 = r5.zzic();	 Catch:{ all -> 0x02b9 }
        r14.zzasl = r7;	 Catch:{ all -> 0x02b9 }
    L_0x00e1:
        r5 = r14.zzacv;	 Catch:{ all -> 0x02b9 }
        r5 = r5.zzgk();	 Catch:{ all -> 0x02b9 }
        r7 = com.google.android.gms.internal.measurement.zzez.zzaik;	 Catch:{ all -> 0x02b9 }
        r5 = r5.zzb(r4, r7);	 Catch:{ all -> 0x02b9 }
        r7 = r14.zzacv;	 Catch:{ all -> 0x02b9 }
        r7 = r7.zzgk();	 Catch:{ all -> 0x02b9 }
        r8 = com.google.android.gms.internal.measurement.zzez.zzail;	 Catch:{ all -> 0x02b9 }
        r7 = r7.zzb(r4, r8);	 Catch:{ all -> 0x02b9 }
        r7 = java.lang.Math.max(r1, r7);	 Catch:{ all -> 0x02b9 }
        r8 = r14.zzjh();	 Catch:{ all -> 0x02b9 }
        r5 = r8.zzb(r4, r5, r7);	 Catch:{ all -> 0x02b9 }
        r7 = r5.isEmpty();	 Catch:{ all -> 0x02b9 }
        if (r7 != 0) goto L_0x002a;	 Catch:{ all -> 0x02b9 }
    L_0x010b:
        r7 = r5.iterator();	 Catch:{ all -> 0x02b9 }
    L_0x010f:
        r8 = r7.hasNext();	 Catch:{ all -> 0x02b9 }
        if (r8 == 0) goto L_0x012a;	 Catch:{ all -> 0x02b9 }
    L_0x0115:
        r8 = r7.next();	 Catch:{ all -> 0x02b9 }
        r8 = (android.util.Pair) r8;	 Catch:{ all -> 0x02b9 }
        r8 = r8.first;	 Catch:{ all -> 0x02b9 }
        r8 = (com.google.android.gms.internal.measurement.zzku) r8;	 Catch:{ all -> 0x02b9 }
        r9 = r8.zzavv;	 Catch:{ all -> 0x02b9 }
        r9 = android.text.TextUtils.isEmpty(r9);	 Catch:{ all -> 0x02b9 }
        if (r9 != 0) goto L_0x010f;	 Catch:{ all -> 0x02b9 }
    L_0x0127:
        r7 = r8.zzavv;	 Catch:{ all -> 0x02b9 }
        goto L_0x012b;	 Catch:{ all -> 0x02b9 }
    L_0x012a:
        r7 = r6;	 Catch:{ all -> 0x02b9 }
    L_0x012b:
        if (r7 == 0) goto L_0x0156;	 Catch:{ all -> 0x02b9 }
    L_0x012d:
        r8 = 0;	 Catch:{ all -> 0x02b9 }
    L_0x012e:
        r9 = r5.size();	 Catch:{ all -> 0x02b9 }
        if (r8 >= r9) goto L_0x0156;	 Catch:{ all -> 0x02b9 }
    L_0x0134:
        r9 = r5.get(r8);	 Catch:{ all -> 0x02b9 }
        r9 = (android.util.Pair) r9;	 Catch:{ all -> 0x02b9 }
        r9 = r9.first;	 Catch:{ all -> 0x02b9 }
        r9 = (com.google.android.gms.internal.measurement.zzku) r9;	 Catch:{ all -> 0x02b9 }
        r10 = r9.zzavv;	 Catch:{ all -> 0x02b9 }
        r10 = android.text.TextUtils.isEmpty(r10);	 Catch:{ all -> 0x02b9 }
        if (r10 != 0) goto L_0x0153;	 Catch:{ all -> 0x02b9 }
    L_0x0146:
        r9 = r9.zzavv;	 Catch:{ all -> 0x02b9 }
        r9 = r9.equals(r7);	 Catch:{ all -> 0x02b9 }
        if (r9 != 0) goto L_0x0153;	 Catch:{ all -> 0x02b9 }
    L_0x014e:
        r5 = r5.subList(r1, r8);	 Catch:{ all -> 0x02b9 }
        goto L_0x0156;	 Catch:{ all -> 0x02b9 }
    L_0x0153:
        r8 = r8 + 1;	 Catch:{ all -> 0x02b9 }
        goto L_0x012e;	 Catch:{ all -> 0x02b9 }
    L_0x0156:
        r7 = new com.google.android.gms.internal.measurement.zzkt;	 Catch:{ all -> 0x02b9 }
        r7.<init>();	 Catch:{ all -> 0x02b9 }
        r8 = r5.size();	 Catch:{ all -> 0x02b9 }
        r8 = new com.google.android.gms.internal.measurement.zzku[r8];	 Catch:{ all -> 0x02b9 }
        r7.zzavf = r8;	 Catch:{ all -> 0x02b9 }
        r8 = new java.util.ArrayList;	 Catch:{ all -> 0x02b9 }
        r9 = r5.size();	 Catch:{ all -> 0x02b9 }
        r8.<init>(r9);	 Catch:{ all -> 0x02b9 }
        r9 = com.google.android.gms.internal.measurement.zzeh.zzht();	 Catch:{ all -> 0x02b9 }
        if (r9 == 0) goto L_0x0180;	 Catch:{ all -> 0x02b9 }
    L_0x0172:
        r9 = r14.zzacv;	 Catch:{ all -> 0x02b9 }
        r9 = r9.zzgk();	 Catch:{ all -> 0x02b9 }
        r9 = r9.zzau(r4);	 Catch:{ all -> 0x02b9 }
        if (r9 == 0) goto L_0x0180;	 Catch:{ all -> 0x02b9 }
    L_0x017e:
        r9 = 1;	 Catch:{ all -> 0x02b9 }
        goto L_0x0181;	 Catch:{ all -> 0x02b9 }
    L_0x0180:
        r9 = 0;	 Catch:{ all -> 0x02b9 }
    L_0x0181:
        r10 = 0;	 Catch:{ all -> 0x02b9 }
    L_0x0182:
        r11 = r7.zzavf;	 Catch:{ all -> 0x02b9 }
        r11 = r11.length;	 Catch:{ all -> 0x02b9 }
        if (r10 >= r11) goto L_0x01da;	 Catch:{ all -> 0x02b9 }
    L_0x0187:
        r11 = r7.zzavf;	 Catch:{ all -> 0x02b9 }
        r12 = r5.get(r10);	 Catch:{ all -> 0x02b9 }
        r12 = (android.util.Pair) r12;	 Catch:{ all -> 0x02b9 }
        r12 = r12.first;	 Catch:{ all -> 0x02b9 }
        r12 = (com.google.android.gms.internal.measurement.zzku) r12;	 Catch:{ all -> 0x02b9 }
        r11[r10] = r12;	 Catch:{ all -> 0x02b9 }
        r11 = r5.get(r10);	 Catch:{ all -> 0x02b9 }
        r11 = (android.util.Pair) r11;	 Catch:{ all -> 0x02b9 }
        r11 = r11.second;	 Catch:{ all -> 0x02b9 }
        r11 = (java.lang.Long) r11;	 Catch:{ all -> 0x02b9 }
        r8.add(r11);	 Catch:{ all -> 0x02b9 }
        r11 = r7.zzavf;	 Catch:{ all -> 0x02b9 }
        r11 = r11[r10];	 Catch:{ all -> 0x02b9 }
        r12 = r14.zzacv;	 Catch:{ all -> 0x02b9 }
        r12 = r12.zzgk();	 Catch:{ all -> 0x02b9 }
        r12 = r12.zzgw();	 Catch:{ all -> 0x02b9 }
        r12 = java.lang.Long.valueOf(r12);	 Catch:{ all -> 0x02b9 }
        r11.zzavu = r12;	 Catch:{ all -> 0x02b9 }
        r11 = r7.zzavf;	 Catch:{ all -> 0x02b9 }
        r11 = r11[r10];	 Catch:{ all -> 0x02b9 }
        r12 = java.lang.Long.valueOf(r2);	 Catch:{ all -> 0x02b9 }
        r11.zzavk = r12;	 Catch:{ all -> 0x02b9 }
        r11 = r7.zzavf;	 Catch:{ all -> 0x02b9 }
        r11 = r11[r10];	 Catch:{ all -> 0x02b9 }
        r12 = r14.zzacv;	 Catch:{ all -> 0x02b9 }
        r12.zzgl();	 Catch:{ all -> 0x02b9 }
        r12 = java.lang.Boolean.valueOf(r1);	 Catch:{ all -> 0x02b9 }
        r11.zzavz = r12;	 Catch:{ all -> 0x02b9 }
        if (r9 != 0) goto L_0x01d7;	 Catch:{ all -> 0x02b9 }
    L_0x01d1:
        r11 = r7.zzavf;	 Catch:{ all -> 0x02b9 }
        r11 = r11[r10];	 Catch:{ all -> 0x02b9 }
        r11.zzawh = r6;	 Catch:{ all -> 0x02b9 }
    L_0x01d7:
        r10 = r10 + 1;	 Catch:{ all -> 0x02b9 }
        goto L_0x0182;	 Catch:{ all -> 0x02b9 }
    L_0x01da:
        r5 = r14.zzacv;	 Catch:{ all -> 0x02b9 }
        r5 = r5.zzgi();	 Catch:{ all -> 0x02b9 }
        r9 = 2;	 Catch:{ all -> 0x02b9 }
        r5 = r5.isLoggable(r9);	 Catch:{ all -> 0x02b9 }
        if (r5 == 0) goto L_0x01ef;	 Catch:{ all -> 0x02b9 }
    L_0x01e7:
        r5 = r14.zzjf();	 Catch:{ all -> 0x02b9 }
        r6 = r5.zzb(r7);	 Catch:{ all -> 0x02b9 }
    L_0x01ef:
        r5 = r14.zzjf();	 Catch:{ all -> 0x02b9 }
        r11 = r5.zza(r7);	 Catch:{ all -> 0x02b9 }
        r5 = com.google.android.gms.internal.measurement.zzez.zzaiu;	 Catch:{ all -> 0x02b9 }
        r5 = r5.get();	 Catch:{ all -> 0x02b9 }
        r5 = (java.lang.String) r5;	 Catch:{ all -> 0x02b9 }
        r10 = new java.net.URL;	 Catch:{ MalformedURLException -> 0x027e }
        r10.<init>(r5);	 Catch:{ MalformedURLException -> 0x027e }
        r9 = r8.isEmpty();	 Catch:{ MalformedURLException -> 0x027e }
        r9 = r9 ^ r0;	 Catch:{ MalformedURLException -> 0x027e }
        com.google.android.gms.common.internal.Preconditions.checkArgument(r9);	 Catch:{ MalformedURLException -> 0x027e }
        r9 = r14.zzasj;	 Catch:{ MalformedURLException -> 0x027e }
        if (r9 == 0) goto L_0x0220;	 Catch:{ MalformedURLException -> 0x027e }
    L_0x0210:
        r8 = r14.zzacv;	 Catch:{ MalformedURLException -> 0x027e }
        r8 = r8.zzgi();	 Catch:{ MalformedURLException -> 0x027e }
        r8 = r8.zziv();	 Catch:{ MalformedURLException -> 0x027e }
        r9 = "Set uploading progress before finishing the previous upload";	 Catch:{ MalformedURLException -> 0x027e }
        r8.log(r9);	 Catch:{ MalformedURLException -> 0x027e }
        goto L_0x0227;	 Catch:{ MalformedURLException -> 0x027e }
    L_0x0220:
        r9 = new java.util.ArrayList;	 Catch:{ MalformedURLException -> 0x027e }
        r9.<init>(r8);	 Catch:{ MalformedURLException -> 0x027e }
        r14.zzasj = r9;	 Catch:{ MalformedURLException -> 0x027e }
    L_0x0227:
        r8 = r14.zzacv;	 Catch:{ MalformedURLException -> 0x027e }
        r8 = r8.zzgj();	 Catch:{ MalformedURLException -> 0x027e }
        r8 = r8.zzalu;	 Catch:{ MalformedURLException -> 0x027e }
        r8.set(r2);	 Catch:{ MalformedURLException -> 0x027e }
        r2 = "?";	 Catch:{ MalformedURLException -> 0x027e }
        r3 = r7.zzavf;	 Catch:{ MalformedURLException -> 0x027e }
        r3 = r3.length;	 Catch:{ MalformedURLException -> 0x027e }
        if (r3 <= 0) goto L_0x023f;	 Catch:{ MalformedURLException -> 0x027e }
    L_0x0239:
        r2 = r7.zzavf;	 Catch:{ MalformedURLException -> 0x027e }
        r2 = r2[r1];	 Catch:{ MalformedURLException -> 0x027e }
        r2 = r2.zzth;	 Catch:{ MalformedURLException -> 0x027e }
    L_0x023f:
        r3 = r14.zzacv;	 Catch:{ MalformedURLException -> 0x027e }
        r3 = r3.zzgi();	 Catch:{ MalformedURLException -> 0x027e }
        r3 = r3.zzjc();	 Catch:{ MalformedURLException -> 0x027e }
        r7 = "Uploading data. app, uncompressed size, data";	 Catch:{ MalformedURLException -> 0x027e }
        r8 = r11.length;	 Catch:{ MalformedURLException -> 0x027e }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ MalformedURLException -> 0x027e }
        r3.zzd(r7, r2, r8, r6);	 Catch:{ MalformedURLException -> 0x027e }
        r14.zzasf = r0;	 Catch:{ MalformedURLException -> 0x027e }
        r8 = r14.zzkz();	 Catch:{ MalformedURLException -> 0x027e }
        r13 = new com.google.android.gms.internal.measurement.zzjv;	 Catch:{ MalformedURLException -> 0x027e }
        r13.<init>(r14, r4);	 Catch:{ MalformedURLException -> 0x027e }
        r8.zzab();	 Catch:{ MalformedURLException -> 0x027e }
        r8.zzch();	 Catch:{ MalformedURLException -> 0x027e }
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r10);	 Catch:{ MalformedURLException -> 0x027e }
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r11);	 Catch:{ MalformedURLException -> 0x027e }
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r13);	 Catch:{ MalformedURLException -> 0x027e }
        r0 = r8.zzgh();	 Catch:{ MalformedURLException -> 0x027e }
        r2 = new com.google.android.gms.internal.measurement.zzfq;	 Catch:{ MalformedURLException -> 0x027e }
        r12 = 0;	 Catch:{ MalformedURLException -> 0x027e }
        r7 = r2;	 Catch:{ MalformedURLException -> 0x027e }
        r9 = r4;	 Catch:{ MalformedURLException -> 0x027e }
        r7.<init>(r8, r9, r10, r11, r12, r13);	 Catch:{ MalformedURLException -> 0x027e }
        r0.zzd(r2);	 Catch:{ MalformedURLException -> 0x027e }
        goto L_0x002a;
    L_0x027e:
        r0 = r14.zzacv;	 Catch:{ all -> 0x02b9 }
        r0 = r0.zzgi();	 Catch:{ all -> 0x02b9 }
        r0 = r0.zziv();	 Catch:{ all -> 0x02b9 }
        r2 = "Failed to parse upload URL. Not uploading. appId";	 Catch:{ all -> 0x02b9 }
        r3 = com.google.android.gms.internal.measurement.zzfi.zzbp(r4);	 Catch:{ all -> 0x02b9 }
        r0.zze(r2, r3, r5);	 Catch:{ all -> 0x02b9 }
        goto L_0x002a;	 Catch:{ all -> 0x02b9 }
    L_0x0293:
        r14.zzasl = r7;	 Catch:{ all -> 0x02b9 }
        r0 = r14.zzjh();	 Catch:{ all -> 0x02b9 }
        r4 = com.google.android.gms.internal.measurement.zzeh.zzhr();	 Catch:{ all -> 0x02b9 }
        r6 = 0;	 Catch:{ all -> 0x02b9 }
        r6 = r2 - r4;	 Catch:{ all -> 0x02b9 }
        r0 = r0.zzag(r6);	 Catch:{ all -> 0x02b9 }
        r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ all -> 0x02b9 }
        if (r2 != 0) goto L_0x002a;	 Catch:{ all -> 0x02b9 }
    L_0x02aa:
        r2 = r14.zzjh();	 Catch:{ all -> 0x02b9 }
        r0 = r2.zzbf(r0);	 Catch:{ all -> 0x02b9 }
        if (r0 == 0) goto L_0x002a;	 Catch:{ all -> 0x02b9 }
    L_0x02b4:
        r14.zzb(r0);	 Catch:{ all -> 0x02b9 }
        goto L_0x002a;
    L_0x02b9:
        r0 = move-exception;
        r14.zzasg = r1;
        r14.zzlh();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjt.zzle():void");
    }

    @WorkerThread
    final void zzlj() {
        zzab();
        zzlc();
        if (!this.zzarz) {
            this.zzacv.zzgi().zzja().log("This instance being marked as an uploader");
            zzab();
            zzlc();
            if (zzlk() && zzli()) {
                zzfk zziv;
                String str;
                int zza = zza(this.zzasi);
                int zzis = this.zzacv.zzfz().zzis();
                zzab();
                if (zza > zzis) {
                    zziv = this.zzacv.zzgi().zziv();
                    str = "Panic: can't downgrade version. Previous, current version";
                } else if (zza < zzis) {
                    if (zza(zzis, this.zzasi)) {
                        zziv = this.zzacv.zzgi().zzjc();
                        str = "Storage version upgraded. Previous, current version";
                    } else {
                        zziv = this.zzacv.zzgi().zziv();
                        str = "Storage version upgrade failed. Previous, current version";
                    }
                }
                zziv.zze(str, Integer.valueOf(zza), Integer.valueOf(zzis));
            }
            this.zzarz = true;
            zzlg();
        }
    }

    final void zzll() {
        this.zzasd++;
    }

    final zzgn zzlm() {
        return this.zzacv;
    }

    final void zzm(boolean z) {
        zzlg();
    }
}
