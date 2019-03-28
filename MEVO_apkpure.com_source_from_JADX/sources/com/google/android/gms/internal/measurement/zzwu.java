package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.net.Uri;
import android.support.annotation.GuardedBy;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class zzwu {
    private static final ConcurrentHashMap<Uri, zzwu> zzbox = new ConcurrentHashMap();
    private static final String[] zzbpe = new String[]{"key", Param.VALUE};
    private final Uri uri;
    private final ContentResolver zzboy;
    private final ContentObserver zzboz;
    private final Object zzbpa = new Object();
    private volatile Map<String, String> zzbpb;
    private final Object zzbpc = new Object();
    @GuardedBy("listenersLock")
    private final List<zzww> zzbpd = new ArrayList();

    private zzwu(ContentResolver contentResolver, Uri uri) {
        this.zzboy = contentResolver;
        this.uri = uri;
        this.zzboz = new zzwv(this, null);
    }

    public static zzwu zza(ContentResolver contentResolver, Uri uri) {
        zzwu zzwu = (zzwu) zzbox.get(uri);
        if (zzwu == null) {
            zzwu = new zzwu(contentResolver, uri);
            zzwu zzwu2 = (zzwu) zzbox.putIfAbsent(uri, zzwu);
            if (zzwu2 == null) {
                zzwu.zzboy.registerContentObserver(zzwu.uri, false, zzwu.zzboz);
                return zzwu;
            }
            zzwu = zzwu2;
        }
        return zzwu;
    }

    private final java.util.Map<java.lang.String, java.lang.String> zzsj() {
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r7 = this;
        r0 = new java.util.HashMap;	 Catch:{ SecurityException -> 0x0032, SecurityException -> 0x0032 }
        r0.<init>();	 Catch:{ SecurityException -> 0x0032, SecurityException -> 0x0032 }
        r1 = r7.zzboy;	 Catch:{ SecurityException -> 0x0032, SecurityException -> 0x0032 }
        r2 = r7.uri;	 Catch:{ SecurityException -> 0x0032, SecurityException -> 0x0032 }
        r3 = zzbpe;	 Catch:{ SecurityException -> 0x0032, SecurityException -> 0x0032 }
        r4 = 0;	 Catch:{ SecurityException -> 0x0032, SecurityException -> 0x0032 }
        r5 = 0;	 Catch:{ SecurityException -> 0x0032, SecurityException -> 0x0032 }
        r6 = 0;	 Catch:{ SecurityException -> 0x0032, SecurityException -> 0x0032 }
        r1 = r1.query(r2, r3, r4, r5, r6);	 Catch:{ SecurityException -> 0x0032, SecurityException -> 0x0032 }
        if (r1 == 0) goto L_0x0031;
    L_0x0014:
        r2 = r1.moveToNext();	 Catch:{ all -> 0x002c }
        if (r2 == 0) goto L_0x0028;	 Catch:{ all -> 0x002c }
    L_0x001a:
        r2 = 0;	 Catch:{ all -> 0x002c }
        r2 = r1.getString(r2);	 Catch:{ all -> 0x002c }
        r3 = 1;	 Catch:{ all -> 0x002c }
        r3 = r1.getString(r3);	 Catch:{ all -> 0x002c }
        r0.put(r2, r3);	 Catch:{ all -> 0x002c }
        goto L_0x0014;
    L_0x0028:
        r1.close();	 Catch:{ SecurityException -> 0x0032, SecurityException -> 0x0032 }
        return r0;	 Catch:{ SecurityException -> 0x0032, SecurityException -> 0x0032 }
    L_0x002c:
        r0 = move-exception;	 Catch:{ SecurityException -> 0x0032, SecurityException -> 0x0032 }
        r1.close();	 Catch:{ SecurityException -> 0x0032, SecurityException -> 0x0032 }
        throw r0;	 Catch:{ SecurityException -> 0x0032, SecurityException -> 0x0032 }
    L_0x0031:
        return r0;
    L_0x0032:
        r0 = "ConfigurationContentLoader";
        r1 = "PhenotypeFlag unable to load ContentProvider, using default values";
        android.util.Log.e(r0, r1);
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzwu.zzsj():java.util.Map<java.lang.String, java.lang.String>");
    }

    private final void zzsk() {
        synchronized (this.zzbpc) {
            for (zzww zzsl : this.zzbpd) {
                zzsl.zzsl();
            }
        }
    }

    public final Map<String, String> zzsh() {
        Map<String, String> zzsj = zzwx.zzd("gms:phenotype:phenotype_flag:debug_disable_caching", false) ? zzsj() : this.zzbpb;
        if (zzsj == null) {
            synchronized (this.zzbpa) {
                zzsj = this.zzbpb;
                if (zzsj == null) {
                    zzsj = zzsj();
                    this.zzbpb = zzsj;
                }
            }
        }
        return zzsj != null ? zzsj : Collections.emptyMap();
    }

    public final void zzsi() {
        synchronized (this.zzbpa) {
            this.zzbpb = null;
        }
    }
}
