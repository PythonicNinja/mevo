package com.mevo.app.presentation.main.ongoing_rent_details;

import com.annimon.stream.function.Predicate;

final /* synthetic */ class OngoingRentCalculator$$Lambda$3 implements Predicate {
    private final OngoingRentCalculator arg$1;

    OngoingRentCalculator$$Lambda$3(OngoingRentCalculator ongoingRentCalculator) {
        this.arg$1 = ongoingRentCalculator;
    }

    public boolean test(Object obj) {
        return this.arg$1.lambda$calculateUsedFreeSecondsAndCost$203$OngoingRentCalculator((TariffTimeUsage) obj);
    }
}
