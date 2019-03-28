package com.inverce.mod.core.configuration.extended;

import com.inverce.mod.core.functional.ISupplier;

final /* synthetic */ class LazyValue$$Lambda$0 implements ISupplier {
    private final LazyValue arg$1;
    private final ISupplier arg$2;

    LazyValue$$Lambda$0(LazyValue lazyValue, ISupplier iSupplier) {
        this.arg$1 = lazyValue;
        this.arg$2 = iSupplier;
    }

    public Object get() {
        return this.arg$1.lambda$new$0$LazyValue(this.arg$2);
    }
}
