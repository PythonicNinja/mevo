package com.google.firebase.iid;

import android.os.Handler.Callback;
import android.os.Message;

final /* synthetic */ class zzac implements Callback {
    private final zzab zzbt;

    zzac(zzab zzab) {
        this.zzbt = zzab;
    }

    public final boolean handleMessage(Message message) {
        return this.zzbt.zza(message);
    }
}
