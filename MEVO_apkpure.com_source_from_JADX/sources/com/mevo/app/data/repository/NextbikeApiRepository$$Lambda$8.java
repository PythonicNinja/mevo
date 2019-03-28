package com.mevo.app.data.repository;

import com.mevo.app.data.repository.NextbikeApiRepository.BookingAvailabilityListener;

final /* synthetic */ class NextbikeApiRepository$$Lambda$8 implements Runnable {
    private final NextbikeApiRepository arg$1;
    private final BookingAvailabilityListener arg$2;

    NextbikeApiRepository$$Lambda$8(NextbikeApiRepository nextbikeApiRepository, BookingAvailabilityListener bookingAvailabilityListener) {
        this.arg$1 = nextbikeApiRepository;
        this.arg$2 = bookingAvailabilityListener;
    }

    public void run() {
        this.arg$1.lambda$isBookingAvailable$50$NextbikeApiRepository(this.arg$2);
    }
}
