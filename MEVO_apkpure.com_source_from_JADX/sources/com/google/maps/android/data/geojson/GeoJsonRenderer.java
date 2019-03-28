package com.google.maps.android.data.geojson;

import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.Renderer;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class GeoJsonRenderer extends Renderer implements Observer {
    private static final Object FEATURE_NOT_ON_MAP = null;

    public GeoJsonRenderer(GoogleMap googleMap, HashMap<GeoJsonFeature, Object> hashMap) {
        super(googleMap, (HashMap) hashMap);
    }

    public void setMap(GoogleMap googleMap) {
        super.setMap(googleMap);
        for (Feature feature : super.getFeatures()) {
            redrawFeatureToMap((GeoJsonFeature) feature, googleMap);
        }
    }

    public void addLayerToMap() {
        if (!isLayerOnMap()) {
            setLayerVisibility(true);
            for (Feature feature : super.getFeatures()) {
                addFeature((GeoJsonFeature) feature);
            }
        }
    }

    public void addFeature(GeoJsonFeature geoJsonFeature) {
        super.addFeature(geoJsonFeature);
        if (isLayerOnMap()) {
            geoJsonFeature.addObserver(this);
        }
    }

    public void removeLayerFromMap() {
        if (isLayerOnMap()) {
            for (Feature feature : super.getFeatures()) {
                Renderer.removeFromMap(super.getAllFeatures().get(feature));
                feature.deleteObserver(this);
            }
            setLayerVisibility(false);
        }
    }

    public void removeFeature(GeoJsonFeature geoJsonFeature) {
        super.removeFeature(geoJsonFeature);
        if (super.getFeatures().contains(geoJsonFeature)) {
            geoJsonFeature.deleteObserver(this);
        }
    }

    private void redrawFeatureToMap(GeoJsonFeature geoJsonFeature) {
        redrawFeatureToMap(geoJsonFeature, getMap());
    }

    private void redrawFeatureToMap(GeoJsonFeature geoJsonFeature, GoogleMap googleMap) {
        Renderer.removeFromMap(getAllFeatures().get(geoJsonFeature));
        putFeatures(geoJsonFeature, FEATURE_NOT_ON_MAP);
        if (googleMap != null && geoJsonFeature.hasGeometry() != null) {
            putFeatures(geoJsonFeature, addGeoJsonFeatureToMap(geoJsonFeature, geoJsonFeature.getGeometry()));
        }
    }

    public void update(Observable observable, Object obj) {
        if ((observable instanceof GeoJsonFeature) != null) {
            GeoJsonFeature geoJsonFeature = (GeoJsonFeature) observable;
            obj = getAllFeatures().get(geoJsonFeature) != FEATURE_NOT_ON_MAP ? true : null;
            if (obj != null && geoJsonFeature.hasGeometry()) {
                redrawFeatureToMap(geoJsonFeature);
            } else if (obj != null && !geoJsonFeature.hasGeometry()) {
                Renderer.removeFromMap(getAllFeatures().get(geoJsonFeature));
                putFeatures(geoJsonFeature, FEATURE_NOT_ON_MAP);
            } else if (obj == null && geoJsonFeature.hasGeometry() != null) {
                addFeature(geoJsonFeature);
            }
        }
    }
}
