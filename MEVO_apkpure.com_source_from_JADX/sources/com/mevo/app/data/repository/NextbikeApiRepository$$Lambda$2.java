package com.mevo.app.data.repository;

import com.mevo.app.data.model.RentalItem;
import com.mevo.app.data.repository.NextbikeApiRepository.SimpleResponseListener;

final /* synthetic */ class NextbikeApiRepository$$Lambda$2 implements Runnable {
    private final RentalItem arg$1;
    private final SimpleResponseListener arg$2;

    NextbikeApiRepository$$Lambda$2(RentalItem rentalItem, SimpleResponseListener simpleResponseListener) {
        this.arg$1 = rentalItem;
        this.arg$2 = simpleResponseListener;
    }

    public void run() {
        NextbikeApiRepository.m74x9ffe2ba1(this.arg$1, this.arg$2);
    }
}
