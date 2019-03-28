package com.mevo.app.presentation.main.ongoing_rent_details;

import com.annimon.stream.function.Predicate;
import com.mevo.app.data.model.BookingItem;

final /* synthetic */ class OngoingRentCalculator$$Lambda$1 implements Predicate {
    private final OngoingRentCalculator arg$1;

    OngoingRentCalculator$$Lambda$1(OngoingRentCalculator ongoingRentCalculator) {
        this.arg$1 = ongoingRentCalculator;
    }

    public boolean test(Object obj) {
        return this.arg$1.lambda$filterUnusedHistoryItems$202$OngoingRentCalculator((BookingItem) obj);
    }
}
