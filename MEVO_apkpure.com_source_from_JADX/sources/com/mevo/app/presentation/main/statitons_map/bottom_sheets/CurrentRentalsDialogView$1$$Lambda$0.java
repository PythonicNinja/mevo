package com.mevo.app.presentation.main.statitons_map.bottom_sheets;

import com.mevo.app.data.repository.NextbikeApiRepository.SimpleResponseListener;
import com.mevo.app.presentation.dialogs.ParkingDialogView;
import com.mevo.app.presentation.main.statitons_map.bottom_sheets.CurrentRentalsDialogView.C08281;

final /* synthetic */ class CurrentRentalsDialogView$1$$Lambda$0 implements SimpleResponseListener {
    private final C08281 arg$1;
    private final ParkingDialogView arg$2;
    private final String arg$3;

    CurrentRentalsDialogView$1$$Lambda$0(C08281 c08281, ParkingDialogView parkingDialogView, String str) {
        this.arg$1 = c08281;
        this.arg$2 = parkingDialogView;
        this.arg$3 = str;
    }

    public void onResponse(boolean z, Exception exception) {
        this.arg$1.lambda$onParkButtonClick$258$CurrentRentalsDialogView$1(this.arg$2, this.arg$3, z, exception);
    }
}
