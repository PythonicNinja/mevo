package com.inverce.mod.core.configuration.shared;

import com.inverce.mod.core.functional.ISupplier;

final /* synthetic */ class SharedValue$$Lambda$1 implements ISupplier {
    private final SharedValueImpl arg$1;

    private SharedValue$$Lambda$1(SharedValueImpl sharedValueImpl) {
        this.arg$1 = sharedValueImpl;
    }

    static ISupplier get$Lambda(SharedValueImpl sharedValueImpl) {
        return new SharedValue$$Lambda$1(sharedValueImpl);
    }

    public Object get() {
        return this.arg$1.getValue();
    }
}
