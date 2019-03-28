package com.mevo.app.data.repository;

import com.mevo.app.data.model.Place;
import com.mevo.app.data.repository.NextbikeApiRepository.ResponseListener;

final /* synthetic */ class NextbikeApiRepository$$Lambda$9 implements Runnable {
    private final Place arg$1;
    private final ResponseListener arg$2;

    NextbikeApiRepository$$Lambda$9(Place place, ResponseListener responseListener) {
        this.arg$1 = place;
        this.arg$2 = responseListener;
    }

    public void run() {
        NextbikeApiRepository.lambda$getStationExtraMapData$52$NextbikeApiRepository(this.arg$1, this.arg$2);
    }
}
