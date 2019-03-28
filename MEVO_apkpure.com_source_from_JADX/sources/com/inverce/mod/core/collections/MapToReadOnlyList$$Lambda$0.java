package com.inverce.mod.core.collections;

import com.inverce.mod.core.functional.IFunction;

final /* synthetic */ class MapToReadOnlyList$$Lambda$0 implements IFunction {
    private final IFunction arg$1;

    MapToReadOnlyList$$Lambda$0(IFunction iFunction) {
        this.arg$1 = iFunction;
    }

    public Object apply(Object obj) {
        return this.arg$1.apply(obj);
    }
}
