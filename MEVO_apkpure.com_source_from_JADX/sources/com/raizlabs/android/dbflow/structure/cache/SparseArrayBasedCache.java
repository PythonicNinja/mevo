package com.raizlabs.android.dbflow.structure.cache;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowLog.Level;

public class SparseArrayBasedCache<TModel> extends ModelCache<TModel, SparseArray<TModel>> {
    public SparseArrayBasedCache() {
        super(new SparseArray());
    }

    public SparseArrayBasedCache(int i) {
        super(new SparseArray(i));
    }

    public SparseArrayBasedCache(@NonNull SparseArray<TModel> sparseArray) {
        super(sparseArray);
    }

    public void addModel(@Nullable Object obj, @NonNull TModel tModel) {
        if (obj instanceof Number) {
            synchronized (((SparseArray) getCache())) {
                ((SparseArray) getCache()).put(((Number) obj).intValue(), tModel);
            }
            return;
        }
        throw new IllegalArgumentException("A SparseArrayBasedCache must use an id that can cast to a Number to convert it into a int");
    }

    public TModel removeModel(@NonNull Object obj) {
        TModel tModel = get(obj);
        synchronized (((SparseArray) getCache())) {
            ((SparseArray) getCache()).remove(((Number) obj).intValue());
        }
        return tModel;
    }

    public void clear() {
        synchronized (((SparseArray) getCache())) {
            ((SparseArray) getCache()).clear();
        }
    }

    public void setCacheSize(int i) {
        Level level = Level.I;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("The cache size for ");
        stringBuilder.append(SparseArrayBasedCache.class.getSimpleName());
        stringBuilder.append(" is not re-configurable.");
        FlowLog.log(level, stringBuilder.toString());
    }

    public TModel get(@Nullable Object obj) {
        if (obj instanceof Number) {
            return ((SparseArray) getCache()).get(((Number) obj).intValue());
        }
        throw new IllegalArgumentException("A SparseArrayBasedCache uses an id that can cast to a Number to convert it into a int");
    }
}
