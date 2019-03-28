package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
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
import java.util.ArrayList;
import java.util.List;

@Class(creator = "PatternItemCreator")
@Reserved({1})
public class PatternItem extends AbstractSafeParcelable {
    public static final Creator<PatternItem> CREATOR = new zzi();
    private static final String TAG = "PatternItem";
    @Field(getter = "getType", id = 2)
    private final int type;
    @Nullable
    @Field(getter = "getLength", id = 3)
    private final Float zzdu;

    @Constructor
    public PatternItem(@Param(id = 2) int i, @Nullable @Param(id = 3) Float f) {
        boolean z = true;
        if (i != 1) {
            if (f == null || f.floatValue() < 0.0f) {
                z = false;
            }
        }
        String valueOf = String.valueOf(f);
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(valueOf).length() + 45);
        stringBuilder.append("Invalid PatternItem: type=");
        stringBuilder.append(i);
        stringBuilder.append(" length=");
        stringBuilder.append(valueOf);
        Preconditions.checkArgument(z, stringBuilder.toString());
        this.type = i;
        this.zzdu = f;
    }

    @Nullable
    static List<PatternItem> zza(@Nullable List<PatternItem> list) {
        if (list == null) {
            return null;
        }
        List<PatternItem> arrayList = new ArrayList(list.size());
        for (Object obj : list) {
            Object obj2;
            if (obj2 == null) {
                obj2 = null;
            } else {
                Gap dash;
                switch (obj2.type) {
                    case 0:
                        dash = new Dash(obj2.zzdu.floatValue());
                        break;
                    case 1:
                        obj2 = new Dot();
                        continue;
                    case 2:
                        dash = new Gap(obj2.zzdu.floatValue());
                        break;
                    default:
                        String str = TAG;
                        int i = obj2.type;
                        StringBuilder stringBuilder = new StringBuilder(37);
                        stringBuilder.append("Unknown PatternItem type: ");
                        stringBuilder.append(i);
                        Log.w(str, stringBuilder.toString());
                        continue;
                }
                obj2 = dash;
            }
            arrayList.add(obj2);
        }
        return arrayList;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PatternItem)) {
            return false;
        }
        PatternItem patternItem = (PatternItem) obj;
        return this.type == patternItem.type && Objects.equal(this.zzdu, patternItem.zzdu);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.type), this.zzdu);
    }

    public String toString() {
        int i = this.type;
        String valueOf = String.valueOf(this.zzdu);
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(valueOf).length() + 39);
        stringBuilder.append("[PatternItem: type=");
        stringBuilder.append(i);
        stringBuilder.append(" length=");
        stringBuilder.append(valueOf);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        i = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.type);
        SafeParcelWriter.writeFloatObject(parcel, 3, this.zzdu, false);
        SafeParcelWriter.finishObjectHeader(parcel, i);
    }
}
