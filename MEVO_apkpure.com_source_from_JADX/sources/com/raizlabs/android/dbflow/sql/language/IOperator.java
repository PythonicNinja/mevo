package com.raizlabs.android.dbflow.sql.language;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.language.Operator.Between;
import com.raizlabs.android.dbflow.sql.language.Operator.In;
import java.util.Collection;

public interface IOperator<T> extends Query, IConditional {
    @NonNull
    Between<T> between(@NonNull T t);

    @NonNull
    Operator<T> concatenate(@Nullable T t);

    @NonNull
    Operator<T> div(@NonNull T t);

    @NonNull
    Operator<T> eq(@Nullable T t);

    @NonNull
    Operator<T> greaterThan(@NonNull T t);

    @NonNull
    Operator<T> greaterThanOrEq(@NonNull T t);

    @NonNull
    In<T> in(@NonNull T t, T... tArr);

    @NonNull
    In<T> in(@NonNull Collection<T> collection);

    @NonNull
    Operator<T> is(@Nullable T t);

    @NonNull
    Operator<T> isNot(@Nullable T t);

    @NonNull
    Operator<T> lessThan(@NonNull T t);

    @NonNull
    Operator<T> lessThanOrEq(@NonNull T t);

    @NonNull
    Operator<T> minus(@NonNull T t);

    @NonNull
    Operator<T> notEq(@Nullable T t);

    @NonNull
    In<T> notIn(@NonNull T t, T... tArr);

    @NonNull
    In<T> notIn(@NonNull Collection<T> collection);

    @NonNull
    Operator<T> plus(@NonNull T t);

    @NonNull
    Operator<T> rem(@NonNull T t);

    Operator<T> times(@NonNull T t);
}
