package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class zzws {
    private static final Uri CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
    private static final Uri zzbol = Uri.parse("content://com.google.android.gsf.gservices/prefix");
    public static final Pattern zzbom = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
    public static final Pattern zzbon = Pattern.compile("^(0|false|f|off|no|n)$", 2);
    private static final AtomicBoolean zzboo = new AtomicBoolean();
    private static HashMap<String, String> zzbop;
    private static final HashMap<String, Boolean> zzboq = new HashMap();
    private static final HashMap<String, Integer> zzbor = new HashMap();
    private static final HashMap<String, Long> zzbos = new HashMap();
    private static final HashMap<String, Float> zzbot = new HashMap();
    private static Object zzbou;
    private static boolean zzbov;
    private static String[] zzbow = new String[0];

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <T> T zza(java.util.HashMap<java.lang.String, T> r2, java.lang.String r3, T r4) {
        /*
        r0 = com.google.android.gms.internal.measurement.zzws.class;
        monitor-enter(r0);
        r1 = r2.containsKey(r3);	 Catch:{ all -> 0x0016 }
        if (r1 == 0) goto L_0x0013;
    L_0x0009:
        r2 = r2.get(r3);	 Catch:{ all -> 0x0016 }
        if (r2 == 0) goto L_0x0010;
    L_0x000f:
        goto L_0x0011;
    L_0x0010:
        r2 = r4;
    L_0x0011:
        monitor-exit(r0);	 Catch:{ all -> 0x0016 }
        return r2;
    L_0x0013:
        monitor-exit(r0);	 Catch:{ all -> 0x0016 }
        r2 = 0;
        return r2;
    L_0x0016:
        r2 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0016 }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzws.zza(java.util.HashMap, java.lang.String, java.lang.Object):T");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String zza(android.content.ContentResolver r13, java.lang.String r14, java.lang.String r15) {
        /*
        r15 = com.google.android.gms.internal.measurement.zzws.class;
        monitor-enter(r15);
        zza(r13);	 Catch:{ all -> 0x00aa }
        r0 = zzbou;	 Catch:{ all -> 0x00aa }
        r1 = zzbop;	 Catch:{ all -> 0x00aa }
        r1 = r1.containsKey(r14);	 Catch:{ all -> 0x00aa }
        r2 = 0;
        if (r1 == 0) goto L_0x001f;
    L_0x0011:
        r13 = zzbop;	 Catch:{ all -> 0x00aa }
        r13 = r13.get(r14);	 Catch:{ all -> 0x00aa }
        r13 = (java.lang.String) r13;	 Catch:{ all -> 0x00aa }
        if (r13 == 0) goto L_0x001c;
    L_0x001b:
        goto L_0x001d;
    L_0x001c:
        r13 = r2;
    L_0x001d:
        monitor-exit(r15);	 Catch:{ all -> 0x00aa }
        return r13;
    L_0x001f:
        r1 = zzbow;	 Catch:{ all -> 0x00aa }
        r3 = r1.length;	 Catch:{ all -> 0x00aa }
        r4 = 0;
        r5 = 0;
    L_0x0024:
        r6 = 1;
        if (r5 >= r3) goto L_0x0063;
    L_0x0027:
        r7 = r1[r5];	 Catch:{ all -> 0x00aa }
        r7 = r14.startsWith(r7);	 Catch:{ all -> 0x00aa }
        if (r7 == 0) goto L_0x0060;
    L_0x002f:
        r0 = zzbov;	 Catch:{ all -> 0x00aa }
        if (r0 == 0) goto L_0x003b;
    L_0x0033:
        r0 = zzbop;	 Catch:{ all -> 0x00aa }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x00aa }
        if (r0 == 0) goto L_0x005e;
    L_0x003b:
        r0 = zzbow;	 Catch:{ all -> 0x00aa }
        r1 = zzbop;	 Catch:{ all -> 0x00aa }
        r13 = zza(r13, r0);	 Catch:{ all -> 0x00aa }
        r1.putAll(r13);	 Catch:{ all -> 0x00aa }
        zzbov = r6;	 Catch:{ all -> 0x00aa }
        r13 = zzbop;	 Catch:{ all -> 0x00aa }
        r13 = r13.containsKey(r14);	 Catch:{ all -> 0x00aa }
        if (r13 == 0) goto L_0x005e;
    L_0x0050:
        r13 = zzbop;	 Catch:{ all -> 0x00aa }
        r13 = r13.get(r14);	 Catch:{ all -> 0x00aa }
        r13 = (java.lang.String) r13;	 Catch:{ all -> 0x00aa }
        if (r13 == 0) goto L_0x005b;
    L_0x005a:
        goto L_0x005c;
    L_0x005b:
        r13 = r2;
    L_0x005c:
        monitor-exit(r15);	 Catch:{ all -> 0x00aa }
        return r13;
    L_0x005e:
        monitor-exit(r15);	 Catch:{ all -> 0x00aa }
        return r2;
    L_0x0060:
        r5 = r5 + 1;
        goto L_0x0024;
    L_0x0063:
        monitor-exit(r15);	 Catch:{ all -> 0x00aa }
        r8 = CONTENT_URI;
        r9 = 0;
        r10 = 0;
        r11 = new java.lang.String[r6];
        r11[r4] = r14;
        r12 = 0;
        r7 = r13;
        r13 = r7.query(r8, r9, r10, r11, r12);
        if (r13 != 0) goto L_0x007a;
    L_0x0074:
        if (r13 == 0) goto L_0x0079;
    L_0x0076:
        r13.close();
    L_0x0079:
        return r2;
    L_0x007a:
        r15 = r13.moveToFirst();	 Catch:{ all -> 0x00a3 }
        if (r15 != 0) goto L_0x0089;
    L_0x0080:
        zza(r0, r14, r2);	 Catch:{ all -> 0x00a3 }
        if (r13 == 0) goto L_0x0088;
    L_0x0085:
        r13.close();
    L_0x0088:
        return r2;
    L_0x0089:
        r15 = r13.getString(r6);	 Catch:{ all -> 0x00a3 }
        if (r15 == 0) goto L_0x0096;
    L_0x008f:
        r1 = r15.equals(r2);	 Catch:{ all -> 0x00a3 }
        if (r1 == 0) goto L_0x0096;
    L_0x0095:
        r15 = r2;
    L_0x0096:
        zza(r0, r14, r15);	 Catch:{ all -> 0x00a3 }
        if (r15 == 0) goto L_0x009c;
    L_0x009b:
        goto L_0x009d;
    L_0x009c:
        r15 = r2;
    L_0x009d:
        if (r13 == 0) goto L_0x00a2;
    L_0x009f:
        r13.close();
    L_0x00a2:
        return r15;
    L_0x00a3:
        r14 = move-exception;
        if (r13 == 0) goto L_0x00a9;
    L_0x00a6:
        r13.close();
    L_0x00a9:
        throw r14;
    L_0x00aa:
        r13 = move-exception;
        monitor-exit(r15);	 Catch:{ all -> 0x00aa }
        throw r13;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzws.zza(android.content.ContentResolver, java.lang.String, java.lang.String):java.lang.String");
    }

    private static Map<String, String> zza(ContentResolver contentResolver, String... strArr) {
        Cursor query = contentResolver.query(zzbol, null, null, strArr, null);
        Map<String, String> treeMap = new TreeMap();
        if (query == null) {
            return treeMap;
        }
        while (query.moveToNext()) {
            try {
                treeMap.put(query.getString(0), query.getString(1));
            } finally {
                query.close();
            }
        }
        return treeMap;
    }

    private static void zza(ContentResolver contentResolver) {
        if (zzbop == null) {
            zzboo.set(false);
            zzbop = new HashMap();
            zzbou = new Object();
            zzbov = false;
            contentResolver.registerContentObserver(CONTENT_URI, true, new zzwt(null));
            return;
        }
        if (zzboo.getAndSet(false)) {
            zzbop.clear();
            zzboq.clear();
            zzbor.clear();
            zzbos.clear();
            zzbot.clear();
            zzbou = new Object();
            zzbov = false;
        }
    }

    private static void zza(Object obj, String str, String str2) {
        synchronized (zzws.class) {
            if (obj == zzbou) {
                zzbop.put(str, str2);
            }
        }
    }

    private static <T> void zza(Object obj, HashMap<String, T> hashMap, String str, T t) {
        synchronized (zzws.class) {
            if (obj == zzbou) {
                hashMap.put(str, t);
                zzbop.remove(str);
            }
        }
    }

    public static boolean zza(ContentResolver contentResolver, String str, boolean z) {
        Object zzb = zzb(contentResolver);
        Object obj = (Boolean) zza(zzboq, str, Boolean.valueOf(z));
        if (obj != null) {
            return obj.booleanValue();
        }
        Object zza = zza(contentResolver, str, null);
        if (zza != null) {
            if (!zza.equals("")) {
                if (zzbom.matcher(zza).matches()) {
                    obj = Boolean.valueOf(true);
                    z = true;
                } else if (zzbon.matcher(zza).matches()) {
                    obj = Boolean.valueOf(false);
                    z = false;
                } else {
                    StringBuilder stringBuilder = new StringBuilder("attempt to read gservices key ");
                    stringBuilder.append(str);
                    stringBuilder.append(" (value \"");
                    stringBuilder.append(zza);
                    stringBuilder.append("\") as boolean");
                    Log.w("Gservices", stringBuilder.toString());
                }
            }
        }
        zza(zzb, zzboq, str, obj);
        return z;
    }

    private static Object zzb(ContentResolver contentResolver) {
        Object obj;
        synchronized (zzws.class) {
            zza(contentResolver);
            obj = zzbou;
        }
        return obj;
    }
}
