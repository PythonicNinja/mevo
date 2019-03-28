package com.inverce.mod.processing;

final /* synthetic */ class ProcessingQueue$$Lambda$6 implements Runnable {
    private final ProcessingQueue arg$1;
    private final Job arg$2;

    ProcessingQueue$$Lambda$6(ProcessingQueue processingQueue, Job job) {
        this.arg$1 = processingQueue;
        this.arg$2 = job;
    }

    public void run() {
        this.arg$1.lambda$offerJob$4$ProcessingQueue(this.arg$2);
    }
}
