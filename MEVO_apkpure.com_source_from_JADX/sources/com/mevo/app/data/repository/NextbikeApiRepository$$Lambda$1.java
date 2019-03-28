package com.mevo.app.data.repository;

import com.mevo.app.data.model.RentalItem;
import com.mevo.app.data.repository.NextbikeApiRepository.SimpleResponseListener;

final /* synthetic */ class NextbikeApiRepository$$Lambda$1 implements Runnable {
    private final RentalItem arg$1;
    private final SimpleResponseListener arg$2;

    NextbikeApiRepository$$Lambda$1(RentalItem rentalItem, SimpleResponseListener simpleResponseListener) {
        this.arg$1 = rentalItem;
        this.arg$2 = simpleResponseListener;
    }

    public void run() {
        NextbikeApiRepository.lambda$cancelBookingForRental$29$NextbikeApiRepository(this.arg$1, this.arg$2);
    }
}
