package com.google.android.gms.maps.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.maps.GoogleMapOptions;

@Class(creator = "LatLngBoundsCreator")
@Reserved({1})
public final class LatLngBounds extends AbstractSafeParcelable implements ReflectedParcelable {
    @KeepForSdk
    public static final Creator<LatLngBounds> CREATOR = new zze();
    @Field(id = 3)
    public final LatLng northeast;
    @Field(id = 2)
    public final LatLng southwest;

    public static final class Builder {
        private double zzdg = Double.POSITIVE_INFINITY;
        private double zzdh = Double.NEGATIVE_INFINITY;
        private double zzdi = Double.NaN;
        private double zzdj = Double.NaN;

        public final LatLngBounds build() {
            Preconditions.checkState(Double.isNaN(this.zzdi) ^ 1, "no included points");
            return new LatLngBounds(new LatLng(this.zzdg, this.zzdi), new LatLng(this.zzdh, this.zzdj));
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.google.android.gms.maps.model.LatLngBounds.Builder include(com.google.android.gms.maps.model.LatLng r7) {
            /*
            r6 = this;
            r0 = r6.zzdg;
            r2 = r7.latitude;
            r0 = java.lang.Math.min(r0, r2);
            r6.zzdg = r0;
            r0 = r6.zzdh;
            r2 = r7.latitude;
            r0 = java.lang.Math.max(r0, r2);
            r6.zzdh = r0;
            r0 = r7.longitude;
            r2 = r6.zzdi;
            r7 = java.lang.Double.isNaN(r2);
            if (r7 == 0) goto L_0x0021;
        L_0x001e:
            r6.zzdi = r0;
            goto L_0x005a;
        L_0x0021:
            r2 = r6.zzdi;
            r4 = r6.zzdj;
            r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            r2 = 0;
            r3 = 1;
            if (r7 > 0) goto L_0x0038;
        L_0x002b:
            r4 = r6.zzdi;
            r7 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
            if (r7 > 0) goto L_0x0045;
        L_0x0031:
            r4 = r6.zzdj;
            r7 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
            if (r7 > 0) goto L_0x0045;
        L_0x0037:
            goto L_0x0044;
        L_0x0038:
            r4 = r6.zzdi;
            r7 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
            if (r7 <= 0) goto L_0x0044;
        L_0x003e:
            r4 = r6.zzdj;
            r7 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
            if (r7 > 0) goto L_0x0045;
        L_0x0044:
            r2 = 1;
        L_0x0045:
            if (r2 != 0) goto L_0x005c;
        L_0x0047:
            r2 = r6.zzdi;
            r2 = com.google.android.gms.maps.model.LatLngBounds.zza(r2, r0);
            r4 = r6.zzdj;
            r4 = com.google.android.gms.maps.model.LatLngBounds.zzb(r4, r0);
            r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r7 >= 0) goto L_0x005a;
        L_0x0057:
            r6.zzdi = r0;
            return r6;
        L_0x005a:
            r6.zzdj = r0;
        L_0x005c:
            return r6;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.LatLngBounds.Builder.include(com.google.android.gms.maps.model.LatLng):com.google.android.gms.maps.model.LatLngBounds$Builder");
        }
    }

    @Constructor
    public LatLngBounds(@Param(id = 2) LatLng latLng, @Param(id = 3) LatLng latLng2) {
        Preconditions.checkNotNull(latLng, "null southwest");
        Preconditions.checkNotNull(latLng2, "null northeast");
        Preconditions.checkArgument(latLng2.latitude >= latLng.latitude, "southern latitude exceeds northern latitude (%s > %s)", Double.valueOf(latLng.latitude), Double.valueOf(latLng2.latitude));
        this.southwest = latLng;
        this.northeast = latLng2;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static LatLngBounds createFromAttributes(Context context, AttributeSet attributeSet) {
        return GoogleMapOptions.zza(context, attributeSet);
    }

    private static double zza(double d, double d2) {
        return ((d - d2) + 360.0d) % 360.0d;
    }

    private final boolean zza(double d) {
        return this.southwest.longitude <= this.northeast.longitude ? this.southwest.longitude <= d && d <= this.northeast.longitude : this.southwest.longitude <= d || d <= this.northeast.longitude;
    }

    private static double zzb(double d, double d2) {
        return ((d2 - d) + 360.0d) % 360.0d;
    }

    public final boolean contains(LatLng latLng) {
        double d = latLng.latitude;
        Object obj = (this.southwest.latitude > d || d > this.northeast.latitude) ? null : 1;
        return obj != null && zza(latLng.longitude);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds latLngBounds = (LatLngBounds) obj;
        return this.southwest.equals(latLngBounds.southwest) && this.northeast.equals(latLngBounds.northeast);
    }

    public final LatLng getCenter() {
        double d = (this.southwest.latitude + this.northeast.latitude) / 2.0d;
        double d2 = this.northeast.longitude;
        double d3 = this.southwest.longitude;
        if (d3 > d2) {
            d2 += 360.0d;
        }
        return new LatLng(d, (d2 + d3) / 2.0d);
    }

    public final int hashCode() {
        return Objects.hashCode(this.southwest, this.northeast);
    }

    public final LatLngBounds including(LatLng latLng) {
        double min = Math.min(this.southwest.latitude, latLng.latitude);
        double max = Math.max(this.northeast.latitude, latLng.latitude);
        double d = this.northeast.longitude;
        double d2 = this.southwest.longitude;
        double d3 = latLng.longitude;
        if (!zza(d3)) {
            if (zza(d2, d3) < zzb(d, d3)) {
                d2 = d3;
            } else {
                d = d3;
            }
        }
        return new LatLngBounds(new LatLng(min, d2), new LatLng(max, d));
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("southwest", this.southwest).add("northeast", this.northeast).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.southwest, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.northeast, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
