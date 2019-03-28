package com.raizlabs.android.dbflow.structure.cache;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class ModelCache<TModel, CacheClass> {
    private CacheClass cache;

    public abstract void addModel(@Nullable Object obj, @NonNull TModel tModel);

    public abstract void clear();

    public abstract TModel get(@Nullable Object obj);

    public abstract TModel removeModel(@NonNull Object obj);

    public abstract void setCacheSize(int i);

    public ModelCache(@NonNull CacheClass cacheClass) {
        this.cache = cacheClass;
    }

    public CacheClass getCache() {
        return this.cache;
    }
}
