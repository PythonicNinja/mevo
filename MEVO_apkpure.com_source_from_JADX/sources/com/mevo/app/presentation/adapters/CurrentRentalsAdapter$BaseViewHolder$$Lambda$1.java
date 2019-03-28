package com.mevo.app.presentation.adapters;

import android.content.Context;
import com.mevo.app.data.model.RentalItem;
import com.mevo.app.presentation.adapters.CurrentRentalsAdapter.BaseViewHolder;

final /* synthetic */ class CurrentRentalsAdapter$BaseViewHolder$$Lambda$1 implements Runnable {
    private final BaseViewHolder arg$1;
    private final RentalItem arg$2;
    private final Context arg$3;

    CurrentRentalsAdapter$BaseViewHolder$$Lambda$1(BaseViewHolder baseViewHolder, RentalItem rentalItem, Context context) {
        this.arg$1 = baseViewHolder;
        this.arg$2 = rentalItem;
        this.arg$3 = context;
    }

    public void run() {
        this.arg$1.lambda$onBindViewHolder$121$CurrentRentalsAdapter$BaseViewHolder(this.arg$2, this.arg$3);
    }
}
