package com.raizlabs.android.dbflow.sql.migration;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.sql.language.BaseQueriable;
import com.raizlabs.android.dbflow.sql.language.OperatorGroup;
import com.raizlabs.android.dbflow.sql.language.SQLOperator;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

public class UpdateTableMigration<TModel> extends BaseMigration {
    @Nullable
    private OperatorGroup setOperatorGroup;
    private final Class<TModel> table;
    @Nullable
    private OperatorGroup whereOperatorGroup;

    public UpdateTableMigration(@NonNull Class<TModel> cls) {
        this.table = cls;
    }

    @NonNull
    public UpdateTableMigration<TModel> set(SQLOperator... sQLOperatorArr) {
        if (this.setOperatorGroup == null) {
            this.setOperatorGroup = OperatorGroup.nonGroupingClause();
        }
        this.setOperatorGroup.andAll(sQLOperatorArr);
        return this;
    }

    @NonNull
    public UpdateTableMigration<TModel> where(SQLOperator... sQLOperatorArr) {
        if (this.whereOperatorGroup == null) {
            this.whereOperatorGroup = OperatorGroup.nonGroupingClause();
        }
        this.whereOperatorGroup.andAll(sQLOperatorArr);
        return this;
    }

    public final void migrate(@NonNull DatabaseWrapper databaseWrapper) {
        getUpdateStatement().execute(databaseWrapper);
    }

    @CallSuper
    public void onPostMigrate() {
        this.setOperatorGroup = null;
        this.whereOperatorGroup = null;
    }

    @NonNull
    public BaseQueriable<TModel> getUpdateStatement() {
        return SQLite.update(this.table).set(this.setOperatorGroup).where(this.whereOperatorGroup);
    }
}
