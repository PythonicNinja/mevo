package com.mevo.app.data.repository;

import com.annimon.stream.function.Predicate;
import com.mevo.app.data.model.SubscriptionBookingItem;

final /* synthetic */ class TariffRepository$$Lambda$8 implements Predicate {
    private final int arg$1;

    TariffRepository$$Lambda$8(int i) {
        this.arg$1 = i;
    }

    public boolean test(Object obj) {
        return TariffRepository.m77xa06faf4d(this.arg$1, (SubscriptionBookingItem) obj);
    }
}
