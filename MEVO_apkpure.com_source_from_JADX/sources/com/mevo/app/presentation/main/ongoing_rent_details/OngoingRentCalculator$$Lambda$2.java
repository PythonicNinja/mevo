package com.mevo.app.presentation.main.ongoing_rent_details;

import java.util.Comparator;

final /* synthetic */ class OngoingRentCalculator$$Lambda$2 implements Comparator {
    static final Comparator $instance = new OngoingRentCalculator$$Lambda$2();

    private OngoingRentCalculator$$Lambda$2() {
    }

    public int compare(Object obj, Object obj2) {
        return Long.compare(((Long) obj).longValue(), ((Long) obj2).longValue());
    }
}
