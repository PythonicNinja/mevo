package com.google.maps.android.data.kml;

import com.google.maps.android.data.Geometry;
import com.google.maps.android.data.MultiGeometry;
import java.util.ArrayList;

public class KmlMultiGeometry extends MultiGeometry {
    public KmlMultiGeometry(ArrayList<Geometry> arrayList) {
        super(arrayList);
    }

    public ArrayList<Geometry> getGeometryObject() {
        return new ArrayList(super.getGeometryObject());
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(getGeometryType());
        stringBuilder.append("{");
        stringBuilder.append("\n geometries=");
        stringBuilder.append(getGeometryObject());
        stringBuilder.append("\n}\n");
        return stringBuilder.toString();
    }
}
