package com.google.android.gms.location;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collections;
import java.util.List;

@Class(creator = "ActivityRecognitionResultCreator")
@Reserved({1000})
public class ActivityRecognitionResult extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<ActivityRecognitionResult> CREATOR = new zzb();
    @Field(id = 5)
    private Bundle extras;
    @Field(id = 1)
    private List<DetectedActivity> zze;
    @Field(id = 2)
    private long zzf;
    @Field(id = 3)
    private long zzg;
    @Field(id = 4)
    private int zzh;

    @VisibleForTesting
    public ActivityRecognitionResult(DetectedActivity detectedActivity, long j, long j2) {
        this(detectedActivity, j, j2, 0, null);
    }

    private ActivityRecognitionResult(DetectedActivity detectedActivity, long j, long j2, int i, Bundle bundle) {
        this(Collections.singletonList(detectedActivity), j, j2, 0, null);
    }

    public ActivityRecognitionResult(List<DetectedActivity> list, long j, long j2) {
        this((List) list, j, j2, 0, null);
    }

    @Constructor
    public ActivityRecognitionResult(@Param(id = 1) List<DetectedActivity> list, @Param(id = 2) long j, @Param(id = 3) long j2, @Param(id = 4) int i, @Param(id = 5) Bundle bundle) {
        boolean z = false;
        boolean z2 = list != null && list.size() > 0;
        Preconditions.checkArgument(z2, "Must have at least 1 detected activity");
        if (j > 0 && j2 > 0) {
            z = true;
        }
        Preconditions.checkArgument(z, "Must set times");
        this.zze = list;
        this.zzf = j;
        this.zzg = j2;
        this.zzh = i;
        this.extras = bundle;
    }

    public static ActivityRecognitionResult extractResult(Intent intent) {
        ActivityRecognitionResult activityRecognitionResult;
        if (hasResult(intent)) {
            SafeParcelable safeParcelable = intent.getExtras().get("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT");
            if (safeParcelable instanceof byte[]) {
                safeParcelable = SafeParcelableSerializer.deserializeFromBytes((byte[]) safeParcelable, CREATOR);
            } else if (safeParcelable instanceof ActivityRecognitionResult) {
            }
            activityRecognitionResult = (ActivityRecognitionResult) safeParcelable;
            if (activityRecognitionResult != null) {
                return activityRecognitionResult;
            }
            List zza = zza(intent);
            return (zza == null || zza.isEmpty()) ? null : (ActivityRecognitionResult) zza.get(zza.size() - 1);
        }
        activityRecognitionResult = null;
        if (activityRecognitionResult != null) {
            return activityRecognitionResult;
        }
        List zza2 = zza(intent);
        if (zza2 == null) {
            return null;
        }
    }

    public static boolean hasResult(Intent intent) {
        if (intent == null) {
            return false;
        }
        if (intent == null ? false : intent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT")) {
            return true;
        }
        List zza = zza(intent);
        return (zza == null || zza.isEmpty()) ? false : true;
    }

    @Nullable
    private static List<ActivityRecognitionResult> zza(Intent intent) {
        return !(intent == null ? false : intent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT_LIST")) ? null : SafeParcelableSerializer.deserializeIterableFromIntentExtra(intent, "com.google.android.location.internal.EXTRA_ACTIVITY_RESULT_LIST", CREATOR);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean zza(android.os.Bundle r5, android.os.Bundle r6) {
        /*
        r0 = 1;
        if (r5 != 0) goto L_0x0006;
    L_0x0003:
        if (r6 != 0) goto L_0x0006;
    L_0x0005:
        return r0;
    L_0x0006:
        r1 = 0;
        if (r5 != 0) goto L_0x000b;
    L_0x0009:
        if (r6 != 0) goto L_0x000f;
    L_0x000b:
        if (r5 == 0) goto L_0x0010;
    L_0x000d:
        if (r6 != 0) goto L_0x0010;
    L_0x000f:
        return r1;
    L_0x0010:
        r2 = r5.size();
        r3 = r6.size();
        if (r2 == r3) goto L_0x001b;
    L_0x001a:
        return r1;
    L_0x001b:
        r2 = r5.keySet();
        r2 = r2.iterator();
    L_0x0023:
        r3 = r2.hasNext();
        if (r3 == 0) goto L_0x0069;
    L_0x0029:
        r3 = r2.next();
        r3 = (java.lang.String) r3;
        r4 = r6.containsKey(r3);
        if (r4 != 0) goto L_0x0036;
    L_0x0035:
        return r1;
    L_0x0036:
        r4 = r5.get(r3);
        if (r4 != 0) goto L_0x0043;
    L_0x003c:
        r3 = r6.get(r3);
        if (r3 == 0) goto L_0x0023;
    L_0x0042:
        return r1;
    L_0x0043:
        r4 = r5.get(r3);
        r4 = r4 instanceof android.os.Bundle;
        if (r4 == 0) goto L_0x005a;
    L_0x004b:
        r4 = r5.getBundle(r3);
        r3 = r6.getBundle(r3);
        r3 = zza(r4, r3);
        if (r3 != 0) goto L_0x0023;
    L_0x0059:
        return r1;
    L_0x005a:
        r4 = r5.get(r3);
        r3 = r6.get(r3);
        r3 = r4.equals(r3);
        if (r3 != 0) goto L_0x0023;
    L_0x0068:
        return r1;
    L_0x0069:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.ActivityRecognitionResult.zza(android.os.Bundle, android.os.Bundle):boolean");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ActivityRecognitionResult activityRecognitionResult = (ActivityRecognitionResult) obj;
        return this.zzf == activityRecognitionResult.zzf && this.zzg == activityRecognitionResult.zzg && this.zzh == activityRecognitionResult.zzh && Objects.equal(this.zze, activityRecognitionResult.zze) && zza(this.extras, activityRecognitionResult.extras);
    }

    public int getActivityConfidence(int i) {
        for (DetectedActivity detectedActivity : this.zze) {
            if (detectedActivity.getType() == i) {
                return detectedActivity.getConfidence();
            }
        }
        return 0;
    }

    public long getElapsedRealtimeMillis() {
        return this.zzg;
    }

    public DetectedActivity getMostProbableActivity() {
        return (DetectedActivity) this.zze.get(0);
    }

    public List<DetectedActivity> getProbableActivities() {
        return this.zze;
    }

    public long getTime() {
        return this.zzf;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzf), Long.valueOf(this.zzg), Integer.valueOf(this.zzh), this.zze, this.extras);
    }

    public String toString() {
        String valueOf = String.valueOf(this.zze);
        long j = this.zzf;
        long j2 = this.zzg;
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(valueOf).length() + 124);
        stringBuilder.append("ActivityRecognitionResult [probableActivities=");
        stringBuilder.append(valueOf);
        stringBuilder.append(", timeMillis=");
        stringBuilder.append(j);
        stringBuilder.append(", elapsedRealtimeMillis=");
        stringBuilder.append(j2);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        i = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.zze, false);
        SafeParcelWriter.writeLong(parcel, 2, this.zzf);
        SafeParcelWriter.writeLong(parcel, 3, this.zzg);
        SafeParcelWriter.writeInt(parcel, 4, this.zzh);
        SafeParcelWriter.writeBundle(parcel, 5, this.extras, false);
        SafeParcelWriter.finishObjectHeader(parcel, i);
    }
}
