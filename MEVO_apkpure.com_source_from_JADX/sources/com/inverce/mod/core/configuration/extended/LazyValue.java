package com.inverce.mod.core.configuration.extended;

import android.support.annotation.NonNull;
import com.inverce.mod.core.configuration.Value;
import com.inverce.mod.core.functional.ISupplier;

public class LazyValue<T> extends Value<T> {
    protected boolean isInitialized;

    public LazyValue(@NonNull ISupplier<T> iSupplier) {
        super(null);
        setGetter(new LazyValue$$Lambda$0(this, iSupplier));
    }

    final /* synthetic */ Object lambda$new$0$LazyValue(@NonNull ISupplier iSupplier) {
        Object access$001 = super.get();
        if (!this.isInitialized) {
            this.isInitialized = true;
            set(iSupplier.get());
        }
        return access$001;
    }

    public boolean isInitialized() {
        return this.isInitialized;
    }
}
