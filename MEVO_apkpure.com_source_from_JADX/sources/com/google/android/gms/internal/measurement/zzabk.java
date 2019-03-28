package com.google.android.gms.internal.measurement;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map.Entry;

final class zzabk extends AbstractSet<Entry<K, V>> {
    private final /* synthetic */ zzabd zzbwm;

    private zzabk(zzabd zzabd) {
        this.zzbwm = zzabd;
    }

    public final /* synthetic */ boolean add(Object obj) {
        Entry entry = (Entry) obj;
        if (contains(entry)) {
            return false;
        }
        this.zzbwm.zza((Comparable) entry.getKey(), entry.getValue());
        return true;
    }

    public final void clear() {
        this.zzbwm.clear();
    }

    public final boolean contains(Object obj) {
        Entry entry = (Entry) obj;
        Object obj2 = this.zzbwm.get(entry.getKey());
        obj = entry.getValue();
        if (obj2 != obj) {
            if (obj2 == null || !obj2.equals(obj)) {
                return false;
            }
        }
        return true;
    }

    public final Iterator<Entry<K, V>> iterator() {
        return new zzabj(this.zzbwm);
    }

    public final boolean remove(Object obj) {
        Entry entry = (Entry) obj;
        if (!contains(entry)) {
            return false;
        }
        this.zzbwm.remove(entry.getKey());
        return true;
    }

    public final int size() {
        return this.zzbwm.size();
    }
}
