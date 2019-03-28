package com.inverce.mod.integrations.support.recycler;

import android.view.View;
import com.inverce.mod.integrations.support.annotations.IBind.ToView;
import com.inverce.mod.integrations.support.annotations.MapValue.ToDrawableRes;

final /* synthetic */ class DataBinder$$Lambda$13 implements ToView {
    private final ToDrawableRes arg$1;

    DataBinder$$Lambda$13(ToDrawableRes toDrawableRes) {
        this.arg$1 = toDrawableRes;
    }

    public void bind(Object obj, View view, int i) {
        view.setBackgroundResource(this.arg$1.get(obj));
    }
}
