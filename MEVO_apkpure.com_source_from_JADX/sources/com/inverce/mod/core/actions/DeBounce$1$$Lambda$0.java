package com.inverce.mod.core.actions;

import android.view.View;
import android.view.View.OnClickListener;
import com.inverce.mod.core.functional.IConsumer;

final /* synthetic */ class DeBounce$1$$Lambda$0 implements IConsumer {
    private final OnClickListener arg$1;

    private DeBounce$1$$Lambda$0(OnClickListener onClickListener) {
        this.arg$1 = onClickListener;
    }

    static IConsumer get$Lambda(OnClickListener onClickListener) {
        return new DeBounce$1$$Lambda$0(onClickListener);
    }

    public void accept(Object obj) {
        this.arg$1.onClick((View) obj);
    }
}
