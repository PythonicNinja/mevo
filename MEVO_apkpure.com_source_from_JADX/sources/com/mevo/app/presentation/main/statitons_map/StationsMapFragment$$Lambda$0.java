package com.mevo.app.presentation.main.statitons_map;

final /* synthetic */ class StationsMapFragment$$Lambda$0 implements Runnable {
    private final StationsMapFragment arg$1;

    StationsMapFragment$$Lambda$0(StationsMapFragment stationsMapFragment) {
        this.arg$1 = stationsMapFragment;
    }

    public void run() {
        this.arg$1.refreshAll();
    }
}
