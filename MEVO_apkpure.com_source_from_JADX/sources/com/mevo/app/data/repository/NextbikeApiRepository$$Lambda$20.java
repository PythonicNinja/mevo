package com.mevo.app.data.repository;

import com.mevo.app.data.repository.NextbikeApiRepository.ResponseListener;

final /* synthetic */ class NextbikeApiRepository$$Lambda$20 implements Runnable {
    private final ResponseListener arg$1;
    private final Exception arg$2;

    NextbikeApiRepository$$Lambda$20(ResponseListener responseListener, Exception exception) {
        this.arg$1 = responseListener;
        this.arg$2 = exception;
    }

    public void run() {
        this.arg$1.onResponse(null, false, this.arg$2);
    }
}
