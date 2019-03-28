package com.mevo.app.presentation.main.rent_bike;

import android.support.design.widget.BottomSheetBehavior;
import com.mevo.app.presentation.main.rent_bike.RentBikeSheetView.RentBikeSheetViewListener;
import com.mevo.app.tools.BotomSheetHelper;

final /* synthetic */ class RentBikeQrFragment$$Lambda$3 implements RentBikeSheetViewListener {
    private final BottomSheetBehavior arg$1;

    RentBikeQrFragment$$Lambda$3(BottomSheetBehavior bottomSheetBehavior) {
        this.arg$1 = bottomSheetBehavior;
    }

    public void onHeaderClick() {
        BotomSheetHelper.toggleSheet(this.arg$1);
    }
}
