package com.inverce.mod.core.threadpool;

import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

public class DefaultHandlerThread extends HandlerThread implements Executor {
    protected Handler handler;

    public DefaultHandlerThread() {
        super("Handler-thread", 5);
    }

    public Handler getHandler() {
        if (this.handler == null && getLooper() != null) {
            this.handler = new Handler(getLooper());
        }
        if (this.handler != null) {
            return this.handler;
        }
        throw new IllegalStateException("Looper not prepared, or thread is no longer active");
    }

    public void execute(@NonNull Runnable runnable) {
        getHandler().post(runnable);
    }
}
