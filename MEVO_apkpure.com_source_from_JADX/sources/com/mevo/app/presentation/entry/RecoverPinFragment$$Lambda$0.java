package com.mevo.app.presentation.entry;

import com.inverce.mod.core.functional.IConsumer;
import com.mevo.app.presentation.ToolbarActivityInterface;

final /* synthetic */ class RecoverPinFragment$$Lambda$0 implements IConsumer {
    static final IConsumer $instance = new RecoverPinFragment$$Lambda$0();

    private RecoverPinFragment$$Lambda$0() {
    }

    public void accept(Object obj) {
        ((ToolbarActivityInterface) obj).getToolbar().setVisibility(0);
    }
}
