package com.inverce.mod.processing;

import java.util.concurrent.Callable;

final /* synthetic */ class Processor$$Lambda$0 implements Processor {
    static final Processor $instance = new Processor$$Lambda$0();

    private Processor$$Lambda$0() {
    }

    public Object processJob(Object obj) {
        return ((Callable) obj).call();
    }
}
