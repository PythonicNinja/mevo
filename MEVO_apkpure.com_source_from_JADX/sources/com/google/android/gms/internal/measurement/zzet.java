package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;

final class zzet {
    final String name;
    final long zzahh;
    final long zzahi;
    final long zzahj;
    final long zzahk;
    final Long zzahl;
    final Long zzahm;
    final Boolean zzahn;
    final String zzth;

    zzet(String str, String str2, long j, long j2, long j3, long j4, Long l, Long l2, Boolean bool) {
        zzet zzet = this;
        long j5 = j;
        long j6 = j2;
        long j7 = j4;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        boolean z = false;
        Preconditions.checkArgument(j5 >= 0);
        Preconditions.checkArgument(j6 >= 0);
        if (j7 >= 0) {
            z = true;
        }
        Preconditions.checkArgument(z);
        zzet.zzth = str;
        zzet.name = str2;
        zzet.zzahh = j5;
        zzet.zzahi = j6;
        zzet.zzahj = j3;
        zzet.zzahk = j7;
        zzet.zzahl = l;
        zzet.zzahm = l2;
        zzet.zzahn = bool;
    }

    final zzet zza(Long l, Long l2, Boolean bool) {
        zzet zzet = this;
        Boolean bool2 = (bool == null || bool.booleanValue()) ? bool : null;
        return new zzet(zzet.zzth, zzet.name, zzet.zzahh, zzet.zzahi, zzet.zzahj, zzet.zzahk, l, l2, bool2);
    }

    final zzet zzah(long j) {
        return new zzet(this.zzth, this.name, this.zzahh, this.zzahi, j, this.zzahk, this.zzahl, this.zzahm, this.zzahn);
    }

    final zzet zzai(long j) {
        return new zzet(this.zzth, this.name, this.zzahh, this.zzahi, this.zzahj, j, this.zzahl, this.zzahm, this.zzahn);
    }

    final zzet zzim() {
        String str = this.zzth;
        String str2 = this.name;
        long j = this.zzahh + 1;
        long j2 = this.zzahi + 1;
        long j3 = this.zzahj;
        long j4 = this.zzahk;
        return new zzet(str, str2, j, j2, j3, j4, this.zzahl, this.zzahm, this.zzahn);
    }
}
