package com.inverce.mod.integrations.support.recycler;

import android.view.View;
import android.view.View.OnClickListener;
import com.inverce.mod.integrations.support.annotations.IBind.ToView;
import com.inverce.mod.integrations.support.annotations.MapValue;

final /* synthetic */ class DataBinder$$Lambda$19 implements ToView {
    private final MapValue arg$1;

    DataBinder$$Lambda$19(MapValue mapValue) {
        this.arg$1 = mapValue;
    }

    public void bind(Object obj, View view, int i) {
        view.setOnClickListener((OnClickListener) this.arg$1.get(obj));
    }
}
