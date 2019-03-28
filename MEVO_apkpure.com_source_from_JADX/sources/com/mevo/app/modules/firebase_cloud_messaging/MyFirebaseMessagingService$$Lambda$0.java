package com.mevo.app.modules.firebase_cloud_messaging;

import com.mevo.app.data.model.event.EventDataBikeReturn;
import com.mevo.app.modules.firebase_cloud_messaging.MyFirebaseMessagingService.PushData;
import org.greenrobot.eventbus.EventBus;

final /* synthetic */ class MyFirebaseMessagingService$$Lambda$0 implements Runnable {
    private final PushData arg$1;

    MyFirebaseMessagingService$$Lambda$0(PushData pushData) {
        this.arg$1 = pushData;
    }

    public void run() {
        EventBus.getDefault().post(new EventDataBikeReturn(this.arg$1.bikeNumber));
    }
}
