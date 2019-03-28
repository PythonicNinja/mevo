package com.google.android.gms.internal.measurement;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class zzaas {
    static String zza(zzaaq zzaaq, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("# ");
        stringBuilder.append(str);
        zza(zzaaq, stringBuilder, 0);
        return stringBuilder.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zza(com.google.android.gms.internal.measurement.zzaaq r12, java.lang.StringBuilder r13, int r14) {
        /*
        r0 = new java.util.HashMap;
        r0.<init>();
        r1 = new java.util.HashMap;
        r1.<init>();
        r2 = new java.util.TreeSet;
        r2.<init>();
        r3 = r12.getClass();
        r3 = r3.getDeclaredMethods();
        r4 = r3.length;
        r5 = 0;
        r6 = 0;
    L_0x001a:
        if (r6 >= r4) goto L_0x0049;
    L_0x001c:
        r7 = r3[r6];
        r8 = r7.getName();
        r1.put(r8, r7);
        r8 = r7.getParameterTypes();
        r8 = r8.length;
        if (r8 != 0) goto L_0x0046;
    L_0x002c:
        r8 = r7.getName();
        r0.put(r8, r7);
        r8 = r7.getName();
        r9 = "get";
        r8 = r8.startsWith(r9);
        if (r8 == 0) goto L_0x0046;
    L_0x003f:
        r7 = r7.getName();
        r2.add(r7);
    L_0x0046:
        r6 = r6 + 1;
        goto L_0x001a;
    L_0x0049:
        r2 = r2.iterator();
    L_0x004d:
        r3 = r2.hasNext();
        if (r3 == 0) goto L_0x027b;
    L_0x0053:
        r3 = r2.next();
        r3 = (java.lang.String) r3;
        r4 = "get";
        r6 = "";
        r4 = r3.replaceFirst(r4, r6);
        r6 = "List";
        r6 = r4.endsWith(r6);
        r7 = 1;
        if (r6 == 0) goto L_0x00c7;
    L_0x006a:
        r6 = "OrBuilderList";
        r6 = r4.endsWith(r6);
        if (r6 != 0) goto L_0x00c7;
    L_0x0072:
        r6 = "List";
        r6 = r4.equals(r6);
        if (r6 != 0) goto L_0x00c7;
    L_0x007a:
        r6 = r4.substring(r5, r7);
        r6 = r6.toLowerCase();
        r6 = java.lang.String.valueOf(r6);
        r8 = r4.length();
        r8 = r8 + -4;
        r8 = r4.substring(r7, r8);
        r8 = java.lang.String.valueOf(r8);
        r9 = r8.length();
        if (r9 == 0) goto L_0x009f;
    L_0x009a:
        r6 = r6.concat(r8);
        goto L_0x00a5;
    L_0x009f:
        r8 = new java.lang.String;
        r8.<init>(r6);
        r6 = r8;
    L_0x00a5:
        r8 = r0.get(r3);
        r8 = (java.lang.reflect.Method) r8;
        if (r8 == 0) goto L_0x00c7;
    L_0x00ad:
        r9 = r8.getReturnType();
        r10 = java.util.List.class;
        r9 = r9.equals(r10);
        if (r9 == 0) goto L_0x00c7;
    L_0x00b9:
        r3 = zzfp(r6);
        r4 = new java.lang.Object[r5];
        r4 = com.google.android.gms.internal.measurement.zzzv.zza(r8, r12, r4);
        zzb(r13, r14, r3, r4);
        goto L_0x004d;
    L_0x00c7:
        r6 = "Map";
        r6 = r4.endsWith(r6);
        if (r6 == 0) goto L_0x0137;
    L_0x00cf:
        r6 = "Map";
        r6 = r4.equals(r6);
        if (r6 != 0) goto L_0x0137;
    L_0x00d7:
        r6 = r4.substring(r5, r7);
        r6 = r6.toLowerCase();
        r6 = java.lang.String.valueOf(r6);
        r8 = r4.length();
        r8 = r8 + -3;
        r8 = r4.substring(r7, r8);
        r8 = java.lang.String.valueOf(r8);
        r9 = r8.length();
        if (r9 == 0) goto L_0x00fc;
    L_0x00f7:
        r6 = r6.concat(r8);
        goto L_0x0102;
    L_0x00fc:
        r8 = new java.lang.String;
        r8.<init>(r6);
        r6 = r8;
    L_0x0102:
        r3 = r0.get(r3);
        r3 = (java.lang.reflect.Method) r3;
        if (r3 == 0) goto L_0x0137;
    L_0x010a:
        r8 = r3.getReturnType();
        r9 = java.util.Map.class;
        r8 = r8.equals(r9);
        if (r8 == 0) goto L_0x0137;
    L_0x0116:
        r8 = java.lang.Deprecated.class;
        r8 = r3.isAnnotationPresent(r8);
        if (r8 != 0) goto L_0x0137;
    L_0x011e:
        r8 = r3.getModifiers();
        r8 = java.lang.reflect.Modifier.isPublic(r8);
        if (r8 == 0) goto L_0x0137;
    L_0x0128:
        r4 = zzfp(r6);
        r6 = new java.lang.Object[r5];
        r3 = com.google.android.gms.internal.measurement.zzzv.zza(r3, r12, r6);
        zzb(r13, r14, r4, r3);
        goto L_0x004d;
    L_0x0137:
        r3 = "set";
        r6 = java.lang.String.valueOf(r4);
        r8 = r6.length();
        if (r8 == 0) goto L_0x0148;
    L_0x0143:
        r3 = r3.concat(r6);
        goto L_0x014e;
    L_0x0148:
        r6 = new java.lang.String;
        r6.<init>(r3);
        r3 = r6;
    L_0x014e:
        r3 = r1.get(r3);
        r3 = (java.lang.reflect.Method) r3;
        if (r3 == 0) goto L_0x004d;
    L_0x0156:
        r3 = "Bytes";
        r3 = r4.endsWith(r3);
        if (r3 == 0) goto L_0x0185;
    L_0x015e:
        r3 = "get";
        r6 = r4.length();
        r6 = r6 + -5;
        r6 = r4.substring(r5, r6);
        r6 = java.lang.String.valueOf(r6);
        r8 = r6.length();
        if (r8 == 0) goto L_0x0179;
    L_0x0174:
        r3 = r3.concat(r6);
        goto L_0x017f;
    L_0x0179:
        r6 = new java.lang.String;
        r6.<init>(r3);
        r3 = r6;
    L_0x017f:
        r3 = r0.containsKey(r3);
        if (r3 != 0) goto L_0x004d;
    L_0x0185:
        r3 = r4.substring(r5, r7);
        r3 = r3.toLowerCase();
        r3 = java.lang.String.valueOf(r3);
        r6 = r4.substring(r7);
        r6 = java.lang.String.valueOf(r6);
        r8 = r6.length();
        if (r8 == 0) goto L_0x01a4;
    L_0x019f:
        r3 = r3.concat(r6);
        goto L_0x01aa;
    L_0x01a4:
        r6 = new java.lang.String;
        r6.<init>(r3);
        r3 = r6;
    L_0x01aa:
        r6 = "get";
        r8 = java.lang.String.valueOf(r4);
        r9 = r8.length();
        if (r9 == 0) goto L_0x01bb;
    L_0x01b6:
        r6 = r6.concat(r8);
        goto L_0x01c1;
    L_0x01bb:
        r8 = new java.lang.String;
        r8.<init>(r6);
        r6 = r8;
    L_0x01c1:
        r6 = r0.get(r6);
        r6 = (java.lang.reflect.Method) r6;
        r8 = "has";
        r4 = java.lang.String.valueOf(r4);
        r9 = r4.length();
        if (r9 == 0) goto L_0x01d8;
    L_0x01d3:
        r4 = r8.concat(r4);
        goto L_0x01dd;
    L_0x01d8:
        r4 = new java.lang.String;
        r4.<init>(r8);
    L_0x01dd:
        r4 = r0.get(r4);
        r4 = (java.lang.reflect.Method) r4;
        if (r6 == 0) goto L_0x004d;
    L_0x01e5:
        r8 = new java.lang.Object[r5];
        r6 = com.google.android.gms.internal.measurement.zzzv.zza(r6, r12, r8);
        if (r4 != 0) goto L_0x0264;
    L_0x01ed:
        r4 = r6 instanceof java.lang.Boolean;
        if (r4 == 0) goto L_0x01ff;
    L_0x01f1:
        r4 = r6;
        r4 = (java.lang.Boolean) r4;
        r4 = r4.booleanValue();
        if (r4 != 0) goto L_0x01fd;
    L_0x01fa:
        r4 = 1;
        goto L_0x025e;
    L_0x01fd:
        r4 = 0;
        goto L_0x025e;
    L_0x01ff:
        r4 = r6 instanceof java.lang.Integer;
        if (r4 == 0) goto L_0x020d;
    L_0x0203:
        r4 = r6;
        r4 = (java.lang.Integer) r4;
        r4 = r4.intValue();
        if (r4 != 0) goto L_0x01fd;
    L_0x020c:
        goto L_0x01fa;
    L_0x020d:
        r4 = r6 instanceof java.lang.Float;
        if (r4 == 0) goto L_0x021e;
    L_0x0211:
        r4 = r6;
        r4 = (java.lang.Float) r4;
        r4 = r4.floatValue();
        r8 = 0;
        r4 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1));
        if (r4 != 0) goto L_0x01fd;
    L_0x021d:
        goto L_0x01fa;
    L_0x021e:
        r4 = r6 instanceof java.lang.Double;
        if (r4 == 0) goto L_0x0230;
    L_0x0222:
        r4 = r6;
        r4 = (java.lang.Double) r4;
        r8 = r4.doubleValue();
        r10 = 0;
        r4 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r4 != 0) goto L_0x01fd;
    L_0x022f:
        goto L_0x01fa;
    L_0x0230:
        r4 = r6 instanceof java.lang.String;
        if (r4 == 0) goto L_0x023b;
    L_0x0234:
        r4 = "";
    L_0x0236:
        r4 = r6.equals(r4);
        goto L_0x025e;
    L_0x023b:
        r4 = r6 instanceof com.google.android.gms.internal.measurement.zzzb;
        if (r4 == 0) goto L_0x0242;
    L_0x023f:
        r4 = com.google.android.gms.internal.measurement.zzzb.zzbte;
        goto L_0x0236;
    L_0x0242:
        r4 = r6 instanceof com.google.android.gms.internal.measurement.zzaaq;
        if (r4 == 0) goto L_0x0250;
    L_0x0246:
        r4 = r6;
        r4 = (com.google.android.gms.internal.measurement.zzaaq) r4;
        r4 = r4.zzuo();
        if (r6 != r4) goto L_0x01fd;
    L_0x024f:
        goto L_0x01fa;
    L_0x0250:
        r4 = r6 instanceof java.lang.Enum;
        if (r4 == 0) goto L_0x01fd;
    L_0x0254:
        r4 = r6;
        r4 = (java.lang.Enum) r4;
        r4 = r4.ordinal();
        if (r4 != 0) goto L_0x01fd;
    L_0x025d:
        goto L_0x01fa;
    L_0x025e:
        if (r4 != 0) goto L_0x0262;
    L_0x0260:
        r4 = 1;
        goto L_0x0270;
    L_0x0262:
        r4 = 0;
        goto L_0x0270;
    L_0x0264:
        r7 = new java.lang.Object[r5];
        r4 = com.google.android.gms.internal.measurement.zzzv.zza(r4, r12, r7);
        r4 = (java.lang.Boolean) r4;
        r4 = r4.booleanValue();
    L_0x0270:
        if (r4 == 0) goto L_0x004d;
    L_0x0272:
        r3 = zzfp(r3);
        zzb(r13, r14, r3, r6);
        goto L_0x004d;
    L_0x027b:
        r13 = r12 instanceof com.google.android.gms.internal.measurement.zzzv.zza;
        if (r13 == 0) goto L_0x029d;
    L_0x027f:
        r13 = r12;
        r13 = (com.google.android.gms.internal.measurement.zzzv.zza) r13;
        r13 = r13.zzbui;
        r13 = r13.iterator();
        r14 = r13.hasNext();
        if (r14 == 0) goto L_0x029d;
    L_0x028e:
        r12 = r13.next();
        r12 = (java.util.Map.Entry) r12;
        r12.getKey();
        r12 = new java.lang.NoSuchMethodError;
        r12.<init>();
        throw r12;
    L_0x029d:
        r12 = (com.google.android.gms.internal.measurement.zzzv) r12;
        r13 = r12.zzbuf;
        if (r13 == 0) goto L_0x02a5;
    L_0x02a3:
        r12 = r12.zzbuf;
    L_0x02a5:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzaas.zza(com.google.android.gms.internal.measurement.zzaaq, java.lang.StringBuilder, int):void");
    }

    static final void zzb(StringBuilder stringBuilder, int i, String str, Object obj) {
        if (obj instanceof List) {
            for (Object zzb : (List) obj) {
                zzb(stringBuilder, i, str, zzb);
            }
        } else if (obj instanceof Map) {
            for (Entry zzb2 : ((Map) obj).entrySet()) {
                zzb(stringBuilder, i, str, zzb2);
            }
        } else {
            stringBuilder.append('\n');
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                stringBuilder.append(' ');
            }
            stringBuilder.append(str);
            if (obj instanceof String) {
                stringBuilder.append(": \"");
                stringBuilder.append(zzabl.zza(zzzb.zzfn((String) obj)));
                stringBuilder.append('\"');
            } else if (obj instanceof zzzb) {
                stringBuilder.append(": \"");
                stringBuilder.append(zzabl.zza((zzzb) obj));
                stringBuilder.append('\"');
            } else if (obj instanceof zzzv) {
                stringBuilder.append(" {");
                zza((zzzv) obj, stringBuilder, i + 2);
                stringBuilder.append("\n");
                while (i2 < i) {
                    stringBuilder.append(' ');
                    i2++;
                }
                stringBuilder.append("}");
            } else if (obj instanceof Entry) {
                stringBuilder.append(" {");
                Entry entry = (Entry) obj;
                int i4 = i + 2;
                zzb(stringBuilder, i4, "key", entry.getKey());
                zzb(stringBuilder, i4, Param.VALUE, entry.getValue());
                stringBuilder.append("\n");
                while (i2 < i) {
                    stringBuilder.append(' ');
                    i2++;
                }
                stringBuilder.append("}");
            } else {
                stringBuilder.append(": ");
                stringBuilder.append(obj.toString());
            }
        }
    }

    private static final String zzfp(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt)) {
                stringBuilder.append(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
            }
            stringBuilder.append(Character.toLowerCase(charAt));
        }
        return stringBuilder.toString();
    }
}
