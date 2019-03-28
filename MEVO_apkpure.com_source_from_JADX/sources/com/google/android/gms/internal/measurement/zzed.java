package com.google.android.gms.internal.measurement;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement.Event;
import com.google.android.gms.measurement.AppMeasurement.Param;
import com.google.android.gms.measurement.AppMeasurement.UserProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

final class zzed extends zzjs {
    zzed(zzjt zzjt) {
        super(zzjt);
    }

    private final java.lang.Boolean zza(double r2, com.google.android.gms.internal.measurement.zzkj r4) {
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
        r1 = this;
        r0 = new java.math.BigDecimal;	 Catch:{ NumberFormatException -> 0x000e }
        r0.<init>(r2);	 Catch:{ NumberFormatException -> 0x000e }
        r2 = java.lang.Math.ulp(r2);	 Catch:{ NumberFormatException -> 0x000e }
        r2 = zza(r0, r4, r2);	 Catch:{ NumberFormatException -> 0x000e }
        return r2;
    L_0x000e:
        r2 = 0;
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzed.zza(double, com.google.android.gms.internal.measurement.zzkj):java.lang.Boolean");
    }

    private final java.lang.Boolean zza(long r2, com.google.android.gms.internal.measurement.zzkj r4) {
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
        r1 = this;
        r0 = new java.math.BigDecimal;	 Catch:{ NumberFormatException -> 0x000c }
        r0.<init>(r2);	 Catch:{ NumberFormatException -> 0x000c }
        r2 = 0;	 Catch:{ NumberFormatException -> 0x000c }
        r2 = zza(r0, r4, r2);	 Catch:{ NumberFormatException -> 0x000c }
        return r2;
    L_0x000c:
        r2 = 0;
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzed.zza(long, com.google.android.gms.internal.measurement.zzkj):java.lang.Boolean");
    }

    private final Boolean zza(zzkh zzkh, String str, zzks[] zzksArr, long j) {
        if (zzkh.zzato != null) {
            Boolean zza = zza(j, zzkh.zzato);
            if (zza == null) {
                return null;
            }
            if (!zza.booleanValue()) {
                return Boolean.valueOf(false);
            }
        }
        Set hashSet = new HashSet();
        for (zzki zzki : zzkh.zzatm) {
            if (TextUtils.isEmpty(zzki.zzatt)) {
                zzgi().zziy().zzg("null or empty param name in filter. event", zzgf().zzbm(str));
                return null;
            }
            hashSet.add(zzki.zzatt);
        }
        Map arrayMap = new ArrayMap();
        for (zzks zzks : zzksArr) {
            if (hashSet.contains(zzks.name)) {
                Object obj;
                Object obj2;
                if (zzks.zzave != null) {
                    obj = zzks.name;
                    obj2 = zzks.zzave;
                } else if (zzks.zzasw != null) {
                    obj = zzks.name;
                    obj2 = zzks.zzasw;
                } else if (zzks.zzale != null) {
                    obj = zzks.name;
                    obj2 = zzks.zzale;
                } else {
                    zzgi().zziy().zze("Unknown value for param. event, param", zzgf().zzbm(str), zzgf().zzbn(zzks.name));
                    return null;
                }
                arrayMap.put(obj, obj2);
            }
        }
        for (zzki zzki2 : zzkh.zzatm) {
            boolean equals = Boolean.TRUE.equals(zzki2.zzats);
            String str2 = zzki2.zzatt;
            if (TextUtils.isEmpty(str2)) {
                zzgi().zziy().zzg("Event has empty param name. event", zzgf().zzbm(str));
                return null;
            }
            Object obj3 = arrayMap.get(str2);
            Boolean zza2;
            if (obj3 instanceof Long) {
                if (zzki2.zzatr == null) {
                    zzgi().zziy().zze("No number filter for long param. event, param", zzgf().zzbm(str), zzgf().zzbn(str2));
                    return null;
                }
                zza2 = zza(((Long) obj3).longValue(), zzki2.zzatr);
                if (zza2 == null) {
                    return null;
                }
                if (((1 ^ zza2.booleanValue()) ^ equals) != 0) {
                    return Boolean.valueOf(false);
                }
            } else if (obj3 instanceof Double) {
                if (zzki2.zzatr == null) {
                    zzgi().zziy().zze("No number filter for double param. event, param", zzgf().zzbm(str), zzgf().zzbn(str2));
                    return null;
                }
                zza2 = zza(((Double) obj3).doubleValue(), zzki2.zzatr);
                if (zza2 == null) {
                    return null;
                }
                if (((1 ^ zza2.booleanValue()) ^ equals) != 0) {
                    return Boolean.valueOf(false);
                }
            } else if (obj3 instanceof String) {
                if (zzki2.zzatq != null) {
                    zza2 = zza((String) obj3, zzki2.zzatq);
                } else if (zzki2.zzatr != null) {
                    String str3 = (String) obj3;
                    if (zzjz.zzcf(str3)) {
                        zza2 = zza(str3, zzki2.zzatr);
                    } else {
                        zzgi().zziy().zze("Invalid param value for number filter. event, param", zzgf().zzbm(str), zzgf().zzbn(str2));
                        return null;
                    }
                } else {
                    zzgi().zziy().zze("No filter for String param. event, param", zzgf().zzbm(str), zzgf().zzbn(str2));
                    return null;
                }
                if (zza2 == null) {
                    return null;
                }
                if (((1 ^ zza2.booleanValue()) ^ equals) != 0) {
                    return Boolean.valueOf(false);
                }
            } else if (obj3 == null) {
                zzgi().zzjc().zze("Missing param for filter. event, param", zzgf().zzbm(str), zzgf().zzbn(str2));
                return Boolean.valueOf(false);
            } else {
                zzgi().zziy().zze("Unknown param type. event, param", zzgf().zzbm(str), zzgf().zzbn(str2));
                return null;
            }
        }
        return Boolean.valueOf(true);
    }

    private final Boolean zza(zzkk zzkk, zzkx zzkx) {
        zzfk zziy;
        String str;
        zzki zzki = zzkk.zzaud;
        if (zzki == null) {
            zziy = zzgi().zziy();
            str = "Missing property filter. property";
        } else {
            Boolean zza;
            boolean equals = Boolean.TRUE.equals(zzki.zzats);
            if (zzkx.zzave != null) {
                if (zzki.zzatr == null) {
                    zziy = zzgi().zziy();
                    str = "No number filter for long property. property";
                } else {
                    zza = zza(zzkx.zzave.longValue(), zzki.zzatr);
                }
            } else if (zzkx.zzasw != null) {
                if (zzki.zzatr == null) {
                    zziy = zzgi().zziy();
                    str = "No number filter for double property. property";
                } else {
                    zza = zza(zzkx.zzasw.doubleValue(), zzki.zzatr);
                }
            } else if (zzkx.zzale == null) {
                zziy = zzgi().zziy();
                str = "User property has no value, property";
            } else if (zzki.zzatq != null) {
                zza = zza(zzkx.zzale, zzki.zzatq);
            } else if (zzki.zzatr == null) {
                zzgi().zziy().zzg("No string or number filter defined. property", zzgf().zzbo(zzkx.name));
                return null;
            } else if (zzjz.zzcf(zzkx.zzale)) {
                zza = zza(zzkx.zzale, zzki.zzatr);
            } else {
                zzgi().zziy().zze("Invalid user property value for Numeric number filter. property, value", zzgf().zzbo(zzkx.name), zzkx.zzale);
                return null;
            }
            return zza(zza, equals);
        }
        zziy.zzg(str, zzgf().zzbo(zzkx.name));
        return null;
    }

    @VisibleForTesting
    private static Boolean zza(Boolean bool, boolean z) {
        return bool == null ? null : Boolean.valueOf(bool.booleanValue() ^ z);
    }

    private final java.lang.Boolean zza(java.lang.String r3, int r4, boolean r5, java.lang.String r6, java.util.List<java.lang.String> r7, java.lang.String r8) {
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
        r2 = this;
        r0 = 0;
        if (r3 != 0) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r1 = 6;
        if (r4 != r1) goto L_0x0010;
    L_0x0007:
        if (r7 == 0) goto L_0x000f;
    L_0x0009:
        r1 = r7.size();
        if (r1 != 0) goto L_0x0013;
    L_0x000f:
        return r0;
    L_0x0010:
        if (r6 != 0) goto L_0x0013;
    L_0x0012:
        return r0;
    L_0x0013:
        if (r5 != 0) goto L_0x001f;
    L_0x0015:
        r1 = 1;
        if (r4 != r1) goto L_0x0019;
    L_0x0018:
        goto L_0x001f;
    L_0x0019:
        r1 = java.util.Locale.ENGLISH;
        r3 = r3.toUpperCase(r1);
    L_0x001f:
        switch(r4) {
            case 1: goto L_0x0040;
            case 2: goto L_0x003b;
            case 3: goto L_0x0036;
            case 4: goto L_0x0031;
            case 5: goto L_0x002c;
            case 6: goto L_0x0023;
            default: goto L_0x0022;
        };
    L_0x0022:
        return r0;
    L_0x0023:
        r3 = r7.contains(r3);
    L_0x0027:
        r3 = java.lang.Boolean.valueOf(r3);
        return r3;
    L_0x002c:
        r3 = r3.equals(r6);
        goto L_0x0027;
    L_0x0031:
        r3 = r3.contains(r6);
        goto L_0x0027;
    L_0x0036:
        r3 = r3.endsWith(r6);
        goto L_0x0027;
    L_0x003b:
        r3 = r3.startsWith(r6);
        goto L_0x0027;
    L_0x0040:
        if (r5 == 0) goto L_0x0044;
    L_0x0042:
        r4 = 0;
        goto L_0x0046;
    L_0x0044:
        r4 = 66;
    L_0x0046:
        r4 = java.util.regex.Pattern.compile(r8, r4);	 Catch:{ PatternSyntaxException -> 0x0057 }
        r3 = r4.matcher(r3);	 Catch:{ PatternSyntaxException -> 0x0057 }
        r3 = r3.matches();	 Catch:{ PatternSyntaxException -> 0x0057 }
        r3 = java.lang.Boolean.valueOf(r3);	 Catch:{ PatternSyntaxException -> 0x0057 }
        return r3;
    L_0x0057:
        r3 = r2.zzgi();
        r3 = r3.zziy();
        r4 = "Invalid regular expression in REGEXP audience filter. expression";
        r3.zzg(r4, r8);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzed.zza(java.lang.String, int, boolean, java.lang.String, java.util.List, java.lang.String):java.lang.Boolean");
    }

    private final java.lang.Boolean zza(java.lang.String r5, com.google.android.gms.internal.measurement.zzkj r6) {
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
        r4 = this;
        r0 = com.google.android.gms.internal.measurement.zzjz.zzcf(r5);
        r1 = 0;
        if (r0 != 0) goto L_0x0008;
    L_0x0007:
        return r1;
    L_0x0008:
        r0 = new java.math.BigDecimal;	 Catch:{ NumberFormatException -> 0x0014 }
        r0.<init>(r5);	 Catch:{ NumberFormatException -> 0x0014 }
        r2 = 0;	 Catch:{ NumberFormatException -> 0x0014 }
        r5 = zza(r0, r6, r2);	 Catch:{ NumberFormatException -> 0x0014 }
        return r5;
    L_0x0014:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzed.zza(java.lang.String, com.google.android.gms.internal.measurement.zzkj):java.lang.Boolean");
    }

    @VisibleForTesting
    private final Boolean zza(String str, zzkl zzkl) {
        Preconditions.checkNotNull(zzkl);
        if (str == null || zzkl.zzaue == null || zzkl.zzaue.intValue() == 0) {
            return null;
        }
        String toUpperCase;
        String str2;
        List list;
        String[] strArr;
        List arrayList;
        if (zzkl.zzaue.intValue() == 6) {
            if (zzkl.zzauh == null || zzkl.zzauh.length == 0) {
                return null;
            }
        } else if (zzkl.zzauf == null) {
            return null;
        }
        int intValue = zzkl.zzaue.intValue();
        boolean z = zzkl.zzaug != null && zzkl.zzaug.booleanValue();
        if (!(z || intValue == 1)) {
            if (intValue != 6) {
                toUpperCase = zzkl.zzauf.toUpperCase(Locale.ENGLISH);
                str2 = toUpperCase;
                if (zzkl.zzauh != null) {
                    list = null;
                } else {
                    strArr = zzkl.zzauh;
                    if (z) {
                        arrayList = new ArrayList();
                        for (String toUpperCase2 : strArr) {
                            arrayList.add(toUpperCase2.toUpperCase(Locale.ENGLISH));
                        }
                        list = arrayList;
                    } else {
                        list = Arrays.asList(strArr);
                    }
                }
                return zza(str, intValue, z, str2, list, intValue != 1 ? str2 : null);
            }
        }
        toUpperCase = zzkl.zzauf;
        str2 = toUpperCase;
        if (zzkl.zzauh != null) {
            strArr = zzkl.zzauh;
            if (z) {
                arrayList = new ArrayList();
                while (r3 < r2) {
                    arrayList.add(toUpperCase2.toUpperCase(Locale.ENGLISH));
                }
                list = arrayList;
            } else {
                list = Arrays.asList(strArr);
            }
        } else {
            list = null;
        }
        if (intValue != 1) {
        }
        return zza(str, intValue, z, str2, list, intValue != 1 ? str2 : null);
    }

    @com.google.android.gms.common.util.VisibleForTesting
    private static java.lang.Boolean zza(java.math.BigDecimal r7, com.google.android.gms.internal.measurement.zzkj r8, double r9) {
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
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r8);
        r0 = r8.zzatw;
        r1 = 0;
        if (r0 == 0) goto L_0x00ec;
    L_0x0008:
        r0 = r8.zzatw;
        r0 = r0.intValue();
        if (r0 != 0) goto L_0x0011;
    L_0x0010:
        return r1;
    L_0x0011:
        r0 = r8.zzatw;
        r0 = r0.intValue();
        r2 = 4;
        if (r0 != r2) goto L_0x0023;
    L_0x001a:
        r0 = r8.zzatz;
        if (r0 == 0) goto L_0x0022;
    L_0x001e:
        r0 = r8.zzaua;
        if (r0 != 0) goto L_0x0028;
    L_0x0022:
        return r1;
    L_0x0023:
        r0 = r8.zzaty;
        if (r0 != 0) goto L_0x0028;
    L_0x0027:
        return r1;
    L_0x0028:
        r0 = r8.zzatw;
        r0 = r0.intValue();
        r3 = r8.zzatw;
        r3 = r3.intValue();
        if (r3 != r2) goto L_0x0059;
    L_0x0036:
        r3 = r8.zzatz;
        r3 = com.google.android.gms.internal.measurement.zzjz.zzcf(r3);
        if (r3 == 0) goto L_0x0058;
    L_0x003e:
        r3 = r8.zzaua;
        r3 = com.google.android.gms.internal.measurement.zzjz.zzcf(r3);
        if (r3 != 0) goto L_0x0047;
    L_0x0046:
        return r1;
    L_0x0047:
        r3 = new java.math.BigDecimal;	 Catch:{ NumberFormatException -> 0x0058 }
        r4 = r8.zzatz;	 Catch:{ NumberFormatException -> 0x0058 }
        r3.<init>(r4);	 Catch:{ NumberFormatException -> 0x0058 }
        r4 = new java.math.BigDecimal;	 Catch:{ NumberFormatException -> 0x0058 }
        r8 = r8.zzaua;	 Catch:{ NumberFormatException -> 0x0058 }
        r4.<init>(r8);	 Catch:{ NumberFormatException -> 0x0058 }
        r8 = r3;
        r3 = r1;
        goto L_0x006b;
    L_0x0058:
        return r1;
    L_0x0059:
        r3 = r8.zzaty;
        r3 = com.google.android.gms.internal.measurement.zzjz.zzcf(r3);
        if (r3 != 0) goto L_0x0062;
    L_0x0061:
        return r1;
    L_0x0062:
        r3 = new java.math.BigDecimal;	 Catch:{ NumberFormatException -> 0x00ec }
        r8 = r8.zzaty;	 Catch:{ NumberFormatException -> 0x00ec }
        r3.<init>(r8);	 Catch:{ NumberFormatException -> 0x00ec }
        r8 = r1;
        r4 = r8;
    L_0x006b:
        if (r0 != r2) goto L_0x0070;
    L_0x006d:
        if (r8 != 0) goto L_0x0072;
    L_0x006f:
        return r1;
    L_0x0070:
        if (r3 == 0) goto L_0x00ec;
    L_0x0072:
        r2 = -1;
        r5 = 0;
        r6 = 1;
        switch(r0) {
            case 1: goto L_0x00e0;
            case 2: goto L_0x00d4;
            case 3: goto L_0x008b;
            case 4: goto L_0x0079;
            default: goto L_0x0078;
        };
    L_0x0078:
        return r1;
    L_0x0079:
        r8 = r7.compareTo(r8);
        if (r8 == r2) goto L_0x0086;
    L_0x007f:
        r7 = r7.compareTo(r4);
        if (r7 == r6) goto L_0x0086;
    L_0x0085:
        r5 = 1;
    L_0x0086:
        r7 = java.lang.Boolean.valueOf(r5);
        return r7;
    L_0x008b:
        r0 = 0;
        r8 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1));
        if (r8 == 0) goto L_0x00c8;
    L_0x0091:
        r8 = new java.math.BigDecimal;
        r8.<init>(r9);
        r0 = new java.math.BigDecimal;
        r1 = 2;
        r0.<init>(r1);
        r8 = r8.multiply(r0);
        r8 = r3.subtract(r8);
        r8 = r7.compareTo(r8);
        if (r8 != r6) goto L_0x00c3;
    L_0x00aa:
        r8 = new java.math.BigDecimal;
        r8.<init>(r9);
        r9 = new java.math.BigDecimal;
        r9.<init>(r1);
        r8 = r8.multiply(r9);
        r8 = r3.add(r8);
        r7 = r7.compareTo(r8);
        if (r7 != r2) goto L_0x00c3;
    L_0x00c2:
        r5 = 1;
    L_0x00c3:
        r7 = java.lang.Boolean.valueOf(r5);
        return r7;
    L_0x00c8:
        r7 = r7.compareTo(r3);
        if (r7 != 0) goto L_0x00cf;
    L_0x00ce:
        r5 = 1;
    L_0x00cf:
        r7 = java.lang.Boolean.valueOf(r5);
        return r7;
    L_0x00d4:
        r7 = r7.compareTo(r3);
        if (r7 != r6) goto L_0x00db;
    L_0x00da:
        r5 = 1;
    L_0x00db:
        r7 = java.lang.Boolean.valueOf(r5);
        return r7;
    L_0x00e0:
        r7 = r7.compareTo(r3);
        if (r7 != r2) goto L_0x00e7;
    L_0x00e6:
        r5 = 1;
    L_0x00e7:
        r7 = java.lang.Boolean.valueOf(r5);
        return r7;
    L_0x00ec:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzed.zza(java.math.BigDecimal, com.google.android.gms.internal.measurement.zzkj, double):java.lang.Boolean");
    }

    private final void zza(Integer num, Integer num2, zzki zzki, Boolean bool, Boolean bool2) {
        if (zzki == null) {
            zzgi().zziy().zze("The leaf filter of event or user property filter is null. audience ID, filter ID", num, num2);
            return;
        }
        boolean z = false;
        boolean z2 = (bool != null && bool.booleanValue()) || (bool2 != null && bool2.booleanValue());
        zzki.zzatu = Boolean.valueOf(z2);
        if (bool2 != null && bool2.booleanValue()) {
            z = true;
        }
        zzki.zzatv = Boolean.valueOf(z);
    }

    private static void zza(Map<Integer, Long> map, int i, long j) {
        Long l = (Long) map.get(Integer.valueOf(i));
        j /= 1000;
        if (l == null || j > l.longValue()) {
            map.put(Integer.valueOf(i), Long.valueOf(j));
        }
    }

    private static zzkq[] zzd(Map<Integer, Long> map) {
        if (map == null) {
            return null;
        }
        int i = 0;
        zzkq[] zzkqArr = new zzkq[map.size()];
        for (Integer num : map.keySet()) {
            zzkq zzkq = new zzkq();
            zzkq.zzaux = num;
            zzkq.zzauy = (Long) map.get(num);
            int i2 = i + 1;
            zzkqArr[i] = zzkq;
            i = i2;
        }
        return zzkqArr;
    }

    @WorkerThread
    final void zza(String str, zzkg[] zzkgArr) {
        zzkg[] zzkgArr2 = zzkgArr;
        Preconditions.checkNotNull(zzkgArr);
        for (zzkg zzkg : zzkgArr2) {
            for (zzkh zzkh : zzkg.zzatg) {
                String zzal = Event.zzal(zzkh.zzatl);
                if (zzal != null) {
                    zzkh.zzatl = zzal;
                }
                for (zzki zzki : zzkh.zzatm) {
                    String zzal2 = Param.zzal(zzki.zzatt);
                    if (zzal2 != null) {
                        zzki.zzatt = zzal2;
                    }
                    zza(zzkg.zzate, zzkh.zzatk, zzki, zzkg.zzath, zzkg.zzati);
                }
            }
            for (zzkk zzkk : zzkg.zzatf) {
                String zzal3 = UserProperty.zzal(zzkk.zzauc);
                if (zzal3 != null) {
                    zzkk.zzauc = zzal3;
                }
                zza(zzkg.zzate, zzkk.zzatk, zzkk.zzaud, zzkg.zzath, zzkg.zzati);
            }
        }
        zzjh().zzb(str, zzkgArr2);
    }

    @WorkerThread
    final zzkp[] zza(String str, zzkr[] zzkrArr, zzkx[] zzkxArr) {
        Iterator it;
        int intValue;
        BitSet bitSet;
        int length;
        HashSet hashSet;
        int i;
        Map map;
        Map map2;
        Map map3;
        int i2;
        Object obj;
        zzkr zzkr;
        Long l;
        long j;
        zzhi zzjh;
        zzkr zzkr2;
        SQLiteException e;
        int i3;
        zzkr zzkr3;
        zzks[] zzksArr;
        int i4;
        int i5;
        int i6;
        zzkr zzkr4;
        zzks[] zzksArr2;
        String str2;
        Long l2;
        long j2;
        String str3;
        ArrayMap arrayMap;
        Map map4;
        Map map5;
        Map map6;
        HashSet hashSet2;
        zzkr zzkr5;
        zzkx[] zzkxArr2;
        zzet zzet;
        long j3;
        String str4;
        Iterator it2;
        HashSet hashSet3;
        Map map7;
        Map map8;
        zzkr zzkr6;
        Iterator it3;
        Map map9;
        BitSet bitSet2;
        Map map10;
        Map map11;
        Map map12;
        BitSet bitSet3;
        Map map13;
        ArrayMap arrayMap2;
        Map map14;
        zzkh zzkh;
        BitSet bitSet4;
        Map map15;
        Iterator it4;
        Map map16;
        Map map17;
        BitSet bitSet5;
        String str5;
        Map map18;
        BitSet bitSet6;
        Iterator it5;
        Map map19;
        Boolean zza;
        ArrayMap arrayMap3;
        Map map20;
        String str6;
        ArrayMap arrayMap4;
        Object zzb;
        Map map21;
        Map map22;
        Map map23;
        Map map24;
        zzfk zziv;
        String str7;
        IOException e2;
        zzed zzed = this;
        String str8 = str;
        zzkr[] zzkrArr2 = zzkrArr;
        zzkx[] zzkxArr3 = zzkxArr;
        Preconditions.checkNotEmpty(str);
        HashSet hashSet4 = new HashSet();
        Map arrayMap5 = new ArrayMap();
        Map arrayMap6 = new ArrayMap();
        Map arrayMap7 = new ArrayMap();
        Map arrayMap8 = new ArrayMap();
        boolean zzd = zzgk().zzd(str8, zzez.zzajx);
        Map zzbi = zzjh().zzbi(str8);
        if (zzbi != null) {
            it = zzbi.keySet().iterator();
            while (it.hasNext()) {
                Map map25;
                Iterator it6;
                BitSet bitSet7;
                intValue = ((Integer) it.next()).intValue();
                zzkv zzkv = (zzkv) zzbi.get(Integer.valueOf(intValue));
                bitSet = (BitSet) arrayMap6.get(Integer.valueOf(intValue));
                BitSet bitSet8 = (BitSet) arrayMap7.get(Integer.valueOf(intValue));
                if (zzd) {
                    map25 = zzbi;
                    zzbi = new ArrayMap();
                    if (zzkv != null) {
                        it6 = it;
                        if (zzkv.zzawn != null) {
                            zzkq[] zzkqArr = zzkv.zzawn;
                            bitSet7 = bitSet8;
                            length = zzkqArr.length;
                            hashSet = hashSet4;
                            i = 0;
                            while (i < length) {
                                int i7 = length;
                                zzkq zzkq = zzkqArr[i];
                                zzkq[] zzkqArr2 = zzkqArr;
                                if (zzkq.zzaux != null) {
                                    zzbi.put(zzkq.zzaux, zzkq.zzauy);
                                }
                                i++;
                                length = i7;
                                zzkqArr = zzkqArr2;
                            }
                            arrayMap8.put(Integer.valueOf(intValue), zzbi);
                        }
                    } else {
                        it6 = it;
                    }
                    bitSet7 = bitSet8;
                    hashSet = hashSet4;
                    arrayMap8.put(Integer.valueOf(intValue), zzbi);
                } else {
                    map25 = zzbi;
                    it6 = it;
                    bitSet7 = bitSet8;
                    hashSet = hashSet4;
                    zzbi = null;
                }
                if (bitSet == null) {
                    bitSet = new BitSet();
                    arrayMap6.put(Integer.valueOf(intValue), bitSet);
                    bitSet8 = new BitSet();
                    arrayMap7.put(Integer.valueOf(intValue), bitSet8);
                } else {
                    bitSet8 = bitSet7;
                }
                int i8 = 0;
                while (i8 < (zzkv.zzawl.length << 6)) {
                    Object obj2;
                    if (zzjz.zza(zzkv.zzawl, i8)) {
                        map = arrayMap8;
                        map2 = arrayMap7;
                        map3 = arrayMap6;
                        zzgi().zzjc().zze("Filter already evaluated. audience ID, filter ID", Integer.valueOf(intValue), Integer.valueOf(i8));
                        bitSet8.set(i8);
                        if (zzjz.zza(zzkv.zzawm, i8)) {
                            bitSet.set(i8);
                            obj2 = 1;
                            if (zzbi != null && r8 == null) {
                                zzbi.remove(Integer.valueOf(i8));
                            }
                            i8++;
                            arrayMap8 = map;
                            arrayMap7 = map2;
                            arrayMap6 = map3;
                        }
                    } else {
                        map = arrayMap8;
                        map2 = arrayMap7;
                        map3 = arrayMap6;
                    }
                    obj2 = null;
                    zzbi.remove(Integer.valueOf(i8));
                    i8++;
                    arrayMap8 = map;
                    arrayMap7 = map2;
                    arrayMap6 = map3;
                }
                map = arrayMap8;
                map2 = arrayMap7;
                map3 = arrayMap6;
                zzkp zzkp = new zzkp();
                arrayMap5.put(Integer.valueOf(intValue), zzkp);
                zzkp.zzauv = Boolean.valueOf(false);
                zzkp.zzauu = zzkv;
                zzkp.zzaut = new zzkv();
                zzkp.zzaut.zzawm = zzjz.zza(bitSet);
                zzkp.zzaut.zzawl = zzjz.zza(bitSet8);
                if (zzd) {
                    zzkp.zzaut.zzawn = zzd(zzbi);
                }
                zzbi = map25;
                it = it6;
                hashSet4 = hashSet;
                arrayMap8 = map;
                arrayMap7 = map2;
                arrayMap6 = map3;
            }
        }
        map = arrayMap8;
        map2 = arrayMap7;
        map3 = arrayMap6;
        hashSet = hashSet4;
        if (zzkrArr2 != null) {
            ArrayMap arrayMap9 = new ArrayMap();
            int length2 = zzkrArr2.length;
            zzkr zzkr7 = null;
            Long l3 = null;
            i2 = 0;
            long j4 = 0;
            while (i2 < length2) {
                zzet zzf;
                Map map26;
                zzkp zzkp2;
                BitSet bitSet9;
                BitSet bitSet10;
                ArrayMap arrayMap10;
                Object obj3;
                zzkx[] zzkxArr4;
                zzkr zzkr8 = zzkrArr2[i2];
                String str9 = zzkr8.name;
                zzks[] zzksArr3 = zzkr8.zzava;
                if (zzgk().zzd(str8, zzez.zzajr)) {
                    int i9;
                    SQLiteDatabase writableDatabase;
                    String str10;
                    String[] strArr;
                    long j5;
                    zzkr zzkr9;
                    Pair zza2;
                    Long valueOf;
                    long j6;
                    Long l4;
                    String str11;
                    Long l5;
                    long j7;
                    zzjf();
                    Long l6 = (Long) zzjz.zzb(zzkr8, "_eid");
                    Object obj4 = l6 != null ? 1 : null;
                    if (obj4 != null) {
                        i9 = i2;
                        if (str9.equals("_ep")) {
                            obj = 1;
                            if (obj == null) {
                                zzjf();
                                str9 = (String) zzjz.zzb(zzkr8, "_en");
                                if (TextUtils.isEmpty(str9)) {
                                    if (!(zzkr7 == null || l3 == null)) {
                                        if (l6.longValue() != l3.longValue()) {
                                        }
                                        zzkr = zzkr7;
                                        l = l3;
                                        j = j4 - 1;
                                        if (j <= 0) {
                                            zzjh = zzjh();
                                            zzjh.zzab();
                                            zzjh.zzgi().zzjc().zzg("Clearing complex main event info. appId", str8);
                                            try {
                                                writableDatabase = zzjh.getWritableDatabase();
                                                str10 = "delete from main_event_params where app_id=?";
                                                zzkr2 = zzkr8;
                                                try {
                                                    strArr = new String[1];
                                                    try {
                                                        strArr[0] = str8;
                                                        writableDatabase.execSQL(str10, strArr);
                                                    } catch (SQLiteException e3) {
                                                        e = e3;
                                                        zzjh.zzgi().zziv().zzg("Error clearing complex main event", e);
                                                        zzkr7 = zzkr;
                                                        j5 = 0;
                                                        i3 = i9;
                                                        zzkr3 = zzkr2;
                                                        zzksArr = new zzks[(zzkr7.zzava.length + zzksArr3.length)];
                                                        i2 = 0;
                                                        for (zzks zzks : zzkr7.zzava) {
                                                            zzjf();
                                                            if (zzjz.zza(zzkr3, zzks.name) == null) {
                                                                i5 = i2 + 1;
                                                                zzksArr[i2] = zzks;
                                                                i2 = i5;
                                                            }
                                                        }
                                                        if (i2 <= 0) {
                                                            intValue = zzksArr3.length;
                                                            i4 = 0;
                                                            while (i4 < intValue) {
                                                                i6 = i2 + 1;
                                                                zzksArr[i2] = zzksArr3[i4];
                                                                i4++;
                                                                i2 = i6;
                                                            }
                                                            if (i2 == zzksArr.length) {
                                                                zzksArr = (zzks[]) Arrays.copyOf(zzksArr, i2);
                                                            }
                                                            zzkr4 = zzkr7;
                                                            zzksArr2 = zzksArr;
                                                            str2 = str9;
                                                        } else {
                                                            zzgi().zziy().zzg("No unique parameters in main event. eventName", str9);
                                                            zzkr4 = zzkr7;
                                                            str2 = str9;
                                                            zzksArr2 = zzksArr3;
                                                        }
                                                        l2 = l;
                                                        j2 = j;
                                                        str3 = str;
                                                        zzf = zzjh().zzf(str3, zzkr3.name);
                                                        if (zzf != null) {
                                                            zzgi().zziy().zze("Event aggregate wasn't created during raw event logging. appId, event", zzfi.zzbp(str), zzgf().zzbm(str2));
                                                            i7 = length2;
                                                            arrayMap = arrayMap9;
                                                            map4 = map2;
                                                            map5 = map3;
                                                            map26 = map;
                                                            map6 = arrayMap5;
                                                            hashSet2 = hashSet;
                                                            zzkr5 = zzkr3;
                                                            zzkxArr2 = zzkxArr;
                                                            zzet = new zzet(str3, zzkr3.name, 1, 1, zzkr3.zzavb.longValue(), 0, null, null, null);
                                                        } else {
                                                            i7 = length2;
                                                            arrayMap = arrayMap9;
                                                            map6 = arrayMap5;
                                                            zzkr5 = zzkr3;
                                                            hashSet2 = hashSet;
                                                            map26 = map;
                                                            map4 = map2;
                                                            map5 = map3;
                                                            zzkxArr2 = zzkxArr;
                                                            zzf = zzf.zzim();
                                                        }
                                                        zzjh().zza(zzf);
                                                        j3 = zzf.zzahh;
                                                        arrayMap6 = arrayMap;
                                                        zzbi = (Map) arrayMap6.get(str2);
                                                        if (zzbi != null) {
                                                            str4 = str;
                                                            zzbi = zzjh().zzk(str4, str2);
                                                            if (zzbi == null) {
                                                                zzbi = new ArrayMap();
                                                            }
                                                            arrayMap6.put(str2, zzbi);
                                                        } else {
                                                            str4 = str;
                                                        }
                                                        arrayMap5 = zzbi;
                                                        it2 = arrayMap5.keySet().iterator();
                                                        while (it2.hasNext()) {
                                                            i6 = ((Integer) it2.next()).intValue();
                                                            hashSet3 = hashSet2;
                                                            if (hashSet3.contains(Integer.valueOf(i6))) {
                                                                map7 = map6;
                                                                zzkp2 = (zzkp) map7.get(Integer.valueOf(i6));
                                                                map8 = arrayMap6;
                                                                arrayMap6 = map5;
                                                                bitSet = (BitSet) arrayMap6.get(Integer.valueOf(i6));
                                                                zzkr6 = zzkr5;
                                                                it3 = it2;
                                                                map9 = map4;
                                                                bitSet2 = (BitSet) map9.get(Integer.valueOf(i6));
                                                                if (zzd) {
                                                                    bitSet9 = bitSet2;
                                                                    map10 = map26;
                                                                    map11 = null;
                                                                } else {
                                                                    bitSet9 = bitSet2;
                                                                    map10 = map26;
                                                                    map11 = (Map) map10.get(Integer.valueOf(i6));
                                                                }
                                                                if (zzkp2 != null) {
                                                                    zzkp2 = new zzkp();
                                                                    map7.put(Integer.valueOf(i6), zzkp2);
                                                                    map12 = map11;
                                                                    zzkp2.zzauv = Boolean.valueOf(true);
                                                                    bitSet3 = new BitSet();
                                                                    arrayMap6.put(Integer.valueOf(i6), bitSet3);
                                                                    bitSet2 = new BitSet();
                                                                    map9.put(Integer.valueOf(i6), bitSet2);
                                                                    if (zzd) {
                                                                        bitSet10 = bitSet3;
                                                                        bitSet3 = bitSet2;
                                                                        map13 = map10;
                                                                        arrayMap2 = map12;
                                                                    } else {
                                                                        arrayMap10 = new ArrayMap();
                                                                        bitSet10 = bitSet3;
                                                                        map10.put(Integer.valueOf(i6), arrayMap10);
                                                                        bitSet3 = bitSet2;
                                                                        map13 = map10;
                                                                        arrayMap2 = arrayMap10;
                                                                    }
                                                                    bitSet = bitSet10;
                                                                } else {
                                                                    map13 = map10;
                                                                    bitSet3 = bitSet9;
                                                                    arrayMap2 = map11;
                                                                }
                                                                it = ((List) arrayMap5.get(Integer.valueOf(i6))).iterator();
                                                                while (it.hasNext()) {
                                                                    map14 = arrayMap5;
                                                                    zzkh = (zzkh) it.next();
                                                                    bitSet4 = bitSet3;
                                                                    map15 = map9;
                                                                    if (zzgi().isLoggable(2)) {
                                                                        it4 = it;
                                                                        map16 = map7;
                                                                        map17 = arrayMap6;
                                                                    } else {
                                                                        it4 = it;
                                                                        map16 = map7;
                                                                        map17 = arrayMap6;
                                                                        zzgi().zzjc().zzd("Evaluating filter. audience, filter, event", Integer.valueOf(i6), zzkh.zzatk, zzgf().zzbm(zzkh.zzatl));
                                                                        zzgi().zzjc().zzg("Filter definition", zzjf().zza(zzkh));
                                                                    }
                                                                    if (zzkh.zzatk != null) {
                                                                        if (zzkh.zzatk.intValue() > 256) {
                                                                            if (zzd) {
                                                                                bitSet5 = bitSet;
                                                                                str5 = str2;
                                                                                map18 = arrayMap2;
                                                                                zzkr3 = zzkr6;
                                                                                bitSet6 = bitSet4;
                                                                                it5 = it4;
                                                                                map19 = map16;
                                                                                if (bitSet5.get(zzkh.zzatk.intValue())) {
                                                                                    map12 = map18;
                                                                                    zza = zza(zzkh, str5, zzksArr2, j3);
                                                                                    zzgi().zzjc().zzg("Event filter result", zza != null ? "null" : zza);
                                                                                    if (zza != null) {
                                                                                        hashSet3.add(Integer.valueOf(i6));
                                                                                    } else {
                                                                                        bitSet6.set(zzkh.zzatk.intValue());
                                                                                        if (zza.booleanValue()) {
                                                                                            bitSet5.set(zzkh.zzatk.intValue());
                                                                                        }
                                                                                    }
                                                                                    bitSet = bitSet5;
                                                                                    bitSet3 = bitSet6;
                                                                                    zzkr6 = zzkr3;
                                                                                    it = it5;
                                                                                    str2 = str5;
                                                                                    arrayMap2 = map12;
                                                                                    arrayMap5 = map14;
                                                                                    map9 = map15;
                                                                                    arrayMap6 = map17;
                                                                                    map7 = map19;
                                                                                    zzkxArr2 = zzkxArr;
                                                                                } else {
                                                                                    zzgi().zzjc().zze("Event filter already evaluated true. audience ID, filter ID", Integer.valueOf(i6), zzkh.zzatk);
                                                                                }
                                                                            } else {
                                                                                if (zzkh == null) {
                                                                                }
                                                                                obj3 = null;
                                                                                if (bitSet.get(zzkh.zzatk.intValue())) {
                                                                                }
                                                                                it5 = it4;
                                                                                zzkr3 = zzkr6;
                                                                                zzkxArr4 = zzkxArr;
                                                                                bitSet5 = bitSet;
                                                                                str5 = str2;
                                                                                arrayMap3 = arrayMap2;
                                                                                map19 = map16;
                                                                                bitSet6 = bitSet4;
                                                                                zza = zza(zzkh, str2, zzksArr2, j3);
                                                                                zzgi().zzjc().zzg("Event filter result", zza == null ? zza : "null");
                                                                                if (zza == null) {
                                                                                    bitSet6.set(zzkh.zzatk.intValue());
                                                                                    if (zza.booleanValue()) {
                                                                                        bitSet5.set(zzkh.zzatk.intValue());
                                                                                        map18 = arrayMap3;
                                                                                        zza(map18, zzkh.zzatk.intValue(), zzkr3.zzavb.longValue());
                                                                                    }
                                                                                } else {
                                                                                    hashSet3.add(Integer.valueOf(i6));
                                                                                }
                                                                                bitSet = bitSet5;
                                                                                bitSet3 = bitSet6;
                                                                                zzkr6 = zzkr3;
                                                                                it = it5;
                                                                                str2 = str5;
                                                                                arrayMap5 = map14;
                                                                                map9 = map15;
                                                                                arrayMap6 = map17;
                                                                                map7 = map19;
                                                                                arrayMap2 = arrayMap3;
                                                                                zzkxArr2 = zzkxArr;
                                                                            }
                                                                            bitSet = bitSet5;
                                                                            bitSet3 = bitSet6;
                                                                            zzkr6 = zzkr3;
                                                                            it = it5;
                                                                            arrayMap5 = map14;
                                                                            map9 = map15;
                                                                            arrayMap6 = map17;
                                                                            map7 = map19;
                                                                            zzkxArr2 = zzkxArr;
                                                                            arrayMap2 = map18;
                                                                            str2 = str5;
                                                                        }
                                                                    }
                                                                    bitSet5 = bitSet;
                                                                    str5 = str2;
                                                                    map12 = arrayMap2;
                                                                    zzkr3 = zzkr6;
                                                                    bitSet6 = bitSet4;
                                                                    it5 = it4;
                                                                    map19 = map16;
                                                                    map20 = map13;
                                                                    str6 = str;
                                                                    zzgi().zziy().zze("Invalid event filter ID. appId, id", zzfi.zzbp(str), String.valueOf(zzkh.zzatk));
                                                                    bitSet = bitSet5;
                                                                    bitSet3 = bitSet6;
                                                                    zzkr6 = zzkr3;
                                                                    it = it5;
                                                                    str2 = str5;
                                                                    arrayMap2 = map12;
                                                                    arrayMap5 = map14;
                                                                    map9 = map15;
                                                                    arrayMap6 = map17;
                                                                    map7 = map19;
                                                                    zzkxArr2 = zzkxArr;
                                                                }
                                                                map15 = map9;
                                                                map6 = map7;
                                                                map5 = arrayMap6;
                                                                hashSet2 = hashSet3;
                                                                arrayMap6 = map8;
                                                                zzkr5 = zzkr6;
                                                                it2 = it3;
                                                                map26 = map13;
                                                                map4 = map15;
                                                                str4 = str;
                                                            } else {
                                                                zzgi().zzjc().zzg("Skipping failed audience ID", Integer.valueOf(i6));
                                                                hashSet2 = hashSet3;
                                                            }
                                                        }
                                                        arrayMap4 = arrayMap6;
                                                        str6 = str4;
                                                        map15 = map4;
                                                        map17 = map5;
                                                        hashSet3 = hashSet2;
                                                        map19 = map6;
                                                        map20 = map26;
                                                        zzkr7 = zzkr4;
                                                        l3 = l2;
                                                        j4 = j2;
                                                        i2 = i3 + 1;
                                                        zzkrArr2 = zzkrArr;
                                                        map = map20;
                                                        hashSet = hashSet3;
                                                        length2 = i7;
                                                        arrayMap9 = arrayMap4;
                                                        map2 = map15;
                                                        map3 = map17;
                                                        arrayMap5 = map19;
                                                        zzkxArr3 = zzkxArr;
                                                        str8 = str6;
                                                    }
                                                } catch (SQLiteException e4) {
                                                    e = e4;
                                                    zzjh.zzgi().zziv().zzg("Error clearing complex main event", e);
                                                    zzkr7 = zzkr;
                                                    j5 = 0;
                                                    i3 = i9;
                                                    zzkr3 = zzkr2;
                                                    zzksArr = new zzks[(zzkr7.zzava.length + zzksArr3.length)];
                                                    i2 = 0;
                                                    for (length = 0; length < i4; length++) {
                                                        zzjf();
                                                        if (zzjz.zza(zzkr3, zzks.name) == null) {
                                                            i5 = i2 + 1;
                                                            zzksArr[i2] = zzks;
                                                            i2 = i5;
                                                        }
                                                    }
                                                    if (i2 <= 0) {
                                                        zzgi().zziy().zzg("No unique parameters in main event. eventName", str9);
                                                        zzkr4 = zzkr7;
                                                        str2 = str9;
                                                        zzksArr2 = zzksArr3;
                                                    } else {
                                                        intValue = zzksArr3.length;
                                                        i4 = 0;
                                                        while (i4 < intValue) {
                                                            i6 = i2 + 1;
                                                            zzksArr[i2] = zzksArr3[i4];
                                                            i4++;
                                                            i2 = i6;
                                                        }
                                                        if (i2 == zzksArr.length) {
                                                            zzksArr = (zzks[]) Arrays.copyOf(zzksArr, i2);
                                                        }
                                                        zzkr4 = zzkr7;
                                                        zzksArr2 = zzksArr;
                                                        str2 = str9;
                                                    }
                                                    l2 = l;
                                                    j2 = j;
                                                    str3 = str;
                                                    zzf = zzjh().zzf(str3, zzkr3.name);
                                                    if (zzf != null) {
                                                        i7 = length2;
                                                        arrayMap = arrayMap9;
                                                        map6 = arrayMap5;
                                                        zzkr5 = zzkr3;
                                                        hashSet2 = hashSet;
                                                        map26 = map;
                                                        map4 = map2;
                                                        map5 = map3;
                                                        zzkxArr2 = zzkxArr;
                                                        zzf = zzf.zzim();
                                                    } else {
                                                        zzgi().zziy().zze("Event aggregate wasn't created during raw event logging. appId, event", zzfi.zzbp(str), zzgf().zzbm(str2));
                                                        i7 = length2;
                                                        arrayMap = arrayMap9;
                                                        map4 = map2;
                                                        map5 = map3;
                                                        map26 = map;
                                                        map6 = arrayMap5;
                                                        hashSet2 = hashSet;
                                                        zzkr5 = zzkr3;
                                                        zzkxArr2 = zzkxArr;
                                                        zzet = new zzet(str3, zzkr3.name, 1, 1, zzkr3.zzavb.longValue(), 0, null, null, null);
                                                    }
                                                    zzjh().zza(zzf);
                                                    j3 = zzf.zzahh;
                                                    arrayMap6 = arrayMap;
                                                    zzbi = (Map) arrayMap6.get(str2);
                                                    if (zzbi != null) {
                                                        str4 = str;
                                                    } else {
                                                        str4 = str;
                                                        zzbi = zzjh().zzk(str4, str2);
                                                        if (zzbi == null) {
                                                            zzbi = new ArrayMap();
                                                        }
                                                        arrayMap6.put(str2, zzbi);
                                                    }
                                                    arrayMap5 = zzbi;
                                                    it2 = arrayMap5.keySet().iterator();
                                                    while (it2.hasNext()) {
                                                        i6 = ((Integer) it2.next()).intValue();
                                                        hashSet3 = hashSet2;
                                                        if (hashSet3.contains(Integer.valueOf(i6))) {
                                                            map7 = map6;
                                                            zzkp2 = (zzkp) map7.get(Integer.valueOf(i6));
                                                            map8 = arrayMap6;
                                                            arrayMap6 = map5;
                                                            bitSet = (BitSet) arrayMap6.get(Integer.valueOf(i6));
                                                            zzkr6 = zzkr5;
                                                            it3 = it2;
                                                            map9 = map4;
                                                            bitSet2 = (BitSet) map9.get(Integer.valueOf(i6));
                                                            if (zzd) {
                                                                bitSet9 = bitSet2;
                                                                map10 = map26;
                                                                map11 = null;
                                                            } else {
                                                                bitSet9 = bitSet2;
                                                                map10 = map26;
                                                                map11 = (Map) map10.get(Integer.valueOf(i6));
                                                            }
                                                            if (zzkp2 != null) {
                                                                map13 = map10;
                                                                bitSet3 = bitSet9;
                                                                arrayMap2 = map11;
                                                            } else {
                                                                zzkp2 = new zzkp();
                                                                map7.put(Integer.valueOf(i6), zzkp2);
                                                                map12 = map11;
                                                                zzkp2.zzauv = Boolean.valueOf(true);
                                                                bitSet3 = new BitSet();
                                                                arrayMap6.put(Integer.valueOf(i6), bitSet3);
                                                                bitSet2 = new BitSet();
                                                                map9.put(Integer.valueOf(i6), bitSet2);
                                                                if (zzd) {
                                                                    bitSet10 = bitSet3;
                                                                    bitSet3 = bitSet2;
                                                                    map13 = map10;
                                                                    arrayMap2 = map12;
                                                                } else {
                                                                    arrayMap10 = new ArrayMap();
                                                                    bitSet10 = bitSet3;
                                                                    map10.put(Integer.valueOf(i6), arrayMap10);
                                                                    bitSet3 = bitSet2;
                                                                    map13 = map10;
                                                                    arrayMap2 = arrayMap10;
                                                                }
                                                                bitSet = bitSet10;
                                                            }
                                                            it = ((List) arrayMap5.get(Integer.valueOf(i6))).iterator();
                                                            while (it.hasNext()) {
                                                                map14 = arrayMap5;
                                                                zzkh = (zzkh) it.next();
                                                                bitSet4 = bitSet3;
                                                                map15 = map9;
                                                                if (zzgi().isLoggable(2)) {
                                                                    it4 = it;
                                                                    map16 = map7;
                                                                    map17 = arrayMap6;
                                                                } else {
                                                                    it4 = it;
                                                                    map16 = map7;
                                                                    map17 = arrayMap6;
                                                                    zzgi().zzjc().zzd("Evaluating filter. audience, filter, event", Integer.valueOf(i6), zzkh.zzatk, zzgf().zzbm(zzkh.zzatl));
                                                                    zzgi().zzjc().zzg("Filter definition", zzjf().zza(zzkh));
                                                                }
                                                                if (zzkh.zzatk != null) {
                                                                    if (zzkh.zzatk.intValue() > 256) {
                                                                        if (zzd) {
                                                                            bitSet5 = bitSet;
                                                                            str5 = str2;
                                                                            map18 = arrayMap2;
                                                                            zzkr3 = zzkr6;
                                                                            bitSet6 = bitSet4;
                                                                            it5 = it4;
                                                                            map19 = map16;
                                                                            if (bitSet5.get(zzkh.zzatk.intValue())) {
                                                                                map12 = map18;
                                                                                zza = zza(zzkh, str5, zzksArr2, j3);
                                                                                if (zza != null) {
                                                                                }
                                                                                zzgi().zzjc().zzg("Event filter result", zza != null ? "null" : zza);
                                                                                if (zza != null) {
                                                                                    bitSet6.set(zzkh.zzatk.intValue());
                                                                                    if (zza.booleanValue()) {
                                                                                        bitSet5.set(zzkh.zzatk.intValue());
                                                                                    }
                                                                                } else {
                                                                                    hashSet3.add(Integer.valueOf(i6));
                                                                                }
                                                                                bitSet = bitSet5;
                                                                                bitSet3 = bitSet6;
                                                                                zzkr6 = zzkr3;
                                                                                it = it5;
                                                                                str2 = str5;
                                                                                arrayMap2 = map12;
                                                                                arrayMap5 = map14;
                                                                                map9 = map15;
                                                                                arrayMap6 = map17;
                                                                                map7 = map19;
                                                                                zzkxArr2 = zzkxArr;
                                                                            } else {
                                                                                zzgi().zzjc().zze("Event filter already evaluated true. audience ID, filter ID", Integer.valueOf(i6), zzkh.zzatk);
                                                                            }
                                                                        } else {
                                                                            if (zzkh == null) {
                                                                            }
                                                                            obj3 = null;
                                                                            if (bitSet.get(zzkh.zzatk.intValue())) {
                                                                            }
                                                                            it5 = it4;
                                                                            zzkr3 = zzkr6;
                                                                            zzkxArr4 = zzkxArr;
                                                                            bitSet5 = bitSet;
                                                                            str5 = str2;
                                                                            arrayMap3 = arrayMap2;
                                                                            map19 = map16;
                                                                            bitSet6 = bitSet4;
                                                                            zza = zza(zzkh, str2, zzksArr2, j3);
                                                                            if (zza == null) {
                                                                            }
                                                                            zzgi().zzjc().zzg("Event filter result", zza == null ? zza : "null");
                                                                            if (zza == null) {
                                                                                hashSet3.add(Integer.valueOf(i6));
                                                                            } else {
                                                                                bitSet6.set(zzkh.zzatk.intValue());
                                                                                if (zza.booleanValue()) {
                                                                                    bitSet5.set(zzkh.zzatk.intValue());
                                                                                    map18 = arrayMap3;
                                                                                    zza(map18, zzkh.zzatk.intValue(), zzkr3.zzavb.longValue());
                                                                                }
                                                                            }
                                                                            bitSet = bitSet5;
                                                                            bitSet3 = bitSet6;
                                                                            zzkr6 = zzkr3;
                                                                            it = it5;
                                                                            str2 = str5;
                                                                            arrayMap5 = map14;
                                                                            map9 = map15;
                                                                            arrayMap6 = map17;
                                                                            map7 = map19;
                                                                            arrayMap2 = arrayMap3;
                                                                            zzkxArr2 = zzkxArr;
                                                                        }
                                                                        bitSet = bitSet5;
                                                                        bitSet3 = bitSet6;
                                                                        zzkr6 = zzkr3;
                                                                        it = it5;
                                                                        arrayMap5 = map14;
                                                                        map9 = map15;
                                                                        arrayMap6 = map17;
                                                                        map7 = map19;
                                                                        zzkxArr2 = zzkxArr;
                                                                        arrayMap2 = map18;
                                                                        str2 = str5;
                                                                    }
                                                                }
                                                                bitSet5 = bitSet;
                                                                str5 = str2;
                                                                map12 = arrayMap2;
                                                                zzkr3 = zzkr6;
                                                                bitSet6 = bitSet4;
                                                                it5 = it4;
                                                                map19 = map16;
                                                                map20 = map13;
                                                                str6 = str;
                                                                zzgi().zziy().zze("Invalid event filter ID. appId, id", zzfi.zzbp(str), String.valueOf(zzkh.zzatk));
                                                                bitSet = bitSet5;
                                                                bitSet3 = bitSet6;
                                                                zzkr6 = zzkr3;
                                                                it = it5;
                                                                str2 = str5;
                                                                arrayMap2 = map12;
                                                                arrayMap5 = map14;
                                                                map9 = map15;
                                                                arrayMap6 = map17;
                                                                map7 = map19;
                                                                zzkxArr2 = zzkxArr;
                                                            }
                                                            map15 = map9;
                                                            map6 = map7;
                                                            map5 = arrayMap6;
                                                            hashSet2 = hashSet3;
                                                            arrayMap6 = map8;
                                                            zzkr5 = zzkr6;
                                                            it2 = it3;
                                                            map26 = map13;
                                                            map4 = map15;
                                                            str4 = str;
                                                        } else {
                                                            zzgi().zzjc().zzg("Skipping failed audience ID", Integer.valueOf(i6));
                                                            hashSet2 = hashSet3;
                                                        }
                                                    }
                                                    arrayMap4 = arrayMap6;
                                                    str6 = str4;
                                                    map15 = map4;
                                                    map17 = map5;
                                                    hashSet3 = hashSet2;
                                                    map19 = map6;
                                                    map20 = map26;
                                                    zzkr7 = zzkr4;
                                                    l3 = l2;
                                                    j4 = j2;
                                                    i2 = i3 + 1;
                                                    zzkrArr2 = zzkrArr;
                                                    map = map20;
                                                    hashSet = hashSet3;
                                                    length2 = i7;
                                                    arrayMap9 = arrayMap4;
                                                    map2 = map15;
                                                    map3 = map17;
                                                    arrayMap5 = map19;
                                                    zzkxArr3 = zzkxArr;
                                                    str8 = str6;
                                                }
                                            } catch (SQLiteException e5) {
                                                e = e5;
                                                zzkr2 = zzkr8;
                                                zzjh.zzgi().zziv().zzg("Error clearing complex main event", e);
                                                zzkr7 = zzkr;
                                                j5 = 0;
                                                i3 = i9;
                                                zzkr3 = zzkr2;
                                                zzksArr = new zzks[(zzkr7.zzava.length + zzksArr3.length)];
                                                i2 = 0;
                                                for (length = 0; length < i4; length++) {
                                                    zzjf();
                                                    if (zzjz.zza(zzkr3, zzks.name) == null) {
                                                        i5 = i2 + 1;
                                                        zzksArr[i2] = zzks;
                                                        i2 = i5;
                                                    }
                                                }
                                                if (i2 <= 0) {
                                                    zzgi().zziy().zzg("No unique parameters in main event. eventName", str9);
                                                    zzkr4 = zzkr7;
                                                    str2 = str9;
                                                    zzksArr2 = zzksArr3;
                                                } else {
                                                    intValue = zzksArr3.length;
                                                    i4 = 0;
                                                    while (i4 < intValue) {
                                                        i6 = i2 + 1;
                                                        zzksArr[i2] = zzksArr3[i4];
                                                        i4++;
                                                        i2 = i6;
                                                    }
                                                    if (i2 == zzksArr.length) {
                                                        zzksArr = (zzks[]) Arrays.copyOf(zzksArr, i2);
                                                    }
                                                    zzkr4 = zzkr7;
                                                    zzksArr2 = zzksArr;
                                                    str2 = str9;
                                                }
                                                l2 = l;
                                                j2 = j;
                                                str3 = str;
                                                zzf = zzjh().zzf(str3, zzkr3.name);
                                                if (zzf != null) {
                                                    i7 = length2;
                                                    arrayMap = arrayMap9;
                                                    map6 = arrayMap5;
                                                    zzkr5 = zzkr3;
                                                    hashSet2 = hashSet;
                                                    map26 = map;
                                                    map4 = map2;
                                                    map5 = map3;
                                                    zzkxArr2 = zzkxArr;
                                                    zzf = zzf.zzim();
                                                } else {
                                                    zzgi().zziy().zze("Event aggregate wasn't created during raw event logging. appId, event", zzfi.zzbp(str), zzgf().zzbm(str2));
                                                    i7 = length2;
                                                    arrayMap = arrayMap9;
                                                    map4 = map2;
                                                    map5 = map3;
                                                    map26 = map;
                                                    map6 = arrayMap5;
                                                    hashSet2 = hashSet;
                                                    zzkr5 = zzkr3;
                                                    zzkxArr2 = zzkxArr;
                                                    zzet = new zzet(str3, zzkr3.name, 1, 1, zzkr3.zzavb.longValue(), 0, null, null, null);
                                                }
                                                zzjh().zza(zzf);
                                                j3 = zzf.zzahh;
                                                arrayMap6 = arrayMap;
                                                zzbi = (Map) arrayMap6.get(str2);
                                                if (zzbi != null) {
                                                    str4 = str;
                                                } else {
                                                    str4 = str;
                                                    zzbi = zzjh().zzk(str4, str2);
                                                    if (zzbi == null) {
                                                        zzbi = new ArrayMap();
                                                    }
                                                    arrayMap6.put(str2, zzbi);
                                                }
                                                arrayMap5 = zzbi;
                                                it2 = arrayMap5.keySet().iterator();
                                                while (it2.hasNext()) {
                                                    i6 = ((Integer) it2.next()).intValue();
                                                    hashSet3 = hashSet2;
                                                    if (hashSet3.contains(Integer.valueOf(i6))) {
                                                        map7 = map6;
                                                        zzkp2 = (zzkp) map7.get(Integer.valueOf(i6));
                                                        map8 = arrayMap6;
                                                        arrayMap6 = map5;
                                                        bitSet = (BitSet) arrayMap6.get(Integer.valueOf(i6));
                                                        zzkr6 = zzkr5;
                                                        it3 = it2;
                                                        map9 = map4;
                                                        bitSet2 = (BitSet) map9.get(Integer.valueOf(i6));
                                                        if (zzd) {
                                                            bitSet9 = bitSet2;
                                                            map10 = map26;
                                                            map11 = null;
                                                        } else {
                                                            bitSet9 = bitSet2;
                                                            map10 = map26;
                                                            map11 = (Map) map10.get(Integer.valueOf(i6));
                                                        }
                                                        if (zzkp2 != null) {
                                                            map13 = map10;
                                                            bitSet3 = bitSet9;
                                                            arrayMap2 = map11;
                                                        } else {
                                                            zzkp2 = new zzkp();
                                                            map7.put(Integer.valueOf(i6), zzkp2);
                                                            map12 = map11;
                                                            zzkp2.zzauv = Boolean.valueOf(true);
                                                            bitSet3 = new BitSet();
                                                            arrayMap6.put(Integer.valueOf(i6), bitSet3);
                                                            bitSet2 = new BitSet();
                                                            map9.put(Integer.valueOf(i6), bitSet2);
                                                            if (zzd) {
                                                                bitSet10 = bitSet3;
                                                                bitSet3 = bitSet2;
                                                                map13 = map10;
                                                                arrayMap2 = map12;
                                                            } else {
                                                                arrayMap10 = new ArrayMap();
                                                                bitSet10 = bitSet3;
                                                                map10.put(Integer.valueOf(i6), arrayMap10);
                                                                bitSet3 = bitSet2;
                                                                map13 = map10;
                                                                arrayMap2 = arrayMap10;
                                                            }
                                                            bitSet = bitSet10;
                                                        }
                                                        it = ((List) arrayMap5.get(Integer.valueOf(i6))).iterator();
                                                        while (it.hasNext()) {
                                                            map14 = arrayMap5;
                                                            zzkh = (zzkh) it.next();
                                                            bitSet4 = bitSet3;
                                                            map15 = map9;
                                                            if (zzgi().isLoggable(2)) {
                                                                it4 = it;
                                                                map16 = map7;
                                                                map17 = arrayMap6;
                                                            } else {
                                                                it4 = it;
                                                                map16 = map7;
                                                                map17 = arrayMap6;
                                                                zzgi().zzjc().zzd("Evaluating filter. audience, filter, event", Integer.valueOf(i6), zzkh.zzatk, zzgf().zzbm(zzkh.zzatl));
                                                                zzgi().zzjc().zzg("Filter definition", zzjf().zza(zzkh));
                                                            }
                                                            if (zzkh.zzatk != null) {
                                                                if (zzkh.zzatk.intValue() > 256) {
                                                                    if (zzd) {
                                                                        bitSet5 = bitSet;
                                                                        str5 = str2;
                                                                        map18 = arrayMap2;
                                                                        zzkr3 = zzkr6;
                                                                        bitSet6 = bitSet4;
                                                                        it5 = it4;
                                                                        map19 = map16;
                                                                        if (bitSet5.get(zzkh.zzatk.intValue())) {
                                                                            map12 = map18;
                                                                            zza = zza(zzkh, str5, zzksArr2, j3);
                                                                            if (zza != null) {
                                                                            }
                                                                            zzgi().zzjc().zzg("Event filter result", zza != null ? "null" : zza);
                                                                            if (zza != null) {
                                                                                bitSet6.set(zzkh.zzatk.intValue());
                                                                                if (zza.booleanValue()) {
                                                                                    bitSet5.set(zzkh.zzatk.intValue());
                                                                                }
                                                                            } else {
                                                                                hashSet3.add(Integer.valueOf(i6));
                                                                            }
                                                                            bitSet = bitSet5;
                                                                            bitSet3 = bitSet6;
                                                                            zzkr6 = zzkr3;
                                                                            it = it5;
                                                                            str2 = str5;
                                                                            arrayMap2 = map12;
                                                                            arrayMap5 = map14;
                                                                            map9 = map15;
                                                                            arrayMap6 = map17;
                                                                            map7 = map19;
                                                                            zzkxArr2 = zzkxArr;
                                                                        } else {
                                                                            zzgi().zzjc().zze("Event filter already evaluated true. audience ID, filter ID", Integer.valueOf(i6), zzkh.zzatk);
                                                                        }
                                                                    } else {
                                                                        if (zzkh == null) {
                                                                        }
                                                                        obj3 = null;
                                                                        if (bitSet.get(zzkh.zzatk.intValue())) {
                                                                        }
                                                                        it5 = it4;
                                                                        zzkr3 = zzkr6;
                                                                        zzkxArr4 = zzkxArr;
                                                                        bitSet5 = bitSet;
                                                                        str5 = str2;
                                                                        arrayMap3 = arrayMap2;
                                                                        map19 = map16;
                                                                        bitSet6 = bitSet4;
                                                                        zza = zza(zzkh, str2, zzksArr2, j3);
                                                                        if (zza == null) {
                                                                        }
                                                                        zzgi().zzjc().zzg("Event filter result", zza == null ? zza : "null");
                                                                        if (zza == null) {
                                                                            hashSet3.add(Integer.valueOf(i6));
                                                                        } else {
                                                                            bitSet6.set(zzkh.zzatk.intValue());
                                                                            if (zza.booleanValue()) {
                                                                                bitSet5.set(zzkh.zzatk.intValue());
                                                                                map18 = arrayMap3;
                                                                                zza(map18, zzkh.zzatk.intValue(), zzkr3.zzavb.longValue());
                                                                            }
                                                                        }
                                                                        bitSet = bitSet5;
                                                                        bitSet3 = bitSet6;
                                                                        zzkr6 = zzkr3;
                                                                        it = it5;
                                                                        str2 = str5;
                                                                        arrayMap5 = map14;
                                                                        map9 = map15;
                                                                        arrayMap6 = map17;
                                                                        map7 = map19;
                                                                        arrayMap2 = arrayMap3;
                                                                        zzkxArr2 = zzkxArr;
                                                                    }
                                                                    bitSet = bitSet5;
                                                                    bitSet3 = bitSet6;
                                                                    zzkr6 = zzkr3;
                                                                    it = it5;
                                                                    arrayMap5 = map14;
                                                                    map9 = map15;
                                                                    arrayMap6 = map17;
                                                                    map7 = map19;
                                                                    zzkxArr2 = zzkxArr;
                                                                    arrayMap2 = map18;
                                                                    str2 = str5;
                                                                }
                                                            }
                                                            bitSet5 = bitSet;
                                                            str5 = str2;
                                                            map12 = arrayMap2;
                                                            zzkr3 = zzkr6;
                                                            bitSet6 = bitSet4;
                                                            it5 = it4;
                                                            map19 = map16;
                                                            map20 = map13;
                                                            str6 = str;
                                                            zzgi().zziy().zze("Invalid event filter ID. appId, id", zzfi.zzbp(str), String.valueOf(zzkh.zzatk));
                                                            bitSet = bitSet5;
                                                            bitSet3 = bitSet6;
                                                            zzkr6 = zzkr3;
                                                            it = it5;
                                                            str2 = str5;
                                                            arrayMap2 = map12;
                                                            arrayMap5 = map14;
                                                            map9 = map15;
                                                            arrayMap6 = map17;
                                                            map7 = map19;
                                                            zzkxArr2 = zzkxArr;
                                                        }
                                                        map15 = map9;
                                                        map6 = map7;
                                                        map5 = arrayMap6;
                                                        hashSet2 = hashSet3;
                                                        arrayMap6 = map8;
                                                        zzkr5 = zzkr6;
                                                        it2 = it3;
                                                        map26 = map13;
                                                        map4 = map15;
                                                        str4 = str;
                                                    } else {
                                                        zzgi().zzjc().zzg("Skipping failed audience ID", Integer.valueOf(i6));
                                                        hashSet2 = hashSet3;
                                                    }
                                                }
                                                arrayMap4 = arrayMap6;
                                                str6 = str4;
                                                map15 = map4;
                                                map17 = map5;
                                                hashSet3 = hashSet2;
                                                map19 = map6;
                                                map20 = map26;
                                                zzkr7 = zzkr4;
                                                l3 = l2;
                                                j4 = j2;
                                                i2 = i3 + 1;
                                                zzkrArr2 = zzkrArr;
                                                map = map20;
                                                hashSet = hashSet3;
                                                length2 = i7;
                                                arrayMap9 = arrayMap4;
                                                map2 = map15;
                                                map3 = map17;
                                                arrayMap5 = map19;
                                                zzkxArr3 = zzkxArr;
                                                str8 = str6;
                                            }
                                            zzkr7 = zzkr;
                                            j5 = 0;
                                            i3 = i9;
                                            zzkr3 = zzkr2;
                                        } else {
                                            zzkr3 = zzkr8;
                                            j5 = 0;
                                            zzkr9 = zzkr;
                                            i3 = i9;
                                            zzjh().zza(str8, l6, j, zzkr);
                                            zzkr7 = zzkr9;
                                        }
                                        zzksArr = new zzks[(zzkr7.zzava.length + zzksArr3.length)];
                                        i2 = 0;
                                        for (length = 0; length < i4; length++) {
                                            zzjf();
                                            if (zzjz.zza(zzkr3, zzks.name) == null) {
                                                i5 = i2 + 1;
                                                zzksArr[i2] = zzks;
                                                i2 = i5;
                                            }
                                        }
                                        if (i2 <= 0) {
                                            intValue = zzksArr3.length;
                                            i4 = 0;
                                            while (i4 < intValue) {
                                                i6 = i2 + 1;
                                                zzksArr[i2] = zzksArr3[i4];
                                                i4++;
                                                i2 = i6;
                                            }
                                            if (i2 == zzksArr.length) {
                                                zzksArr = (zzks[]) Arrays.copyOf(zzksArr, i2);
                                            }
                                            zzkr4 = zzkr7;
                                            zzksArr2 = zzksArr;
                                            str2 = str9;
                                        } else {
                                            zzgi().zziy().zzg("No unique parameters in main event. eventName", str9);
                                            zzkr4 = zzkr7;
                                            str2 = str9;
                                            zzksArr2 = zzksArr3;
                                        }
                                        l2 = l;
                                        j2 = j;
                                        str3 = str;
                                    }
                                    zza2 = zzjh().zza(str8, l6);
                                    if (zza2 != null) {
                                        if (zza2.first == null) {
                                            zzkr7 = (zzkr) zza2.first;
                                            j4 = ((Long) zza2.second).longValue();
                                            zzjf();
                                            l3 = (Long) zzjz.zzb(zzkr7, "_eid");
                                            zzkr = zzkr7;
                                            l = l3;
                                            j = j4 - 1;
                                            if (j <= 0) {
                                                zzkr3 = zzkr8;
                                                j5 = 0;
                                                zzkr9 = zzkr;
                                                i3 = i9;
                                                zzjh().zza(str8, l6, j, zzkr);
                                                zzkr7 = zzkr9;
                                            } else {
                                                zzjh = zzjh();
                                                zzjh.zzab();
                                                zzjh.zzgi().zzjc().zzg("Clearing complex main event info. appId", str8);
                                                writableDatabase = zzjh.getWritableDatabase();
                                                str10 = "delete from main_event_params where app_id=?";
                                                zzkr2 = zzkr8;
                                                strArr = new String[1];
                                                strArr[0] = str8;
                                                writableDatabase.execSQL(str10, strArr);
                                                zzkr7 = zzkr;
                                                j5 = 0;
                                                i3 = i9;
                                                zzkr3 = zzkr2;
                                            }
                                            zzksArr = new zzks[(zzkr7.zzava.length + zzksArr3.length)];
                                            i2 = 0;
                                            for (length = 0; length < i4; length++) {
                                                zzjf();
                                                if (zzjz.zza(zzkr3, zzks.name) == null) {
                                                    i5 = i2 + 1;
                                                    zzksArr[i2] = zzks;
                                                    i2 = i5;
                                                }
                                            }
                                            if (i2 <= 0) {
                                                zzgi().zziy().zzg("No unique parameters in main event. eventName", str9);
                                                zzkr4 = zzkr7;
                                                str2 = str9;
                                                zzksArr2 = zzksArr3;
                                            } else {
                                                intValue = zzksArr3.length;
                                                i4 = 0;
                                                while (i4 < intValue) {
                                                    i6 = i2 + 1;
                                                    zzksArr[i2] = zzksArr3[i4];
                                                    i4++;
                                                    i2 = i6;
                                                }
                                                if (i2 == zzksArr.length) {
                                                    zzksArr = (zzks[]) Arrays.copyOf(zzksArr, i2);
                                                }
                                                zzkr4 = zzkr7;
                                                zzksArr2 = zzksArr;
                                                str2 = str9;
                                            }
                                            l2 = l;
                                            j2 = j;
                                            str3 = str;
                                        }
                                    }
                                    i3 = i9;
                                    zzgi().zziv().zze("Extra parameter without existing main event. eventName, eventId", str9, l6);
                                } else {
                                    zzgi().zziv().zzg("Extra parameter without an event name. eventId", l6);
                                    i3 = i9;
                                }
                                i7 = length2;
                                arrayMap4 = arrayMap9;
                                map19 = arrayMap5;
                                hashSet3 = hashSet;
                                map20 = map;
                                map15 = map2;
                                map17 = map3;
                                str6 = str;
                                i2 = i3 + 1;
                                zzkrArr2 = zzkrArr;
                                map = map20;
                                hashSet = hashSet3;
                                length2 = i7;
                                arrayMap9 = arrayMap4;
                                map2 = map15;
                                map3 = map17;
                                arrayMap5 = map19;
                                zzkxArr3 = zzkxArr;
                                str8 = str6;
                            } else {
                                zzkr3 = zzkr8;
                                i3 = i9;
                                if (obj4 == null) {
                                    zzjf();
                                    valueOf = Long.valueOf(0);
                                    zzb = zzjz.zzb(zzkr3, "_epc");
                                    if (zzb == null) {
                                        zzb = valueOf;
                                    }
                                    j5 = ((Long) zzb).longValue();
                                    if (j5 > 0) {
                                        zzgi().zziy().zzg("Complex event with zero extra param count. eventName", str9);
                                        j6 = 0;
                                        l4 = l6;
                                        j2 = j5;
                                        str3 = str;
                                    } else {
                                        j6 = 0;
                                        str11 = str;
                                        l5 = l6;
                                        l4 = l6;
                                        j7 = j5;
                                        j2 = j5;
                                        str3 = str11;
                                        zzjh().zza(str11, l5, j7, zzkr3);
                                    }
                                    str2 = str9;
                                    zzksArr2 = zzksArr3;
                                    zzkr4 = zzkr3;
                                    l2 = l4;
                                } else {
                                    str3 = str;
                                }
                            }
                            zzf = zzjh().zzf(str3, zzkr3.name);
                            if (zzf != null) {
                                zzgi().zziy().zze("Event aggregate wasn't created during raw event logging. appId, event", zzfi.zzbp(str), zzgf().zzbm(str2));
                                i7 = length2;
                                arrayMap = arrayMap9;
                                map4 = map2;
                                map5 = map3;
                                map26 = map;
                                map6 = arrayMap5;
                                hashSet2 = hashSet;
                                zzkr5 = zzkr3;
                                zzkxArr2 = zzkxArr;
                                zzet = new zzet(str3, zzkr3.name, 1, 1, zzkr3.zzavb.longValue(), 0, null, null, null);
                            } else {
                                i7 = length2;
                                arrayMap = arrayMap9;
                                map6 = arrayMap5;
                                zzkr5 = zzkr3;
                                hashSet2 = hashSet;
                                map26 = map;
                                map4 = map2;
                                map5 = map3;
                                zzkxArr2 = zzkxArr;
                                zzf = zzf.zzim();
                            }
                            zzjh().zza(zzf);
                            j3 = zzf.zzahh;
                            arrayMap6 = arrayMap;
                            zzbi = (Map) arrayMap6.get(str2);
                            if (zzbi != null) {
                                str4 = str;
                                zzbi = zzjh().zzk(str4, str2);
                                if (zzbi == null) {
                                    zzbi = new ArrayMap();
                                }
                                arrayMap6.put(str2, zzbi);
                            } else {
                                str4 = str;
                            }
                            arrayMap5 = zzbi;
                            it2 = arrayMap5.keySet().iterator();
                            while (it2.hasNext()) {
                                i6 = ((Integer) it2.next()).intValue();
                                hashSet3 = hashSet2;
                                if (hashSet3.contains(Integer.valueOf(i6))) {
                                    zzgi().zzjc().zzg("Skipping failed audience ID", Integer.valueOf(i6));
                                    hashSet2 = hashSet3;
                                } else {
                                    map7 = map6;
                                    zzkp2 = (zzkp) map7.get(Integer.valueOf(i6));
                                    map8 = arrayMap6;
                                    arrayMap6 = map5;
                                    bitSet = (BitSet) arrayMap6.get(Integer.valueOf(i6));
                                    zzkr6 = zzkr5;
                                    it3 = it2;
                                    map9 = map4;
                                    bitSet2 = (BitSet) map9.get(Integer.valueOf(i6));
                                    if (zzd) {
                                        bitSet9 = bitSet2;
                                        map10 = map26;
                                        map11 = (Map) map10.get(Integer.valueOf(i6));
                                    } else {
                                        bitSet9 = bitSet2;
                                        map10 = map26;
                                        map11 = null;
                                    }
                                    if (zzkp2 != null) {
                                        zzkp2 = new zzkp();
                                        map7.put(Integer.valueOf(i6), zzkp2);
                                        map12 = map11;
                                        zzkp2.zzauv = Boolean.valueOf(true);
                                        bitSet3 = new BitSet();
                                        arrayMap6.put(Integer.valueOf(i6), bitSet3);
                                        bitSet2 = new BitSet();
                                        map9.put(Integer.valueOf(i6), bitSet2);
                                        if (zzd) {
                                            arrayMap10 = new ArrayMap();
                                            bitSet10 = bitSet3;
                                            map10.put(Integer.valueOf(i6), arrayMap10);
                                            bitSet3 = bitSet2;
                                            map13 = map10;
                                            arrayMap2 = arrayMap10;
                                        } else {
                                            bitSet10 = bitSet3;
                                            bitSet3 = bitSet2;
                                            map13 = map10;
                                            arrayMap2 = map12;
                                        }
                                        bitSet = bitSet10;
                                    } else {
                                        map13 = map10;
                                        bitSet3 = bitSet9;
                                        arrayMap2 = map11;
                                    }
                                    it = ((List) arrayMap5.get(Integer.valueOf(i6))).iterator();
                                    while (it.hasNext()) {
                                        map14 = arrayMap5;
                                        zzkh = (zzkh) it.next();
                                        bitSet4 = bitSet3;
                                        map15 = map9;
                                        if (zzgi().isLoggable(2)) {
                                            it4 = it;
                                            map16 = map7;
                                            map17 = arrayMap6;
                                            zzgi().zzjc().zzd("Evaluating filter. audience, filter, event", Integer.valueOf(i6), zzkh.zzatk, zzgf().zzbm(zzkh.zzatl));
                                            zzgi().zzjc().zzg("Filter definition", zzjf().zza(zzkh));
                                        } else {
                                            it4 = it;
                                            map16 = map7;
                                            map17 = arrayMap6;
                                        }
                                        if (zzkh.zzatk != null) {
                                            if (zzkh.zzatk.intValue() > 256) {
                                                if (zzd) {
                                                    if (zzkh == null && zzkh.zzatm != null && zzkh.zzatm.length > 0) {
                                                        if (zzkh.zzatm[0].zzatu != null && zzkh.zzatm[0].zzatu.booleanValue()) {
                                                            obj3 = 1;
                                                            if (bitSet.get(zzkh.zzatk.intValue()) || obj3 != null) {
                                                                it5 = it4;
                                                                zzkr3 = zzkr6;
                                                                zzkxArr4 = zzkxArr;
                                                                bitSet5 = bitSet;
                                                                str5 = str2;
                                                                arrayMap3 = arrayMap2;
                                                                map19 = map16;
                                                                bitSet6 = bitSet4;
                                                                zza = zza(zzkh, str2, zzksArr2, j3);
                                                                if (zza == null) {
                                                                }
                                                                zzgi().zzjc().zzg("Event filter result", zza == null ? zza : "null");
                                                                if (zza == null) {
                                                                    hashSet3.add(Integer.valueOf(i6));
                                                                } else {
                                                                    bitSet6.set(zzkh.zzatk.intValue());
                                                                    if (zza.booleanValue()) {
                                                                        bitSet5.set(zzkh.zzatk.intValue());
                                                                        if (!(obj3 == null || zzkr3.zzavb == null)) {
                                                                            map18 = arrayMap3;
                                                                            zza(map18, zzkh.zzatk.intValue(), zzkr3.zzavb.longValue());
                                                                        }
                                                                    }
                                                                }
                                                                bitSet = bitSet5;
                                                                bitSet3 = bitSet6;
                                                                zzkr6 = zzkr3;
                                                                it = it5;
                                                                str2 = str5;
                                                                arrayMap5 = map14;
                                                                map9 = map15;
                                                                arrayMap6 = map17;
                                                                map7 = map19;
                                                                arrayMap2 = arrayMap3;
                                                                zzkxArr2 = zzkxArr;
                                                            } else {
                                                                zzgi().zzjc().zze("Event filter already evaluated true and it is not associated with a dynamic audience. audience ID, filter ID", Integer.valueOf(i6), zzkh.zzatk);
                                                                arrayMap5 = map14;
                                                                bitSet3 = bitSet4;
                                                                map9 = map15;
                                                                it = it4;
                                                                map7 = map16;
                                                                arrayMap6 = map17;
                                                                zzkxArr2 = zzkxArr;
                                                            }
                                                        }
                                                    }
                                                    obj3 = null;
                                                    if (bitSet.get(zzkh.zzatk.intValue())) {
                                                    }
                                                    it5 = it4;
                                                    zzkr3 = zzkr6;
                                                    zzkxArr4 = zzkxArr;
                                                    bitSet5 = bitSet;
                                                    str5 = str2;
                                                    arrayMap3 = arrayMap2;
                                                    map19 = map16;
                                                    bitSet6 = bitSet4;
                                                    zza = zza(zzkh, str2, zzksArr2, j3);
                                                    if (zza == null) {
                                                    }
                                                    zzgi().zzjc().zzg("Event filter result", zza == null ? zza : "null");
                                                    if (zza == null) {
                                                        bitSet6.set(zzkh.zzatk.intValue());
                                                        if (zza.booleanValue()) {
                                                            bitSet5.set(zzkh.zzatk.intValue());
                                                            map18 = arrayMap3;
                                                            zza(map18, zzkh.zzatk.intValue(), zzkr3.zzavb.longValue());
                                                        }
                                                    } else {
                                                        hashSet3.add(Integer.valueOf(i6));
                                                    }
                                                    bitSet = bitSet5;
                                                    bitSet3 = bitSet6;
                                                    zzkr6 = zzkr3;
                                                    it = it5;
                                                    str2 = str5;
                                                    arrayMap5 = map14;
                                                    map9 = map15;
                                                    arrayMap6 = map17;
                                                    map7 = map19;
                                                    arrayMap2 = arrayMap3;
                                                    zzkxArr2 = zzkxArr;
                                                } else {
                                                    bitSet5 = bitSet;
                                                    str5 = str2;
                                                    map18 = arrayMap2;
                                                    zzkr3 = zzkr6;
                                                    bitSet6 = bitSet4;
                                                    it5 = it4;
                                                    map19 = map16;
                                                    if (bitSet5.get(zzkh.zzatk.intValue())) {
                                                        zzgi().zzjc().zze("Event filter already evaluated true. audience ID, filter ID", Integer.valueOf(i6), zzkh.zzatk);
                                                    } else {
                                                        map12 = map18;
                                                        zza = zza(zzkh, str5, zzksArr2, j3);
                                                        if (zza != null) {
                                                        }
                                                        zzgi().zzjc().zzg("Event filter result", zza != null ? "null" : zza);
                                                        if (zza != null) {
                                                            hashSet3.add(Integer.valueOf(i6));
                                                        } else {
                                                            bitSet6.set(zzkh.zzatk.intValue());
                                                            if (zza.booleanValue()) {
                                                                bitSet5.set(zzkh.zzatk.intValue());
                                                            }
                                                        }
                                                        bitSet = bitSet5;
                                                        bitSet3 = bitSet6;
                                                        zzkr6 = zzkr3;
                                                        it = it5;
                                                        str2 = str5;
                                                        arrayMap2 = map12;
                                                        arrayMap5 = map14;
                                                        map9 = map15;
                                                        arrayMap6 = map17;
                                                        map7 = map19;
                                                        zzkxArr2 = zzkxArr;
                                                    }
                                                }
                                                bitSet = bitSet5;
                                                bitSet3 = bitSet6;
                                                zzkr6 = zzkr3;
                                                it = it5;
                                                arrayMap5 = map14;
                                                map9 = map15;
                                                arrayMap6 = map17;
                                                map7 = map19;
                                                zzkxArr2 = zzkxArr;
                                                arrayMap2 = map18;
                                                str2 = str5;
                                            }
                                        }
                                        bitSet5 = bitSet;
                                        str5 = str2;
                                        map12 = arrayMap2;
                                        zzkr3 = zzkr6;
                                        bitSet6 = bitSet4;
                                        it5 = it4;
                                        map19 = map16;
                                        map20 = map13;
                                        str6 = str;
                                        zzgi().zziy().zze("Invalid event filter ID. appId, id", zzfi.zzbp(str), String.valueOf(zzkh.zzatk));
                                        bitSet = bitSet5;
                                        bitSet3 = bitSet6;
                                        zzkr6 = zzkr3;
                                        it = it5;
                                        str2 = str5;
                                        arrayMap2 = map12;
                                        arrayMap5 = map14;
                                        map9 = map15;
                                        arrayMap6 = map17;
                                        map7 = map19;
                                        zzkxArr2 = zzkxArr;
                                    }
                                    map15 = map9;
                                    map6 = map7;
                                    map5 = arrayMap6;
                                    hashSet2 = hashSet3;
                                    arrayMap6 = map8;
                                    zzkr5 = zzkr6;
                                    it2 = it3;
                                    map26 = map13;
                                    map4 = map15;
                                    str4 = str;
                                }
                            }
                            arrayMap4 = arrayMap6;
                            str6 = str4;
                            map15 = map4;
                            map17 = map5;
                            hashSet3 = hashSet2;
                            map19 = map6;
                            map20 = map26;
                            zzkr7 = zzkr4;
                            l3 = l2;
                            j4 = j2;
                            i2 = i3 + 1;
                            zzkrArr2 = zzkrArr;
                            map = map20;
                            hashSet = hashSet3;
                            length2 = i7;
                            arrayMap9 = arrayMap4;
                            map2 = map15;
                            map3 = map17;
                            arrayMap5 = map19;
                            zzkxArr3 = zzkxArr;
                            str8 = str6;
                        }
                    } else {
                        i9 = i2;
                    }
                    obj = null;
                    if (obj == null) {
                        zzkr3 = zzkr8;
                        i3 = i9;
                        if (obj4 == null) {
                            str3 = str;
                        } else {
                            zzjf();
                            valueOf = Long.valueOf(0);
                            zzb = zzjz.zzb(zzkr3, "_epc");
                            if (zzb == null) {
                                zzb = valueOf;
                            }
                            j5 = ((Long) zzb).longValue();
                            if (j5 > 0) {
                                j6 = 0;
                                str11 = str;
                                l5 = l6;
                                l4 = l6;
                                j7 = j5;
                                j2 = j5;
                                str3 = str11;
                                zzjh().zza(str11, l5, j7, zzkr3);
                            } else {
                                zzgi().zziy().zzg("Complex event with zero extra param count. eventName", str9);
                                j6 = 0;
                                l4 = l6;
                                j2 = j5;
                                str3 = str;
                            }
                            str2 = str9;
                            zzksArr2 = zzksArr3;
                            zzkr4 = zzkr3;
                            l2 = l4;
                        }
                    } else {
                        zzjf();
                        str9 = (String) zzjz.zzb(zzkr8, "_en");
                        if (TextUtils.isEmpty(str9)) {
                            if (l6.longValue() != l3.longValue()) {
                                zza2 = zzjh().zza(str8, l6);
                                if (zza2 != null) {
                                    if (zza2.first == null) {
                                        zzkr7 = (zzkr) zza2.first;
                                        j4 = ((Long) zza2.second).longValue();
                                        zzjf();
                                        l3 = (Long) zzjz.zzb(zzkr7, "_eid");
                                    }
                                }
                                i3 = i9;
                                zzgi().zziv().zze("Extra parameter without existing main event. eventName, eventId", str9, l6);
                            }
                            zzkr = zzkr7;
                            l = l3;
                            j = j4 - 1;
                            if (j <= 0) {
                                zzjh = zzjh();
                                zzjh.zzab();
                                zzjh.zzgi().zzjc().zzg("Clearing complex main event info. appId", str8);
                                writableDatabase = zzjh.getWritableDatabase();
                                str10 = "delete from main_event_params where app_id=?";
                                zzkr2 = zzkr8;
                                strArr = new String[1];
                                strArr[0] = str8;
                                writableDatabase.execSQL(str10, strArr);
                                zzkr7 = zzkr;
                                j5 = 0;
                                i3 = i9;
                                zzkr3 = zzkr2;
                            } else {
                                zzkr3 = zzkr8;
                                j5 = 0;
                                zzkr9 = zzkr;
                                i3 = i9;
                                zzjh().zza(str8, l6, j, zzkr);
                                zzkr7 = zzkr9;
                            }
                            zzksArr = new zzks[(zzkr7.zzava.length + zzksArr3.length)];
                            i2 = 0;
                            for (length = 0; length < i4; length++) {
                                zzjf();
                                if (zzjz.zza(zzkr3, zzks.name) == null) {
                                    i5 = i2 + 1;
                                    zzksArr[i2] = zzks;
                                    i2 = i5;
                                }
                            }
                            if (i2 <= 0) {
                                intValue = zzksArr3.length;
                                i4 = 0;
                                while (i4 < intValue) {
                                    i6 = i2 + 1;
                                    zzksArr[i2] = zzksArr3[i4];
                                    i4++;
                                    i2 = i6;
                                }
                                if (i2 == zzksArr.length) {
                                    zzksArr = (zzks[]) Arrays.copyOf(zzksArr, i2);
                                }
                                zzkr4 = zzkr7;
                                zzksArr2 = zzksArr;
                                str2 = str9;
                            } else {
                                zzgi().zziy().zzg("No unique parameters in main event. eventName", str9);
                                zzkr4 = zzkr7;
                                str2 = str9;
                                zzksArr2 = zzksArr3;
                            }
                            l2 = l;
                            j2 = j;
                            str3 = str;
                        } else {
                            zzgi().zziv().zzg("Extra parameter without an event name. eventId", l6);
                            i3 = i9;
                        }
                        i7 = length2;
                        arrayMap4 = arrayMap9;
                        map19 = arrayMap5;
                        hashSet3 = hashSet;
                        map20 = map;
                        map15 = map2;
                        map17 = map3;
                        str6 = str;
                        i2 = i3 + 1;
                        zzkrArr2 = zzkrArr;
                        map = map20;
                        hashSet = hashSet3;
                        length2 = i7;
                        arrayMap9 = arrayMap4;
                        map2 = map15;
                        map3 = map17;
                        arrayMap5 = map19;
                        zzkxArr3 = zzkxArr;
                        str8 = str6;
                    }
                    zzf = zzjh().zzf(str3, zzkr3.name);
                    if (zzf != null) {
                        i7 = length2;
                        arrayMap = arrayMap9;
                        map6 = arrayMap5;
                        zzkr5 = zzkr3;
                        hashSet2 = hashSet;
                        map26 = map;
                        map4 = map2;
                        map5 = map3;
                        zzkxArr2 = zzkxArr;
                        zzf = zzf.zzim();
                    } else {
                        zzgi().zziy().zze("Event aggregate wasn't created during raw event logging. appId, event", zzfi.zzbp(str), zzgf().zzbm(str2));
                        i7 = length2;
                        arrayMap = arrayMap9;
                        map4 = map2;
                        map5 = map3;
                        map26 = map;
                        map6 = arrayMap5;
                        hashSet2 = hashSet;
                        zzkr5 = zzkr3;
                        zzkxArr2 = zzkxArr;
                        zzet = new zzet(str3, zzkr3.name, 1, 1, zzkr3.zzavb.longValue(), 0, null, null, null);
                    }
                    zzjh().zza(zzf);
                    j3 = zzf.zzahh;
                    arrayMap6 = arrayMap;
                    zzbi = (Map) arrayMap6.get(str2);
                    if (zzbi != null) {
                        str4 = str;
                    } else {
                        str4 = str;
                        zzbi = zzjh().zzk(str4, str2);
                        if (zzbi == null) {
                            zzbi = new ArrayMap();
                        }
                        arrayMap6.put(str2, zzbi);
                    }
                    arrayMap5 = zzbi;
                    it2 = arrayMap5.keySet().iterator();
                    while (it2.hasNext()) {
                        i6 = ((Integer) it2.next()).intValue();
                        hashSet3 = hashSet2;
                        if (hashSet3.contains(Integer.valueOf(i6))) {
                            map7 = map6;
                            zzkp2 = (zzkp) map7.get(Integer.valueOf(i6));
                            map8 = arrayMap6;
                            arrayMap6 = map5;
                            bitSet = (BitSet) arrayMap6.get(Integer.valueOf(i6));
                            zzkr6 = zzkr5;
                            it3 = it2;
                            map9 = map4;
                            bitSet2 = (BitSet) map9.get(Integer.valueOf(i6));
                            if (zzd) {
                                bitSet9 = bitSet2;
                                map10 = map26;
                                map11 = null;
                            } else {
                                bitSet9 = bitSet2;
                                map10 = map26;
                                map11 = (Map) map10.get(Integer.valueOf(i6));
                            }
                            if (zzkp2 != null) {
                                map13 = map10;
                                bitSet3 = bitSet9;
                                arrayMap2 = map11;
                            } else {
                                zzkp2 = new zzkp();
                                map7.put(Integer.valueOf(i6), zzkp2);
                                map12 = map11;
                                zzkp2.zzauv = Boolean.valueOf(true);
                                bitSet3 = new BitSet();
                                arrayMap6.put(Integer.valueOf(i6), bitSet3);
                                bitSet2 = new BitSet();
                                map9.put(Integer.valueOf(i6), bitSet2);
                                if (zzd) {
                                    bitSet10 = bitSet3;
                                    bitSet3 = bitSet2;
                                    map13 = map10;
                                    arrayMap2 = map12;
                                } else {
                                    arrayMap10 = new ArrayMap();
                                    bitSet10 = bitSet3;
                                    map10.put(Integer.valueOf(i6), arrayMap10);
                                    bitSet3 = bitSet2;
                                    map13 = map10;
                                    arrayMap2 = arrayMap10;
                                }
                                bitSet = bitSet10;
                            }
                            it = ((List) arrayMap5.get(Integer.valueOf(i6))).iterator();
                            while (it.hasNext()) {
                                map14 = arrayMap5;
                                zzkh = (zzkh) it.next();
                                bitSet4 = bitSet3;
                                map15 = map9;
                                if (zzgi().isLoggable(2)) {
                                    it4 = it;
                                    map16 = map7;
                                    map17 = arrayMap6;
                                } else {
                                    it4 = it;
                                    map16 = map7;
                                    map17 = arrayMap6;
                                    zzgi().zzjc().zzd("Evaluating filter. audience, filter, event", Integer.valueOf(i6), zzkh.zzatk, zzgf().zzbm(zzkh.zzatl));
                                    zzgi().zzjc().zzg("Filter definition", zzjf().zza(zzkh));
                                }
                                if (zzkh.zzatk != null) {
                                    if (zzkh.zzatk.intValue() > 256) {
                                        if (zzd) {
                                            bitSet5 = bitSet;
                                            str5 = str2;
                                            map18 = arrayMap2;
                                            zzkr3 = zzkr6;
                                            bitSet6 = bitSet4;
                                            it5 = it4;
                                            map19 = map16;
                                            if (bitSet5.get(zzkh.zzatk.intValue())) {
                                                map12 = map18;
                                                zza = zza(zzkh, str5, zzksArr2, j3);
                                                if (zza != null) {
                                                }
                                                zzgi().zzjc().zzg("Event filter result", zza != null ? "null" : zza);
                                                if (zza != null) {
                                                    bitSet6.set(zzkh.zzatk.intValue());
                                                    if (zza.booleanValue()) {
                                                        bitSet5.set(zzkh.zzatk.intValue());
                                                    }
                                                } else {
                                                    hashSet3.add(Integer.valueOf(i6));
                                                }
                                                bitSet = bitSet5;
                                                bitSet3 = bitSet6;
                                                zzkr6 = zzkr3;
                                                it = it5;
                                                str2 = str5;
                                                arrayMap2 = map12;
                                                arrayMap5 = map14;
                                                map9 = map15;
                                                arrayMap6 = map17;
                                                map7 = map19;
                                                zzkxArr2 = zzkxArr;
                                            } else {
                                                zzgi().zzjc().zze("Event filter already evaluated true. audience ID, filter ID", Integer.valueOf(i6), zzkh.zzatk);
                                            }
                                        } else {
                                            if (zzkh == null) {
                                            }
                                            obj3 = null;
                                            if (bitSet.get(zzkh.zzatk.intValue())) {
                                            }
                                            it5 = it4;
                                            zzkr3 = zzkr6;
                                            zzkxArr4 = zzkxArr;
                                            bitSet5 = bitSet;
                                            str5 = str2;
                                            arrayMap3 = arrayMap2;
                                            map19 = map16;
                                            bitSet6 = bitSet4;
                                            zza = zza(zzkh, str2, zzksArr2, j3);
                                            if (zza == null) {
                                            }
                                            zzgi().zzjc().zzg("Event filter result", zza == null ? zza : "null");
                                            if (zza == null) {
                                                hashSet3.add(Integer.valueOf(i6));
                                            } else {
                                                bitSet6.set(zzkh.zzatk.intValue());
                                                if (zza.booleanValue()) {
                                                    bitSet5.set(zzkh.zzatk.intValue());
                                                    map18 = arrayMap3;
                                                    zza(map18, zzkh.zzatk.intValue(), zzkr3.zzavb.longValue());
                                                }
                                            }
                                            bitSet = bitSet5;
                                            bitSet3 = bitSet6;
                                            zzkr6 = zzkr3;
                                            it = it5;
                                            str2 = str5;
                                            arrayMap5 = map14;
                                            map9 = map15;
                                            arrayMap6 = map17;
                                            map7 = map19;
                                            arrayMap2 = arrayMap3;
                                            zzkxArr2 = zzkxArr;
                                        }
                                        bitSet = bitSet5;
                                        bitSet3 = bitSet6;
                                        zzkr6 = zzkr3;
                                        it = it5;
                                        arrayMap5 = map14;
                                        map9 = map15;
                                        arrayMap6 = map17;
                                        map7 = map19;
                                        zzkxArr2 = zzkxArr;
                                        arrayMap2 = map18;
                                        str2 = str5;
                                    }
                                }
                                bitSet5 = bitSet;
                                str5 = str2;
                                map12 = arrayMap2;
                                zzkr3 = zzkr6;
                                bitSet6 = bitSet4;
                                it5 = it4;
                                map19 = map16;
                                map20 = map13;
                                str6 = str;
                                zzgi().zziy().zze("Invalid event filter ID. appId, id", zzfi.zzbp(str), String.valueOf(zzkh.zzatk));
                                bitSet = bitSet5;
                                bitSet3 = bitSet6;
                                zzkr6 = zzkr3;
                                it = it5;
                                str2 = str5;
                                arrayMap2 = map12;
                                arrayMap5 = map14;
                                map9 = map15;
                                arrayMap6 = map17;
                                map7 = map19;
                                zzkxArr2 = zzkxArr;
                            }
                            map15 = map9;
                            map6 = map7;
                            map5 = arrayMap6;
                            hashSet2 = hashSet3;
                            arrayMap6 = map8;
                            zzkr5 = zzkr6;
                            it2 = it3;
                            map26 = map13;
                            map4 = map15;
                            str4 = str;
                        } else {
                            zzgi().zzjc().zzg("Skipping failed audience ID", Integer.valueOf(i6));
                            hashSet2 = hashSet3;
                        }
                    }
                    arrayMap4 = arrayMap6;
                    str6 = str4;
                    map15 = map4;
                    map17 = map5;
                    hashSet3 = hashSet2;
                    map19 = map6;
                    map20 = map26;
                    zzkr7 = zzkr4;
                    l3 = l2;
                    j4 = j2;
                    i2 = i3 + 1;
                    zzkrArr2 = zzkrArr;
                    map = map20;
                    hashSet = hashSet3;
                    length2 = i7;
                    arrayMap9 = arrayMap4;
                    map2 = map15;
                    map3 = map17;
                    arrayMap5 = map19;
                    zzkxArr3 = zzkxArr;
                    str8 = str6;
                } else {
                    zzkr3 = zzkr8;
                    i3 = i2;
                    str3 = str8;
                }
                zzkr4 = zzkr7;
                l2 = l3;
                str2 = str9;
                zzksArr2 = zzksArr3;
                j2 = j4;
                zzf = zzjh().zzf(str3, zzkr3.name);
                if (zzf != null) {
                    zzgi().zziy().zze("Event aggregate wasn't created during raw event logging. appId, event", zzfi.zzbp(str), zzgf().zzbm(str2));
                    i7 = length2;
                    arrayMap = arrayMap9;
                    map4 = map2;
                    map5 = map3;
                    map26 = map;
                    map6 = arrayMap5;
                    hashSet2 = hashSet;
                    zzkr5 = zzkr3;
                    zzkxArr2 = zzkxArr;
                    zzet = new zzet(str3, zzkr3.name, 1, 1, zzkr3.zzavb.longValue(), 0, null, null, null);
                } else {
                    i7 = length2;
                    arrayMap = arrayMap9;
                    map6 = arrayMap5;
                    zzkr5 = zzkr3;
                    hashSet2 = hashSet;
                    map26 = map;
                    map4 = map2;
                    map5 = map3;
                    zzkxArr2 = zzkxArr;
                    zzf = zzf.zzim();
                }
                zzjh().zza(zzf);
                j3 = zzf.zzahh;
                arrayMap6 = arrayMap;
                zzbi = (Map) arrayMap6.get(str2);
                if (zzbi != null) {
                    str4 = str;
                    zzbi = zzjh().zzk(str4, str2);
                    if (zzbi == null) {
                        zzbi = new ArrayMap();
                    }
                    arrayMap6.put(str2, zzbi);
                } else {
                    str4 = str;
                }
                arrayMap5 = zzbi;
                it2 = arrayMap5.keySet().iterator();
                while (it2.hasNext()) {
                    i6 = ((Integer) it2.next()).intValue();
                    hashSet3 = hashSet2;
                    if (hashSet3.contains(Integer.valueOf(i6))) {
                        zzgi().zzjc().zzg("Skipping failed audience ID", Integer.valueOf(i6));
                        hashSet2 = hashSet3;
                    } else {
                        map7 = map6;
                        zzkp2 = (zzkp) map7.get(Integer.valueOf(i6));
                        map8 = arrayMap6;
                        arrayMap6 = map5;
                        bitSet = (BitSet) arrayMap6.get(Integer.valueOf(i6));
                        zzkr6 = zzkr5;
                        it3 = it2;
                        map9 = map4;
                        bitSet2 = (BitSet) map9.get(Integer.valueOf(i6));
                        if (zzd) {
                            bitSet9 = bitSet2;
                            map10 = map26;
                            map11 = (Map) map10.get(Integer.valueOf(i6));
                        } else {
                            bitSet9 = bitSet2;
                            map10 = map26;
                            map11 = null;
                        }
                        if (zzkp2 != null) {
                            zzkp2 = new zzkp();
                            map7.put(Integer.valueOf(i6), zzkp2);
                            map12 = map11;
                            zzkp2.zzauv = Boolean.valueOf(true);
                            bitSet3 = new BitSet();
                            arrayMap6.put(Integer.valueOf(i6), bitSet3);
                            bitSet2 = new BitSet();
                            map9.put(Integer.valueOf(i6), bitSet2);
                            if (zzd) {
                                arrayMap10 = new ArrayMap();
                                bitSet10 = bitSet3;
                                map10.put(Integer.valueOf(i6), arrayMap10);
                                bitSet3 = bitSet2;
                                map13 = map10;
                                arrayMap2 = arrayMap10;
                            } else {
                                bitSet10 = bitSet3;
                                bitSet3 = bitSet2;
                                map13 = map10;
                                arrayMap2 = map12;
                            }
                            bitSet = bitSet10;
                        } else {
                            map13 = map10;
                            bitSet3 = bitSet9;
                            arrayMap2 = map11;
                        }
                        it = ((List) arrayMap5.get(Integer.valueOf(i6))).iterator();
                        while (it.hasNext()) {
                            map14 = arrayMap5;
                            zzkh = (zzkh) it.next();
                            bitSet4 = bitSet3;
                            map15 = map9;
                            if (zzgi().isLoggable(2)) {
                                it4 = it;
                                map16 = map7;
                                map17 = arrayMap6;
                                zzgi().zzjc().zzd("Evaluating filter. audience, filter, event", Integer.valueOf(i6), zzkh.zzatk, zzgf().zzbm(zzkh.zzatl));
                                zzgi().zzjc().zzg("Filter definition", zzjf().zza(zzkh));
                            } else {
                                it4 = it;
                                map16 = map7;
                                map17 = arrayMap6;
                            }
                            if (zzkh.zzatk != null) {
                                if (zzkh.zzatk.intValue() > 256) {
                                    if (zzd) {
                                        if (zzkh == null) {
                                        }
                                        obj3 = null;
                                        if (bitSet.get(zzkh.zzatk.intValue())) {
                                        }
                                        it5 = it4;
                                        zzkr3 = zzkr6;
                                        zzkxArr4 = zzkxArr;
                                        bitSet5 = bitSet;
                                        str5 = str2;
                                        arrayMap3 = arrayMap2;
                                        map19 = map16;
                                        bitSet6 = bitSet4;
                                        zza = zza(zzkh, str2, zzksArr2, j3);
                                        if (zza == null) {
                                        }
                                        zzgi().zzjc().zzg("Event filter result", zza == null ? zza : "null");
                                        if (zza == null) {
                                            bitSet6.set(zzkh.zzatk.intValue());
                                            if (zza.booleanValue()) {
                                                bitSet5.set(zzkh.zzatk.intValue());
                                                map18 = arrayMap3;
                                                zza(map18, zzkh.zzatk.intValue(), zzkr3.zzavb.longValue());
                                            }
                                        } else {
                                            hashSet3.add(Integer.valueOf(i6));
                                        }
                                        bitSet = bitSet5;
                                        bitSet3 = bitSet6;
                                        zzkr6 = zzkr3;
                                        it = it5;
                                        str2 = str5;
                                        arrayMap5 = map14;
                                        map9 = map15;
                                        arrayMap6 = map17;
                                        map7 = map19;
                                        arrayMap2 = arrayMap3;
                                        zzkxArr2 = zzkxArr;
                                    } else {
                                        bitSet5 = bitSet;
                                        str5 = str2;
                                        map18 = arrayMap2;
                                        zzkr3 = zzkr6;
                                        bitSet6 = bitSet4;
                                        it5 = it4;
                                        map19 = map16;
                                        if (bitSet5.get(zzkh.zzatk.intValue())) {
                                            zzgi().zzjc().zze("Event filter already evaluated true. audience ID, filter ID", Integer.valueOf(i6), zzkh.zzatk);
                                        } else {
                                            map12 = map18;
                                            zza = zza(zzkh, str5, zzksArr2, j3);
                                            if (zza != null) {
                                            }
                                            zzgi().zzjc().zzg("Event filter result", zza != null ? "null" : zza);
                                            if (zza != null) {
                                                hashSet3.add(Integer.valueOf(i6));
                                            } else {
                                                bitSet6.set(zzkh.zzatk.intValue());
                                                if (zza.booleanValue()) {
                                                    bitSet5.set(zzkh.zzatk.intValue());
                                                }
                                            }
                                            bitSet = bitSet5;
                                            bitSet3 = bitSet6;
                                            zzkr6 = zzkr3;
                                            it = it5;
                                            str2 = str5;
                                            arrayMap2 = map12;
                                            arrayMap5 = map14;
                                            map9 = map15;
                                            arrayMap6 = map17;
                                            map7 = map19;
                                            zzkxArr2 = zzkxArr;
                                        }
                                    }
                                    bitSet = bitSet5;
                                    bitSet3 = bitSet6;
                                    zzkr6 = zzkr3;
                                    it = it5;
                                    arrayMap5 = map14;
                                    map9 = map15;
                                    arrayMap6 = map17;
                                    map7 = map19;
                                    zzkxArr2 = zzkxArr;
                                    arrayMap2 = map18;
                                    str2 = str5;
                                }
                            }
                            bitSet5 = bitSet;
                            str5 = str2;
                            map12 = arrayMap2;
                            zzkr3 = zzkr6;
                            bitSet6 = bitSet4;
                            it5 = it4;
                            map19 = map16;
                            map20 = map13;
                            str6 = str;
                            zzgi().zziy().zze("Invalid event filter ID. appId, id", zzfi.zzbp(str), String.valueOf(zzkh.zzatk));
                            bitSet = bitSet5;
                            bitSet3 = bitSet6;
                            zzkr6 = zzkr3;
                            it = it5;
                            str2 = str5;
                            arrayMap2 = map12;
                            arrayMap5 = map14;
                            map9 = map15;
                            arrayMap6 = map17;
                            map7 = map19;
                            zzkxArr2 = zzkxArr;
                        }
                        map15 = map9;
                        map6 = map7;
                        map5 = arrayMap6;
                        hashSet2 = hashSet3;
                        arrayMap6 = map8;
                        zzkr5 = zzkr6;
                        it2 = it3;
                        map26 = map13;
                        map4 = map15;
                        str4 = str;
                    }
                }
                arrayMap4 = arrayMap6;
                str6 = str4;
                map15 = map4;
                map17 = map5;
                hashSet3 = hashSet2;
                map19 = map6;
                map20 = map26;
                zzkr7 = zzkr4;
                l3 = l2;
                j4 = j2;
                i2 = i3 + 1;
                zzkrArr2 = zzkrArr;
                map = map20;
                hashSet = hashSet3;
                length2 = i7;
                arrayMap9 = arrayMap4;
                map2 = map15;
                map3 = map17;
                arrayMap5 = map19;
                zzkxArr3 = zzkxArr;
                str8 = str6;
            }
        }
        map19 = arrayMap5;
        str6 = str8;
        hashSet3 = hashSet;
        map20 = map;
        map15 = map2;
        map17 = map3;
        zzkx[] zzkxArr5 = zzkxArr3;
        if (zzkxArr5 != null) {
            map11 = new ArrayMap();
            length = zzkxArr5.length;
            i2 = 0;
            while (i2 < length) {
                Map map27;
                int i10;
                Map map28;
                zzkx zzkx = zzkxArr5[i2];
                arrayMap7 = (Map) map11.get(zzkx.name);
                if (arrayMap7 == null) {
                    arrayMap7 = zzjh().zzl(str6, zzkx.name);
                    if (arrayMap7 == null) {
                        arrayMap7 = new ArrayMap();
                    }
                    map11.put(zzkx.name, arrayMap7);
                }
                Iterator it7 = arrayMap7.keySet().iterator();
                while (it7.hasNext()) {
                    i = ((Integer) it7.next()).intValue();
                    if (hashSet3.contains(Integer.valueOf(i))) {
                        zzgi().zzjc().zzg("Skipping failed audience ID", Integer.valueOf(i));
                    } else {
                        BitSet bitSet11;
                        BitSet bitSet12;
                        Iterator it8;
                        map9 = map19;
                        zzkp zzkp3 = (zzkp) map9.get(Integer.valueOf(i));
                        zzbi = map17;
                        BitSet bitSet13 = (BitSet) zzbi.get(Integer.valueOf(i));
                        map27 = map11;
                        i10 = length;
                        map18 = map15;
                        bitSet2 = (BitSet) map18.get(Integer.valueOf(i));
                        if (zzd) {
                            bitSet11 = bitSet2;
                            map11 = (Map) map20.get(Integer.valueOf(i));
                        } else {
                            bitSet11 = bitSet2;
                            map11 = null;
                        }
                        Map map29;
                        if (zzkp3 == null) {
                            BitSet bitSet14;
                            zzkp3 = new zzkp();
                            map9.put(Integer.valueOf(i), zzkp3);
                            map29 = map11;
                            zzkp3.zzauv = Boolean.valueOf(true);
                            bitSet2 = new BitSet();
                            zzbi.put(Integer.valueOf(i), bitSet2);
                            bitSet12 = new BitSet();
                            map18.put(Integer.valueOf(i), bitSet12);
                            if (zzd) {
                                ArrayMap arrayMap11 = new ArrayMap();
                                bitSet14 = bitSet2;
                                map20.put(Integer.valueOf(i), arrayMap11);
                                it8 = it7;
                                map11 = arrayMap11;
                            } else {
                                bitSet14 = bitSet2;
                                it8 = it7;
                                map11 = map29;
                            }
                            bitSet13 = bitSet14;
                        } else {
                            map29 = map11;
                            it8 = it7;
                            bitSet12 = bitSet11;
                        }
                        it7 = ((List) arrayMap7.get(Integer.valueOf(i))).iterator();
                        while (it7.hasNext()) {
                            int i11;
                            Map map30 = arrayMap7;
                            zzkk zzkk = (zzkk) it7.next();
                            Iterator it9 = it7;
                            map21 = map20;
                            if (zzgi().isLoggable(2)) {
                                map22 = map18;
                                map23 = map9;
                                map28 = zzbi;
                                i11 = i2;
                                zzgi().zzjc().zzd("Evaluating filter. audience, filter, property", Integer.valueOf(i), zzkk.zzatk, zzgf().zzbo(zzkk.zzauc));
                                zzgi().zzjc().zzg("Filter definition", zzjf().zza(zzkk));
                            } else {
                                map28 = zzbi;
                                map22 = map18;
                                i11 = i2;
                                map23 = map9;
                            }
                            if (zzkk.zzatk != null) {
                                if (zzkk.zzatk.intValue() <= 256) {
                                    zzfk zzjc;
                                    if (zzd) {
                                        zzb = (zzkk == null || zzkk.zzaud == null || zzkk.zzaud.zzatu == null || !zzkk.zzaud.zzatu.booleanValue()) ? null : 1;
                                        if (bitSet13.get(zzkk.zzatk.intValue()) && zzb == null) {
                                            zzjc = zzgi().zzjc();
                                            str2 = "Property filter already evaluated true and it is not associated with a dynamic audience. audience ID, filter ID";
                                        } else {
                                            Boolean zza3 = zza(zzkk, zzkx);
                                            zzgi().zzjc().zzg("Property filter result", zza3 == null ? "null" : zza3);
                                            if (zza3 == null) {
                                                hashSet3.add(Integer.valueOf(i));
                                                arrayMap7 = map30;
                                                it7 = it9;
                                                map20 = map21;
                                                map18 = map22;
                                                map9 = map23;
                                                zzbi = map28;
                                                i2 = i11;
                                            } else {
                                                bitSet12.set(zzkk.zzatk.intValue());
                                                bitSet13.set(zzkk.zzatk.intValue(), zza3.booleanValue());
                                                if (!(!zza3.booleanValue() || zzb == null || zzkx.zzaws == null)) {
                                                    zza(map11, zzkk.zzatk.intValue(), zzkx.zzaws.longValue());
                                                }
                                                arrayMap7 = map30;
                                                it7 = it9;
                                                map20 = map21;
                                                map18 = map22;
                                                map9 = map23;
                                                zzbi = map28;
                                                i2 = i11;
                                            }
                                        }
                                    } else if (bitSet13.get(zzkk.zzatk.intValue())) {
                                        zzjc = zzgi().zzjc();
                                        str2 = "Property filter already evaluated true. audience ID, filter ID";
                                    } else {
                                        zza = zza(zzkk, zzkx);
                                        zzgi().zzjc().zzg("Property filter result", zza == null ? "null" : zza);
                                        if (zza != null) {
                                            bitSet12.set(zzkk.zzatk.intValue());
                                            if (zza.booleanValue()) {
                                                bitSet13.set(zzkk.zzatk.intValue());
                                            }
                                            arrayMap7 = map30;
                                            it7 = it9;
                                            map20 = map21;
                                            map18 = map22;
                                            map9 = map23;
                                            zzbi = map28;
                                            i2 = i11;
                                        }
                                        hashSet3.add(Integer.valueOf(i));
                                        arrayMap7 = map30;
                                        it7 = it9;
                                        map20 = map21;
                                        map18 = map22;
                                        map9 = map23;
                                        zzbi = map28;
                                        i2 = i11;
                                    }
                                    zzjc.zze(str2, Integer.valueOf(i), zzkk.zzatk);
                                    arrayMap7 = map30;
                                    it7 = it9;
                                    map20 = map21;
                                    map18 = map22;
                                    map9 = map23;
                                    zzbi = map28;
                                    i2 = i11;
                                }
                            }
                            zzgi().zziy().zze("Invalid property filter ID. appId, id", zzfi.zzbp(str), String.valueOf(zzkk.zzatk));
                            hashSet3.add(Integer.valueOf(i));
                            map11 = map27;
                            length = i10;
                            it7 = it8;
                            arrayMap7 = map30;
                            map20 = map21;
                            map15 = map22;
                            map19 = map23;
                            map17 = map28;
                            i2 = i11;
                        }
                        map21 = map20;
                        map17 = zzbi;
                        map15 = map18;
                        map19 = map9;
                        map11 = map27;
                        length = i10;
                        it7 = it8;
                        zzkxArr5 = zzkxArr;
                    }
                }
                map27 = map11;
                i10 = length;
                map22 = map15;
                map28 = map17;
                map23 = map19;
                i2++;
                map20 = map20;
                zzkxArr5 = zzkxArr;
            }
        }
        map21 = map20;
        map22 = map15;
        map23 = map19;
        zzbi = map17;
        zzkp[] zzkpArr = new zzkp[zzbi.size()];
        length = 0;
        for (Integer intValue2 : zzbi.keySet()) {
            i2 = intValue2.intValue();
            if (!hashSet3.contains(Integer.valueOf(i2))) {
                arrayMap7 = map23;
                zzkp zzkp4 = (zzkp) arrayMap7.get(Integer.valueOf(i2));
                if (zzkp4 == null) {
                    zzkp4 = new zzkp();
                }
                int i12 = length + 1;
                zzkpArr[length] = zzkp4;
                zzkp4.zzate = Integer.valueOf(i2);
                zzkp4.zzaut = new zzkv();
                zzkp4.zzaut.zzawm = zzjz.zza((BitSet) zzbi.get(Integer.valueOf(i2)));
                arrayMap5 = map22;
                zzkp4.zzaut.zzawl = zzjz.zza((BitSet) arrayMap5.get(Integer.valueOf(i2)));
                if (zzd) {
                    map9 = map21;
                    zzkp4.zzaut.zzawn = zzd((Map) map9.get(Integer.valueOf(i2)));
                } else {
                    map9 = map21;
                }
                zzhi zzjh2 = zzjh();
                zzacj zzacj = zzkp4.zzaut;
                zzjh2.zzch();
                zzjh2.zzab();
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotNull(zzacj);
                try {
                    byte[] bArr = new byte[zzacj.zzwb()];
                    map24 = zzbi;
                    try {
                        zzacb zzb2 = zzacb.zzb(bArr, 0, bArr.length);
                        zzacj.zza(zzb2);
                        zzb2.zzvt();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("app_id", str6);
                        contentValues.put("audience_id", Integer.valueOf(i2));
                        contentValues.put("current_results", bArr);
                        try {
                            try {
                                if (zzjh2.getWritableDatabase().insertWithOnConflict("audience_filter_values", null, contentValues, 5) == -1) {
                                    zzjh2.zzgi().zziv().zzg("Failed to insert filter results (got -1). appId", zzfi.zzbp(str));
                                }
                            } catch (SQLiteException e6) {
                                e = e6;
                                obj = e;
                                zziv = zzjh2.zzgi().zziv();
                                str7 = "Error storing filter results. appId";
                                zziv.zze(str7, zzfi.zzbp(str), obj);
                                map23 = arrayMap7;
                                length = i12;
                                map22 = arrayMap5;
                                map21 = map9;
                                zzbi = map24;
                            }
                        } catch (SQLiteException e7) {
                            e = e7;
                            obj = e;
                            zziv = zzjh2.zzgi().zziv();
                            str7 = "Error storing filter results. appId";
                            zziv.zze(str7, zzfi.zzbp(str), obj);
                            map23 = arrayMap7;
                            length = i12;
                            map22 = arrayMap5;
                            map21 = map9;
                            zzbi = map24;
                        }
                    } catch (IOException e8) {
                        e2 = e8;
                        obj = e2;
                        zziv = zzjh2.zzgi().zziv();
                        str7 = "Configuration loss. Failed to serialize filter results. appId";
                        zziv.zze(str7, zzfi.zzbp(str), obj);
                        map23 = arrayMap7;
                        length = i12;
                        map22 = arrayMap5;
                        map21 = map9;
                        zzbi = map24;
                    }
                } catch (IOException e9) {
                    e2 = e9;
                    map24 = zzbi;
                    obj = e2;
                    zziv = zzjh2.zzgi().zziv();
                    str7 = "Configuration loss. Failed to serialize filter results. appId";
                    zziv.zze(str7, zzfi.zzbp(str), obj);
                    map23 = arrayMap7;
                    length = i12;
                    map22 = arrayMap5;
                    map21 = map9;
                    zzbi = map24;
                }
                map23 = arrayMap7;
                length = i12;
                map22 = arrayMap5;
                map21 = map9;
                zzbi = map24;
            }
        }
        return (zzkp[]) Arrays.copyOf(zzkpArr, length);
    }

    protected final boolean zzgn() {
        return false;
    }
}
