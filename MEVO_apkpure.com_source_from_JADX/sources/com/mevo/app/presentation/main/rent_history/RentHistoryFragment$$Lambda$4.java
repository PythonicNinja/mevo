package com.mevo.app.presentation.main.rent_history;

import com.annimon.stream.function.Function;
import com.mevo.app.data.model.RentalItem;

final /* synthetic */ class RentHistoryFragment$$Lambda$4 implements Function {
    private final RentHistoryFragment arg$1;
    private final String arg$2;

    RentHistoryFragment$$Lambda$4(RentHistoryFragment rentHistoryFragment, String str) {
        this.arg$1 = rentHistoryFragment;
        this.arg$2 = str;
    }

    public Object apply(Object obj) {
        return this.arg$1.lambda$null$239$RentHistoryFragment(this.arg$2, (RentalItem) obj);
    }
}
