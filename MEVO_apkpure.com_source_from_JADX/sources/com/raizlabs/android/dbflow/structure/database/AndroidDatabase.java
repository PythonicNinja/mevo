package com.raizlabs.android.dbflow.structure.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class AndroidDatabase implements DatabaseWrapper {
    private final SQLiteDatabase database;

    public static AndroidDatabase from(@NonNull SQLiteDatabase sQLiteDatabase) {
        return new AndroidDatabase(sQLiteDatabase);
    }

    AndroidDatabase(@NonNull SQLiteDatabase sQLiteDatabase) {
        this.database = sQLiteDatabase;
    }

    public void execSQL(@NonNull String str) {
        this.database.execSQL(str);
    }

    public void beginTransaction() {
        this.database.beginTransaction();
    }

    public void setTransactionSuccessful() {
        this.database.setTransactionSuccessful();
    }

    public void endTransaction() {
        this.database.endTransaction();
    }

    public int getVersion() {
        return this.database.getVersion();
    }

    public SQLiteDatabase getDatabase() {
        return this.database;
    }

    @NonNull
    public DatabaseStatement compileStatement(@NonNull String str) {
        return AndroidDatabaseStatement.from(this.database.compileStatement(str), this.database);
    }

    @NonNull
    public FlowCursor rawQuery(@NonNull String str, @Nullable String[] strArr) {
        return FlowCursor.from(this.database.rawQuery(str, strArr));
    }

    public long updateWithOnConflict(@NonNull String str, @NonNull ContentValues contentValues, @Nullable String str2, @Nullable String[] strArr, int i) {
        if (VERSION.SDK_INT >= 8) {
            return (long) this.database.updateWithOnConflict(str, contentValues, str2, strArr, i);
        }
        return (long) this.database.update(str, contentValues, str2, strArr);
    }

    public long insertWithOnConflict(@NonNull String str, @Nullable String str2, @NonNull ContentValues contentValues, int i) {
        if (VERSION.SDK_INT >= 8) {
            return this.database.insertWithOnConflict(str, str2, contentValues, i);
        }
        return this.database.insert(str, str2, contentValues);
    }

    @NonNull
    public FlowCursor query(@NonNull String str, @Nullable String[] strArr, @Nullable String str2, @Nullable String[] strArr2, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
        return FlowCursor.from(this.database.query(str, strArr, str2, strArr2, str3, str4, str5));
    }

    public int delete(@NonNull String str, @Nullable String str2, @Nullable String[] strArr) {
        return this.database.delete(str, str2, strArr);
    }
}
