package com.mevo.app.data.repository;

import com.annimon.stream.function.Function;
import com.mevo.app.data.model.RentalItem;
import com.mevo.app.presentation.main.ongoing_rent_details.TariffTimeUsage;

final /* synthetic */ class TariffUsageCalculator$$Lambda$1 implements Function {
    private final long arg$1;
    private final long arg$2;

    TariffUsageCalculator$$Lambda$1(long j, long j2) {
        this.arg$1 = j;
        this.arg$2 = j2;
    }

    public Object apply(Object obj) {
        return new TariffTimeUsage(((RentalItem) obj).startTimestampSeconds, ((RentalItem) obj).endTimestampSeconds, this.arg$1, this.arg$2, (RentalItem) obj);
    }
}
