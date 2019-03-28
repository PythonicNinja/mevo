package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.support.annotation.GuardedBy;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement;
import com.raizlabs.android.dbflow.sql.language.Operator.Operation;

public final class zzfi extends zzhj {
    private long zzafi = -1;
    private char zzakl = '\u0000';
    @GuardedBy("this")
    private String zzakm;
    private final zzfk zzakn = new zzfk(this, 6, false, false);
    private final zzfk zzako = new zzfk(this, 6, true, false);
    private final zzfk zzakp = new zzfk(this, 6, false, true);
    private final zzfk zzakq = new zzfk(this, 5, false, false);
    private final zzfk zzakr = new zzfk(this, 5, true, false);
    private final zzfk zzaks = new zzfk(this, 5, false, true);
    private final zzfk zzakt = new zzfk(this, 4, false, false);
    private final zzfk zzaku = new zzfk(this, 3, false, false);
    private final zzfk zzakv = new zzfk(this, 2, false, false);

    zzfi(zzgn zzgn) {
        super(zzgn);
    }

    @VisibleForTesting
    private static String zza(boolean z, Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof Integer) {
            obj = Long.valueOf((long) ((Integer) obj).intValue());
        }
        int i = 0;
        String valueOf;
        if (obj instanceof Long) {
            if (!z) {
                return String.valueOf(obj);
            }
            Long l = (Long) obj;
            if (Math.abs(l.longValue()) < 100) {
                return String.valueOf(obj);
            }
            String str = String.valueOf(obj).charAt(0) == '-' ? Operation.MINUS : "";
            valueOf = String.valueOf(Math.abs(l.longValue()));
            long round = Math.round(Math.pow(10.0d, (double) (valueOf.length() - 1)));
            long round2 = Math.round(Math.pow(10.0d, (double) valueOf.length()) - 1.0d);
            StringBuilder stringBuilder = new StringBuilder((String.valueOf(str).length() + 43) + String.valueOf(str).length());
            stringBuilder.append(str);
            stringBuilder.append(round);
            stringBuilder.append("...");
            stringBuilder.append(str);
            stringBuilder.append(round2);
            return stringBuilder.toString();
        } else if (obj instanceof Boolean) {
            return String.valueOf(obj);
        } else {
            if (!(obj instanceof Throwable)) {
                return obj instanceof zzfl ? ((zzfl) obj).zzale : z ? Operation.MINUS : String.valueOf(obj);
            } else {
                Throwable th = (Throwable) obj;
                StringBuilder stringBuilder2 = new StringBuilder(z ? th.getClass().getName() : th.toString());
                valueOf = zzbq(AppMeasurement.class.getCanonicalName());
                String zzbq = zzbq(zzgn.class.getCanonicalName());
                StackTraceElement[] stackTrace = th.getStackTrace();
                int length = stackTrace.length;
                while (i < length) {
                    StackTraceElement stackTraceElement = stackTrace[i];
                    if (!stackTraceElement.isNativeMethod()) {
                        String className = stackTraceElement.getClassName();
                        if (className != null) {
                            className = zzbq(className);
                            if (className.equals(valueOf) || className.equals(zzbq)) {
                                stringBuilder2.append(": ");
                                stringBuilder2.append(stackTraceElement);
                                break;
                            }
                        } else {
                            continue;
                        }
                    }
                    i++;
                }
                return stringBuilder2.toString();
            }
        }
    }

    static String zza(boolean z, String str, Object obj, Object obj2, Object obj3) {
        if (str == null) {
            Object obj4 = "";
        }
        obj = zza(z, obj);
        obj2 = zza(z, obj2);
        Object zza = zza(z, obj3);
        StringBuilder stringBuilder = new StringBuilder();
        String str2 = "";
        if (!TextUtils.isEmpty(obj4)) {
            stringBuilder.append(obj4);
            str2 = ": ";
        }
        if (!TextUtils.isEmpty(obj)) {
            stringBuilder.append(str2);
            stringBuilder.append(obj);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(obj2)) {
            stringBuilder.append(str2);
            stringBuilder.append(obj2);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zza)) {
            stringBuilder.append(str2);
            stringBuilder.append(zza);
        }
        return stringBuilder.toString();
    }

    protected static Object zzbp(String str) {
        return str == null ? null : new zzfl(str);
    }

    private static String zzbq(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf == -1 ? str : str.substring(0, lastIndexOf);
    }

    private final String zzjd() {
        String str;
        synchronized (this) {
            if (this.zzakm == null) {
                this.zzakm = zzeh.zzhn();
            }
            str = this.zzakm;
        }
        return str;
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @VisibleForTesting
    protected final boolean isLoggable(int i) {
        return Log.isLoggable(zzjd(), i);
    }

    @VisibleForTesting
    protected final void zza(int i, String str) {
        Log.println(i, zzjd(), str);
    }

    protected final void zza(int i, boolean z, boolean z2, String str, Object obj, Object obj2, Object obj3) {
        if (!z && isLoggable(i)) {
            zza(i, zza(false, str, obj, obj2, obj3));
        }
        if (!z2 && i >= 5) {
            String str2;
            Preconditions.checkNotNull(str);
            zzhj zzka = this.zzacv.zzka();
            if (zzka == null) {
                str2 = "Scheduler not set. Not logging error/warn";
            } else if (zzka.isInitialized()) {
                if (i < 0) {
                    i = 0;
                }
                zzka.zzc(new zzfj(this, i >= 9 ? 8 : i, str, obj, obj2, obj3));
            } else {
                str2 = "Scheduler not initialized. Not logging error/warn";
            }
            zza(6, str2);
        }
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    public final /* bridge */ /* synthetic */ void zzfu() {
        super.zzfu();
    }

    public final /* bridge */ /* synthetic */ void zzfv() {
        super.zzfv();
    }

    public final /* bridge */ /* synthetic */ void zzfw() {
        super.zzfw();
    }

    public final /* bridge */ /* synthetic */ zzer zzge() {
        return super.zzge();
    }

    public final /* bridge */ /* synthetic */ zzfg zzgf() {
        return super.zzgf();
    }

    public final /* bridge */ /* synthetic */ zzkd zzgg() {
        return super.zzgg();
    }

    public final /* bridge */ /* synthetic */ zzgi zzgh() {
        return super.zzgh();
    }

    public final /* bridge */ /* synthetic */ zzfi zzgi() {
        return super.zzgi();
    }

    public final /* bridge */ /* synthetic */ zzft zzgj() {
        return super.zzgj();
    }

    public final /* bridge */ /* synthetic */ zzeh zzgk() {
        return super.zzgk();
    }

    public final /* bridge */ /* synthetic */ zzee zzgl() {
        return super.zzgl();
    }

    protected final boolean zzgn() {
        return false;
    }

    public final zzfk zziv() {
        return this.zzakn;
    }

    public final zzfk zziw() {
        return this.zzako;
    }

    public final zzfk zzix() {
        return this.zzakp;
    }

    public final zzfk zziy() {
        return this.zzakq;
    }

    public final zzfk zziz() {
        return this.zzaks;
    }

    public final zzfk zzja() {
        return this.zzakt;
    }

    public final zzfk zzjb() {
        return this.zzaku;
    }

    public final zzfk zzjc() {
        return this.zzakv;
    }

    public final String zzje() {
        Pair zzfi = zzgj().zzals.zzfi();
        if (zzfi != null) {
            if (zzfi != zzft.zzalr) {
                String valueOf = String.valueOf(zzfi.second);
                String str = (String) zzfi.first;
                StringBuilder stringBuilder = new StringBuilder((String.valueOf(valueOf).length() + 1) + String.valueOf(str).length());
                stringBuilder.append(valueOf);
                stringBuilder.append(":");
                stringBuilder.append(str);
                return stringBuilder.toString();
            }
        }
        return null;
    }
}
