package com.crashlytics.android.beta;

import android.annotation.TargetApi;
import android.app.Activity;
import io.fabric.sdk.android.ActivityLifecycleManager;
import io.fabric.sdk.android.ActivityLifecycleManager.Callbacks;
import java.util.concurrent.ExecutorService;

@TargetApi(14)
class ActivityLifecycleCheckForUpdatesController extends AbstractCheckForUpdatesController {
    private final Callbacks callbacks = new C07321();
    private final ExecutorService executorService;

    /* renamed from: com.crashlytics.android.beta.ActivityLifecycleCheckForUpdatesController$1 */
    class C07321 extends Callbacks {

        /* renamed from: com.crashlytics.android.beta.ActivityLifecycleCheckForUpdatesController$1$1 */
        class C03501 implements Runnable {
            C03501() {
            }

            public void run() {
                ActivityLifecycleCheckForUpdatesController.this.checkForUpdates();
            }
        }

        C07321() {
        }

        public void onActivityStarted(Activity activity) {
            if (ActivityLifecycleCheckForUpdatesController.this.signalExternallyReady() != null) {
                ActivityLifecycleCheckForUpdatesController.this.executorService.submit(new C03501());
            }
        }
    }

    public boolean isActivityLifecycleTriggered() {
        return true;
    }

    public ActivityLifecycleCheckForUpdatesController(ActivityLifecycleManager activityLifecycleManager, ExecutorService executorService) {
        this.executorService = executorService;
        activityLifecycleManager.registerCallbacks(this.callbacks);
    }
}
