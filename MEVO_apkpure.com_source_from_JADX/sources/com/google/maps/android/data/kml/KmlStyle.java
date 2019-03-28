package com.google.maps.android.data.kml;

import android.graphics.Color;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.data.Style;
import com.mevo.app.constants.BikeTypes;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class KmlStyle extends Style {
    private static final int HSV_VALUES = 3;
    private static final int HUE_VALUE = 0;
    private static final int INITIAL_SCALE = 1;
    private final HashMap<String, String> mBalloonOptions = new HashMap();
    private boolean mFill = true;
    private boolean mIconRandomColorMode = false;
    private String mIconUrl;
    private boolean mLineRandomColorMode = false;
    private float mMarkerColor = 0.0f;
    private boolean mOutline = true;
    private boolean mPolyRandomColorMode = false;
    private double mScale = 1.0d;
    private String mStyleId = null;
    private final HashSet<String> mStylesSet = new HashSet();

    KmlStyle() {
    }

    void setInfoWindowText(String str) {
        this.mBalloonOptions.put("text", str);
    }

    String getStyleId() {
        return this.mStyleId;
    }

    void setStyleId(String str) {
        this.mStyleId = str;
    }

    public boolean isStyleSet(String str) {
        return this.mStylesSet.contains(str);
    }

    public boolean hasFill() {
        return this.mFill;
    }

    public void setFill(boolean z) {
        this.mFill = z;
    }

    double getIconScale() {
        return this.mScale;
    }

    void setIconScale(double d) {
        this.mScale = d;
        this.mStylesSet.add("iconScale");
    }

    public boolean hasOutline() {
        return this.mOutline;
    }

    public boolean hasBalloonStyle() {
        return this.mBalloonOptions.size() > 0;
    }

    void setOutline(boolean z) {
        this.mOutline = z;
        this.mStylesSet.add("outline");
    }

    public String getIconUrl() {
        return this.mIconUrl;
    }

    void setIconUrl(String str) {
        this.mIconUrl = str;
        this.mStylesSet.add("iconUrl");
    }

    void setFillColor(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("#");
        stringBuilder.append(convertColor(str));
        setPolygonFillColor(Color.parseColor(stringBuilder.toString()));
        this.mStylesSet.add("fillColor");
    }

    void setMarkerColor(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("#");
        stringBuilder.append(convertColor(str));
        this.mMarkerColor = getHueValue(Color.parseColor(stringBuilder.toString()));
        this.mMarkerOptions.icon(BitmapDescriptorFactory.defaultMarker(this.mMarkerColor));
        this.mStylesSet.add("markerColor");
    }

    private static float getHueValue(int i) {
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        return fArr[0];
    }

    private static String convertColor(String str) {
        StringBuilder stringBuilder;
        if (str.length() > 6) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str.substring(0, 2));
            stringBuilder.append(str.substring(6, 8));
            stringBuilder.append(str.substring(4, 6));
            stringBuilder.append(str.substring(2, 4));
            str = stringBuilder.toString();
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str.substring(4, 6));
            stringBuilder.append(str.substring(2, 4));
            stringBuilder.append(str.substring(0, 2));
            str = stringBuilder.toString();
        }
        if (!str.substring(0, 1).equals(" ")) {
            return str;
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(BikeTypes.DEFAULT);
        stringBuilder2.append(str.substring(1, str.length()));
        return stringBuilder2.toString();
    }

    void setHeading(float f) {
        setMarkerRotation(f);
        this.mStylesSet.add("heading");
    }

    void setHotSpot(float f, float f2, String str, String str2) {
        setMarkerHotSpot(f, f2, str, str2);
        this.mStylesSet.add("hotSpot");
    }

    void setIconColorMode(String str) {
        this.mIconRandomColorMode = str.equals("random");
        this.mStylesSet.add("iconColorMode");
    }

    boolean isIconRandomColorMode() {
        return this.mIconRandomColorMode;
    }

    void setLineColorMode(String str) {
        this.mLineRandomColorMode = str.equals("random");
        this.mStylesSet.add("lineColorMode");
    }

    public boolean isLineRandomColorMode() {
        return this.mLineRandomColorMode;
    }

    void setPolyColorMode(String str) {
        this.mPolyRandomColorMode = str.equals("random");
        this.mStylesSet.add("polyColorMode");
    }

    public boolean isPolyRandomColorMode() {
        return this.mPolyRandomColorMode;
    }

    void setOutlineColor(String str) {
        PolylineOptions polylineOptions = this.mPolylineOptions;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("#");
        stringBuilder.append(convertColor(str));
        polylineOptions.color(Color.parseColor(stringBuilder.toString()));
        PolygonOptions polygonOptions = this.mPolygonOptions;
        stringBuilder = new StringBuilder();
        stringBuilder.append("#");
        stringBuilder.append(str);
        polygonOptions.strokeColor(Color.parseColor(stringBuilder.toString()));
        this.mStylesSet.add("outlineColor");
    }

    void setWidth(Float f) {
        setLineStringWidth(f.floatValue());
        setPolygonStrokeWidth(f.floatValue());
        this.mStylesSet.add(SettingsJsonConstants.ICON_WIDTH_KEY);
    }

    public HashMap<String, String> getBalloonOptions() {
        return this.mBalloonOptions;
    }

    private static MarkerOptions createMarkerOptions(MarkerOptions markerOptions, boolean z, float f) {
        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.rotation(markerOptions.getRotation());
        markerOptions2.anchor(markerOptions.getAnchorU(), markerOptions.getAnchorV());
        if (z) {
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(getHueValue(computeRandomColor((int) f))));
        }
        markerOptions2.icon(markerOptions.getIcon());
        return markerOptions2;
    }

    private static PolylineOptions createPolylineOptions(PolylineOptions polylineOptions) {
        PolylineOptions polylineOptions2 = new PolylineOptions();
        polylineOptions2.color(polylineOptions.getColor());
        polylineOptions2.width(polylineOptions.getWidth());
        return polylineOptions2;
    }

    private static PolygonOptions createPolygonOptions(PolygonOptions polygonOptions, boolean z, boolean z2) {
        PolygonOptions polygonOptions2 = new PolygonOptions();
        if (z) {
            polygonOptions2.fillColor(polygonOptions.getFillColor());
        }
        if (z2) {
            polygonOptions2.strokeColor(polygonOptions.getStrokeColor());
            polygonOptions2.strokeWidth(polygonOptions.getStrokeWidth());
        }
        return polygonOptions2;
    }

    public MarkerOptions getMarkerOptions() {
        return createMarkerOptions(this.mMarkerOptions, isIconRandomColorMode(), this.mMarkerColor);
    }

    public PolylineOptions getPolylineOptions() {
        return createPolylineOptions(this.mPolylineOptions);
    }

    public PolygonOptions getPolygonOptions() {
        return createPolygonOptions(this.mPolygonOptions, this.mFill, this.mOutline);
    }

    public static int computeRandomColor(int i) {
        Random random = new Random();
        int red = Color.red(i);
        int green = Color.green(i);
        i = Color.blue(i);
        if (red != 0) {
            red = random.nextInt(red);
        }
        if (i != 0) {
            i = random.nextInt(i);
        }
        if (green != 0) {
            green = random.nextInt(green);
        }
        return Color.rgb(red, green, i);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Style");
        stringBuilder.append("{");
        stringBuilder.append("\n balloon options=");
        stringBuilder.append(this.mBalloonOptions);
        stringBuilder.append(",\n fill=");
        stringBuilder.append(this.mFill);
        stringBuilder.append(",\n outline=");
        stringBuilder.append(this.mOutline);
        stringBuilder.append(",\n icon url=");
        stringBuilder.append(this.mIconUrl);
        stringBuilder.append(",\n scale=");
        stringBuilder.append(this.mScale);
        stringBuilder.append(",\n style id=");
        stringBuilder.append(this.mStyleId);
        stringBuilder.append("\n}\n");
        return stringBuilder.toString();
    }
}
