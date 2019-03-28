package com.raizlabs.android.dbflow.sql.language;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.sql.Query;

public interface WhereBase<TModel> extends Query, Actionable {
    @NonNull
    Query getQueryBuilderBase();

    @NonNull
    Class<TModel> getTable();
}
