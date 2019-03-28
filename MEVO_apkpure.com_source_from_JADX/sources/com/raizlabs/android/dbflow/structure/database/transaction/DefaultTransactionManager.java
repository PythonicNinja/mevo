package com.raizlabs.android.dbflow.structure.database.transaction;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.runtime.BaseTransactionManager;

public class DefaultTransactionManager extends BaseTransactionManager {
    public DefaultTransactionManager(@NonNull DatabaseDefinition databaseDefinition) {
        super(new DefaultTransactionQueue("DBFlow Transaction Queue"), databaseDefinition);
    }

    public DefaultTransactionManager(@NonNull ITransactionQueue iTransactionQueue, @NonNull DatabaseDefinition databaseDefinition) {
        super(iTransactionQueue, databaseDefinition);
    }
}
