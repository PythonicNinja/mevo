package com.inverce.mod.integrations.support.recycler;

import android.widget.TextView;
import com.inverce.mod.core.functional.IFunction;

final /* synthetic */ class DataBinder$$Lambda$6 implements IFunction {
    private final int arg$1;

    DataBinder$$Lambda$6(int i) {
        this.arg$1 = i;
    }

    public Object apply(Object obj) {
        return ((TextView) ((BindViewHolder) obj).get(this.arg$1));
    }
}
