package com.raizlabs.android.dbflow.structure.cache;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowLog.Level;
import java.util.HashMap;
import java.util.Map;

public class SimpleMapCache<TModel> extends ModelCache<TModel, Map<Object, TModel>> {
    public SimpleMapCache(int i) {
        super(new HashMap(i));
    }

    public SimpleMapCache(@NonNull Map<Object, TModel> map) {
        super(map);
    }

    public void addModel(@Nullable Object obj, @NonNull TModel tModel) {
        ((Map) getCache()).put(obj, tModel);
    }

    public TModel removeModel(@NonNull Object obj) {
        return ((Map) getCache()).remove(obj);
    }

    public void clear() {
        ((Map) getCache()).clear();
    }

    public TModel get(@Nullable Object obj) {
        return ((Map) getCache()).get(obj);
    }

    public void setCacheSize(int i) {
        Level level = Level.I;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("The cache size for ");
        stringBuilder.append(SimpleMapCache.class.getSimpleName());
        stringBuilder.append(" is not re-configurable.");
        FlowLog.log(level, stringBuilder.toString());
    }
}
