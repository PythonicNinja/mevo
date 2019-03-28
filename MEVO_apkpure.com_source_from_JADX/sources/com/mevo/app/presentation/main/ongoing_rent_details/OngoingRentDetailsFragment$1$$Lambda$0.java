package com.mevo.app.presentation.main.ongoing_rent_details;

import com.annimon.stream.function.Predicate;
import com.mevo.app.data.model.RentalItem;
import com.mevo.app.presentation.main.ongoing_rent_details.OngoingRentDetailsFragment.C08251;

final /* synthetic */ class OngoingRentDetailsFragment$1$$Lambda$0 implements Predicate {
    private final C08251 arg$1;

    OngoingRentDetailsFragment$1$$Lambda$0(C08251 c08251) {
        this.arg$1 = c08251;
    }

    public boolean test(Object obj) {
        return this.arg$1.lambda$onResponse$217$OngoingRentDetailsFragment$1((RentalItem) obj);
    }
}
