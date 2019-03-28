package com.google.maps.android.data.kml;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.Geometry;
import java.util.HashMap;

public class KmlPlacemark extends Feature {
    private final KmlStyle mInlineStyle;
    private final String mStyle;

    public KmlPlacemark(Geometry geometry, String str, KmlStyle kmlStyle, HashMap<String, String> hashMap) {
        super(geometry, str, hashMap);
        this.mStyle = str;
        this.mInlineStyle = kmlStyle;
    }

    public String getStyleId() {
        return super.getId();
    }

    public KmlStyle getInlineStyle() {
        return this.mInlineStyle;
    }

    public PolygonOptions getPolygonOptions() {
        return this.mInlineStyle.getPolygonOptions();
    }

    public MarkerOptions getMarkerOptions() {
        return this.mInlineStyle.getMarkerOptions();
    }

    public PolylineOptions getPolylineOptions() {
        return this.mInlineStyle.getPolylineOptions();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Placemark");
        stringBuilder.append("{");
        stringBuilder.append("\n style id=");
        stringBuilder.append(this.mStyle);
        stringBuilder.append(",\n inline style=");
        stringBuilder.append(this.mInlineStyle);
        stringBuilder.append("\n}\n");
        return stringBuilder.toString();
    }
}
