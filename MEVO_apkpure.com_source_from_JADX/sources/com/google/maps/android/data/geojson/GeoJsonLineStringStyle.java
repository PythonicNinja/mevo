package com.google.maps.android.data.geojson;

import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.data.Style;
import java.util.Arrays;

public class GeoJsonLineStringStyle extends Style implements GeoJsonStyle {
    private static final String[] GEOMETRY_TYPE = new String[]{"LineString", "MultiLineString", "GeometryCollection"};

    public GeoJsonLineStringStyle() {
        this.mPolylineOptions = new PolylineOptions();
    }

    public String[] getGeometryType() {
        return GEOMETRY_TYPE;
    }

    public int getColor() {
        return this.mPolylineOptions.getColor();
    }

    public void setColor(int i) {
        this.mPolylineOptions.color(i);
        styleChanged();
    }

    public boolean isClickable() {
        return this.mPolylineOptions.isClickable();
    }

    public void setClickable(boolean z) {
        this.mPolylineOptions.clickable(z);
        styleChanged();
    }

    public boolean isGeodesic() {
        return this.mPolylineOptions.isGeodesic();
    }

    public void setGeodesic(boolean z) {
        this.mPolylineOptions.geodesic(z);
        styleChanged();
    }

    public float getWidth() {
        return this.mPolylineOptions.getWidth();
    }

    public void setWidth(float f) {
        setLineStringWidth(f);
        styleChanged();
    }

    public float getZIndex() {
        return this.mPolylineOptions.getZIndex();
    }

    public void setZIndex(float f) {
        this.mPolylineOptions.zIndex(f);
        styleChanged();
    }

    public boolean isVisible() {
        return this.mPolylineOptions.isVisible();
    }

    public void setVisible(boolean z) {
        this.mPolylineOptions.visible(z);
        styleChanged();
    }

    private void styleChanged() {
        setChanged();
        notifyObservers();
    }

    public PolylineOptions toPolylineOptions() {
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.color(this.mPolylineOptions.getColor());
        polylineOptions.clickable(this.mPolylineOptions.isClickable());
        polylineOptions.geodesic(this.mPolylineOptions.isGeodesic());
        polylineOptions.visible(this.mPolylineOptions.isVisible());
        polylineOptions.width(this.mPolylineOptions.getWidth());
        polylineOptions.zIndex(this.mPolylineOptions.getZIndex());
        return polylineOptions;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("LineStringStyle{");
        stringBuilder.append("\n geometry type=");
        stringBuilder.append(Arrays.toString(GEOMETRY_TYPE));
        stringBuilder.append(",\n color=");
        stringBuilder.append(getColor());
        stringBuilder.append(",\n clickable=");
        stringBuilder.append(isClickable());
        stringBuilder.append(",\n geodesic=");
        stringBuilder.append(isGeodesic());
        stringBuilder.append(",\n visible=");
        stringBuilder.append(isVisible());
        stringBuilder.append(",\n width=");
        stringBuilder.append(getWidth());
        stringBuilder.append(",\n z index=");
        stringBuilder.append(getZIndex());
        stringBuilder.append("\n}\n");
        return stringBuilder.toString();
    }
}
