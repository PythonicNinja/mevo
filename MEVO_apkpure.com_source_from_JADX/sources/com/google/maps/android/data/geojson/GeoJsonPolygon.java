package com.google.maps.android.data.geojson;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.data.DataPolygon;
import java.util.ArrayList;
import java.util.List;

public class GeoJsonPolygon implements DataPolygon {
    private static final String GEOMETRY_TYPE = "Polygon";
    private static final int POLYGON_INNER_COORDINATE_INDEX = 1;
    private static final int POLYGON_OUTER_COORDINATE_INDEX = 0;
    private final List<? extends List<LatLng>> mCoordinates;

    public String getType() {
        return "Polygon";
    }

    public GeoJsonPolygon(List<? extends List<LatLng>> list) {
        if (list == null) {
            throw new IllegalArgumentException("Coordinates cannot be null");
        }
        this.mCoordinates = list;
    }

    public List<? extends List<LatLng>> getCoordinates() {
        return this.mCoordinates;
    }

    public List<? extends List<LatLng>> getGeometryObject() {
        return getCoordinates();
    }

    public String getGeometryType() {
        return getType();
    }

    public ArrayList<LatLng> getOuterBoundaryCoordinates() {
        return (ArrayList) getCoordinates().get(0);
    }

    public ArrayList<ArrayList<LatLng>> getInnerBoundaryCoordinates() {
        ArrayList<ArrayList<LatLng>> arrayList = new ArrayList();
        for (int i = 1; i < getCoordinates().size(); i++) {
            arrayList.add((ArrayList) getCoordinates().get(i));
        }
        return arrayList;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Polygon");
        stringBuilder.append("{");
        stringBuilder.append("\n coordinates=");
        stringBuilder.append(this.mCoordinates);
        stringBuilder.append("\n}\n");
        return stringBuilder.toString();
    }
}
