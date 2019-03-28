package com.raizlabs.android.dbflow.structure.database;

import android.content.ContentValues;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface DatabaseWrapper {
    void beginTransaction();

    @NonNull
    DatabaseStatement compileStatement(@NonNull String str);

    int delete(@NonNull String str, @Nullable String str2, @Nullable String[] strArr);

    void endTransaction();

    void execSQL(@NonNull String str);

    int getVersion();

    long insertWithOnConflict(@NonNull String str, @Nullable String str2, @NonNull ContentValues contentValues, int i);

    @NonNull
    FlowCursor query(@NonNull String str, @Nullable String[] strArr, @Nullable String str2, @Nullable String[] strArr2, @Nullable String str3, @Nullable String str4, @Nullable String str5);

    @NonNull
    FlowCursor rawQuery(@NonNull String str, @Nullable String[] strArr);

    void setTransactionSuccessful();

    long updateWithOnConflict(@NonNull String str, @NonNull ContentValues contentValues, @Nullable String str2, @Nullable String[] strArr, int i);
}
