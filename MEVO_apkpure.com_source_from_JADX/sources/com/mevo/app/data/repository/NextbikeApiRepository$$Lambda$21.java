package com.mevo.app.data.repository;

import com.mevo.app.data.repository.NextbikeApiRepository.SimpleResponseListener;

final /* synthetic */ class NextbikeApiRepository$$Lambda$21 implements Runnable {
    private final SimpleResponseListener arg$1;

    NextbikeApiRepository$$Lambda$21(SimpleResponseListener simpleResponseListener) {
        this.arg$1 = simpleResponseListener;
    }

    public void run() {
        this.arg$1.onResponse(true, null);
    }
}
