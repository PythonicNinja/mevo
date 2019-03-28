package com.inverce.mod.core.reflection;

import com.inverce.mod.core.functional.IFunction;

final /* synthetic */ class Reflection$$Lambda$0 implements IFunction {
    static final IFunction $instance = new Reflection$$Lambda$0();

    private Reflection$$Lambda$0() {
    }

    public Object apply(Object obj) {
        return Reflection.getImplementedInterfacesInternal((Class) obj);
    }
}
