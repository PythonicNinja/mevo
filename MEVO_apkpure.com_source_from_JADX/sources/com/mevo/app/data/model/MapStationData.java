package com.mevo.app.data.model;

import android.support.annotation.Nullable;
import com.google.android.gms.maps.model.LatLng;
import java.util.List;

public class MapStationData {
    public final Place place;
    @Nullable
    public final List<LatLng> routePoints;
    public final StationJson stationJson;
    @Nullable
    public final Long walkToStationDurationSeconds;

    public MapStationData(Place place, StationJson stationJson, @Nullable List<LatLng> list, @Nullable Long l) {
        this.place = place;
        this.stationJson = stationJson;
        this.routePoints = list;
        this.walkToStationDurationSeconds = l;
    }
}
