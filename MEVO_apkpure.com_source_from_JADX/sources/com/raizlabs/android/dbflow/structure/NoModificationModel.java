package com.raizlabs.android.dbflow.structure;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

abstract class NoModificationModel implements ReadOnlyModel {
    private transient RetrievalAdapter adapter;

    static class InvalidSqlViewOperationException extends RuntimeException {
        InvalidSqlViewOperationException(String str) {
            super(str);
        }
    }

    NoModificationModel() {
    }

    public boolean exists() {
        return getRetrievalAdapter().exists(this);
    }

    public boolean exists(@NonNull DatabaseWrapper databaseWrapper) {
        return getRetrievalAdapter().exists(this, databaseWrapper);
    }

    public void load() {
        getRetrievalAdapter().load(this);
    }

    public void load(@NonNull DatabaseWrapper databaseWrapper) {
        getRetrievalAdapter().load(this, databaseWrapper);
    }

    public RetrievalAdapter getRetrievalAdapter() {
        if (this.adapter == null) {
            this.adapter = FlowManager.getInstanceAdapter(getClass());
        }
        return this.adapter;
    }
}
