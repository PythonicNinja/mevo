package com.inverce.mod.core.configuration;

import com.inverce.mod.core.functional.ISupplier;

final /* synthetic */ class ReadOnlyValue$$Lambda$1 implements ISupplier {
    private final Object arg$1;

    ReadOnlyValue$$Lambda$1(Object obj) {
        this.arg$1 = obj;
    }

    public Object get() {
        return ReadOnlyValue.lambda$new$0$ReadOnlyValue(this.arg$1);
    }
}
