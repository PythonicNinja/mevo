package com.google.android.gms.internal.measurement;

final class zzzu implements zzaap {
    private static final zzzu zzbue = new zzzu();

    private zzzu() {
    }

    public static zzzu zzua() {
        return zzbue;
    }

    public final boolean zzd(Class<?> cls) {
        return zzzv.class.isAssignableFrom(cls);
    }

    public final zzaao zze(Class<?> cls) {
        if (zzzv.class.isAssignableFrom(cls)) {
            try {
                return (zzaao) zzzv.zzf(cls.asSubclass(zzzv.class)).zza(3, null, null);
            } catch (Throwable e) {
                String str = "Unable to get message info for ";
                String valueOf = String.valueOf(cls.getName());
                throw new RuntimeException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), e);
            }
        }
        String str2 = "Unsupported message type: ";
        valueOf = String.valueOf(cls.getName());
        throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
    }
}
