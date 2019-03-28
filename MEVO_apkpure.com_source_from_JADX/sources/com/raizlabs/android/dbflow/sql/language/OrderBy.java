package com.raizlabs.android.dbflow.sql.language;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.annotation.Collate;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;

public class OrderBy implements Query {
    public static final String ASCENDING = "ASC";
    public static final String DESCENDING = "DESC";
    private Collate collation;
    private NameAlias column;
    private boolean isAscending;
    private String orderByString;

    @NonNull
    public static OrderBy fromProperty(@NonNull IProperty iProperty) {
        return new OrderBy(iProperty.getNameAlias());
    }

    @NonNull
    public static OrderBy fromNameAlias(@NonNull NameAlias nameAlias) {
        return new OrderBy(nameAlias);
    }

    @NonNull
    public static OrderBy fromString(@NonNull String str) {
        return new OrderBy(str);
    }

    OrderBy(NameAlias nameAlias) {
        this.column = nameAlias;
    }

    OrderBy(NameAlias nameAlias, boolean z) {
        this(nameAlias);
        this.isAscending = z;
    }

    OrderBy(String str) {
        this.orderByString = str;
    }

    @NonNull
    public OrderBy ascending() {
        this.isAscending = true;
        return this;
    }

    @NonNull
    public OrderBy descending() {
        this.isAscending = false;
        return this;
    }

    @NonNull
    public OrderBy collate(Collate collate) {
        this.collation = collate;
        return this;
    }

    public String getQuery() {
        if (this.orderByString != null) {
            return this.orderByString;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.column);
        stringBuilder.append(" ");
        if (this.collation != null) {
            stringBuilder.append("COLLATE");
            stringBuilder.append(" ");
            stringBuilder.append(this.collation);
            stringBuilder.append(" ");
        }
        stringBuilder.append(this.isAscending ? ASCENDING : DESCENDING);
        return stringBuilder.toString();
    }

    public String toString() {
        return getQuery();
    }
}
