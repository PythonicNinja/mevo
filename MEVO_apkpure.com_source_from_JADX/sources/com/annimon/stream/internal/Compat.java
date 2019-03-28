package com.annimon.stream.internal;

import java.lang.reflect.Array;

public final class Compat {
    private static final String BAD_SIZE = "Stream size exceeds max array size";
    static final long MAX_ARRAY_SIZE = 2147483639;

    public static <T> java.util.Queue<T> queue() {
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
        r0 = new java.util.ArrayDeque;	 Catch:{ NoClassDefFoundError -> 0x0006 }
        r0.<init>();	 Catch:{ NoClassDefFoundError -> 0x0006 }
        return r0;
    L_0x0006:
        r0 = new java.util.LinkedList;
        r0.<init>();
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.annimon.stream.internal.Compat.queue():java.util.Queue<T>");
    }

    @java.lang.SafeVarargs
    public static <E> E[] newArray(int r1, E... r2) {
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
        r0 = java.util.Arrays.copyOf(r2, r1);	 Catch:{ NoSuchMethodError -> 0x0005 }
        return r0;
    L_0x0005:
        r1 = newArrayCompat(r2, r1);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.annimon.stream.internal.Compat.newArray(int, java.lang.Object[]):E[]");
    }

    public static <E> E[] newArrayCompat(E[] eArr, int i) {
        Object[] objArr = (Object[]) Array.newInstance(eArr.getClass().getComponentType(), i);
        System.arraycopy(eArr, 0, objArr, 0, Math.min(i, eArr.length));
        return objArr;
    }

    static void checkMaxArraySize(long j) {
        if (j >= MAX_ARRAY_SIZE) {
            throw new IllegalArgumentException(BAD_SIZE);
        }
    }
}
