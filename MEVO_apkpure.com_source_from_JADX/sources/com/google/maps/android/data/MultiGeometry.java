package com.google.maps.android.data;

import java.util.ArrayList;
import java.util.List;

public class MultiGeometry implements Geometry {
    private String geometryType = "MultiGeometry";
    private List<Geometry> mGeometries;

    public MultiGeometry(List<? extends Geometry> list) {
        if (list == null) {
            throw new IllegalArgumentException("Geometries cannot be null");
        }
        List arrayList = new ArrayList();
        for (Geometry add : list) {
            arrayList.add(add);
        }
        this.mGeometries = arrayList;
    }

    public String getGeometryType() {
        return this.geometryType;
    }

    public List<Geometry> getGeometryObject() {
        return this.mGeometries;
    }

    public void setGeometryType(String str) {
        this.geometryType = str;
    }

    public String toString() {
        String str = "Geometries=";
        if (this.geometryType.equals("MultiPoint")) {
            str = "LineStrings=";
        }
        if (this.geometryType.equals("MultiLineString")) {
            str = "points=";
        }
        if (this.geometryType.equals("MultiPolygon")) {
            str = "Polygons=";
        }
        StringBuilder stringBuilder = new StringBuilder(getGeometryType());
        stringBuilder.append("{");
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("\n ");
        stringBuilder2.append(str);
        stringBuilder.append(stringBuilder2.toString());
        stringBuilder.append(getGeometryObject());
        stringBuilder.append("\n}\n");
        return stringBuilder.toString();
    }
}
