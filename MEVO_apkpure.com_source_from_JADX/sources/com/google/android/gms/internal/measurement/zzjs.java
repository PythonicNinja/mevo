package com.google.android.gms.internal.measurement;

abstract class zzjs extends zzjr {
    private boolean zzvn;

    zzjs(zzjt zzjt) {
        super(zzjt);
        this.zzalo.zzb(this);
    }

    final boolean isInitialized() {
        return this.zzvn;
    }

    protected final void zzch() {
        if (!isInitialized()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    protected abstract boolean zzgn();

    public final void zzm() {
        if (this.zzvn) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzgn();
        this.zzalo.zzll();
        this.zzvn = true;
    }
}
