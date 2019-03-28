package com.raizlabs.android.dbflow.sql.language;

import android.database.Cursor;
import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowLog.Level;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.runtime.NotifyDistributor;
import com.raizlabs.android.dbflow.sql.SqlUtils;
import com.raizlabs.android.dbflow.sql.queriable.Queriable;
import com.raizlabs.android.dbflow.structure.BaseModel.Action;
import com.raizlabs.android.dbflow.structure.database.DatabaseStatement;
import com.raizlabs.android.dbflow.structure.database.DatabaseStatementWrapper;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.FlowCursor;

public abstract class BaseQueriable<TModel> implements Queriable, Actionable {
    private final Class<TModel> table;

    @NonNull
    public abstract Action getPrimaryAction();

    protected BaseQueriable(Class<TModel> cls) {
        this.table = cls;
    }

    @NonNull
    public Class<TModel> getTable() {
        return this.table;
    }

    public long count(@NonNull DatabaseWrapper databaseWrapper) {
        try {
            String query = getQuery();
            Level level = Level.V;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Executing query: ");
            stringBuilder.append(query);
            FlowLog.log(level, stringBuilder.toString());
            return SqlUtils.longForQuery(databaseWrapper, query);
        } catch (Throwable e) {
            FlowLog.log(Level.W, e);
            return 0;
        }
    }

    public long count() {
        return count(FlowManager.getWritableDatabaseForTable(this.table));
    }

    public boolean hasData() {
        return count() > 0;
    }

    public boolean hasData(@NonNull DatabaseWrapper databaseWrapper) {
        return count(databaseWrapper) > 0 ? true : null;
    }

    public FlowCursor query() {
        query(FlowManager.getWritableDatabaseForTable(this.table));
        return null;
    }

    public FlowCursor query(@NonNull DatabaseWrapper databaseWrapper) {
        if (getPrimaryAction().equals(Action.INSERT)) {
            databaseWrapper = compileStatement(databaseWrapper);
            databaseWrapper.executeInsert();
            databaseWrapper.close();
        } else {
            String query = getQuery();
            Level level = Level.V;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Executing query: ");
            stringBuilder.append(query);
            FlowLog.log(level, stringBuilder.toString());
            databaseWrapper.execSQL(query);
        }
        return null;
    }

    public long executeInsert() {
        return executeInsert(FlowManager.getWritableDatabaseForTable(this.table));
    }

    public long executeInsert(@NonNull DatabaseWrapper databaseWrapper) {
        return compileStatement().executeInsert();
    }

    public void execute() {
        Cursor query = query();
        if (query != null) {
            query.close();
        } else {
            NotifyDistributor.get().notifyTableChanged(getTable(), getPrimaryAction());
        }
    }

    public void execute(@NonNull DatabaseWrapper databaseWrapper) {
        databaseWrapper = query(databaseWrapper);
        if (databaseWrapper != null) {
            databaseWrapper.close();
        } else {
            NotifyDistributor.get().notifyTableChanged(getTable(), getPrimaryAction());
        }
    }

    @NonNull
    public DatabaseStatement compileStatement() {
        return compileStatement(FlowManager.getWritableDatabaseForTable(this.table));
    }

    @NonNull
    public DatabaseStatement compileStatement(@NonNull DatabaseWrapper databaseWrapper) {
        String query = getQuery();
        Level level = Level.V;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Compiling Query Into Statement: ");
        stringBuilder.append(query);
        FlowLog.log(level, stringBuilder.toString());
        return new DatabaseStatementWrapper(databaseWrapper.compileStatement(query), this);
    }

    public String toString() {
        return getQuery();
    }
}
