package com.mevo.app.presentation.main.statitons_map.utils;

import android.content.Context;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.annimon.stream.Stream;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraIdleListener;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.ClusterManager.OnClusterClickListener;
import com.google.maps.android.clustering.ClusterManager.OnClusterItemClickListener;
import com.google.maps.android.clustering.ClusterManager.OnClusterItemInfoWindowClickListener;
import com.google.maps.android.data.geojson.GeoJsonFeature;
import com.google.maps.android.data.geojson.GeoJsonLayer;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;
import com.inverce.mod.core.IM;
import com.mevo.app.Cfg;
import com.mevo.app.data.model.Domain;
import com.mevo.app.data.model.Place;
import com.mevo.app.presentation.main.statitons_map.adapters.StationWindowAdapter;
import com.mevo.app.tools.Tools;
import com.mevo.app.tools.UserManager;
import com.mevo.app.tools.location.LocationTool;
import com.mevo.app.tools.map.BikeClusterRenderer;
import com.mevo.app.tools.map.MapUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public class StationsMapHelper implements OnClusterClickListener<Place>, OnClusterItemClickListener<Place>, OnClusterItemInfoWindowClickListener<Place>, OnCameraIdleListener {
    private static final float DEFAULT_ZOOM = 15.5f;
    private ClusterManager<Place> clusterManager;
    private BikeClusterRenderer clusterRenderer;
    private GeoJsonLayer flexzonesMapLayer;
    private GoogleMap googleMap;
    private StationsMapListener listener;
    private boolean mapPrepared = false;
    private CameraPosition savedCameraPosition;
    private int userSelectedPlaceId;
    private LatLng userSelectedPlaceLatLng;

    public interface MapCenteredListener {
        void onMapCentered(boolean z);
    }

    public interface StationsMapListener {
        void onStationInfoWindowClick(Place place);

        void onStationPinClick(@NonNull Place place, @NonNull Marker marker);

        boolean onStationsClusterClick(Cluster<Place> cluster);
    }

    public StationsMapHelper(@NonNull StationsMapListener stationsMapListener) {
        this.listener = stationsMapListener;
    }

    public void onCreate(int i, LatLng latLng, CameraPosition cameraPosition) {
        this.userSelectedPlaceId = i;
        this.userSelectedPlaceLatLng = latLng;
        this.savedCameraPosition = cameraPosition;
    }

    public void onStop() {
        if (this.googleMap != null && this.mapPrepared) {
            this.savedCameraPosition = this.googleMap.getCameraPosition();
        }
    }

    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.setOnCameraIdleListener(this);
    }

    public void onDestroy() {
        if (this.googleMap != null) {
            this.googleMap.setOnMarkerClickListener(null);
            this.googleMap.setInfoWindowAdapter(null);
            this.googleMap.setOnInfoWindowClickListener(null);
        }
        if (this.clusterManager != null) {
            this.clusterManager.getMarkerCollection().setOnInfoWindowAdapter(null);
            this.clusterManager.setOnClusterClickListener(null);
            this.clusterManager.setOnClusterItemClickListener(null);
            this.clusterManager.setOnClusterItemInfoWindowClickListener(null);
        }
        this.googleMap = null;
        this.clusterManager = null;
        this.clusterRenderer = null;
        this.flexzonesMapLayer = null;
    }

    public boolean isMapPrepared() {
        return this.mapPrepared;
    }

    public Marker getMarkerForPlace(Place place) {
        if (this.clusterRenderer == null) {
            return null;
        }
        return this.clusterRenderer.getMarker((ClusterItem) place);
    }

    public void setStationsOnMap(@Nullable List<Place> list, Context context) {
        if (this.googleMap != null) {
            if (context != null) {
                if (list == null) {
                    list = new ArrayList();
                }
                if (this.clusterManager == null) {
                    this.clusterManager = new ClusterManager(context, this.googleMap);
                    this.clusterRenderer = new BikeClusterRenderer(context, this.googleMap, this.clusterManager);
                    this.clusterManager.setRenderer(this.clusterRenderer);
                    this.googleMap.setInfoWindowAdapter(this.clusterManager.getMarkerManager());
                    this.googleMap.setOnInfoWindowClickListener(this.clusterManager);
                    this.clusterManager.setOnClusterClickListener(this);
                    this.clusterManager.setOnClusterItemClickListener(this);
                    this.clusterManager.setOnClusterItemInfoWindowClickListener(this);
                }
                this.clusterManager.clearItems();
                this.clusterManager.cluster();
                IM.onUi().schedule(new StationsMapHelper$$Lambda$0(this, list, context), (long) 50, TimeUnit.MILLISECONDS);
            }
        }
    }

    final /* synthetic */ void lambda$setStationsOnMap$269$StationsMapHelper(List list, Context context) {
        if (this.clusterManager != null) {
            this.clusterManager.addItems(list);
            this.clusterManager.getMarkerCollection().setOnInfoWindowAdapter(new StationWindowAdapter(context));
            this.googleMap.setOnMarkerClickListener(this.clusterManager);
            this.clusterManager.cluster();
        }
    }

    public boolean onClusterClick(Cluster<Place> cluster) {
        return this.listener.onStationsClusterClick(cluster);
    }

    public boolean onClusterItemClick(Place place) {
        if (place != null) {
            if (this.googleMap != null) {
                Marker marker = this.clusterRenderer.getMarker((ClusterItem) place);
                if (marker == null) {
                    return true;
                }
                this.listener.onStationPinClick(place, marker);
                return true;
            }
        }
        return true;
    }

    public void onClusterItemInfoWindowClick(Place place) {
        this.listener.onStationInfoWindowClick(place);
    }

    public void onCameraIdle() {
        if (this.clusterManager != null) {
            this.clusterManager.onCameraIdle();
        }
        if (!this.mapPrepared) {
            centerOnStart();
            this.mapPrepared = true;
        }
    }

    private void centerOnStart() {
        if (this.googleMap != null) {
            if (didUserSelectPlaceToShow()) {
                this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(this.userSelectedPlaceLatLng, DEFAULT_ZOOM));
            } else if (this.savedCameraPosition != null) {
                this.googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(this.savedCameraPosition));
                this.savedCameraPosition = null;
            } else if (LocationTool.get().getLastKnownLocation() != null) {
                Location lastKnownLocation = LocationTool.get().getLastKnownLocation();
                this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude()), DEFAULT_ZOOM));
            } else {
                String domain = UserManager.getUser().getDomain();
                if (domain == null) {
                    domain = "";
                }
                Object obj = null;
                for (Domain domain2 : Cfg.get().bikeDomains()) {
                    if (domain2.getDomainCode() != null && domain2.getDomainCode().equalsIgnoreCase(r0)) {
                        MapUtils.setMapPos(this.googleMap, domain2.getNorthEast(), domain2.getSouthWest());
                        obj = 1;
                        break;
                    }
                }
                if (obj == null) {
                    MapUtils.setMapPos(this.googleMap, Cfg.get().domain().getNorthEast(), Cfg.get().domain().getSouthWest());
                }
            }
        }
    }

    public boolean didUserSelectPlaceToShow() {
        return this.userSelectedPlaceId > 0;
    }

    public void centerOnPlaceAfterMapPrepared(List<Place> list, MapCenteredListener mapCenteredListener) {
        IM.onBg().execute(new StationsMapHelper$$Lambda$1(this, list, mapCenteredListener));
    }

    final /* synthetic */ void lambda$centerOnPlaceAfterMapPrepared$271$StationsMapHelper(List list, final MapCenteredListener mapCenteredListener) {
        Place place = (Place) Stream.of((Iterable) list).filter(new StationsMapHelper$$Lambda$2(this)).findFirst().orElse(null);
        if (place != null) {
            centerOnPlaceAfterMapPreparedAsyncHelper(place, 200, System.currentTimeMillis() + 30000, mapCenteredListener);
        } else {
            IM.onUi().execute(new TimerTask() {
                public void run() {
                    mapCenteredListener.onMapCentered(false);
                }
            });
        }
    }

    final /* synthetic */ boolean lambda$null$270$StationsMapHelper(Place place) {
        return place.getUid() == this.userSelectedPlaceId ? true : null;
    }

    private void centerOnPlaceAfterMapPreparedAsyncHelper(Place place, int i, long j, MapCenteredListener mapCenteredListener) {
        final Place place2 = place;
        final MapCenteredListener mapCenteredListener2 = mapCenteredListener;
        final long j2 = j;
        final int i2 = i;
        IM.onBg().schedule(new TimerTask() {

            /* renamed from: com.mevo.app.presentation.main.statitons_map.utils.StationsMapHelper$2$1 */
            class C04531 extends TimerTask {
                C04531() {
                }

                public void run() {
                    if (StationsMapHelper.this.googleMap != null) {
                        if (StationsMapHelper.this.mapPrepared && StationsMapHelper.this.getMarkerForPlace(place2) != null) {
                            StationsMapHelper.this.listener.onStationInfoWindowClick(place2);
                            StationsMapHelper.this.userSelectedPlaceId = 0;
                            StationsMapHelper.this.userSelectedPlaceLatLng = null;
                            mapCenteredListener2.onMapCentered(true);
                        } else if (j2 > System.currentTimeMillis()) {
                            StationsMapHelper.this.centerOnPlaceAfterMapPreparedAsyncHelper(place2, i2, j2, mapCenteredListener2);
                        } else {
                            mapCenteredListener2.onMapCentered(false);
                        }
                    }
                }
            }

            public void run() {
                IM.onUi().execute(new C04531());
            }
        }, (long) i, TimeUnit.MILLISECONDS);
    }

    public void setFlexzoneLayerOnMap(JSONObject jSONObject) {
        if (this.flexzonesMapLayer != null) {
            this.flexzonesMapLayer.removeLayerFromMap();
        }
        this.flexzonesMapLayer = new GeoJsonLayer(this.googleMap, jSONObject);
        this.flexzonesMapLayer.addLayerToMap();
        for (GeoJsonFeature geoJsonFeature : this.flexzonesMapLayer.getFeatures()) {
            GeoJsonPolygonStyle geoJsonPolygonStyle = new GeoJsonPolygonStyle();
            if (geoJsonFeature.hasProperty("color")) {
                geoJsonPolygonStyle.setStrokeColor(Tools.adjustAlphaHex(geoJsonFeature.getProperty("color"), 0.6f));
            }
            if (geoJsonFeature.hasProperty("fill")) {
                geoJsonPolygonStyle.setFillColor(Tools.adjustAlphaHex(geoJsonFeature.getProperty("fill"), 0.2f));
            }
            geoJsonFeature.setPolygonStyle(geoJsonPolygonStyle);
        }
    }

    public GeoJsonLayer getFlexzonesMapLayer() {
        return this.flexzonesMapLayer;
    }
}
