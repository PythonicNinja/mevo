package com.google.android.gms.internal.measurement;

import android.content.BroadcastReceiver.PendingResult;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.MainThread;
import com.google.android.gms.common.internal.Preconditions;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;

public final class zzgd {
    private final zzgg zzamz;

    public zzgd(zzgg zzgg) {
        Preconditions.checkNotNull(zzgg);
        this.zzamz = zzgg;
    }

    public static boolean zza(android.content.Context r4) {
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
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r4);
        r0 = 0;
        r1 = r4.getPackageManager();	 Catch:{ NameNotFoundException -> 0x001e }
        if (r1 != 0) goto L_0x000b;	 Catch:{ NameNotFoundException -> 0x001e }
    L_0x000a:
        return r0;	 Catch:{ NameNotFoundException -> 0x001e }
    L_0x000b:
        r2 = new android.content.ComponentName;	 Catch:{ NameNotFoundException -> 0x001e }
        r3 = "com.google.android.gms.measurement.AppMeasurementReceiver";	 Catch:{ NameNotFoundException -> 0x001e }
        r2.<init>(r4, r3);	 Catch:{ NameNotFoundException -> 0x001e }
        r4 = r1.getReceiverInfo(r2, r0);	 Catch:{ NameNotFoundException -> 0x001e }
        if (r4 == 0) goto L_0x001e;	 Catch:{ NameNotFoundException -> 0x001e }
    L_0x0018:
        r4 = r4.enabled;	 Catch:{ NameNotFoundException -> 0x001e }
        if (r4 == 0) goto L_0x001e;
    L_0x001c:
        r4 = 1;
        return r4;
    L_0x001e:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgd.zza(android.content.Context):boolean");
    }

    @MainThread
    public final void onReceive(Context context, Intent intent) {
        zzgn zza = zzgn.zza(context, null, null);
        zzfi zzgi = zza.zzgi();
        if (intent == null) {
            zzgi.zziy().log("Receiver called with null intent");
            return;
        }
        zza.zzgl();
        String action = intent.getAction();
        zzgi.zzjc().zzg("Local receiver got", action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            intent = new Intent().setClassName(context, "com.google.android.gms.measurement.AppMeasurementService");
            intent.setAction("com.google.android.gms.measurement.UPLOAD");
            zzgi.zzjc().log("Starting wakeful intent.");
            this.zzamz.doStartService(context, intent);
            return;
        }
        if ("com.android.vending.INSTALL_REFERRER".equals(action)) {
            try {
                zza.zzgh().zzc(new zzge(this, zza, zzgi));
            } catch (Exception e) {
                zzgi.zziy().zzg("Install Referrer Reporter encountered a problem", e);
            }
            PendingResult doGoAsync = this.zzamz.doGoAsync();
            action = intent.getStringExtra("referrer");
            if (action == null) {
                zzgi.zzjc().log("Install referrer extras are null");
                if (doGoAsync != null) {
                    doGoAsync.finish();
                }
                return;
            }
            zzgi.zzja().zzg("Install referrer extras are", action);
            if (!action.contains(Operation.EMPTY_PARAM)) {
                String str = Operation.EMPTY_PARAM;
                action = String.valueOf(action);
                action = action.length() != 0 ? str.concat(action) : new String(str);
            }
            Bundle zza2 = zza.zzgg().zza(Uri.parse(action));
            if (zza2 == null) {
                zzgi.zzjc().log("No campaign defined in install referrer broadcast");
                if (doGoAsync != null) {
                    doGoAsync.finish();
                    return;
                }
            }
            long longExtra = intent.getLongExtra("referrer_timestamp_seconds", 0) * 1000;
            if (longExtra == 0) {
                zzgi.zziy().log("Install referrer is missing timestamp");
            }
            zza.zzgh().zzc(new zzgf(this, zza, longExtra, zza2, context, zzgi, doGoAsync));
        }
    }
}
