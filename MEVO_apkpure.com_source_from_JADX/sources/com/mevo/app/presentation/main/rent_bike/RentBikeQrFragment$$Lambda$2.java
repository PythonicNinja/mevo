package com.mevo.app.presentation.main.rent_bike;

import com.mevo.app.presentation.dialogs.QrScanResultDialog.DecisionCallback;

final /* synthetic */ class RentBikeQrFragment$$Lambda$2 implements DecisionCallback {
    private final RentBikeQrFragment arg$1;
    private final String arg$2;

    RentBikeQrFragment$$Lambda$2(RentBikeQrFragment rentBikeQrFragment, String str) {
        this.arg$1 = rentBikeQrFragment;
        this.arg$2 = str;
    }

    public void onDecision(boolean z) {
        this.arg$1.lambda$onScanResult$221$RentBikeQrFragment(this.arg$2, z);
    }
}
