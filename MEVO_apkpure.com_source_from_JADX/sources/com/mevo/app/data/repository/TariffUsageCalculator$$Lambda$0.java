package com.mevo.app.data.repository;

import com.annimon.stream.function.Predicate;
import com.mevo.app.data.model.RentalItem;

final /* synthetic */ class TariffUsageCalculator$$Lambda$0 implements Predicate {
    private final long arg$1;
    private final long arg$2;

    TariffUsageCalculator$$Lambda$0(long j, long j2) {
        this.arg$1 = j;
        this.arg$2 = j2;
    }

    public boolean test(Object obj) {
        return TariffUsageCalculator.lambda$calculateTimeUsage$88$TariffUsageCalculator(this.arg$1, this.arg$2, (RentalItem) obj);
    }
}
