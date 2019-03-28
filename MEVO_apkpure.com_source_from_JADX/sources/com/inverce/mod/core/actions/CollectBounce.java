package com.inverce.mod.core.actions;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.inverce.mod.core.IM;
import com.inverce.mod.core.Log;
import com.inverce.mod.core.functional.Aggregator;
import com.inverce.mod.core.functional.IConsumer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class CollectBounce<T> {
    protected long delay = 500;
    protected boolean emitOnDelay;
    protected List<T> events = Collections.synchronizedList(new ArrayList());
    @Nullable
    protected ScheduledFuture<?> feature;
    protected IConsumer<List<T>> report;

    static final /* synthetic */ Object lambda$aggregateUseNew$0$CollectBounce(Object obj, Object obj2) {
        return obj2;
    }

    static final /* synthetic */ Object lambda$aggregateUseOld$1$CollectBounce(Object obj, Object obj2) {
        return obj2;
    }

    final /* bridge */ /* synthetic */ void bridge$lambda$0$CollectBounce() {
        internalReport();
    }

    @NonNull
    public CollectBounce<T> setConsumer(@NonNull IConsumer<List<T>> iConsumer) {
        this.report = iConsumer;
        return this;
    }

    @NonNull
    public CollectBounce<T> setDelay(long j) {
        this.delay = j;
        return this;
    }

    @NonNull
    public CollectBounce<T> emitAfterDelay() {
        this.emitOnDelay = true;
        return this;
    }

    @NonNull
    public CollectBounce<T> emitWhenNoNewEvent() {
        this.emitOnDelay = false;
        return this;
    }

    private boolean defaultEquals(@Nullable T t, T t2) {
        if (t != t2) {
            if (t == null || t.equals(t2) == null) {
                return null;
            }
        }
        return true;
    }

    public static <T> Aggregator<T> aggregateUseNew() {
        return CollectBounce$$Lambda$0.$instance;
    }

    public static <T> Aggregator<T> aggregateUseOld() {
        return CollectBounce$$Lambda$1.$instance;
    }

    public synchronized void post(T t) {
        this.events.add(t);
        if (this.emitOnDelay == null) {
            cancel();
        }
        if (this.emitOnDelay == null || this.feature == null) {
            scheduleTask();
        }
    }

    private synchronized void internalReport() {
        if (this.events != null) {
            this.report.accept(this.events);
            this.events.clear();
            Log.m68w("reporting event");
            this.feature = null;
        }
    }

    public void cancel() {
        if (this.feature != null) {
            this.feature.cancel(false);
            this.feature = null;
        }
    }

    private void scheduleTask() {
        cancel();
        this.feature = IM.onBg().schedule(new CollectBounce$$Lambda$2(this), this.delay, TimeUnit.MILLISECONDS);
    }
}
