package com.mevo.app.data.repository;

import com.annimon.stream.function.Predicate;
import com.mevo.app.data.model.SubscriptionBookingItem;

final /* synthetic */ class TariffRepository$$Lambda$13 implements Predicate {
    static final Predicate $instance = new TariffRepository$$Lambda$13();

    private TariffRepository$$Lambda$13() {
    }

    public boolean test(Object obj) {
        return ((SubscriptionBookingItem) obj).isFinished();
    }
}
