package com.mevo.app.data.repository;

import com.annimon.stream.function.Predicate;
import com.mevo.app.data.model.BookingItem;

final /* synthetic */ class TariffUsageCalculator$$Lambda$3 implements Predicate {
    static final Predicate $instance = new TariffUsageCalculator$$Lambda$3();

    private TariffUsageCalculator$$Lambda$3() {
    }

    public boolean test(Object obj) {
        return TariffUsageCalculator.lambda$calculateTimeUsage$91$TariffUsageCalculator((BookingItem) obj);
    }
}
