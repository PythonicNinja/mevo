package com.google.maps.android.data;

import com.google.android.gms.maps.model.LatLng;
import java.util.List;

public class LineString implements Geometry<List<LatLng>> {
    private static final String GEOMETRY_TYPE = "LineString";
    private final List<LatLng> mCoordinates;

    public String getGeometryType() {
        return GEOMETRY_TYPE;
    }

    public LineString(List<LatLng> list) {
        if (list == null) {
            throw new IllegalArgumentException("Coordinates cannot be null");
        }
        this.mCoordinates = list;
    }

    public List<LatLng> getGeometryObject() {
        return this.mCoordinates;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(GEOMETRY_TYPE);
        stringBuilder.append("{");
        stringBuilder.append("\n coordinates=");
        stringBuilder.append(this.mCoordinates);
        stringBuilder.append("\n}\n");
        return stringBuilder.toString();
    }
}
