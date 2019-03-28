package com.inverce.mod.core.configuration;

import com.inverce.mod.core.configuration.extended.BoxedValue;
import com.inverce.mod.core.functional.ISupplier;

final /* synthetic */ class Value$$Lambda$2 implements ISupplier {
    private final BoxedValue arg$1;

    private Value$$Lambda$2(BoxedValue boxedValue) {
        this.arg$1 = boxedValue;
    }

    static ISupplier get$Lambda(BoxedValue boxedValue) {
        return new Value$$Lambda$2(boxedValue);
    }

    public Object get() {
        return this.arg$1.getValue();
    }
}
