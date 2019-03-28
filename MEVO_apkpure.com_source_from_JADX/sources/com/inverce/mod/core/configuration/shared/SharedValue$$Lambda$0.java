package com.inverce.mod.core.configuration.shared;

import com.inverce.mod.core.functional.IConsumer;

final /* synthetic */ class SharedValue$$Lambda$0 implements IConsumer {
    private final SharedValueImpl arg$1;

    private SharedValue$$Lambda$0(SharedValueImpl sharedValueImpl) {
        this.arg$1 = sharedValueImpl;
    }

    static IConsumer get$Lambda(SharedValueImpl sharedValueImpl) {
        return new SharedValue$$Lambda$0(sharedValueImpl);
    }

    public void accept(Object obj) {
        this.arg$1.setValue(obj);
    }
}
