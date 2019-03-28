package com.google.android.gms.internal.measurement;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public final class zzace<M extends zzacd<M>, T> {
    public final int tag;
    private final int type;
    protected final Class<T> zzbze;
    protected final boolean zzbzf;
    private final zzzv<?, ?> zzbzg;

    private zzace(int i, Class<T> cls, int i2, boolean z) {
        this(11, cls, null, 810, false);
    }

    private zzace(int i, Class<T> cls, zzzv<?, ?> zzzv, int i2, boolean z) {
        this.type = i;
        this.zzbze = cls;
        this.tag = i2;
        this.zzbzf = false;
        this.zzbzg = null;
    }

    public static <M extends zzacd<M>, T extends zzacj> zzace<M, T> zza(int i, Class<T> cls, long j) {
        return new zzace(11, cls, 810, false);
    }

    private final Object zzf(zzaca zzaca) {
        StringBuilder stringBuilder;
        String valueOf;
        Class componentType = this.zzbzf ? this.zzbze.getComponentType() : this.zzbze;
        try {
            zzacj zzacj;
            switch (this.type) {
                case 10:
                    zzacj = (zzacj) componentType.newInstance();
                    zzaca.zza(zzacj, this.tag >>> 3);
                    return zzacj;
                case 11:
                    zzacj = (zzacj) componentType.newInstance();
                    zzaca.zza(zzacj);
                    return zzacj;
                default:
                    int i = this.type;
                    stringBuilder = new StringBuilder(24);
                    stringBuilder.append("Unknown type ");
                    stringBuilder.append(i);
                    throw new IllegalArgumentException(stringBuilder.toString());
            }
        } catch (Throwable e) {
            valueOf = String.valueOf(componentType);
            stringBuilder = new StringBuilder(String.valueOf(valueOf).length() + 33);
            stringBuilder.append("Error creating instance of class ");
            stringBuilder.append(valueOf);
            throw new IllegalArgumentException(stringBuilder.toString(), e);
        } catch (Throwable e2) {
            valueOf = String.valueOf(componentType);
            stringBuilder = new StringBuilder(String.valueOf(valueOf).length() + 33);
            stringBuilder.append("Error creating instance of class ");
            stringBuilder.append(valueOf);
            throw new IllegalArgumentException(stringBuilder.toString(), e2);
        } catch (Throwable e22) {
            throw new IllegalArgumentException("Error reading extension field", e22);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzace)) {
            return false;
        }
        zzace zzace = (zzace) obj;
        return this.type == zzace.type && this.zzbze == zzace.zzbze && this.tag == zzace.tag && this.zzbzf == zzace.zzbzf;
    }

    public final int hashCode() {
        return ((((((this.type + 1147) * 31) + this.zzbze.hashCode()) * 31) + this.tag) * 31) + this.zzbzf;
    }

    protected final void zza(Object obj, zzacb zzacb) {
        try {
            zzacb.zzar(this.tag);
            switch (this.type) {
                case 10:
                    int i = this.tag >>> 3;
                    ((zzacj) obj).zza(zzacb);
                    zzacb.zzg(i, 4);
                    return;
                case 11:
                    zzacb.zzb((zzacj) obj);
                    return;
                default:
                    int i2 = this.type;
                    StringBuilder stringBuilder = new StringBuilder(24);
                    stringBuilder.append("Unknown type ");
                    stringBuilder.append(i2);
                    throw new IllegalArgumentException(stringBuilder.toString());
            }
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    final T zzi(List<zzacl> list) {
        if (list == null) {
            return null;
        }
        if (this.zzbzf) {
            List arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                zzacl zzacl = (zzacl) list.get(i);
                if (zzacl.zzbtj.length != 0) {
                    arrayList.add(zzf(zzaca.zzi(zzacl.zzbtj)));
                }
            }
            int size = arrayList.size();
            if (size == 0) {
                return null;
            }
            T cast = this.zzbze.cast(Array.newInstance(this.zzbze.getComponentType(), size));
            for (int i2 = 0; i2 < size; i2++) {
                Array.set(cast, i2, arrayList.get(i2));
            }
            return cast;
        } else if (list.isEmpty()) {
            return null;
        } else {
            return this.zzbze.cast(zzf(zzaca.zzi(((zzacl) list.get(list.size() - 1)).zzbtj)));
        }
    }

    protected final int zzv(Object obj) {
        int i = this.tag >>> 3;
        switch (this.type) {
            case 10:
                return (zzacb.zzaq(i) << 1) + ((zzacj) obj).zzwb();
            case 11:
                return zzacb.zzb(i, (zzacj) obj);
            default:
                i = this.type;
                StringBuilder stringBuilder = new StringBuilder(24);
                stringBuilder.append("Unknown type ");
                stringBuilder.append(i);
                throw new IllegalArgumentException(stringBuilder.toString());
        }
    }
}
