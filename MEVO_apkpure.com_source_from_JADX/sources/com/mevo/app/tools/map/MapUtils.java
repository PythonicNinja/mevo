package com.mevo.app.tools.map;

import android.location.Location;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.PolyUtil;
import com.google.maps.android.data.Geometry;
import com.google.maps.android.data.geojson.GeoJsonFeature;
import com.google.maps.android.data.geojson.GeoJsonLayer;
import com.google.maps.android.data.geojson.GeoJsonPolygon;
import com.mevo.app.tools.location.LocationTool;

public class MapUtils {
    public static void setMapPosToMevo(GoogleMap googleMap) {
        if (googleMap != null) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(new LatLngBounds(new LatLng(54.297823d, 18.378381d), new LatLng(54.50628d, 18.994483d)), 0));
        }
    }

    public static void setMapPos(GoogleMap googleMap, LatLng latLng, LatLng latLng2) {
        if (googleMap != null) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(new LatLngBounds(latLng2, latLng), null));
        }
    }

    public static boolean checkIfPointInLayer(GeoJsonLayer geoJsonLayer, Location location) {
        if (geoJsonLayer != null) {
            if (location != null) {
                for (GeoJsonFeature geometry : geoJsonLayer.getFeatures()) {
                    if (checkIfPointInGeometry(geometry.getGeometry(), LocationTool.toLatLng(location))) {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    private static boolean checkIfPointInGeometry(Geometry geometry, LatLng latLng) {
        if (!(geometry instanceof GeoJsonPolygon) || PolyUtil.containsLocation(latLng, ((GeoJsonPolygon) geometry).getOuterBoundaryCoordinates(), false) == null) {
            return false;
        }
        return true;
    }
}
