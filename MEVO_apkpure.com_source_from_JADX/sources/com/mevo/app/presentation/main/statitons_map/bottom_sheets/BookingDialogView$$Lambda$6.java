package com.mevo.app.presentation.main.statitons_map.bottom_sheets;

import com.mevo.app.tools.bike_tools.BookingRepository.BookingListener;

final /* synthetic */ class BookingDialogView$$Lambda$6 implements BookingListener {
    private final BookingDialogView arg$1;

    BookingDialogView$$Lambda$6(BookingDialogView bookingDialogView) {
        this.arg$1 = bookingDialogView;
    }

    public void onBooking(boolean z, boolean z2) {
        this.arg$1.lambda$onBookingDecision$254$BookingDialogView(z, z2);
    }
}
