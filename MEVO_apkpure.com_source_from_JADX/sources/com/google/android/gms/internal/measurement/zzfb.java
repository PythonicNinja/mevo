package com.google.android.gms.internal.measurement;

public abstract class zzfb extends zzo implements zzfa {
    public zzfb() {
        super("com.google.android.gms.measurement.internal.IMeasurementService");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected final boolean dispatchTransaction(int r7, android.os.Parcel r8, android.os.Parcel r9, int r10) throws android.os.RemoteException {
        /*
        r6 = this;
        switch(r7) {
            case 1: goto L_0x0123;
            case 2: goto L_0x010f;
            case 3: goto L_0x0003;
            case 4: goto L_0x0103;
            case 5: goto L_0x00ef;
            case 6: goto L_0x00e3;
            case 7: goto L_0x00cc;
            case 8: goto L_0x0003;
            case 9: goto L_0x00b5;
            case 10: goto L_0x009f;
            case 11: goto L_0x008b;
            case 12: goto L_0x0076;
            case 13: goto L_0x0069;
            case 14: goto L_0x0050;
            case 15: goto L_0x003a;
            case 16: goto L_0x0024;
            case 17: goto L_0x0012;
            case 18: goto L_0x0005;
            default: goto L_0x0003;
        };
    L_0x0003:
        r7 = 0;
        return r7;
    L_0x0005:
        r7 = com.google.android.gms.internal.measurement.zzeb.CREATOR;
        r7 = com.google.android.gms.internal.measurement.zzp.zza(r8, r7);
        r7 = (com.google.android.gms.internal.measurement.zzeb) r7;
        r6.zzd(r7);
        goto L_0x0136;
    L_0x0012:
        r7 = r8.readString();
        r10 = r8.readString();
        r8 = r8.readString();
        r7 = r6.zze(r7, r10, r8);
        goto L_0x00dc;
    L_0x0024:
        r7 = r8.readString();
        r10 = r8.readString();
        r0 = com.google.android.gms.internal.measurement.zzeb.CREATOR;
        r8 = com.google.android.gms.internal.measurement.zzp.zza(r8, r0);
        r8 = (com.google.android.gms.internal.measurement.zzeb) r8;
        r7 = r6.zza(r7, r10, r8);
        goto L_0x00dc;
    L_0x003a:
        r7 = r8.readString();
        r10 = r8.readString();
        r0 = r8.readString();
        r8 = com.google.android.gms.internal.measurement.zzp.zza(r8);
        r7 = r6.zza(r7, r10, r0, r8);
        goto L_0x00dc;
    L_0x0050:
        r7 = r8.readString();
        r10 = r8.readString();
        r0 = com.google.android.gms.internal.measurement.zzp.zza(r8);
        r1 = com.google.android.gms.internal.measurement.zzeb.CREATOR;
        r8 = com.google.android.gms.internal.measurement.zzp.zza(r8, r1);
        r8 = (com.google.android.gms.internal.measurement.zzeb) r8;
        r7 = r6.zza(r7, r10, r0, r8);
        goto L_0x00dc;
    L_0x0069:
        r7 = com.google.android.gms.internal.measurement.zzef.CREATOR;
        r7 = com.google.android.gms.internal.measurement.zzp.zza(r8, r7);
        r7 = (com.google.android.gms.internal.measurement.zzef) r7;
        r6.zzb(r7);
        goto L_0x0136;
    L_0x0076:
        r7 = com.google.android.gms.internal.measurement.zzef.CREATOR;
        r7 = com.google.android.gms.internal.measurement.zzp.zza(r8, r7);
        r7 = (com.google.android.gms.internal.measurement.zzef) r7;
        r10 = com.google.android.gms.internal.measurement.zzeb.CREATOR;
        r8 = com.google.android.gms.internal.measurement.zzp.zza(r8, r10);
        r8 = (com.google.android.gms.internal.measurement.zzeb) r8;
        r6.zza(r7, r8);
        goto L_0x0136;
    L_0x008b:
        r7 = com.google.android.gms.internal.measurement.zzeb.CREATOR;
        r7 = com.google.android.gms.internal.measurement.zzp.zza(r8, r7);
        r7 = (com.google.android.gms.internal.measurement.zzeb) r7;
        r7 = r6.zzc(r7);
        r9.writeNoException();
        r9.writeString(r7);
        goto L_0x0139;
    L_0x009f:
        r1 = r8.readLong();
        r3 = r8.readString();
        r4 = r8.readString();
        r5 = r8.readString();
        r0 = r6;
        r0.zza(r1, r3, r4, r5);
        goto L_0x0136;
    L_0x00b5:
        r7 = com.google.android.gms.internal.measurement.zzex.CREATOR;
        r7 = com.google.android.gms.internal.measurement.zzp.zza(r8, r7);
        r7 = (com.google.android.gms.internal.measurement.zzex) r7;
        r8 = r8.readString();
        r7 = r6.zza(r7, r8);
        r9.writeNoException();
        r9.writeByteArray(r7);
        goto L_0x0139;
    L_0x00cc:
        r7 = com.google.android.gms.internal.measurement.zzeb.CREATOR;
        r7 = com.google.android.gms.internal.measurement.zzp.zza(r8, r7);
        r7 = (com.google.android.gms.internal.measurement.zzeb) r7;
        r8 = com.google.android.gms.internal.measurement.zzp.zza(r8);
        r7 = r6.zza(r7, r8);
    L_0x00dc:
        r9.writeNoException();
        r9.writeTypedList(r7);
        goto L_0x0139;
    L_0x00e3:
        r7 = com.google.android.gms.internal.measurement.zzeb.CREATOR;
        r7 = com.google.android.gms.internal.measurement.zzp.zza(r8, r7);
        r7 = (com.google.android.gms.internal.measurement.zzeb) r7;
        r6.zzb(r7);
        goto L_0x0136;
    L_0x00ef:
        r7 = com.google.android.gms.internal.measurement.zzex.CREATOR;
        r7 = com.google.android.gms.internal.measurement.zzp.zza(r8, r7);
        r7 = (com.google.android.gms.internal.measurement.zzex) r7;
        r10 = r8.readString();
        r8 = r8.readString();
        r6.zza(r7, r10, r8);
        goto L_0x0136;
    L_0x0103:
        r7 = com.google.android.gms.internal.measurement.zzeb.CREATOR;
        r7 = com.google.android.gms.internal.measurement.zzp.zza(r8, r7);
        r7 = (com.google.android.gms.internal.measurement.zzeb) r7;
        r6.zza(r7);
        goto L_0x0136;
    L_0x010f:
        r7 = com.google.android.gms.internal.measurement.zzka.CREATOR;
        r7 = com.google.android.gms.internal.measurement.zzp.zza(r8, r7);
        r7 = (com.google.android.gms.internal.measurement.zzka) r7;
        r10 = com.google.android.gms.internal.measurement.zzeb.CREATOR;
        r8 = com.google.android.gms.internal.measurement.zzp.zza(r8, r10);
        r8 = (com.google.android.gms.internal.measurement.zzeb) r8;
        r6.zza(r7, r8);
        goto L_0x0136;
    L_0x0123:
        r7 = com.google.android.gms.internal.measurement.zzex.CREATOR;
        r7 = com.google.android.gms.internal.measurement.zzp.zza(r8, r7);
        r7 = (com.google.android.gms.internal.measurement.zzex) r7;
        r10 = com.google.android.gms.internal.measurement.zzeb.CREATOR;
        r8 = com.google.android.gms.internal.measurement.zzp.zza(r8, r10);
        r8 = (com.google.android.gms.internal.measurement.zzeb) r8;
        r6.zza(r7, r8);
    L_0x0136:
        r9.writeNoException();
    L_0x0139:
        r7 = 1;
        return r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzfb.dispatchTransaction(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
