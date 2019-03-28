package com.mevo.app.presentation.main.rent_bike;

import android.util.Pair;
import com.mevo.app.presentation.custom_views.CustomInput.Validator;

final /* synthetic */ class RentBikeSheetView$$Lambda$2 implements Validator {
    private final RentBikeSheetView arg$1;

    RentBikeSheetView$$Lambda$2(RentBikeSheetView rentBikeSheetView) {
        this.arg$1 = rentBikeSheetView;
    }

    public Pair checkValid(String str) {
        return this.arg$1.lambda$setValidator$233$RentBikeSheetView(str);
    }
}
