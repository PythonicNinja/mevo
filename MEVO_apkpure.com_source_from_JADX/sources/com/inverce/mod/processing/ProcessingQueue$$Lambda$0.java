package com.inverce.mod.processing;

final /* synthetic */ class ProcessingQueue$$Lambda$0 implements Processor {
    private final TaskMapper arg$1;

    private ProcessingQueue$$Lambda$0(TaskMapper taskMapper) {
        this.arg$1 = taskMapper;
    }

    static Processor get$Lambda(TaskMapper taskMapper) {
        return new ProcessingQueue$$Lambda$0(taskMapper);
    }

    public Object processJob(Object obj) {
        return this.arg$1.processJob(obj);
    }
}
