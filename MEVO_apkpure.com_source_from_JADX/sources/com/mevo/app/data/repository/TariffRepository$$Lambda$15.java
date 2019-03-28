package com.mevo.app.data.repository;

import com.mevo.app.data.repository.NextbikeApiRepository.SimpleResponseListener;

final /* synthetic */ class TariffRepository$$Lambda$15 implements Runnable {
    private final SimpleResponseListener arg$1;

    TariffRepository$$Lambda$15(SimpleResponseListener simpleResponseListener) {
        this.arg$1 = simpleResponseListener;
    }

    public void run() {
        this.arg$1.onResponse(false, new Exception("Error occurred"));
    }
}
