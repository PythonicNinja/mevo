package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzabj implements Iterator<Entry<K, V>> {
    private int pos;
    private final /* synthetic */ zzabd zzbwm;
    private boolean zzbwn;
    private Iterator<Entry<K, V>> zzbwo;

    private zzabj(zzabd zzabd) {
        this.zzbwm = zzabd;
        this.pos = -1;
    }

    private final Iterator<Entry<K, V>> zzve() {
        if (this.zzbwo == null) {
            this.zzbwo = this.zzbwm.zzbwg.entrySet().iterator();
        }
        return this.zzbwo;
    }

    public final boolean hasNext() {
        return this.pos + 1 >= this.zzbwm.zzbwf.size() ? !this.zzbwm.zzbwg.isEmpty() && zzve().hasNext() : true;
    }

    public final /* synthetic */ Object next() {
        this.zzbwn = true;
        int i = this.pos + 1;
        this.pos = i;
        return (Entry) (i < this.zzbwm.zzbwf.size() ? this.zzbwm.zzbwf.get(this.pos) : zzve().next());
    }

    public final void remove() {
        if (this.zzbwn) {
            this.zzbwn = false;
            this.zzbwm.zzva();
            if (this.pos < this.zzbwm.zzbwf.size()) {
                zzabd zzabd = this.zzbwm;
                int i = this.pos;
                this.pos = i - 1;
                zzabd.zzai(i);
                return;
            }
            zzve().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }
}
