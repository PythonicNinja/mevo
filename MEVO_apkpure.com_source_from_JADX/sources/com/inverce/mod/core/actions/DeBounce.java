package com.inverce.mod.core.actions;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import com.inverce.mod.core.IM;
import com.inverce.mod.core.Log;
import com.inverce.mod.core.functional.Aggregator;
import com.inverce.mod.core.functional.IConsumer;
import com.inverce.mod.core.functional.IsEqual;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class DeBounce<T> {
    protected long delay = 500;
    protected T evt;
    @Nullable
    protected ScheduledFuture<?> feature;
    protected IConsumer<T> report;

    static final /* synthetic */ Object lambda$aggregateUseNew$0$DeBounce(Object obj, Object obj2) {
        return obj2;
    }

    static final /* synthetic */ Object lambda$aggregateUseOld$1$DeBounce(Object obj, Object obj2) {
        return obj2;
    }

    final /* bridge */ /* synthetic */ boolean bridge$lambda$0$DeBounce(Object obj, Object obj2) {
        return defaultEquals(obj, obj2);
    }

    final /* bridge */ /* synthetic */ void bridge$lambda$1$DeBounce() {
        internalReport();
    }

    @NonNull
    public DeBounce<T> setConsumer(IConsumer<T> iConsumer) {
        this.report = iConsumer;
        return this;
    }

    @NonNull
    public DeBounce<T> setDelay(long j) {
        this.delay = j;
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

    @NonNull
    public static <T> Aggregator<T> aggregateUseNew() {
        return DeBounce$$Lambda$0.$instance;
    }

    @NonNull
    public static <T> Aggregator<T> aggregateUseOld() {
        return DeBounce$$Lambda$1.$instance;
    }

    public synchronized void post(@NonNull T t) {
        post(t, new DeBounce$$Lambda$2(this), aggregateUseNew());
    }

    public synchronized void post(@NonNull T t, @NonNull IsEqual<T> isEqual) {
        post(t, isEqual, aggregateUseNew());
    }

    public synchronized void post(@NonNull T t, @NonNull Aggregator<T> aggregator) {
        post(t, new DeBounce$$Lambda$3(this), aggregator);
    }

    public synchronized void post(@NonNull T t, @NonNull IsEqual<T> isEqual, @NonNull Aggregator<T> aggregator) {
        if (this.evt == null) {
            this.evt = t;
            scheduleTask();
        } else if (isEqual.isEqual(this.evt, t) == null) {
            internalReport();
            cancel();
            this.evt = t;
            scheduleTask();
        } else {
            this.evt = aggregator.apply(this.evt, t);
            scheduleTask();
        }
    }

    private synchronized void internalReport() {
        if (this.evt != null) {
            this.report.accept(this.evt);
            Log.m68w("reporting event");
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
        this.feature = IM.onBg().schedule(new DeBounce$$Lambda$4(this), this.delay, TimeUnit.MILLISECONDS);
    }

    public static OnClickListener deBounce(final int i, @NonNull final OnClickListener onClickListener) {
        return new OnClickListener() {
            @NonNull
            DeBounce<View> deBounce;

            public void onClick(@NonNull View view) {
                this.deBounce.post(view);
            }
        };
    }
}
