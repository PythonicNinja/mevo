package com.mevo.app.data.repository;

import com.mevo.app.data.model.event.EventDataSubscriptionPurchased;
import org.greenrobot.eventbus.EventBus;

final /* synthetic */ class TariffRepository$$Lambda$9 implements Runnable {
    static final Runnable $instance = new TariffRepository$$Lambda$9();

    private TariffRepository$$Lambda$9() {
    }

    public void run() {
        EventBus.getDefault().post(new EventDataSubscriptionPurchased());
    }
}
