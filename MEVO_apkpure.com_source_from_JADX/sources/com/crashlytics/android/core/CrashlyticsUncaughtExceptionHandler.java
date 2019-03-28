package com.crashlytics.android.core;

import io.fabric.sdk.android.Fabric;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.atomic.AtomicBoolean;

class CrashlyticsUncaughtExceptionHandler implements UncaughtExceptionHandler {
    private final CrashListener crashListener;
    private final UncaughtExceptionHandler defaultHandler;
    private final AtomicBoolean isHandlingException = new AtomicBoolean(null);

    interface CrashListener {
        void onUncaughtException(Thread thread, Throwable th);
    }

    public CrashlyticsUncaughtExceptionHandler(CrashListener crashListener, UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.crashListener = crashListener;
        this.defaultHandler = uncaughtExceptionHandler;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        this.isHandlingException.set(true);
        try {
            this.crashListener.onUncaughtException(thread, th);
        } catch (Throwable e) {
            Fabric.getLogger().mo2497e(CrashlyticsCore.TAG, "An error occurred in the uncaught exception handler", e);
        } catch (Throwable th2) {
            Fabric.getLogger().mo2494d(CrashlyticsCore.TAG, "Crashlytics completed exception processing. Invoking default exception handler.");
            this.defaultHandler.uncaughtException(thread, th);
            this.isHandlingException.set(false);
        }
        Fabric.getLogger().mo2494d(CrashlyticsCore.TAG, "Crashlytics completed exception processing. Invoking default exception handler.");
        this.defaultHandler.uncaughtException(thread, th);
        this.isHandlingException.set(false);
    }

    boolean isHandlingException() {
        return this.isHandlingException.get();
    }
}
