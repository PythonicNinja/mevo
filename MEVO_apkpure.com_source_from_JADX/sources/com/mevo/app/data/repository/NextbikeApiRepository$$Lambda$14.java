package com.mevo.app.data.repository;

import com.mevo.app.data.repository.NextbikeApiRepository.BookingAvailabilityListener;

final /* synthetic */ class NextbikeApiRepository$$Lambda$14 implements Runnable {
    private final BookingAvailabilityListener arg$1;

    NextbikeApiRepository$$Lambda$14(BookingAvailabilityListener bookingAvailabilityListener) {
        this.arg$1 = bookingAvailabilityListener;
    }

    public void run() {
        this.arg$1.onResponse(true, true);
    }
}
