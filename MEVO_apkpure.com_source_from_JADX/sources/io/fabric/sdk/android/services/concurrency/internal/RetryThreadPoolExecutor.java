package io.fabric.sdk.android.services.concurrency.internal;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

public class RetryThreadPoolExecutor extends ScheduledThreadPoolExecutor {
    private final Backoff backoff;
    private final RetryPolicy retryPolicy;

    public RetryThreadPoolExecutor(int i, RetryPolicy retryPolicy, Backoff backoff) {
        this(i, Executors.defaultThreadFactory(), retryPolicy, backoff);
    }

    public RetryThreadPoolExecutor(int i, ThreadFactory threadFactory, RetryPolicy retryPolicy, Backoff backoff) {
        super(i, threadFactory);
        if (retryPolicy == null) {
            throw new NullPointerException("retry policy must not be null");
        } else if (backoff == null) {
            throw new NullPointerException("backoff must not be null");
        } else {
            this.retryPolicy = retryPolicy;
            this.backoff = backoff;
        }
    }

    public Future<?> scheduleWithRetry(Runnable runnable) {
        return scheduleWithRetryInternal(Executors.callable(runnable));
    }

    public <T> Future<T> scheduleWithRetry(Runnable runnable, T t) {
        return scheduleWithRetryInternal(Executors.callable(runnable, t));
    }

    public <T> Future<T> scheduleWithRetry(Callable<T> callable) {
        return scheduleWithRetryInternal(callable);
    }

    private <T> Future<T> scheduleWithRetryInternal(Callable<T> callable) {
        if (callable == null) {
            throw new NullPointerException();
        }
        Object retryFuture = new RetryFuture(callable, new RetryState(this.backoff, this.retryPolicy), this);
        execute(retryFuture);
        return retryFuture;
    }

    public RetryPolicy getRetryPolicy() {
        return this.retryPolicy;
    }

    public Backoff getBackoff() {
        return this.backoff;
    }
}
