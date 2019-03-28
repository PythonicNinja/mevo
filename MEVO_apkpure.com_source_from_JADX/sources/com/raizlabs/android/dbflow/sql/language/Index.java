package com.raizlabs.android.dbflow.sql.language;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.SqlUtils;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import java.util.ArrayList;
import java.util.List;

public class Index<TModel> implements Query {
    private List<NameAlias> columns;
    @NonNull
    private final String indexName;
    private boolean isUnique = false;
    @NonNull
    private Class<TModel> table;

    public Index(@NonNull String str) {
        this.indexName = str;
        this.columns = new ArrayList();
    }

    @NonNull
    public Index<TModel> unique(boolean z) {
        this.isUnique = z;
        return this;
    }

    @NonNull
    public Index<TModel> on(@NonNull Class<TModel> cls, IProperty... iPropertyArr) {
        this.table = cls;
        for (IProperty and : iPropertyArr) {
            and(and);
        }
        return this;
    }

    @NonNull
    public Index<TModel> on(@NonNull Class<TModel> cls, @NonNull NameAlias nameAlias, NameAlias... nameAliasArr) {
        this.table = cls;
        and(nameAlias);
        for (NameAlias and : nameAliasArr) {
            and(and);
        }
        return this;
    }

    @NonNull
    public Index<TModel> and(@NonNull IProperty iProperty) {
        if (!this.columns.contains(iProperty.getNameAlias())) {
            this.columns.add(iProperty.getNameAlias());
        }
        return this;
    }

    @NonNull
    public Index<TModel> and(@NonNull NameAlias nameAlias) {
        if (!this.columns.contains(nameAlias)) {
            this.columns.add(nameAlias);
        }
        return this;
    }

    @NonNull
    public String getIndexName() {
        return this.indexName;
    }

    @NonNull
    public Class<TModel> getTable() {
        return this.table;
    }

    public boolean isUnique() {
        return this.isUnique;
    }

    public void enable(@NonNull DatabaseWrapper databaseWrapper) {
        if (this.table == null) {
            throw new IllegalStateException("Please call on() to set a table to use this index on.");
        }
        if (this.columns != null) {
            if (!this.columns.isEmpty()) {
                databaseWrapper.execSQL(getQuery());
                return;
            }
        }
        throw new IllegalStateException("There should be at least one column in this index");
    }

    public void enable() {
        enable(FlowManager.getDatabaseForTable(this.table).getWritableDatabase());
    }

    public void disable() {
        SqlUtils.dropIndex(FlowManager.getDatabaseForTable(this.table).getWritableDatabase(), this.indexName);
    }

    public void disable(DatabaseWrapper databaseWrapper) {
        SqlUtils.dropIndex(databaseWrapper, this.indexName);
    }

    public String getQuery() {
        return new QueryBuilder("CREATE ").append(this.isUnique ? "UNIQUE " : "").append("INDEX IF NOT EXISTS ").appendQuotedIfNeeded(this.indexName).append(" ON ").append(FlowManager.getTableName(this.table)).append("(").appendList(this.columns).append(")").getQuery();
    }
}
