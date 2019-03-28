package com.google.maps.android;

import com.google.android.gms.maps.model.LatLng;
import java.util.List;

public class SphericalUtil {
    private SphericalUtil() {
    }

    public static double computeHeading(LatLng latLng, LatLng latLng2) {
        double toRadians = Math.toRadians(latLng.latitude);
        double toRadians2 = Math.toRadians(latLng.longitude);
        double toRadians3 = Math.toRadians(latLng2.latitude);
        latLng = Math.toRadians(latLng2.longitude) - toRadians2;
        return MathUtil.wrap(Math.toDegrees(Math.atan2(Math.sin(latLng) * Math.cos(toRadians3), (Math.cos(toRadians) * Math.sin(toRadians3)) - ((Math.sin(toRadians) * Math.cos(toRadians3)) * Math.cos(latLng)))), -180.0d, 180.0d);
    }

    public static LatLng computeOffset(LatLng latLng, double d, double d2) {
        d /= 6371009.0d;
        d2 = Math.toRadians(d2);
        double toRadians = Math.toRadians(latLng.latitude);
        double toRadians2 = Math.toRadians(latLng.longitude);
        double cos = Math.cos(d);
        latLng = Math.sin(d);
        double sin = Math.sin(toRadians);
        latLng *= Math.cos(toRadians);
        double cos2 = (cos * sin) + (Math.cos(d2) * latLng);
        return new LatLng(Math.toDegrees(Math.asin(cos2)), Math.toDegrees(toRadians2 + Math.atan2(latLng * Math.sin(d2), cos - (sin * cos2))));
    }

    public static LatLng computeOffsetOrigin(LatLng latLng, double d, double d2) {
        LatLng latLng2 = latLng;
        double toRadians = Math.toRadians(d2);
        double d3 = d / 6371009.0d;
        double cos = Math.cos(d3);
        double sin = Math.sin(d3) * Math.cos(toRadians);
        d3 = Math.sin(d3) * Math.sin(toRadians);
        toRadians = Math.sin(Math.toRadians(latLng2.latitude));
        double d4 = cos * cos;
        double d5 = sin * sin;
        double d6 = ((d5 * d4) + (d4 * d4)) - ((d4 * toRadians) * toRadians);
        if (d6 < 0.0d) {
            return null;
        }
        double d7 = sin * toRadians;
        d4 += d5;
        d5 = (d7 + Math.sqrt(d6)) / d4;
        toRadians = (toRadians - (sin * d5)) / cos;
        d5 = Math.atan2(toRadians, d5);
        if (d5 < -1.5707963267948966d || d5 > 1.5707963267948966d) {
            d5 = Math.atan2(toRadians, (d7 - Math.sqrt(d6)) / d4);
        }
        if (d5 >= -1.5707963267948966d) {
            if (d5 <= 1.5707963267948966d) {
                return new LatLng(Math.toDegrees(d5), Math.toDegrees(Math.toRadians(latLng2.longitude) - Math.atan2(d3, (cos * Math.cos(d5)) - (sin * Math.sin(d5)))));
            }
        }
        return null;
    }

    public static LatLng interpolate(LatLng latLng, LatLng latLng2, double d) {
        LatLng latLng3 = latLng;
        LatLng latLng4 = latLng2;
        double toRadians = Math.toRadians(latLng3.latitude);
        double toRadians2 = Math.toRadians(latLng3.longitude);
        double toRadians3 = Math.toRadians(latLng4.latitude);
        double toRadians4 = Math.toRadians(latLng4.longitude);
        double cos = Math.cos(toRadians);
        double cos2 = Math.cos(toRadians3);
        double d2 = toRadians3;
        toRadians3 = computeAngleBetween(latLng, latLng2);
        double sin = Math.sin(toRadians3);
        if (sin < 1.0E-6d) {
            return latLng3;
        }
        double sin2 = Math.sin((1.0d - d) * toRadians3) / sin;
        double sin3 = Math.sin(d * toRadians3) / sin;
        cos *= sin2;
        cos2 *= sin3;
        toRadians3 = (Math.cos(toRadians2) * cos) + (Math.cos(toRadians4) * cos2);
        cos = (cos * Math.sin(toRadians2)) + (cos2 * Math.sin(toRadians4));
        return new LatLng(Math.toDegrees(Math.atan2((sin2 * Math.sin(toRadians)) + (sin3 * Math.sin(d2)), Math.sqrt((toRadians3 * toRadians3) + (cos * cos)))), Math.toDegrees(Math.atan2(cos, toRadians3)));
    }

    private static double distanceRadians(double d, double d2, double d3, double d4) {
        return MathUtil.arcHav(MathUtil.havDistance(d, d3, d2 - d4));
    }

    static double computeAngleBetween(LatLng latLng, LatLng latLng2) {
        return distanceRadians(Math.toRadians(latLng.latitude), Math.toRadians(latLng.longitude), Math.toRadians(latLng2.latitude), Math.toRadians(latLng2.longitude));
    }

    public static double computeDistanceBetween(LatLng latLng, LatLng latLng2) {
        return computeAngleBetween(latLng, latLng2) * 6371009.0d;
    }

    public static double computeLength(List<LatLng> list) {
        double d = 0.0d;
        if (list.size() < 2) {
            return 0.0d;
        }
        LatLng latLng = (LatLng) list.get(0);
        double toRadians = Math.toRadians(latLng.latitude);
        double toRadians2 = Math.toRadians(latLng.longitude);
        for (LatLng latLng2 : list) {
            double toRadians3 = Math.toRadians(latLng2.latitude);
            double toRadians4 = Math.toRadians(latLng2.longitude);
            d += distanceRadians(toRadians, toRadians2, toRadians3, toRadians4);
            toRadians = toRadians3;
            toRadians2 = toRadians4;
        }
        return d * 6371009.0d;
    }

    public static double computeArea(List<LatLng> list) {
        return Math.abs(computeSignedArea(list));
    }

    public static double computeSignedArea(List<LatLng> list) {
        return computeSignedArea(list, 6371009.0d);
    }

    static double computeSignedArea(List<LatLng> list, double d) {
        int size = list.size();
        double d2 = 0.0d;
        if (size < 3) {
            return 0.0d;
        }
        LatLng latLng = (LatLng) list.get(size - 1);
        double tan = Math.tan((1.5707963267948966d - Math.toRadians(latLng.latitude)) / 2.0d);
        double toRadians = Math.toRadians(latLng.longitude);
        double d3 = tan;
        double d4 = toRadians;
        for (LatLng latLng2 : list) {
            tan = Math.tan((1.5707963267948966d - Math.toRadians(latLng2.latitude)) / 2.0d);
            double toRadians2 = Math.toRadians(latLng2.longitude);
            d2 += polarTriangleArea(tan, toRadians2, d3, d4);
            d3 = tan;
            d4 = toRadians2;
        }
        return d2 * (d * d);
    }

    private static double polarTriangleArea(double d, double d2, double d3, double d4) {
        d2 -= d4;
        d *= d3;
        return Math.atan2(Math.sin(d2) * d, (d * Math.cos(d2)) + 1.0d) * 2.0d;
    }
}
