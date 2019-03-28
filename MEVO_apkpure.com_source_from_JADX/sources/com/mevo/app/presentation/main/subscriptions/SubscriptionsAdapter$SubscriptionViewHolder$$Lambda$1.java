package com.mevo.app.presentation.main.subscriptions;

import com.annimon.stream.function.Predicate;
import com.mevo.app.data.model.Tariff;
import com.mevo.app.data.model.TariffExtraInfo;

final /* synthetic */ class SubscriptionsAdapter$SubscriptionViewHolder$$Lambda$1 implements Predicate {
    private final Tariff arg$1;

    SubscriptionsAdapter$SubscriptionViewHolder$$Lambda$1(Tariff tariff) {
        this.arg$1 = tariff;
    }

    public boolean test(Object obj) {
        return ((TariffExtraInfo) obj).getCode().equals(this.arg$1.code);
    }
}
