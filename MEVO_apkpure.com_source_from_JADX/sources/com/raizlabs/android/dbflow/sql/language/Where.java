package com.raizlabs.android.dbflow.sql.language;

import android.support.annotation.NonNull;
import com.google.android.gms.actions.SearchIntents;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.queriable.ModelQueriable;
import com.raizlabs.android.dbflow.structure.BaseModel.Action;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.FlowCursor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Where<TModel> extends BaseModelQueriable<TModel> implements ModelQueriable<TModel>, Transformable<TModel> {
    private static final int VALUE_UNSET = -1;
    private final List<NameAlias> groupByList = new ArrayList();
    private OperatorGroup havingGroup;
    private int limit = -1;
    private int offset = -1;
    private OperatorGroup operatorGroup;
    private final List<OrderBy> orderByList = new ArrayList();
    private final WhereBase<TModel> whereBase;

    public Where(@NonNull WhereBase<TModel> whereBase, SQLOperator... sQLOperatorArr) {
        super(whereBase.getTable());
        this.whereBase = whereBase;
        this.operatorGroup = OperatorGroup.nonGroupingClause();
        this.havingGroup = OperatorGroup.nonGroupingClause();
        this.operatorGroup.andAll(sQLOperatorArr);
    }

    @NonNull
    public Where<TModel> and(@NonNull SQLOperator sQLOperator) {
        this.operatorGroup.and(sQLOperator);
        return this;
    }

    @NonNull
    public Where<TModel> or(@NonNull SQLOperator sQLOperator) {
        this.operatorGroup.or(sQLOperator);
        return this;
    }

    @NonNull
    public Where<TModel> andAll(@NonNull List<SQLOperator> list) {
        this.operatorGroup.andAll((Collection) list);
        return this;
    }

    @NonNull
    public Where<TModel> andAll(SQLOperator... sQLOperatorArr) {
        this.operatorGroup.andAll(sQLOperatorArr);
        return this;
    }

    @NonNull
    public Where<TModel> groupBy(NameAlias... nameAliasArr) {
        Collections.addAll(this.groupByList, nameAliasArr);
        return this;
    }

    @NonNull
    public Where<TModel> groupBy(IProperty... iPropertyArr) {
        for (IProperty nameAlias : iPropertyArr) {
            this.groupByList.add(nameAlias.getNameAlias());
        }
        return this;
    }

    @NonNull
    public Where<TModel> having(SQLOperator... sQLOperatorArr) {
        this.havingGroup.andAll(sQLOperatorArr);
        return this;
    }

    @NonNull
    public Where<TModel> orderBy(@NonNull NameAlias nameAlias, boolean z) {
        this.orderByList.add(new OrderBy(nameAlias, z));
        return this;
    }

    @NonNull
    public Where<TModel> orderBy(@NonNull IProperty iProperty, boolean z) {
        this.orderByList.add(new OrderBy(iProperty.getNameAlias(), z));
        return this;
    }

    @NonNull
    public Where<TModel> orderBy(@NonNull OrderBy orderBy) {
        this.orderByList.add(orderBy);
        return this;
    }

    @NonNull
    public Where<TModel> orderByAll(@NonNull List<OrderBy> list) {
        this.orderByList.addAll(list);
        return this;
    }

    @NonNull
    public Where<TModel> limit(int i) {
        this.limit = i;
        return this;
    }

    @NonNull
    public Where<TModel> offset(int i) {
        this.offset = i;
        return this;
    }

    @NonNull
    public Where<TModel> exists(@NonNull Where where) {
        this.operatorGroup.and(new ExistenceOperator().where(where));
        return this;
    }

    @NonNull
    public Action getPrimaryAction() {
        return this.whereBase.getPrimaryAction();
    }

    public String getQuery() {
        QueryBuilder appendQualifier = new QueryBuilder().append(this.whereBase.getQuery().trim()).appendSpace().appendQualifier("WHERE", this.operatorGroup.getQuery()).appendQualifier("GROUP BY", QueryBuilder.join((CharSequence) ",", this.groupByList)).appendQualifier("HAVING", this.havingGroup.getQuery()).appendQualifier("ORDER BY", QueryBuilder.join((CharSequence) ",", this.orderByList));
        if (this.limit > -1) {
            appendQualifier.appendQualifier("LIMIT", String.valueOf(this.limit));
        }
        if (this.offset > -1) {
            appendQualifier.appendQualifier("OFFSET", String.valueOf(this.offset));
        }
        return appendQualifier.getQuery();
    }

    public FlowCursor query(@NonNull DatabaseWrapper databaseWrapper) {
        if (this.whereBase.getQueryBuilderBase() instanceof Select) {
            return databaseWrapper.rawQuery(getQuery(), null);
        }
        return super.query(databaseWrapper);
    }

    public FlowCursor query() {
        return query(FlowManager.getDatabaseForTable(getTable()).getWritableDatabase());
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

    @NonNull
    public WhereBase<TModel> getWhereBase() {
        return this.whereBase;
    }

    private void checkSelect(String str) {
        if (!(this.whereBase.getQueryBuilderBase() instanceof Select)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Please use ");
            stringBuilder.append(str);
            stringBuilder.append("(). The beginning is not a ISelect");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }
}
