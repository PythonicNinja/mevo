package com.inverce.mod.core.configuration;

import com.inverce.mod.core.functional.ISupplier;

final /* synthetic */ class ReadOnlyValue$$Lambda$0 implements ISupplier {
    private final Value arg$1;

    private ReadOnlyValue$$Lambda$0(Value value) {
        this.arg$1 = value;
    }

    static ISupplier get$Lambda(Value value) {
        return new ReadOnlyValue$$Lambda$0(value);
    }

    public Object get() {
        return this.arg$1.get();
    }
}
