package com.google.maps.android.data.kml;

public class KmlBoolean {
    public static boolean parseBoolean(String str) {
        if (!"1".equals(str)) {
            if ("true".equals(str) == null) {
                return null;
            }
        }
        return true;
    }
}
