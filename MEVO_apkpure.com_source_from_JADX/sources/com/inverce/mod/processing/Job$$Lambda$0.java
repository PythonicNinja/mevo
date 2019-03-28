package com.inverce.mod.processing;

final /* synthetic */ class Job$$Lambda$0 implements Runnable {
    private final Job arg$1;
    private final ProcessingQueue arg$2;
    private final Object arg$3;

    Job$$Lambda$0(Job job, ProcessingQueue processingQueue, Object obj) {
        this.arg$1 = job;
        this.arg$2 = processingQueue;
        this.arg$3 = obj;
    }

    public void run() {
        this.arg$1.lambda$accept$0$Job(this.arg$2, this.arg$3);
    }
}
