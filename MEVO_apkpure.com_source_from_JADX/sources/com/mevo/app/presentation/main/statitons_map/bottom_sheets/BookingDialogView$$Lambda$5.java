package com.mevo.app.presentation.main.statitons_map.bottom_sheets;

import com.mevo.app.data.repository.NextbikeApiRepository.BookingAvailabilityListener;
import com.mevo.app.presentation.main.MainActivityInterface;

final /* synthetic */ class BookingDialogView$$Lambda$5 implements BookingAvailabilityListener {
    private final BookingDialogView arg$1;
    private final MainActivityInterface arg$2;
    private final String arg$3;

    BookingDialogView$$Lambda$5(BookingDialogView bookingDialogView, MainActivityInterface mainActivityInterface, String str) {
        this.arg$1 = bookingDialogView;
        this.arg$2 = mainActivityInterface;
        this.arg$3 = str;
    }

    public void onResponse(boolean z, boolean z2) {
        this.arg$1.lambda$onTryBookBike$253$BookingDialogView(this.arg$2, this.arg$3, z, z2);
    }
}
