package com.mevo.app.data.repository;

import com.annimon.stream.function.Function;
import com.google.android.gms.maps.model.LatLng;
import com.mevo.app.data.model.response.ResponseLatLon;

final /* synthetic */ class DirectionsRepository$$Lambda$4 implements Function {
    static final Function $instance = new DirectionsRepository$$Lambda$4();

    private DirectionsRepository$$Lambda$4() {
    }

    public Object apply(Object obj) {
        return new LatLng(((ResponseLatLon) obj).lat, ((ResponseLatLon) obj).lon);
    }
}
