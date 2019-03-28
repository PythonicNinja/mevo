package com.google.maps.android.data.geojson;

import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.Geometry;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class GeoJsonFeature extends Feature implements Observer {
    private final LatLngBounds mBoundingBox;
    private final String mId;
    private GeoJsonLineStringStyle mLineStringStyle;
    private GeoJsonPointStyle mPointStyle;
    private GeoJsonPolygonStyle mPolygonStyle;

    public GeoJsonFeature(Geometry geometry, String str, HashMap<String, String> hashMap, LatLngBounds latLngBounds) {
        super(geometry, str, hashMap);
        this.mId = str;
        this.mBoundingBox = latLngBounds;
    }

    public String setProperty(String str, String str2) {
        return super.setProperty(str, str2);
    }

    public String removeProperty(String str) {
        return super.removeProperty(str);
    }

    public GeoJsonPointStyle getPointStyle() {
        return this.mPointStyle;
    }

    public void setPointStyle(GeoJsonPointStyle geoJsonPointStyle) {
        if (geoJsonPointStyle == null) {
            throw new IllegalArgumentException("Point style cannot be null");
        }
        if (this.mPointStyle != null) {
            this.mPointStyle.deleteObserver(this);
        }
        this.mPointStyle = geoJsonPointStyle;
        this.mPointStyle.addObserver(this);
        checkRedrawFeature(this.mPointStyle);
    }

    public GeoJsonLineStringStyle getLineStringStyle() {
        return this.mLineStringStyle;
    }

    public void setLineStringStyle(GeoJsonLineStringStyle geoJsonLineStringStyle) {
        if (geoJsonLineStringStyle == null) {
            throw new IllegalArgumentException("Line string style cannot be null");
        }
        if (this.mLineStringStyle != null) {
            this.mLineStringStyle.deleteObserver(this);
        }
        this.mLineStringStyle = geoJsonLineStringStyle;
        this.mLineStringStyle.addObserver(this);
        checkRedrawFeature(this.mLineStringStyle);
    }

    public GeoJsonPolygonStyle getPolygonStyle() {
        return this.mPolygonStyle;
    }

    public void setPolygonStyle(GeoJsonPolygonStyle geoJsonPolygonStyle) {
        if (geoJsonPolygonStyle == null) {
            throw new IllegalArgumentException("Polygon style cannot be null");
        }
        if (this.mPolygonStyle != null) {
            this.mPolygonStyle.deleteObserver(this);
        }
        this.mPolygonStyle = geoJsonPolygonStyle;
        this.mPolygonStyle.addObserver(this);
        checkRedrawFeature(this.mPolygonStyle);
    }

    public PolygonOptions getPolygonOptions() {
        return this.mPolygonStyle.toPolygonOptions();
    }

    public MarkerOptions getMarkerOptions() {
        return this.mPointStyle.toMarkerOptions();
    }

    public PolylineOptions getPolylineOptions() {
        return this.mLineStringStyle.toPolylineOptions();
    }

    private void checkRedrawFeature(GeoJsonStyle geoJsonStyle) {
        if (hasGeometry() && Arrays.asList(geoJsonStyle.getGeometryType()).contains(getGeometry().getGeometryType()) != null) {
            setChanged();
            notifyObservers();
        }
    }

    public void setGeometry(Geometry geometry) {
        super.setGeometry(geometry);
        setChanged();
        notifyObservers();
    }

    public LatLngBounds getBoundingBox() {
        return this.mBoundingBox;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Feature{");
        stringBuilder.append("\n bounding box=");
        stringBuilder.append(this.mBoundingBox);
        stringBuilder.append(",\n geometry=");
        stringBuilder.append(getGeometry());
        stringBuilder.append(",\n point style=");
        stringBuilder.append(this.mPointStyle);
        stringBuilder.append(",\n line string style=");
        stringBuilder.append(this.mLineStringStyle);
        stringBuilder.append(",\n polygon style=");
        stringBuilder.append(this.mPolygonStyle);
        stringBuilder.append(",\n id=");
        stringBuilder.append(this.mId);
        stringBuilder.append(",\n properties=");
        stringBuilder.append(getProperties());
        stringBuilder.append("\n}\n");
        return stringBuilder.toString();
    }

    public void update(Observable observable, Object obj) {
        if ((observable instanceof GeoJsonStyle) != null) {
            checkRedrawFeature((GeoJsonStyle) observable);
        }
    }
}
