package com.inverce.mod.integrations.support.recycler;

import android.support.v7.widget.RecyclerView.ViewHolder;
import com.inverce.mod.core.functional.IFunction;
import com.inverce.mod.integrations.support.annotations.IBind;
import com.inverce.mod.integrations.support.annotations.IBind.ToView;

final /* synthetic */ class DataBinder$$Lambda$2 implements IBind {
    private final IFunction arg$1;
    private final ToView arg$2;

    DataBinder$$Lambda$2(IFunction iFunction, ToView toView) {
        this.arg$1 = iFunction;
        this.arg$2 = toView;
    }

    public void onBindViewHolder(ViewHolder viewHolder, Object obj, int i) {
        DataBinder.lambda$bind$2$DataBinder(this.arg$1, this.arg$2, (BindViewHolder) viewHolder, obj, i);
    }
}
