package com.mevo.app.presentation.adapters;

import android.content.Context;
import com.mevo.app.data.model.BookingItem;
import com.mevo.app.presentation.adapters.CurrentRentalsAdapter.BookedViewHolder;

final /* synthetic */ class CurrentRentalsAdapter$BookedViewHolder$$Lambda$1 implements Runnable {
    private final BookedViewHolder arg$1;
    private final BookingItem arg$2;
    private final Context arg$3;

    CurrentRentalsAdapter$BookedViewHolder$$Lambda$1(BookedViewHolder bookedViewHolder, BookingItem bookingItem, Context context) {
        this.arg$1 = bookedViewHolder;
        this.arg$2 = bookingItem;
        this.arg$3 = context;
    }

    public void run() {
        this.arg$1.m138xd0d9b549(this.arg$2, this.arg$3);
    }
}
