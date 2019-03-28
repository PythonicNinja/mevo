package com.raizlabs.android.dbflow.sql.queriable;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.language.BaseModelQueriable;
import com.raizlabs.android.dbflow.structure.BaseModel.Action;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.FlowCursor;

public class StringQuery<TModel> extends BaseModelQueriable<TModel> implements Query, ModelQueriable<TModel> {
    private String[] args;
    private final String query;

    public StringQuery(@NonNull Class<TModel> cls, @NonNull String str) {
        super(cls);
        this.query = str;
    }

    public String getQuery() {
        return this.query;
    }

    public FlowCursor query() {
        return query(FlowManager.getDatabaseForTable(getTable()).getWritableDatabase());
    }

    public FlowCursor query(@NonNull DatabaseWrapper databaseWrapper) {
        return databaseWrapper.rawQuery(this.query, this.args);
    }

    @NonNull
    public StringQuery<TModel> setSelectionArgs(@NonNull String[] strArr) {
        this.args = strArr;
        return this;
    }

    @NonNull
    public Action getPrimaryAction() {
        return Action.CHANGE;
    }
}
