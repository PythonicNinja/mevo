package com.mevo.app.data.repository;

import com.mevo.app.data.exceptions.InsufficientFundsException;
import com.mevo.app.data.repository.NextbikeApiRepository.SimpleResponseListener;

final /* synthetic */ class TariffRepository$$Lambda$1 implements Runnable {
    private final SimpleResponseListener arg$1;

    TariffRepository$$Lambda$1(SimpleResponseListener simpleResponseListener) {
        this.arg$1 = simpleResponseListener;
    }

    public void run() {
        this.arg$1.onResponse(false, new InsufficientFundsException());
    }
}
