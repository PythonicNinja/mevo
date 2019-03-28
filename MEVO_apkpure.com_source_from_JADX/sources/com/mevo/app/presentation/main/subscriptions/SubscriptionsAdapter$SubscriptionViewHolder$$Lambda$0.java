package com.mevo.app.presentation.main.subscriptions;

import android.view.View;
import android.view.View.OnClickListener;
import com.mevo.app.data.model.Tariff;

final /* synthetic */ class SubscriptionsAdapter$SubscriptionViewHolder$$Lambda$0 implements OnClickListener {
    private final SubscriptionViewHolder arg$1;
    private final Tariff arg$2;

    SubscriptionsAdapter$SubscriptionViewHolder$$Lambda$0(SubscriptionViewHolder subscriptionViewHolder, Tariff tariff) {
        this.arg$1 = subscriptionViewHolder;
        this.arg$2 = tariff;
    }

    public void onClick(View view) {
        this.arg$1.lambda$bindData$272$SubscriptionsAdapter$SubscriptionViewHolder(this.arg$2, view);
    }
}
