package com.google.android.gms.maps.model;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.IOUtils;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;

@Class(creator = "MapStyleOptionsCreator")
@Reserved({1})
public final class MapStyleOptions extends AbstractSafeParcelable {
    public static final Creator<MapStyleOptions> CREATOR = new zzg();
    private static final String TAG = "MapStyleOptions";
    @Field(getter = "getJson", id = 2)
    private String zzdk;

    @Constructor
    public MapStyleOptions(@Param(id = 2) String str) {
        this.zzdk = str;
    }

    public static MapStyleOptions loadRawResourceStyle(Context context, int i) throws NotFoundException {
        try {
            return new MapStyleOptions(new String(IOUtils.readInputStreamFully(context.getResources().openRawResource(i)), HttpRequest.CHARSET_UTF8));
        } catch (IOException e) {
            String valueOf = String.valueOf(e);
            StringBuilder stringBuilder = new StringBuilder(String.valueOf(valueOf).length() + 37);
            stringBuilder.append("Failed to read resource ");
            stringBuilder.append(i);
            stringBuilder.append(": ");
            stringBuilder.append(valueOf);
            throw new NotFoundException(stringBuilder.toString());
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        i = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzdk, false);
        SafeParcelWriter.finishObjectHeader(parcel, i);
    }
}
