package com.mevo.app.data.repository;

import com.mevo.app.data.model.MapStationData;
import com.mevo.app.data.model.Place;
import com.mevo.app.data.model.StationJson;
import com.mevo.app.data.repository.NextbikeApiRepository.ResponseListener;
import java.util.List;

final /* synthetic */ class NextbikeApiRepository$$Lambda$10 implements Runnable {
    private final ResponseListener arg$1;
    private final Place arg$2;
    private final StationJson arg$3;
    private final List arg$4;
    private final Long arg$5;

    NextbikeApiRepository$$Lambda$10(ResponseListener responseListener, Place place, StationJson stationJson, List list, Long l) {
        this.arg$1 = responseListener;
        this.arg$2 = place;
        this.arg$3 = stationJson;
        this.arg$4 = list;
        this.arg$5 = l;
    }

    public void run() {
        this.arg$1.onResponse(new MapStationData(this.arg$2, this.arg$3, this.arg$4, this.arg$5), true, null);
    }
}
