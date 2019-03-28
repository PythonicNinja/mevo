package com.mevo.app.presentation.main.statitons_map.utils;

import com.mevo.app.presentation.main.statitons_map.utils.StationsMapHelper.MapCenteredListener;
import java.util.List;

final /* synthetic */ class StationsMapHelper$$Lambda$1 implements Runnable {
    private final StationsMapHelper arg$1;
    private final List arg$2;
    private final MapCenteredListener arg$3;

    StationsMapHelper$$Lambda$1(StationsMapHelper stationsMapHelper, List list, MapCenteredListener mapCenteredListener) {
        this.arg$1 = stationsMapHelper;
        this.arg$2 = list;
        this.arg$3 = mapCenteredListener;
    }

    public void run() {
        this.arg$1.lambda$centerOnPlaceAfterMapPrepared$271$StationsMapHelper(this.arg$2, this.arg$3);
    }
}
