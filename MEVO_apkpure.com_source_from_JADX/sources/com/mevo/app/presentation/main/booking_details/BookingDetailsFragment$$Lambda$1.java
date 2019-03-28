package com.mevo.app.presentation.main.booking_details;

import com.mevo.app.data.repository.NextbikeApiRepository.SimpleResponseListener;

final /* synthetic */ class BookingDetailsFragment$$Lambda$1 implements SimpleResponseListener {
    private final BookingDetailsFragment arg$1;
    private final String arg$2;

    BookingDetailsFragment$$Lambda$1(BookingDetailsFragment bookingDetailsFragment, String str) {
        this.arg$1 = bookingDetailsFragment;
        this.arg$2 = str;
    }

    public void onResponse(boolean z, Exception exception) {
        this.arg$1.lambda$onCancelBookingBike$176$BookingDetailsFragment(this.arg$2, z, exception);
    }
}
