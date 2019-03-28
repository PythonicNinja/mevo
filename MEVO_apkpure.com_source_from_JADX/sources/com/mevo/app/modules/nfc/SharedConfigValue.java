package com.mevo.app.modules.nfc;

import android.support.annotation.VisibleForTesting;
import com.google.gson.Gson;
import com.inverce.mod.core.configuration.Value;
import com.inverce.mod.core.configuration.shared.SharedStringValue;
import com.mevo.app.data.network.Rest;

@VisibleForTesting(otherwise = 3)
public class SharedConfigValue extends Value<NfcConfiguration> {
    private SharedStringValue prefs;

    public SharedConfigValue(String str, NfcConfiguration nfcConfiguration) {
        this.prefs = new SharedStringValue(str, gson().toJson((Object) nfcConfiguration));
        setSetter(new SharedConfigValue$$Lambda$0(this));
        setGetter(new SharedConfigValue$$Lambda$1(this));
    }

    final /* synthetic */ void lambda$new$101$SharedConfigValue(NfcConfiguration nfcConfiguration) {
        this.prefs.set(gson().toJson((Object) nfcConfiguration));
    }

    final /* synthetic */ NfcConfiguration lambda$new$102$SharedConfigValue() {
        return (NfcConfiguration) gson().fromJson((String) this.prefs.get(), NfcConfiguration.class);
    }

    private Gson gson() {
        return Rest.getJsonSerializer();
    }
}
