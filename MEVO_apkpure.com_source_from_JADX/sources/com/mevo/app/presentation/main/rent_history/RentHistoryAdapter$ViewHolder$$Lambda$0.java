package com.mevo.app.presentation.main.rent_history;

import android.view.View;
import android.view.View.OnClickListener;
import com.mevo.app.C0434R;
import com.mevo.app.presentation.dialogs.InfoDialog.Builder;
import com.mevo.app.presentation.main.rent_history.RentHistoryAdapter.HistoryRentalItemViewModel;

final /* synthetic */ class RentHistoryAdapter$ViewHolder$$Lambda$0 implements OnClickListener {
    private final HistoryRentalItemViewModel arg$1;

    RentHistoryAdapter$ViewHolder$$Lambda$0(HistoryRentalItemViewModel historyRentalItemViewModel) {
        this.arg$1 = historyRentalItemViewModel;
    }

    public void onClick(View view) {
        new Builder().setText(this.arg$1.serviceFeeInfo).setPositiveButton((int) C0434R.string.close, null).build().show();
    }
}
