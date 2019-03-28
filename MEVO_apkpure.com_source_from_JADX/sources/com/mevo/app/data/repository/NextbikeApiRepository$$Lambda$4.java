package com.mevo.app.data.repository;

import com.mevo.app.data.repository.NextbikeApiRepository.ResponseListener;
import java.util.List;

final /* synthetic */ class NextbikeApiRepository$$Lambda$4 implements Runnable {
    private final List arg$1;
    private final ResponseListener arg$2;
    private final String arg$3;

    NextbikeApiRepository$$Lambda$4(List list, ResponseListener responseListener, String str) {
        this.arg$1 = list;
        this.arg$2 = responseListener;
        this.arg$3 = str;
    }

    public void run() {
        NextbikeApiRepository.lambda$getPaymentForm$39$NextbikeApiRepository(this.arg$1, this.arg$2, this.arg$3);
    }
}
