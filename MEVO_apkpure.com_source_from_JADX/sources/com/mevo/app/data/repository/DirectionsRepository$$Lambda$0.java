package com.mevo.app.data.repository;

import com.mevo.app.data.repository.DirectionsRepository.ResponseListener;
import java.util.List;

final /* synthetic */ class DirectionsRepository$$Lambda$0 implements Runnable {
    private final DirectionsRepository arg$1;
    private final List arg$2;
    private final ResponseListener arg$3;

    DirectionsRepository$$Lambda$0(DirectionsRepository directionsRepository, List list, ResponseListener responseListener) {
        this.arg$1 = directionsRepository;
        this.arg$2 = list;
        this.arg$3 = responseListener;
    }

    public void run() {
        this.arg$1.lambda$getRoutesForRentalItems$10$DirectionsRepository(this.arg$2, this.arg$3);
    }
}
