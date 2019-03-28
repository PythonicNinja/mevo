package com.mevo.app.data.repository;

import com.mevo.app.data.repository.NextbikeApiRepository.ResponseListener;

final /* synthetic */ class TariffRepository$$Lambda$0 implements Runnable {
    private final TariffRepository arg$1;
    private final ResponseListener arg$2;

    TariffRepository$$Lambda$0(TariffRepository tariffRepository, ResponseListener responseListener) {
        this.arg$1 = tariffRepository;
        this.arg$2 = responseListener;
    }

    public void run() {
        this.arg$1.lambda$getCurrentTariff$67$TariffRepository(this.arg$2);
    }
}
