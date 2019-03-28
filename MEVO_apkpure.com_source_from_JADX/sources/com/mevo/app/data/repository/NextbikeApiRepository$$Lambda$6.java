package com.mevo.app.data.repository;

import com.annimon.stream.function.Predicate;
import com.mevo.app.data.model.RentalItem;

final /* synthetic */ class NextbikeApiRepository$$Lambda$6 implements Predicate {
    static final Predicate $instance = new NextbikeApiRepository$$Lambda$6();

    private NextbikeApiRepository$$Lambda$6() {
    }

    public boolean test(Object obj) {
        return (((RentalItem) obj).isReturned() ^ 1);
    }
}
