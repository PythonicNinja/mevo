package com.inverce.mod.core.collections;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.inverce.mod.core.functional.IFunction;
import com.inverce.mod.core.reflection.TypeToken;
import java.util.HashMap;

public class CacheFunctionMap<Key, Value> extends HashMap<Key, Value> {
    protected IFunction<Key, Value> generator;
    protected Class<Key> keyClass;

    /* renamed from: com.inverce.mod.core.collections.CacheFunctionMap$1 */
    class C07941 extends TypeToken<Key> {
        C07941() {
        }
    }

    public CacheFunctionMap(@NonNull IFunction<Key, Value> iFunction) {
        this.keyClass = new C07941().getRawType();
        this.generator = iFunction;
    }

    public CacheFunctionMap(@NonNull Class<Key> cls, @NonNull IFunction<Key, Value> iFunction) {
        this.generator = iFunction;
        this.keyClass = cls;
    }

    public Value get(@Nullable Object obj) {
        Value value = super.get(obj);
        if (value != null || !this.keyClass.isInstance(obj)) {
            return value;
        }
        value = this.generator.apply(obj);
        put(obj, value);
        return value;
    }
}
