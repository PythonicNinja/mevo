package com.mevo.app.data.repository;

import com.mevo.app.data.repository.NextbikeApiRepository.BookingListener;

final /* synthetic */ class NextbikeApiRepository$$Lambda$0 implements Runnable {
    private final BookingListener arg$1;
    private final String arg$2;

    NextbikeApiRepository$$Lambda$0(BookingListener bookingListener, String str) {
        this.arg$1 = bookingListener;
        this.arg$2 = str;
    }

    public void run() {
        NextbikeApiRepository.lambda$bookBike$23$NextbikeApiRepository(this.arg$1, this.arg$2);
    }
}
