package com.mevo.app.data.repository;

import com.mevo.app.data.repository.NextbikeApiRepository.ResponseListener;

final /* synthetic */ class NextbikeApiRepository$$Lambda$18 implements Runnable {
    private final ResponseListener arg$1;
    private final String arg$2;

    NextbikeApiRepository$$Lambda$18(ResponseListener responseListener, String str) {
        this.arg$1 = responseListener;
        this.arg$2 = str;
    }

    public void run() {
        this.arg$1.onResponse(this.arg$2, true, null);
    }
}
