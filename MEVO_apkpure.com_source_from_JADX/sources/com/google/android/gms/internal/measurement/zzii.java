package com.google.android.gms.internal.measurement;

import android.os.Bundle;

final class zzii implements Runnable {
    private final /* synthetic */ boolean zzaqj;
    private final /* synthetic */ zzig zzaqk;
    private final /* synthetic */ zzig zzaql;
    private final /* synthetic */ zzih zzaqm;

    zzii(zzih zzih, boolean z, zzig zzig, zzig zzig2) {
        this.zzaqm = zzih;
        this.zzaqj = z;
        this.zzaqk = zzig;
        this.zzaql = zzig2;
    }

    public final void run() {
        Object obj;
        Bundle bundle;
        if (this.zzaqj && this.zzaqm.zzaqd != null) {
            this.zzaqm.zza(this.zzaqm.zzaqd);
        }
        if (this.zzaqk != null && this.zzaqk.zzaqb == this.zzaql.zzaqb && zzkd.zzs(this.zzaqk.zzaqa, this.zzaql.zzaqa)) {
            if (zzkd.zzs(this.zzaqk.zzuk, this.zzaql.zzuk)) {
                obj = null;
                if (obj != null) {
                    bundle = new Bundle();
                    zzih.zza(this.zzaql, bundle, true);
                    if (this.zzaqk != null) {
                        if (this.zzaqk.zzuk != null) {
                            bundle.putString("_pn", this.zzaqk.zzuk);
                        }
                        bundle.putString("_pc", this.zzaqk.zzaqa);
                        bundle.putLong("_pi", this.zzaqk.zzaqb);
                    }
                    this.zzaqm.zzfy().zza("auto", "_vs", bundle);
                }
                this.zzaqm.zzaqd = this.zzaql;
                this.zzaqm.zzga().zzb(this.zzaql);
            }
        }
        obj = 1;
        if (obj != null) {
            bundle = new Bundle();
            zzih.zza(this.zzaql, bundle, true);
            if (this.zzaqk != null) {
                if (this.zzaqk.zzuk != null) {
                    bundle.putString("_pn", this.zzaqk.zzuk);
                }
                bundle.putString("_pc", this.zzaqk.zzaqa);
                bundle.putLong("_pi", this.zzaqk.zzaqb);
            }
            this.zzaqm.zzfy().zza("auto", "_vs", bundle);
        }
        this.zzaqm.zzaqd = this.zzaql;
        this.zzaqm.zzga().zzb(this.zzaql);
    }
}
