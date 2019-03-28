package com.mevo.app.tools.location;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.inverce.mod.core.IM;
import com.mevo.app.tools.Log;
import com.mevo.app.tools.Permissions;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LocationTool extends LocationListenerAdapter {
    private static final int LOCATION_REQUEST_TIMEOUT = 10000;
    private static final int MAXIMUM_LOCATION_AGE = 30000;
    private static final int MAXIMUM_LOCATION_AGE_SMALL = 10000;
    private static final int MINIMUM_LOCATION_ACCURACY = 100;
    private static final int MIN_DISPLACEMENT_METERS = 5;
    private static final long MIN_UPDATE_INTERVAL = 2000;
    private static final String TAG = "LocationTool";
    private static Location currentLocation;
    private static LocationTool instance;
    private static long lastUpdateTimestamp;
    private LocationManager locationManager;

    /* renamed from: com.mevo.app.tools.location.LocationTool$1 */
    class C08511 implements LocationCallback {
        C08511() {
        }

        public void onReceive(@Nullable Location location) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("received 1st location: ");
            stringBuilder.append(String.valueOf(location));
            Log.m102w("Location tool", stringBuilder.toString());
        }
    }

    public static LocationTool get() {
        if (instance == null) {
            instance = new LocationTool();
        }
        return instance;
    }

    private static List<String> getProvidersNotPassive(LocationManager locationManager) {
        locationManager = locationManager.getProviders(true);
        if (locationManager == null) {
            return new ArrayList();
        }
        locationManager.remove("passive");
        return locationManager;
    }

    public static boolean areLocationServicesEnabled() {
        LocationManager locationManager = (LocationManager) IM.context().getApplicationContext().getSystemService(Param.LOCATION);
        boolean z = false;
        if (locationManager != null) {
            for (String str : getProvidersNotPassive(locationManager)) {
                if (str.equals("gps") || str.equals("network")) {
                    z = true;
                }
            }
        }
        return z;
    }

    private LocationTool() {
    }

    private String getBestAvilableProvider() {
        if (this.locationManager == null) {
            this.locationManager = (LocationManager) IM.context().getApplicationContext().getSystemService(Param.LOCATION);
        }
        ArrayList arrayList = new ArrayList(this.locationManager.getAllProviders());
        if (arrayList.contains("gps")) {
            return "gps";
        }
        if (arrayList.contains("network")) {
            return "network";
        }
        return arrayList.contains("passive") ? "passive" : null;
    }

    public synchronized void requestLocationUpdates(LocationListener locationListener) {
        if (Permissions.hasPermissions(Permissions.PERMISSIONS_LOCATION, IM.activity())) {
            if (this.locationManager == null) {
                this.locationManager = (LocationManager) IM.context().getApplicationContext().getSystemService(Param.LOCATION);
            }
            String bestAvilableProvider = getBestAvilableProvider();
            if (bestAvilableProvider != null) {
                this.locationManager.requestLocationUpdates(bestAvilableProvider, MIN_UPDATE_INTERVAL, 5.0f, locationListener);
            }
        }
    }

    public synchronized void removeLocationUpdates(LocationListener locationListener) {
        if (Permissions.hasPermissions(Permissions.PERMISSIONS_LOCATION, IM.activity())) {
            if (this.locationManager == null) {
                this.locationManager = (LocationManager) IM.context().getApplicationContext().getSystemService(Param.LOCATION);
            }
            this.locationManager.removeUpdates(locationListener);
        }
    }

    public synchronized void start() {
        if (Permissions.hasPermissions(Permissions.PERMISSIONS_LOCATION, IM.activity())) {
            if (this.locationManager == null) {
                this.locationManager = (LocationManager) IM.context().getApplicationContext().getSystemService(Param.LOCATION);
            }
            ArrayList arrayList = new ArrayList(this.locationManager.getAllProviders());
            String str = null;
            if (arrayList.contains("gps")) {
                str = "gps";
            } else if (arrayList.contains("network")) {
                str = "network";
            } else if (arrayList.contains("passive")) {
                str = "passive";
            }
            String str2 = str;
            if (str2 != null) {
                this.locationManager.requestLocationUpdates(str2, MIN_UPDATE_INTERVAL, 5.0f, this);
                getCurrentLocationAsync(new C08511());
            }
        }
    }

    @Nullable
    public Location getLastKnownLocation() {
        if (!hasTimePassed(lastUpdateTimestamp, 30000)) {
            return currentLocation;
        }
        Location location = null;
        try {
            LocationManager locationManager = (LocationManager) IM.context().getSystemService(Param.LOCATION);
            Location lastKnownLocation = Permissions.hasPermissions(Permissions.PERMISSIONS_LOCATION, IM.activity()) ? locationManager.getLastKnownLocation(locationManager.getBestProvider(new Criteria(), false)) : null;
            if (lastKnownLocation == null) {
                for (String str : getProvidersNotPassive(locationManager)) {
                    if (lastKnownLocation == null) {
                        if (Permissions.hasPermissions(Permissions.PERMISSIONS_LOCATION, IM.activity())) {
                            lastKnownLocation = locationManager.getLastKnownLocation(str);
                        }
                        if (lastKnownLocation != null && (lastKnownLocation.getAccuracy() > 100.0f || hasTimePassed(lastKnownLocation.getTime(), 30000))) {
                            lastKnownLocation = null;
                        }
                    }
                }
            }
            location = lastKnownLocation;
        } catch (Throwable e) {
            Log.ex(TAG, e);
        }
        return location;
    }

    public synchronized void stop() {
        if (Permissions.hasPermissions(Permissions.PERMISSIONS_LOCATION, IM.activity())) {
            if (this.locationManager != null) {
                this.locationManager.removeUpdates(this);
            }
            this.locationManager = null;
        }
        instance = null;
    }

    public void getCurrentLocationAsync(@NonNull LocationCallback locationCallback) {
        LocationTool locationTool = this;
        LocationCallback locationCallback2 = locationCallback;
        if (!Permissions.hasPermissions(Permissions.PERMISSIONS_LOCATION, IM.activity())) {
            onReceiveCurrentLocation(null, locationCallback2, null, null);
        } else if (hasTimePassed(lastUpdateTimestamp, 10000) || currentLocation == null) {
            LocationManager locationManager = (LocationManager) IM.context().getApplicationContext().getSystemService(Param.LOCATION);
            List<String> providersNotPassive = getProvidersNotPassive(locationManager);
            if (providersNotPassive.size() > 0) {
                List arrayList = new ArrayList();
                final Object obj = new Object();
                final List list = arrayList;
                final LocationCallback locationCallback3 = locationCallback2;
                final LocationManager locationManager2 = locationManager;
                LocationListenerAdapter c08522 = new LocationListenerAdapter() {
                    /* JADX WARNING: inconsistent code. */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void onLocationChanged(android.location.Location r5) {
                        /*
                        r4 = this;
                        r0 = r2;
                        monitor-enter(r0);
                        r1 = r5.getAccuracy();	 Catch:{ all -> 0x0049 }
                        r2 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
                        r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1));
                        if (r1 >= 0) goto L_0x0047;
                    L_0x000d:
                        r1 = r5.getAccuracy();	 Catch:{ all -> 0x0049 }
                        r2 = 0;
                        r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1));
                        if (r1 <= 0) goto L_0x0047;
                    L_0x0016:
                        r1 = r3;	 Catch:{ all -> 0x0049 }
                        r1 = r1.isEmpty();	 Catch:{ all -> 0x0049 }
                        if (r1 == 0) goto L_0x0020;
                    L_0x001e:
                        monitor-exit(r0);	 Catch:{ all -> 0x0049 }
                        return;
                    L_0x0020:
                        r1 = com.mevo.app.tools.location.LocationTool.this;	 Catch:{ all -> 0x0049 }
                        r2 = r4;	 Catch:{ all -> 0x0049 }
                        r3 = r5;	 Catch:{ all -> 0x0049 }
                        r1.onReceiveCurrentLocation(r5, r2, r3, r4);	 Catch:{ all -> 0x0049 }
                        r1 = com.mevo.app.tools.location.LocationTool.this;	 Catch:{ all -> 0x0049 }
                        r1.saveCurrentLocation(r5);	 Catch:{ all -> 0x0049 }
                        r5 = r3;	 Catch:{ all -> 0x0049 }
                        r5 = r5.size();	 Catch:{ all -> 0x0049 }
                        if (r5 <= 0) goto L_0x0042;
                    L_0x0036:
                        r5 = r3;	 Catch:{ all -> 0x0049 }
                        r1 = 0;
                        r5 = r5.get(r1);	 Catch:{ all -> 0x0049 }
                        r5 = (java.util.concurrent.ScheduledFuture) r5;	 Catch:{ all -> 0x0049 }
                        r5.cancel(r1);	 Catch:{ all -> 0x0049 }
                    L_0x0042:
                        r5 = r3;	 Catch:{ all -> 0x0049 }
                        r5.clear();	 Catch:{ all -> 0x0049 }
                    L_0x0047:
                        monitor-exit(r0);	 Catch:{ all -> 0x0049 }
                        return;
                    L_0x0049:
                        r5 = move-exception;
                        monitor-exit(r0);	 Catch:{ all -> 0x0049 }
                        throw r5;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.mevo.app.tools.location.LocationTool.2.onLocationChanged(android.location.Location):void");
                    }
                };
                final LocationListenerAdapter locationListenerAdapter = c08522;
                arrayList.add(IM.onBg().schedule(new Runnable() {
                    public void run() {
                        synchronized (obj) {
                            if (list.isEmpty()) {
                                return;
                            }
                            list.clear();
                            LocationTool.this.onReceiveCurrentLocation(null, locationCallback3, locationManager2, locationListenerAdapter);
                        }
                    }
                }, 10000, TimeUnit.MILLISECONDS));
                for (String requestLocationUpdates : providersNotPassive) {
                    LocationManager locationManager3 = locationManager;
                    locationManager.requestLocationUpdates(requestLocationUpdates, 0, 0.0f, c08522);
                    locationManager = locationManager3;
                }
            } else {
                onReceiveCurrentLocation(null, locationCallback2, null, null);
            }
        } else {
            onReceiveCurrentLocation(currentLocation, locationCallback2, null, null);
        }
    }

    private void onReceiveCurrentLocation(Location location, LocationCallback locationCallback, LocationManager locationManager, LocationListener locationListener) {
        IM.onUi().execute(new LocationTool$$Lambda$0(locationCallback, location, locationManager, locationListener));
    }

    static final /* synthetic */ void lambda$onReceiveCurrentLocation$335$LocationTool(LocationCallback locationCallback, Location location, LocationManager locationManager, LocationListener locationListener) {
        if (locationCallback != null) {
            locationCallback.onReceive(location);
        }
        if (locationManager != null && locationListener != null) {
            locationManager.removeUpdates(locationListener);
        }
    }

    private void saveCurrentLocation(Location location) {
        currentLocation = location;
        lastUpdateTimestamp = System.currentTimeMillis();
    }

    public static LatLng toLatLng(Location location) {
        return new LatLng(location.getLatitude(), location.getLongitude());
    }

    public void onLocationChanged(Location location) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Received new location: ");
        stringBuilder.append(location.toString());
        Log.m94i(str, stringBuilder.toString());
        saveCurrentLocation(location);
    }

    private boolean hasTimePassed(long j, long j2) {
        return j + j2 < System.currentTimeMillis() ? 1 : 0;
    }

    public static double calculateDistanceMeters(LatLng latLng, Location location) {
        if (latLng != null) {
            if (location != null) {
                double d = latLng.latitude;
                double latitude = location.getLatitude();
                double d2 = latLng.longitude;
                latLng = location.getLongitude();
                double toRadians = Math.toRadians(latitude - d);
                toRadians /= 2.0d;
                latLng = Math.toRadians(latLng - d2) / 4611686018427387904;
                return (((double) 6371) * (Math.asin(Math.sqrt((Math.sin(toRadians) * Math.sin(toRadians)) + (((Math.cos(Math.toRadians(d)) * Math.cos(Math.toRadians(latitude))) * Math.sin(latLng)) * Math.sin(latLng)))) * 4611686018427387904)) * 0;
            }
        }
        return 0;
    }

    public static double calculateDistanceKm(LatLng latLng, LatLng latLng2) {
        if (latLng != null) {
            if (latLng2 != null) {
                double d = latLng.latitude;
                double d2 = latLng2.latitude;
                double d3 = latLng.longitude;
                latLng = latLng2.longitude;
                double toRadians = Math.toRadians(d2 - d);
                toRadians /= 2.0d;
                latLng = Math.toRadians(latLng - d3) / 4611686018427387904;
                return ((double) 6371) * (Math.asin(Math.sqrt((Math.sin(toRadians) * Math.sin(toRadians)) + (((Math.cos(Math.toRadians(d)) * Math.cos(Math.toRadians(d2))) * Math.sin(latLng)) * Math.sin(latLng)))) * 4611686018427387904);
            }
        }
        return 0;
    }
}
