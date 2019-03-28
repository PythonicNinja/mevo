package com.raizlabs.android.dbflow.sql.queriable;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.InstanceAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.FlowCursor;

public abstract class ModelLoader<TModel, TReturn> {
    private DatabaseDefinition databaseDefinition;
    private InstanceAdapter<TModel> instanceAdapter;
    private final Class<TModel> modelClass;

    @Nullable
    public abstract TReturn convertToData(@NonNull FlowCursor flowCursor, @Nullable TReturn tReturn);

    public ModelLoader(@NonNull Class<TModel> cls) {
        this.modelClass = cls;
    }

    @Nullable
    public TReturn load(@NonNull String str) {
        return load(getDatabaseDefinition().getWritableDatabase(), str);
    }

    @Nullable
    public TReturn load(@NonNull String str, @Nullable TReturn tReturn) {
        return load(getDatabaseDefinition().getWritableDatabase(), str, tReturn);
    }

    @Nullable
    public TReturn load(@NonNull DatabaseWrapper databaseWrapper, @NonNull String str) {
        return load(databaseWrapper, str, null);
    }

    @Nullable
    public TReturn load(@NonNull DatabaseWrapper databaseWrapper, @NonNull String str, @Nullable TReturn tReturn) {
        return load(databaseWrapper.rawQuery(str, null), (Object) tReturn);
    }

    @Nullable
    public TReturn load(@Nullable FlowCursor flowCursor) {
        return load(flowCursor, null);
    }

    @Nullable
    public TReturn load(@Nullable FlowCursor flowCursor, @Nullable TReturn tReturn) {
        if (flowCursor != null) {
            try {
                tReturn = convertToData(flowCursor, tReturn);
            } finally {
                flowCursor.close();
            }
        }
        return tReturn;
    }

    @NonNull
    public Class<TModel> getModelClass() {
        return this.modelClass;
    }

    @NonNull
    public InstanceAdapter<TModel> getInstanceAdapter() {
        if (this.instanceAdapter == null) {
            this.instanceAdapter = FlowManager.getInstanceAdapter(this.modelClass);
        }
        return this.instanceAdapter;
    }

    @NonNull
    public DatabaseDefinition getDatabaseDefinition() {
        if (this.databaseDefinition == null) {
            this.databaseDefinition = FlowManager.getDatabaseForTable(this.modelClass);
        }
        return this.databaseDefinition;
    }
}
