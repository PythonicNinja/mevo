package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

final class zzzr<FieldDescriptorType extends zzzt<FieldDescriptorType>> {
    private static final zzzr zzbub = new zzzr(true);
    private boolean zzbnw;
    private final zzabd<FieldDescriptorType, Object> zzbtz = zzabd.zzag(16);
    private boolean zzbua = false;

    private zzzr() {
    }

    private zzzr(boolean z) {
        if (!this.zzbnw) {
            this.zzbtz.zzru();
            this.zzbnw = true;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zza(com.google.android.gms.internal.measurement.zzabu r2, java.lang.Object r3) {
        /*
        com.google.android.gms.internal.measurement.zzzw.checkNotNull(r3);
        r0 = com.google.android.gms.internal.measurement.zzzs.zzbuc;
        r2 = r2.zzvk();
        r2 = r2.ordinal();
        r2 = r0[r2];
        r0 = 1;
        r1 = 0;
        switch(r2) {
            case 1: goto L_0x0040;
            case 2: goto L_0x003d;
            case 3: goto L_0x003a;
            case 4: goto L_0x0037;
            case 5: goto L_0x0034;
            case 6: goto L_0x0031;
            case 7: goto L_0x0028;
            case 8: goto L_0x001e;
            case 9: goto L_0x0015;
            default: goto L_0x0014;
        };
    L_0x0014:
        goto L_0x0043;
    L_0x0015:
        r2 = r3 instanceof com.google.android.gms.internal.measurement.zzaaq;
        if (r2 != 0) goto L_0x0026;
    L_0x0019:
        r2 = r3 instanceof com.google.android.gms.internal.measurement.zzzz;
        if (r2 == 0) goto L_0x0043;
    L_0x001d:
        goto L_0x0026;
    L_0x001e:
        r2 = r3 instanceof java.lang.Integer;
        if (r2 != 0) goto L_0x0026;
    L_0x0022:
        r2 = r3 instanceof com.google.android.gms.internal.measurement.zzzx;
        if (r2 == 0) goto L_0x0043;
    L_0x0026:
        r1 = 1;
        goto L_0x0043;
    L_0x0028:
        r2 = r3 instanceof com.google.android.gms.internal.measurement.zzzb;
        if (r2 != 0) goto L_0x0026;
    L_0x002c:
        r2 = r3 instanceof byte[];
        if (r2 == 0) goto L_0x0043;
    L_0x0030:
        goto L_0x0026;
    L_0x0031:
        r0 = r3 instanceof java.lang.String;
        goto L_0x0042;
    L_0x0034:
        r0 = r3 instanceof java.lang.Boolean;
        goto L_0x0042;
    L_0x0037:
        r0 = r3 instanceof java.lang.Double;
        goto L_0x0042;
    L_0x003a:
        r0 = r3 instanceof java.lang.Float;
        goto L_0x0042;
    L_0x003d:
        r0 = r3 instanceof java.lang.Long;
        goto L_0x0042;
    L_0x0040:
        r0 = r3 instanceof java.lang.Integer;
    L_0x0042:
        r1 = r0;
    L_0x0043:
        if (r1 != 0) goto L_0x004d;
    L_0x0045:
        r2 = new java.lang.IllegalArgumentException;
        r3 = "Wrong object type used with protocol message reflection.";
        r2.<init>(r3);
        throw r2;
    L_0x004d:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzzr.zza(com.google.android.gms.internal.measurement.zzabu, java.lang.Object):void");
    }

    private final void zza(FieldDescriptorType fieldDescriptorType, Object obj) {
        if (!fieldDescriptorType.zztz()) {
            zza(fieldDescriptorType.zzty(), obj);
        } else if (obj instanceof List) {
            List arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            ArrayList arrayList2 = (ArrayList) arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList2.get(i);
                i++;
                zza(fieldDescriptorType.zzty(), obj2);
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof zzzz) {
            this.zzbua = true;
        }
        this.zzbtz.zza((Comparable) fieldDescriptorType, obj);
    }

    public static <T extends zzzt<T>> zzzr<T> zztx() {
        return zzbub;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzzr zzzr = new zzzr();
        for (int i = 0; i < this.zzbtz.zzuy(); i++) {
            Entry zzah = this.zzbtz.zzah(i);
            zzzr.zza((zzzt) zzah.getKey(), zzah.getValue());
        }
        for (Entry zzah2 : this.zzbtz.zzuz()) {
            zzzr.zza((zzzt) zzah2.getKey(), zzah2.getValue());
        }
        zzzr.zzbua = this.zzbua;
        return zzzr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzzr)) {
            return false;
        }
        return this.zzbtz.equals(((zzzr) obj).zzbtz);
    }

    public final int hashCode() {
        return this.zzbtz.hashCode();
    }

    public final Iterator<Entry<FieldDescriptorType, Object>> iterator() {
        return this.zzbua ? new zzaac(this.zzbtz.entrySet().iterator()) : this.zzbtz.entrySet().iterator();
    }
}
