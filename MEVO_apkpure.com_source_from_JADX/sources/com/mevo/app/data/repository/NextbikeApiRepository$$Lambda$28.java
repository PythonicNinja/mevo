package com.mevo.app.data.repository;

import com.mevo.app.data.repository.NextbikeApiRepository.BookingListener;

final /* synthetic */ class NextbikeApiRepository$$Lambda$28 implements Runnable {
    private final BookingListener arg$1;

    NextbikeApiRepository$$Lambda$28(BookingListener bookingListener) {
        this.arg$1 = bookingListener;
    }

    public void run() {
        this.arg$1.onResponse(null, false, false);
    }
}
