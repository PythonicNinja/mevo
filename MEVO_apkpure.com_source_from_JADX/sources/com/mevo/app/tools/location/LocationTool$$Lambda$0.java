package com.mevo.app.tools.location;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

final /* synthetic */ class LocationTool$$Lambda$0 implements Runnable {
    private final LocationCallback arg$1;
    private final Location arg$2;
    private final LocationManager arg$3;
    private final LocationListener arg$4;

    LocationTool$$Lambda$0(LocationCallback locationCallback, Location location, LocationManager locationManager, LocationListener locationListener) {
        this.arg$1 = locationCallback;
        this.arg$2 = location;
        this.arg$3 = locationManager;
        this.arg$4 = locationListener;
    }

    public void run() {
        LocationTool.lambda$onReceiveCurrentLocation$335$LocationTool(this.arg$1, this.arg$2, this.arg$3, this.arg$4);
    }
}
