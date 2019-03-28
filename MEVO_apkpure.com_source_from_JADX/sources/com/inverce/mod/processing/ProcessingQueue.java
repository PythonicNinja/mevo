package com.inverce.mod.processing;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.inverce.mod.core.IM;
import com.inverce.mod.core.functional.IConsumer;
import com.inverce.mod.core.threadpool.NamedThreadPool;
import com.inverce.mod.core.verification.Preconditions;
import com.inverce.mod.processing.Processor.EX;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ProcessingQueue {
    protected List<Thread> activeThreads = Collections.synchronizedList(new ArrayList());
    protected List<Job<?, ?>> awaiting = Collections.synchronizedList(new ArrayList());
    protected Settings cfg = new Settings();
    protected QueueListener events = new QueueListenerAdapter();
    protected List<Job<?, ?>> processing = Collections.synchronizedList(new ArrayList());

    public enum FailureAction {
        ABORT,
        IGNORE
    }

    private static class Settings {
        boolean asynchronous = true;
        FailureAction failureAction = FailureAction.ABORT;
        boolean isCancelled;
        boolean isContinuous;
        boolean isDone;
        boolean isFinishing;
        boolean isStarted;
        boolean isWaitingToStart;
        int poolSize;
        ThreadFactory threadFactory;

        private Settings() {
            this.poolSize = 8;
        }
    }

    final /* bridge */ /* synthetic */ void bridge$lambda$0$ProcessingQueue() {
        fillQueue();
    }

    public static ProcessingQueue create() {
        return new ProcessingQueue();
    }

    private ProcessingQueue() {
        Settings settings = this.cfg;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ProcessingQueue#");
        stringBuilder.append(hashCode());
        settings.threadFactory = new NamedThreadPool(stringBuilder.toString());
    }

    @NonNull
    public ProcessingQueue setAsynchronous(boolean z) {
        Preconditions.checkState(this.cfg.isStarted ^ 1, "ProcessingQueue already isStarted");
        this.cfg.asynchronous = z;
        return this;
    }

    @NonNull
    public ProcessingQueue setPoolSize(int i) {
        Preconditions.checkArgument(i > 0, "Pool size must be greater than 0");
        this.cfg.poolSize = i;
        return this;
    }

    @NonNull
    public ProcessingQueue setFailureAction(FailureAction failureAction) {
        Preconditions.checkState(this.cfg.isStarted ^ 1, "ProcessingQueue already isStarted");
        this.cfg.failureAction = failureAction;
        return this;
    }

    @NonNull
    public ProcessingQueue setThreadFactory(@NonNull ThreadFactory threadFactory) {
        Preconditions.checkState(this.cfg.isStarted ^ 1, "ProcessingQueue already isStarted");
        Preconditions.checkNotNull(threadFactory, "Factory cannot be null");
        this.cfg.threadFactory = threadFactory;
        return this;
    }

    @NonNull
    public ProcessingQueue setContinuous(boolean z) {
        Preconditions.checkState(this.cfg.isStarted ^ 1, "ProcessingQueue already isStarted");
        this.cfg.isContinuous = z;
        return this;
    }

    public List<Job<?, ?>> getProcessing() {
        return Collections.unmodifiableList(new ArrayList(this.processing));
    }

    public List<Job<?, ?>> getAwaiting() {
        return Collections.unmodifiableList(new ArrayList(this.awaiting));
    }

    @NonNull
    public ProcessingQueue setListener(@Nullable QueueListener queueListener) {
        if (queueListener == null) {
            queueListener = new QueueListenerAdapter();
        }
        this.events = queueListener;
        return this;
    }

    @NonNull
    public <T> ProcessingQueue processTask(@NonNull TaskMapper<T> taskMapper, @NonNull List<T> list) {
        Processor processor = Processor.RUNNABLES;
        taskMapper.getClass();
        return processInternal(EX.map(processor, ProcessingQueue$$Lambda$0.get$Lambda(taskMapper)), list, false);
    }

    @NonNull
    public <T> ProcessingQueue process(@NonNull IConsumer<T> iConsumer, @NonNull List<T> list) {
        return processInternal(EX.map(Processor.RUNNABLES, new ProcessingQueue$$Lambda$1(iConsumer)), list, false);
    }

    @NonNull
    public <T, R> ProcessingQueue process(@NonNull Processor<T, R> processor, @NonNull List<T> list) {
        return processInternal(processor, list, false);
    }

    @NonNull
    public <T> ProcessingQueue processTaskIfNotAdded(@NonNull TaskMapper<T> taskMapper, @NonNull List<T> list) {
        Processor processor = Processor.RUNNABLES;
        taskMapper.getClass();
        return processInternal(EX.map(processor, ProcessingQueue$$Lambda$2.get$Lambda(taskMapper)), list, true);
    }

    @NonNull
    public <T> ProcessingQueue processIfNotAdded(@NonNull IConsumer<T> iConsumer, @NonNull List<T> list) {
        return processInternal(EX.map(Processor.RUNNABLES, new ProcessingQueue$$Lambda$3(iConsumer)), list, true);
    }

    @NonNull
    public <T, R> ProcessingQueue processIfNotAdded(@NonNull Processor<T, R> processor, @NonNull List<T> list) {
        return processInternal(processor, list, true);
    }

    @NonNull
    <T, R> ProcessingQueue processInternal(@NonNull Processor<T, R> processor, @NonNull List<T> list, boolean z) {
        Preconditions.checkNotNull(processor, "Processor connot be null");
        Preconditions.checkNotNull(list, "You must specify elements");
        boolean z2 = true;
        Preconditions.checkArgument(this.cfg.isCancelled ^ true, "Cant add task to cancelled queue");
        if (this.cfg.isDone) {
            if (!this.cfg.isContinuous) {
                z2 = false;
            }
        }
        Preconditions.checkArgument(z2, "Adding more task after queue started supported with continous mode");
        for (Object next : list) {
            if (!z || !contains(next)) {
                this.awaiting.add(new Job(next, processor));
            }
        }
        if (!(this.cfg.isContinuous == null || this.cfg.isStarted == null)) {
            IM.onBg().execute(new ProcessingQueue$$Lambda$4(this));
        }
        return this;
    }

    public boolean isStarted() {
        return this.cfg.isStarted;
    }

    public boolean isCancelled() {
        return this.cfg.isCancelled;
    }

    public boolean isFinished() {
        return this.cfg.isDone && !this.cfg.isContinuous;
    }

    public synchronized void start() {
        Preconditions.checkArgument(this.cfg.isStarted ^ true);
        Preconditions.checkArgument(this.cfg.isDone ^ true);
        Preconditions.checkArgument(this.awaiting.size() > 0, "You need to add at least one item to process");
        this.cfg.isStarted = true;
        IM.onBg().execute(new ProcessingQueue$$Lambda$5(this));
        this.events.onQueueStarted(this);
    }

    @WorkerThread
    private synchronized boolean offerJob(@NonNull Job<?, ?> job) {
        if (this.processing.size() >= (this.cfg.asynchronous ? this.cfg.poolSize : 1)) {
            return null;
        }
        this.awaiting.remove(job);
        this.processing.add(job);
        Thread newThread = this.cfg.threadFactory.newThread(new ProcessingQueue$$Lambda$6(this, job));
        job.thread = newThread;
        newThread.start();
        this.activeThreads.add(newThread);
        this.events.onJobStarted(this, job.item, job.processor);
        return true;
    }

    final /* synthetic */ void lambda$offerJob$4$ProcessingQueue(@NonNull Job job) {
        job.accept(this);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.WorkerThread
    synchronized void finishJob(@android.support.annotation.NonNull com.inverce.mod.processing.JobResult<?, ?> r4) {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = r3.processing;	 Catch:{ all -> 0x0070 }
        r1 = r4.job;	 Catch:{ all -> 0x0070 }
        r0.remove(r1);	 Catch:{ all -> 0x0070 }
        r0 = r3.activeThreads;	 Catch:{ all -> 0x0070 }
        r1 = r4.job;	 Catch:{ all -> 0x0070 }
        r1 = r1.thread;	 Catch:{ all -> 0x0070 }
        r0.remove(r1);	 Catch:{ all -> 0x0070 }
        r0 = r4.exception;	 Catch:{ all -> 0x0070 }
        if (r0 != 0) goto L_0x001f;
    L_0x0015:
        r0 = r3.events;	 Catch:{ all -> 0x0070 }
        r1 = r4.job;	 Catch:{ all -> 0x0070 }
        r2 = r4.result;	 Catch:{ all -> 0x0070 }
        r0.onJobResult(r3, r1, r2);	 Catch:{ all -> 0x0070 }
        goto L_0x0028;
    L_0x001f:
        r0 = r3.events;	 Catch:{ all -> 0x0070 }
        r1 = r4.job;	 Catch:{ all -> 0x0070 }
        r2 = r4.exception;	 Catch:{ all -> 0x0070 }
        r0.onJobFailure(r3, r1, r2);	 Catch:{ all -> 0x0070 }
    L_0x0028:
        r4 = r4.exception;	 Catch:{ all -> 0x0070 }
        r0 = 1;
        if (r4 == 0) goto L_0x003e;
    L_0x002d:
        r4 = r3.cfg;	 Catch:{ all -> 0x0070 }
        r4 = r4.failureAction;	 Catch:{ all -> 0x0070 }
        r1 = com.inverce.mod.processing.ProcessingQueue.FailureAction.ABORT;	 Catch:{ all -> 0x0070 }
        if (r4 != r1) goto L_0x003e;
    L_0x0035:
        r3.cancel();	 Catch:{ all -> 0x0070 }
        r4 = r3.cfg;	 Catch:{ all -> 0x0070 }
        r4.isDone = r0;	 Catch:{ all -> 0x0070 }
        monitor-exit(r3);
        return;
    L_0x003e:
        r4 = r3.awaiting;	 Catch:{ all -> 0x0070 }
        r4 = r4.size();	 Catch:{ all -> 0x0070 }
        if (r4 <= 0) goto L_0x004f;
    L_0x0046:
        r4 = r3.cfg;	 Catch:{ all -> 0x0070 }
        r4 = r4.isCancelled;	 Catch:{ all -> 0x0070 }
        if (r4 != 0) goto L_0x004f;
    L_0x004c:
        r3.fillQueue();	 Catch:{ all -> 0x0070 }
    L_0x004f:
        r4 = r3.processing;	 Catch:{ all -> 0x0070 }
        r4 = r4.size();	 Catch:{ all -> 0x0070 }
        if (r4 != 0) goto L_0x006e;
    L_0x0057:
        r4 = r3.awaiting;	 Catch:{ all -> 0x0070 }
        r4 = r4.size();	 Catch:{ all -> 0x0070 }
        if (r4 != 0) goto L_0x006e;
    L_0x005f:
        r4 = r3.cfg;	 Catch:{ all -> 0x0070 }
        r4 = r4.isCancelled;	 Catch:{ all -> 0x0070 }
        if (r4 != 0) goto L_0x006e;
    L_0x0065:
        r4 = r3.cfg;	 Catch:{ all -> 0x0070 }
        r4.isDone = r0;	 Catch:{ all -> 0x0070 }
        r4 = r3.events;	 Catch:{ all -> 0x0070 }
        r4.onQueueFinished(r3);	 Catch:{ all -> 0x0070 }
    L_0x006e:
        monitor-exit(r3);
        return;
    L_0x0070:
        r4 = move-exception;
        monitor-exit(r3);
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inverce.mod.processing.ProcessingQueue.finishJob(com.inverce.mod.processing.JobResult):void");
    }

    @WorkerThread
    private synchronized void fillQueue() {
        if (this.cfg.asynchronous) {
            Iterator it = new ArrayList(this.awaiting).iterator();
            while (it.hasNext()) {
                if (!offerJob((Job) it.next())) {
                    break;
                }
            }
        }
        offerJob((Job) this.awaiting.get(0));
    }

    public synchronized boolean contains(Object obj) {
        Iterator it = new ArrayList(this.processing).iterator();
        while (it.hasNext()) {
            if (((Job) it.next()).getItem().equals(obj)) {
                return true;
            }
        }
        it = new ArrayList(this.awaiting).iterator();
        while (it.hasNext()) {
            if (((Job) it.next()).getItem().equals(obj)) {
                return true;
            }
        }
        return null;
    }

    public synchronized boolean cancelItem(Object obj) {
        Iterator it = new ArrayList(this.awaiting).iterator();
        while (it.hasNext()) {
            Job job = (Job) it.next();
            if (job.getItem().equals(obj)) {
                this.awaiting.remove(job);
                return true;
            }
        }
        it = new ArrayList(this.processing).iterator();
        while (it.hasNext()) {
            job = (Job) it.next();
            if (job.getItem().equals(obj)) {
                synchronized (job.getThread()) {
                    job.getThread().interrupt();
                }
                this.activeThreads.remove(job.thread);
                this.processing.remove(job);
                return true;
            }
        }
        return null;
    }

    public synchronized void cancel() {
        this.cfg.isCancelled = true;
        this.cfg.isFinishing = true;
        this.cfg.isStarted = false;
        this.cfg.isDone = true ^ this.cfg.isContinuous;
        for (Thread thread : this.activeThreads) {
            synchronized (thread) {
                thread.interrupt();
            }
        }
        this.activeThreads.clear();
        IM.onBg().schedule(new ProcessingQueue$$Lambda$7(this), 100, TimeUnit.MILLISECONDS);
    }

    final /* synthetic */ void lambda$cancel$5$ProcessingQueue() {
        this.events.onQueueCancelled(this);
        this.cfg.isFinishing = false;
    }
}
