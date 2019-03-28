package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public final class zzgi extends zzhj {
    private static final AtomicLong zzanv = new AtomicLong(Long.MIN_VALUE);
    private ExecutorService executor;
    private zzgm zzanm;
    private zzgm zzann;
    private final PriorityBlockingQueue<zzgl<?>> zzano = new PriorityBlockingQueue();
    private final BlockingQueue<zzgl<?>> zzanp = new LinkedBlockingQueue();
    private final UncaughtExceptionHandler zzanq = new zzgk(this, "Thread death: Uncaught exception on worker thread");
    private final UncaughtExceptionHandler zzanr = new zzgk(this, "Thread death: Uncaught exception on network thread");
    private final Object zzans = new Object();
    private final Semaphore zzant = new Semaphore(2);
    private volatile boolean zzanu;

    zzgi(zzgn zzgn) {
        super(zzgn);
    }

    private final void zza(zzgl<?> zzgl) {
        synchronized (this.zzans) {
            this.zzano.add(zzgl);
            if (this.zzanm == null) {
                this.zzanm = new zzgm(this, "Measurement Worker", this.zzano);
                this.zzanm.setUncaughtExceptionHandler(this.zzanq);
                this.zzanm.start();
            } else {
                this.zzanm.zzjx();
            }
        }
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    final <T> T zza(java.util.concurrent.atomic.AtomicReference<T> r1, long r2, java.lang.String r4, java.lang.Runnable r5) {
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
        r0 = this;
        monitor-enter(r1);
        r2 = r0.zzgh();	 Catch:{ all -> 0x005c }
        r2.zzc(r5);	 Catch:{ all -> 0x005c }
        r2 = 15000; // 0x3a98 float:2.102E-41 double:7.411E-320;
        r1.wait(r2);	 Catch:{ InterruptedException -> 0x0037 }
        monitor-exit(r1);	 Catch:{ all -> 0x005c }
        r1 = r1.get();
        if (r1 != 0) goto L_0x0036;
    L_0x0014:
        r2 = r0.zzgi();
        r2 = r2.zziy();
        r3 = "Timed out waiting for ";
        r4 = java.lang.String.valueOf(r4);
        r5 = r4.length();
        if (r5 == 0) goto L_0x002d;
    L_0x0028:
        r3 = r3.concat(r4);
        goto L_0x0033;
    L_0x002d:
        r4 = new java.lang.String;
        r4.<init>(r3);
        r3 = r4;
    L_0x0033:
        r2.log(r3);
    L_0x0036:
        return r1;
    L_0x0037:
        r2 = r0.zzgi();	 Catch:{ all -> 0x005c }
        r2 = r2.zziy();	 Catch:{ all -> 0x005c }
        r3 = "Interrupted waiting for ";	 Catch:{ all -> 0x005c }
        r4 = java.lang.String.valueOf(r4);	 Catch:{ all -> 0x005c }
        r5 = r4.length();	 Catch:{ all -> 0x005c }
        if (r5 == 0) goto L_0x0050;	 Catch:{ all -> 0x005c }
    L_0x004b:
        r3 = r3.concat(r4);	 Catch:{ all -> 0x005c }
        goto L_0x0056;	 Catch:{ all -> 0x005c }
    L_0x0050:
        r4 = new java.lang.String;	 Catch:{ all -> 0x005c }
        r4.<init>(r3);	 Catch:{ all -> 0x005c }
        r3 = r4;	 Catch:{ all -> 0x005c }
    L_0x0056:
        r2.log(r3);	 Catch:{ all -> 0x005c }
        r2 = 0;	 Catch:{ all -> 0x005c }
        monitor-exit(r1);	 Catch:{ all -> 0x005c }
        return r2;	 Catch:{ all -> 0x005c }
    L_0x005c:
        r2 = move-exception;	 Catch:{ all -> 0x005c }
        monitor-exit(r1);	 Catch:{ all -> 0x005c }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgi.zza(java.util.concurrent.atomic.AtomicReference, long, java.lang.String, java.lang.Runnable):T");
    }

    public final void zzab() {
        if (Thread.currentThread() != this.zzanm) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    public final <V> Future<V> zzb(Callable<V> callable) throws IllegalStateException {
        zzch();
        Preconditions.checkNotNull(callable);
        zzgl zzgl = new zzgl(this, (Callable) callable, false, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzanm) {
            if (!this.zzano.isEmpty()) {
                zzgi().zziy().log("Callable skipped the worker queue.");
            }
            zzgl.run();
            return zzgl;
        }
        zza(zzgl);
        return zzgl;
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    public final <V> Future<V> zzc(Callable<V> callable) throws IllegalStateException {
        zzch();
        Preconditions.checkNotNull(callable);
        zzgl zzgl = new zzgl(this, (Callable) callable, true, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzanm) {
            zzgl.run();
            return zzgl;
        }
        zza(zzgl);
        return zzgl;
    }

    public final void zzc(Runnable runnable) throws IllegalStateException {
        zzch();
        Preconditions.checkNotNull(runnable);
        zza(new zzgl(this, runnable, false, "Task exception on worker thread"));
    }

    public final void zzd(Runnable runnable) throws IllegalStateException {
        zzch();
        Preconditions.checkNotNull(runnable);
        zzgl zzgl = new zzgl(this, runnable, false, "Task exception on network thread");
        synchronized (this.zzans) {
            this.zzanp.add(zzgl);
            if (this.zzann == null) {
                this.zzann = new zzgm(this, "Measurement Network", this.zzanp);
                this.zzann.setUncaughtExceptionHandler(this.zzanr);
                this.zzann.start();
            } else {
                this.zzann.zzjx();
            }
        }
    }

    public final /* bridge */ /* synthetic */ void zzfu() {
        super.zzfu();
    }

    public final /* bridge */ /* synthetic */ void zzfv() {
        super.zzfv();
    }

    public final void zzfw() {
        if (Thread.currentThread() != this.zzann) {
            throw new IllegalStateException("Call expected from network thread");
        }
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
        return false;
    }

    public final boolean zzju() {
        return Thread.currentThread() == this.zzanm;
    }

    final ExecutorService zzjv() {
        ExecutorService executorService;
        synchronized (this.zzans) {
            if (this.executor == null) {
                this.executor = new ThreadPoolExecutor(0, 1, 30, TimeUnit.SECONDS, new ArrayBlockingQueue(100));
            }
            executorService = this.executor;
        }
        return executorService;
    }
}
