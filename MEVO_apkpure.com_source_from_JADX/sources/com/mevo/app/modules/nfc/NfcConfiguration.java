package com.mevo.app.modules.nfc;

import android.support.annotation.Nullable;
import com.inverce.mod.core.IM;

public class NfcConfiguration {
    private boolean enabled = true;

    public boolean isEnabled() {
        return this.enabled;
    }

    public NfcConfiguration setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    @Nullable
    public String getCardId() {
        return AccountStorage.GetAccount(IM.activity());
    }
}
