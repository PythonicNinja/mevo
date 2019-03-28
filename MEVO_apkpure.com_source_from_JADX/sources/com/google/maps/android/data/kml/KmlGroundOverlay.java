package com.google.maps.android.data.kml;

import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.HashMap;
import java.util.Map;

public class KmlGroundOverlay {
    private final GroundOverlayOptions mGroundOverlayOptions = new GroundOverlayOptions();
    private String mImageUrl;
    private LatLngBounds mLatLngBox;
    private final Map<String, String> mProperties;

    KmlGroundOverlay(String str, LatLngBounds latLngBounds, float f, int i, HashMap<String, String> hashMap, float f2) {
        this.mImageUrl = str;
        this.mProperties = hashMap;
        if (latLngBounds == null) {
            throw new IllegalArgumentException("No LatLonBox given");
        }
        this.mLatLngBox = latLngBounds;
        this.mGroundOverlayOptions.positionFromBounds(latLngBounds);
        this.mGroundOverlayOptions.bearing(f2);
        this.mGroundOverlayOptions.zIndex(f);
        this.mGroundOverlayOptions.visible(i != 0 ? true : null);
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public LatLngBounds getLatLngBox() {
        return this.mLatLngBox;
    }

    public Iterable<String> getProperties() {
        return this.mProperties.keySet();
    }

    public String getProperty(String str) {
        return (String) this.mProperties.get(str);
    }

    public boolean hasProperty(String str) {
        return this.mProperties.get(str) != null ? true : null;
    }

    GroundOverlayOptions getGroundOverlayOptions() {
        return this.mGroundOverlayOptions;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("GroundOverlay");
        stringBuilder.append("{");
        stringBuilder.append("\n properties=");
        stringBuilder.append(this.mProperties);
        stringBuilder.append(",\n image url=");
        stringBuilder.append(this.mImageUrl);
        stringBuilder.append(",\n LatLngBox=");
        stringBuilder.append(this.mLatLngBox);
        stringBuilder.append("\n}\n");
        return stringBuilder.toString();
    }
}
