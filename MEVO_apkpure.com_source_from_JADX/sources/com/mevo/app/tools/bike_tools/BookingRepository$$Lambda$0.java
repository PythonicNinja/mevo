package com.mevo.app.tools.bike_tools;

import com.mevo.app.data.model.BookingItem;
import com.mevo.app.data.repository.NextbikeApiRepository.BookingListener;

final /* synthetic */ class BookingRepository$$Lambda$0 implements BookingListener {
    private final BookingRepository.BookingListener arg$1;

    BookingRepository$$Lambda$0(BookingRepository.BookingListener bookingListener) {
        this.arg$1 = bookingListener;
    }

    public void onResponse(BookingItem bookingItem, boolean z, boolean z2) {
        BookingRepository.lambda$tryBookBike$331$BookingRepository(this.arg$1, bookingItem, z, z2);
    }
}
