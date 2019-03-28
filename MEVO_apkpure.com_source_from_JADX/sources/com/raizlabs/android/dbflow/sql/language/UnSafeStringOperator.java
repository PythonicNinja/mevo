package com.raizlabs.android.dbflow.sql.language;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.StringUtils;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.QueryBuilder;

public class UnSafeStringOperator implements SQLOperator, Query {
    private final String conditionString;
    private String separator = "";

    @NonNull
    public String columnName() {
        return "";
    }

    @NonNull
    public String operation() {
        return "";
    }

    public Object value() {
        return "";
    }

    public UnSafeStringOperator(String str, String[] strArr) {
        if (str != null) {
            for (String replaceFirst : strArr) {
                str = str.replaceFirst("\\?", replaceFirst);
            }
        }
        this.conditionString = str;
    }

    public void appendConditionToQuery(@NonNull QueryBuilder queryBuilder) {
        queryBuilder.append(this.conditionString);
    }

    @Nullable
    public String separator() {
        return this.separator;
    }

    @NonNull
    public SQLOperator separator(@NonNull String str) {
        this.separator = str;
        return this;
    }

    public boolean hasSeparator() {
        return StringUtils.isNotNullOrEmpty(this.separator);
    }

    public String getQuery() {
        QueryBuilder queryBuilder = new QueryBuilder();
        appendConditionToQuery(queryBuilder);
        return queryBuilder.getQuery();
    }
}
