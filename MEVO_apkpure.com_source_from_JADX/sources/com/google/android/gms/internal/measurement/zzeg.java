package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzeg implements Creator<zzef> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 0;
        long j2 = j;
        long j3 = j2;
        String str = null;
        String str2 = str;
        zzka zzka = str2;
        String str3 = zzka;
        zzex zzex = str3;
        zzex zzex2 = zzex;
        zzex zzex3 = zzex2;
        boolean z = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    str = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 3:
                    str2 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 4:
                    zzka = (zzka) SafeParcelReader.createParcelable(parcel2, readHeader, zzka.CREATOR);
                    break;
                case 5:
                    j = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 6:
                    z = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 7:
                    str3 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 8:
                    zzex = (zzex) SafeParcelReader.createParcelable(parcel2, readHeader, zzex.CREATOR);
                    break;
                case 9:
                    j2 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 10:
                    zzex2 = (zzex) SafeParcelReader.createParcelable(parcel2, readHeader, zzex.CREATOR);
                    break;
                case 11:
                    j3 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 12:
                    zzex3 = (zzex) SafeParcelReader.createParcelable(parcel2, readHeader, zzex.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new zzef(str, str2, zzka, j, z, str3, zzex, j2, zzex2, j3, zzex3);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzef[i];
    }
}
