package com.mevo.app.tools.bike_tools;

import com.mevo.app.presentation.main.MainActivityInterface;
import com.mevo.app.tools.bike_tools.BookingRepository.BookingListener;

final /* synthetic */ class BookingRepository$$Lambda$1 implements BookingListener {
    private final BookingRepository arg$1;
    private final String arg$2;
    private final BookingListener arg$3;
    private final MainActivityInterface arg$4;

    BookingRepository$$Lambda$1(BookingRepository bookingRepository, String str, BookingListener bookingListener, MainActivityInterface mainActivityInterface) {
        this.arg$1 = bookingRepository;
        this.arg$2 = str;
        this.arg$3 = bookingListener;
        this.arg$4 = mainActivityInterface;
    }

    public void onBooking(boolean z, boolean z2) {
        this.arg$1.lambda$onBookingDecision$332$BookingRepository(this.arg$2, this.arg$3, this.arg$4, z, z2);
    }
}
