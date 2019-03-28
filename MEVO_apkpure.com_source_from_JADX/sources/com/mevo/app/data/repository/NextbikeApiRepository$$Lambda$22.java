package com.mevo.app.data.repository;

import com.mevo.app.data.repository.NextbikeApiRepository.SimpleResponseListener;

final /* synthetic */ class NextbikeApiRepository$$Lambda$22 implements Runnable {
    private final SimpleResponseListener arg$1;
    private final boolean arg$2;

    NextbikeApiRepository$$Lambda$22(SimpleResponseListener simpleResponseListener, boolean z) {
        this.arg$1 = simpleResponseListener;
        this.arg$2 = z;
    }

    public void run() {
        this.arg$1.onResponse(this.arg$2, null);
    }
}
