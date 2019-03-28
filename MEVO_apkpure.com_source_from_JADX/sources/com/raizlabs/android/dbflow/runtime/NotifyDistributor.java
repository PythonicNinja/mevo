package com.raizlabs.android.dbflow.runtime;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.BaseModel.Action;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

public class NotifyDistributor implements ModelNotifier {
    private static NotifyDistributor distributor;

    @NonNull
    public static NotifyDistributor get() {
        if (distributor == null) {
            distributor = new NotifyDistributor();
        }
        return distributor;
    }

    public TableNotifierRegister newRegister() {
        throw new RuntimeException("Cannot create a register from the distributor class");
    }

    public <TModel> void notifyModelChanged(@NonNull TModel tModel, @NonNull ModelAdapter<TModel> modelAdapter, @NonNull Action action) {
        FlowManager.getModelNotifierForTable(modelAdapter.getModelClass()).notifyModelChanged(tModel, modelAdapter, action);
    }

    public <TModel> void notifyTableChanged(@NonNull Class<TModel> cls, @NonNull Action action) {
        FlowManager.getModelNotifierForTable(cls).notifyTableChanged(cls, action);
    }
}
