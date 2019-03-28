package com.inverce.mod.integrations.support.recycler;

import android.view.View;
import com.inverce.mod.integrations.support.annotations.IBind.ToView;
import com.inverce.mod.integrations.support.annotations.MapValue.ToDrawable;

final /* synthetic */ class DataBinder$$Lambda$15 implements ToView {
    private final ToDrawable arg$1;

    DataBinder$$Lambda$15(ToDrawable toDrawable) {
        this.arg$1 = toDrawable;
    }

    public void bind(Object obj, View view, int i) {
        DataBinder.lambda$bindBackground$15$DataBinder(this.arg$1, obj, view, i);
    }
}
