package com.inverce.mod.integrations.support.recycler;

import android.support.v7.widget.RecyclerView.ViewHolder;
import com.inverce.mod.integrations.support.annotations.IBind;
import com.inverce.mod.integrations.support.annotations.IBind.ToHolder;

final /* synthetic */ class DataBinder$$Lambda$1 implements IBind {
    private final ToHolder arg$1;

    DataBinder$$Lambda$1(ToHolder toHolder) {
        this.arg$1 = toHolder;
    }

    public void onBindViewHolder(ViewHolder viewHolder, Object obj, int i) {
        this.arg$1.bind(obj, (BindViewHolder) viewHolder, i);
    }
}
