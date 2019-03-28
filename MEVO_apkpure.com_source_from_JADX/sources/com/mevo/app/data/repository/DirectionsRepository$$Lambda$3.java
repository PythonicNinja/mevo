package com.mevo.app.data.repository;

import com.annimon.stream.function.Predicate;
import com.mevo.app.data.model.response.ResponseLatLon;

final /* synthetic */ class DirectionsRepository$$Lambda$3 implements Predicate {
    static final Predicate $instance = new DirectionsRepository$$Lambda$3();

    private DirectionsRepository$$Lambda$3() {
    }

    public boolean test(Object obj) {
        return DirectionsRepository.lambda$getRouteForRentalItemSync$15$DirectionsRepository((ResponseLatLon) obj);
    }
}
