package com.crashlytics.android.answers;

import com.mevo.app.presentation.custom_views.TopToast;
import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

class BackgroundManager {
    private static final int BACKGROUND_DELAY = 5000;
    final AtomicReference<ScheduledFuture<?>> backgroundFutureRef = new AtomicReference();
    private final ScheduledExecutorService executorService;
    private volatile boolean flushOnBackground = true;
    boolean inBackground = true;
    private final List<Listener> listeners = new ArrayList();

    /* renamed from: com.crashlytics.android.answers.BackgroundManager$1 */
    class C03461 implements Runnable {
        C03461() {
        }

        public void run() {
            BackgroundManager.this.backgroundFutureRef.set(null);
            BackgroundManager.this.notifyBackground();
        }
    }

    public interface Listener {
        void onBackground();
    }

    public BackgroundManager(ScheduledExecutorService scheduledExecutorService) {
        this.executorService = scheduledExecutorService;
    }

    public void setFlushOnBackground(boolean z) {
        this.flushOnBackground = z;
    }

    private void notifyBackground() {
        for (Listener onBackground : this.listeners) {
            onBackground.onBackground();
        }
    }

    public void registerListener(Listener listener) {
        this.listeners.add(listener);
    }

    public void onActivityResumed() {
        this.inBackground = false;
        ScheduledFuture scheduledFuture = (ScheduledFuture) this.backgroundFutureRef.getAndSet(null);
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
    }

    public void onActivityPaused() {
        if (this.flushOnBackground && !this.inBackground) {
            this.inBackground = true;
            try {
                this.backgroundFutureRef.compareAndSet(null, this.executorService.schedule(new C03461(), TopToast.DURATION_LONG, TimeUnit.MILLISECONDS));
            } catch (Throwable e) {
                Fabric.getLogger().mo2495d(Answers.TAG, "Failed to schedule background detector", e);
            }
        }
    }
}
