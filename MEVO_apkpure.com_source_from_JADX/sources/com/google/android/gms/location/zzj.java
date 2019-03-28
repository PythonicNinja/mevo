package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@Class(creator = "DeviceOrientationRequestCreator")
public final class zzj extends AbstractSafeParcelable {
    public static final Creator<zzj> CREATOR = new zzk();
    @Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_SHOULD_USE_MAG", id = 1)
    private boolean zzt;
    @Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_MINIMUM_SAMPLING_PERIOD_MS", id = 2)
    private long zzu;
    @Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_SMALLEST_ANGLE_CHANGE_RADIANS", id = 3)
    private float zzv;
    @Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_EXPIRE_AT_MS", id = 4)
    private long zzw;
    @Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_NUM_UPDATES", id = 5)
    private int zzx;

    public zzj() {
        this(true, 50, 0.0f, Long.MAX_VALUE, Integer.MAX_VALUE);
    }

    @Constructor
    zzj(@Param(id = 1) boolean z, @Param(id = 2) long j, @Param(id = 3) float f, @Param(id = 4) long j2, @Param(id = 5) int i) {
        this.zzt = z;
        this.zzu = j;
        this.zzv = f;
        this.zzw = j2;
        this.zzx = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzj)) {
            return false;
        }
        zzj zzj = (zzj) obj;
        return this.zzt == zzj.zzt && this.zzu == zzj.zzu && Float.compare(this.zzv, zzj.zzv) == 0 && this.zzw == zzj.zzw && this.zzx == zzj.zzx;
    }

    public final int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.zzt), Long.valueOf(this.zzu), Float.valueOf(this.zzv), Long.valueOf(this.zzw), Integer.valueOf(this.zzx));
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DeviceOrientationRequest[mShouldUseMag=");
        stringBuilder.append(this.zzt);
        stringBuilder.append(" mMinimumSamplingPeriodMs=");
        stringBuilder.append(this.zzu);
        stringBuilder.append(" mSmallestAngleChangeRadians=");
        stringBuilder.append(this.zzv);
        if (this.zzw != Long.MAX_VALUE) {
            long elapsedRealtime = this.zzw - SystemClock.elapsedRealtime();
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
        SafeParcelWriter.writeBoolean(parcel, 1, this.zzt);
        SafeParcelWriter.writeLong(parcel, 2, this.zzu);
        SafeParcelWriter.writeFloat(parcel, 3, this.zzv);
        SafeParcelWriter.writeLong(parcel, 4, this.zzw);
        SafeParcelWriter.writeInt(parcel, 5, this.zzx);
        SafeParcelWriter.finishObjectHeader(parcel, i);
    }
}
