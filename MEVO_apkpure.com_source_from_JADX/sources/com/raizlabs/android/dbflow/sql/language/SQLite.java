package com.raizlabs.android.dbflow.sql.language;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.language.property.Property;

public class SQLite {
    @NonNull
    public static Select select(IProperty... iPropertyArr) {
        return new Select(iPropertyArr);
    }

    @NonNull
    public static Select selectCountOf(IProperty... iPropertyArr) {
        return new Select(Method.count(iPropertyArr));
    }

    @NonNull
    public static <TModel> Update<TModel> update(@NonNull Class<TModel> cls) {
        return new Update(cls);
    }

    @NonNull
    public static <TModel> Insert<TModel> insert(@NonNull Class<TModel> cls) {
        return new Insert(cls);
    }

    @NonNull
    public static Delete delete() {
        return new Delete();
    }

    @NonNull
    public static <TModel> From<TModel> delete(@NonNull Class<TModel> cls) {
        return delete().from(cls);
    }

    @NonNull
    public static <TModel> Index<TModel> index(@NonNull String str) {
        return new Index(str);
    }

    @NonNull
    public static Trigger createTrigger(@NonNull String str) {
        return Trigger.create(str);
    }

    @NonNull
    public static <TReturn> CaseCondition<TReturn> caseWhen(@NonNull SQLOperator sQLOperator) {
        return new Case().when(sQLOperator);
    }

    @NonNull
    public static <TReturn> Case<TReturn> _case(@NonNull Property<TReturn> property) {
        return new Case(property);
    }

    @NonNull
    public static <TReturn> Case<TReturn> _case(@NonNull IProperty iProperty) {
        return new Case(iProperty);
    }
}
