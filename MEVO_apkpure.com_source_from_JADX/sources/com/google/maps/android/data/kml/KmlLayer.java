package com.google.maps.android.data.kml;

import android.content.Context;
import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.data.Layer;
import com.google.maps.android.data.Renderer;
import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class KmlLayer extends Layer {
    public KmlLayer(GoogleMap googleMap, int i, Context context) throws XmlPullParserException, IOException {
        this(googleMap, context.getResources().openRawResource(i), context);
    }

    public KmlLayer(GoogleMap googleMap, InputStream inputStream, Context context) throws XmlPullParserException, IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("KML InputStream cannot be null");
        }
        Renderer kmlRenderer = new KmlRenderer(googleMap, context);
        context = new KmlParser(createXmlParser(inputStream));
        context.parseKml();
        inputStream.close();
        kmlRenderer.storeKmlData(context.getStyles(), context.getStyleMaps(), context.getPlacemarks(), context.getContainers(), context.getGroundOverlays());
        storeRenderer(kmlRenderer);
    }

    private static XmlPullParser createXmlParser(InputStream inputStream) throws XmlPullParserException {
        XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
        newInstance.setNamespaceAware(true);
        XmlPullParser newPullParser = newInstance.newPullParser();
        newPullParser.setInput(inputStream, null);
        return newPullParser;
    }

    public void addLayerToMap() throws IOException, XmlPullParserException {
        super.addKMLToMap();
    }

    public boolean hasPlacemarks() {
        return hasFeatures();
    }

    public Iterable<KmlPlacemark> getPlacemarks() {
        return getFeatures();
    }

    public boolean hasContainers() {
        return super.hasContainers();
    }

    public Iterable<KmlContainer> getContainers() {
        return super.getContainers();
    }

    public Iterable<KmlGroundOverlay> getGroundOverlays() {
        return super.getGroundOverlays();
    }
}
