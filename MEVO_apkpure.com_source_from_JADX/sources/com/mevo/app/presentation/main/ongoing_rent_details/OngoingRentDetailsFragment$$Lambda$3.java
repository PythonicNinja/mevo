package com.mevo.app.presentation.main.ongoing_rent_details;

import com.annimon.stream.function.Predicate;
import com.mevo.app.data.model.BookingItem;

final /* synthetic */ class OngoingRentDetailsFragment$$Lambda$3 implements Predicate {
    private final OngoingRentDetailsFragment arg$1;

    OngoingRentDetailsFragment$$Lambda$3(OngoingRentDetailsFragment ongoingRentDetailsFragment) {
        this.arg$1 = ongoingRentDetailsFragment;
    }

    public boolean test(Object obj) {
        return this.arg$1.lambda$getBookingForTrackedRental$216$OngoingRentDetailsFragment((BookingItem) obj);
    }
}
