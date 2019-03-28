package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement.ConditionalUserProperty;
import com.google.android.gms.measurement.AppMeasurement.EventInterceptor;
import com.google.android.gms.measurement.AppMeasurement.OnEventListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.mevo.app.presentation.custom_views.TopToast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReference;

public final class zzhm extends zzdz {
    @VisibleForTesting
    protected zzif zzapl;
    private EventInterceptor zzapm;
    private final Set<OnEventListener> zzapn = new CopyOnWriteArraySet();
    private boolean zzapo;
    private final AtomicReference<String> zzapp = new AtomicReference();
    @VisibleForTesting
    protected boolean zzapq = true;

    protected zzhm(zzgn zzgn) {
        super(zzgn);
    }

    private final void zza(ConditionalUserProperty conditionalUserProperty) {
        long currentTimeMillis = zzbt().currentTimeMillis();
        Preconditions.checkNotNull(conditionalUserProperty);
        Preconditions.checkNotEmpty(conditionalUserProperty.mName);
        Preconditions.checkNotEmpty(conditionalUserProperty.mOrigin);
        Preconditions.checkNotNull(conditionalUserProperty.mValue);
        conditionalUserProperty.mCreationTimestamp = currentTimeMillis;
        String str = conditionalUserProperty.mName;
        Object obj = conditionalUserProperty.mValue;
        if (zzgg().zzcj(str) != 0) {
            zzgi().zziv().zzg("Invalid conditional user property name", zzgf().zzbo(str));
        } else if (zzgg().zzi(str, obj) != 0) {
            zzgi().zziv().zze("Invalid conditional user property value", zzgf().zzbo(str), obj);
        } else {
            Object zzj = zzgg().zzj(str, obj);
            if (zzj == null) {
                zzgi().zziv().zze("Unable to normalize conditional user property value", zzgf().zzbo(str), obj);
                return;
            }
            conditionalUserProperty.mValue = zzj;
            long j = conditionalUserProperty.mTriggerTimeout;
            if (TextUtils.isEmpty(conditionalUserProperty.mTriggerEventName) || (j <= 15552000000L && j >= 1)) {
                j = conditionalUserProperty.mTimeToLive;
                if (j <= 15552000000L) {
                    if (j >= 1) {
                        zzgh().zzc(new zzht(this, conditionalUserProperty));
                        return;
                    }
                }
                zzgi().zziv().zze("Invalid conditional user property time to live", zzgf().zzbo(str), Long.valueOf(j));
                return;
            }
            zzgi().zziv().zze("Invalid conditional user property timeout", zzgf().zzbo(str), Long.valueOf(j));
        }
    }

    @android.support.annotation.WorkerThread
    private final void zza(java.lang.String r29, java.lang.String r30, long r31, android.os.Bundle r33, boolean r34, boolean r35, boolean r36, java.lang.String r37) {
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
        r28 = this;
        r1 = r28;
        r8 = r29;
        r6 = r30;
        r5 = r33;
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r29);
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r30);
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r33);
        r28.zzab();
        r28.zzch();
        r4 = r1.zzacv;
        r4 = r4.isEnabled();
        if (r4 != 0) goto L_0x002d;
    L_0x001f:
        r2 = r28.zzgi();
        r2 = r2.zzjb();
        r3 = "Event not sent since app measurement is disabled";
        r2.log(r3);
        return;
    L_0x002d:
        r4 = r1.zzapo;
        r7 = 0;
        r16 = 0;
        r15 = 1;
        if (r4 != 0) goto L_0x0072;
    L_0x0035:
        r1.zzapo = r15;
        r4 = "com.google.android.gms.tagmanager.TagManagerService";	 Catch:{ ClassNotFoundException -> 0x0065 }
        r4 = java.lang.Class.forName(r4);	 Catch:{ ClassNotFoundException -> 0x0065 }
        r9 = "initialize";	 Catch:{ Exception -> 0x0055 }
        r10 = new java.lang.Class[r15];	 Catch:{ Exception -> 0x0055 }
        r11 = android.content.Context.class;	 Catch:{ Exception -> 0x0055 }
        r10[r16] = r11;	 Catch:{ Exception -> 0x0055 }
        r4 = r4.getDeclaredMethod(r9, r10);	 Catch:{ Exception -> 0x0055 }
        r9 = new java.lang.Object[r15];	 Catch:{ Exception -> 0x0055 }
        r10 = r28.getContext();	 Catch:{ Exception -> 0x0055 }
        r9[r16] = r10;	 Catch:{ Exception -> 0x0055 }
        r4.invoke(r7, r9);	 Catch:{ Exception -> 0x0055 }
        goto L_0x0072;
    L_0x0055:
        r0 = move-exception;
        r4 = r0;
        r9 = r28.zzgi();	 Catch:{ ClassNotFoundException -> 0x0065 }
        r9 = r9.zziy();	 Catch:{ ClassNotFoundException -> 0x0065 }
        r10 = "Failed to invoke Tag Manager's initialize() method";	 Catch:{ ClassNotFoundException -> 0x0065 }
        r9.zzg(r10, r4);	 Catch:{ ClassNotFoundException -> 0x0065 }
        goto L_0x0072;
    L_0x0065:
        r4 = r28.zzgi();
        r4 = r4.zzja();
        r9 = "Tag Manager is not found and thus will not be used";
        r4.log(r9);
    L_0x0072:
        r4 = 40;
        if (r36 == 0) goto L_0x00e0;
    L_0x0076:
        r28.zzgl();
        r9 = "_iap";
        r9 = r9.equals(r6);
        if (r9 != 0) goto L_0x00e0;
    L_0x0081:
        r9 = r1.zzacv;
        r9 = r9.zzgg();
        r10 = "event";
        r10 = r9.zzq(r10, r6);
        r11 = 2;
        if (r10 != 0) goto L_0x0091;
    L_0x0090:
        goto L_0x00aa;
    L_0x0091:
        r10 = "event";
        r12 = com.google.android.gms.measurement.AppMeasurement.Event.zzacw;
        r10 = r9.zza(r10, r12, r6);
        if (r10 != 0) goto L_0x00a0;
    L_0x009b:
        r9 = 13;
        r11 = 13;
        goto L_0x00aa;
    L_0x00a0:
        r10 = "event";
        r9 = r9.zza(r10, r4, r6);
        if (r9 != 0) goto L_0x00a9;
    L_0x00a8:
        goto L_0x00aa;
    L_0x00a9:
        r11 = 0;
    L_0x00aa:
        if (r11 == 0) goto L_0x00e0;
    L_0x00ac:
        r2 = r28.zzgi();
        r2 = r2.zzix();
        r3 = "Invalid public event name. Event will not be logged (FE)";
        r5 = r28.zzgf();
        r5 = r5.zzbm(r6);
        r2.zzg(r3, r5);
        r2 = r1.zzacv;
        r2.zzgg();
        r2 = com.google.android.gms.internal.measurement.zzkd.zza(r6, r4, r15);
        if (r6 == 0) goto L_0x00d3;
    L_0x00cc:
        r16 = r30.length();
        r3 = r16;
        goto L_0x00d4;
    L_0x00d3:
        r3 = 0;
    L_0x00d4:
        r4 = r1.zzacv;
        r4 = r4.zzgg();
        r5 = "_ev";
        r4.zza(r11, r5, r2, r3);
        return;
    L_0x00e0:
        r28.zzgl();
        r9 = r28.zzgb();
        r14 = r9.zzkn();
        if (r14 == 0) goto L_0x00f7;
    L_0x00ed:
        r9 = "_sc";
        r9 = r5.containsKey(r9);
        if (r9 != 0) goto L_0x00f7;
    L_0x00f5:
        r14.zzaqc = r15;
    L_0x00f7:
        if (r34 == 0) goto L_0x00fd;
    L_0x00f9:
        if (r36 == 0) goto L_0x00fd;
    L_0x00fb:
        r9 = 1;
        goto L_0x00fe;
    L_0x00fd:
        r9 = 0;
    L_0x00fe:
        com.google.android.gms.internal.measurement.zzih.zza(r14, r5, r9);
        r9 = "am";
        r17 = r9.equals(r8);
        r9 = com.google.android.gms.internal.measurement.zzkd.zzcm(r30);
        if (r34 == 0) goto L_0x013c;
    L_0x010d:
        r2 = r1.zzapm;
        if (r2 == 0) goto L_0x013c;
    L_0x0111:
        if (r9 != 0) goto L_0x013c;
    L_0x0113:
        if (r17 != 0) goto L_0x013c;
    L_0x0115:
        r2 = r28.zzgi();
        r2 = r2.zzjb();
        r3 = "Passing event to registered event handler (FE)";
        r4 = r28.zzgf();
        r4 = r4.zzbm(r6);
        r7 = r28.zzgf();
        r7 = r7.zzb(r5);
        r2.zze(r3, r4, r7);
        r2 = r1.zzapm;
        r3 = r8;
        r4 = r6;
        r6 = r31;
        r2.interceptEvent(r3, r4, r5, r6);
        return;
    L_0x013c:
        r2 = r1.zzacv;
        r2 = r2.zzkg();
        if (r2 != 0) goto L_0x0145;
    L_0x0144:
        return;
    L_0x0145:
        r2 = r28.zzgg();
        r10 = r2.zzch(r6);
        if (r10 == 0) goto L_0x0183;
    L_0x014f:
        r2 = r28.zzgi();
        r2 = r2.zzix();
        r3 = "Invalid event name. Event will not be logged (FE)";
        r5 = r28.zzgf();
        r5 = r5.zzbm(r6);
        r2.zzg(r3, r5);
        r28.zzgg();
        r12 = com.google.android.gms.internal.measurement.zzkd.zza(r6, r4, r15);
        if (r6 == 0) goto L_0x0174;
    L_0x016d:
        r16 = r30.length();
        r13 = r16;
        goto L_0x0175;
    L_0x0174:
        r13 = 0;
    L_0x0175:
        r2 = r1.zzacv;
        r8 = r2.zzgg();
        r11 = "_ev";
        r9 = r37;
        r8.zza(r9, r10, r11, r12, r13);
        return;
    L_0x0183:
        r2 = "_o";
        r4 = "_sn";
        r9 = "_sc";
        r10 = "_si";
        r2 = new java.lang.String[]{r2, r4, r9, r10};
        r2 = com.google.android.gms.common.util.CollectionUtils.listOf(r2);
        r9 = r28.zzgg();
        r4 = 1;
        r10 = r37;
        r11 = r6;
        r12 = r5;
        r13 = r2;
        r18 = r14;
        r14 = r36;
        r7 = 1;
        r15 = r4;
        r4 = r9.zza(r10, r11, r12, r13, r14, r15);
        if (r4 == 0) goto L_0x01db;
    L_0x01a9:
        r9 = "_sc";
        r9 = r4.containsKey(r9);
        if (r9 == 0) goto L_0x01db;
    L_0x01b1:
        r9 = "_si";
        r9 = r4.containsKey(r9);
        if (r9 != 0) goto L_0x01ba;
    L_0x01b9:
        goto L_0x01db;
    L_0x01ba:
        r9 = "_sn";
        r9 = r4.getString(r9);
        r10 = "_sc";
        r10 = r4.getString(r10);
        r11 = "_si";
        r11 = r4.getLong(r11);
        r11 = java.lang.Long.valueOf(r11);
        r12 = new com.google.android.gms.internal.measurement.zzig;
        r13 = r11.longValue();
        r12.<init>(r9, r10, r13);
        r14 = r12;
        goto L_0x01dc;
    L_0x01db:
        r14 = 0;
    L_0x01dc:
        if (r14 != 0) goto L_0x01e1;
    L_0x01de:
        r15 = r18;
        goto L_0x01e2;
    L_0x01e1:
        r15 = r14;
    L_0x01e2:
        r14 = new java.util.ArrayList;
        r14.<init>();
        r14.add(r4);
        r9 = r28.zzgg();
        r9 = r9.zzlo();
        r12 = r9.nextLong();
        r9 = r4.keySet();
        r5 = r33.size();
        r5 = new java.lang.String[r5];
        r5 = r9.toArray(r5);
        r5 = (java.lang.String[]) r5;
        java.util.Arrays.sort(r5);
        r11 = r5.length;
        r9 = 0;
        r10 = 0;
    L_0x020c:
        if (r10 >= r11) goto L_0x02c6;
    L_0x020e:
        r7 = r5[r10];
        r21 = r5;
        r5 = r4.get(r7);
        r28.zzgg();
        r5 = com.google.android.gms.internal.measurement.zzkd.zze(r5);
        if (r5 == 0) goto L_0x02a6;
    L_0x021f:
        r22 = r9;
        r9 = r5.length;
        r4.putInt(r7, r9);
        r23 = r10;
        r9 = 0;
    L_0x0228:
        r10 = r5.length;
        if (r9 >= r10) goto L_0x0293;
    L_0x022b:
        r10 = r5[r9];
        r8 = 1;
        com.google.android.gms.internal.measurement.zzih.zza(r15, r10, r8);
        r18 = r28.zzgg();
        r19 = "_ep";
        r20 = 0;
        r24 = r9;
        r8 = r22;
        r9 = r18;
        r22 = r10;
        r18 = r23;
        r10 = r37;
        r23 = r11;
        r11 = r19;
        r25 = r4;
        r26 = r5;
        r4 = r12;
        r12 = r22;
        r13 = r2;
        r27 = r2;
        r2 = r14;
        r14 = r36;
        r19 = r15;
        r15 = r20;
        r9 = r9.zza(r10, r11, r12, r13, r14, r15);
        r10 = "_en";
        r9.putString(r10, r6);
        r10 = "_eid";
        r9.putLong(r10, r4);
        r10 = "_gn";
        r9.putString(r10, r7);
        r10 = "_ll";
        r11 = r26;
        r12 = r11.length;
        r9.putInt(r10, r12);
        r10 = "_i";
        r12 = r24;
        r9.putInt(r10, r12);
        r2.add(r9);
        r9 = r12 + 1;
        r14 = r2;
        r12 = r4;
        r22 = r8;
        r5 = r11;
        r15 = r19;
        r11 = r23;
        r4 = r25;
        r2 = r27;
        r8 = r29;
        r23 = r18;
        goto L_0x0228;
    L_0x0293:
        r27 = r2;
        r25 = r4;
        r2 = r14;
        r19 = r15;
        r8 = r22;
        r18 = r23;
        r23 = r11;
        r11 = r5;
        r4 = r12;
        r7 = r11.length;
        r9 = r8 + r7;
        goto L_0x02b3;
    L_0x02a6:
        r27 = r2;
        r25 = r4;
        r8 = r9;
        r18 = r10;
        r23 = r11;
        r4 = r12;
        r2 = r14;
        r19 = r15;
    L_0x02b3:
        r10 = r18 + 1;
        r14 = r2;
        r12 = r4;
        r15 = r19;
        r5 = r21;
        r11 = r23;
        r4 = r25;
        r2 = r27;
        r7 = 1;
        r8 = r29;
        goto L_0x020c;
    L_0x02c6:
        r25 = r4;
        r8 = r9;
        r4 = r12;
        r2 = r14;
        if (r8 == 0) goto L_0x02d9;
    L_0x02cd:
        r3 = "_eid";
        r7 = r25;
        r7.putLong(r3, r4);
        r3 = "_epc";
        r7.putInt(r3, r8);
    L_0x02d9:
        r8 = 0;
    L_0x02da:
        r3 = r2.size();
        if (r8 >= r3) goto L_0x0366;
    L_0x02e0:
        r3 = r2.get(r8);
        r3 = (android.os.Bundle) r3;
        if (r8 == 0) goto L_0x02ea;
    L_0x02e8:
        r4 = 1;
        goto L_0x02eb;
    L_0x02ea:
        r4 = 0;
    L_0x02eb:
        if (r4 == 0) goto L_0x02f0;
    L_0x02ed:
        r4 = "_ep";
        goto L_0x02f1;
    L_0x02f0:
        r4 = r6;
    L_0x02f1:
        r5 = "_o";
        r7 = 1;
        r9 = r29;
        r3.putString(r5, r9);
        if (r35 == 0) goto L_0x0303;
    L_0x02fb:
        r5 = r28.zzgg();
        r3 = r5.zzd(r3);
    L_0x0303:
        r11 = r3;
        r3 = r28.zzgi();
        r3 = r3.zzjb();
        r5 = "Logging event (FE)";
        r12 = r28.zzgf();
        r12 = r12.zzbm(r6);
        r13 = r28.zzgf();
        r13 = r13.zzb(r11);
        r3.zze(r5, r12, r13);
        r12 = new com.google.android.gms.internal.measurement.zzex;
        r5 = new com.google.android.gms.internal.measurement.zzeu;
        r5.<init>(r11);
        r13 = r2;
        r2 = r12;
        r3 = r4;
        r4 = r5;
        r5 = r9;
        r14 = r6;
        r15 = 1;
        r6 = r31;
        r2.<init>(r3, r4, r5, r6);
        r2 = r28.zzga();
        r6 = r37;
        r2.zzb(r12, r6);
        if (r17 != 0) goto L_0x0360;
    L_0x033f:
        r2 = r1.zzapn;
        r12 = r2.iterator();
    L_0x0345:
        r2 = r12.hasNext();
        if (r2 == 0) goto L_0x0360;
    L_0x034b:
        r2 = r12.next();
        r2 = (com.google.android.gms.measurement.AppMeasurement.OnEventListener) r2;
        r5 = new android.os.Bundle;
        r5.<init>(r11);
        r3 = r9;
        r4 = r14;
        r6 = r31;
        r2.onEvent(r3, r4, r5, r6);
        r6 = r37;
        goto L_0x0345;
    L_0x0360:
        r8 = r8 + 1;
        r2 = r13;
        r6 = r14;
        goto L_0x02da;
    L_0x0366:
        r14 = r6;
        r15 = 1;
        r28.zzgl();
        r2 = r28.zzgb();
        r2 = r2.zzkn();
        if (r2 == 0) goto L_0x0384;
    L_0x0375:
        r2 = "_ae";
        r2 = r2.equals(r14);
        if (r2 == 0) goto L_0x0384;
    L_0x037d:
        r2 = r28.zzgd();
        r2.zzl(r15);
    L_0x0384:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzhm.zza(java.lang.String, java.lang.String, long, android.os.Bundle, boolean, boolean, boolean, java.lang.String):void");
    }

    private final void zza(String str, String str2, long j, Object obj) {
        zzgh().zzc(new zzho(this, str, str2, obj, j));
    }

    private final void zza(String str, String str2, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zzb(str, str2, zzbt().currentTimeMillis(), bundle, z, z2, z3, str3);
    }

    @WorkerThread
    private final void zza(String str, String str2, Object obj, long j) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzab();
        zzfv();
        zzch();
        if (!this.zzacv.isEnabled()) {
            zzgi().zzjb().log("User property not set since app measurement is disabled");
        } else if (this.zzacv.zzkg()) {
            zzgi().zzjb().zze("Setting user property (FE)", zzgf().zzbm(str2), obj);
            zzga().zzb(new zzka(str2, j, obj, str));
        }
    }

    private final void zza(String str, String str2, String str3, Bundle bundle) {
        long currentTimeMillis = zzbt().currentTimeMillis();
        Preconditions.checkNotEmpty(str2);
        ConditionalUserProperty conditionalUserProperty = new ConditionalUserProperty();
        conditionalUserProperty.mAppId = str;
        conditionalUserProperty.mName = str2;
        conditionalUserProperty.mCreationTimestamp = currentTimeMillis;
        if (str3 != null) {
            conditionalUserProperty.mExpiredEventName = str3;
            conditionalUserProperty.mExpiredEventParams = bundle;
        }
        zzgh().zzc(new zzhu(this, conditionalUserProperty));
    }

    @VisibleForTesting
    private final Map<String, Object> zzb(String str, String str2, String str3, boolean z) {
        zzfk zziv;
        if (zzgh().zzju()) {
            zziv = zzgi().zziv();
            str2 = "Cannot get user properties from analytics worker thread";
        } else if (zzee.isMainThread()) {
            zziv = zzgi().zziv();
            str2 = "Cannot get user properties from main thread";
        } else {
            AtomicReference atomicReference = new AtomicReference();
            synchronized (atomicReference) {
                this.zzacv.zzgh().zzc(new zzhw(this, atomicReference, str, str2, str3, z));
                try {
                    atomicReference.wait(TopToast.DURATION_LONG);
                } catch (InterruptedException e) {
                    zzgi().zziy().zzg("Interrupted waiting for get user properties", e);
                }
            }
            List<zzka> list = (List) atomicReference.get();
            if (list == null) {
                zziv = zzgi().zziy();
                str2 = "Timed out waiting for get user properties";
            } else {
                Map<String, Object> arrayMap = new ArrayMap(list.size());
                for (zzka zzka : list) {
                    arrayMap.put(zzka.name, zzka.getValue());
                }
                return arrayMap;
            }
        }
        zziv.log(str2);
        return Collections.emptyMap();
    }

    @android.support.annotation.WorkerThread
    private final void zzb(com.google.android.gms.measurement.AppMeasurement.ConditionalUserProperty r26) {
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
        r0 = r26;
        r25.zzab();
        r25.zzch();
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r26);
        r1 = r0.mName;
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r1);
        r1 = r0.mOrigin;
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r1);
        r1 = r0.mValue;
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r1);
        r1 = r25;
        r2 = r1.zzacv;
        r2 = r2.isEnabled();
        if (r2 != 0) goto L_0x0032;
    L_0x0024:
        r0 = r25.zzgi();
        r0 = r0.zzjb();
        r2 = "Conditional property not sent since Firebase Analytics is disabled";
        r0.log(r2);
        return;
    L_0x0032:
        r2 = new com.google.android.gms.internal.measurement.zzka;
        r4 = r0.mName;
        r5 = r0.mTriggeredTimestamp;
        r7 = r0.mValue;
        r8 = r0.mOrigin;
        r3 = r2;
        r3.<init>(r4, r5, r7, r8);
        r9 = r25.zzgg();	 Catch:{ IllegalArgumentException -> 0x00ad }
        r10 = r0.mAppId;	 Catch:{ IllegalArgumentException -> 0x00ad }
        r11 = r0.mTriggeredEventName;	 Catch:{ IllegalArgumentException -> 0x00ad }
        r12 = r0.mTriggeredEventParams;	 Catch:{ IllegalArgumentException -> 0x00ad }
        r13 = r0.mOrigin;	 Catch:{ IllegalArgumentException -> 0x00ad }
        r14 = 0;	 Catch:{ IllegalArgumentException -> 0x00ad }
        r16 = 1;	 Catch:{ IllegalArgumentException -> 0x00ad }
        r17 = 0;	 Catch:{ IllegalArgumentException -> 0x00ad }
        r14 = r9.zza(r10, r11, r12, r13, r14, r16, r17);	 Catch:{ IllegalArgumentException -> 0x00ad }
        r3 = r25.zzgg();	 Catch:{ IllegalArgumentException -> 0x00ad }
        r4 = r0.mAppId;	 Catch:{ IllegalArgumentException -> 0x00ad }
        r5 = r0.mTimedOutEventName;	 Catch:{ IllegalArgumentException -> 0x00ad }
        r6 = r0.mTimedOutEventParams;	 Catch:{ IllegalArgumentException -> 0x00ad }
        r7 = r0.mOrigin;	 Catch:{ IllegalArgumentException -> 0x00ad }
        r8 = 0;	 Catch:{ IllegalArgumentException -> 0x00ad }
        r10 = 1;	 Catch:{ IllegalArgumentException -> 0x00ad }
        r11 = 0;	 Catch:{ IllegalArgumentException -> 0x00ad }
        r11 = r3.zza(r4, r5, r6, r7, r8, r10, r11);	 Catch:{ IllegalArgumentException -> 0x00ad }
        r15 = r25.zzgg();	 Catch:{ IllegalArgumentException -> 0x00ad }
        r3 = r0.mAppId;	 Catch:{ IllegalArgumentException -> 0x00ad }
        r4 = r0.mExpiredEventName;	 Catch:{ IllegalArgumentException -> 0x00ad }
        r5 = r0.mExpiredEventParams;	 Catch:{ IllegalArgumentException -> 0x00ad }
        r6 = r0.mOrigin;	 Catch:{ IllegalArgumentException -> 0x00ad }
        r20 = 0;	 Catch:{ IllegalArgumentException -> 0x00ad }
        r22 = 1;	 Catch:{ IllegalArgumentException -> 0x00ad }
        r23 = 0;	 Catch:{ IllegalArgumentException -> 0x00ad }
        r16 = r3;	 Catch:{ IllegalArgumentException -> 0x00ad }
        r17 = r4;	 Catch:{ IllegalArgumentException -> 0x00ad }
        r18 = r5;	 Catch:{ IllegalArgumentException -> 0x00ad }
        r19 = r6;	 Catch:{ IllegalArgumentException -> 0x00ad }
        r17 = r15.zza(r16, r17, r18, r19, r20, r22, r23);	 Catch:{ IllegalArgumentException -> 0x00ad }
        r15 = new com.google.android.gms.internal.measurement.zzef;
        r4 = r0.mAppId;
        r5 = r0.mOrigin;
        r7 = r0.mCreationTimestamp;
        r10 = r0.mTriggerEventName;
        r12 = r0.mTriggerTimeout;
        r24 = r10;
        r9 = r0.mTimeToLive;
        r3 = r15;
        r6 = r2;
        r18 = r9;
        r0 = 0;
        r9 = r0;
        r10 = r24;
        r0 = r15;
        r15 = r18;
        r3.<init>(r4, r5, r6, r7, r9, r10, r11, r12, r14, r15, r17);
        r2 = r25.zzga();
        r2.zzd(r0);
    L_0x00ad:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzhm.zzb(com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty):void");
    }

    private final void zzb(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        Bundle bundle2;
        Bundle bundle3 = bundle;
        if (bundle3 == null) {
            bundle2 = new Bundle();
        } else {
            Bundle bundle4 = new Bundle(bundle3);
            for (String str4 : bundle4.keySet()) {
                Object obj = bundle4.get(str4);
                if (obj instanceof Bundle) {
                    bundle4.putBundle(str4, new Bundle((Bundle) obj));
                } else {
                    int i = 0;
                    if (obj instanceof Parcelable[]) {
                        Parcelable[] parcelableArr = (Parcelable[]) obj;
                        while (i < parcelableArr.length) {
                            if (parcelableArr[i] instanceof Bundle) {
                                parcelableArr[i] = new Bundle((Bundle) parcelableArr[i]);
                            }
                            i++;
                        }
                    } else if (obj instanceof ArrayList) {
                        ArrayList arrayList = (ArrayList) obj;
                        while (i < arrayList.size()) {
                            Object obj2 = arrayList.get(i);
                            if (obj2 instanceof Bundle) {
                                arrayList.set(i, new Bundle((Bundle) obj2));
                            }
                            i++;
                        }
                    }
                }
            }
            bundle2 = bundle4;
        }
        zzgh().zzc(new zzie(this, str, str2, j, bundle2, z, z2, z3, str3));
    }

    @android.support.annotation.WorkerThread
    private final void zzc(com.google.android.gms.measurement.AppMeasurement.ConditionalUserProperty r23) {
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
        r22 = this;
        r0 = r23;
        r22.zzab();
        r22.zzch();
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r23);
        r1 = r0.mName;
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r1);
        r1 = r22;
        r2 = r1.zzacv;
        r2 = r2.isEnabled();
        if (r2 != 0) goto L_0x0028;
    L_0x001a:
        r0 = r22.zzgi();
        r0 = r0.zzjb();
        r2 = "Conditional property not cleared since Firebase Analytics is disabled";
        r0.log(r2);
        return;
    L_0x0028:
        r2 = new com.google.android.gms.internal.measurement.zzka;
        r4 = r0.mName;
        r5 = 0;
        r7 = 0;
        r8 = 0;
        r3 = r2;
        r3.<init>(r4, r5, r7, r8);
        r9 = r22.zzgg();	 Catch:{ IllegalArgumentException -> 0x0072 }
        r10 = r0.mAppId;	 Catch:{ IllegalArgumentException -> 0x0072 }
        r11 = r0.mExpiredEventName;	 Catch:{ IllegalArgumentException -> 0x0072 }
        r12 = r0.mExpiredEventParams;	 Catch:{ IllegalArgumentException -> 0x0072 }
        r13 = r0.mOrigin;	 Catch:{ IllegalArgumentException -> 0x0072 }
        r14 = r0.mCreationTimestamp;	 Catch:{ IllegalArgumentException -> 0x0072 }
        r16 = 1;	 Catch:{ IllegalArgumentException -> 0x0072 }
        r17 = 0;	 Catch:{ IllegalArgumentException -> 0x0072 }
        r17 = r9.zza(r10, r11, r12, r13, r14, r16, r17);	 Catch:{ IllegalArgumentException -> 0x0072 }
        r15 = new com.google.android.gms.internal.measurement.zzef;
        r4 = r0.mAppId;
        r5 = r0.mOrigin;
        r7 = r0.mCreationTimestamp;
        r9 = r0.mActive;
        r10 = r0.mTriggerEventName;
        r12 = r0.mTriggerTimeout;
        r14 = 0;
        r18 = r12;
        r11 = r0.mTimeToLive;
        r3 = r15;
        r6 = r2;
        r20 = r11;
        r0 = 0;
        r11 = r0;
        r12 = r18;
        r0 = r15;
        r15 = r20;
        r3.<init>(r4, r5, r6, r7, r9, r10, r11, r12, r14, r15, r17);
        r2 = r22.zzga();
        r2.zzd(r0);
    L_0x0072:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzhm.zzc(com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty):void");
    }

    @VisibleForTesting
    private final List<ConditionalUserProperty> zzf(String str, String str2, String str3) {
        zzfk zziv;
        if (zzgh().zzju()) {
            zziv = zzgi().zziv();
            str2 = "Cannot get conditional user properties from analytics worker thread";
        } else if (zzee.isMainThread()) {
            zziv = zzgi().zziv();
            str2 = "Cannot get conditional user properties from main thread";
        } else {
            AtomicReference atomicReference = new AtomicReference();
            synchronized (atomicReference) {
                this.zzacv.zzgh().zzc(new zzhv(this, atomicReference, str, str2, str3));
                try {
                    atomicReference.wait(TopToast.DURATION_LONG);
                } catch (InterruptedException e) {
                    zzgi().zziy().zze("Interrupted waiting for get conditional user properties", str, e);
                }
            }
            List<zzef> list = (List) atomicReference.get();
            if (list == null) {
                zzgi().zziy().zzg("Timed out waiting for get conditional user properties", str);
                return Collections.emptyList();
            }
            List<ConditionalUserProperty> arrayList = new ArrayList(list.size());
            for (zzef zzef : list) {
                ConditionalUserProperty conditionalUserProperty = new ConditionalUserProperty();
                conditionalUserProperty.mAppId = zzef.packageName;
                conditionalUserProperty.mOrigin = zzef.origin;
                conditionalUserProperty.mCreationTimestamp = zzef.creationTimestamp;
                conditionalUserProperty.mName = zzef.zzage.name;
                conditionalUserProperty.mValue = zzef.zzage.getValue();
                conditionalUserProperty.mActive = zzef.active;
                conditionalUserProperty.mTriggerEventName = zzef.triggerEventName;
                if (zzef.zzagf != null) {
                    conditionalUserProperty.mTimedOutEventName = zzef.zzagf.name;
                    if (zzef.zzagf.zzahg != null) {
                        conditionalUserProperty.mTimedOutEventParams = zzef.zzagf.zzahg.zzin();
                    }
                }
                conditionalUserProperty.mTriggerTimeout = zzef.triggerTimeout;
                if (zzef.zzagg != null) {
                    conditionalUserProperty.mTriggeredEventName = zzef.zzagg.name;
                    if (zzef.zzagg.zzahg != null) {
                        conditionalUserProperty.mTriggeredEventParams = zzef.zzagg.zzahg.zzin();
                    }
                }
                conditionalUserProperty.mTriggeredTimestamp = zzef.zzage.zzast;
                conditionalUserProperty.mTimeToLive = zzef.timeToLive;
                if (zzef.zzagh != null) {
                    conditionalUserProperty.mExpiredEventName = zzef.zzagh.name;
                    if (zzef.zzagh.zzahg != null) {
                        conditionalUserProperty.mExpiredEventParams = zzef.zzagh.zzahg.zzin();
                    }
                }
                arrayList.add(conditionalUserProperty);
            }
            return arrayList;
        }
        zziv.log(str2);
        return Collections.emptyList();
    }

    @WorkerThread
    private final void zzi(boolean z) {
        zzab();
        zzfv();
        zzch();
        zzgi().zzjb().zzg("Setting app measurement enabled (FE)", Boolean.valueOf(z));
        zzgj().setMeasurementEnabled(z);
        if (!zzgk().zzbc(zzfz().zzah())) {
            zzga().zzkp();
        } else if (this.zzacv.isEnabled() && this.zzapq) {
            zzgi().zzjb().log("Recording app launch after enabling measurement for the first time (FE)");
            zzkm();
        } else {
            zzga().zzkp();
        }
    }

    public final void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        zzfv();
        zza(null, str, str2, bundle);
    }

    public final void clearConditionalUserPropertyAs(String str, String str2, String str3, Bundle bundle) {
        Preconditions.checkNotEmpty(str);
        zzfu();
        zza(str, str2, str3, bundle);
    }

    public final Task<String> getAppInstanceId() {
        try {
            String zzjk = zzgj().zzjk();
            return zzjk != null ? Tasks.forResult(zzjk) : Tasks.call(zzgh().zzjv(), new zzhq(this));
        } catch (Exception e) {
            zzgi().zziy().log("Failed to schedule task for getAppInstanceId");
            return Tasks.forException(e);
        }
    }

    public final List<ConditionalUserProperty> getConditionalUserProperties(String str, String str2) {
        zzfv();
        return zzf(null, str, str2);
    }

    public final List<ConditionalUserProperty> getConditionalUserPropertiesAs(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzfu();
        return zzf(str, str2, str3);
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final Map<String, Object> getUserProperties(String str, String str2, boolean z) {
        zzfv();
        return zzb(null, str, str2, z);
    }

    public final Map<String, Object> getUserPropertiesAs(String str, String str2, String str3, boolean z) {
        Preconditions.checkNotEmpty(str);
        zzfu();
        return zzb(str, str2, str3, z);
    }

    public final void logEvent(String str, String str2, Bundle bundle) {
        boolean z;
        zzfv();
        if (this.zzapm != null) {
            if (!zzkd.zzcm(str2)) {
                z = false;
                zza(str, str2, bundle, true, z, false, null);
            }
        }
        z = true;
        zza(str, str2, bundle, true, z, false, null);
    }

    public final void logEventNoInterceptor(String str, String str2, Bundle bundle, long j) {
        zzfv();
        zzb(str, str2, j, bundle, false, true, true, null);
    }

    public final void registerOnMeasurementEventListener(OnEventListener onEventListener) {
        zzfv();
        zzch();
        Preconditions.checkNotNull(onEventListener);
        if (!this.zzapn.add(onEventListener)) {
            zzgi().zziy().log("OnEventListener already registered");
        }
    }

    public final void resetAnalyticsData() {
        zzgh().zzc(new zzhs(this, zzbt().currentTimeMillis()));
    }

    public final void setConditionalUserProperty(ConditionalUserProperty conditionalUserProperty) {
        Preconditions.checkNotNull(conditionalUserProperty);
        zzfv();
        ConditionalUserProperty conditionalUserProperty2 = new ConditionalUserProperty(conditionalUserProperty);
        if (!TextUtils.isEmpty(conditionalUserProperty2.mAppId)) {
            zzgi().zziy().log("Package name should be null when calling setConditionalUserProperty");
        }
        conditionalUserProperty2.mAppId = null;
        zza(conditionalUserProperty2);
    }

    public final void setConditionalUserPropertyAs(ConditionalUserProperty conditionalUserProperty) {
        Preconditions.checkNotNull(conditionalUserProperty);
        Preconditions.checkNotEmpty(conditionalUserProperty.mAppId);
        zzfu();
        zza(new ConditionalUserProperty(conditionalUserProperty));
    }

    @WorkerThread
    public final void setEventInterceptor(EventInterceptor eventInterceptor) {
        zzab();
        zzfv();
        zzch();
        if (!(eventInterceptor == null || eventInterceptor == this.zzapm)) {
            Preconditions.checkState(this.zzapm == null, "EventInterceptor already set.");
        }
        this.zzapm = eventInterceptor;
    }

    public final void setMeasurementEnabled(boolean z) {
        zzch();
        zzfv();
        zzgh().zzc(new zzib(this, z));
    }

    public final void setMinimumSessionDuration(long j) {
        zzfv();
        zzgh().zzc(new zzic(this, j));
    }

    public final void setSessionTimeoutDuration(long j) {
        zzfv();
        zzgh().zzc(new zzid(this, j));
    }

    public final void setUserProperty(String str, String str2, Object obj) {
        Preconditions.checkNotEmpty(str);
        long currentTimeMillis = zzbt().currentTimeMillis();
        int zzcj = zzgg().zzcj(str2);
        int i = 0;
        if (zzcj != 0) {
            zzgg();
            str = zzkd.zza(str2, 24, true);
            if (str2 != null) {
                i = str2.length();
            }
            this.zzacv.zzgg().zza(zzcj, "_ev", str, i);
        } else if (obj != null) {
            zzcj = zzgg().zzi(str2, obj);
            if (zzcj != 0) {
                zzgg();
                str = zzkd.zza(str2, 24, true);
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    i = String.valueOf(obj).length();
                }
                this.zzacv.zzgg().zza(zzcj, "_ev", str, i);
                return;
            }
            Object zzj = zzgg().zzj(str2, obj);
            if (zzj != null) {
                zza(str, str2, currentTimeMillis, zzj);
            }
        } else {
            zza(str, str2, currentTimeMillis, null);
        }
    }

    public final void unregisterOnMeasurementEventListener(OnEventListener onEventListener) {
        zzfv();
        zzch();
        Preconditions.checkNotNull(onEventListener);
        if (!this.zzapn.remove(onEventListener)) {
            zzgi().zziy().log("OnEventListener had not been registered");
        }
    }

    @WorkerThread
    final void zza(String str, String str2, Bundle bundle) {
        boolean z;
        zzfv();
        zzab();
        if (this.zzapm != null) {
            if (!zzkd.zzcm(str2)) {
                z = false;
                zza(str, str2, zzbt().currentTimeMillis(), bundle, true, z, false, null);
            }
        }
        z = true;
        zza(str, str2, zzbt().currentTimeMillis(), bundle, true, z, false, null);
    }

    public final void zza(String str, String str2, Bundle bundle, boolean z) {
        boolean z2;
        zzfv();
        if (this.zzapm != null) {
            if (!zzkd.zzcm(str2)) {
                z2 = false;
                zza(str, str2, bundle, true, z2, true, null);
            }
        }
        z2 = true;
        zza(str, str2, bundle, true, z2, true, null);
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    @android.support.annotation.Nullable
    final java.lang.String zzaj(long r4) {
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
        r0 = new java.util.concurrent.atomic.AtomicReference;
        r0.<init>();
        monitor-enter(r0);
        r1 = r3.zzgh();	 Catch:{ all -> 0x002d }
        r2 = new com.google.android.gms.internal.measurement.zzhr;	 Catch:{ all -> 0x002d }
        r2.<init>(r3, r0);	 Catch:{ all -> 0x002d }
        r1.zzc(r2);	 Catch:{ all -> 0x002d }
        r0.wait(r4);	 Catch:{ InterruptedException -> 0x001d }
        monitor-exit(r0);	 Catch:{ all -> 0x002d }
        r4 = r0.get();
        r4 = (java.lang.String) r4;
        return r4;
    L_0x001d:
        r4 = r3.zzgi();	 Catch:{ all -> 0x002d }
        r4 = r4.zziy();	 Catch:{ all -> 0x002d }
        r5 = "Interrupted waiting for app instance id";	 Catch:{ all -> 0x002d }
        r4.log(r5);	 Catch:{ all -> 0x002d }
        r4 = 0;	 Catch:{ all -> 0x002d }
        monitor-exit(r0);	 Catch:{ all -> 0x002d }
        return r4;	 Catch:{ all -> 0x002d }
    L_0x002d:
        r4 = move-exception;	 Catch:{ all -> 0x002d }
        monitor-exit(r0);	 Catch:{ all -> 0x002d }
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzhm.zzaj(long):java.lang.String");
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    final void zzbu(@Nullable String str) {
        this.zzapp.set(str);
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
        return false;
    }

    public final List<zzka> zzj(boolean z) {
        zzfk zziv;
        String str;
        zzfv();
        zzch();
        zzgi().zzjb().log("Fetching user attributes (FE)");
        if (zzgh().zzju()) {
            zziv = zzgi().zziv();
            str = "Cannot get all user properties from analytics worker thread";
        } else if (zzee.isMainThread()) {
            zziv = zzgi().zziv();
            str = "Cannot get all user properties from main thread";
        } else {
            AtomicReference atomicReference = new AtomicReference();
            synchronized (atomicReference) {
                this.zzacv.zzgh().zzc(new zzhp(this, atomicReference, z));
                try {
                    atomicReference.wait(TopToast.DURATION_LONG);
                } catch (InterruptedException e) {
                    zzgi().zziy().zzg("Interrupted waiting for get user properties", e);
                }
            }
            List<zzka> list = (List) atomicReference.get();
            if (list != null) {
                return list;
            }
            zziv = zzgi().zziy();
            str = "Timed out waiting for get user properties";
        }
        zziv.log(str);
        return Collections.emptyList();
    }

    @Nullable
    public final String zzjk() {
        zzfv();
        return (String) this.zzapp.get();
    }

    public final Boolean zzkh() {
        AtomicReference atomicReference = new AtomicReference();
        return (Boolean) zzgh().zza(atomicReference, 15000, "boolean test flag value", new zzhn(this, atomicReference));
    }

    public final String zzki() {
        AtomicReference atomicReference = new AtomicReference();
        return (String) zzgh().zza(atomicReference, 15000, "String test flag value", new zzhx(this, atomicReference));
    }

    public final Long zzkj() {
        AtomicReference atomicReference = new AtomicReference();
        return (Long) zzgh().zza(atomicReference, 15000, "long test flag value", new zzhy(this, atomicReference));
    }

    public final Integer zzkk() {
        AtomicReference atomicReference = new AtomicReference();
        return (Integer) zzgh().zza(atomicReference, 15000, "int test flag value", new zzhz(this, atomicReference));
    }

    public final Double zzkl() {
        AtomicReference atomicReference = new AtomicReference();
        return (Double) zzgh().zza(atomicReference, 15000, "double test flag value", new zzia(this, atomicReference));
    }

    @WorkerThread
    public final void zzkm() {
        zzab();
        zzfv();
        zzch();
        if (this.zzacv.zzkg()) {
            zzga().zzkm();
            this.zzapq = false;
            String zzjn = zzgj().zzjn();
            if (!TextUtils.isEmpty(zzjn)) {
                zzge().zzch();
                if (!zzjn.equals(VERSION.RELEASE)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("_po", zzjn);
                    logEvent("auto", "_ou", bundle);
                }
            }
        }
    }
}
