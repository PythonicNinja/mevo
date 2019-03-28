package com.mevo.app.data.repository;

import com.annimon.stream.function.Predicate;
import com.mevo.app.data.model.RentalItem;

final /* synthetic */ class TariffRepository$$Lambda$3 implements Predicate {
    static final Predicate $instance = new TariffRepository$$Lambda$3();

    private TariffRepository$$Lambda$3() {
    }

    public boolean test(Object obj) {
        return (((RentalItem) obj).isReturned() ^ 1);
    }
}
