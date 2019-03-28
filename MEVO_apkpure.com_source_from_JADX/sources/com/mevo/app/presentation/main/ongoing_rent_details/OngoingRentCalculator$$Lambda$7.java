package com.mevo.app.presentation.main.ongoing_rent_details;

import com.annimon.stream.function.ToLongFunction;

final /* synthetic */ class OngoingRentCalculator$$Lambda$7 implements ToLongFunction {
    static final ToLongFunction $instance = new OngoingRentCalculator$$Lambda$7();

    private OngoingRentCalculator$$Lambda$7() {
    }

    public long applyAsLong(Object obj) {
        return ((TariffTimeUsage) obj).usedFreeSeconds;
    }
}
