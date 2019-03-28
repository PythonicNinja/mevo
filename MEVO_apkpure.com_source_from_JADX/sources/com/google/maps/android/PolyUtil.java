package com.google.maps.android;

import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class PolyUtil {
    private static final double DEFAULT_TOLERANCE = 0.1d;

    private PolyUtil() {
    }

    private static double tanLatGC(double d, double d2, double d3, double d4) {
        return ((Math.tan(d) * Math.sin(d3 - d4)) + (Math.tan(d2) * Math.sin(d4))) / Math.sin(d3);
    }

    private static double mercatorLatRhumb(double d, double d2, double d3, double d4) {
        return ((MathUtil.mercator(d) * (d3 - d4)) + (MathUtil.mercator(d2) * d4)) / d3;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean intersects(double r18, double r20, double r22, double r24, double r26, boolean r28) {
        /*
        r8 = 0;
        r10 = (r26 > r8 ? 1 : (r26 == r8 ? 0 : -1));
        r11 = 0;
        if (r10 < 0) goto L_0x000b;
    L_0x0007:
        r10 = (r26 > r22 ? 1 : (r26 == r22 ? 0 : -1));
        if (r10 >= 0) goto L_0x0013;
    L_0x000b:
        r10 = (r26 > r8 ? 1 : (r26 == r8 ? 0 : -1));
        if (r10 >= 0) goto L_0x0014;
    L_0x000f:
        r10 = (r26 > r22 ? 1 : (r26 == r22 ? 0 : -1));
        if (r10 >= 0) goto L_0x0014;
    L_0x0013:
        return r11;
    L_0x0014:
        r12 = -4613618979930100456; // 0xbff921fb54442d18 float:3.37028055E12 double:-1.5707963267948966;
        r10 = (r24 > r12 ? 1 : (r24 == r12 ? 0 : -1));
        if (r10 > 0) goto L_0x001e;
    L_0x001d:
        return r11;
    L_0x001e:
        r10 = (r18 > r12 ? 1 : (r18 == r12 ? 0 : -1));
        if (r10 <= 0) goto L_0x0098;
    L_0x0022:
        r10 = (r20 > r12 ? 1 : (r20 == r12 ? 0 : -1));
        if (r10 <= 0) goto L_0x0098;
    L_0x0026:
        r12 = 4609753056924675352; // 0x3ff921fb54442d18 float:3.37028055E12 double:1.5707963267948966;
        r10 = (r18 > r12 ? 1 : (r18 == r12 ? 0 : -1));
        if (r10 >= 0) goto L_0x0098;
    L_0x002f:
        r10 = (r20 > r12 ? 1 : (r20 == r12 ? 0 : -1));
        if (r10 < 0) goto L_0x0034;
    L_0x0033:
        goto L_0x0098;
    L_0x0034:
        r14 = -4609115380302729960; // 0xc00921fb54442d18 float:3.37028055E12 double:-3.141592653589793;
        r10 = (r22 > r14 ? 1 : (r22 == r14 ? 0 : -1));
        if (r10 > 0) goto L_0x003e;
    L_0x003d:
        return r11;
    L_0x003e:
        r10 = 0;
        r14 = r22 - r26;
        r14 = r14 * r18;
        r16 = r20 * r26;
        r14 = r14 + r16;
        r14 = r14 / r22;
        r10 = (r18 > r8 ? 1 : (r18 == r8 ? 0 : -1));
        if (r10 < 0) goto L_0x0056;
    L_0x004d:
        r10 = (r20 > r8 ? 1 : (r20 == r8 ? 0 : -1));
        if (r10 < 0) goto L_0x0056;
    L_0x0051:
        r10 = (r24 > r14 ? 1 : (r24 == r14 ? 0 : -1));
        if (r10 >= 0) goto L_0x0056;
    L_0x0055:
        return r11;
    L_0x0056:
        r10 = (r18 > r8 ? 1 : (r18 == r8 ? 0 : -1));
        r16 = 1;
        if (r10 > 0) goto L_0x0065;
    L_0x005c:
        r10 = (r20 > r8 ? 1 : (r20 == r8 ? 0 : -1));
        if (r10 > 0) goto L_0x0065;
    L_0x0060:
        r8 = (r24 > r14 ? 1 : (r24 == r14 ? 0 : -1));
        if (r8 < 0) goto L_0x0065;
    L_0x0064:
        return r16;
    L_0x0065:
        r8 = (r24 > r12 ? 1 : (r24 == r12 ? 0 : -1));
        if (r8 < 0) goto L_0x006a;
    L_0x0069:
        return r16;
    L_0x006a:
        if (r28 == 0) goto L_0x0082;
    L_0x006c:
        r8 = java.lang.Math.tan(r24);
        r0 = r18;
        r2 = r20;
        r4 = r22;
        r6 = r26;
        r0 = tanLatGC(r0, r2, r4, r6);
        r2 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1));
        if (r2 < 0) goto L_0x0097;
    L_0x0080:
        r11 = 1;
        goto L_0x0097;
    L_0x0082:
        r8 = com.google.maps.android.MathUtil.mercator(r24);
        r0 = r18;
        r2 = r20;
        r4 = r22;
        r6 = r26;
        r0 = mercatorLatRhumb(r0, r2, r4, r6);
        r2 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1));
        if (r2 < 0) goto L_0x0097;
    L_0x0096:
        goto L_0x0080;
    L_0x0097:
        return r11;
    L_0x0098:
        return r11;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.maps.android.PolyUtil.intersects(double, double, double, double, double, boolean):boolean");
    }

    public static boolean containsLocation(LatLng latLng, List<LatLng> list, boolean z) {
        return containsLocation(latLng.latitude, latLng.longitude, list, z);
    }

    public static boolean containsLocation(double d, double d2, List<LatLng> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return false;
        }
        double toRadians = Math.toRadians(d);
        double toRadians2 = Math.toRadians(d2);
        boolean z2 = true;
        LatLng latLng = (LatLng) list.get(size - 1);
        double toRadians3 = Math.toRadians(latLng.latitude);
        double toRadians4 = Math.toRadians(latLng.longitude);
        double d3 = toRadians3;
        int i = 0;
        for (LatLng latLng2 : list) {
            double wrap = MathUtil.wrap(toRadians2 - toRadians4, -3.141592653589793d, 3.141592653589793d);
            if (toRadians == d3 && wrap == 0.0d) {
                return true;
            }
            double toRadians5 = Math.toRadians(latLng2.latitude);
            double toRadians6 = Math.toRadians(latLng2.longitude);
            if (intersects(d3, toRadians5, MathUtil.wrap(toRadians6 - toRadians4, -3.141592653589793d, 3.141592653589793d), toRadians, wrap, z)) {
                i++;
            }
            d3 = toRadians5;
            toRadians4 = toRadians6;
        }
        if ((i & 1) == 0) {
            z2 = false;
        }
        return z2;
    }

    public static boolean isLocationOnEdge(LatLng latLng, List<LatLng> list, boolean z, double d) {
        return isLocationOnEdgeOrPath(latLng, list, true, z, d);
    }

    public static boolean isLocationOnEdge(LatLng latLng, List<LatLng> list, boolean z) {
        return isLocationOnEdge(latLng, list, z, DEFAULT_TOLERANCE);
    }

    public static boolean isLocationOnPath(LatLng latLng, List<LatLng> list, boolean z, double d) {
        return isLocationOnEdgeOrPath(latLng, list, false, z, d);
    }

    public static boolean isLocationOnPath(LatLng latLng, List<LatLng> list, boolean z) {
        return isLocationOnPath(latLng, list, z, DEFAULT_TOLERANCE);
    }

    private static boolean isLocationOnEdgeOrPath(LatLng latLng, List<LatLng> list, boolean z, boolean z2, double d) {
        LatLng latLng2 = latLng;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return false;
        }
        int i2;
        List list2;
        double d2 = d / 6371009.0d;
        double hav = MathUtil.hav(d2);
        double toRadians = Math.toRadians(latLng2.latitude);
        double toRadians2 = Math.toRadians(latLng2.longitude);
        if (z) {
            i2 = size - 1;
            list2 = list;
        } else {
            list2 = list;
            i2 = 0;
        }
        LatLng latLng3 = (LatLng) list2.get(i2);
        double toRadians3 = Math.toRadians(latLng3.latitude);
        double toRadians4 = Math.toRadians(latLng3.longitude);
        double d3;
        Iterator it;
        double toRadians5;
        double d4;
        if (z2) {
            double d5 = toRadians3;
            d3 = toRadians4;
            for (LatLng latLng4 : list) {
                toRadians5 = Math.toRadians(latLng4.latitude);
                d2 = Math.toRadians(latLng4.longitude);
                d4 = toRadians;
                if (isOnSegmentGC(d5, d3, toRadians5, d2, toRadians, toRadians2, hav)) {
                    return true;
                }
                d3 = d2;
                d5 = toRadians5;
                toRadians = d4;
            }
        } else {
            d4 = toRadians;
            double d6 = toRadians - d2;
            d2 += toRadians;
            double mercator = MathUtil.mercator(toRadians3);
            double mercator2 = MathUtil.mercator(toRadians);
            double[] dArr = new double[3];
            it = list.iterator();
            toRadians5 = mercator;
            while (it.hasNext()) {
                LatLng latLng5 = (LatLng) it.next();
                double toRadians6 = Math.toRadians(latLng5.latitude);
                d4 = MathUtil.mercator(toRadians6);
                Iterator it2 = it;
                double toRadians7 = Math.toRadians(latLng5.longitude);
                if (Math.max(toRadians3, toRadians6) >= d6 && Math.min(toRadians3, toRadians6) <= r3) {
                    double wrap = MathUtil.wrap(toRadians7 - toRadians4, -3.141592653589793d, 3.141592653589793d);
                    toRadians3 = MathUtil.wrap(toRadians2 - toRadians4, -3.141592653589793d, 3.141592653589793d);
                    dArr[i] = toRadians3;
                    dArr[1] = toRadians3 + 6.283185307179586d;
                    dArr[2] = toRadians3 - 6.283185307179586d;
                    int length = dArr.length;
                    int i3 = 0;
                    while (i3 < length) {
                        d3 = dArr[i3];
                        double d7 = d4 - toRadians5;
                        double d8 = (wrap * wrap) + (d7 * d7);
                        double d9 = 0.0d;
                        if (d8 > 0.0d) {
                            d9 = MathUtil.clamp(((d3 * wrap) + ((mercator2 - toRadians5) * d7)) / d8, 0.0d, 1.0d);
                        }
                        double d10 = d2;
                        d7 = d3 - (d9 * wrap);
                        int i4 = i3;
                        double d11 = toRadians6;
                        if (MathUtil.havDistance(toRadians, MathUtil.inverseMercator(toRadians5 + (d9 * d7)), d7) < hav) {
                            return true;
                        }
                        i3 = i4 + 1;
                        toRadians6 = d11;
                        d2 = d10;
                    }
                    continue;
                }
                toRadians4 = toRadians7;
                toRadians3 = toRadians6;
                toRadians5 = d4;
                it = it2;
                d2 = d2;
                i = 0;
            }
        }
        return false;
    }

    private static double sinDeltaBearing(double d, double d2, double d3, double d4, double d5, double d6) {
        double sin = Math.sin(d);
        double cos = Math.cos(d3);
        double cos2 = Math.cos(d5);
        double d7 = d6 - d2;
        double d8 = d4 - d2;
        double sin2 = Math.sin(d7) * cos2;
        double sin3 = Math.sin(d8) * cos;
        sin *= 2.0d;
        double sin4 = Math.sin(d5 - d) + ((cos2 * sin) * MathUtil.hav(d7));
        double sin5 = Math.sin(d3 - d) + ((sin * cos) * MathUtil.hav(d8));
        d8 = ((sin2 * sin2) + (sin4 * sin4)) * ((sin3 * sin3) + (sin5 * sin5));
        if (d8 <= 0.0d) {
            return 1.0d;
        }
        return ((sin2 * sin5) - (sin4 * sin3)) / Math.sqrt(d8);
    }

    private static boolean isOnSegmentGC(double d, double d2, double d3, double d4, double d5, double d6, double d7) {
        double havDistance = MathUtil.havDistance(d, d5, d2 - d6);
        if (havDistance <= d7) {
            return true;
        }
        double havDistance2 = MathUtil.havDistance(d3, d5, d4 - d6);
        if (havDistance2 <= d7) {
            return true;
        }
        double havFromSin = MathUtil.havFromSin(MathUtil.sinFromHav(havDistance) * sinDeltaBearing(d, d2, d3, d4, d5, d6));
        boolean z = false;
        if (havFromSin > d7) {
            return false;
        }
        double havDistance3 = MathUtil.havDistance(d, d3, d2 - d4);
        double d8 = ((1.0d - (havDistance3 * 2.0d)) * havFromSin) + havDistance3;
        if (havDistance <= d8) {
            if (havDistance2 <= d8) {
                if (havDistance3 < 0.74d) {
                    return true;
                }
                double d9 = 1.0d - (2.0d * havFromSin);
                if (MathUtil.sinSumFromHav((havDistance - havFromSin) / d9, (havDistance2 - havFromSin) / d9) > 0.0d) {
                    z = true;
                }
                return z;
            }
        }
        return false;
    }

    public static List<LatLng> simplify(List<LatLng> list, double d) {
        List<LatLng> list2 = list;
        int size = list.size();
        if (size < 1) {
            throw new IllegalArgumentException("Polyline must have at least 1 point");
        }
        double d2 = 0.0d;
        if (d <= 0.0d) {
            throw new IllegalArgumentException("Tolerance must be greater than zero");
        }
        boolean isClosedPolygon = isClosedPolygon(list);
        Object obj = null;
        if (isClosedPolygon) {
            obj = (LatLng) list2.get(list.size() - 1);
            list2.remove(list.size() - 1);
            list2.add(new LatLng(obj.latitude + 1.0E-11d, obj.longitude + 1.0E-11d));
        }
        Stack stack = new Stack();
        double[] dArr = new double[size];
        int i = 0;
        dArr[0] = 1.0d;
        dArr[size - 1] = 1.0d;
        if (size > 2) {
            int i2;
            stack.push(new int[]{null, i2});
            size = 0;
            while (stack.size() > 0) {
                int[] iArr = (int[]) stack.pop();
                i2 = iArr[i] + 1;
                double d3 = d2;
                while (i2 < iArr[1]) {
                    d2 = distanceToLine((LatLng) list2.get(i2), (LatLng) list2.get(iArr[i]), (LatLng) list2.get(iArr[1]));
                    if (d2 > d3) {
                        d3 = d2;
                        size = i2;
                    }
                    i2++;
                    i = 0;
                }
                if (d3 > d) {
                    dArr[size] = d3;
                    stack.push(new int[]{iArr[0], size});
                    stack.push(new int[]{size, iArr[1]});
                }
                d2 = 0.0d;
                i = 0;
            }
        }
        int i3 = 0;
        if (isClosedPolygon) {
            list2.remove(list.size() - 1);
            list2.add(obj);
        }
        List arrayList = new ArrayList();
        for (LatLng latLng : list) {
            if (dArr[i3] != 0.0d) {
                arrayList.add(latLng);
            }
            i3++;
        }
        return arrayList;
    }

    public static boolean isClosedPolygon(List<LatLng> list) {
        if (((LatLng) list.get(0)).equals((LatLng) list.get(list.size() - 1)) != null) {
            return true;
        }
        return false;
    }

    public static double distanceToLine(LatLng latLng, LatLng latLng2, LatLng latLng3) {
        if (latLng2.equals(latLng3)) {
            return SphericalUtil.computeDistanceBetween(latLng3, latLng);
        }
        double toRadians = Math.toRadians(latLng.latitude);
        double toRadians2 = Math.toRadians(latLng.longitude);
        double toRadians3 = Math.toRadians(latLng2.latitude);
        double toRadians4 = Math.toRadians(latLng2.longitude);
        double toRadians5 = Math.toRadians(latLng3.latitude) - toRadians3;
        double toRadians6 = Math.toRadians(latLng3.longitude) - toRadians4;
        toRadians = (((toRadians - toRadians3) * toRadians5) + ((toRadians2 - toRadians4) * toRadians6)) / ((toRadians5 * toRadians5) + (toRadians6 * toRadians6));
        if (toRadians <= 0.0d) {
            return SphericalUtil.computeDistanceBetween(latLng, latLng2);
        }
        if (toRadians >= 1.0d) {
            return SphericalUtil.computeDistanceBetween(latLng, latLng3);
        }
        return SphericalUtil.computeDistanceBetween(new LatLng(latLng.latitude - latLng2.latitude, latLng.longitude - latLng2.longitude), new LatLng((latLng3.latitude - latLng2.latitude) * toRadians, toRadians * (latLng3.longitude - latLng2.longitude)));
    }

    public static List<LatLng> decode(String str) {
        int length = str.length();
        List<LatLng> arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            int i4 = 1;
            int i5 = 0;
            while (true) {
                int i6 = i + 1;
                i = (str.charAt(i) - 63) - 1;
                i4 += i << i5;
                i5 += 5;
                if (i < 31) {
                    break;
                }
                i = i6;
            }
            i = ((i4 & 1) != 0 ? (i4 >> 1) ^ -1 : i4 >> 1) + i2;
            i2 = 1;
            i4 = 0;
            while (true) {
                i5 = i6 + 1;
                i6 = (str.charAt(i6) - 63) - 1;
                i2 += i6 << i4;
                i4 += 5;
                if (i6 < 31) {
                    break;
                }
                i6 = i5;
            }
            i3 += (i2 & 1) != 0 ? (i2 >> 1) ^ -1 : i2 >> 1;
            arrayList.add(new LatLng(((double) i) * 1.0E-5d, ((double) i3) * 1.0E-5d));
            i2 = i;
            i = i5;
        }
        return arrayList;
    }

    public static String encode(List<LatLng> list) {
        StringBuffer stringBuffer = new StringBuffer();
        long j = 0;
        long j2 = 0;
        for (LatLng latLng : list) {
            long round = Math.round(latLng.latitude * 100000.0d);
            long round2 = Math.round(latLng.longitude * 100000.0d);
            long j3 = round - j;
            j = round2 - j2;
            encode(j3, stringBuffer);
            encode(j, stringBuffer);
            j = round;
            j2 = round2;
        }
        return stringBuffer.toString();
    }

    private static void encode(long j, StringBuffer stringBuffer) {
        long j2 = j < 0 ? (j << 1) ^ -1 : j << 1;
        while (j2 >= 32) {
            stringBuffer.append(Character.toChars((int) (((j2 & 31) | 32) + 63)));
            j2 >>= 5;
        }
        stringBuffer.append(Character.toChars((int) (j2 + 63)));
    }
}
