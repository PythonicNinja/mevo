package com.mevo.app.data.repository;

import com.annimon.stream.function.Predicate;
import com.mevo.app.presentation.main.ongoing_rent_details.TariffTimeUsage;

final /* synthetic */ class TariffUsageCalculator$$Lambda$6 implements Predicate {
    private final long arg$1;

    TariffUsageCalculator$$Lambda$6(long j) {
        this.arg$1 = j;
    }

    public boolean test(Object obj) {
        return TariffUsageCalculator.lambda$calculateTimeUsage$94$TariffUsageCalculator(this.arg$1, (TariffTimeUsage) obj);
    }
}
