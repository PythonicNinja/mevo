package com.mevo.app.presentation.main.rent_history;

import com.annimon.stream.function.Predicate;
import com.mevo.app.data.model.RentalItem;

final /* synthetic */ class RentHistoryFragment$$Lambda$0 implements Predicate {
    static final Predicate $instance = new RentHistoryFragment$$Lambda$0();

    private RentHistoryFragment$$Lambda$0() {
    }

    public boolean test(Object obj) {
        return ((RentalItem) obj).isReturned();
    }
}
