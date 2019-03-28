package com.raizlabs.android.dbflow.sql.language;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.sql.QueryBuilder;

public interface SQLOperator {
    void appendConditionToQuery(@NonNull QueryBuilder queryBuilder);

    @NonNull
    String columnName();

    boolean hasSeparator();

    @NonNull
    String operation();

    @NonNull
    SQLOperator separator(@NonNull String str);

    @Nullable
    String separator();

    @Nullable
    Object value();
}
