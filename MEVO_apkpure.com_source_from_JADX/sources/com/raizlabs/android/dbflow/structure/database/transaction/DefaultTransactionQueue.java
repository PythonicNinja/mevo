package com.raizlabs.android.dbflow.structure.database.transaction;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowLog.Level;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

public class DefaultTransactionQueue extends Thread implements ITransactionQueue {
    private boolean isQuitting = null;
    private final LinkedBlockingQueue<Transaction> queue = new LinkedBlockingQueue();

    public DefaultTransactionQueue(String str) {
        super(str);
    }

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
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/221634215.run(Unknown Source)
*/
        /*
        r2 = this;
        android.os.Looper.prepare();
        r0 = 10;
        android.os.Process.setThreadPriority(r0);
    L_0x0008:
        r0 = r2.queue;	 Catch:{ InterruptedException -> 0x0018 }
        r0 = r0.take();	 Catch:{ InterruptedException -> 0x0018 }
        r0 = (com.raizlabs.android.dbflow.structure.database.transaction.Transaction) r0;	 Catch:{ InterruptedException -> 0x0018 }
        r1 = r2.isQuitting;
        if (r1 != 0) goto L_0x0008;
    L_0x0014:
        r0.executeSync();
        goto L_0x0008;
    L_0x0018:
        monitor-enter(r2);
        r0 = r2.isQuitting;	 Catch:{ all -> 0x002d }
        if (r0 == 0) goto L_0x002b;	 Catch:{ all -> 0x002d }
    L_0x001d:
        r0 = r2.queue;	 Catch:{ all -> 0x002d }
        monitor-enter(r0);	 Catch:{ all -> 0x002d }
        r1 = r2.queue;	 Catch:{ all -> 0x0028 }
        r1.clear();	 Catch:{ all -> 0x0028 }
        monitor-exit(r0);	 Catch:{ all -> 0x0028 }
        monitor-exit(r2);	 Catch:{ all -> 0x002d }
        return;
    L_0x0028:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0028 }
        throw r1;	 Catch:{ all -> 0x002d }
    L_0x002b:
        monitor-exit(r2);	 Catch:{ all -> 0x002d }
        goto L_0x0008;	 Catch:{ all -> 0x002d }
    L_0x002d:
        r0 = move-exception;	 Catch:{ all -> 0x002d }
        monitor-exit(r2);	 Catch:{ all -> 0x002d }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.raizlabs.android.dbflow.structure.database.transaction.DefaultTransactionQueue.run():void");
    }

    public void add(@NonNull Transaction transaction) {
        synchronized (this.queue) {
            if (!this.queue.contains(transaction)) {
                this.queue.add(transaction);
            }
        }
    }

    public void cancel(@NonNull Transaction transaction) {
        synchronized (this.queue) {
            if (this.queue.contains(transaction)) {
                this.queue.remove(transaction);
            }
        }
    }

    public void cancel(@NonNull String str) {
        synchronized (this.queue) {
            Iterator it = this.queue.iterator();
            while (it.hasNext()) {
                Transaction transaction = (Transaction) it.next();
                if (transaction.name() != null && transaction.name().equals(str)) {
                    it.remove();
                }
            }
        }
    }

    public void startIfNotAlive() {
        synchronized (this) {
            if (!isAlive()) {
                try {
                    start();
                } catch (Throwable e) {
                    FlowLog.log(Level.E, e);
                }
            }
        }
    }

    public void quit() {
        synchronized (this) {
            this.isQuitting = true;
        }
        interrupt();
    }
}
