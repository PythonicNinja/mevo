package com.raizlabs.android.dbflow.structure;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;

public abstract class InstanceAdapter<TModel> extends RetrievalAdapter<TModel> {
    @NonNull
    public abstract TModel newInstance();

    public InstanceAdapter(@NonNull DatabaseDefinition databaseDefinition) {
        super(databaseDefinition);
    }
}
