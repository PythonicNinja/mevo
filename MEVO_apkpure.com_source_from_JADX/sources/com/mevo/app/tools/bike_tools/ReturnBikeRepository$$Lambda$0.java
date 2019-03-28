package com.mevo.app.tools.bike_tools;

import com.mevo.app.presentation.dialogs.ReturnConfirmDialog.DecisionCallback;

final /* synthetic */ class ReturnBikeRepository$$Lambda$0 implements DecisionCallback {
    private final ReturnBikeRepository arg$1;
    private final int arg$2;

    ReturnBikeRepository$$Lambda$0(ReturnBikeRepository returnBikeRepository, int i) {
        this.arg$1 = returnBikeRepository;
        this.arg$2 = i;
    }

    public void onDecision(boolean z) {
        this.arg$1.lambda$tryReturnBikeOnStation$333$ReturnBikeRepository(this.arg$2, z);
    }
}
