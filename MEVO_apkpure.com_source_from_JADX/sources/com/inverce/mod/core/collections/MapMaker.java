package com.inverce.mod.core.collections;

import android.support.annotation.NonNull;
import java.util.HashMap;
import java.util.Map;

public class MapMaker<K, V, T extends Map<K, V>> {
    T container;

    public static class WithKeys<K> {
        private final K[] keys;

        WithKeys(K[] kArr) {
            this.keys = kArr;
        }

        @SafeVarargs
        public final <V> MapMaker<K, V, HashMap<K, V>> vals(@NonNull V... vArr) {
            if (this.keys.length != vArr.length) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Expected: ");
                stringBuilder.append(this.keys.length);
                stringBuilder.append(" values, received: ");
                stringBuilder.append(vArr.length);
                throw new IllegalArgumentException(stringBuilder.toString());
            }
            Map hashMap = new HashMap();
            for (int i = 0; i < this.keys.length; i++) {
                hashMap.put(this.keys[i], vArr[i]);
            }
            return new MapMaker(hashMap);
        }
    }

    public MapMaker(T t) {
        this.container = t;
    }

    @NonNull
    public static <K, V, T extends Map<K, V>> MapMaker<K, V, T> New(@NonNull T t, K k, V v) {
        return new MapMaker(t).put(k, v);
    }

    @NonNull
    public static <K, V> MapMaker<K, V, HashMap<K, V>> New(K k, V v) {
        return New(new HashMap(), k, v);
    }

    @SafeVarargs
    public static <K> WithKeys<K> keys(K... kArr) {
        return new WithKeys(kArr);
    }

    @NonNull
    public MapMaker<K, V, T> put(K k, V v) {
        this.container.put(k, v);
        return this;
    }

    public T build() {
        return this.container;
    }
}
