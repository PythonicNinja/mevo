package com.mevo.app.presentation.main.statitons_map.utils;

import com.annimon.stream.function.Predicate;
import com.mevo.app.data.model.Place;

final /* synthetic */ class StationsMapHelper$$Lambda$2 implements Predicate {
    private final StationsMapHelper arg$1;

    StationsMapHelper$$Lambda$2(StationsMapHelper stationsMapHelper) {
        this.arg$1 = stationsMapHelper;
    }

    public boolean test(Object obj) {
        return this.arg$1.lambda$null$270$StationsMapHelper((Place) obj);
    }
}
