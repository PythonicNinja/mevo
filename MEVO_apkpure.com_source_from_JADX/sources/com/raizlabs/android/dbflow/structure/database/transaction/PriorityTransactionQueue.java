package com.raizlabs.android.dbflow.structure.database.transaction;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowLog.Level;
import com.raizlabs.android.dbflow.structure.database.transaction.PriorityTransactionWrapper.Builder;
import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityTransactionQueue extends Thread implements ITransactionQueue {
    private boolean isQuitting = null;
    private final PriorityBlockingQueue<PriorityEntry<Transaction>> queue = new PriorityBlockingQueue();

    class PriorityEntry<E extends Transaction> implements Comparable<PriorityEntry<Transaction>> {
        final E entry;
        final PriorityTransactionWrapper transactionWrapper;

        public PriorityEntry(E e) {
            this.entry = e;
            if ((e.transaction() instanceof PriorityTransactionWrapper) != null) {
                this.transactionWrapper = (PriorityTransactionWrapper) e.transaction();
            } else {
                this.transactionWrapper = new Builder(e.transaction()).build();
            }
        }

        public E getEntry() {
            return this.entry;
        }

        public int compareTo(@NonNull PriorityEntry<Transaction> priorityEntry) {
            return this.transactionWrapper.compareTo(priorityEntry.transactionWrapper);
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (obj != null) {
                if (getClass() == obj.getClass()) {
                    PriorityEntry priorityEntry = (PriorityEntry) obj;
                    if (this.transactionWrapper != null) {
                        z = this.transactionWrapper.equals(priorityEntry.transactionWrapper);
                    } else if (priorityEntry.transactionWrapper != null) {
                        z = false;
                    }
                    return z;
                }
            }
            return false;
        }

        public int hashCode() {
            return this.transactionWrapper != null ? this.transactionWrapper.hashCode() : 0;
        }
    }

    public PriorityTransactionQueue(String str) {
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
        r0 = r2.queue;	 Catch:{ InterruptedException -> 0x0016 }
        r0 = r0.take();	 Catch:{ InterruptedException -> 0x0016 }
        r0 = (com.raizlabs.android.dbflow.structure.database.transaction.PriorityTransactionQueue.PriorityEntry) r0;	 Catch:{ InterruptedException -> 0x0016 }
        r0 = r0.entry;
        r0.executeSync();
        goto L_0x0008;
    L_0x0016:
        r0 = r2.isQuitting;
        if (r0 == 0) goto L_0x0008;
    L_0x001a:
        r0 = r2.queue;
        monitor-enter(r0);
        r1 = r2.queue;	 Catch:{ all -> 0x0024 }
        r1.clear();	 Catch:{ all -> 0x0024 }
        monitor-exit(r0);	 Catch:{ all -> 0x0024 }
        return;	 Catch:{ all -> 0x0024 }
    L_0x0024:
        r1 = move-exception;	 Catch:{ all -> 0x0024 }
        monitor-exit(r0);	 Catch:{ all -> 0x0024 }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.raizlabs.android.dbflow.structure.database.transaction.PriorityTransactionQueue.run():void");
    }

    public void add(@NonNull Transaction transaction) {
        synchronized (this.queue) {
            PriorityEntry priorityEntry = new PriorityEntry(transaction);
            if (this.queue.contains(priorityEntry) == null) {
                this.queue.add(priorityEntry);
            }
        }
    }

    public void cancel(@NonNull Transaction transaction) {
        synchronized (this.queue) {
            PriorityEntry priorityEntry = new PriorityEntry(transaction);
            if (this.queue.contains(priorityEntry) != null) {
                this.queue.remove(priorityEntry);
            }
        }
    }

    public void cancel(@NonNull String str) {
        synchronized (this.queue) {
            Iterator it = this.queue.iterator();
            while (it.hasNext()) {
                Transaction transaction = ((PriorityEntry) it.next()).entry;
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
        this.isQuitting = true;
        interrupt();
    }

    private void throwInvalidTransactionType(Transaction transaction) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Transaction of type:");
        stringBuilder.append(transaction != null ? transaction.transaction().getClass() : "Unknown");
        stringBuilder.append(" should be of type PriorityTransactionWrapper");
        throw new IllegalArgumentException(stringBuilder.toString());
    }
}
