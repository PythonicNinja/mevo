package com.raizlabs.android.dbflow.sql.language;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.language.NameAlias.Builder;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.language.property.PropertyFactory;
import com.raizlabs.android.dbflow.sql.queriable.ModelQueriable;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Join<TModel, TFromModel> implements Query {
    private NameAlias alias;
    private From<TFromModel> from;
    private OperatorGroup onGroup;
    private final Class<TModel> table;
    private JoinType type;
    private List<IProperty> using = new ArrayList();

    public enum JoinType {
        LEFT_OUTER,
        INNER,
        CROSS,
        NATURAL
    }

    public Join(@NonNull From<TFromModel> from, @NonNull Class<TModel> cls, @NonNull JoinType joinType) {
        this.from = from;
        this.table = cls;
        this.type = joinType;
        this.alias = new Builder(FlowManager.getTableName(cls)).build();
    }

    public Join(@NonNull From<TFromModel> from, @NonNull JoinType joinType, @NonNull ModelQueriable<TModel> modelQueriable) {
        this.table = modelQueriable.getTable();
        this.from = from;
        this.type = joinType;
        this.alias = PropertyFactory.from((ModelQueriable) modelQueriable).getNameAlias();
    }

    @NonNull
    public Join<TModel, TFromModel> as(@NonNull String str) {
        this.alias = this.alias.newBuilder().as(str).build();
        return this;
    }

    @NonNull
    public From<TFromModel> on(SQLOperator... sQLOperatorArr) {
        checkNatural();
        this.onGroup = OperatorGroup.nonGroupingClause();
        this.onGroup.andAll(sQLOperatorArr);
        return this.from;
    }

    @NonNull
    public From<TFromModel> using(IProperty... iPropertyArr) {
        checkNatural();
        Collections.addAll(this.using, iPropertyArr);
        return this.from;
    }

    public From<TFromModel> end() {
        return this.from;
    }

    public String getQuery() {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.append(this.type.name().replace(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR, " ")).appendSpace();
        queryBuilder.append("JOIN").appendSpace().append(this.alias.getFullQuery()).appendSpace();
        if (!JoinType.NATURAL.equals(this.type)) {
            if (this.onGroup != null) {
                queryBuilder.append("ON").appendSpace().append(this.onGroup.getQuery()).appendSpace();
            } else if (!this.using.isEmpty()) {
                queryBuilder.append("USING (").appendList(this.using).append(")").appendSpace();
            }
        }
        return queryBuilder.getQuery();
    }

    @NonNull
    public Class<TModel> getTable() {
        return this.table;
    }

    private void checkNatural() {
        if (JoinType.NATURAL.equals(this.type)) {
            throw new IllegalArgumentException("Cannot specify a clause for this join if its NATURAL. Specifying a clause would have no effect. Call end() to continue the query.");
        }
    }
}
