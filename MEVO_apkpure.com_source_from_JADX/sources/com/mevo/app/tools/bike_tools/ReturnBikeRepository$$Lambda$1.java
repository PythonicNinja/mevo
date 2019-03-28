package com.mevo.app.tools.bike_tools;

import com.mevo.app.presentation.dialogs.ReturnConfirmDialog.DecisionCallback;

final /* synthetic */ class ReturnBikeRepository$$Lambda$1 implements DecisionCallback {
    private final ReturnBikeRepository arg$1;

    ReturnBikeRepository$$Lambda$1(ReturnBikeRepository returnBikeRepository) {
        this.arg$1 = returnBikeRepository;
    }

    public void onDecision(boolean z) {
        this.arg$1.lambda$returnBikeOutsideStation$334$ReturnBikeRepository(z);
    }
}
