package com.raizlabs.android.dbflow.sql.language;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.QueryBuilder;

public class ExistenceOperator implements SQLOperator, Query {
    private Where innerWhere;

    public boolean hasSeparator() {
        return false;
    }

    @NonNull
    public String operation() {
        return "";
    }

    public void appendConditionToQuery(@NonNull QueryBuilder queryBuilder) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        stringBuilder.append(this.innerWhere.getQuery().trim());
        stringBuilder.append(")");
        queryBuilder.appendQualifier("EXISTS", stringBuilder.toString());
    }

    @NonNull
    public String columnName() {
        throw new RuntimeException("Method not valid for ExistenceOperator");
    }

    @Nullable
    public String separator() {
        throw new RuntimeException("Method not valid for ExistenceOperator");
    }

    @NonNull
    public SQLOperator separator(@NonNull String str) {
        throw new RuntimeException("Method not valid for ExistenceOperator");
    }

    public Object value() {
        return this.innerWhere;
    }

    public ExistenceOperator where(@NonNull Where where) {
        this.innerWhere = where;
        return this;
    }

    public String getQuery() {
        QueryBuilder queryBuilder = new QueryBuilder();
        appendConditionToQuery(queryBuilder);
        return queryBuilder.getQuery();
    }
}
