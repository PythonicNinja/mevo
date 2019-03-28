package com.mevo.app.presentation.main.statitons_map;

import com.mevo.app.data.model.response.ResponseFlexzones;
import com.mevo.app.data.repository.NextbikeApiRepository.ResponseListener;

final /* synthetic */ class StationsMapFragment$$Lambda$6 implements ResponseListener {
    private final StationsMapFragment arg$1;

    StationsMapFragment$$Lambda$6(StationsMapFragment stationsMapFragment) {
        this.arg$1 = stationsMapFragment;
    }

    public void onResponse(Object obj, boolean z, Exception exception) {
        this.arg$1.lambda$setupFlexzonesIfShould$268$StationsMapFragment((ResponseFlexzones) obj, z, exception);
    }
}
