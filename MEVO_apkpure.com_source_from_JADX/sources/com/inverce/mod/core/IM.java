package com.inverce.mod.core;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import com.inverce.mod.core.configuration.extended.LazyValue;
import com.inverce.mod.core.configuration.shared.SharedBoolAutoToggle;
import com.inverce.mod.core.internal.IMInternal;
import com.inverce.mod.core.threadpool.DefaultHandlerThread;
import com.inverce.mod.core.threadpool.DynamicScheduledExecutor;
import com.inverce.mod.core.threadpool.UIScheduler;

@SuppressLint({"StaticFieldLeak"})
public class IM {
    private static final SharedBoolAutoToggle FIRST_RUN = new SharedBoolAutoToggle("im_first_run", true, false);
    private static final LazyValue<Boolean> IS_FIRST_RUN;
    private static final LazyValue<String> SESSION_UUID = new LazyValue(IM$$Lambda$0.$instance);
    @NonNull
    private static IMInternal internal = IMInternal.get();

    static {
        SharedBoolAutoToggle sharedBoolAutoToggle = FIRST_RUN;
        sharedBoolAutoToggle.getClass();
        IS_FIRST_RUN = new LazyValue(IM$$Lambda$1.get$Lambda(sharedBoolAutoToggle));
    }

    @NonNull
    public static Context context() {
        if (internal.getActivity() != null) {
            return internal.getActivity();
        }
        return internal.getContext();
    }

    @NonNull
    public static Application application() {
        return (Application) context().getApplicationContext();
    }

    @Nullable
    public static Activity activity() {
        return internal.getActivity();
    }

    public static FragmentActivity activitySupport() {
        Activity activity = activity();
        return activity instanceof FragmentActivity ? (FragmentActivity) activity : null;
    }

    @NonNull
    public static Resources resources() {
        return context().getResources();
    }

    @NonNull
    public static LayoutInflater inflater() {
        return LayoutInflater.from(context());
    }

    @NonNull
    public static DynamicScheduledExecutor onBg() {
        return internal.getBgExecutor();
    }

    @NonNull
    public static UIScheduler onUi() {
        return internal.getUiExecutor();
    }

    @NonNull
    public static DefaultHandlerThread onLooper() {
        return internal.getLooperHandlerThread();
    }

    public static void enableInEditModeForView(@NonNull View view) {
        if (view.isInEditMode()) {
            IMInternal.get().setInEdit(true);
            IMInitializer.initialize(view.getContext());
        }
    }

    public static boolean isFirstRun() {
        return ((Boolean) IS_FIRST_RUN.get()).booleanValue();
    }

    @NonNull
    public static String sessionUuid() {
        return (String) SESSION_UUID.get();
    }
}
