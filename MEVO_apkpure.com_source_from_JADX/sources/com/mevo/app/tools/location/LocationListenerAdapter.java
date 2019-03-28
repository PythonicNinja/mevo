package com.mevo.app.tools.location;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public abstract class LocationListenerAdapter implements LocationListener {
    public void onLocationChanged(Location location) {
    }

    public void onProviderDisabled(String str) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }
}
