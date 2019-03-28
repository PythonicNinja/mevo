package com.inverce.mod.core.configuration;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.inverce.mod.core.functional.ISupplier;

public class ReadOnlyValue<T> {
    protected ISupplier<T> supplier;

    static final /* synthetic */ Object lambda$new$0$ReadOnlyValue(Object obj) {
        return obj;
    }

    protected ReadOnlyValue() {
    }

    public ReadOnlyValue(@NonNull Value<T> value) {
        value.getClass();
        this(ReadOnlyValue$$Lambda$0.get$Lambda(value));
    }

    public ReadOnlyValue(T t) {
        this(new ReadOnlyValue$$Lambda$1(t));
    }

    public ReadOnlyValue(ISupplier<T> iSupplier) {
        setGetter(iSupplier);
    }

    @Nullable
    public T get() {
        return this.supplier != null ? this.supplier.get() : null;
    }

    protected void setGetter(ISupplier<T> iSupplier) {
        this.supplier = iSupplier;
    }

    @NonNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Value: ");
        stringBuilder.append(String.valueOf(get()));
        return stringBuilder.toString();
    }
}
