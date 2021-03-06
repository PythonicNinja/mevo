package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.measurement.AppMeasurement.Event;
import com.google.android.gms.measurement.AppMeasurement.UserProperty;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.security.auth.x500.X500Principal;

public final class zzkd extends zzhj {
    private static final String[] zzasx = new String[]{"firebase_", "google_", "ga_"};
    private int zzadj;
    private SecureRandom zzasy;
    private final AtomicLong zzasz = new AtomicLong(0);
    private Integer zzata = null;

    zzkd(zzgn zzgn) {
        super(zzgn);
    }

    static java.security.MessageDigest getMessageDigest() {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r0 = 0;
    L_0x0001:
        r1 = 2;
        if (r0 >= r1) goto L_0x0010;
    L_0x0004:
        r1 = "MD5";	 Catch:{ NoSuchAlgorithmException -> 0x000d }
        r1 = java.security.MessageDigest.getInstance(r1);	 Catch:{ NoSuchAlgorithmException -> 0x000d }
        if (r1 == 0) goto L_0x000d;
    L_0x000c:
        return r1;
    L_0x000d:
        r0 = r0 + 1;
        goto L_0x0001;
    L_0x0010:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzkd.getMessageDigest():java.security.MessageDigest");
    }

    private static Object zza(int i, Object obj, boolean z) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof Long) || (obj instanceof Double)) {
            return obj;
        }
        if (obj instanceof Integer) {
            return Long.valueOf((long) ((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return Long.valueOf((long) ((Byte) obj).byteValue());
        }
        if (obj instanceof Short) {
            return Long.valueOf((long) ((Short) obj).shortValue());
        }
        if (obj instanceof Boolean) {
            return Long.valueOf(((Boolean) obj).booleanValue() ? 1 : 0);
        } else if (obj instanceof Float) {
            return Double.valueOf(((Float) obj).doubleValue());
        } else {
            if (!((obj instanceof String) || (obj instanceof Character))) {
                if (!(obj instanceof CharSequence)) {
                    return null;
                }
            }
            return zza(String.valueOf(obj), i, z);
        }
    }

    public static String zza(String str, int i, boolean z) {
        if (str.codePointCount(0, str.length()) > i) {
            if (z) {
                return String.valueOf(str.substring(0, str.offsetByCodePoints(0, i))).concat("...");
            }
            str = null;
        }
        return str;
    }

    @Nullable
    public static String zza(String str, String[] strArr, String[] strArr2) {
        Preconditions.checkNotNull(strArr);
        Preconditions.checkNotNull(strArr2);
        int min = Math.min(strArr.length, strArr2.length);
        for (int i = 0; i < min; i++) {
            if (zzs(str, strArr[i])) {
                return strArr2[i];
            }
        }
        return null;
    }

    private static void zza(Bundle bundle, Object obj) {
        Preconditions.checkNotNull(bundle);
        if (obj == null) {
            return;
        }
        if ((obj instanceof String) || (obj instanceof CharSequence)) {
            bundle.putLong("_el", (long) String.valueOf(obj).length());
        }
    }

    static boolean zza(Context context, boolean z) {
        Preconditions.checkNotNull(context);
        return zzc(context, VERSION.SDK_INT >= 24 ? "com.google.android.gms.measurement.AppMeasurementJobService" : "com.google.android.gms.measurement.AppMeasurementService");
    }

    private static boolean zza(Bundle bundle, int i) {
        if (bundle.getLong("_err") != 0) {
            return false;
        }
        bundle.putLong("_err", (long) i);
        return true;
    }

    private final boolean zza(String str, String str2, int i, Object obj, boolean z) {
        if (obj == null || (obj instanceof Long) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Boolean) || (obj instanceof Double)) {
            return true;
        }
        if (!((obj instanceof String) || (obj instanceof Character))) {
            if (!(obj instanceof CharSequence)) {
                if ((obj instanceof Bundle) && z) {
                    return true;
                }
                int length;
                Object obj2;
                if ((obj instanceof Parcelable[]) && z) {
                    Parcelable[] parcelableArr = (Parcelable[]) obj;
                    length = parcelableArr.length;
                    i = 0;
                    while (i < length) {
                        obj2 = parcelableArr[i];
                        if (obj2 instanceof Bundle) {
                            i++;
                        } else {
                            zzgi().zziy().zze("All Parcelable[] elements must be of type Bundle. Value type, name", obj2.getClass(), str2);
                            return false;
                        }
                    }
                    return true;
                } else if (!(obj instanceof ArrayList) || !z) {
                    return false;
                } else {
                    ArrayList arrayList = (ArrayList) obj;
                    length = arrayList.size();
                    i = 0;
                    while (i < length) {
                        obj2 = arrayList.get(i);
                        i++;
                        if (!(obj2 instanceof Bundle)) {
                            zzgi().zziy().zze("All ArrayList elements must be of type Bundle. Value type, name", obj2.getClass(), str2);
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        String valueOf = String.valueOf(obj);
        if (valueOf.codePointCount(0, valueOf.length()) > i) {
            zzgi().zziy().zzd("Value is too long; discarded. Value kind, name, value length", str, str2, Integer.valueOf(valueOf.length()));
            return false;
        }
        return true;
    }

    static byte[] zza(Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            parcelable.writeToParcel(obtain, 0);
            byte[] marshall = obtain.marshall();
            return marshall;
        } finally {
            obtain.recycle();
        }
    }

    @VisibleForTesting
    static long zzc(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        long j = null;
        Preconditions.checkState(bArr.length > 0);
        long j2 = 0;
        int length = bArr.length - 1;
        while (length >= 0 && length >= bArr.length - 8) {
            j += 8;
            length--;
            j2 += (((long) bArr[length]) & 255) << j;
        }
        return j2;
    }

    private static boolean zzc(android.content.Context r3, java.lang.String r4) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r0 = 0;
        r1 = r3.getPackageManager();	 Catch:{ NameNotFoundException -> 0x0019 }
        if (r1 != 0) goto L_0x0008;	 Catch:{ NameNotFoundException -> 0x0019 }
    L_0x0007:
        return r0;	 Catch:{ NameNotFoundException -> 0x0019 }
    L_0x0008:
        r2 = new android.content.ComponentName;	 Catch:{ NameNotFoundException -> 0x0019 }
        r2.<init>(r3, r4);	 Catch:{ NameNotFoundException -> 0x0019 }
        r3 = r1.getServiceInfo(r2, r0);	 Catch:{ NameNotFoundException -> 0x0019 }
        if (r3 == 0) goto L_0x0019;	 Catch:{ NameNotFoundException -> 0x0019 }
    L_0x0013:
        r3 = r3.enabled;	 Catch:{ NameNotFoundException -> 0x0019 }
        if (r3 == 0) goto L_0x0019;
    L_0x0017:
        r3 = 1;
        return r3;
    L_0x0019:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzkd.zzc(android.content.Context, java.lang.String):boolean");
    }

    static boolean zzcg(String str) {
        Preconditions.checkNotEmpty(str);
        if (str.charAt(0) == '_') {
            if (!str.equals("_ep")) {
                return false;
            }
        }
        return true;
    }

    private static int zzcl(String str) {
        return "_ldl".equals(str) ? 2048 : "_id".equals(str) ? 256 : 36;
    }

    static boolean zzcm(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
    }

    static boolean zzd(Intent intent) {
        String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
        if (!("android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(stringExtra) || "https://www.google.com".equals(stringExtra))) {
            if (!"android-app://com.google.appcrawler".equals(stringExtra)) {
                return false;
            }
        }
        return true;
    }

    @VisibleForTesting
    private final boolean zze(Context context, String str) {
        Object e;
        zzfk zziv;
        String str2;
        X500Principal x500Principal = new X500Principal("CN=Android Debug,O=Android,C=US");
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str, 64);
            if (!(packageInfo == null || packageInfo.signatures == null || packageInfo.signatures.length <= 0)) {
                return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(packageInfo.signatures[0].toByteArray()))).getSubjectX500Principal().equals(x500Principal);
            }
        } catch (CertificateException e2) {
            e = e2;
            zziv = zzgi().zziv();
            str2 = "Error obtaining certificate";
            zziv.zzg(str2, e);
            return true;
        } catch (NameNotFoundException e3) {
            e = e3;
            zziv = zzgi().zziv();
            str2 = "Package name not found";
            zziv.zzg(str2, e);
            return true;
        }
        return true;
    }

    static Bundle[] zze(Object obj) {
        if (obj instanceof Bundle) {
            return new Bundle[]{(Bundle) obj};
        }
        Object[] copyOf;
        if (obj instanceof Parcelable[]) {
            Parcelable[] parcelableArr = (Parcelable[]) obj;
            copyOf = Arrays.copyOf(parcelableArr, parcelableArr.length, Bundle[].class);
        } else if (!(obj instanceof ArrayList)) {
            return null;
        } else {
            ArrayList arrayList = (ArrayList) obj;
            copyOf = arrayList.toArray(new Bundle[arrayList.size()]);
        }
        return (Bundle[]) copyOf;
    }

    public static java.lang.Object zzf(java.lang.Object r4) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r0 = 0;
        if (r4 != 0) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r1 = new java.io.ByteArrayOutputStream;	 Catch:{ all -> 0x0032 }
        r1.<init>();	 Catch:{ all -> 0x0032 }
        r2 = new java.io.ObjectOutputStream;	 Catch:{ all -> 0x0032 }
        r2.<init>(r1);	 Catch:{ all -> 0x0032 }
        r2.writeObject(r4);	 Catch:{ all -> 0x002f }
        r2.flush();	 Catch:{ all -> 0x002f }
        r4 = new java.io.ObjectInputStream;	 Catch:{ all -> 0x002f }
        r3 = new java.io.ByteArrayInputStream;	 Catch:{ all -> 0x002f }
        r1 = r1.toByteArray();	 Catch:{ all -> 0x002f }
        r3.<init>(r1);	 Catch:{ all -> 0x002f }
        r4.<init>(r3);	 Catch:{ all -> 0x002f }
        r1 = r4.readObject();	 Catch:{ all -> 0x002d }
        r2.close();	 Catch:{ IOException -> 0x0040, IOException -> 0x0040 }
        r4.close();	 Catch:{ IOException -> 0x0040, IOException -> 0x0040 }
        return r1;	 Catch:{ IOException -> 0x0040, IOException -> 0x0040 }
    L_0x002d:
        r1 = move-exception;	 Catch:{ IOException -> 0x0040, IOException -> 0x0040 }
        goto L_0x0035;	 Catch:{ IOException -> 0x0040, IOException -> 0x0040 }
    L_0x002f:
        r1 = move-exception;	 Catch:{ IOException -> 0x0040, IOException -> 0x0040 }
        r4 = r0;	 Catch:{ IOException -> 0x0040, IOException -> 0x0040 }
        goto L_0x0035;	 Catch:{ IOException -> 0x0040, IOException -> 0x0040 }
    L_0x0032:
        r1 = move-exception;	 Catch:{ IOException -> 0x0040, IOException -> 0x0040 }
        r4 = r0;	 Catch:{ IOException -> 0x0040, IOException -> 0x0040 }
        r2 = r4;	 Catch:{ IOException -> 0x0040, IOException -> 0x0040 }
    L_0x0035:
        if (r2 == 0) goto L_0x003a;	 Catch:{ IOException -> 0x0040, IOException -> 0x0040 }
    L_0x0037:
        r2.close();	 Catch:{ IOException -> 0x0040, IOException -> 0x0040 }
    L_0x003a:
        if (r4 == 0) goto L_0x003f;	 Catch:{ IOException -> 0x0040, IOException -> 0x0040 }
    L_0x003c:
        r4.close();	 Catch:{ IOException -> 0x0040, IOException -> 0x0040 }
    L_0x003f:
        throw r1;	 Catch:{ IOException -> 0x0040, IOException -> 0x0040 }
    L_0x0040:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzkd.zzf(java.lang.Object):java.lang.Object");
    }

    private final boolean zzr(String str, String str2) {
        if (str2 == null) {
            zzgi().zziv().zzg("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            zzgi().zziv().zzg("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (Character.isLetter(codePointAt) || codePointAt == 95) {
                int length = str2.length();
                codePointAt = Character.charCount(codePointAt);
                while (codePointAt < length) {
                    int codePointAt2 = str2.codePointAt(codePointAt);
                    if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                        codePointAt += Character.charCount(codePointAt2);
                    } else {
                        zzgi().zziv().zze("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                        return false;
                    }
                }
                return true;
            }
            zzgi().zziv().zze("Name must start with a letter or _ (underscore). Type, name", str, str2);
            return false;
        }
    }

    static boolean zzs(String str, String str2) {
        return (str == null && str2 == null) ? true : str == null ? false : str.equals(str2);
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    final Bundle zza(@NonNull Uri uri) {
        if (uri == null) {
            return null;
        }
        try {
            Object queryParameter;
            Object queryParameter2;
            Object queryParameter3;
            Object queryParameter4;
            if (uri.isHierarchical()) {
                queryParameter = uri.getQueryParameter("utm_campaign");
                queryParameter2 = uri.getQueryParameter("utm_source");
                queryParameter3 = uri.getQueryParameter("utm_medium");
                queryParameter4 = uri.getQueryParameter("gclid");
            } else {
                queryParameter = null;
                queryParameter2 = queryParameter;
                queryParameter3 = queryParameter2;
                queryParameter4 = queryParameter3;
            }
            if (TextUtils.isEmpty(queryParameter) && TextUtils.isEmpty(queryParameter2) && TextUtils.isEmpty(queryParameter3)) {
                if (TextUtils.isEmpty(queryParameter4)) {
                    return null;
                }
            }
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(queryParameter)) {
                bundle.putString(Param.CAMPAIGN, queryParameter);
            }
            if (!TextUtils.isEmpty(queryParameter2)) {
                bundle.putString(Param.SOURCE, queryParameter2);
            }
            if (!TextUtils.isEmpty(queryParameter3)) {
                bundle.putString(Param.MEDIUM, queryParameter3);
            }
            if (!TextUtils.isEmpty(queryParameter4)) {
                bundle.putString("gclid", queryParameter4);
            }
            queryParameter = uri.getQueryParameter("utm_term");
            if (!TextUtils.isEmpty(queryParameter)) {
                bundle.putString(Param.TERM, queryParameter);
            }
            queryParameter = uri.getQueryParameter("utm_content");
            if (!TextUtils.isEmpty(queryParameter)) {
                bundle.putString(Param.CONTENT, queryParameter);
            }
            queryParameter = uri.getQueryParameter(Param.ACLID);
            if (!TextUtils.isEmpty(queryParameter)) {
                bundle.putString(Param.ACLID, queryParameter);
            }
            queryParameter = uri.getQueryParameter(Param.CP1);
            if (!TextUtils.isEmpty(queryParameter)) {
                bundle.putString(Param.CP1, queryParameter);
            }
            Object queryParameter5 = uri.getQueryParameter("anid");
            if (!TextUtils.isEmpty(queryParameter5)) {
                bundle.putString("anid", queryParameter5);
            }
            return bundle;
        } catch (UnsupportedOperationException e) {
            zzgi().zziy().zzg("Install referrer url isn't a hierarchical URI", e);
            return null;
        }
    }

    final Bundle zza(String str, String str2, Bundle bundle, @Nullable List<String> list, boolean z, boolean z2) {
        zzkd zzkd = this;
        Bundle bundle2 = bundle;
        List<String> list2 = list;
        String[] strArr = null;
        if (bundle2 == null) {
            return null;
        }
        Bundle bundle3 = new Bundle(bundle2);
        int i = 0;
        for (String str3 : bundle.keySet()) {
            int i2;
            Object obj;
            String str4;
            Object obj2;
            int i3;
            boolean z3;
            StringBuilder stringBuilder;
            String str5;
            String str6;
            int i4;
            zzkd zzkd2;
            String str7;
            int i5;
            if (list2 != null) {
                if (!list2.contains(str3)) {
                }
                i2 = 0;
                if (i2 == 0) {
                    if (zza(bundle3, i2)) {
                        bundle3.putString("_ev", zza(str3, 40, true));
                        if (i2 == 3) {
                            zza(bundle3, (Object) str3);
                        }
                    }
                    bundle3.remove(str3);
                } else {
                    obj = bundle2.get(str3);
                    zzab();
                    if (z2) {
                        str4 = "param";
                        if (obj instanceof Parcelable[]) {
                            if (obj instanceof ArrayList) {
                                i2 = ((ArrayList) obj).size();
                            }
                            obj2 = 1;
                            if (obj2 == null) {
                                i3 = 17;
                                z3 = true;
                                if (i3 != 0 || "_ev".equals(str3)) {
                                    if (zzcg(str3)) {
                                        i++;
                                        if (i > 25) {
                                            stringBuilder = new StringBuilder(48);
                                            stringBuilder.append("Event can't contain more than 25 params");
                                            zzgi().zziv().zze(stringBuilder.toString(), zzgf().zzbm(str2), zzgf().zzb(bundle2));
                                            zza(bundle3, 5);
                                            bundle3.remove(str3);
                                        }
                                    }
                                    str5 = str2;
                                } else {
                                    if (zza(bundle3, i3)) {
                                        bundle3.putString("_ev", zza(str3, 40, z3));
                                        zza(bundle3, bundle2.get(str3));
                                    }
                                    bundle3.remove(str3);
                                }
                            }
                        } else {
                            i2 = ((Parcelable[]) obj).length;
                        }
                        if (i2 > 1000) {
                            zzgi().zziy().zzd("Parameter array is too long; discarded. Value kind, name, array length", str4, str3, Integer.valueOf(i2));
                            obj2 = null;
                            if (obj2 == null) {
                                i3 = 17;
                                z3 = true;
                                if (i3 != 0) {
                                }
                                if (zzcg(str3)) {
                                    i++;
                                    if (i > 25) {
                                        stringBuilder = new StringBuilder(48);
                                        stringBuilder.append("Event can't contain more than 25 params");
                                        zzgi().zziv().zze(stringBuilder.toString(), zzgf().zzbm(str2), zzgf().zzb(bundle2));
                                        zza(bundle3, 5);
                                        bundle3.remove(str3);
                                    }
                                }
                                str5 = str2;
                            }
                        }
                        obj2 = 1;
                        if (obj2 == null) {
                            i3 = 17;
                            z3 = true;
                            if (i3 != 0) {
                            }
                            if (zzcg(str3)) {
                                i++;
                                if (i > 25) {
                                    stringBuilder = new StringBuilder(48);
                                    stringBuilder.append("Event can't contain more than 25 params");
                                    zzgi().zziv().zze(stringBuilder.toString(), zzgf().zzbm(str2), zzgf().zzb(bundle2));
                                    zza(bundle3, 5);
                                    bundle3.remove(str3);
                                }
                            }
                            str5 = str2;
                        }
                    }
                    if ((zzgk().zzax(str) || !zzcm(str2)) && !zzcm(str3)) {
                        z3 = true;
                        str6 = "param";
                        i4 = 100;
                        zzkd2 = zzkd;
                        str7 = str3;
                    } else {
                        str6 = "param";
                        zzkd2 = zzkd;
                        str7 = str3;
                        i4 = 256;
                        z3 = true;
                    }
                    i3 = zzkd2.zza(str6, str7, i4, obj, z2) ? 0 : 4;
                    if (i3 != 0) {
                    }
                    if (zzcg(str3)) {
                        i++;
                        if (i > 25) {
                            stringBuilder = new StringBuilder(48);
                            stringBuilder.append("Event can't contain more than 25 params");
                            zzgi().zziv().zze(stringBuilder.toString(), zzgf().zzbm(str2), zzgf().zzb(bundle2));
                            zza(bundle3, 5);
                            bundle3.remove(str3);
                        }
                    }
                    str5 = str2;
                }
                strArr = null;
            }
            i2 = 14;
            if (z) {
                if (zzq("event param", str3)) {
                    if (!zza("event param", strArr, str3)) {
                        i5 = 14;
                        if (i5 == 0) {
                            if (zzr("event param", str3)) {
                                if (!zza("event param", strArr, str3)) {
                                    if (!zza("event param", 40, str3)) {
                                    }
                                    i2 = 0;
                                }
                            }
                            i2 = 3;
                        } else {
                            i2 = i5;
                        }
                        if (i2 == 0) {
                            obj = bundle2.get(str3);
                            zzab();
                            if (z2) {
                                str4 = "param";
                                if (obj instanceof Parcelable[]) {
                                    if (obj instanceof ArrayList) {
                                        i2 = ((ArrayList) obj).size();
                                    }
                                    obj2 = 1;
                                    if (obj2 == null) {
                                        i3 = 17;
                                        z3 = true;
                                        if (i3 != 0) {
                                        }
                                        if (zzcg(str3)) {
                                            i++;
                                            if (i > 25) {
                                                stringBuilder = new StringBuilder(48);
                                                stringBuilder.append("Event can't contain more than 25 params");
                                                zzgi().zziv().zze(stringBuilder.toString(), zzgf().zzbm(str2), zzgf().zzb(bundle2));
                                                zza(bundle3, 5);
                                                bundle3.remove(str3);
                                            }
                                        }
                                        str5 = str2;
                                    }
                                } else {
                                    i2 = ((Parcelable[]) obj).length;
                                }
                                if (i2 > 1000) {
                                    zzgi().zziy().zzd("Parameter array is too long; discarded. Value kind, name, array length", str4, str3, Integer.valueOf(i2));
                                    obj2 = null;
                                    if (obj2 == null) {
                                        i3 = 17;
                                        z3 = true;
                                        if (i3 != 0) {
                                        }
                                        if (zzcg(str3)) {
                                            i++;
                                            if (i > 25) {
                                                stringBuilder = new StringBuilder(48);
                                                stringBuilder.append("Event can't contain more than 25 params");
                                                zzgi().zziv().zze(stringBuilder.toString(), zzgf().zzbm(str2), zzgf().zzb(bundle2));
                                                zza(bundle3, 5);
                                                bundle3.remove(str3);
                                            }
                                        }
                                        str5 = str2;
                                    }
                                }
                                obj2 = 1;
                                if (obj2 == null) {
                                    i3 = 17;
                                    z3 = true;
                                    if (i3 != 0) {
                                    }
                                    if (zzcg(str3)) {
                                        i++;
                                        if (i > 25) {
                                            stringBuilder = new StringBuilder(48);
                                            stringBuilder.append("Event can't contain more than 25 params");
                                            zzgi().zziv().zze(stringBuilder.toString(), zzgf().zzbm(str2), zzgf().zzb(bundle2));
                                            zza(bundle3, 5);
                                            bundle3.remove(str3);
                                        }
                                    }
                                    str5 = str2;
                                }
                            }
                            if (zzgk().zzax(str)) {
                            }
                            z3 = true;
                            str6 = "param";
                            i4 = 100;
                            zzkd2 = zzkd;
                            str7 = str3;
                            if (zzkd2.zza(str6, str7, i4, obj, z2)) {
                            }
                            if (i3 != 0) {
                            }
                            if (zzcg(str3)) {
                                i++;
                                if (i > 25) {
                                    stringBuilder = new StringBuilder(48);
                                    stringBuilder.append("Event can't contain more than 25 params");
                                    zzgi().zziv().zze(stringBuilder.toString(), zzgf().zzbm(str2), zzgf().zzb(bundle2));
                                    zza(bundle3, 5);
                                    bundle3.remove(str3);
                                }
                            }
                            str5 = str2;
                        } else {
                            if (zza(bundle3, i2)) {
                                bundle3.putString("_ev", zza(str3, 40, true));
                                if (i2 == 3) {
                                    zza(bundle3, (Object) str3);
                                }
                            }
                            bundle3.remove(str3);
                        }
                        strArr = null;
                    } else if (!zza("event param", 40, str3)) {
                    }
                }
                i5 = 3;
                if (i5 == 0) {
                    i2 = i5;
                } else {
                    if (zzr("event param", str3)) {
                        if (!zza("event param", strArr, str3)) {
                            if (zza("event param", 40, str3)) {
                            }
                            i2 = 0;
                        }
                    }
                    i2 = 3;
                }
                if (i2 == 0) {
                    if (zza(bundle3, i2)) {
                        bundle3.putString("_ev", zza(str3, 40, true));
                        if (i2 == 3) {
                            zza(bundle3, (Object) str3);
                        }
                    }
                    bundle3.remove(str3);
                } else {
                    obj = bundle2.get(str3);
                    zzab();
                    if (z2) {
                        str4 = "param";
                        if (obj instanceof Parcelable[]) {
                            i2 = ((Parcelable[]) obj).length;
                        } else {
                            if (obj instanceof ArrayList) {
                                i2 = ((ArrayList) obj).size();
                            }
                            obj2 = 1;
                            if (obj2 == null) {
                                i3 = 17;
                                z3 = true;
                                if (i3 != 0) {
                                }
                                if (zzcg(str3)) {
                                    i++;
                                    if (i > 25) {
                                        stringBuilder = new StringBuilder(48);
                                        stringBuilder.append("Event can't contain more than 25 params");
                                        zzgi().zziv().zze(stringBuilder.toString(), zzgf().zzbm(str2), zzgf().zzb(bundle2));
                                        zza(bundle3, 5);
                                        bundle3.remove(str3);
                                    }
                                }
                                str5 = str2;
                            }
                        }
                        if (i2 > 1000) {
                            zzgi().zziy().zzd("Parameter array is too long; discarded. Value kind, name, array length", str4, str3, Integer.valueOf(i2));
                            obj2 = null;
                            if (obj2 == null) {
                                i3 = 17;
                                z3 = true;
                                if (i3 != 0) {
                                }
                                if (zzcg(str3)) {
                                    i++;
                                    if (i > 25) {
                                        stringBuilder = new StringBuilder(48);
                                        stringBuilder.append("Event can't contain more than 25 params");
                                        zzgi().zziv().zze(stringBuilder.toString(), zzgf().zzbm(str2), zzgf().zzb(bundle2));
                                        zza(bundle3, 5);
                                        bundle3.remove(str3);
                                    }
                                }
                                str5 = str2;
                            }
                        }
                        obj2 = 1;
                        if (obj2 == null) {
                            i3 = 17;
                            z3 = true;
                            if (i3 != 0) {
                            }
                            if (zzcg(str3)) {
                                i++;
                                if (i > 25) {
                                    stringBuilder = new StringBuilder(48);
                                    stringBuilder.append("Event can't contain more than 25 params");
                                    zzgi().zziv().zze(stringBuilder.toString(), zzgf().zzbm(str2), zzgf().zzb(bundle2));
                                    zza(bundle3, 5);
                                    bundle3.remove(str3);
                                }
                            }
                            str5 = str2;
                        }
                    }
                    if (zzgk().zzax(str)) {
                    }
                    z3 = true;
                    str6 = "param";
                    i4 = 100;
                    zzkd2 = zzkd;
                    str7 = str3;
                    if (zzkd2.zza(str6, str7, i4, obj, z2)) {
                    }
                    if (i3 != 0) {
                    }
                    if (zzcg(str3)) {
                        i++;
                        if (i > 25) {
                            stringBuilder = new StringBuilder(48);
                            stringBuilder.append("Event can't contain more than 25 params");
                            zzgi().zziv().zze(stringBuilder.toString(), zzgf().zzbm(str2), zzgf().zzb(bundle2));
                            zza(bundle3, 5);
                            bundle3.remove(str3);
                        }
                    }
                    str5 = str2;
                }
                strArr = null;
            }
            i5 = 0;
            if (i5 == 0) {
                if (zzr("event param", str3)) {
                    if (!zza("event param", strArr, str3)) {
                        if (zza("event param", 40, str3)) {
                        }
                        i2 = 0;
                    }
                }
                i2 = 3;
            } else {
                i2 = i5;
            }
            if (i2 == 0) {
                obj = bundle2.get(str3);
                zzab();
                if (z2) {
                    str4 = "param";
                    if (obj instanceof Parcelable[]) {
                        if (obj instanceof ArrayList) {
                            i2 = ((ArrayList) obj).size();
                        }
                        obj2 = 1;
                        if (obj2 == null) {
                            i3 = 17;
                            z3 = true;
                            if (i3 != 0) {
                            }
                            if (zzcg(str3)) {
                                i++;
                                if (i > 25) {
                                    stringBuilder = new StringBuilder(48);
                                    stringBuilder.append("Event can't contain more than 25 params");
                                    zzgi().zziv().zze(stringBuilder.toString(), zzgf().zzbm(str2), zzgf().zzb(bundle2));
                                    zza(bundle3, 5);
                                    bundle3.remove(str3);
                                }
                            }
                            str5 = str2;
                        }
                    } else {
                        i2 = ((Parcelable[]) obj).length;
                    }
                    if (i2 > 1000) {
                        zzgi().zziy().zzd("Parameter array is too long; discarded. Value kind, name, array length", str4, str3, Integer.valueOf(i2));
                        obj2 = null;
                        if (obj2 == null) {
                            i3 = 17;
                            z3 = true;
                            if (i3 != 0) {
                            }
                            if (zzcg(str3)) {
                                i++;
                                if (i > 25) {
                                    stringBuilder = new StringBuilder(48);
                                    stringBuilder.append("Event can't contain more than 25 params");
                                    zzgi().zziv().zze(stringBuilder.toString(), zzgf().zzbm(str2), zzgf().zzb(bundle2));
                                    zza(bundle3, 5);
                                    bundle3.remove(str3);
                                }
                            }
                            str5 = str2;
                        }
                    }
                    obj2 = 1;
                    if (obj2 == null) {
                        i3 = 17;
                        z3 = true;
                        if (i3 != 0) {
                        }
                        if (zzcg(str3)) {
                            i++;
                            if (i > 25) {
                                stringBuilder = new StringBuilder(48);
                                stringBuilder.append("Event can't contain more than 25 params");
                                zzgi().zziv().zze(stringBuilder.toString(), zzgf().zzbm(str2), zzgf().zzb(bundle2));
                                zza(bundle3, 5);
                                bundle3.remove(str3);
                            }
                        }
                        str5 = str2;
                    }
                }
                if (zzgk().zzax(str)) {
                }
                z3 = true;
                str6 = "param";
                i4 = 100;
                zzkd2 = zzkd;
                str7 = str3;
                if (zzkd2.zza(str6, str7, i4, obj, z2)) {
                }
                if (i3 != 0) {
                }
                if (zzcg(str3)) {
                    i++;
                    if (i > 25) {
                        stringBuilder = new StringBuilder(48);
                        stringBuilder.append("Event can't contain more than 25 params");
                        zzgi().zziv().zze(stringBuilder.toString(), zzgf().zzbm(str2), zzgf().zzb(bundle2));
                        zza(bundle3, 5);
                        bundle3.remove(str3);
                    }
                }
                str5 = str2;
            } else {
                if (zza(bundle3, i2)) {
                    bundle3.putString("_ev", zza(str3, 40, true));
                    if (i2 == 3) {
                        zza(bundle3, (Object) str3);
                    }
                }
                bundle3.remove(str3);
            }
            strArr = null;
        }
        return bundle3;
    }

    final zzex zza(String str, String str2, Bundle bundle, String str3, long j, boolean z, boolean z2) {
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        if (zzch(str2) != 0) {
            zzgi().zziv().zzg("Invalid conditional property event name", zzgf().zzbo(str2));
            throw new IllegalArgumentException();
        }
        Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
        bundle2.putString("_o", str3);
        return new zzex(str2, new zzeu(zzd(zza(str, str2, bundle2, CollectionUtils.listOf((Object) "_o"), false, false))), str3, j);
    }

    public final void zza(int i, String str, String str2, int i2) {
        zza(null, i, str, str2, i2);
    }

    final void zza(Bundle bundle, String str, Object obj) {
        if (bundle != null) {
            if (obj instanceof Long) {
                bundle.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof String) {
                bundle.putString(str, String.valueOf(obj));
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            } else {
                if (str != null) {
                    zzgi().zziz().zze("Not putting event parameter. Invalid value type. name, type", zzgf().zzbn(str), obj != null ? obj.getClass().getSimpleName() : null);
                }
            }
        }
    }

    final void zza(String str, int i, String str2, String str3, int i2) {
        Bundle bundle = new Bundle();
        zza(bundle, i);
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString(str2, str3);
        }
        if (i == 6 || i == 7 || i == 2) {
            bundle.putLong("_el", (long) i2);
        }
        this.zzacv.zzgl();
        this.zzacv.zzfy().logEvent("auto", "_err", bundle);
    }

    final boolean zza(String str, int i, String str2) {
        if (str2 == null) {
            zzgi().zziv().zzg("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.codePointCount(0, str2.length()) <= i) {
            return true;
        } else {
            zzgi().zziv().zzd("Name is too long. Type, maximum supported length, name", str, Integer.valueOf(i), str2);
            return false;
        }
    }

    final boolean zza(String str, String[] strArr, String str2) {
        if (str2 == null) {
            zzgi().zziv().zzg("Name is required and can't be null. Type", str);
            return false;
        }
        Object obj;
        Preconditions.checkNotNull(str2);
        for (String startsWith : zzasx) {
            if (str2.startsWith(startsWith)) {
                obj = 1;
                break;
            }
        }
        obj = null;
        if (obj != null) {
            zzgi().zziv().zze("Name starts with reserved prefix. Type, name", str, str2);
            return false;
        }
        if (strArr != null) {
            Object obj2;
            Preconditions.checkNotNull(strArr);
            for (String zzs : strArr) {
                if (zzs(str2, zzs)) {
                    obj2 = 1;
                    break;
                }
            }
            obj2 = null;
            if (obj2 != null) {
                zzgi().zziv().zze("Name is reserved. Type, name", str, str2);
                return false;
            }
        }
        return true;
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    final int zzch(String str) {
        return !zzr(NotificationCompat.CATEGORY_EVENT, str) ? 2 : !zza(NotificationCompat.CATEGORY_EVENT, Event.zzacw, str) ? 13 : !zza(NotificationCompat.CATEGORY_EVENT, 40, str) ? 2 : 0;
    }

    public final int zzci(String str) {
        return !zzq("user property", str) ? 6 : !zza("user property", UserProperty.zzada, str) ? 15 : !zza("user property", 24, str) ? 6 : 0;
    }

    final int zzcj(String str) {
        return !zzr("user property", str) ? 6 : !zza("user property", UserProperty.zzada, str) ? 15 : !zza("user property", 24, str) ? 6 : 0;
    }

    final boolean zzck(String str) {
        if (TextUtils.isEmpty(str)) {
            zzgi().zziv().log("Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI");
            return false;
        }
        Preconditions.checkNotNull(str);
        if (str.matches("^(1:\\d+:android:[a-f0-9]+|ca-app-pub-.*)$")) {
            return true;
        }
        zzgi().zziv().zzg("Invalid google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI. provided id", str);
        return false;
    }

    final boolean zzcn(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String zzhs = zzgk().zzhs();
        zzgl();
        return zzhs.equals(str);
    }

    @WorkerThread
    final long zzd(Context context, String str) {
        zzab();
        Preconditions.checkNotNull(context);
        Preconditions.checkNotEmpty(str);
        PackageManager packageManager = context.getPackageManager();
        MessageDigest messageDigest = getMessageDigest();
        if (messageDigest == null) {
            zzgi().zziv().log("Could not get MD5 instance");
            return -1;
        }
        if (packageManager != null) {
            try {
                if (!zze(context, str)) {
                    PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(getContext().getPackageName(), 64);
                    if (packageInfo.signatures != null && packageInfo.signatures.length > 0) {
                        return zzc(messageDigest.digest(packageInfo.signatures[0].toByteArray()));
                    }
                    zzgi().zziy().log("Could not get signatures");
                    return -1;
                }
            } catch (NameNotFoundException e) {
                zzgi().zziv().zzg("Package name not found", e);
            }
        }
        return 0;
    }

    final Bundle zzd(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                Object zzh = zzh(str, bundle.get(str));
                if (zzh == null) {
                    zzgi().zziy().zzg("Param value can't be null", zzgf().zzbn(str));
                } else {
                    zza(bundle2, str, zzh);
                }
            }
        }
        return bundle2;
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
        return true;
    }

    @WorkerThread
    protected final void zzgo() {
        zzab();
        SecureRandom secureRandom = new SecureRandom();
        long nextLong = secureRandom.nextLong();
        if (nextLong == 0) {
            nextLong = secureRandom.nextLong();
            if (nextLong == 0) {
                zzgi().zziy().log("Utils falling back to Random for random id");
            }
        }
        this.zzasz.set(nextLong);
    }

    final Object zzh(String str, Object obj) {
        boolean z;
        int i = 256;
        if ("_ev".equals(str)) {
            z = true;
        } else {
            if (!zzcm(str)) {
                i = 100;
            }
            z = false;
        }
        return zza(i, obj, z);
    }

    final int zzi(String str, Object obj) {
        return "_ldl".equals(str) ? zza("user property referrer", str, zzcl(str), obj, false) : zza("user property", str, zzcl(str), obj, false) ? 0 : 7;
    }

    final Object zzj(String str, Object obj) {
        int zzcl;
        boolean z;
        if ("_ldl".equals(str)) {
            zzcl = zzcl(str);
            z = true;
        } else {
            zzcl = zzcl(str);
            z = false;
        }
        return zza(zzcl, obj, z);
    }

    public final long zzln() {
        if (this.zzasz.get() == 0) {
            long j;
            synchronized (this.zzasz) {
                long nextLong = new Random(System.nanoTime() ^ zzbt().currentTimeMillis()).nextLong();
                int i = this.zzadj + 1;
                this.zzadj = i;
                j = nextLong + ((long) i);
            }
            return j;
        }
        synchronized (this.zzasz) {
            this.zzasz.compareAndSet(-1, 1);
            nextLong = this.zzasz.getAndIncrement();
        }
        return nextLong;
    }

    @WorkerThread
    final SecureRandom zzlo() {
        zzab();
        if (this.zzasy == null) {
            this.zzasy = new SecureRandom();
        }
        return this.zzasy;
    }

    public final int zzlp() {
        if (this.zzata == null) {
            this.zzata = Integer.valueOf(GoogleApiAvailabilityLight.getInstance().getApkVersion(getContext()) / 1000);
        }
        return this.zzata.intValue();
    }

    final boolean zzq(String str, String str2) {
        if (str2 == null) {
            zzgi().zziv().zzg("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            zzgi().zziv().zzg("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (Character.isLetter(codePointAt)) {
                int length = str2.length();
                codePointAt = Character.charCount(codePointAt);
                while (codePointAt < length) {
                    int codePointAt2 = str2.codePointAt(codePointAt);
                    if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                        codePointAt += Character.charCount(codePointAt2);
                    } else {
                        zzgi().zziv().zze("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                        return false;
                    }
                }
                return true;
            }
            zzgi().zziv().zze("Name must start with a letter. Type, name", str, str2);
            return false;
        }
    }

    @WorkerThread
    final boolean zzx(String str) {
        zzab();
        if (Wrappers.packageManager(getContext()).checkCallingOrSelfPermission(str) == 0) {
            return true;
        }
        zzgi().zzjb().zzg("Permission not granted", str);
        return false;
    }
}
