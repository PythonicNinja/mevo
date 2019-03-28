package com.inverce.mod.processing;

import com.inverce.mod.core.functional.IConsumer;

final /* synthetic */ class ProcessingQueue$$Lambda$9 implements Runnable {
    private final IConsumer arg$1;
    private final Object arg$2;

    ProcessingQueue$$Lambda$9(IConsumer iConsumer, Object obj) {
        this.arg$1 = iConsumer;
        this.arg$2 = obj;
    }

    public void run() {
        this.arg$1.accept(this.arg$2);
    }
}
