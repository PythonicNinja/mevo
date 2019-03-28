package com.raizlabs.android.dbflow.structure.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;

public class FlowSQLiteOpenHelper extends SQLiteOpenHelper implements OpenHelper {
    private AndroidDatabase androidDatabase;
    private DatabaseHelperDelegate databaseHelperDelegate;

    private class BackupHelper extends SQLiteOpenHelper implements OpenHelper {
        private AndroidDatabase androidDatabase;
        private final BaseDatabaseHelper baseDatabaseHelper;

        public void backupDB() {
        }

        public void closeDB() {
        }

        @Nullable
        public DatabaseHelperDelegate getDelegate() {
            return null;
        }

        public boolean isDatabaseIntegrityOk() {
            return false;
        }

        public void performRestoreFromBackup() {
        }

        public void setDatabaseListener(@Nullable DatabaseHelperListener databaseHelperListener) {
        }

        public BackupHelper(Context context, String str, int i, DatabaseDefinition databaseDefinition) {
            super(context, str, null, i);
            this.baseDatabaseHelper = new BaseDatabaseHelper(databaseDefinition);
        }

        @NonNull
        public DatabaseWrapper getDatabase() {
            if (this.androidDatabase == null) {
                this.androidDatabase = AndroidDatabase.from(getWritableDatabase());
            }
            return this.androidDatabase;
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            this.baseDatabaseHelper.onCreate(AndroidDatabase.from(sQLiteDatabase));
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.baseDatabaseHelper.onUpgrade(AndroidDatabase.from(sQLiteDatabase), i, i2);
        }

        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            this.baseDatabaseHelper.onOpen(AndroidDatabase.from(sQLiteDatabase));
        }

        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.baseDatabaseHelper.onDowngrade(AndroidDatabase.from(sQLiteDatabase), i, i2);
        }
    }

    public FlowSQLiteOpenHelper(@NonNull DatabaseDefinition databaseDefinition, @NonNull DatabaseHelperListener databaseHelperListener) {
        OpenHelper openHelper;
        super(FlowManager.getContext(), databaseDefinition.isInMemory() ? null : databaseDefinition.getDatabaseFileName(), null, databaseDefinition.getDatabaseVersion());
        if (databaseDefinition.backupEnabled()) {
            OpenHelper backupHelper = new BackupHelper(FlowManager.getContext(), DatabaseHelperDelegate.getTempDbFileName(databaseDefinition), databaseDefinition.getDatabaseVersion(), databaseDefinition);
        } else {
            openHelper = null;
        }
        this.databaseHelperDelegate = new DatabaseHelperDelegate(databaseHelperListener, databaseDefinition, openHelper);
    }

    public void performRestoreFromBackup() {
        this.databaseHelperDelegate.performRestoreFromBackup();
    }

    @Nullable
    public DatabaseHelperDelegate getDelegate() {
        return this.databaseHelperDelegate;
    }

    public boolean isDatabaseIntegrityOk() {
        return this.databaseHelperDelegate.isDatabaseIntegrityOk();
    }

    public void backupDB() {
        this.databaseHelperDelegate.backupDB();
    }

    @NonNull
    public DatabaseWrapper getDatabase() {
        if (this.androidDatabase == null || !this.androidDatabase.getDatabase().isOpen()) {
            this.androidDatabase = AndroidDatabase.from(getWritableDatabase());
        }
        return this.androidDatabase;
    }

    public void setDatabaseListener(@Nullable DatabaseHelperListener databaseHelperListener) {
        this.databaseHelperDelegate.setDatabaseHelperListener(databaseHelperListener);
    }

    public void onCreate(@NonNull SQLiteDatabase sQLiteDatabase) {
        this.databaseHelperDelegate.onCreate(AndroidDatabase.from(sQLiteDatabase));
    }

    public void onUpgrade(@NonNull SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.databaseHelperDelegate.onUpgrade(AndroidDatabase.from(sQLiteDatabase), i, i2);
    }

    public void onOpen(@NonNull SQLiteDatabase sQLiteDatabase) {
        this.databaseHelperDelegate.onOpen(AndroidDatabase.from(sQLiteDatabase));
    }

    public void onDowngrade(@NonNull SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.databaseHelperDelegate.onDowngrade(AndroidDatabase.from(sQLiteDatabase), i, i2);
    }

    public void closeDB() {
        getDatabase();
        this.androidDatabase.getDatabase().close();
    }
}
