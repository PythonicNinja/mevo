package com.raizlabs.android.dbflow.sql.language;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.annotation.Collate;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowLog.Level;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.converter.TypeConverter;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Operator<T> extends BaseOperator implements IOperator<T> {
    private boolean convertToDB;
    private TypeConverter typeConverter;

    public static class Operation {
        public static final String AND = "AND";
        public static final String BETWEEN = "BETWEEN";
        public static final String CONCATENATE = "||";
        public static final String DIVISION = "/";
        public static final String EMPTY_PARAM = "?";
        public static final String EQUALS = "=";
        public static final String GLOB = "GLOB";
        public static final String GREATER_THAN = ">";
        public static final String GREATER_THAN_OR_EQUALS = ">=";
        public static final String IN = "IN";
        public static final String IS_NOT_NULL = "IS NOT NULL";
        public static final String IS_NULL = "IS NULL";
        public static final String LESS_THAN = "<";
        public static final String LESS_THAN_OR_EQUALS = "<=";
        public static final String LIKE = "LIKE";
        public static final String MINUS = "-";
        public static final String MOD = "%";
        public static final String MULTIPLY = "*";
        public static final String NOT_EQUALS = "!=";
        public static final String NOT_IN = "NOT IN";
        public static final String NOT_LIKE = "NOT LIKE";
        public static final String OR = "OR";
        public static final String PLUS = "+";
    }

    public static class Between<T> extends BaseOperator implements Query {
        @Nullable
        private T secondValue;

        private Between(Operator<T> operator, T t) {
            super(operator.nameAlias);
            this.operation = String.format(" %1s ", new Object[]{Operation.BETWEEN});
            this.value = t;
            this.isValueSet = true;
            this.postArg = operator.postArgument();
        }

        @NonNull
        public Between<T> and(@Nullable T t) {
            this.secondValue = t;
            return this;
        }

        @Nullable
        public T secondValue() {
            return this.secondValue;
        }

        public void appendConditionToQuery(@NonNull QueryBuilder queryBuilder) {
            queryBuilder.append(columnName()).append(operation()).append(convertObjectToString(value(), true)).appendSpaceSeparated(Operation.AND).append(convertObjectToString(secondValue(), true)).appendSpace().appendOptional(postArgument());
        }

        public String getQuery() {
            QueryBuilder queryBuilder = new QueryBuilder();
            appendConditionToQuery(queryBuilder);
            return queryBuilder.getQuery();
        }
    }

    public static class In<T> extends BaseOperator implements Query {
        private List<T> inArguments;

        @SafeVarargs
        private In(Operator<T> operator, T t, boolean z, T... tArr) {
            super(operator.columnAlias());
            this.inArguments = new ArrayList();
            this.inArguments.add(t);
            Collections.addAll(this.inArguments, tArr);
            operator = " %1s ";
            t = new Object[1];
            t[null] = z ? Operation.IN : Operation.NOT_IN;
            this.operation = String.format(operator, t);
        }

        private In(Operator<T> operator, Collection<T> collection, boolean z) {
            super(operator.columnAlias());
            this.inArguments = new ArrayList();
            this.inArguments.addAll(collection);
            operator = " %1s ";
            collection = new Object[1];
            collection[0] = z ? Operation.IN : Operation.NOT_IN;
            this.operation = String.format(operator, collection);
        }

        @NonNull
        public In<T> and(@Nullable T t) {
            this.inArguments.add(t);
            return this;
        }

        public void appendConditionToQuery(@NonNull QueryBuilder queryBuilder) {
            queryBuilder.append(columnName()).append(operation()).append("(").append(BaseOperator.joinArguments(",", this.inArguments, this)).append(")");
        }

        public String getQuery() {
            QueryBuilder queryBuilder = new QueryBuilder();
            appendConditionToQuery(queryBuilder);
            return queryBuilder.getQuery();
        }
    }

    public static String convertValueToString(Object obj) {
        return BaseOperator.convertValueToString(obj, false);
    }

    @NonNull
    public static <T> Operator<T> op(NameAlias nameAlias) {
        return new Operator(nameAlias);
    }

    @NonNull
    public static <T> Operator<T> op(NameAlias nameAlias, TypeConverter typeConverter, boolean z) {
        return new Operator(nameAlias, typeConverter, z);
    }

    Operator(NameAlias nameAlias) {
        super(nameAlias);
    }

    Operator(NameAlias nameAlias, TypeConverter typeConverter, boolean z) {
        super(nameAlias);
        this.typeConverter = typeConverter;
        this.convertToDB = z;
    }

    Operator(Operator operator) {
        super(operator.nameAlias);
        this.typeConverter = operator.typeConverter;
        this.convertToDB = operator.convertToDB;
        this.value = operator.value;
    }

    public void appendConditionToQuery(@NonNull QueryBuilder queryBuilder) {
        queryBuilder.append(columnName()).append(operation());
        if (this.isValueSet) {
            queryBuilder.append(convertObjectToString(value(), true));
        }
        if (postArgument() != null) {
            queryBuilder.appendSpace().append(postArgument());
        }
    }

    @NonNull
    public Operator<T> is(T t) {
        this.operation = Operation.EQUALS;
        return value(t);
    }

    @NonNull
    public Operator<T> eq(T t) {
        return is((Object) t);
    }

    @NonNull
    public Operator<T> isNot(T t) {
        this.operation = Operation.NOT_EQUALS;
        return value(t);
    }

    @NonNull
    public Operator<T> notEq(T t) {
        return isNot((Object) t);
    }

    @NonNull
    public Operator<T> like(@NonNull String str) {
        this.operation = String.format(" %1s ", new Object[]{Operation.LIKE});
        return value(str);
    }

    @NonNull
    public Operator<T> notLike(@NonNull String str) {
        this.operation = String.format(" %1s ", new Object[]{Operation.NOT_LIKE});
        return value(str);
    }

    @NonNull
    public Operator<T> glob(@NonNull String str) {
        this.operation = String.format(" %1s ", new Object[]{Operation.GLOB});
        return value(str);
    }

    public Operator<T> value(Object obj) {
        this.value = obj;
        this.isValueSet = true;
        return this;
    }

    @NonNull
    public Operator<T> greaterThan(@NonNull T t) {
        this.operation = Operation.GREATER_THAN;
        return value(t);
    }

    @NonNull
    public Operator<T> greaterThanOrEq(@NonNull T t) {
        this.operation = Operation.GREATER_THAN_OR_EQUALS;
        return value(t);
    }

    @NonNull
    public Operator<T> lessThan(@NonNull T t) {
        this.operation = Operation.LESS_THAN;
        return value(t);
    }

    @NonNull
    public Operator<T> lessThanOrEq(@NonNull T t) {
        this.operation = Operation.LESS_THAN_OR_EQUALS;
        return value(t);
    }

    @NonNull
    public Operator<T> plus(@NonNull T t) {
        return assignValueOp(t, Operation.PLUS);
    }

    @NonNull
    public Operator<T> minus(@NonNull T t) {
        return assignValueOp(t, Operation.MINUS);
    }

    @NonNull
    public Operator<T> div(@NonNull T t) {
        return assignValueOp(t, Operation.DIVISION);
    }

    public Operator<T> times(@NonNull T t) {
        return assignValueOp(t, Operation.MULTIPLY);
    }

    @NonNull
    public Operator<T> rem(@NonNull T t) {
        return assignValueOp(t, Operation.MOD);
    }

    @NonNull
    public Operator<T> operation(String str) {
        this.operation = str;
        return this;
    }

    @NonNull
    public Operator<T> collate(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("COLLATE ");
        stringBuilder.append(str);
        this.postArg = stringBuilder.toString();
        return this;
    }

    @NonNull
    public Operator<T> collate(Collate collate) {
        if (collate.equals(Collate.NONE)) {
            this.postArg = null;
        } else {
            collate(collate.name());
        }
        return this;
    }

    @NonNull
    public Operator<T> postfix(String str) {
        this.postArg = str;
        return this;
    }

    @NonNull
    public Operator<T> isNull() {
        this.operation = String.format(" %1s ", new Object[]{Operation.IS_NULL});
        return this;
    }

    @NonNull
    public Operator<T> isNotNull() {
        this.operation = String.format(" %1s ", new Object[]{Operation.IS_NOT_NULL});
        return this;
    }

    @NonNull
    public Operator<T> separator(@NonNull String str) {
        this.separator = str;
        return this;
    }

    @NonNull
    public Operator is(@NonNull IConditional iConditional) {
        return assignValueOp(iConditional, Operation.EQUALS);
    }

    @NonNull
    public Operator eq(@NonNull IConditional iConditional) {
        return assignValueOp(iConditional, Operation.EQUALS);
    }

    @NonNull
    public Operator isNot(@NonNull IConditional iConditional) {
        return assignValueOp(iConditional, Operation.NOT_EQUALS);
    }

    @NonNull
    public Operator notEq(@NonNull IConditional iConditional) {
        return assignValueOp(iConditional, Operation.NOT_EQUALS);
    }

    @NonNull
    public Operator<T> like(@NonNull IConditional iConditional) {
        return like(iConditional.getQuery());
    }

    @NonNull
    public Operator<T> glob(@NonNull IConditional iConditional) {
        return glob(iConditional.getQuery());
    }

    @NonNull
    public Operator<T> greaterThan(@NonNull IConditional iConditional) {
        return assignValueOp(iConditional, Operation.GREATER_THAN);
    }

    @NonNull
    public Operator<T> greaterThanOrEq(@NonNull IConditional iConditional) {
        return assignValueOp(iConditional, Operation.GREATER_THAN_OR_EQUALS);
    }

    @NonNull
    public Operator<T> lessThan(@NonNull IConditional iConditional) {
        return assignValueOp(iConditional, Operation.LESS_THAN);
    }

    @NonNull
    public Operator<T> lessThanOrEq(@NonNull IConditional iConditional) {
        return assignValueOp(iConditional, Operation.LESS_THAN_OR_EQUALS);
    }

    @NonNull
    public Between between(@NonNull IConditional iConditional) {
        return new Between(iConditional);
    }

    @NonNull
    public In in(@NonNull IConditional iConditional, @NonNull IConditional... iConditionalArr) {
        return new In(iConditional, true, iConditionalArr);
    }

    @NonNull
    public In notIn(@NonNull IConditional iConditional, @NonNull IConditional... iConditionalArr) {
        return new In(iConditional, false, iConditionalArr);
    }

    @NonNull
    public In notIn(@NonNull BaseModelQueriable baseModelQueriable, @NonNull BaseModelQueriable[] baseModelQueriableArr) {
        return new In(baseModelQueriable, false, (Object[]) baseModelQueriableArr);
    }

    @NonNull
    public Operator is(@NonNull BaseModelQueriable baseModelQueriable) {
        return assignValueOp(baseModelQueriable, Operation.EQUALS);
    }

    @NonNull
    public Operator eq(@NonNull BaseModelQueriable baseModelQueriable) {
        return assignValueOp(baseModelQueriable, Operation.EQUALS);
    }

    @NonNull
    public Operator isNot(@NonNull BaseModelQueriable baseModelQueriable) {
        return assignValueOp(baseModelQueriable, Operation.NOT_EQUALS);
    }

    @NonNull
    public Operator notEq(@NonNull BaseModelQueriable baseModelQueriable) {
        return assignValueOp(baseModelQueriable, Operation.NOT_EQUALS);
    }

    @NonNull
    public Operator<T> like(@NonNull BaseModelQueriable baseModelQueriable) {
        return assignValueOp(baseModelQueriable, Operation.LIKE);
    }

    @NonNull
    public Operator notLike(@NonNull IConditional iConditional) {
        return assignValueOp(iConditional, Operation.NOT_LIKE);
    }

    @NonNull
    public Operator notLike(@NonNull BaseModelQueriable baseModelQueriable) {
        return assignValueOp(baseModelQueriable, Operation.NOT_LIKE);
    }

    @NonNull
    public Operator<T> glob(@NonNull BaseModelQueriable baseModelQueriable) {
        return assignValueOp(baseModelQueriable, Operation.GLOB);
    }

    @NonNull
    public Operator<T> greaterThan(@NonNull BaseModelQueriable baseModelQueriable) {
        return assignValueOp(baseModelQueriable, Operation.GREATER_THAN);
    }

    @NonNull
    public Operator<T> greaterThanOrEq(@NonNull BaseModelQueriable baseModelQueriable) {
        return assignValueOp(baseModelQueriable, Operation.GREATER_THAN_OR_EQUALS);
    }

    @NonNull
    public Operator<T> lessThan(@NonNull BaseModelQueriable baseModelQueriable) {
        return assignValueOp(baseModelQueriable, Operation.LESS_THAN);
    }

    @NonNull
    public Operator<T> lessThanOrEq(@NonNull BaseModelQueriable baseModelQueriable) {
        return assignValueOp(baseModelQueriable, Operation.LESS_THAN_OR_EQUALS);
    }

    @NonNull
    public Operator plus(IConditional iConditional) {
        return assignValueOp(iConditional, Operation.PLUS);
    }

    @NonNull
    public Operator minus(IConditional iConditional) {
        return assignValueOp(iConditional, Operation.MINUS);
    }

    @NonNull
    public Operator div(IConditional iConditional) {
        return assignValueOp(iConditional, Operation.DIVISION);
    }

    @NonNull
    public Operator times(IConditional iConditional) {
        return assignValueOp(iConditional, Operation.MULTIPLY);
    }

    @NonNull
    public Operator rem(IConditional iConditional) {
        return assignValueOp(iConditional, Operation.MOD);
    }

    @NonNull
    public Operator plus(@NonNull BaseModelQueriable baseModelQueriable) {
        return assignValueOp(baseModelQueriable, Operation.PLUS);
    }

    @NonNull
    public Operator minus(@NonNull BaseModelQueriable baseModelQueriable) {
        return assignValueOp(baseModelQueriable, Operation.MINUS);
    }

    @NonNull
    public Operator div(@NonNull BaseModelQueriable baseModelQueriable) {
        return assignValueOp(baseModelQueriable, Operation.DIVISION);
    }

    @NonNull
    public Operator times(@NonNull BaseModelQueriable baseModelQueriable) {
        return assignValueOp(baseModelQueriable, Operation.MULTIPLY);
    }

    @NonNull
    public Operator rem(@NonNull BaseModelQueriable baseModelQueriable) {
        return assignValueOp(baseModelQueriable, Operation.MOD);
    }

    @NonNull
    public Between between(@NonNull BaseModelQueriable baseModelQueriable) {
        return new Between(baseModelQueriable);
    }

    @NonNull
    public In in(@NonNull BaseModelQueriable baseModelQueriable, @NonNull BaseModelQueriable... baseModelQueriableArr) {
        return new In(baseModelQueriable, true, baseModelQueriableArr);
    }

    public String getQuery() {
        QueryBuilder queryBuilder = new QueryBuilder();
        appendConditionToQuery(queryBuilder);
        return queryBuilder.getQuery();
    }

    @NonNull
    public Operator<T> concatenate(Object obj) {
        this.operation = new QueryBuilder(Operation.EQUALS).append(columnName()).toString();
        TypeConverter typeConverter = this.typeConverter;
        if (typeConverter == null && obj != null) {
            typeConverter = FlowManager.getTypeConverterForClass(obj.getClass());
        }
        if (typeConverter != null && this.convertToDB) {
            obj = typeConverter.getDBValue(obj);
        }
        if (!((obj instanceof String) || (obj instanceof IOperator))) {
            if (!(obj instanceof Character)) {
                if (obj instanceof Number) {
                    this.operation = String.format("%1s %1s ", new Object[]{this.operation, Operation.PLUS});
                    this.value = obj;
                    this.isValueSet = true;
                    return this;
                }
                String str = "Cannot concatenate the %1s";
                Object[] objArr = new Object[1];
                if (obj != null) {
                    obj = obj.getClass();
                } else {
                    obj = "null";
                }
                objArr[0] = obj;
                throw new IllegalArgumentException(String.format(str, objArr));
            }
        }
        this.operation = String.format("%1s %1s ", new Object[]{this.operation, Operation.CONCATENATE});
        this.value = obj;
        this.isValueSet = true;
        return this;
    }

    @NonNull
    public Operator<T> concatenate(@NonNull IConditional iConditional) {
        return concatenate((Object) iConditional);
    }

    @NonNull
    public Between<T> between(@NonNull T t) {
        return new Between(t);
    }

    @SafeVarargs
    @NonNull
    public final In<T> in(@NonNull T t, T... tArr) {
        return new In(t, true, tArr);
    }

    @SafeVarargs
    @NonNull
    public final In<T> notIn(@NonNull T t, T... tArr) {
        return new In(t, false, tArr);
    }

    @NonNull
    public In<T> in(@NonNull Collection<T> collection) {
        return new In((Collection) collection, true);
    }

    @NonNull
    public In<T> notIn(@NonNull Collection<T> collection) {
        return new In((Collection) collection, false);
    }

    public String convertObjectToString(Object obj, boolean z) {
        if (this.typeConverter == null) {
            return super.convertObjectToString(obj, z);
        }
        try {
            if (this.convertToDB) {
                obj = this.typeConverter.getDBValue(obj);
            }
        } catch (Throwable e) {
            FlowLog.log(Level.W, e);
        }
        return BaseOperator.convertValueToString(obj, z, false);
    }

    private Operator<T> assignValueOp(Object obj, String str) {
        this.operation = str;
        return value(obj);
    }
}
