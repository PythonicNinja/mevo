package com.raizlabs.android.dbflow.structure;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.sql.language.OperatorGroup;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

public abstract class QueryModelAdapter<TQueryModel> extends InstanceAdapter<TQueryModel> {
    public QueryModelAdapter(DatabaseDefinition databaseDefinition) {
        super(databaseDefinition);
    }

    public OperatorGroup getPrimaryConditionClause(@NonNull TQueryModel tQueryModel) {
        throw new UnsupportedOperationException("QueryModels cannot check for existence");
    }

    public boolean exists(@NonNull TQueryModel tQueryModel) {
        throw new UnsupportedOperationException("QueryModels cannot check for existence");
    }

    public boolean exists(@NonNull TQueryModel tQueryModel, @NonNull DatabaseWrapper databaseWrapper) {
        throw new UnsupportedOperationException("QueryModels cannot check for existence");
    }
}
