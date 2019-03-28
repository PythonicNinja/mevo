package com.crashlytics.android.answers;

import android.content.Context;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.events.EventsStorageListener;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import io.fabric.sdk.android.services.settings.AnalyticsSettingsData;
import java.util.concurrent.ScheduledExecutorService;

class AnswersEventsHandler implements EventsStorageListener {
    private final Context context;
    final ScheduledExecutorService executor;
    private final AnswersFilesManagerProvider filesManagerProvider;
    private final Kit kit;
    private final SessionMetadataCollector metadataCollector;
    private final HttpRequestFactory requestFactory;
    SessionAnalyticsManagerStrategy strategy = new DisabledSessionAnalyticsManagerStrategy();

    /* renamed from: com.crashlytics.android.answers.AnswersEventsHandler$2 */
    class C03412 implements Runnable {
        C03412() {
        }

        public void run() {
            try {
                SessionAnalyticsManagerStrategy sessionAnalyticsManagerStrategy = AnswersEventsHandler.this.strategy;
                AnswersEventsHandler.this.strategy = new DisabledSessionAnalyticsManagerStrategy();
                sessionAnalyticsManagerStrategy.deleteAllEvents();
            } catch (Throwable e) {
                Fabric.getLogger().mo2497e(Answers.TAG, "Failed to disable events", e);
            }
        }
    }

    /* renamed from: com.crashlytics.android.answers.AnswersEventsHandler$3 */
    class C03423 implements Runnable {
        C03423() {
        }

        public void run() {
            try {
                AnswersEventsHandler.this.strategy.sendEvents();
            } catch (Throwable e) {
                Fabric.getLogger().mo2497e(Answers.TAG, "Failed to send events files", e);
            }
        }
    }

    /* renamed from: com.crashlytics.android.answers.AnswersEventsHandler$4 */
    class C03434 implements Runnable {
        C03434() {
        }

        public void run() {
            try {
                SessionEventMetadata metadata = AnswersEventsHandler.this.metadataCollector.getMetadata();
                SessionAnalyticsFilesManager analyticsFilesManager = AnswersEventsHandler.this.filesManagerProvider.getAnalyticsFilesManager();
                analyticsFilesManager.registerRollOverListener(AnswersEventsHandler.this);
                AnswersEventsHandler.this.strategy = new EnabledSessionAnalyticsManagerStrategy(AnswersEventsHandler.this.kit, AnswersEventsHandler.this.context, AnswersEventsHandler.this.executor, analyticsFilesManager, AnswersEventsHandler.this.requestFactory, metadata);
            } catch (Throwable e) {
                Fabric.getLogger().mo2497e(Answers.TAG, "Failed to enable events", e);
            }
        }
    }

    /* renamed from: com.crashlytics.android.answers.AnswersEventsHandler$5 */
    class C03445 implements Runnable {
        C03445() {
        }

        public void run() {
            try {
                AnswersEventsHandler.this.strategy.rollFileOver();
            } catch (Throwable e) {
                Fabric.getLogger().mo2497e(Answers.TAG, "Failed to flush events", e);
            }
        }
    }

    public AnswersEventsHandler(Kit kit, Context context, AnswersFilesManagerProvider answersFilesManagerProvider, SessionMetadataCollector sessionMetadataCollector, HttpRequestFactory httpRequestFactory, ScheduledExecutorService scheduledExecutorService) {
        this.kit = kit;
        this.context = context;
        this.filesManagerProvider = answersFilesManagerProvider;
        this.metadataCollector = sessionMetadataCollector;
        this.requestFactory = httpRequestFactory;
        this.executor = scheduledExecutorService;
    }

    public void processEventAsync(Builder builder) {
        processEvent(builder, false, false);
    }

    public void processEventAsyncAndFlush(Builder builder) {
        processEvent(builder, false, true);
    }

    public void processEventSync(Builder builder) {
        processEvent(builder, true, false);
    }

    public void setAnalyticsSettingsData(final AnalyticsSettingsData analyticsSettingsData, final String str) {
        executeAsync(new Runnable() {
            public void run() {
                try {
                    AnswersEventsHandler.this.strategy.setAnalyticsSettingsData(analyticsSettingsData, str);
                } catch (Throwable e) {
                    Fabric.getLogger().mo2497e(Answers.TAG, "Failed to set analytics settings data", e);
                }
            }
        });
    }

    public void disable() {
        executeAsync(new C03412());
    }

    public void onRollOver(String str) {
        executeAsync(new C03423());
    }

    public void enable() {
        executeAsync(new C03434());
    }

    public void flushEvents() {
        executeAsync(new C03445());
    }

    void processEvent(final Builder builder, boolean z, final boolean z2) {
        Runnable c03456 = new Runnable() {
            public void run() {
                try {
                    AnswersEventsHandler.this.strategy.processEvent(builder);
                    if (z2) {
                        AnswersEventsHandler.this.strategy.rollFileOver();
                    }
                } catch (Throwable e) {
                    Fabric.getLogger().mo2497e(Answers.TAG, "Failed to process event", e);
                }
            }
        };
        if (z) {
            executeSync(c03456);
        } else {
            executeAsync(c03456);
        }
    }

    private void executeSync(Runnable runnable) {
        try {
            this.executor.submit(runnable).get();
        } catch (Runnable runnable2) {
            Fabric.getLogger().mo2497e(Answers.TAG, "Failed to run events task", runnable2);
        }
    }

    private void executeAsync(Runnable runnable) {
        try {
            this.executor.submit(runnable);
        } catch (Runnable runnable2) {
            Fabric.getLogger().mo2497e(Answers.TAG, "Failed to submit events task", runnable2);
        }
    }
}
