package com.inverce.mod.integrations.support.flow;

import android.os.Bundle;
import com.inverce.mod.core.functional.IConsumer;

final /* synthetic */ class BaseFragment$$Lambda$0 implements IConsumer {
    private final BaseFragment arg$1;

    BaseFragment$$Lambda$0(BaseFragment baseFragment) {
        this.arg$1 = baseFragment;
    }

    public void accept(Object obj) {
        this.arg$1.setArguments((Bundle) obj);
    }
}
