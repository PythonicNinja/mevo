package com.mevo.app.data.repository;

import com.annimon.stream.function.Predicate;
import com.mevo.app.data.model.SubscriptionBookingItem;

final /* synthetic */ class TariffRepository$$Lambda$12 implements Predicate {
    private final long arg$1;

    TariffRepository$$Lambda$12(long j) {
        this.arg$1 = j;
    }

    public boolean test(Object obj) {
        return ((SubscriptionBookingItem) obj).countsTowardsToday(this.arg$1);
    }
}
