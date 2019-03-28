package com.raizlabs.android.dbflow.sql.language;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.QueryBuilder;

public class Delete implements Query {
    public static <TModel> void table(@NonNull Class<TModel> cls, SQLOperator... sQLOperatorArr) {
        new Delete().from(cls).where(sQLOperatorArr).executeUpdateDelete();
    }

    public static void tables(Class<?>... clsArr) {
        for (Class table : clsArr) {
            table(table, new SQLOperator[0]);
        }
    }

    @NonNull
    public <TModel> From<TModel> from(@NonNull Class<TModel> cls) {
        return new From(this, cls);
    }

    public String getQuery() {
        return new QueryBuilder().append("DELETE").appendSpace().getQuery();
    }
}
