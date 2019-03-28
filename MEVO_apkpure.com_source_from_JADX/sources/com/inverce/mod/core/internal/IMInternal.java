package com.inverce.mod.core.internal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import com.inverce.mod.core.Lifecycle;
import com.inverce.mod.core.threadpool.DefaultHandlerThread;
import com.inverce.mod.core.threadpool.DynamicScheduledExecutor;
import com.inverce.mod.core.threadpool.UIScheduler;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

@SuppressLint({"StaticFieldLeak"})
@RestrictTo({Scope.LIBRARY_GROUP})
public class IMInternal {
    private static boolean inEdit;
    private static IMInternal internal;
    @NonNull
    private WeakReference<Activity> activity = new WeakReference(null);
    private DynamicScheduledExecutor bgExecutorService;
    private Context context;
    private DefaultHandlerThread looperHandlerThread;
    private UIScheduler uiExecutor;
    private Handler uiHandler;

    @NonNull
    public static IMInternal get() {
        if (internal != null) {
            return internal;
        }
        IMInternal iMInternal = new IMInternal();
        internal = iMInternal;
        return iMInternal;
    }

    public boolean isInEdit() {
        return inEdit;
    }

    public void setInEdit(boolean z) {
        inEdit = z;
    }

    public void initialize(Context context) {
        this.context = context;
        this.uiHandler = new Handler(Looper.getMainLooper());
        this.bgExecutorService = new DynamicScheduledExecutor();
        this.bgExecutorService.setKeepAliveTime(5, TimeUnit.SECONDS);
        this.uiExecutor = new UIScheduler();
        Lifecycle.initialize();
    }

    public Context getContext() {
        return this.context;
    }

    public Handler getUiHandler() {
        return this.uiHandler;
    }

    public DynamicScheduledExecutor getBgExecutor() {
        return this.bgExecutorService;
    }

    public UIScheduler getUiExecutor() {
        return this.uiExecutor;
    }

    public void setActivity(Activity activity) {
        this.activity = new WeakReference(activity);
    }

    public Activity getActivity() {
        return (Activity) this.activity.get();
    }

    public DefaultHandlerThread getLooperHandlerThread() {
        if (this.looperHandlerThread == null) {
            this.looperHandlerThread = new DefaultHandlerThread();
            this.looperHandlerThread.start();
        }
        return this.looperHandlerThread;
    }
}
