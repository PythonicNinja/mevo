package com.raizlabs.android.dbflow.structure;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

public abstract class BaseModelView extends NoModificationModel {
    public /* bridge */ /* synthetic */ boolean exists() {
        return super.exists();
    }

    public /* bridge */ /* synthetic */ boolean exists(@NonNull DatabaseWrapper databaseWrapper) {
        return super.exists(databaseWrapper);
    }

    public /* bridge */ /* synthetic */ RetrievalAdapter getRetrievalAdapter() {
        return super.getRetrievalAdapter();
    }

    public /* bridge */ /* synthetic */ void load() {
        super.load();
    }

    public /* bridge */ /* synthetic */ void load(@NonNull DatabaseWrapper databaseWrapper) {
        super.load(databaseWrapper);
    }
}
