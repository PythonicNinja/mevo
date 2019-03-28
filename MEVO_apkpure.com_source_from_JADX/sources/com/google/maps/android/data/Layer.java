package com.google.maps.android.data;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnPolygonClickListener;
import com.google.android.gms.maps.GoogleMap.OnPolylineClickListener;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.maps.android.data.geojson.GeoJsonLineStringStyle;
import com.google.maps.android.data.geojson.GeoJsonPointStyle;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;
import com.google.maps.android.data.geojson.GeoJsonRenderer;
import com.google.maps.android.data.kml.KmlContainer;
import com.google.maps.android.data.kml.KmlGroundOverlay;
import com.google.maps.android.data.kml.KmlRenderer;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParserException;

public abstract class Layer {
    private Renderer mRenderer;

    public interface OnFeatureClickListener {
        void onFeatureClick(Feature feature);
    }

    protected void addKMLToMap() throws IOException, XmlPullParserException {
        if (this.mRenderer instanceof KmlRenderer) {
            ((KmlRenderer) this.mRenderer).addLayerToMap();
            return;
        }
        throw new UnsupportedOperationException("Stored renderer is not a KmlRenderer");
    }

    protected void addGeoJsonToMap() {
        if (this.mRenderer instanceof GeoJsonRenderer) {
            ((GeoJsonRenderer) this.mRenderer).addLayerToMap();
            return;
        }
        throw new UnsupportedOperationException("Stored renderer is not a GeoJsonRenderer");
    }

    public void removeLayerFromMap() {
        if (this.mRenderer instanceof GeoJsonRenderer) {
            ((GeoJsonRenderer) this.mRenderer).removeLayerFromMap();
        } else if (this.mRenderer instanceof KmlRenderer) {
            ((KmlRenderer) this.mRenderer).removeLayerFromMap();
        }
    }

    public void setOnFeatureClickListener(final OnFeatureClickListener onFeatureClickListener) {
        GoogleMap map = getMap();
        map.setOnPolygonClickListener(new OnPolygonClickListener() {
            public void onPolygonClick(Polygon polygon) {
                if (Layer.this.getFeature(polygon) != null) {
                    onFeatureClickListener.onFeatureClick(Layer.this.getFeature(polygon));
                } else if (Layer.this.getContainerFeature(polygon) != null) {
                    onFeatureClickListener.onFeatureClick(Layer.this.getContainerFeature(polygon));
                } else {
                    onFeatureClickListener.onFeatureClick(Layer.this.getFeature(Layer.this.multiObjectHandler(polygon)));
                }
            }
        });
        map.setOnMarkerClickListener(new OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {
                if (Layer.this.getFeature(marker) != null) {
                    onFeatureClickListener.onFeatureClick(Layer.this.getFeature(marker));
                } else if (Layer.this.getContainerFeature(marker) != null) {
                    onFeatureClickListener.onFeatureClick(Layer.this.getContainerFeature(marker));
                } else {
                    onFeatureClickListener.onFeatureClick(Layer.this.getFeature(Layer.this.multiObjectHandler(marker)));
                }
                return null;
            }
        });
        map.setOnPolylineClickListener(new OnPolylineClickListener() {
            public void onPolylineClick(Polyline polyline) {
                if (Layer.this.getFeature(polyline) != null) {
                    onFeatureClickListener.onFeatureClick(Layer.this.getFeature(polyline));
                } else if (Layer.this.getContainerFeature(polyline) != null) {
                    onFeatureClickListener.onFeatureClick(Layer.this.getContainerFeature(polyline));
                } else {
                    onFeatureClickListener.onFeatureClick(Layer.this.getFeature(Layer.this.multiObjectHandler(polyline)));
                }
            }
        });
    }

    private ArrayList<?> multiObjectHandler(Object obj) {
        for (Object next : this.mRenderer.getValues()) {
            if (next.getClass().getSimpleName().equals("ArrayList")) {
                ArrayList<?> arrayList = (ArrayList) next;
                if (arrayList.contains(obj)) {
                    return arrayList;
                }
            }
        }
        return null;
    }

    protected void storeRenderer(Renderer renderer) {
        this.mRenderer = renderer;
    }

    public Iterable<? extends Feature> getFeatures() {
        return this.mRenderer.getFeatures();
    }

    public Feature getFeature(Object obj) {
        return this.mRenderer.getFeature(obj);
    }

    public Feature getContainerFeature(Object obj) {
        return this.mRenderer.getContainerFeature(obj);
    }

    protected boolean hasFeatures() {
        return this.mRenderer.hasFeatures();
    }

    protected boolean hasContainers() {
        return this.mRenderer instanceof KmlRenderer ? ((KmlRenderer) this.mRenderer).hasNestedContainers() : false;
    }

    protected Iterable<KmlContainer> getContainers() {
        return this.mRenderer instanceof KmlRenderer ? ((KmlRenderer) this.mRenderer).getNestedContainers() : null;
    }

    protected Iterable<KmlGroundOverlay> getGroundOverlays() {
        return this.mRenderer instanceof KmlRenderer ? ((KmlRenderer) this.mRenderer).getGroundOverlays() : null;
    }

    public GoogleMap getMap() {
        return this.mRenderer.getMap();
    }

    public void setMap(GoogleMap googleMap) {
        this.mRenderer.setMap(googleMap);
    }

    public boolean isLayerOnMap() {
        return this.mRenderer.isLayerOnMap();
    }

    protected void addFeature(Feature feature) {
        this.mRenderer.addFeature(feature);
    }

    protected void removeFeature(Feature feature) {
        this.mRenderer.removeFeature(feature);
    }

    public GeoJsonPointStyle getDefaultPointStyle() {
        return this.mRenderer.getDefaultPointStyle();
    }

    public GeoJsonLineStringStyle getDefaultLineStringStyle() {
        return this.mRenderer.getDefaultLineStringStyle();
    }

    public GeoJsonPolygonStyle getDefaultPolygonStyle() {
        return this.mRenderer.getDefaultPolygonStyle();
    }
}
