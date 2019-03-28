package com.mevo.app.data.repository;

import com.mevo.app.data.model.repository.ResponseActiveTariff;
import com.mevo.app.data.repository.NextbikeApiRepository.ResponseListener;

final /* synthetic */ class TariffRepository$$Lambda$22 implements Runnable {
    private final ResponseListener arg$1;
    private final ResponseActiveTariff arg$2;

    TariffRepository$$Lambda$22(ResponseListener responseListener, ResponseActiveTariff responseActiveTariff) {
        this.arg$1 = responseListener;
        this.arg$2 = responseActiveTariff;
    }

    public void run() {
        this.arg$1.onResponse(this.arg$2, this.arg$2.success, this.arg$2.error);
    }
}
