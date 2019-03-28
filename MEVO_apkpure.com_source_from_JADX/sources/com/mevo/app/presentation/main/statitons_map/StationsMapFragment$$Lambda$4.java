package com.mevo.app.presentation.main.statitons_map;

import com.google.android.gms.maps.model.Marker;
import com.mevo.app.data.model.MapStationData;
import com.mevo.app.data.repository.NextbikeApiRepository.ResponseListener;

final /* synthetic */ class StationsMapFragment$$Lambda$4 implements ResponseListener {
    private final StationsMapFragment arg$1;
    private final Marker arg$2;

    StationsMapFragment$$Lambda$4(StationsMapFragment stationsMapFragment, Marker marker) {
        this.arg$1 = stationsMapFragment;
        this.arg$2 = marker;
    }

    public void onResponse(Object obj, boolean z, Exception exception) {
        this.arg$1.lambda$onStationPinClick$264$StationsMapFragment(this.arg$2, (MapStationData) obj, z, exception);
    }
}
