package com.mevo.app.modules.nfc;

import com.annimon.stream.function.Function;

final /* synthetic */ class HCEManager$$Lambda$0 implements Function {
    private final boolean arg$1;

    HCEManager$$Lambda$0(boolean z) {
        this.arg$1 = z;
    }

    public Object apply(Object obj) {
        return ((NfcConfiguration) obj).setEnabled(this.arg$1);
    }
}
