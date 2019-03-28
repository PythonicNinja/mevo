package com.raizlabs.android.dbflow.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface Table {
    public static final int DEFAULT_CACHE_SIZE = 25;

    boolean allFields() default false;

    boolean assignDefaultValuesFromCursor() default true;

    int cacheSize() default 25;

    boolean cachingEnabled() default false;

    Class<?> database();

    IndexGroup[] indexGroups() default {};

    InheritedColumn[] inheritedColumns() default {};

    InheritedPrimaryKey[] inheritedPrimaryKeys() default {};

    ConflictAction insertConflict() default ConflictAction.NONE;

    String name() default "";

    boolean orderedCursorLookUp() default false;

    ConflictAction primaryKeyConflict() default ConflictAction.NONE;

    UniqueGroup[] uniqueColumnGroups() default {};

    ConflictAction updateConflict() default ConflictAction.NONE;

    boolean useBooleanGetterSetters() default true;
}
