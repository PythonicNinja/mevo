package com.mevo.app.data.repository;

import com.annimon.stream.function.Predicate;
import com.mevo.app.data.model.BookingItem;
import com.mevo.app.presentation.main.ongoing_rent_details.TariffTimeUsage;

final /* synthetic */ class TariffUsageCalculator$$Lambda$9 implements Predicate {
    static final Predicate $instance = new TariffUsageCalculator$$Lambda$9();

    private TariffUsageCalculator$$Lambda$9() {
    }

    public boolean test(Object obj) {
        return (((TariffTimeUsage) obj).item instanceof BookingItem);
    }
}
