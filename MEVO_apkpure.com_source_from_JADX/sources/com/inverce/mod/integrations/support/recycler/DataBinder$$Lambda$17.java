package com.inverce.mod.integrations.support.recycler;

import android.view.View;
import com.inverce.mod.core.Ui;
import com.inverce.mod.integrations.support.annotations.IBind.ToView;
import com.inverce.mod.integrations.support.annotations.MapValue;

final /* synthetic */ class DataBinder$$Lambda$17 implements ToView {
    private final MapValue arg$1;

    DataBinder$$Lambda$17(MapValue mapValue) {
        this.arg$1 = mapValue;
    }

    public void bind(Object obj, View view, int i) {
        Ui.visible(view, ((Boolean) this.arg$1.get(obj)).booleanValue());
    }
}
