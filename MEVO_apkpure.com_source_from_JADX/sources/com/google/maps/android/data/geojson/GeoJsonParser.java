package com.google.maps.android.data.geojson;

import android.util.Log;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.data.Geometry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class GeoJsonParser {
    private static final String BOUNDING_BOX = "bbox";
    private static final String FEATURE = "Feature";
    private static final String FEATURE_COLLECTION = "FeatureCollection";
    private static final String FEATURE_COLLECTION_ARRAY = "features";
    private static final String FEATURE_GEOMETRY = "geometry";
    private static final String FEATURE_ID = "id";
    private static final String GEOMETRY_COLLECTION = "GeometryCollection";
    private static final String GEOMETRY_COLLECTION_ARRAY = "geometries";
    private static final String GEOMETRY_COORDINATES_ARRAY = "coordinates";
    private static final String LINESTRING = "LineString";
    private static final String LOG_TAG = "GeoJsonParser";
    private static final String MULTILINESTRING = "MultiLineString";
    private static final String MULTIPOINT = "MultiPoint";
    private static final String MULTIPOLYGON = "MultiPolygon";
    private static final String POINT = "Point";
    private static final String POLYGON = "Polygon";
    private static final String PROPERTIES = "properties";
    private LatLngBounds mBoundingBox = null;
    private final ArrayList<GeoJsonFeature> mGeoJsonFeatures = new ArrayList();
    private final JSONObject mGeoJsonFile;

    GeoJsonParser(JSONObject jSONObject) {
        this.mGeoJsonFile = jSONObject;
        parseGeoJson();
    }

    private static boolean isGeometry(String str) {
        return str.matches("Point|MultiPoint|LineString|MultiLineString|Polygon|MultiPolygon|GeometryCollection");
    }

    private static com.google.maps.android.data.geojson.GeoJsonFeature parseFeature(org.json.JSONObject r6) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r0 = new java.util.HashMap;
        r0.<init>();
        r1 = 0;
        r2 = "id";	 Catch:{ JSONException -> 0x0066 }
        r2 = r6.has(r2);	 Catch:{ JSONException -> 0x0066 }
        if (r2 == 0) goto L_0x0015;	 Catch:{ JSONException -> 0x0066 }
    L_0x000e:
        r2 = "id";	 Catch:{ JSONException -> 0x0066 }
        r2 = r6.getString(r2);	 Catch:{ JSONException -> 0x0066 }
        goto L_0x0016;	 Catch:{ JSONException -> 0x0066 }
    L_0x0015:
        r2 = r1;	 Catch:{ JSONException -> 0x0066 }
    L_0x0016:
        r3 = "bbox";	 Catch:{ JSONException -> 0x0066 }
        r3 = r6.has(r3);	 Catch:{ JSONException -> 0x0066 }
        if (r3 == 0) goto L_0x0029;	 Catch:{ JSONException -> 0x0066 }
    L_0x001e:
        r3 = "bbox";	 Catch:{ JSONException -> 0x0066 }
        r3 = r6.getJSONArray(r3);	 Catch:{ JSONException -> 0x0066 }
        r3 = parseBoundingBox(r3);	 Catch:{ JSONException -> 0x0066 }
        goto L_0x002a;	 Catch:{ JSONException -> 0x0066 }
    L_0x0029:
        r3 = r1;	 Catch:{ JSONException -> 0x0066 }
    L_0x002a:
        r4 = "geometry";	 Catch:{ JSONException -> 0x0066 }
        r4 = r6.has(r4);	 Catch:{ JSONException -> 0x0066 }
        if (r4 == 0) goto L_0x0045;	 Catch:{ JSONException -> 0x0066 }
    L_0x0032:
        r4 = "geometry";	 Catch:{ JSONException -> 0x0066 }
        r4 = r6.isNull(r4);	 Catch:{ JSONException -> 0x0066 }
        if (r4 != 0) goto L_0x0045;	 Catch:{ JSONException -> 0x0066 }
    L_0x003a:
        r4 = "geometry";	 Catch:{ JSONException -> 0x0066 }
        r4 = r6.getJSONObject(r4);	 Catch:{ JSONException -> 0x0066 }
        r4 = parseGeometry(r4);	 Catch:{ JSONException -> 0x0066 }
        goto L_0x0046;	 Catch:{ JSONException -> 0x0066 }
    L_0x0045:
        r4 = r1;	 Catch:{ JSONException -> 0x0066 }
    L_0x0046:
        r5 = "properties";	 Catch:{ JSONException -> 0x0066 }
        r5 = r6.has(r5);	 Catch:{ JSONException -> 0x0066 }
        if (r5 == 0) goto L_0x0060;	 Catch:{ JSONException -> 0x0066 }
    L_0x004e:
        r5 = "properties";	 Catch:{ JSONException -> 0x0066 }
        r5 = r6.isNull(r5);	 Catch:{ JSONException -> 0x0066 }
        if (r5 != 0) goto L_0x0060;	 Catch:{ JSONException -> 0x0066 }
    L_0x0056:
        r0 = "properties";	 Catch:{ JSONException -> 0x0066 }
        r0 = r6.getJSONObject(r0);	 Catch:{ JSONException -> 0x0066 }
        r0 = parseProperties(r0);	 Catch:{ JSONException -> 0x0066 }
    L_0x0060:
        r6 = new com.google.maps.android.data.geojson.GeoJsonFeature;
        r6.<init>(r4, r2, r0, r3);
        return r6;
    L_0x0066:
        r0 = "GeoJsonParser";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Feature could not be successfully parsed ";
        r2.append(r3);
        r6 = r6.toString();
        r2.append(r6);
        r6 = r2.toString();
        android.util.Log.w(r0, r6);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.maps.android.data.geojson.GeoJsonParser.parseFeature(org.json.JSONObject):com.google.maps.android.data.geojson.GeoJsonFeature");
    }

    private static LatLngBounds parseBoundingBox(JSONArray jSONArray) throws JSONException {
        return new LatLngBounds(new LatLng(jSONArray.getDouble(1), jSONArray.getDouble(0)), new LatLng(jSONArray.getDouble(3), jSONArray.getDouble(2)));
    }

    private static com.google.maps.android.data.Geometry parseGeometry(org.json.JSONObject r3) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r0 = 0;
        r1 = "type";	 Catch:{ JSONException -> 0x0028 }
        r1 = r3.getString(r1);	 Catch:{ JSONException -> 0x0028 }
        r2 = "GeometryCollection";	 Catch:{ JSONException -> 0x0028 }
        r2 = r1.equals(r2);	 Catch:{ JSONException -> 0x0028 }
        if (r2 == 0) goto L_0x0016;	 Catch:{ JSONException -> 0x0028 }
    L_0x000f:
        r2 = "geometries";	 Catch:{ JSONException -> 0x0028 }
        r3 = r3.getJSONArray(r2);	 Catch:{ JSONException -> 0x0028 }
        goto L_0x0022;	 Catch:{ JSONException -> 0x0028 }
    L_0x0016:
        r2 = isGeometry(r1);	 Catch:{ JSONException -> 0x0028 }
        if (r2 == 0) goto L_0x0027;	 Catch:{ JSONException -> 0x0028 }
    L_0x001c:
        r2 = "coordinates";	 Catch:{ JSONException -> 0x0028 }
        r3 = r3.getJSONArray(r2);	 Catch:{ JSONException -> 0x0028 }
    L_0x0022:
        r3 = createGeometry(r1, r3);	 Catch:{ JSONException -> 0x0028 }
        return r3;
    L_0x0027:
        return r0;
    L_0x0028:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.maps.android.data.geojson.GeoJsonParser.parseGeometry(org.json.JSONObject):com.google.maps.android.data.Geometry");
    }

    private static GeoJsonFeature parseGeometryToFeature(JSONObject jSONObject) {
        jSONObject = parseGeometry(jSONObject);
        if (jSONObject != null) {
            return new GeoJsonFeature(jSONObject, null, new HashMap(), null);
        }
        Log.w(LOG_TAG, "Geometry could not be parsed");
        return null;
    }

    private static HashMap<String, String> parseProperties(JSONObject jSONObject) throws JSONException {
        HashMap<String, String> hashMap = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            hashMap.put(str, jSONObject.isNull(str) ? null : jSONObject.getString(str));
        }
        return hashMap;
    }

    private static Geometry createGeometry(String str, JSONArray jSONArray) throws JSONException {
        if (str.equals(POINT)) {
            return createPoint(jSONArray);
        }
        if (str.equals(MULTIPOINT)) {
            return createMultiPoint(jSONArray);
        }
        if (str.equals(LINESTRING)) {
            return createLineString(jSONArray);
        }
        if (str.equals(MULTILINESTRING)) {
            return createMultiLineString(jSONArray);
        }
        if (str.equals("Polygon")) {
            return createPolygon(jSONArray);
        }
        if (str.equals(MULTIPOLYGON)) {
            return createMultiPolygon(jSONArray);
        }
        return str.equals(GEOMETRY_COLLECTION) != null ? createGeometryCollection(jSONArray) : null;
    }

    private static GeoJsonPoint createPoint(JSONArray jSONArray) throws JSONException {
        return new GeoJsonPoint(parseCoordinate(jSONArray));
    }

    private static GeoJsonMultiPoint createMultiPoint(JSONArray jSONArray) throws JSONException {
        List arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(createPoint(jSONArray.getJSONArray(i)));
        }
        return new GeoJsonMultiPoint(arrayList);
    }

    private static GeoJsonLineString createLineString(JSONArray jSONArray) throws JSONException {
        return new GeoJsonLineString(parseCoordinatesArray(jSONArray));
    }

    private static GeoJsonMultiLineString createMultiLineString(JSONArray jSONArray) throws JSONException {
        List arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(createLineString(jSONArray.getJSONArray(i)));
        }
        return new GeoJsonMultiLineString(arrayList);
    }

    private static GeoJsonPolygon createPolygon(JSONArray jSONArray) throws JSONException {
        return new GeoJsonPolygon(parseCoordinatesArrays(jSONArray));
    }

    private static GeoJsonMultiPolygon createMultiPolygon(JSONArray jSONArray) throws JSONException {
        List arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(createPolygon(jSONArray.getJSONArray(i)));
        }
        return new GeoJsonMultiPolygon(arrayList);
    }

    private static GeoJsonGeometryCollection createGeometryCollection(JSONArray jSONArray) throws JSONException {
        List arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            Geometry parseGeometry = parseGeometry(jSONArray.getJSONObject(i));
            if (parseGeometry != null) {
                arrayList.add(parseGeometry);
            }
        }
        return new GeoJsonGeometryCollection(arrayList);
    }

    private static LatLng parseCoordinate(JSONArray jSONArray) throws JSONException {
        return new LatLng(jSONArray.getDouble(1), jSONArray.getDouble(0));
    }

    private static ArrayList<LatLng> parseCoordinatesArray(JSONArray jSONArray) throws JSONException {
        ArrayList<LatLng> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(parseCoordinate(jSONArray.getJSONArray(i)));
        }
        return arrayList;
    }

    private static ArrayList<ArrayList<LatLng>> parseCoordinatesArrays(JSONArray jSONArray) throws JSONException {
        ArrayList<ArrayList<LatLng>> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(parseCoordinatesArray(jSONArray.getJSONArray(i)));
        }
        return arrayList;
    }

    private void parseGeoJson() {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r2 = this;
        r0 = r2.mGeoJsonFile;	 Catch:{ JSONException -> 0x004e }
        r1 = "type";	 Catch:{ JSONException -> 0x004e }
        r0 = r0.getString(r1);	 Catch:{ JSONException -> 0x004e }
        r1 = "Feature";	 Catch:{ JSONException -> 0x004e }
        r1 = r0.equals(r1);	 Catch:{ JSONException -> 0x004e }
        if (r1 == 0) goto L_0x001e;	 Catch:{ JSONException -> 0x004e }
    L_0x0010:
        r0 = r2.mGeoJsonFile;	 Catch:{ JSONException -> 0x004e }
        r0 = parseFeature(r0);	 Catch:{ JSONException -> 0x004e }
        if (r0 == 0) goto L_0x0055;	 Catch:{ JSONException -> 0x004e }
    L_0x0018:
        r1 = r2.mGeoJsonFeatures;	 Catch:{ JSONException -> 0x004e }
        r1.add(r0);	 Catch:{ JSONException -> 0x004e }
        goto L_0x0055;	 Catch:{ JSONException -> 0x004e }
    L_0x001e:
        r1 = "FeatureCollection";	 Catch:{ JSONException -> 0x004e }
        r1 = r0.equals(r1);	 Catch:{ JSONException -> 0x004e }
        if (r1 == 0) goto L_0x0032;	 Catch:{ JSONException -> 0x004e }
    L_0x0026:
        r0 = r2.mGeoJsonFeatures;	 Catch:{ JSONException -> 0x004e }
        r1 = r2.mGeoJsonFile;	 Catch:{ JSONException -> 0x004e }
        r1 = r2.parseFeatureCollection(r1);	 Catch:{ JSONException -> 0x004e }
        r0.addAll(r1);	 Catch:{ JSONException -> 0x004e }
        goto L_0x0055;	 Catch:{ JSONException -> 0x004e }
    L_0x0032:
        r0 = isGeometry(r0);	 Catch:{ JSONException -> 0x004e }
        if (r0 == 0) goto L_0x0046;	 Catch:{ JSONException -> 0x004e }
    L_0x0038:
        r0 = r2.mGeoJsonFile;	 Catch:{ JSONException -> 0x004e }
        r0 = parseGeometryToFeature(r0);	 Catch:{ JSONException -> 0x004e }
        if (r0 == 0) goto L_0x0055;	 Catch:{ JSONException -> 0x004e }
    L_0x0040:
        r1 = r2.mGeoJsonFeatures;	 Catch:{ JSONException -> 0x004e }
        r1.add(r0);	 Catch:{ JSONException -> 0x004e }
        goto L_0x0055;	 Catch:{ JSONException -> 0x004e }
    L_0x0046:
        r0 = "GeoJsonParser";	 Catch:{ JSONException -> 0x004e }
        r1 = "GeoJSON file could not be parsed.";	 Catch:{ JSONException -> 0x004e }
        android.util.Log.w(r0, r1);	 Catch:{ JSONException -> 0x004e }
        goto L_0x0055;
    L_0x004e:
        r0 = "GeoJsonParser";
        r1 = "GeoJSON file could not be parsed.";
        android.util.Log.w(r0, r1);
    L_0x0055:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.maps.android.data.geojson.GeoJsonParser.parseGeoJson():void");
    }

    private java.util.ArrayList<com.google.maps.android.data.geojson.GeoJsonFeature> parseFeatureCollection(org.json.JSONObject r6) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r5 = this;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = "features";	 Catch:{ JSONException -> 0x0073 }
        r1 = r6.getJSONArray(r1);	 Catch:{ JSONException -> 0x0073 }
        r2 = "bbox";	 Catch:{ JSONException -> 0x0073 }
        r2 = r6.has(r2);	 Catch:{ JSONException -> 0x0073 }
        if (r2 == 0) goto L_0x001f;	 Catch:{ JSONException -> 0x0073 }
    L_0x0013:
        r2 = "bbox";	 Catch:{ JSONException -> 0x0073 }
        r6 = r6.getJSONArray(r2);	 Catch:{ JSONException -> 0x0073 }
        r6 = parseBoundingBox(r6);	 Catch:{ JSONException -> 0x0073 }
        r5.mBoundingBox = r6;	 Catch:{ JSONException -> 0x0073 }
    L_0x001f:
        r6 = 0;
    L_0x0020:
        r2 = r1.length();
        if (r6 >= r2) goto L_0x0072;
    L_0x0026:
        r2 = r1.getJSONObject(r6);	 Catch:{ JSONException -> 0x0059 }
        r3 = "type";	 Catch:{ JSONException -> 0x0059 }
        r3 = r2.getString(r3);	 Catch:{ JSONException -> 0x0059 }
        r4 = "Feature";	 Catch:{ JSONException -> 0x0059 }
        r3 = r3.equals(r4);	 Catch:{ JSONException -> 0x0059 }
        if (r3 == 0) goto L_0x006f;	 Catch:{ JSONException -> 0x0059 }
    L_0x0038:
        r2 = parseFeature(r2);	 Catch:{ JSONException -> 0x0059 }
        if (r2 == 0) goto L_0x0042;	 Catch:{ JSONException -> 0x0059 }
    L_0x003e:
        r0.add(r2);	 Catch:{ JSONException -> 0x0059 }
        goto L_0x006f;	 Catch:{ JSONException -> 0x0059 }
    L_0x0042:
        r2 = "GeoJsonParser";	 Catch:{ JSONException -> 0x0059 }
        r3 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0059 }
        r3.<init>();	 Catch:{ JSONException -> 0x0059 }
        r4 = "Index of Feature in Feature Collection that could not be created: ";	 Catch:{ JSONException -> 0x0059 }
        r3.append(r4);	 Catch:{ JSONException -> 0x0059 }
        r3.append(r6);	 Catch:{ JSONException -> 0x0059 }
        r3 = r3.toString();	 Catch:{ JSONException -> 0x0059 }
        android.util.Log.w(r2, r3);	 Catch:{ JSONException -> 0x0059 }
        goto L_0x006f;
    L_0x0059:
        r2 = "GeoJsonParser";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Index of Feature in Feature Collection that could not be created: ";
        r3.append(r4);
        r3.append(r6);
        r3 = r3.toString();
        android.util.Log.w(r2, r3);
    L_0x006f:
        r6 = r6 + 1;
        goto L_0x0020;
    L_0x0072:
        return r0;
    L_0x0073:
        r6 = "GeoJsonParser";
        r1 = "Feature Collection could not be created.";
        android.util.Log.w(r6, r1);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.maps.android.data.geojson.GeoJsonParser.parseFeatureCollection(org.json.JSONObject):java.util.ArrayList<com.google.maps.android.data.geojson.GeoJsonFeature>");
    }

    ArrayList<GeoJsonFeature> getFeatures() {
        return this.mGeoJsonFeatures;
    }

    LatLngBounds getBoundingBox() {
        return this.mBoundingBox;
    }
}
