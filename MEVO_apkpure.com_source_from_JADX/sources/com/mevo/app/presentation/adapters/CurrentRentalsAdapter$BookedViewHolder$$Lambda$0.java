package com.mevo.app.presentation.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.mevo.app.data.model.BookingItem;
import com.mevo.app.presentation.adapters.CurrentRentalsAdapter.BookedViewHolder;

final /* synthetic */ class CurrentRentalsAdapter$BookedViewHolder$$Lambda$0 implements OnClickListener {
    private final BookedViewHolder arg$1;
    private final BookingItem arg$2;

    CurrentRentalsAdapter$BookedViewHolder$$Lambda$0(BookedViewHolder bookedViewHolder, BookingItem bookingItem) {
        this.arg$1 = bookedViewHolder;
        this.arg$2 = bookingItem;
    }

    public void onClick(View view) {
        this.arg$1.m137x79bbc46a(this.arg$2, view);
    }
}
