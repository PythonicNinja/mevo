package com.google.android.gms.internal.measurement;

abstract class zzdz extends zzdy {
    private boolean zzvn;

    zzdz(zzgn zzgn) {
        super(zzgn);
        this.zzacv.zzb(this);
    }

    final boolean isInitialized() {
        return this.zzvn;
    }

    protected final void zzch() {
        if (!isInitialized()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzgm() {
        if (this.zzvn) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzgo();
        this.zzacv.zzkf();
        this.zzvn = true;
    }

    protected abstract boolean zzgn();

    protected void zzgo() {
    }

    public final void zzm() {
        if (this.zzvn) {
            throw new IllegalStateException("Can't initialize twice");
        } else if (!zzgn()) {
            this.zzacv.zzkf();
            this.zzvn = true;
        }
    }
}
