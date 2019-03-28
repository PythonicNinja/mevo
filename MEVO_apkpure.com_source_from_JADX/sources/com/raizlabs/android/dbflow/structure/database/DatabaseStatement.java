package com.raizlabs.android.dbflow.structure.database;

import android.support.annotation.Nullable;

public interface DatabaseStatement {
    void bindBlob(int i, byte[] bArr);

    void bindBlobOrNull(int i, @Nullable byte[] bArr);

    void bindDouble(int i, double d);

    void bindDoubleOrNull(int i, @Nullable Double d);

    void bindLong(int i, long j);

    void bindNull(int i);

    void bindNumber(int i, @Nullable Number number);

    void bindNumberOrNull(int i, @Nullable Number number);

    void bindString(int i, String str);

    void bindStringOrNull(int i, @Nullable String str);

    void close();

    void execute();

    long executeInsert();

    long executeUpdateDelete();

    long simpleQueryForLong();

    @Nullable
    String simpleQueryForString();
}
