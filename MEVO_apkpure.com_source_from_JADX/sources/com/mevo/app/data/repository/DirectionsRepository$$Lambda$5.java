package com.mevo.app.data.repository;

import android.location.Location;
import com.google.android.gms.maps.model.LatLng;
import com.mevo.app.data.repository.DirectionsRepository.DirectionsResponseListener;
import com.mevo.app.tools.location.LocationCallback;

final /* synthetic */ class DirectionsRepository$$Lambda$5 implements LocationCallback {
    private final DirectionsRepository arg$1;
    private final LatLng arg$2;
    private final DirectionsResponseListener arg$3;

    DirectionsRepository$$Lambda$5(DirectionsRepository directionsRepository, LatLng latLng, DirectionsResponseListener directionsResponseListener) {
        this.arg$1 = directionsRepository;
        this.arg$2 = latLng;
        this.arg$3 = directionsResponseListener;
    }

    public void onReceive(Location location) {
        this.arg$1.lambda$getDirections$17$DirectionsRepository(this.arg$2, this.arg$3, location);
    }
}
