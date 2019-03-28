package com.inverce.mod.core.actions;

import com.inverce.mod.core.functional.IsEqual;

final /* synthetic */ class DeBounce$$Lambda$2 implements IsEqual {
    private final DeBounce arg$1;

    DeBounce$$Lambda$2(DeBounce deBounce) {
        this.arg$1 = deBounce;
    }

    public boolean isEqual(Object obj, Object obj2) {
        return this.arg$1.bridge$lambda$0$DeBounce(obj, obj2);
    }
}
