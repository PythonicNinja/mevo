package com.google.maps.android.data;

import com.google.android.gms.maps.model.LatLng;

public class Point implements Geometry {
    private static final String GEOMETRY_TYPE = "Point";
    private final LatLng mCoordinates;

    public String getGeometryType() {
        return GEOMETRY_TYPE;
    }

    public Point(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("Coordinates cannot be null");
        }
        this.mCoordinates = latLng;
    }

    public LatLng getGeometryObject() {
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
