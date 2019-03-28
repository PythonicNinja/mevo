package com.mevo.app.data.repository;

import com.mevo.app.data.model.BookingItem;

final /* synthetic */ class NextbikeApiRepository$2$$Lambda$0 implements Runnable {
    private final BookingItem arg$1;

    NextbikeApiRepository$2$$Lambda$0(BookingItem bookingItem) {
        this.arg$1 = bookingItem;
    }

    public void run() {
        new TariffRepository().updateCustomBookingHistoryBookingFinished(this.arg$1.id);
    }
}
