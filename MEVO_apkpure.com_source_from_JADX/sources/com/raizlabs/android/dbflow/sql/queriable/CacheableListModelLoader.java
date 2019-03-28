package com.raizlabs.android.dbflow.sql.queriable;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.cache.ModelCache;
import com.raizlabs.android.dbflow.structure.database.FlowCursor;
import java.util.ArrayList;
import java.util.List;

public class CacheableListModelLoader<TModel> extends ListModelLoader<TModel> {
    private ModelAdapter<TModel> modelAdapter;
    private ModelCache<TModel, ?> modelCache;

    public CacheableListModelLoader(@NonNull Class<TModel> cls) {
        super(cls);
    }

    @NonNull
    public ModelCache<TModel, ?> getModelCache() {
        if (this.modelCache == null) {
            this.modelCache = this.modelAdapter.getModelCache();
            if (this.modelCache == null) {
                throw new IllegalArgumentException("ModelCache specified in convertToCacheableList() must not be null.");
            }
        }
        return this.modelCache;
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
    public List<TModel> convertToData(@NonNull FlowCursor flowCursor, @Nullable List<TModel> list) {
        if (list == null) {
            list = new ArrayList();
        }
        Object[] objArr = new Object[getModelAdapter().getCachingColumns().length];
        if (flowCursor.moveToFirst()) {
            do {
                Object[] cachingColumnValuesFromCursor = getModelAdapter().getCachingColumnValuesFromCursor(objArr, flowCursor);
                Object obj = getModelCache().get(getModelAdapter().getCachingId(cachingColumnValuesFromCursor));
                if (obj != null) {
                    getModelAdapter().reloadRelationships(obj, flowCursor);
                    list.add(obj);
                } else {
                    obj = getModelAdapter().newInstance();
                    getModelAdapter().loadFromCursor(flowCursor, obj);
                    getModelCache().addModel(getModelAdapter().getCachingId(cachingColumnValuesFromCursor), obj);
                    list.add(obj);
                }
            } while (flowCursor.moveToNext());
        }
        return list;
    }
}
