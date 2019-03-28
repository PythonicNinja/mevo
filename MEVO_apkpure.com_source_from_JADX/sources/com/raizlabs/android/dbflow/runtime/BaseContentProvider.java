package com.raizlabs.android.dbflow.runtime;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.net.Uri;
import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.DatabaseHolder;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;

public abstract class BaseContentProvider extends ContentProvider {
    protected DatabaseDefinition database;
    protected Class<? extends DatabaseHolder> moduleClass;

    public interface PropertyConverter {
        IProperty fromName(String str);
    }

    protected abstract int bulkInsert(@NonNull Uri uri, @NonNull ContentValues contentValues);

    protected abstract String getDatabaseName();

    protected BaseContentProvider() {
    }

    protected BaseContentProvider(@NonNull Class<? extends DatabaseHolder> cls) {
        this.moduleClass = cls;
    }

    public boolean onCreate() {
        if (this.moduleClass != null) {
            FlowManager.initModule(this.moduleClass);
        } else if (getContext() != null) {
            FlowManager.init(getContext());
        }
        return true;
    }

    public int bulkInsert(@NonNull final Uri uri, @NonNull final ContentValues[] contentValuesArr) {
        final int[] iArr = new int[]{0};
        getDatabase().executeTransaction(new ITransaction() {
            public void execute(DatabaseWrapper databaseWrapper) {
                for (ContentValues contentValues : contentValuesArr) {
                    int[] iArr = iArr;
                    iArr[0] = iArr[0] + BaseContentProvider.this.bulkInsert(uri, contentValues);
                }
            }
        });
        getContext().getContentResolver().notifyChange(uri, null);
        return iArr[0];
    }

    @NonNull
    protected DatabaseDefinition getDatabase() {
        if (this.database == null) {
            this.database = FlowManager.getDatabase(getDatabaseName());
        }
        return this.database;
    }
}
