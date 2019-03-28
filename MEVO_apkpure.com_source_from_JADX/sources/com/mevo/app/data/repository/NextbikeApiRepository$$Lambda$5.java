package com.mevo.app.data.repository;

import com.mevo.app.data.repository.NextbikeApiRepository.ResponseCurrentRentalsAndBookingsListener;

final /* synthetic */ class NextbikeApiRepository$$Lambda$5 implements Runnable {
    private final NextbikeApiRepository arg$1;
    private final String arg$2;
    private final ResponseCurrentRentalsAndBookingsListener arg$3;

    NextbikeApiRepository$$Lambda$5(NextbikeApiRepository nextbikeApiRepository, String str, ResponseCurrentRentalsAndBookingsListener responseCurrentRentalsAndBookingsListener) {
        this.arg$1 = nextbikeApiRepository;
        this.arg$2 = str;
        this.arg$3 = responseCurrentRentalsAndBookingsListener;
    }

    public void run() {
        this.arg$1.m75xdcb83036(this.arg$2, this.arg$3);
    }
}
