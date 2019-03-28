package com.raizlabs.android.dbflow.structure.database;

import android.support.annotation.Nullable;

public abstract class BaseDatabaseStatement implements DatabaseStatement {
    public void bindStringOrNull(int i, @Nullable String str) {
        if (str != null) {
            bindString(i, str);
        } else {
            bindNull(i);
        }
    }

    public void bindNumber(int i, @Nullable Number number) {
        bindNumberOrNull(i, number);
    }

    public void bindNumberOrNull(int i, @Nullable Number number) {
        if (number != null) {
            bindLong(i, number.longValue());
        } else {
            bindNull(i);
        }
    }

    public void bindDoubleOrNull(int i, @Nullable Double d) {
        if (d != null) {
            bindDouble(i, d.doubleValue());
        } else {
            bindNull(i);
        }
    }

    public void bindBlobOrNull(int i, @Nullable byte[] bArr) {
        if (bArr != null) {
            bindBlob(i, bArr);
        } else {
            bindNull(i);
        }
    }
}
