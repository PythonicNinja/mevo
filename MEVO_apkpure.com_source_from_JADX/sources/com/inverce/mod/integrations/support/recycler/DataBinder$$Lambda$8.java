package com.inverce.mod.integrations.support.recycler;

import android.widget.ImageView;
import com.inverce.mod.core.functional.IFunction;

final /* synthetic */ class DataBinder$$Lambda$8 implements IFunction {
    private final int arg$1;

    DataBinder$$Lambda$8(int i) {
        this.arg$1 = i;
    }

    public Object apply(Object obj) {
        return ((ImageView) ((BindViewHolder) obj).get(this.arg$1));
    }
}
