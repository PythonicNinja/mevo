package com.inverce.mod.core.configuration.extended;

import android.support.annotation.NonNull;
import com.inverce.mod.core.functional.ISupplier;

public class LazyWeakValue<T> extends WeakValue<T> {
    ISupplier<T> initValue;

    public LazyWeakValue(@NonNull ISupplier<T> iSupplier) {
        super(null);
        this.initValue = iSupplier;
    }

    public boolean isInitialized() {
        return super.get() != null;
    }

    public T get() {
        T t = super.get();
        if (t != null) {
            return t;
        }
        t = this.initValue.get();
        set(t);
        return t;
    }
}
