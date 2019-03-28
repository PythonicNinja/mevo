package com.mevo.app.presentation.main.statitons_map;

import com.mevo.app.data.model.Place;
import com.mevo.app.presentation.dialogs.BikeReservationDialog.ReservationDecisionListener;

final /* synthetic */ class StationsMapFragment$$Lambda$7 implements ReservationDecisionListener {
    private final Place arg$1;

    StationsMapFragment$$Lambda$7(Place place) {
        this.arg$1 = place;
    }

    public void onBookingDecision(boolean z, String str) {
        StationsMapFragment.lambda$null$266$StationsMapFragment(this.arg$1, z, str);
    }
}
