package com.google.android.gms.internal.measurement;

import java.io.PrintStream;

public final class zzxi {
    private static final zzxj zzbqa;
    private static final int zzbqb;

    static final class zza extends zzxj {
        zza() {
        }

        public final void zza(Throwable th, PrintStream printStream) {
            th.printStackTrace(printStream);
        }
    }

    static {
        Integer zzsr;
        zzxj zzxn;
        Throwable th;
        PrintStream printStream;
        String name;
        StringBuilder stringBuilder;
        int i = 1;
        try {
            zzsr = zzsr();
            if (zzsr != null) {
                try {
                    if (zzsr.intValue() >= 19) {
                        zzxn = new zzxn();
                        zzbqa = zzxn;
                        if (zzsr != null) {
                            i = zzsr.intValue();
                        }
                        zzbqb = i;
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
                    zzxn = new zza();
                    zzbqa = zzxn;
                    if (zzsr != null) {
                        i = zzsr.intValue();
                    }
                    zzbqb = i;
                }
            }
            zzxn = (Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic") ^ 1) != 0 ? new zzxm() : new zza();
        } catch (Throwable th3) {
            th = th3;
            zzsr = null;
            printStream = System.err;
            name = zza.class.getName();
            stringBuilder = new StringBuilder(String.valueOf(name).length() + 132);
            stringBuilder.append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ");
            stringBuilder.append(name);
            stringBuilder.append("will be used. The error is: ");
            printStream.println(stringBuilder.toString());
            th.printStackTrace(System.err);
            zzxn = new zza();
            zzbqa = zzxn;
            if (zzsr != null) {
                i = zzsr.intValue();
            }
            zzbqb = i;
        }
        zzbqa = zzxn;
        if (zzsr != null) {
            i = zzsr.intValue();
        }
        zzbqb = i;
    }

    public static void zza(Throwable th, PrintStream printStream) {
        zzbqa.zza(th, printStream);
    }

    private static Integer zzsr() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
            return null;
        }
    }
}
