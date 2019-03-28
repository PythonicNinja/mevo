package com.mevo.app.presentation.main.rent_history;

import com.annimon.stream.function.Predicate;
import com.mevo.app.data.model.RentalItem;

final /* synthetic */ class RentHistoryFragment$$Lambda$1 implements Predicate {
    static final Predicate $instance = new RentHistoryFragment$$Lambda$1();

    private RentHistoryFragment$$Lambda$1() {
    }

    public boolean test(Object obj) {
        return RentHistoryFragment.lambda$onHistoryReceived$237$RentHistoryFragment((RentalItem) obj);
    }
}
