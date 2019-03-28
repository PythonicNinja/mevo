package com.inverce.mod.integrations.support.recycler;

import android.view.View;
import android.widget.ImageView;
import com.inverce.mod.integrations.support.annotations.IBind.ToView;
import com.inverce.mod.integrations.support.annotations.MapValue;

final /* synthetic */ class DataBinder$$Lambda$11 implements ToView {
    private final DataBinder arg$1;
    private final MapValue arg$2;

    DataBinder$$Lambda$11(DataBinder dataBinder, MapValue mapValue) {
        this.arg$1 = dataBinder;
        this.arg$2 = mapValue;
    }

    public void bind(Object obj, View view, int i) {
        this.arg$1.lambda$bindImage$11$DataBinder(this.arg$2, obj, (ImageView) view, i);
    }
}
