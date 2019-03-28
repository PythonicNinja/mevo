package com.mevo.app.tools;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import java.lang.ref.WeakReference;

public class Lifecycle {
    private static final String TAG = "Lifecycle";
    private static State state;
    private static WeakReference<Activity> weakActivity;

    private static class LifecycleListener implements ActivityLifecycleCallbacks {
        private LifecycleListener() {
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            Lifecycle.switchState(activity, State.Created, bundle);
        }

        public void onActivityStarted(Activity activity) {
            Lifecycle.switchState(activity, State.Started, null);
        }

        public void onActivityResumed(Activity activity) {
            Lifecycle.switchState(activity, State.Resumed, null);
        }

        public void onActivityPaused(Activity activity) {
            if (activity == Lifecycle.weakActivity.get()) {
                Lifecycle.switchState(activity, State.Paused, null);
            }
        }

        public void onActivityStopped(Activity activity) {
            if (activity == Lifecycle.weakActivity.get()) {
                Lifecycle.switchState(activity, State.Stopped, null);
            }
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            if (activity == Lifecycle.weakActivity.get()) {
                Lifecycle.switchState(activity, State.SaveInstanceState, bundle);
            }
        }

        public void onActivityDestroyed(Activity activity) {
            if (activity == Lifecycle.weakActivity.get()) {
                Lifecycle.switchState(activity, State.Destroyed, null);
            }
        }
    }

    public enum State {
        Created,
        Started,
        Resumed,
        Paused,
        Stopped,
        SaveInstanceState,
        Destroyed,
        Invalid
    }

    @Nullable
    public static Activity getActivity() {
        return (Activity) weakActivity.get();
    }

    @Nullable
    public static AppCompatActivity getSupportActivity() {
        Activity activity = getActivity();
        return (activity == null || !(activity instanceof AppCompatActivity)) ? null : (AppCompatActivity) activity;
    }

    public static State getState() {
        return state;
    }

    public static void init(Application application) {
        application.registerActivityLifecycleCallbacks(new LifecycleListener());
        weakActivity = new WeakReference(null);
        state = State.Invalid;
    }

    private static void switchState(Activity activity, State state, Bundle bundle) {
        state = state;
        if (state == State.Created || state == State.Started || state == State.Resumed) {
            weakActivity = new WeakReference(activity);
        }
    }
}
