package com.mevo.app.presentation.main.statitons_map.bottom_sheets;

import com.annimon.stream.function.Predicate;
import com.mevo.app.data.model.Bike$$CC;
import com.mevo.app.data.model.BikeJson;

final /* synthetic */ class BookingDialogView$$Lambda$4 implements Predicate {
    static final Predicate $instance = new BookingDialogView$$Lambda$4();

    private BookingDialogView$$Lambda$4() {
    }

    public boolean test(Object obj) {
        return Bike$$CC.isBikeAvailable$$STATIC$$((BikeJson) obj);
    }
}
