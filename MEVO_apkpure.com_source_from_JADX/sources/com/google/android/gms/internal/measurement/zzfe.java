package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;

public final class zzfe extends zzdz {
    private final zzff zzakf = new zzff(this, getContext(), "google_app_measurement_local.db");
    private boolean zzakg;

    zzfe(zzgn zzgn) {
        super(zzgn);
    }

    @WorkerThread
    @VisibleForTesting
    private final SQLiteDatabase getWritableDatabase() throws SQLiteException {
        if (this.zzakg) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zzakf.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzakg = true;
        return null;
    }

    @android.support.annotation.WorkerThread
    private final boolean zza(int r20, byte[] r21) {
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
        r19 = this;
        r1 = r19;
        r19.zzfv();
        r19.zzab();
        r2 = r1.zzakg;
        r3 = 0;
        if (r2 == 0) goto L_0x000e;
    L_0x000d:
        return r3;
    L_0x000e:
        r2 = new android.content.ContentValues;
        r2.<init>();
        r4 = "type";
        r5 = java.lang.Integer.valueOf(r20);
        r2.put(r4, r5);
        r4 = "entry";
        r5 = r21;
        r2.put(r4, r5);
        r4 = 5;
        r5 = 0;
        r6 = 5;
    L_0x0026:
        if (r5 >= r4) goto L_0x0160;
    L_0x0028:
        r7 = 0;
        r8 = 1;
        r9 = r19.getWritableDatabase();	 Catch:{ SQLiteFullException -> 0x0130, SQLiteDatabaseLockedException -> 0x011d, SQLiteException -> 0x00ee, all -> 0x00e7 }
        if (r9 != 0) goto L_0x0045;
    L_0x0030:
        r1.zzakg = r8;	 Catch:{ SQLiteFullException -> 0x0041, SQLiteDatabaseLockedException -> 0x003e, SQLiteException -> 0x0038 }
        if (r9 == 0) goto L_0x0037;
    L_0x0034:
        r9.close();
    L_0x0037:
        return r3;
    L_0x0038:
        r0 = move-exception;
        r3 = r0;
        r12 = r7;
    L_0x003b:
        r7 = r9;
        goto L_0x00f2;
    L_0x003e:
        r4 = r7;
        goto L_0x00e1;
    L_0x0041:
        r0 = move-exception;
    L_0x0042:
        r3 = r0;
        goto L_0x0134;
    L_0x0045:
        r9.beginTransaction();	 Catch:{ SQLiteFullException -> 0x00e3, SQLiteDatabaseLockedException -> 0x003e, SQLiteException -> 0x00db, all -> 0x00d5 }
        r10 = 0;	 Catch:{ SQLiteFullException -> 0x00e3, SQLiteDatabaseLockedException -> 0x003e, SQLiteException -> 0x00db, all -> 0x00d5 }
        r12 = "select count(1) from messages";	 Catch:{ SQLiteFullException -> 0x00e3, SQLiteDatabaseLockedException -> 0x003e, SQLiteException -> 0x00db, all -> 0x00d5 }
        r12 = r9.rawQuery(r12, r7);	 Catch:{ SQLiteFullException -> 0x00e3, SQLiteDatabaseLockedException -> 0x003e, SQLiteException -> 0x00db, all -> 0x00d5 }
        if (r12 == 0) goto L_0x0069;
    L_0x0052:
        r13 = r12.moveToFirst();	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        if (r13 == 0) goto L_0x0069;	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
    L_0x0058:
        r10 = r12.getLong(r3);	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        goto L_0x0069;	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
    L_0x005d:
        r0 = move-exception;	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r2 = r0;	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        goto L_0x0155;	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
    L_0x0061:
        r0 = move-exception;	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r3 = r0;	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        goto L_0x003b;	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
    L_0x0064:
        r0 = move-exception;	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r3 = r0;	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r7 = r12;	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        goto L_0x0134;	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
    L_0x0069:
        r13 = 100000; // 0x186a0 float:1.4013E-40 double:4.94066E-319;	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r15 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1));	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        if (r15 < 0) goto L_0x00ba;	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
    L_0x0070:
        r15 = r19.zzgi();	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r15 = r15.zziv();	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r4 = "Data loss, local db full";	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r15.log(r4);	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r4 = 0;	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r16 = r13 - r10;	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r10 = 1;	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r13 = r16 + r10;	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r4 = "messages";	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r10 = "rowid in (select rowid from messages order by rowid asc limit ?)";	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r11 = new java.lang.String[r8];	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r15 = java.lang.Long.toString(r13);	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r11[r3] = r15;	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r4 = r9.delete(r4, r10, r11);	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r10 = (long) r4;	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r4 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1));	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        if (r4 == 0) goto L_0x00ba;	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
    L_0x0099:
        r4 = r19.zzgi();	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r4 = r4.zziv();	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r15 = "Different delete count than expected in local db. expected, received, difference";	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r3 = java.lang.Long.valueOf(r13);	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r8 = java.lang.Long.valueOf(r10);	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r16 = 0;	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r18 = r8;	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r7 = r13 - r10;	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r7 = java.lang.Long.valueOf(r7);	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r8 = r18;	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r4.zzd(r15, r3, r8, r7);	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
    L_0x00ba:
        r3 = "messages";	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r4 = 0;	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r9.insertOrThrow(r3, r4, r2);	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r9.setTransactionSuccessful();	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        r9.endTransaction();	 Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        if (r12 == 0) goto L_0x00cb;
    L_0x00c8:
        r12.close();
    L_0x00cb:
        if (r9 == 0) goto L_0x00d0;
    L_0x00cd:
        r9.close();
    L_0x00d0:
        r2 = 1;
        return r2;
    L_0x00d2:
        r7 = r12;
        goto L_0x011f;
    L_0x00d5:
        r0 = move-exception;
        r4 = r7;
        r2 = r0;
        r12 = r4;
        goto L_0x0155;
    L_0x00db:
        r0 = move-exception;
        r4 = r7;
        r3 = r0;
        r12 = r4;
        goto L_0x003b;
    L_0x00e1:
        r7 = r4;
        goto L_0x011f;
    L_0x00e3:
        r0 = move-exception;
        r4 = r7;
        goto L_0x0042;
    L_0x00e7:
        r0 = move-exception;
        r4 = r7;
        r2 = r0;
        r9 = r4;
        r12 = r9;
        goto L_0x0155;
    L_0x00ee:
        r0 = move-exception;
        r4 = r7;
        r3 = r0;
        r12 = r7;
    L_0x00f2:
        if (r7 == 0) goto L_0x0102;
    L_0x00f4:
        r4 = r7.inTransaction();	 Catch:{ all -> 0x00fe }
        if (r4 == 0) goto L_0x0102;	 Catch:{ all -> 0x00fe }
    L_0x00fa:
        r7.endTransaction();	 Catch:{ all -> 0x00fe }
        goto L_0x0102;	 Catch:{ all -> 0x00fe }
    L_0x00fe:
        r0 = move-exception;	 Catch:{ all -> 0x00fe }
        r2 = r0;	 Catch:{ all -> 0x00fe }
        r9 = r7;	 Catch:{ all -> 0x00fe }
        goto L_0x0155;	 Catch:{ all -> 0x00fe }
    L_0x0102:
        r4 = r19.zzgi();	 Catch:{ all -> 0x00fe }
        r4 = r4.zziv();	 Catch:{ all -> 0x00fe }
        r8 = "Error writing entry to local database";	 Catch:{ all -> 0x00fe }
        r4.zzg(r8, r3);	 Catch:{ all -> 0x00fe }
        r3 = 1;	 Catch:{ all -> 0x00fe }
        r1.zzakg = r3;	 Catch:{ all -> 0x00fe }
        if (r12 == 0) goto L_0x0117;
    L_0x0114:
        r12.close();
    L_0x0117:
        if (r7 == 0) goto L_0x014c;
    L_0x0119:
        r7.close();
        goto L_0x014c;
    L_0x011d:
        r4 = r7;
        r9 = r7;
    L_0x011f:
        r3 = (long) r6;
        android.os.SystemClock.sleep(r3);	 Catch:{ all -> 0x0152 }
        r6 = r6 + 20;
        if (r7 == 0) goto L_0x012a;
    L_0x0127:
        r7.close();
    L_0x012a:
        if (r9 == 0) goto L_0x014c;
    L_0x012c:
        r9.close();
        goto L_0x014c;
    L_0x0130:
        r0 = move-exception;
        r4 = r7;
        r3 = r0;
        r9 = r7;
    L_0x0134:
        r4 = r19.zzgi();	 Catch:{ all -> 0x0152 }
        r4 = r4.zziv();	 Catch:{ all -> 0x0152 }
        r8 = "Error writing entry to local database";	 Catch:{ all -> 0x0152 }
        r4.zzg(r8, r3);	 Catch:{ all -> 0x0152 }
        r3 = 1;	 Catch:{ all -> 0x0152 }
        r1.zzakg = r3;	 Catch:{ all -> 0x0152 }
        if (r7 == 0) goto L_0x0149;
    L_0x0146:
        r7.close();
    L_0x0149:
        if (r9 == 0) goto L_0x014c;
    L_0x014b:
        goto L_0x012c;
    L_0x014c:
        r5 = r5 + 1;
        r3 = 0;
        r4 = 5;
        goto L_0x0026;
    L_0x0152:
        r0 = move-exception;
        r2 = r0;
        r12 = r7;
    L_0x0155:
        if (r12 == 0) goto L_0x015a;
    L_0x0157:
        r12.close();
    L_0x015a:
        if (r9 == 0) goto L_0x015f;
    L_0x015c:
        r9.close();
    L_0x015f:
        throw r2;
    L_0x0160:
        r2 = r19.zzgi();
        r2 = r2.zziy();
        r3 = "Failed to write entry to local database";
        r2.log(r3);
        r2 = 0;
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzfe.zza(int, byte[]):boolean");
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @WorkerThread
    public final void resetAnalyticsData() {
        zzfv();
        zzab();
        try {
            int delete = getWritableDatabase().delete("messages", null, null) + 0;
            if (delete > 0) {
                zzgi().zzjc().zzg("Reset local analytics data. records", Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzgi().zziv().zzg("Error resetting local analytics data. error", e);
        }
    }

    public final boolean zza(zzex zzex) {
        Parcel obtain = Parcel.obtain();
        zzex.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(0, marshall);
        }
        zzgi().zziy().log("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public final boolean zza(zzka zzka) {
        Parcel obtain = Parcel.obtain();
        zzka.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(1, marshall);
        }
        zzgi().zziy().log("User property too long for local database. Sending directly to service");
        return false;
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    public final boolean zzc(zzef zzef) {
        zzgg();
        byte[] zza = zzkd.zza((Parcelable) zzef);
        if (zza.length <= 131072) {
            return zza(2, zza);
        }
        zzgi().zziy().log("Conditional user property too long for local database. Sending directly to service");
        return false;
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

    public final java.util.List<com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable> zzp(int r21) {
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
        r20 = this;
        r1 = r20;
        r20.zzab();
        r20.zzfv();
        r2 = r1.zzakg;
        r3 = 0;
        if (r2 == 0) goto L_0x000e;
    L_0x000d:
        return r3;
    L_0x000e:
        r2 = new java.util.ArrayList;
        r2.<init>();
        r4 = r20.getContext();
        r5 = "google_app_measurement_local.db";
        r4 = r4.getDatabasePath(r5);
        r4 = r4.exists();
        if (r4 != 0) goto L_0x0024;
    L_0x0023:
        return r2;
    L_0x0024:
        r4 = 5;
        r5 = 0;
        r6 = 0;
        r7 = 5;
    L_0x0028:
        if (r6 >= r4) goto L_0x01fb;
    L_0x002a:
        r8 = 1;
        r15 = r20.getWritableDatabase();	 Catch:{ SQLiteFullException -> 0x01cb, SQLiteDatabaseLockedException -> 0x01b5, SQLiteException -> 0x018f, all -> 0x0189 }
        if (r15 != 0) goto L_0x004b;
    L_0x0031:
        r1.zzakg = r8;	 Catch:{ SQLiteFullException -> 0x0046, SQLiteDatabaseLockedException -> 0x0043, SQLiteException -> 0x003e, all -> 0x0039 }
        if (r15 == 0) goto L_0x0038;
    L_0x0035:
        r15.close();
    L_0x0038:
        return r3;
    L_0x0039:
        r0 = move-exception;
        r2 = r0;
        r9 = r3;
        goto L_0x01ef;
    L_0x003e:
        r0 = move-exception;
        r4 = r0;
        r9 = r3;
        goto L_0x0193;
    L_0x0043:
        r4 = r15;
        goto L_0x0182;
    L_0x0046:
        r0 = move-exception;
        r4 = r0;
        r9 = r3;
        goto L_0x01cf;
    L_0x004b:
        r15.beginTransaction();	 Catch:{ SQLiteFullException -> 0x0184, SQLiteDatabaseLockedException -> 0x0043, SQLiteException -> 0x017d, all -> 0x0177 }
        r10 = "messages";	 Catch:{ SQLiteFullException -> 0x0184, SQLiteDatabaseLockedException -> 0x0043, SQLiteException -> 0x017d, all -> 0x0177 }
        r9 = "rowid";	 Catch:{ SQLiteFullException -> 0x0184, SQLiteDatabaseLockedException -> 0x0043, SQLiteException -> 0x017d, all -> 0x0177 }
        r11 = "type";	 Catch:{ SQLiteFullException -> 0x0184, SQLiteDatabaseLockedException -> 0x0043, SQLiteException -> 0x017d, all -> 0x0177 }
        r12 = "entry";	 Catch:{ SQLiteFullException -> 0x0184, SQLiteDatabaseLockedException -> 0x0043, SQLiteException -> 0x017d, all -> 0x0177 }
        r11 = new java.lang.String[]{r9, r11, r12};	 Catch:{ SQLiteFullException -> 0x0184, SQLiteDatabaseLockedException -> 0x0043, SQLiteException -> 0x017d, all -> 0x0177 }
        r12 = 0;	 Catch:{ SQLiteFullException -> 0x0184, SQLiteDatabaseLockedException -> 0x0043, SQLiteException -> 0x017d, all -> 0x0177 }
        r13 = 0;	 Catch:{ SQLiteFullException -> 0x0184, SQLiteDatabaseLockedException -> 0x0043, SQLiteException -> 0x017d, all -> 0x0177 }
        r14 = 0;	 Catch:{ SQLiteFullException -> 0x0184, SQLiteDatabaseLockedException -> 0x0043, SQLiteException -> 0x017d, all -> 0x0177 }
        r16 = 0;	 Catch:{ SQLiteFullException -> 0x0184, SQLiteDatabaseLockedException -> 0x0043, SQLiteException -> 0x017d, all -> 0x0177 }
        r17 = "rowid asc";	 Catch:{ SQLiteFullException -> 0x0184, SQLiteDatabaseLockedException -> 0x0043, SQLiteException -> 0x017d, all -> 0x0177 }
        r9 = 100;	 Catch:{ SQLiteFullException -> 0x0184, SQLiteDatabaseLockedException -> 0x0043, SQLiteException -> 0x017d, all -> 0x0177 }
        r18 = java.lang.Integer.toString(r9);	 Catch:{ SQLiteFullException -> 0x0184, SQLiteDatabaseLockedException -> 0x0043, SQLiteException -> 0x017d, all -> 0x0177 }
        r9 = r15;
        r4 = r15;
        r15 = r16;
        r16 = r17;
        r17 = r18;
        r9 = r9.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ SQLiteFullException -> 0x0173, SQLiteDatabaseLockedException -> 0x0182, SQLiteException -> 0x016f, all -> 0x016d }
        r10 = -1;
    L_0x0075:
        r12 = r9.moveToNext();	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        if (r12 == 0) goto L_0x0135;	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
    L_0x007b:
        r10 = r9.getLong(r5);	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        r12 = r9.getInt(r8);	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        r13 = 2;	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        r14 = r9.getBlob(r13);	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        if (r12 != 0) goto L_0x00be;	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
    L_0x008a:
        r12 = android.os.Parcel.obtain();	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        r13 = r14.length;	 Catch:{ ParseException -> 0x00a9 }
        r12.unmarshall(r14, r5, r13);	 Catch:{ ParseException -> 0x00a9 }
        r12.setDataPosition(r5);	 Catch:{ ParseException -> 0x00a9 }
        r13 = com.google.android.gms.internal.measurement.zzex.CREATOR;	 Catch:{ ParseException -> 0x00a9 }
        r13 = r13.createFromParcel(r12);	 Catch:{ ParseException -> 0x00a9 }
        r13 = (com.google.android.gms.internal.measurement.zzex) r13;	 Catch:{ ParseException -> 0x00a9 }
        r12.recycle();	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        if (r13 == 0) goto L_0x0075;	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
    L_0x00a2:
        r2.add(r13);	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        goto L_0x0075;
    L_0x00a6:
        r0 = move-exception;
        r10 = r0;
        goto L_0x00ba;
    L_0x00a9:
        r13 = r20.zzgi();	 Catch:{ all -> 0x00a6 }
        r13 = r13.zziv();	 Catch:{ all -> 0x00a6 }
        r14 = "Failed to load event from local database";	 Catch:{ all -> 0x00a6 }
        r13.log(r14);	 Catch:{ all -> 0x00a6 }
        r12.recycle();	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        goto L_0x0075;	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
    L_0x00ba:
        r12.recycle();	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        throw r10;	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
    L_0x00be:
        if (r12 != r8) goto L_0x00f2;	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
    L_0x00c0:
        r12 = android.os.Parcel.obtain();	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        r13 = r14.length;	 Catch:{ ParseException -> 0x00da }
        r12.unmarshall(r14, r5, r13);	 Catch:{ ParseException -> 0x00da }
        r12.setDataPosition(r5);	 Catch:{ ParseException -> 0x00da }
        r13 = com.google.android.gms.internal.measurement.zzka.CREATOR;	 Catch:{ ParseException -> 0x00da }
        r13 = r13.createFromParcel(r12);	 Catch:{ ParseException -> 0x00da }
        r13 = (com.google.android.gms.internal.measurement.zzka) r13;	 Catch:{ ParseException -> 0x00da }
        r12.recycle();	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        goto L_0x00eb;
    L_0x00d7:
        r0 = move-exception;
        r10 = r0;
        goto L_0x00ee;
    L_0x00da:
        r13 = r20.zzgi();	 Catch:{ all -> 0x00d7 }
        r13 = r13.zziv();	 Catch:{ all -> 0x00d7 }
        r14 = "Failed to load user property from local database";	 Catch:{ all -> 0x00d7 }
        r13.log(r14);	 Catch:{ all -> 0x00d7 }
        r12.recycle();	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        r13 = r3;	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
    L_0x00eb:
        if (r13 == 0) goto L_0x0075;	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
    L_0x00ed:
        goto L_0x00a2;	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
    L_0x00ee:
        r12.recycle();	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        throw r10;	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
    L_0x00f2:
        if (r12 != r13) goto L_0x0126;	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
    L_0x00f4:
        r12 = android.os.Parcel.obtain();	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        r13 = r14.length;	 Catch:{ ParseException -> 0x010e }
        r12.unmarshall(r14, r5, r13);	 Catch:{ ParseException -> 0x010e }
        r12.setDataPosition(r5);	 Catch:{ ParseException -> 0x010e }
        r13 = com.google.android.gms.internal.measurement.zzef.CREATOR;	 Catch:{ ParseException -> 0x010e }
        r13 = r13.createFromParcel(r12);	 Catch:{ ParseException -> 0x010e }
        r13 = (com.google.android.gms.internal.measurement.zzef) r13;	 Catch:{ ParseException -> 0x010e }
        r12.recycle();	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        goto L_0x011f;
    L_0x010b:
        r0 = move-exception;
        r10 = r0;
        goto L_0x0122;
    L_0x010e:
        r13 = r20.zzgi();	 Catch:{ all -> 0x010b }
        r13 = r13.zziv();	 Catch:{ all -> 0x010b }
        r14 = "Failed to load user property from local database";	 Catch:{ all -> 0x010b }
        r13.log(r14);	 Catch:{ all -> 0x010b }
        r12.recycle();	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        r13 = r3;	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
    L_0x011f:
        if (r13 == 0) goto L_0x0075;	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
    L_0x0121:
        goto L_0x00a2;	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
    L_0x0122:
        r12.recycle();	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        throw r10;	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
    L_0x0126:
        r12 = r20.zzgi();	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        r12 = r12.zziv();	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        r13 = "Unknown record type in local database";	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        r12.log(r13);	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        goto L_0x0075;	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
    L_0x0135:
        r12 = "messages";	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        r13 = "rowid <= ?";	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        r14 = new java.lang.String[r8];	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        r10 = java.lang.Long.toString(r10);	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        r14[r5] = r10;	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        r10 = r4.delete(r12, r13, r14);	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        r11 = r2.size();	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        if (r10 >= r11) goto L_0x0158;	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
    L_0x014b:
        r10 = r20.zzgi();	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        r10 = r10.zziv();	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        r11 = "Fewer entries removed from local database than expected";	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        r10.log(r11);	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
    L_0x0158:
        r4.setTransactionSuccessful();	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        r4.endTransaction();	 Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x01b7, SQLiteException -> 0x0169 }
        if (r9 == 0) goto L_0x0163;
    L_0x0160:
        r9.close();
    L_0x0163:
        if (r4 == 0) goto L_0x0168;
    L_0x0165:
        r4.close();
    L_0x0168:
        return r2;
    L_0x0169:
        r0 = move-exception;
        goto L_0x0171;
    L_0x016b:
        r0 = move-exception;
        goto L_0x0175;
    L_0x016d:
        r0 = move-exception;
        goto L_0x0179;
    L_0x016f:
        r0 = move-exception;
        r9 = r3;
    L_0x0171:
        r15 = r4;
        goto L_0x0180;
    L_0x0173:
        r0 = move-exception;
        r9 = r3;
    L_0x0175:
        r15 = r4;
        goto L_0x0187;
    L_0x0177:
        r0 = move-exception;
        r4 = r15;
    L_0x0179:
        r2 = r0;
        r9 = r3;
        goto L_0x01f0;
    L_0x017d:
        r0 = move-exception;
        r4 = r15;
        r9 = r3;
    L_0x0180:
        r4 = r0;
        goto L_0x0193;
    L_0x0182:
        r9 = r3;
        goto L_0x01b7;
    L_0x0184:
        r0 = move-exception;
        r4 = r15;
        r9 = r3;
    L_0x0187:
        r4 = r0;
        goto L_0x01cf;
    L_0x0189:
        r0 = move-exception;
        r2 = r0;
        r4 = r3;
        r9 = r4;
        goto L_0x01f0;
    L_0x018f:
        r0 = move-exception;
        r4 = r0;
        r9 = r3;
        r15 = r9;
    L_0x0193:
        if (r15 == 0) goto L_0x019e;
    L_0x0195:
        r10 = r15.inTransaction();	 Catch:{ all -> 0x01ed }
        if (r10 == 0) goto L_0x019e;	 Catch:{ all -> 0x01ed }
    L_0x019b:
        r15.endTransaction();	 Catch:{ all -> 0x01ed }
    L_0x019e:
        r10 = r20.zzgi();	 Catch:{ all -> 0x01ed }
        r10 = r10.zziv();	 Catch:{ all -> 0x01ed }
        r11 = "Error reading entries from local database";	 Catch:{ all -> 0x01ed }
        r10.zzg(r11, r4);	 Catch:{ all -> 0x01ed }
        r1.zzakg = r8;	 Catch:{ all -> 0x01ed }
        if (r9 == 0) goto L_0x01b2;
    L_0x01af:
        r9.close();
    L_0x01b2:
        if (r15 == 0) goto L_0x01e8;
    L_0x01b4:
        goto L_0x01e5;
    L_0x01b5:
        r4 = r3;
        r9 = r4;
    L_0x01b7:
        r10 = (long) r7;
        android.os.SystemClock.sleep(r10);	 Catch:{ all -> 0x01c8 }
        r7 = r7 + 20;
        if (r9 == 0) goto L_0x01c2;
    L_0x01bf:
        r9.close();
    L_0x01c2:
        if (r4 == 0) goto L_0x01e8;
    L_0x01c4:
        r4.close();
        goto L_0x01e8;
    L_0x01c8:
        r0 = move-exception;
        r2 = r0;
        goto L_0x01f0;
    L_0x01cb:
        r0 = move-exception;
        r4 = r0;
        r9 = r3;
        r15 = r9;
    L_0x01cf:
        r10 = r20.zzgi();	 Catch:{ all -> 0x01ed }
        r10 = r10.zziv();	 Catch:{ all -> 0x01ed }
        r11 = "Error reading entries from local database";	 Catch:{ all -> 0x01ed }
        r10.zzg(r11, r4);	 Catch:{ all -> 0x01ed }
        r1.zzakg = r8;	 Catch:{ all -> 0x01ed }
        if (r9 == 0) goto L_0x01e3;
    L_0x01e0:
        r9.close();
    L_0x01e3:
        if (r15 == 0) goto L_0x01e8;
    L_0x01e5:
        r15.close();
    L_0x01e8:
        r6 = r6 + 1;
        r4 = 5;
        goto L_0x0028;
    L_0x01ed:
        r0 = move-exception;
        r2 = r0;
    L_0x01ef:
        r4 = r15;
    L_0x01f0:
        if (r9 == 0) goto L_0x01f5;
    L_0x01f2:
        r9.close();
    L_0x01f5:
        if (r4 == 0) goto L_0x01fa;
    L_0x01f7:
        r4.close();
    L_0x01fa:
        throw r2;
    L_0x01fb:
        r2 = r20.zzgi();
        r2 = r2.zziy();
        r4 = "Failed to read events from database in reasonable time";
        r2.log(r4);
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzfe.zzp(int):java.util.List<com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable>");
    }
}
