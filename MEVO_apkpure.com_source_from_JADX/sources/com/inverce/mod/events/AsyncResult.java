package com.inverce.mod.events;

import android.support.annotation.NonNull;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class AsyncResult<T> extends AbstractList<T> {
    protected List<T> store;

    AsyncResult(List<T> list) {
        this.store = list;
    }

    public int size() {
        return this.store.size();
    }

    public T get(int i) {
        return this.store.get(i);
    }

    public static <Y> AsyncResult<Y> yield() {
        return new AsyncResult(new ArrayList(0));
    }

    public static <Y> AsyncResult<Y> yield(Y y) {
        return new AsyncResult(Collections.singletonList(y));
    }

    @SafeVarargs
    public static <Y> AsyncResult<Y> yield(Y... yArr) {
        return new AsyncResult(Arrays.asList(yArr));
    }

    public static <Y> AsyncResult<Y> yield(@NonNull List<Y> list) {
        return new AsyncResult(list);
    }
}
