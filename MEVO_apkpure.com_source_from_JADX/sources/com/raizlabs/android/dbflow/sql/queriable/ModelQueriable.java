package com.raizlabs.android.dbflow.sql.queriable;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.list.FlowQueryList;
import com.raizlabs.android.dbflow.sql.language.CursorResult;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import java.util.List;

public interface ModelQueriable<TModel> extends Queriable {
    @NonNull
    AsyncQuery<TModel> async();

    @NonNull
    FlowCursorList<TModel> cursorList();

    @NonNull
    ModelQueriable<TModel> disableCaching();

    @NonNull
    FlowQueryList<TModel> flowQueryList();

    @NonNull
    Class<TModel> getTable();

    @NonNull
    <TQueryModel> List<TQueryModel> queryCustomList(@NonNull Class<TQueryModel> cls);

    @Nullable
    <TQueryModel> TQueryModel queryCustomSingle(@NonNull Class<TQueryModel> cls);

    @NonNull
    List<TModel> queryList();

    @NonNull
    List<TModel> queryList(@NonNull DatabaseWrapper databaseWrapper);

    @NonNull
    CursorResult<TModel> queryResults();

    @Nullable
    TModel querySingle();

    @Nullable
    TModel querySingle(@NonNull DatabaseWrapper databaseWrapper);
}
