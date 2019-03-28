package com.mevo.app.presentation.entry;

import com.inverce.mod.core.functional.IConsumer;
import com.mevo.app.presentation.ToolbarActivityInterface;

final /* synthetic */ class LoginFragment$$Lambda$1 implements IConsumer {
    static final IConsumer $instance = new LoginFragment$$Lambda$1();

    private LoginFragment$$Lambda$1() {
    }

    public void accept(Object obj) {
        ((ToolbarActivityInterface) obj).getToolbar().setVisibility(0);
    }
}
