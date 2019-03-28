package com.mevo.app.data.repository;

import com.annimon.stream.function.Predicate;
import com.mevo.app.data.model.BookingItem;

final /* synthetic */ class TariffRepository$$Lambda$4 implements Predicate {
    static final Predicate $instance = new TariffRepository$$Lambda$4();

    private TariffRepository$$Lambda$4() {
    }

    public boolean test(Object obj) {
        return (((BookingItem) obj).isFinishedAccordingToApi() ^ 1);
    }
}
