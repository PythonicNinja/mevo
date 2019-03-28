package com.mevo.app.data.repository;

import com.mevo.app.data.model.response.ResponseBookBike;
import com.mevo.app.data.repository.NextbikeApiRepository.BookingListener;

final /* synthetic */ class NextbikeApiRepository$$Lambda$30 implements Runnable {
    private final BookingListener arg$1;
    private final ResponseBookBike arg$2;

    NextbikeApiRepository$$Lambda$30(BookingListener bookingListener, ResponseBookBike responseBookBike) {
        this.arg$1 = bookingListener;
        this.arg$2 = responseBookBike;
    }

    public void run() {
        this.arg$1.onResponse(this.arg$2.getBookingItem(), true, false);
    }
}
