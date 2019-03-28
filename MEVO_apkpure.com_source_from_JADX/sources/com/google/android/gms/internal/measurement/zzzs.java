package com.google.android.gms.internal.measurement;

final /* synthetic */ class zzzs {
    static final /* synthetic */ int[] zzbuc = new int[zzabz.values().length];
    private static final /* synthetic */ int[] zzbud = new int[zzabu.values().length];

    static {
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
        r0 = com.google.android.gms.internal.measurement.zzabu.values();
        r0 = r0.length;
        r0 = new int[r0];
        zzbud = r0;
        r0 = 1;
        r1 = zzbud;	 Catch:{ NoSuchFieldError -> 0x0014 }
        r2 = com.google.android.gms.internal.measurement.zzabu.DOUBLE;	 Catch:{ NoSuchFieldError -> 0x0014 }
        r2 = r2.ordinal();	 Catch:{ NoSuchFieldError -> 0x0014 }
        r1[r2] = r0;	 Catch:{ NoSuchFieldError -> 0x0014 }
    L_0x0014:
        r1 = 2;
        r2 = zzbud;	 Catch:{ NoSuchFieldError -> 0x001f }
        r3 = com.google.android.gms.internal.measurement.zzabu.FLOAT;	 Catch:{ NoSuchFieldError -> 0x001f }
        r3 = r3.ordinal();	 Catch:{ NoSuchFieldError -> 0x001f }
        r2[r3] = r1;	 Catch:{ NoSuchFieldError -> 0x001f }
    L_0x001f:
        r2 = 3;
        r3 = zzbud;	 Catch:{ NoSuchFieldError -> 0x002a }
        r4 = com.google.android.gms.internal.measurement.zzabu.INT64;	 Catch:{ NoSuchFieldError -> 0x002a }
        r4 = r4.ordinal();	 Catch:{ NoSuchFieldError -> 0x002a }
        r3[r4] = r2;	 Catch:{ NoSuchFieldError -> 0x002a }
    L_0x002a:
        r3 = 4;
        r4 = zzbud;	 Catch:{ NoSuchFieldError -> 0x0035 }
        r5 = com.google.android.gms.internal.measurement.zzabu.UINT64;	 Catch:{ NoSuchFieldError -> 0x0035 }
        r5 = r5.ordinal();	 Catch:{ NoSuchFieldError -> 0x0035 }
        r4[r5] = r3;	 Catch:{ NoSuchFieldError -> 0x0035 }
    L_0x0035:
        r4 = 5;
        r5 = zzbud;	 Catch:{ NoSuchFieldError -> 0x0040 }
        r6 = com.google.android.gms.internal.measurement.zzabu.INT32;	 Catch:{ NoSuchFieldError -> 0x0040 }
        r6 = r6.ordinal();	 Catch:{ NoSuchFieldError -> 0x0040 }
        r5[r6] = r4;	 Catch:{ NoSuchFieldError -> 0x0040 }
    L_0x0040:
        r5 = 6;
        r6 = zzbud;	 Catch:{ NoSuchFieldError -> 0x004b }
        r7 = com.google.android.gms.internal.measurement.zzabu.FIXED64;	 Catch:{ NoSuchFieldError -> 0x004b }
        r7 = r7.ordinal();	 Catch:{ NoSuchFieldError -> 0x004b }
        r6[r7] = r5;	 Catch:{ NoSuchFieldError -> 0x004b }
    L_0x004b:
        r6 = 7;
        r7 = zzbud;	 Catch:{ NoSuchFieldError -> 0x0056 }
        r8 = com.google.android.gms.internal.measurement.zzabu.FIXED32;	 Catch:{ NoSuchFieldError -> 0x0056 }
        r8 = r8.ordinal();	 Catch:{ NoSuchFieldError -> 0x0056 }
        r7[r8] = r6;	 Catch:{ NoSuchFieldError -> 0x0056 }
    L_0x0056:
        r7 = 8;
        r8 = zzbud;	 Catch:{ NoSuchFieldError -> 0x0062 }
        r9 = com.google.android.gms.internal.measurement.zzabu.BOOL;	 Catch:{ NoSuchFieldError -> 0x0062 }
        r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x0062 }
        r8[r9] = r7;	 Catch:{ NoSuchFieldError -> 0x0062 }
    L_0x0062:
        r8 = 9;
        r9 = zzbud;	 Catch:{ NoSuchFieldError -> 0x006e }
        r10 = com.google.android.gms.internal.measurement.zzabu.GROUP;	 Catch:{ NoSuchFieldError -> 0x006e }
        r10 = r10.ordinal();	 Catch:{ NoSuchFieldError -> 0x006e }
        r9[r10] = r8;	 Catch:{ NoSuchFieldError -> 0x006e }
    L_0x006e:
        r9 = zzbud;	 Catch:{ NoSuchFieldError -> 0x007a }
        r10 = com.google.android.gms.internal.measurement.zzabu.MESSAGE;	 Catch:{ NoSuchFieldError -> 0x007a }
        r10 = r10.ordinal();	 Catch:{ NoSuchFieldError -> 0x007a }
        r11 = 10;	 Catch:{ NoSuchFieldError -> 0x007a }
        r9[r10] = r11;	 Catch:{ NoSuchFieldError -> 0x007a }
    L_0x007a:
        r9 = zzbud;	 Catch:{ NoSuchFieldError -> 0x0086 }
        r10 = com.google.android.gms.internal.measurement.zzabu.STRING;	 Catch:{ NoSuchFieldError -> 0x0086 }
        r10 = r10.ordinal();	 Catch:{ NoSuchFieldError -> 0x0086 }
        r11 = 11;	 Catch:{ NoSuchFieldError -> 0x0086 }
        r9[r10] = r11;	 Catch:{ NoSuchFieldError -> 0x0086 }
    L_0x0086:
        r9 = zzbud;	 Catch:{ NoSuchFieldError -> 0x0092 }
        r10 = com.google.android.gms.internal.measurement.zzabu.BYTES;	 Catch:{ NoSuchFieldError -> 0x0092 }
        r10 = r10.ordinal();	 Catch:{ NoSuchFieldError -> 0x0092 }
        r11 = 12;	 Catch:{ NoSuchFieldError -> 0x0092 }
        r9[r10] = r11;	 Catch:{ NoSuchFieldError -> 0x0092 }
    L_0x0092:
        r9 = zzbud;	 Catch:{ NoSuchFieldError -> 0x009e }
        r10 = com.google.android.gms.internal.measurement.zzabu.UINT32;	 Catch:{ NoSuchFieldError -> 0x009e }
        r10 = r10.ordinal();	 Catch:{ NoSuchFieldError -> 0x009e }
        r11 = 13;	 Catch:{ NoSuchFieldError -> 0x009e }
        r9[r10] = r11;	 Catch:{ NoSuchFieldError -> 0x009e }
    L_0x009e:
        r9 = zzbud;	 Catch:{ NoSuchFieldError -> 0x00aa }
        r10 = com.google.android.gms.internal.measurement.zzabu.SFIXED32;	 Catch:{ NoSuchFieldError -> 0x00aa }
        r10 = r10.ordinal();	 Catch:{ NoSuchFieldError -> 0x00aa }
        r11 = 14;	 Catch:{ NoSuchFieldError -> 0x00aa }
        r9[r10] = r11;	 Catch:{ NoSuchFieldError -> 0x00aa }
    L_0x00aa:
        r9 = zzbud;	 Catch:{ NoSuchFieldError -> 0x00b6 }
        r10 = com.google.android.gms.internal.measurement.zzabu.SFIXED64;	 Catch:{ NoSuchFieldError -> 0x00b6 }
        r10 = r10.ordinal();	 Catch:{ NoSuchFieldError -> 0x00b6 }
        r11 = 15;	 Catch:{ NoSuchFieldError -> 0x00b6 }
        r9[r10] = r11;	 Catch:{ NoSuchFieldError -> 0x00b6 }
    L_0x00b6:
        r9 = zzbud;	 Catch:{ NoSuchFieldError -> 0x00c2 }
        r10 = com.google.android.gms.internal.measurement.zzabu.SINT32;	 Catch:{ NoSuchFieldError -> 0x00c2 }
        r10 = r10.ordinal();	 Catch:{ NoSuchFieldError -> 0x00c2 }
        r11 = 16;	 Catch:{ NoSuchFieldError -> 0x00c2 }
        r9[r10] = r11;	 Catch:{ NoSuchFieldError -> 0x00c2 }
    L_0x00c2:
        r9 = zzbud;	 Catch:{ NoSuchFieldError -> 0x00ce }
        r10 = com.google.android.gms.internal.measurement.zzabu.SINT64;	 Catch:{ NoSuchFieldError -> 0x00ce }
        r10 = r10.ordinal();	 Catch:{ NoSuchFieldError -> 0x00ce }
        r11 = 17;	 Catch:{ NoSuchFieldError -> 0x00ce }
        r9[r10] = r11;	 Catch:{ NoSuchFieldError -> 0x00ce }
    L_0x00ce:
        r9 = zzbud;	 Catch:{ NoSuchFieldError -> 0x00da }
        r10 = com.google.android.gms.internal.measurement.zzabu.ENUM;	 Catch:{ NoSuchFieldError -> 0x00da }
        r10 = r10.ordinal();	 Catch:{ NoSuchFieldError -> 0x00da }
        r11 = 18;	 Catch:{ NoSuchFieldError -> 0x00da }
        r9[r10] = r11;	 Catch:{ NoSuchFieldError -> 0x00da }
    L_0x00da:
        r9 = com.google.android.gms.internal.measurement.zzabz.values();
        r9 = r9.length;
        r9 = new int[r9];
        zzbuc = r9;
        r9 = zzbuc;	 Catch:{ NoSuchFieldError -> 0x00ed }
        r10 = com.google.android.gms.internal.measurement.zzabz.INT;	 Catch:{ NoSuchFieldError -> 0x00ed }
        r10 = r10.ordinal();	 Catch:{ NoSuchFieldError -> 0x00ed }
        r9[r10] = r0;	 Catch:{ NoSuchFieldError -> 0x00ed }
    L_0x00ed:
        r0 = zzbuc;	 Catch:{ NoSuchFieldError -> 0x00f7 }
        r9 = com.google.android.gms.internal.measurement.zzabz.LONG;	 Catch:{ NoSuchFieldError -> 0x00f7 }
        r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x00f7 }
        r0[r9] = r1;	 Catch:{ NoSuchFieldError -> 0x00f7 }
    L_0x00f7:
        r0 = zzbuc;	 Catch:{ NoSuchFieldError -> 0x0101 }
        r1 = com.google.android.gms.internal.measurement.zzabz.FLOAT;	 Catch:{ NoSuchFieldError -> 0x0101 }
        r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0101 }
        r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0101 }
    L_0x0101:
        r0 = zzbuc;	 Catch:{ NoSuchFieldError -> 0x010b }
        r1 = com.google.android.gms.internal.measurement.zzabz.DOUBLE;	 Catch:{ NoSuchFieldError -> 0x010b }
        r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x010b }
        r0[r1] = r3;	 Catch:{ NoSuchFieldError -> 0x010b }
    L_0x010b:
        r0 = zzbuc;	 Catch:{ NoSuchFieldError -> 0x0115 }
        r1 = com.google.android.gms.internal.measurement.zzabz.BOOLEAN;	 Catch:{ NoSuchFieldError -> 0x0115 }
        r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0115 }
        r0[r1] = r4;	 Catch:{ NoSuchFieldError -> 0x0115 }
    L_0x0115:
        r0 = zzbuc;	 Catch:{ NoSuchFieldError -> 0x011f }
        r1 = com.google.android.gms.internal.measurement.zzabz.STRING;	 Catch:{ NoSuchFieldError -> 0x011f }
        r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x011f }
        r0[r1] = r5;	 Catch:{ NoSuchFieldError -> 0x011f }
    L_0x011f:
        r0 = zzbuc;	 Catch:{ NoSuchFieldError -> 0x0129 }
        r1 = com.google.android.gms.internal.measurement.zzabz.BYTE_STRING;	 Catch:{ NoSuchFieldError -> 0x0129 }
        r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0129 }
        r0[r1] = r6;	 Catch:{ NoSuchFieldError -> 0x0129 }
    L_0x0129:
        r0 = zzbuc;	 Catch:{ NoSuchFieldError -> 0x0133 }
        r1 = com.google.android.gms.internal.measurement.zzabz.ENUM;	 Catch:{ NoSuchFieldError -> 0x0133 }
        r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0133 }
        r0[r1] = r7;	 Catch:{ NoSuchFieldError -> 0x0133 }
    L_0x0133:
        r0 = zzbuc;	 Catch:{ NoSuchFieldError -> 0x013d }
        r1 = com.google.android.gms.internal.measurement.zzabz.MESSAGE;	 Catch:{ NoSuchFieldError -> 0x013d }
        r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x013d }
        r0[r1] = r8;	 Catch:{ NoSuchFieldError -> 0x013d }
    L_0x013d:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzzs.<clinit>():void");
    }
}
