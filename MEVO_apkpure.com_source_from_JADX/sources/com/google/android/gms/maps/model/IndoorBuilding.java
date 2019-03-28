package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.maps.zzn;
import com.google.android.gms.internal.maps.zzq;
import com.google.android.gms.internal.maps.zzr;
import java.util.ArrayList;
import java.util.List;

public final class IndoorBuilding {
    @NonNull
    private final zzn zzdc;
    @NonNull
    private final zza zzdd;

    @VisibleForTesting
    static class zza {
        public static final zza zzde = new zza();

        private zza() {
        }

        @NonNull
        public static zzq zza(IBinder iBinder) {
            return zzr.zzf(iBinder);
        }

        @NonNull
        public static IndoorLevel zza(@NonNull zzq zzq) {
            return new IndoorLevel(zzq);
        }
    }

    public IndoorBuilding(@NonNull zzn zzn) {
        this(zzn, zza.zzde);
    }

    @VisibleForTesting
    private IndoorBuilding(@NonNull zzn zzn, @NonNull zza zza) {
        this.zzdc = (zzn) Preconditions.checkNotNull(zzn, "delegate");
        this.zzdd = (zza) Preconditions.checkNotNull(zza, "shim");
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof IndoorBuilding)) {
            return false;
        }
        try {
            return this.zzdc.zzb(((IndoorBuilding) obj).zzdc);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final int getActiveLevelIndex() {
        try {
            return this.zzdc.getActiveLevelIndex();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final int getDefaultLevelIndex() {
        try {
            return this.zzdc.getDefaultLevelIndex();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final List<IndoorLevel> getLevels() {
        try {
            List<IBinder> levels = this.zzdc.getLevels();
            List<IndoorLevel> arrayList = new ArrayList(levels.size());
            for (IBinder zza : levels) {
                arrayList.add(zza.zza(zza.zza(zza)));
            }
            return arrayList;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final int hashCode() {
        try {
            return this.zzdc.zzi();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean isUnderground() {
        try {
            return this.zzdc.isUnderground();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
