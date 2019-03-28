package com.mevo.app.presentation.main.statitons_map;

import com.mevo.app.data.repository.StationsDataRepository.StationsDataListener;
import java.util.List;

final /* synthetic */ class StationsMapFragment$$Lambda$2 implements StationsDataListener {
    private final StationsMapFragment arg$1;

    StationsMapFragment$$Lambda$2(StationsMapFragment stationsMapFragment) {
        this.arg$1 = stationsMapFragment;
    }

    public void onDataReceived(boolean z, List list) {
        this.arg$1.lambda$fetchStationsData$262$StationsMapFragment(z, list);
    }
}
