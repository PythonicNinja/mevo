package com.inverce.mod.core.configuration;

import com.inverce.mod.core.configuration.extended.BoxedValue;
import com.inverce.mod.core.functional.IConsumer;

final /* synthetic */ class Value$$Lambda$1 implements IConsumer {
    private final BoxedValue arg$1;

    private Value$$Lambda$1(BoxedValue boxedValue) {
        this.arg$1 = boxedValue;
    }

    static IConsumer get$Lambda(BoxedValue boxedValue) {
        return new Value$$Lambda$1(boxedValue);
    }

    public void accept(Object obj) {
        this.arg$1.setValue(obj);
    }
}
