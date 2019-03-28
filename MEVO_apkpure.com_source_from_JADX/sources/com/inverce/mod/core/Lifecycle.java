package com.inverce.mod.core;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import com.inverce.mod.core.interfaces.ActivityStateListener;
import com.inverce.mod.core.interfaces.LifecycleState;
import com.inverce.mod.core.internal.IMInternal;

public class Lifecycle {
    private static int activityHash;
    @NonNull
    private static LifecycleState currentLifecycleState = LifecycleState.NotCreated;
    private static ActivityStateListener listener;

    private static class StatesAdapterImpl implements ActivityLifecycleCallbacks {
        private StatesAdapterImpl() {
        }

        public void onActivityCreated(@NonNull Activity activity, Bundle bundle) {
            Lifecycle.onActivityState(LifecycleState.Created, activity, bundle);
        }

        public void onActivityStarted(@NonNull Activity activity) {
            Lifecycle.onActivityState(LifecycleState.Started, activity, null);
        }

        public void onActivityResumed(@NonNull Activity activity) {
            Lifecycle.onActivityState(LifecycleState.Resumed, activity, null);
        }

        public void onActivityPaused(@NonNull Activity activity) {
            Lifecycle.onActivityState(LifecycleState.Paused, activity, null);
        }

        public void onActivityStopped(@NonNull Activity activity) {
            Lifecycle.onActivityState(LifecycleState.Stopped, activity, null);
        }

        public void onActivitySaveInstanceState(@NonNull Activity activity, Bundle bundle) {
            Lifecycle.onActivityState(LifecycleState.SaveInstanceState, activity, bundle);
        }

        public void onActivityDestroyed(@NonNull Activity activity) {
            Lifecycle.onActivityState(LifecycleState.Destroyed, activity, null);
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static void initialize() {
        if (!IMInternal.get().isInEdit()) {
            IM.application().registerActivityLifecycleCallbacks(new StatesAdapterImpl());
        }
    }

    public static void setListener(ActivityStateListener activityStateListener) {
        listener = activityStateListener;
    }

    @NonNull
    public static LifecycleState getActivityState() {
        return currentLifecycleState;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    static synchronized void onActivityState(@android.support.annotation.NonNull com.inverce.mod.core.interfaces.LifecycleState r3, @android.support.annotation.NonNull android.app.Activity r4, android.os.Bundle r5) {
        /*
        r0 = com.inverce.mod.core.Lifecycle.class;
        monitor-enter(r0);
        r1 = r3.ordinal();	 Catch:{ all -> 0x0035 }
        r2 = 3;
        if (r1 <= r2) goto L_0x0014;
    L_0x000a:
        r1 = r4.hashCode();	 Catch:{ all -> 0x0035 }
        r2 = activityHash;	 Catch:{ all -> 0x0035 }
        if (r1 == r2) goto L_0x0014;
    L_0x0012:
        monitor-exit(r0);
        return;
    L_0x0014:
        currentLifecycleState = r3;	 Catch:{ all -> 0x0035 }
        r1 = r3.ordinal();	 Catch:{ all -> 0x0035 }
        r2 = 4;
        if (r1 >= r2) goto L_0x002a;
    L_0x001d:
        r1 = r4.hashCode();	 Catch:{ all -> 0x0035 }
        activityHash = r1;	 Catch:{ all -> 0x0035 }
        r1 = com.inverce.mod.core.internal.IMInternal.get();	 Catch:{ all -> 0x0035 }
        r1.setActivity(r4);	 Catch:{ all -> 0x0035 }
    L_0x002a:
        r1 = listener;	 Catch:{ all -> 0x0035 }
        if (r1 == 0) goto L_0x0033;
    L_0x002e:
        r1 = listener;	 Catch:{ all -> 0x0035 }
        r1.activityStateChanged(r3, r4, r5);	 Catch:{ all -> 0x0035 }
    L_0x0033:
        monitor-exit(r0);
        return;
    L_0x0035:
        r3 = move-exception;
        monitor-exit(r0);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inverce.mod.core.Lifecycle.onActivityState(com.inverce.mod.core.interfaces.LifecycleState, android.app.Activity, android.os.Bundle):void");
    }
}
