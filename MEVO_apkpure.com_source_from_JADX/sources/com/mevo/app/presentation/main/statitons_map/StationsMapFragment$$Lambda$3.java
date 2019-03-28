package com.mevo.app.presentation.main.statitons_map;

import com.mevo.app.presentation.main.statitons_map.utils.StationsMapHelper.MapCenteredListener;

final /* synthetic */ class StationsMapFragment$$Lambda$3 implements MapCenteredListener {
    private final StationsMapFragment arg$1;

    StationsMapFragment$$Lambda$3(StationsMapFragment stationsMapFragment) {
        this.arg$1 = stationsMapFragment;
    }

    public void onMapCentered(boolean z) {
        this.arg$1.lambda$centerOnPlaceAfterMapPrepared$263$StationsMapFragment(z);
    }
}
