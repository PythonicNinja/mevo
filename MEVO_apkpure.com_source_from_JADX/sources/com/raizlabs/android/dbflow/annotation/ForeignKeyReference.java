package com.raizlabs.android.dbflow.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface ForeignKeyReference {
    String columnName();

    String foreignKeyColumnName();
}
