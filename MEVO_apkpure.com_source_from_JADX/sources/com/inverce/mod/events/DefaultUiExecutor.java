package com.inverce.mod.events;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class DefaultUiExecutor implements Executor {
    protected Handler handler = new Handler(Looper.getMainLooper());

    public void execute(@NonNull Runnable runnable) {
        this.handler.post(runnable);
    }
}
