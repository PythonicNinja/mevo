package com.inverce.mod.integrations.support.recycler;

import android.view.View;
import android.widget.TextView;
import com.inverce.mod.integrations.support.annotations.IBind.ToView;
import com.inverce.mod.integrations.support.annotations.MapValue.ToStringRes;

final /* synthetic */ class DataBinder$$Lambda$7 implements ToView {
    private final ToStringRes arg$1;

    DataBinder$$Lambda$7(ToStringRes toStringRes) {
        this.arg$1 = toStringRes;
    }

    public void bind(Object obj, View view, int i) {
        ((TextView) view).setText(this.arg$1.get(obj));
    }
}
