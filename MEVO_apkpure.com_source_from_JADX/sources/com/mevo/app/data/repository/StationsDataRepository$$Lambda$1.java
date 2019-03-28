package com.mevo.app.data.repository;

final /* synthetic */ class StationsDataRepository$$Lambda$1 implements Runnable {
    private final StationsDataRepository arg$1;
    private final boolean arg$2;

    StationsDataRepository$$Lambda$1(StationsDataRepository stationsDataRepository, boolean z) {
        this.arg$1 = stationsDataRepository;
        this.arg$2 = z;
    }

    public void run() {
        this.arg$1.lambda$informAllStationsDataListeners$65$StationsDataRepository(this.arg$2);
    }
}
