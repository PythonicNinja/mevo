package com.mevo.app.data.model;

import android.location.Location;
import com.mevo.app.tools.location.LocationTool;
import java.util.Comparator;

final /* synthetic */ class StationJson$Sort$$Lambda$1 implements Comparator {
    private final Location arg$1;

    StationJson$Sort$$Lambda$1(Location location) {
        this.arg$1 = location;
    }

    public int compare(Object obj, Object obj2) {
        return Double.compare(LocationTool.calculateDistanceMeters(((StationJson) obj).getPosition(), this.arg$1), LocationTool.calculateDistanceMeters(((StationJson) obj2).getPosition(), this.arg$1));
    }
}
