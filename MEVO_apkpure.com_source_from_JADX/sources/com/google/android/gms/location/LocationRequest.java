package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.VisibleForTesting;

@Class(creator = "LocationRequestCreator")
@Reserved({1000})
public final class LocationRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<LocationRequest> CREATOR = new zzab();
    public static final int PRIORITY_BALANCED_POWER_ACCURACY = 102;
    public static final int PRIORITY_HIGH_ACCURACY = 100;
    public static final int PRIORITY_LOW_POWER = 104;
    public static final int PRIORITY_NO_POWER = 105;
    @Field(defaultValueUnchecked = "LocationRequest.DEFAULT_PRIORITY", id = 1)
    private int priority;
    @Field(defaultValueUnchecked = "LocationRequest.DEFAULT_EXPIRE_AT", id = 5)
    private long zzaf;
    @Field(defaultValueUnchecked = "LocationRequest.DEFAULT_INTERVAL", id = 2)
    private long zzaw;
    @Field(defaultValueUnchecked = "LocationRequest.DEFAULT_FASTEST_INTERVAL", id = 3)
    private long zzax;
    @Field(defaultValueUnchecked = "LocationRequest.DEFAULT_EXPLICIT_FASTEST_INTERVAL", id = 4)
    private boolean zzay;
    @Field(defaultValueUnchecked = "LocationRequest.DEFAULT_SMALLEST_DISPLACEMENT", id = 7)
    private float zzaz;
    @Field(defaultValueUnchecked = "LocationRequest.DEFAULT_MAX_WAIT_TIME", id = 8)
    private long zzba;
    @Field(defaultValueUnchecked = "LocationRequest.DEFAULT_NUM_UPDATES", id = 6)
    private int zzx;

    public LocationRequest() {
        this.priority = 102;
        this.zzaw = 3600000;
        this.zzax = 600000;
        this.zzay = false;
        this.zzaf = Long.MAX_VALUE;
        this.zzx = Integer.MAX_VALUE;
        this.zzaz = 0.0f;
        this.zzba = 0;
    }

    @Constructor
    LocationRequest(@Param(id = 1) int i, @Param(id = 2) long j, @Param(id = 3) long j2, @Param(id = 4) boolean z, @Param(id = 5) long j3, @Param(id = 6) int i2, @Param(id = 7) float f, @Param(id = 8) long j4) {
        this.priority = i;
        this.zzaw = j;
        this.zzax = j2;
        this.zzay = z;
        this.zzaf = j3;
        this.zzx = i2;
        this.zzaz = f;
        this.zzba = j4;
    }

    @VisibleForTesting
    public static LocationRequest create() {
        return new LocationRequest();
    }

    private static void zza(long j) {
        if (j < 0) {
            StringBuilder stringBuilder = new StringBuilder(38);
            stringBuilder.append("invalid interval: ");
            stringBuilder.append(j);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocationRequest)) {
            return false;
        }
        LocationRequest locationRequest = (LocationRequest) obj;
        return this.priority == locationRequest.priority && this.zzaw == locationRequest.zzaw && this.zzax == locationRequest.zzax && this.zzay == locationRequest.zzay && this.zzaf == locationRequest.zzaf && this.zzx == locationRequest.zzx && this.zzaz == locationRequest.zzaz && getMaxWaitTime() == locationRequest.getMaxWaitTime();
    }

    public final long getExpirationTime() {
        return this.zzaf;
    }

    public final long getFastestInterval() {
        return this.zzax;
    }

    public final long getInterval() {
        return this.zzaw;
    }

    public final long getMaxWaitTime() {
        long j = this.zzba;
        return j < this.zzaw ? this.zzaw : j;
    }

    public final int getNumUpdates() {
        return this.zzx;
    }

    public final int getPriority() {
        return this.priority;
    }

    public final float getSmallestDisplacement() {
        return this.zzaz;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.priority), Long.valueOf(this.zzaw), Float.valueOf(this.zzaz), Long.valueOf(this.zzba));
    }

    public final boolean isFastestIntervalExplicitlySet() {
        return this.zzay;
    }

    public final LocationRequest setExpirationDuration(long j) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j2 = Long.MAX_VALUE;
        if (j <= Long.MAX_VALUE - elapsedRealtime) {
            j2 = j + elapsedRealtime;
        }
        this.zzaf = j2;
        if (this.zzaf < 0) {
            this.zzaf = 0;
        }
        return this;
    }

    @VisibleForTesting
    public final LocationRequest setExpirationTime(long j) {
        this.zzaf = j;
        if (this.zzaf < 0) {
            this.zzaf = 0;
        }
        return this;
    }

    public final LocationRequest setFastestInterval(long j) {
        zza(j);
        this.zzay = true;
        this.zzax = j;
        return this;
    }

    public final LocationRequest setInterval(long j) {
        zza(j);
        this.zzaw = j;
        if (!this.zzay) {
            this.zzax = (long) (((double) this.zzaw) / 6.0d);
        }
        return this;
    }

    @VisibleForTesting
    public final LocationRequest setMaxWaitTime(long j) {
        zza(j);
        this.zzba = j;
        return this;
    }

    @VisibleForTesting
    public final LocationRequest setNumUpdates(int i) {
        if (i <= 0) {
            StringBuilder stringBuilder = new StringBuilder(31);
            stringBuilder.append("invalid numUpdates: ");
            stringBuilder.append(i);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        this.zzx = i;
        return this;
    }

    @VisibleForTesting
    public final LocationRequest setPriority(int i) {
        switch (i) {
            case 100:
            case 102:
            case 104:
            case 105:
                this.priority = i;
                return this;
            default:
                StringBuilder stringBuilder = new StringBuilder(28);
                stringBuilder.append("invalid quality: ");
                stringBuilder.append(i);
                throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    @VisibleForTesting
    public final LocationRequest setSmallestDisplacement(float f) {
        if (f < 0.0f) {
            StringBuilder stringBuilder = new StringBuilder(37);
            stringBuilder.append("invalid displacement: ");
            stringBuilder.append(f);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        this.zzaz = f;
        return this;
    }

    public final String toString() {
        String str;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Request[");
        switch (this.priority) {
            case 100:
                str = "PRIORITY_HIGH_ACCURACY";
                break;
            case 102:
                str = "PRIORITY_BALANCED_POWER_ACCURACY";
                break;
            case 104:
                str = "PRIORITY_LOW_POWER";
                break;
            case 105:
                str = "PRIORITY_NO_POWER";
                break;
            default:
                str = "???";
                break;
        }
        stringBuilder.append(str);
        if (this.priority != 105) {
            stringBuilder.append(" requested=");
            stringBuilder.append(this.zzaw);
            stringBuilder.append("ms");
        }
        stringBuilder.append(" fastest=");
        stringBuilder.append(this.zzax);
        stringBuilder.append("ms");
        if (this.zzba > this.zzaw) {
            stringBuilder.append(" maxWait=");
            stringBuilder.append(this.zzba);
            stringBuilder.append("ms");
        }
        if (this.zzaz > 0.0f) {
            stringBuilder.append(" smallestDisplacement=");
            stringBuilder.append(this.zzaz);
            stringBuilder.append("m");
        }
        if (this.zzaf != Long.MAX_VALUE) {
            long elapsedRealtime = this.zzaf - SystemClock.elapsedRealtime();
            stringBuilder.append(" expireIn=");
            stringBuilder.append(elapsedRealtime);
            stringBuilder.append("ms");
        }
        if (this.zzx != Integer.MAX_VALUE) {
            stringBuilder.append(" num=");
            stringBuilder.append(this.zzx);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        i = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.priority);
        SafeParcelWriter.writeLong(parcel, 2, this.zzaw);
        SafeParcelWriter.writeLong(parcel, 3, this.zzax);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzay);
        SafeParcelWriter.writeLong(parcel, 5, this.zzaf);
        SafeParcelWriter.writeInt(parcel, 6, this.zzx);
        SafeParcelWriter.writeFloat(parcel, 7, this.zzaz);
        SafeParcelWriter.writeLong(parcel, 8, this.zzba);
        SafeParcelWriter.finishObjectHeader(parcel, i);
    }
}
