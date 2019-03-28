package com.inverce.mod.processing;

final /* synthetic */ class Processor$EX$$Lambda$0 implements Processor {
    private final Processor arg$1;
    private final Processor arg$2;

    Processor$EX$$Lambda$0(Processor processor, Processor processor2) {
        this.arg$1 = processor;
        this.arg$2 = processor2;
    }

    public Object processJob(Object obj) {
        return this.arg$1.processJob(this.arg$2.processJob(obj));
    }
}
