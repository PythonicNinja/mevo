package com.mevo.app.presentation.main.ongoing_rent_details;

import com.annimon.stream.function.ToLongFunction;

final /* synthetic */ class OngoingRentCalculator$$Lambda$5 implements ToLongFunction {
    static final ToLongFunction $instance = new OngoingRentCalculator$$Lambda$5();

    private OngoingRentCalculator$$Lambda$5() {
    }

    public long applyAsLong(Object obj) {
        return ((TariffTimeUsage) obj).usedFreeSeconds;
    }
}
