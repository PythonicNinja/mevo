package com.mevo.app.presentation.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.mevo.app.data.model.RentalItem;
import com.mevo.app.presentation.adapters.CurrentRentalsAdapter.ParkedViewHolder;

final /* synthetic */ class CurrentRentalsAdapter$ParkedViewHolder$$Lambda$0 implements OnClickListener {
    private final ParkedViewHolder arg$1;
    private final RentalItem arg$2;

    CurrentRentalsAdapter$ParkedViewHolder$$Lambda$0(ParkedViewHolder parkedViewHolder, RentalItem rentalItem) {
        this.arg$1 = parkedViewHolder;
        this.arg$2 = rentalItem;
    }

    public void onClick(View view) {
        this.arg$1.m139xdd13922d(this.arg$2, view);
    }
}
