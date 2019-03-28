package com.mevo.app.presentation.main.ongoing_rent_details;

import com.annimon.stream.function.ToLongFunction;

final /* synthetic */ class OngoingRentCalculator$$Lambda$8 implements ToLongFunction {
    static final ToLongFunction $instance = new OngoingRentCalculator$$Lambda$8();

    private OngoingRentCalculator$$Lambda$8() {
    }

    public long applyAsLong(Object obj) {
        return ((TariffTimeUsage) obj).usedPaidSeconds;
    }
}
