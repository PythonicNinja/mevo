package com.mevo.app.data.repository;

import com.mevo.app.data.repository.NextbikeApiRepository.ResponseListener;

final /* synthetic */ class NextbikeApiRepository$$Lambda$19 implements Runnable {
    private final ResponseListener arg$1;

    NextbikeApiRepository$$Lambda$19(ResponseListener responseListener) {
        this.arg$1 = responseListener;
    }

    public void run() {
        this.arg$1.onResponse(null, false, null);
    }
}
