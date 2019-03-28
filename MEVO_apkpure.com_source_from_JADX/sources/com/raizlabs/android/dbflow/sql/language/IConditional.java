package com.raizlabs.android.dbflow.sql.language;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.language.Operator.Between;
import com.raizlabs.android.dbflow.sql.language.Operator.In;

public interface IConditional extends Query {
    @NonNull
    Between between(@NonNull BaseModelQueriable baseModelQueriable);

    @NonNull
    Between between(@NonNull IConditional iConditional);

    @NonNull
    Operator concatenate(@NonNull IConditional iConditional);

    @NonNull
    Operator div(@NonNull BaseModelQueriable baseModelQueriable);

    @NonNull
    Operator eq(@NonNull BaseModelQueriable baseModelQueriable);

    @NonNull
    Operator eq(@NonNull IConditional iConditional);

    @NonNull
    Operator glob(@NonNull BaseModelQueriable baseModelQueriable);

    @NonNull
    Operator glob(@NonNull IConditional iConditional);

    @NonNull
    Operator glob(@NonNull String str);

    @NonNull
    Operator greaterThan(@NonNull BaseModelQueriable baseModelQueriable);

    @NonNull
    Operator greaterThan(@NonNull IConditional iConditional);

    @NonNull
    Operator greaterThanOrEq(@NonNull BaseModelQueriable baseModelQueriable);

    @NonNull
    Operator greaterThanOrEq(@NonNull IConditional iConditional);

    @NonNull
    In in(@NonNull BaseModelQueriable baseModelQueriable, @NonNull BaseModelQueriable... baseModelQueriableArr);

    @NonNull
    In in(@NonNull IConditional iConditional, @NonNull IConditional... iConditionalArr);

    @NonNull
    Operator is(@NonNull BaseModelQueriable baseModelQueriable);

    @NonNull
    Operator is(@NonNull IConditional iConditional);

    @NonNull
    Operator isNot(@NonNull BaseModelQueriable baseModelQueriable);

    @NonNull
    Operator isNot(@NonNull IConditional iConditional);

    @NonNull
    Operator isNotNull();

    @NonNull
    Operator isNull();

    @NonNull
    Operator lessThan(@NonNull BaseModelQueriable baseModelQueriable);

    @NonNull
    Operator lessThan(@NonNull IConditional iConditional);

    @NonNull
    Operator lessThanOrEq(@NonNull BaseModelQueriable baseModelQueriable);

    @NonNull
    Operator lessThanOrEq(@NonNull IConditional iConditional);

    @NonNull
    Operator like(@NonNull BaseModelQueriable baseModelQueriable);

    @NonNull
    Operator like(@NonNull IConditional iConditional);

    @NonNull
    Operator like(@NonNull String str);

    @NonNull
    Operator minus(@NonNull BaseModelQueriable baseModelQueriable);

    @NonNull
    Operator notEq(@NonNull BaseModelQueriable baseModelQueriable);

    @NonNull
    Operator notEq(@NonNull IConditional iConditional);

    @NonNull
    In notIn(@NonNull BaseModelQueriable baseModelQueriable, @NonNull BaseModelQueriable... baseModelQueriableArr);

    @NonNull
    In notIn(@NonNull IConditional iConditional, @NonNull IConditional... iConditionalArr);

    @NonNull
    Operator notLike(@NonNull BaseModelQueriable baseModelQueriable);

    @NonNull
    Operator notLike(@NonNull IConditional iConditional);

    @NonNull
    Operator notLike(@NonNull String str);

    @NonNull
    Operator plus(@NonNull BaseModelQueriable baseModelQueriable);

    @NonNull
    Operator rem(@NonNull BaseModelQueriable baseModelQueriable);

    @NonNull
    Operator times(@NonNull BaseModelQueriable baseModelQueriable);
}
