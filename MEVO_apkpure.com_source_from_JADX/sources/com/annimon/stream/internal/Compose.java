package com.annimon.stream.internal;

import java.io.Closeable;

public final class Compose {
    private Compose() {
    }

    public static Runnable runnables(final Runnable runnable, final Runnable runnable2) {
        return new Runnable() {
            public void run() {
                /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
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
                r0 = r1;	 Catch:{ Throwable -> 0x000b }
                r0.run();	 Catch:{ Throwable -> 0x000b }
                r0 = r2;
                r0.run();
                return;
            L_0x000b:
                r0 = move-exception;
                r1 = r2;	 Catch:{ Throwable -> 0x0011 }
                r1.run();	 Catch:{ Throwable -> 0x0011 }
            L_0x0011:
                r1 = r0 instanceof java.lang.RuntimeException;
                if (r1 == 0) goto L_0x0018;
            L_0x0015:
                r0 = (java.lang.RuntimeException) r0;
                throw r0;
            L_0x0018:
                r0 = (java.lang.Error) r0;
                throw r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.annimon.stream.internal.Compose.1.run():void");
            }
        };
    }

    public static Runnable closeables(final Closeable closeable, final Closeable closeable2) {
        return new Runnable() {
            public void run() {
                /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
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
                r0 = r1;	 Catch:{ Throwable -> 0x0020 }
                r0.close();	 Catch:{ Throwable -> 0x0020 }
                r0 = r2;	 Catch:{ Throwable -> 0x000b }
                r0.close();	 Catch:{ Throwable -> 0x000b }
                return;
            L_0x000b:
                r0 = move-exception;
                r1 = r0 instanceof java.lang.RuntimeException;
                if (r1 == 0) goto L_0x0013;
            L_0x0010:
                r0 = (java.lang.RuntimeException) r0;
                throw r0;
            L_0x0013:
                r1 = r0 instanceof java.lang.Error;
                if (r1 == 0) goto L_0x001a;
            L_0x0017:
                r0 = (java.lang.Error) r0;
                throw r0;
            L_0x001a:
                r1 = new java.lang.RuntimeException;
                r1.<init>(r0);
                throw r1;
            L_0x0020:
                r0 = move-exception;
                r1 = r2;	 Catch:{ Throwable -> 0x0026 }
                r1.close();	 Catch:{ Throwable -> 0x0026 }
            L_0x0026:
                r1 = r0 instanceof java.lang.RuntimeException;
                if (r1 == 0) goto L_0x002d;
            L_0x002a:
                r0 = (java.lang.RuntimeException) r0;
                throw r0;
            L_0x002d:
                r0 = (java.lang.Error) r0;
                throw r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.annimon.stream.internal.Compose.2.run():void");
            }
        };
    }
}
