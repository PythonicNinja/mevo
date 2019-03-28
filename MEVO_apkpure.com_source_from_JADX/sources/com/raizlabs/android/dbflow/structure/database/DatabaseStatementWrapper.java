package com.raizlabs.android.dbflow.structure.database;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.runtime.NotifyDistributor;
import com.raizlabs.android.dbflow.sql.language.BaseQueriable;

public class DatabaseStatementWrapper<TModel> extends BaseDatabaseStatement {
    @NonNull
    private final DatabaseStatement databaseStatement;
    private final BaseQueriable<TModel> modelQueriable;

    public DatabaseStatementWrapper(@NonNull DatabaseStatement databaseStatement, @NonNull BaseQueriable<TModel> baseQueriable) {
        this.databaseStatement = databaseStatement;
        this.modelQueriable = baseQueriable;
    }

    public long executeUpdateDelete() {
        long executeUpdateDelete = this.databaseStatement.executeUpdateDelete();
        if (executeUpdateDelete > 0) {
            NotifyDistributor.get().notifyTableChanged(this.modelQueriable.getTable(), this.modelQueriable.getPrimaryAction());
        }
        return executeUpdateDelete;
    }

    public void execute() {
        this.databaseStatement.execute();
    }

    public void close() {
        this.databaseStatement.close();
    }

    public long simpleQueryForLong() {
        return this.databaseStatement.simpleQueryForLong();
    }

    @Nullable
    public String simpleQueryForString() {
        return this.databaseStatement.simpleQueryForString();
    }

    public long executeInsert() {
        long executeInsert = this.databaseStatement.executeInsert();
        if (executeInsert > 0) {
            NotifyDistributor.get().notifyTableChanged(this.modelQueriable.getTable(), this.modelQueriable.getPrimaryAction());
        }
        return executeInsert;
    }

    public void bindString(int i, String str) {
        this.databaseStatement.bindString(i, str);
    }

    public void bindNull(int i) {
        this.databaseStatement.bindNull(i);
    }

    public void bindLong(int i, long j) {
        this.databaseStatement.bindLong(i, j);
    }

    public void bindDouble(int i, double d) {
        this.databaseStatement.bindDouble(i, d);
    }

    public void bindBlob(int i, byte[] bArr) {
        this.databaseStatement.bindBlob(i, bArr);
    }
}
