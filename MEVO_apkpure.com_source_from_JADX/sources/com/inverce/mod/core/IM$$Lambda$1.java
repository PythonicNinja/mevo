package com.inverce.mod.core;

import com.inverce.mod.core.configuration.shared.SharedBoolAutoToggle;
import com.inverce.mod.core.functional.ISupplier;

final /* synthetic */ class IM$$Lambda$1 implements ISupplier {
    private final SharedBoolAutoToggle arg$1;

    private IM$$Lambda$1(SharedBoolAutoToggle sharedBoolAutoToggle) {
        this.arg$1 = sharedBoolAutoToggle;
    }

    static ISupplier get$Lambda(SharedBoolAutoToggle sharedBoolAutoToggle) {
        return new IM$$Lambda$1(sharedBoolAutoToggle);
    }

    public Object get() {
        return this.arg$1.get();
    }
}
