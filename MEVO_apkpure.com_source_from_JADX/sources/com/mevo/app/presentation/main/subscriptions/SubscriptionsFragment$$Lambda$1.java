package com.mevo.app.presentation.main.subscriptions;

import com.mevo.app.data.repository.NextbikeApiRepository.ResponseListener;
import java.util.List;

final /* synthetic */ class SubscriptionsFragment$$Lambda$1 implements ResponseListener {
    private final SubscriptionsFragment arg$1;

    SubscriptionsFragment$$Lambda$1(SubscriptionsFragment subscriptionsFragment) {
        this.arg$1 = subscriptionsFragment;
    }

    public void onResponse(Object obj, boolean z, Exception exception) {
        this.arg$1.lambda$fetchAvailableTariffs$275$SubscriptionsFragment((List) obj, z, exception);
    }
}
