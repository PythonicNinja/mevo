package com.raizlabs.android.dbflow.sql.language;

import android.content.ContentValues;
import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.SqlUtils;
import com.raizlabs.android.dbflow.structure.BaseModel.Action;

public class Set<TModel> extends BaseTransformable<TModel> implements WhereBase<TModel> {
    private OperatorGroup operatorGroup = OperatorGroup.nonGroupingClause().setAllCommaSeparated(true);
    private Query update;

    public Set(@NonNull Query query, @NonNull Class<TModel> cls) {
        super(cls);
        this.update = query;
    }

    @NonNull
    public Set<TModel> conditions(SQLOperator... sQLOperatorArr) {
        this.operatorGroup.andAll(sQLOperatorArr);
        return this;
    }

    @NonNull
    public Set<TModel> conditionValues(@NonNull ContentValues contentValues) {
        SqlUtils.addContentValues(contentValues, this.operatorGroup);
        return this;
    }

    public String getQuery() {
        return new QueryBuilder(this.update.getQuery()).append("SET ").append(this.operatorGroup.getQuery()).appendSpace().getQuery();
    }

    @NonNull
    public Query getQueryBuilderBase() {
        return this.update;
    }

    @NonNull
    public Action getPrimaryAction() {
        return Action.UPDATE;
    }
}
