package com.raizlabs.android.dbflow.sql.queriable;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.cache.ModelCache;
import com.raizlabs.android.dbflow.structure.database.FlowCursor;

public class CacheableModelLoader<TModel> extends SingleModelLoader<TModel> {
    private ModelAdapter<TModel> modelAdapter;
    private ModelCache<TModel, ?> modelCache;

    public CacheableModelLoader(@NonNull Class<TModel> cls) {
        super(cls);
    }

    @NonNull
    public ModelAdapter<TModel> getModelAdapter() {
        if (this.modelAdapter == null) {
            if (getInstanceAdapter() instanceof ModelAdapter) {
                this.modelAdapter = (ModelAdapter) getInstanceAdapter();
                if (!this.modelAdapter.cachingEnabled()) {
                    throw new IllegalArgumentException("You cannot call this method for a table that has no caching id. Eitheruse one Primary Key or use the MultiCacheKeyConverter");
                }
            }
            throw new IllegalArgumentException("A non-Table type was used.");
        }
        return this.modelAdapter;
    }

    @NonNull
    public ModelCache<TModel, ?> getModelCache() {
        if (this.modelCache == null) {
            this.modelCache = getModelAdapter().getModelCache();
        }
        return this.modelCache;
    }

    @Nullable
    public TModel convertToData(@NonNull FlowCursor flowCursor, @Nullable TModel tModel, boolean z) {
        if (z) {
            if (!flowCursor.moveToFirst()) {
                return null;
            }
        }
        Object[] cachingColumnValuesFromCursor = getModelAdapter().getCachingColumnValuesFromCursor(new Object[getModelAdapter().getCachingColumns().length], flowCursor);
        TModel tModel2 = getModelCache().get(getModelAdapter().getCachingId(cachingColumnValuesFromCursor));
        if (tModel2 == null) {
            if (tModel == null) {
                tModel = getModelAdapter().newInstance();
            }
            tModel2 = tModel;
            getModelAdapter().loadFromCursor(flowCursor, tModel2);
            getModelCache().addModel(getModelAdapter().getCachingId(cachingColumnValuesFromCursor), tModel2);
        } else {
            getModelAdapter().reloadRelationships(tModel2, flowCursor);
        }
        return tModel2;
    }
}
