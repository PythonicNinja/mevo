package com.mevo.app.data.repository;

import com.annimon.stream.function.Predicate;
import com.mevo.app.presentation.main.ongoing_rent_details.TariffTimeEvent;

final /* synthetic */ class TariffUsageCalculator$$Lambda$5 implements Predicate {
    private final long arg$1;
    private final long arg$2;

    TariffUsageCalculator$$Lambda$5(long j, long j2) {
        this.arg$1 = j;
        this.arg$2 = j2;
    }

    public boolean test(Object obj) {
        return TariffUsageCalculator.lambda$calculateTimeUsage$93$TariffUsageCalculator(this.arg$1, this.arg$2, (TariffTimeEvent) obj);
    }
}
