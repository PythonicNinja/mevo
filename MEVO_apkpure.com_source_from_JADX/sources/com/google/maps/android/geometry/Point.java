package com.google.maps.android.geometry;

public class Point {
    /* renamed from: x */
    public final double f14x;
    /* renamed from: y */
    public final double f15y;

    public Point(double d, double d2) {
        this.f14x = d;
        this.f15y = d2;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Point{x=");
        stringBuilder.append(this.f14x);
        stringBuilder.append(", y=");
        stringBuilder.append(this.f15y);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
