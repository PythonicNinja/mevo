package com.raizlabs.android.dbflow.sql.language;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;

public class Update<TModel> implements Query {
    private ConflictAction conflictAction = ConflictAction.NONE;
    private final Class<TModel> table;

    public Update(Class<TModel> cls) {
        this.table = cls;
    }

    @NonNull
    public Update<TModel> conflictAction(@NonNull ConflictAction conflictAction) {
        this.conflictAction = conflictAction;
        return this;
    }

    @NonNull
    public Update<TModel> or(@NonNull ConflictAction conflictAction) {
        return conflictAction(conflictAction);
    }

    @NonNull
    public Update<TModel> orRollback() {
        return conflictAction(ConflictAction.ROLLBACK);
    }

    @NonNull
    public Update<TModel> orAbort() {
        return conflictAction(ConflictAction.ABORT);
    }

    @NonNull
    public Update<TModel> orReplace() {
        return conflictAction(ConflictAction.REPLACE);
    }

    @NonNull
    public Update<TModel> orFail() {
        return conflictAction(ConflictAction.FAIL);
    }

    @NonNull
    public Update<TModel> orIgnore() {
        return conflictAction(ConflictAction.IGNORE);
    }

    @NonNull
    public Set<TModel> set(SQLOperator... sQLOperatorArr) {
        return new Set(this, this.table).conditions(sQLOperatorArr);
    }

    public String getQuery() {
        QueryBuilder queryBuilder = new QueryBuilder("UPDATE ");
        if (!(this.conflictAction == null || this.conflictAction.equals(ConflictAction.NONE))) {
            queryBuilder.append(Operation.OR).appendSpaceSeparated(this.conflictAction.name());
        }
        queryBuilder.append(FlowManager.getTableName(this.table)).appendSpace();
        return queryBuilder.getQuery();
    }

    public Class<TModel> getTable() {
        return this.table;
    }
}
