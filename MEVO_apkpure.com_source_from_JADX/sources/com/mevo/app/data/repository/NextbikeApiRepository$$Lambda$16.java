package com.mevo.app.data.repository;

import com.mevo.app.data.repository.NextbikeApiRepository.ResponseCurrentRentalsAndBookingsListener;

final /* synthetic */ class NextbikeApiRepository$$Lambda$16 implements Runnable {
    private final ResponseCurrentRentalsAndBookingsListener arg$1;

    NextbikeApiRepository$$Lambda$16(ResponseCurrentRentalsAndBookingsListener responseCurrentRentalsAndBookingsListener) {
        this.arg$1 = responseCurrentRentalsAndBookingsListener;
    }

    public void run() {
        this.arg$1.onResponse(null, null, false, null);
    }
}
