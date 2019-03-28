package com.inverce.mod.core.collections;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.inverce.mod.core.functional.IFunction;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class MapToReadOnlyList<T> extends AbstractList<T> {
    @NonNull
    private final List<?> list;
    @NonNull
    private final IFunction<Object, T> map;

    static final /* synthetic */ Object lambda$new$1$MapToReadOnlyList(Object obj) {
        return obj;
    }

    public <Y> MapToReadOnlyList(@Nullable List<Y> list, @NonNull IFunction<Y, T> iFunction) {
        if (list == null) {
            list = new ArrayList();
        }
        this.list = list;
        this.map = new MapToReadOnlyList$$Lambda$0(iFunction);
    }

    public MapToReadOnlyList(List<?> list) {
        this(list, MapToReadOnlyList$$Lambda$1.$instance);
    }

    @NonNull
    public T get(int i) {
        return this.map.apply(this.list.get(i));
    }

    public int size() {
        return this.list.size();
    }
}
