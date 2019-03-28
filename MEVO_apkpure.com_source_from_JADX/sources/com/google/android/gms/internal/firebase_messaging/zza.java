package com.google.android.gms.internal.firebase_messaging;

import java.io.PrintStream;

public final class zza {
    private static final zzb zza;
    private static final int zzb;

    static final class zza extends zzb {
        zza() {
        }

        public final void zza(Throwable th, Throwable th2) {
        }
    }

    static {
        Integer zza;
        zzb zzf;
        Throwable th;
        PrintStream printStream;
        String name;
        StringBuilder stringBuilder;
        int i = 1;
        try {
            zza = zza();
            if (zza != null) {
                try {
                    if (zza.intValue() >= 19) {
                        zzf = new zzf();
                        zza = zzf;
                        if (zza != null) {
                            i = zza.intValue();
                        }
                        zzb = i;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    printStream = System.err;
                    name = zza.class.getName();
                    stringBuilder = new StringBuilder(String.valueOf(name).length() + 132);
                    stringBuilder.append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ");
                    stringBuilder.append(name);
                    stringBuilder.append("will be used. The error is: ");
                    printStream.println(stringBuilder.toString());
                    th.printStackTrace(System.err);
                    zzf = new zza();
                    zza = zzf;
                    if (zza != null) {
                        i = zza.intValue();
                    }
                    zzb = i;
                }
            }
            zzf = (Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic") ^ 1) != 0 ? new zze() : new zza();
        } catch (Throwable th3) {
            th = th3;
            zza = null;
            printStream = System.err;
            name = zza.class.getName();
            stringBuilder = new StringBuilder(String.valueOf(name).length() + 132);
            stringBuilder.append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ");
            stringBuilder.append(name);
            stringBuilder.append("will be used. The error is: ");
            printStream.println(stringBuilder.toString());
            th.printStackTrace(System.err);
            zzf = new zza();
            zza = zzf;
            if (zza != null) {
                i = zza.intValue();
            }
            zzb = i;
        }
        zza = zzf;
        if (zza != null) {
            i = zza.intValue();
        }
        zzb = i;
    }

    private static Integer zza() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
            return null;
        }
    }

    public static void zza(Throwable th, Throwable th2) {
        zza.zza(th, th2);
    }
}
