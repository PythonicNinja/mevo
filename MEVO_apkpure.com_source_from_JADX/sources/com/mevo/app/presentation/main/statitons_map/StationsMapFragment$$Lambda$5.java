package com.mevo.app.presentation.main.statitons_map;

import com.mevo.app.data.model.Place;
import com.mevo.app.data.repository.NextbikeApiRepository.BookingAvailabilityListener;

final /* synthetic */ class StationsMapFragment$$Lambda$5 implements BookingAvailabilityListener {
    private final StationsMapFragment arg$1;
    private final Place arg$2;

    StationsMapFragment$$Lambda$5(StationsMapFragment stationsMapFragment, Place place) {
        this.arg$1 = stationsMapFragment;
        this.arg$2 = place;
    }

    public void onResponse(boolean z, boolean z2) {
        this.arg$1.lambda$onStationInfoWindowClick$267$StationsMapFragment(this.arg$2, z, z2);
    }
}
