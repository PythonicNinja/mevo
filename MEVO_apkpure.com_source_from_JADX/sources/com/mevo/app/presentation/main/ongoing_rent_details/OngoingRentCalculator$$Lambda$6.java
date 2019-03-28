package com.mevo.app.presentation.main.ongoing_rent_details;

import com.annimon.stream.function.ToLongFunction;

final /* synthetic */ class OngoingRentCalculator$$Lambda$6 implements ToLongFunction {
    static final ToLongFunction $instance = new OngoingRentCalculator$$Lambda$6();

    private OngoingRentCalculator$$Lambda$6() {
    }

    public long applyAsLong(Object obj) {
        return ((TariffTimeUsage) obj).usedPaidSeconds;
    }
}
