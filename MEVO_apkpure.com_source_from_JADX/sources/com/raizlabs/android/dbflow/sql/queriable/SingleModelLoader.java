package com.raizlabs.android.dbflow.sql.queriable;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.structure.database.FlowCursor;

public class SingleModelLoader<TModel> extends ModelLoader<TModel, TModel> {
    public SingleModelLoader(Class<TModel> cls) {
        super(cls);
    }

    @Nullable
    public TModel convertToData(@NonNull FlowCursor flowCursor, @Nullable TModel tModel, boolean z) {
        if (!z || flowCursor.moveToFirst()) {
            if (tModel == null) {
                tModel = getInstanceAdapter().newInstance();
            }
            getInstanceAdapter().loadFromCursor(flowCursor, tModel);
        }
        return tModel;
    }

    public TModel convertToData(@NonNull FlowCursor flowCursor, @Nullable TModel tModel) {
        return convertToData(flowCursor, tModel, true);
    }
}
