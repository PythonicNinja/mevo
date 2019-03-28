package com.mevo.app.presentation.main.rent_bike;

import com.mevo.app.tools.bike_tools.RentBikeRepository.RentCallback;

final /* synthetic */ class RentBikeSheetView$$Lambda$3 implements RentCallback {
    private final RentBikeSheetView arg$1;

    RentBikeSheetView$$Lambda$3(RentBikeSheetView rentBikeSheetView) {
        this.arg$1 = rentBikeSheetView;
    }

    public void onRent(boolean z) {
        this.arg$1.lambda$rentBike$234$RentBikeSheetView(z);
    }
}
