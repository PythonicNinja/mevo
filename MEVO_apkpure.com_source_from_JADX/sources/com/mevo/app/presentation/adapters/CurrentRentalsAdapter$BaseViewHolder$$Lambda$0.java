package com.mevo.app.presentation.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.mevo.app.data.model.RentalItem;
import com.mevo.app.presentation.adapters.CurrentRentalsAdapter.BaseViewHolder;

final /* synthetic */ class CurrentRentalsAdapter$BaseViewHolder$$Lambda$0 implements OnClickListener {
    private final BaseViewHolder arg$1;
    private final RentalItem arg$2;

    CurrentRentalsAdapter$BaseViewHolder$$Lambda$0(BaseViewHolder baseViewHolder, RentalItem rentalItem) {
        this.arg$1 = baseViewHolder;
        this.arg$2 = rentalItem;
    }

    public void onClick(View view) {
        this.arg$1.lambda$onBindViewHolder$120$CurrentRentalsAdapter$BaseViewHolder(this.arg$2, view);
    }
}
