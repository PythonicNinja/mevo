package com.google.maps.android.data.geojson;

import android.content.Context;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.data.Layer;
import com.google.maps.android.data.Layer.OnFeatureClickListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class GeoJsonLayer extends Layer {
    private LatLngBounds mBoundingBox;

    public interface GeoJsonOnFeatureClickListener extends OnFeatureClickListener {
    }

    public GeoJsonLayer(GoogleMap googleMap, JSONObject jSONObject) {
        if (jSONObject == null) {
            throw new IllegalArgumentException("GeoJSON file cannot be null");
        }
        this.mBoundingBox = null;
        GeoJsonParser geoJsonParser = new GeoJsonParser(jSONObject);
        this.mBoundingBox = geoJsonParser.getBoundingBox();
        jSONObject = new HashMap();
        Iterator it = geoJsonParser.getFeatures().iterator();
        while (it.hasNext()) {
            jSONObject.put((GeoJsonFeature) it.next(), null);
        }
        storeRenderer(new GeoJsonRenderer(googleMap, jSONObject));
    }

    public GeoJsonLayer(GoogleMap googleMap, int i, Context context) throws IOException, JSONException {
        this(googleMap, createJsonFileObject(context.getResources().openRawResource(i)));
    }

    private static JSONObject createJsonFileObject(InputStream inputStream) throws IOException, JSONException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while (true) {
            inputStream = bufferedReader.readLine();
            if (inputStream != null) {
                stringBuilder.append(inputStream);
            } else {
                bufferedReader.close();
                return new JSONObject(stringBuilder.toString());
            }
        }
    }

    public void addLayerToMap() {
        super.addGeoJsonToMap();
    }

    public Iterable<GeoJsonFeature> getFeatures() {
        return super.getFeatures();
    }

    public void addFeature(GeoJsonFeature geoJsonFeature) {
        if (geoJsonFeature == null) {
            throw new IllegalArgumentException("Feature cannot be null");
        }
        super.addFeature(geoJsonFeature);
    }

    public void removeFeature(GeoJsonFeature geoJsonFeature) {
        if (geoJsonFeature == null) {
            throw new IllegalArgumentException("Feature cannot be null");
        }
        super.removeFeature(geoJsonFeature);
    }

    public LatLngBounds getBoundingBox() {
        return this.mBoundingBox;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Collection{");
        stringBuilder.append("\n Bounding box=");
        stringBuilder.append(this.mBoundingBox);
        stringBuilder.append("\n}\n");
        return stringBuilder.toString();
    }
}
