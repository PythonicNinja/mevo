package com.mevo.app.data.repository;

import com.annimon.stream.function.Predicate;
import com.mevo.app.data.model.RentalItem;
import com.mevo.app.presentation.main.ongoing_rent_details.TariffTimeUsage;

final /* synthetic */ class TariffUsageCalculator$$Lambda$7 implements Predicate {
    static final Predicate $instance = new TariffUsageCalculator$$Lambda$7();

    private TariffUsageCalculator$$Lambda$7() {
    }

    public boolean test(Object obj) {
        return (((TariffTimeUsage) obj).item instanceof RentalItem);
    }
}
