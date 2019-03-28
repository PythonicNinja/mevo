package org.simpleframework.xml.util;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class LimitedCache<T> extends LinkedHashMap<Object, T> implements Cache<T> {
    private final int capacity;

    public LimitedCache() {
        this(50000);
    }

    public LimitedCache(int i) {
        this.capacity = i;
    }

    public void cache(Object obj, T t) {
        put(obj, t);
    }

    public T take(Object obj) {
        return remove(obj);
    }

    public T fetch(Object obj) {
        return get(obj);
    }

    public boolean contains(Object obj) {
        return containsKey(obj);
    }

    protected boolean removeEldestEntry(Entry<Object, T> entry) {
        return size() > this.capacity ? true : null;
    }
}
