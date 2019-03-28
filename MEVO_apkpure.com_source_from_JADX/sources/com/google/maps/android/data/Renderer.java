package com.google.maps.android.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.C0404R;
import com.google.maps.android.data.geojson.BiMultiMap;
import com.google.maps.android.data.geojson.GeoJsonFeature;
import com.google.maps.android.data.geojson.GeoJsonLineString;
import com.google.maps.android.data.geojson.GeoJsonLineStringStyle;
import com.google.maps.android.data.geojson.GeoJsonMultiLineString;
import com.google.maps.android.data.geojson.GeoJsonMultiPoint;
import com.google.maps.android.data.geojson.GeoJsonMultiPolygon;
import com.google.maps.android.data.geojson.GeoJsonPoint;
import com.google.maps.android.data.geojson.GeoJsonPointStyle;
import com.google.maps.android.data.geojson.GeoJsonPolygon;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;
import com.google.maps.android.data.kml.KmlContainer;
import com.google.maps.android.data.kml.KmlGroundOverlay;
import com.google.maps.android.data.kml.KmlMultiGeometry;
import com.google.maps.android.data.kml.KmlPlacemark;
import com.google.maps.android.data.kml.KmlStyle;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Renderer {
    private static final Object FEATURE_NOT_ON_MAP = null;
    private static final int LRU_CACHE_SIZE = 50;
    private BiMultiMap<Feature> mContainerFeatures;
    private ArrayList<KmlContainer> mContainers;
    private Context mContext;
    private final GeoJsonLineStringStyle mDefaultLineStringStyle;
    private final GeoJsonPointStyle mDefaultPointStyle;
    private final GeoJsonPolygonStyle mDefaultPolygonStyle;
    private final BiMultiMap<Feature> mFeatures = new BiMultiMap();
    private HashMap<KmlGroundOverlay, GroundOverlay> mGroundOverlays;
    private final LruCache<String, Bitmap> mImagesCache;
    private boolean mLayerOnMap;
    private GoogleMap mMap;
    private final ArrayList<String> mMarkerIconUrls;
    private HashMap<String, String> mStyleMaps;
    private HashMap<String, KmlStyle> mStyles;
    private HashMap<String, KmlStyle> mStylesRenderer;

    /* renamed from: com.google.maps.android.data.Renderer$1 */
    class C07931 implements InfoWindowAdapter {
        public View getInfoWindow(Marker marker) {
            return null;
        }

        C07931() {
        }

        public View getInfoContents(Marker marker) {
            View inflate = LayoutInflater.from(Renderer.this.mContext).inflate(C0404R.layout.amu_info_window, null);
            TextView textView = (TextView) inflate.findViewById(C0404R.id.window);
            if (marker.getSnippet() != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(marker.getTitle());
                stringBuilder.append("<br>");
                stringBuilder.append(marker.getSnippet());
                textView.setText(Html.fromHtml(stringBuilder.toString()));
            } else {
                textView.setText(Html.fromHtml(marker.getTitle()));
            }
            return inflate;
        }
    }

    public Renderer(GoogleMap googleMap, Context context) {
        this.mMap = googleMap;
        this.mContext = context;
        this.mLayerOnMap = null;
        this.mImagesCache = new LruCache(50);
        this.mMarkerIconUrls = new ArrayList();
        this.mStylesRenderer = new HashMap();
        this.mDefaultPointStyle = null;
        this.mDefaultLineStringStyle = null;
        this.mDefaultPolygonStyle = null;
        this.mContainerFeatures = new BiMultiMap();
    }

    public Renderer(GoogleMap googleMap, HashMap<? extends Feature, Object> hashMap) {
        this.mMap = googleMap;
        this.mFeatures.putAll(hashMap);
        this.mLayerOnMap = null;
        this.mMarkerIconUrls = null;
        this.mDefaultPointStyle = new GeoJsonPointStyle();
        this.mDefaultLineStringStyle = new GeoJsonLineStringStyle();
        this.mDefaultPolygonStyle = new GeoJsonPolygonStyle();
        this.mImagesCache = null;
        this.mContainerFeatures = null;
    }

    public boolean isLayerOnMap() {
        return this.mLayerOnMap;
    }

    protected void setLayerVisibility(boolean z) {
        this.mLayerOnMap = z;
    }

    public GoogleMap getMap() {
        return this.mMap;
    }

    public void setMap(GoogleMap googleMap) {
        this.mMap = googleMap;
    }

    protected void putContainerFeature(Object obj, Feature feature) {
        this.mContainerFeatures.put((Object) feature, obj);
    }

    public Set<Feature> getFeatures() {
        return this.mFeatures.keySet();
    }

    public Feature getFeature(Object obj) {
        return (Feature) this.mFeatures.getKey(obj);
    }

    public Feature getContainerFeature(Object obj) {
        return this.mContainerFeatures != null ? (Feature) this.mContainerFeatures.getKey(obj) : null;
    }

    public Collection<Object> getValues() {
        return this.mFeatures.values();
    }

    protected HashMap<? extends Feature, Object> getAllFeatures() {
        return this.mFeatures;
    }

    public ArrayList<String> getMarkerIconUrls() {
        return this.mMarkerIconUrls;
    }

    public HashMap<String, KmlStyle> getStylesRenderer() {
        return this.mStylesRenderer;
    }

    public HashMap<String, String> getStyleMaps() {
        return this.mStyleMaps;
    }

    public LruCache<String, Bitmap> getImagesCache() {
        return this.mImagesCache;
    }

    public HashMap<KmlGroundOverlay, GroundOverlay> getGroundOverlayMap() {
        return this.mGroundOverlays;
    }

    public ArrayList<KmlContainer> getContainerList() {
        return this.mContainers;
    }

    protected KmlStyle getPlacemarkStyle(String str) {
        return this.mStylesRenderer.get(str) != null ? (KmlStyle) this.mStylesRenderer.get(str) : (KmlStyle) this.mStylesRenderer.get(null);
    }

    public GeoJsonPointStyle getDefaultPointStyle() {
        return this.mDefaultPointStyle;
    }

    public GeoJsonLineStringStyle getDefaultLineStringStyle() {
        return this.mDefaultLineStringStyle;
    }

    public GeoJsonPolygonStyle getDefaultPolygonStyle() {
        return this.mDefaultPolygonStyle;
    }

    public void putFeatures(Feature feature, Object obj) {
        this.mFeatures.put((Object) feature, obj);
    }

    public void putStyles() {
        this.mStylesRenderer.putAll(this.mStyles);
    }

    public void putStyles(HashMap<String, KmlStyle> hashMap) {
        this.mStylesRenderer.putAll(hashMap);
    }

    public void putImagesCache(String str, Bitmap bitmap) {
        this.mImagesCache.put(str, bitmap);
    }

    public boolean hasFeatures() {
        return this.mFeatures.size() > 0;
    }

    protected static void removeFeatures(HashMap<Feature, Object> hashMap) {
        for (Object next : hashMap.values()) {
            if (next instanceof Marker) {
                ((Marker) next).remove();
            } else if (next instanceof Polyline) {
                ((Polyline) next).remove();
            } else if (next instanceof Polygon) {
                ((Polygon) next).remove();
            }
        }
    }

    protected void removeFeature(Feature feature) {
        if (this.mFeatures.containsKey(feature)) {
            removeFromMap(this.mFeatures.remove(feature));
        }
    }

    private void setFeatureDefaultStyles(GeoJsonFeature geoJsonFeature) {
        if (geoJsonFeature.getPointStyle() == null) {
            geoJsonFeature.setPointStyle(this.mDefaultPointStyle);
        }
        if (geoJsonFeature.getLineStringStyle() == null) {
            geoJsonFeature.setLineStringStyle(this.mDefaultLineStringStyle);
        }
        if (geoJsonFeature.getPolygonStyle() == null) {
            geoJsonFeature.setPolygonStyle(this.mDefaultPolygonStyle);
        }
    }

    public void clearStylesRenderer() {
        this.mStylesRenderer.clear();
    }

    protected void storeData(HashMap<String, KmlStyle> hashMap, HashMap<String, String> hashMap2, HashMap<KmlPlacemark, Object> hashMap3, ArrayList<KmlContainer> arrayList, HashMap<KmlGroundOverlay, GroundOverlay> hashMap4) {
        this.mStyles = hashMap;
        this.mStyleMaps = hashMap2;
        this.mFeatures.putAll(hashMap3);
        this.mContainers = arrayList;
        this.mGroundOverlays = hashMap4;
    }

    public void addFeature(Feature feature) {
        Object obj = FEATURE_NOT_ON_MAP;
        if (feature instanceof GeoJsonFeature) {
            setFeatureDefaultStyles((GeoJsonFeature) feature);
        }
        if (this.mLayerOnMap) {
            if (this.mFeatures.containsKey(feature)) {
                removeFromMap(this.mFeatures.get(feature));
            }
            if (feature.hasGeometry()) {
                if (feature instanceof KmlPlacemark) {
                    KmlPlacemark kmlPlacemark = (KmlPlacemark) feature;
                    obj = addKmlPlacemarkToMap(kmlPlacemark, feature.getGeometry(), getPlacemarkStyle(feature.getId()), kmlPlacemark.getInlineStyle(), getPlacemarkVisibility(feature));
                } else {
                    obj = addGeoJsonFeatureToMap(feature, feature.getGeometry());
                }
            }
        }
        this.mFeatures.put((Object) feature, obj);
    }

    public static void removeFromMap(Object obj) {
        if (obj instanceof Marker) {
            ((Marker) obj).remove();
        } else if (obj instanceof Polyline) {
            ((Polyline) obj).remove();
        } else if (obj instanceof Polygon) {
            ((Polygon) obj).remove();
        } else if (obj instanceof ArrayList) {
            obj = ((ArrayList) obj).iterator();
            while (obj.hasNext()) {
                removeFromMap(obj.next());
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected java.lang.Object addGeoJsonFeatureToMap(com.google.maps.android.data.Feature r3, com.google.maps.android.data.Geometry r4) {
        /*
        r2 = this;
        r0 = r4.getGeometryType();
        r1 = r0.hashCode();
        switch(r1) {
            case -2116761119: goto L_0x0048;
            case -1065891849: goto L_0x003e;
            case -627102946: goto L_0x0034;
            case 77292912: goto L_0x002a;
            case 1267133722: goto L_0x0020;
            case 1806700869: goto L_0x0016;
            case 1950410960: goto L_0x000c;
            default: goto L_0x000b;
        };
    L_0x000b:
        goto L_0x0052;
    L_0x000c:
        r1 = "GeometryCollection";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0052;
    L_0x0014:
        r0 = 6;
        goto L_0x0053;
    L_0x0016:
        r1 = "LineString";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0052;
    L_0x001e:
        r0 = 1;
        goto L_0x0053;
    L_0x0020:
        r1 = "Polygon";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0052;
    L_0x0028:
        r0 = 2;
        goto L_0x0053;
    L_0x002a:
        r1 = "Point";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0052;
    L_0x0032:
        r0 = 0;
        goto L_0x0053;
    L_0x0034:
        r1 = "MultiLineString";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0052;
    L_0x003c:
        r0 = 4;
        goto L_0x0053;
    L_0x003e:
        r1 = "MultiPoint";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0052;
    L_0x0046:
        r0 = 3;
        goto L_0x0053;
    L_0x0048:
        r1 = "MultiPolygon";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0052;
    L_0x0050:
        r0 = 5;
        goto L_0x0053;
    L_0x0052:
        r0 = -1;
    L_0x0053:
        r1 = 0;
        switch(r0) {
            case 0: goto L_0x00c4;
            case 1: goto L_0x00a8;
            case 2: goto L_0x008c;
            case 3: goto L_0x007f;
            case 4: goto L_0x0072;
            case 5: goto L_0x0065;
            case 6: goto L_0x0058;
            default: goto L_0x0057;
        };
    L_0x0057:
        return r1;
    L_0x0058:
        r3 = (com.google.maps.android.data.geojson.GeoJsonFeature) r3;
        r4 = (com.google.maps.android.data.geojson.GeoJsonGeometryCollection) r4;
        r4 = r4.getGeometries();
        r3 = r2.addGeometryCollectionToMap(r3, r4);
        return r3;
    L_0x0065:
        r3 = (com.google.maps.android.data.geojson.GeoJsonFeature) r3;
        r3 = r3.getPolygonStyle();
        r4 = (com.google.maps.android.data.geojson.GeoJsonMultiPolygon) r4;
        r3 = r2.addMultiPolygonToMap(r3, r4);
        return r3;
    L_0x0072:
        r3 = (com.google.maps.android.data.geojson.GeoJsonFeature) r3;
        r3 = r3.getLineStringStyle();
        r4 = (com.google.maps.android.data.geojson.GeoJsonMultiLineString) r4;
        r3 = r2.addMultiLineStringToMap(r3, r4);
        return r3;
    L_0x007f:
        r3 = (com.google.maps.android.data.geojson.GeoJsonFeature) r3;
        r3 = r3.getPointStyle();
        r4 = (com.google.maps.android.data.geojson.GeoJsonMultiPoint) r4;
        r3 = r2.addMultiPointToMap(r3, r4);
        return r3;
    L_0x008c:
        r0 = r3 instanceof com.google.maps.android.data.geojson.GeoJsonFeature;
        if (r0 == 0) goto L_0x0097;
    L_0x0090:
        r3 = (com.google.maps.android.data.geojson.GeoJsonFeature) r3;
        r1 = r3.getPolygonOptions();
        goto L_0x00a1;
    L_0x0097:
        r0 = r3 instanceof com.google.maps.android.data.kml.KmlPlacemark;
        if (r0 == 0) goto L_0x00a1;
    L_0x009b:
        r3 = (com.google.maps.android.data.kml.KmlPlacemark) r3;
        r1 = r3.getPolygonOptions();
    L_0x00a1:
        r4 = (com.google.maps.android.data.DataPolygon) r4;
        r3 = r2.addPolygonToMap(r1, r4);
        return r3;
    L_0x00a8:
        r0 = r3 instanceof com.google.maps.android.data.geojson.GeoJsonFeature;
        if (r0 == 0) goto L_0x00b3;
    L_0x00ac:
        r3 = (com.google.maps.android.data.geojson.GeoJsonFeature) r3;
        r1 = r3.getPolylineOptions();
        goto L_0x00bd;
    L_0x00b3:
        r0 = r3 instanceof com.google.maps.android.data.kml.KmlPlacemark;
        if (r0 == 0) goto L_0x00bd;
    L_0x00b7:
        r3 = (com.google.maps.android.data.kml.KmlPlacemark) r3;
        r1 = r3.getPolylineOptions();
    L_0x00bd:
        r4 = (com.google.maps.android.data.geojson.GeoJsonLineString) r4;
        r3 = r2.addLineStringToMap(r1, r4);
        return r3;
    L_0x00c4:
        r0 = r3 instanceof com.google.maps.android.data.geojson.GeoJsonFeature;
        if (r0 == 0) goto L_0x00cf;
    L_0x00c8:
        r3 = (com.google.maps.android.data.geojson.GeoJsonFeature) r3;
        r1 = r3.getMarkerOptions();
        goto L_0x00d9;
    L_0x00cf:
        r0 = r3 instanceof com.google.maps.android.data.kml.KmlPlacemark;
        if (r0 == 0) goto L_0x00d9;
    L_0x00d3:
        r3 = (com.google.maps.android.data.kml.KmlPlacemark) r3;
        r1 = r3.getMarkerOptions();
    L_0x00d9:
        r4 = (com.google.maps.android.data.geojson.GeoJsonPoint) r4;
        r3 = r2.addPointToMap(r1, r4);
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.maps.android.data.Renderer.addGeoJsonFeatureToMap(com.google.maps.android.data.Feature, com.google.maps.android.data.Geometry):java.lang.Object");
    }

    protected java.lang.Object addKmlPlacemarkToMap(com.google.maps.android.data.kml.KmlPlacemark r11, com.google.maps.android.data.Geometry r12, com.google.maps.android.data.kml.KmlStyle r13, com.google.maps.android.data.kml.KmlStyle r14, boolean r15) {
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
        r10 = this;
        r0 = r12.getGeometryType();
        r1 = "drawOrder";
        r1 = r11.hasProperty(r1);
        r2 = 0;
        r3 = 0;
        if (r1 == 0) goto L_0x001b;
    L_0x000e:
        r4 = "drawOrder";	 Catch:{ NumberFormatException -> 0x001a }
        r4 = r11.getProperty(r4);	 Catch:{ NumberFormatException -> 0x001a }
        r4 = java.lang.Float.parseFloat(r4);	 Catch:{ NumberFormatException -> 0x001a }
        r3 = r4;
        goto L_0x001b;
    L_0x001a:
        r1 = 0;
    L_0x001b:
        r4 = -1;
        r5 = r0.hashCode();
        r6 = 77292912; // 0x49b6570 float:3.653348E-36 double:3.81877725E-316;
        if (r5 == r6) goto L_0x0053;
    L_0x0025:
        r2 = 89139371; // 0x55028ab float:9.7875825E-36 double:4.4040701E-316;
        if (r5 == r2) goto L_0x0049;
    L_0x002a:
        r2 = 1267133722; // 0x4b86ed1a float:1.7685044E7 double:6.260472407E-315;
        if (r5 == r2) goto L_0x003f;
    L_0x002f:
        r2 = 1806700869; // 0x6bb01145 float:4.25705E26 double:8.926288317E-315;
        if (r5 == r2) goto L_0x0035;
    L_0x0034:
        goto L_0x005c;
    L_0x0035:
        r2 = "LineString";
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x005c;
    L_0x003d:
        r2 = 1;
        goto L_0x005d;
    L_0x003f:
        r2 = "Polygon";
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x005c;
    L_0x0047:
        r2 = 2;
        goto L_0x005d;
    L_0x0049:
        r2 = "MultiGeometry";
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x005c;
    L_0x0051:
        r2 = 3;
        goto L_0x005d;
    L_0x0053:
        r5 = "Point";
        r0 = r0.equals(r5);
        if (r0 == 0) goto L_0x005c;
    L_0x005b:
        goto L_0x005d;
    L_0x005c:
        r2 = -1;
    L_0x005d:
        switch(r2) {
            case 0: goto L_0x00c3;
            case 1: goto L_0x0099;
            case 2: goto L_0x006f;
            case 3: goto L_0x0062;
            default: goto L_0x0060;
        };
    L_0x0060:
        r11 = 0;
        return r11;
    L_0x0062:
        r6 = r12;
        r6 = (com.google.maps.android.data.kml.KmlMultiGeometry) r6;
        r4 = r10;
        r5 = r11;
        r7 = r13;
        r8 = r14;
        r9 = r15;
        r11 = r4.addMultiGeometryToMap(r5, r6, r7, r8, r9);
        return r11;
    L_0x006f:
        r11 = r13.getPolygonOptions();
        if (r14 == 0) goto L_0x0079;
    L_0x0075:
        r10.setInlinePolygonStyle(r11, r14);
        goto L_0x008a;
    L_0x0079:
        r13 = r13.isPolyRandomColorMode();
        if (r13 == 0) goto L_0x008a;
    L_0x007f:
        r13 = r11.getFillColor();
        r13 = com.google.maps.android.data.kml.KmlStyle.computeRandomColor(r13);
        r11.fillColor(r13);
    L_0x008a:
        r12 = (com.google.maps.android.data.DataPolygon) r12;
        r11 = r10.addPolygonToMap(r11, r12);
        r11.setVisible(r15);
        if (r1 == 0) goto L_0x0098;
    L_0x0095:
        r11.setZIndex(r3);
    L_0x0098:
        return r11;
    L_0x0099:
        r11 = r13.getPolylineOptions();
        if (r14 == 0) goto L_0x00a3;
    L_0x009f:
        r10.setInlineLineStringStyle(r11, r14);
        goto L_0x00b4;
    L_0x00a3:
        r13 = r13.isLineRandomColorMode();
        if (r13 == 0) goto L_0x00b4;
    L_0x00a9:
        r13 = r11.getColor();
        r13 = com.google.maps.android.data.kml.KmlStyle.computeRandomColor(r13);
        r11.color(r13);
    L_0x00b4:
        r12 = (com.google.maps.android.data.LineString) r12;
        r11 = r10.addLineStringToMap(r11, r12);
        r11.setVisible(r15);
        if (r1 == 0) goto L_0x00c2;
    L_0x00bf:
        r11.setZIndex(r3);
    L_0x00c2:
        return r11;
    L_0x00c3:
        r0 = r13.getMarkerOptions();
        if (r14 == 0) goto L_0x00d1;
    L_0x00c9:
        r2 = r13.getIconUrl();
        r10.setInlinePointStyle(r0, r14, r2);
        goto L_0x00de;
    L_0x00d1:
        r14 = r13.getIconUrl();
        if (r14 == 0) goto L_0x00de;
    L_0x00d7:
        r14 = r13.getIconUrl();
        r10.addMarkerIcons(r14, r0);
    L_0x00de:
        r12 = (com.google.maps.android.data.kml.KmlPoint) r12;
        r12 = r10.addPointToMap(r0, r12);
        r12.setVisible(r15);
        r10.setMarkerInfoWindow(r13, r12, r11);
        if (r1 == 0) goto L_0x00ef;
    L_0x00ec:
        r12.setZIndex(r3);
    L_0x00ef:
        return r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.maps.android.data.Renderer.addKmlPlacemarkToMap(com.google.maps.android.data.kml.KmlPlacemark, com.google.maps.android.data.Geometry, com.google.maps.android.data.kml.KmlStyle, com.google.maps.android.data.kml.KmlStyle, boolean):java.lang.Object");
    }

    protected Marker addPointToMap(MarkerOptions markerOptions, Point point) {
        markerOptions.position(point.getGeometryObject());
        return this.mMap.addMarker(markerOptions);
    }

    private void setInlinePointStyle(MarkerOptions markerOptions, KmlStyle kmlStyle, String str) {
        MarkerOptions markerOptions2 = kmlStyle.getMarkerOptions();
        if (kmlStyle.isStyleSet("heading")) {
            markerOptions.rotation(markerOptions2.getRotation());
        }
        if (kmlStyle.isStyleSet("hotSpot")) {
            markerOptions.anchor(markerOptions2.getAnchorU(), markerOptions2.getAnchorV());
        }
        if (kmlStyle.isStyleSet("markerColor")) {
            markerOptions.icon(markerOptions2.getIcon());
        }
        if (kmlStyle.isStyleSet("iconUrl")) {
            addMarkerIcons(kmlStyle.getIconUrl(), markerOptions);
        } else if (str != null) {
            addMarkerIcons(str, markerOptions);
        }
    }

    protected Polyline addLineStringToMap(PolylineOptions polylineOptions, LineString lineString) {
        polylineOptions.addAll(lineString.getGeometryObject());
        polylineOptions = this.mMap.addPolyline(polylineOptions);
        polylineOptions.setClickable(true);
        return polylineOptions;
    }

    private void setInlineLineStringStyle(PolylineOptions polylineOptions, KmlStyle kmlStyle) {
        PolylineOptions polylineOptions2 = kmlStyle.getPolylineOptions();
        if (kmlStyle.isStyleSet("outlineColor")) {
            polylineOptions.color(polylineOptions2.getColor());
        }
        if (kmlStyle.isStyleSet(SettingsJsonConstants.ICON_WIDTH_KEY)) {
            polylineOptions.width(polylineOptions2.getWidth());
        }
        if (kmlStyle.isLineRandomColorMode() != null) {
            polylineOptions.color(KmlStyle.computeRandomColor(polylineOptions2.getColor()));
        }
    }

    protected Polygon addPolygonToMap(PolygonOptions polygonOptions, DataPolygon dataPolygon) {
        polygonOptions.addAll(dataPolygon.getOuterBoundaryCoordinates());
        for (List addHole : dataPolygon.getInnerBoundaryCoordinates()) {
            polygonOptions.addHole(addHole);
        }
        polygonOptions = this.mMap.addPolygon(polygonOptions);
        polygonOptions.setClickable(true);
        return polygonOptions;
    }

    private void setInlinePolygonStyle(PolygonOptions polygonOptions, KmlStyle kmlStyle) {
        PolygonOptions polygonOptions2 = kmlStyle.getPolygonOptions();
        if (kmlStyle.hasFill() && kmlStyle.isStyleSet("fillColor")) {
            polygonOptions.fillColor(polygonOptions2.getFillColor());
        }
        if (kmlStyle.hasOutline()) {
            if (kmlStyle.isStyleSet("outlineColor")) {
                polygonOptions.strokeColor(polygonOptions2.getStrokeColor());
            }
            if (kmlStyle.isStyleSet(SettingsJsonConstants.ICON_WIDTH_KEY)) {
                polygonOptions.strokeWidth(polygonOptions2.getStrokeWidth());
            }
        }
        if (kmlStyle.isPolyRandomColorMode() != null) {
            polygonOptions.fillColor(KmlStyle.computeRandomColor(polygonOptions2.getFillColor()));
        }
    }

    private ArrayList<Object> addGeometryCollectionToMap(GeoJsonFeature geoJsonFeature, List<Geometry> list) {
        ArrayList<Object> arrayList = new ArrayList();
        for (Geometry addGeoJsonFeatureToMap : list) {
            arrayList.add(addGeoJsonFeatureToMap(geoJsonFeature, addGeoJsonFeatureToMap));
        }
        return arrayList;
    }

    protected static boolean getPlacemarkVisibility(Feature feature) {
        return (feature.hasProperty("visibility") && Integer.parseInt(feature.getProperty("visibility")) == null) ? null : true;
    }

    public void assignStyleMap(HashMap<String, String> hashMap, HashMap<String, KmlStyle> hashMap2) {
        for (String str : hashMap.keySet()) {
            String str2 = (String) hashMap.get(str);
            if (hashMap2.containsKey(str2)) {
                hashMap2.put(str, hashMap2.get(str2));
            }
        }
    }

    private ArrayList<Object> addMultiGeometryToMap(KmlPlacemark kmlPlacemark, KmlMultiGeometry kmlMultiGeometry, KmlStyle kmlStyle, KmlStyle kmlStyle2, boolean z) {
        ArrayList<Object> arrayList = new ArrayList();
        kmlMultiGeometry = kmlMultiGeometry.getGeometryObject().iterator();
        while (kmlMultiGeometry.hasNext()) {
            arrayList.add(addKmlPlacemarkToMap(kmlPlacemark, (Geometry) kmlMultiGeometry.next(), kmlStyle, kmlStyle2, z));
        }
        return arrayList;
    }

    private ArrayList<Marker> addMultiPointToMap(GeoJsonPointStyle geoJsonPointStyle, GeoJsonMultiPoint geoJsonMultiPoint) {
        ArrayList<Marker> arrayList = new ArrayList();
        for (GeoJsonPoint addPointToMap : geoJsonMultiPoint.getPoints()) {
            arrayList.add(addPointToMap(geoJsonPointStyle.toMarkerOptions(), addPointToMap));
        }
        return arrayList;
    }

    private ArrayList<Polyline> addMultiLineStringToMap(GeoJsonLineStringStyle geoJsonLineStringStyle, GeoJsonMultiLineString geoJsonMultiLineString) {
        ArrayList<Polyline> arrayList = new ArrayList();
        for (GeoJsonLineString addLineStringToMap : geoJsonMultiLineString.getLineStrings()) {
            arrayList.add(addLineStringToMap(geoJsonLineStringStyle.toPolylineOptions(), addLineStringToMap));
        }
        return arrayList;
    }

    private ArrayList<Polygon> addMultiPolygonToMap(GeoJsonPolygonStyle geoJsonPolygonStyle, GeoJsonMultiPolygon geoJsonMultiPolygon) {
        ArrayList<Polygon> arrayList = new ArrayList();
        for (GeoJsonPolygon addPolygonToMap : geoJsonMultiPolygon.getPolygons()) {
            arrayList.add(addPolygonToMap(geoJsonPolygonStyle.toPolygonOptions(), addPolygonToMap));
        }
        return arrayList;
    }

    private void addMarkerIcons(String str, MarkerOptions markerOptions) {
        if (this.mImagesCache.get(str) != null) {
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap((Bitmap) this.mImagesCache.get(str)));
        } else if (this.mMarkerIconUrls.contains(str) == null) {
            this.mMarkerIconUrls.add(str);
        }
    }

    public GroundOverlay attachGroundOverlay(GroundOverlayOptions groundOverlayOptions) {
        return this.mMap.addGroundOverlay(groundOverlayOptions);
    }

    private void setMarkerInfoWindow(KmlStyle kmlStyle, Marker marker, KmlPlacemark kmlPlacemark) {
        boolean hasProperty = kmlPlacemark.hasProperty("name");
        boolean hasProperty2 = kmlPlacemark.hasProperty("description");
        boolean hasBalloonStyle = kmlStyle.hasBalloonStyle();
        boolean containsKey = kmlStyle.getBalloonOptions().containsKey("text");
        if (hasBalloonStyle && containsKey) {
            marker.setTitle((String) kmlStyle.getBalloonOptions().get("text"));
            createInfoWindow();
        } else if (hasBalloonStyle && hasProperty) {
            marker.setTitle(kmlPlacemark.getProperty("name"));
            createInfoWindow();
        } else if (hasProperty && hasProperty2) {
            marker.setTitle(kmlPlacemark.getProperty("name"));
            marker.setSnippet(kmlPlacemark.getProperty("description"));
            createInfoWindow();
        } else if (hasProperty2) {
            marker.setTitle(kmlPlacemark.getProperty("description"));
            createInfoWindow();
        } else if (hasProperty) {
            marker.setTitle(kmlPlacemark.getProperty("name"));
            createInfoWindow();
        }
    }

    private void createInfoWindow() {
        this.mMap.setInfoWindowAdapter(new C07931());
    }
}
