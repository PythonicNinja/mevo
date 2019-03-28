package com.mevo.app.data.repository;

import com.mevo.app.data.repository.NextbikeApiRepository.SimpleResponseListener;
import java.io.IOException;

final /* synthetic */ class TariffRepository$$Lambda$21 implements Runnable {
    private final SimpleResponseListener arg$1;
    private final IOException arg$2;

    TariffRepository$$Lambda$21(SimpleResponseListener simpleResponseListener, IOException iOException) {
        this.arg$1 = simpleResponseListener;
        this.arg$2 = iOException;
    }

    public void run() {
        this.arg$1.onResponse(false, this.arg$2);
    }
}
