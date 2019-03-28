package com.raizlabs.android.dbflow.sql.language;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import java.util.ArrayList;
import java.util.List;

public class Case<TReturn> implements Query {
    private IProperty caseColumn;
    private List<CaseCondition<TReturn>> caseConditions = new ArrayList();
    private String columnName;
    private boolean efficientCase = false;
    private boolean elseSpecified = false;
    private TReturn elseValue;
    private boolean endSpecified = false;

    Case() {
    }

    Case(IProperty iProperty) {
        this.caseColumn = iProperty;
        if (iProperty != null) {
            this.efficientCase = true;
        }
    }

    @NonNull
    public CaseCondition<TReturn> when(@NonNull SQLOperator sQLOperator) {
        if (this.efficientCase) {
            throw new IllegalStateException("When using the efficient CASE method,you must pass in value only, not condition.");
        }
        CaseCondition<TReturn> caseCondition = new CaseCondition(this, sQLOperator);
        this.caseConditions.add(caseCondition);
        return caseCondition;
    }

    @NonNull
    public CaseCondition<TReturn> when(@Nullable TReturn tReturn) {
        if (this.efficientCase) {
            CaseCondition<TReturn> caseCondition = new CaseCondition(this, (Object) tReturn);
            this.caseConditions.add(caseCondition);
            return caseCondition;
        }
        throw new IllegalStateException("When not using the efficient CASE method, you must pass in the SQLOperator as a parameter");
    }

    @NonNull
    public CaseCondition<TReturn> when(@NonNull IProperty iProperty) {
        if (this.efficientCase) {
            CaseCondition<TReturn> caseCondition = new CaseCondition(this, iProperty);
            this.caseConditions.add(caseCondition);
            return caseCondition;
        }
        throw new IllegalStateException("When not using the efficient CASE method, you must pass in the SQLOperator as a parameter");
    }

    @NonNull
    public Case<TReturn> _else(@Nullable TReturn tReturn) {
        this.elseValue = tReturn;
        this.elseSpecified = true;
        return this;
    }

    @NonNull
    public Property<Case<TReturn>> end(@Nullable String str) {
        this.endSpecified = true;
        if (str != null) {
            this.columnName = QueryBuilder.quoteIfNeeded(str);
        }
        return new Property(null, NameAlias.rawBuilder(getQuery()).build());
    }

    @NonNull
    public Property<Case<TReturn>> end() {
        return end(null);
    }

    @NonNull
    public Operator endAsOperator() {
        return Operator.op(end().getNameAlias());
    }

    boolean isEfficientCase() {
        return this.efficientCase;
    }

    public String getQuery() {
        QueryBuilder queryBuilder = new QueryBuilder(" CASE");
        if (isEfficientCase()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" ");
            stringBuilder.append(BaseOperator.convertValueToString(this.caseColumn, false));
            queryBuilder.append(stringBuilder.toString());
        }
        queryBuilder.append(QueryBuilder.join((CharSequence) "", this.caseConditions));
        if (this.elseSpecified) {
            queryBuilder.append(" ELSE ").append(BaseOperator.convertValueToString(this.elseValue, false));
        }
        if (this.endSpecified) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(" END ");
            stringBuilder.append(this.columnName != null ? this.columnName : "");
            queryBuilder.append(stringBuilder.toString());
        }
        return queryBuilder.getQuery();
    }
}
