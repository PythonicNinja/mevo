package com.raizlabs.android.dbflow.sql.language;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class OperatorGroup extends BaseOperator implements Query, Iterable<SQLOperator> {
    private boolean allCommaSeparated;
    @NonNull
    private final List<SQLOperator> conditionsList;
    private boolean isChanged;
    private QueryBuilder query;
    private boolean useParenthesis;

    @NonNull
    public static OperatorGroup clause() {
        return new OperatorGroup();
    }

    @NonNull
    public static OperatorGroup clause(SQLOperator... sQLOperatorArr) {
        return new OperatorGroup().andAll(sQLOperatorArr);
    }

    public static OperatorGroup nonGroupingClause() {
        return new OperatorGroup().setUseParenthesis(false);
    }

    public static OperatorGroup nonGroupingClause(SQLOperator... sQLOperatorArr) {
        return new OperatorGroup().setUseParenthesis(false).andAll(sQLOperatorArr);
    }

    protected OperatorGroup(NameAlias nameAlias) {
        super(nameAlias);
        this.conditionsList = new ArrayList();
        this.useParenthesis = true;
        this.separator = Operation.AND;
    }

    protected OperatorGroup() {
        this(null);
    }

    @NonNull
    public OperatorGroup setAllCommaSeparated(boolean z) {
        this.allCommaSeparated = z;
        this.isChanged = true;
        return this;
    }

    @NonNull
    public OperatorGroup setUseParenthesis(boolean z) {
        this.useParenthesis = z;
        this.isChanged = true;
        return this;
    }

    @NonNull
    public OperatorGroup or(SQLOperator sQLOperator) {
        return operator(Operation.OR, sQLOperator);
    }

    @NonNull
    public OperatorGroup and(SQLOperator sQLOperator) {
        return operator(Operation.AND, sQLOperator);
    }

    @NonNull
    public OperatorGroup andAll(SQLOperator... sQLOperatorArr) {
        for (SQLOperator and : sQLOperatorArr) {
            and(and);
        }
        return this;
    }

    @NonNull
    public OperatorGroup andAll(Collection<SQLOperator> collection) {
        for (SQLOperator and : collection) {
            and(and);
        }
        return this;
    }

    @NonNull
    public OperatorGroup orAll(SQLOperator... sQLOperatorArr) {
        for (SQLOperator or : sQLOperatorArr) {
            or(or);
        }
        return this;
    }

    @NonNull
    public OperatorGroup orAll(Collection<SQLOperator> collection) {
        for (SQLOperator or : collection) {
            or(or);
        }
        return this;
    }

    @NonNull
    private OperatorGroup operator(String str, @Nullable SQLOperator sQLOperator) {
        if (sQLOperator != null) {
            setPreviousSeparator(str);
            this.conditionsList.add(sQLOperator);
            this.isChanged = true;
        }
        return this;
    }

    public void appendConditionToQuery(@NonNull QueryBuilder queryBuilder) {
        int size = this.conditionsList.size();
        if (this.useParenthesis && size > 0) {
            queryBuilder.append("(");
        }
        int i = 0;
        while (i < size) {
            SQLOperator sQLOperator = (SQLOperator) this.conditionsList.get(i);
            sQLOperator.appendConditionToQuery(queryBuilder);
            if (!this.allCommaSeparated && sQLOperator.hasSeparator() && i < size - 1) {
                queryBuilder.appendSpaceSeparated(sQLOperator.separator());
            } else if (i < size - 1) {
                queryBuilder.append(", ");
            }
            i++;
        }
        if (this.useParenthesis && size > 0) {
            queryBuilder.append(")");
        }
    }

    private void setPreviousSeparator(String str) {
        if (this.conditionsList.size() > 0) {
            ((SQLOperator) this.conditionsList.get(this.conditionsList.size() - 1)).separator(str);
        }
    }

    public String getQuery() {
        if (this.isChanged) {
            this.query = getQuerySafe();
        }
        return this.query == null ? "" : this.query.toString();
    }

    public String toString() {
        return getQuerySafe().toString();
    }

    public int size() {
        return this.conditionsList.size();
    }

    @NonNull
    public List<SQLOperator> getConditions() {
        return this.conditionsList;
    }

    public Iterator<SQLOperator> iterator() {
        return this.conditionsList.iterator();
    }

    private QueryBuilder getQuerySafe() {
        QueryBuilder queryBuilder = new QueryBuilder();
        appendConditionToQuery(queryBuilder);
        return queryBuilder;
    }
}
