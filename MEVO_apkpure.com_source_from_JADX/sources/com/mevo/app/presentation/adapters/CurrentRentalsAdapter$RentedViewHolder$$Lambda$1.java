package com.mevo.app.presentation.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.mevo.app.data.model.RentalItem;
import com.mevo.app.presentation.adapters.CurrentRentalsAdapter.RentedViewHolder;

final /* synthetic */ class CurrentRentalsAdapter$RentedViewHolder$$Lambda$1 implements OnClickListener {
    private final RentedViewHolder arg$1;
    private final RentalItem arg$2;

    CurrentRentalsAdapter$RentedViewHolder$$Lambda$1(RentedViewHolder rentedViewHolder, RentalItem rentalItem) {
        this.arg$1 = rentedViewHolder;
        this.arg$2 = rentalItem;
    }

    public void onClick(View view) {
        this.arg$1.m142x86514780(this.arg$2, view);
    }
}
