package com.mevo.app.presentation.main.booking_details;

import com.mevo.app.data.repository.DirectionsRepository.DirectionsResponseListener;
import java.util.List;

final /* synthetic */ class BookingDetailsFragment$$Lambda$0 implements DirectionsResponseListener {
    private final BookingDetailsFragment arg$1;

    BookingDetailsFragment$$Lambda$0(BookingDetailsFragment bookingDetailsFragment) {
        this.arg$1 = bookingDetailsFragment;
    }

    public void onResponse(List list, long j, boolean z, Exception exception) {
        this.arg$1.lambda$fetchRoute$174$BookingDetailsFragment(list, j, z, exception);
    }
}
