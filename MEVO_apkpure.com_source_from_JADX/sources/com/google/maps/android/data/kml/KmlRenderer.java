package com.google.maps.android.data.kml;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.Geometry;
import com.google.maps.android.data.Renderer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class KmlRenderer extends Renderer {
    private static final String LOG_TAG = "KmlRenderer";
    private ArrayList<KmlContainer> mContainers;
    private boolean mGroundOverlayImagesDownloaded = false;
    private final ArrayList<String> mGroundOverlayUrls = new ArrayList();
    private HashMap<KmlGroundOverlay, GroundOverlay> mGroundOverlays;
    private boolean mMarkerIconsDownloaded = false;

    private class GroundOverlayImageDownload extends AsyncTask<String, Void, Bitmap> {
        private final String mGroundOverlayUrl;

        public GroundOverlayImageDownload(String str) {
            this.mGroundOverlayUrl = str;
        }

        protected android.graphics.Bitmap doInBackground(java.lang.String... r4) {
            /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
            /*
            r3 = this;
            r4 = new java.net.URL;	 Catch:{ MalformedURLException -> 0x0032, IOException -> 0x0012 }
            r0 = r3.mGroundOverlayUrl;	 Catch:{ MalformedURLException -> 0x0032, IOException -> 0x0012 }
            r4.<init>(r0);	 Catch:{ MalformedURLException -> 0x0032, IOException -> 0x0012 }
            r4 = r4.getContent();	 Catch:{ MalformedURLException -> 0x0032, IOException -> 0x0012 }
            r4 = (java.io.InputStream) r4;	 Catch:{ MalformedURLException -> 0x0032, IOException -> 0x0012 }
            r4 = android.graphics.BitmapFactory.decodeStream(r4);	 Catch:{ MalformedURLException -> 0x0032, IOException -> 0x0012 }
            return r4;
        L_0x0012:
            r4 = move-exception;
            r0 = "KmlRenderer";
            r1 = new java.lang.StringBuilder;
            r1.<init>();
            r2 = "Image [";
            r1.append(r2);
            r2 = r3.mGroundOverlayUrl;
            r1.append(r2);
            r2 = "] download issue";
            r1.append(r2);
            r1 = r1.toString();
            android.util.Log.e(r0, r1, r4);
            r4 = 0;
            return r4;
        L_0x0032:
            r4 = r3.mGroundOverlayUrl;
            r4 = android.graphics.BitmapFactory.decodeFile(r4);
            return r4;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.maps.android.data.kml.KmlRenderer.GroundOverlayImageDownload.doInBackground(java.lang.String[]):android.graphics.Bitmap");
        }

        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap == null) {
                bitmap = KmlRenderer.LOG_TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Image at this URL could not be found ");
                stringBuilder.append(this.mGroundOverlayUrl);
                Log.e(bitmap, stringBuilder.toString());
                return;
            }
            KmlRenderer.this.putImagesCache(this.mGroundOverlayUrl, bitmap);
            if (KmlRenderer.this.isLayerOnMap() != null) {
                KmlRenderer.this.addGroundOverlayToMap(this.mGroundOverlayUrl, KmlRenderer.this.mGroundOverlays, true);
                KmlRenderer.this.addGroundOverlayInContainerGroups(this.mGroundOverlayUrl, KmlRenderer.this.mContainers, true);
            }
        }
    }

    private class MarkerIconImageDownload extends AsyncTask<String, Void, Bitmap> {
        private final String mIconUrl;

        public MarkerIconImageDownload(String str) {
            this.mIconUrl = str;
        }

        protected android.graphics.Bitmap doInBackground(java.lang.String... r2) {
            /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
            /*
            r1 = this;
            r2 = new java.net.URL;	 Catch:{ MalformedURLException -> 0x0018, IOException -> 0x0012 }
            r0 = r1.mIconUrl;	 Catch:{ MalformedURLException -> 0x0018, IOException -> 0x0012 }
            r2.<init>(r0);	 Catch:{ MalformedURLException -> 0x0018, IOException -> 0x0012 }
            r2 = r2.getContent();	 Catch:{ MalformedURLException -> 0x0018, IOException -> 0x0012 }
            r2 = (java.io.InputStream) r2;	 Catch:{ MalformedURLException -> 0x0018, IOException -> 0x0012 }
            r2 = android.graphics.BitmapFactory.decodeStream(r2);	 Catch:{ MalformedURLException -> 0x0018, IOException -> 0x0012 }
            return r2;
        L_0x0012:
            r2 = move-exception;
            r2.printStackTrace();
            r2 = 0;
            return r2;
        L_0x0018:
            r2 = r1.mIconUrl;
            r2 = android.graphics.BitmapFactory.decodeFile(r2);
            return r2;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.maps.android.data.kml.KmlRenderer.MarkerIconImageDownload.doInBackground(java.lang.String[]):android.graphics.Bitmap");
        }

        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap == null) {
                bitmap = KmlRenderer.LOG_TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Image at this URL could not be found ");
                stringBuilder.append(this.mIconUrl);
                Log.e(bitmap, stringBuilder.toString());
                return;
            }
            KmlRenderer.this.putImagesCache(this.mIconUrl, bitmap);
            if (KmlRenderer.this.isLayerOnMap() != null) {
                KmlRenderer.this.addIconToMarkers(this.mIconUrl, KmlRenderer.this.getAllFeatures());
                KmlRenderer.this.addContainerGroupIconsToMarkers(this.mIconUrl, KmlRenderer.this.mContainers);
            }
        }
    }

    KmlRenderer(GoogleMap googleMap, Context context) {
        super(googleMap, context);
    }

    private static BitmapDescriptor scaleIcon(Bitmap bitmap, Double d) {
        return BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(bitmap, (int) (((double) bitmap.getWidth()) * d.doubleValue()), (int) (((double) bitmap.getHeight()) * d.doubleValue()), false));
    }

    private void removePlacemarks(HashMap<? extends Feature, Object> hashMap) {
        Renderer.removeFeatures(hashMap);
    }

    static boolean getContainerVisibility(KmlContainer kmlContainer, boolean z) {
        kmlContainer = (kmlContainer.hasProperty("visibility") && Integer.parseInt(kmlContainer.getProperty("visibility")) == null) ? null : true;
        if (!z || kmlContainer == null) {
            return false;
        }
        return true;
    }

    private void removeGroundOverlays(HashMap<KmlGroundOverlay, GroundOverlay> hashMap) {
        hashMap = hashMap.values().iterator();
        while (hashMap.hasNext()) {
            ((GroundOverlay) hashMap.next()).remove();
        }
    }

    private void removeContainers(Iterable<KmlContainer> iterable) {
        for (KmlContainer kmlContainer : iterable) {
            removePlacemarks(kmlContainer.getPlacemarksHashMap());
            removeGroundOverlays(kmlContainer.getGroundOverlayHashMap());
            removeContainers(kmlContainer.getContainers());
        }
    }

    public void addLayerToMap() {
        setLayerVisibility(true);
        this.mGroundOverlays = getGroundOverlayMap();
        this.mContainers = getContainerList();
        putStyles();
        assignStyleMap(getStyleMaps(), getStylesRenderer());
        addGroundOverlays(this.mGroundOverlays, this.mContainers);
        addContainerGroupToMap(this.mContainers, true);
        addPlacemarksToMap(getAllFeatures());
        if (!this.mGroundOverlayImagesDownloaded) {
            downloadGroundOverlays();
        }
        if (!this.mMarkerIconsDownloaded) {
            downloadMarkerIcons();
        }
    }

    void storeKmlData(HashMap<String, KmlStyle> hashMap, HashMap<String, String> hashMap2, HashMap<KmlPlacemark, Object> hashMap3, ArrayList<KmlContainer> arrayList, HashMap<KmlGroundOverlay, GroundOverlay> hashMap4) {
        storeData(hashMap, hashMap2, hashMap3, arrayList, hashMap4);
    }

    public void setMap(GoogleMap googleMap) {
        removeLayerFromMap();
        super.setMap(googleMap);
        addLayerToMap();
    }

    boolean hasKmlPlacemarks() {
        return hasFeatures();
    }

    Iterable<? extends Feature> getKmlPlacemarks() {
        return getFeatures();
    }

    public boolean hasNestedContainers() {
        return this.mContainers.size() > 0;
    }

    public Iterable<KmlContainer> getNestedContainers() {
        return this.mContainers;
    }

    public Iterable<KmlGroundOverlay> getGroundOverlays() {
        return this.mGroundOverlays.keySet();
    }

    public void removeLayerFromMap() {
        removePlacemarks(getAllFeatures());
        removeGroundOverlays(this.mGroundOverlays);
        if (hasNestedContainers()) {
            removeContainers(getNestedContainers());
        }
        setLayerVisibility(false);
        clearStylesRenderer();
    }

    private void addPlacemarksToMap(HashMap<? extends Feature, Object> hashMap) {
        hashMap = hashMap.keySet().iterator();
        while (hashMap.hasNext()) {
            addFeature((Feature) hashMap.next());
        }
    }

    private void addContainerGroupToMap(Iterable<KmlContainer> iterable, boolean z) {
        for (KmlContainer kmlContainer : iterable) {
            boolean containerVisibility = getContainerVisibility(kmlContainer, z);
            if (kmlContainer.getStyles() != null) {
                putStyles(kmlContainer.getStyles());
            }
            if (kmlContainer.getStyleMap() != null) {
                super.assignStyleMap(kmlContainer.getStyleMap(), getStylesRenderer());
            }
            addContainerObjectToMap(kmlContainer, containerVisibility);
            if (kmlContainer.hasContainers()) {
                addContainerGroupToMap(kmlContainer.getContainers(), containerVisibility);
            }
        }
    }

    private void addContainerObjectToMap(KmlContainer kmlContainer, boolean z) {
        for (Feature feature : kmlContainer.getPlacemarks()) {
            boolean z2 = z && Renderer.getPlacemarkVisibility(feature);
            if (feature.getGeometry() != null) {
                String id = feature.getId();
                Geometry geometry = feature.getGeometry();
                KmlStyle placemarkStyle = getPlacemarkStyle(id);
                KmlPlacemark kmlPlacemark = (KmlPlacemark) feature;
                Object addKmlPlacemarkToMap = addKmlPlacemarkToMap(kmlPlacemark, geometry, placemarkStyle, kmlPlacemark.getInlineStyle(), z2);
                kmlContainer.setPlacemark(kmlPlacemark, addKmlPlacemarkToMap);
                putContainerFeature(addKmlPlacemarkToMap, feature);
            }
        }
    }

    private void downloadMarkerIcons() {
        this.mMarkerIconsDownloaded = true;
        Iterator it = getMarkerIconUrls().iterator();
        while (it.hasNext()) {
            new MarkerIconImageDownload((String) it.next()).execute(new String[0]);
            it.remove();
        }
    }

    private void addIconToMarkers(String str, HashMap<KmlPlacemark, Object> hashMap) {
        for (Feature feature : hashMap.keySet()) {
            KmlStyle kmlStyle = (KmlStyle) getStylesRenderer().get(feature.getId());
            KmlPlacemark kmlPlacemark = (KmlPlacemark) feature;
            KmlStyle inlineStyle = kmlPlacemark.getInlineStyle();
            if ("Point".equals(feature.getGeometry().getGeometryType())) {
                Object obj = null;
                Object obj2 = (inlineStyle == null || !str.equals(inlineStyle.getIconUrl())) ? null : 1;
                if (kmlStyle != null && str.equals(kmlStyle.getIconUrl())) {
                    obj = 1;
                }
                if (obj2 != null) {
                    scaleBitmap(inlineStyle, hashMap, kmlPlacemark);
                } else if (obj != null) {
                    scaleBitmap(kmlStyle, hashMap, kmlPlacemark);
                }
            }
        }
    }

    private void scaleBitmap(KmlStyle kmlStyle, HashMap<KmlPlacemark, Object> hashMap, KmlPlacemark kmlPlacemark) {
        double iconScale = kmlStyle.getIconScale();
        ((Marker) hashMap.get(kmlPlacemark)).setIcon(scaleIcon((Bitmap) getImagesCache().get(kmlStyle.getIconUrl()), Double.valueOf(iconScale)));
    }

    private void addContainerGroupIconsToMarkers(String str, Iterable<KmlContainer> iterable) {
        for (KmlContainer kmlContainer : iterable) {
            addIconToMarkers(str, kmlContainer.getPlacemarksHashMap());
            if (kmlContainer.hasContainers()) {
                addContainerGroupIconsToMarkers(str, kmlContainer.getContainers());
            }
        }
    }

    private void addGroundOverlays(HashMap<KmlGroundOverlay, GroundOverlay> hashMap, Iterable<KmlContainer> iterable) {
        addGroundOverlays(hashMap);
        for (KmlContainer kmlContainer : iterable) {
            addGroundOverlays(kmlContainer.getGroundOverlayHashMap(), kmlContainer.getContainers());
        }
    }

    private void addGroundOverlays(HashMap<KmlGroundOverlay, GroundOverlay> hashMap) {
        hashMap = hashMap.keySet().iterator();
        while (hashMap.hasNext()) {
            KmlGroundOverlay kmlGroundOverlay = (KmlGroundOverlay) hashMap.next();
            String imageUrl = kmlGroundOverlay.getImageUrl();
            if (!(imageUrl == null || kmlGroundOverlay.getLatLngBox() == null)) {
                if (getImagesCache().get(imageUrl) != null) {
                    addGroundOverlayToMap(imageUrl, this.mGroundOverlays, true);
                } else if (!this.mGroundOverlayUrls.contains(imageUrl)) {
                    this.mGroundOverlayUrls.add(imageUrl);
                }
            }
        }
    }

    private void downloadGroundOverlays() {
        this.mGroundOverlayImagesDownloaded = true;
        Iterator it = this.mGroundOverlayUrls.iterator();
        while (it.hasNext()) {
            new GroundOverlayImageDownload((String) it.next()).execute(new String[0]);
            it.remove();
        }
    }

    private void addGroundOverlayToMap(String str, HashMap<KmlGroundOverlay, GroundOverlay> hashMap, boolean z) {
        BitmapDescriptor fromBitmap = BitmapDescriptorFactory.fromBitmap((Bitmap) getImagesCache().get(str));
        for (KmlGroundOverlay kmlGroundOverlay : hashMap.keySet()) {
            if (kmlGroundOverlay.getImageUrl().equals(str)) {
                GroundOverlay attachGroundOverlay = attachGroundOverlay(kmlGroundOverlay.getGroundOverlayOptions().image(fromBitmap));
                if (!z) {
                    attachGroundOverlay.setVisible(false);
                }
                hashMap.put(kmlGroundOverlay, attachGroundOverlay);
            }
        }
    }

    private void addGroundOverlayInContainerGroups(String str, Iterable<KmlContainer> iterable, boolean z) {
        for (KmlContainer kmlContainer : iterable) {
            boolean containerVisibility = getContainerVisibility(kmlContainer, z);
            addGroundOverlayToMap(str, kmlContainer.getGroundOverlayHashMap(), containerVisibility);
            if (kmlContainer.hasContainers()) {
                addGroundOverlayInContainerGroups(str, kmlContainer.getContainers(), containerVisibility);
            }
        }
    }
}
