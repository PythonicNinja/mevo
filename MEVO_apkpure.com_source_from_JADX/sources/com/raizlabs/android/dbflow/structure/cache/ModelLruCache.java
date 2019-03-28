package com.raizlabs.android.dbflow.structure.cache;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ModelLruCache<TModel> extends ModelCache<TModel, LruCache<Long, TModel>> {
    public static <TModel> ModelLruCache<TModel> newInstance(int i) {
        if (i <= 0) {
            i = 25;
        }
        return new ModelLruCache(i);
    }

    protected ModelLruCache(int i) {
        super(new LruCache(i));
    }

    public void addModel(@Nullable Object obj, @NonNull TModel tModel) {
        if (obj instanceof Number) {
            synchronized (((LruCache) getCache())) {
                ((LruCache) getCache()).put(Long.valueOf(((Number) obj).longValue()), tModel);
            }
            return;
        }
        throw new IllegalArgumentException("A ModelLruCache must use an id that can cast toa Number to convert it into a long");
    }

    public TModel removeModel(@NonNull Object obj) {
        if (obj instanceof Number) {
            synchronized (((LruCache) getCache())) {
                obj = ((LruCache) getCache()).remove(Long.valueOf(((Number) obj).longValue()));
            }
            return obj;
        }
        throw new IllegalArgumentException("A ModelLruCache uses an id that can cast toa Number to convert it into a long");
    }

    public void clear() {
        synchronized (((LruCache) getCache())) {
            ((LruCache) getCache()).evictAll();
        }
    }

    public void setCacheSize(int i) {
        ((LruCache) getCache()).resize(i);
    }

    public TModel get(@Nullable Object obj) {
        if (obj instanceof Number) {
            return ((LruCache) getCache()).get(Long.valueOf(((Number) obj).longValue()));
        }
        throw new IllegalArgumentException("A ModelLruCache must use an id that can cast toa Number to convert it into a long");
    }
}
