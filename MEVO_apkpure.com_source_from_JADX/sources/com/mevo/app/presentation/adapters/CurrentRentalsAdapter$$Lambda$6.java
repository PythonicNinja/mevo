package com.mevo.app.presentation.adapters;

import com.inverce.mod.core.functional.IPredicate;
import com.mevo.app.data.model.RentalWithBattery;

final /* synthetic */ class CurrentRentalsAdapter$$Lambda$6 implements IPredicate {
    static final IPredicate $instance = new CurrentRentalsAdapter$$Lambda$6();

    private CurrentRentalsAdapter$$Lambda$6() {
    }

    public boolean test(Object obj) {
        return (obj instanceof RentalWithBattery);
    }
}
