package com.inverce.mod.integrations.support.recycler;

import java.util.List;

final /* synthetic */ class RecyclerAdapter$$Lambda$0 implements Runnable {
    private final RecyclerAdapter arg$1;
    private final List arg$2;
    private final boolean arg$3;

    RecyclerAdapter$$Lambda$0(RecyclerAdapter recyclerAdapter, List list, boolean z) {
        this.arg$1 = recyclerAdapter;
        this.arg$2 = list;
        this.arg$3 = z;
    }

    public void run() {
        this.arg$1.lambda$setData$0$RecyclerAdapter(this.arg$2, this.arg$3);
    }
}
