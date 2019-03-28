package com.mevo.app.tools.bike_tools;

import com.mevo.app.presentation.dialogs.BikeReservedDialog.FinishListener;

final /* synthetic */ class BookingRepository$$Lambda$2 implements FinishListener {
    private final BookingRepository arg$1;

    BookingRepository$$Lambda$2(BookingRepository bookingRepository) {
        this.arg$1 = bookingRepository;
    }

    public void onFinished() {
        this.arg$1.bridge$lambda$0$BookingRepository();
    }
}
