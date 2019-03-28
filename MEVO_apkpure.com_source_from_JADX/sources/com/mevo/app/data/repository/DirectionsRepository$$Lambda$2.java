package com.mevo.app.data.repository;

import com.mevo.app.data.model.RentalItem;
import com.mevo.app.data.repository.DirectionsRepository.ResponseListener;

final /* synthetic */ class DirectionsRepository$$Lambda$2 implements Runnable {
    private final DirectionsRepository arg$1;
    private final RentalItem arg$2;
    private final boolean arg$3;
    private final ResponseListener arg$4;

    DirectionsRepository$$Lambda$2(DirectionsRepository directionsRepository, RentalItem rentalItem, boolean z, ResponseListener responseListener) {
        this.arg$1 = directionsRepository;
        this.arg$2 = rentalItem;
        this.arg$3 = z;
        this.arg$4 = responseListener;
    }

    public void run() {
        this.arg$1.lambda$getRouteForRentalItem$14$DirectionsRepository(this.arg$2, this.arg$3, this.arg$4);
    }
}
