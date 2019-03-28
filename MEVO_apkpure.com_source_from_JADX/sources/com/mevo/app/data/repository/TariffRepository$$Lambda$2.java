package com.mevo.app.data.repository;

import com.mevo.app.data.model.Tariff;
import com.mevo.app.data.repository.NextbikeApiRepository.SimpleResponseListener;

final /* synthetic */ class TariffRepository$$Lambda$2 implements Runnable {
    private final TariffRepository arg$1;
    private final SimpleResponseListener arg$2;
    private final Tariff arg$3;

    TariffRepository$$Lambda$2(TariffRepository tariffRepository, SimpleResponseListener simpleResponseListener, Tariff tariff) {
        this.arg$1 = tariffRepository;
        this.arg$2 = simpleResponseListener;
        this.arg$3 = tariff;
    }

    public void run() {
        this.arg$1.lambda$purchaseTariff$76$TariffRepository(this.arg$2, this.arg$3);
    }
}
