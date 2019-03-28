package com.inverce.mod.integrations.support.recycler;

import com.inverce.mod.core.functional.IFunction;

final /* synthetic */ class DataBinder$$Lambda$18 implements IFunction {
    private final int arg$1;

    DataBinder$$Lambda$18(int i) {
        this.arg$1 = i;
    }

    public Object apply(Object obj) {
        return ((BindViewHolder) obj).get(this.arg$1);
    }
}
