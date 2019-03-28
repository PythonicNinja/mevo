package com.google.maps.android.data.kml;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.data.DataPolygon;
import java.util.ArrayList;
import java.util.List;

public class KmlPolygon implements DataPolygon<ArrayList<ArrayList<LatLng>>> {
    public static final String GEOMETRY_TYPE = "Polygon";
    private final List<List<LatLng>> mInnerBoundaryCoordinates;
    private final List<LatLng> mOuterBoundaryCoordinates;

    public String getGeometryType() {
        return GEOMETRY_TYPE;
    }

    public KmlPolygon(List<LatLng> list, List<List<LatLng>> list2) {
        if (list == null) {
            throw new IllegalArgumentException("Outer boundary coordinates cannot be null");
        }
        this.mOuterBoundaryCoordinates = list;
        this.mInnerBoundaryCoordinates = list2;
    }

    public List<List<LatLng>> getGeometryObject() {
        List<List<LatLng>> arrayList = new ArrayList();
        arrayList.add(this.mOuterBoundaryCoordinates);
        if (this.mInnerBoundaryCoordinates != null) {
            arrayList.addAll(this.mInnerBoundaryCoordinates);
        }
        return arrayList;
    }

    public List<LatLng> getOuterBoundaryCoordinates() {
        return this.mOuterBoundaryCoordinates;
    }

    public List<List<LatLng>> getInnerBoundaryCoordinates() {
        return this.mInnerBoundaryCoordinates;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(GEOMETRY_TYPE);
        stringBuilder.append("{");
        stringBuilder.append("\n outer coordinates=");
        stringBuilder.append(this.mOuterBoundaryCoordinates);
        stringBuilder.append(",\n inner coordinates=");
        stringBuilder.append(this.mInnerBoundaryCoordinates);
        stringBuilder.append("\n}\n");
        return stringBuilder.toString();
    }
}
