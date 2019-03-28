package com.mevo.app.presentation.main.subscriptions;

import com.mevo.app.data.repository.NextbikeApiRepository.SimpleResponseListener;

final /* synthetic */ class SubscriptionsFragment$$Lambda$3 implements SimpleResponseListener {
    private final SubscriptionsFragment arg$1;

    SubscriptionsFragment$$Lambda$3(SubscriptionsFragment subscriptionsFragment) {
        this.arg$1 = subscriptionsFragment;
    }

    public void onResponse(boolean z, Exception exception) {
        this.arg$1.lambda$subscribe$277$SubscriptionsFragment(z, exception);
    }
}
