package com.inverce.mod.events;

import com.inverce.mod.core.functional.IFunction;

final /* synthetic */ class Channel$$Lambda$0 implements IFunction {
    static final IFunction $instance = new Channel$$Lambda$0();

    private Channel$$Lambda$0() {
    }

    public Object apply(Object obj) {
        return Channel.getListenersInClassImpl((Class) obj);
    }
}
