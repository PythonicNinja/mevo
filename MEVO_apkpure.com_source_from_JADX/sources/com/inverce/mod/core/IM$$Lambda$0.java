package com.inverce.mod.core;

import com.inverce.mod.core.functional.ISupplier;
import java.util.UUID;

final /* synthetic */ class IM$$Lambda$0 implements ISupplier {
    static final ISupplier $instance = new IM$$Lambda$0();

    private IM$$Lambda$0() {
    }

    public Object get() {
        return UUID.randomUUID().toString();
    }
}
