package com.inverce.mod.processing;

import android.support.annotation.NonNull;
import com.inverce.mod.core.IM;
import com.inverce.mod.core.functional.IConsumer;

public class Job<ITEM, RESULT> implements IConsumer<ProcessingQueue> {
    protected ITEM item;
    protected Processor<ITEM, RESULT> processor;
    protected Thread thread;

    public Job(ITEM item, Processor<ITEM, RESULT> processor) {
        this.item = item;
        this.processor = processor;
    }

    public void accept(@NonNull ProcessingQueue processingQueue) {
        try {
            IM.onBg().execute(new Job$$Lambda$0(this, processingQueue, this.processor.processJob(this.item)));
        } catch (Exception e) {
            IM.onBg().execute(new Job$$Lambda$1(this, processingQueue, e));
        }
    }

    final /* synthetic */ void lambda$accept$0$Job(@NonNull ProcessingQueue processingQueue, Object obj) {
        processingQueue.finishJob(new JobResult(this, obj));
    }

    final /* synthetic */ void lambda$accept$1$Job(@NonNull ProcessingQueue processingQueue, Exception exception) {
        processingQueue.finishJob(new JobResult(this, exception));
    }

    public ITEM getItem() {
        return this.item;
    }

    public Processor<ITEM, RESULT> getProcessor() {
        return this.processor;
    }

    public Thread getThread() {
        return this.thread;
    }
}
