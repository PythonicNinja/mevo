package com.google.maps.android.data.kml;

import com.google.android.gms.maps.model.GroundOverlay;
import java.util.ArrayList;
import java.util.HashMap;

public class KmlContainer {
    private String mContainerId;
    private final ArrayList<KmlContainer> mContainers;
    private final HashMap<KmlGroundOverlay, GroundOverlay> mGroundOverlays;
    private final HashMap<KmlPlacemark, Object> mPlacemarks;
    private final HashMap<String, String> mProperties;
    private final HashMap<String, String> mStyleMap;
    private HashMap<String, KmlStyle> mStyles;

    KmlContainer(HashMap<String, String> hashMap, HashMap<String, KmlStyle> hashMap2, HashMap<KmlPlacemark, Object> hashMap3, HashMap<String, String> hashMap4, ArrayList<KmlContainer> arrayList, HashMap<KmlGroundOverlay, GroundOverlay> hashMap5, String str) {
        this.mProperties = hashMap;
        this.mPlacemarks = hashMap3;
        this.mStyles = hashMap2;
        this.mStyleMap = hashMap4;
        this.mContainers = arrayList;
        this.mGroundOverlays = hashMap5;
        this.mContainerId = str;
    }

    HashMap<String, KmlStyle> getStyles() {
        return this.mStyles;
    }

    void setPlacemark(KmlPlacemark kmlPlacemark, Object obj) {
        this.mPlacemarks.put(kmlPlacemark, obj);
    }

    HashMap<String, String> getStyleMap() {
        return this.mStyleMap;
    }

    HashMap<KmlGroundOverlay, GroundOverlay> getGroundOverlayHashMap() {
        return this.mGroundOverlays;
    }

    public String getContainerId() {
        return this.mContainerId;
    }

    public KmlStyle getStyle(String str) {
        return (KmlStyle) this.mStyles.get(str);
    }

    HashMap<KmlPlacemark, Object> getPlacemarksHashMap() {
        return this.mPlacemarks;
    }

    public String getProperty(String str) {
        return (String) this.mProperties.get(str);
    }

    public boolean hasProperties() {
        return this.mProperties.size() > 0;
    }

    public boolean hasProperty(String str) {
        return this.mProperties.containsKey(str);
    }

    public boolean hasContainers() {
        return this.mContainers.size() > 0;
    }

    public Iterable<KmlContainer> getContainers() {
        return this.mContainers;
    }

    public Iterable<String> getProperties() {
        return this.mProperties.keySet();
    }

    public Iterable<KmlPlacemark> getPlacemarks() {
        return this.mPlacemarks.keySet();
    }

    public boolean hasPlacemarks() {
        return this.mPlacemarks.size() > 0;
    }

    public Iterable<KmlGroundOverlay> getGroundOverlays() {
        return this.mGroundOverlays.keySet();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Container");
        stringBuilder.append("{");
        stringBuilder.append("\n properties=");
        stringBuilder.append(this.mProperties);
        stringBuilder.append(",\n placemarks=");
        stringBuilder.append(this.mPlacemarks);
        stringBuilder.append(",\n containers=");
        stringBuilder.append(this.mContainers);
        stringBuilder.append(",\n ground overlays=");
        stringBuilder.append(this.mGroundOverlays);
        stringBuilder.append(",\n style maps=");
        stringBuilder.append(this.mStyleMap);
        stringBuilder.append(",\n styles=");
        stringBuilder.append(this.mStyles);
        stringBuilder.append("\n}\n");
        return stringBuilder.toString();
    }
}
