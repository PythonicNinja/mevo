package com.inverce.mod.integrations.support.recycler;

import android.view.View;
import com.inverce.mod.core.functional.IFunction;

final /* synthetic */ class MultiRecyclerAdapter$$Lambda$1 implements IFunction {
    static final IFunction $instance = new MultiRecyclerAdapter$$Lambda$1();

    private MultiRecyclerAdapter$$Lambda$1() {
    }

    public Object apply(Object obj) {
        return new BindViewHolder((View) obj);
    }
}
