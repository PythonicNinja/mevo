package com.mevo.app.presentation.main.statitons_map.utils;

import android.content.Context;
import java.util.List;

final /* synthetic */ class StationsMapHelper$$Lambda$0 implements Runnable {
    private final StationsMapHelper arg$1;
    private final List arg$2;
    private final Context arg$3;

    StationsMapHelper$$Lambda$0(StationsMapHelper stationsMapHelper, List list, Context context) {
        this.arg$1 = stationsMapHelper;
        this.arg$2 = list;
        this.arg$3 = context;
    }

    public void run() {
        this.arg$1.lambda$setStationsOnMap$269$StationsMapHelper(this.arg$2, this.arg$3);
    }
}
