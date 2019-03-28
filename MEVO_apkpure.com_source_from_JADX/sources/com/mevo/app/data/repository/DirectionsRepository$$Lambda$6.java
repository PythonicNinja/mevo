package com.mevo.app.data.repository;

import com.mevo.app.data.model.RentalRoute;
import com.mevo.app.data.repository.DirectionsRepository.ResponseListener;

final /* synthetic */ class DirectionsRepository$$Lambda$6 implements Runnable {
    private final ResponseListener arg$1;
    private final RentalRoute arg$2;
    private final boolean arg$3;
    private final Exception arg$4;

    DirectionsRepository$$Lambda$6(ResponseListener responseListener, RentalRoute rentalRoute, boolean z, Exception exception) {
        this.arg$1 = responseListener;
        this.arg$2 = rentalRoute;
        this.arg$3 = z;
        this.arg$4 = exception;
    }

    public void run() {
        this.arg$1.onResponse(this.arg$2, this.arg$3, this.arg$4);
    }
}
