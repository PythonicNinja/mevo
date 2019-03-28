package com.mevo.app.presentation.main.contact_us;

import com.inverce.mod.core.functional.IConsumer;
import com.mevo.app.presentation.ToolbarActivityInterface;

final /* synthetic */ class ContactUsFragment$$Lambda$0 implements IConsumer {
    static final IConsumer $instance = new ContactUsFragment$$Lambda$0();

    private ContactUsFragment$$Lambda$0() {
    }

    public void accept(Object obj) {
        ((ToolbarActivityInterface) obj).getToolbar().setContactVisibility(false);
    }
}
