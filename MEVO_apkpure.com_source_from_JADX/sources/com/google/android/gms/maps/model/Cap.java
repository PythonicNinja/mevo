package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;

@Class(creator = "CapCreator")
@Reserved({1})
public class Cap extends AbstractSafeParcelable {
    public static final Creator<Cap> CREATOR = new zzb();
    private static final String TAG = "Cap";
    @Nullable
    @Field(getter = "getWrappedBitmapDescriptorImplBinder", id = 3, type = "android.os.IBinder")
    private final BitmapDescriptor bitmapDescriptor;
    @Field(getter = "getType", id = 2)
    private final int type;
    @Nullable
    @Field(getter = "getBitmapRefWidth", id = 4)
    private final Float zzcm;

    protected Cap(int i) {
        this(i, null, null);
    }

    @Constructor
    Cap(@Param(id = 2) int i, @Nullable @Param(id = 3) IBinder iBinder, @Nullable @Param(id = 4) Float f) {
        this(i, iBinder == null ? null : new BitmapDescriptor(Stub.asInterface(iBinder)), f);
    }

    private Cap(int i, @Nullable BitmapDescriptor bitmapDescriptor, @Nullable Float f) {
        boolean z;
        Object obj = (f == null || f.floatValue() <= 0.0f) ? null : 1;
        if (i == 3) {
            if (bitmapDescriptor == null || obj == null) {
                z = false;
                Preconditions.checkArgument(z, String.format("Invalid Cap: type=%s bitmapDescriptor=%s bitmapRefWidth=%s", new Object[]{Integer.valueOf(i), bitmapDescriptor, f}));
                this.type = i;
                this.bitmapDescriptor = bitmapDescriptor;
                this.zzcm = f;
            }
        }
        z = true;
        Preconditions.checkArgument(z, String.format("Invalid Cap: type=%s bitmapDescriptor=%s bitmapRefWidth=%s", new Object[]{Integer.valueOf(i), bitmapDescriptor, f}));
        this.type = i;
        this.bitmapDescriptor = bitmapDescriptor;
        this.zzcm = f;
    }

    protected Cap(@NonNull BitmapDescriptor bitmapDescriptor, float f) {
        this(3, bitmapDescriptor, Float.valueOf(f));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Cap)) {
            return false;
        }
        Cap cap = (Cap) obj;
        return this.type == cap.type && Objects.equal(this.bitmapDescriptor, cap.bitmapDescriptor) && Objects.equal(this.zzcm, cap.zzcm);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.type), this.bitmapDescriptor, this.zzcm);
    }

    public String toString() {
        int i = this.type;
        StringBuilder stringBuilder = new StringBuilder(23);
        stringBuilder.append("[Cap: type=");
        stringBuilder.append(i);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        i = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.type);
        SafeParcelWriter.writeIBinder(parcel, 3, this.bitmapDescriptor == null ? null : this.bitmapDescriptor.zza().asBinder(), false);
        SafeParcelWriter.writeFloatObject(parcel, 4, this.zzcm, false);
        SafeParcelWriter.finishObjectHeader(parcel, i);
    }

    final Cap zzg() {
        switch (this.type) {
            case 0:
                return new ButtCap();
            case 1:
                return new SquareCap();
            case 2:
                return new RoundCap();
            case 3:
                return new CustomCap(this.bitmapDescriptor, this.zzcm.floatValue());
            default:
                String str = TAG;
                int i = this.type;
                StringBuilder stringBuilder = new StringBuilder(29);
                stringBuilder.append("Unknown Cap type: ");
                stringBuilder.append(i);
                Log.w(str, stringBuilder.toString());
                return this;
        }
    }
}
