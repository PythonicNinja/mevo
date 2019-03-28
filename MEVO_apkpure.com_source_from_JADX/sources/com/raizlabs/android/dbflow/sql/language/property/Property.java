package com.raizlabs.android.dbflow.sql.language.property;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.BaseModelQueriable;
import com.raizlabs.android.dbflow.sql.language.IConditional;
import com.raizlabs.android.dbflow.sql.language.IOperator;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.NameAlias.Builder;
import com.raizlabs.android.dbflow.sql.language.Operator;
import com.raizlabs.android.dbflow.sql.language.Operator.Between;
import com.raizlabs.android.dbflow.sql.language.Operator.In;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;
import com.raizlabs.android.dbflow.sql.language.OrderBy;
import java.util.Collection;

public class Property<T> implements IProperty<Property<T>>, IConditional, IOperator<T> {
    public static final Property<?> ALL_PROPERTY = new Property<Object>(null, Operation.MULTIPLY) {
        @NonNull
        public /* bridge */ /* synthetic */ IProperty as(@NonNull String str) {
            return super.as(str);
        }

        @NonNull
        public /* bridge */ /* synthetic */ IProperty concatenate(@NonNull IProperty iProperty) {
            return super.concatenate(iProperty);
        }

        @NonNull
        public /* bridge */ /* synthetic */ IProperty distinct() {
            return super.distinct();
        }

        @NonNull
        public /* bridge */ /* synthetic */ IProperty div(@NonNull IProperty iProperty) {
            return super.div(iProperty);
        }

        @NonNull
        public /* bridge */ /* synthetic */ IProperty minus(@NonNull IProperty iProperty) {
            return super.minus(iProperty);
        }

        @NonNull
        public /* bridge */ /* synthetic */ IProperty plus(@NonNull IProperty iProperty) {
            return super.plus(iProperty);
        }

        @NonNull
        public /* bridge */ /* synthetic */ IProperty rem(@NonNull IProperty iProperty) {
            return super.rem(iProperty);
        }

        public /* bridge */ /* synthetic */ IProperty times(@NonNull IProperty iProperty) {
            return super.times(iProperty);
        }

        @NonNull
        public /* bridge */ /* synthetic */ IProperty withTable() {
            return super.withTable();
        }

        @NonNull
        public /* bridge */ /* synthetic */ IProperty withTable(@NonNull NameAlias nameAlias) {
            return super.withTable(nameAlias);
        }

        public String toString() {
            return this.nameAlias.nameRaw();
        }
    };
    public static final Property<?> WILDCARD = new Property(null, NameAlias.rawBuilder(Operation.EMPTY_PARAM).build());
    protected NameAlias nameAlias;
    @Nullable
    final Class<?> table;

    public Property(@Nullable Class<?> cls, @NonNull NameAlias nameAlias) {
        this.table = cls;
        this.nameAlias = nameAlias;
    }

    public Property(@Nullable Class<?> cls, @Nullable String str) {
        this.table = cls;
        if (str != null) {
            this.nameAlias = new Builder(str).build();
        }
    }

    public Property(@Nullable Class<?> cls, @NonNull String str, @NonNull String str2) {
        this((Class) cls, NameAlias.builder(str).as(str2).build());
    }

    @NonNull
    public Property<T> withTable() {
        return withTable(new Builder(FlowManager.getTableName(this.table)).build());
    }

    @NonNull
    public NameAlias getNameAlias() {
        return this.nameAlias;
    }

    public String getQuery() {
        return getNameAlias().getQuery();
    }

    @NonNull
    public String getCursorKey() {
        return getNameAlias().getQuery();
    }

    @NonNull
    public String getDefinition() {
        return getNameAlias().getFullQuery();
    }

    public String toString() {
        return getNameAlias().toString();
    }

    @NonNull
    public Operator is(@NonNull IConditional iConditional) {
        return getCondition().is(iConditional);
    }

    @NonNull
    public Operator eq(@NonNull IConditional iConditional) {
        return getCondition().eq(iConditional);
    }

    @NonNull
    public Operator isNot(@NonNull IConditional iConditional) {
        return getCondition().isNot(iConditional);
    }

    @NonNull
    public Operator notEq(@NonNull IConditional iConditional) {
        return getCondition().notEq(iConditional);
    }

    @NonNull
    public Operator like(@NonNull IConditional iConditional) {
        return getCondition().like(iConditional);
    }

    @NonNull
    public Operator glob(@NonNull IConditional iConditional) {
        return getCondition().glob(iConditional);
    }

    @NonNull
    public Operator<T> like(@NonNull String str) {
        return getCondition().like(str);
    }

    @NonNull
    public Operator<T> notLike(@NonNull String str) {
        return getCondition().notLike(str);
    }

    @NonNull
    public Operator<T> glob(@NonNull String str) {
        return getCondition().glob(str);
    }

    @NonNull
    public Operator greaterThan(@NonNull IConditional iConditional) {
        return getCondition().greaterThan(iConditional);
    }

    @NonNull
    public Operator greaterThanOrEq(@NonNull IConditional iConditional) {
        return getCondition().greaterThanOrEq(iConditional);
    }

    @NonNull
    public Operator lessThan(@NonNull IConditional iConditional) {
        return getCondition().lessThan(iConditional);
    }

    @NonNull
    public Operator lessThanOrEq(@NonNull IConditional iConditional) {
        return getCondition().lessThanOrEq(iConditional);
    }

    @NonNull
    public Between between(@NonNull IConditional iConditional) {
        return getCondition().between(iConditional);
    }

    @NonNull
    public In in(@NonNull IConditional iConditional, @NonNull IConditional... iConditionalArr) {
        return getCondition().in(iConditional, iConditionalArr);
    }

    @NonNull
    public In notIn(@NonNull IConditional iConditional, @NonNull IConditional... iConditionalArr) {
        return getCondition().notIn(iConditional, iConditionalArr);
    }

    @NonNull
    public Operator is(@NonNull BaseModelQueriable baseModelQueriable) {
        return getCondition().is(baseModelQueriable);
    }

    @NonNull
    public Operator isNull() {
        return getCondition().isNull();
    }

    @NonNull
    public Operator eq(@NonNull BaseModelQueriable baseModelQueriable) {
        return getCondition().eq(baseModelQueriable);
    }

    @NonNull
    public Operator isNot(@NonNull BaseModelQueriable baseModelQueriable) {
        return getCondition().isNot(baseModelQueriable);
    }

    @NonNull
    public Operator isNotNull() {
        return getCondition().isNotNull();
    }

    @NonNull
    public Operator notEq(@NonNull BaseModelQueriable baseModelQueriable) {
        return getCondition().notEq(baseModelQueriable);
    }

    @NonNull
    public Operator like(@NonNull BaseModelQueriable baseModelQueriable) {
        return getCondition().like(baseModelQueriable);
    }

    @NonNull
    public Operator notLike(@NonNull IConditional iConditional) {
        return getCondition().notLike(iConditional);
    }

    @NonNull
    public Operator notLike(@NonNull BaseModelQueriable baseModelQueriable) {
        return getCondition().notLike(baseModelQueriable);
    }

    @NonNull
    public Operator glob(@NonNull BaseModelQueriable baseModelQueriable) {
        return getCondition().glob(baseModelQueriable);
    }

    @NonNull
    public Operator greaterThan(@NonNull BaseModelQueriable baseModelQueriable) {
        return getCondition().greaterThan(baseModelQueriable);
    }

    @NonNull
    public Operator greaterThanOrEq(@NonNull BaseModelQueriable baseModelQueriable) {
        return getCondition().greaterThanOrEq(baseModelQueriable);
    }

    @NonNull
    public Operator lessThan(@NonNull BaseModelQueriable baseModelQueriable) {
        return getCondition().lessThan(baseModelQueriable);
    }

    @NonNull
    public Operator lessThanOrEq(@NonNull BaseModelQueriable baseModelQueriable) {
        return getCondition().lessThanOrEq(baseModelQueriable);
    }

    @NonNull
    public Between between(@NonNull BaseModelQueriable baseModelQueriable) {
        return getCondition().between(baseModelQueriable);
    }

    @NonNull
    public In in(@NonNull BaseModelQueriable baseModelQueriable, @NonNull BaseModelQueriable... baseModelQueriableArr) {
        return getCondition().in(baseModelQueriable, baseModelQueriableArr);
    }

    @NonNull
    public In notIn(@NonNull BaseModelQueriable baseModelQueriable, @NonNull BaseModelQueriable... baseModelQueriableArr) {
        return getCondition().notIn(baseModelQueriable, baseModelQueriableArr);
    }

    @NonNull
    public Operator concatenate(@NonNull IConditional iConditional) {
        return getCondition().concatenate(iConditional);
    }

    @NonNull
    public Operator plus(@NonNull BaseModelQueriable baseModelQueriable) {
        return getCondition().plus(baseModelQueriable);
    }

    @NonNull
    public Operator minus(@NonNull BaseModelQueriable baseModelQueriable) {
        return getCondition().minus(baseModelQueriable);
    }

    @NonNull
    public Operator div(@NonNull BaseModelQueriable baseModelQueriable) {
        return getCondition().div(baseModelQueriable);
    }

    @NonNull
    public Operator times(@NonNull BaseModelQueriable baseModelQueriable) {
        return getCondition().times(baseModelQueriable);
    }

    @NonNull
    public Operator rem(@NonNull BaseModelQueriable baseModelQueriable) {
        return getCondition().rem(baseModelQueriable);
    }

    @NonNull
    public Class<?> getTable() {
        return this.table;
    }

    @NonNull
    public Property<T> plus(@NonNull IProperty iProperty) {
        return new Property(this.table, NameAlias.joinNames(Operation.PLUS, this.nameAlias.fullName(), iProperty.toString()));
    }

    @NonNull
    public Property<T> minus(@NonNull IProperty iProperty) {
        return new Property(this.table, NameAlias.joinNames(Operation.MINUS, this.nameAlias.fullName(), iProperty.toString()));
    }

    @NonNull
    public Property<T> div(@NonNull IProperty iProperty) {
        return new Property(this.table, NameAlias.joinNames(Operation.DIVISION, this.nameAlias.fullName(), iProperty.toString()));
    }

    public Property<T> times(@NonNull IProperty iProperty) {
        return new Property(this.table, NameAlias.joinNames(Operation.MULTIPLY, this.nameAlias.fullName(), iProperty.toString()));
    }

    @NonNull
    public Property<T> rem(@NonNull IProperty iProperty) {
        return new Property(this.table, NameAlias.joinNames(Operation.MOD, this.nameAlias.fullName(), iProperty.toString()));
    }

    @NonNull
    public Property<T> concatenate(@NonNull IProperty iProperty) {
        return new Property(this.table, NameAlias.joinNames(Operation.CONCATENATE, this.nameAlias.fullName(), iProperty.toString()));
    }

    @NonNull
    public Property<T> as(@NonNull String str) {
        return new Property(this.table, getNameAlias().newBuilder().as(str).build());
    }

    @NonNull
    public Property<T> distinct() {
        return new Property(this.table, getDistinctAliasName());
    }

    @NonNull
    public Property<T> withTable(@NonNull NameAlias nameAlias) {
        return new Property(this.table, getNameAlias().newBuilder().withTable(nameAlias.getQuery()).build());
    }

    @NonNull
    public Operator<T> is(T t) {
        return getCondition().is((Object) t);
    }

    @NonNull
    public Operator<T> eq(T t) {
        return getCondition().eq((Object) t);
    }

    @NonNull
    public Operator<T> isNot(T t) {
        return getCondition().isNot((Object) t);
    }

    @NonNull
    public Operator<T> notEq(T t) {
        return getCondition().notEq((Object) t);
    }

    @NonNull
    public Operator<T> greaterThan(@NonNull T t) {
        return getCondition().greaterThan((Object) t);
    }

    @NonNull
    public Operator<T> greaterThanOrEq(@NonNull T t) {
        return getCondition().greaterThanOrEq((Object) t);
    }

    @NonNull
    public Operator<T> lessThan(@NonNull T t) {
        return getCondition().lessThan((Object) t);
    }

    @NonNull
    public Operator<T> lessThanOrEq(@NonNull T t) {
        return getCondition().lessThanOrEq((Object) t);
    }

    @NonNull
    public Between<T> between(@NonNull T t) {
        return getCondition().between((Object) t);
    }

    @NonNull
    public In<T> in(@NonNull T t, T... tArr) {
        return getCondition().in((Object) t, (Object[]) tArr);
    }

    @NonNull
    public In<T> notIn(@NonNull T t, T... tArr) {
        return getCondition().notIn((Object) t, (Object[]) tArr);
    }

    @NonNull
    public In<T> in(@NonNull Collection<T> collection) {
        return getCondition().in(collection);
    }

    @NonNull
    public In<T> notIn(@NonNull Collection<T> collection) {
        return getCondition().notIn(collection);
    }

    @NonNull
    public Operator<T> concatenate(T t) {
        return getCondition().concatenate((Object) t);
    }

    @NonNull
    public Operator<T> plus(@NonNull T t) {
        return getCondition().plus((Object) t);
    }

    @NonNull
    public Operator<T> minus(@NonNull T t) {
        return getCondition().minus((Object) t);
    }

    @NonNull
    public Operator<T> div(@NonNull T t) {
        return getCondition().div((Object) t);
    }

    public Operator<T> times(@NonNull T t) {
        return getCondition().times((Object) t);
    }

    @NonNull
    public Operator<T> rem(@NonNull T t) {
        return getCondition().rem((Object) t);
    }

    @NonNull
    public OrderBy asc() {
        return OrderBy.fromProperty(this).ascending();
    }

    @NonNull
    public OrderBy desc() {
        return OrderBy.fromProperty(this).descending();
    }

    protected NameAlias getDistinctAliasName() {
        return getNameAlias().newBuilder().distinct().build();
    }

    @NonNull
    protected Operator<T> getCondition() {
        return Operator.op(getNameAlias());
    }
}
