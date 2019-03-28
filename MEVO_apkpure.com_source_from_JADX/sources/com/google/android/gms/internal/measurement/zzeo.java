package com.google.android.gms.internal.measurement;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class zzeo {
    static void zza(zzfi zzfi, SQLiteDatabase sQLiteDatabase) {
        if (zzfi == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        File file = new File(sQLiteDatabase.getPath());
        if (!file.setReadable(false, false)) {
            zzfi.zziy().log("Failed to turn off database read permission");
        }
        if (!file.setWritable(false, false)) {
            zzfi.zziy().log("Failed to turn off database write permission");
        }
        if (!file.setReadable(true, true)) {
            zzfi.zziy().log("Failed to turn on database read permission for owner");
        }
        if (!file.setWritable(true, true)) {
            zzfi.zziy().log("Failed to turn on database write permission for owner");
        }
    }

    @WorkerThread
    static void zza(zzfi zzfi, SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String[] strArr) throws SQLiteException {
        if (zzfi == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        if (!zza(zzfi, sQLiteDatabase, str)) {
            sQLiteDatabase.execSQL(str2);
        }
        if (zzfi == null) {
            try {
                throw new IllegalArgumentException("Monitor must not be null");
            } catch (SQLiteException e) {
                zzfi.zziv().zzg("Failed to verify columns on table that was just created", str);
                throw e;
            }
        }
        Iterable zzb = zzb(sQLiteDatabase, str);
        String[] split = str3.split(",");
        int length = split.length;
        int i = 0;
        while (i < length) {
            String str4 = split[i];
            if (zzb.remove(str4)) {
                i++;
            } else {
                StringBuilder stringBuilder = new StringBuilder((String.valueOf(str).length() + 35) + String.valueOf(str4).length());
                stringBuilder.append("Table ");
                stringBuilder.append(str);
                stringBuilder.append(" is missing required column: ");
                stringBuilder.append(str4);
                throw new SQLiteException(stringBuilder.toString());
            }
        }
        if (strArr != null) {
            for (int i2 = 0; i2 < strArr.length; i2 += 2) {
                if (!zzb.remove(strArr[i2])) {
                    sQLiteDatabase.execSQL(strArr[i2 + 1]);
                }
            }
        }
        if (!zzb.isEmpty()) {
            zzfi.zziy().zze("Table has extra columns. table, columns", str, TextUtils.join(", ", zzb));
        }
    }

    @WorkerThread
    private static boolean zza(zzfi zzfi, SQLiteDatabase sQLiteDatabase, String str) {
        Object obj;
        Throwable th;
        if (zzfi == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        Cursor cursor = null;
        try {
            SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
            Cursor query = sQLiteDatabase2.query("SQLITE_MASTER", new String[]{"name"}, "name=?", new String[]{str}, null, null, null);
            try {
                boolean moveToFirst = query.moveToFirst();
                if (query != null) {
                    query.close();
                }
                return moveToFirst;
            } catch (SQLiteException e) {
                SQLiteException sQLiteException = e;
                cursor = query;
                obj = sQLiteException;
                try {
                    zzfi.zziy().zze("Error querying for table", str, obj);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                cursor = query;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (SQLiteException e2) {
            obj = e2;
            zzfi.zziy().zze("Error querying for table", str, obj);
            if (cursor != null) {
                cursor.close();
            }
            return false;
        }
    }

    @WorkerThread
    private static Set<String> zzb(SQLiteDatabase sQLiteDatabase, String str) {
        Object hashSet = new HashSet();
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 22);
        stringBuilder.append("SELECT * FROM ");
        stringBuilder.append(str);
        stringBuilder.append(" LIMIT 0");
        Cursor rawQuery = sQLiteDatabase.rawQuery(stringBuilder.toString(), null);
        try {
            Collections.addAll(hashSet, rawQuery.getColumnNames());
            return hashSet;
        } finally {
            rawQuery.close();
        }
    }
}
