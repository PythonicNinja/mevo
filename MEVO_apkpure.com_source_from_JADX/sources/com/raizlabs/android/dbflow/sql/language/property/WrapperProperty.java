package com.raizlabs.android.dbflow.sql.language.property;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.sql.language.NameAlias;

public class WrapperProperty<T, V> extends Property<V> {
    private WrapperProperty<V, T> databaseProperty;

    public WrapperProperty(@NonNull Class<?> cls, @NonNull NameAlias nameAlias) {
        super((Class) cls, nameAlias);
    }

    public WrapperProperty(@NonNull Class<?> cls, @NonNull String str) {
        super((Class) cls, str);
    }

    @NonNull
    public Property<T> invertProperty() {
        if (this.databaseProperty == null) {
            this.databaseProperty = new WrapperProperty(this.table, this.nameAlias);
        }
        return this.databaseProperty;
    }
}
