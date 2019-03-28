package com.inverce.mod.integrations.support.recycler;

import android.view.View;
import android.widget.ImageView;
import com.inverce.mod.integrations.support.annotations.IBind.ToView;
import com.inverce.mod.integrations.support.annotations.MapValue.ToDrawableRes;

final /* synthetic */ class DataBinder$$Lambda$9 implements ToView {
    private final ToDrawableRes arg$1;

    DataBinder$$Lambda$9(ToDrawableRes toDrawableRes) {
        this.arg$1 = toDrawableRes;
    }

    public void bind(Object obj, View view, int i) {
        ((ImageView) view).setImageResource(this.arg$1.get(obj));
    }
}
