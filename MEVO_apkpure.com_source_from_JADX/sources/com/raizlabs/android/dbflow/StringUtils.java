package com.raizlabs.android.dbflow;

public class StringUtils {
    public static boolean isNotNullOrEmpty(String str) {
        return (str == null || str.equals("") || str.length() <= null) ? null : true;
    }

    public static boolean isNullOrEmpty(String str) {
        if (!(str == null || str.equals(""))) {
            if (str.length() > null) {
                return null;
            }
        }
        return true;
    }
}
