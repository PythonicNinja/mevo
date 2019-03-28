package com.mevo.app.presentation.main.ongoing_rent_details;

import com.annimon.stream.function.Predicate;
import com.mevo.app.data.model.RentalItem;

final /* synthetic */ class OngoingRentCalculator$$Lambda$0 implements Predicate {
    private final OngoingRentCalculator arg$1;

    OngoingRentCalculator$$Lambda$0(OngoingRentCalculator ongoingRentCalculator) {
        this.arg$1 = ongoingRentCalculator;
    }

    public boolean test(Object obj) {
        return this.arg$1.lambda$filterUnusedHistoryItems$201$OngoingRentCalculator((RentalItem) obj);
    }
}
