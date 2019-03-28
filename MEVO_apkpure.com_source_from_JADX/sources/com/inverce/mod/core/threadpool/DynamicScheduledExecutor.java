package com.inverce.mod.core.threadpool;

import android.support.annotation.NonNull;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DynamicScheduledExecutor extends ThreadPoolExecutor implements ScheduledExecutorService {
    protected static ScheduledThreadPoolExecutor scheduler;

    public DynamicScheduledExecutor() {
        super(0, Integer.MAX_VALUE, 1, TimeUnit.SECONDS, new SynchronousQueue());
        setThreadFactory(new NamedThreadPool("Dynamic"));
        if (scheduler == null) {
            scheduler = new ScheduledThreadPoolExecutor(1, new NamedThreadPool("Dynamic-Scheduler"));
            scheduler.setKeepAliveTime(1, TimeUnit.SECONDS);
            scheduler.allowCoreThreadTimeOut(true);
        }
    }

    public void setKeepAliveTime(long j, TimeUnit timeUnit) {
        super.setKeepAliveTime(j, timeUnit);
        scheduler.setKeepAliveTime(j, timeUnit);
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
                DynamicScheduledExecutor.this.execute(runnable);
            }
        };
    }
}
