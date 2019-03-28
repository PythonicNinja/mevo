package com.mevo.app.presentation.main.subscriptions;

import com.mevo.app.data.model.repository.ResponseActiveTariff;
import com.mevo.app.data.repository.NextbikeApiRepository.ResponseListener;

final /* synthetic */ class SubscriptionsFragment$$Lambda$0 implements ResponseListener {
    private final SubscriptionsFragment arg$1;

    SubscriptionsFragment$$Lambda$0(SubscriptionsFragment subscriptionsFragment) {
        this.arg$1 = subscriptionsFragment;
    }

    public void onResponse(Object obj, boolean z, Exception exception) {
        this.arg$1.lambda$fetchCurrentTariff$274$SubscriptionsFragment((ResponseActiveTariff) obj, z, exception);
    }
}
