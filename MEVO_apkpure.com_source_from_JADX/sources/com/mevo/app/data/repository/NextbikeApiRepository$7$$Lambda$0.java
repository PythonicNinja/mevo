package com.mevo.app.data.repository;

import com.annimon.stream.function.Predicate;
import com.mevo.app.data.model.RentalItem;

final /* synthetic */ class NextbikeApiRepository$7$$Lambda$0 implements Predicate {
    static final Predicate $instance = new NextbikeApiRepository$7$$Lambda$0();

    private NextbikeApiRepository$7$$Lambda$0() {
    }

    public boolean test(Object obj) {
        return (((RentalItem) obj).isReturned() ^ 1);
    }
}
