package com.raizlabs.android.dbflow.sql.language;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.language.property.IndexProperty;
import com.raizlabs.android.dbflow.structure.BaseModel.Action;

public class IndexedBy<TModel> extends BaseTransformable<TModel> {
    private final IndexProperty<TModel> indexProperty;
    private final WhereBase<TModel> whereBase;

    public IndexedBy(IndexProperty<TModel> indexProperty, WhereBase<TModel> whereBase) {
        super(whereBase.getTable());
        this.indexProperty = indexProperty;
        this.whereBase = whereBase;
    }

    @NonNull
    public Query getQueryBuilderBase() {
        return this.whereBase.getQueryBuilderBase();
    }

    public String getQuery() {
        return new QueryBuilder(this.whereBase.getQuery()).append(" INDEXED BY ").append(QueryBuilder.quoteIfNeeded(this.indexProperty.getIndexName())).appendSpace().getQuery();
    }

    @NonNull
    public Action getPrimaryAction() {
        return this.whereBase.getPrimaryAction();
    }
}
