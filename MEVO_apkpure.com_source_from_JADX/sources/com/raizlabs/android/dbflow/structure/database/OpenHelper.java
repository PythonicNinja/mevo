package com.raizlabs.android.dbflow.structure.database;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface OpenHelper {
    void backupDB();

    void closeDB();

    @NonNull
    DatabaseWrapper getDatabase();

    @Nullable
    DatabaseHelperDelegate getDelegate();

    boolean isDatabaseIntegrityOk();

    void performRestoreFromBackup();

    void setDatabaseListener(@Nullable DatabaseHelperListener databaseHelperListener);
}
