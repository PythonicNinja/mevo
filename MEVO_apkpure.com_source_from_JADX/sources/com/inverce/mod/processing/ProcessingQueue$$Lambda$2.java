package com.inverce.mod.processing;

final /* synthetic */ class ProcessingQueue$$Lambda$2 implements Processor {
    private final TaskMapper arg$1;

    private ProcessingQueue$$Lambda$2(TaskMapper taskMapper) {
        this.arg$1 = taskMapper;
    }

    static Processor get$Lambda(TaskMapper taskMapper) {
        return new ProcessingQueue$$Lambda$2(taskMapper);
    }

    public Object processJob(Object obj) {
        return this.arg$1.processJob(obj);
    }
}
