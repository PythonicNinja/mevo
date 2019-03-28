package com.mevo.app.presentation.entry;

import com.inverce.mod.core.functional.IConsumer;
import com.mevo.app.presentation.ToolbarActivityInterface;

final /* synthetic */ class LoginFragment$$Lambda$0 implements IConsumer {
    static final IConsumer $instance = new LoginFragment$$Lambda$0();

    private LoginFragment$$Lambda$0() {
    }

    public void accept(Object obj) {
        ((ToolbarActivityInterface) obj).getToolbar().setVisibility(8);
    }
}
