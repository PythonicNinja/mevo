package com.mevo.app.presentation.main.rent_history;

import android.view.View;
import android.view.View.OnClickListener;
import com.mevo.app.presentation.main.rent_history.RentHistoryAdapter.HistoryRentalItemViewModel;
import com.mevo.app.presentation.main.rent_history.RentHistoryAdapter.ViewHolder;

final /* synthetic */ class RentHistoryAdapter$ViewHolder$$Lambda$1 implements OnClickListener {
    private final ViewHolder arg$1;
    private final HistoryRentalItemViewModel arg$2;

    RentHistoryAdapter$ViewHolder$$Lambda$1(ViewHolder viewHolder, HistoryRentalItemViewModel historyRentalItemViewModel) {
        this.arg$1 = viewHolder;
        this.arg$2 = historyRentalItemViewModel;
    }

    public void onClick(View view) {
        this.arg$1.lambda$bindData$236$RentHistoryAdapter$ViewHolder(this.arg$2, view);
    }
}
