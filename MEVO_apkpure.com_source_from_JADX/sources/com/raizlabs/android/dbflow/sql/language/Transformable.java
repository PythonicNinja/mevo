package com.raizlabs.android.dbflow.sql.language;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import java.util.List;

public interface Transformable<T> {
    @NonNull
    Where<T> groupBy(NameAlias... nameAliasArr);

    @NonNull
    Where<T> groupBy(IProperty... iPropertyArr);

    @NonNull
    Where<T> having(SQLOperator... sQLOperatorArr);

    @NonNull
    Where<T> limit(int i);

    @NonNull
    Where<T> offset(int i);

    @NonNull
    Where<T> orderBy(@NonNull NameAlias nameAlias, boolean z);

    @NonNull
    Where<T> orderBy(@NonNull OrderBy orderBy);

    @NonNull
    Where<T> orderBy(@NonNull IProperty iProperty, boolean z);

    @NonNull
    Where<T> orderByAll(@NonNull List<OrderBy> list);
}
