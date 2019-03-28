package com.raizlabs.android.dbflow.sql.language;

import android.support.annotation.NonNull;
import com.google.android.gms.actions.SearchIntents;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.FlowCursor;
import java.util.List;

public abstract class BaseTransformable<TModel> extends BaseModelQueriable<TModel> implements Transformable<TModel>, WhereBase<TModel> {
    protected BaseTransformable(Class<TModel> cls) {
        super(cls);
    }

    @NonNull
    public Where<TModel> where(@NonNull SQLOperator... sQLOperatorArr) {
        return new Where(this, sQLOperatorArr);
    }

    public FlowCursor query() {
        return where(new SQLOperator[0]).query();
    }

    public FlowCursor query(@NonNull DatabaseWrapper databaseWrapper) {
        return where(new SQLOperator[0]).query(databaseWrapper);
    }

    public long count() {
        return where(new SQLOperator[0]).count();
    }

    public long count(@NonNull DatabaseWrapper databaseWrapper) {
        return where(new SQLOperator[0]).count(databaseWrapper);
    }

    public long executeUpdateDelete(@NonNull DatabaseWrapper databaseWrapper) {
        return where(new SQLOperator[0]).executeUpdateDelete(databaseWrapper);
    }

    @NonNull
    public Where<TModel> groupBy(NameAlias... nameAliasArr) {
        return where(new SQLOperator[0]).groupBy(nameAliasArr);
    }

    @NonNull
    public Where<TModel> groupBy(IProperty... iPropertyArr) {
        return where(new SQLOperator[0]).groupBy(iPropertyArr);
    }

    @NonNull
    public Where<TModel> orderBy(@NonNull NameAlias nameAlias, boolean z) {
        return where(new SQLOperator[0]).orderBy(nameAlias, z);
    }

    @NonNull
    public Where<TModel> orderBy(@NonNull IProperty iProperty, boolean z) {
        return where(new SQLOperator[0]).orderBy(iProperty, z);
    }

    @NonNull
    public Where<TModel> orderByAll(@NonNull List<OrderBy> list) {
        return where(new SQLOperator[0]).orderByAll(list);
    }

    @NonNull
    public Where<TModel> orderBy(@NonNull OrderBy orderBy) {
        return where(new SQLOperator[0]).orderBy(orderBy);
    }

    @NonNull
    public Where<TModel> limit(int i) {
        return where(new SQLOperator[0]).limit(i);
    }

    @NonNull
    public Where<TModel> offset(int i) {
        return where(new SQLOperator[0]).offset(i);
    }

    @NonNull
    public Where<TModel> having(SQLOperator... sQLOperatorArr) {
        return where(new SQLOperator[0]).having(sQLOperatorArr);
    }

    @NonNull
    public List<TModel> queryList() {
        checkSelect(SearchIntents.EXTRA_QUERY);
        return super.queryList();
    }

    public TModel querySingle() {
        checkSelect(SearchIntents.EXTRA_QUERY);
        limit(1);
        return super.querySingle();
    }

    private void checkSelect(String str) {
        if (!(getQueryBuilderBase() instanceof Select)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Please use ");
            stringBuilder.append(str);
            stringBuilder.append("(). The beginning is not a Select");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }
}
