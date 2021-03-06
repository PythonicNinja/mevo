package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;

final class zzt implements zzbq {
    private final /* synthetic */ zzr zzgc;

    private zzt(zzr zzr) {
        this.zzgc = zzr;
    }

    public final void zzb(int i, boolean z) {
        this.zzgc.zzga.lock();
        try {
            if (!(this.zzgc.zzfz || this.zzgc.zzfy == null)) {
                if (this.zzgc.zzfy.isSuccess()) {
                    this.zzgc.zzfz = true;
                    this.zzgc.zzfs.onConnectionSuspended(i);
                    this.zzgc.zzga.unlock();
                }
            }
            this.zzgc.zzfz = false;
            this.zzgc.zza(i, z);
            this.zzgc.zzga.unlock();
        } catch (Throwable th) {
            this.zzgc.zzga.unlock();
        }
    }

    public final void zzb(@Nullable Bundle bundle) {
        this.zzgc.zzga.lock();
        try {
            this.zzgc.zza(bundle);
            this.zzgc.zzfx = ConnectionResult.RESULT_SUCCESS;
            this.zzgc.zzaa();
        } finally {
            this.zzgc.zzga.unlock();
        }
    }

    public final void zzc(@NonNull ConnectionResult connectionResult) {
        this.zzgc.zzga.lock();
        try {
            this.zzgc.zzfx = connectionResult;
            this.zzgc.zzaa();
        } finally {
            this.zzgc.zzga.unlock();
        }
    }
}
