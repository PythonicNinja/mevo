package com.mevo.app.data.repository;

import com.mevo.app.data.model.repository.ResponseCurrentRentalsAndBookings;
import com.mevo.app.data.repository.NextbikeApiRepository.ResponseCurrentRentalsAndBookingsListener;

final /* synthetic */ class NextbikeApiRepository$$Lambda$15 implements Runnable {
    private final ResponseCurrentRentalsAndBookingsListener arg$1;
    private final ResponseCurrentRentalsAndBookings arg$2;

    NextbikeApiRepository$$Lambda$15(ResponseCurrentRentalsAndBookingsListener responseCurrentRentalsAndBookingsListener, ResponseCurrentRentalsAndBookings responseCurrentRentalsAndBookings) {
        this.arg$1 = responseCurrentRentalsAndBookingsListener;
        this.arg$2 = responseCurrentRentalsAndBookings;
    }

    public void run() {
        this.arg$1.onResponse(this.arg$2.bookings, this.arg$2.rentals, true, null);
    }
}
