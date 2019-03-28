package com.google.android.gms.internal.measurement;

import java.lang.reflect.InvocationTargetException;

public final class zzack {
    private static void zza(java.lang.String r12, java.lang.Object r13, java.lang.StringBuffer r14, java.lang.StringBuffer r15) throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
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
        if (r13 == 0) goto L_0x01d1;
    L_0x0002:
        r0 = r13 instanceof com.google.android.gms.internal.measurement.zzacj;
        r1 = 1;
        r2 = 0;
        if (r0 == 0) goto L_0x0108;
    L_0x0008:
        r0 = r14.length();
        if (r12 == 0) goto L_0x0022;
    L_0x000e:
        r15.append(r14);
        r3 = zzfs(r12);
        r15.append(r3);
        r3 = " <\n";
        r15.append(r3);
        r3 = "  ";
        r14.append(r3);
    L_0x0022:
        r3 = r13.getClass();
        r4 = r3.getFields();
        r5 = r4.length;
        r6 = 0;
    L_0x002c:
        if (r6 >= r5) goto L_0x008b;
    L_0x002e:
        r7 = r4[r6];
        r8 = r7.getModifiers();
        r9 = r7.getName();
        r10 = "cachedSize";
        r10 = r10.equals(r9);
        if (r10 != 0) goto L_0x0088;
    L_0x0040:
        r10 = r8 & 1;
        if (r10 != r1) goto L_0x0088;
    L_0x0044:
        r8 = r8 & 8;
        r10 = 8;
        if (r8 == r10) goto L_0x0088;
    L_0x004a:
        r8 = "_";
        r8 = r9.startsWith(r8);
        if (r8 != 0) goto L_0x0088;
    L_0x0052:
        r8 = "_";
        r8 = r9.endsWith(r8);
        if (r8 != 0) goto L_0x0088;
    L_0x005a:
        r8 = r7.getType();
        r7 = r7.get(r13);
        r10 = r8.isArray();
        if (r10 == 0) goto L_0x0085;
    L_0x0068:
        r8 = r8.getComponentType();
        r10 = java.lang.Byte.TYPE;
        if (r8 == r10) goto L_0x0085;
    L_0x0070:
        if (r7 != 0) goto L_0x0074;
    L_0x0072:
        r8 = 0;
        goto L_0x0078;
    L_0x0074:
        r8 = java.lang.reflect.Array.getLength(r7);
    L_0x0078:
        r10 = 0;
    L_0x0079:
        if (r10 >= r8) goto L_0x0088;
    L_0x007b:
        r11 = java.lang.reflect.Array.get(r7, r10);
        zza(r9, r11, r14, r15);
        r10 = r10 + 1;
        goto L_0x0079;
    L_0x0085:
        zza(r9, r7, r14, r15);
    L_0x0088:
        r6 = r6 + 1;
        goto L_0x002c;
    L_0x008b:
        r1 = r3.getMethods();
        r4 = r1.length;
        r5 = 0;
    L_0x0091:
        if (r5 >= r4) goto L_0x00fa;
    L_0x0093:
        r6 = r1[r5];
        r6 = r6.getName();
        r7 = "set";
        r7 = r6.startsWith(r7);
        if (r7 == 0) goto L_0x00f7;
    L_0x00a1:
        r7 = 3;
        r6 = r6.substring(r7);
        r7 = "has";	 Catch:{ NoSuchMethodException -> 0x00f7 }
        r8 = java.lang.String.valueOf(r6);	 Catch:{ NoSuchMethodException -> 0x00f7 }
        r9 = r8.length();	 Catch:{ NoSuchMethodException -> 0x00f7 }
        if (r9 == 0) goto L_0x00b7;	 Catch:{ NoSuchMethodException -> 0x00f7 }
    L_0x00b2:
        r7 = r7.concat(r8);	 Catch:{ NoSuchMethodException -> 0x00f7 }
        goto L_0x00bd;	 Catch:{ NoSuchMethodException -> 0x00f7 }
    L_0x00b7:
        r8 = new java.lang.String;	 Catch:{ NoSuchMethodException -> 0x00f7 }
        r8.<init>(r7);	 Catch:{ NoSuchMethodException -> 0x00f7 }
        r7 = r8;	 Catch:{ NoSuchMethodException -> 0x00f7 }
    L_0x00bd:
        r8 = new java.lang.Class[r2];	 Catch:{ NoSuchMethodException -> 0x00f7 }
        r7 = r3.getMethod(r7, r8);	 Catch:{ NoSuchMethodException -> 0x00f7 }
        r8 = new java.lang.Object[r2];
        r7 = r7.invoke(r13, r8);
        r7 = (java.lang.Boolean) r7;
        r7 = r7.booleanValue();
        if (r7 == 0) goto L_0x00f7;
    L_0x00d1:
        r7 = "get";	 Catch:{ NoSuchMethodException -> 0x00f7 }
        r8 = java.lang.String.valueOf(r6);	 Catch:{ NoSuchMethodException -> 0x00f7 }
        r9 = r8.length();	 Catch:{ NoSuchMethodException -> 0x00f7 }
        if (r9 == 0) goto L_0x00e2;	 Catch:{ NoSuchMethodException -> 0x00f7 }
    L_0x00dd:
        r7 = r7.concat(r8);	 Catch:{ NoSuchMethodException -> 0x00f7 }
        goto L_0x00e8;	 Catch:{ NoSuchMethodException -> 0x00f7 }
    L_0x00e2:
        r8 = new java.lang.String;	 Catch:{ NoSuchMethodException -> 0x00f7 }
        r8.<init>(r7);	 Catch:{ NoSuchMethodException -> 0x00f7 }
        r7 = r8;	 Catch:{ NoSuchMethodException -> 0x00f7 }
    L_0x00e8:
        r8 = new java.lang.Class[r2];	 Catch:{ NoSuchMethodException -> 0x00f7 }
        r7 = r3.getMethod(r7, r8);	 Catch:{ NoSuchMethodException -> 0x00f7 }
        r8 = new java.lang.Object[r2];
        r7 = r7.invoke(r13, r8);
        zza(r6, r7, r14, r15);
    L_0x00f7:
        r5 = r5 + 1;
        goto L_0x0091;
    L_0x00fa:
        if (r12 == 0) goto L_0x0107;
    L_0x00fc:
        r14.setLength(r0);
        r15.append(r14);
        r12 = ">\n";
        r15.append(r12);
    L_0x0107:
        return;
    L_0x0108:
        r12 = zzfs(r12);
        r15.append(r14);
        r15.append(r12);
        r12 = ": ";
        r15.append(r12);
        r12 = r13 instanceof java.lang.String;
        r14 = 32;
        r0 = 34;
        if (r12 == 0) goto L_0x0182;
    L_0x011f:
        r13 = (java.lang.String) r13;
        r12 = "http";
        r12 = r13.startsWith(r12);
        if (r12 != 0) goto L_0x013f;
    L_0x0129:
        r12 = r13.length();
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r12 <= r3) goto L_0x013f;
    L_0x0131:
        r12 = r13.substring(r2, r3);
        r12 = java.lang.String.valueOf(r12);
        r13 = "[...]";
        r13 = r12.concat(r13);
    L_0x013f:
        r12 = r13.length();
        r3 = new java.lang.StringBuilder;
        r3.<init>(r12);
        r4 = 0;
    L_0x0149:
        if (r4 >= r12) goto L_0x0173;
    L_0x014b:
        r5 = r13.charAt(r4);
        if (r5 < r14) goto L_0x015f;
    L_0x0151:
        r6 = 126; // 0x7e float:1.77E-43 double:6.23E-322;
        if (r5 > r6) goto L_0x015f;
    L_0x0155:
        if (r5 == r0) goto L_0x015f;
    L_0x0157:
        r6 = 39;
        if (r5 == r6) goto L_0x015f;
    L_0x015b:
        r3.append(r5);
        goto L_0x0170;
    L_0x015f:
        r6 = "\\u%04x";
        r7 = new java.lang.Object[r1];
        r5 = java.lang.Integer.valueOf(r5);
        r7[r2] = r5;
        r5 = java.lang.String.format(r6, r7);
        r3.append(r5);
    L_0x0170:
        r4 = r4 + 1;
        goto L_0x0149;
    L_0x0173:
        r12 = r3.toString();
        r13 = "\"";
        r15.append(r13);
        r15.append(r12);
        r12 = "\"";
        goto L_0x018c;
    L_0x0182:
        r12 = r13 instanceof byte[];
        if (r12 == 0) goto L_0x01c9;
    L_0x0186:
        r13 = (byte[]) r13;
        if (r13 != 0) goto L_0x0190;
    L_0x018a:
        r12 = "\"\"";
    L_0x018c:
        r15.append(r12);
        goto L_0x01cc;
    L_0x0190:
        r15.append(r0);
        r12 = 0;
    L_0x0194:
        r3 = r13.length;
        if (r12 >= r3) goto L_0x01c5;
    L_0x0197:
        r3 = r13[r12];
        r3 = r3 & 255;
        r4 = 92;
        if (r3 == r4) goto L_0x01bb;
    L_0x019f:
        if (r3 != r0) goto L_0x01a2;
    L_0x01a1:
        goto L_0x01bb;
    L_0x01a2:
        if (r3 < r14) goto L_0x01a9;
    L_0x01a4:
        r4 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        if (r3 >= r4) goto L_0x01a9;
    L_0x01a8:
        goto L_0x01be;
    L_0x01a9:
        r4 = "\\%03o";
        r5 = new java.lang.Object[r1];
        r3 = java.lang.Integer.valueOf(r3);
        r5[r2] = r3;
        r3 = java.lang.String.format(r4, r5);
        r15.append(r3);
        goto L_0x01c2;
    L_0x01bb:
        r15.append(r4);
    L_0x01be:
        r3 = (char) r3;
        r15.append(r3);
    L_0x01c2:
        r12 = r12 + 1;
        goto L_0x0194;
    L_0x01c5:
        r15.append(r0);
        goto L_0x01cc;
    L_0x01c9:
        r15.append(r13);
    L_0x01cc:
        r12 = "\n";
        r15.append(r12);
    L_0x01d1:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzack.zza(java.lang.String, java.lang.Object, java.lang.StringBuffer, java.lang.StringBuffer):void");
    }

    public static <T extends zzacj> String zzc(T t) {
        String str;
        String valueOf;
        if (t == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            zza(null, t, new StringBuffer(), stringBuffer);
            return stringBuffer.toString();
        } catch (IllegalAccessException e) {
            str = "Error printing proto: ";
            valueOf = String.valueOf(e.getMessage());
            return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
        } catch (InvocationTargetException e2) {
            str = "Error printing proto: ";
            valueOf = String.valueOf(e2.getMessage());
            return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
        }
    }

    private static String zzfs(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (i != 0) {
                if (Character.isUpperCase(charAt)) {
                    stringBuffer.append('_');
                }
                stringBuffer.append(charAt);
            }
            charAt = Character.toLowerCase(charAt);
            stringBuffer.append(charAt);
        }
        return stringBuffer.toString();
    }
}
