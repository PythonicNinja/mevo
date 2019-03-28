package com.raizlabs.android.dbflow.sql.language;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.SqlUtils;
import java.util.ArrayList;
import java.util.List;

public class CompletedTrigger<TModel> implements Query {
    private final List<Query> triggerLogicQuery = new ArrayList();
    private TriggerMethod<TModel> triggerMethod;

    CompletedTrigger(TriggerMethod<TModel> triggerMethod, Query query) {
        this.triggerMethod = triggerMethod;
        this.triggerLogicQuery.add(query);
    }

    @NonNull
    public CompletedTrigger<TModel> and(@NonNull Query query) {
        this.triggerLogicQuery.add(query);
        return this;
    }

    public String getQuery() {
        QueryBuilder queryBuilder = new QueryBuilder(this.triggerMethod.getQuery());
        queryBuilder.append("\nBEGIN").append("\n").append(QueryBuilder.join((CharSequence) ";\n", this.triggerLogicQuery)).append(";").append("\nEND");
        return queryBuilder.getQuery();
    }

    public void enable() {
        FlowManager.getDatabaseForTable(this.triggerMethod.onTable).getWritableDatabase().execSQL(getQuery());
    }

    public void disable() {
        SqlUtils.dropTrigger(this.triggerMethod.onTable, this.triggerMethod.trigger.triggerName);
    }
}
