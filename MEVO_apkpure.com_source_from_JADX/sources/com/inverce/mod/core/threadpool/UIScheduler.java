package com.inverce.mod.core.threadpool;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UIScheduler implements Executor {
    protected static ScheduledThreadPoolExecutor scheduler;
    @NonNull
    protected final Handler uiHandler = new Handler(Looper.getMainLooper());

    public UIScheduler() {
        if (scheduler == null) {
            scheduler = new ScheduledThreadPoolExecutor(1, new NamedThreadPool("UI-Scheduler"));
            scheduler.setKeepAliveTime(2, TimeUnit.SECONDS);
            scheduler.allowCoreThreadTimeOut(true);
        }
    }

    @NonNull
    public ScheduledFuture<?> schedule(@NonNull Runnable runnable, long j, @NonNull TimeUnit timeUnit) {
        return scheduler.schedule(wrap(runnable), j, timeUnit);
    }

    @NonNull
    public <V> ScheduledFuture<V> schedule(@NonNull Callable<V> callable, long j, @NonNull TimeUnit timeUnit) {
        return scheduler.schedule(callable, j, timeUnit);
    }

    @NonNull
    public ScheduledFuture<?> scheduleAtFixedRate(@NonNull Runnable runnable, long j, long j2, @NonNull TimeUnit timeUnit) {
        return scheduler.scheduleAtFixedRate(wrap(runnable), j, j2, timeUnit);
    }

    @NonNull
    public ScheduledFuture<?> scheduleWithFixedDelay(@NonNull Runnable runnable, long j, long j2, @NonNull TimeUnit timeUnit) {
        return scheduler.scheduleWithFixedDelay(wrap(runnable), j, j2, timeUnit);
    }

    private Runnable wrap(@NonNull final Runnable runnable) {
        return new Runnable() {
            public void run() {
                UIScheduler.this.execute(runnable);
            }
        };
    }

    public void execute(@NonNull Runnable runnable) {
        this.uiHandler.post(runnable);
    }
}
