package io.fabric.sdk.android.services.common;

import io.fabric.sdk.android.services.concurrency.internal.Backoff;
import io.fabric.sdk.android.services.concurrency.internal.RetryPolicy;
import io.fabric.sdk.android.services.concurrency.internal.RetryThreadPoolExecutor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public final class ExecutorUtils {
    private static final long DEFAULT_TERMINATION_TIMEOUT = 2;

    private ExecutorUtils() {
    }

    public static ExecutorService buildSingleThreadExecutorService(String str) {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor(getNamedThreadFactory(str));
        addDelayedShutdownHook(str, newSingleThreadExecutor);
        return newSingleThreadExecutor;
    }

    public static RetryThreadPoolExecutor buildRetryThreadPoolExecutor(String str, int i, RetryPolicy retryPolicy, Backoff backoff) {
        Object retryThreadPoolExecutor = new RetryThreadPoolExecutor(i, getNamedThreadFactory(str), retryPolicy, backoff);
        addDelayedShutdownHook(str, retryThreadPoolExecutor);
        return retryThreadPoolExecutor;
    }

    public static ScheduledExecutorService buildSingleThreadScheduledExecutorService(String str) {
        Object newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor(getNamedThreadFactory(str));
        addDelayedShutdownHook(str, newSingleThreadScheduledExecutor);
        return newSingleThreadScheduledExecutor;
    }

    public static final ThreadFactory getNamedThreadFactory(final String str) {
        final AtomicLong atomicLong = new AtomicLong(1);
        return new ThreadFactory() {
            public Thread newThread(final Runnable runnable) {
                runnable = Executors.defaultThreadFactory().newThread(new BackgroundPriorityRunnable() {
                    public void onRun() {
                        runnable.run();
                    }
                });
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(atomicLong.getAndIncrement());
                runnable.setName(stringBuilder.toString());
                return runnable;
            }
        };
    }

    private static final void addDelayedShutdownHook(String str, ExecutorService executorService) {
        addDelayedShutdownHook(str, executorService, 2, TimeUnit.SECONDS);
    }

    public static final void addDelayedShutdownHook(String str, ExecutorService executorService, long j, TimeUnit timeUnit) {
        Runtime runtime = Runtime.getRuntime();
        final String str2 = str;
        final ExecutorService executorService2 = executorService;
        final long j2 = j;
        final TimeUnit timeUnit2 = timeUnit;
        Runnable c08872 = new BackgroundPriorityRunnable() {
            public void onRun() {
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
                /*
                r7 = this;
                r0 = io.fabric.sdk.android.Fabric.getLogger();	 Catch:{ InterruptedException -> 0x004f }
                r1 = "Fabric";	 Catch:{ InterruptedException -> 0x004f }
                r2 = new java.lang.StringBuilder;	 Catch:{ InterruptedException -> 0x004f }
                r2.<init>();	 Catch:{ InterruptedException -> 0x004f }
                r3 = "Executing shutdown hook for ";	 Catch:{ InterruptedException -> 0x004f }
                r2.append(r3);	 Catch:{ InterruptedException -> 0x004f }
                r3 = r3;	 Catch:{ InterruptedException -> 0x004f }
                r2.append(r3);	 Catch:{ InterruptedException -> 0x004f }
                r2 = r2.toString();	 Catch:{ InterruptedException -> 0x004f }
                r0.mo2494d(r1, r2);	 Catch:{ InterruptedException -> 0x004f }
                r0 = r4;	 Catch:{ InterruptedException -> 0x004f }
                r0.shutdown();	 Catch:{ InterruptedException -> 0x004f }
                r0 = r4;	 Catch:{ InterruptedException -> 0x004f }
                r1 = r5;	 Catch:{ InterruptedException -> 0x004f }
                r3 = r7;	 Catch:{ InterruptedException -> 0x004f }
                r0 = r0.awaitTermination(r1, r3);	 Catch:{ InterruptedException -> 0x004f }
                if (r0 != 0) goto L_0x006d;	 Catch:{ InterruptedException -> 0x004f }
            L_0x002d:
                r0 = io.fabric.sdk.android.Fabric.getLogger();	 Catch:{ InterruptedException -> 0x004f }
                r1 = "Fabric";	 Catch:{ InterruptedException -> 0x004f }
                r2 = new java.lang.StringBuilder;	 Catch:{ InterruptedException -> 0x004f }
                r2.<init>();	 Catch:{ InterruptedException -> 0x004f }
                r3 = r3;	 Catch:{ InterruptedException -> 0x004f }
                r2.append(r3);	 Catch:{ InterruptedException -> 0x004f }
                r3 = " did not shut down in the allocated time. Requesting immediate shutdown.";	 Catch:{ InterruptedException -> 0x004f }
                r2.append(r3);	 Catch:{ InterruptedException -> 0x004f }
                r2 = r2.toString();	 Catch:{ InterruptedException -> 0x004f }
                r0.mo2494d(r1, r2);	 Catch:{ InterruptedException -> 0x004f }
                r0 = r4;	 Catch:{ InterruptedException -> 0x004f }
                r0.shutdownNow();	 Catch:{ InterruptedException -> 0x004f }
                goto L_0x006d;
            L_0x004f:
                r0 = io.fabric.sdk.android.Fabric.getLogger();
                r1 = "Fabric";
                r2 = java.util.Locale.US;
                r3 = "Interrupted while waiting for %s to shut down. Requesting immediate shutdown.";
                r4 = 1;
                r4 = new java.lang.Object[r4];
                r5 = 0;
                r6 = r3;
                r4[r5] = r6;
                r2 = java.lang.String.format(r2, r3, r4);
                r0.mo2494d(r1, r2);
                r0 = r4;
                r0.shutdownNow();
            L_0x006d:
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: io.fabric.sdk.android.services.common.ExecutorUtils.2.onRun():void");
            }
        };
        executorService = new StringBuilder();
        executorService.append("Crashlytics Shutdown Hook for ");
        executorService.append(str);
        runtime.addShutdownHook(new Thread(c08872, executorService.toString()));
    }
}
