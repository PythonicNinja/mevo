package com.inverce.mod.processing;

final /* synthetic */ class Job$$Lambda$1 implements Runnable {
    private final Job arg$1;
    private final ProcessingQueue arg$2;
    private final Exception arg$3;

    Job$$Lambda$1(Job job, ProcessingQueue processingQueue, Exception exception) {
        this.arg$1 = job;
        this.arg$2 = processingQueue;
        this.arg$3 = exception;
    }

    public void run() {
        this.arg$1.lambda$accept$1$Job(this.arg$2, this.arg$3);
    }
}
