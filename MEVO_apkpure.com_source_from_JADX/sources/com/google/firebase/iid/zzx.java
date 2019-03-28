package com.google.firebase.iid;

import android.util.Base64;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.VisibleForTesting;
import java.security.KeyPair;

final class zzx {
    private final KeyPair zzbi;
    private final long zzbj;

    @VisibleForTesting
    zzx(KeyPair keyPair, long j) {
        this.zzbi = keyPair;
        this.zzbj = j;
    }

    private final String zzu() {
        return Base64.encodeToString(this.zzbi.getPublic().getEncoded(), 11);
    }

    private final String zzv() {
        return Base64.encodeToString(this.zzbi.getPrivate().getEncoded(), 11);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzx)) {
            return false;
        }
        zzx zzx = (zzx) obj;
        return this.zzbj == zzx.zzbj && this.zzbi.getPublic().equals(zzx.zzbi.getPublic()) && this.zzbi.getPrivate().equals(zzx.zzbi.getPrivate());
    }

    final long getCreationTime() {
        return this.zzbj;
    }

    final KeyPair getKeyPair() {
        return this.zzbi;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzbi.getPublic(), this.zzbi.getPrivate(), Long.valueOf(this.zzbj));
    }
}
