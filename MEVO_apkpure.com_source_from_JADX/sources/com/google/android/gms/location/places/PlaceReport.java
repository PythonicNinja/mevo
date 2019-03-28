package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.os.EnvironmentCompat;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Objects.ToStringHelper;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.firebase.analytics.FirebaseAnalytics;

@Class(creator = "PlaceReportCreator")
public class PlaceReport extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<PlaceReport> CREATOR = new zza();
    @Field(getter = "getTag", id = 3)
    private final String tag;
    @VersionField(id = 1)
    private final int versionCode;
    @Field(getter = "getPlaceId", id = 2)
    private final String zza;
    @Field(getter = "getSource", id = 4)
    private final String zzb;

    @Constructor
    PlaceReport(@Param(id = 1) int i, @Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) String str3) {
        this.versionCode = i;
        this.zza = str;
        this.tag = str2;
        this.zzb = str3;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @com.google.android.gms.common.util.VisibleForTesting
    public static com.google.android.gms.location.places.PlaceReport create(java.lang.String r4, java.lang.String r5) {
        /*
        r0 = "unknown";
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r4);
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r5);
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r0);
        r1 = r0.hashCode();
        r2 = 0;
        r3 = 1;
        switch(r1) {
            case -1436706272: goto L_0x0047;
            case -1194968642: goto L_0x003d;
            case -284840886: goto L_0x0033;
            case -262743844: goto L_0x0029;
            case 1164924125: goto L_0x001f;
            case 1287171955: goto L_0x0015;
            default: goto L_0x0014;
        };
    L_0x0014:
        goto L_0x0051;
    L_0x0015:
        r1 = "inferredRadioSignals";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x0051;
    L_0x001d:
        r1 = 3;
        goto L_0x0052;
    L_0x001f:
        r1 = "inferredSnappedToRoad";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x0051;
    L_0x0027:
        r1 = 5;
        goto L_0x0052;
    L_0x0029:
        r1 = "inferredReverseGeocoding";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x0051;
    L_0x0031:
        r1 = 4;
        goto L_0x0052;
    L_0x0033:
        r1 = "unknown";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x0051;
    L_0x003b:
        r1 = 0;
        goto L_0x0052;
    L_0x003d:
        r1 = "userReported";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x0051;
    L_0x0045:
        r1 = 1;
        goto L_0x0052;
    L_0x0047:
        r1 = "inferredGeofencing";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x0051;
    L_0x004f:
        r1 = 2;
        goto L_0x0052;
    L_0x0051:
        r1 = -1;
    L_0x0052:
        switch(r1) {
            case 0: goto L_0x0056;
            case 1: goto L_0x0056;
            case 2: goto L_0x0056;
            case 3: goto L_0x0056;
            case 4: goto L_0x0056;
            case 5: goto L_0x0056;
            default: goto L_0x0055;
        };
    L_0x0055:
        goto L_0x0057;
    L_0x0056:
        r2 = 1;
    L_0x0057:
        r1 = "Invalid source";
        com.google.android.gms.common.internal.Preconditions.checkArgument(r2, r1);
        r1 = new com.google.android.gms.location.places.PlaceReport;
        r1.<init>(r3, r4, r5, r0);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.places.PlaceReport.create(java.lang.String, java.lang.String):com.google.android.gms.location.places.PlaceReport");
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlaceReport)) {
            return false;
        }
        PlaceReport placeReport = (PlaceReport) obj;
        return Objects.equal(this.zza, placeReport.zza) && Objects.equal(this.tag, placeReport.tag) && Objects.equal(this.zzb, placeReport.zzb);
    }

    public String getPlaceId() {
        return this.zza;
    }

    public String getTag() {
        return this.tag;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, this.tag, this.zzb);
    }

    public String toString() {
        ToStringHelper toStringHelper = Objects.toStringHelper(this);
        toStringHelper.add("placeId", this.zza);
        toStringHelper.add("tag", this.tag);
        if (!EnvironmentCompat.MEDIA_UNKNOWN.equals(this.zzb)) {
            toStringHelper.add(FirebaseAnalytics.Param.SOURCE, this.zzb);
        }
        return toStringHelper.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        i = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeString(parcel, 2, getPlaceId(), false);
        SafeParcelWriter.writeString(parcel, 3, getTag(), false);
        SafeParcelWriter.writeString(parcel, 4, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(parcel, i);
    }
}
