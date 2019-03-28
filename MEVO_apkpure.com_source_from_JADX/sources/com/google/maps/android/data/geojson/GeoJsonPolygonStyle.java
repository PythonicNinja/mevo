package com.google.maps.android.data.geojson;

import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.data.Style;
import com.google.maps.android.data.kml.KmlPolygon;
import java.util.Arrays;

public class GeoJsonPolygonStyle extends Style implements GeoJsonStyle {
    private static final String[] GEOMETRY_TYPE = new String[]{KmlPolygon.GEOMETRY_TYPE, "MultiPolygon", "GeometryCollection"};

    public GeoJsonPolygonStyle() {
        this.mPolygonOptions = new PolygonOptions();
    }

    public String[] getGeometryType() {
        return GEOMETRY_TYPE;
    }

    public int getFillColor() {
        return this.mPolygonOptions.getFillColor();
    }

    public void setFillColor(int i) {
        setPolygonFillColor(i);
        styleChanged();
    }

    public boolean isGeodesic() {
        return this.mPolygonOptions.isGeodesic();
    }

    public void setGeodesic(boolean z) {
        this.mPolygonOptions.geodesic(z);
        styleChanged();
    }

    public int getStrokeColor() {
        return this.mPolygonOptions.getStrokeColor();
    }

    public void setStrokeColor(int i) {
        this.mPolygonOptions.strokeColor(i);
        styleChanged();
    }

    public float getStrokeWidth() {
        return this.mPolygonOptions.getStrokeWidth();
    }

    public void setStrokeWidth(float f) {
        setPolygonStrokeWidth(f);
        styleChanged();
    }

    public float getZIndex() {
        return this.mPolygonOptions.getZIndex();
    }

    public void setZIndex(float f) {
        this.mPolygonOptions.zIndex(f);
        styleChanged();
    }

    public boolean isVisible() {
        return this.mPolygonOptions.isVisible();
    }

    public void setVisible(boolean z) {
        this.mPolygonOptions.visible(z);
        styleChanged();
    }

    private void styleChanged() {
        setChanged();
        notifyObservers();
    }

    public PolygonOptions toPolygonOptions() {
        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.fillColor(this.mPolygonOptions.getFillColor());
        polygonOptions.geodesic(this.mPolygonOptions.isGeodesic());
        polygonOptions.strokeColor(this.mPolygonOptions.getStrokeColor());
        polygonOptions.strokeWidth(this.mPolygonOptions.getStrokeWidth());
        polygonOptions.visible(this.mPolygonOptions.isVisible());
        polygonOptions.zIndex(this.mPolygonOptions.getZIndex());
        return polygonOptions;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("PolygonStyle{");
        stringBuilder.append("\n geometry type=");
        stringBuilder.append(Arrays.toString(GEOMETRY_TYPE));
        stringBuilder.append(",\n fill color=");
        stringBuilder.append(getFillColor());
        stringBuilder.append(",\n geodesic=");
        stringBuilder.append(isGeodesic());
        stringBuilder.append(",\n stroke color=");
        stringBuilder.append(getStrokeColor());
        stringBuilder.append(",\n stroke width=");
        stringBuilder.append(getStrokeWidth());
        stringBuilder.append(",\n visible=");
        stringBuilder.append(isVisible());
        stringBuilder.append(",\n z index=");
        stringBuilder.append(getZIndex());
        stringBuilder.append("\n}\n");
        return stringBuilder.toString();
    }
}
