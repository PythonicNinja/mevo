package com.google.android.gms.internal.measurement;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.data.DataBufferSafeParcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

final class zzek extends zzjs {
    private static final String[] zzagl = new String[]{"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;"};
    private static final String[] zzagm = new String[]{Param.ORIGIN, "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    private static final String[] zzagn = new String[]{"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;"};
    private static final String[] zzago = new String[]{"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    private static final String[] zzagp = new String[]{"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    private static final String[] zzagq = new String[]{"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    private final zzen zzagr = new zzen(this, getContext(), "google_app_measurement.db");
    private final zzjo zzags = new zzjo(zzbt());

    zzek(zzjt zzjt) {
        super(zzjt);
    }

    @WorkerThread
    private final long zza(String str, String[] strArr) {
        Object e;
        Throwable th;
        Cursor cursor = null;
        try {
            Cursor rawQuery = getWritableDatabase().rawQuery(str, strArr);
            try {
                if (rawQuery.moveToFirst()) {
                    long j = rawQuery.getLong(0);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    return j;
                }
                throw new SQLiteException("Database returned empty set");
            } catch (SQLiteException e2) {
                e = e2;
                cursor = rawQuery;
                try {
                    zzgi().zziv().zze("Database error", str, e);
                    throw e;
                } catch (Throwable th2) {
                    th = th2;
                    rawQuery = cursor;
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                throw th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            zzgi().zziv().zze("Database error", str, e);
            throw e;
        }
    }

    @WorkerThread
    private final long zza(String str, String[] strArr, long j) {
        Object e;
        Throwable th;
        Cursor cursor = null;
        try {
            Cursor rawQuery = getWritableDatabase().rawQuery(str, strArr);
            try {
                if (rawQuery.moveToFirst()) {
                    j = rawQuery.getLong(0);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    return j;
                }
                if (rawQuery != null) {
                    rawQuery.close();
                }
                return j;
            } catch (SQLiteException e2) {
                e = e2;
                cursor = rawQuery;
                try {
                    zzgi().zziv().zze("Database error", str, e);
                    throw e;
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                cursor = rawQuery;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (SQLiteException e3) {
            e = e3;
            zzgi().zziv().zze("Database error", str, e);
            throw e;
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final Object zza(Cursor cursor, int i) {
        int type = cursor.getType(i);
        switch (type) {
            case 0:
                zzgi().zziv().log("Loaded invalid null value from database");
                return null;
            case 1:
                return Long.valueOf(cursor.getLong(i));
            case 2:
                return Double.valueOf(cursor.getDouble(i));
            case 3:
                return cursor.getString(i);
            case 4:
                zzgi().zziv().log("Loaded invalid blob type value, ignoring it");
                return null;
            default:
                zzgi().zziv().zzg("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
                return null;
        }
    }

    @WorkerThread
    private static void zza(ContentValues contentValues, String str, Object obj) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
        } else if (obj instanceof Double) {
            contentValues.put(str, (Double) obj);
        } else {
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    @WorkerThread
    private final boolean zza(String str, int i, zzkh zzkh) {
        zzch();
        zzab();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzkh);
        if (TextUtils.isEmpty(zzkh.zzatl)) {
            zzgi().zziy().zzd("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", zzfi.zzbp(str), Integer.valueOf(i), String.valueOf(zzkh.zzatk));
            return false;
        }
        try {
            byte[] bArr = new byte[zzkh.zzwb()];
            zzacb zzb = zzacb.zzb(bArr, 0, bArr.length);
            zzkh.zza(zzb);
            zzb.zzvt();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", zzkh.zzatk);
            contentValues.put("event_name", zzkh.zzatl);
            contentValues.put(DataBufferSafeParcelable.DATA_FIELD, bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("event_filters", null, contentValues, 5) == -1) {
                    zzgi().zziv().zzg("Failed to insert event filter (got -1). appId", zzfi.zzbp(str));
                }
                return true;
            } catch (SQLiteException e) {
                zzgi().zziv().zze("Error storing event filter. appId", zzfi.zzbp(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzgi().zziv().zze("Configuration loss. Failed to serialize event filter. appId", zzfi.zzbp(str), e2);
            return false;
        }
    }

    @WorkerThread
    private final boolean zza(String str, int i, zzkk zzkk) {
        zzch();
        zzab();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzkk);
        if (TextUtils.isEmpty(zzkk.zzauc)) {
            zzgi().zziy().zzd("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", zzfi.zzbp(str), Integer.valueOf(i), String.valueOf(zzkk.zzatk));
            return false;
        }
        try {
            byte[] bArr = new byte[zzkk.zzwb()];
            zzacb zzb = zzacb.zzb(bArr, 0, bArr.length);
            zzkk.zza(zzb);
            zzb.zzvt();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", zzkk.zzatk);
            contentValues.put("property_name", zzkk.zzauc);
            contentValues.put(DataBufferSafeParcelable.DATA_FIELD, bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("property_filters", null, contentValues, 5) != -1) {
                    return true;
                }
                zzgi().zziv().zzg("Failed to insert property filter (got -1). appId", zzfi.zzbp(str));
                return false;
            } catch (SQLiteException e) {
                zzgi().zziv().zze("Error storing property filter. appId", zzfi.zzbp(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzgi().zziv().zze("Configuration loss. Failed to serialize property filter. appId", zzfi.zzbp(str), e2);
            return false;
        }
    }

    private final boolean zza(String str, List<Integer> list) {
        Preconditions.checkNotEmpty(str);
        zzch();
        zzab();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        try {
            if (zza("select count(1) from audience_filter_values where app_id=?", new String[]{str}) <= ((long) Math.max(0, Math.min(2000, zzgk().zzb(str, zzez.zzajj))))) {
                return false;
            }
            Iterable arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                Integer num = (Integer) list.get(i);
                if (num == null || !(num instanceof Integer)) {
                    return false;
                }
                arrayList.add(Integer.toString(num.intValue()));
            }
            String join = TextUtils.join(",", arrayList);
            StringBuilder stringBuilder = new StringBuilder(String.valueOf(join).length() + 2);
            stringBuilder.append("(");
            stringBuilder.append(join);
            stringBuilder.append(")");
            join = stringBuilder.toString();
            StringBuilder stringBuilder2 = new StringBuilder(String.valueOf(join).length() + 140);
            stringBuilder2.append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ");
            stringBuilder2.append(join);
            stringBuilder2.append(" order by rowid desc limit -1 offset ?)");
            return writableDatabase.delete("audience_filter_values", stringBuilder2.toString(), new String[]{str, Integer.toString(r2)}) > 0;
        } catch (SQLiteException e) {
            zzgi().zziv().zze("Database error querying filters. appId", zzfi.zzbp(str), e);
            return false;
        }
    }

    private final boolean zzid() {
        return getContext().getDatabasePath("google_app_measurement.db").exists();
    }

    @WorkerThread
    public final void beginTransaction() {
        zzch();
        getWritableDatabase().beginTransaction();
    }

    @WorkerThread
    public final void endTransaction() {
        zzch();
        getWritableDatabase().endTransaction();
    }

    @WorkerThread
    @VisibleForTesting
    final SQLiteDatabase getWritableDatabase() {
        zzab();
        try {
            return this.zzagr.getWritableDatabase();
        } catch (SQLiteException e) {
            zzgi().zziy().zzg("Error opening database", e);
            throw e;
        }
    }

    @WorkerThread
    public final void setTransactionSuccessful() {
        zzch();
        getWritableDatabase().setTransactionSuccessful();
    }

    public final long zza(zzku zzku) throws IOException {
        zzab();
        zzch();
        Preconditions.checkNotNull(zzku);
        Preconditions.checkNotEmpty(zzku.zzth);
        try {
            long j;
            Object obj = new byte[zzku.zzwb()];
            zzacb zzb = zzacb.zzb(obj, 0, obj.length);
            zzku.zza(zzb);
            zzb.zzvt();
            zzhi zzjf = zzjf();
            Preconditions.checkNotNull(obj);
            zzjf.zzgg().zzab();
            MessageDigest messageDigest = zzkd.getMessageDigest();
            if (messageDigest == null) {
                zzjf.zzgi().zziv().log("Failed to get MD5");
                j = 0;
            } else {
                j = zzkd.zzc(messageDigest.digest(obj));
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzku.zzth);
            contentValues.put("metadata_fingerprint", Long.valueOf(j));
            contentValues.put("metadata", obj);
            try {
                getWritableDatabase().insertWithOnConflict("raw_events_metadata", null, contentValues, 4);
                return j;
            } catch (SQLiteException e) {
                zzgi().zziv().zze("Error storing raw event metadata. appId", zzfi.zzbp(zzku.zzth), e);
                throw e;
            }
        } catch (IOException e2) {
            zzgi().zziv().zze("Data loss. Failed to serialize event metadata. appId", zzfi.zzbp(zzku.zzth), e2);
            throw e2;
        }
    }

    public final Pair<zzkr, Long> zza(String str, Long l) {
        Cursor rawQuery;
        Object e;
        Throwable th;
        zzab();
        zzch();
        try {
            rawQuery = getWritableDatabase().rawQuery("select main_event, children_to_process from main_event_params where app_id=? and event_id=?", new String[]{str, String.valueOf(l)});
            try {
                if (rawQuery.moveToFirst()) {
                    byte[] blob = rawQuery.getBlob(0);
                    Long valueOf = Long.valueOf(rawQuery.getLong(1));
                    zzaca zza = zzaca.zza(blob, 0, blob.length);
                    zzacj zzkr = new zzkr();
                    try {
                        zzkr.zzb(zza);
                        Pair<zzkr, Long> create = Pair.create(zzkr, valueOf);
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        return create;
                    } catch (IOException e2) {
                        zzgi().zziv().zzd("Failed to merge main event. appId, eventId", zzfi.zzbp(str), l, e2);
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        return null;
                    }
                }
                zzgi().zzjc().log("Main event not found");
                if (rawQuery != null) {
                    rawQuery.close();
                }
                return null;
            } catch (SQLiteException e3) {
                e = e3;
                try {
                    zzgi().zziv().zzg("Error selecting main event", e);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e4) {
            e = e4;
            rawQuery = null;
            zzgi().zziv().zzg("Error selecting main event", e);
            if (rawQuery != null) {
                rawQuery.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            rawQuery = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final zzel zza(long j, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        Object obj;
        Throwable th;
        Preconditions.checkNotEmpty(str);
        zzab();
        zzch();
        String[] strArr = new String[]{str};
        zzel zzel = new zzel();
        Cursor cursor = null;
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            SQLiteDatabase sQLiteDatabase = writableDatabase;
            Cursor query = sQLiteDatabase.query("apps", new String[]{"day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count"}, "app_id=?", new String[]{str}, null, null, null);
            try {
                if (query.moveToFirst()) {
                    if (query.getLong(0) == j) {
                        zzel.zzagu = query.getLong(1);
                        zzel.zzagt = query.getLong(2);
                        zzel.zzagv = query.getLong(3);
                        zzel.zzagw = query.getLong(4);
                        zzel.zzagx = query.getLong(5);
                    }
                    if (z) {
                        zzel.zzagu++;
                    }
                    if (z2) {
                        zzel.zzagt++;
                    }
                    if (z3) {
                        zzel.zzagv++;
                    }
                    if (z4) {
                        zzel.zzagw++;
                    }
                    if (z5) {
                        zzel.zzagx++;
                    }
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("day", Long.valueOf(j));
                    contentValues.put("daily_public_events_count", Long.valueOf(zzel.zzagt));
                    contentValues.put("daily_events_count", Long.valueOf(zzel.zzagu));
                    contentValues.put("daily_conversions_count", Long.valueOf(zzel.zzagv));
                    contentValues.put("daily_error_events_count", Long.valueOf(zzel.zzagw));
                    contentValues.put("daily_realtime_events_count", Long.valueOf(zzel.zzagx));
                    writableDatabase.update("apps", contentValues, "app_id=?", strArr);
                    if (query != null) {
                        query.close();
                    }
                    return zzel;
                }
                zzgi().zziy().zzg("Not updating daily counts, app is not known. appId", zzfi.zzbp(str));
                if (query != null) {
                    query.close();
                }
                return zzel;
            } catch (SQLiteException e) {
                obj = e;
                cursor = query;
                try {
                    zzgi().zziv().zze("Error updating daily counts. appId", zzfi.zzbp(str), obj);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return zzel;
                } catch (Throwable th2) {
                    th = th2;
                    query = cursor;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            } catch (Throwable th22) {
                th = th22;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (SQLiteException e2) {
            obj = e2;
            zzgi().zziv().zze("Error updating daily counts. appId", zzfi.zzbp(str), obj);
            if (cursor != null) {
                cursor.close();
            }
            return zzel;
        }
    }

    @WorkerThread
    public final void zza(zzea zzea) {
        Preconditions.checkNotNull(zzea);
        zzab();
        zzch();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzea.zzah());
        contentValues.put("app_instance_id", zzea.getAppInstanceId());
        contentValues.put("gmp_app_id", zzea.getGmpAppId());
        contentValues.put("resettable_device_id_hash", zzea.zzgq());
        contentValues.put("last_bundle_index", Long.valueOf(zzea.zzgy()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzea.zzgs()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzea.zzgt()));
        contentValues.put("app_version", zzea.zzag());
        contentValues.put("app_store", zzea.zzgv());
        contentValues.put("gmp_version", Long.valueOf(zzea.zzgw()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzea.zzgx()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzea.isMeasurementEnabled()));
        contentValues.put("day", Long.valueOf(zzea.zzhc()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzea.zzhd()));
        contentValues.put("daily_events_count", Long.valueOf(zzea.zzhe()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzea.zzhf()));
        contentValues.put("config_fetched_time", Long.valueOf(zzea.zzgz()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzea.zzha()));
        contentValues.put("app_version_int", Long.valueOf(zzea.zzgu()));
        contentValues.put("firebase_instance_id", zzea.zzgr());
        contentValues.put("daily_error_events_count", Long.valueOf(zzea.zzhh()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzea.zzhg()));
        contentValues.put("health_monitor_sample", zzea.zzhi());
        contentValues.put("android_id", Long.valueOf(zzea.zzhk()));
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(zzea.zzhl()));
        contentValues.put("ssaid_reporting_enabled", Boolean.valueOf(zzea.zzhm()));
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (((long) writableDatabase.update("apps", contentValues, "app_id = ?", new String[]{zzea.zzah()})) == 0 && writableDatabase.insertWithOnConflict("apps", null, contentValues, 5) == -1) {
                zzgi().zziv().zzg("Failed to insert/update app (got -1). appId", zzfi.zzbp(zzea.zzah()));
            }
        } catch (SQLiteException e) {
            zzgi().zziv().zze("Error storing app. appId", zzfi.zzbp(zzea.zzah()), e);
        }
    }

    @WorkerThread
    public final void zza(zzet zzet) {
        Preconditions.checkNotNull(zzet);
        zzab();
        zzch();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzet.zzth);
        contentValues.put("name", zzet.name);
        contentValues.put("lifetime_count", Long.valueOf(zzet.zzahh));
        contentValues.put("current_bundle_count", Long.valueOf(zzet.zzahi));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzet.zzahj));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzet.zzahk));
        contentValues.put("last_sampled_complex_event_id", zzet.zzahl);
        contentValues.put("last_sampling_rate", zzet.zzahm);
        Long valueOf = (zzet.zzahn == null || !zzet.zzahn.booleanValue()) ? null : Long.valueOf(1);
        contentValues.put("last_exempt_from_sampling", valueOf);
        try {
            if (getWritableDatabase().insertWithOnConflict("events", null, contentValues, 5) == -1) {
                zzgi().zziv().zzg("Failed to insert/update event aggregates (got -1). appId", zzfi.zzbp(zzet.zzth));
            }
        } catch (SQLiteException e) {
            zzgi().zziv().zze("Error storing event aggregates. appId", zzfi.zzbp(zzet.zzth), e);
        }
    }

    @WorkerThread
    public final boolean zza(zzef zzef) {
        Preconditions.checkNotNull(zzef);
        zzab();
        zzch();
        if (zzh(zzef.packageName, zzef.zzage.name) == null) {
            if (zza("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{zzef.packageName}) >= 1000) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzef.packageName);
        contentValues.put(Param.ORIGIN, zzef.origin);
        contentValues.put("name", zzef.zzage.name);
        zza(contentValues, Param.VALUE, zzef.zzage.getValue());
        contentValues.put("active", Boolean.valueOf(zzef.active));
        contentValues.put("trigger_event_name", zzef.triggerEventName);
        contentValues.put("trigger_timeout", Long.valueOf(zzef.triggerTimeout));
        zzgg();
        contentValues.put("timed_out_event", zzkd.zza(zzef.zzagf));
        contentValues.put("creation_timestamp", Long.valueOf(zzef.creationTimestamp));
        zzgg();
        contentValues.put("triggered_event", zzkd.zza(zzef.zzagg));
        contentValues.put("triggered_timestamp", Long.valueOf(zzef.zzage.zzast));
        contentValues.put("time_to_live", Long.valueOf(zzef.timeToLive));
        zzgg();
        contentValues.put("expired_event", zzkd.zza(zzef.zzagh));
        try {
            if (getWritableDatabase().insertWithOnConflict("conditional_properties", null, contentValues, 5) == -1) {
                zzgi().zziv().zzg("Failed to insert/update conditional user property (got -1)", zzfi.zzbp(zzef.packageName));
                return true;
            }
        } catch (SQLiteException e) {
            zzgi().zziv().zze("Error storing conditional user property", zzfi.zzbp(zzef.packageName), e);
        }
        return true;
    }

    public final boolean zza(zzes zzes, long j, boolean z) {
        Object e;
        zzfk zziv;
        String str;
        zzab();
        zzch();
        Preconditions.checkNotNull(zzes);
        Preconditions.checkNotEmpty(zzes.zzth);
        zzacj zzkr = new zzkr();
        zzkr.zzavc = Long.valueOf(zzes.zzahf);
        zzkr.zzava = new zzks[zzes.zzahg.size()];
        Iterator it = zzes.zzahg.iterator();
        int i = 0;
        while (it.hasNext()) {
            String str2 = (String) it.next();
            zzks zzks = new zzks();
            int i2 = i + 1;
            zzkr.zzava[i] = zzks;
            zzks.name = str2;
            zzjf().zza(zzks, zzes.zzahg.get(str2));
            i = i2;
        }
        try {
            byte[] bArr = new byte[zzkr.zzwb()];
            zzacb zzb = zzacb.zzb(bArr, 0, bArr.length);
            zzkr.zza(zzb);
            zzb.zzvt();
            zzgi().zzjc().zze("Saving event, name, data size", zzgf().zzbm(zzes.name), Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzes.zzth);
            contentValues.put("name", zzes.name);
            contentValues.put(AppMeasurement.Param.TIMESTAMP, Long.valueOf(zzes.timestamp));
            contentValues.put("metadata_fingerprint", Long.valueOf(j));
            contentValues.put(DataBufferSafeParcelable.DATA_FIELD, bArr);
            contentValues.put("realtime", Integer.valueOf(z));
            try {
                if (getWritableDatabase().insert("raw_events", null, contentValues) != -1) {
                    return true;
                }
                zzgi().zziv().zzg("Failed to insert raw event (got -1). appId", zzfi.zzbp(zzes.zzth));
                return false;
            } catch (SQLiteException e2) {
                e = e2;
                zziv = zzgi().zziv();
                str = "Error storing raw event. appId";
                zziv.zze(str, zzfi.zzbp(zzes.zzth), e);
                return false;
            }
        } catch (IOException e3) {
            e = e3;
            zziv = zzgi().zziv();
            str = "Data loss. Failed to serialize event params/data. appId";
            zziv.zze(str, zzfi.zzbp(zzes.zzth), e);
            return false;
        }
    }

    @WorkerThread
    public final boolean zza(zzkc zzkc) {
        Preconditions.checkNotNull(zzkc);
        zzab();
        zzch();
        if (zzh(zzkc.zzth, zzkc.name) == null) {
            if (zzkd.zzcg(zzkc.name)) {
                if (zza("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzkc.zzth}) >= 25) {
                    return false;
                }
            }
            if (zza("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzkc.zzth, zzkc.origin}) >= 25) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzkc.zzth);
        contentValues.put(Param.ORIGIN, zzkc.origin);
        contentValues.put("name", zzkc.name);
        contentValues.put("set_timestamp", Long.valueOf(zzkc.zzast));
        zza(contentValues, Param.VALUE, zzkc.value);
        try {
            if (getWritableDatabase().insertWithOnConflict("user_attributes", null, contentValues, 5) == -1) {
                zzgi().zziv().zzg("Failed to insert/update user property (got -1). appId", zzfi.zzbp(zzkc.zzth));
                return true;
            }
        } catch (SQLiteException e) {
            zzgi().zziv().zze("Error storing user property. appId", zzfi.zzbp(zzkc.zzth), e);
        }
        return true;
    }

    @WorkerThread
    public final boolean zza(zzku zzku, boolean z) {
        Object e;
        zzfk zziv;
        String str;
        zzab();
        zzch();
        Preconditions.checkNotNull(zzku);
        Preconditions.checkNotEmpty(zzku.zzth);
        Preconditions.checkNotNull(zzku.zzavm);
        zzhx();
        long currentTimeMillis = zzbt().currentTimeMillis();
        if (zzku.zzavm.longValue() < currentTimeMillis - zzeh.zzhq() || zzku.zzavm.longValue() > currentTimeMillis + zzeh.zzhq()) {
            zzgi().zziy().zzd("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzfi.zzbp(zzku.zzth), Long.valueOf(currentTimeMillis), zzku.zzavm);
        }
        try {
            byte[] bArr = new byte[zzku.zzwb()];
            zzacb zzb = zzacb.zzb(bArr, 0, bArr.length);
            zzku.zza(zzb);
            zzb.zzvt();
            bArr = zzjf().zzb(bArr);
            zzgi().zzjc().zzg("Saving bundle, size", Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzku.zzth);
            contentValues.put("bundle_end_timestamp", zzku.zzavm);
            contentValues.put(DataBufferSafeParcelable.DATA_FIELD, bArr);
            contentValues.put("has_realtime", Integer.valueOf(z));
            if (zzku.zzawj != null) {
                contentValues.put("retry_count", zzku.zzawj);
            }
            try {
                if (getWritableDatabase().insert("queue", null, contentValues) != -1) {
                    return true;
                }
                zzgi().zziv().zzg("Failed to insert bundle (got -1). appId", zzfi.zzbp(zzku.zzth));
                return false;
            } catch (SQLiteException e2) {
                e = e2;
                zziv = zzgi().zziv();
                str = "Error storing bundle. appId";
                zziv.zze(str, zzfi.zzbp(zzku.zzth), e);
                return false;
            }
        } catch (IOException e3) {
            e = e3;
            zziv = zzgi().zziv();
            str = "Data loss. Failed to serialize bundle. appId";
            zziv.zze(str, zzfi.zzbp(zzku.zzth), e);
            return false;
        }
    }

    public final boolean zza(String str, Long l, long j, zzkr zzkr) {
        zzab();
        zzch();
        Preconditions.checkNotNull(zzkr);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l);
        try {
            byte[] bArr = new byte[zzkr.zzwb()];
            zzacb zzb = zzacb.zzb(bArr, 0, bArr.length);
            zzkr.zza(zzb);
            zzb.zzvt();
            zzgi().zzjc().zze("Saving complex main event, appId, data size", zzgf().zzbm(str), Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("event_id", l);
            contentValues.put("children_to_process", Long.valueOf(j));
            contentValues.put("main_event", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("main_event_params", null, contentValues, 5) != -1) {
                    return true;
                }
                zzgi().zziv().zzg("Failed to insert complex main event (got -1). appId", zzfi.zzbp(str));
                return false;
            } catch (SQLiteException e) {
                zzgi().zziv().zze("Error storing complex main event. appId", zzfi.zzbp(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzgi().zziv().zzd("Data loss. Failed to serialize event params/data. appId, eventId", zzfi.zzbp(str), l, e2);
            return false;
        }
    }

    public final String zzag(long j) {
        Object e;
        Throwable th;
        zzab();
        zzch();
        Cursor rawQuery;
        try {
            rawQuery = getWritableDatabase().rawQuery("select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;", new String[]{String.valueOf(j)});
            try {
                if (rawQuery.moveToFirst()) {
                    String string = rawQuery.getString(0);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    return string;
                }
                zzgi().zzjc().log("No expired configs for apps with pending events");
                if (rawQuery != null) {
                    rawQuery.close();
                }
                return null;
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    zzgi().zziv().zzg("Error selecting expired configs", e);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            rawQuery = null;
            zzgi().zziv().zzg("Error selecting expired configs", e);
            if (rawQuery != null) {
                rawQuery.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            rawQuery = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final List<Pair<zzku, Long>> zzb(String str, int i, int i2) {
        Cursor query;
        Object e;
        zzfk zziv;
        String str2;
        Object zzbp;
        Object e2;
        Throwable th;
        zzab();
        zzch();
        Preconditions.checkArgument(i > 0);
        Preconditions.checkArgument(i2 > 0);
        Preconditions.checkNotEmpty(str);
        Cursor cursor = null;
        try {
            query = getWritableDatabase().query("queue", new String[]{"rowid", DataBufferSafeParcelable.DATA_FIELD, "retry_count"}, "app_id=?", new String[]{str}, null, null, "rowid", String.valueOf(i));
            try {
                if (query.moveToFirst()) {
                    List<Pair<zzku, Long>> arrayList = new ArrayList();
                    int i3 = 0;
                    do {
                        long j = query.getLong(0);
                        try {
                            byte[] zza = zzjf().zza(query.getBlob(1));
                            if (!arrayList.isEmpty() && zza.length + i3 > i2) {
                                break;
                            }
                            zzaca zza2 = zzaca.zza(zza, 0, zza.length);
                            zzacj zzku = new zzku();
                            try {
                                zzku.zzb(zza2);
                                if (!query.isNull(2)) {
                                    zzku.zzawj = Integer.valueOf(query.getInt(2));
                                }
                                i3 += zza.length;
                                arrayList.add(Pair.create(zzku, Long.valueOf(j)));
                            } catch (IOException e3) {
                                e = e3;
                                zziv = zzgi().zziv();
                                str2 = "Failed to merge queued bundle. appId";
                                zzbp = zzfi.zzbp(str);
                                zziv.zze(str2, zzbp, e);
                                if (query.moveToNext()) {
                                    break;
                                } else if (i3 > i2) {
                                }
                                if (query != null) {
                                    query.close();
                                }
                                return arrayList;
                            }
                            if (query.moveToNext()) {
                                break;
                            }
                        } catch (IOException e4) {
                            e = e4;
                            zziv = zzgi().zziv();
                            str2 = "Failed to unzip queued bundle. appId";
                            zzbp = zzfi.zzbp(str);
                            zziv.zze(str2, zzbp, e);
                            if (query.moveToNext()) {
                                break;
                            } else if (i3 > i2) {
                            }
                            if (query != null) {
                                query.close();
                            }
                            return arrayList;
                        }
                    } while (i3 > i2);
                    if (query != null) {
                        query.close();
                    }
                    return arrayList;
                }
                List<Pair<zzku, Long>> emptyList = Collections.emptyList();
                if (query != null) {
                    query.close();
                }
                return emptyList;
            } catch (SQLiteException e5) {
                e2 = e5;
                cursor = query;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (SQLiteException e6) {
            e2 = e6;
            try {
                zzgi().zziv().zze("Error querying bundles. appId", zzfi.zzbp(str), e2);
                List<Pair<zzku, Long>> emptyList2 = Collections.emptyList();
                if (cursor != null) {
                    cursor.close();
                }
                return emptyList2;
            } catch (Throwable th3) {
                th = th3;
                query = cursor;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.WorkerThread
    public final java.util.List<com.google.android.gms.internal.measurement.zzkc> zzb(java.lang.String r24, java.lang.String r25, java.lang.String r26) {
        /*
        r23 = this;
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24);
        r23.zzab();
        r23.zzch();
        r1 = new java.util.ArrayList;
        r1.<init>();
        r2 = 0;
        r3 = new java.util.ArrayList;	 Catch:{ SQLiteException -> 0x0109, all -> 0x0104 }
        r4 = 3;
        r3.<init>(r4);	 Catch:{ SQLiteException -> 0x0109, all -> 0x0104 }
        r12 = r24;
        r3.add(r12);	 Catch:{ SQLiteException -> 0x0100, all -> 0x0104 }
        r5 = new java.lang.StringBuilder;	 Catch:{ SQLiteException -> 0x0100, all -> 0x0104 }
        r6 = "app_id=?";
        r5.<init>(r6);	 Catch:{ SQLiteException -> 0x0100, all -> 0x0104 }
        r6 = android.text.TextUtils.isEmpty(r25);	 Catch:{ SQLiteException -> 0x0100, all -> 0x0104 }
        if (r6 != 0) goto L_0x0037;
    L_0x0027:
        r6 = r25;
        r3.add(r6);	 Catch:{ SQLiteException -> 0x0032, all -> 0x0104 }
        r7 = " and origin=?";
        r5.append(r7);	 Catch:{ SQLiteException -> 0x0032, all -> 0x0104 }
        goto L_0x0039;
    L_0x0032:
        r0 = move-exception;
        r13 = r23;
        goto L_0x0110;
    L_0x0037:
        r6 = r25;
    L_0x0039:
        r7 = android.text.TextUtils.isEmpty(r26);	 Catch:{ SQLiteException -> 0x0032, all -> 0x0104 }
        if (r7 != 0) goto L_0x0051;
    L_0x003f:
        r7 = java.lang.String.valueOf(r26);	 Catch:{ SQLiteException -> 0x0032, all -> 0x0104 }
        r8 = "*";
        r7 = r7.concat(r8);	 Catch:{ SQLiteException -> 0x0032, all -> 0x0104 }
        r3.add(r7);	 Catch:{ SQLiteException -> 0x0032, all -> 0x0104 }
        r7 = " and name glob ?";
        r5.append(r7);	 Catch:{ SQLiteException -> 0x0032, all -> 0x0104 }
    L_0x0051:
        r7 = r3.size();	 Catch:{ SQLiteException -> 0x0032, all -> 0x0104 }
        r7 = new java.lang.String[r7];	 Catch:{ SQLiteException -> 0x0032, all -> 0x0104 }
        r3 = r3.toArray(r7);	 Catch:{ SQLiteException -> 0x0032, all -> 0x0104 }
        r17 = r3;
        r17 = (java.lang.String[]) r17;	 Catch:{ SQLiteException -> 0x0032, all -> 0x0104 }
        r13 = r23.getWritableDatabase();	 Catch:{ SQLiteException -> 0x0032, all -> 0x0104 }
        r14 = "user_attributes";
        r3 = "name";
        r7 = "set_timestamp";
        r8 = "value";
        r9 = "origin";
        r15 = new java.lang.String[]{r3, r7, r8, r9};	 Catch:{ SQLiteException -> 0x0032, all -> 0x0104 }
        r16 = r5.toString();	 Catch:{ SQLiteException -> 0x0032, all -> 0x0104 }
        r18 = 0;
        r19 = 0;
        r20 = "rowid";
        r21 = "1001";
        r3 = r13.query(r14, r15, r16, r17, r18, r19, r20, r21);	 Catch:{ SQLiteException -> 0x0032, all -> 0x0104 }
        r5 = r3.moveToFirst();	 Catch:{ SQLiteException -> 0x00fb, all -> 0x00f7 }
        if (r5 != 0) goto L_0x008d;
    L_0x0087:
        if (r3 == 0) goto L_0x008c;
    L_0x0089:
        r3.close();
    L_0x008c:
        return r1;
    L_0x008d:
        r5 = r1.size();	 Catch:{ SQLiteException -> 0x00fb, all -> 0x00f7 }
        r7 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        if (r5 < r7) goto L_0x00a9;
    L_0x0095:
        r4 = r23.zzgi();	 Catch:{ SQLiteException -> 0x00fb, all -> 0x00f7 }
        r4 = r4.zziv();	 Catch:{ SQLiteException -> 0x00fb, all -> 0x00f7 }
        r5 = "Read more than the max allowed user properties, ignoring excess";
        r7 = java.lang.Integer.valueOf(r7);	 Catch:{ SQLiteException -> 0x00fb, all -> 0x00f7 }
        r4.zzg(r5, r7);	 Catch:{ SQLiteException -> 0x00fb, all -> 0x00f7 }
        r13 = r23;
        goto L_0x00ec;
    L_0x00a9:
        r5 = 0;
        r8 = r3.getString(r5);	 Catch:{ SQLiteException -> 0x00fb, all -> 0x00f7 }
        r5 = 1;
        r9 = r3.getLong(r5);	 Catch:{ SQLiteException -> 0x00fb, all -> 0x00f7 }
        r5 = 2;
        r13 = r23;
        r11 = r13.zza(r3, r5);	 Catch:{ SQLiteException -> 0x00f5 }
        r14 = r3.getString(r4);	 Catch:{ SQLiteException -> 0x00f5 }
        if (r11 != 0) goto L_0x00d8;
    L_0x00c0:
        r5 = r23.zzgi();	 Catch:{ SQLiteException -> 0x00d4 }
        r5 = r5.zziv();	 Catch:{ SQLiteException -> 0x00d4 }
        r6 = "(2)Read invalid user property value, ignoring it";
        r7 = com.google.android.gms.internal.measurement.zzfi.zzbp(r24);	 Catch:{ SQLiteException -> 0x00d4 }
        r15 = r26;
        r5.zzd(r6, r7, r14, r15);	 Catch:{ SQLiteException -> 0x00d4 }
        goto L_0x00e6;
    L_0x00d4:
        r0 = move-exception;
        r1 = r0;
        r6 = r14;
        goto L_0x0112;
    L_0x00d8:
        r15 = r26;
        r7 = new com.google.android.gms.internal.measurement.zzkc;	 Catch:{ SQLiteException -> 0x00d4 }
        r5 = r7;
        r6 = r12;
        r4 = r7;
        r7 = r14;
        r5.<init>(r6, r7, r8, r9, r11);	 Catch:{ SQLiteException -> 0x00d4 }
        r1.add(r4);	 Catch:{ SQLiteException -> 0x00d4 }
    L_0x00e6:
        r4 = r3.moveToNext();	 Catch:{ SQLiteException -> 0x00d4 }
        if (r4 != 0) goto L_0x00f2;
    L_0x00ec:
        if (r3 == 0) goto L_0x00f1;
    L_0x00ee:
        r3.close();
    L_0x00f1:
        return r1;
    L_0x00f2:
        r6 = r14;
        r4 = 3;
        goto L_0x008d;
    L_0x00f5:
        r0 = move-exception;
        goto L_0x00fe;
    L_0x00f7:
        r0 = move-exception;
        r13 = r23;
        goto L_0x012a;
    L_0x00fb:
        r0 = move-exception;
        r13 = r23;
    L_0x00fe:
        r1 = r0;
        goto L_0x0112;
    L_0x0100:
        r0 = move-exception;
        r13 = r23;
        goto L_0x010e;
    L_0x0104:
        r0 = move-exception;
        r13 = r23;
        r1 = r0;
        goto L_0x012c;
    L_0x0109:
        r0 = move-exception;
        r13 = r23;
        r12 = r24;
    L_0x010e:
        r6 = r25;
    L_0x0110:
        r1 = r0;
        r3 = r2;
    L_0x0112:
        r4 = r23.zzgi();	 Catch:{ all -> 0x0129 }
        r4 = r4.zziv();	 Catch:{ all -> 0x0129 }
        r5 = "(2)Error querying user properties";
        r7 = com.google.android.gms.internal.measurement.zzfi.zzbp(r24);	 Catch:{ all -> 0x0129 }
        r4.zzd(r5, r7, r6, r1);	 Catch:{ all -> 0x0129 }
        if (r3 == 0) goto L_0x0128;
    L_0x0125:
        r3.close();
    L_0x0128:
        return r2;
    L_0x0129:
        r0 = move-exception;
    L_0x012a:
        r1 = r0;
        r2 = r3;
    L_0x012c:
        if (r2 == 0) goto L_0x0131;
    L_0x012e:
        r2.close();
    L_0x0131:
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzb(java.lang.String, java.lang.String, java.lang.String):java.util.List<com.google.android.gms.internal.measurement.zzkc>");
    }

    public final List<zzef> zzb(String str, String[] strArr) {
        Object obj;
        Throwable th;
        zzab();
        zzch();
        List<zzef> arrayList = new ArrayList();
        Cursor cursor = null;
        Cursor query;
        try {
            query = getWritableDatabase().query("conditional_properties", new String[]{"app_id", Param.ORIGIN, "name", Param.VALUE, "active", "trigger_event_name", "trigger_timeout", "timed_out_event", "creation_timestamp", "triggered_event", "triggered_timestamp", "time_to_live", "expired_event"}, str, strArr, null, null, "rowid", "1001");
            try {
                if (query.moveToFirst()) {
                    do {
                        if (arrayList.size() >= 1000) {
                            zzgi().zziv().zzg("Read more than the max allowed conditional properties, ignoring extra", Integer.valueOf(1000));
                            break;
                        }
                        boolean z = false;
                        String string = query.getString(0);
                        String string2 = query.getString(1);
                        String string3 = query.getString(2);
                        Object zza = zza(query, 3);
                        if (query.getInt(4) != 0) {
                            z = true;
                        }
                        String string4 = query.getString(5);
                        long j = query.getLong(6);
                        zzex zzex = (zzex) zzjf().zza(query.getBlob(7), zzex.CREATOR);
                        long j2 = query.getLong(8);
                        zzex zzex2 = (zzex) zzjf().zza(query.getBlob(9), zzex.CREATOR);
                        long j3 = query.getLong(10);
                        long j4 = query.getLong(11);
                        zzex zzex3 = (zzex) zzjf().zza(query.getBlob(12), zzex.CREATOR);
                        zzka zzka = new zzka(string3, j3, zza, string2);
                        boolean z2 = z;
                        zzef zzef = r4;
                        zzef zzef2 = new zzef(string, string2, zzka, j2, z2, string4, zzex, j, zzex2, j4, zzex3);
                        arrayList.add(zzef);
                    } while (query.moveToNext());
                    if (query != null) {
                        query.close();
                    }
                    return arrayList;
                }
                if (query != null) {
                    query.close();
                }
                return arrayList;
            } catch (SQLiteException e) {
                obj = e;
                cursor = query;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (SQLiteException e2) {
            obj = e2;
            try {
                zzgi().zziv().zzg("Error querying conditional user property value", obj);
                arrayList = Collections.emptyList();
                if (cursor != null) {
                    cursor.close();
                }
                return arrayList;
            } catch (Throwable th22) {
                th = th22;
                query = cursor;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        }
    }

    @WorkerThread
    final void zzb(String str, zzkg[] zzkgArr) {
        zzch();
        zzab();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzkgArr);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            zzch();
            zzab();
            Preconditions.checkNotEmpty(str);
            SQLiteDatabase writableDatabase2 = getWritableDatabase();
            String[] strArr = new String[1];
            int i = 0;
            strArr[0] = str;
            writableDatabase2.delete("property_filters", "app_id=?", strArr);
            writableDatabase2.delete("event_filters", "app_id=?", new String[]{str});
            for (zzkg zzkg : zzkgArr) {
                zzch();
                zzab();
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotNull(zzkg);
                Preconditions.checkNotNull(zzkg.zzatg);
                Preconditions.checkNotNull(zzkg.zzatf);
                if (zzkg.zzate == null) {
                    zzgi().zziy().zzg("Audience with no ID. appId", zzfi.zzbp(str));
                } else {
                    zzfk zziy;
                    String str2;
                    Object zzbp;
                    Object obj;
                    Object obj2;
                    int intValue = zzkg.zzate.intValue();
                    for (zzkh zzkh : zzkg.zzatg) {
                        if (zzkh.zzatk == null) {
                            zziy = zzgi().zziy();
                            str2 = "Event filter with no ID. Audience definition ignored. appId, audienceId";
                            zzbp = zzfi.zzbp(str);
                            obj = zzkg.zzate;
                            break;
                        }
                    }
                    for (zzkk zzkk : zzkg.zzatf) {
                        if (zzkk.zzatk == null) {
                            zziy = zzgi().zziy();
                            str2 = "Property filter with no ID. Audience definition ignored. appId, audienceId";
                            zzbp = zzfi.zzbp(str);
                            obj = zzkg.zzate;
                            zziy.zze(str2, zzbp, obj);
                            break;
                        }
                    }
                    for (zzkh zzkh2 : zzkg.zzatg) {
                        if (!zza(str, intValue, zzkh2)) {
                            obj2 = null;
                            break;
                        }
                    }
                    obj2 = 1;
                    if (obj2 != null) {
                        for (zzkk zzkk2 : zzkg.zzatf) {
                            if (!zza(str, intValue, zzkk2)) {
                                obj2 = null;
                                break;
                            }
                        }
                    }
                    if (obj2 == null) {
                        zzch();
                        zzab();
                        Preconditions.checkNotEmpty(str);
                        SQLiteDatabase writableDatabase3 = getWritableDatabase();
                        writableDatabase3.delete("property_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(intValue)});
                        writableDatabase3.delete("event_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(intValue)});
                    }
                }
            }
            List arrayList = new ArrayList();
            int length = zzkgArr.length;
            while (i < length) {
                arrayList.add(zzkgArr[i].zzate);
                i++;
            }
            zza(str, arrayList);
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    @WorkerThread
    public final List<zzkc> zzbe(String str) {
        Object e;
        Throwable th;
        Preconditions.checkNotEmpty(str);
        zzab();
        zzch();
        List<zzkc> arrayList = new ArrayList();
        Cursor query;
        try {
            query = getWritableDatabase().query("user_attributes", new String[]{"name", Param.ORIGIN, "set_timestamp", Param.VALUE}, "app_id=?", new String[]{str}, null, null, "rowid", "1000");
            try {
                if (query.moveToFirst()) {
                    do {
                        String string = query.getString(0);
                        String string2 = query.getString(1);
                        if (string2 == null) {
                            string2 = "";
                        }
                        String str2 = string2;
                        long j = query.getLong(2);
                        Object zza = zza(query, 3);
                        if (zza == null) {
                            zzgi().zziv().zzg("Read invalid user property value, ignoring it. appId", zzfi.zzbp(str));
                        } else {
                            arrayList.add(new zzkc(str, str2, string, j, zza));
                        }
                    } while (query.moveToNext());
                    if (query != null) {
                        query.close();
                    }
                    return arrayList;
                }
                if (query != null) {
                    query.close();
                }
                return arrayList;
            } catch (SQLiteException e2) {
                e = e2;
            }
        } catch (SQLiteException e3) {
            e = e3;
            query = null;
            try {
                zzgi().zziv().zze("Error querying user properties. appId", zzfi.zzbp(str), e);
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final zzea zzbf(String str) {
        SQLiteException e;
        Cursor cursor;
        Object obj;
        Throwable th;
        Throwable th2;
        zzek zzek;
        String str2 = str;
        Preconditions.checkNotEmpty(str);
        zzab();
        zzch();
        Cursor query;
        try {
            String[] strArr = new String[1];
            boolean z = false;
            strArr[0] = str2;
            query = getWritableDatabase().query("apps", new String[]{"app_instance_id", "gmp_app_id", "resettable_device_id_hash", "last_bundle_index", "last_bundle_start_timestamp", "last_bundle_end_timestamp", "app_version", "app_store", "gmp_version", "dev_cert_hash", "measurement_enabled", "day", "daily_public_events_count", "daily_events_count", "daily_conversions_count", "config_fetched_time", "failed_config_fetch_time", "app_version_int", "firebase_instance_id", "daily_error_events_count", "daily_realtime_events_count", "health_monitor_sample", "android_id", "adid_reporting_enabled", "ssaid_reporting_enabled"}, "app_id=?", strArr, null, null, null);
            try {
                if (query.moveToFirst()) {
                    try {
                        boolean z2;
                        zzea zzea = new zzea(this.zzalo.zzlm(), str2);
                        zzea.zzam(query.getString(0));
                        zzea.zzan(query.getString(1));
                        zzea.zzao(query.getString(2));
                        zzea.zzw(query.getLong(3));
                        zzea.zzr(query.getLong(4));
                        zzea.zzs(query.getLong(5));
                        zzea.setAppVersion(query.getString(6));
                        zzea.zzaq(query.getString(7));
                        zzea.zzu(query.getLong(8));
                        zzea.zzv(query.getLong(9));
                        if (!query.isNull(10)) {
                            if (query.getInt(10) == 0) {
                                z2 = false;
                                zzea.setMeasurementEnabled(z2);
                                zzea.zzz(query.getLong(11));
                                zzea.zzaa(query.getLong(12));
                                zzea.zzab(query.getLong(13));
                                zzea.zzac(query.getLong(14));
                                zzea.zzx(query.getLong(15));
                                zzea.zzy(query.getLong(16));
                                zzea.zzt(query.isNull(17) ? -2147483648L : (long) query.getInt(17));
                                zzea.zzap(query.getString(18));
                                zzea.zzae(query.getLong(19));
                                zzea.zzad(query.getLong(20));
                                zzea.zzar(query.getString(21));
                                zzea.zzaf(query.isNull(22) ? 0 : query.getLong(22));
                                if (!query.isNull(23)) {
                                    if (query.getInt(23) != 0) {
                                        z2 = false;
                                        zzea.zzd(z2);
                                        if (query.isNull(24) || query.getInt(24) != 0) {
                                            z = true;
                                        }
                                        zzea.zze(z);
                                        zzea.zzgp();
                                        if (query.moveToNext()) {
                                            zzgi().zziv().zzg("Got multiple records for app, expected one. appId", zzfi.zzbp(str));
                                        }
                                        if (query != null) {
                                            query.close();
                                        }
                                        return zzea;
                                    }
                                }
                                z2 = true;
                                zzea.zzd(z2);
                                z = true;
                                zzea.zze(z);
                                zzea.zzgp();
                                if (query.moveToNext()) {
                                    zzgi().zziv().zzg("Got multiple records for app, expected one. appId", zzfi.zzbp(str));
                                }
                                if (query != null) {
                                    query.close();
                                }
                                return zzea;
                            }
                        }
                        z2 = true;
                        zzea.setMeasurementEnabled(z2);
                        zzea.zzz(query.getLong(11));
                        zzea.zzaa(query.getLong(12));
                        zzea.zzab(query.getLong(13));
                        zzea.zzac(query.getLong(14));
                        zzea.zzx(query.getLong(15));
                        zzea.zzy(query.getLong(16));
                        if (query.isNull(17)) {
                        }
                        zzea.zzt(query.isNull(17) ? -2147483648L : (long) query.getInt(17));
                        zzea.zzap(query.getString(18));
                        zzea.zzae(query.getLong(19));
                        zzea.zzad(query.getLong(20));
                        zzea.zzar(query.getString(21));
                        if (query.isNull(22)) {
                        }
                        zzea.zzaf(query.isNull(22) ? 0 : query.getLong(22));
                        if (query.isNull(23)) {
                            if (query.getInt(23) != 0) {
                                z2 = false;
                                zzea.zzd(z2);
                                z = true;
                                zzea.zze(z);
                                zzea.zzgp();
                                if (query.moveToNext()) {
                                    zzgi().zziv().zzg("Got multiple records for app, expected one. appId", zzfi.zzbp(str));
                                }
                                if (query != null) {
                                    query.close();
                                }
                                return zzea;
                            }
                        }
                        z2 = true;
                        zzea.zzd(z2);
                        z = true;
                        zzea.zze(z);
                        zzea.zzgp();
                        if (query.moveToNext()) {
                            zzgi().zziv().zzg("Got multiple records for app, expected one. appId", zzfi.zzbp(str));
                        }
                        if (query != null) {
                            query.close();
                        }
                        return zzea;
                    } catch (SQLiteException e2) {
                        e = e2;
                        cursor = query;
                        obj = e;
                        try {
                            zzgi().zziv().zze("Error querying app. appId", zzfi.zzbp(str), obj);
                            if (cursor != null) {
                                cursor.close();
                            }
                            return null;
                        } catch (Throwable th22) {
                            th = th22;
                            query = cursor;
                            if (query != null) {
                                query.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th22 = th3;
                        th = th22;
                        if (query != null) {
                            query.close();
                        }
                        throw th;
                    }
                }
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (SQLiteException e3) {
                e = e3;
                zzek = this;
                cursor = query;
                obj = e;
                zzgi().zziv().zze("Error querying app. appId", zzfi.zzbp(str), obj);
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            } catch (Throwable th4) {
                th22 = th4;
                zzek = this;
                th = th22;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (SQLiteException e4) {
            zzek = this;
            obj = e4;
            cursor = null;
            zzgi().zziv().zze("Error querying app. appId", zzfi.zzbp(str), obj);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th222) {
            zzek = this;
            th = th222;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    public final long zzbg(String str) {
        Preconditions.checkNotEmpty(str);
        zzab();
        zzch();
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            String valueOf = String.valueOf(Math.max(0, Math.min(1000000, zzgk().zzb(str, zzez.zzait))));
            return (long) writableDatabase.delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str, valueOf});
        } catch (SQLiteException e) {
            zzgi().zziv().zze("Error deleting over the limit events. appId", zzfi.zzbp(str), e);
            return 0;
        }
    }

    @WorkerThread
    public final byte[] zzbh(String str) {
        Object e;
        Throwable th;
        Preconditions.checkNotEmpty(str);
        zzab();
        zzch();
        Cursor query;
        try {
            query = getWritableDatabase().query("apps", new String[]{"remote_config"}, "app_id=?", new String[]{str}, null, null, null);
            try {
                if (query.moveToFirst()) {
                    byte[] blob = query.getBlob(0);
                    if (query.moveToNext()) {
                        zzgi().zziv().zzg("Got multiple records for app config, expected one. appId", zzfi.zzbp(str));
                    }
                    if (query != null) {
                        query.close();
                    }
                    return blob;
                }
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    zzgi().zziv().zze("Error querying remote config. appId", zzfi.zzbp(str), e);
                    if (query != null) {
                        query.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            query = null;
            zzgi().zziv().zze("Error querying remote config. appId", zzfi.zzbp(str), e);
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final java.util.Map<java.lang.Integer, com.google.android.gms.internal.measurement.zzkv> zzbi(java.lang.String r12) {
        /*
        r11 = this;
        r11.zzch();
        r11.zzab();
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12);
        r0 = r11.getWritableDatabase();
        r8 = 0;
        r1 = "audience_filter_values";
        r2 = "audience_id";
        r3 = "current_results";
        r2 = new java.lang.String[]{r2, r3};	 Catch:{ SQLiteException -> 0x007c, all -> 0x0079 }
        r3 = "app_id=?";
        r9 = 1;
        r4 = new java.lang.String[r9];	 Catch:{ SQLiteException -> 0x007c, all -> 0x0079 }
        r10 = 0;
        r4[r10] = r12;	 Catch:{ SQLiteException -> 0x007c, all -> 0x0079 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r0 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ SQLiteException -> 0x007c, all -> 0x0079 }
        r1 = r0.moveToFirst();	 Catch:{ SQLiteException -> 0x0077 }
        if (r1 != 0) goto L_0x0033;
    L_0x002d:
        if (r0 == 0) goto L_0x0032;
    L_0x002f:
        r0.close();
    L_0x0032:
        return r8;
    L_0x0033:
        r1 = new android.support.v4.util.ArrayMap;	 Catch:{ SQLiteException -> 0x0077 }
        r1.<init>();	 Catch:{ SQLiteException -> 0x0077 }
    L_0x0038:
        r2 = r0.getInt(r10);	 Catch:{ SQLiteException -> 0x0077 }
        r3 = r0.getBlob(r9);	 Catch:{ SQLiteException -> 0x0077 }
        r4 = r3.length;	 Catch:{ SQLiteException -> 0x0077 }
        r3 = com.google.android.gms.internal.measurement.zzaca.zza(r3, r10, r4);	 Catch:{ SQLiteException -> 0x0077 }
        r4 = new com.google.android.gms.internal.measurement.zzkv;	 Catch:{ SQLiteException -> 0x0077 }
        r4.<init>();	 Catch:{ SQLiteException -> 0x0077 }
        r4.zzb(r3);	 Catch:{ IOException -> 0x0055 }
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ SQLiteException -> 0x0077 }
        r1.put(r2, r4);	 Catch:{ SQLiteException -> 0x0077 }
        goto L_0x006b;
    L_0x0055:
        r3 = move-exception;
        r4 = r11.zzgi();	 Catch:{ SQLiteException -> 0x0077 }
        r4 = r4.zziv();	 Catch:{ SQLiteException -> 0x0077 }
        r5 = "Failed to merge filter results. appId, audienceId, error";
        r6 = com.google.android.gms.internal.measurement.zzfi.zzbp(r12);	 Catch:{ SQLiteException -> 0x0077 }
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ SQLiteException -> 0x0077 }
        r4.zzd(r5, r6, r2, r3);	 Catch:{ SQLiteException -> 0x0077 }
    L_0x006b:
        r2 = r0.moveToNext();	 Catch:{ SQLiteException -> 0x0077 }
        if (r2 != 0) goto L_0x0038;
    L_0x0071:
        if (r0 == 0) goto L_0x0076;
    L_0x0073:
        r0.close();
    L_0x0076:
        return r1;
    L_0x0077:
        r1 = move-exception;
        goto L_0x007e;
    L_0x0079:
        r12 = move-exception;
        r0 = r8;
        goto L_0x0096;
    L_0x007c:
        r1 = move-exception;
        r0 = r8;
    L_0x007e:
        r2 = r11.zzgi();	 Catch:{ all -> 0x0095 }
        r2 = r2.zziv();	 Catch:{ all -> 0x0095 }
        r3 = "Database error querying filter results. appId";
        r12 = com.google.android.gms.internal.measurement.zzfi.zzbp(r12);	 Catch:{ all -> 0x0095 }
        r2.zze(r3, r12, r1);	 Catch:{ all -> 0x0095 }
        if (r0 == 0) goto L_0x0094;
    L_0x0091:
        r0.close();
    L_0x0094:
        return r8;
    L_0x0095:
        r12 = move-exception;
    L_0x0096:
        if (r0 == 0) goto L_0x009b;
    L_0x0098:
        r0.close();
    L_0x009b:
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzbi(java.lang.String):java.util.Map<java.lang.Integer, com.google.android.gms.internal.measurement.zzkv>");
    }

    public final long zzbj(String str) {
        Preconditions.checkNotEmpty(str);
        return zza("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0);
    }

    @WorkerThread
    public final List<zzef> zzc(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzab();
        zzch();
        List arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder stringBuilder = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            stringBuilder.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat(Operation.MULTIPLY));
            stringBuilder.append(" and name glob ?");
        }
        return zzb(stringBuilder.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    @WorkerThread
    @VisibleForTesting
    final void zzc(List<Long> list) {
        zzab();
        zzch();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (zzid()) {
            String join = TextUtils.join(",", list);
            StringBuilder stringBuilder = new StringBuilder(String.valueOf(join).length() + 2);
            stringBuilder.append("(");
            stringBuilder.append(join);
            stringBuilder.append(")");
            join = stringBuilder.toString();
            stringBuilder = new StringBuilder(String.valueOf(join).length() + 80);
            stringBuilder.append("SELECT COUNT(1) FROM queue WHERE rowid IN ");
            stringBuilder.append(join);
            stringBuilder.append(" AND retry_count =  2147483647 LIMIT 1");
            if (zza(stringBuilder.toString(), null) > 0) {
                zzgi().zziy().log("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                StringBuilder stringBuilder2 = new StringBuilder(String.valueOf(join).length() + 127);
                stringBuilder2.append("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN ");
                stringBuilder2.append(join);
                stringBuilder2.append(" AND (retry_count IS NULL OR retry_count < 2147483647)");
                writableDatabase.execSQL(stringBuilder2.toString());
            } catch (SQLiteException e) {
                zzgi().zziv().zzg("Error incrementing retry count. error", e);
            }
        }
    }

    @WorkerThread
    public final zzet zzf(String str, String str2) {
        Object obj;
        SQLiteException e;
        Cursor cursor;
        Throwable th;
        Throwable th2;
        String str3 = str2;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzab();
        zzch();
        try {
            String[] strArr = new String[2];
            strArr[0] = str;
            boolean z = true;
            strArr[1] = str3;
            Cursor query = getWritableDatabase().query("events", new String[]{"lifetime_count", "current_bundle_count", "last_fire_timestamp", "last_bundled_timestamp", "last_sampled_complex_event_id", "last_sampling_rate", "last_exempt_from_sampling"}, "app_id=? and name=?", strArr, null, null, null);
            try {
                if (query.moveToFirst()) {
                    Boolean bool;
                    long j = query.getLong(0);
                    long j2 = query.getLong(1);
                    long j3 = query.getLong(2);
                    long j4 = query.isNull(3) ? 0 : query.getLong(3);
                    zzet valueOf = query.isNull(4) ? null : Long.valueOf(query.getLong(4));
                    zzet valueOf2 = query.isNull(5) ? null : Long.valueOf(query.getLong(5));
                    if (query.isNull(6)) {
                        bool = null;
                    } else {
                        try {
                            if (query.getLong(6) != 1) {
                                z = false;
                            }
                            bool = Boolean.valueOf(z);
                        } catch (SQLiteException e2) {
                            obj = e2;
                            cursor = query;
                            try {
                                zzgi().zziv().zzd("Error querying events. appId", zzfi.zzbp(str), zzgf().zzbm(str2), obj);
                                if (cursor != null) {
                                    cursor.close();
                                }
                                return null;
                            } catch (Throwable th3) {
                                th = th3;
                                th2 = th;
                                if (cursor != null) {
                                    cursor.close();
                                }
                                throw th2;
                            }
                        } catch (Throwable th4) {
                            th2 = th4;
                            cursor = query;
                            if (cursor != null) {
                                cursor.close();
                            }
                            throw th2;
                        }
                    }
                    zzet zzet = zzet;
                    String str4 = str3;
                    cursor = query;
                    try {
                        zzet = new zzet(str, str4, j, j2, j3, j4, valueOf, valueOf2, bool);
                        if (cursor.moveToNext()) {
                            zzgi().zziv().zzg("Got multiple records for event aggregates, expected one. appId", zzfi.zzbp(str));
                        }
                        if (cursor != null) {
                            cursor.close();
                        }
                        return zzet;
                    } catch (SQLiteException e3) {
                        e2 = e3;
                        obj = e2;
                        zzgi().zziv().zzd("Error querying events. appId", zzfi.zzbp(str), zzgf().zzbm(str2), obj);
                        if (cursor != null) {
                            cursor.close();
                        }
                        return null;
                    }
                }
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (SQLiteException e4) {
                e2 = e4;
                cursor = query;
                obj = e2;
                zzgi().zziv().zzd("Error querying events. appId", zzfi.zzbp(str), zzgf().zzbm(str2), obj);
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            } catch (Throwable th5) {
                th4 = th5;
                cursor = query;
                th2 = th4;
                if (cursor != null) {
                    cursor.close();
                }
                throw th2;
            }
        } catch (SQLiteException e22) {
            obj = e22;
            cursor = null;
            zzgi().zziv().zzd("Error querying events. appId", zzfi.zzbp(str), zzgf().zzbm(str2), obj);
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th42) {
            th2 = th42;
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            throw th2;
        }
    }

    @WorkerThread
    public final void zzg(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzab();
        zzch();
        try {
            zzgi().zzjc().zzg("Deleted user attribute rows", Integer.valueOf(getWritableDatabase().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2})));
        } catch (SQLiteException e) {
            zzgi().zziv().zzd("Error deleting user attribute. appId", zzfi.zzbp(str), zzgf().zzbo(str2), e);
        }
    }

    protected final boolean zzgn() {
        return false;
    }

    @WorkerThread
    public final zzkc zzh(String str, String str2) {
        Cursor query;
        SQLiteException e;
        Object obj;
        Throwable th;
        Throwable th2;
        zzek zzek;
        String str3 = str2;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzab();
        zzch();
        try {
            query = getWritableDatabase().query("user_attributes", new String[]{"set_timestamp", Param.VALUE, Param.ORIGIN}, "app_id=? and name=?", new String[]{str, str3}, null, null, null);
            try {
                if (query.moveToFirst()) {
                    long j = query.getLong(0);
                    try {
                        String str4 = str;
                        zzkc zzkc = new zzkc(str4, query.getString(2), str3, j, zza(query, 1));
                        if (query.moveToNext()) {
                            zzgi().zziv().zzg("Got multiple records for user property, expected one. appId", zzfi.zzbp(str));
                        }
                        if (query != null) {
                            query.close();
                        }
                        return zzkc;
                    } catch (SQLiteException e2) {
                        e = e2;
                        obj = e;
                        try {
                            zzgi().zziv().zzd("Error querying user property. appId", zzfi.zzbp(str), zzgf().zzbo(str3), obj);
                            if (query != null) {
                                query.close();
                            }
                            return null;
                        } catch (Throwable th3) {
                            th = th3;
                            th2 = th;
                            if (query != null) {
                                query.close();
                            }
                            throw th2;
                        }
                    }
                }
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (SQLiteException e3) {
                e = e3;
                zzek = this;
                obj = e;
                zzgi().zziv().zzd("Error querying user property. appId", zzfi.zzbp(str), zzgf().zzbo(str3), obj);
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (Throwable th4) {
                th = th4;
                zzek = this;
                th2 = th;
                if (query != null) {
                    query.close();
                }
                throw th2;
            }
        } catch (SQLiteException e4) {
            zzek = this;
            obj = e4;
            query = null;
            zzgi().zziv().zzd("Error querying user property. appId", zzfi.zzbp(str), zzgf().zzbo(str3), obj);
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th5) {
            zzek = this;
            th2 = th5;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th2;
        }
    }

    @WorkerThread
    public final String zzhv() {
        Cursor rawQuery;
        Object e;
        Throwable th;
        try {
            rawQuery = getWritableDatabase().rawQuery("select app_id from queue order by has_realtime desc, rowid asc limit 1;", null);
            try {
                if (rawQuery.moveToFirst()) {
                    String string = rawQuery.getString(0);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    return string;
                }
                if (rawQuery != null) {
                    rawQuery.close();
                }
                return null;
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    zzgi().zziv().zzg("Database error getting next bundle app id", e);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            rawQuery = null;
            zzgi().zziv().zzg("Database error getting next bundle app id", e);
            if (rawQuery != null) {
                rawQuery.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            rawQuery = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
    }

    public final boolean zzhw() {
        return zza("select count(1) > 0 from queue where has_realtime = 1", null) != 0;
    }

    @WorkerThread
    final void zzhx() {
        zzab();
        zzch();
        if (zzid()) {
            long j = zzgj().zzalw.get();
            long elapsedRealtime = zzbt().elapsedRealtime();
            if (Math.abs(elapsedRealtime - j) > ((Long) zzez.zzajc.get()).longValue()) {
                zzgj().zzalw.set(elapsedRealtime);
                zzab();
                zzch();
                if (zzid()) {
                    int delete = getWritableDatabase().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(zzbt().currentTimeMillis()), String.valueOf(zzeh.zzhq())});
                    if (delete > 0) {
                        zzgi().zzjc().zzg("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
                    }
                }
            }
        }
    }

    @WorkerThread
    public final long zzhy() {
        return zza("select max(bundle_end_timestamp) from queue", null, 0);
    }

    @WorkerThread
    public final long zzhz() {
        return zza("select max(timestamp) from raw_events", null, 0);
    }

    @WorkerThread
    public final zzef zzi(String str, String str2) {
        Cursor query;
        SQLiteException e;
        Object obj;
        Throwable th;
        Throwable th2;
        zzek zzek;
        String str3 = str2;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzab();
        zzch();
        try {
            query = getWritableDatabase().query("conditional_properties", new String[]{Param.ORIGIN, Param.VALUE, "active", "trigger_event_name", "trigger_timeout", "timed_out_event", "creation_timestamp", "triggered_event", "triggered_timestamp", "time_to_live", "expired_event"}, "app_id=? and name=?", new String[]{str, str3}, null, null, null);
            try {
                if (query.moveToFirst()) {
                    String string = query.getString(0);
                    try {
                        Object zza = zza(query, 1);
                        boolean z = query.getInt(2) != 0;
                        String string2 = query.getString(3);
                        long j = query.getLong(4);
                        zzex zzex = (zzex) zzjf().zza(query.getBlob(5), zzex.CREATOR);
                        String str4 = str;
                        zzef zzef = new zzef(str4, string, new zzka(str3, query.getLong(8), zza, string), query.getLong(6), z, string2, zzex, j, (zzex) zzjf().zza(query.getBlob(7), zzex.CREATOR), query.getLong(9), (zzex) zzjf().zza(query.getBlob(10), zzex.CREATOR));
                        if (query.moveToNext()) {
                            zzgi().zziv().zze("Got multiple records for conditional property, expected one", zzfi.zzbp(str), zzgf().zzbo(str3));
                        }
                        if (query != null) {
                            query.close();
                        }
                        return zzef;
                    } catch (SQLiteException e2) {
                        e = e2;
                        obj = e;
                        try {
                            zzgi().zziv().zzd("Error querying conditional property", zzfi.zzbp(str), zzgf().zzbo(str3), obj);
                            if (query != null) {
                                query.close();
                            }
                            return null;
                        } catch (Throwable th3) {
                            th = th3;
                            th2 = th;
                            if (query != null) {
                                query.close();
                            }
                            throw th2;
                        }
                    }
                }
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (SQLiteException e3) {
                e = e3;
                zzek = this;
                obj = e;
                zzgi().zziv().zzd("Error querying conditional property", zzfi.zzbp(str), zzgf().zzbo(str3), obj);
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (Throwable th4) {
                th = th4;
                zzek = this;
                th2 = th;
                if (query != null) {
                    query.close();
                }
                throw th2;
            }
        } catch (SQLiteException e4) {
            zzek = this;
            obj = e4;
            query = null;
            zzgi().zziv().zzd("Error querying conditional property", zzfi.zzbp(str), zzgf().zzbo(str3), obj);
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th5) {
            zzek = this;
            th2 = th5;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th2;
        }
    }

    public final boolean zzia() {
        return zza("select count(1) > 0 from raw_events", null) != 0;
    }

    public final boolean zzib() {
        return zza("select count(1) > 0 from raw_events where realtime = 1", null) != 0;
    }

    public final long zzic() {
        Object obj;
        Throwable th;
        Cursor cursor = null;
        try {
            Cursor rawQuery = getWritableDatabase().rawQuery("select rowid from raw_events order by rowid desc limit 1;", null);
            try {
                if (rawQuery.moveToFirst()) {
                    long j = rawQuery.getLong(0);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    return j;
                }
                if (rawQuery != null) {
                    rawQuery.close();
                }
                return -1;
            } catch (SQLiteException e) {
                Cursor cursor2 = rawQuery;
                obj = e;
                cursor = cursor2;
                try {
                    zzgi().zziv().zzg("Error querying raw events", obj);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return -1;
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                cursor = rawQuery;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (SQLiteException e2) {
            obj = e2;
            zzgi().zziv().zzg("Error querying raw events", obj);
            if (cursor != null) {
                cursor.close();
            }
            return -1;
        }
    }

    @WorkerThread
    public final int zzj(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzab();
        zzch();
        try {
            return getWritableDatabase().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzgi().zziv().zzd("Error deleting conditional property", zzfi.zzbp(str), zzgf().zzbo(str2), e);
            return 0;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.zzkh>> zzk(java.lang.String r13, java.lang.String r14) {
        /*
        r12 = this;
        r12.zzch();
        r12.zzab();
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r13);
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14);
        r0 = new android.support.v4.util.ArrayMap;
        r0.<init>();
        r1 = r12.getWritableDatabase();
        r9 = 0;
        r2 = "event_filters";
        r3 = "audience_id";
        r4 = "data";
        r3 = new java.lang.String[]{r3, r4};	 Catch:{ SQLiteException -> 0x0096, all -> 0x0093 }
        r4 = "app_id=? AND event_name=?";
        r5 = 2;
        r5 = new java.lang.String[r5];	 Catch:{ SQLiteException -> 0x0096, all -> 0x0093 }
        r10 = 0;
        r5[r10] = r13;	 Catch:{ SQLiteException -> 0x0096, all -> 0x0093 }
        r11 = 1;
        r5[r11] = r14;	 Catch:{ SQLiteException -> 0x0096, all -> 0x0093 }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r14 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ SQLiteException -> 0x0096, all -> 0x0093 }
        r1 = r14.moveToFirst();	 Catch:{ SQLiteException -> 0x0091 }
        if (r1 != 0) goto L_0x0042;
    L_0x0038:
        r0 = java.util.Collections.emptyMap();	 Catch:{ SQLiteException -> 0x0091 }
        if (r14 == 0) goto L_0x0041;
    L_0x003e:
        r14.close();
    L_0x0041:
        return r0;
    L_0x0042:
        r1 = r14.getBlob(r11);	 Catch:{ SQLiteException -> 0x0091 }
        r2 = r1.length;	 Catch:{ SQLiteException -> 0x0091 }
        r1 = com.google.android.gms.internal.measurement.zzaca.zza(r1, r10, r2);	 Catch:{ SQLiteException -> 0x0091 }
        r2 = new com.google.android.gms.internal.measurement.zzkh;	 Catch:{ SQLiteException -> 0x0091 }
        r2.<init>();	 Catch:{ SQLiteException -> 0x0091 }
        r2.zzb(r1);	 Catch:{ IOException -> 0x0073 }
        r1 = r14.getInt(r10);	 Catch:{ SQLiteException -> 0x0091 }
        r3 = java.lang.Integer.valueOf(r1);	 Catch:{ SQLiteException -> 0x0091 }
        r3 = r0.get(r3);	 Catch:{ SQLiteException -> 0x0091 }
        r3 = (java.util.List) r3;	 Catch:{ SQLiteException -> 0x0091 }
        if (r3 != 0) goto L_0x006f;
    L_0x0063:
        r3 = new java.util.ArrayList;	 Catch:{ SQLiteException -> 0x0091 }
        r3.<init>();	 Catch:{ SQLiteException -> 0x0091 }
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ SQLiteException -> 0x0091 }
        r0.put(r1, r3);	 Catch:{ SQLiteException -> 0x0091 }
    L_0x006f:
        r3.add(r2);	 Catch:{ SQLiteException -> 0x0091 }
        goto L_0x0085;
    L_0x0073:
        r1 = move-exception;
        r2 = r12.zzgi();	 Catch:{ SQLiteException -> 0x0091 }
        r2 = r2.zziv();	 Catch:{ SQLiteException -> 0x0091 }
        r3 = "Failed to merge filter. appId";
        r4 = com.google.android.gms.internal.measurement.zzfi.zzbp(r13);	 Catch:{ SQLiteException -> 0x0091 }
        r2.zze(r3, r4, r1);	 Catch:{ SQLiteException -> 0x0091 }
    L_0x0085:
        r1 = r14.moveToNext();	 Catch:{ SQLiteException -> 0x0091 }
        if (r1 != 0) goto L_0x0042;
    L_0x008b:
        if (r14 == 0) goto L_0x0090;
    L_0x008d:
        r14.close();
    L_0x0090:
        return r0;
    L_0x0091:
        r0 = move-exception;
        goto L_0x0098;
    L_0x0093:
        r13 = move-exception;
        r14 = r9;
        goto L_0x00b0;
    L_0x0096:
        r0 = move-exception;
        r14 = r9;
    L_0x0098:
        r1 = r12.zzgi();	 Catch:{ all -> 0x00af }
        r1 = r1.zziv();	 Catch:{ all -> 0x00af }
        r2 = "Database error querying filters. appId";
        r13 = com.google.android.gms.internal.measurement.zzfi.zzbp(r13);	 Catch:{ all -> 0x00af }
        r1.zze(r2, r13, r0);	 Catch:{ all -> 0x00af }
        if (r14 == 0) goto L_0x00ae;
    L_0x00ab:
        r14.close();
    L_0x00ae:
        return r9;
    L_0x00af:
        r13 = move-exception;
    L_0x00b0:
        if (r14 == 0) goto L_0x00b5;
    L_0x00b2:
        r14.close();
    L_0x00b5:
        throw r13;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzk(java.lang.String, java.lang.String):java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.zzkh>>");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.zzkk>> zzl(java.lang.String r13, java.lang.String r14) {
        /*
        r12 = this;
        r12.zzch();
        r12.zzab();
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r13);
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14);
        r0 = new android.support.v4.util.ArrayMap;
        r0.<init>();
        r1 = r12.getWritableDatabase();
        r9 = 0;
        r2 = "property_filters";
        r3 = "audience_id";
        r4 = "data";
        r3 = new java.lang.String[]{r3, r4};	 Catch:{ SQLiteException -> 0x0096, all -> 0x0093 }
        r4 = "app_id=? AND property_name=?";
        r5 = 2;
        r5 = new java.lang.String[r5];	 Catch:{ SQLiteException -> 0x0096, all -> 0x0093 }
        r10 = 0;
        r5[r10] = r13;	 Catch:{ SQLiteException -> 0x0096, all -> 0x0093 }
        r11 = 1;
        r5[r11] = r14;	 Catch:{ SQLiteException -> 0x0096, all -> 0x0093 }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r14 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ SQLiteException -> 0x0096, all -> 0x0093 }
        r1 = r14.moveToFirst();	 Catch:{ SQLiteException -> 0x0091 }
        if (r1 != 0) goto L_0x0042;
    L_0x0038:
        r0 = java.util.Collections.emptyMap();	 Catch:{ SQLiteException -> 0x0091 }
        if (r14 == 0) goto L_0x0041;
    L_0x003e:
        r14.close();
    L_0x0041:
        return r0;
    L_0x0042:
        r1 = r14.getBlob(r11);	 Catch:{ SQLiteException -> 0x0091 }
        r2 = r1.length;	 Catch:{ SQLiteException -> 0x0091 }
        r1 = com.google.android.gms.internal.measurement.zzaca.zza(r1, r10, r2);	 Catch:{ SQLiteException -> 0x0091 }
        r2 = new com.google.android.gms.internal.measurement.zzkk;	 Catch:{ SQLiteException -> 0x0091 }
        r2.<init>();	 Catch:{ SQLiteException -> 0x0091 }
        r2.zzb(r1);	 Catch:{ IOException -> 0x0073 }
        r1 = r14.getInt(r10);	 Catch:{ SQLiteException -> 0x0091 }
        r3 = java.lang.Integer.valueOf(r1);	 Catch:{ SQLiteException -> 0x0091 }
        r3 = r0.get(r3);	 Catch:{ SQLiteException -> 0x0091 }
        r3 = (java.util.List) r3;	 Catch:{ SQLiteException -> 0x0091 }
        if (r3 != 0) goto L_0x006f;
    L_0x0063:
        r3 = new java.util.ArrayList;	 Catch:{ SQLiteException -> 0x0091 }
        r3.<init>();	 Catch:{ SQLiteException -> 0x0091 }
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ SQLiteException -> 0x0091 }
        r0.put(r1, r3);	 Catch:{ SQLiteException -> 0x0091 }
    L_0x006f:
        r3.add(r2);	 Catch:{ SQLiteException -> 0x0091 }
        goto L_0x0085;
    L_0x0073:
        r1 = move-exception;
        r2 = r12.zzgi();	 Catch:{ SQLiteException -> 0x0091 }
        r2 = r2.zziv();	 Catch:{ SQLiteException -> 0x0091 }
        r3 = "Failed to merge filter";
        r4 = com.google.android.gms.internal.measurement.zzfi.zzbp(r13);	 Catch:{ SQLiteException -> 0x0091 }
        r2.zze(r3, r4, r1);	 Catch:{ SQLiteException -> 0x0091 }
    L_0x0085:
        r1 = r14.moveToNext();	 Catch:{ SQLiteException -> 0x0091 }
        if (r1 != 0) goto L_0x0042;
    L_0x008b:
        if (r14 == 0) goto L_0x0090;
    L_0x008d:
        r14.close();
    L_0x0090:
        return r0;
    L_0x0091:
        r0 = move-exception;
        goto L_0x0098;
    L_0x0093:
        r13 = move-exception;
        r14 = r9;
        goto L_0x00b0;
    L_0x0096:
        r0 = move-exception;
        r14 = r9;
    L_0x0098:
        r1 = r12.zzgi();	 Catch:{ all -> 0x00af }
        r1 = r1.zziv();	 Catch:{ all -> 0x00af }
        r2 = "Database error querying filters. appId";
        r13 = com.google.android.gms.internal.measurement.zzfi.zzbp(r13);	 Catch:{ all -> 0x00af }
        r1.zze(r2, r13, r0);	 Catch:{ all -> 0x00af }
        if (r14 == 0) goto L_0x00ae;
    L_0x00ab:
        r14.close();
    L_0x00ae:
        return r9;
    L_0x00af:
        r13 = move-exception;
    L_0x00b0:
        if (r14 == 0) goto L_0x00b5;
    L_0x00b2:
        r14.close();
    L_0x00b5:
        throw r13;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzek.zzl(java.lang.String, java.lang.String):java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.zzkk>>");
    }

    @WorkerThread
    @VisibleForTesting
    protected final long zzm(String str, String str2) {
        SQLiteException e;
        Throwable th;
        Throwable th2;
        zzek zzek;
        String str3 = str;
        String str4 = str2;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzab();
        zzch();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        long zza;
        try {
            ContentValues contentValues;
            StringBuilder stringBuilder = new StringBuilder(String.valueOf(str2).length() + 32);
            stringBuilder.append("select ");
            stringBuilder.append(str4);
            stringBuilder.append(" from app2 where app_id=?");
            try {
                zza = zza(stringBuilder.toString(), new String[]{str3}, -1);
                if (zza == -1) {
                    contentValues = new ContentValues();
                    contentValues.put("app_id", str3);
                    contentValues.put("first_open_count", Integer.valueOf(0));
                    contentValues.put("previous_install_count", Integer.valueOf(0));
                    if (writableDatabase.insertWithOnConflict("app2", null, contentValues, 5) == -1) {
                        zzgi().zziv().zze("Failed to insert column (got -1). appId", zzfi.zzbp(str), str4);
                        writableDatabase.endTransaction();
                        return -1;
                    }
                    zza = 0;
                }
            } catch (SQLiteException e2) {
                e = e2;
                zza = 0;
                try {
                    zzgi().zziv().zzd("Error inserting column. appId", zzfi.zzbp(str), str4, e);
                    writableDatabase.endTransaction();
                    return zza;
                } catch (Throwable th3) {
                    th = th3;
                    th2 = th;
                    writableDatabase.endTransaction();
                    throw th2;
                }
            }
            try {
                contentValues = new ContentValues();
                contentValues.put("app_id", str3);
                contentValues.put(str4, Long.valueOf(zza + 1));
                if (((long) writableDatabase.update("app2", contentValues, "app_id = ?", new String[]{str3})) == 0) {
                    zzgi().zziv().zze("Failed to update column (got 0). appId", zzfi.zzbp(str), str4);
                    writableDatabase.endTransaction();
                    return -1;
                }
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
                return zza;
            } catch (SQLiteException e3) {
                e = e3;
                zzgi().zziv().zzd("Error inserting column. appId", zzfi.zzbp(str), str4, e);
                writableDatabase.endTransaction();
                return zza;
            }
        } catch (SQLiteException e4) {
            e = e4;
            zzek = this;
            zza = 0;
            zzgi().zziv().zzd("Error inserting column. appId", zzfi.zzbp(str), str4, e);
            writableDatabase.endTransaction();
            return zza;
        } catch (Throwable th4) {
            th = th4;
            zzek = this;
            th2 = th;
            writableDatabase.endTransaction();
            throw th2;
        }
    }
}
