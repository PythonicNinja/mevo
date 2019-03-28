package com.mevo.app.presentation.main.subscriptions;

import android.view.View;
import com.mevo.app.data.model.Tariff;
import com.mevo.app.presentation.dialogs.InfoDialog.OnClickListener;

final /* synthetic */ class SubscriptionsFragment$$Lambda$2 implements OnClickListener {
    private final SubscriptionsFragment arg$1;
    private final Tariff arg$2;

    SubscriptionsFragment$$Lambda$2(SubscriptionsFragment subscriptionsFragment, Tariff tariff) {
        this.arg$1 = subscriptionsFragment;
        this.arg$2 = tariff;
    }

    public void onClick(View view) {
        this.arg$1.lambda$showPurchaseDialog$276$SubscriptionsFragment(this.arg$2, view);
    }
}
