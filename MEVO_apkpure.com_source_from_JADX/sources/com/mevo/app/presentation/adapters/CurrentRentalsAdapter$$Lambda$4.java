package com.mevo.app.presentation.adapters;

import com.inverce.mod.core.functional.IPredicate;
import com.mevo.app.data.model.BookingItem;

final /* synthetic */ class CurrentRentalsAdapter$$Lambda$4 implements IPredicate {
    static final IPredicate $instance = new CurrentRentalsAdapter$$Lambda$4();

    private CurrentRentalsAdapter$$Lambda$4() {
    }

    public boolean test(Object obj) {
        return (obj instanceof BookingItem);
    }
}
