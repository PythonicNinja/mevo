package com.inverce.mod.processing;

import com.inverce.mod.core.functional.IConsumer;

final /* synthetic */ class ProcessingQueue$$Lambda$3 implements Processor {
    private final IConsumer arg$1;

    ProcessingQueue$$Lambda$3(IConsumer iConsumer) {
        this.arg$1 = iConsumer;
    }

    public Object processJob(Object obj) {
        return new ProcessingQueue$$Lambda$8(this.arg$1, obj);
    }
}
