package com.mevo.app.data.repository;

import com.annimon.stream.function.Predicate;
import com.mevo.app.presentation.main.ongoing_rent_details.TariffTimeEvent;
import com.mevo.app.presentation.main.ongoing_rent_details.TariffTimeUsage;

final /* synthetic */ class TariffUsageCalculator$$Lambda$8 implements Predicate {
    private final TariffTimeEvent arg$1;

    TariffUsageCalculator$$Lambda$8(TariffTimeEvent tariffTimeEvent) {
        this.arg$1 = tariffTimeEvent;
    }

    public boolean test(Object obj) {
        return TariffUsageCalculator.lambda$findItemForEvent$96$TariffUsageCalculator(this.arg$1, (TariffTimeUsage) obj);
    }
}
