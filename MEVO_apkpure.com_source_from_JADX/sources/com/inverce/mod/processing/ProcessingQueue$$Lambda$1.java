package com.inverce.mod.processing;

import com.inverce.mod.core.functional.IConsumer;

final /* synthetic */ class ProcessingQueue$$Lambda$1 implements Processor {
    private final IConsumer arg$1;

    ProcessingQueue$$Lambda$1(IConsumer iConsumer) {
        this.arg$1 = iConsumer;
    }

    public Object processJob(Object obj) {
        return new ProcessingQueue$$Lambda$9(this.arg$1, obj);
    }
}
