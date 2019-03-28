package com.mevo.app.presentation.main.ongoing_rent_details;

import com.annimon.stream.function.Predicate;

final /* synthetic */ class OngoingRentCalculator$$Lambda$4 implements Predicate {
    private final OngoingRentCalculator arg$1;

    OngoingRentCalculator$$Lambda$4(OngoingRentCalculator ongoingRentCalculator) {
        this.arg$1 = ongoingRentCalculator;
    }

    public boolean test(Object obj) {
        return this.arg$1.lambda$calculateUsedFreeSecondsAndCost$204$OngoingRentCalculator((TariffTimeUsage) obj);
    }
}
