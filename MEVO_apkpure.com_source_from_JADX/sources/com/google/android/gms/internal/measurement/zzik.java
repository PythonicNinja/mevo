package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.mevo.app.tools.formatters.Formatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@VisibleForTesting
public final class zzik extends zzdz {
    private final zziy zzaqo;
    private zzfa zzaqp;
    private volatile Boolean zzaqq;
    private final zzep zzaqr;
    private final zzjo zzaqs;
    private final List<Runnable> zzaqt = new ArrayList();
    private final zzep zzaqu;

    protected zzik(zzgn zzgn) {
        super(zzgn);
        this.zzaqs = new zzjo(zzgn.zzbt());
        this.zzaqo = new zziy(this);
        this.zzaqr = new zzil(this, zzgn);
        this.zzaqu = new zziq(this, zzgn);
    }

    @WorkerThread
    private final void onServiceDisconnected(ComponentName componentName) {
        zzab();
        if (this.zzaqp != null) {
            this.zzaqp = null;
            zzgi().zzjc().zzg("Disconnected from device MeasurementService", componentName);
            zzab();
            zzdf();
        }
    }

    @WorkerThread
    private final void zzcu() {
        zzab();
        this.zzaqs.start();
        this.zzaqr.zzh(((Long) zzez.zzajk.get()).longValue());
    }

    @WorkerThread
    private final void zzcv() {
        zzab();
        if (isConnected()) {
            zzgi().zzjc().log("Inactivity, disconnecting from the service");
            disconnect();
        }
    }

    @WorkerThread
    private final void zzf(Runnable runnable) throws IllegalStateException {
        zzab();
        if (isConnected()) {
            runnable.run();
        } else if (((long) this.zzaqt.size()) >= 1000) {
            zzgi().zziv().log("Discarding data. Max runnable queue size reached");
        } else {
            this.zzaqt.add(runnable);
            this.zzaqu.zzh(Formatter.MINUTE);
            zzdf();
        }
    }

    @Nullable
    @WorkerThread
    private final zzeb zzk(boolean z) {
        zzgl();
        return zzfz().zzbl(z ? zzgi().zzje() : null);
    }

    private final boolean zzkq() {
        zzgl();
        return true;
    }

    @WorkerThread
    private final void zzks() {
        zzab();
        zzgi().zzjc().zzg("Processing queued up service tasks", Integer.valueOf(this.zzaqt.size()));
        for (Runnable run : this.zzaqt) {
            try {
                run.run();
            } catch (Exception e) {
                zzgi().zziv().zzg("Task exception while flushing queue", e);
            }
        }
        this.zzaqt.clear();
        this.zzaqu.cancel();
    }

    @android.support.annotation.WorkerThread
    public final void disconnect() {
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
        r3.zzch();
        r0 = com.google.android.gms.common.stats.ConnectionTracker.getInstance();	 Catch:{ IllegalStateException -> 0x0013, IllegalStateException -> 0x0013 }
        r1 = r3.getContext();	 Catch:{ IllegalStateException -> 0x0013, IllegalStateException -> 0x0013 }
        r2 = r3.zzaqo;	 Catch:{ IllegalStateException -> 0x0013, IllegalStateException -> 0x0013 }
        r0.unbindService(r1, r2);	 Catch:{ IllegalStateException -> 0x0013, IllegalStateException -> 0x0013 }
    L_0x0013:
        r0 = 0;
        r3.zzaqp = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzik.disconnect():void");
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @WorkerThread
    public final boolean isConnected() {
        zzab();
        zzch();
        return this.zzaqp != null;
    }

    @WorkerThread
    protected final void resetAnalyticsData() {
        zzab();
        zzfv();
        zzch();
        zzeb zzk = zzk(false);
        if (zzkq()) {
            zzgc().resetAnalyticsData();
        }
        zzf(new zzim(this, zzk));
    }

    @WorkerThread
    @VisibleForTesting
    protected final void zza(zzfa zzfa) {
        zzab();
        Preconditions.checkNotNull(zzfa);
        this.zzaqp = zzfa;
        zzcu();
        zzks();
    }

    @WorkerThread
    @VisibleForTesting
    final void zza(zzfa zzfa, AbstractSafeParcelable abstractSafeParcelable, zzeb zzeb) {
        zzfk zziv;
        String str;
        zzab();
        zzfv();
        zzch();
        boolean zzkq = zzkq();
        int i = 0;
        int i2 = 100;
        while (i < 1001 && r4 == 100) {
            int size;
            ArrayList arrayList;
            int size2;
            int i3;
            AbstractSafeParcelable abstractSafeParcelable2;
            List arrayList2 = new ArrayList();
            if (zzkq) {
                Object zzp = zzgc().zzp(100);
                if (zzp != null) {
                    arrayList2.addAll(zzp);
                    size = zzp.size();
                    if (abstractSafeParcelable != null && size < 100) {
                        arrayList2.add(abstractSafeParcelable);
                    }
                    arrayList = (ArrayList) arrayList2;
                    size2 = arrayList.size();
                    i3 = 0;
                    while (i3 < size2) {
                        Object obj = arrayList.get(i3);
                        i3++;
                        abstractSafeParcelable2 = (AbstractSafeParcelable) obj;
                        if (abstractSafeParcelable2 instanceof zzex) {
                            try {
                                zzfa.zza((zzex) abstractSafeParcelable2, zzeb);
                            } catch (RemoteException e) {
                                obj = e;
                                zziv = zzgi().zziv();
                                str = "Failed to send event to the service";
                                zziv.zzg(str, obj);
                            }
                        } else if (abstractSafeParcelable2 instanceof zzka) {
                            try {
                                zzfa.zza((zzka) abstractSafeParcelable2, zzeb);
                            } catch (RemoteException e2) {
                                obj = e2;
                                zziv = zzgi().zziv();
                                str = "Failed to send attribute to the service";
                                zziv.zzg(str, obj);
                            }
                        } else if (abstractSafeParcelable2 instanceof zzef) {
                            zzgi().zziv().log("Discarding data. Unrecognized parcel type.");
                        } else {
                            try {
                                zzfa.zza((zzef) abstractSafeParcelable2, zzeb);
                            } catch (RemoteException e3) {
                                obj = e3;
                                zziv = zzgi().zziv();
                                str = "Failed to send conditional property to the service";
                                zziv.zzg(str, obj);
                            }
                        }
                    }
                    i++;
                    i2 = size;
                }
            }
            size = 0;
            arrayList2.add(abstractSafeParcelable);
            arrayList = (ArrayList) arrayList2;
            size2 = arrayList.size();
            i3 = 0;
            while (i3 < size2) {
                Object obj2 = arrayList.get(i3);
                i3++;
                abstractSafeParcelable2 = (AbstractSafeParcelable) obj2;
                if (abstractSafeParcelable2 instanceof zzex) {
                    zzfa.zza((zzex) abstractSafeParcelable2, zzeb);
                } else if (abstractSafeParcelable2 instanceof zzka) {
                    zzfa.zza((zzka) abstractSafeParcelable2, zzeb);
                } else if (abstractSafeParcelable2 instanceof zzef) {
                    zzgi().zziv().log("Discarding data. Unrecognized parcel type.");
                } else {
                    zzfa.zza((zzef) abstractSafeParcelable2, zzeb);
                }
            }
            i++;
            i2 = size;
        }
    }

    @WorkerThread
    public final void zza(AtomicReference<String> atomicReference) {
        zzab();
        zzch();
        zzf(new zzin(this, atomicReference, zzk(false)));
    }

    @WorkerThread
    protected final void zza(AtomicReference<List<zzef>> atomicReference, String str, String str2, String str3) {
        zzab();
        zzch();
        zzf(new zziu(this, atomicReference, str, str2, str3, zzk(false)));
    }

    @WorkerThread
    protected final void zza(AtomicReference<List<zzka>> atomicReference, String str, String str2, String str3, boolean z) {
        zzab();
        zzch();
        zzf(new zziv(this, atomicReference, str, str2, str3, z, zzk(false)));
    }

    @WorkerThread
    protected final void zza(AtomicReference<List<zzka>> atomicReference, boolean z) {
        zzab();
        zzch();
        zzf(new zzix(this, atomicReference, zzk(false), z));
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    @WorkerThread
    protected final void zzb(zzex zzex, String str) {
        Preconditions.checkNotNull(zzex);
        zzab();
        zzch();
        boolean zzkq = zzkq();
        boolean z = zzkq && zzgc().zza(zzex);
        zzf(new zzis(this, zzkq, z, zzex, zzk(true), str));
    }

    @WorkerThread
    protected final void zzb(zzig zzig) {
        zzab();
        zzch();
        zzf(new zzip(this, zzig));
    }

    @WorkerThread
    protected final void zzb(zzka zzka) {
        zzab();
        zzch();
        boolean z = zzkq() && zzgc().zza(zzka);
        zzf(new zziw(this, z, zzka, zzk(true)));
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    @WorkerThread
    protected final void zzd(zzef zzef) {
        Preconditions.checkNotNull(zzef);
        zzab();
        zzch();
        zzgl();
        zzf(new zzit(this, true, zzgc().zzc(zzef), new zzef(zzef), zzk(true), zzef));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.WorkerThread
    final void zzdf() {
        /*
        r6 = this;
        r6.zzab();
        r6.zzch();
        r0 = r6.isConnected();
        if (r0 == 0) goto L_0x000d;
    L_0x000c:
        return;
    L_0x000d:
        r0 = r6.zzaqq;
        r1 = 0;
        r2 = 1;
        if (r0 != 0) goto L_0x0115;
    L_0x0013:
        r6.zzab();
        r6.zzch();
        r0 = r6.zzgj();
        r0 = r0.zzjl();
        if (r0 == 0) goto L_0x002c;
    L_0x0023:
        r0 = r0.booleanValue();
        if (r0 == 0) goto L_0x002c;
    L_0x0029:
        r0 = 1;
        goto L_0x010f;
    L_0x002c:
        r6.zzgl();
        r0 = r6.zzfz();
        r0 = r0.zzit();
        if (r0 != r2) goto L_0x003d;
    L_0x0039:
        r0 = 1;
    L_0x003a:
        r3 = 1;
        goto L_0x00ec;
    L_0x003d:
        r0 = r6.zzgi();
        r0 = r0.zzjc();
        r3 = "Checking service availability";
        r0.log(r3);
        r0 = r6.zzgg();
        r3 = com.google.android.gms.common.GoogleApiAvailabilityLight.getInstance();
        r0 = r0.getContext();
        r4 = 12451000; // 0xbdfcb8 float:1.7447567E-38 double:6.1516114E-317;
        r0 = r3.isGooglePlayServicesAvailable(r0, r4);
        r3 = 9;
        if (r0 == r3) goto L_0x00e1;
    L_0x0061:
        r3 = 18;
        if (r0 == r3) goto L_0x00d6;
    L_0x0065:
        switch(r0) {
            case 0: goto L_0x00c7;
            case 1: goto L_0x00b7;
            case 2: goto L_0x008b;
            case 3: goto L_0x007d;
            default: goto L_0x0068;
        };
    L_0x0068:
        r3 = r6.zzgi();
        r3 = r3.zziy();
        r4 = "Unexpected service status";
        r0 = java.lang.Integer.valueOf(r0);
        r3.zzg(r4, r0);
    L_0x0079:
        r0 = 0;
    L_0x007a:
        r3 = 0;
        goto L_0x00ec;
    L_0x007d:
        r0 = r6.zzgi();
        r0 = r0.zziy();
        r3 = "Service disabled";
    L_0x0087:
        r0.log(r3);
        goto L_0x0079;
    L_0x008b:
        r0 = r6.zzgi();
        r0 = r0.zzjb();
        r3 = "Service container out of date";
        r0.log(r3);
        r0 = r6.zzgg();
        r0 = r0.zzlp();
        r3 = 12600; // 0x3138 float:1.7656E-41 double:6.225E-320;
        if (r0 >= r3) goto L_0x00a5;
    L_0x00a4:
        goto L_0x00c4;
    L_0x00a5:
        r0 = r6.zzgj();
        r0 = r0.zzjl();
        if (r0 == 0) goto L_0x00b5;
    L_0x00af:
        r0 = r0.booleanValue();
        if (r0 == 0) goto L_0x0079;
    L_0x00b5:
        r0 = 1;
        goto L_0x007a;
    L_0x00b7:
        r0 = r6.zzgi();
        r0 = r0.zzjc();
        r3 = "Service missing";
        r0.log(r3);
    L_0x00c4:
        r0 = 0;
        goto L_0x003a;
    L_0x00c7:
        r0 = r6.zzgi();
        r0 = r0.zzjc();
        r3 = "Service available";
    L_0x00d1:
        r0.log(r3);
        goto L_0x0039;
    L_0x00d6:
        r0 = r6.zzgi();
        r0 = r0.zziy();
        r3 = "Service updating";
        goto L_0x00d1;
    L_0x00e1:
        r0 = r6.zzgi();
        r0 = r0.zziy();
        r3 = "Service invalid";
        goto L_0x0087;
    L_0x00ec:
        if (r0 != 0) goto L_0x0106;
    L_0x00ee:
        r4 = r6.zzgk();
        r4 = r4.zzhu();
        if (r4 == 0) goto L_0x0106;
    L_0x00f8:
        r3 = r6.zzgi();
        r3 = r3.zziv();
        r4 = "No way to upload. Consider using the full version of Analytics";
        r3.log(r4);
        r3 = 0;
    L_0x0106:
        if (r3 == 0) goto L_0x010f;
    L_0x0108:
        r3 = r6.zzgj();
        r3.zzf(r0);
    L_0x010f:
        r0 = java.lang.Boolean.valueOf(r0);
        r6.zzaqq = r0;
    L_0x0115:
        r0 = r6.zzaqq;
        r0 = r0.booleanValue();
        if (r0 == 0) goto L_0x0123;
    L_0x011d:
        r0 = r6.zzaqo;
        r0.zzkt();
        return;
    L_0x0123:
        r0 = r6.zzgk();
        r0 = r0.zzhu();
        if (r0 != 0) goto L_0x0183;
    L_0x012d:
        r6.zzgl();
        r0 = r6.getContext();
        r0 = r0.getPackageManager();
        r3 = new android.content.Intent;
        r3.<init>();
        r4 = r6.getContext();
        r5 = "com.google.android.gms.measurement.AppMeasurementService";
        r3 = r3.setClassName(r4, r5);
        r4 = 65536; // 0x10000 float:9.18355E-41 double:3.2379E-319;
        r0 = r0.queryIntentServices(r3, r4);
        if (r0 == 0) goto L_0x0156;
    L_0x014f:
        r0 = r0.size();
        if (r0 <= 0) goto L_0x0156;
    L_0x0155:
        r1 = 1;
    L_0x0156:
        if (r1 == 0) goto L_0x0176;
    L_0x0158:
        r0 = new android.content.Intent;
        r1 = "com.google.android.gms.measurement.START";
        r0.<init>(r1);
        r1 = new android.content.ComponentName;
        r2 = r6.getContext();
        r6.zzgl();
        r3 = "com.google.android.gms.measurement.AppMeasurementService";
        r1.<init>(r2, r3);
        r0.setComponent(r1);
        r1 = r6.zzaqo;
        r1.zzc(r0);
        return;
    L_0x0176:
        r0 = r6.zzgi();
        r0 = r0.zziv();
        r1 = "Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest";
        r0.log(r1);
    L_0x0183:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzik.zzdf():void");
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

    @WorkerThread
    protected final void zzkm() {
        zzab();
        zzch();
        zzf(new zzio(this, zzk(true)));
    }

    @WorkerThread
    protected final void zzkp() {
        zzab();
        zzch();
        zzf(new zzir(this, zzk(true)));
    }

    final Boolean zzkr() {
        return this.zzaqq;
    }
}
