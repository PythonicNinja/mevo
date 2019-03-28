package com.raizlabs.android.dbflow.structure;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;

public abstract class ModelViewAdapter<TModelView> extends InstanceAdapter<TModelView> {
    public abstract String getCreationQuery();

    public abstract String getViewName();

    public ModelViewAdapter(@NonNull DatabaseDefinition databaseDefinition) {
        super(databaseDefinition);
    }
}
