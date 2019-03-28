package com.raizlabs.android.dbflow.sql.language.property;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.OrderBy;

public interface IProperty<P extends IProperty> extends Query {
    @NonNull
    P as(@NonNull String str);

    @NonNull
    OrderBy asc();

    @NonNull
    P concatenate(@NonNull IProperty iProperty);

    @NonNull
    OrderBy desc();

    @NonNull
    P distinct();

    @NonNull
    P div(@NonNull IProperty iProperty);

    @NonNull
    String getCursorKey();

    @NonNull
    NameAlias getNameAlias();

    @NonNull
    Class<?> getTable();

    @NonNull
    P minus(@NonNull IProperty iProperty);

    @NonNull
    P plus(@NonNull IProperty iProperty);

    @NonNull
    P rem(@NonNull IProperty iProperty);

    P times(@NonNull IProperty iProperty);

    @NonNull
    P withTable();

    @NonNull
    P withTable(@NonNull NameAlias nameAlias);
}
